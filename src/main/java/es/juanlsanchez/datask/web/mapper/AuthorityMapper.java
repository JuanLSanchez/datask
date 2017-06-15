package es.juanlsanchez.datask.web.mapper;

import org.mapstruct.Mapper;

import es.juanlsanchez.datask.domain.Authority;
import es.juanlsanchez.datask.security.RolEnum;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {


  public default String toString(Authority authority) {
    return authority.getName().role();
  }

  public default Authority fromRolEnum(RolEnum rolEnum) {
    return new Authority(rolEnum);
  }

}
