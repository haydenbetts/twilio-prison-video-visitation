package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

public abstract class TlsProtocol {
    protected static final short ADS_MODE_0_N = 1;
    protected static final short ADS_MODE_0_N_FIRSTONLY = 2;
    protected static final short ADS_MODE_1_Nsub1 = 0;
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_END = 16;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private static final String TLS_ERROR_MESSAGE = "Internal TLS error, this could be an attack";
    private ByteQueue alertQueue;
    protected boolean allowCertificateStatus;
    private volatile boolean appDataReady;
    private volatile boolean appDataSplitEnabled;
    private volatile int appDataSplitMode;
    private ByteQueue applicationDataQueue;
    protected boolean blocking;
    protected Hashtable clientExtensions;
    private volatile boolean closed;
    protected short connection_state;
    protected boolean expectSessionTicket;
    private byte[] expected_verify_data;
    private volatile boolean failedWithError;
    private ByteQueue handshakeQueue;
    protected ByteQueueInputStream inputBuffers;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected ByteQueueOutputStream outputBuffer;
    protected Certificate peerCertificate;
    protected boolean receivedChangeCipherSpec;
    RecordStream recordStream;
    protected boolean resumedSession;
    protected SecureRandom secureRandom;
    protected boolean secure_renegotiation;
    protected SecurityParameters securityParameters;
    protected Hashtable serverExtensions;
    protected SessionParameters sessionParameters;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;
    protected TlsSession tlsSession;

    class HandshakeMessage extends ByteArrayOutputStream {
        HandshakeMessage(TlsProtocol tlsProtocol, short s) throws IOException {
            this(s, 60);
        }

        HandshakeMessage(short s, int i) throws IOException {
            super(i + 4);
            TlsUtils.writeUint8(s, (OutputStream) this);
            this.count += 3;
        }

        /* access modifiers changed from: package-private */
        public void writeToRecordStream() throws IOException {
            int i = this.count - 4;
            TlsUtils.checkUint24(i);
            TlsUtils.writeUint24(i, this.buf, 1);
            TlsProtocol.this.writeHandshakeMessage(this.buf, 0, this.count);
            this.buf = null;
        }
    }

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom2) {
        this.applicationDataQueue = new ByteQueue();
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue();
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.recordStream = new RecordStream(this, inputStream, outputStream);
        this.secureRandom = secureRandom2;
    }

    public TlsProtocol(SecureRandom secureRandom2) {
        this.applicationDataQueue = new ByteQueue();
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue();
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.appDataSplitMode = 0;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom2;
    }

    protected static void assertEmpty(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert(50);
        }
    }

    protected static byte[] createRandomBlock(boolean z, RandomGenerator randomGenerator) {
        byte[] bArr = new byte[32];
        randomGenerator.nextBytes(bArr);
        if (z) {
            TlsUtils.writeGMTUnixTime(bArr, 0);
        }
        return bArr;
    }

    protected static byte[] createRenegotiationInfo(byte[] bArr) throws IOException {
        return TlsUtils.encodeOpaque8(bArr);
    }

    protected static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) throws IOException {
        byte[] generatePremasterSecret = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, generatePremasterSecret);
        } finally {
            if (generatePremasterSecret != null) {
                Arrays.fill(generatePremasterSecret, (byte) 0);
            }
        }
    }

    protected static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest forkPRFHash = tlsHandshakeHash.forkPRFHash();
        if (bArr != null && TlsUtils.isSSL(tlsContext)) {
            forkPRFHash.update(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[forkPRFHash.getDigestSize()];
        forkPRFHash.doFinal(bArr2, 0);
        return bArr2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        if (r4 == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        if (r4 == false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        throw new org.bouncycastle.crypto.tls.TlsFatalAlert(47);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int getPRFAlgorithm(org.bouncycastle.crypto.tls.TlsContext r4, int r5) throws java.io.IOException {
        /*
            boolean r4 = org.bouncycastle.crypto.tls.TlsUtils.isTLSv12((org.bouncycastle.crypto.tls.TlsContext) r4)
            r0 = 1
            r1 = 47
            switch(r5) {
                case 59: goto L_0x0038;
                case 60: goto L_0x0038;
                case 61: goto L_0x0038;
                case 62: goto L_0x0038;
                case 63: goto L_0x0038;
                case 64: goto L_0x0038;
                default: goto L_0x000a;
            }
        L_0x000a:
            switch(r5) {
                case 103: goto L_0x0038;
                case 104: goto L_0x0038;
                case 105: goto L_0x0038;
                case 106: goto L_0x0038;
                case 107: goto L_0x0038;
                default: goto L_0x000d;
            }
        L_0x000d:
            r2 = 2
            switch(r5) {
                case 156: goto L_0x0038;
                case 157: goto L_0x002f;
                case 158: goto L_0x0038;
                case 159: goto L_0x002f;
                case 160: goto L_0x0038;
                case 161: goto L_0x002f;
                case 162: goto L_0x0038;
                case 163: goto L_0x002f;
                case 164: goto L_0x0038;
                case 165: goto L_0x002f;
                default: goto L_0x0011;
            }
        L_0x0011:
            switch(r5) {
                case 168: goto L_0x0038;
                case 169: goto L_0x002f;
                case 170: goto L_0x0038;
                case 171: goto L_0x002f;
                case 172: goto L_0x0038;
                case 173: goto L_0x002f;
                default: goto L_0x0014;
            }
        L_0x0014:
            r3 = 0
            switch(r5) {
                case 175: goto L_0x002b;
                case 177: goto L_0x002b;
                case 179: goto L_0x002b;
                case 181: goto L_0x002b;
                case 183: goto L_0x002b;
                case 49208: goto L_0x002b;
                case 49211: goto L_0x002b;
                case 49266: goto L_0x0038;
                case 49267: goto L_0x002f;
                case 49268: goto L_0x0038;
                case 49269: goto L_0x002f;
                case 49270: goto L_0x0038;
                case 49271: goto L_0x002f;
                case 49272: goto L_0x0038;
                case 49273: goto L_0x002f;
                case 49274: goto L_0x0038;
                case 49275: goto L_0x002f;
                case 49276: goto L_0x0038;
                case 49277: goto L_0x002f;
                case 49278: goto L_0x0038;
                case 49279: goto L_0x002f;
                case 49280: goto L_0x0038;
                case 49281: goto L_0x002f;
                case 49282: goto L_0x0038;
                case 49283: goto L_0x002f;
                case 49284: goto L_0x0038;
                case 49285: goto L_0x002f;
                case 49286: goto L_0x0038;
                case 49287: goto L_0x002f;
                case 49288: goto L_0x0038;
                case 49289: goto L_0x002f;
                case 49290: goto L_0x0038;
                case 49291: goto L_0x002f;
                case 49292: goto L_0x0038;
                case 49293: goto L_0x002f;
                case 49294: goto L_0x0038;
                case 49295: goto L_0x002f;
                case 49296: goto L_0x0038;
                case 49297: goto L_0x002f;
                case 49298: goto L_0x0038;
                case 49299: goto L_0x002f;
                case 49301: goto L_0x002b;
                case 49303: goto L_0x002b;
                case 49305: goto L_0x002b;
                case 65296: goto L_0x0038;
                case 65297: goto L_0x0038;
                case 65298: goto L_0x0038;
                case 65299: goto L_0x0038;
                case 65300: goto L_0x0038;
                case 65301: goto L_0x0038;
                default: goto L_0x0018;
            }
        L_0x0018:
            switch(r5) {
                case 185: goto L_0x002b;
                case 186: goto L_0x0038;
                case 187: goto L_0x0038;
                case 188: goto L_0x0038;
                case 189: goto L_0x0038;
                case 190: goto L_0x0038;
                case 191: goto L_0x0038;
                case 192: goto L_0x0038;
                case 193: goto L_0x0038;
                case 194: goto L_0x0038;
                case 195: goto L_0x0038;
                case 196: goto L_0x0038;
                case 197: goto L_0x0038;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r5) {
                case 49187: goto L_0x0038;
                case 49188: goto L_0x002f;
                case 49189: goto L_0x0038;
                case 49190: goto L_0x002f;
                case 49191: goto L_0x0038;
                case 49192: goto L_0x002f;
                case 49193: goto L_0x0038;
                case 49194: goto L_0x002f;
                case 49195: goto L_0x0038;
                case 49196: goto L_0x002f;
                case 49197: goto L_0x0038;
                case 49198: goto L_0x002f;
                case 49199: goto L_0x0038;
                case 49200: goto L_0x002f;
                case 49201: goto L_0x0038;
                case 49202: goto L_0x002f;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r5) {
                case 49307: goto L_0x002b;
                case 49308: goto L_0x0038;
                case 49309: goto L_0x0038;
                case 49310: goto L_0x0038;
                case 49311: goto L_0x0038;
                case 49312: goto L_0x0038;
                case 49313: goto L_0x0038;
                case 49314: goto L_0x0038;
                case 49315: goto L_0x0038;
                case 49316: goto L_0x0038;
                case 49317: goto L_0x0038;
                case 49318: goto L_0x0038;
                case 49319: goto L_0x0038;
                case 49320: goto L_0x0038;
                case 49321: goto L_0x0038;
                case 49322: goto L_0x0038;
                case 49323: goto L_0x0038;
                case 49324: goto L_0x0038;
                case 49325: goto L_0x0038;
                case 49326: goto L_0x0038;
                case 49327: goto L_0x0038;
                default: goto L_0x0021;
            }
        L_0x0021:
            switch(r5) {
                case 52392: goto L_0x0038;
                case 52393: goto L_0x0038;
                case 52394: goto L_0x0038;
                case 52395: goto L_0x0038;
                case 52396: goto L_0x0038;
                case 52397: goto L_0x0038;
                case 52398: goto L_0x0038;
                default: goto L_0x0024;
            }
        L_0x0024:
            switch(r5) {
                case 65280: goto L_0x0038;
                case 65281: goto L_0x0038;
                case 65282: goto L_0x0038;
                case 65283: goto L_0x0038;
                case 65284: goto L_0x0038;
                case 65285: goto L_0x0038;
                default: goto L_0x0027;
            }
        L_0x0027:
            if (r4 == 0) goto L_0x002a
            return r0
        L_0x002a:
            return r3
        L_0x002b:
            if (r4 == 0) goto L_0x002e
            return r2
        L_0x002e:
            return r3
        L_0x002f:
            if (r4 == 0) goto L_0x0032
            return r2
        L_0x0032:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r1)
            throw r4
        L_0x0038:
            if (r4 == 0) goto L_0x003b
            return r0
        L_0x003b:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r4.<init>(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsProtocol.getPRFAlgorithm(org.bouncycastle.crypto.tls.TlsContext, int):int");
    }

    private void processAlert() throws IOException {
        while (this.alertQueue.available() >= 2) {
            byte[] removeData = this.alertQueue.removeData(2, 0);
            short s = (short) removeData[0];
            short s2 = (short) removeData[1];
            getPeer().notifyAlertReceived(s, s2);
            if (s != 2) {
                if (s2 == 0) {
                    handleClose(false);
                }
                handleWarningMessage(s2);
            } else {
                invalidateSession();
                this.failedWithError = true;
                this.closed = true;
                this.recordStream.safeClose();
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
    }

    private void processApplicationData() {
    }

    private void processChangeCipherSpec(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            if (TlsUtils.readUint8(bArr, i + i3) != 1) {
                throw new TlsFatalAlert(50);
            } else if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert(10);
            } else {
                this.recordStream.receivedReadCipherSpec();
                this.receivedChangeCipherSpec = true;
                handleChangeCipherSpecMessage();
                i3++;
            }
        }
    }

    private void processHandshake() throws IOException {
        boolean z;
        do {
            z = true;
            if (this.handshakeQueue.available() >= 4) {
                byte[] bArr = new byte[4];
                this.handshakeQueue.read(bArr, 0, 4, 0);
                short readUint8 = TlsUtils.readUint8(bArr, 0);
                int readUint24 = TlsUtils.readUint24(bArr, 1);
                if (this.handshakeQueue.available() >= readUint24 + 4) {
                    byte[] removeData = this.handshakeQueue.removeData(readUint24, 4);
                    checkReceivedChangeCipherSpec(this.connection_state == 16 || readUint8 == 20);
                    if (readUint8 != 0) {
                        if (readUint8 == 20) {
                            TlsContext context = getContext();
                            if (this.expected_verify_data == null && context.getSecurityParameters().getMasterSecret() != null) {
                                this.expected_verify_data = createVerifyData(!context.isServer());
                            }
                        }
                        this.recordStream.updateHandshakeData(bArr, 0, 4);
                        this.recordStream.updateHandshakeData(removeData, 0, readUint24);
                    }
                    handleHandshakeMessage(readUint8, removeData);
                    continue;
                }
            }
            z = false;
            continue;
        } while (z);
    }

    protected static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque16);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2)), TlsUtils.readOpaque16(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert(47);
            }
        }
        return hashtable;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    protected static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
        TlsUtils.writeOpaque16(byteArrayOutputStream.toByteArray(), outputStream);
    }

    protected static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean z) throws IOException {
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (z == (bArr.length == 0)) {
                TlsUtils.checkUint16(intValue);
                TlsUtils.writeUint16(intValue, outputStream);
                TlsUtils.writeOpaque16(bArr, outputStream);
            }
        }
    }

    protected static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    /* access modifiers changed from: protected */
    public int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    /* access modifiers changed from: protected */
    public void applyMaxFragmentLengthExtension() throws IOException {
        if (this.securityParameters.maxFragmentLength < 0) {
            return;
        }
        if (MaxFragmentLength.isValid(this.securityParameters.maxFragmentLength)) {
            this.recordStream.setPlaintextLimit(1 << (this.securityParameters.maxFragmentLength + 8));
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public void blockForHandshake() throws IOException {
        if (this.blocking) {
            while (this.connection_state != 16) {
                safeReadRecord();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkReceivedChangeCipherSpec(boolean z) throws IOException {
        if (z != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    public void close() throws IOException {
        handleClose(true);
    }

    /* access modifiers changed from: protected */
    public void completeHandshake() throws IOException {
        try {
            this.recordStream.finaliseHandshake();
            this.appDataSplitEnabled = !TlsUtils.isTLSv11(getContext());
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new SessionParameters.Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                getContextAdmin().setResumableSession(this.tlsSession);
            }
            getPeer().notifyHandshakeComplete();
        } finally {
            cleanupHandshake();
        }
    }

    /* access modifiers changed from: protected */
    public byte[] createVerifyData(boolean z) {
        TlsContext context = getContext();
        return TlsUtils.calculateVerifyData(context, z ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(context, this.recordStream.getHandshakeHash(), z ? TlsUtils.SSL_SERVER : TlsUtils.SSL_CLIENT));
    }

    /* access modifiers changed from: protected */
    public void failWithError(short s, short s2, String str, Throwable th) throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (s == 2) {
                invalidateSession();
                this.failedWithError = true;
            }
            raiseAlert(s, s2, str, th);
            this.recordStream.safeClose();
            if (s != 2) {
                return;
            }
        }
        throw new IOException(TLS_ERROR_MESSAGE);
    }

    /* access modifiers changed from: protected */
    public void flush() throws IOException {
        this.recordStream.flush();
    }

    public int getAvailableInputBytes() {
        if (!this.blocking) {
            return applicationDataAvailable();
        }
        throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
    }

    public int getAvailableOutputBytes() {
        if (!this.blocking) {
            return this.outputBuffer.getBuffer().available();
        }
        throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
    }

    /* access modifiers changed from: protected */
    public abstract TlsContext getContext();

    /* access modifiers changed from: package-private */
    public abstract AbstractTlsContext getContextAdmin();

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    /* access modifiers changed from: protected */
    public abstract TlsPeer getPeer();

    /* access modifiers changed from: protected */
    public void handleChangeCipherSpecMessage() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleClose(boolean z) throws IOException {
        if (!this.closed) {
            if (z && !this.appDataReady) {
                raiseWarning(90, "User canceled handshake");
            }
            failWithError(1, 0, "Connection closed", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void handleHandshakeMessage(short s, byte[] bArr) throws IOException;

    /* access modifiers changed from: protected */
    public void handleWarningMessage(short s) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void invalidateSession() {
        SessionParameters sessionParameters2 = this.sessionParameters;
        if (sessionParameters2 != null) {
            sessionParameters2.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession2 = this.tlsSession;
        if (tlsSession2 != null) {
            tlsSession2.invalidate();
            this.tlsSession = null;
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void offerInput(byte[] bArr) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        } else if (!this.closed) {
            this.inputBuffers.addBytes(bArr);
            while (this.inputBuffers.available() >= 5) {
                byte[] bArr2 = new byte[5];
                this.inputBuffers.peek(bArr2);
                if (this.inputBuffers.available() >= TlsUtils.readUint16(bArr2, 3) + 5) {
                    safeReadRecord();
                } else {
                    return;
                }
            }
        } else {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
    }

    public void offerOutput(byte[] bArr, int i, int i2) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        } else if (this.appDataReady) {
            writeData(bArr, i, i2);
        } else {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        }
    }

    /* access modifiers changed from: protected */
    public void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] bArr = this.expected_verify_data;
        if (bArr != null) {
            byte[] readFully = TlsUtils.readFully(bArr.length, (InputStream) byteArrayInputStream);
            assertEmpty(byteArrayInputStream);
            if (!Arrays.constantTimeAreEqual(this.expected_verify_data, readFully)) {
                throw new TlsFatalAlert(51);
            }
            return;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    /* access modifiers changed from: protected */
    public void processRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        switch (s) {
            case 20:
                processChangeCipherSpec(bArr, i, i2);
                return;
            case 21:
                this.alertQueue.addData(bArr, i, i2);
                processAlert();
                return;
            case 22:
                this.handshakeQueue.addData(bArr, i, i2);
                processHandshake();
                return;
            case 23:
                if (this.appDataReady) {
                    this.applicationDataQueue.addData(bArr, i, i2);
                    processApplicationData();
                    return;
                }
                throw new TlsFatalAlert(10);
            case 24:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert(10);
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void raiseAlert(short s, short s2, String str, Throwable th) throws IOException {
        getPeer().notifyAlertRaised(s, s2, str, th);
        safeWriteRecord(21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    /* access modifiers changed from: protected */
    public void raiseWarning(short s, String str) throws IOException {
        raiseAlert(1, s, str, (Throwable) null);
    }

    /* access modifiers changed from: protected */
    public int readApplicationData(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (!this.closed) {
                safeReadRecord();
            } else if (!this.failedWithError) {
                return -1;
            } else {
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
        int min = Math.min(i2, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(bArr, i, min, 0);
        return min;
    }

    public int readInput(byte[] bArr, int i, int i2) {
        if (!this.blocking) {
            try {
                return readApplicationData(bArr, i, Math.min(i2, applicationDataAvailable()));
            } catch (IOException e) {
                throw new RuntimeException(e.toString());
            }
        } else {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
    }

    public int readOutput(byte[] bArr, int i, int i2) {
        if (!this.blocking) {
            int min = Math.min(getAvailableOutputBytes(), i2);
            this.outputBuffer.getBuffer().removeData(bArr, i, min, 0);
            return min;
        }
        throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
    }

    /* access modifiers changed from: protected */
    public void refuseRenegotiation() throws IOException {
        if (!TlsUtils.isSSL(getContext())) {
            raiseWarning(100, "Renegotiation not supported");
            return;
        }
        throw new TlsFatalAlert(40);
    }

    /* access modifiers changed from: protected */
    public void safeReadRecord() throws IOException {
        try {
            if (!this.recordStream.readRecord()) {
                throw new EOFException();
            }
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), "Failed to read record", e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to read record", e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to read record", e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void safeWriteRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        try {
            this.recordStream.writeRecord(s, bArr, i, i2);
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), "Failed to write record", e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to write record", e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to write record", e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void sendCertificateMessage(Certificate certificate) throws IOException {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                raiseWarning(41, serverVersion.toString() + " client didn't provide credentials");
                return;
            }
        }
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 11);
        certificate.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendChangeCipherSpecMessage() throws IOException {
        safeWriteRecord(20, new byte[]{1}, 0, 1);
        this.recordStream.sentWriteCipherSpec();
    }

    /* access modifiers changed from: protected */
    public void sendFinishedMessage() throws IOException {
        byte[] createVerifyData = createVerifyData(getContext().isServer());
        HandshakeMessage handshakeMessage = new HandshakeMessage(20, createVerifyData.length);
        handshakeMessage.write(createVerifyData);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendSupplementalDataMessage(Vector vector) throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, 23);
        writeSupplementalData(handshakeMessage, vector);
        handshakeMessage.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void setAppDataSplitMode(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + i);
        }
        this.appDataSplitMode = i;
    }

    /* access modifiers changed from: protected */
    public void writeData(byte[] bArr, int i, int i2) throws IOException {
        if (!this.closed) {
            while (i2 > 0) {
                if (this.appDataSplitEnabled) {
                    int i3 = this.appDataSplitMode;
                    if (i3 != 1) {
                        if (i3 != 2) {
                            safeWriteRecord(23, bArr, i, 1);
                            i++;
                            i2--;
                        } else {
                            this.appDataSplitEnabled = false;
                        }
                    }
                    safeWriteRecord(23, TlsUtils.EMPTY_BYTES, 0, 0);
                }
                if (i2 > 0) {
                    int min = Math.min(i2, this.recordStream.getPlaintextLimit());
                    safeWriteRecord(23, bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
        } else if (this.failedWithError) {
            throw new IOException(TLS_ERROR_MESSAGE);
        } else {
            throw new IOException("Sorry, connection has been closed, you cannot write more data");
        }
    }

    /* access modifiers changed from: protected */
    public void writeHandshakeMessage(byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int min = Math.min(i2, this.recordStream.getPlaintextLimit());
            safeWriteRecord(22, bArr, i, min);
            i += min;
            i2 -= min;
        }
    }
}
