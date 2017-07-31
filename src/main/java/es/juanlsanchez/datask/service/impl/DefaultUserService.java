package es.juanlsanchez.datask.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.mapstruct.ap.internal.util.Strings;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.repository.UserRepository;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.UserService;

@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public DefaultUserService(final UserRepository userRepository,
      final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User getOneByPrincipal() {
    return this.findOneByPrincipal()
        .orElseThrow(() -> new IllegalArgumentException("Not found principal"));
  }

  @Override
  public Optional<User> findOneByPrincipal() {
    User result = null;
    String login = SecurityUtils.getCurrentUserLogin();
    if (!StringUtils.isEmpty(login)) {
      result = getOneByLogin(login);
    }
    return Optional.ofNullable(result);
  }

  private User getOneByLogin(String login) {
    return userRepository.findOneByLogin(login)
        .orElseThrow(() -> new IllegalArgumentException("Not found user"));
  }

  @Override
  public User getUserWithAuthorities() {
    User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
    user.getAuthorities().size(); // eagerly load the association
    return user;
  }

  @Override
  public Page<User> findAll(String q, Pageable pageable) {
    String regex = q == null ? null : "%" + q + "%";
    return this.userRepository.findAll(regex, pageable);
  }

  @Override
  public User create(User user) {
    if (!user.isNew()) {
      throw new IllegalArgumentException("The user has been created yet");
    }
    if (Strings.isEmpty(user.getPassword())) {
      throw new IllegalArgumentException("The password cannot be empty");
    }
    checkDuplicateLogin(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCreationMoment(Instant.now());

    return this.userRepository.save(user);
  }

  @Override
  public User update(User user, String newPassword) {
    if (user.isNew()) {
      throw new IllegalArgumentException("The user is new");
    }
    checkDuplicateLogin(user);
    if (!Strings.isEmpty(newPassword)) {
      user.setPassword(passwordEncoder.encode(newPassword));
    }

    return this.userRepository.save(user);
  }

  private void checkDuplicateLogin(User user) {
    String message = "User " + user.getLogin() + " already exists";
    try {
      if (this.userRepository.findOneByLogin(user.getLogin())
          .filter(userLogin -> !userLogin.equals(user)).isPresent()) {
        throw new IllegalArgumentException(message);
      }
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException(message);
    }
  }

  @Override
  public void delete(User user) {
    this.userRepository.delete(user);
  }

  @Override
  public User getOne(Long userId) {
    return findOne(userId)
        .orElseThrow(() -> new IllegalArgumentException("Not found user" + userId));
  }

  @Override
  public Optional<User> findOne(Long userId) {
    return Optional.ofNullable(this.userRepository.findOne(userId));
  }

}
