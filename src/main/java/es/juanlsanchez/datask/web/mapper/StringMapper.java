package es.juanlsanchez.datask.web.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.Authority;

@Mapper(componentModel = "spring")
public interface StringMapper {


  public Set<Authority> fromDomainRoleList(Set<Authority> domainRole);

  public default String fromDomainRole(Authority domainRole) {
    return domainRole.getName().role();
  }

}
