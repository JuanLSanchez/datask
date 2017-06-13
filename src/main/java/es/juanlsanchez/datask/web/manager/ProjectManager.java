package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.ProjectDTO;

public interface ProjectManager {

  public Page<ProjectDTO> findAll(String q, Pageable pageable);

}
