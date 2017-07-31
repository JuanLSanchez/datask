package es.juanlsanchez.datask.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;

public interface UserProjectService {

  public UserProject save(UserProject userProject);

  public Page<UserProject> findAllByProjectId(Long projectId, Pageable pageable);

  public Page<UserProject> findAllByUserId(Long userId, Pageable pageable);

  public UserProject getOne(Long userProjectId);

  public Optional<UserProject> findOne(Long userProjectId);

  public UserProject create(UserProject userProject);

  public Optional<UserProject> findOneByUserIdAndProjectIdAndRelationType(Long userId,
      Long projecId, EnumProjectUser relationType);

  public void delete(Long userProjectId);

}
