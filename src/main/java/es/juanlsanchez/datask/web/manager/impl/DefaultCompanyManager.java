package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.service.CompanyService;
import es.juanlsanchez.datask.web.dto.CompanyDetailsDTO;
import es.juanlsanchez.datask.web.manager.CompanyManager;
import es.juanlsanchez.datask.web.mapper.CompanyDetailsDTOMapper;

@Component
public class DefaultCompanyManager implements CompanyManager {

  private final CompanyService companyService;
  private final CompanyDetailsDTOMapper companyDetailsDTOMapper;

  public DefaultCompanyManager(CompanyService companyService,
      CompanyDetailsDTOMapper companyDetailsDTOMapper) {
    this.companyService = companyService;
    this.companyDetailsDTOMapper = companyDetailsDTOMapper;
  }

  @Override
  public Page<CompanyDetailsDTO> findAll(String q, Pageable pageable) {
    Page<Company> result = companyService.findAll(q, pageable);
    return result.map(company -> this.companyDetailsDTOMapper.fromCompany(company));
  }

}
