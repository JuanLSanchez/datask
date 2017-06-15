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

import es.juanlsanchez.datask.web.dto.UserCreateDTO;
import es.juanlsanchez.datask.web.dto.UserDetailsDTO;
import es.juanlsanchez.datask.web.manager.UserManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(UserResource.URL)
@Api(value = "User", tags = "User")
@Slf4j
public class UserResource {

  static final String URL = "${spring.application.prefix}${spring.application.version}/user";
  static final String ID_PARAM = "{userId}";
  static final String ID = "/id/" + ID_PARAM;

  static final CharSequence SECURITY_ID_PARAM = "{\\d+}";
  public static final String SECURITY_URL = URL;
  public static final String SECURITY_ID = ID.replace(ID_PARAM, SECURITY_ID_PARAM);

  private final UserManager userManager;

  public UserResource(final UserManager userManager) {
    this.userManager = userManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserDetailsDTO>> getPrincipal(String q, Pageable pageable)
      throws URISyntaxException {
    log.debug("REST request to list users");

    return ResponseEntity.ok(userManager.findAll(q, pageable));
  }

  @RequestMapping(value = ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDetailsDTO> getOne(@PathVariable Long userId)
      throws URISyntaxException {
    log.debug("REST request to get user {}", userId);

    return ResponseEntity.ok(userManager.getOne(userId));
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDetailsDTO> create(@Valid @RequestBody UserCreateDTO user)
      throws URISyntaxException {
    log.debug("REST request to create user {}", user);

    return ResponseEntity.ok(userManager.create(user));
  }

  @RequestMapping(value = ID, method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDetailsDTO> update(@PathVariable Long userId,
      @Valid @RequestBody UserCreateDTO user) throws URISyntaxException {
    log.debug("REST request to updte user {} with {}", userId, user);

    return ResponseEntity.ok(userManager.update(userId, user));
  }

  @RequestMapping(value = ID, method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long userId) throws URISyntaxException {
    log.debug("REST request to delete user {}", userId);

    userManager.delete(userId);

    return ResponseEntity.ok().build();
  }

}
