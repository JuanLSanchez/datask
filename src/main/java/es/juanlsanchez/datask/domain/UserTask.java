package es.juanlsanchez.datask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserTask extends BaseEntity {

  public static final String A_TASK = "task";

  @Column(name = "faouvorite")
  @Builder.Default
  private boolean faouvorite = false;

  @Column(name = "assigned")
  @Builder.Default
  private boolean assigned = false;

  // Relationships ----------------------------------------------
  @ManyToOne
  @JoinColumn(name = "task_id")
  private Task task;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
