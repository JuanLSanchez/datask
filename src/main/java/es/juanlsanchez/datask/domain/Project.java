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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.enumeration.EnumProjectStatus;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumProjectStatus status;

  @Column(name = "completed_estimated")
  private Integer completedEstimated;

  @OneToMany(mappedBy = "project")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Panel> panels = new HashSet<>();

  @OneToOne
  @JoinColumn(unique = true)
  private Budget budget;

  @OneToMany(mappedBy = "project")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Notification> notifications = new HashSet<>();

  @ManyToOne
  private Company company;

  @OneToOne(mappedBy = "project")
  @JsonIgnore
  private UserProject userProject;

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

  public EnumProjectStatus getStatus() {
    return status;
  }

  public void setStatus(EnumProjectStatus status) {
    this.status = status;
  }

  public Integer getCompletedEstimated() {
    return completedEstimated;
  }

  public void setCompletedEstimated(Integer completedEstimated) {
    this.completedEstimated = completedEstimated;
  }

  public Set<Panel> getPanels() {
    return panels;
  }

  public void setPanels(Set<Panel> panels) {
    this.panels = panels;
  }

  public Budget getBudget() {
    return budget;
  }

  public void setBudget(Budget budget) {
    this.budget = budget;
  }

  public Set<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(Set<Notification> notifications) {
    this.notifications = notifications;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public UserProject getUserProject() {
    return userProject;
  }

  public void setUserProject(UserProject userProject) {
    this.userProject = userProject;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    if (project.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, project.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Project{" + "id=" + id + ", name='" + name + "'" + ", status='" + status + "'"
        + ", completedEstimated='" + completedEstimated + "'" + '}';
  }
}
