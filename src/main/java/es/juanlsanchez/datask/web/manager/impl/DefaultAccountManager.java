package es.juanlsanchez.datask.web.manager.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.service.UserDataService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.dto.UserDataDTO;
import es.juanlsanchez.datask.web.manager.AccountManager;
import es.juanlsanchez.datask.web.mapper.UserDTOMapper;
import es.juanlsanchez.datask.web.mapper.UserDataDTOMapper;

@Component
public class DefaultAccountManager implements AccountManager {

  private final UserService userService;
  private final UserDataService userDataService;

  private final UserDTOMapper userDTOMapper;
  private final UserDataDTOMapper userDataDTOMapper;


  @Inject
  public DefaultAccountManager(final UserService userService, final UserDTOMapper userMapper,
      final UserDataService userDataService, final UserDataDTOMapper userDataDTOMapper) {
    this.userService = userService;
    this.userDTOMapper = userMapper;
    this.userDataService = userDataService;
    this.userDataDTOMapper = userDataDTOMapper;
  }

  @Override
  public UserDTO getPrincipal() {
    return this.userDTOMapper.fromUser(this.userService.getPrincipal());
  }

  @Override
  public UserDataDTO getPrincipalData() {
    return this.userDataDTOMapper.fromUserData(this.userDataService.getPrincipalData());
  }

}
