package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class ConnectionAttemptIndication extends DataMessage {
    public ConnectionAttemptIndication() {
        super(MessageType.Indication, Message.generateTransactionId());
    }
}
