package es.juanlsanchez.datask.web.rest;

import java.net.URISyntaxException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.dto.UserDataDTO;
import es.juanlsanchez.datask.web.manager.AccountManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(AccountResource.URL)
@Api(value = "Me", tags = "Account")
@Slf4j
public class AccountResource {

  static final String URL = "${spring.application.prefix}${spring.application.version}/account";
  static final String DATA = "/data";

  public static final String SECURITY_URL = URL;
  public static final String SECURITY_DATA = DATA;

  private final AccountManager accountManager;

  @Inject
  public AccountResource(final AccountManager accountManager) {
    this.accountManager = accountManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> getPrincipal() throws URISyntaxException {
    log.debug("REST request to get account");

    return ResponseEntity.ok(accountManager.getPrincipal());
  }

  @RequestMapping(value = DATA, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDataDTO> getPrincipalData() throws URISyntaxException {
    log.debug("REST request to get data");

    return ResponseEntity.ok(accountManager.getPrincipalData());
  }

}
