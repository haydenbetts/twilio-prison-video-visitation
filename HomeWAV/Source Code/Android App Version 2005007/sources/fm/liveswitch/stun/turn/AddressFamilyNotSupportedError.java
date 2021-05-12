package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class AddressFamilyNotSupportedError extends Error {
    public AddressFamilyNotSupportedError() {
        this((String) null);
    }

    public AddressFamilyNotSupportedError(String str) {
        super(ErrorCode.StunUnauthorized, "Server responded with 440 Address Family Not Supported.");
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 440 Address Family Not Supported.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }
}
