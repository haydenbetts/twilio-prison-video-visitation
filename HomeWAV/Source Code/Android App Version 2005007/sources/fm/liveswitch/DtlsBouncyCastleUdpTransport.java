package fm.liveswitch;

import java.util.Iterator;
import java.util.Vector;
import org.bouncycastle.crypto.tls.DatagramTransport;

class DtlsBouncyCastleUdpTransport implements DatagramTransport {
    private static int MAX_IP_OVERHEAD = (20 + 64);
    private static int MIN_IP_OVERHEAD = 20;
    private static int UDP_OVERHEAD = 8;
    private int receiveLimit;
    private ManagedCondition receiveLock;
    private Vector<DataBuffer> receivedPackets = new Vector<>();
    private Object receivedPacketsLock = new Object();
    private IAction1<byte[]> sendCallback;
    private int sendLimit;

    public DtlsBouncyCastleUdpTransport(IAction1<byte[]> iAction1) {
        int i = UDP_OVERHEAD;
        this.receiveLimit = (1500 - MIN_IP_OVERHEAD) - i;
        this.sendLimit = (1500 - MAX_IP_OVERHEAD) - i;
        this.sendCallback = iAction1;
        this.receiveLock = new ManagedCondition();
    }

    public int getReceiveLimit() {
        return this.receiveLimit;
    }

    public int getSendLimit() {
        return this.sendLimit;
    }

    public void push(DataBuffer dataBuffer) {
        synchronized (this.receivedPacketsLock) {
            this.receivedPackets.add(dataBuffer);
            dataBuffer.keep();
        }
        synchronized (this.receiveLock) {
            this.receiveLock.notify();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0045, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int pull(byte[] r6, int r7, int r8) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.receivedPacketsLock
            monitor-enter(r0)
            java.util.Vector<fm.liveswitch.DataBuffer> r1 = r5.receivedPackets     // Catch:{ all -> 0x0049 }
            int r1 = r1.size()     // Catch:{ all -> 0x0049 }
            if (r1 <= 0) goto L_0x0046
            java.util.Vector<fm.liveswitch.DataBuffer> r1 = r5.receivedPackets     // Catch:{ all -> 0x0049 }
            r2 = 0
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0049 }
            fm.liveswitch.DataBuffer r1 = (fm.liveswitch.DataBuffer) r1     // Catch:{ all -> 0x0049 }
            int r3 = r1.getLength()     // Catch:{ all -> 0x0049 }
            int r8 = fm.liveswitch.MathAssistant.min((int) r8, (int) r3)     // Catch:{ all -> 0x0049 }
            byte[] r3 = r1.getData()     // Catch:{ all -> 0x0049 }
            int r4 = r1.getIndex()     // Catch:{ all -> 0x0049 }
            fm.liveswitch.BitAssistant.copy(r3, r4, r6, r7, r8)     // Catch:{ all -> 0x0049 }
            int r6 = r1.getLength()     // Catch:{ all -> 0x0049 }
            if (r8 != r6) goto L_0x0036
            java.util.Vector<fm.liveswitch.DataBuffer> r6 = r5.receivedPackets     // Catch:{ all -> 0x0049 }
            r6.remove(r2)     // Catch:{ all -> 0x0049 }
            r1.free()     // Catch:{ all -> 0x0049 }
            goto L_0x0044
        L_0x0036:
            java.util.Vector<fm.liveswitch.DataBuffer> r6 = r5.receivedPackets     // Catch:{ all -> 0x0049 }
            int r7 = r1.getLength()     // Catch:{ all -> 0x0049 }
            int r7 = r7 - r8
            fm.liveswitch.DataBuffer r7 = r1.subset(r8, r7)     // Catch:{ all -> 0x0049 }
            r6.set(r2, r7)     // Catch:{ all -> 0x0049 }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            return r8
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            r6 = -1
            return r6
        L_0x0049:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.DtlsBouncyCastleUdpTransport.pull(byte[], int, int):int");
    }

    public int receive(byte[] bArr, int i, int i2, int i3) {
        synchronized (this.receiveLock) {
            int pull = pull(bArr, i, i2);
            if (pull != -1) {
                return pull;
            }
            this.receiveLock.halt(i3);
            int pull2 = pull(bArr, i, i2);
            if (pull2 != -1) {
                return pull2;
            }
            return -1;
        }
    }

    public void send(byte[] bArr, int i, int i2) {
        IAction1<byte[]> iAction1 = this.sendCallback;
        if (iAction1 != null) {
            iAction1.invoke(BitAssistant.subArray(bArr, i, i2));
        }
    }

    public void close() {
        synchronized (this.receivedPacketsLock) {
            Iterator<DataBuffer> it = this.receivedPackets.iterator();
            while (it.hasNext()) {
                it.next().free();
            }
            this.receivedPackets.clear();
        }
    }
}
