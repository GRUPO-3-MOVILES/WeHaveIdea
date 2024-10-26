package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Connection;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    void saveConnection(Connection connection);
    Optional<Connection> findById(String connectionId);
    List<Connection> findAll();
    List<Connection> findByProfileId(String profileId);
    List<Connection> findByProfileIdAndStatus(String profileId, String status);
}
