package org.java_websocket.drafts;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.ws.WebSocketProtocol;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.enums.Role;
import org.java_websocket.exceptions.IncompleteException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExceededException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.extensions.DefaultExtension;
import org.java_websocket.extensions.IExtension;
import org.java_websocket.framing.BinaryFrame;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.framing.TextFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;
import org.java_websocket.util.Base64;
import org.java_websocket.util.Charsetfunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Draft_6455 extends Draft {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CONNECTION = "Connection";
    private static final String SEC_WEB_SOCKET_ACCEPT = "Sec-WebSocket-Accept";
    private static final String SEC_WEB_SOCKET_EXTENSIONS = "Sec-WebSocket-Extensions";
    private static final String SEC_WEB_SOCKET_KEY = "Sec-WebSocket-Key";
    private static final String SEC_WEB_SOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    private static final String UPGRADE = "Upgrade";
    private final List<ByteBuffer> byteBufferList;
    private Framedata currentContinuousFrame;
    private IExtension extension;
    private ByteBuffer incompleteframe;
    private List<IExtension> knownExtensions;
    private List<IProtocol> knownProtocols;
    private final Logger log;
    private int maxFrameSize;
    private IProtocol protocol;
    private final Random reuseableRandom;

    private byte getMaskByte(boolean z) {
        if (z) {
            return ByteCompanionObject.MIN_VALUE;
        }
        return 0;
    }

    private byte getRSVByte(int i) {
        if (i == 1) {
            return 64;
        }
        if (i == 2) {
            return 32;
        }
        if (i == 3) {
            return Tnaf.POW_2_WIDTH;
        }
        return 0;
    }

    public Draft_6455() {
        this((List<IExtension>) Collections.emptyList());
    }

    public Draft_6455(IExtension iExtension) {
        this((List<IExtension>) Collections.singletonList(iExtension));
    }

    public Draft_6455(List<IExtension> list) {
        this(list, (List<IProtocol>) Collections.singletonList(new Protocol("")));
    }

    public Draft_6455(List<IExtension> list, List<IProtocol> list2) {
        this(list, list2, Integer.MAX_VALUE);
    }

    public Draft_6455(List<IExtension> list, int i) {
        this(list, Collections.singletonList(new Protocol("")), i);
    }

    public Draft_6455(List<IExtension> list, List<IProtocol> list2, int i) {
        this.log = LoggerFactory.getLogger((Class<?>) Draft_6455.class);
        this.extension = new DefaultExtension();
        this.reuseableRandom = new Random();
        if (list == null || list2 == null || i < 1) {
            throw new IllegalArgumentException();
        }
        this.knownExtensions = new ArrayList(list.size());
        this.knownProtocols = new ArrayList(list2.size());
        boolean z = false;
        this.byteBufferList = new ArrayList();
        for (IExtension iExtension : list) {
            if (iExtension.getClass().equals(DefaultExtension.class)) {
                z = true;
            }
        }
        this.knownExtensions.addAll(list);
        if (!z) {
            List<IExtension> list3 = this.knownExtensions;
            list3.add(list3.size(), this.extension);
        }
        this.knownProtocols.addAll(list2);
        this.maxFrameSize = i;
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException {
        if (readVersion(clientHandshake) != 13) {
            this.log.trace("acceptHandshakeAsServer - Wrong websocket version.");
            return HandshakeState.NOT_MATCHED;
        }
        HandshakeState handshakeState = HandshakeState.NOT_MATCHED;
        String fieldValue = clientHandshake.getFieldValue(SEC_WEB_SOCKET_EXTENSIONS);
        Iterator<IExtension> it = this.knownExtensions.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IExtension next = it.next();
            if (next.acceptProvidedExtensionAsServer(fieldValue)) {
                this.extension = next;
                handshakeState = HandshakeState.MATCHED;
                this.log.trace("acceptHandshakeAsServer - Matching extension found: {}", (Object) this.extension);
                break;
            }
        }
        if (containsRequestedProtocol(clientHandshake.getFieldValue(SEC_WEB_SOCKET_PROTOCOL)) == HandshakeState.MATCHED && handshakeState == HandshakeState.MATCHED) {
            return HandshakeState.MATCHED;
        }
        this.log.trace("acceptHandshakeAsServer - No matching extension or protocol found.");
        return HandshakeState.NOT_MATCHED;
    }

    private HandshakeState containsRequestedProtocol(String str) {
        for (IProtocol next : this.knownProtocols) {
            if (next.acceptProvidedProtocol(str)) {
                this.protocol = next;
                this.log.trace("acceptHandshake - Matching protocol found: {}", (Object) next);
                return HandshakeState.MATCHED;
            }
        }
        return HandshakeState.NOT_MATCHED;
    }

    public HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException {
        if (!basicAccept(serverHandshake)) {
            this.log.trace("acceptHandshakeAsClient - Missing/wrong upgrade or connection in handshake.");
            return HandshakeState.NOT_MATCHED;
        } else if (!clientHandshake.hasFieldValue(SEC_WEB_SOCKET_KEY) || !serverHandshake.hasFieldValue(SEC_WEB_SOCKET_ACCEPT)) {
            this.log.trace("acceptHandshakeAsClient - Missing Sec-WebSocket-Key or Sec-WebSocket-Accept");
            return HandshakeState.NOT_MATCHED;
        } else {
            if (!generateFinalKey(clientHandshake.getFieldValue(SEC_WEB_SOCKET_KEY)).equals(serverHandshake.getFieldValue(SEC_WEB_SOCKET_ACCEPT))) {
                this.log.trace("acceptHandshakeAsClient - Wrong key for Sec-WebSocket-Key.");
                return HandshakeState.NOT_MATCHED;
            }
            HandshakeState handshakeState = HandshakeState.NOT_MATCHED;
            String fieldValue = serverHandshake.getFieldValue(SEC_WEB_SOCKET_EXTENSIONS);
            Iterator<IExtension> it = this.knownExtensions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                IExtension next = it.next();
                if (next.acceptProvidedExtensionAsClient(fieldValue)) {
                    this.extension = next;
                    handshakeState = HandshakeState.MATCHED;
                    this.log.trace("acceptHandshakeAsClient - Matching extension found: {}", (Object) this.extension);
                    break;
                }
            }
            if (containsRequestedProtocol(serverHandshake.getFieldValue(SEC_WEB_SOCKET_PROTOCOL)) == HandshakeState.MATCHED && handshakeState == HandshakeState.MATCHED) {
                return HandshakeState.MATCHED;
            }
            this.log.trace("acceptHandshakeAsClient - No matching extension or protocol found.");
            return HandshakeState.NOT_MATCHED;
        }
    }

    public IExtension getExtension() {
        return this.extension;
    }

    public List<IExtension> getKnownExtensions() {
        return this.knownExtensions;
    }

    public IProtocol getProtocol() {
        return this.protocol;
    }

    public int getMaxFrameSize() {
        return this.maxFrameSize;
    }

    public List<IProtocol> getKnownProtocols() {
        return this.knownProtocols;
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put(UPGRADE, "websocket");
        clientHandshakeBuilder.put(CONNECTION, UPGRADE);
        byte[] bArr = new byte[16];
        this.reuseableRandom.nextBytes(bArr);
        clientHandshakeBuilder.put(SEC_WEB_SOCKET_KEY, Base64.encodeBytes(bArr));
        clientHandshakeBuilder.put("Sec-WebSocket-Version", "13");
        StringBuilder sb = new StringBuilder();
        for (IExtension next : this.knownExtensions) {
            if (!(next.getProvidedExtensionAsClient() == null || next.getProvidedExtensionAsClient().length() == 0)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(next.getProvidedExtensionAsClient());
            }
        }
        if (sb.length() != 0) {
            clientHandshakeBuilder.put(SEC_WEB_SOCKET_EXTENSIONS, sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        for (IProtocol next2 : this.knownProtocols) {
            if (next2.getProvidedProtocol().length() != 0) {
                if (sb2.length() > 0) {
                    sb2.append(", ");
                }
                sb2.append(next2.getProvidedProtocol());
            }
        }
        if (sb2.length() != 0) {
            clientHandshakeBuilder.put(SEC_WEB_SOCKET_PROTOCOL, sb2.toString());
        }
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.put(UPGRADE, "websocket");
        serverHandshakeBuilder.put(CONNECTION, clientHandshake.getFieldValue(CONNECTION));
        String fieldValue = clientHandshake.getFieldValue(SEC_WEB_SOCKET_KEY);
        if (fieldValue != null) {
            serverHandshakeBuilder.put(SEC_WEB_SOCKET_ACCEPT, generateFinalKey(fieldValue));
            if (getExtension().getProvidedExtensionAsServer().length() != 0) {
                serverHandshakeBuilder.put(SEC_WEB_SOCKET_EXTENSIONS, getExtension().getProvidedExtensionAsServer());
            }
            if (!(getProtocol() == null || getProtocol().getProvidedProtocol().length() == 0)) {
                serverHandshakeBuilder.put(SEC_WEB_SOCKET_PROTOCOL, getProtocol().getProvidedProtocol());
            }
            serverHandshakeBuilder.setHttpStatusMessage("Web Socket Protocol Handshake");
            serverHandshakeBuilder.put("Server", "TooTallNate Java-WebSocket");
            serverHandshakeBuilder.put("Date", getServerTime());
            return serverHandshakeBuilder;
        }
        throw new InvalidHandshakeException("missing Sec-WebSocket-Key");
    }

    public Draft copyInstance() {
        ArrayList arrayList = new ArrayList();
        for (IExtension copyInstance : getKnownExtensions()) {
            arrayList.add(copyInstance.copyInstance());
        }
        ArrayList arrayList2 = new ArrayList();
        for (IProtocol copyInstance2 : getKnownProtocols()) {
            arrayList2.add(copyInstance2.copyInstance());
        }
        return new Draft_6455(arrayList, arrayList2, this.maxFrameSize);
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        getExtension().encodeFrame(framedata);
        if (this.log.isTraceEnabled()) {
            this.log.trace("afterEnconding({}): {}", (Object) Integer.valueOf(framedata.getPayloadData().remaining()), (Object) framedata.getPayloadData().remaining() > 1000 ? "too big to display" : new String(framedata.getPayloadData().array()));
        }
        return createByteBufferFromFramedata(framedata);
    }

    private ByteBuffer createByteBufferFromFramedata(Framedata framedata) {
        ByteBuffer payloadData = framedata.getPayloadData();
        int i = 0;
        boolean z = this.role == Role.CLIENT;
        int sizeBytes = getSizeBytes(payloadData);
        ByteBuffer allocate = ByteBuffer.allocate((sizeBytes > 1 ? sizeBytes + 1 : sizeBytes) + 1 + (z ? 4 : 0) + payloadData.remaining());
        byte fromOpcode = (byte) (fromOpcode(framedata.getOpcode()) | ((byte) (framedata.isFin() ? -128 : 0)));
        if (framedata.isRSV1()) {
            fromOpcode = (byte) (fromOpcode | getRSVByte(1));
        }
        if (framedata.isRSV2()) {
            fromOpcode = (byte) (fromOpcode | getRSVByte(2));
        }
        if (framedata.isRSV3()) {
            fromOpcode = (byte) (getRSVByte(3) | fromOpcode);
        }
        allocate.put(fromOpcode);
        byte[] byteArray = toByteArray((long) payloadData.remaining(), sizeBytes);
        if (sizeBytes == 1) {
            allocate.put((byte) (byteArray[0] | getMaskByte(z)));
        } else if (sizeBytes == 2) {
            allocate.put((byte) (getMaskByte(z) | 126));
            allocate.put(byteArray);
        } else if (sizeBytes == 8) {
            allocate.put((byte) (getMaskByte(z) | ByteCompanionObject.MAX_VALUE));
            allocate.put(byteArray);
        } else {
            throw new IllegalStateException("Size representation not supported/specified");
        }
        if (z) {
            ByteBuffer allocate2 = ByteBuffer.allocate(4);
            allocate2.putInt(this.reuseableRandom.nextInt());
            allocate.put(allocate2.array());
            while (payloadData.hasRemaining()) {
                allocate.put((byte) (payloadData.get() ^ allocate2.get(i % 4)));
                i++;
            }
        } else {
            allocate.put(payloadData);
            payloadData.flip();
        }
        allocate.flip();
        return allocate;
    }

    private Framedata translateSingleFrame(ByteBuffer byteBuffer) throws IncompleteException, InvalidDataException {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i = 2;
            translateSingleFrameCheckPacketSize(remaining, 2);
            byte b = byteBuffer.get();
            boolean z = (b >> 8) != 0;
            boolean z2 = (b & 64) != 0;
            boolean z3 = (b & 32) != 0;
            boolean z4 = (b & Tnaf.POW_2_WIDTH) != 0;
            byte b2 = byteBuffer.get();
            boolean z5 = (b2 & ByteCompanionObject.MIN_VALUE) != 0;
            int i2 = (byte) (b2 & ByteCompanionObject.MAX_VALUE);
            Opcode opcode = toOpcode((byte) (b & 15));
            if (i2 < 0 || i2 > 125) {
                TranslatedPayloadMetaData translateSingleFramePayloadLength = translateSingleFramePayloadLength(byteBuffer, opcode, i2, remaining, 2);
                i2 = translateSingleFramePayloadLength.getPayloadLength();
                i = translateSingleFramePayloadLength.getRealPackageSize();
            }
            translateSingleFrameCheckLengthLimit((long) i2);
            translateSingleFrameCheckPacketSize(remaining, i + (z5 ? 4 : 0) + i2);
            ByteBuffer allocate = ByteBuffer.allocate(checkAlloc(i2));
            if (z5) {
                byte[] bArr = new byte[4];
                byteBuffer.get(bArr);
                for (int i3 = 0; i3 < i2; i3++) {
                    allocate.put((byte) (byteBuffer.get() ^ bArr[i3 % 4]));
                }
            } else {
                allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
                byteBuffer.position(byteBuffer.position() + allocate.limit());
            }
            FramedataImpl1 framedataImpl1 = FramedataImpl1.get(opcode);
            framedataImpl1.setFin(z);
            framedataImpl1.setRSV1(z2);
            framedataImpl1.setRSV2(z3);
            framedataImpl1.setRSV3(z4);
            allocate.flip();
            framedataImpl1.setPayload(allocate);
            getExtension().isFrameValid(framedataImpl1);
            getExtension().decodeFrame(framedataImpl1);
            if (this.log.isTraceEnabled()) {
                this.log.trace("afterDecoding({}): {}", (Object) Integer.valueOf(framedataImpl1.getPayloadData().remaining()), (Object) framedataImpl1.getPayloadData().remaining() > 1000 ? "too big to display" : new String(framedataImpl1.getPayloadData().array()));
            }
            framedataImpl1.isValid();
            return framedataImpl1;
        }
        throw new IllegalArgumentException();
    }

    private TranslatedPayloadMetaData translateSingleFramePayloadLength(ByteBuffer byteBuffer, Opcode opcode, int i, int i2, int i3) throws InvalidFrameException, IncompleteException, LimitExceededException {
        int i4;
        int i5;
        if (opcode == Opcode.PING || opcode == Opcode.PONG || opcode == Opcode.CLOSING) {
            this.log.trace("Invalid frame: more than 125 octets");
            throw new InvalidFrameException("more than 125 octets");
        }
        if (i == 126) {
            i4 = i3 + 2;
            translateSingleFrameCheckPacketSize(i2, i4);
            byte[] bArr = new byte[3];
            bArr[1] = byteBuffer.get();
            bArr[2] = byteBuffer.get();
            i5 = new BigInteger(bArr).intValue();
        } else {
            i4 = i3 + 8;
            translateSingleFrameCheckPacketSize(i2, i4);
            byte[] bArr2 = new byte[8];
            for (int i6 = 0; i6 < 8; i6++) {
                bArr2[i6] = byteBuffer.get();
            }
            long longValue = new BigInteger(bArr2).longValue();
            translateSingleFrameCheckLengthLimit(longValue);
            i5 = (int) longValue;
        }
        return new TranslatedPayloadMetaData(i5, i4);
    }

    private void translateSingleFrameCheckLengthLimit(long j) throws LimitExceededException {
        if (j <= 2147483647L) {
            int i = this.maxFrameSize;
            if (j > ((long) i)) {
                this.log.trace("Payload limit reached. Allowed: {} Current: {}", (Object) Integer.valueOf(i), (Object) Long.valueOf(j));
                throw new LimitExceededException("Payload limit reached.", this.maxFrameSize);
            } else if (j < 0) {
                this.log.trace("Limit underflow: Payloadsize is to little...");
                throw new LimitExceededException("Payloadsize is to little...");
            }
        } else {
            this.log.trace("Limit exedeed: Payloadsize is to big...");
            throw new LimitExceededException("Payloadsize is to big...");
        }
    }

    private void translateSingleFrameCheckPacketSize(int i, int i2) throws IncompleteException {
        if (i < i2) {
            this.log.trace("Incomplete frame: maxpacketsize < realpacketsize");
            throw new IncompleteException(i2);
        }
    }

    private int getSizeBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= 125) {
            return 1;
        }
        return byteBuffer.remaining() <= 65535 ? 2 : 8;
    }

    public List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        LinkedList linkedList;
        while (true) {
            linkedList = new LinkedList();
            if (this.incompleteframe == null) {
                break;
            }
            try {
                byteBuffer.mark();
                int remaining = byteBuffer.remaining();
                int remaining2 = this.incompleteframe.remaining();
                if (remaining2 > remaining) {
                    this.incompleteframe.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(byteBuffer.position() + remaining);
                    return Collections.emptyList();
                }
                this.incompleteframe.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                linkedList.add(translateSingleFrame((ByteBuffer) this.incompleteframe.duplicate().position(0)));
                this.incompleteframe = null;
            } catch (IncompleteException e) {
                ByteBuffer allocate = ByteBuffer.allocate(checkAlloc(e.getPreferredSize()));
                this.incompleteframe.rewind();
                allocate.put(this.incompleteframe);
                this.incompleteframe = allocate;
            }
        }
        while (byteBuffer.hasRemaining()) {
            byteBuffer.mark();
            try {
                linkedList.add(translateSingleFrame(byteBuffer));
            } catch (IncompleteException e2) {
                byteBuffer.reset();
                ByteBuffer allocate2 = ByteBuffer.allocate(checkAlloc(e2.getPreferredSize()));
                this.incompleteframe = allocate2;
                allocate2.put(byteBuffer);
            }
        }
        return linkedList;
    }

    public List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z) {
        BinaryFrame binaryFrame = new BinaryFrame();
        binaryFrame.setPayload(byteBuffer);
        binaryFrame.setTransferemasked(z);
        try {
            binaryFrame.isValid();
            return Collections.singletonList(binaryFrame);
        } catch (InvalidDataException e) {
            throw new NotSendableException((Throwable) e);
        }
    }

    public List<Framedata> createFrames(String str, boolean z) {
        TextFrame textFrame = new TextFrame();
        textFrame.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(str)));
        textFrame.setTransferemasked(z);
        try {
            textFrame.isValid();
            return Collections.singletonList(textFrame);
        } catch (InvalidDataException e) {
            throw new NotSendableException((Throwable) e);
        }
    }

    public void reset() {
        this.incompleteframe = null;
        IExtension iExtension = this.extension;
        if (iExtension != null) {
            iExtension.reset();
        }
        this.extension = new DefaultExtension();
        this.protocol = null;
    }

    private String getServerTime() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(instance.getTime());
    }

    private String generateFinalKey(String str) {
        try {
            return Base64.encodeBytes(MessageDigest.getInstance("SHA1").digest((str.trim() + WebSocketProtocol.ACCEPT_MAGIC).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private byte[] toByteArray(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((int) (j >>> (i2 - (i3 * 8))));
        }
        return bArr;
    }

    private byte fromOpcode(Opcode opcode) {
        if (opcode == Opcode.CONTINUOUS) {
            return 0;
        }
        if (opcode == Opcode.TEXT) {
            return 1;
        }
        if (opcode == Opcode.BINARY) {
            return 2;
        }
        if (opcode == Opcode.CLOSING) {
            return 8;
        }
        if (opcode == Opcode.PING) {
            return 9;
        }
        if (opcode == Opcode.PONG) {
            return 10;
        }
        throw new IllegalArgumentException("Don't know how to handle " + opcode.toString());
    }

    private Opcode toOpcode(byte b) throws InvalidFrameException {
        if (b == 0) {
            return Opcode.CONTINUOUS;
        }
        if (b == 1) {
            return Opcode.TEXT;
        }
        if (b == 2) {
            return Opcode.BINARY;
        }
        switch (b) {
            case 8:
                return Opcode.CLOSING;
            case 9:
                return Opcode.PING;
            case 10:
                return Opcode.PONG;
            default:
                throw new InvalidFrameException("Unknown opcode " + ((short) b));
        }
    }

    public void processFrame(WebSocketImpl webSocketImpl, Framedata framedata) throws InvalidDataException {
        Opcode opcode = framedata.getOpcode();
        if (opcode == Opcode.CLOSING) {
            processFrameClosing(webSocketImpl, framedata);
        } else if (opcode == Opcode.PING) {
            webSocketImpl.getWebSocketListener().onWebsocketPing(webSocketImpl, framedata);
        } else if (opcode == Opcode.PONG) {
            webSocketImpl.updateLastPong();
            webSocketImpl.getWebSocketListener().onWebsocketPong(webSocketImpl, framedata);
        } else if (!framedata.isFin() || opcode == Opcode.CONTINUOUS) {
            processFrameContinuousAndNonFin(webSocketImpl, framedata, opcode);
        } else if (this.currentContinuousFrame != null) {
            this.log.error("Protocol error: Continuous frame sequence not completed.");
            throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
        } else if (opcode == Opcode.TEXT) {
            processFrameText(webSocketImpl, framedata);
        } else if (opcode == Opcode.BINARY) {
            processFrameBinary(webSocketImpl, framedata);
        } else {
            this.log.error("non control or continious frame expected");
            throw new InvalidDataException(1002, "non control or continious frame expected");
        }
    }

    private void processFrameContinuousAndNonFin(WebSocketImpl webSocketImpl, Framedata framedata, Opcode opcode) throws InvalidDataException {
        if (opcode != Opcode.CONTINUOUS) {
            processFrameIsNotFin(framedata);
        } else if (framedata.isFin()) {
            processFrameIsFin(webSocketImpl, framedata);
        } else if (this.currentContinuousFrame == null) {
            this.log.error("Protocol error: Continuous frame sequence was not started.");
            throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
        }
        if (opcode == Opcode.TEXT && !Charsetfunctions.isValidUTF8(framedata.getPayloadData())) {
            this.log.error("Protocol error: Payload is not UTF8");
            throw new InvalidDataException(1007);
        } else if (opcode == Opcode.CONTINUOUS && this.currentContinuousFrame != null) {
            addToBufferList(framedata.getPayloadData());
        }
    }

    private void processFrameBinary(WebSocketImpl webSocketImpl, Framedata framedata) {
        try {
            webSocketImpl.getWebSocketListener().onWebsocketMessage((WebSocket) webSocketImpl, framedata.getPayloadData());
        } catch (RuntimeException e) {
            logRuntimeException(webSocketImpl, e);
        }
    }

    private void logRuntimeException(WebSocketImpl webSocketImpl, RuntimeException runtimeException) {
        this.log.error("Runtime exception during onWebsocketMessage", (Throwable) runtimeException);
        webSocketImpl.getWebSocketListener().onWebsocketError(webSocketImpl, runtimeException);
    }

    private void processFrameText(WebSocketImpl webSocketImpl, Framedata framedata) throws InvalidDataException {
        try {
            webSocketImpl.getWebSocketListener().onWebsocketMessage((WebSocket) webSocketImpl, Charsetfunctions.stringUtf8(framedata.getPayloadData()));
        } catch (RuntimeException e) {
            logRuntimeException(webSocketImpl, e);
        }
    }

    private void processFrameIsFin(WebSocketImpl webSocketImpl, Framedata framedata) throws InvalidDataException {
        if (this.currentContinuousFrame != null) {
            addToBufferList(framedata.getPayloadData());
            checkBufferLimit();
            if (this.currentContinuousFrame.getOpcode() == Opcode.TEXT) {
                ((FramedataImpl1) this.currentContinuousFrame).setPayload(getPayloadFromByteBufferList());
                ((FramedataImpl1) this.currentContinuousFrame).isValid();
                try {
                    webSocketImpl.getWebSocketListener().onWebsocketMessage((WebSocket) webSocketImpl, Charsetfunctions.stringUtf8(this.currentContinuousFrame.getPayloadData()));
                } catch (RuntimeException e) {
                    logRuntimeException(webSocketImpl, e);
                }
            } else if (this.currentContinuousFrame.getOpcode() == Opcode.BINARY) {
                ((FramedataImpl1) this.currentContinuousFrame).setPayload(getPayloadFromByteBufferList());
                ((FramedataImpl1) this.currentContinuousFrame).isValid();
                try {
                    webSocketImpl.getWebSocketListener().onWebsocketMessage((WebSocket) webSocketImpl, this.currentContinuousFrame.getPayloadData());
                } catch (RuntimeException e2) {
                    logRuntimeException(webSocketImpl, e2);
                }
            }
            this.currentContinuousFrame = null;
            clearBufferList();
            return;
        }
        this.log.trace("Protocol error: Previous continuous frame sequence not completed.");
        throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
    }

    private void processFrameIsNotFin(Framedata framedata) throws InvalidDataException {
        if (this.currentContinuousFrame == null) {
            this.currentContinuousFrame = framedata;
            addToBufferList(framedata.getPayloadData());
            checkBufferLimit();
            return;
        }
        this.log.trace("Protocol error: Previous continuous frame sequence not completed.");
        throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
    }

    private void processFrameClosing(WebSocketImpl webSocketImpl, Framedata framedata) {
        String str;
        int i;
        if (framedata instanceof CloseFrame) {
            CloseFrame closeFrame = (CloseFrame) framedata;
            i = closeFrame.getCloseCode();
            str = closeFrame.getMessage();
        } else {
            i = 1005;
            str = "";
        }
        if (webSocketImpl.getReadyState() == ReadyState.CLOSING) {
            webSocketImpl.closeConnection(i, str, true);
        } else if (getCloseHandshakeType() == CloseHandshakeType.TWOWAY) {
            webSocketImpl.close(i, str, true);
        } else {
            webSocketImpl.flushAndClose(i, str, false);
        }
    }

    private void clearBufferList() {
        synchronized (this.byteBufferList) {
            this.byteBufferList.clear();
        }
    }

    private void addToBufferList(ByteBuffer byteBuffer) {
        synchronized (this.byteBufferList) {
            this.byteBufferList.add(byteBuffer);
        }
    }

    private void checkBufferLimit() throws LimitExceededException {
        long byteBufferListSize = getByteBufferListSize();
        if (byteBufferListSize > ((long) this.maxFrameSize)) {
            clearBufferList();
            this.log.trace("Payload limit reached. Allowed: {} Current: {}", (Object) Integer.valueOf(this.maxFrameSize), (Object) Long.valueOf(byteBufferListSize));
            throw new LimitExceededException(this.maxFrameSize);
        }
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.TWOWAY;
    }

    public String toString() {
        String draft = super.toString();
        if (getExtension() != null) {
            draft = draft + " extension: " + getExtension().toString();
        }
        if (getProtocol() != null) {
            draft = draft + " protocol: " + getProtocol().toString();
        }
        return draft + " max frame size: " + this.maxFrameSize;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Draft_6455 draft_6455 = (Draft_6455) obj;
        if (this.maxFrameSize != draft_6455.getMaxFrameSize()) {
            return false;
        }
        IExtension iExtension = this.extension;
        if (iExtension == null ? draft_6455.getExtension() != null : !iExtension.equals(draft_6455.getExtension())) {
            return false;
        }
        IProtocol iProtocol = this.protocol;
        IProtocol protocol2 = draft_6455.getProtocol();
        if (iProtocol != null) {
            return iProtocol.equals(protocol2);
        }
        if (protocol2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        IExtension iExtension = this.extension;
        int i = 0;
        int hashCode = (iExtension != null ? iExtension.hashCode() : 0) * 31;
        IProtocol iProtocol = this.protocol;
        if (iProtocol != null) {
            i = iProtocol.hashCode();
        }
        int i2 = this.maxFrameSize;
        return ((hashCode + i) * 31) + (i2 ^ (i2 >>> 32));
    }

    private ByteBuffer getPayloadFromByteBufferList() throws LimitExceededException {
        ByteBuffer allocate;
        synchronized (this.byteBufferList) {
            long j = 0;
            for (ByteBuffer limit : this.byteBufferList) {
                j += (long) limit.limit();
            }
            checkBufferLimit();
            allocate = ByteBuffer.allocate((int) j);
            for (ByteBuffer put : this.byteBufferList) {
                allocate.put(put);
            }
        }
        allocate.flip();
        return allocate;
    }

    private long getByteBufferListSize() {
        long j;
        synchronized (this.byteBufferList) {
            j = 0;
            for (ByteBuffer limit : this.byteBufferList) {
                j += (long) limit.limit();
            }
        }
        return j;
    }

    private class TranslatedPayloadMetaData {
        private int payloadLength;
        private int realPackageSize;

        /* access modifiers changed from: private */
        public int getPayloadLength() {
            return this.payloadLength;
        }

        /* access modifiers changed from: private */
        public int getRealPackageSize() {
            return this.realPackageSize;
        }

        TranslatedPayloadMetaData(int i, int i2) {
            this.payloadLength = i;
            this.realPackageSize = i2;
        }
    }
}
