package es.juanlsanchez.datask.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.repository.CompanyRepository;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.CompanyService;

@Service
public class DefaultCompanyService implements CompanyService {

  private final CompanyRepository companyRepository;

  public DefaultCompanyService(final CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Override
  public Company getPrincipalCompany() {
    Company result = null;
    String login = SecurityUtils.getCurrentUserLogin();
    if (!StringUtils.isEmpty(login)) {
      result = getOneByLogin(login);
    }
    return result;
  }

  private Company getOneByLogin(String login) {
    return this.companyRepository.findOneByUserLogin(login)
        .orElseThrow(() -> new IllegalArgumentException("Not found user company"));
  }

}