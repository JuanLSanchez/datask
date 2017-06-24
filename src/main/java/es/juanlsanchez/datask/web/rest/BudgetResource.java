package es.juanlsanchez.datask.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.datask.web.dto.BudgetCreateDTO;
import es.juanlsanchez.datask.web.dto.BudgetDetailsDTO;
import es.juanlsanchez.datask.web.manager.BudgetManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BudgetResource.URL)
@Api(value = "Budget", tags = "Budget")
@Slf4j
public class BudgetResource {

  static final String URL = "${spring.application.prefix}${spring.application.version}/budget";
  private static final String BY_PRINCIPAL = "/by_principal";

  public static final String SECURITY_URL = URL;
  public static final String SECURITY_BY_PRINCIPAL = BY_PRINCIPAL;


  private final BudgetManager budgetManager;

  public BudgetResource(final BudgetManager budgetManager) {
    this.budgetManager = budgetManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<BudgetDetailsDTO>> findAll(Pageable pageable) {
    log.debug("REST to get all budget with pageable={}", pageable);

    return ResponseEntity.ok(budgetManager.findAll(pageable));
  }

  @RequestMapping(value = BY_PRINCIPAL, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<BudgetDetailsDTO>> findAllByPrincipal(Pageable pageable) {
    log.debug("REST to get all budget by principal with pageable={}", pageable);

    return ResponseEntity.ok(budgetManager.findAllByPrincipal(pageable));
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BudgetDetailsDTO> create(
      @Valid @RequestBody BudgetCreateDTO budgetCreateDTO) throws URISyntaxException {
    log.debug("REST request to create budget {}", budgetCreateDTO);

    return ResponseEntity.ok(budgetManager.create(budgetCreateDTO));
  }

}
