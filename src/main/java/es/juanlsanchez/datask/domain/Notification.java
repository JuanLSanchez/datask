package es.juanlsanchez.datask.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumNotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Notification extends BaseEntity {

  private static final String C_USER_ID = "user_id";
  private static final String C_PROJECT_ID = "project_id";
  private static final String C_COMMENT_ID = "comment_id";
  private static final String C_TASK_ID = "task_id";

  public static final String A_COMMENT = "comment";
  public static final String A_TASK = "task";
  public static final String A_PROJECT = "project";

  @Column(name = "creation_date")
  private Instant creationDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumNotificationStatus status;

  // Relationships ----------------------------------------------

  @ManyToOne
  @JoinColumn(name = C_TASK_ID)
  private Task task;

  @ManyToOne
  @JoinColumn(name = C_COMMENT_ID)
  private Comment comment;

  @ManyToOne
  @JoinColumn(name = C_PROJECT_ID)
  private Project project;

  @ManyToOne
  @JoinColumn(name = C_USER_ID, nullable = false)
  private User user;

}
