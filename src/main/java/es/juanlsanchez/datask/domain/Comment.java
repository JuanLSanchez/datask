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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.enumeration.EnumCommentStatus;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comment implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "creation_date")
  private ZonedDateTime creationDate;

  @Column(name = "modification_date")
  private ZonedDateTime modificationDate;

  @NotNull
  @Column(name = "text", nullable = false)
  private String text;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumCommentStatus status;

  @OneToOne
  @JoinColumn(unique = true)
  private Comment origin;

  @OneToMany(mappedBy = "comment")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<Notification> notifications = new HashSet<>();

  @ManyToOne
  private UserData userData;

  @ManyToOne
  private Task task;

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

  public ZonedDateTime getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(ZonedDateTime modificationDate) {
    this.modificationDate = modificationDate;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public EnumCommentStatus getStatus() {
    return status;
  }

  public void setStatus(EnumCommentStatus status) {
    this.status = status;
  }

  public Comment getOrigin() {
    return origin;
  }

  public void setOrigin(Comment comment) {
    this.origin = comment;
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

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    if (comment.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Comment{" + "id=" + id + ", creationDate='" + creationDate + "'"
        + ", modificationDate='" + modificationDate + "'" + ", text='" + text + "'" + ", status='"
        + status + "'" + '}';
  }
}
