package es.juanlsanchez.datask.web.manager.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.manager.AccountManager;
import es.juanlsanchez.datask.web.mapper.UserMapper;

@Component
public class DefaultAccountManager implements AccountManager {

  private final UserService userService;
  private final UserMapper userMapper;


  @Inject
  public DefaultAccountManager(final UserService userService, final UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @Override
  public UserDTO getPrincipal() {
    return this.userMapper.userToUserDTO(this.userService.getPrincipal());
  }

}
