package es.juanlsanchez.datask.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class InvoiceLine extends BaseEntity {
    // Attributes -------------------------------------------------------------
    @Range(min = 0, max = 100)
    private int iva;
    private double base;

    // Relationships------------------------------------------------------------
    @Valid
    @ManyToOne(optional = false)
    private Invoice invoice;

}
