package es.juanlsanchez.datask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.UserProject;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

}
