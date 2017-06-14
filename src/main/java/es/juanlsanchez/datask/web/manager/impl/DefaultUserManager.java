package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.manager.UserManager;
import es.juanlsanchez.datask.web.mapper.UserDTOMapper;

@Component
public class DefaultUserManager implements UserManager {

  private final UserDTOMapper userDTOMapper;
  private final UserService userService;

  public DefaultUserManager(final UserDTOMapper userDTOMapper, final UserService userService) {
    this.userDTOMapper = userDTOMapper;
    this.userService = userService;
  }

  @Override
  public Page<UserDTO> findAll(String q, Pageable pageable) {
    return this.userService.findAll(q, pageable).map(user -> this.userDTOMapper.fromUser(user));
  }



}
