package es.juanlsanchez.datask.web.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.service.ProjectService;
import es.juanlsanchez.datask.web.dto.ProjectDTO;
import es.juanlsanchez.datask.web.manager.ProjectManager;
import es.juanlsanchez.datask.web.mapper.ProjectDTOMapper;

@Component
public class DefaultProjectManager implements ProjectManager {

  private final ProjectService projectService;

  private final ProjectDTOMapper projectDTOMapper;

  public DefaultProjectManager(final ProjectService projectService,
      final ProjectDTOMapper projectDTOMapper) {
    this.projectService = projectService;
    this.projectDTOMapper = projectDTOMapper;
  }

  @Override
  public Page<ProjectDTO> findAll(String q, Pageable pageable) {
    return projectService.findAll(q, pageable)
        .map(project -> projectDTOMapper.fromProject(project));
  }

}
