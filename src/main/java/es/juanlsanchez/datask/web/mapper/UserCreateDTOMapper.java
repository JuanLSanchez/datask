package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.domain.UserData;
import es.juanlsanchez.datask.web.dto.UserCreateDTO;

@Mapper(componentModel = "spring", uses = AuthorityMapper.class)
public interface UserCreateDTOMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "company", ignore = true),
      @Mapping(target = "creationMoment", ignore = true)})
  public User toUser(UserCreateDTO userCreateDTO);

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "user", ignore = true)})
  public UserData toUserData(UserCreateDTO userCreateDTO);

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "user", ignore = true)})
  void updateUserData(UserCreateDTO userCreateDTO, @MappingTarget UserData userData);

  @Mappings({@Mapping(target = "id", ignore = true),
      @Mapping(target = "creationMoment", ignore = true),
      @Mapping(target = "company", ignore = true), @Mapping(target = "password", ignore = true)})
  public void updateUser(UserCreateDTO userCreateDTO, @MappingTarget User user);

}
