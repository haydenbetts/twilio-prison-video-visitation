package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class TryAlternateStunError extends Error {
    private AlternateServerAttribute _alternateServer;

    public AlternateServerAttribute getAlternateServer() {
        return this._alternateServer;
    }

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 300 Try Alternate.");
        }
        String concat = StringExtensions.concat(errorCode, " ", super.getMessage().trim());
        if (getAlternateServer() != null) {
            return StringExtensions.concat(concat, " ", getAlternateServer().toString());
        }
        return StringExtensions.concat(concat, " Alternate server attribute missing.");
    }

    private void setAlternateServer(AlternateServerAttribute alternateServerAttribute) {
        this._alternateServer = alternateServerAttribute;
    }

    TryAlternateStunError(String str, AlternateServerAttribute alternateServerAttribute) {
        super(ErrorCode.StunTryAlternate, str);
        setAlternateServer(alternateServerAttribute);
    }
}
