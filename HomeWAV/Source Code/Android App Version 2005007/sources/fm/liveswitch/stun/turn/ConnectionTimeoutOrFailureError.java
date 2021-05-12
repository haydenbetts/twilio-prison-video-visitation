package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class ConnectionTimeoutOrFailureError extends Error {
    public ConnectionTimeoutOrFailureError() {
        this((String) null);
    }

    public ConnectionTimeoutOrFailureError(String str) {
        super(ErrorCode.StunTurnConnectionTimeoutOrFailure, str);
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 447 Connection Timeout or Failure.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }
}
