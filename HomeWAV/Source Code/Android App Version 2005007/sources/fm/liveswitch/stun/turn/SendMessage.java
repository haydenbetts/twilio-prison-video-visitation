package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public abstract class SendMessage extends Message {
    public int getMethod() {
        return Message.getSendMethod();
    }

    public SendMessage(MessageType messageType, DataBuffer dataBuffer) {
        super(messageType, dataBuffer);
    }
}
