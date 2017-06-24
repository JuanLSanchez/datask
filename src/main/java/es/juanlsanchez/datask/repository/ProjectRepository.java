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

  @Query("select distinct project from Project project join project.userProject userProject join userProject.user user "//
      + "where (:q is null or project.name like :q) " //
      + "and user.login = :login")
  public Page<Project> findAllByLogin(@Param("login") String login, @Param("q") String regex,
      Pageable pageable);

  @Query("select distinct project from Project project " //
      + "join fetch project.userProject userProject " //
      + "join fetch userProject.user where project.id = :id")
  public Project findOneFetchUserProject(@Param("id") Long id);

}
