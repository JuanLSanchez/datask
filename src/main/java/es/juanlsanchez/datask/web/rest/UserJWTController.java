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

import es.juanlsanchez.datask.manager.UserJWTManager;
import es.juanlsanchez.datask.security.jwt.JWTConfigurer;
import es.juanlsanchez.datask.web.dto.JWTTokenDTO;
import es.juanlsanchez.datask.web.dto.LoginDTO;

@RestController
@RequestMapping("/api")
public class UserJWTController {

  private final UserJWTManager userJWTManager;

  @Inject
  public UserJWTController(final UserJWTManager userJWTManager) {
    this.userJWTManager = userJWTManager;

  }

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> authorize(@Valid @RequestBody LoginDTO loginDTO,
      HttpServletResponse response) {

    try {
      JWTTokenDTO jwt = this.userJWTManager.authorize(loginDTO);

      response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt.getIdToken());

      return ResponseEntity.ok(jwt);
    } catch (AuthenticationException exception) {
      return new ResponseEntity<>(
          Collections.singletonMap("AuthenticationException", exception.getLocalizedMessage()),
          HttpStatus.UNAUTHORIZED);
    }
  }
}
