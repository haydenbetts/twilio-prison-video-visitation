package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.ws.WebSocketProtocol;
import org.java_websocket.WebSocketImpl;

public class WebSocket extends WebSocketBase implements IWebSocket {
    private static ILog __log = Log.getLogger(WebSocket.class);
    private boolean __isOpen;
    private boolean __secure;
    private boolean _closing;
    private Exception _exception;
    private ByteCollection _fragmentedMessageBytes;
    private WebSocketFrameType _fragmentedMessageType;
    private byte[] _handshakeEndOfResponse;
    private WebSocketOpenArgs _openArgs;
    private boolean _opening;
    private String _protocol;
    private ByteCollection _receiveBuffer;
    private URI _requestUri;
    private String _secWebSocketAccept;
    private String _secWebSocketKey;
    private ArrayList<WebSocketSendState> _sendQueue;
    private boolean _sending;
    private Random _sendingRandomizer;
    private TcpSocket _socket;
    private Object _stateLock;

    public static boolean getExists() {
        return true;
    }

    public void close() {
        try {
            close(new WebSocketCloseArgs());
        } catch (Exception e) {
            __log.error("Could not close WebSocket.", e);
        }
    }

    public void close(WebSocketCloseArgs webSocketCloseArgs) {
        webSocketCloseArgs.setStatusCode(WebSocketStatusCode.Normal);
        webSocketCloseArgs.setReason((String) null);
        close(webSocketCloseArgs, true);
    }

    private void close(WebSocketCloseArgs webSocketCloseArgs, boolean z) {
        if (z) {
            synchronized (this._stateLock) {
                if (getIsOpen() && !this._opening && !this._closing) {
                    this._sendQueue.clear();
                    ByteCollection byteCollection = new ByteCollection();
                    byteCollection.addRange(Binary.toBytes16((short) webSocketCloseArgs.getStatusCode().getAssignedValue(), false));
                    if (webSocketCloseArgs.getReason() != null) {
                        byteCollection.addRange(Utf8.encode(webSocketCloseArgs.getReason()));
                    }
                    WebSocketSendArgs webSocketSendArgs = new WebSocketSendArgs();
                    webSocketSendArgs.setBinaryMessage(byteCollection.toArray());
                    send(webSocketSendArgs, WebSocketFrameType.Close);
                    this._closing = true;
                    close(webSocketCloseArgs, false);
                }
            }
            return;
        }
        synchronized (this._stateLock) {
            if (getIsOpen()) {
                this._socket.close();
                this._closing = false;
                this.__isOpen = false;
                super.raiseCloseComplete(webSocketCloseArgs, webSocketCloseArgs.getStatusCode(), webSocketCloseArgs.getReason());
            }
        }
    }

    /* access modifiers changed from: private */
    public void closeComplete(WebSocketCloseCompleteArgs webSocketCloseCompleteArgs) {
        super.raiseStreamFailure(this._openArgs, webSocketCloseCompleteArgs.getStatusCode(), this._exception);
    }

    private void closeWithException(Exception exc, WebSocketStatusCode webSocketStatusCode, boolean z) {
        this._exception = exc;
        WebSocketCloseArgs webSocketCloseArgs = new WebSocketCloseArgs();
        webSocketCloseArgs.setStatusCode(webSocketStatusCode);
        webSocketCloseArgs.setReason(exc.getMessage());
        webSocketCloseArgs.setOnComplete(new IActionDelegate1<WebSocketCloseCompleteArgs>() {
            public String getId() {
                return "fm.liveswitch.WebSocket.closeComplete";
            }

            public void invoke(WebSocketCloseCompleteArgs webSocketCloseCompleteArgs) {
                WebSocket.this.closeComplete(webSocketCloseCompleteArgs);
            }
        });
        close(webSocketCloseArgs, z);
    }

    /* access modifiers changed from: private */
    public void connectFailure(Exception exc, boolean z) {
        synchronized (this._stateLock) {
            this._opening = false;
            super.raiseOpenFailure(this._openArgs, WebSocketStatusCode.NoStatus, exc);
        }
    }

    /* access modifiers changed from: private */
    public void connectSuccess() {
        handshakeSend();
    }

    /* access modifiers changed from: private */
    public void dnsResolveCallback(String[] strArr, Object obj) {
        WebSocketOpenArgs webSocketOpenArgs = (WebSocketOpenArgs) obj;
        if (strArr == null) {
            strArr = new String[]{"127.0.0.1"};
        }
        String str = strArr[0];
        __log.info(StringExtensions.format("WebSocket address resolved to {0}.", (Object) str));
        this._socket = new TcpSocket(false, Global.equals(LocalNetwork.getAddressType(str), AddressType.IPv6), getSecure());
        this._receiveBuffer = new ByteCollection();
        this._sendQueue = new ArrayList<>();
        this._openArgs = webSocketOpenArgs;
        this._socket.connectAsync(UriExtensions.getDnsSafeHost(this._requestUri), str, getServerPort(), 0, new IActionDelegate0() {
            public String getId() {
                return "fm.liveswitch.WebSocket.connectSuccess";
            }

            public void invoke() {
                WebSocket.this.connectSuccess();
            }
        }, new IActionDelegate2<Exception, Boolean>() {
            public String getId() {
                return "fm.liveswitch.WebSocket.connectFailure";
            }

            public void invoke(Exception exc, Boolean bool) {
                WebSocket.this.connectFailure(exc, bool.booleanValue());
            }
        });
    }

    public int getBufferedAmount() {
        int i;
        synchronized (this._stateLock) {
            i = 0;
            Iterator<WebSocketSendState> it = this._sendQueue.iterator();
            while (it.hasNext()) {
                i += ArrayExtensions.getLength(it.next().getRequestBytes());
            }
        }
        return i;
    }

    private WebSocketFrameType getFrameType(byte b) {
        if (b == 0) {
            return WebSocketFrameType.Continuation;
        }
        if (b == 1) {
            return WebSocketFrameType.Text;
        }
        if (b == 2) {
            return WebSocketFrameType.Binary;
        }
        if (b == 8) {
            return WebSocketFrameType.Close;
        }
        if (b == 9) {
            return WebSocketFrameType.Ping;
        }
        if (b == 10) {
            return WebSocketFrameType.Pong;
        }
        return WebSocketFrameType.Unknown;
    }

    public boolean getIsOpen() {
        return this.__isOpen;
    }

    public String getProtocol() {
        return this._protocol;
    }

    public boolean getSecure() {
        return this.__secure;
    }

    private int getServerPort() {
        int port = this._requestUri.getPort();
        if (port > 0) {
            return port;
        }
        if (Global.equals(this._requestUri.getScheme(), "wss")) {
            return WebSocketImpl.DEFAULT_WSS_PORT;
        }
        return 80;
    }

    private void handshakeReceive() {
        this._socket.setOnReceiveSuccess(new IActionDelegate1<DataBuffer>() {
            public String getId() {
                return "fm.liveswitch.WebSocket.handshakeReceiveSuccess";
            }

            public void invoke(DataBuffer dataBuffer) {
                WebSocket.this.handshakeReceiveSuccess(dataBuffer);
            }
        });
        this._socket.setOnReceiveFailure(new IActionDelegate2<Exception, Boolean>() {
            public String getId() {
                return "fm.liveswitch.WebSocket.handshakeReceiveFailure";
            }

            public void invoke(Exception exc, Boolean bool) {
                WebSocket.this.handshakeReceiveFailure(exc, bool.booleanValue());
            }
        });
        this._socket.receiveAsync(this._openArgs.getHandshakeTimeout());
    }

    /* access modifiers changed from: private */
    public void handshakeReceiveFailure(Exception exc, boolean z) {
        try {
            synchronized (this._stateLock) {
                this._opening = false;
                super.raiseOpenFailure(this._openArgs, WebSocketStatusCode.NoStatus, new Exception(StringExtensions.format("Could not receive handshake response. {0}", (Object) exc.getMessage()), exc));
            }
        } catch (Exception e) {
            Unhandled.logException(e, "WebSocket -> HandshakeReceive -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void handshakeReceiveSuccess(DataBuffer dataBuffer) {
        try {
            this._receiveBuffer.addRange(dataBuffer.toArray());
            int indexOf = indexOf(this._handshakeEndOfResponse, this._receiveBuffer.toArray());
            if (indexOf > -1) {
                byte[] range = this._receiveBuffer.getRange(0, ArrayExtensions.getLength(this._handshakeEndOfResponse) + indexOf);
                this._receiveBuffer.removeRange(0, indexOf + ArrayExtensions.getLength(this._handshakeEndOfResponse));
                String decode = Utf8.decode(range, 0, ArrayExtensions.getLength(range));
                String[] split = Splitter.split(StringExtensions.trimEnd(decode, new char[]{13, 10}), "\r\n");
                HashMap hashMap = new HashMap();
                String str = split[0];
                if (ArrayExtensions.getLength((Object[]) split) > 1) {
                    for (int i = 1; i < ArrayExtensions.getLength((Object[]) split); i++) {
                        String[] split2 = StringExtensions.split(split[i], new char[]{':'});
                        if (ArrayExtensions.getLength((Object[]) split2) == 2) {
                            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), split2[0].trim(), split2[1].trim());
                        }
                    }
                }
                if (this._openArgs.getOnResponseReceived() != null) {
                    WebSocketMockResponse webSocketMockResponse = new WebSocketMockResponse();
                    webSocketMockResponse.setResponseUri(this._requestUri);
                    String[] split3 = StringExtensions.split(str, new char[]{' '});
                    if (ArrayExtensions.getLength((Object[]) split3) > 1) {
                        IntegerHolder integerHolder = new IntegerHolder(0);
                        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(split3[1], integerHolder);
                        int value = integerHolder.getValue();
                        if (tryParseIntegerValue) {
                            webSocketMockResponse.setStatusCode(value);
                        }
                    }
                    for (String str2 : HashMapExtensions.getAllKeys(hashMap)) {
                        if (Global.equals(StringExtensions.toLower(str2), "content-type")) {
                            webSocketMockResponse.setContentType((String) HashMapExtensions.getItem(hashMap).get(str2));
                        } else if (Global.equals(StringExtensions.toLower(str2), "content-length")) {
                            LongHolder longHolder = new LongHolder(0);
                            boolean tryParseLongValue = ParseAssistant.tryParseLongValue((String) HashMapExtensions.getItem(hashMap).get(str2), longHolder);
                            long value2 = longHolder.getValue();
                            if (tryParseLongValue) {
                                webSocketMockResponse.setContentLength(value2);
                            }
                        } else {
                            HashMapExtensions.set(HashMapExtensions.getItem(webSocketMockResponse.getHeaders()), str2, HashMapExtensions.getItem(hashMap).get(str2));
                        }
                    }
                    HttpResponseReceivedArgs httpResponseReceivedArgs = new HttpResponseReceivedArgs();
                    httpResponseReceivedArgs.setResponse(webSocketMockResponse);
                    httpResponseReceivedArgs.setSender(this._openArgs.getSender());
                    this._openArgs.getOnResponseReceived().invoke(httpResponseReceivedArgs);
                }
                if (!handshakeResponseIsValid(str, hashMap)) {
                    synchronized (this._stateLock) {
                        this._opening = false;
                        super.raiseOpenFailure(this._openArgs, WebSocketStatusCode.NoStatus, new Exception(StringExtensions.format("Invalid handshake response ({0}).", (Object) decode)));
                    }
                    return;
                }
                synchronized (this._stateLock) {
                    this.__isOpen = true;
                    this._opening = false;
                    super.raiseOpenSuccess(this._openArgs);
                    if (ArrayListExtensions.getCount(this._sendQueue) > 0) {
                        WebSocketSendState webSocketSendState = (WebSocketSendState) ArrayListExtensions.getItem(this._sendQueue).get(0);
                        sendNow(webSocketSendState.getSendArgs(), webSocketSendState.getRequestBytes());
                    }
                }
                this._socket.setOnReceiveSuccess(new IActionDelegate1<DataBuffer>() {
                    public String getId() {
                        return "fm.liveswitch.WebSocket.listenSuccess";
                    }

                    public void invoke(DataBuffer dataBuffer) {
                        WebSocket.this.listenSuccess(dataBuffer);
                    }
                });
                this._socket.setOnReceiveFailure(new IActionDelegate2<Exception, Boolean>() {
                    public String getId() {
                        return "fm.liveswitch.WebSocket.listenFailure";
                    }

                    public void invoke(Exception exc, Boolean bool) {
                        WebSocket.this.listenFailure(exc, bool.booleanValue());
                    }
                });
                listen();
                return;
            }
            handshakeReceive();
        } catch (Exception e) {
            synchronized (this._stateLock) {
                this._opening = false;
                super.raiseOpenFailure(this._openArgs, WebSocketStatusCode.NoStatus, e);
            }
        }
    }

    private boolean handshakeResponseIsValid(String str, HashMap<String, String> hashMap) {
        if (!Global.equals(str, "HTTP/1.1 101 Switching Protocols")) {
            return false;
        }
        String str2 = HashMapExtensions.getItem(hashMap).get("Upgrade");
        String str3 = HashMapExtensions.getItem(hashMap).get("Connection");
        String str4 = HashMapExtensions.getItem(hashMap).get("Sec-WebSocket-Accept");
        String str5 = HashMapExtensions.getItem(hashMap).get("Sec-WebSocket-Protocol");
        if (str2 == null || str3 == null || str4 == null || !Global.equals(StringExtensions.toLower(str2), "websocket") || !Global.equals(StringExtensions.toLower(str3), "upgrade") || !Global.equals(StringExtensions.toLower(str4), StringExtensions.toLower(this._secWebSocketAccept))) {
            return false;
        }
        if (str5 == null || getProtocol() == null || Global.equals(StringExtensions.toLower(str5), StringExtensions.toLower(getProtocol()))) {
            return true;
        }
        return false;
    }

    private void handshakeSend() {
        String concat = StringExtensions.concat(UriExtensions.getAbsolutePath(this._requestUri), UriExtensions.getQuery(this._requestUri));
        String dnsSafeHost = UriExtensions.getDnsSafeHost(this._requestUri);
        if (getServerPort() != 80) {
            dnsSafeHost = StringExtensions.concat(dnsSafeHost, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, IntegerExtensions.toString(Integer.valueOf(getServerPort())));
        }
        WebSocketMockRequest webSocketMockRequest = new WebSocketMockRequest();
        try {
            webSocketMockRequest.setRequestUri(new URI(this._requestUri.toString().replace("ws://", "http://").replace("wss://", "https://")));
            webSocketMockRequest.setMethod(DefaultHttpClient.METHOD_GET);
            if (this._openArgs.getHeaders() != null) {
                for (String next : HashMapExtensions.getAllKeys(this._openArgs.getHeaders())) {
                    HashMapExtensions.set(HashMapExtensions.getItem(webSocketMockRequest.getHeaders()), next, HashMapExtensions.getItem(this._openArgs.getHeaders()).get(next));
                }
            }
            if (!StringExtensions.isNullOrEmpty(getProtocol())) {
                HashMapExtensions.set(HashMapExtensions.getItem(webSocketMockRequest.getHeaders()), "Sec-WebSocket-Protocol", getProtocol());
            }
            if (this._openArgs.getOnRequestCreated() != null) {
                HttpRequestCreatedArgs httpRequestCreatedArgs = new HttpRequestCreatedArgs();
                httpRequestCreatedArgs.setRequest(webSocketMockRequest);
                httpRequestCreatedArgs.setSender(this._openArgs.getSender());
                this._openArgs.getOnRequestCreated().invoke(httpRequestCreatedArgs);
            }
            StringBuilder sb = new StringBuilder();
            for (String next2 : HashMapExtensions.getAllKeys(webSocketMockRequest.getHeaders())) {
                StringBuilderExtensions.append(sb, StringExtensions.format("{0}: {1}\r\n", next2, HashMapExtensions.getItem(webSocketMockRequest.getHeaders()).get(next2)));
            }
            this._socket.sendAsync(DataBuffer.wrap(Utf8.encode(StringExtensions.concat((Object[]) new String[]{"GET ", concat, " HTTP/1.1\r\nHost: ", dnsSafeHost, "\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Key: ", this._secWebSocketKey, "\r\nSec-WebSocket-Version: 13\r\n", sb.toString(), "\r\n"}))), this._openArgs.getHandshakeTimeout(), new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.WebSocket.handshakeSendSuccess";
                }

                public void invoke() {
                    WebSocket.this.handshakeSendSuccess();
                }
            }, new IActionDelegate2<Exception, Boolean>() {
                public String getId() {
                    return "fm.liveswitch.WebSocket.handshakeSendFailure";
                }

                public void invoke(Exception exc, Boolean bool) {
                    WebSocket.this.handshakeSendFailure(exc, bool.booleanValue());
                }
            });
        } catch (Exception e) {
            super.raiseOpenFailure(this._openArgs, WebSocketStatusCode.NoStatus, new Exception(StringExtensions.format("Could not parse request URI. {0}", (Object) e.getMessage()), e));
        }
    }

    /* access modifiers changed from: private */
    public void handshakeSendFailure(Exception exc, boolean z) {
        synchronized (this._stateLock) {
            this._opening = false;
            super.raiseOpenFailure(this._openArgs, WebSocketStatusCode.NoStatus, new Exception(StringExtensions.format("Could not send handshake request. {0}", (Object) exc.getMessage()), exc));
        }
    }

    /* access modifiers changed from: private */
    public void handshakeSendSuccess() {
        handshakeReceive();
    }

    private int indexOf(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int i2 = -1;
        for (int i3 = 0; i3 < ArrayExtensions.getLength(bArr2); i3++) {
            if (bArr2[i3] == bArr[i]) {
                if (i == 0) {
                    i2 = i3;
                }
                if (i == ArrayExtensions.getLength(bArr) - 1) {
                    return i2;
                }
                i++;
            } else {
                i = 0;
            }
        }
        return -1;
    }

    private void listen() {
        this._socket.receiveAsync(this._openArgs.getStreamTimeout());
    }

    /* access modifiers changed from: private */
    public void listenFailure(Exception exc, boolean z) {
        try {
            if (getIsOpen() && !this._opening && !this._closing) {
                closeWithException(exc, WebSocketStatusCode.GoingAway, true);
            }
        } catch (Exception e) {
            Unhandled.logException(e, "WebSocket -> Listen -> OnFailure");
        }
    }

    /* access modifiers changed from: private */
    public void listenSuccess(DataBuffer dataBuffer) {
        try {
            if (getIsOpen() && !this._opening && !this._closing) {
                this._receiveBuffer.addRange(dataBuffer.toArray());
                Holder holder = new Holder(WebSocketStatusCode.Normal);
                Holder holder2 = new Holder(null);
                BooleanHolder booleanHolder = new BooleanHolder(false);
                boolean processReceiveBuffer = processReceiveBuffer(holder, holder2, booleanHolder);
                WebSocketStatusCode webSocketStatusCode = (WebSocketStatusCode) holder.getValue();
                Exception exc = (Exception) holder2.getValue();
                boolean value = booleanHolder.getValue();
                if (processReceiveBuffer) {
                    listen();
                } else {
                    closeWithException(exc, webSocketStatusCode, value);
                }
            }
        } catch (Exception e) {
            Unhandled.logException(e, "WebSocket -> Listen -> OnSuccess");
        }
    }

    public void open(WebSocketOpenArgs webSocketOpenArgs) {
        synchronized (this._stateLock) {
            if (getIsOpen()) {
                super.raiseOpenFailure(webSocketOpenArgs, WebSocketStatusCode.NoStatus, new Exception("Already open."));
            } else if (this._opening) {
                super.raiseOpenFailure(webSocketOpenArgs, WebSocketStatusCode.NoStatus, new Exception("Already opening."));
            } else {
                this._opening = true;
                this.__secure = Global.equals(this._requestUri.getScheme(), "wss");
                Dns.resolve(UriExtensions.getDnsSafeHost(this._requestUri), new IActionDelegate2<String[], Object>() {
                    public String getId() {
                        return "fm.liveswitch.WebSocket.dnsResolveCallback";
                    }

                    public void invoke(String[] strArr, Object obj) {
                        WebSocket.this.dnsResolveCallback(strArr, obj);
                    }
                }, webSocketOpenArgs);
            }
        }
    }

    private boolean processReceiveBuffer(Holder<WebSocketStatusCode> holder, Holder<Exception> holder2, BooleanHolder booleanHolder) {
        boolean z;
        WebSocketFrameType frameType;
        byte[] range;
        long fromBytes64;
        Holder<WebSocketStatusCode> holder3 = holder;
        Holder<Exception> holder4 = holder2;
        holder3.setValue(WebSocketStatusCode.Normal);
        holder4.setValue(null);
        booleanHolder.setValue(true);
        do {
            int i = 2;
            if (this._receiveBuffer.getCount() < 2) {
                return true;
            }
            byte b = this._receiveBuffer.get(0);
            byte b2 = this._receiveBuffer.get(1);
            z = (b & ByteCompanionObject.MIN_VALUE) == 128;
            byte b3 = (byte) (b & 15);
            frameType = getFrameType(b3);
            if (Global.equals(frameType, WebSocketFrameType.Unknown)) {
                holder3.setValue(WebSocketStatusCode.InvalidType);
                holder4.setValue(new Exception(StringExtensions.format("Invalid frame type received (opcode {0}).", (Object) ByteExtensions.toString(Byte.valueOf(b3)))));
                return false;
            } else if ((b2 & ByteCompanionObject.MIN_VALUE) == 128) {
                holder3.setValue(WebSocketStatusCode.ProtocolError);
                holder4.setValue(new Exception("Masked frame received from server."));
                return false;
            } else {
                long j = (long) (b2 & ByteCompanionObject.MAX_VALUE);
                int i2 = j == 126 ? 2 : j == 127 ? 8 : 0;
                int i3 = i2 + 2;
                if (this._receiveBuffer.getCount() < i3) {
                    return true;
                }
                if (i2 > 0) {
                    byte[] range2 = this._receiveBuffer.getRange(2, i2);
                    if (i2 == 2) {
                        fromBytes64 = (long) Binary.fromBytes16(range2, 0, false);
                    } else {
                        if (i2 == 8) {
                            fromBytes64 = Binary.fromBytes64(range2, 0, false);
                        }
                        i = i3;
                    }
                    j = fromBytes64;
                    i = i3;
                }
                if (j > 2147483647L) {
                    holder3.setValue(WebSocketStatusCode.MessageTooLarge);
                    holder4.setValue(new Exception(StringExtensions.format("Maximum payload size of 2GB was exceeded (actual {0} bytes).", (Object) LongExtensions.toString(Long.valueOf(j)))));
                    return false;
                }
                int i4 = (int) j;
                int i5 = i + i4;
                if (this._receiveBuffer.getCount() < i5) {
                    return true;
                }
                range = this._receiveBuffer.getRange(i, i4);
                this._receiveBuffer.removeRange(0, i5);
            }
        } while (processReceiveFrame(range, z, frameType, holder, holder2, booleanHolder));
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:15|16|(2:18|19)|20|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0060 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0086 A[SYNTHETIC, Splitter:B:32:0x0086] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean processReceiveFrame(byte[] r5, boolean r6, fm.liveswitch.WebSocketFrameType r7, fm.liveswitch.Holder<fm.liveswitch.WebSocketStatusCode> r8, fm.liveswitch.Holder<java.lang.Exception> r9, fm.liveswitch.BooleanHolder r10) {
        /*
            r4 = this;
            fm.liveswitch.WebSocketStatusCode r0 = fm.liveswitch.WebSocketStatusCode.Normal
            r8.setValue(r0)
            r0 = 0
            r9.setValue(r0)
            r1 = 1
            r10.setValue(r1)
            if (r6 != 0) goto L_0x0027
            fm.liveswitch.WebSocketFrameType r6 = fm.liveswitch.WebSocketFrameType.Continuation
            boolean r6 = fm.liveswitch.Global.equals(r7, r6)
            if (r6 != 0) goto L_0x0021
            fm.liveswitch.ByteCollection r6 = new fm.liveswitch.ByteCollection
            r6.<init>(r5)
            r4._fragmentedMessageBytes = r6
            r4._fragmentedMessageType = r7
            goto L_0x0026
        L_0x0021:
            fm.liveswitch.ByteCollection r6 = r4._fragmentedMessageBytes
            r6.addRange((byte[]) r5)
        L_0x0026:
            return r1
        L_0x0027:
            fm.liveswitch.WebSocketFrameType r6 = fm.liveswitch.WebSocketFrameType.Continuation
            boolean r6 = fm.liveswitch.Global.equals(r7, r6)
            if (r6 == 0) goto L_0x003d
            fm.liveswitch.ByteCollection r6 = r4._fragmentedMessageBytes
            r6.addRange((byte[]) r5)
            fm.liveswitch.ByteCollection r5 = r4._fragmentedMessageBytes
            byte[] r5 = r5.toArray()
            fm.liveswitch.WebSocketFrameType r6 = r4._fragmentedMessageType
            goto L_0x003e
        L_0x003d:
            r6 = r7
        L_0x003e:
            fm.liveswitch.WebSocketFrameType r2 = fm.liveswitch.WebSocketFrameType.Close
            boolean r2 = fm.liveswitch.Global.equals(r7, r2)
            r3 = 0
            if (r2 == 0) goto L_0x00a7
            java.lang.Object r2 = r4._stateLock
            monitor-enter(r2)
            java.util.ArrayList<fm.liveswitch.WebSocketSendState> r6 = r4._sendQueue     // Catch:{ all -> 0x00a4 }
            r6.clear()     // Catch:{ all -> 0x00a4 }
            boolean r6 = r4._closing     // Catch:{ all -> 0x00a4 }
            if (r6 != 0) goto L_0x0060
            fm.liveswitch.WebSocketSendArgs r6 = new fm.liveswitch.WebSocketSendArgs     // Catch:{ Exception -> 0x0060 }
            r6.<init>()     // Catch:{ Exception -> 0x0060 }
            r6.setBinaryMessage(r5)     // Catch:{ Exception -> 0x0060 }
            fm.liveswitch.WebSocketFrameType r7 = fm.liveswitch.WebSocketFrameType.Close     // Catch:{ Exception -> 0x0060 }
            r4.send(r6, r7)     // Catch:{ Exception -> 0x0060 }
        L_0x0060:
            r4._closing = r1     // Catch:{ all -> 0x00a4 }
            monitor-exit(r2)     // Catch:{ all -> 0x00a4 }
            fm.liveswitch.WebSocketStatusCode r6 = fm.liveswitch.WebSocketStatusCode.NoStatus
            r8.setValue(r6)
            int r6 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            r7 = 2
            if (r6 < r7) goto L_0x0080
            int r6 = fm.liveswitch.Binary.fromBytes16(r5, r3, r3)
            fm.liveswitch.WebSocketStatusCode r6 = fm.liveswitch.WebSocketStatusCode.getByAssignedValue(r6)     // Catch:{ Exception -> 0x007b }
            r8.setValue(r6)     // Catch:{ Exception -> 0x007b }
            goto L_0x0080
        L_0x007b:
            fm.liveswitch.WebSocketStatusCode r6 = fm.liveswitch.WebSocketStatusCode.NoStatus
            r8.setValue(r6)
        L_0x0080:
            int r6 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)
            if (r6 <= r7) goto L_0x0092
            int r6 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)     // Catch:{ Exception -> 0x0090 }
            int r6 = r6 - r7
            java.lang.String r0 = fm.liveswitch.Utf8.decode(r5, r7, r6)     // Catch:{ Exception -> 0x0090 }
            goto L_0x0092
        L_0x0090:
            java.lang.String r0 = "Reason was given, but not in UTF-8 format."
        L_0x0092:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r6 = "Connection closed ({0})."
            java.lang.String r6 = fm.liveswitch.StringExtensions.format((java.lang.String) r6, (java.lang.Object) r0)
            r5.<init>(r6)
            r9.setValue(r5)
            r10.setValue(r3)
            return r3
        L_0x00a4:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00a4 }
            throw r5
        L_0x00a7:
            fm.liveswitch.WebSocketFrameType r1 = fm.liveswitch.WebSocketFrameType.Text
            boolean r1 = fm.liveswitch.Global.equals(r6, r1)
            if (r1 == 0) goto L_0x00cd
            int r6 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r5)     // Catch:{ Exception -> 0x00bd }
            java.lang.String r5 = fm.liveswitch.Utf8.decode(r5, r3, r6)     // Catch:{ Exception -> 0x00bd }
            fm.liveswitch.WebSocketOpenArgs r6 = r4._openArgs
            super.raiseReceive(r6, r5, r0)
            goto L_0x00f0
        L_0x00bd:
            fm.liveswitch.WebSocketStatusCode r5 = fm.liveswitch.WebSocketStatusCode.InvalidData
            r8.setValue(r5)
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r6 = "Message of type text was not in UTF-8 format."
            r5.<init>(r6)
            r9.setValue(r5)
            return r3
        L_0x00cd:
            fm.liveswitch.WebSocketFrameType r1 = fm.liveswitch.WebSocketFrameType.Binary
            boolean r6 = fm.liveswitch.Global.equals(r6, r1)
            if (r6 == 0) goto L_0x00db
            fm.liveswitch.WebSocketOpenArgs r6 = r4._openArgs
            super.raiseReceive(r6, r0, r5)
            goto L_0x00f0
        L_0x00db:
            fm.liveswitch.WebSocketFrameType r6 = fm.liveswitch.WebSocketFrameType.Ping
            boolean r6 = fm.liveswitch.Global.equals(r7, r6)
            if (r6 == 0) goto L_0x00f0
            fm.liveswitch.WebSocketSendArgs r6 = new fm.liveswitch.WebSocketSendArgs     // Catch:{ Exception -> 0x00f0 }
            r6.<init>()     // Catch:{ Exception -> 0x00f0 }
            r6.setBinaryMessage(r5)     // Catch:{ Exception -> 0x00f0 }
            fm.liveswitch.WebSocketFrameType r5 = fm.liveswitch.WebSocketFrameType.Pong     // Catch:{ Exception -> 0x00f0 }
            r4.send(r6, r5)     // Catch:{ Exception -> 0x00f0 }
        L_0x00f0:
            boolean r5 = r4.processReceiveBuffer(r8, r9, r10)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.WebSocket.processReceiveFrame(byte[], boolean, fm.liveswitch.WebSocketFrameType, fm.liveswitch.Holder, fm.liveswitch.Holder, fm.liveswitch.BooleanHolder):boolean");
    }

    public void send(WebSocketSendArgs webSocketSendArgs) {
        if (webSocketSendArgs.getTextMessage() == null && webSocketSendArgs.getBinaryMessage() == null) {
            throw new RuntimeException(new Exception("textMessage and binaryMessage cannot both be null."));
        } else if (webSocketSendArgs.getBinaryMessage() == null) {
            webSocketSendArgs.setBinaryMessage(Utf8.encode(webSocketSendArgs.getTextMessage()));
            send(webSocketSendArgs, WebSocketFrameType.Text);
        } else {
            send(webSocketSendArgs, WebSocketFrameType.Binary);
        }
    }

    private void send(WebSocketSendArgs webSocketSendArgs, WebSocketFrameType webSocketFrameType) {
        if (getIsOpen()) {
            ByteCollection byteCollection = new ByteCollection();
            if (webSocketFrameType == WebSocketFrameType.Continuation) {
                byteCollection.add((byte) ByteCompanionObject.MIN_VALUE);
            } else if (webSocketFrameType == WebSocketFrameType.Text) {
                byteCollection.add((byte) -127);
            } else if (webSocketFrameType == WebSocketFrameType.Binary) {
                byteCollection.add((byte) -126);
            } else if (webSocketFrameType == WebSocketFrameType.Close) {
                byteCollection.add((byte) -120);
            } else if (webSocketFrameType == WebSocketFrameType.Ping) {
                byteCollection.add((byte) -119);
            } else if (webSocketFrameType == WebSocketFrameType.Pong) {
                byteCollection.add((byte) -118);
            }
            if (webSocketSendArgs.getBinaryMessage() == null) {
                byteCollection.add((byte) ByteCompanionObject.MIN_VALUE);
            } else if (ArrayExtensions.getLength(webSocketSendArgs.getBinaryMessage()) < 126) {
                byteCollection.add((byte) (((byte) ArrayExtensions.getLength(webSocketSendArgs.getBinaryMessage())) | ByteCompanionObject.MIN_VALUE));
            } else if (ArrayExtensions.getLength(webSocketSendArgs.getBinaryMessage()) <= 65535) {
                byteCollection.add((byte) -2);
                byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(webSocketSendArgs.getBinaryMessage()), false));
            } else {
                byteCollection.add((byte) -1);
                byteCollection.addRange(Binary.toBytes64((long) ArrayExtensions.getLength(webSocketSendArgs.getBinaryMessage()), false));
            }
            byte[] bArr = new byte[4];
            RandomExtensions.nextBytes(this._sendingRandomizer, bArr);
            if (Platform.getInstance().getIsLittleEndian()) {
                BitAssistant.reverse(bArr);
            }
            byteCollection.addRange(bArr);
            if (webSocketSendArgs.getBinaryMessage() != null) {
                for (int i = 0; i < ArrayExtensions.getLength(webSocketSendArgs.getBinaryMessage()); i++) {
                    webSocketSendArgs.getBinaryMessage()[i] = (byte) (bArr[i % 4] ^ webSocketSendArgs.getBinaryMessage()[i]);
                }
                byteCollection.addRange(webSocketSendArgs.getBinaryMessage());
            }
            synchronized (this._stateLock) {
                if (getIsOpen() && !this._opening) {
                    if (!this._closing) {
                        if (this._sending) {
                            WebSocketSendState webSocketSendState = new WebSocketSendState();
                            webSocketSendState.setSendArgs(webSocketSendArgs);
                            webSocketSendState.setRequestBytes(byteCollection.toArray());
                            this._sendQueue.add(webSocketSendState);
                            return;
                        }
                        this._sending = true;
                        sendNow(webSocketSendArgs, byteCollection.toArray());
                        return;
                    }
                }
                WebSocketSendState webSocketSendState2 = new WebSocketSendState();
                webSocketSendState2.setSendArgs(webSocketSendArgs);
                webSocketSendState2.setRequestBytes(byteCollection.toArray());
                this._sendQueue.add(webSocketSendState2);
            }
        }
    }

    private void sendNow(WebSocketSendArgs webSocketSendArgs, byte[] bArr) {
        this._socket.sendAsync(DataBuffer.wrap(bArr), webSocketSendArgs.getTimeout(), new IActionDelegate0() {
            public String getId() {
                return "fm.liveswitch.WebSocket.sendNowSuccess";
            }

            public void invoke() {
                WebSocket.this.sendNowSuccess();
            }
        }, new IActionDelegate2<Exception, Boolean>() {
            public String getId() {
                return "fm.liveswitch.WebSocket.sendNowFailure";
            }

            public void invoke(Exception exc, Boolean bool) {
                WebSocket.this.sendNowFailure(exc, bool.booleanValue());
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendNowFailure(Exception exc, boolean z) {
        closeWithException(exc, WebSocketStatusCode.GoingAway, true);
    }

    /* access modifiers changed from: private */
    public void sendNowSuccess() {
        synchronized (this._stateLock) {
            if (ArrayListExtensions.getCount(this._sendQueue) > 0) {
                WebSocketSendState webSocketSendState = (WebSocketSendState) ArrayListExtensions.getItem(this._sendQueue).get(0);
                ArrayListExtensions.removeAt(this._sendQueue, 0);
                sendNow(webSocketSendState.getSendArgs(), webSocketSendState.getRequestBytes());
            } else {
                this._sending = false;
            }
        }
    }

    private void setProtocol(String str) {
        this._protocol = str;
    }

    public WebSocket(String str) {
        this(str, (String) null);
    }

    public WebSocket(String str, String str2) {
        this._opening = false;
        this._closing = false;
        this._sending = false;
        this._stateLock = new Object();
        this._handshakeEndOfResponse = Utf8.encode("\r\n\r\n");
        this._sendingRandomizer = new Random();
        try {
            this._requestUri = new URI(str);
        } catch (Exception unused) {
        }
        setProtocol(str2);
        this._secWebSocketKey = Base64.encode(Guid.newGuid().toByteArray());
        this._secWebSocketAccept = Base64.encodeBuffer(HashContextBase.compute(HashType.Sha1, StringExtensions.concat(this._secWebSocketKey, WebSocketProtocol.ACCEPT_MAGIC)));
    }
}
