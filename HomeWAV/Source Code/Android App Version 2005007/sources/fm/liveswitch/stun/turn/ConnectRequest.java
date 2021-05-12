package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class ConnectRequest extends ConnectMessage {
    public ConnectRequest() {
        super(MessageType.Request, Message.generateTransactionId());
    }
}
