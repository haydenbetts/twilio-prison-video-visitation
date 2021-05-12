package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public abstract class DataMessage extends Message {
    public DataMessage(MessageType messageType, DataBuffer dataBuffer) {
        super(messageType, dataBuffer);
    }

    public int getMethod() {
        return Message.getDataMethod();
    }
}
