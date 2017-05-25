package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;

/**
 * A UserProject.
 */
@Entity
@Table(name = "user_project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserProject implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "relation_type", nullable = false)
  private EnumProjectUser relationType;

  @OneToOne
  @JoinColumn(unique = true)
  private Project project;

  @OneToOne
  @JoinColumn(unique = true)
  private UserData userData;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EnumProjectUser getRelationType() {
    return relationType;
  }

  public void setRelationType(EnumProjectUser relationType) {
    this.relationType = relationType;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
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
    UserProject userProject = (UserProject) o;
    if (userProject.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, userProject.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "UserProject{" + "id=" + id + ", relationType='" + relationType + "'" + '}';
  }
}
