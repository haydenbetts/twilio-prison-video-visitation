package fm.liveswitch;

import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpSocket extends DatagramSocket {
    static IDataBufferPool _dataBufferPool;
    private static int _defaultReceiveBufferSize = 65536;
    private static int _defaultSendBufferSize = 65536;
    static ILog _log;
    private static boolean attemptDscp;
    private String _LocalIPAddress;
    private int _LocalPort;
    private boolean _ipv6;
    private ExecutorService execIn;
    private ExecutorService execOut;
    private boolean isClosed;
    private Object isClosedLock;
    private int maxQueuedPackets;
    /* access modifiers changed from: private */
    public DatagramSocket socket;

    static {
        Class<UdpSocket> cls = UdpSocket.class;
        _dataBufferPool = DataBufferPool.getTracer((Class) cls);
        _log = Log.getLogger((Class) cls);
    }

    public static int getDefaultSendBufferSize() {
        return _defaultSendBufferSize;
    }

    public static void setDefaultSendBufferSize(int i) {
        _defaultSendBufferSize = i;
    }

    public static int getDefaultReceiveBufferSize() {
        return _defaultReceiveBufferSize;
    }

    public static void setDefaultReceiveBufferSize(int i) {
        _defaultReceiveBufferSize = i;
    }

    public int getReceiveBufferSize() {
        try {
            return this.socket.getReceiveBufferSize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getSendBufferSize() {
        try {
            return this.socket.getSendBufferSize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UdpSocket(boolean z) {
        this(z, getDefaultSendBufferSize(), getDefaultReceiveBufferSize());
    }

    public UdpSocket(boolean z, int i, int i2) {
        this.execIn = Executors.newFixedThreadPool(1);
        this.execOut = Executors.newFixedThreadPool(1);
        this.isClosedLock = new Object();
        this._ipv6 = z;
        if (z) {
            this._LocalIPAddress = "::";
        } else {
            this._LocalIPAddress = "0.0.0.0";
        }
        this._LocalPort = 9;
        try {
            this.socket = new DatagramSocket((SocketAddress) null);
            if (i2 >= 0) {
                int max = Math.max(2048, i2);
                while (true) {
                    if (max < 2048) {
                        break;
                    } else if (setReceiveBufferSize(max)) {
                        break;
                    } else {
                        max = (int) Math.ceil((double) (((float) max) / 2.0f));
                    }
                }
                _log.verbose(String.format(Locale.getDefault(), "Socket receive buffer size is %d.", new Object[]{Integer.valueOf(getReceiveBufferSize())}));
            }
            if (i >= 0) {
                int max2 = Math.max(2048, i);
                while (true) {
                    if (max2 < 2048) {
                        break;
                    } else if (setSendBufferSize(max2)) {
                        break;
                    } else {
                        max2 = (int) Math.ceil((double) (((float) max2) / 2.0f));
                    }
                }
                _log.verbose(String.format(Locale.getDefault(), "Socket send buffer size is %d.", new Object[]{Integer.valueOf(getSendBufferSize())}));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean setReceiveBufferSize(int i) {
        try {
            this.socket.setReceiveBufferSize(i);
            return true;
        } catch (Exception unused) {
            _log.debug(String.format(Locale.getDefault(), "Could not set socket receive buffer size to %d.", new Object[]{Integer.valueOf(i)}));
            return false;
        }
    }

    private boolean setSendBufferSize(int i) {
        try {
            this.socket.setSendBufferSize(i);
            return true;
        } catch (Exception unused) {
            _log.debug(String.format(Locale.getDefault(), "Could not set socket send buffer size to %d.", new Object[]{Integer.valueOf(i)}));
            return false;
        }
    }

    public boolean getIPv6() {
        return this._ipv6;
    }

    public boolean getIsClosed() {
        return this.isClosed;
    }

    public String getLocalIPAddress() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket != null) {
            try {
                this._LocalIPAddress = datagramSocket.getLocalAddress().getHostAddress();
            } catch (Exception unused) {
            }
        }
        return this._LocalIPAddress;
    }

    public int getLocalPort() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket != null) {
            try {
                this._LocalPort = datagramSocket.getLocalPort();
            } catch (Exception unused) {
            }
        }
        return this._LocalPort;
    }

    public void setMaxQueuedPackets(int i) {
        this.maxQueuedPackets = i;
    }

    public int getMaxQueuedPackets() {
        return this.maxQueuedPackets;
    }

    public static boolean getAttemptDscp() {
        return attemptDscp;
    }

    public static void setAttemptDscp(boolean z) {
        attemptDscp = z;
    }

    private static void tuneSocket(DatagramSocket datagramSocket) {
        if (attemptDscp) {
            try {
                datagramSocket.setTrafficClass(46);
            } catch (Exception e) {
                attemptDscp = false;
                _log.debug("Could not enable DSCP for UDP. Disabling for future sockets.", e);
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
            this.socket.bind(new InetSocketAddress(inetAddress, i));
            tuneSocket(this.socket);
            return true;
        } catch (BindException e) {
            booleanHolder.setValue(e.getMessage().contains("in use"));
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void receiveAsync(final IAction3<DataBuffer, String, Integer> iAction3, final IAction1<Exception> iAction1) {
        try {
            this.execIn.submit(new Runnable() {
                public void run() {
                    DataBuffer take = UdpSocket._dataBufferPool.take(2048);
                    try {
                        DatagramPacket datagramPacket = new DatagramPacket(take.getData(), take.getIndex(), take.getLength());
                        UdpSocket.this.socket.receive(datagramPacket);
                        UdpSocket.this.raiseReceiveSuccess(iAction3, take.subset(datagramPacket.getOffset() - take.getIndex(), datagramPacket.getLength()), datagramPacket.getAddress().getHostAddress(), datagramPacket.getPort());
                    } catch (Exception e) {
                        UdpSocket.this.raiseReceiveFailure(iAction1, new Exception(String.format("Socket (%s:%d) receive failed.", new Object[]{UdpSocket.this.getLocalIPAddress(), Integer.valueOf(UdpSocket.this.getLocalPort())}), e));
                    } catch (Throwable th) {
                        take.free();
                        throw th;
                    }
                    take.free();
                }
            });
        } catch (Exception e) {
            raiseReceiveFailure(iAction1, new Exception(String.format("Socket (%s:%d) receive failed.", new Object[]{getLocalIPAddress(), Integer.valueOf(getLocalPort())}), e));
        }
    }

    public Error send(DataBuffer dataBuffer, String str, int i) {
        InetAddress inetAddress;
        try {
            if (getIPv6()) {
                inetAddress = Inet6Address.getByName(str);
            } else {
                inetAddress = Inet4Address.getByName(str);
            }
            this.socket.send(new DatagramPacket(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength(), new InetSocketAddress(inetAddress, i)));
            return null;
        } catch (Exception unused) {
            if (getIPv6()) {
                return new Error(ErrorCode.SocketSendError, String.format("Error occurred on socket [%s]:%d.", new Object[]{getLocalIPAddress(), Integer.valueOf(getLocalPort())}));
            }
            return new Error(ErrorCode.SocketSendError, String.format("Error occurred on socket %s:%d.", new Object[]{getLocalIPAddress(), Integer.valueOf(getLocalPort())}));
        }
    }

    public void close() {
        synchronized (this.isClosedLock) {
            if (!this.isClosed) {
                this.isClosed = true;
                this.execIn.shutdownNow();
                this.execOut.shutdownNow();
                this.socket.close();
            }
        }
    }
}
