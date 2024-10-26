package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Connection;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.*;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.*;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.ConnectionService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    private final ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    @Override
    public Connection handle(CreateConnectionCommand command) {
        Connection connection = new Connection(command);
        connectionRepository.saveConnection(connection);
        return connection;
    }

    @Override
    public Connection handle(RequestConnectionCommand command) {
        Optional<Connection> connection = connectionRepository.findById(command.ConnectionId());
        if (connection.isPresent()) {
            try {
                connection.get().requestConnection();
                connectionRepository.saveConnection(connection.get());
                return connection.get();
            } catch (Exception e) {
                Logger.getLogger("Error: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public Connection handle(ConfirmConnectionCommand command) {
        Optional<Connection> connection = connectionRepository.findById(command.ConnectionId());
        if (connection.isPresent()) {
            try {
                connection.get().confirmConnection();
                connectionRepository.saveConnection(connection.get());
                return connection.get();
            } catch (Exception e) {
                Logger.getLogger("Error: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public Connection handle(RejectConnectionCommand command) {
        Optional<Connection> connection = connectionRepository.findById(command.ConnectionId());
        if (connection.isPresent()) {
            try {
                connection.get().rejectConnection();
                connectionRepository.saveConnection(connection.get());
                return connection.get();
            } catch (Exception e) {
                Logger.getLogger("Error: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public Connection handle(CancelConnectionCommand command) {
        Optional<Connection> connection = connectionRepository.findById(command.ConnectionId());
        if (connection.isPresent()) {
            try {
                connection.get().cancelConnection();
                connectionRepository.saveConnection(connection.get());
                return connection.get();
            } catch (Exception e) {
                Logger.getLogger("Error: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Connection> handle(GetAllConnectionsByProfileIdQuery query) {
        return connectionRepository.findByProfileId(query.profileId());
    }

    @Override
    public List<Connection> handle(GetAllCanceledConnectionsByProfileIdQuery query) {
        return connectionRepository.findByProfileIdAndStatus(query.profileId(), "CANCELED");
    }

    @Override
    public List<Connection> handle(GetAllConfirmedConnectionsByProfileIdQuery query) {
        return connectionRepository.findByProfileIdAndStatus(query.profileId(), "CONFIRMED");
    }

    @Override
    public List<Connection> handle(GetAllRequestedConnectionsByProfileIdQuery query) {
        return connectionRepository.findByProfileIdAndStatus(query.profileId(), "REQUESTED");
    }

    @Override
    public List<Connection> handle(GetAllRejectedConnectionsByProfileIdQuery query) {
        return connectionRepository.findByProfileIdAndStatus(query.profileId(), "REJECTED");
    }
}
