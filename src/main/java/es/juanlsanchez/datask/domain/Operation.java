package es.juanlsanchez.datask.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Operation extends UserObject {
  // Attributes -------------------------------------------------------------
  @NotBlank
  private String name;

  // Relationships------------------------------------------------------------
  @Valid
  @OneToMany(mappedBy = "operation")
  private Collection<Invoice> invoices;
  @Valid
  @ManyToOne(optional = false)
  private Section section;

  @Override
  public String toString() {
    return "Operation [name=" + name + "]";
  }



}
