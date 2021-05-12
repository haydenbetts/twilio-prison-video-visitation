package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class InsufficientCapacityError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 508 Insufficient Capacity.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public InsufficientCapacityError() {
        this((String) null);
    }

    public InsufficientCapacityError(String str) {
        super(ErrorCode.StunTurnInsufficientCapacity, str);
    }
}
