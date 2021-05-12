package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class ForbiddenError extends Error {
    public ForbiddenError() {
        this((String) null);
    }

    public ForbiddenError(String str) {
        super(ErrorCode.StunTurnForbidden, str);
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 403 Forbidden.");
        }
        return StringExtensions.concat(StringExtensions.concat(errorCode, " ", super.getMessage().trim()), " The request was valid but cannot be performed due to administrative or similar restrictions.");
    }
}
