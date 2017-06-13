package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.web.dto.ProjectDTO;

@Mapper(componentModel = "spring")
public interface ProjectDTOMapper {

  public ProjectDTO fromProject(Project project);

}
