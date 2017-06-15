package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.web.dto.ProjectDTO;

@Mapper(componentModel = "spring")
public interface ProjectDTOMapper {

  @Mappings({@Mapping(target = "companyId", source = "company.id"),
      @Mapping(target = "budgetId", source = "budget.id")})
  public ProjectDTO fromProject(Project project);

}
