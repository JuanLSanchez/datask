package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

  public Optional<UserData> findOneByUserLogin(String login);

}
