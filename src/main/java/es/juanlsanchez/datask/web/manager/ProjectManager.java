package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.ProjectCreateDTO;
import es.juanlsanchez.datask.web.dto.ProjectDetailsDTO;

public interface ProjectManager {

  public Page<ProjectDetailsDTO> findAll(String q, Pageable pageable);

  public Page<ProjectDetailsDTO> findAllByPrincipal(String q, Pageable pageable);

  public ProjectDetailsDTO create(ProjectCreateDTO projectCreateDTO);

  public ProjectDetailsDTO getOne(Long projectId);

}
