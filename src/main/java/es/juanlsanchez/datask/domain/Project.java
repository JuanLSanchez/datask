package es.juanlsanchez.datask.domain;

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

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A project.
 */
@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Project extends BaseEntity {

  public static final String A_BUDGET = "budget";
  public static final String A_COMPANY = "company";

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumProjectStatus status;

  @Column(name = "completed_estimated")
  private Integer completedEstimated;

  // Relationships ----------------------------------------------

  @OneToMany(mappedBy = Panel.A_PROJECT)
  @Builder.Default
  private Set<Panel> panels = new HashSet<>();

  @OneToOne
  @JoinColumn(unique = true, name = "budget_id")
  private Budget budget;

  @OneToMany(mappedBy = Notification.A_PROJECT)
  @Builder.Default
  private Set<Notification> notifications = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @OneToMany(mappedBy = UserProject.A_PROJECT)
  private Set<UserProject> userProject;

}
