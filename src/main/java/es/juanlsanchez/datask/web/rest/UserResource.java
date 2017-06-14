package es.juanlsanchez.datask.web.rest;

import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

  public static final String SECURITY_URL = URL;

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

}
