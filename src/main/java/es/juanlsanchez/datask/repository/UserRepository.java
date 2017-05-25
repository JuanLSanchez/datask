package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import es.juanlsanchez.datask.domain.User;

@Transactional(rollbackFor = Throwable.class)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneById(Long userId);

    @Override
    void delete(User t);

}
