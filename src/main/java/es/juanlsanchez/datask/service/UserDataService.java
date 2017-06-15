package es.juanlsanchez.datask.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.juanlsanchez.datask.domain.UserData;

public interface UserDataService {

  public UserData getPrincipalData();

  public Page<UserData> findAll(String q, Pageable pageable);

  public UserData create(UserData userData);

  public UserData getOneByUserId(Long userId);

  public Optional<UserData> findOneByUserId(Long userId);

  public UserData update(UserData userData);

  public void delete(UserData userData);

}
