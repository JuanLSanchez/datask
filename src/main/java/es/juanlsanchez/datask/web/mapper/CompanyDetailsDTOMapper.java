package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.web.dto.CompanyDetailsDTO;

@Mapper(componentModel = "spring")
public interface CompanyDetailsDTOMapper {

  @Mappings({@Mapping(target = "subscriptionId", source = "subscription.id")})
  public CompanyDetailsDTO fromCompany(Company company);

}
