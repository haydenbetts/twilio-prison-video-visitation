package fm.liveswitch.stun.turn;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class PeerAddressFamilyMismatchError extends Error {
    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 443 Peer Address Family Mismatch.");
        }
        return StringExtensions.concat(errorCode, " ", super.getMessage().trim());
    }

    public PeerAddressFamilyMismatchError() {
        this((String) null);
    }

    public PeerAddressFamilyMismatchError(String str) {
        super(ErrorCode.StunTurnPeerAddressFamilyMismatch, str);
    }
}
