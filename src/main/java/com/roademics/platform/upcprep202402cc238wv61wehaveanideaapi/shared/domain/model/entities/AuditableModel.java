package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.entities;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document // MongoDB no necesita @MappedSuperclass
public abstract class AuditableModel {

    @Getter
    @CreatedDate
    private Date createdAt;

    @Getter
    @LastModifiedDate
    private Date updatedAt;
}

