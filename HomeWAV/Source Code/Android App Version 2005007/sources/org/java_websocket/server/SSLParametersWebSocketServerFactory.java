package org.java_websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import org.java_websocket.SSLSocketChannel2;

public class SSLParametersWebSocketServerFactory extends DefaultSSLWebSocketServerFactory {
    private final SSLParameters sslParameters;

    public SSLParametersWebSocketServerFactory(SSLContext sSLContext, SSLParameters sSLParameters) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor(), sSLParameters);
    }

    public SSLParametersWebSocketServerFactory(SSLContext sSLContext, ExecutorService executorService, SSLParameters sSLParameters) {
        super(sSLContext, executorService);
        if (sSLParameters != null) {
            this.sslParameters = sSLParameters;
            return;
        }
        throw new IllegalArgumentException();
    }

    public ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException {
        SSLEngine createSSLEngine = this.sslcontext.createSSLEngine();
        createSSLEngine.setUseClientMode(false);
        createSSLEngine.setSSLParameters(this.sslParameters);
        return new SSLSocketChannel2(socketChannel, createSSLEngine, this.exec, selectionKey);
    }
}
