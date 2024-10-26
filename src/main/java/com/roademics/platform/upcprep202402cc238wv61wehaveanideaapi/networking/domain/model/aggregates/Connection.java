package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreateConnectionCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects.ConnectionStatus;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects.ProfileId;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Document(collection = "connections")
public class Connection extends AuditableAbstractAggregateRoot<Connection> {

    private ProfileId profileIdRequester;
    private ProfileId profileIdRequested;
    private ConnectionStatus status;

    public Connection(CreateConnectionCommand command){
        this.profileIdRequester = new ProfileId(command.firstProfileId());
        this.profileIdRequested = new ProfileId(command.secondProfileId());
        this.status = ConnectionStatus.valueOf(command.connectionStatus());
    }

    public void requestConnection() {
        this.status = ConnectionStatus.REQUESTED;
    }

    public void confirmConnection() {
        this.status = ConnectionStatus.CONFIRMED;
    }

    public void rejectConnection() {
        this.status = ConnectionStatus.REJECTED;
    }

    public void cancelConnection() {
        this.status = ConnectionStatus.CANCELLED;
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }
}
