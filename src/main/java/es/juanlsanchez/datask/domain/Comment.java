package es.juanlsanchez.datask.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumCommentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Comment extends BaseEntity {

  private static final String C_TASK_ID = "task_id";
  private static final String C_USER_ID = "user_id";

  public static final String A_TASK = "task";
  private static final String A_ORIGIN = "origin";

  @Column(name = "creation_date")
  private Instant creationDate;

  @Column(name = "modification_date")
  private Instant modificationDate;

  @NotNull
  @Column(name = "text", nullable = false)
  private String text;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumCommentStatus status;

  // Relationships ----------------------------------------------

  @OneToMany(mappedBy = A_ORIGIN)
  private Set<Comment> response;

  @ManyToOne
  @JoinColumn(name = "origin_id")
  private Comment origin;

  @OneToMany(mappedBy = Notification.A_COMMENT)
  @Builder.Default
  private Set<Notification> notifications = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = C_USER_ID)
  private User user;

  @ManyToOne
  @JoinColumn(name = C_TASK_ID)
  private Task task;

}
