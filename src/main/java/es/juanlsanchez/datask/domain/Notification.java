package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import es.juanlsanchez.datask.domain.enumeration.EnumNotification;
import es.juanlsanchez.datask.domain.enumeration.EnumNotificationStatus;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notification implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "creation_date")
  private ZonedDateTime creationDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumNotificationStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private EnumNotification type;

  @ManyToOne
  private Task task;

  @ManyToOne
  private Comment comment;

  @ManyToOne
  private Project project;

  @ManyToOne
  private UserData userData;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(ZonedDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public EnumNotificationStatus getStatus() {
    return status;
  }

  public void setStatus(EnumNotificationStatus status) {
    this.status = status;
  }

  public EnumNotification getType() {
    return type;
  }

  public void setType(EnumNotification type) {
    this.type = type;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
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
    Notification notification = (Notification) o;
    if (notification.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, notification.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Notification{" + "id=" + id + ", creationDate='" + creationDate + "'" + ", status='"
        + status + "'" + ", type='" + type + "'" + '}';
  }
}
