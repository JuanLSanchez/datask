package es.juanlsanchez.datask.web.mapper.decorator;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.mapper.UserMapper;

public class UserMapperDecorator implements UserMapper {

  @Autowired
  @Qualifier("delegate")
  private UserMapper delegate;

  @Override
  public UserDTO userToUserDTO(User principal) {
    UserDTO result = delegate.userToUserDTO(principal);
    result.setAuthorities(principal.getAuthorities().stream()
        .map(authority -> authority.getName().toString()).collect(Collectors.toList()));
    return result;
  }

}
