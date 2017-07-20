package es.juanlsanchez.datask.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskComplexity;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A task.
 */
@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Task extends BaseEntity {

  public static final String A_PANEL = "panel";
  public static final String A_PREFERENCE = "preference";

  public static final String C_TASK_ID = "task_id";

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

  // Relationships ----------------------------------------------

  @OneToMany(mappedBy = Time.A_TASK)
  @JsonIgnore
  @Builder.Default
  private Set<Time> times = new HashSet<>();

  @OneToMany(mappedBy = Comment.A_TASK)
  @JsonIgnore
  @Builder.Default
  private Set<Comment> comments = new HashSet<>();

  @OneToMany(mappedBy = Notification.A_TASK)
  @JsonIgnore
  @Builder.Default
  private Set<Notification> notifications = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "panel_id")
  private Panel panel;

  @OneToMany(mappedBy = UserTask.A_TASK)
  private Set<UserTask> userTasks;

  @OneToOne(mappedBy = TaskRequest.A_TASK)
  private TaskRequest taskRequest;

}
