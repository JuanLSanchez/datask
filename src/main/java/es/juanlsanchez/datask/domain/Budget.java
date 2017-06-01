package es.juanlsanchez.datask.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A budget.
 */
@Entity
@Table(name = "budget")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Budget extends BaseEntity {

  public static final String C_PROJECT_ID = "project_id";
  public static final String C_END_DATE = "end_date";
  public static final String C_START_DATE = "start_date";
  public static final String C_BILLING_DATE = "billing_date";
  public static final String C_HOURS = "hours";
  public static final String C_OWN_AMOUNT = "own_amount";

  public static final String A_PROJECT = "project";
  public static final String A_END_DATE = "endDate";
  public static final String A_START_DATE = "startDate";
  public static final String A_BILLING_DATE = "billingDate";
  public static final String A_HOURS = "hours";
  public static final String A_OWN_AMOUNT = "ownAmount";

  @Column(name = C_OWN_AMOUNT)
  private Float ownAmount;

  @Column(name = C_HOURS)
  private Float hours;

  @Column(name = C_BILLING_DATE)
  private LocalDate billingDate;

  @Column(name = C_START_DATE)
  private LocalDate startDate;

  @Column(name = C_END_DATE)
  private LocalDate endDate;

  // Relationships ----------------------------------------------

  @OneToOne(mappedBy = Project.A_BUDGET)
  private Project project;

}
