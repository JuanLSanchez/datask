package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.UserCreateDTO;
import es.juanlsanchez.datask.web.dto.UserDetailsDTO;

public interface UserManager {

  public Page<UserDetailsDTO> findAll(String q, Pageable pageable);

  public UserDetailsDTO create(UserCreateDTO user);

  public UserDetailsDTO getOne(Long userId);

  public UserDetailsDTO update(Long userId, UserCreateDTO user);

}
