package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

  public Optional<UserData> findOneByUserLogin(String login);

  @Query("select distinct userData from UserData userData join userData.user user "//
      + "where :q is null or "//
      + "userData.name like :q or "//
      + "userData.surname like :q")
  public Page<UserData> findAll(@Param("q") String regex, Pageable pageable);

}
