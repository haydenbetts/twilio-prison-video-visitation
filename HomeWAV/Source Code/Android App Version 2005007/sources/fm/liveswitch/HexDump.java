package fm.liveswitch;

import fm.liveswitch.sdp.AddressType;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeType;
import fm.liveswitch.sdp.ConnectionData;
import fm.liveswitch.sdp.CryptoAttribute;
import fm.liveswitch.sdp.Media;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.Origin;
import fm.liveswitch.sdp.ice.CandidateAttribute;
import fm.liveswitch.sdp.ice.FingerprintAttribute;

class HexDump {
    private static IDataBufferPool __dataBufferPool;
    private static ILog __log;
    private long __baseTimestamp = -1;
    private String __callId;
    private DispatchQueue<String> __dispatchQueue;
    /* access modifiers changed from: private */
    public FileStream __file;
    private long __firstTimestamp = -1;
    private DataBuffer __iPv4Header;
    private DataBuffer __iPv6Header;
    private boolean __opened;
    private Object __openedLock = new Object();
    private DataBuffer __udpHeader;
    private volatile boolean __unsupportedAddressType = false;
    private String _path;

    private String getDefaultSourceIPAddress(boolean z) {
        return z ? "127.0.0.1" : "127.0.0.2";
    }

    private int getDefaultSourcePort(boolean z) {
        return z ? 1000 : 2000;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (r0 == null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        r0.waitForDrain();
        r3.__dispatchQueue.destroy();
        r3.__dispatchQueue = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r0 = r3.__iPv4Header;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r0 == null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r0.free();
        r3.__iPv4Header = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r0 = r3.__iPv6Header;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r0 == null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        r0.free();
        r3.__iPv6Header = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        r0 = r3.__udpHeader;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0030, code lost:
        if (r0 == null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        r0.free();
        r3.__udpHeader = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r0 = r3.__file;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        r0.close();
        r3.__file = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        r0 = r3.__dispatchQueue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean close() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.__openedLock
            monitor-enter(r0)
            boolean r1 = r3.__opened     // Catch:{ all -> 0x0042 }
            r2 = 0
            if (r1 != 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            return r2
        L_0x000a:
            r3.__opened = r2     // Catch:{ all -> 0x0042 }
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            fm.liveswitch.DispatchQueue<java.lang.String> r0 = r3.__dispatchQueue
            r1 = 0
            if (r0 == 0) goto L_0x001c
            r0.waitForDrain()
            fm.liveswitch.DispatchQueue<java.lang.String> r0 = r3.__dispatchQueue
            r0.destroy()
            r3.__dispatchQueue = r1
        L_0x001c:
            fm.liveswitch.DataBuffer r0 = r3.__iPv4Header
            if (r0 == 0) goto L_0x0025
            r0.free()
            r3.__iPv4Header = r1
        L_0x0025:
            fm.liveswitch.DataBuffer r0 = r3.__iPv6Header
            if (r0 == 0) goto L_0x002e
            r0.free()
            r3.__iPv6Header = r1
        L_0x002e:
            fm.liveswitch.DataBuffer r0 = r3.__udpHeader
            if (r0 == 0) goto L_0x0037
            r0.free()
            r3.__udpHeader = r1
        L_0x0037:
            fm.liveswitch.FileStream r0 = r3.__file
            if (r0 == 0) goto L_0x0040
            r0.close()
            r3.__file = r1
        L_0x0040:
            r0 = 1
            return r0
        L_0x0042:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.HexDump.close():boolean");
    }

    private String getDefaultDestinationIPAddress(boolean z) {
        return getDefaultSourceIPAddress(!z);
    }

    private int getDefaultDestinationPort(boolean z) {
        return getDefaultSourcePort(!z);
    }

    public String getPath() {
        return this._path;
    }

    static {
        Class<HexDump> cls = HexDump.class;
        __log = Log.getLogger((Class) cls);
        __dataBufferPool = DataBufferPool.getTracer((Class) cls);
    }

    public HexDump(String str) {
        setPath(str);
        this.__callId = Utility.generateId();
    }

    public boolean open() {
        synchronized (this.__openedLock) {
            if (this.__opened) {
                return false;
            }
            this.__opened = true;
            FileStream fileStream = new FileStream(getPath());
            this.__file = fileStream;
            fileStream.open(FileStreamAccess.Write);
            this.__iPv4Header = __dataBufferPool.take(20, false, true);
            this.__iPv6Header = __dataBufferPool.take(40, false, true);
            this.__udpHeader = __dataBufferPool.take(8, false, true);
            this.__iPv4Header.write4(4, 0, 0);
            DataBuffer dataBuffer = this.__iPv4Header;
            dataBuffer.write4(dataBuffer.getLength() / 4, 0, 4);
            this.__iPv4Header.write8(255, 8);
            this.__iPv6Header.write4(6, 0, 0);
            this.__iPv6Header.write8(255, 7);
            this.__dispatchQueue = new DispatchQueue<>(new IAction1<String>() {
                public void invoke(String str) {
                    byte[] encode = Utf8.encode(str);
                    HexDump.this.__file.write(encode, 0, ArrayExtensions.getLength(encode));
                    HexDump.this.__file.flush();
                }
            });
            return true;
        }
    }

    private String processSdpMessage(Message message, String str, int i) {
        String ip4 = Global.equals(LocalNetwork.getAddressType(str), AddressType.IPv4) ? AddressType.getIP4() : AddressType.getIP6();
        Message parse = Message.parse(message.toString());
        Origin origin = parse.getOrigin();
        if (origin != null) {
            origin.setUnicastAddress(str);
            origin.setAddressType(ip4);
        }
        ConnectionData connectionData = parse.getConnectionData();
        if (connectionData != null) {
            connectionData.setConnectionAddress(str);
            connectionData.setAddressType(ip4);
        }
        parse.removeSessionAttribute(AttributeType.IceFingerprintAttribute);
        parse.removeSessionAttribute(AttributeType.CryptoAttribute);
        for (MediaDescription mediaDescription : parse.getMediaDescriptions()) {
            Media media = mediaDescription.getMedia();
            if (media != null) {
                media.setTransportPort(i);
                media.setTransportProtocol(fm.liveswitch.sdp.rtp.Media.getRtpAvpfTransportProtocol());
            }
            ConnectionData connectionData2 = mediaDescription.getConnectionData();
            if (connectionData2 != null) {
                connectionData2.setConnectionAddress(str);
                connectionData2.setAddressType(ip4);
            }
            for (Attribute attribute : mediaDescription.getMediaAttributes()) {
                fm.liveswitch.sdp.rtcp.Attribute attribute2 = (fm.liveswitch.sdp.rtcp.Attribute) Global.tryCast(attribute, fm.liveswitch.sdp.rtcp.Attribute.class);
                if (attribute2 != null) {
                    attribute2.setConnectionAddress(str);
                    attribute2.setAddressType(ip4);
                    attribute2.setPort(i);
                }
                CandidateAttribute candidateAttribute = (CandidateAttribute) Global.tryCast(attribute, CandidateAttribute.class);
                if (candidateAttribute != null) {
                    mediaDescription.removeMediaAttribute(candidateAttribute);
                }
                FingerprintAttribute fingerprintAttribute = (FingerprintAttribute) Global.tryCast(attribute, FingerprintAttribute.class);
                if (fingerprintAttribute != null) {
                    mediaDescription.removeMediaAttribute(fingerprintAttribute);
                }
                CryptoAttribute cryptoAttribute = (CryptoAttribute) Global.tryCast(attribute, CryptoAttribute.class);
                if (cryptoAttribute != null) {
                    mediaDescription.removeMediaAttribute(cryptoAttribute);
                }
            }
        }
        return parse.toString();
    }

    private void setPath(String str) {
        this._path = str;
    }

    public boolean write(boolean z, long j, DataBuffer[] dataBufferArr, String str, String str2, int i, int i2) {
        String str3;
        int i3;
        long j2 = j;
        if (this.__baseTimestamp == -1) {
            this.__baseTimestamp = DateExtensions.getTicks(DateExtensions.getUtcNow());
            this.__firstTimestamp = j2;
            open();
        }
        AddressType addressType = LocalNetwork.getAddressType(str);
        if (!Global.equals(addressType, AddressType.IPv4) && !Global.equals(addressType, AddressType.IPv6) && !this.__unsupportedAddressType) {
            __log.warn(StringExtensions.format("Address type '{0}' is not supported.", (Object) addressType.toString()));
            this.__unsupportedAddressType = true;
        }
        if (this.__unsupportedAddressType) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, StringExtensions.format("{0} {1}\n", z ? "O " : "I ", Iso8601Timestamp.dateTimeToIso8601(DateExtensions.createDate(this.__baseTimestamp + (j2 - this.__firstTimestamp)))));
        int length = (Global.equals(addressType, AddressType.IPv4) ? this.__iPv4Header : this.__iPv6Header).getLength();
        int length2 = this.__udpHeader.getLength();
        int i4 = 0;
        for (int i5 = 0; i5 < ArrayExtensions.getLength((Object[]) dataBufferArr); i5++) {
            i4 += dataBufferArr[i5].getLength();
        }
        String str4 = StringExtensions.empty;
        if (Global.equals(addressType, AddressType.IPv4)) {
            this.__iPv4Header.write16(length + length2 + i4, 2);
            this.__iPv4Header.write8(17, 9);
            this.__iPv4Header.write16(0, 10);
            this.__iPv4Header.writeBytes(LocalNetwork.getAddressBytes(str), 12);
            this.__iPv4Header.writeBytes(LocalNetwork.getAddressBytes(str2), 16);
            str3 = StringExtensions.concat(str4, this.__iPv4Header.toHexString());
        } else {
            this.__iPv6Header.write16(length2 + i4, 4);
            this.__iPv6Header.write8(17, 6);
            this.__iPv6Header.writeBytes(LocalNetwork.getAddressBytes(str), 8);
            this.__iPv6Header.writeBytes(LocalNetwork.getAddressBytes(str2), 24);
            str3 = StringExtensions.concat(str4, this.__iPv6Header.toHexString());
        }
        this.__udpHeader.write16(i, 0);
        this.__udpHeader.write16(i2, 2);
        this.__udpHeader.write16(length2 + i4, 4);
        this.__udpHeader.write16(0, 6);
        String concat = StringExtensions.concat(str3, this.__udpHeader.toHexString());
        for (int i6 = 0; i6 < ArrayExtensions.getLength((Object[]) dataBufferArr); i6++) {
            concat = StringExtensions.concat(concat, dataBufferArr[i6].toHexString());
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < StringExtensions.getLength(concat)) {
            StringBuilderExtensions.append(sb, BitAssistant.getHexString(Binary.toBytes24(i8, false)));
            int i9 = i7;
            while (true) {
                i3 = i7 + 32;
                if (i9 >= i3 || i9 >= StringExtensions.getLength(concat)) {
                    StringBuilderExtensions.append(sb, "\n");
                    i8 += 16;
                    i7 = i3;
                } else {
                    StringBuilderExtensions.append(sb, ' ');
                    StringBuilderExtensions.append(sb, concat.charAt(i9));
                    StringBuilderExtensions.append(sb, concat.charAt(i9 + 1));
                    i9 += 2;
                }
            }
            StringBuilderExtensions.append(sb, "\n");
            i8 += 16;
            i7 = i3;
        }
        this.__dispatchQueue.enqueue(sb.toString());
        return true;
    }

    private boolean writeAck(boolean z, long j, String str, String str2, int i, int i2) {
        String str3 = str;
        String str4 = str2;
        StringBuilder sb = new StringBuilder();
        if (z) {
            StringBuilderExtensions.append(sb, StringExtensions.format("ACK sip:remote@{0}:{1} SIP/2.0\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:remote@{0}:{1}>\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
        } else {
            StringBuilderExtensions.append(sb, StringExtensions.format("ACK sip:local@{0}:{1} SIP/2.0\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:remote@{0}:{1}>\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:remote@{0}:{1}>\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
        }
        StringBuilderExtensions.append(sb, StringExtensions.format("Call-ID: {0}\n", (Object) this.__callId));
        StringBuilderExtensions.append(sb, StringExtensions.format("Max-Forwards: 255\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("CSeq: 1 ACK\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Length: 0\n", new Object[0]));
        StringBuilderExtensions.append(sb, "\n");
        return write(z, j, new DataBuffer[]{DataBuffer.wrap(Utf8.encode(sb.toString()))}, str, str2, i, i2);
    }

    public boolean writeAnswer(boolean z, long j, Message message) {
        return writeAnswer(z, j, message, getDefaultSourceIPAddress(z), getDefaultDestinationIPAddress(z), getDefaultSourcePort(z), getDefaultDestinationPort(z));
    }

    public boolean writeAnswer(boolean z, long j, Message message, String str, String str2, int i, int i2) {
        String str3 = str;
        String str4 = str2;
        String processSdpMessage = processSdpMessage(message, str3, i);
        StringBuilder sb = new StringBuilder();
        if (z) {
            StringBuilderExtensions.append(sb, StringExtensions.format("SIP/2.0 200 OK\n", new Object[0]));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:local@{0}:{1}>\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:local@{0}:{1}>\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
        } else {
            StringBuilderExtensions.append(sb, StringExtensions.format("SIP/2.0 200 OK\n", new Object[0]));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:local@{0}:{1}>\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
        }
        StringBuilderExtensions.append(sb, StringExtensions.format("Call-ID: {0}\n", (Object) this.__callId));
        StringBuilderExtensions.append(sb, StringExtensions.format("Max-Forwards: 255\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("CSeq: 1 INVITE\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Type: application/sdp\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Length: {0}\n", (Object) IntegerExtensions.toString(Integer.valueOf(StringExtensions.getLength(processSdpMessage)))));
        StringBuilderExtensions.append(sb, "\n");
        StringBuilderExtensions.append(sb, StringExtensions.format("{0}\n", (Object) processSdpMessage));
        StringBuilderExtensions.append(sb, "\n");
        if (!write(z, j, new DataBuffer[]{DataBuffer.wrap(Utf8.encode(sb.toString()))}, str, str2, i, i2)) {
            return false;
        }
        return writeAck(!z, j, str2, str, i2, i);
    }

    public boolean writeBye(boolean z, long j) {
        return writeBye(z, j, getDefaultSourceIPAddress(z), getDefaultDestinationIPAddress(z), getDefaultSourcePort(z), getDefaultDestinationPort(z));
    }

    public boolean writeBye(boolean z, long j, String str, String str2, int i, int i2) {
        String str3 = str;
        String str4 = str2;
        StringBuilder sb = new StringBuilder();
        if (z) {
            StringBuilderExtensions.append(sb, StringExtensions.format("BYE sip:remote@{0}:{1} SIP/2.0\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:local@{0}:{1}>\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:local@{0}:{1}>\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
        } else {
            StringBuilderExtensions.append(sb, StringExtensions.format("BYE sip:local@{0}:{1} SIP/2.0\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:local@{0}:{1}>\n", str3, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
        }
        StringBuilderExtensions.append(sb, StringExtensions.format("Call-ID: {0}\n", (Object) this.__callId));
        StringBuilderExtensions.append(sb, StringExtensions.format("Max-Forwards: 255\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("CSeq: 2 BYE\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Length: 0\n", new Object[0]));
        StringBuilderExtensions.append(sb, "\n");
        if (!write(z, j, new DataBuffer[]{DataBuffer.wrap(Utf8.encode(sb.toString()))}, str, str2, i, i2)) {
            return false;
        }
        return writeOK(!z, j, str2, str, i2, i);
    }

    public boolean writeOffer(boolean z, long j, Message message) {
        return writeOffer(z, j, message, getDefaultSourceIPAddress(z), getDefaultDestinationIPAddress(z), getDefaultSourcePort(z), getDefaultDestinationPort(z));
    }

    public boolean writeOffer(boolean z, long j, Message message, String str, String str2, int i, int i2) {
        String str3 = str;
        String str4 = str2;
        Message message2 = message;
        String processSdpMessage = processSdpMessage(message, str, i);
        StringBuilder sb = new StringBuilder();
        if (z) {
            StringBuilderExtensions.append(sb, StringExtensions.format("INVITE sip:remote@{0}:{1} SIP/2.0\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
        } else {
            StringBuilderExtensions.append(sb, StringExtensions.format("INVITE sip:local@{0}:{1} SIP/2.0\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:remote@{0}:{1}>\n", str4, IntegerExtensions.toString(Integer.valueOf(i2))));
        }
        StringBuilderExtensions.append(sb, StringExtensions.format("Call-ID: {0}\n", (Object) this.__callId));
        StringBuilderExtensions.append(sb, StringExtensions.format("Max-Forwards: 255\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("CSeq: 1 INVITE\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Type: application/sdp\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Length: {0}\n", (Object) IntegerExtensions.toString(Integer.valueOf(StringExtensions.getLength(processSdpMessage)))));
        StringBuilderExtensions.append(sb, "\n");
        StringBuilderExtensions.append(sb, StringExtensions.format("{0}\n", (Object) processSdpMessage));
        StringBuilderExtensions.append(sb, "\n");
        return write(z, j, new DataBuffer[]{DataBuffer.wrap(Utf8.encode(sb.toString()))}, str, str2, i, i2);
    }

    private boolean writeOK(boolean z, long j, String str, String str2, int i, int i2) {
        String str3 = str;
        String str4 = str2;
        StringBuilder sb = new StringBuilder();
        if (z) {
            StringBuilderExtensions.append(sb, StringExtensions.format("SIP/2.0 200 OK\n", new Object[0]));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:remote@{0}:{1}>\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
        } else {
            StringBuilderExtensions.append(sb, StringExtensions.format("SIP/2.0 200 OK\n", new Object[0]));
            StringBuilderExtensions.append(sb, StringExtensions.format("Via: SIP/2.0/UDP {0}:{1}\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("To: <sip:remote@{0}:{1}>\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
            StringBuilderExtensions.append(sb, StringExtensions.format("From: <sip:local@{0}:{1}>\n", str, IntegerExtensions.toString(Integer.valueOf(i))));
            StringBuilderExtensions.append(sb, StringExtensions.format("Contact: <sip:remote@{0}:{1}>\n", str2, IntegerExtensions.toString(Integer.valueOf(i2))));
        }
        StringBuilderExtensions.append(sb, StringExtensions.format("Call-ID: {0}\n", (Object) this.__callId));
        StringBuilderExtensions.append(sb, StringExtensions.format("Max-Forwards: 255\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("CSeq: 2 BYE\n", new Object[0]));
        StringBuilderExtensions.append(sb, StringExtensions.format("Content-Length: 0\n", new Object[0]));
        StringBuilderExtensions.append(sb, "\n");
        return write(z, j, new DataBuffer[]{DataBuffer.wrap(Utf8.encode(sb.toString()))}, str, str2, i, i2);
    }

    public boolean writeRtcp(boolean z, long j, RtcpPacket[] rtcpPacketArr) {
        return writeRtcp(z, j, rtcpPacketArr, getDefaultSourceIPAddress(z), getDefaultDestinationIPAddress(z), getDefaultSourcePort(z), getDefaultDestinationPort(z));
    }

    public boolean writeRtcp(boolean z, long j, RtcpPacket[] rtcpPacketArr, String str, String str2, int i, int i2) {
        DataBuffer[] dataBufferArr = new DataBuffer[ArrayExtensions.getLength((Object[]) rtcpPacketArr)];
        for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) rtcpPacketArr); i3++) {
            dataBufferArr[i3] = rtcpPacketArr[i3].getBuffer();
        }
        return write(z, j, dataBufferArr, str, str2, i, i2);
    }

    public boolean writeRtp(boolean z, long j, RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        return writeRtp(z, j, rtpPacketHeader, dataBuffer, getDefaultSourceIPAddress(z), getDefaultDestinationIPAddress(z), getDefaultSourcePort(z), getDefaultDestinationPort(z));
    }

    public boolean writeRtp(boolean z, long j, RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer, String str, String str2, int i, int i2) {
        DataBuffer take = __dataBufferPool.take(rtpPacketHeader.calculateHeaderLength());
        try {
            rtpPacketHeader.writeTo(take, 0);
            return write(z, j, new DataBuffer[]{take, dataBuffer}, str, str2, i, i2);
        } finally {
            take.free();
        }
    }
}
