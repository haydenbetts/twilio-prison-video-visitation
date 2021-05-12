package com.pusher.client.connection;

import java.util.logging.Logger;

public class ConnectionStateChange {
    private static final Logger log = Logger.getLogger(ConnectionStateChange.class.getName());
    private final ConnectionState currentState;
    private final ConnectionState previousState;

    public ConnectionStateChange(ConnectionState connectionState, ConnectionState connectionState2) {
        if (connectionState == connectionState2) {
            Logger logger = log;
            logger.fine("Attempted to create an connection state update where both previous and current state are: " + connectionState2);
        }
        this.previousState = connectionState;
        this.currentState = connectionState2;
    }

    public ConnectionState getPreviousState() {
        return this.previousState;
    }

    public ConnectionState getCurrentState() {
        return this.currentState;
    }

    public int hashCode() {
        return this.previousState.hashCode() + this.currentState.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ConnectionStateChange)) {
            return false;
        }
        ConnectionStateChange connectionStateChange = (ConnectionStateChange) obj;
        if (this.currentState == connectionStateChange.currentState && this.previousState == connectionStateChange.previousState) {
            return true;
        }
        return false;
    }
}
