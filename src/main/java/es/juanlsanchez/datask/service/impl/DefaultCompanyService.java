package es.juanlsanchez.datask.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @Override
  public Company getOne(Long id) {
    return this.findOne(id)
        .orElseThrow(() -> new IllegalArgumentException("Not found company " + id));
  }

  @Override
  public Optional<Company> findOne(Long id) {
    return Optional.ofNullable(this.companyRepository.findOne(id));
  }

  @Override
  public Page<Company> findAll(String q, Pageable pageable) {
    String regex = q == null ? null : "%" + q + "%";
    return this.companyRepository.findAll(regex, pageable);
  }

  @Override
  public Company create(Company company) {
    if (!company.isNew()) {
      throw new IllegalArgumentException("The company has been created yet");
    }

    return this.companyRepository.save(company);
  }

}
