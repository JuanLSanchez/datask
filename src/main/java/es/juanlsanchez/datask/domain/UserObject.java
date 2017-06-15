package es.juanlsanchez.datask.domain;

import javax.persistence.JoinColumn;
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
  @JoinColumn(name = "user_id")
  private User principal;

}
