package es.juanlsanchez.datask.mapper;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.web.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

  public UserDTO userToUserDTO(User principal);

}
