package org.java_websocket.client;

import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketListener;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;

public abstract class WebSocketClient extends AbstractWebSocket implements Runnable, WebSocket {
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private Thread connectReadThread;
    private int connectTimeout;
    private DnsResolver dnsResolver;
    private Draft draft;
    /* access modifiers changed from: private */
    public WebSocketImpl engine;
    private Map<String, String> headers;
    /* access modifiers changed from: private */
    public OutputStream ostream;
    private Proxy proxy;
    /* access modifiers changed from: private */
    public Socket socket;
    private SocketFactory socketFactory;
    protected URI uri;
    /* access modifiers changed from: private */
    public Thread writeThread;

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exc);

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(ServerHandshake serverHandshake);

    public final void onWriteDemand(WebSocket webSocket) {
    }

    public WebSocketClient(URI uri2) {
        this(uri2, (Draft) new Draft_6455());
    }

    public WebSocketClient(URI uri2, Draft draft2) {
        this(uri2, draft2, (Map<String, String>) null, 0);
    }

    public WebSocketClient(URI uri2, Map<String, String> map) {
        this(uri2, new Draft_6455(), map);
    }

    public WebSocketClient(URI uri2, Draft draft2, Map<String, String> map) {
        this(uri2, draft2, map, 0);
    }

    public WebSocketClient(URI uri2, Draft draft2, Map<String, String> map, int i) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.socketFactory = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        this.dnsResolver = null;
        if (uri2 == null) {
            throw new IllegalArgumentException();
        } else if (draft2 != null) {
            this.uri = uri2;
            this.draft = draft2;
            this.dnsResolver = new DnsResolver() {
                public InetAddress resolve(URI uri) throws UnknownHostException {
                    return InetAddress.getByName(uri.getHost());
                }
            };
            if (map != null) {
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                this.headers = treeMap;
                treeMap.putAll(map);
            }
            this.connectTimeout = i;
            setTcpNoDelay(false);
            setReuseAddr(false);
            this.engine = new WebSocketImpl((WebSocketListener) this, draft2);
        } else {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
    }

    public URI getURI() {
        return this.uri;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void addHeader(String str, String str2) {
        if (this.headers == null) {
            this.headers = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        }
        this.headers.put(str, str2);
    }

    public String removeHeader(String str) {
        Map<String, String> map = this.headers;
        if (map == null) {
            return null;
        }
        return map.remove(str);
    }

    public void clearHeaders() {
        this.headers = null;
    }

    public void setDnsResolver(DnsResolver dnsResolver2) {
        this.dnsResolver = dnsResolver2;
    }

    public void reconnect() {
        reset();
        connect();
    }

    public boolean reconnectBlocking() throws InterruptedException {
        reset();
        return connectBlocking();
    }

    private void reset() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == this.writeThread || currentThread == this.connectReadThread) {
            throw new IllegalStateException("You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to insure a successful cleanup.");
        }
        try {
            closeBlocking();
            Thread thread = this.writeThread;
            if (thread != null) {
                thread.interrupt();
                this.writeThread = null;
            }
            Thread thread2 = this.connectReadThread;
            if (thread2 != null) {
                thread2.interrupt();
                this.connectReadThread = null;
            }
            this.draft.reset();
            Socket socket2 = this.socket;
            if (socket2 != null) {
                socket2.close();
                this.socket = null;
            }
            this.connectLatch = new CountDownLatch(1);
            this.closeLatch = new CountDownLatch(1);
            this.engine = new WebSocketImpl((WebSocketListener) this, this.draft);
        } catch (Exception e) {
            onError(e);
            this.engine.closeConnection(1006, e.getMessage());
        }
    }

    public void connect() {
        if (this.connectReadThread == null) {
            Thread thread = new Thread(this);
            this.connectReadThread = thread;
            thread.setName("WebSocketConnectReadThread-" + this.connectReadThread.getId());
            this.connectReadThread.start();
            return;
        }
        throw new IllegalStateException("WebSocketClient objects are not reuseable");
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.engine.isOpen();
    }

    public boolean connectBlocking(long j, TimeUnit timeUnit) throws InterruptedException {
        connect();
        return this.connectLatch.await(j, timeUnit) && this.engine.isOpen();
    }

    public void close() {
        if (this.writeThread != null) {
            this.engine.close(1000);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    public void send(String str) {
        this.engine.send(str);
    }

    public void send(byte[] bArr) {
        this.engine.send(bArr);
    }

    public <T> T getAttachment() {
        return this.engine.getAttachment();
    }

    public <T> void setAttachment(T t) {
        this.engine.setAttachment(t);
    }

    /* access modifiers changed from: protected */
    public Collection<WebSocket> getConnections() {
        return Collections.singletonList(this.engine);
    }

    public void sendPing() {
        this.engine.sendPing();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e A[Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008c A[Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ce A[Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }, LOOP:0: B:24:0x00bc->B:31:0x00ce, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r8 = this;
            r0 = -1
            javax.net.SocketFactory r1 = r8.socketFactory     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x000e
            java.net.Socket r1 = r1.createSocket()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r8.socket = r1     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            goto L_0x0023
        L_0x000e:
            java.net.Socket r1 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            if (r1 != 0) goto L_0x001d
            java.net.Socket r1 = new java.net.Socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Proxy r4 = r8.proxy     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r1.<init>(r4)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r8.socket = r1     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r1 = 1
            goto L_0x0024
        L_0x001d:
            boolean r1 = r1.isClosed()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            if (r1 != 0) goto L_0x00f5
        L_0x0023:
            r1 = 0
        L_0x0024:
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            boolean r5 = r8.isTcpNoDelay()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r4.setTcpNoDelay(r5)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            boolean r5 = r8.isReuseAddr()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r4.setReuseAddress(r5)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            boolean r4 = r4.isConnected()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            if (r4 != 0) goto L_0x0056
            java.net.InetSocketAddress r4 = new java.net.InetSocketAddress     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            org.java_websocket.client.DnsResolver r5 = r8.dnsResolver     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.URI r6 = r8.uri     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.InetAddress r5 = r5.resolve(r6)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            int r6 = r8.getPort()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Socket r5 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            int r6 = r8.connectTimeout     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r5.connect(r4, r6)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
        L_0x0056:
            r4 = 0
            if (r1 == 0) goto L_0x0086
            java.lang.String r1 = "wss"
            java.net.URI r5 = r8.uri     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.lang.String r5 = r5.getScheme()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            boolean r1 = r1.equals(r5)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            if (r1 == 0) goto L_0x0086
            java.lang.String r1 = "TLSv1.2"
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r1.init(r4, r4, r4)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            javax.net.ssl.SSLSocketFactory r1 = r1.getSocketFactory()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Socket r5 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.URI r6 = r8.uri     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.lang.String r6 = r6.getHost()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            int r7 = r8.getPort()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Socket r1 = r1.createSocket(r5, r6, r7, r2)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r8.socket = r1     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
        L_0x0086:
            java.net.Socket r1 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            boolean r2 = r1 instanceof javax.net.ssl.SSLSocket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            if (r2 == 0) goto L_0x0098
            javax.net.ssl.SSLSocket r1 = (javax.net.ssl.SSLSocket) r1     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            javax.net.ssl.SSLParameters r2 = r1.getSSLParameters()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r8.onSetSSLParameters(r2)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r1.setSSLParameters(r2)     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
        L_0x0098:
            java.net.Socket r1 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.net.Socket r2 = r8.socket     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.io.OutputStream r2 = r2.getOutputStream()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r8.ostream = r2     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r8.sendHandshake()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            java.lang.Thread r2 = new java.lang.Thread
            org.java_websocket.client.WebSocketClient$WebsocketWriteThread r5 = new org.java_websocket.client.WebSocketClient$WebsocketWriteThread
            r5.<init>(r8)
            r2.<init>(r5)
            r8.writeThread = r2
            r2.start()
            r2 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r2]
        L_0x00bc:
            boolean r5 = r8.isClosing()     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            if (r5 != 0) goto L_0x00d8
            boolean r5 = r8.isClosed()     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            if (r5 != 0) goto L_0x00d8
            int r5 = r1.read(r2)     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            if (r5 == r0) goto L_0x00d8
            org.java_websocket.WebSocketImpl r6 = r8.engine     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.wrap(r2, r3, r5)     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            r6.decode(r5)     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            goto L_0x00bc
        L_0x00d8:
            org.java_websocket.WebSocketImpl r0 = r8.engine     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            r0.eot()     // Catch:{ IOException -> 0x00ee, RuntimeException -> 0x00de }
            goto L_0x00f2
        L_0x00de:
            r0 = move-exception
            r8.onError(r0)
            org.java_websocket.WebSocketImpl r1 = r8.engine
            r2 = 1006(0x3ee, float:1.41E-42)
            java.lang.String r0 = r0.getMessage()
            r1.closeConnection((int) r2, (java.lang.String) r0)
            goto L_0x00f2
        L_0x00ee:
            r0 = move-exception
            r8.handleIOException(r0)
        L_0x00f2:
            r8.connectReadThread = r4
            return
        L_0x00f5:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            r1.<init>()     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
            throw r1     // Catch:{ Exception -> 0x012a, InternalError -> 0x00fb }
        L_0x00fb:
            r1 = move-exception
            java.lang.Throwable r2 = r1.getCause()
            boolean r2 = r2 instanceof java.lang.reflect.InvocationTargetException
            if (r2 == 0) goto L_0x0129
            java.lang.Throwable r2 = r1.getCause()
            java.lang.Throwable r2 = r2.getCause()
            boolean r2 = r2 instanceof java.io.IOException
            if (r2 == 0) goto L_0x0129
            java.lang.Throwable r1 = r1.getCause()
            java.lang.Throwable r1 = r1.getCause()
            java.io.IOException r1 = (java.io.IOException) r1
            org.java_websocket.WebSocketImpl r2 = r8.engine
            r8.onWebsocketError(r2, r1)
            org.java_websocket.WebSocketImpl r2 = r8.engine
            java.lang.String r1 = r1.getMessage()
            r2.closeConnection((int) r0, (java.lang.String) r1)
            return
        L_0x0129:
            throw r1
        L_0x012a:
            r1 = move-exception
            org.java_websocket.WebSocketImpl r2 = r8.engine
            r8.onWebsocketError(r2, r1)
            org.java_websocket.WebSocketImpl r2 = r8.engine
            java.lang.String r1 = r1.getMessage()
            r2.closeConnection((int) r0, (java.lang.String) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.client.WebSocketClient.run():void");
    }

    /* access modifiers changed from: protected */
    public void onSetSSLParameters(SSLParameters sSLParameters) {
        sSLParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if ("wss".equals(scheme)) {
            return WebSocketImpl.DEFAULT_WSS_PORT;
        }
        if ("ws".equals(scheme)) {
            return 80;
        }
        throw new IllegalArgumentException("unknown scheme: " + scheme);
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String str;
        String rawPath = this.uri.getRawPath();
        String rawQuery = this.uri.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + '?' + rawQuery;
        }
        int port = getPort();
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri.getHost());
        if (port == 80 || port == 443) {
            str = "";
        } else {
            str = Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + port;
        }
        sb.append(str);
        String sb2 = sb.toString();
        HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(rawPath);
        handshakeImpl1Client.put("Host", sb2);
        Map<String, String> map = this.headers;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                handshakeImpl1Client.put((String) next.getKey(), (String) next.getValue());
            }
        }
        this.engine.startHandshake(handshakeImpl1Client);
    }

    public ReadyState getReadyState() {
        return this.engine.getReadyState();
    }

    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        startConnectionLostTimer();
        onOpen((ServerHandshake) handshakedata);
        this.connectLatch.countDown();
    }

    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        stopConnectionLostTimer();
        Thread thread = this.writeThread;
        if (thread != null) {
            thread.interrupt();
        }
        onClose(i, str, z);
        this.connectLatch.countDown();
        this.closeLatch.countDown();
    }

    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(exc);
    }

    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    public WebSocket getConnection() {
        return this.engine;
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        Socket socket2 = this.socket;
        if (socket2 != null) {
            return (InetSocketAddress) socket2.getLocalSocketAddress();
        }
        return null;
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        Socket socket2 = this.socket;
        if (socket2 != null) {
            return (InetSocketAddress) socket2.getRemoteSocketAddress();
        }
        return null;
    }

    private class WebsocketWriteThread implements Runnable {
        private final WebSocketClient webSocketClient;

        WebsocketWriteThread(WebSocketClient webSocketClient2) {
            this.webSocketClient = webSocketClient2;
        }

        public void run() {
            Thread currentThread = Thread.currentThread();
            currentThread.setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            try {
                runWriteData();
            } catch (IOException e) {
                WebSocketClient.this.handleIOException(e);
            } catch (Throwable th) {
                closeSocket();
                Thread unused = WebSocketClient.this.writeThread = null;
                throw th;
            }
            closeSocket();
            Thread unused2 = WebSocketClient.this.writeThread = null;
        }

        private void runWriteData() throws IOException {
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer take = WebSocketClient.this.engine.outQueue.take();
                    WebSocketClient.this.ostream.write(take.array(), 0, take.limit());
                    WebSocketClient.this.ostream.flush();
                } catch (InterruptedException unused) {
                    for (ByteBuffer byteBuffer : WebSocketClient.this.engine.outQueue) {
                        WebSocketClient.this.ostream.write(byteBuffer.array(), 0, byteBuffer.limit());
                        WebSocketClient.this.ostream.flush();
                    }
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        private void closeSocket() {
            try {
                if (WebSocketClient.this.socket != null) {
                    WebSocketClient.this.socket.close();
                }
            } catch (IOException e) {
                WebSocketClient.this.onWebsocketError(this.webSocketClient, e);
            }
        }
    }

    public void setProxy(Proxy proxy2) {
        if (proxy2 != null) {
            this.proxy = proxy2;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Deprecated
    public void setSocket(Socket socket2) {
        if (this.socket == null) {
            this.socket = socket2;
            return;
        }
        throw new IllegalStateException("socket has already been set");
    }

    public void setSocketFactory(SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
    }

    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        this.engine.sendFragmentedFrame(opcode, byteBuffer, z);
    }

    public boolean isOpen() {
        return this.engine.isOpen();
    }

    public boolean isFlushAndClose() {
        return this.engine.isFlushAndClose();
    }

    public boolean isClosed() {
        return this.engine.isClosed();
    }

    public boolean isClosing() {
        return this.engine.isClosing();
    }

    public boolean hasBufferedData() {
        return this.engine.hasBufferedData();
    }

    public void close(int i) {
        this.engine.close(i);
    }

    public void close(int i, String str) {
        this.engine.close(i, str);
    }

    public void closeConnection(int i, String str) {
        this.engine.closeConnection(i, str);
    }

    public void send(ByteBuffer byteBuffer) {
        this.engine.send(byteBuffer);
    }

    public void sendFrame(Framedata framedata) {
        this.engine.sendFrame(framedata);
    }

    public void sendFrame(Collection<Framedata> collection) {
        this.engine.sendFrame(collection);
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.engine.getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.engine.getRemoteSocketAddress();
    }

    public String getResourceDescriptor() {
        return this.uri.getPath();
    }

    public boolean hasSSLSupport() {
        return this.engine.hasSSLSupport();
    }

    public SSLSession getSSLSession() {
        return this.engine.getSSLSession();
    }

    /* access modifiers changed from: private */
    public void handleIOException(IOException iOException) {
        if (iOException instanceof SSLException) {
            onError(iOException);
        }
        this.engine.eot();
    }
}
