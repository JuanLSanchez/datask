package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.ProjectService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.ProjectCreateDTO;
import es.juanlsanchez.datask.web.dto.ProjectDetailsDTO;
import es.juanlsanchez.datask.web.manager.ProjectManager;
import es.juanlsanchez.datask.web.mapper.ProjectCreateDTOMapper;
import es.juanlsanchez.datask.web.mapper.ProjectDetailsDTOMapper;

@Component
public class DefaultProjectManager implements ProjectManager {

  private final ProjectService projectService;
  private final UserService userService;

  private final ProjectDetailsDTOMapper projectDetailsDTOMapper;
  private final ProjectCreateDTOMapper projectCreateDTOMapper;

  public DefaultProjectManager(final ProjectService projectService,
      final ProjectDetailsDTOMapper projectDetailsDTOMapper,
      final ProjectCreateDTOMapper projectCreateDTOMapper, final UserService userService) {
    this.projectService = projectService;
    this.projectDetailsDTOMapper = projectDetailsDTOMapper;
    this.projectCreateDTOMapper = projectCreateDTOMapper;
    this.userService = userService;
  }

  @Override
  public Page<ProjectDetailsDTO> findAll(String q, Pageable pageable) {
    return projectService.findAll(q, pageable)
        .map(project -> projectDetailsDTOMapper.fromProject(project));
  }

  @Override
  public Page<ProjectDetailsDTO> findAllByPrincipal(String q, Pageable pageable) {
    String login = SecurityUtils.getCurrentUserLogin();
    return projectService.findAllByLogin(login, q, pageable)
        .map(project -> projectDetailsDTOMapper.fromProject(project));
  }

  @Override
  public ProjectDetailsDTO create(ProjectCreateDTO projectCreateDTO) {
    return projectDetailsDTOMapper.fromProject(projectService.create(
        projectCreateDTOMapper.toProject(projectCreateDTO), userService.getOneByPrincipal()));
  }

}
