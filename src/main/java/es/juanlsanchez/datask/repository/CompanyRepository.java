package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  public Optional<Company> findOneByUserLogin(String login);

  @Query("select company from Company company "//
      + "where :q is null or "//
      + "company.name like :q or "//
      + "company.address like :q")
  public Page<Company> findAll(@Param("q") String q, Pageable pageable);

}
