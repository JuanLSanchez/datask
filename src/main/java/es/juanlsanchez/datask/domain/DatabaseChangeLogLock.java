package es.juanlsanchez.datask.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "databasechangeloglock")
@Data
public class DatabaseChangeLogLock {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "LOCKED")
    private boolean locked;
    @Column(name = "LOCKGRANTED")
    private Instant lockgranted;
    @Column(name = "LOCKEDBY")
    private String lockedby;

}
