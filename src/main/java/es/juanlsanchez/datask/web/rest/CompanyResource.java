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

import es.juanlsanchez.datask.web.dto.CompanyCreateDTO;
import es.juanlsanchez.datask.web.dto.CompanyDetailsDTO;
import es.juanlsanchez.datask.web.manager.CompanyManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CompanyResource.URL)
@Api(value = "Company", tags = "Company")
@Slf4j
public class CompanyResource {

  static final String URL = "${spring.application.prefix}${spring.application.version}/company";
  static final String ID_PARAM = "{companyId}";
  static final String ID = "/id/" + ID_PARAM;

  static final CharSequence SECURITY_ID_PARAM = "{\\d+}";
  public static final String SECURITY_URL = URL;
  public static final String SECURITY_ID = ID.replace(ID_PARAM, SECURITY_ID_PARAM);

  private final CompanyManager companyManager;

  public CompanyResource(final CompanyManager companyManager) {
    this.companyManager = companyManager;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<CompanyDetailsDTO>> findAll(String q, Pageable pageable)
      throws URISyntaxException {
    log.debug("REST request to list company");

    return ResponseEntity.ok(companyManager.findAll(q, pageable));
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CompanyDetailsDTO> create(
      @Valid @RequestBody CompanyCreateDTO companyCreateDTO) throws URISyntaxException {
    log.debug("REST request to create company {}", companyCreateDTO);

    return ResponseEntity.ok(companyManager.create(companyCreateDTO));
  }

  @RequestMapping(value = ID, method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CompanyDetailsDTO> update(@PathVariable Long companyId,
      @Valid @RequestBody CompanyCreateDTO companyCreateDTO) throws URISyntaxException {
    log.debug("REST request to updte company {} with {}", companyId, companyCreateDTO);

    return ResponseEntity.ok(companyManager.update(companyId, companyCreateDTO));
  }

  @RequestMapping(value = ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CompanyDetailsDTO> getOne(@PathVariable Long companyId)
      throws URISyntaxException {
    log.debug("REST request to get company {}", companyId);

    return ResponseEntity.ok(companyManager.getOne(companyId));
  }


}
