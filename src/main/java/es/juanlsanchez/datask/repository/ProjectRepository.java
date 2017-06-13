package es.juanlsanchez.datask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("select project from Project project where :q is null or project.name like :q")
  public Page<Project> findAll(@Param("q") String q, Pageable pageable);

}
