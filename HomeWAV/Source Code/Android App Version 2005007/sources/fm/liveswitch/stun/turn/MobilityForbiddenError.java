package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class MobilityForbiddenError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 405 Mobility Forbidden.");
        }
        return StringExtensions.concat(StringExtensions.concat(errorCode, " ", super.getMessage().trim()), " The request was valid but cannot be performed due to administrative or similar restrictions.");
    }

    public MobilityForbiddenError() {
        this((String) null);
    }

    public MobilityForbiddenError(String str) {
        super(ErrorCode.StunTurnMobilityForbidden, str);
    }
}
