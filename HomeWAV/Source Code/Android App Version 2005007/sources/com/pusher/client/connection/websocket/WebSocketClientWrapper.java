package com.pusher.client.connection.websocket;

import java.io.IOException;
import java.net.Proxy;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketClientWrapper extends WebSocketClient {
    private static final String WSS_SCHEME = "wss";
    private WebSocketListener webSocketListener;

    public WebSocketClientWrapper(URI uri, Proxy proxy, WebSocketListener webSocketListener2) throws SSLException {
        super(uri);
        if (uri.getScheme().equals(WSS_SCHEME)) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                setSocket(instance.getSocketFactory().createSocket());
            } catch (IOException e) {
                throw new SSLException(e);
            } catch (NoSuchAlgorithmException e2) {
                throw new SSLException(e2);
            } catch (KeyManagementException e3) {
                throw new SSLException(e3);
            }
        }
        this.webSocketListener = webSocketListener2;
        setProxy(proxy);
    }

    public void onOpen(ServerHandshake serverHandshake) {
        WebSocketListener webSocketListener2 = this.webSocketListener;
        if (webSocketListener2 != null) {
            webSocketListener2.onOpen(serverHandshake);
        }
    }

    public void onMessage(String str) {
        WebSocketListener webSocketListener2 = this.webSocketListener;
        if (webSocketListener2 != null) {
            webSocketListener2.onMessage(str);
        }
    }

    public void onClose(int i, String str, boolean z) {
        WebSocketListener webSocketListener2 = this.webSocketListener;
        if (webSocketListener2 != null) {
            webSocketListener2.onClose(i, str, z);
        }
    }

    public void onError(Exception exc) {
        WebSocketListener webSocketListener2 = this.webSocketListener;
        if (webSocketListener2 != null) {
            webSocketListener2.onError(exc);
        }
    }

    public void removeWebSocketListener() {
        this.webSocketListener = null;
    }

    /* access modifiers changed from: protected */
    public void onSetSSLParameters(SSLParameters sSLParameters) {
        try {
            super.onSetSSLParameters(sSLParameters);
        } catch (NoSuchMethodError unused) {
        }
    }
}
