package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.enumeration.EnumPanelStatus;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskStatus;
import io.swagger.annotations.ApiModel;

/**
 * The Employee entity.
 * 
 */
@ApiModel(description = ""
    + "The Employee entity.                                                   " + "")
@Entity
@Table(name = "panel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Panel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumPanelStatus status;

  @Column(name = "order_panel")
  private Integer orderPanel;

  @Column(name = "creation_date")
  private LocalDate creationDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "default_task_status")
  private EnumTaskStatus defaultTaskStatus;

  @OneToMany(mappedBy = "panel")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Task> tasks = new HashSet<>();

  @ManyToOne
  private Project project;

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

  public EnumPanelStatus getStatus() {
    return status;
  }

  public void setStatus(EnumPanelStatus status) {
    this.status = status;
  }

  public Integer getOrderPanel() {
    return orderPanel;
  }

  public void setOrderPanel(Integer orderPanel) {
    this.orderPanel = orderPanel;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public EnumTaskStatus getDefaultTaskStatus() {
    return defaultTaskStatus;
  }

  public void setDefaultTaskStatus(EnumTaskStatus defaultTaskStatus) {
    this.defaultTaskStatus = defaultTaskStatus;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Panel panel = (Panel) o;
    if (panel.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, panel.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Panel{" + "id=" + id + ", name='" + name + "'" + ", status='" + status + "'"
        + ", orderPanel='" + orderPanel + "'" + ", creationDate='" + creationDate + "'"
        + ", defaultTaskStatus='" + defaultTaskStatus + "'" + '}';
  }
}
