package es.juanlsanchez.datask.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.domain.User;

public interface ProjectService {

  public Page<Project> findAll(String q, Pageable pageable);

  public Page<Project> findAllByLogin(String login, String q, Pageable pageable);

  public Project create(Project project, User manager);

  public Project update(Project project);

  public Project getOneByPrincipal(User principal, Long projectId);

  public Optional<Project> findOneByPrincipal(User principal, Long projectId);

  public Project getOne(Long projectId);

  public Optional<Project> findOne(Long projectId);

}
