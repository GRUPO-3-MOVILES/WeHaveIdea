package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Connection;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.*;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.*;

import java.util.List;

public interface ConnectionService {

    // Query
    List<Connection> handle(GetAllConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllCanceledConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllConfirmedConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllRequestedConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllRejectedConnectionsByProfileIdQuery query);

    // Command
    Connection handle(CreateConnectionCommand command);
    Connection handle(RequestConnectionCommand command);
    Connection handle(ConfirmConnectionCommand command);
    Connection handle(RejectConnectionCommand command);
    Connection handle(CancelConnectionCommand command);
}
