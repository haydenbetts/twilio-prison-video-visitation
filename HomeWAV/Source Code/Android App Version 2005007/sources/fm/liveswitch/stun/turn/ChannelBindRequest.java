package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class ChannelBindRequest extends ChannelBindMessage {
    public ChannelBindRequest() {
        super(MessageType.Request, Message.generateTransactionId());
    }
}
