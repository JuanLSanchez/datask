package es.juanlsanchez.datask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.Project;

public interface ProjectService {

  public Page<Project> findAll(String q, Pageable pageable);

  public Page<Project> findAllByLogin(String login, String q, Pageable pageable);

}
