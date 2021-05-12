package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class AllocationMismatchError extends Error {
    public AllocationMismatchError() {
        this((String) null);
    }

    public AllocationMismatchError(String str) {
        super(ErrorCode.StunTurnAllocationMismatch, str);
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 437 Allocation Mismatch.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }
}
