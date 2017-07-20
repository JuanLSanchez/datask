package es.juanlsanchez.datask.domain;

import java.time.LocalDate;
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

import org.springframework.data.annotation.CreatedDate;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumPanelStatus;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Employee entity.
 * 
 */
@Entity
@Table(name = "panel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Panel extends BaseEntity {

  public static final String A_PROJECT = "project";

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumPanelStatus status;

  @Column(name = "order_panel")
  private Integer orderPanel;

  @Column(name = "creation_date")
  @CreatedDate
  private LocalDate creationDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "default_task_status")
  private EnumTaskStatus defaultTaskStatus;

  // Relationships ----------------------------------------------

  @OneToMany(mappedBy = Task.A_PANEL)
  @Builder.Default
  private Set<Task> tasks = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

}
