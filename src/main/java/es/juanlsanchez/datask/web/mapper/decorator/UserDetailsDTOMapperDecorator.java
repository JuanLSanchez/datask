package es.juanlsanchez.datask.web.mapper.decorator;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import es.juanlsanchez.datask.domain.UserData;
import es.juanlsanchez.datask.web.dto.UserDetailsDTO;
import es.juanlsanchez.datask.web.mapper.UserDetailsDTOMapper;

public class UserDetailsDTOMapperDecorator implements UserDetailsDTOMapper {

  @Autowired
  @Qualifier("delegate")
  private UserDetailsDTOMapper delegate;

  @Override
  public UserDetailsDTO fromUser(UserData userData) {
    UserDetailsDTO result = delegate.fromUser(userData);
    result.setAuthorities(userData.getUser().getAuthorities().stream()
        .map(authority -> authority.getName().toString()).collect(Collectors.toList()));
    return result;
  }

}
