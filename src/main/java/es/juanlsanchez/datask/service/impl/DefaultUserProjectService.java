package es.juanlsanchez.datask.service.impl;

import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.UserProject;
import es.juanlsanchez.datask.repository.UserProjectRepository;
import es.juanlsanchez.datask.service.UserProjectService;

@Service
public class DefaultUserProjectService implements UserProjectService {

  private final UserProjectRepository userProjectRepository;

  public DefaultUserProjectService(final UserProjectRepository userProjectRepository) {
    this.userProjectRepository = userProjectRepository;
  }

  @Override
  public UserProject save(UserProject userProject) {
    if (userProject.getProject() == null || userProject.getProject().isNew()) {
      throw new IllegalArgumentException("User project without project");
    }
    if (userProject.getUser() == null || userProject.getUser().isNew()) {
      throw new IllegalArgumentException("User project without user");
    }
    if (userProject.getRelationType() == null) {
      throw new IllegalArgumentException("User project without relation type");
    }
    return userProjectRepository.save(userProject);
  }

}
