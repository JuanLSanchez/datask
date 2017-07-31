package es.juanlsanchez.datask.web.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.web.dto.UserProjectCreateDTO;
import es.juanlsanchez.datask.web.dto.UserProjectDetailsDTO;

public interface UserProjectManager {

  public Page<UserProjectDetailsDTO> findAllByProjectId(Long projectId, Pageable pageable);

  public Page<UserProjectDetailsDTO> findAllByUserId(Long userId, Pageable pageable);

  public UserProjectDetailsDTO getOne(Long userProjectId);

  public UserProjectDetailsDTO create(UserProjectCreateDTO userProjectCreateDTO);

  public void delete(Long userProjectId);

}
