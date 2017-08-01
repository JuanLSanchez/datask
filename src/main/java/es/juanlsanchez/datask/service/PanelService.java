package es.juanlsanchez.datask.service;

import es.juanlsanchez.datask.domain.Panel;

public interface PanelService {

  public Panel create(Panel panel);

  public void delete(Long panelId);

}
