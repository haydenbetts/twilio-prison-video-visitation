package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public abstract class RefreshMessage extends Message {
    public int getMethod() {
        return Message.getRefreshMethod();
    }

    public RefreshMessage(MessageType messageType, DataBuffer dataBuffer) {
        super(messageType, dataBuffer);
    }
}
