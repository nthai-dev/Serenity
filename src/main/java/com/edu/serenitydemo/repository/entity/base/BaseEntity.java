package com.edu.serenitydemo.repository.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    private Date createdTimestamp;

    private Date lastUpdatedTimestamp;

    @PrePersist
    public void preInsert() {
        Date createDate = new Date();
        this.createdTimestamp = createDate;
        this.lastUpdatedTimestamp = createDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedTimestamp = new Date();
    }
}
