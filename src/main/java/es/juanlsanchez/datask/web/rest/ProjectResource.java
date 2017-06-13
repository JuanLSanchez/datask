package es.juanlsanchez.datask.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.datask.web.dto.ProjectDTO;
import es.juanlsanchez.datask.web.manager.ProjectManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ProjectResource.URL)
@Api(value = "Project", tags = "Project")
@Slf4j
public class ProjectResource {


  static final String URL = "${spring.application.prefix}${spring.application.version}/project";

  public static final String SECURITY_URL = URL;

  private final ProjectManager projectManager;

  public ProjectResource(final ProjectManager projectManager) {
    this.projectManager = projectManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ProjectDTO>> findAll(String q, Pageable pageable) {
    log.debug("REST to get all project with pageable={} and q='{}'", pageable, q);

    return ResponseEntity.ok(projectManager.findAll(q, pageable));
  }



}
