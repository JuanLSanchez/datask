package es.juanlsanchez.datask.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.Budget;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.repository.BudgetRepository;
import es.juanlsanchez.datask.service.BudgetService;

@Service
public class DefaultBudgetService implements BudgetService {

  private final BudgetRepository budgetRepository;

  public DefaultBudgetService(final BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public Page<Budget> findAll(Pageable pageable) {
    return budgetRepository.findAll(pageable);
  }

  @Override
  public Page<Budget> findAllByPrincipal(User principal, Pageable pageable) {
    Page<Budget> result = budgetRepository.findAllByPrincipal(principal, pageable);
    return result;
  }

  @Override
  public Budget create(Budget budget) {
    if (!budget.isNew()) {
      throw new IllegalArgumentException("The budget already exist");
    }
    checkBudget(budget);
    return this.budgetRepository.save(budget);
  }

  @Override
  public Page<Budget> findAllByProjectId(Long projectId, Pageable pageable) {
    return this.budgetRepository.findAllByProjectId(projectId, pageable);
  }

  @Override
  public Budget getOne(Long budgetId) {
    return this.findOne(budgetId)
        .orElseThrow(() -> new IllegalArgumentException("Not found budget " + budgetId));
  }

  @Override
  public Optional<Budget> findOne(Long budgetId) {
    return Optional.ofNullable(this.budgetRepository.getOne(budgetId));
  }

  @Override
  public Budget update(Budget budget) {
    if (budget.isNew()) {
      throw new IllegalArgumentException("The budget not exist");
    }
    checkBudget(budget);
    return this.budgetRepository.save(budget);
  }


  // Utilities -------------------------
  private void checkBudget(Budget budget) {
    if (budget.getStartDate().isAfter(budget.getEndDate())) {
      throw new IllegalArgumentException("Start date cannot be after end date");
    }
  }

}
