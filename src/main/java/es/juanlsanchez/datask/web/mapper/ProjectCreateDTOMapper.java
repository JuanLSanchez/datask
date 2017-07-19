package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.web.dto.ProjectCreateDTO;

@Mapper(componentModel = "spring")
public interface ProjectCreateDTOMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "company", ignore = true),
      @Mapping(target = "notifications", ignore = true), @Mapping(target = "panels", ignore = true),
      @Mapping(target = "userProject", ignore = true), @Mapping(target = "budget", ignore = true)})
  public Project toProject(ProjectCreateDTO projectCreateDTO);

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "company", ignore = true),
      @Mapping(target = "notifications", ignore = true), @Mapping(target = "panels", ignore = true),
      @Mapping(target = "userProject", ignore = true), @Mapping(target = "budget", ignore = true)})
  public void update(ProjectCreateDTO projectCreateDTO, @MappingTarget Project project);

}
