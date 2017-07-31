package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

  public Page<UserProject> findAllByProjectId(Long projectId, Pageable pageable);

  public Page<UserProject> findAllByUserId(Long userId, Pageable pageable);

  public Optional<UserProject> findOneByUserIdAndProjectIdAndRelationType(Long userId,
      Long projecId, EnumProjectUser relationType);

}
