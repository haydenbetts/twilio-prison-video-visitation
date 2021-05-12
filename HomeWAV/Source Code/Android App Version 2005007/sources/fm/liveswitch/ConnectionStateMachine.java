package fm.liveswitch;

public class ConnectionStateMachine extends StateMachine<ConnectionState> {
    public ConnectionStateMachine() {
        super(ConnectionState.New);
        super.addTransition(ConnectionState.New, ConnectionState.Initializing);
        super.addTransition(ConnectionState.New, ConnectionState.Failing);
        super.addTransition(ConnectionState.New, ConnectionState.Closing);
        super.addTransition(ConnectionState.Initializing, ConnectionState.Connecting);
        super.addTransition(ConnectionState.Initializing, ConnectionState.Failing);
        super.addTransition(ConnectionState.Initializing, ConnectionState.Closing);
        super.addTransition(ConnectionState.Connecting, ConnectionState.Connected);
        super.addTransition(ConnectionState.Connecting, ConnectionState.Failing);
        super.addTransition(ConnectionState.Connecting, ConnectionState.Closing);
        super.addTransition(ConnectionState.Connected, ConnectionState.Failing);
        super.addTransition(ConnectionState.Connected, ConnectionState.Closing);
        super.addTransition(ConnectionState.Closing, ConnectionState.Failing);
        super.addTransition(ConnectionState.Closing, ConnectionState.Closed);
        super.addTransition(ConnectionState.Failing, ConnectionState.Failed);
    }

    /* access modifiers changed from: protected */
    public int stateToValue(ConnectionState connectionState) {
        return connectionState.getAssignedValue();
    }

    /* access modifiers changed from: protected */
    public ConnectionState valueToState(int i) {
        return ConnectionState.getByAssignedValue(i);
    }
}
