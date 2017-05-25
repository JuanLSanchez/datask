package es.juanlsanchez.datask.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "databasechangelog")
@Entity
@Data
public class DatabaseChangeLog {

    @Id
    @Column(name = "ID", length=30)
    private String id;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "FILENAME")
    private String filename;
    @Column(name = "DATEEXECUTED")
    private Instant dateExecuted;
    @Column(name = "ORDEREXECUTED")
    private Integer orderExecuted;
    @Column(name = "EXECTYPE")
    private String execType;
    @Column(name = "MD5SUM")
    private String md5Sum;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "TAG")
    private String tag;
    @Column(name = "LIQUIBASE")
    private String liqibase;
    @Column(name = "CONTEXTS")
    private String context;
    @Column(name = "LABELS")
    private String labels;
    @Column(name = "DEPLOYMENT_ID")
    private String deploimentId;

}
