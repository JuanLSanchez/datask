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

import es.juanlsanchez.datask.domain.enumeration.EnumProjectOrder;

/**
 * A Preference.
 */
@Entity
@Table(name = "preference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Preference implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "colour")
  private String colour;

  @Column(name = "abreviation")
  private String abreviation;

  @Enumerated(EnumType.STRING)
  @Column(name = "project_order")
  private EnumProjectOrder projectOrder;

  @OneToMany(mappedBy = "preference")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Task> tasks = new HashSet<>();

  @OneToOne(mappedBy = "preference")
  @JsonIgnore
  private UserData userData;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  public String getAbreviation() {
    return abreviation;
  }

  public void setAbreviation(String abreviation) {
    this.abreviation = abreviation;
  }

  public EnumProjectOrder getProjectOrder() {
    return projectOrder;
  }

  public void setProjectOrder(EnumProjectOrder projectOrder) {
    this.projectOrder = projectOrder;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public UserData getUserData() {
    return userData;
  }

  public void setUserData(UserData userData) {
    this.userData = userData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Preference preference = (Preference) o;
    if (preference.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, preference.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Preference{" + "id=" + id + ", colour='" + colour + "'" + ", abreviation='"
        + abreviation + "'" + ", projectOrder='" + projectOrder + "'" + '}';
  }
}
