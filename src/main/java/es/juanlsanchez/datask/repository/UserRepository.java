package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.juanlsanchez.datask.domain.User;

@Transactional(rollbackFor = Throwable.class)
public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findOneByLogin(String login);

  public Optional<User> findOneById(Long userId);

  @Override
  public void delete(User t);

  @Query("select userData.user from UserData userData "//
      + "where :q is null or "//
      + "userData.name like :q or "//
      + "userData.surname like :q")
  public Page<User> findAll(@Param("q") String regex, Pageable pageable);

}
