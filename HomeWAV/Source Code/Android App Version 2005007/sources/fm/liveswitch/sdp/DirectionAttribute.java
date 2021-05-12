package fm.liveswitch.sdp;

import fm.liveswitch.Global;
import fm.liveswitch.Log;
import fm.liveswitch.StreamDirection;

public abstract class DirectionAttribute extends Attribute {
    public abstract StreamDirection getStreamDirection();

    protected DirectionAttribute() {
    }

    public static DirectionAttribute generateDirectionAttribute(StreamDirection streamDirection) {
        if (Global.equals(streamDirection, StreamDirection.Inactive) || Global.equals(streamDirection, StreamDirection.Unset)) {
            if (Global.equals(streamDirection, StreamDirection.Unset)) {
                Log.debug("Attempted to create Sdp Direction attribute with unset direction. Creating Inactive direction attribute instead.");
            }
            return new InactiveAttribute();
        } else if (Global.equals(streamDirection, StreamDirection.SendOnly)) {
            return new SendOnlyAttribute();
        } else {
            if (Global.equals(streamDirection, StreamDirection.ReceiveOnly)) {
                return new ReceiveOnlyAttribute();
            }
            if (Global.equals(streamDirection, StreamDirection.SendReceive)) {
                return new SendReceiveAttribute();
            }
            throw new RuntimeException(new Exception("Unexpected Sdp.StreamDirection"));
        }
    }
}
