package es.juanlsanchez.datask.service.impl;

import java.util.Optional;

import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;
import es.juanlsanchez.datask.repository.ProjectRepository;
import es.juanlsanchez.datask.service.ProjectService;
import es.juanlsanchez.datask.service.UserProjectService;

@Service
public class DefaultProjectService implements ProjectService {

  private final ProjectRepository projectRepository;
  private final UserProjectService userProjectService;

  public DefaultProjectService(final ProjectRepository projectRepository,
      final UserProjectService userProjectService) {
    this.projectRepository = projectRepository;
    this.userProjectService = userProjectService;
  }

  @Override
  public Page<Project> findAll(String q, Pageable pageable) {
    String regex = q == null ? null : "%" + q + "%";
    return projectRepository.findAll(regex, pageable);
  }

  @Override
  public Page<Project> findAllByLogin(String login, String q, Pageable pageable) {
    if (Strings.isEmpty(login)) {
      throw new IllegalArgumentException("Not found principal");
    }
    String regex = q == null ? null : "%" + q + "%";
    return projectRepository.findAllByLogin(login, regex, pageable);
  }

  @Override
  public Project create(Project project, User manager) {
    if (!project.isNew()) {
      throw new IllegalArgumentException("The project has been created yet");
    }

    // Save project
    Project result = projectRepository.save(project);
    // Create manager user
    UserProject userProject = new UserProject(EnumProjectUser.MANAGER, project, manager);
    userProjectService.save(userProject);
    result.setUserProject(Sets.newHashSet(userProject));

    return result;
  }

  @Override
  public Project update(Project project) {
    if (project.isNew()) {
      throw new IllegalArgumentException("The project is new");
    }
    checkProject(project);
    return projectRepository.save(project);
  }

  private void checkProject(Project project) {
    if (project.getUserProject().size() < 0) {
      throw new IllegalArgumentException("Project without user");
    }
    if (!project.getUserProject().stream()
        .anyMatch(userProject -> userProject.getRelationType().equals(EnumProjectUser.MANAGER))) {
      throw new IllegalArgumentException("Project without user manager");
    }
  }

  @Override
  public Project getOneByPrincipal(User principal, Long projectId) {
    return findOneByPrincipal(principal, projectId)
        .orElseThrow(() -> new IllegalArgumentException("Not found project"));
  }

  @Override
  public Optional<Project> findOneByPrincipal(User principal, Long projectId) {
    return Optional.ofNullable(this.projectRepository.findOneFetchUserProject(projectId))
        .filter(project -> project.getUserProject().stream()
            .anyMatch(userProject -> userProject.getRelationType().equals(EnumProjectUser.MANAGER)
                && userProject.getUser().equals(principal)));
  }

}
