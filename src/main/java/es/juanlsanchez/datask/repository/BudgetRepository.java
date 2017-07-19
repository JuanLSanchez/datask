package es.juanlsanchez.datask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.Budget;
import es.juanlsanchez.datask.domain.User;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

  @Query("select project.budget from Project project " //
      + "join project.userProject userProject " //
      + "where userProject.user = :principal")
  public Page<Budget> findAllByPrincipal(@Param("principal") User principal, Pageable pageable);

  public Page<Budget> findAllByProjectId(Long projectId, Pageable pageable);


}
