package com.pusher.client.channel.impl;

import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.Authorizer;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.channel.PrivateEncryptedChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.crypto.nacl.AuthenticityException;
import com.pusher.client.crypto.nacl.SecretBoxOpener;
import com.pusher.client.crypto.nacl.SecretBoxOpenerFactory;
import com.pusher.client.util.Factory;
import com.pusher.client.util.internal.Base64;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PrivateEncryptedChannelImpl extends ChannelImpl implements PrivateEncryptedChannel {
    private final Authorizer authorizer;
    private final InternalConnection connection;
    private ConnectionEventListener disposeSecretBoxOpenerOnDisconnectedListener = new ConnectionEventListener() {
        public void onError(String str, String str2, Exception exc) {
        }

        public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
            PrivateEncryptedChannelImpl.this.disposeSecretBoxOpener();
        }
    };
    private SecretBoxOpener secretBoxOpener;
    private SecretBoxOpenerFactory secretBoxOpenerFactory;

    public PrivateEncryptedChannelImpl(InternalConnection internalConnection, String str, Authorizer authorizer2, Factory factory, SecretBoxOpenerFactory secretBoxOpenerFactory2) {
        super(str, factory);
        this.connection = internalConnection;
        this.authorizer = authorizer2;
        this.secretBoxOpenerFactory = secretBoxOpenerFactory2;
    }

    public void bind(String str, SubscriptionEventListener subscriptionEventListener) {
        if (subscriptionEventListener instanceof PrivateEncryptedChannelEventListener) {
            super.bind(str, subscriptionEventListener);
            return;
        }
        throw new IllegalArgumentException("Only instances of PrivateEncryptedChannelEventListener can be bound to a private encrypted channel");
    }

    public String toSubscribeMessage() {
        String authenticate = authenticate();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Modules.CHANNEL_MODULE, this.name);
        linkedHashMap.put("auth", authenticate);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("event", "pusher:subscribe");
        linkedHashMap2.put("data", linkedHashMap);
        return this.GSON.toJson((Object) linkedHashMap2);
    }

    private String authenticate() {
        try {
            Map map = (Map) this.GSON.fromJson(getAuthResponse(), Map.class);
            String str = (String) map.get("auth");
            String str2 = (String) map.get("shared_secret");
            if (str == null || str2 == null) {
                throw new AuthorizationFailureException("Didn't receive all the fields expected from the Authorizer, expected an auth and shared_secret.");
            }
            createSecretBoxOpener(Base64.decode(str2));
            return str;
        } catch (AuthorizationFailureException e) {
            throw e;
        } catch (Exception e2) {
            throw new AuthorizationFailureException("Unable to parse response from Authorizer", e2);
        }
    }

    private void createSecretBoxOpener(byte[] bArr) {
        this.secretBoxOpener = this.secretBoxOpenerFactory.create(bArr);
        setListenerToDisposeSecretBoxOpenerOnDisconnected();
    }

    private void setListenerToDisposeSecretBoxOpenerOnDisconnected() {
        this.connection.bind(ConnectionState.DISCONNECTED, this.disposeSecretBoxOpenerOnDisconnectedListener);
    }

    public void updateState(ChannelState channelState) {
        super.updateState(channelState);
        if (channelState == ChannelState.UNSUBSCRIBED) {
            disposeSecretBoxOpener();
        }
    }

    public PusherEvent prepareEvent(String str, String str2) {
        try {
            return decryptMessage(str2);
        } catch (AuthenticityException unused) {
            disposeSecretBoxOpener();
            authenticate();
            try {
                return decryptMessage(str2);
            } catch (AuthenticityException unused2) {
                notifyListenersOfDecryptFailure(str, "Failed to decrypt message.");
                return null;
            }
        }
    }

    private void notifyListenersOfDecryptFailure(String str, String str2) {
        Set<SubscriptionEventListener> interestedListeners = getInterestedListeners(str);
        if (interestedListeners != null) {
            Iterator<SubscriptionEventListener> it = interestedListeners.iterator();
            while (it.hasNext()) {
                ((PrivateEncryptedChannelEventListener) it.next()).onDecryptionFailure(str, str2);
            }
        }
    }

    private class EncryptedReceivedData {
        String ciphertext;
        String nonce;

        private EncryptedReceivedData() {
        }

        public byte[] getNonce() {
            return Base64.decode(this.nonce);
        }

        public byte[] getCiphertext() {
            return Base64.decode(this.ciphertext);
        }
    }

    private PusherEvent decryptMessage(String str) {
        Map map = (Map) this.GSON.fromJson(str, (Type) Map.class);
        EncryptedReceivedData encryptedReceivedData = (EncryptedReceivedData) this.GSON.fromJson((String) map.get("data"), EncryptedReceivedData.class);
        map.put("data", this.secretBoxOpener.open(encryptedReceivedData.getCiphertext(), encryptedReceivedData.getNonce()));
        return new PusherEvent(map);
    }

    /* access modifiers changed from: private */
    public void disposeSecretBoxOpener() {
        SecretBoxOpener secretBoxOpener2 = this.secretBoxOpener;
        if (secretBoxOpener2 != null) {
            secretBoxOpener2.clearKey();
            this.secretBoxOpener = null;
            removeListenerToDisposeSecretBoxOpenerOnDisconnected();
        }
    }

    private void removeListenerToDisposeSecretBoxOpenerOnDisconnected() {
        this.connection.unbind(ConnectionState.DISCONNECTED, this.disposeSecretBoxOpenerOnDisconnectedListener);
    }

    private String getAuthResponse() {
        return this.authorizer.authorize(getName(), this.connection.getSocketId());
    }

    /* access modifiers changed from: protected */
    public String[] getDisallowedNameExpressions() {
        return new String[]{"^(?!private-encrypted-).*"};
    }

    public String toString() {
        return String.format("[Private Encrypted Channel: name=%s]", new Object[]{this.name});
    }
}
