package es.juanlsanchez.datask.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.Company;

public interface CompanyService {

  public Company getPrincipalCompany();

  public Company getOne(Long id);

  public Optional<Company> findOne(Long id);

  public Page<Company> findAll(String q, Pageable pageable);

  public Company create(Company company);

}
