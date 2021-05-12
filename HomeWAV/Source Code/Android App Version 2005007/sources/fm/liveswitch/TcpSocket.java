package fm.liveswitch;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.auth.x500.X500Principal;

public class TcpSocket extends StreamSocket {
    private static TcpSocketCipherSuites _cipherSuites = TcpSocketCipherSuites.Default;
    static IDataBufferPool _dataBufferPool = DataBufferPool.getTracer(TcpSocket.class);
    private static boolean attemptDscp;
    private static boolean attemptNoDelay;
    private String _LocalIPAddress;
    private int _LocalPort;
    private String _RemoteIPAddress;
    private int _RemotePort;
    private ExecutorService _execAccept = Executors.newFixedThreadPool(1);
    private ExecutorService _execConnect = Executors.newFixedThreadPool(1);
    private ExecutorService _execIn = Executors.newFixedThreadPool(1);
    private ExecutorService _execOut = Executors.newFixedThreadPool(1);
    /* access modifiers changed from: private */
    public InputStream _in;
    private boolean _ipv6;
    private boolean _isClosed;
    private OutputStream _out;
    private String _remoteHostname;
    private boolean _secure;
    private boolean _server;
    private ServerSocket _serverSocket;
    private Socket _socket;

    public boolean getServer() {
        return this._server;
    }

    public boolean getSecure() {
        return this._secure;
    }

    public boolean getIPv6() {
        return this._ipv6;
    }

    public static TcpSocketCipherSuites getCipherSuites() {
        return _cipherSuites;
    }

    public static void setCipherSuites(TcpSocketCipherSuites tcpSocketCipherSuites) {
        _cipherSuites = tcpSocketCipherSuites;
    }

    public TcpSocket(boolean z, boolean z2, boolean z3) {
        try {
            this._server = z;
            this._ipv6 = z2;
            this._secure = z3;
            if (z2) {
                this._LocalIPAddress = "::";
                this._RemoteIPAddress = "::";
            } else {
                this._LocalIPAddress = "0.0.0.0";
                this._RemoteIPAddress = "0.0.0.0";
            }
            this._LocalPort = 0;
            this._RemotePort = 0;
            if (z) {
                if (z3) {
                    this._serverSocket = SSLServerSocketFactory.getDefault().createServerSocket();
                    if (_cipherSuites == TcpSocketCipherSuites.All) {
                        SSLServerSocket sSLServerSocket = (SSLServerSocket) this._serverSocket;
                        sSLServerSocket.setEnabledCipherSuites(sSLServerSocket.getSupportedCipherSuites());
                        return;
                    }
                    return;
                }
                this._serverSocket = ServerSocketFactory.getDefault().createServerSocket();
            } else if (z3) {
                this._socket = SSLSocketFactory.getDefault().createSocket();
                if (_cipherSuites == TcpSocketCipherSuites.All) {
                    SSLSocket sSLSocket = (SSLSocket) this._socket;
                    sSLSocket.setEnabledCipherSuites(sSLSocket.getSupportedCipherSuites());
                }
            } else {
                this._socket = SocketFactory.getDefault().createSocket();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TcpSocket(Socket socket, boolean z, boolean z2) {
        this._server = z;
        this._secure = z2;
        boolean z3 = socket.getLocalAddress() instanceof Inet6Address;
        this._ipv6 = z3;
        if (z3) {
            this._LocalIPAddress = "::";
            this._RemoteIPAddress = "::";
        } else {
            this._LocalIPAddress = "0.0.0.0";
            this._RemoteIPAddress = "0.0.0.0";
        }
        this._LocalPort = 0;
        this._RemotePort = 0;
        this._socket = socket;
        try {
            this._out = socket.getOutputStream();
            this._in = socket.getInputStream();
        } catch (Exception e) {
            Log.debug(StringExtensions.format("Could not accept a connection on TCP socket: {0}.", (Object) e.getMessage()));
        }
    }

    public boolean getIsClosed() {
        return this._isClosed;
    }

    public String getLocalIPAddress() {
        try {
            Socket socket = this._socket;
            if (socket != null) {
                this._LocalIPAddress = socket.getLocalAddress().getHostAddress();
            } else {
                this._LocalIPAddress = this._serverSocket.getInetAddress().getHostAddress();
            }
        } catch (Exception unused) {
        }
        return this._LocalIPAddress;
    }

    public int getLocalPort() {
        try {
            Socket socket = this._socket;
            if (socket != null) {
                this._LocalPort = socket.getLocalPort();
            } else {
                this._LocalPort = this._serverSocket.getLocalPort();
            }
        } catch (Exception unused) {
        }
        return this._LocalPort;
    }

    public String getRemoteIPAddress() {
        try {
            Socket socket = this._socket;
            if (socket != null) {
                this._RemoteIPAddress = socket.getInetAddress().getHostAddress();
            }
        } catch (Exception unused) {
        }
        return this._RemoteIPAddress;
    }

    public int getRemotePort() {
        try {
            Socket socket = this._socket;
            if (socket != null) {
                this._RemotePort = socket.getPort();
            }
        } catch (Exception unused) {
        }
        return this._RemotePort;
    }

    public String getRemoteHostname() {
        return this._remoteHostname;
    }

    public static boolean getAttemptNoDelay() {
        return attemptNoDelay;
    }

    public static void setAttemptNoDelay(boolean z) {
        attemptNoDelay = z;
    }

    public static boolean getAttemptDscp() {
        return attemptDscp;
    }

    public static void setAttemptDscp(boolean z) {
        attemptDscp = z;
    }

    private static void tuneSocket(Socket socket) {
        if (attemptNoDelay) {
            try {
                socket.setTcpNoDelay(true);
            } catch (Exception e) {
                attemptNoDelay = false;
                Log.debug("Could not enable no-delay for TCP. Disabling for future sockets.", e);
            }
        }
        if (attemptDscp) {
            try {
                socket.setTrafficClass(46);
            } catch (Exception e2) {
                attemptDscp = false;
                Log.debug("Could not enable DSCP for TCP. Disabling for future sockets.", e2);
            }
        }
    }

    public boolean bind(String str, int i, BooleanHolder booleanHolder) {
        InetAddress inetAddress;
        booleanHolder.setValue(false);
        try {
            if (getIPv6()) {
                inetAddress = Inet6Address.getByName(str);
            } else {
                inetAddress = Inet4Address.getByName(str);
            }
            Socket socket = this._socket;
            if (socket != null) {
                socket.bind(new InetSocketAddress(inetAddress, i));
                tuneSocket(this._socket);
                return true;
            }
            this._serverSocket.bind(new InetSocketAddress(inetAddress, i));
            return true;
        } catch (BindException e) {
            booleanHolder.setValue(e.getMessage().contains("in use"));
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public TcpSocket accept() throws Exception {
        if (this._socket == null) {
            Socket accept = this._serverSocket.accept();
            tuneSocket(accept);
            return new TcpSocket(accept, getServer(), getSecure());
        }
        throw new Exception("Client TCP sockets cannot 'accept'.");
    }

    public void acceptAsync(final IAction0 iAction0, final IAction1<Exception> iAction1, final IAction1<StreamSocket> iAction12) {
        try {
            this._execAccept.submit(new Runnable() {
                public void run() {
                    try {
                        TcpSocket access$000 = TcpSocket.this.accept();
                        TcpSocket.this.raiseAcceptSuccess(iAction0);
                        TcpSocket.this.raiseAcceptSocket(iAction12, access$000);
                    } catch (Exception e) {
                        TcpSocket.this.raiseAcceptFailure(iAction1, new Exception("Socket accept failed.", e));
                    }
                }
            });
        } catch (Exception e) {
            raiseAcceptFailure(iAction1, new Exception("Socket accept failed.", e));
        }
    }

    /* access modifiers changed from: private */
    public void connect(String str, String str2, int i) throws Exception {
        InetAddress inetAddress;
        if (this._socket != null) {
            this._remoteHostname = str;
            if (getIPv6()) {
                inetAddress = Inet6Address.getByName(str2);
            } else {
                inetAddress = Inet4Address.getByName(str2);
            }
            this._socket.connect(new InetSocketAddress(inetAddress, i));
            if (!this._secure || new DefaultHostnameVerifier().verify(str, ((SSLSocket) this._socket).getSession())) {
                this._out = this._socket.getOutputStream();
                this._in = this._socket.getInputStream();
                return;
            }
            throw new RuntimeException("SSL socket authentication failed.");
        }
        throw new Exception("Server TCP sockets cannot 'connect'.");
    }

    public void connectAsync(String str, String str2, int i, int i2, IAction0 iAction0, final IAction2<Exception, Boolean> iAction2) {
        final TimeoutTimer timeoutTimer;
        if (i2 > 0) {
            try {
                TimeoutTimer timeoutTimer2 = new TimeoutTimer(new IAction1<Object>() {
                    public void invoke(Object obj) {
                        TcpSocket.this.raiseConnectFailure(iAction2, new Exception("Socket connect timed out."), true);
                    }
                }, (Object) null);
                timeoutTimer2.start(i2);
                timeoutTimer = timeoutTimer2;
            } catch (Exception e) {
                raiseConnectFailure(iAction2, new Exception("Socket connect failed.", e), false);
                return;
            }
        } else {
            timeoutTimer = null;
        }
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final IAction0 iAction02 = iAction0;
        final IAction2<Exception, Boolean> iAction22 = iAction2;
        this._execConnect.submit(new Runnable() {
            public void run() {
                try {
                    TcpSocket.this.connect(str3, str4, i3);
                    TimeoutTimer timeoutTimer = timeoutTimer;
                    if (timeoutTimer == null || timeoutTimer.stop()) {
                        TcpSocket.this.raiseConnectSuccess(iAction02);
                    }
                } catch (Exception e) {
                    TimeoutTimer timeoutTimer2 = timeoutTimer;
                    if (timeoutTimer2 == null || timeoutTimer2.stop()) {
                        TcpSocket.this.raiseConnectFailure(iAction22, new Exception("Socket connect failed.", e), false);
                    }
                }
            }
        });
    }

    public boolean send(DataBuffer dataBuffer) {
        try {
            this._out.write(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void sendAsync(DataBuffer dataBuffer, int i, IAction0 iAction0, final IAction2<Exception, Boolean> iAction2) {
        final TimeoutTimer timeoutTimer;
        if (i > 0) {
            try {
                TimeoutTimer timeoutTimer2 = new TimeoutTimer(new IAction1<Object>() {
                    public void invoke(Object obj) {
                        TcpSocket.this.raiseSendFailure(iAction2, new Exception("Socket send timed out."), true);
                    }
                }, (Object) null);
                timeoutTimer2.start(i);
                timeoutTimer = timeoutTimer2;
            } catch (Exception e) {
                raiseSendFailure(iAction2, new Exception("Socket send failed.", e), false);
                return;
            }
        } else {
            timeoutTimer = null;
        }
        dataBuffer.keep();
        final DataBuffer dataBuffer2 = dataBuffer;
        final IAction0 iAction02 = iAction0;
        final IAction2<Exception, Boolean> iAction22 = iAction2;
        this._execOut.submit(new Runnable() {
            public void run() {
                try {
                    TcpSocket.this.send(dataBuffer2);
                    TimeoutTimer timeoutTimer = timeoutTimer;
                    if (timeoutTimer == null || timeoutTimer.stop()) {
                        TcpSocket.this.raiseSendSuccess(iAction02);
                    }
                } catch (Exception e) {
                    TimeoutTimer timeoutTimer2 = timeoutTimer;
                    if (timeoutTimer2 == null || timeoutTimer2.stop()) {
                        TcpSocket.this.raiseSendFailure(iAction22, new Exception("Socket send failed.", e), false);
                    }
                } catch (Throwable th) {
                    dataBuffer2.free();
                    throw th;
                }
                dataBuffer2.free();
            }
        });
    }

    public void receiveAsync(int i) {
        final IAction1<DataBuffer> onReceiveSuccess = super.getOnReceiveSuccess();
        final IAction2<Exception, Boolean> onReceiveFailure = super.getOnReceiveFailure();
        final TimeoutTimer timeoutTimer = null;
        if (i > 0) {
            try {
                TimeoutTimer timeoutTimer2 = new TimeoutTimer(new IAction1<Object>() {
                    public void invoke(Object obj) {
                        TcpSocket.this.raiseReceiveFailure(onReceiveFailure, new Exception("Socket receive timed out."), true);
                    }
                }, (Object) null);
                timeoutTimer2.start(i);
                timeoutTimer = timeoutTimer2;
            } catch (Exception e) {
                raiseReceiveFailure(onReceiveFailure, new Exception("Socket receive failed.", e), false);
                return;
            }
        }
        this._execIn.submit(new Runnable() {
            public void run() {
                TimeoutTimer timeoutTimer;
                DataBuffer take = TcpSocket._dataBufferPool.take(2048);
                try {
                    int read = TcpSocket.this._in.read(take.getData(), take.getIndex(), take.getLength());
                    if (read <= 0 || ((timeoutTimer = timeoutTimer) != null && !timeoutTimer.stop())) {
                        if (read == 0) {
                            TcpSocket.this.close();
                        }
                        take.free();
                    }
                    TcpSocket.this.raiseReceiveSuccess(onReceiveSuccess, take.subset(0, read));
                    take.free();
                } catch (Exception e) {
                    TimeoutTimer timeoutTimer2 = timeoutTimer;
                    if (timeoutTimer2 == null || timeoutTimer2.stop()) {
                        TcpSocket.this.raiseReceiveFailure(onReceiveFailure, new Exception("Socket receive failed.", e), false);
                        TcpSocket.this.close();
                    }
                } catch (Throwable th) {
                    take.free();
                    throw th;
                }
            }
        });
    }

    public void close() {
        try {
            InputStream inputStream = this._in;
            if (inputStream != null) {
                inputStream.close();
            }
            OutputStream outputStream = this._out;
            if (outputStream != null) {
                outputStream.close();
            }
            Socket socket = this._socket;
            if (socket != null) {
                socket.close();
            }
            ServerSocket serverSocket = this._serverSocket;
            if (serverSocket != null) {
                serverSocket.close();
            }
            ExecutorService executorService = this._execAccept;
            if (executorService != null) {
                executorService.shutdown();
            }
            ExecutorService executorService2 = this._execConnect;
            if (executorService2 != null) {
                executorService2.shutdown();
            }
            ExecutorService executorService3 = this._execIn;
            if (executorService3 != null) {
                executorService3.shutdown();
            }
            ExecutorService executorService4 = this._execOut;
            if (executorService4 != null) {
                executorService4.shutdown();
            }
        } catch (Exception unused) {
        }
        this._isClosed = true;
    }

    final class DefaultHostnameVerifier implements HostnameVerifier {
        private static final int ALT_DNS_NAME = 2;
        private static final int ALT_IPA_NAME = 7;

        DefaultHostnameVerifier() {
        }

        public final boolean verify(String str, SSLSession sSLSession) {
            try {
                return verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            } catch (SSLException unused) {
                return false;
            }
        }

        public boolean verify(String str, X509Certificate x509Certificate) {
            return verifyHostName(str, x509Certificate);
        }

        private boolean verifyHostName(String str, X509Certificate x509Certificate) {
            String lowerCase = str.toLowerCase(Locale.US);
            boolean z = false;
            for (String verifyHostName : getSubjectAltNames(x509Certificate, 2)) {
                if (verifyHostName(lowerCase, verifyHostName)) {
                    return true;
                }
                z = true;
            }
            if (!z) {
                String findMostSpecific = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn");
                if (findMostSpecific != null) {
                    return verifyHostName(lowerCase, findMostSpecific);
                }
            }
            return false;
        }

        private List<String> getSubjectAltNames(X509Certificate x509Certificate, int i) {
            String str;
            ArrayList arrayList = new ArrayList();
            try {
                Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                if (subjectAlternativeNames == null) {
                    return Collections.emptyList();
                }
                for (List next : subjectAlternativeNames) {
                    if (next != null) {
                        if (next.size() >= 2) {
                            Integer num = (Integer) next.get(0);
                            if (num != null) {
                                if (num.intValue() == i && (str = (String) next.get(1)) != null) {
                                    arrayList.add(str);
                                }
                            }
                        }
                    }
                }
                return arrayList;
            } catch (CertificateParsingException unused) {
                return Collections.emptyList();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
            r5 = r1 + 1;
            r4 = r9.length() - r5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean verifyHostName(java.lang.String r8, java.lang.String r9) {
            /*
                r7 = this;
                r0 = 0
                if (r8 == 0) goto L_0x006c
                boolean r1 = r8.isEmpty()
                if (r1 != 0) goto L_0x006c
                if (r9 == 0) goto L_0x006c
                boolean r1 = r9.isEmpty()
                if (r1 == 0) goto L_0x0012
                goto L_0x006c
            L_0x0012:
                java.util.Locale r1 = java.util.Locale.US
                java.lang.String r9 = r9.toLowerCase(r1)
                java.lang.String r1 = "*"
                boolean r1 = r9.contains(r1)
                if (r1 != 0) goto L_0x0025
                boolean r8 = r8.equals(r9)
                return r8
            L_0x0025:
                java.lang.String r1 = "*."
                boolean r1 = r9.startsWith(r1)
                r2 = 1
                if (r1 == 0) goto L_0x003b
                int r1 = r9.length()
                r3 = 2
                int r1 = r1 - r3
                boolean r1 = r8.regionMatches(r0, r9, r3, r1)
                if (r1 == 0) goto L_0x003b
                return r2
            L_0x003b:
                r1 = 42
                int r1 = r9.indexOf(r1)
                r3 = 46
                int r4 = r9.indexOf(r3)
                if (r1 <= r4) goto L_0x004a
                return r0
            L_0x004a:
                boolean r4 = r8.regionMatches(r0, r9, r0, r1)
                if (r4 != 0) goto L_0x0051
                return r0
            L_0x0051:
                int r4 = r9.length()
                int r5 = r1 + 1
                int r4 = r4 - r5
                int r6 = r8.length()
                int r6 = r6 - r4
                int r1 = r8.indexOf(r3, r1)
                if (r1 >= r6) goto L_0x0064
                return r0
            L_0x0064:
                boolean r8 = r8.regionMatches(r6, r9, r5, r4)
                if (r8 != 0) goto L_0x006b
                return r0
            L_0x006b:
                return r2
            L_0x006c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TcpSocket.DefaultHostnameVerifier.verifyHostName(java.lang.String, java.lang.String):boolean");
        }
    }

    final class DistinguishedNameParser {
        private int beg;
        private char[] chars;
        private int cur;
        private final String dn;
        private int end;
        private final int length;
        private int pos;

        public DistinguishedNameParser(X500Principal x500Principal) {
            String name = x500Principal.getName("RFC2253");
            this.dn = name;
            this.length = name.length();
        }

        /* JADX WARNING: Removed duplicated region for block: B:6:0x0015 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String nextAT() {
            /*
                r7 = this;
            L_0x0000:
                int r0 = r7.pos
                int r1 = r7.length
                r2 = 32
                if (r0 >= r1) goto L_0x0013
                char[] r3 = r7.chars
                char r3 = r3[r0]
                if (r3 != r2) goto L_0x0013
                int r0 = r0 + 1
                r7.pos = r0
                goto L_0x0000
            L_0x0013:
                if (r0 != r1) goto L_0x0017
                r0 = 0
                return r0
            L_0x0017:
                r7.beg = r0
                int r0 = r0 + 1
                r7.pos = r0
            L_0x001d:
                int r0 = r7.pos
                int r1 = r7.length
                r3 = 61
                if (r0 >= r1) goto L_0x0034
                char[] r4 = r7.chars
                char r5 = r4[r0]
                if (r5 == r3) goto L_0x0034
                char r4 = r4[r0]
                if (r4 == r2) goto L_0x0034
                int r0 = r0 + 1
                r7.pos = r0
                goto L_0x001d
            L_0x0034:
                java.lang.String r4 = "Unexpected end of DN: "
                if (r0 >= r1) goto L_0x00da
                r7.end = r0
                char[] r1 = r7.chars
                char r0 = r1[r0]
                if (r0 != r2) goto L_0x0075
            L_0x0040:
                int r0 = r7.pos
                int r1 = r7.length
                if (r0 >= r1) goto L_0x0055
                char[] r5 = r7.chars
                char r6 = r5[r0]
                if (r6 == r3) goto L_0x0055
                char r5 = r5[r0]
                if (r5 != r2) goto L_0x0055
                int r0 = r0 + 1
                r7.pos = r0
                goto L_0x0040
            L_0x0055:
                char[] r5 = r7.chars
                char r5 = r5[r0]
                if (r5 != r3) goto L_0x005e
                if (r0 == r1) goto L_0x005e
                goto L_0x0075
            L_0x005e:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r4)
                java.lang.String r2 = r7.dn
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0075:
                int r0 = r7.pos
                int r0 = r0 + 1
                r7.pos = r0
            L_0x007b:
                int r0 = r7.pos
                int r1 = r7.length
                if (r0 >= r1) goto L_0x008c
                char[] r1 = r7.chars
                char r1 = r1[r0]
                if (r1 != r2) goto L_0x008c
                int r0 = r0 + 1
                r7.pos = r0
                goto L_0x007b
            L_0x008c:
                int r0 = r7.end
                int r1 = r7.beg
                int r0 = r0 - r1
                r2 = 4
                if (r0 <= r2) goto L_0x00cd
                char[] r0 = r7.chars
                int r3 = r1 + 3
                char r3 = r0[r3]
                r4 = 46
                if (r3 != r4) goto L_0x00cd
                char r3 = r0[r1]
                r4 = 79
                if (r3 == r4) goto L_0x00aa
                char r3 = r0[r1]
                r4 = 111(0x6f, float:1.56E-43)
                if (r3 != r4) goto L_0x00cd
            L_0x00aa:
                int r3 = r1 + 1
                char r3 = r0[r3]
                r4 = 73
                if (r3 == r4) goto L_0x00ba
                int r3 = r1 + 1
                char r3 = r0[r3]
                r4 = 105(0x69, float:1.47E-43)
                if (r3 != r4) goto L_0x00cd
            L_0x00ba:
                int r3 = r1 + 2
                char r3 = r0[r3]
                r4 = 68
                if (r3 == r4) goto L_0x00ca
                int r3 = r1 + 2
                char r0 = r0[r3]
                r3 = 100
                if (r0 != r3) goto L_0x00cd
            L_0x00ca:
                int r1 = r1 + r2
                r7.beg = r1
            L_0x00cd:
                java.lang.String r0 = new java.lang.String
                char[] r1 = r7.chars
                int r2 = r7.beg
                int r3 = r7.end
                int r3 = r3 - r2
                r0.<init>(r1, r2, r3)
                return r0
            L_0x00da:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r4)
                java.lang.String r2 = r7.dn
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TcpSocket.DistinguishedNameParser.nextAT():java.lang.String");
        }

        private String quotedAV() {
            int i = this.pos + 1;
            this.pos = i;
            this.beg = i;
            this.end = i;
            while (true) {
                int i2 = this.pos;
                if (i2 != this.length) {
                    char[] cArr = this.chars;
                    if (cArr[i2] == '\"') {
                        this.pos = i2 + 1;
                        while (true) {
                            int i3 = this.pos;
                            if (i3 >= this.length || this.chars[i3] != ' ') {
                                char[] cArr2 = this.chars;
                                int i4 = this.beg;
                            } else {
                                this.pos = i3 + 1;
                            }
                        }
                        char[] cArr22 = this.chars;
                        int i42 = this.beg;
                        return new String(cArr22, i42, this.end - i42);
                    }
                    if (cArr[i2] == '\\') {
                        cArr[this.end] = getEscaped();
                    } else {
                        cArr[this.end] = cArr[i2];
                    }
                    this.pos++;
                    this.end++;
                } else {
                    throw new IllegalStateException("Unexpected end of DN: " + this.dn);
                }
            }
        }

        private String hexAV() {
            int i;
            int i2 = this.pos;
            if (i2 + 4 < this.length) {
                this.beg = i2;
                this.pos = i2 + 1;
                while (true) {
                    i = this.pos;
                    if (i == this.length) {
                        break;
                    }
                    char[] cArr = this.chars;
                    if (cArr[i] == '+' || cArr[i] == ',' || cArr[i] == ';') {
                        break;
                    } else if (cArr[i] == ' ') {
                        this.end = i;
                        this.pos = i + 1;
                        while (true) {
                            int i3 = this.pos;
                            if (i3 >= this.length || this.chars[i3] != ' ') {
                                break;
                            }
                            this.pos = i3 + 1;
                        }
                    } else {
                        if (cArr[i] >= 'A' && cArr[i] <= 'F') {
                            cArr[i] = (char) (cArr[i] + ' ');
                        }
                        this.pos = i + 1;
                    }
                }
                this.end = i;
                int i4 = this.end;
                int i5 = this.beg;
                int i6 = i4 - i5;
                if (i6 < 5 || (i6 & 1) == 0) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.dn);
                }
                int i7 = i6 / 2;
                byte[] bArr = new byte[i7];
                int i8 = i5 + 1;
                for (int i9 = 0; i9 < i7; i9++) {
                    bArr[i9] = (byte) getByte(i8);
                    i8 += 2;
                }
                return new String(this.chars, this.beg, i6);
            }
            throw new IllegalStateException("Unexpected end of DN: " + this.dn);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
            r1 = r8.chars;
            r2 = r8.beg;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a3, code lost:
            return new java.lang.String(r1, r2, r8.cur - r2);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String escapedAV() {
            /*
                r8 = this;
                int r0 = r8.pos
                r8.beg = r0
                r8.end = r0
            L_0x0006:
                int r0 = r8.pos
                int r1 = r8.length
                if (r0 < r1) goto L_0x0019
                java.lang.String r0 = new java.lang.String
                char[] r1 = r8.chars
                int r2 = r8.beg
                int r3 = r8.end
                int r3 = r3 - r2
                r0.<init>(r1, r2, r3)
                return r0
            L_0x0019:
                char[] r1 = r8.chars
                char r2 = r1[r0]
                r3 = 44
                r4 = 43
                r5 = 59
                r6 = 32
                if (r2 == r6) goto L_0x0060
                if (r2 == r5) goto L_0x0053
                r5 = 92
                if (r2 == r5) goto L_0x0040
                if (r2 == r4) goto L_0x0053
                if (r2 == r3) goto L_0x0053
                int r2 = r8.end
                int r3 = r2 + 1
                r8.end = r3
                char r3 = r1[r0]
                r1[r2] = r3
                int r0 = r0 + 1
                r8.pos = r0
                goto L_0x0006
            L_0x0040:
                int r0 = r8.end
                int r2 = r0 + 1
                r8.end = r2
                char r2 = r8.getEscaped()
                r1[r0] = r2
                int r0 = r8.pos
                int r0 = r0 + 1
                r8.pos = r0
                goto L_0x0006
            L_0x0053:
                java.lang.String r0 = new java.lang.String
                char[] r1 = r8.chars
                int r2 = r8.beg
                int r3 = r8.end
                int r3 = r3 - r2
                r0.<init>(r1, r2, r3)
                return r0
            L_0x0060:
                int r2 = r8.end
                r8.cur = r2
                int r0 = r0 + 1
                r8.pos = r0
                int r0 = r2 + 1
                r8.end = r0
                r1[r2] = r6
            L_0x006e:
                int r0 = r8.pos
                int r1 = r8.length
                if (r0 >= r1) goto L_0x0087
                char[] r2 = r8.chars
                char r7 = r2[r0]
                if (r7 != r6) goto L_0x0087
                int r1 = r8.end
                int r7 = r1 + 1
                r8.end = r7
                r2[r1] = r6
                int r0 = r0 + 1
                r8.pos = r0
                goto L_0x006e
            L_0x0087:
                if (r0 == r1) goto L_0x0097
                char[] r1 = r8.chars
                char r2 = r1[r0]
                if (r2 == r3) goto L_0x0097
                char r2 = r1[r0]
                if (r2 == r4) goto L_0x0097
                char r0 = r1[r0]
                if (r0 != r5) goto L_0x0006
            L_0x0097:
                java.lang.String r0 = new java.lang.String
                char[] r1 = r8.chars
                int r2 = r8.beg
                int r3 = r8.cur
                int r3 = r3 - r2
                r0.<init>(r1, r2, r3)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TcpSocket.DistinguishedNameParser.escapedAV():java.lang.String");
        }

        private char getEscaped() {
            int i = this.pos + 1;
            this.pos = i;
            if (i != this.length) {
                char[] cArr = this.chars;
                char c = cArr[i];
                if (!(c == ' ' || c == '%' || c == '\\' || c == '_' || c == '\"' || c == '#')) {
                    switch (c) {
                        case '*':
                        case '+':
                        case ',':
                            break;
                        default:
                            switch (c) {
                                case ';':
                                case '<':
                                case '=':
                                case '>':
                                    break;
                                default:
                                    return getUTF8();
                            }
                    }
                }
                return cArr[i];
            }
            throw new IllegalStateException("Unexpected end of DN: " + this.dn);
        }

        private char getUTF8() {
            int i;
            int i2;
            int i3 = getByte(this.pos);
            this.pos++;
            if (i3 < 128) {
                return (char) i3;
            }
            if (i3 < 192 || i3 > 247) {
                return '?';
            }
            if (i3 <= 223) {
                i2 = i3 & 31;
                i = 1;
            } else if (i3 <= 239) {
                i = 2;
                i2 = i3 & 15;
            } else {
                i = 3;
                i2 = i3 & 7;
            }
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = this.pos + 1;
                this.pos = i5;
                if (i5 == this.length || this.chars[i5] != '\\') {
                    return '?';
                }
                int i6 = i5 + 1;
                this.pos = i6;
                int i7 = getByte(i6);
                this.pos++;
                if ((i7 & 192) != 128) {
                    return '?';
                }
                i2 = (i2 << 6) + (i7 & 63);
            }
            return (char) i2;
        }

        private int getByte(int i) {
            int i2;
            int i3;
            int i4 = i + 1;
            if (i4 < this.length) {
                char[] cArr = this.chars;
                char c = cArr[i];
                if (c >= '0' && c <= '9') {
                    i2 = c - '0';
                } else if (c >= 'a' && c <= 'f') {
                    i2 = c - 'W';
                } else if (c < 'A' || c > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.dn);
                } else {
                    i2 = c - '7';
                }
                char c2 = cArr[i4];
                if (c2 >= '0' && c2 <= '9') {
                    i3 = c2 - '0';
                } else if (c2 >= 'a' && c2 <= 'f') {
                    i3 = c2 - 'W';
                } else if (c2 < 'A' || c2 > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.dn);
                } else {
                    i3 = c2 - '7';
                }
                return (i2 << 4) + i3;
            }
            throw new IllegalStateException("Malformed DN: " + this.dn);
        }

        public String findMostSpecific(String str) {
            String str2;
            this.pos = 0;
            this.beg = 0;
            this.end = 0;
            this.cur = 0;
            this.chars = this.dn.toCharArray();
            String nextAT = nextAT();
            if (nextAT == null) {
                return null;
            }
            do {
                int i = this.pos;
                if (i == this.length) {
                    return null;
                }
                char c = this.chars[i];
                if (c != '\"') {
                    str2 = c != '#' ? (c == '+' || c == ',' || c == ';') ? "" : escapedAV() : hexAV();
                } else {
                    str2 = quotedAV();
                }
                if (str.equalsIgnoreCase(nextAT)) {
                    return str2;
                }
                int i2 = this.pos;
                if (i2 >= this.length) {
                    return null;
                }
                char[] cArr = this.chars;
                if (cArr[i2] == ',' || cArr[i2] == ';' || cArr[i2] == '+') {
                    this.pos = i2 + 1;
                    nextAT = nextAT();
                } else {
                    throw new IllegalStateException("Malformed DN: " + this.dn);
                }
            } while (nextAT != null);
            throw new IllegalStateException("Malformed DN: " + this.dn);
        }
    }
}
