package es.juanlsanchez.datask.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A task request.
 */
@Entity
@Table(name = "task_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskRequest extends BaseEntity {

  public static final String A_TASK = "task";

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

  // Relationships ----------------------------------------------

  @OneToOne
  @JoinColumn(unique = true, name = "task_id")
  private Task task;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
