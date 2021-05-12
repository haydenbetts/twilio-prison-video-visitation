package com.pusher.client.crypto.nacl;

import com.pusher.client.crypto.nacl.TweetNaclFast;
import com.pusher.client.util.internal.Preconditions;
import java.util.Arrays;

public class SecretBoxOpener {
    private byte[] key;

    public SecretBoxOpener(byte[] bArr) {
        Preconditions.checkNotNull(bArr, "null key passed");
        boolean z = bArr.length == 32;
        Preconditions.checkArgument(z, "key length must be 32 bytes, but is " + bArr.length + " bytes");
        this.key = bArr;
    }

    public String open(byte[] bArr, byte[] bArr2) throws AuthenticityException {
        Preconditions.checkNotNull(this.key, "key has been cleared, create new instance");
        boolean z = bArr2.length == 24;
        Preconditions.checkArgument(z, "nonce length must be 24 bytes, but is " + this.key.length + " bytes");
        try {
            return new String(new TweetNaclFast.SecretBox(this.key).open(bArr, bArr2));
        } catch (Exception unused) {
            throw new AuthenticityException();
        }
    }

    public void clearKey() {
        Arrays.fill(this.key, (byte) 0);
        if (this.key[0] == 0) {
            this.key = null;
            return;
        }
        throw new SecurityException("key not cleared correctly");
    }
}
