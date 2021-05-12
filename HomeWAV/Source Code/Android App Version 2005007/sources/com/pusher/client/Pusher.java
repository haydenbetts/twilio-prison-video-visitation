package com.pusher.client;

import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PresenceChannelEventListener;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.channel.PrivateEncryptedChannelEventListener;
import com.pusher.client.channel.impl.ChannelImpl;
import com.pusher.client.channel.impl.ChannelManager;
import com.pusher.client.channel.impl.PresenceChannelImpl;
import com.pusher.client.channel.impl.PrivateChannelImpl;
import com.pusher.client.channel.impl.PrivateEncryptedChannelImpl;
import com.pusher.client.connection.Connection;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;

public class Pusher implements Client {
    private final ChannelManager channelManager;
    private final InternalConnection connection;
    private final Factory factory;
    private final PusherOptions pusherOptions;

    public Pusher(String str) {
        this(str, new PusherOptions());
    }

    public Pusher(String str, PusherOptions pusherOptions2) {
        this(str, pusherOptions2, new Factory());
    }

    Pusher(String str, PusherOptions pusherOptions2, Factory factory2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("API Key cannot be null or empty");
        } else if (pusherOptions2 != null) {
            this.pusherOptions = pusherOptions2;
            this.factory = factory2;
            InternalConnection connection2 = factory2.getConnection(str, pusherOptions2);
            this.connection = connection2;
            ChannelManager channelManager2 = factory2.getChannelManager();
            this.channelManager = channelManager2;
            channelManager2.setConnection(connection2);
        } else {
            throw new IllegalArgumentException("PusherOptions cannot be null");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect() {
        connect((ConnectionEventListener) null, new ConnectionState[0]);
    }

    public void connect(ConnectionEventListener connectionEventListener, ConnectionState... connectionStateArr) {
        if (connectionEventListener != null) {
            if (connectionStateArr.length == 0) {
                connectionStateArr = new ConnectionState[]{ConnectionState.ALL};
            }
            for (ConnectionState bind : connectionStateArr) {
                this.connection.bind(bind, connectionEventListener);
            }
        } else if (connectionStateArr.length > 0) {
            throw new IllegalArgumentException("Cannot bind to connection states with a null connection event listener");
        }
        this.connection.connect();
    }

    public void disconnect() {
        if (this.connection.getState() == ConnectionState.CONNECTED) {
            this.connection.disconnect();
        }
    }

    public Channel subscribe(String str) {
        return subscribe(str, (ChannelEventListener) null, new String[0]);
    }

    public Channel subscribe(String str, ChannelEventListener channelEventListener, String... strArr) {
        ChannelImpl newPublicChannel = this.factory.newPublicChannel(str);
        this.channelManager.subscribeTo(newPublicChannel, channelEventListener, strArr);
        return newPublicChannel;
    }

    public PrivateChannel subscribePrivate(String str) {
        return subscribePrivate(str, (PrivateChannelEventListener) null, new String[0]);
    }

    public PrivateChannel subscribePrivate(String str, PrivateChannelEventListener privateChannelEventListener, String... strArr) {
        throwExceptionIfNoAuthorizerHasBeenSet();
        PrivateChannelImpl newPrivateChannel = this.factory.newPrivateChannel(this.connection, str, this.pusherOptions.getAuthorizer());
        this.channelManager.subscribeTo(newPrivateChannel, privateChannelEventListener, strArr);
        return newPrivateChannel;
    }

    public PrivateEncryptedChannel subscribePrivateEncrypted(String str, PrivateEncryptedChannelEventListener privateEncryptedChannelEventListener, String... strArr) {
        throwExceptionIfNoAuthorizerHasBeenSet();
        PrivateEncryptedChannelImpl newPrivateEncryptedChannel = this.factory.newPrivateEncryptedChannel(this.connection, str, this.pusherOptions.getAuthorizer());
        this.channelManager.subscribeTo(newPrivateEncryptedChannel, privateEncryptedChannelEventListener, strArr);
        return newPrivateEncryptedChannel;
    }

    public PresenceChannel subscribePresence(String str) {
        return subscribePresence(str, (PresenceChannelEventListener) null, new String[0]);
    }

    public PresenceChannel subscribePresence(String str, PresenceChannelEventListener presenceChannelEventListener, String... strArr) {
        throwExceptionIfNoAuthorizerHasBeenSet();
        PresenceChannelImpl newPresenceChannel = this.factory.newPresenceChannel(this.connection, str, this.pusherOptions.getAuthorizer());
        this.channelManager.subscribeTo(newPresenceChannel, presenceChannelEventListener, strArr);
        return newPresenceChannel;
    }

    public void unsubscribe(String str) {
        this.channelManager.unsubscribeFrom(str);
    }

    private void throwExceptionIfNoAuthorizerHasBeenSet() {
        if (this.pusherOptions.getAuthorizer() == null) {
            throw new IllegalStateException("Cannot subscribe to a private or presence channel because no Authorizer has been set. Call PusherOptions.setAuthorizer() before connecting to Pusher");
        }
    }

    public Channel getChannel(String str) {
        return this.channelManager.getChannel(str);
    }

    public PrivateChannel getPrivateChannel(String str) {
        return this.channelManager.getPrivateChannel(str);
    }

    public PrivateEncryptedChannel getPrivateEncryptedChannel(String str) {
        return this.channelManager.getPrivateEncryptedChannel(str);
    }

    public PresenceChannel getPresenceChannel(String str) {
        return this.channelManager.getPresenceChannel(str);
    }
}
