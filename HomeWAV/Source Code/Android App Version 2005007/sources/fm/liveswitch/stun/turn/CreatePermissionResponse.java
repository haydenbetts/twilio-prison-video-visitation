package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.stun.MessageType;

public class CreatePermissionResponse extends CreatePermissionMessage {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CreatePermissionResponse(DataBuffer dataBuffer, boolean z) {
        super(z ? MessageType.SuccessResponse : MessageType.ErrorResponse, dataBuffer);
    }
}
