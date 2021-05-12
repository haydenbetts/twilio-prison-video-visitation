package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;

public class BindingRequest extends BindingMessage {
    public BindingRequest() {
        this(Message.generateTransactionId());
    }

    public BindingRequest(DataBuffer dataBuffer) {
        super(MessageType.Request, dataBuffer);
    }
}
