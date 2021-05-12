package fm.liveswitch;

public class StreamStateMachine extends StateMachine<StreamState> {
    /* access modifiers changed from: protected */
    public int stateToValue(StreamState streamState) {
        return streamState.getAssignedValue();
    }

    public StreamStateMachine() {
        super(StreamState.New);
        super.addTransition(StreamState.New, StreamState.Initializing);
        super.addTransition(StreamState.New, StreamState.Failing);
        super.addTransition(StreamState.New, StreamState.Closing);
        super.addTransition(StreamState.Initializing, StreamState.Connecting);
        super.addTransition(StreamState.Initializing, StreamState.Failing);
        super.addTransition(StreamState.Initializing, StreamState.Closing);
        super.addTransition(StreamState.Connecting, StreamState.Connected);
        super.addTransition(StreamState.Connecting, StreamState.Failing);
        super.addTransition(StreamState.Connecting, StreamState.Closing);
        super.addTransition(StreamState.Connected, StreamState.Failing);
        super.addTransition(StreamState.Connected, StreamState.Closing);
        super.addTransition(StreamState.Closing, StreamState.Failing);
        super.addTransition(StreamState.Closing, StreamState.Closed);
        super.addTransition(StreamState.Failing, StreamState.Failed);
    }

    /* access modifiers changed from: protected */
    public StreamState valueToState(int i) {
        return StreamState.getByAssignedValue(i);
    }
}
