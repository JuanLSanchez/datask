package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.web.dto.ProjectDetailsDTO;

@Mapper(componentModel = "spring")
public interface ProjectDetailsDTOMapper {

  @Mappings({@Mapping(target = "companyId", source = "company.id"),
      @Mapping(target = "budgetId", source = "budget.id")})
  public ProjectDetailsDTO fromProject(Project project);

}
