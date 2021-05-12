package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.StringExtensions;

public class StaleNonceError extends Error {
    private NonceAttribute _nonce;
    private RealmAttribute _realm;

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 438 Stale Nonce.");
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

    public StaleNonceError() {
        this((String) null, (NonceAttribute) null, (RealmAttribute) null);
    }

    public StaleNonceError(String str) {
        this(str, (NonceAttribute) null, (RealmAttribute) null);
    }

    public StaleNonceError(String str, NonceAttribute nonceAttribute, RealmAttribute realmAttribute) {
        super(ErrorCode.StunStaleNonce, str);
        setNonce(nonceAttribute);
        setRealm(realmAttribute);
    }

    public StaleNonceError(NonceAttribute nonceAttribute, RealmAttribute realmAttribute) {
        this((String) null, nonceAttribute, realmAttribute);
    }
}
