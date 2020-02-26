package com.pdurasek.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AuditedEntity {

    private static final Logger log = LoggerFactory.getLogger(AuditedEntity.class);

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "ss")
    private Date creationDateTime = null;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "ss")
    private Date lastUpdatedDateTime = null;

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    @PrePersist
    public void createAuditInfo() {
        setCreationDateTime(new Date(System.currentTimeMillis()));
        setLastUpdatedDateTime(getCreationDateTime());

        log.debug("Creating audit info for " + this.getClass().getName() + " on pre persist");
    }

    @PreUpdate
    public void updateAuditInfo() {
        setLastUpdatedDateTime(new Date(System.currentTimeMillis()));

        log.debug("Updating audit info for " + this.getClass().getName() + " on pre update");
    }
}
