package es.juanlsanchez.datask.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Supplier extends UserObject {
  // Attributes -------------------------------------------------------------
  @NotBlank
  private String name;
  private String nif;

  // Relationships------------------------------------------------------------
  @OneToMany(mappedBy = "supplier")
  private Collection<Invoice> invoices;

  @Override
  public String toString() {
    return "Supplier [name=" + name + ", nif=" + nif + "]";
  }



}
