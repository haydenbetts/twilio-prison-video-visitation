package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class UnsupportedTransportProtocolError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 442 Unsupported Transport Protocol.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public UnsupportedTransportProtocolError() {
        this((String) null);
    }

    public UnsupportedTransportProtocolError(String str) {
        super(ErrorCode.StunTurnUnsupportedTransportProtocol, str);
    }
}
