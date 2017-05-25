package es.juanlsanchez.datask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Income;
import es.juanlsanchez.datask.web.dto.IncomeDTO;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

  @Mappings({@Mapping(target = "id", source = "id"),
      @Mapping(target = "incomeDate", source = "incomeDate"),
      @Mapping(target = "name", source = "name"), @Mapping(target = "nif", source = "nif"),
      @Mapping(target = "base", source = "base"), @Mapping(target = "iva", source = "iva")})
  public IncomeDTO incomeToIncomeDTO(Income income);

  @Mappings({@Mapping(target = "id", ignore = true),
      @Mapping(target = "incomeDate", source = "incomeDate"),
      @Mapping(target = "name", source = "name"), @Mapping(target = "nif", source = "nif"),
      @Mapping(target = "base", source = "base"), @Mapping(target = "iva", source = "iva"),
      @Mapping(target = "principal", ignore = true)})
  public Income incomeDTOToIncome(IncomeDTO income);


  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "principal", ignore = true)})
  public void updateIncome(Income income, @MappingTarget Income incomeTarget);

}
