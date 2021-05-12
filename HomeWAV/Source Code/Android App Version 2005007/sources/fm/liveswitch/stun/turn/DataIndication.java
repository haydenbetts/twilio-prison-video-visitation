package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class DataIndication extends DataMessage {
    public DataIndication() {
        super(MessageType.Indication, Message.generateTransactionId());
    }
}
