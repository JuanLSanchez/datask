package es.juanlsanchez.datask.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.juanlsanchez.datask.manager.IncomeManager;
import es.juanlsanchez.datask.web.dto.IncomeDTO;
import es.juanlsanchez.datask.web.dto.RangeDTO;
import es.juanlsanchez.datask.web.util.pagination.HeaderUtil;
import es.juanlsanchez.datask.web.util.pagination.PaginationUtil;
import javassist.NotFoundException;

@Controller
@RequestMapping("/income")
public class IncomeResource {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final IncomeManager incomeManager;

  @Inject
  public IncomeResource(final IncomeManager incomeManager) {
    this.incomeManager = incomeManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<IncomeDTO>> findAllByPrincipal(Pageable pageable)
      throws URISyntaxException {
    log.debug("REST request to list income");

    Page<IncomeDTO> page = incomeManager.findAllByPrincipal(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/income");

    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<IncomeDTO> create(@Valid @RequestBody IncomeDTO income)
      throws URISyntaxException {
    log.debug("REST request to create income: {}", income);

    IncomeDTO incomeCreated = incomeManager.create(income);

    return ResponseEntity.created(new URI("/api/income/" + incomeCreated.getId()))
        .headers(HeaderUtil.createAlert("income", incomeCreated.getId().toString()))
        .body(incomeCreated);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<IncomeDTO> findOne(@PathVariable Long id) throws URISyntaxException {
    log.debug("REST request to find income: {}", id);
    return incomeManager.findOne(id)
        .map(incomeDto -> new ResponseEntity<>(incomeDto, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<IncomeDTO> update(@Valid @RequestBody IncomeDTO income,
      @PathVariable Long id) throws URISyntaxException, NotFoundException {
    log.debug("REST request to update income '{}' with: {}", id, income);

    IncomeDTO incomeCreated = incomeManager.update(income, id);

    return ResponseEntity.ok()
        .headers(HeaderUtil.createAlert("income", incomeCreated.getId().toString()))
        .body(incomeCreated);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id)
      throws URISyntaxException, NotFoundException {
    log.debug("REST request to delete income '{}' with: {}", id);

    incomeManager.delete(id);

    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityDeletionAlert("schedule", id.toString())).build();
  }

  @RequestMapping(value = "/range", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RangeDTO> range() {
    log.debug("REST request to get range");
    return ResponseEntity.ok(this.incomeManager.getRangeByPrincipal());
  }

  // Statistics

  @RequestMapping(value = "/statistic/evolution", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<LocalDate, Double>> evolutionInDaysInTheRange(
      @RequestParam(required = true,
          name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
      @RequestParam(required = true,
          name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
    log.debug("REST request to get evolition in the range {}-{}", start, end);
    return ResponseEntity.ok(this.incomeManager.evolutionInDaysInTheRange(start, end));
  }


}
