package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.domain.Panel;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;
import es.juanlsanchez.datask.security.RolEnum;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.PanelService;
import es.juanlsanchez.datask.service.UserProjectService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.PanelCreateDTO;
import es.juanlsanchez.datask.web.dto.PanelDetailsDTO;
import es.juanlsanchez.datask.web.manager.PanelManager;
import es.juanlsanchez.datask.web.mapper.PanelCreateDTOMapper;
import es.juanlsanchez.datask.web.mapper.PanelDetailsDTOMapper;

@Component
public class DefaultPanelManager implements PanelManager {

  private final PanelService panelService;
  private final PanelCreateDTOMapper panelCreateDTOMapper;
  private final PanelDetailsDTOMapper panelDetailsDTOMapper;
  private final UserProjectService userProjectService;
  private final UserService userService;

  public DefaultPanelManager(final PanelService panelService,
      final PanelCreateDTOMapper panelCreateDTOMapper,
      final PanelDetailsDTOMapper panelDetailsDTOMapper,
      final UserProjectService userProjectService, final UserService userService) {
    this.panelService = panelService;
    this.panelCreateDTOMapper = panelCreateDTOMapper;
    this.panelDetailsDTOMapper = panelDetailsDTOMapper;
    this.userProjectService = userProjectService;
    this.userService = userService;
  }

  @Override
  public PanelDetailsDTO create(PanelCreateDTO panelCreateDTO) {
    checkPermissions(panelCreateDTO.getProjectId());
    Panel panel = panelService.create(panelCreateDTOMapper.fromPanel(panelCreateDTO));
    return panelDetailsDTOMapper.fromPanel(panel);
  }

  @Override
  public void delete(Long panelId) {
    checkPermissions(panelId);
    panelService.delete(panelId);

  }

  // Utilities
  private boolean isManagerOfProject(Long projecId) {
    User user = userService.getOneByPrincipal();
    return this.userProjectService
        .findOneByUserIdAndProjectIdAndRelationType(user.getId(), projecId, EnumProjectUser.MANAGER)
        .isPresent();
  }

  private void checkPermissions(Long projecId) {
    if (SecurityUtils.isCurrentUserInAnyRoles(RolEnum.ADMIN.role(), RolEnum.MANAGER.role())
        || !isManagerOfProject(projecId)) {
      throw new IllegalArgumentException("You don't have permissions");
    }
  }

}
