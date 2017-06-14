package es.juanlsanchez.datask.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.repository.UserRepository;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.UserService;

@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;

  @Inject
  public DefaultUserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getPrincipal() {
    User result = null;
    String login = SecurityUtils.getCurrentUserLogin();
    if (!StringUtils.isEmpty(login)) {
      result = getOneByLogin(login);
    }
    return result;
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

}
