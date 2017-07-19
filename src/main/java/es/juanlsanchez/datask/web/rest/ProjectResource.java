package es.juanlsanchez.datask.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.datask.web.dto.ProjectCreateDTO;
import es.juanlsanchez.datask.web.dto.ProjectDetailsDTO;
import es.juanlsanchez.datask.web.manager.ProjectManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ProjectResource.URL)
@Api(value = "Project", tags = "Project")
@Slf4j
public class ProjectResource {


  static final String URL = "${spring.application.prefix}${spring.application.version}/project";
  private static final String BY_PRINCIPAL = "/by_principal";
  private static final String ID_PARAM = "{projectId}";
  private static final String ID = "/id/" + ID_PARAM;

  public static final String SECURITY_URL = URL;
  public static final String SECURITY_BY_PRINCIPAL = BY_PRINCIPAL;
  static final CharSequence SECURITY_ID_PARAM = "{\\d+}";
  public static final String SECURITY_ID = ID.replace(ID_PARAM, SECURITY_ID_PARAM);


  private final ProjectManager projectManager;

  public ProjectResource(final ProjectManager projectManager) {
    this.projectManager = projectManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ProjectDetailsDTO>> findAll(String q, Pageable pageable) {
    log.debug("REST to get all project with pageable={} and q='{}'", pageable, q);

    return ResponseEntity.ok(projectManager.findAll(q, pageable));
  }

  @RequestMapping(value = BY_PRINCIPAL, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ProjectDetailsDTO>> findAllByPrincipal(String q, Pageable pageable) {
    log.debug("REST to get all project by principal with pageable={} and q='{}'", pageable, q);

    return ResponseEntity.ok(projectManager.findAllByPrincipal(q, pageable));
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProjectDetailsDTO> create(
      @Valid @RequestBody ProjectCreateDTO projectCreateDTO) throws URISyntaxException {
    log.debug("REST request to create project {}", projectCreateDTO);

    return ResponseEntity.ok(projectManager.create(projectCreateDTO));
  }

  @RequestMapping(value = ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProjectDetailsDTO> getOne(@PathVariable Long projectId)
      throws URISyntaxException {
    log.debug("REST request to get project {}", projectId);

    return ResponseEntity.ok(projectManager.getOne(projectId));
  }

  @RequestMapping(value = ID, method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProjectDetailsDTO> update(@PathVariable Long projectId,
      @RequestBody ProjectCreateDTO projectCreateDTO) throws URISyntaxException {
    log.debug("REST request to update project {}", projectId);

    return ResponseEntity.ok(projectManager.update(projectCreateDTO, projectId));
  }

}
