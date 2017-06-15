package es.juanlsanchez.datask.web.manager.impl;

import org.mapstruct.ap.internal.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.domain.Company;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.domain.UserData;
import es.juanlsanchez.datask.security.RolEnum;
import es.juanlsanchez.datask.security.SecurityUtils;
import es.juanlsanchez.datask.service.CompanyService;
import es.juanlsanchez.datask.service.UserDataService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.UserCreateDTO;
import es.juanlsanchez.datask.web.dto.UserDetailsDTO;
import es.juanlsanchez.datask.web.manager.UserManager;
import es.juanlsanchez.datask.web.mapper.UserCreateDTOMapper;
import es.juanlsanchez.datask.web.mapper.UserDetailsDTOMapper;

@Component
public class DefaultUserManager implements UserManager {

  private final UserDetailsDTOMapper userDetailsDTOMapper;
  private final UserCreateDTOMapper userCreateDTOMapper;
  private final UserService userService;
  private final UserDataService userDataService;
  private final CompanyService companyService;

  public DefaultUserManager(final UserDetailsDTOMapper userDetailsDTOMapper,
      final UserService userService, final UserDataService userDataService,
      final UserCreateDTOMapper userCreateDTOMapper, final CompanyService companyService) {
    this.userDetailsDTOMapper = userDetailsDTOMapper;
    this.userService = userService;
    this.userDataService = userDataService;
    this.userCreateDTOMapper = userCreateDTOMapper;
    this.companyService = companyService;
  }

  @Override
  public Page<UserDetailsDTO> findAll(String q, Pageable pageable) {
    return this.userDataService.findAll(q, pageable)
        .map(user -> this.userDetailsDTOMapper.fromUserData(user));
  }

  @Override
  public UserDetailsDTO create(UserCreateDTO userCreateDTO) {
    // Map
    Company company = null;
    if (userCreateDTO.getCompanyId() != null) {
      company = companyService.getOne(userCreateDTO.getCompanyId());
    }
    User user = userCreateDTOMapper.toUser(userCreateDTO);
    UserData userData = userCreateDTOMapper.toUserData(userCreateDTO);

    // Create
    user.setCompany(company);
    user = userService.create(user);
    userData.setUser(user);
    userDataService.create(userData);

    return userDetailsDTOMapper.fromUserData(userData);
  }

  @Override
  public UserDetailsDTO getOne(Long userId) {
    UserData userData = userDataService.getOneByUserId(userId);
    checkPrincipal(userData);
    return userDetailsDTOMapper.fromUserData(userData);
  }

  @Override
  public UserDetailsDTO update(Long userId, UserCreateDTO userCreateDTO) {
    UserData userData = userDataService.getOneByUserId(userId);
    User user = userData.getUser();

    checkPrincipal(userData);

    // Update user if is admin
    if (SecurityUtils.isCurrentUserInRole(RolEnum.ADMIN.role())) {
      // Update user
      userCreateDTOMapper.updateUser(userCreateDTO, user);
      // Update company
      Company company = null;
      if (userCreateDTO.getCompanyId() != null) {
        company = companyService.getOne(userCreateDTO.getCompanyId());
      }
      user.setCompany(company);
      // Save user update
      user = userService.update(user, userCreateDTO.getPassword());
      userData.setUser(user);
    } else if (!Strings.isEmpty(userCreateDTO.getPassword())) {
      user = userService.update(user, userCreateDTO.getPassword());
    }

    // Update user data
    userCreateDTOMapper.updateUserData(userCreateDTO, userData);
    userData = userDataService.update(userData);

    return userDetailsDTOMapper.fromUserData(userData);
  }

  private void checkPrincipal(UserData userData) {
    if (!SecurityUtils.isCurrentUserInRole(RolEnum.ADMIN.role())) {
      String login = SecurityUtils.getCurrentUserLogin();
      if (!login.equals(userData.getUser().getLogin())) {
        throw new IllegalArgumentException("You cannot edit or show this user");
      }
    }
  }

  @Override
  public void delete(Long userId) {
    UserData userData = userDataService.getOneByUserId(userId);
    User user = userData.getUser();

    userDataService.delete(userData);
    userService.delete(user);

  }



}
