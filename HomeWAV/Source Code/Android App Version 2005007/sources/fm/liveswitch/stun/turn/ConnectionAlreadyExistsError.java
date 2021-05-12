package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class ConnectionAlreadyExistsError extends Error {
    public ConnectionAlreadyExistsError() {
        this((String) null);
    }

    public ConnectionAlreadyExistsError(String str) {
        super(ErrorCode.StunTurnConnectionAlreadyExists, str);
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 446 Connection Already Exists.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }
}
