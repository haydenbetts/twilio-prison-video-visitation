package org.java_websocket.server;

import java.io.IOException;
import java.lang.Thread;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.SocketChannelIOHelper;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.exceptions.WrappedIOException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebSocketServer extends AbstractWebSocket implements Runnable {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final InetSocketAddress address;
    private BlockingQueue<ByteBuffer> buffers;
    private final Collection<WebSocket> connections;
    protected List<WebSocketWorker> decoders;
    private List<Draft> drafts;
    private List<WebSocketImpl> iqueue;
    private final AtomicBoolean isclosed;
    /* access modifiers changed from: private */
    public final Logger log;
    private int maxPendingConnections;
    private int queueinvokes;
    private final AtomicInteger queuesize;
    private Selector selector;
    private Thread selectorthread;
    private ServerSocketChannel server;
    private WebSocketServerFactory wsf;

    public abstract void onClose(WebSocket webSocket, int i, String str, boolean z);

    public void onCloseInitiated(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str, boolean z) {
    }

    /* access modifiers changed from: protected */
    public boolean onConnect(SelectionKey selectionKey) {
        return true;
    }

    public abstract void onError(WebSocket webSocket, Exception exc);

    public abstract void onMessage(WebSocket webSocket, String str);

    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(WebSocket webSocket, ClientHandshake clientHandshake);

    public abstract void onStart();

    /* access modifiers changed from: protected */
    public void releaseBuffers(WebSocket webSocket) throws InterruptedException {
    }

    public WebSocketServer() {
        this(new InetSocketAddress(80), AVAILABLE_PROCESSORS, (List<Draft>) null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, AVAILABLE_PROCESSORS, (List<Draft>) null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i) {
        this(inetSocketAddress, i, (List<Draft>) null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, List<Draft> list) {
        this(inetSocketAddress, AVAILABLE_PROCESSORS, list);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list) {
        this(inetSocketAddress, i, list, new HashSet());
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list, Collection<WebSocket> collection) {
        this.log = LoggerFactory.getLogger((Class<?>) WebSocketServer.class);
        this.isclosed = new AtomicBoolean(false);
        this.queueinvokes = 0;
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        this.maxPendingConnections = -1;
        if (inetSocketAddress == null || i < 1 || collection == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        if (list == null) {
            this.drafts = Collections.emptyList();
        } else {
            this.drafts = list;
        }
        this.address = inetSocketAddress;
        this.connections = collection;
        setTcpNoDelay(false);
        setReuseAddr(false);
        this.iqueue = new LinkedList();
        this.decoders = new ArrayList(i);
        this.buffers = new LinkedBlockingQueue();
        for (int i2 = 0; i2 < i; i2++) {
            this.decoders.add(new WebSocketWorker());
        }
    }

    public void start() {
        if (this.selectorthread == null) {
            new Thread(this).start();
            return;
        }
        throw new IllegalStateException(getClass().getName() + " can only be started once.");
    }

    public void stop(int i) throws InterruptedException {
        ArrayList<WebSocket> arrayList;
        Selector selector2;
        if (this.isclosed.compareAndSet(false, true)) {
            synchronized (this.connections) {
                arrayList = new ArrayList<>(this.connections);
            }
            for (WebSocket close : arrayList) {
                close.close(1001);
            }
            this.wsf.close();
            synchronized (this) {
                if (!(this.selectorthread == null || (selector2 = this.selector) == null)) {
                    selector2.wakeup();
                    this.selectorthread.join((long) i);
                }
            }
        }
    }

    public void stop() throws IOException, InterruptedException {
        stop(0);
    }

    public Collection<WebSocket> getConnections() {
        Collection<WebSocket> unmodifiableCollection;
        synchronized (this.connections) {
            unmodifiableCollection = Collections.unmodifiableCollection(new ArrayList(this.connections));
        }
        return unmodifiableCollection;
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = r2.server;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getPort() {
        /*
            r2 = this;
            java.net.InetSocketAddress r0 = r2.getAddress()
            int r0 = r0.getPort()
            if (r0 != 0) goto L_0x0016
            java.nio.channels.ServerSocketChannel r1 = r2.server
            if (r1 == 0) goto L_0x0016
            java.net.ServerSocket r0 = r1.socket()
            int r0 = r0.getLocalPort()
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer.getPort():int");
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    public void setMaxPendingConnections(int i) {
        this.maxPendingConnections = i;
    }

    public int getMaxPendingConnections() {
        return this.maxPendingConnections;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0078, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0079, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007b, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x007c, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0082, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0084, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00a4, code lost:
        doServerShutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00a7, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0086 */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0086 A[EDGE_INSN: B:52:0x0086->B:53:? ?: BREAK  , PHI: r0 r2 
      PHI: (r0v9 int) = (r0v3 int), (r0v10 int), (r0v10 int), (r0v10 int), (r0v10 int) binds: [B:13:0x001c, B:24:0x0043, B:48:0x007e, B:49:?, B:28:0x004f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r2v5 int) = (r2v1 int), (r2v6 int), (r2v6 int), (r2v6 int), (r2v6 int) binds: [B:13:0x001c, B:24:0x0043, B:48:0x007e, B:49:?, B:28:0x004f] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:52:0x0086] */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[ExcHandler: ClosedByInterruptException (unused java.nio.channels.ClosedByInterruptException), SYNTHETIC, Splitter:B:13:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:8:? A[ExcHandler: CancelledKeyException (unused java.nio.channels.CancelledKeyException), PHI: r0 r2 
      PHI: (r0v12 int) = (r0v3 int), (r0v10 int), (r0v10 int), (r0v10 int), (r0v10 int) binds: [B:13:0x001c, B:24:0x0043, B:48:0x007e, B:49:?, B:28:0x004f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r2v8 int) = (r2v1 int), (r2v6 int), (r2v6 int), (r2v6 int), (r2v6 int) binds: [B:13:0x001c, B:24:0x0043, B:48:0x007e, B:49:?, B:28:0x004f] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:13:0x001c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            boolean r0 = r7.doEnsureSingleThread()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = r7.doSetupSelectorAndServerThread()
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            r0 = 0
            r1 = 5
            r2 = 5
        L_0x0011:
            r3 = 0
            java.lang.Thread r4 = r7.selectorthread     // Catch:{ RuntimeException -> 0x00aa }
            boolean r4 = r4.isInterrupted()     // Catch:{ RuntimeException -> 0x00aa }
            if (r4 != 0) goto L_0x00ae
            if (r2 == 0) goto L_0x00ae
            java.util.concurrent.atomic.AtomicBoolean r4 = r7.isclosed     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            boolean r4 = r4.get()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            if (r4 == 0) goto L_0x0025
            r0 = 5
        L_0x0025:
            java.nio.channels.Selector r4 = r7.selector     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            long r5 = (long) r0     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            int r4 = r4.select(r5)     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            if (r4 != 0) goto L_0x0038
            java.util.concurrent.atomic.AtomicBoolean r4 = r7.isclosed     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            boolean r4 = r4.get()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            if (r4 == 0) goto L_0x0038
            int r2 = r2 + -1
        L_0x0038:
            java.nio.channels.Selector r4 = r7.selector     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            java.util.Set r4 = r4.selectedKeys()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0095, IOException -> 0x008e, InterruptedException -> 0x0086 }
            r5 = r3
        L_0x0043:
            boolean r6 = r4.hasNext()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0084, IOException -> 0x0082, InterruptedException -> 0x0086 }
            if (r6 == 0) goto L_0x007e
            java.lang.Object r6 = r4.next()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0084, IOException -> 0x0082, InterruptedException -> 0x0086 }
            java.nio.channels.SelectionKey r6 = (java.nio.channels.SelectionKey) r6     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0084, IOException -> 0x0082, InterruptedException -> 0x0086 }
            boolean r5 = r6.isValid()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
            if (r5 != 0) goto L_0x0056
            goto L_0x0076
        L_0x0056:
            boolean r5 = r6.isAcceptable()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
            if (r5 == 0) goto L_0x0060
            r7.doAccept(r6, r4)     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
            goto L_0x0076
        L_0x0060:
            boolean r5 = r6.isReadable()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
            if (r5 == 0) goto L_0x006d
            boolean r5 = r7.doRead(r6, r4)     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
            if (r5 != 0) goto L_0x006d
            goto L_0x0076
        L_0x006d:
            boolean r5 = r6.isWritable()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
            if (r5 == 0) goto L_0x0076
            r7.doWrite(r6)     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x007b, IOException -> 0x0078, InterruptedException -> 0x0086 }
        L_0x0076:
            r5 = r6
            goto L_0x0043
        L_0x0078:
            r4 = move-exception
            r5 = r6
            goto L_0x0090
        L_0x007b:
            r4 = move-exception
            r5 = r6
            goto L_0x0097
        L_0x007e:
            r7.doAdditionalRead()     // Catch:{ CancelledKeyException -> 0x0011, ClosedByInterruptException -> 0x00a4, WrappedIOException -> 0x0084, IOException -> 0x0082, InterruptedException -> 0x0086 }
            goto L_0x0011
        L_0x0082:
            r4 = move-exception
            goto L_0x0090
        L_0x0084:
            r4 = move-exception
            goto L_0x0097
        L_0x0086:
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ RuntimeException -> 0x00aa }
            r4.interrupt()     // Catch:{ RuntimeException -> 0x00aa }
            goto L_0x0011
        L_0x008e:
            r4 = move-exception
            r5 = r3
        L_0x0090:
            r7.handleIOException(r5, r3, r4)     // Catch:{ RuntimeException -> 0x00aa }
            goto L_0x0011
        L_0x0095:
            r4 = move-exception
            r5 = r3
        L_0x0097:
            org.java_websocket.WebSocket r6 = r4.getConnection()     // Catch:{ RuntimeException -> 0x00aa }
            java.io.IOException r4 = r4.getIOException()     // Catch:{ RuntimeException -> 0x00aa }
            r7.handleIOException(r5, r6, r4)     // Catch:{ RuntimeException -> 0x00aa }
            goto L_0x0011
        L_0x00a4:
            r7.doServerShutdown()
            return
        L_0x00a8:
            r0 = move-exception
            goto L_0x00b2
        L_0x00aa:
            r0 = move-exception
            r7.handleFatal(r3, r0)     // Catch:{ all -> 0x00a8 }
        L_0x00ae:
            r7.doServerShutdown()
            return
        L_0x00b2:
            r7.doServerShutdown()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer.run():void");
    }

    private void doAdditionalRead() throws InterruptedException, IOException {
        while (!this.iqueue.isEmpty()) {
            WebSocketImpl remove = this.iqueue.remove(0);
            WrappedByteChannel wrappedByteChannel = (WrappedByteChannel) remove.getChannel();
            ByteBuffer takeBuffer = takeBuffer();
            try {
                if (SocketChannelIOHelper.readMore(takeBuffer, remove, wrappedByteChannel)) {
                    this.iqueue.add(remove);
                }
                if (takeBuffer.hasRemaining()) {
                    remove.inQueue.put(takeBuffer);
                    queue(remove);
                } else {
                    pushBuffer(takeBuffer);
                }
            } catch (IOException e) {
                pushBuffer(takeBuffer);
                throw e;
            }
        }
    }

    private void doAccept(SelectionKey selectionKey, Iterator<SelectionKey> it) throws IOException, InterruptedException {
        if (!onConnect(selectionKey)) {
            selectionKey.cancel();
            return;
        }
        SocketChannel accept = this.server.accept();
        if (accept != null) {
            accept.configureBlocking(false);
            Socket socket = accept.socket();
            socket.setTcpNoDelay(isTcpNoDelay());
            socket.setKeepAlive(true);
            WebSocketImpl createWebSocket = this.wsf.createWebSocket((WebSocketAdapter) this, this.drafts);
            createWebSocket.setSelectionKey(accept.register(this.selector, 1, createWebSocket));
            try {
                createWebSocket.setChannel(this.wsf.wrapChannel(accept, createWebSocket.getSelectionKey()));
                it.remove();
                allocateBuffers(createWebSocket);
            } catch (IOException e) {
                if (createWebSocket.getSelectionKey() != null) {
                    createWebSocket.getSelectionKey().cancel();
                }
                handleIOException(createWebSocket.getSelectionKey(), (WebSocket) null, e);
            }
        }
    }

    private boolean doRead(SelectionKey selectionKey, Iterator<SelectionKey> it) throws InterruptedException, WrappedIOException {
        WebSocketImpl webSocketImpl = (WebSocketImpl) selectionKey.attachment();
        ByteBuffer takeBuffer = takeBuffer();
        if (webSocketImpl.getChannel() == null) {
            selectionKey.cancel();
            handleIOException(selectionKey, webSocketImpl, new IOException());
            return false;
        }
        try {
            if (!SocketChannelIOHelper.read(takeBuffer, webSocketImpl, webSocketImpl.getChannel())) {
                pushBuffer(takeBuffer);
                return true;
            } else if (takeBuffer.hasRemaining()) {
                webSocketImpl.inQueue.put(takeBuffer);
                queue(webSocketImpl);
                it.remove();
                if (!(webSocketImpl.getChannel() instanceof WrappedByteChannel) || !((WrappedByteChannel) webSocketImpl.getChannel()).isNeedRead()) {
                    return true;
                }
                this.iqueue.add(webSocketImpl);
                return true;
            } else {
                pushBuffer(takeBuffer);
                return true;
            }
        } catch (IOException e) {
            pushBuffer(takeBuffer);
            throw new WrappedIOException(webSocketImpl, e);
        }
    }

    private void doWrite(SelectionKey selectionKey) throws WrappedIOException {
        WebSocketImpl webSocketImpl = (WebSocketImpl) selectionKey.attachment();
        try {
            if (SocketChannelIOHelper.batch(webSocketImpl, webSocketImpl.getChannel()) && selectionKey.isValid()) {
                selectionKey.interestOps(1);
            }
        } catch (IOException e) {
            throw new WrappedIOException(webSocketImpl, e);
        }
    }

    private boolean doSetupSelectorAndServerThread() {
        Thread thread = this.selectorthread;
        thread.setName("WebSocketSelector-" + this.selectorthread.getId());
        try {
            ServerSocketChannel open = ServerSocketChannel.open();
            this.server = open;
            open.configureBlocking(false);
            ServerSocket socket = this.server.socket();
            socket.setReceiveBufferSize(16384);
            socket.setReuseAddress(isReuseAddr());
            socket.bind(this.address, getMaxPendingConnections());
            Selector open2 = Selector.open();
            this.selector = open2;
            ServerSocketChannel serverSocketChannel = this.server;
            serverSocketChannel.register(open2, serverSocketChannel.validOps());
            startConnectionLostTimer();
            for (WebSocketWorker start : this.decoders) {
                start.start();
            }
            onStart();
            return true;
        } catch (IOException e) {
            handleFatal((WebSocket) null, e);
            return false;
        }
    }

    private boolean doEnsureSingleThread() {
        synchronized (this) {
            if (this.selectorthread == null) {
                this.selectorthread = Thread.currentThread();
                if (this.isclosed.get()) {
                    return false;
                }
                return true;
            }
            throw new IllegalStateException(getClass().getName() + " can only be started once.");
        }
    }

    private void doServerShutdown() {
        stopConnectionLostTimer();
        List<WebSocketWorker> list = this.decoders;
        if (list != null) {
            for (WebSocketWorker interrupt : list) {
                interrupt.interrupt();
            }
        }
        Selector selector2 = this.selector;
        if (selector2 != null) {
            try {
                selector2.close();
            } catch (IOException e) {
                this.log.error("IOException during selector.close", (Throwable) e);
                onError((WebSocket) null, e);
            }
        }
        ServerSocketChannel serverSocketChannel = this.server;
        if (serverSocketChannel != null) {
            try {
                serverSocketChannel.close();
            } catch (IOException e2) {
                this.log.error("IOException during server.close", (Throwable) e2);
                onError((WebSocket) null, e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void allocateBuffers(WebSocket webSocket) throws InterruptedException {
        if (this.queuesize.get() < (this.decoders.size() * 2) + 1) {
            this.queuesize.incrementAndGet();
            this.buffers.put(createBuffer());
        }
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(16384);
    }

    /* access modifiers changed from: protected */
    public void queue(WebSocketImpl webSocketImpl) throws InterruptedException {
        if (webSocketImpl.getWorkerThread() == null) {
            List<WebSocketWorker> list = this.decoders;
            webSocketImpl.setWorkerThread(list.get(this.queueinvokes % list.size()));
            this.queueinvokes++;
        }
        webSocketImpl.getWorkerThread().put(webSocketImpl);
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return this.buffers.take();
    }

    /* access modifiers changed from: private */
    public void pushBuffer(ByteBuffer byteBuffer) throws InterruptedException {
        if (this.buffers.size() <= this.queuesize.intValue()) {
            this.buffers.put(byteBuffer);
        }
    }

    private void handleIOException(SelectionKey selectionKey, WebSocket webSocket, IOException iOException) {
        SelectableChannel channel;
        if (selectionKey != null) {
            selectionKey.cancel();
        }
        if (webSocket != null) {
            webSocket.closeConnection(1006, iOException.getMessage());
        } else if (selectionKey != null && (channel = selectionKey.channel()) != null && channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException unused) {
            }
            this.log.trace("Connection closed because of exception", (Throwable) iOException);
        }
    }

    /* access modifiers changed from: private */
    public void handleFatal(WebSocket webSocket, Exception exc) {
        this.log.error("Shutdown due to fatal error", (Throwable) exc);
        onError(webSocket, exc);
        List<WebSocketWorker> list = this.decoders;
        if (list != null) {
            for (WebSocketWorker interrupt : list) {
                interrupt.interrupt();
            }
        }
        Thread thread = this.selectorthread;
        if (thread != null) {
            thread.interrupt();
        }
        try {
            stop();
        } catch (IOException e) {
            this.log.error("Error during shutdown", (Throwable) e);
            onError((WebSocket) null, e);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            this.log.error("Interrupt during stop", (Throwable) exc);
            onError((WebSocket) null, e2);
        }
    }

    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(webSocket, str);
    }

    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(webSocket, byteBuffer);
    }

    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        if (addConnection(webSocket)) {
            onOpen(webSocket, (ClientHandshake) handshakedata);
        }
    }

    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.selector.wakeup();
        try {
            if (removeConnection(webSocket)) {
                onClose(webSocket, i, str, z);
            }
        } finally {
            try {
                releaseBuffers(webSocket);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean removeConnection(WebSocket webSocket) {
        boolean z;
        synchronized (this.connections) {
            if (this.connections.contains(webSocket)) {
                z = this.connections.remove(webSocket);
            } else {
                this.log.trace("Removing connection which is not in the connections collection! Possible no handshake recieved! {}", (Object) webSocket);
                z = false;
            }
        }
        if (this.isclosed.get() && this.connections.isEmpty()) {
            this.selectorthread.interrupt();
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean addConnection(WebSocket webSocket) {
        boolean add;
        if (!this.isclosed.get()) {
            synchronized (this.connections) {
                add = this.connections.add(webSocket);
            }
            return add;
        }
        webSocket.close(1001);
        return true;
    }

    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(webSocket, exc);
    }

    public final void onWriteDemand(WebSocket webSocket) {
        WebSocketImpl webSocketImpl = (WebSocketImpl) webSocket;
        try {
            webSocketImpl.getSelectionKey().interestOps(5);
        } catch (CancelledKeyException unused) {
            webSocketImpl.outQueue.clear();
        }
        this.selector.wakeup();
    }

    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(webSocket, i, str);
    }

    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(webSocket, i, str, z);
    }

    public final void setWebSocketFactory(WebSocketServerFactory webSocketServerFactory) {
        WebSocketServerFactory webSocketServerFactory2 = this.wsf;
        if (webSocketServerFactory2 != null) {
            webSocketServerFactory2.close();
        }
        this.wsf = webSocketServerFactory;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    private Socket getSocket(WebSocket webSocket) {
        return ((SocketChannel) ((WebSocketImpl) webSocket).getSelectionKey().channel()).socket();
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getRemoteSocketAddress();
    }

    public void broadcast(String str) {
        broadcast(str, this.connections);
    }

    public void broadcast(byte[] bArr) {
        broadcast(bArr, this.connections);
    }

    public void broadcast(ByteBuffer byteBuffer) {
        broadcast(byteBuffer, this.connections);
    }

    public void broadcast(byte[] bArr, Collection<WebSocket> collection) {
        if (bArr == null || collection == null) {
            throw new IllegalArgumentException();
        }
        broadcast(ByteBuffer.wrap(bArr), collection);
    }

    public void broadcast(ByteBuffer byteBuffer, Collection<WebSocket> collection) {
        if (byteBuffer == null || collection == null) {
            throw new IllegalArgumentException();
        }
        doBroadcast(byteBuffer, collection);
    }

    public void broadcast(String str, Collection<WebSocket> collection) {
        if (str == null || collection == null) {
            throw new IllegalArgumentException();
        }
        doBroadcast(str, collection);
    }

    private void doBroadcast(Object obj, Collection<WebSocket> collection) {
        ArrayList<WebSocket> arrayList;
        ByteBuffer byteBuffer = null;
        String str = obj instanceof String ? (String) obj : null;
        if (obj instanceof ByteBuffer) {
            byteBuffer = (ByteBuffer) obj;
        }
        if (str != null || byteBuffer != null) {
            HashMap hashMap = new HashMap();
            synchronized (collection) {
                arrayList = new ArrayList<>(collection);
            }
            for (WebSocket webSocket : arrayList) {
                if (webSocket != null) {
                    Draft draft = webSocket.getDraft();
                    fillFrames(draft, hashMap, str, byteBuffer);
                    try {
                        webSocket.sendFrame((Collection<Framedata>) (Collection) hashMap.get(draft));
                    } catch (WebsocketNotConnectedException unused) {
                    }
                }
            }
        }
    }

    private void fillFrames(Draft draft, Map<Draft, List<Framedata>> map, String str, ByteBuffer byteBuffer) {
        if (!map.containsKey(draft)) {
            List<Framedata> list = null;
            if (str != null) {
                list = draft.createFrames(str, false);
            }
            if (byteBuffer != null) {
                list = draft.createFrames(byteBuffer, false);
            }
            if (list != null) {
                map.put(draft, list);
            }
        }
    }

    public class WebSocketWorker extends Thread {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue();

        static {
            Class<WebSocketServer> cls = WebSocketServer.class;
        }

        public WebSocketWorker() {
            setName("WebSocketWorker-" + getId());
            setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(WebSocketServer.this) {
                public void uncaughtException(Thread thread, Throwable th) {
                    WebSocketServer.this.log.error("Uncaught exception in thread {}: {}", (Object) thread.getName(), (Object) th);
                }
            });
        }

        public void put(WebSocketImpl webSocketImpl) throws InterruptedException {
            this.iqueue.put(webSocketImpl);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
            r0 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:10:? A[ExcHandler: InterruptedException (unused java.lang.InterruptedException), SYNTHETIC, Splitter:B:1:0x0001] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r4 = this;
            L_0x0000:
                r0 = 0
                java.util.concurrent.BlockingQueue<org.java_websocket.WebSocketImpl> r1 = r4.iqueue     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0017 }
                java.lang.Object r1 = r1.take()     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0017 }
                org.java_websocket.WebSocketImpl r1 = (org.java_websocket.WebSocketImpl) r1     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0017 }
                java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r0 = r1.inQueue     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0015 }
                java.lang.Object r0 = r0.poll()     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0015 }
                java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0015 }
                r4.doDecode(r1, r0)     // Catch:{ InterruptedException -> 0x0021, RuntimeException -> 0x0015 }
                goto L_0x0000
            L_0x0015:
                r0 = move-exception
                goto L_0x001b
            L_0x0017:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
            L_0x001b:
                org.java_websocket.server.WebSocketServer r2 = org.java_websocket.server.WebSocketServer.this
                r2.handleFatal(r1, r0)
                goto L_0x0028
            L_0x0021:
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer.WebSocketWorker.run():void");
        }

        private void doDecode(WebSocketImpl webSocketImpl, ByteBuffer byteBuffer) throws InterruptedException {
            try {
                webSocketImpl.decode(byteBuffer);
            } catch (Exception e) {
                WebSocketServer.this.log.error("Error while reading from remote connection", (Throwable) e);
            } catch (Throwable th) {
                WebSocketServer.this.pushBuffer(byteBuffer);
                throw th;
            }
            WebSocketServer.this.pushBuffer(byteBuffer);
        }
    }
}
