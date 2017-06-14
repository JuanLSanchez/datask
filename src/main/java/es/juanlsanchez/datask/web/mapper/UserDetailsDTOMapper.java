package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.UserData;
import es.juanlsanchez.datask.web.dto.UserDetailsDTO;

@Mapper(componentModel = "spring")
public interface UserDetailsDTOMapper {

  @Mappings({@Mapping(target = "login", source = "user.login"),
      @Mapping(target = "creationMoment", source = "user.creationMoment")})
  public UserDetailsDTO fromUser(UserData userData);

}
