package es.juanlsanchez.datask.web.manager.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.domain.Budget;
import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.service.BudgetService;
import es.juanlsanchez.datask.service.ProjectService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.BudgetCreateDTO;
import es.juanlsanchez.datask.web.dto.BudgetDetailsDTO;
import es.juanlsanchez.datask.web.manager.BudgetManager;
import es.juanlsanchez.datask.web.mapper.BudgetCreateDTOMapper;
import es.juanlsanchez.datask.web.mapper.BudgetDetailsDTOMapper;


@Component
public class DefaultBudgetManager implements BudgetManager {

  private final BudgetService budgetService;
  private final UserService userService;
  private final ProjectService projectService;

  private final BudgetDetailsDTOMapper budgetDetailsDTOMapper;
  private final BudgetCreateDTOMapper budgetCreateDTOMapper;

  public DefaultBudgetManager(final BudgetService budgetService,
      final BudgetDetailsDTOMapper budgetDetailsDTOMapper,
      final BudgetCreateDTOMapper budgetCreateDTOMapper, final UserService userService,
      final ProjectService projectService) {
    this.budgetCreateDTOMapper = budgetCreateDTOMapper;
    this.budgetDetailsDTOMapper = budgetDetailsDTOMapper;
    this.budgetService = budgetService;
    this.userService = userService;
    this.projectService = projectService;
  }

  @Override
  public Page<BudgetDetailsDTO> findAll(Pageable pageable) {
    return budgetService.findAll(pageable).map(budget -> budgetDetailsDTOMapper.fromBudget(budget));
  }

  @Override
  public Page<BudgetDetailsDTO> findAllByPrincipal(Pageable pageable) {
    User principal = userService.getOneByPrincipal();

    return budgetService.findAllByPrincipal(principal, pageable)
        .map(budget -> budgetDetailsDTOMapper.fromBudget(budget));
  }

  @Override
  public BudgetDetailsDTO create(BudgetCreateDTO budgetCreateDTO) {
    Budget budget = budgetCreateDTOMapper.toBudget(budgetCreateDTO);
    if (budgetCreateDTO.getProjectId() != null) {
      User principal = userService.getOneByPrincipal();
      Project project = projectService.getOneByPrincipal(principal, budgetCreateDTO.getProjectId());

      budget.setProject(project);
      budget = budgetService.create(budget);
      project.setBudget(budget);
      projectService.update(project);
    }

    return budgetDetailsDTOMapper.fromBudget(budget);
  }

}
