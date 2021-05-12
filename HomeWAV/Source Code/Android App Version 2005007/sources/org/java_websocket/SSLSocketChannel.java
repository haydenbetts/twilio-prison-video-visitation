package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.util.ByteBufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLSocketChannel implements WrappedByteChannel, ByteChannel, ISSLChannel {
    private final SSLEngine engine;
    private ExecutorService executor;
    private final Logger log = LoggerFactory.getLogger((Class<?>) SSLSocketChannel.class);
    private ByteBuffer myAppData;
    private ByteBuffer myNetData;
    private ByteBuffer peerAppData;
    private ByteBuffer peerNetData;
    private final SocketChannel socketChannel;

    public boolean isNeedWrite() {
        return false;
    }

    public void writeMore() throws IOException {
    }

    public SSLSocketChannel(SocketChannel socketChannel2, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        if (socketChannel2 == null || sSLEngine == null || this.executor == executorService) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = socketChannel2;
        this.engine = sSLEngine;
        this.executor = executorService;
        this.myNetData = ByteBuffer.allocate(sSLEngine.getSession().getPacketBufferSize());
        this.peerNetData = ByteBuffer.allocate(sSLEngine.getSession().getPacketBufferSize());
        sSLEngine.beginHandshake();
        if (!doHandshake()) {
            try {
                socketChannel2.close();
            } catch (IOException e) {
                this.log.error("Exception during the closing of the channel", (Throwable) e);
            }
        } else if (selectionKey != null) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
        }
    }

    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.peerAppData.hasRemaining()) {
            this.peerAppData.flip();
            return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
        }
        this.peerNetData.compact();
        int read = this.socketChannel.read(this.peerNetData);
        if (read <= 0) {
            if (!this.peerNetData.hasRemaining()) {
                if (read < 0) {
                    handleEndOfStream();
                }
                ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                return read;
            }
        }
        this.peerNetData.flip();
        if (this.peerNetData.hasRemaining()) {
            this.peerAppData.compact();
            try {
                SSLEngineResult unwrap = this.engine.unwrap(this.peerNetData, this.peerAppData);
                int i = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[unwrap.getStatus().ordinal()];
                if (i == 1) {
                    this.peerAppData.flip();
                    return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                } else if (i == 2) {
                    this.peerAppData.flip();
                    return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                } else if (i == 3) {
                    this.peerAppData = enlargeApplicationBuffer(this.peerAppData);
                    return read(byteBuffer);
                } else if (i == 4) {
                    closeConnection();
                    byteBuffer.clear();
                    return -1;
                } else {
                    throw new IllegalStateException("Invalid SSL status: " + unwrap.getStatus());
                }
            } catch (SSLException e) {
                this.log.error("SSLExcpetion during unwrap", (Throwable) e);
                throw e;
            }
        }
        ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
        return read;
    }

    public synchronized int write(ByteBuffer byteBuffer) throws IOException {
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            this.myNetData.clear();
            SSLEngineResult wrap = this.engine.wrap(byteBuffer, this.myNetData);
            int i2 = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()];
            if (i2 == 1) {
                this.myNetData.flip();
                while (this.myNetData.hasRemaining()) {
                    i += this.socketChannel.write(this.myNetData);
                }
            } else if (i2 == 2) {
                throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
            } else if (i2 == 3) {
                this.myNetData = enlargePacketBuffer(this.myNetData);
            } else if (i2 == 4) {
                closeConnection();
                return 0;
            } else {
                throw new IllegalStateException("Invalid SSL status: " + wrap.getStatus());
            }
        }
        return i;
    }

    private boolean doHandshake() throws IOException {
        SSLEngineResult.HandshakeStatus handshakeStatus;
        int applicationBufferSize = this.engine.getSession().getApplicationBufferSize();
        this.myAppData = ByteBuffer.allocate(applicationBufferSize);
        this.peerAppData = ByteBuffer.allocate(applicationBufferSize);
        this.myNetData.clear();
        this.peerNetData.clear();
        SSLEngineResult.HandshakeStatus handshakeStatus2 = this.engine.getHandshakeStatus();
        boolean z = false;
        while (!z) {
            int i = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus2.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.myNetData.clear();
                        try {
                            SSLEngineResult wrap = this.engine.wrap(this.myAppData, this.myNetData);
                            handshakeStatus = wrap.getHandshakeStatus();
                            int i2 = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()];
                            if (i2 == 1) {
                                this.myNetData.flip();
                                while (this.myNetData.hasRemaining()) {
                                    this.socketChannel.write(this.myNetData);
                                }
                            } else if (i2 == 2) {
                                throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
                            } else if (i2 == 3) {
                                this.myNetData = enlargePacketBuffer(this.myNetData);
                            } else if (i2 == 4) {
                                try {
                                    this.myNetData.flip();
                                    while (this.myNetData.hasRemaining()) {
                                        this.socketChannel.write(this.myNetData);
                                    }
                                    this.peerNetData.clear();
                                } catch (Exception unused) {
                                    handshakeStatus2 = this.engine.getHandshakeStatus();
                                }
                            } else {
                                throw new IllegalStateException("Invalid SSL status: " + wrap.getStatus());
                            }
                        } catch (SSLException unused2) {
                            this.engine.closeOutbound();
                            handshakeStatus2 = this.engine.getHandshakeStatus();
                        }
                    } else if (i == 4) {
                        while (true) {
                            Runnable delegatedTask = this.engine.getDelegatedTask();
                            if (delegatedTask == null) {
                                break;
                            }
                            this.executor.execute(delegatedTask);
                        }
                        handshakeStatus2 = this.engine.getHandshakeStatus();
                    } else if (i != 5) {
                        throw new IllegalStateException("Invalid SSL status: " + handshakeStatus2);
                    }
                } else if (this.socketChannel.read(this.peerNetData) >= 0) {
                    this.peerNetData.flip();
                    try {
                        SSLEngineResult unwrap = this.engine.unwrap(this.peerNetData, this.peerAppData);
                        this.peerNetData.compact();
                        handshakeStatus = unwrap.getHandshakeStatus();
                        int i3 = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[unwrap.getStatus().ordinal()];
                        if (i3 != 1) {
                            if (i3 == 2) {
                                this.peerNetData = handleBufferUnderflow(this.peerNetData);
                            } else if (i3 == 3) {
                                this.peerAppData = enlargeApplicationBuffer(this.peerAppData);
                            } else if (i3 != 4) {
                                throw new IllegalStateException("Invalid SSL status: " + unwrap.getStatus());
                            } else if (this.engine.isOutboundDone()) {
                                return false;
                            } else {
                                this.engine.closeOutbound();
                                handshakeStatus2 = this.engine.getHandshakeStatus();
                            }
                        }
                    } catch (SSLException unused3) {
                        this.engine.closeOutbound();
                        handshakeStatus2 = this.engine.getHandshakeStatus();
                    }
                } else if (this.engine.isInboundDone() && this.engine.isOutboundDone()) {
                    return false;
                } else {
                    try {
                        this.engine.closeInbound();
                    } catch (SSLException unused4) {
                    }
                    this.engine.closeOutbound();
                    handshakeStatus2 = this.engine.getHandshakeStatus();
                }
                handshakeStatus2 = handshakeStatus;
            } else {
                z = !this.peerNetData.hasRemaining();
                if (z) {
                    return true;
                }
                this.socketChannel.write(this.peerNetData);
            }
        }
        return true;
    }

    /* renamed from: org.java_websocket.SSLSocketChannel$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0059 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0063 */
        static {
            /*
                javax.net.ssl.SSLEngineResult$HandshakeStatus[] r0 = javax.net.ssl.SSLEngineResult.HandshakeStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = r0
                r1 = 1
                javax.net.ssl.SSLEngineResult$HandshakeStatus r2 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r5 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r4 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r5 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r6 = 5
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                javax.net.ssl.SSLEngineResult$Status[] r4 = javax.net.ssl.SSLEngineResult.Status.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status = r4
                javax.net.ssl.SSLEngineResult$Status r5 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ NoSuchFieldError -> 0x004f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r1 = $SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch:{ NoSuchFieldError -> 0x0059 }
                javax.net.ssl.SSLEngineResult$Status r4 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch:{ NoSuchFieldError -> 0x0063 }
                javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch:{ NoSuchFieldError -> 0x006d }
                javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SSLSocketChannel.AnonymousClass1.<clinit>():void");
        }
    }

    private ByteBuffer enlargePacketBuffer(ByteBuffer byteBuffer) {
        return enlargeBuffer(byteBuffer, this.engine.getSession().getPacketBufferSize());
    }

    private ByteBuffer enlargeApplicationBuffer(ByteBuffer byteBuffer) {
        return enlargeBuffer(byteBuffer, this.engine.getSession().getApplicationBufferSize());
    }

    private ByteBuffer enlargeBuffer(ByteBuffer byteBuffer, int i) {
        if (i > byteBuffer.capacity()) {
            return ByteBuffer.allocate(i);
        }
        return ByteBuffer.allocate(byteBuffer.capacity() * 2);
    }

    private ByteBuffer handleBufferUnderflow(ByteBuffer byteBuffer) {
        if (this.engine.getSession().getPacketBufferSize() < byteBuffer.limit()) {
            return byteBuffer;
        }
        ByteBuffer enlargePacketBuffer = enlargePacketBuffer(byteBuffer);
        byteBuffer.flip();
        enlargePacketBuffer.put(byteBuffer);
        return enlargePacketBuffer;
    }

    private void closeConnection() throws IOException {
        this.engine.closeOutbound();
        try {
            doHandshake();
        } catch (IOException unused) {
        }
        this.socketChannel.close();
    }

    private void handleEndOfStream() throws IOException {
        try {
            this.engine.closeInbound();
        } catch (Exception unused) {
            this.log.error("This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream.");
        }
        closeConnection();
    }

    public boolean isNeedRead() {
        return this.peerNetData.hasRemaining() || this.peerAppData.hasRemaining();
    }

    public int readMore(ByteBuffer byteBuffer) throws IOException {
        return read(byteBuffer);
    }

    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    public void close() throws IOException {
        closeConnection();
    }

    public SSLEngine getSSLEngine() {
        return this.engine;
    }
}
