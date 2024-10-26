package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Connection;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.*;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.*;

import java.util.List;

public interface ConnectionService {
    String handle(CreateConnectionCommand command);
    String handle(RequestConnectionCommand command);
    String handle(ConfirmConnectionCommand command);
    String handle(RejectConnectionCommand command);
    String handle(CancelConnectionCommand command);
    List<Connection> handle(GetAllConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllCanceledConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllConfirmedConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllRequestedConnectionsByProfileIdQuery query);
    List<Connection> handle(GetAllRejectedConnectionsByProfileIdQuery query);
}
