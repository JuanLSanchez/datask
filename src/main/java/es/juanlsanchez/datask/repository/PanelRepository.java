package es.juanlsanchez.datask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.juanlsanchez.datask.domain.Panel;

public interface PanelRepository extends JpaRepository<Panel, Long> {

}
