package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.juanlsanchez.datask.domain.Panel;
import es.juanlsanchez.datask.web.dto.PanelDetailsDTO;

@Mapper(componentModel = "spring")
public interface PanelDetailsDTOMapper {

  @Mapping(target = "projectId", source = "project.id")
  public PanelDetailsDTO fromPanel(Panel panel);

}
