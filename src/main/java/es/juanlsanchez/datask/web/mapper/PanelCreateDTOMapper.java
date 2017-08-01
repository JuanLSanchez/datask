package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.juanlsanchez.datask.domain.Panel;
import es.juanlsanchez.datask.web.dto.PanelCreateDTO;

@Mapper(componentModel = "spring")
public interface PanelCreateDTOMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "project", ignore = true),
      @Mapping(target = "tasks", ignore = true)})
  public Panel fromPanel(PanelCreateDTO panelCreateDTO);

}
