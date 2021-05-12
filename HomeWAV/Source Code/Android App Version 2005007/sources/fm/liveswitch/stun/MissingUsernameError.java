package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class MissingUsernameError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 432 Missing Username.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public MissingUsernameError() {
        this((String) null);
    }

    public MissingUsernameError(String str) {
        super(ErrorCode.StunMissingUsername, str);
    }
}
