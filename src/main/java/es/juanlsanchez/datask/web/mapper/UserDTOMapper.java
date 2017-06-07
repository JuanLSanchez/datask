package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.mapper.decorator.UserDTOMapperDecorator;

@Mapper(componentModel = "spring")
@DecoratedWith(UserDTOMapperDecorator.class)
public interface UserDTOMapper {

  @Mapping(target = "authorities", ignore = true)
  public UserDTO fromUser(User principal);

}
