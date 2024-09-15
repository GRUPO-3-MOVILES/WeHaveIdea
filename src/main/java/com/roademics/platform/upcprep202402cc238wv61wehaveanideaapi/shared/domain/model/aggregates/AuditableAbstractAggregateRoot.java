package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document // MongoDB equivalente a @Entity en JPA
public abstract class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    @Getter
    @Id // MongoDB usa @Id en lugar de @GeneratedValue
    private String id; // MongoDB generalmente usa cadenas para las IDs, aunque puedes usar otro tipo si lo prefieres.

    @Getter
    @CreatedDate
    private Date createdAt;

    @Getter
    @LastModifiedDate
    private Date updatedAt;
}

