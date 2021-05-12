package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class AllocateRequest extends AllocateMessage {
    public AllocateRequest() {
        super(MessageType.Request, Message.generateTransactionId());
    }
}
