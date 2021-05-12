package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public abstract class ConnectionBindMessage extends Message {
    public ConnectionBindMessage(MessageType messageType, DataBuffer dataBuffer) {
        super(messageType, dataBuffer);
    }

    public int getMethod() {
        return Message.getConnectionBindMethod();
    }
}
