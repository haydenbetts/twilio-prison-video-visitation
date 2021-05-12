package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;

public abstract class BindingMessage extends Message {
    public BindingMessage(MessageType messageType, DataBuffer dataBuffer) {
        super(messageType, dataBuffer);
    }

    public int getMethod() {
        return Message.getBindingMethod();
    }
}
