package es.juanlsanchez.datask.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumCompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Company extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_company")
  private EnumCompanyType typeCompany;

  // Relationships ----------------------------------------------

  @OneToMany(mappedBy = Project.A_COMPANY)
  private Set<Project> projects = new HashSet<>();

  @OneToMany(mappedBy = User.A_COMPANY)
  private Set<User> user = new HashSet<>();

  @OneToOne(mappedBy = Subscription.A_SUBSCRIPTION)
  private Subscription subscription;

}
