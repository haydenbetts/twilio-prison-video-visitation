package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;

public class BindingResponse extends BindingMessage {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BindingResponse(DataBuffer dataBuffer, boolean z) {
        super(z ? MessageType.SuccessResponse : MessageType.ErrorResponse, dataBuffer);
    }
}
