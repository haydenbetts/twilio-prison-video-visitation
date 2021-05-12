package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class UnauthorizedStunError extends Error {
    private NonceAttribute _nonce;
    private RealmAttribute _realm;

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 401 Unauthorized.");
        }
        String str = "missing";
        String concat = StringExtensions.concat(StringExtensions.concat(errorCode, " ", super.getMessage().trim()), " Nonce: ", getNonce() != null ? getNonce().getValue() : str);
        if (getRealm() != null) {
            str = getRealm().getValue();
        }
        return StringExtensions.concat(concat, " Realm: ", str);
    }

    public NonceAttribute getNonce() {
        return this._nonce;
    }

    public RealmAttribute getRealm() {
        return this._realm;
    }

    private void setNonce(NonceAttribute nonceAttribute) {
        this._nonce = nonceAttribute;
    }

    private void setRealm(RealmAttribute realmAttribute) {
        this._realm = realmAttribute;
    }

    public UnauthorizedStunError() {
        this((String) null);
    }

    public UnauthorizedStunError(String str) {
        this((NonceAttribute) null, (RealmAttribute) null, str);
    }

    public UnauthorizedStunError(NonceAttribute nonceAttribute, RealmAttribute realmAttribute, String str) {
        super(ErrorCode.StunUnauthorized, "Server responded with 401 Unauthorized.");
        setNonce(nonceAttribute);
        setRealm(realmAttribute);
    }
}
