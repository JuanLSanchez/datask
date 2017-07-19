package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.BudgetCreateDTO;
import es.juanlsanchez.datask.web.dto.BudgetDetailsDTO;

public interface BudgetManager {

  public Page<BudgetDetailsDTO> findAll(Pageable pageable);

  public Page<BudgetDetailsDTO> findAllByPrincipal(Pageable pageable);

  public BudgetDetailsDTO create(BudgetCreateDTO budgetCreateDTO);

  public Page<BudgetDetailsDTO> findAllByProject(Long projectId, Pageable pageable);

}
