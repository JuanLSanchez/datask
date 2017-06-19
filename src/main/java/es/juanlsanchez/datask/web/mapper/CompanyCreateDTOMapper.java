package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.web.dto.CompanyCreateDTO;

@Mapper(componentModel = "spring")
public interface CompanyCreateDTOMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "projects", ignore = true),
      @Mapping(target = "subscription", ignore = true), @Mapping(target = "user", ignore = true)})
  public Company toCompany(CompanyCreateDTO companyCreateDTO);

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "projects", ignore = true),
      @Mapping(target = "subscription", ignore = true), @Mapping(target = "user", ignore = true)})
  public void updateCompany(CompanyCreateDTO companyCreateDTO, @MappingTarget Company company);

}
