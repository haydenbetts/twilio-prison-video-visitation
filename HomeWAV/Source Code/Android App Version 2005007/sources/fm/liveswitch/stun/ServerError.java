package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class ServerError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 500 Server Error.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public ServerError() {
        this((String) null);
    }

    public ServerError(String str) {
        super(ErrorCode.StunServerError, "Server responded with 500 Server Error.");
    }
}
