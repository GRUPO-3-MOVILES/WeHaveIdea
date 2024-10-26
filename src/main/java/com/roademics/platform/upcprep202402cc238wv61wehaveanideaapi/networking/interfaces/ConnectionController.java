package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Connection;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.*;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.*;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/connection")
public class ConnectionController {

    private final ConnectionService connectionService;

    @Autowired
    private ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    // Crear una nueva conexión
    @PostMapping("/create")
    public ResponseEntity<Connection> createConnection(@RequestBody CreateConnectionCommand command) {
        Connection connection = connectionService.handle(command);
        return ResponseEntity.ok(connection);
    }

    // Request a connection
    @PostMapping("/request")
    public ResponseEntity<Connection> requestConnection(@RequestBody RequestConnectionCommand command) {
        Connection connection = connectionService.handle(command);
        return ResponseEntity.ok(connection);
    }

    // Confirm a connection
    @PostMapping("/confirm")
    public ResponseEntity<Connection> confirmConnection(@RequestBody ConfirmConnectionCommand command) {
        Connection connection = connectionService.handle(command);
        return ResponseEntity.ok(connection);
    }

    // Reject a connection
    @PostMapping("/reject")
    public ResponseEntity<Connection> rejectConnection(@RequestBody RejectConnectionCommand command) {
        Connection connection = connectionService.handle(command);
        return ResponseEntity.ok(connection);
    }

    // Cancel a connection
    @PostMapping("/cancel")
    public ResponseEntity<Connection> cancelConnection(@RequestBody CancelConnectionCommand command) {
        Connection connection = connectionService.handle(command);
        return ResponseEntity.ok(connection);
    }

    // Obtener todas las conexiones para un perfil
    @PostMapping("/get-all")
    public ResponseEntity<List<Connection>> getAllConnectionsByProfileId(@RequestBody GetAllConnectionsByProfileIdQuery query) {
        List<Connection> connections = connectionService.handle(query);
        return ResponseEntity.ok(connections);
    }

    // Obtener todas las conexiones canceladas para un perfil
    @PostMapping("/get-all-canceled")
    public ResponseEntity<List<Connection>> getAllCanceledConnectionsByProfileId(@RequestBody GetAllCanceledConnectionsByProfileIdQuery query) {
        List<Connection> connections = connectionService.handle(query);
        return ResponseEntity.ok(connections);
    }

    // Obtener todas las conexiones confirmadas para un perfil
    @PostMapping("/get-all-confirmed")
    public ResponseEntity<List<Connection>> getAllConfirmedConnectionsByProfileId(@RequestBody GetAllConfirmedConnectionsByProfileIdQuery query) {
        List<Connection> connections = connectionService.handle(query);
        return ResponseEntity.ok(connections);
    }

    // Obtener todas las conexiones solicitadas para un perfil
    @PostMapping("/get-all-requested")
    public ResponseEntity<List<Connection>> getAllRequestedConnectionsByProfileId(@RequestBody GetAllRequestedConnectionsByProfileIdQuery query) {
        List<Connection> connections = connectionService.handle(query);
        return ResponseEntity.ok(connections);
    }

    // Obtener todas las conexiones rechazadas para un perfil
    @PostMapping("/get-all-rejected")
    public ResponseEntity<List<Connection>> getAllRejectedConnectionsByProfileId(@RequestBody GetAllRejectedConnectionsByProfileIdQuery query) {
        List<Connection> connections = connectionService.handle(query);
        return ResponseEntity.ok(connections);
    }


}
