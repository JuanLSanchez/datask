package es.juanlsanchez.datask.web.rest;

import java.util.Collections;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.datask.security.jwt.JWTConfigurer;
import es.juanlsanchez.datask.web.dto.JWTTokenDTO;
import es.juanlsanchez.datask.web.dto.LoginDTO;
import es.juanlsanchez.datask.web.manager.UserJWTManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(UserJWTResource.URL)
@Api(value = "User JWT", tags = "User")
@Slf4j
public class UserJWTResource {


  static final String URL = "${spring.application.prefix}${spring.application.version}/user";
  private static final String AUTHENTICATE = "/authenticate";

  public static final String SECURITY_URL = URL;
  public static final String SECURITY_AUTHENTICATE_URL = AUTHENTICATE;

  private final UserJWTManager userJWTManager;

  @Inject
  public UserJWTResource(final UserJWTManager userJWTManager) {
    this.userJWTManager = userJWTManager;

  }

  @RequestMapping(value = AUTHENTICATE, method = RequestMethod.POST)
  public ResponseEntity<?> authorize(@Valid @RequestBody LoginDTO loginDTO,
      HttpServletResponse response) {

    try {
      JWTTokenDTO jwt = this.userJWTManager.authorize(loginDTO);

      response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt.getIdToken());
      log.debug("Logging user {} with Bearer {}", loginDTO, jwt.getIdToken());

      return ResponseEntity.ok(jwt);

    } catch (AuthenticationException exception) {

      log.debug("Bad logging user {}", loginDTO);

      return new ResponseEntity<>(
          Collections.singletonMap("AuthenticationException", exception.getLocalizedMessage()),
          HttpStatus.UNAUTHORIZED);
    }
  }
}
