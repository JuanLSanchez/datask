package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  public Optional<Company> findOneByUserLogin(String login);

}
