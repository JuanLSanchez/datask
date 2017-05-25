package es.juanlsanchez.datask.service;

import es.juanlsanchez.datask.domain.User;

public interface UserService {

  public User getPrincipal();

  public User getUserWithAuthorities();

}
