package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.enumeration.EnumTaskComplexity;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskType;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Task implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "task_type")
  private EnumTaskType taskType;

  @Enumerated(EnumType.STRING)
  @Column(name = "complexity")
  private EnumTaskComplexity complexity;

  @Column(name = "estimated_hours")
  private Integer estimatedHours;

  @Column(name = "estimated_minutes")
  private Integer estimatedMinutes;

  @NotNull
  @Column(name = "creation_date", nullable = false)
  private ZonedDateTime creationDate;

  @Column(name = "order_task")
  private Integer orderTask;

  @OneToMany(mappedBy = "task")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Time> times = new HashSet<>();

  @OneToMany(mappedBy = "task")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Comment> comments = new HashSet<>();

  @OneToMany(mappedBy = "task")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Notification> notifications = new HashSet<>();

  @ManyToOne
  private UserData userData;

  @ManyToOne
  private Panel panel;

  @ManyToOne
  private Preference preference;

  @OneToOne(mappedBy = "task")
  @JsonIgnore
  private TaskRequest taskRequest;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EnumTaskType getTaskType() {
    return taskType;
  }

  public void setTaskType(EnumTaskType taskType) {
    this.taskType = taskType;
  }

  public EnumTaskComplexity getComplexity() {
    return complexity;
  }

  public void setComplexity(EnumTaskComplexity complexity) {
    this.complexity = complexity;
  }

  public Integer getEstimatedHours() {
    return estimatedHours;
  }

  public void setEstimatedHours(Integer estimatedHours) {
    this.estimatedHours = estimatedHours;
  }

  public Integer getEstimatedMinutes() {
    return estimatedMinutes;
  }

  public void setEstimatedMinutes(Integer estimatedMinutes) {
    this.estimatedMinutes = estimatedMinutes;
  }

  public ZonedDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(ZonedDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public Integer getOrderTask() {
    return orderTask;
  }

  public void setOrderTask(Integer orderTask) {
    this.orderTask = orderTask;
  }

  public Set<Time> getTimes() {
    return times;
  }

  public void setTimes(Set<Time> times) {
    this.times = times;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Set<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(Set<Notification> notifications) {
    this.notifications = notifications;
  }

  public UserData getUserData() {
    return userData;
  }

  public void setUserData(UserData userData) {
    this.userData = userData;
  }

  public Panel getPanel() {
    return panel;
  }

  public void setPanel(Panel panel) {
    this.panel = panel;
  }

  public Preference getPreference() {
    return preference;
  }

  public void setPreference(Preference preference) {
    this.preference = preference;
  }

  public TaskRequest getTaskRequest() {
    return taskRequest;
  }

  public void setTaskRequest(TaskRequest taskRequest) {
    this.taskRequest = taskRequest;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    if (task.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, task.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Task{" + "id=" + id + ", title='" + title + "'" + ", description='" + description + "'"
        + ", taskType='" + taskType + "'" + ", complexity='" + complexity + "'"
        + ", estimatedHours='" + estimatedHours + "'" + ", estimatedMinutes='" + estimatedMinutes
        + "'" + ", creationDate='" + creationDate + "'" + ", orderTask='" + orderTask + "'" + '}';
  }
}
