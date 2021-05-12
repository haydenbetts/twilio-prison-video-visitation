package com.pusher.client.crypto.nacl;

public class SecretBoxOpenerFactory {
    public SecretBoxOpener create(byte[] bArr) {
        return new SecretBoxOpener(bArr);
    }
}
