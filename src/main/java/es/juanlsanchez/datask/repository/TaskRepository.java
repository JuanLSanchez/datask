package es.juanlsanchez.datask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.juanlsanchez.datask.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

  public void deleteAllByPanelId(Long panelId);

}
