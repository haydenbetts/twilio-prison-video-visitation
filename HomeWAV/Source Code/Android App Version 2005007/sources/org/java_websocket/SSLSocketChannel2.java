package org.java_websocket;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.java_websocket.interfaces.ISSLChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLSocketChannel2 implements ByteChannel, WrappedByteChannel, ISSLChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static ByteBuffer emptybuffer = ByteBuffer.allocate(0);
    protected int bufferallocations = 0;
    protected ExecutorService exec;
    protected ByteBuffer inCrypt;
    protected ByteBuffer inData;
    private final Logger log = LoggerFactory.getLogger((Class<?>) SSLSocketChannel2.class);
    protected ByteBuffer outCrypt;
    protected SSLEngineResult readEngineResult;
    private byte[] saveCryptData = null;
    protected SelectionKey selectionKey;
    protected SocketChannel socketChannel;
    protected SSLEngine sslEngine;
    protected List<Future<?>> tasks;
    protected SSLEngineResult writeEngineResult;

    public SSLSocketChannel2(SocketChannel socketChannel2, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey2) throws IOException {
        if (socketChannel2 == null || sSLEngine == null || executorService == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = socketChannel2;
        this.sslEngine = sSLEngine;
        this.exec = executorService;
        SSLEngineResult sSLEngineResult = new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, sSLEngine.getHandshakeStatus(), 0, 0);
        this.writeEngineResult = sSLEngineResult;
        this.readEngineResult = sSLEngineResult;
        this.tasks = new ArrayList(3);
        if (selectionKey2 != null) {
            selectionKey2.interestOps(selectionKey2.interestOps() | 4);
            this.selectionKey = selectionKey2;
        }
        createBuffers(sSLEngine.getSession());
        this.socketChannel.write(wrap(emptybuffer));
        processHandshake();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0006 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void consumeFutureUninterruptible(java.util.concurrent.Future<?> r2) {
        /*
            r1 = this;
        L_0x0000:
            r2.get()     // Catch:{ InterruptedException -> 0x0006 }
            return
        L_0x0004:
            r2 = move-exception
            goto L_0x000e
        L_0x0006:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ ExecutionException -> 0x0004 }
            r0.interrupt()     // Catch:{ ExecutionException -> 0x0004 }
            goto L_0x0000
        L_0x000e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SSLSocketChannel2.consumeFutureUninterruptible(java.util.concurrent.Future):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void processHandshake() throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            javax.net.ssl.SSLEngine r0 = r3.sslEngine     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x000d
            monitor-exit(r3)
            return
        L_0x000d:
            java.util.List<java.util.concurrent.Future<?>> r0 = r3.tasks     // Catch:{ all -> 0x00ca }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00ca }
            if (r0 != 0) goto L_0x003c
            java.util.List<java.util.concurrent.Future<?>> r0 = r3.tasks     // Catch:{ all -> 0x00ca }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00ca }
        L_0x001b:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x00ca }
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x00ca }
            java.util.concurrent.Future r1 = (java.util.concurrent.Future) r1     // Catch:{ all -> 0x00ca }
            boolean r2 = r1.isDone()     // Catch:{ all -> 0x00ca }
            if (r2 == 0) goto L_0x0031
            r0.remove()     // Catch:{ all -> 0x00ca }
            goto L_0x001b
        L_0x0031:
            boolean r0 = r3.isBlocking()     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x003a
            r3.consumeFutureUninterruptible(r1)     // Catch:{ all -> 0x00ca }
        L_0x003a:
            monitor-exit(r3)
            return
        L_0x003c:
            javax.net.ssl.SSLEngine r0 = r3.sslEngine     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x0090
            boolean r0 = r3.isBlocking()     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x0056
            javax.net.ssl.SSLEngineResult r0 = r3.readEngineResult     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$Status r0 = r0.getStatus()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x006b
        L_0x0056:
            java.nio.ByteBuffer r0 = r3.inCrypt     // Catch:{ all -> 0x00ca }
            r0.compact()     // Catch:{ all -> 0x00ca }
            java.nio.channels.SocketChannel r0 = r3.socketChannel     // Catch:{ all -> 0x00ca }
            java.nio.ByteBuffer r1 = r3.inCrypt     // Catch:{ all -> 0x00ca }
            int r0 = r0.read(r1)     // Catch:{ all -> 0x00ca }
            r1 = -1
            if (r0 == r1) goto L_0x0088
            java.nio.ByteBuffer r0 = r3.inCrypt     // Catch:{ all -> 0x00ca }
            r0.flip()     // Catch:{ all -> 0x00ca }
        L_0x006b:
            java.nio.ByteBuffer r0 = r3.inData     // Catch:{ all -> 0x00ca }
            r0.compact()     // Catch:{ all -> 0x00ca }
            r3.unwrap()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult r0 = r3.readEngineResult     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x0090
            javax.net.ssl.SSLEngine r0 = r3.sslEngine     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLSession r0 = r0.getSession()     // Catch:{ all -> 0x00ca }
            r3.createBuffers(r0)     // Catch:{ all -> 0x00ca }
            monitor-exit(r3)
            return
        L_0x0088:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = "connection closed unexpectedly by peer"
            r0.<init>(r1)     // Catch:{ all -> 0x00ca }
            throw r0     // Catch:{ all -> 0x00ca }
        L_0x0090:
            r3.consumeDelegatedTasks()     // Catch:{ all -> 0x00ca }
            java.util.List<java.util.concurrent.Future<?>> r0 = r3.tasks     // Catch:{ all -> 0x00ca }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00ca }
            if (r0 != 0) goto L_0x00a5
            javax.net.ssl.SSLEngine r0 = r3.sslEngine     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x00c5
        L_0x00a5:
            java.nio.channels.SocketChannel r0 = r3.socketChannel     // Catch:{ all -> 0x00ca }
            java.nio.ByteBuffer r1 = emptybuffer     // Catch:{ all -> 0x00ca }
            java.nio.ByteBuffer r1 = r3.wrap(r1)     // Catch:{ all -> 0x00ca }
            r0.write(r1)     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult r0 = r3.writeEngineResult     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x00c5
            javax.net.ssl.SSLEngine r0 = r3.sslEngine     // Catch:{ all -> 0x00ca }
            javax.net.ssl.SSLSession r0 = r0.getSession()     // Catch:{ all -> 0x00ca }
            r3.createBuffers(r0)     // Catch:{ all -> 0x00ca }
            monitor-exit(r3)
            return
        L_0x00c5:
            r0 = 1
            r3.bufferallocations = r0     // Catch:{ all -> 0x00ca }
            monitor-exit(r3)
            return
        L_0x00ca:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SSLSocketChannel2.processHandshake():void");
    }

    private synchronized ByteBuffer wrap(ByteBuffer byteBuffer) throws SSLException {
        this.outCrypt.compact();
        this.writeEngineResult = this.sslEngine.wrap(byteBuffer, this.outCrypt);
        this.outCrypt.flip();
        return this.outCrypt;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:8|9|(1:15)(1:21)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018 A[LOOP:0: B:8:0x0018->B:21:0x0018, LOOP_START, SYNTHETIC, Splitter:B:8:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.nio.ByteBuffer unwrap() throws javax.net.ssl.SSLException {
        /*
            r4 = this;
            monitor-enter(r4)
            javax.net.ssl.SSLEngineResult r0 = r4.readEngineResult     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$Status r0 = r0.getStatus()     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x004d }
            if (r0 != r1) goto L_0x0018
            javax.net.ssl.SSLEngine r0 = r4.sslEngine     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x004d }
            if (r0 != r1) goto L_0x0018
            r4.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            java.nio.ByteBuffer r0 = r4.inData     // Catch:{ all -> 0x004d }
            int r0 = r0.remaining()     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngine r1 = r4.sslEngine     // Catch:{ all -> 0x004d }
            java.nio.ByteBuffer r2 = r4.inCrypt     // Catch:{ all -> 0x004d }
            java.nio.ByteBuffer r3 = r4.inData     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult r1 = r1.unwrap(r2, r3)     // Catch:{ all -> 0x004d }
            r4.readEngineResult = r1     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$Status r1 = r1.getStatus()     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$Status r2 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ all -> 0x004d }
            if (r1 != r2) goto L_0x0044
            java.nio.ByteBuffer r1 = r4.inData     // Catch:{ all -> 0x004d }
            int r1 = r1.remaining()     // Catch:{ all -> 0x004d }
            if (r0 != r1) goto L_0x0018
            javax.net.ssl.SSLEngine r0 = r4.sslEngine     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()     // Catch:{ all -> 0x004d }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x004d }
            if (r0 == r1) goto L_0x0018
        L_0x0044:
            java.nio.ByteBuffer r0 = r4.inData     // Catch:{ all -> 0x004d }
            r0.flip()     // Catch:{ all -> 0x004d }
            java.nio.ByteBuffer r0 = r4.inData     // Catch:{ all -> 0x004d }
            monitor-exit(r4)
            return r0
        L_0x004d:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SSLSocketChannel2.unwrap():java.nio.ByteBuffer");
    }

    /* access modifiers changed from: protected */
    public void consumeDelegatedTasks() {
        while (true) {
            Runnable delegatedTask = this.sslEngine.getDelegatedTask();
            if (delegatedTask != null) {
                this.tasks.add(this.exec.submit(delegatedTask));
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void createBuffers(SSLSession sSLSession) {
        saveCryptedData();
        int packetBufferSize = sSLSession.getPacketBufferSize();
        int max = Math.max(sSLSession.getApplicationBufferSize(), packetBufferSize);
        ByteBuffer byteBuffer = this.inData;
        if (byteBuffer == null) {
            this.inData = ByteBuffer.allocate(max);
            this.outCrypt = ByteBuffer.allocate(packetBufferSize);
            this.inCrypt = ByteBuffer.allocate(packetBufferSize);
        } else {
            if (byteBuffer.capacity() != max) {
                this.inData = ByteBuffer.allocate(max);
            }
            if (this.outCrypt.capacity() != packetBufferSize) {
                this.outCrypt = ByteBuffer.allocate(packetBufferSize);
            }
            if (this.inCrypt.capacity() != packetBufferSize) {
                this.inCrypt = ByteBuffer.allocate(packetBufferSize);
            }
        }
        if (this.inData.remaining() != 0 && this.log.isTraceEnabled()) {
            this.log.trace(new String(this.inData.array(), this.inData.position(), this.inData.remaining()));
        }
        this.inData.rewind();
        this.inData.flip();
        if (this.inCrypt.remaining() != 0 && this.log.isTraceEnabled()) {
            this.log.trace(new String(this.inCrypt.array(), this.inCrypt.position(), this.inCrypt.remaining()));
        }
        this.inCrypt.rewind();
        this.inCrypt.flip();
        this.outCrypt.rewind();
        this.outCrypt.flip();
        this.bufferallocations++;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!isHandShakeComplete()) {
            processHandshake();
            return 0;
        }
        int write = this.socketChannel.write(wrap(byteBuffer));
        if (this.writeEngineResult.getStatus() != SSLEngineResult.Status.CLOSED) {
            return write;
        }
        throw new EOFException("Connection is closed");
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(java.nio.ByteBuffer r3) throws java.io.IOException {
        /*
            r2 = this;
            r2.tryRestoreCryptedData()
        L_0x0003:
            boolean r0 = r3.hasRemaining()
            r1 = 0
            if (r0 != 0) goto L_0x000b
            return r1
        L_0x000b:
            boolean r0 = r2.isHandShakeComplete()
            if (r0 != 0) goto L_0x002b
            boolean r0 = r2.isBlocking()
            if (r0 == 0) goto L_0x0021
        L_0x0017:
            boolean r0 = r2.isHandShakeComplete()
            if (r0 != 0) goto L_0x002b
            r2.processHandshake()
            goto L_0x0017
        L_0x0021:
            r2.processHandshake()
            boolean r0 = r2.isHandShakeComplete()
            if (r0 != 0) goto L_0x002b
            return r1
        L_0x002b:
            int r0 = r2.readRemaining(r3)
            if (r0 == 0) goto L_0x0032
            return r0
        L_0x0032:
            java.nio.ByteBuffer r0 = r2.inData
            r0.clear()
            java.nio.ByteBuffer r0 = r2.inCrypt
            boolean r0 = r0.hasRemaining()
            if (r0 != 0) goto L_0x0045
            java.nio.ByteBuffer r0 = r2.inCrypt
            r0.clear()
            goto L_0x004a
        L_0x0045:
            java.nio.ByteBuffer r0 = r2.inCrypt
            r0.compact()
        L_0x004a:
            boolean r0 = r2.isBlocking()
            if (r0 != 0) goto L_0x005a
            javax.net.ssl.SSLEngineResult r0 = r2.readEngineResult
            javax.net.ssl.SSLEngineResult$Status r0 = r0.getStatus()
            javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW
            if (r0 != r1) goto L_0x0066
        L_0x005a:
            java.nio.channels.SocketChannel r0 = r2.socketChannel
            java.nio.ByteBuffer r1 = r2.inCrypt
            int r0 = r0.read(r1)
            r1 = -1
            if (r0 != r1) goto L_0x0066
            return r1
        L_0x0066:
            java.nio.ByteBuffer r0 = r2.inCrypt
            r0.flip()
            r2.unwrap()
            java.nio.ByteBuffer r0 = r2.inData
            int r0 = r2.transfereTo(r0, r3)
            if (r0 != 0) goto L_0x007d
            boolean r1 = r2.isBlocking()
            if (r1 == 0) goto L_0x007d
            goto L_0x0003
        L_0x007d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SSLSocketChannel2.read(java.nio.ByteBuffer):int");
    }

    private int readRemaining(ByteBuffer byteBuffer) throws SSLException {
        if (this.inData.hasRemaining()) {
            return transfereTo(this.inData, byteBuffer);
        }
        if (!this.inData.hasRemaining()) {
            this.inData.clear();
        }
        tryRestoreCryptedData();
        if (!this.inCrypt.hasRemaining()) {
            return 0;
        }
        unwrap();
        int transfereTo = transfereTo(this.inData, byteBuffer);
        if (this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED) {
            return -1;
        }
        if (transfereTo > 0) {
            return transfereTo;
        }
        return 0;
    }

    public boolean isConnected() {
        return this.socketChannel.isConnected();
    }

    public void close() throws IOException {
        this.sslEngine.closeOutbound();
        this.sslEngine.getSession().invalidate();
        if (this.socketChannel.isOpen()) {
            this.socketChannel.write(wrap(emptybuffer));
        }
        this.socketChannel.close();
    }

    private boolean isHandShakeComplete() {
        SSLEngineResult.HandshakeStatus handshakeStatus = this.sslEngine.getHandshakeStatus();
        return handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED || handshakeStatus == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    public SelectableChannel configureBlocking(boolean z) throws IOException {
        return this.socketChannel.configureBlocking(z);
    }

    public boolean connect(SocketAddress socketAddress) throws IOException {
        return this.socketChannel.connect(socketAddress);
    }

    public boolean finishConnect() throws IOException {
        return this.socketChannel.finishConnect();
    }

    public Socket socket() {
        return this.socketChannel.socket();
    }

    public boolean isInboundDone() {
        return this.sslEngine.isInboundDone();
    }

    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    public boolean isNeedWrite() {
        return this.outCrypt.hasRemaining() || !isHandShakeComplete();
    }

    public void writeMore() throws IOException {
        write(this.outCrypt);
    }

    public boolean isNeedRead() {
        return (this.saveCryptData == null && !this.inData.hasRemaining() && (!this.inCrypt.hasRemaining() || this.readEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW || this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED)) ? false : true;
    }

    public int readMore(ByteBuffer byteBuffer) throws SSLException {
        return readRemaining(byteBuffer);
    }

    private int transfereTo(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining > remaining2) {
            int min = Math.min(remaining, remaining2);
            for (int i = 0; i < min; i++) {
                byteBuffer2.put(byteBuffer.get());
            }
            return min;
        }
        byteBuffer2.put(byteBuffer);
        return remaining;
    }

    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    public SSLEngine getSSLEngine() {
        return this.sslEngine;
    }

    private void saveCryptedData() {
        ByteBuffer byteBuffer = this.inCrypt;
        if (byteBuffer != null && byteBuffer.remaining() > 0) {
            byte[] bArr = new byte[this.inCrypt.remaining()];
            this.saveCryptData = bArr;
            this.inCrypt.get(bArr);
        }
    }

    private void tryRestoreCryptedData() {
        if (this.saveCryptData != null) {
            this.inCrypt.clear();
            this.inCrypt.put(this.saveCryptData);
            this.inCrypt.flip();
            this.saveCryptData = null;
        }
    }
}
