package es.juanlsanchez.datask.web.mapper.decorator;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.mapper.UserDTOMapper;

public class UserDTOMapperDecorator implements UserDTOMapper {

  @Autowired
  @Qualifier("delegate")
  private UserDTOMapper delegate;

  @Override
  public UserDTO fromUser(User principal) {
    UserDTO result = delegate.fromUser(principal);
    result.setAuthorities(principal.getAuthorities().stream()
        .map(authority -> authority.getName().toString()).collect(Collectors.toList()));
    return result;
  }

}
