package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class ConnectionBindRequest extends ConnectionBindMessage {
    public ConnectionBindRequest() {
        super(MessageType.Request, Message.generateTransactionId());
    }
}
