package es.juanlsanchez.datask.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.Project;
import es.juanlsanchez.datask.repository.ProjectRepository;
import es.juanlsanchez.datask.service.ProjectService;

@Service
public class DefaultProjectService implements ProjectService {

  private final ProjectRepository projectRepository;

  public DefaultProjectService(final ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public Page<Project> findAll(String q, Pageable pageable) {
    String regex = q == null ? null : "%" + q + "%";
    return projectRepository.findAll(regex, pageable);
  }

}