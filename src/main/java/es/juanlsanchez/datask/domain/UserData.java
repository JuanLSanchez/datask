package es.juanlsanchez.datask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A user data.
 */
@Entity
@Table(name = "user_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserData extends BaseEntity {

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "surname", nullable = false)
  private String surname;

  @NotNull
  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "office_phone")
  private String officePhone;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_user")
  private EnumUserType typeUser;

  // Relationships ----------------------------------------------

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;


}
