package es.juanlsanchez.datask.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.datask.web.dto.PanelCreateDTO;
import es.juanlsanchez.datask.web.dto.PanelDetailsDTO;
import es.juanlsanchez.datask.web.manager.PanelManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(PanelResource.URL)
@Api(value = "Panel", tags = "Panel")
@Slf4j
public class PanelResource {

  static final String URL = "${spring.application.prefix}${spring.application.version}/panel";
  static final String ID_PARAM = "{panelId}";
  static final String ID = "/id/" + ID_PARAM;

  static final CharSequence SECURITY_ID_PARAM = "{\\d+}";
  public static final String SECURITY_URL = URL;
  public static final String SECURITY_ID = ID.replace(ID_PARAM, SECURITY_ID_PARAM);

  private final PanelManager panelManager;

  public PanelResource(final PanelManager panelManager) {
    this.panelManager = panelManager;
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PanelDetailsDTO> create(@Valid @RequestBody PanelCreateDTO panelCreateDTO)
      throws URISyntaxException {
    log.debug("REST request to create panel {} ", panelCreateDTO);

    return ResponseEntity.ok(panelManager.create(panelCreateDTO));
  }

  @RequestMapping(value = ID, method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long panelId) throws URISyntaxException {
    log.debug("REST request to delete panel {}", panelId);
    panelManager.delete(panelId);
    return ResponseEntity.ok().build();
  }

}
