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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import es.juanlsanchez.datask.domain.enumeration.EnumRequestStatus;

/**
 * A TaskRequest.
 */
@Entity
@Table(name = "task_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaskRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(name = "estimated_amount", nullable = false)
  private Float estimatedAmount;

  @NotNull
  @Column(name = "billing_amount", nullable = false)
  private Float billingAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumRequestStatus status;

  @Column(name = "request_date")
  private ZonedDateTime requestDate;

  @Column(name = "request_changed")
  private ZonedDateTime requestChanged;

  @OneToOne
  @JoinColumn(unique = true)
  private Task task;

  @ManyToOne
  private UserData userData;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getEstimatedAmount() {
    return estimatedAmount;
  }

  public void setEstimatedAmount(Float estimatedAmount) {
    this.estimatedAmount = estimatedAmount;
  }

  public Float getBillingAmount() {
    return billingAmount;
  }

  public void setBillingAmount(Float billingAmount) {
    this.billingAmount = billingAmount;
  }

  public EnumRequestStatus getStatus() {
    return status;
  }

  public void setStatus(EnumRequestStatus status) {
    this.status = status;
  }

  public ZonedDateTime getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(ZonedDateTime requestDate) {
    this.requestDate = requestDate;
  }

  public ZonedDateTime getRequestChanged() {
    return requestChanged;
  }

  public void setRequestChanged(ZonedDateTime requestChanged) {
    this.requestChanged = requestChanged;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
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
    TaskRequest taskRequest = (TaskRequest) o;
    if (taskRequest.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, taskRequest.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "TaskRequest{" + "id=" + id + ", estimatedAmount='" + estimatedAmount + "'"
        + ", billingAmount='" + billingAmount + "'" + ", status='" + status + "'"
        + ", requestDate='" + requestDate + "'" + ", requestChanged='" + requestChanged + "'" + '}';
  }
}
