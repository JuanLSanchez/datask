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

import es.juanlsanchez.datask.web.dto.UserProjectCreateDTO;
import es.juanlsanchez.datask.web.dto.UserProjectDetailsDTO;
import es.juanlsanchez.datask.web.manager.UserProjectManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(UserProjectResource.URL)
@Api(value = "UserProject", tags = "UserProject")
@Slf4j
public class UserProjectResource {

  static final String URL = "${spring.application.prefix}${spring.application.version}/userProject";
  private static final String ID_PARAM = "{userProjectId}";
  private static final String ID = "/id/" + ID_PARAM;
  private static final String USER_ID_PARAM = "{userId}";
  private static final String USER_ID = "/user/id/" + USER_ID_PARAM;
  private static final String PROJECT_ID_PARAM = "{projectId}";
  private static final String PROJECT_ID = "/project/id/" + PROJECT_ID_PARAM;

  static final CharSequence SECURITY_ID_PARAM = "{\\d+}";
  public static final String SECURITY_URL = URL;
  public static final String SECURITY_ID = ID.replace(ID_PARAM, SECURITY_ID_PARAM);
  public static final String SECURITY_USER_ID = USER_ID.replace(USER_ID_PARAM, SECURITY_ID_PARAM);
  public static final String SECURITY_PROJECT_ID =
      PROJECT_ID.replace(PROJECT_ID_PARAM, SECURITY_ID_PARAM);


  private final UserProjectManager userProjectManager;

  public UserProjectResource(final UserProjectManager userProjectManager) {
    this.userProjectManager = userProjectManager;
  }

  @RequestMapping(value = PROJECT_ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserProjectDetailsDTO>> findAllByProjectId(
      @PathVariable Long projectId, Pageable pageable) throws URISyntaxException {
    log.debug("REST request to list users project of project " + projectId);

    return ResponseEntity.ok(userProjectManager.findAllByProjectId(projectId, pageable));
  }

  @RequestMapping(value = USER_ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserProjectDetailsDTO>> findAllByUserId(@PathVariable Long userId,
      Pageable pageable) throws URISyntaxException {
    log.debug("REST request to list projects user of user" + userId);

    return ResponseEntity.ok(userProjectManager.findAllByUserId(userId, pageable));
  }

  @RequestMapping(value = ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserProjectDetailsDTO> getOne(@PathVariable Long userProjectId)
      throws URISyntaxException {
    log.debug("REST request to get userProject" + userProjectId);

    return ResponseEntity.ok(userProjectManager.getOne(userProjectId));
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserProjectDetailsDTO> create(
      @Valid @RequestBody UserProjectCreateDTO userProjectCreateDTO) throws URISyntaxException {
    log.debug("REST request to create userProject {}", userProjectCreateDTO);

    return ResponseEntity.ok(userProjectManager.create(userProjectCreateDTO));
  }

  @RequestMapping(value = ID, method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long userProjectId) throws URISyntaxException {
    log.debug("REST request to delete userProject" + userProjectId);
    userProjectManager.delete(userProjectId);
    return ResponseEntity.ok().build();
  }

}
