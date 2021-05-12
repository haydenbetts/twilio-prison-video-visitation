package fm.liveswitch;

public class DataChannelStateMachine extends StateMachine<DataChannelState> {
    public DataChannelStateMachine() {
        super(DataChannelState.New);
        super.addTransition(DataChannelState.New, DataChannelState.Connecting);
        super.addTransition(DataChannelState.New, DataChannelState.Failed);
        super.addTransition(DataChannelState.New, DataChannelState.Closing);
        super.addTransition(DataChannelState.Connecting, DataChannelState.Connected);
        super.addTransition(DataChannelState.Connecting, DataChannelState.Failed);
        super.addTransition(DataChannelState.Connecting, DataChannelState.Closing);
        super.addTransition(DataChannelState.Connected, DataChannelState.Failed);
        super.addTransition(DataChannelState.Connected, DataChannelState.Closing);
        super.addTransition(DataChannelState.Closing, DataChannelState.Failed);
        super.addTransition(DataChannelState.Closing, DataChannelState.Closed);
    }

    /* access modifiers changed from: protected */
    public int stateToValue(DataChannelState dataChannelState) {
        return dataChannelState.getAssignedValue();
    }

    /* access modifiers changed from: protected */
    public DataChannelState valueToState(int i) {
        return DataChannelState.getByAssignedValue(i);
    }
}
