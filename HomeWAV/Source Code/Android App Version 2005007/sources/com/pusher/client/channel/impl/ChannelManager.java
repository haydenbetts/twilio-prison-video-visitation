package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager implements ConnectionEventListener {
    private static final Gson GSON = new Gson();
    private final Map<String, InternalChannel> channelNameToChannelMap = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public InternalConnection connection;
    private final Factory factory;

    public void onError(String str, String str2, Exception exc) {
    }

    public ChannelManager(Factory factory2) {
        this.factory = factory2;
    }

    public Channel getChannel(String str) {
        if (str.startsWith("private-")) {
            throw new IllegalArgumentException("Please use the getPrivateChannel method");
        } else if (!str.startsWith("presence-")) {
            return findChannelInChannelMap(str);
        } else {
            throw new IllegalArgumentException("Please use the getPresenceChannel method");
        }
    }

    public PrivateChannel getPrivateChannel(String str) throws IllegalArgumentException {
        if (str.startsWith("private-")) {
            return (PrivateChannel) findChannelInChannelMap(str);
        }
        throw new IllegalArgumentException("Private channels must begin with 'private-'");
    }

    public PrivateEncryptedChannel getPrivateEncryptedChannel(String str) throws IllegalArgumentException {
        if (str.startsWith("private-encrypted-")) {
            return (PrivateEncryptedChannel) findChannelInChannelMap(str);
        }
        throw new IllegalArgumentException("Encrypted private channels must begin with 'private-encrypted-'");
    }

    public PresenceChannel getPresenceChannel(String str) throws IllegalArgumentException {
        if (str.startsWith("presence-")) {
            return (PresenceChannel) findChannelInChannelMap(str);
        }
        throw new IllegalArgumentException("Presence channels must begin with 'presence-'");
    }

    private InternalChannel findChannelInChannelMap(String str) {
        return this.channelNameToChannelMap.get(str);
    }

    public void setConnection(InternalConnection internalConnection) {
        if (internalConnection != null) {
            InternalConnection internalConnection2 = this.connection;
            if (internalConnection2 != null) {
                internalConnection2.unbind(ConnectionState.CONNECTED, this);
            }
            this.connection = internalConnection;
            internalConnection.bind(ConnectionState.CONNECTED, this);
            return;
        }
        throw new IllegalArgumentException("Cannot construct ChannelManager with a null connection");
    }

    public void subscribeTo(InternalChannel internalChannel, ChannelEventListener channelEventListener, String... strArr) {
        validateArgumentsAndBindEvents(internalChannel, channelEventListener, strArr);
        this.channelNameToChannelMap.put(internalChannel.getName(), internalChannel);
        sendOrQueueSubscribeMessage(internalChannel);
    }

    public void unsubscribeFrom(String str) {
        if (str != null) {
            InternalChannel remove = this.channelNameToChannelMap.remove(str);
            if (remove != null && this.connection.getState() == ConnectionState.CONNECTED) {
                sendUnsubscribeMessage(remove);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Cannot unsubscribe from null channel");
    }

    public void onMessage(String str, String str2) {
        InternalChannel internalChannel;
        Object obj = ((Map) GSON.fromJson(str2, Map.class)).get(Modules.CHANNEL_MODULE);
        if (obj != null && (internalChannel = this.channelNameToChannelMap.get((String) obj)) != null) {
            internalChannel.onMessage(str, str2);
        }
    }

    public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
        if (connectionStateChange.getCurrentState() == ConnectionState.CONNECTED) {
            for (InternalChannel sendOrQueueSubscribeMessage : this.channelNameToChannelMap.values()) {
                sendOrQueueSubscribeMessage(sendOrQueueSubscribeMessage);
            }
        }
    }

    private void sendOrQueueSubscribeMessage(final InternalChannel internalChannel) {
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                if (ChannelManager.this.connection.getState() == ConnectionState.CONNECTED) {
                    try {
                        ChannelManager.this.connection.sendMessage(internalChannel.toSubscribeMessage());
                        internalChannel.updateState(ChannelState.SUBSCRIBE_SENT);
                    } catch (AuthorizationFailureException e) {
                        ChannelManager.this.handleAuthenticationFailure(internalChannel, e);
                    }
                }
            }
        });
    }

    private void sendUnsubscribeMessage(final InternalChannel internalChannel) {
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                ChannelManager.this.connection.sendMessage(internalChannel.toUnsubscribeMessage());
                internalChannel.updateState(ChannelState.UNSUBSCRIBED);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleAuthenticationFailure(final InternalChannel internalChannel, final Exception exc) {
        this.channelNameToChannelMap.remove(internalChannel.getName());
        internalChannel.updateState(ChannelState.FAILED);
        if (internalChannel.getEventListener() != null) {
            this.factory.queueOnEventThread(new Runnable() {
                public void run() {
                    ((PrivateChannelEventListener) internalChannel.getEventListener()).onAuthenticationFailure(exc.getMessage(), exc);
                }
            });
        }
    }

    private void validateArgumentsAndBindEvents(InternalChannel internalChannel, ChannelEventListener channelEventListener, String... strArr) {
        if (internalChannel == null) {
            throw new IllegalArgumentException("Cannot subscribe to a null channel");
        } else if (!this.channelNameToChannelMap.containsKey(internalChannel.getName())) {
            for (String bind : strArr) {
                internalChannel.bind(bind, channelEventListener);
            }
            internalChannel.setEventListener(channelEventListener);
        } else {
            throw new IllegalArgumentException("Already subscribed to a channel with name " + internalChannel.getName());
        }
    }
}
