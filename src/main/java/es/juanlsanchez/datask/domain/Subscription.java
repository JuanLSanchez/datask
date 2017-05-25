package es.juanlsanchez.datask.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import es.juanlsanchez.datask.domain.enumeration.EnumSubscription;
import es.juanlsanchez.datask.domain.enumeration.EnumSubscriptionStatus;

/**
 * A Subscription.
 */
@Entity
@Table(name = "subscription")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Subscription implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

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

  @OneToOne
  @JoinColumn(unique = true)
  private Company company;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getStart() {
    return start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }

  public EnumSubscription getType() {
    return type;
  }

  public void setType(EnumSubscription type) {
    this.type = type;
  }

  public EnumSubscriptionStatus getStatus() {
    return status;
  }

  public void setStatus(EnumSubscriptionStatus status) {
    this.status = status;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscription subscription = (Subscription) o;
    if (subscription.id == null || id == null) {
      return false;
    }
    return Objects.equals(id, subscription.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Subscription{" + "id=" + id + ", start='" + start + "'" + ", end='" + end + "'"
        + ", type='" + type + "'" + ", status='" + status + "'" + '}';
  }
}
