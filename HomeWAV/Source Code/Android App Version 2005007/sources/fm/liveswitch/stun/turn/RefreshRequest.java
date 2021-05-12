package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class RefreshRequest extends RefreshMessage {
    public RefreshRequest() {
        super(MessageType.Request, Message.generateTransactionId());
    }
}
