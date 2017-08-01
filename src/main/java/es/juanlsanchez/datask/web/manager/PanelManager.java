package es.juanlsanchez.datask.web.manager;

import es.juanlsanchez.datask.web.dto.PanelCreateDTO;
import es.juanlsanchez.datask.web.dto.PanelDetailsDTO;

public interface PanelManager {

  public PanelDetailsDTO create(PanelCreateDTO panelCreateDTO);

  public void delete(Long panelId);

}
