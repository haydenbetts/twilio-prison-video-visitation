package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class SendIndication extends SendMessage {
    public SendIndication() {
        super(MessageType.Indication, Message.generateTransactionId());
    }
}
