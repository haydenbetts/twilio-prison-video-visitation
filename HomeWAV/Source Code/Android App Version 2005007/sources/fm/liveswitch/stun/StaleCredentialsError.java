package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class StaleCredentialsError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 430 Stale Credentials.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public StaleCredentialsError() {
        this((String) null);
    }

    public StaleCredentialsError(String str) {
        super(ErrorCode.StunStaleCredentials, str);
    }
}
