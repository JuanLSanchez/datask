package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.web.dto.UserProjectCreateDTO;

@Mapper(componentModel = "spring")
public interface UserProjectCreateDTOMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "user", ignore = true),
      @Mapping(target = "project", ignore = true)})
  public UserProject toUserProject(UserProjectCreateDTO userProjectCreateDTO);

}
