package es.juanlsanchez.datask.domain;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class UserObject extends BaseEntity {

  @ManyToOne
  private User principal;

}
