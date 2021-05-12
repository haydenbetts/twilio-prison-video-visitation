package com.pusher.client.connection.websocket;

import com.google.gson.Gson;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketConnection implements InternalConnection, WebSocketListener {
    /* access modifiers changed from: private */
    public static final Gson GSON = new Gson();
    private static final String INTERNAL_EVENT_PREFIX = "pusher:";
    private static final String PING_EVENT_SERIALIZED = "{\"event\": \"pusher:ping\"}";
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(WebSocketConnection.class.getName());
    private final ActivityTimer activityTimer;
    private final Map<ConnectionState, Set<ConnectionEventListener>> eventListeners = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public final Factory factory;
    private final int maxReconnectionAttempts;
    private final int maxReconnectionGap;
    private final Proxy proxy;
    private int reconnectAttempts;
    private String socketId;
    /* access modifiers changed from: private */
    public volatile ConnectionState state = ConnectionState.DISCONNECTED;
    /* access modifiers changed from: private */
    public WebSocketClientWrapper underlyingConnection;
    private final URI webSocketUri;

    private boolean shouldReconnect(int i) {
        return i < 4000 || i >= 4100;
    }

    public void onOpen(ServerHandshake serverHandshake) {
    }

    public WebSocketConnection(String str, long j, long j2, int i, int i2, Proxy proxy2, Factory factory2) throws URISyntaxException {
        this.reconnectAttempts = 0;
        String str2 = str;
        this.webSocketUri = new URI(str);
        this.activityTimer = new ActivityTimer(j, j2);
        this.maxReconnectionAttempts = i;
        this.maxReconnectionGap = i2;
        this.proxy = proxy2;
        this.factory = factory2;
        for (ConnectionState put : ConnectionState.values()) {
            this.eventListeners.put(put, Collections.newSetFromMap(new ConcurrentHashMap()));
        }
    }

    public void connect() {
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                if (WebSocketConnection.this.state == ConnectionState.DISCONNECTED) {
                    WebSocketConnection.this.tryConnecting();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void tryConnecting() {
        try {
            this.underlyingConnection = this.factory.newWebSocketClientWrapper(this.webSocketUri, this.proxy, this);
            updateState(ConnectionState.CONNECTING);
            this.underlyingConnection.connect();
        } catch (SSLException e) {
            sendErrorToAllListeners("Error connecting over SSL", (String) null, e);
        }
    }

    public void disconnect() {
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                if (WebSocketConnection.this.state == ConnectionState.CONNECTED) {
                    WebSocketConnection.this.updateState(ConnectionState.DISCONNECTING);
                    WebSocketConnection.this.underlyingConnection.close();
                }
            }
        });
    }

    public void bind(ConnectionState connectionState, ConnectionEventListener connectionEventListener) {
        this.eventListeners.get(connectionState).add(connectionEventListener);
    }

    public boolean unbind(ConnectionState connectionState, ConnectionEventListener connectionEventListener) {
        return this.eventListeners.get(connectionState).remove(connectionEventListener);
    }

    public ConnectionState getState() {
        return this.state;
    }

    public void sendMessage(final String str) {
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                try {
                    if (WebSocketConnection.this.state == ConnectionState.CONNECTED) {
                        WebSocketConnection.this.underlyingConnection.send(str);
                        return;
                    }
                    WebSocketConnection webSocketConnection = WebSocketConnection.this;
                    webSocketConnection.sendErrorToAllListeners("Cannot send a message while in " + WebSocketConnection.this.state + " state", (String) null, (Exception) null);
                } catch (Exception e) {
                    WebSocketConnection webSocketConnection2 = WebSocketConnection.this;
                    webSocketConnection2.sendErrorToAllListeners("An exception occurred while sending message [" + str + "]", (String) null, e);
                }
            }
        });
    }

    public String getSocketId() {
        return this.socketId;
    }

    /* access modifiers changed from: private */
    public void updateState(ConnectionState connectionState) {
        Logger logger = log;
        logger.fine("State transition requested, current [" + this.state + "], new [" + connectionState + "]");
        final ConnectionStateChange connectionStateChange = new ConnectionStateChange(this.state, connectionState);
        this.state = connectionState;
        HashSet<ConnectionEventListener> hashSet = new HashSet<>();
        hashSet.addAll(this.eventListeners.get(ConnectionState.ALL));
        hashSet.addAll(this.eventListeners.get(connectionState));
        for (final ConnectionEventListener connectionEventListener : hashSet) {
            this.factory.queueOnEventThread(new Runnable() {
                public void run() {
                    connectionEventListener.onConnectionStateChange(connectionStateChange);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleEvent(String str, String str2) {
        if (str.startsWith(INTERNAL_EVENT_PREFIX)) {
            handleInternalEvent(str, str2);
        } else {
            this.factory.getChannelManager().onMessage(str, str2);
        }
    }

    private void handleInternalEvent(String str, String str2) {
        if (str.equals("pusher:connection_established")) {
            handleConnectionMessage(str2);
        } else if (str.equals("pusher:error")) {
            handleError(str2);
        }
    }

    private void handleConnectionMessage(String str) {
        Gson gson = GSON;
        this.socketId = (String) ((Map) gson.fromJson((String) ((Map) gson.fromJson(str, Map.class)).get("data"), Map.class)).get("socket_id");
        if (this.state != ConnectionState.CONNECTED) {
            updateState(ConnectionState.CONNECTED);
        }
        this.reconnectAttempts = 0;
    }

    private void handleError(String str) {
        Map map;
        Gson gson = GSON;
        Object obj = ((Map) gson.fromJson(str, Map.class)).get("data");
        if (obj instanceof String) {
            map = (Map) gson.fromJson((String) obj, Map.class);
        } else {
            map = (Map) obj;
        }
        String str2 = (String) map.get("message");
        Object obj2 = map.get("code");
        sendErrorToAllListeners(str2, obj2 != null ? String.valueOf(Math.round(((Double) obj2).doubleValue())) : null, (Exception) null);
    }

    /* access modifiers changed from: private */
    public void sendErrorToAllListeners(String str, String str2, Exception exc) {
        HashSet<ConnectionEventListener> hashSet = new HashSet<>();
        for (Set<ConnectionEventListener> addAll : this.eventListeners.values()) {
            hashSet.addAll(addAll);
        }
        for (final ConnectionEventListener connectionEventListener : hashSet) {
            final String str3 = str;
            final String str4 = str2;
            final Exception exc2 = exc;
            this.factory.queueOnEventThread(new Runnable() {
                public void run() {
                    connectionEventListener.onError(str3, str4, exc2);
                }
            });
        }
    }

    public void onMessage(final String str) {
        this.activityTimer.activity();
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                WebSocketConnection.this.handleEvent((String) ((Map) WebSocketConnection.GSON.fromJson(str, Map.class)).get("event"), str);
            }
        });
    }

    public void onClose(int i, String str, boolean z) {
        if (this.state == ConnectionState.DISCONNECTED || this.state == ConnectionState.RECONNECTING) {
            Logger logger = log;
            logger.warning("Received close from underlying socket when already disconnected.Close code [" + i + "], Reason [" + str + "], Remote [" + z + "]");
            return;
        }
        if (!shouldReconnect(i)) {
            updateState(ConnectionState.DISCONNECTING);
        }
        if (this.state == ConnectionState.CONNECTED || this.state == ConnectionState.CONNECTING) {
            if (this.reconnectAttempts < this.maxReconnectionAttempts) {
                tryReconnecting();
                return;
            }
            updateState(ConnectionState.DISCONNECTING);
            cancelTimeoutsAndTransitonToDisconnected();
        } else if (this.state == ConnectionState.DISCONNECTING) {
            cancelTimeoutsAndTransitonToDisconnected();
        }
    }

    private void tryReconnecting() {
        this.reconnectAttempts++;
        updateState(ConnectionState.RECONNECTING);
        int i = this.maxReconnectionGap;
        int i2 = this.reconnectAttempts;
        this.factory.getTimers().schedule(new Runnable() {
            public void run() {
                WebSocketConnection.this.underlyingConnection.removeWebSocketListener();
                WebSocketConnection.this.tryConnecting();
            }
        }, (long) Math.min(i, i2 * i2), TimeUnit.SECONDS);
    }

    private void cancelTimeoutsAndTransitonToDisconnected() {
        this.activityTimer.cancelTimeouts();
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                WebSocketConnection.this.updateState(ConnectionState.DISCONNECTED);
                WebSocketConnection.this.factory.shutdownThreads();
            }
        });
        this.reconnectAttempts = 0;
    }

    public void onError(final Exception exc) {
        this.factory.queueOnEventThread(new Runnable() {
            public void run() {
                WebSocketConnection.this.sendErrorToAllListeners("An exception was thrown by the websocket", (String) null, exc);
            }
        });
    }

    private class ActivityTimer {
        private final long activityTimeout;
        private Future<?> pingTimer;
        private final long pongTimeout;
        private Future<?> pongTimer;

        ActivityTimer(long j, long j2) {
            this.activityTimeout = j;
            this.pongTimeout = j2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void activity() {
            Future<?> future = this.pongTimer;
            if (future != null) {
                future.cancel(true);
            }
            Future<?> future2 = this.pingTimer;
            if (future2 != null) {
                future2.cancel(false);
            }
            this.pingTimer = WebSocketConnection.this.factory.getTimers().schedule(new Runnable() {
                public void run() {
                    WebSocketConnection.log.fine("Sending ping");
                    WebSocketConnection.this.sendMessage(WebSocketConnection.PING_EVENT_SERIALIZED);
                    ActivityTimer.this.schedulePongCheck();
                }
            }, this.activityTimeout, TimeUnit.MILLISECONDS);
        }

        /* access modifiers changed from: package-private */
        public synchronized void cancelTimeouts() {
            Future<?> future = this.pingTimer;
            if (future != null) {
                future.cancel(false);
            }
            Future<?> future2 = this.pongTimer;
            if (future2 != null) {
                future2.cancel(false);
            }
        }

        /* access modifiers changed from: private */
        public synchronized void schedulePongCheck() {
            Future<?> future = this.pongTimer;
            if (future != null) {
                future.cancel(false);
            }
            this.pongTimer = WebSocketConnection.this.factory.getTimers().schedule(new Runnable() {
                public void run() {
                    WebSocketConnection.log.fine("Timed out awaiting pong from server - disconnecting");
                    WebSocketConnection.this.underlyingConnection.removeWebSocketListener();
                    WebSocketConnection.this.underlyingConnection.close();
                    WebSocketConnection.this.onClose(-1, "Pong timeout", false);
                }
            }, this.pongTimeout, TimeUnit.MILLISECONDS);
        }
    }
}
