package es.juanlsanchez.datask.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.repository.UserRepository;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

  @Inject
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String login) {
    log.debug("Authenticating {}", login);
    Optional<User> userFromDatabase = userRepository.findOneByLogin(login);
    return userFromDatabase.map(user -> {
      if (!user.isActivated()) {
        throw new UserNotActivatedException("User " + login + " was not activated");
      }
      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
          .map(authority -> new SimpleGrantedAuthority(authority.getName().role()))
          .collect(Collectors.toList());
      return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
          grantedAuthorities);
    }).orElseThrow(() -> new UsernameNotFoundException(
        "User " + login + " was not found in the " + "database"));
  }
}
