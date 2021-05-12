package fm.liveswitch.stun.turn;

import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;

public class CreatePermissionRequest extends CreatePermissionMessage {
    public CreatePermissionRequest() {
        super(MessageType.Request, Message.generateTransactionId());
    }
}
