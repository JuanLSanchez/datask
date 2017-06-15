package es.juanlsanchez.datask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.User;

public interface UserService {

  public User getPrincipal();

  public User getUserWithAuthorities();

  public Page<User> findAll(String q, Pageable pageable);

  public User create(User user);

  public User update(User user, String newPassword);

  public void delete(User user);

}
