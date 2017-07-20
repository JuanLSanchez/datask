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
  private static final String ID_PROJECT_PARAM = "{projectId}";
  private static final String BY_PROJECT = "/by_project/id/" + ID_PROJECT_PARAM;
  private static final String ID_PARAM = "{budgetId}";
  private static final String ID = "/id/" + ID_PARAM;

  public static final CharSequence SECURITY_ID_PARAM = "{\\d+}";
  public static final String SECURITY_URL = URL;
  public static final String SECURITY_BY_PRINCIPAL = BY_PRINCIPAL;
  public static final String SECURITY_BY_PROJECT =
      BY_PROJECT.replace(ID_PROJECT_PARAM, SECURITY_ID_PARAM);
  public static final String SECURITY_ID = ID.replace(ID_PARAM, SECURITY_ID_PARAM);


  private final BudgetManager budgetManager;

  public BudgetResource(final BudgetManager budgetManager) {
    this.budgetManager = budgetManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<BudgetDetailsDTO>> findAll(Pageable pageable) {
    log.debug("REST to get all budget with pageable={}", pageable);

    return ResponseEntity.ok(budgetManager.findAll(pageable));
  }

  @RequestMapping(value = BY_PROJECT, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<BudgetDetailsDTO>> findAllByProject(@PathVariable Long projectId,
      Pageable pageable) {
    log.debug("REST to get all budget from project {} with pageable={}", projectId, pageable);

    return ResponseEntity.ok(budgetManager.findAllByProject(projectId, pageable));
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

  @RequestMapping(value = ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BudgetDetailsDTO> getOne(@PathVariable Long budgetId)
      throws URISyntaxException {
    log.debug("REST request to get budget {}", budgetId);

    return ResponseEntity.ok(budgetManager.getOne(budgetId));
  }

  @RequestMapping(value = ID, method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BudgetDetailsDTO> update(@PathVariable Long budgetId,
      @Valid @RequestBody BudgetCreateDTO budgetCreateDTO) throws URISyntaxException {
    log.debug("REST request to update budget{}", budgetId);

    return ResponseEntity.ok(budgetManager.update(budgetCreateDTO, budgetId));
  }

}
