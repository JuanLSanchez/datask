package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.domain.Subscription;
import es.juanlsanchez.datask.service.CompanyService;
import es.juanlsanchez.datask.service.SubscriptionService;
import es.juanlsanchez.datask.web.dto.CompanyCreateDTO;
import es.juanlsanchez.datask.web.dto.CompanyDetailsDTO;
import es.juanlsanchez.datask.web.manager.CompanyManager;
import es.juanlsanchez.datask.web.mapper.CompanyCreateDTOMapper;
import es.juanlsanchez.datask.web.mapper.CompanyDetailsDTOMapper;

@Component
public class DefaultCompanyManager implements CompanyManager {

  private final CompanyService companyService;
  private final SubscriptionService subscriptionService;
  private final CompanyDetailsDTOMapper companyDetailsDTOMapper;
  private final CompanyCreateDTOMapper companyCreateDTOMapper;

  public DefaultCompanyManager(final CompanyService companyService,
      final CompanyDetailsDTOMapper companyDetailsDTOMapper,
      final SubscriptionService subscriptionService,
      final CompanyCreateDTOMapper companyCreateDTOMapper) {
    this.companyService = companyService;
    this.companyDetailsDTOMapper = companyDetailsDTOMapper;
    this.subscriptionService = subscriptionService;
    this.companyCreateDTOMapper = companyCreateDTOMapper;
  }

  @Override
  public Page<CompanyDetailsDTO> findAll(String q, Pageable pageable) {
    Page<Company> result = companyService.findAll(q, pageable);
    return result.map(company -> this.companyDetailsDTOMapper.fromCompany(company));
  }

  @Override
  public CompanyDetailsDTO create(CompanyCreateDTO companyCreateDTO) {
    Company company = companyCreateDTOMapper.toCompany(companyCreateDTO);

    if (companyCreateDTO.getSubscriptionId() != null) {
      Subscription subscription =
          subscriptionService.getOneByIdWithoutCompany(companyCreateDTO.getSubscriptionId());
      company.setSubscription(subscription);
    }

    return companyDetailsDTOMapper.fromCompany(companyService.create(company));
  }

  @Override
  public CompanyDetailsDTO update(Long companyId, CompanyCreateDTO companyCreateDTO) {
    Company company = companyService.getOne(companyId);
    companyCreateDTOMapper.updateCompany(companyCreateDTO, company);

    if (companyCreateDTO.getSubscriptionId() == null) {
      company.setSubscription(null);
    } else if (company.getSubscription() == null
        || !company.getSubscription().getId().equals(companyCreateDTO.getSubscriptionId())) {
      Subscription subscription =
          subscriptionService.getOneByIdWithoutCompany(companyCreateDTO.getSubscriptionId());
      company.setSubscription(subscription);

    }

    return companyDetailsDTOMapper.fromCompany(companyService.update(company));
  }

  @Override
  public CompanyDetailsDTO getOne(Long companyId) {
    return companyDetailsDTOMapper.fromCompany(this.companyService.getOne(companyId));
  }

}
