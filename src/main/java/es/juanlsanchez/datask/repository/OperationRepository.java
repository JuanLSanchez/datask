package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import es.juanlsanchez.datask.domain.Operation;
import es.juanlsanchez.datask.domain.User;

@Transactional(rollbackFor = Throwable.class)
public interface OperationRepository extends JpaRepository<Operation, Long> {

  @Query("select operation from Operation operation "
      + "where operation.principal.login=?#{principal.username} ")
  public Page<Operation> findAllByPrincipal(Pageable pageable);

  public Optional<Operation> findOneByIdAndPrincipal(Long id, User principal);

}
