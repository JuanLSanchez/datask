package es.juanlsanchez.datask.web.manager.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;
import es.juanlsanchez.datask.security.RolEnum;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.ProjectService;
import es.juanlsanchez.datask.service.UserProjectService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserProjectCreateDTO;
import es.juanlsanchez.datask.web.dto.UserProjectDetailsDTO;
import es.juanlsanchez.datask.web.manager.UserProjectManager;
import es.juanlsanchez.datask.web.mapper.UserProjectCreateDTOMapper;
import es.juanlsanchez.datask.web.mapper.UserProjectDetailsDTOMapper;

@Component
public class DefaultUserProjectManager implements UserProjectManager {

  private final UserProjectService userProjectService;
  private final UserService userService;
  private final ProjectService projectService;
  private final UserProjectDetailsDTOMapper userProjectDetailsDTOMapper;
  private final UserProjectCreateDTOMapper userProjectCreateDTOMapper;

  public DefaultUserProjectManager(final UserProjectService userProjectService,
      final UserService userService, final ProjectService projectService,
      final UserProjectDetailsDTOMapper userProjectDetailsDTOMapper,
      final UserProjectCreateDTOMapper userProjectCreateDTOMapper) {
    this.userProjectService = userProjectService;
    this.userService = userService;
    this.projectService = projectService;
    this.userProjectDetailsDTOMapper = userProjectDetailsDTOMapper;
    this.userProjectCreateDTOMapper = userProjectCreateDTOMapper;
  }

  @Override
  public Page<UserProjectDetailsDTO> findAllByProjectId(Long projectId, Pageable pageable) {
    getOneProjectByPrincipal(projectId);
    return userProjectService.findAllByProjectId(projectId, pageable)
        .map(userProject -> userProjectDetailsDTOMapper.fromUserProject(userProject));
  }

  @Override
  public Page<UserProjectDetailsDTO> findAllByUserId(Long userId, Pageable pageable) {
    getOneUserByPrincipal(userId);
    return userProjectService.findAllByUserId(userId, pageable)
        .map(userProject -> userProjectDetailsDTOMapper.fromUserProject(userProject));
  }

  @Override
  public UserProjectDetailsDTO getOne(Long userProjectId) {
    return userProjectDetailsDTOMapper.fromUserProject(userProjectService.getOne(userProjectId));
  }

  @Override
  public UserProjectDetailsDTO create(UserProjectCreateDTO userProjectCreateDTO) {
    UserProject userProject = userProjectCreateDTOMapper.toUserProject(userProjectCreateDTO);
    Project project = getOneProjectByPrincipal(userProjectCreateDTO.getProjectId());
    userProject.setProject(project);
    if (SecurityUtils.isCurrentUserInAnyRoles(RolEnum.ADMIN.role(), RolEnum.MANAGER.role())
        || isManagerOfProject(project.getId())) {
      userProject.setUser(userService.getOne(userProjectCreateDTO.getUserId()));
    } else {
      throw new IllegalArgumentException("You don't have permissions");
    }

    return userProjectDetailsDTOMapper.fromUserProject(userProjectService.create(userProject));
  }

  @Override
  public void delete(Long userProjectId) {
    UserProject userProject = userProjectService.getOne(userProjectId);
    if (SecurityUtils.isCurrentUserInAnyRoles(RolEnum.ADMIN.role(), RolEnum.MANAGER.role())
        || isManagerOfProject(userProject.getProject().getId())) {
      this.userProjectService.delete(userProjectId);
    } else {
      throw new IllegalArgumentException("You don't have permissions");
    }
  }

  // Utilities ----------------------------------------------------

  private boolean isManagerOfProject(Long projecId) {
    User user = userService.getOneByPrincipal();
    return this.userProjectService
        .findOneByUserIdAndProjectIdAndRelationType(user.getId(), projecId, EnumProjectUser.MANAGER)
        .isPresent();
  }

  private Project getOneProjectByPrincipal(Long projectId) {
    Project project;
    User principal = userService.getOneByPrincipal();

    if (SecurityUtils.isCurrentUserInAnyRoles(RolEnum.ADMIN.role(), RolEnum.MANAGER.role())) {
      project = projectService.getOne(projectId);
    } else {
      project = projectService.getOneByPrincipal(principal, projectId);
    }

    return project;
  }

  private User getOneUserByPrincipal(Long userId) {
    User result;
    User principal = userService.getOneByPrincipal();

    if (SecurityUtils.isCurrentUserInAnyRoles(RolEnum.ADMIN.role(), RolEnum.MANAGER.role())) {
      result = userService.getOne(userId);
    } else {
      result = Optional.ofNullable(principal).filter(user -> user.getId().equals(userId))
          .orElseThrow(() -> new IllegalArgumentException("Not found user " + userId));
    }
    return result;
  }

}
