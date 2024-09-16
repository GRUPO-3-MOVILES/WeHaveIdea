package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document // MongoDB equivalente a @Entity en JPA
@Getter
public abstract class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    @Id // MongoDB usa @Id en lugar de @GeneratedValue
    private Long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}

