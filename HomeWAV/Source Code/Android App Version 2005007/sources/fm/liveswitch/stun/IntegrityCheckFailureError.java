package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class IntegrityCheckFailureError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 432 Missing Username.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public IntegrityCheckFailureError() {
        this((String) null);
    }

    public IntegrityCheckFailureError(String str) {
        super(ErrorCode.StunMissingUsername, str);
    }
}
