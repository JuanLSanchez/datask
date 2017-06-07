package es.juanlsanchez.datask.service.impl;

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
        .orElseThrow(() -> new IllegalArgumentException("Not found user"));
  }



}
