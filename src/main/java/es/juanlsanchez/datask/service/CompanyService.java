package es.juanlsanchez.datask.service;

import java.util.Optional;

import es.juanlsanchez.datask.domain.Company;

public interface CompanyService {

  public Company getPrincipalCompany();

  public Company getOne(Long id);

  public Optional<Company> findOne(Long id);

}
