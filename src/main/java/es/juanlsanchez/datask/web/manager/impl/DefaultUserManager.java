package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.service.UserDataService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserDetailsDTO;
import es.juanlsanchez.datask.web.manager.UserManager;
import es.juanlsanchez.datask.web.mapper.UserDetailsDTOMapper;

@Component
public class DefaultUserManager implements UserManager {

  private final UserDetailsDTOMapper userDetailsDTOMapper;
  private final UserService userService;
  private final UserDataService userDataService;

  public DefaultUserManager(final UserDetailsDTOMapper userDetailsDTOMapper,
      final UserService userService, final UserDataService userDataService) {
    this.userDetailsDTOMapper = userDetailsDTOMapper;
    this.userService = userService;
    this.userDataService = userDataService;
  }

  @Override
  public Page<UserDetailsDTO> findAll(String q, Pageable pageable) {
    return this.userDataService.findAll(q, pageable)
        .map(user -> this.userDetailsDTOMapper.fromUser(user));
  }



}
