package es.juanlsanchez.datask.web.manager;

import es.juanlsanchez.datask.web.dto.UserDTO;
import es.juanlsanchez.datask.web.dto.UserDataDTO;

public interface AccountManager {

  public UserDTO getPrincipal();

  public UserDataDTO getPrincipalData();

}
