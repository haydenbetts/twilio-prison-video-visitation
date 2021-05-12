package com.pusher.client.channel;

public interface PrivateEncryptedChannelEventListener extends PrivateChannelEventListener {
    void onDecryptionFailure(String str, String str2);
}
