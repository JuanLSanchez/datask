package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.UserData;
import es.juanlsanchez.datask.web.dto.UserDataDTO;

@Mapper(componentModel = "spring")
public interface UserDataDTOMapper {

  public UserDataDTO fromUserData(UserData principalData);

}
