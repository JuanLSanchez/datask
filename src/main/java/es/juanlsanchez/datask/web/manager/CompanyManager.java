package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.CompanyCreateDTO;
import es.juanlsanchez.datask.web.dto.CompanyDetailsDTO;

public interface CompanyManager {

  public Page<CompanyDetailsDTO> findAll(String q, Pageable pageable);

  public CompanyDetailsDTO create(CompanyCreateDTO companyCreateDTO);

  public CompanyDetailsDTO update(Long companyId, CompanyCreateDTO companyCreateDTO);

  public CompanyDetailsDTO getOne(Long companyId);

}
