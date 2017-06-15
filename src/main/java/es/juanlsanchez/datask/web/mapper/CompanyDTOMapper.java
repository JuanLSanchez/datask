package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.web.dto.CompanyDTO;

@Mapper(componentModel = "spring")
public interface CompanyDTOMapper {

  public CompanyDTO fromCompany(Company principalCompany);

}
