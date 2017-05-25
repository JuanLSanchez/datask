package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.enumeration.EnumCompanyType;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_company")
  private EnumCompanyType typeCompany;

  @OneToMany(mappedBy = "company")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Project> projects = new HashSet<>();

  @OneToMany(mappedBy = "company")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<UserData> userData = new HashSet<>();

  @OneToOne(mappedBy = "company")
  @JsonIgnore
  private Subscription subscription;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public EnumCompanyType getTypeCompany() {
    return typeCompany;
  }

  public void setTypeCompany(EnumCompanyType typeCompany) {
    this.typeCompany = typeCompany;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public void setProjects(Set<Project> projects) {
    this.projects = projects;
  }

  public Set<UserData> getUserData() {
    return userData;
  }

  public void setUserData(Set<UserData> userData) {
    this.userData = userData;
  }

  public Subscription getSubscription() {
    return subscription;
  }

  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Company company = (Company) o;
    if (company.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, company.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Company{" + "id=" + id + ", name='" + name + "'" + ", address='" + address + "'"
        + ", typeCompany='" + typeCompany + "'" + '}';
  }
}
