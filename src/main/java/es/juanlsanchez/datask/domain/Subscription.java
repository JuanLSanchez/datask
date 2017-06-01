package es.juanlsanchez.datask.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.juanlsanchez.datask.domain.base.BaseEntity;
import es.juanlsanchez.datask.domain.enumeration.EnumSubscription;
import es.juanlsanchez.datask.domain.enumeration.EnumSubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A Subscription.
 */
@Entity
@Table(name = "subscription")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Subscription extends BaseEntity {

  public static final String A_SUBSCRIPTION = "company";

  @Column(name = "start")
  private LocalDate start;

  @Column(name = "end")
  private LocalDate end;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private EnumSubscription type;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EnumSubscriptionStatus status;

  // Relationships ----------------------------------------------

  @OneToOne
  @JoinColumn(unique = true, name = "company_id")
  private Company company;

}
