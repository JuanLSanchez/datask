package es.juanlsanchez.datask.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;
import es.juanlsanchez.datask.repository.UserProjectRepository;
import es.juanlsanchez.datask.service.UserProjectService;

@Service
public class DefaultUserProjectService implements UserProjectService {

  private final UserProjectRepository userProjectRepository;

  public DefaultUserProjectService(final UserProjectRepository userProjectRepository) {
    this.userProjectRepository = userProjectRepository;
  }

  @Override
  public UserProject save(UserProject userProject) {
    if (userProject.getProject() == null || userProject.getProject().isNew()) {
      throw new IllegalArgumentException("User project without project");
    }
    if (userProject.getUser() == null || userProject.getUser().isNew()) {
      throw new IllegalArgumentException("User project without user");
    }
    if (userProject.getRelationType() == null) {
      throw new IllegalArgumentException("User project without relation type");
    }
    return userProjectRepository.save(userProject);
  }

  @Override
  public Page<UserProject> findAllByProjectId(Long projectId, Pageable pageable) {
    return userProjectRepository.findAllByProjectId(projectId, pageable);
  }

  @Override
  public Page<UserProject> findAllByUserId(Long userId, Pageable pageable) {
    return userProjectRepository.findAllByUserId(userId, pageable);
  }

  @Override
  public UserProject getOne(Long userProjectId) {
    return findOne(userProjectId)
        .orElseThrow(() -> new IllegalArgumentException("Not found userProject " + userProjectId));
  }

  @Override
  public Optional<UserProject> findOne(Long userProjectId) {
    return Optional.ofNullable(userProjectRepository.findOne(userProjectId));
  }

  @Override
  public UserProject create(UserProject userProject) {
    if (!userProject.isNew()) {
      throw new IllegalArgumentException("UserProject already exist");
    }
    return save(userProject);
  }

  @Override
  public Optional<UserProject> findOneByUserIdAndProjectIdAndRelationType(Long userId,
      Long projecId, EnumProjectUser relationType) {
    return userProjectRepository.findOneByUserIdAndProjectIdAndRelationType(userId, projecId,
        relationType);
  }

  @Override
  public void delete(Long userProjectId) {
    userProjectRepository.delete(userProjectId);
  }

}
