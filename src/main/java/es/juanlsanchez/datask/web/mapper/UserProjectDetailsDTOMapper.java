package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.web.dto.UserProjectDetailsDTO;

@Mapper(componentModel = "spring")
public interface UserProjectDetailsDTOMapper {

  @Mappings({@Mapping(target = "projectId", source = "project.id"),
      @Mapping(target = "userId", source = "user.id")})
  public UserProjectDetailsDTO fromUserProject(UserProject userProject);

}
