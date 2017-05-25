package es.juanlsanchez.datask.domain;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import es.juanlsanchez.datask.annotation.PastLocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {@Index(columnList = "dateBuy")})
public class Invoice extends UserObject {
  // Attributes -------------------------------------------------------------
  private String number;
  @NotNull
  @PastLocalDate
  private LocalDate dateBuy;

  // Relationships------------------------------------------------------------
  @Valid
  @ManyToOne(optional = false)
  private Supplier supplier;
  @Valid
  @ManyToOne(optional = false)
  private Operation operation;
  @Valid
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
  private Collection<InvoiceLine> invoiceLines;


  @Override
  public String toString() {
    return "Invoice [number=" + number + ", dateBuy=" + dateBuy + "]";
  }



}
