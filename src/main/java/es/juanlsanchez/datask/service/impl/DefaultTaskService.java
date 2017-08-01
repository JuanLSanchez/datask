package es.juanlsanchez.datask.service.impl;

import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.repository.TaskRepository;
import es.juanlsanchez.datask.service.TaskService;

@Service
public class DefaultTaskService implements TaskService {

  private final TaskRepository taskRepository;

  public DefaultTaskService(final TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public void deleteAllByPanelId(Long panelId) {
    taskRepository.deleteAllByPanelId(panelId);
  }

}
