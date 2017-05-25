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

import es.juanlsanchez.datask.domain.enumeration.EnumTime;

/**
 * A Time.
 */
@Entity
@Table(name = "time")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Time implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "start")
  private ZonedDateTime start;

  @Column(name = "end")
  private ZonedDateTime end;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private EnumTime type;

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

  public ZonedDateTime getStart() {
    return start;
  }

  public void setStart(ZonedDateTime start) {
    this.start = start;
  }

  public ZonedDateTime getEnd() {
    return end;
  }

  public void setEnd(ZonedDateTime end) {
    this.end = end;
  }

  public EnumTime getType() {
    return type;
  }

  public void setType(EnumTime type) {
    this.type = type;
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
    Time time = (Time) o;
    if (time.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, time.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Time{" + "id=" + id + ", start='" + start + "'" + ", end='" + end + "'" + ", type='"
        + type + "'" + '}';
  }
}
