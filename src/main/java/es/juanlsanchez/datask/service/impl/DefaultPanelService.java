package es.juanlsanchez.datask.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.Panel;
import es.juanlsanchez.datask.repository.PanelRepository;
import es.juanlsanchez.datask.service.PanelService;
import es.juanlsanchez.datask.service.TaskService;

@Service
public class DefaultPanelService implements PanelService {

  private final PanelRepository panelRepository;

  private final TaskService taskService;

  public DefaultPanelService(final PanelRepository panelRepository, final TaskService taskService) {
    this.panelRepository = panelRepository;
    this.taskService = taskService;
  }

  @Override
  public Panel create(Panel panel) {
    panel.setCreationDate(LocalDate.now());
    return panelRepository.save(panel);
  }

  @Override
  public void delete(Long panelId) {
    taskService.deleteAllByPanelId(panelId);
    panelRepository.delete(panelId);
  }

}
