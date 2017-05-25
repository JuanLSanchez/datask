package es.juanlsanchez.datask.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Budget.
 */
@Entity
@Table(name = "budget")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "idd")
    private Long idd;

    @Column(name = "own_amount")
    private Float ownAmount;

    @Column(name = "hours")
    private Float hours;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToOne(mappedBy = "budget")
    @JsonIgnore
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdd() {
        return idd;
    }

    public void setIdd(Long idd) {
        this.idd = idd;
    }

    public Float getOwnAmount() {
        return ownAmount;
    }

    public void setOwnAmount(Float ownAmount) {
        this.ownAmount = ownAmount;
    }

    public Float getHours() {
        return hours;
    }

    public void setHours(Float hours) {
        this.hours = hours;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Budget budget = (Budget) o;
        if(budget.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, budget.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Budget{" +
            "id=" + id +
            ", idd='" + idd + "'" +
            ", ownAmount='" + ownAmount + "'" +
            ", hours='" + hours + "'" +
            ", billingDate='" + billingDate + "'" +
            ", startDate='" + startDate + "'" +
            ", endDate='" + endDate + "'" +
            '}';
    }
}
