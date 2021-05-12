package org.bouncycastle.crypto.tls;

import org.bouncycastle.util.Arrays;

class TlsSessionImpl implements TlsSession {
    final byte[] sessionID;
    SessionParameters sessionParameters;

    TlsSessionImpl(byte[] bArr, SessionParameters sessionParameters2) {
        if (bArr == null) {
            throw new IllegalArgumentException("'sessionID' cannot be null");
        } else if (bArr.length < 1 || bArr.length > 32) {
            throw new IllegalArgumentException("'sessionID' must have length between 1 and 32 bytes, inclusive");
        } else {
            this.sessionID = Arrays.clone(bArr);
            this.sessionParameters = sessionParameters2;
        }
    }

    public synchronized SessionParameters exportSessionParameters() {
        SessionParameters sessionParameters2;
        sessionParameters2 = this.sessionParameters;
        return sessionParameters2 == null ? null : sessionParameters2.copy();
    }

    public synchronized byte[] getSessionID() {
        return this.sessionID;
    }

    public synchronized void invalidate() {
        SessionParameters sessionParameters2 = this.sessionParameters;
        if (sessionParameters2 != null) {
            sessionParameters2.clear();
            this.sessionParameters = null;
        }
    }

    public synchronized boolean isResumable() {
        return this.sessionParameters != null;
    }
}
