package com.pusher.client.util;

import com.pusher.client.Authorizer;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.impl.ChannelImpl;
import com.pusher.client.channel.impl.ChannelManager;
import com.pusher.client.channel.impl.PresenceChannelImpl;
import com.pusher.client.channel.impl.PrivateChannelImpl;
import com.pusher.client.channel.impl.PrivateEncryptedChannelImpl;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.connection.websocket.WebSocketClientWrapper;
import com.pusher.client.connection.websocket.WebSocketConnection;
import com.pusher.client.connection.websocket.WebSocketListener;
import com.pusher.client.crypto.nacl.SecretBoxOpenerFactory;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import javax.net.ssl.SSLException;

public class Factory {
    /* access modifiers changed from: private */
    public static final Object eventLock = new Object();
    private ChannelManager channelManager;
    private InternalConnection connection;
    private ExecutorService eventQueue;
    private ScheduledExecutorService timers;

    public synchronized InternalConnection getConnection(String str, PusherOptions pusherOptions) {
        if (this.connection == null) {
            try {
                this.connection = new WebSocketConnection(pusherOptions.buildUrl(str), pusherOptions.getActivityTimeout(), pusherOptions.getPongTimeout(), pusherOptions.getMaxReconnectionAttempts(), pusherOptions.getMaxReconnectGapInSeconds(), pusherOptions.getProxy(), this);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Failed to initialise connection", e);
            }
        }
        return this.connection;
    }

    public WebSocketClientWrapper newWebSocketClientWrapper(URI uri, Proxy proxy, WebSocketListener webSocketListener) throws SSLException {
        return new WebSocketClientWrapper(uri, proxy, webSocketListener);
    }

    public synchronized ScheduledExecutorService getTimers() {
        if (this.timers == null) {
            this.timers = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory("timers"));
        }
        return this.timers;
    }

    public ChannelImpl newPublicChannel(String str) {
        return new ChannelImpl(str, this);
    }

    public PrivateChannelImpl newPrivateChannel(InternalConnection internalConnection, String str, Authorizer authorizer) {
        return new PrivateChannelImpl(internalConnection, str, authorizer, this);
    }

    public PrivateEncryptedChannelImpl newPrivateEncryptedChannel(InternalConnection internalConnection, String str, Authorizer authorizer) {
        return new PrivateEncryptedChannelImpl(internalConnection, str, authorizer, this, new SecretBoxOpenerFactory());
    }

    public PresenceChannelImpl newPresenceChannel(InternalConnection internalConnection, String str, Authorizer authorizer) {
        return new PresenceChannelImpl(internalConnection, str, authorizer, this);
    }

    public synchronized ChannelManager getChannelManager() {
        if (this.channelManager == null) {
            this.channelManager = new ChannelManager(this);
        }
        return this.channelManager;
    }

    public synchronized void queueOnEventThread(final Runnable runnable) {
        if (this.eventQueue == null) {
            this.eventQueue = Executors.newSingleThreadExecutor(new DaemonThreadFactory("eventQueue"));
        }
        this.eventQueue.execute(new Runnable() {
            public void run() {
                synchronized (Factory.eventLock) {
                    runnable.run();
                }
            }
        });
    }

    public synchronized void shutdownThreads() {
        ExecutorService executorService = this.eventQueue;
        if (executorService != null) {
            executorService.shutdown();
            this.eventQueue = null;
        }
        ScheduledExecutorService scheduledExecutorService = this.timers;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.timers = null;
        }
    }

    private static class DaemonThreadFactory implements ThreadFactory {
        private final String name;

        public DaemonThreadFactory(String str) {
            this.name = str;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("pusher-java-client " + this.name);
            return thread;
        }
    }
}
