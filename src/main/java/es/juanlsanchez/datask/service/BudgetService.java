package es.juanlsanchez.datask.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.Budget;
import es.juanlsanchez.datask.domain.User;

public interface BudgetService {

  public Page<Budget> findAll(Pageable pageable);

  public Page<Budget> findAllByPrincipal(User principal, Pageable pageable);

  public Budget create(Budget budget);

  public Page<Budget> findAllByProjectId(Long projectId, Pageable pageable);

  public Budget getOne(Long budgetId);

  public Optional<Budget> findOne(Long budgetId);

  public Budget update(Budget budget);

}
