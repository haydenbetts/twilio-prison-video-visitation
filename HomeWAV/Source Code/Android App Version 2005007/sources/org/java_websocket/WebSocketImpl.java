package org.java_websocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.ssl.SSLSession;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExceededException;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.util.Charsetfunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketImpl implements WebSocket {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_PORT = 80;
    public static final int DEFAULT_WSS_PORT = 443;
    public static final int RCVBUF = 16384;
    private Object attachment;
    private ByteChannel channel;
    private Integer closecode;
    private Boolean closedremotely;
    private String closemessage;
    private Draft draft;
    private boolean flushandclosestate;
    private ClientHandshake handshakerequest;
    public final BlockingQueue<ByteBuffer> inQueue;
    private SelectionKey key;
    private List<Draft> knownDrafts;
    private long lastPong;
    private final Logger log;
    public final BlockingQueue<ByteBuffer> outQueue;
    private volatile ReadyState readyState;
    private String resourceDescriptor;
    private Role role;
    private final Object synchronizeWriteObject;
    private ByteBuffer tmpHandshakeBytes;
    private WebSocketServer.WebSocketWorker workerThread;
    private final WebSocketListener wsl;

    public WebSocketImpl(WebSocketListener webSocketListener, List<Draft> list) {
        this(webSocketListener, (Draft) null);
        this.role = Role.SERVER;
        if (list == null || list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            this.knownDrafts = arrayList;
            arrayList.add(new Draft_6455());
            return;
        }
        this.knownDrafts = list;
    }

    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft2) {
        this.log = LoggerFactory.getLogger((Class<?>) WebSocketImpl.class);
        this.flushandclosestate = false;
        this.readyState = ReadyState.NOT_YET_CONNECTED;
        this.draft = null;
        this.tmpHandshakeBytes = ByteBuffer.allocate(0);
        this.handshakerequest = null;
        this.closemessage = null;
        this.closecode = null;
        this.closedremotely = null;
        this.resourceDescriptor = null;
        this.lastPong = System.nanoTime();
        this.synchronizeWriteObject = new Object();
        if (webSocketListener == null || (draft2 == null && this.role == Role.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.outQueue = new LinkedBlockingQueue();
        this.inQueue = new LinkedBlockingQueue();
        this.wsl = webSocketListener;
        this.role = Role.CLIENT;
        if (draft2 != null) {
            this.draft = draft2.copyInstance();
        }
    }

    public void decode(ByteBuffer byteBuffer) {
        this.log.trace("process({}): ({})", (Object) Integer.valueOf(byteBuffer.remaining()), (Object) byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
        if (this.readyState != ReadyState.NOT_YET_CONNECTED) {
            if (this.readyState == ReadyState.OPEN) {
                decodeFrames(byteBuffer);
            }
        } else if (decodeHandshake(byteBuffer) && !isClosing() && !isClosed()) {
            if (byteBuffer.hasRemaining()) {
                decodeFrames(byteBuffer);
            } else if (this.tmpHandshakeBytes.hasRemaining()) {
                decodeFrames(this.tmpHandshakeBytes);
            }
        }
    }

    private boolean decodeHandshake(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        if (this.tmpHandshakeBytes.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.tmpHandshakeBytes.remaining() < byteBuffer.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(this.tmpHandshakeBytes.capacity() + byteBuffer.remaining());
                this.tmpHandshakeBytes.flip();
                allocate.put(this.tmpHandshakeBytes);
                this.tmpHandshakeBytes = allocate;
            }
            this.tmpHandshakeBytes.put(byteBuffer);
            this.tmpHandshakeBytes.flip();
            byteBuffer2 = this.tmpHandshakeBytes;
        }
        byteBuffer2.mark();
        try {
            if (this.role == Role.SERVER) {
                Draft draft2 = this.draft;
                if (draft2 == null) {
                    for (Draft copyInstance : this.knownDrafts) {
                        Draft copyInstance2 = copyInstance.copyInstance();
                        try {
                            copyInstance2.setParseMode(this.role);
                            byteBuffer2.reset();
                            Handshakedata translateHandshake = copyInstance2.translateHandshake(byteBuffer2);
                            if (!(translateHandshake instanceof ClientHandshake)) {
                                this.log.trace("Closing due to wrong handshake");
                                closeConnectionDueToWrongHandshake(new InvalidDataException(1002, "wrong http function"));
                                return false;
                            }
                            ClientHandshake clientHandshake = (ClientHandshake) translateHandshake;
                            if (copyInstance2.acceptHandshakeAsServer(clientHandshake) == HandshakeState.MATCHED) {
                                this.resourceDescriptor = clientHandshake.getResourceDescriptor();
                                try {
                                    write(copyInstance2.createHandshake(copyInstance2.postProcessHandshakeResponseAsServer(clientHandshake, this.wsl.onWebsocketHandshakeReceivedAsServer(this, copyInstance2, clientHandshake))));
                                    this.draft = copyInstance2;
                                    open(clientHandshake);
                                    return true;
                                } catch (InvalidDataException e) {
                                    this.log.trace("Closing due to wrong handshake. Possible handshake rejection", (Throwable) e);
                                    closeConnectionDueToWrongHandshake(e);
                                    return false;
                                } catch (RuntimeException e2) {
                                    this.log.error("Closing due to internal server error", (Throwable) e2);
                                    this.wsl.onWebsocketError(this, e2);
                                    closeConnectionDueToInternalServerError(e2);
                                    return false;
                                }
                            } else {
                                continue;
                            }
                        } catch (InvalidHandshakeException unused) {
                        }
                    }
                    if (this.draft == null) {
                        this.log.trace("Closing due to protocol error: no draft matches");
                        closeConnectionDueToWrongHandshake(new InvalidDataException(1002, "no draft matches"));
                    }
                    return false;
                }
                Handshakedata translateHandshake2 = draft2.translateHandshake(byteBuffer2);
                if (!(translateHandshake2 instanceof ClientHandshake)) {
                    this.log.trace("Closing due to protocol error: wrong http function");
                    flushAndClose(1002, "wrong http function", false);
                    return false;
                }
                ClientHandshake clientHandshake2 = (ClientHandshake) translateHandshake2;
                if (this.draft.acceptHandshakeAsServer(clientHandshake2) == HandshakeState.MATCHED) {
                    open(clientHandshake2);
                    return true;
                }
                this.log.trace("Closing due to protocol error: the handshake did finally not match");
                close(1002, "the handshake did finally not match");
                return false;
            }
            if (this.role == Role.CLIENT) {
                this.draft.setParseMode(this.role);
                Handshakedata translateHandshake3 = this.draft.translateHandshake(byteBuffer2);
                if (!(translateHandshake3 instanceof ServerHandshake)) {
                    this.log.trace("Closing due to protocol error: wrong http function");
                    flushAndClose(1002, "wrong http function", false);
                    return false;
                }
                ServerHandshake serverHandshake = (ServerHandshake) translateHandshake3;
                if (this.draft.acceptHandshakeAsClient(this.handshakerequest, serverHandshake) == HandshakeState.MATCHED) {
                    try {
                        this.wsl.onWebsocketHandshakeReceivedAsClient(this, this.handshakerequest, serverHandshake);
                        open(serverHandshake);
                        return true;
                    } catch (InvalidDataException e3) {
                        this.log.trace("Closing due to invalid data exception. Possible handshake rejection", (Throwable) e3);
                        flushAndClose(e3.getCloseCode(), e3.getMessage(), false);
                        return false;
                    } catch (RuntimeException e4) {
                        this.log.error("Closing since client was never connected", (Throwable) e4);
                        this.wsl.onWebsocketError(this, e4);
                        flushAndClose(-1, e4.getMessage(), false);
                        return false;
                    }
                } else {
                    this.log.trace("Closing due to protocol error: draft {} refuses handshake", (Object) this.draft);
                    close(1002, "draft " + this.draft + " refuses handshake");
                }
            }
            return false;
        } catch (InvalidHandshakeException e5) {
            try {
                this.log.trace("Closing due to invalid handshake", (Throwable) e5);
                close((InvalidDataException) e5);
            } catch (IncompleteHandshakeException e6) {
                if (this.tmpHandshakeBytes.capacity() == 0) {
                    byteBuffer2.reset();
                    int preferredSize = e6.getPreferredSize();
                    if (preferredSize == 0) {
                        preferredSize = byteBuffer2.capacity() + 16;
                    }
                    ByteBuffer allocate2 = ByteBuffer.allocate(preferredSize);
                    this.tmpHandshakeBytes = allocate2;
                    allocate2.put(byteBuffer);
                } else {
                    ByteBuffer byteBuffer3 = this.tmpHandshakeBytes;
                    byteBuffer3.position(byteBuffer3.limit());
                    ByteBuffer byteBuffer4 = this.tmpHandshakeBytes;
                    byteBuffer4.limit(byteBuffer4.capacity());
                }
            }
        }
    }

    private void decodeFrames(ByteBuffer byteBuffer) {
        try {
            for (Framedata next : this.draft.translateFrame(byteBuffer)) {
                this.log.trace("matched frame: {}", (Object) next);
                this.draft.processFrame(this, next);
            }
        } catch (LimitExceededException e) {
            if (e.getLimit() == Integer.MAX_VALUE) {
                this.log.error("Closing due to invalid size of frame", (Throwable) e);
                this.wsl.onWebsocketError(this, e);
            }
            close((InvalidDataException) e);
        } catch (InvalidDataException e2) {
            this.log.error("Closing due to invalid data in frame", (Throwable) e2);
            this.wsl.onWebsocketError(this, e2);
            close(e2);
        }
    }

    private void closeConnectionDueToWrongHandshake(InvalidDataException invalidDataException) {
        write(generateHttpResponseDueToError(404));
        flushAndClose(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    private void closeConnectionDueToInternalServerError(RuntimeException runtimeException) {
        write(generateHttpResponseDueToError(500));
        flushAndClose(-1, runtimeException.getMessage(), false);
    }

    private ByteBuffer generateHttpResponseDueToError(int i) {
        String str = i != 404 ? "500 Internal Server Error" : "404 WebSocket Upgrade Failure";
        return ByteBuffer.wrap(Charsetfunctions.asciiBytes("HTTP/1.1 " + str + "\r\nContent-Type: text/html\nServer: TooTallNate Java-WebSocket\r\nContent-Length: " + (str.length() + 48) + "\r\n\r\n<html><head></head><body><h1>" + str + "</h1></body></html>"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0085, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void close(int r6, java.lang.String r7, boolean r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            org.java_websocket.enums.ReadyState r0 = r5.readyState     // Catch:{ all -> 0x0086 }
            org.java_websocket.enums.ReadyState r1 = org.java_websocket.enums.ReadyState.CLOSING     // Catch:{ all -> 0x0086 }
            if (r0 == r1) goto L_0x0084
            org.java_websocket.enums.ReadyState r0 = r5.readyState     // Catch:{ all -> 0x0086 }
            org.java_websocket.enums.ReadyState r1 = org.java_websocket.enums.ReadyState.CLOSED     // Catch:{ all -> 0x0086 }
            if (r0 == r1) goto L_0x0084
            org.java_websocket.enums.ReadyState r0 = r5.readyState     // Catch:{ all -> 0x0086 }
            org.java_websocket.enums.ReadyState r1 = org.java_websocket.enums.ReadyState.OPEN     // Catch:{ all -> 0x0086 }
            r2 = 0
            if (r0 != r1) goto L_0x0067
            r0 = 1006(0x3ee, float:1.41E-42)
            if (r6 != r0) goto L_0x0021
            org.java_websocket.enums.ReadyState r8 = org.java_websocket.enums.ReadyState.CLOSING     // Catch:{ all -> 0x0086 }
            r5.readyState = r8     // Catch:{ all -> 0x0086 }
            r5.flushAndClose(r6, r7, r2)     // Catch:{ all -> 0x0086 }
            monitor-exit(r5)
            return
        L_0x0021:
            org.java_websocket.drafts.Draft r1 = r5.draft     // Catch:{ all -> 0x0086 }
            org.java_websocket.enums.CloseHandshakeType r1 = r1.getCloseHandshakeType()     // Catch:{ all -> 0x0086 }
            org.java_websocket.enums.CloseHandshakeType r3 = org.java_websocket.enums.CloseHandshakeType.NONE     // Catch:{ all -> 0x0086 }
            if (r1 == r3) goto L_0x0063
            if (r8 != 0) goto L_0x0039
            org.java_websocket.WebSocketListener r1 = r5.wsl     // Catch:{ RuntimeException -> 0x0033 }
            r1.onWebsocketCloseInitiated(r5, r6, r7)     // Catch:{ RuntimeException -> 0x0033 }
            goto L_0x0039
        L_0x0033:
            r1 = move-exception
            org.java_websocket.WebSocketListener r3 = r5.wsl     // Catch:{ InvalidDataException -> 0x0051 }
            r3.onWebsocketError(r5, r1)     // Catch:{ InvalidDataException -> 0x0051 }
        L_0x0039:
            boolean r1 = r5.isOpen()     // Catch:{ InvalidDataException -> 0x0051 }
            if (r1 == 0) goto L_0x0063
            org.java_websocket.framing.CloseFrame r1 = new org.java_websocket.framing.CloseFrame     // Catch:{ InvalidDataException -> 0x0051 }
            r1.<init>()     // Catch:{ InvalidDataException -> 0x0051 }
            r1.setReason(r7)     // Catch:{ InvalidDataException -> 0x0051 }
            r1.setCode(r6)     // Catch:{ InvalidDataException -> 0x0051 }
            r1.isValid()     // Catch:{ InvalidDataException -> 0x0051 }
            r5.sendFrame((org.java_websocket.framing.Framedata) r1)     // Catch:{ InvalidDataException -> 0x0051 }
            goto L_0x0063
        L_0x0051:
            r1 = move-exception
            org.slf4j.Logger r3 = r5.log     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "generated frame is invalid"
            r3.error((java.lang.String) r4, (java.lang.Throwable) r1)     // Catch:{ all -> 0x0086 }
            org.java_websocket.WebSocketListener r3 = r5.wsl     // Catch:{ all -> 0x0086 }
            r3.onWebsocketError(r5, r1)     // Catch:{ all -> 0x0086 }
            java.lang.String r1 = "generated frame is invalid"
            r5.flushAndClose(r0, r1, r2)     // Catch:{ all -> 0x0086 }
        L_0x0063:
            r5.flushAndClose(r6, r7, r8)     // Catch:{ all -> 0x0086 }
            goto L_0x007b
        L_0x0067:
            r0 = -3
            if (r6 != r0) goto L_0x006f
            r6 = 1
            r5.flushAndClose(r0, r7, r6)     // Catch:{ all -> 0x0086 }
            goto L_0x007b
        L_0x006f:
            r0 = 1002(0x3ea, float:1.404E-42)
            if (r6 != r0) goto L_0x0077
            r5.flushAndClose(r6, r7, r8)     // Catch:{ all -> 0x0086 }
            goto L_0x007b
        L_0x0077:
            r6 = -1
            r5.flushAndClose(r6, r7, r2)     // Catch:{ all -> 0x0086 }
        L_0x007b:
            org.java_websocket.enums.ReadyState r6 = org.java_websocket.enums.ReadyState.CLOSING     // Catch:{ all -> 0x0086 }
            r5.readyState = r6     // Catch:{ all -> 0x0086 }
            r6 = 0
            r5.tmpHandshakeBytes = r6     // Catch:{ all -> 0x0086 }
            monitor-exit(r5)
            return
        L_0x0084:
            monitor-exit(r5)
            return
        L_0x0086:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.WebSocketImpl.close(int, java.lang.String, boolean):void");
    }

    public void close(int i, String str) {
        close(i, str, false);
    }

    public synchronized void closeConnection(int i, String str, boolean z) {
        if (this.readyState != ReadyState.CLOSED) {
            if (this.readyState == ReadyState.OPEN && i == 1006) {
                this.readyState = ReadyState.CLOSING;
            }
            SelectionKey selectionKey = this.key;
            if (selectionKey != null) {
                selectionKey.cancel();
            }
            ByteChannel byteChannel = this.channel;
            if (byteChannel != null) {
                try {
                    byteChannel.close();
                } catch (IOException e) {
                    if (e.getMessage() == null || !e.getMessage().equals("Broken pipe")) {
                        this.log.error("Exception during channel.close()", (Throwable) e);
                        this.wsl.onWebsocketError(this, e);
                    } else {
                        this.log.trace("Caught IOException: Broken pipe during closeConnection()", (Throwable) e);
                    }
                }
            }
            try {
                this.wsl.onWebsocketClose(this, i, str, z);
            } catch (RuntimeException e2) {
                this.wsl.onWebsocketError(this, e2);
            }
            Draft draft2 = this.draft;
            if (draft2 != null) {
                draft2.reset();
            }
            this.handshakerequest = null;
            this.readyState = ReadyState.CLOSED;
        }
    }

    /* access modifiers changed from: protected */
    public void closeConnection(int i, boolean z) {
        closeConnection(i, "", z);
    }

    public void closeConnection() {
        if (this.closedremotely != null) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
            return;
        }
        throw new IllegalStateException("this method must be used in conjunction with flushAndClose");
    }

    public void closeConnection(int i, String str) {
        closeConnection(i, str, false);
    }

    public synchronized void flushAndClose(int i, String str, boolean z) {
        if (!this.flushandclosestate) {
            this.closecode = Integer.valueOf(i);
            this.closemessage = str;
            this.closedremotely = Boolean.valueOf(z);
            this.flushandclosestate = true;
            this.wsl.onWriteDemand(this);
            try {
                this.wsl.onWebsocketClosing(this, i, str, z);
            } catch (RuntimeException e) {
                this.log.error("Exception in onWebsocketClosing", (Throwable) e);
                this.wsl.onWebsocketError(this, e);
            }
            Draft draft2 = this.draft;
            if (draft2 != null) {
                draft2.reset();
            }
            this.handshakerequest = null;
        }
    }

    public void eot() {
        if (this.readyState == ReadyState.NOT_YET_CONNECTED) {
            closeConnection(-1, true);
        } else if (this.flushandclosestate) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
        } else if (this.draft.getCloseHandshakeType() == CloseHandshakeType.NONE) {
            closeConnection(1000, true);
        } else if (this.draft.getCloseHandshakeType() != CloseHandshakeType.ONEWAY) {
            closeConnection(1006, true);
        } else if (this.role == Role.SERVER) {
            closeConnection(1006, true);
        } else {
            closeConnection(1000, true);
        }
    }

    public void close(int i) {
        close(i, "", false);
    }

    public void close(InvalidDataException invalidDataException) {
        close(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    public void send(String str) {
        if (str != null) {
            send((Collection<Framedata>) this.draft.createFrames(str, this.role == Role.CLIENT));
            return;
        }
        throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }

    public void send(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            send((Collection<Framedata>) this.draft.createFrames(byteBuffer, this.role == Role.CLIENT));
            return;
        }
        throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }

    public void send(byte[] bArr) {
        send(ByteBuffer.wrap(bArr));
    }

    private void send(Collection<Framedata> collection) {
        if (!isOpen()) {
            throw new WebsocketNotConnectedException();
        } else if (collection != null) {
            ArrayList arrayList = new ArrayList();
            for (Framedata next : collection) {
                this.log.trace("send frame: {}", (Object) next);
                arrayList.add(this.draft.createBinaryFrame(next));
            }
            write((List<ByteBuffer>) arrayList);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        send((Collection<Framedata>) this.draft.continuousFrame(opcode, byteBuffer, z));
    }

    public void sendFrame(Collection<Framedata> collection) {
        send(collection);
    }

    public void sendFrame(Framedata framedata) {
        send((Collection<Framedata>) Collections.singletonList(framedata));
    }

    public void sendPing() throws NullPointerException {
        PingFrame onPreparePing = this.wsl.onPreparePing(this);
        Objects.requireNonNull(onPreparePing, "onPreparePing(WebSocket) returned null. PingFrame to sent can't be null.");
        sendFrame((Framedata) onPreparePing);
    }

    public boolean hasBufferedData() {
        return !this.outQueue.isEmpty();
    }

    public void startHandshake(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        this.handshakerequest = this.draft.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        this.resourceDescriptor = clientHandshakeBuilder.getResourceDescriptor();
        try {
            this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
            write(this.draft.createHandshake(this.handshakerequest));
        } catch (InvalidDataException unused) {
            throw new InvalidHandshakeException("Handshake data rejected by client.");
        } catch (RuntimeException e) {
            this.log.error("Exception in startHandshake", (Throwable) e);
            this.wsl.onWebsocketError(this, e);
            throw new InvalidHandshakeException("rejected because of " + e);
        }
    }

    private void write(ByteBuffer byteBuffer) {
        this.log.trace("write({}): {}", (Object) Integer.valueOf(byteBuffer.remaining()), (Object) byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
        this.outQueue.add(byteBuffer);
        this.wsl.onWriteDemand(this);
    }

    private void write(List<ByteBuffer> list) {
        synchronized (this.synchronizeWriteObject) {
            for (ByteBuffer write : list) {
                write(write);
            }
        }
    }

    private void open(Handshakedata handshakedata) {
        this.log.trace("open using draft: {}", (Object) this.draft);
        this.readyState = ReadyState.OPEN;
        try {
            this.wsl.onWebsocketOpen(this, handshakedata);
        } catch (RuntimeException e) {
            this.wsl.onWebsocketError(this, e);
        }
    }

    public boolean isOpen() {
        return this.readyState == ReadyState.OPEN;
    }

    public boolean isClosing() {
        return this.readyState == ReadyState.CLOSING;
    }

    public boolean isFlushAndClose() {
        return this.flushandclosestate;
    }

    public boolean isClosed() {
        return this.readyState == ReadyState.CLOSED;
    }

    public ReadyState getReadyState() {
        return this.readyState;
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.key = selectionKey;
    }

    public SelectionKey getSelectionKey() {
        return this.key;
    }

    public String toString() {
        return super.toString();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.wsl.getRemoteSocketAddress(this);
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.wsl.getLocalSocketAddress(this);
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void close() {
        close(1000);
    }

    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }

    /* access modifiers changed from: package-private */
    public long getLastPong() {
        return this.lastPong;
    }

    public void updateLastPong() {
        this.lastPong = System.nanoTime();
    }

    public WebSocketListener getWebSocketListener() {
        return this.wsl;
    }

    public <T> T getAttachment() {
        return this.attachment;
    }

    public boolean hasSSLSupport() {
        return this.channel instanceof ISSLChannel;
    }

    public SSLSession getSSLSession() {
        if (hasSSLSupport()) {
            return ((ISSLChannel) this.channel).getSSLEngine().getSession();
        }
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    public <T> void setAttachment(T t) {
        this.attachment = t;
    }

    public ByteChannel getChannel() {
        return this.channel;
    }

    public void setChannel(ByteChannel byteChannel) {
        this.channel = byteChannel;
    }

    public WebSocketServer.WebSocketWorker getWorkerThread() {
        return this.workerThread;
    }

    public void setWorkerThread(WebSocketServer.WebSocketWorker webSocketWorker) {
        this.workerThread = webSocketWorker;
    }
}
