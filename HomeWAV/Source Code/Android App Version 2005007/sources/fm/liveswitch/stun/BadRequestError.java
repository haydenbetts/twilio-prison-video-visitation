package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class BadRequestError extends Error {
    public BadRequestError() {
        this((String) null);
    }

    public BadRequestError(String str) {
        super(ErrorCode.StunBadRequest, str);
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 400 Bad Request.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim(), " Stun request was malformed.");
    }
}
