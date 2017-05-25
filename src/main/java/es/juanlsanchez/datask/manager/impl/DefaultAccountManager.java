package es.juanlsanchez.datask.manager.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.manager.AccountManager;
import es.juanlsanchez.datask.mapper.UserMapper;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserDTO;

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
