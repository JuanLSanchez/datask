package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.web.dto.UserDTO;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface UserDTOMapper {

  public UserDTO fromUser(User user);

}
