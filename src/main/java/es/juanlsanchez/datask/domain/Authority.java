package es.juanlsanchez.datask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.juanlsanchez.datask.security.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Table(name = "authority")
public class Authority {

  @NotNull
  @Size(min = 0, max = 50)
  @Id
  @Column(length = 50)
  @Enumerated(EnumType.STRING)
  private RolEnum name;

}
