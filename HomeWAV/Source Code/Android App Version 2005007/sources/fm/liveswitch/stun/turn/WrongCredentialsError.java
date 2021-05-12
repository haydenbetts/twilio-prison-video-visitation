package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class WrongCredentialsError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 441 Wrong Credentials.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public WrongCredentialsError() {
        this((String) null);
    }

    public WrongCredentialsError(String str) {
        super(ErrorCode.StunTurnWrongCredentials, str);
    }
}
