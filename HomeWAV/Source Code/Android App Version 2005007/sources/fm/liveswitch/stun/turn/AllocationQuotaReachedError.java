package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class AllocationQuotaReachedError extends Error {
    public AllocationQuotaReachedError() {
        this((String) null);
    }

    public AllocationQuotaReachedError(String str) {
        super(ErrorCode.StunTurnAllocationQuotaReached, str);
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 486 Allocation Quota Reached.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }
}
