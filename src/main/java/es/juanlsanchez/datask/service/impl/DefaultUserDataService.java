package es.juanlsanchez.datask.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.juanlsanchez.datask.domain.UserData;
import es.juanlsanchez.datask.repository.UserDataRepository;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.UserDataService;

@Service
public class DefaultUserDataService implements UserDataService {

  private final UserDataRepository userDataRepository;

  public DefaultUserDataService(final UserDataRepository userDataRepository) {
    this.userDataRepository = userDataRepository;
  }

  @Override
  public UserData getPrincipalData() {
    UserData result = null;
    String login = SecurityUtils.getCurrentUserLogin();
    if (!StringUtils.isEmpty(login)) {
      result = getOneByLogin(login);
    }
    return result;
  }

  private UserData getOneByLogin(String login) {
    return userDataRepository.findOneByUserLogin(login)
        .orElseThrow(() -> new IllegalArgumentException("Not found user data"));
  }

  @Override
  public Page<UserData> findAll(String q, Pageable pageable) {
    String regex = q == null ? null : "%" + q + "%";
    return this.userDataRepository.findAll(regex, pageable);
  }

  @Override
  public UserData create(UserData userData) {
    if (!userData.isNew()) {
      throw new IllegalArgumentException("The data has been created yet");
    }
    if (userData.getUser() == null) {
      throw new IllegalArgumentException("Data without user");
    }
    return this.userDataRepository.save(userData);
  }

  @Override
  public UserData getOneByUserId(Long userId) {
    return this.findOneByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("Not found user " + userId));
  }

  @Override
  public UserData update(UserData userData) {
    if (userData.isNew()) {
      throw new IllegalArgumentException("The user data is new");
    }
    if (userData.getUser() == null) {
      throw new IllegalArgumentException("Data without user");
    }

    return this.userDataRepository.save(userData);
  }

  @Override
  public Optional<UserData> findOneByUserId(Long userId) {
    return this.userDataRepository.findOneByUserId(userId);
  }

  @Override
  public void delete(UserData userData) {
    this.userDataRepository.delete(userData);
  }



}
