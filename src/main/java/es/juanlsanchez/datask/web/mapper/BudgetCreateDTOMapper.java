package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Budget;
import es.juanlsanchez.datask.web.dto.BudgetCreateDTO;

@Mapper(componentModel = "spring")
public interface BudgetCreateDTOMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "project", ignore = true)})
  public Budget toBudget(BudgetCreateDTO budgetCreateDTO);

}
