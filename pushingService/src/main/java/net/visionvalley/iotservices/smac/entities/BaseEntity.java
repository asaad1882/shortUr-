package net.visionvalley.iotservices.smac.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    @PrePersist
    public void setCreated() {
        this.created = new Date();
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        if (lastUpdated == null)
            return created;
        return lastUpdated;
    }

    @PreUpdate
    public void setLastUpdated() {
        this.lastUpdated = new Date();
    }

    public Date getTime() {
        if (this.lastUpdated != null)
            return this.lastUpdated;
        return this.created;
    }

    public String getReadableDateTime(Date date) {
        if (date == null) return "";
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        return dateFormat.format(date);
    }

    public String getReadableDayMonth(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat("dd MMMM").format(date);
    }

    public String getReadableDateWithoutTime(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM, dd yyyy");
        return sdf.format(date);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
