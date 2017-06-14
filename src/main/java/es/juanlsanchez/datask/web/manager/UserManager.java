package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.UserDetailsDTO;

public interface UserManager {

  public Page<UserDetailsDTO> findAll(String q, Pageable pageable);

}
