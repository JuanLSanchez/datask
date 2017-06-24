package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Budget;
import es.juanlsanchez.datask.web.dto.BudgetDetailsDTO;

@Mapper(componentModel = "spring")
public interface BudgetDetailsDTOMapper {

  @Mappings({@Mapping(target = "projectId", source = "project.id")})
  public BudgetDetailsDTO fromBudget(Budget budget);

}
