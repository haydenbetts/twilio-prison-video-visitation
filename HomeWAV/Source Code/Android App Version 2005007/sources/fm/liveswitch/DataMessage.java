package fm.liveswitch;

import java.util.ArrayList;

class DataMessage {
    static final int IntUnset = -1;
    private static IDataBufferPool __dataBufferPool;
    private static ILog __log;
    private ArrayList<DatamessageheaderElement> __elements = new ArrayList<>();
    private DataBuffer _dataBytes;
    private String _dataString;

    /* access modifiers changed from: package-private */
    public int getVersion() {
        return 1;
    }

    static {
        Class<DataMessage> cls = DataMessage.class;
        __dataBufferPool = DataBufferPool.getTracer((Class) cls);
        __log = Log.getLogger((Class) cls);
    }

    public DataMessage(DataBuffer dataBuffer) {
        setDataBytes(dataBuffer);
    }

    public DataMessage(String str) {
        setDataString(str);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0033 A[LOOP:0: B:9:0x0029->B:11:0x0033, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f A[LOOP:1: B:23:0x0075->B:25:0x007f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.DataBuffer getBytes() {
        /*
            r8 = this;
            fm.liveswitch.DataBuffer r0 = r8.getDataBytes()
            r1 = 4
            r2 = 0
            if (r0 == 0) goto L_0x0012
            fm.liveswitch.DataBuffer r0 = r8.getDataBytes()
            int r0 = r0.getLength()
        L_0x0010:
            int r0 = r0 + r1
            goto L_0x0026
        L_0x0012:
            java.lang.String r0 = r8.getDataString()
            if (r0 == 0) goto L_0x0025
            java.lang.String r0 = r8.getDataString()
            byte[] r2 = fm.liveswitch.Utf8.encode(r0)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r2)
            goto L_0x0010
        L_0x0025:
            r0 = 4
        L_0x0026:
            r3 = 0
            r4 = 0
            r5 = 4
        L_0x0029:
            fm.liveswitch.DatamessageheaderElement[] r6 = r8.getElements()
            int r6 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r6)
            if (r4 >= r6) goto L_0x0046
            fm.liveswitch.DatamessageheaderElement[] r6 = r8.getElements()
            r6 = r6[r4]
            int r7 = r6.getLength()
            int r0 = r0 + r7
            int r6 = r6.getLength()
            int r5 = r5 + r6
            int r4 = r4 + 1
            goto L_0x0029
        L_0x0046:
            boolean r4 = fm.liveswitch.DataBufferPool.getIsSupported()
            if (r4 == 0) goto L_0x0053
            fm.liveswitch.IDataBufferPool r4 = __dataBufferPool
            fm.liveswitch.DataBuffer r0 = r4.take(r0)
            goto L_0x0057
        L_0x0053:
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.allocate(r0)
        L_0x0057:
            r4 = 1
            r0.write4(r4, r3, r3)
            java.lang.String r6 = r8.getDataString()
            if (r6 == 0) goto L_0x0065
            r0.write4(r4, r3, r1)
            goto L_0x006e
        L_0x0065:
            fm.liveswitch.DataBuffer r6 = r8.getDataBytes()
            if (r6 == 0) goto L_0x006e
            r0.write4(r3, r3, r1)
        L_0x006e:
            r0.write8(r3, r4)
            r4 = 2
            r0.write16(r5, r4)
        L_0x0075:
            fm.liveswitch.DatamessageheaderElement[] r4 = r8.getElements()
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)
            if (r3 >= r4) goto L_0x0094
            fm.liveswitch.DatamessageheaderElement[] r4 = r8.getElements()
            r4 = r4[r3]
            int r5 = r4.getLength()
            fm.liveswitch.DataBuffer r4 = r4.getBytes()
            r0.write(r4, r1)
            int r1 = r1 + r5
            int r3 = r3 + 1
            goto L_0x0075
        L_0x0094:
            if (r2 == 0) goto L_0x009a
            r0.writeBytes(r2, r1)
            return r0
        L_0x009a:
            fm.liveswitch.DataBuffer r2 = r8.getDataBytes()
            if (r2 == 0) goto L_0x00a7
            fm.liveswitch.DataBuffer r2 = r8.getDataBytes()
            r0.write(r2, r1)
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.DataMessage.getBytes():fm.liveswitch.DataBuffer");
    }

    public DataBuffer getDataBytes() {
        return this._dataBytes;
    }

    public String getDataString() {
        return this._dataString;
    }

    /* access modifiers changed from: package-private */
    public DatamessageheaderElement[] getElements() {
        return (DatamessageheaderElement[]) this.__elements.toArray(new DatamessageheaderElement[0]);
    }

    /* access modifiers changed from: package-private */
    public int getNumberOfRetransmissions() {
        for (DatamessageheaderElement datamessageheaderElement : getElements()) {
            if (datamessageheaderElement.getType() == DatamessageheaderType.getDeliveryAttempts()) {
                return ((DatamessageheaderDeliveryAttemptsElement) datamessageheaderElement).getDeliveryAttempts();
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public String getRemoteConnectionId() {
        for (DatamessageheaderElement datamessageheaderElement : getElements()) {
            if (datamessageheaderElement.getType() == DatamessageheaderType.getConnectionId()) {
                return ((DatamessageheaderConnectionIdElement) datamessageheaderElement).getConnectionId();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public long getTimeToLive() {
        for (DatamessageheaderElement datamessageheaderElement : getElements()) {
            if (datamessageheaderElement.getType() == DatamessageheaderType.getTimeToLive()) {
                return ((DatamessageheaderTimeToLiveElement) datamessageheaderElement).getTimeToLive();
            }
        }
        return -1;
    }

    static DataMessage parseBytes(DataBuffer dataBuffer) {
        ArrayList arrayList;
        DataMessage dataMessage;
        int read4 = dataBuffer.read4(0, 0);
        if (read4 != 1) {
            __log.error(StringExtensions.format("Cannot parse Liveswitch Data Message of version {0}", (Object) IntegerExtensions.toString(Integer.valueOf(read4))));
            return null;
        }
        int read42 = dataBuffer.read4(0, 4);
        int read16 = dataBuffer.read16(2);
        if (read16 > 0) {
            arrayList = new ArrayList();
            int i = -1;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 4;
                if (i3 >= read16) {
                    break;
                }
                IntegerHolder integerHolder = new IntegerHolder(i);
                DatamessageheaderElement parseBytes = DatamessageheaderElement.parseBytes(dataBuffer, i3, integerHolder);
                int value = integerHolder.getValue();
                i2 += value;
                int i4 = i2 + 4;
                if (i4 > read16) {
                    __log.error(StringExtensions.format("Could not parse Data Message. Header length declared to have {0} bytes, while {1} bytes of header length were read.", IntegerExtensions.toString(Integer.valueOf(read16)), IntegerExtensions.toString(Integer.valueOf(i4))));
                    return null;
                }
                if (parseBytes != null) {
                    arrayList.add(parseBytes);
                }
                i = value;
            }
        } else {
            arrayList = null;
        }
        if (read42 == 0) {
            dataMessage = new DataMessage(dataBuffer.subset(read16));
        } else if (read42 == 1) {
            dataMessage = new DataMessage(dataBuffer.readUtf8String(read16));
        } else {
            Log.error(StringExtensions.format("Received a data message with an unknown payload {0}. Will not parse.", (Object) IntegerExtensions.toString(Integer.valueOf(read42))));
            return null;
        }
        if (arrayList != null) {
            dataMessage.setElements((DatamessageheaderElement[]) arrayList.toArray(new DatamessageheaderElement[0]));
        }
        return dataMessage;
    }

    private void setDataBytes(DataBuffer dataBuffer) {
        this._dataBytes = dataBuffer;
    }

    private void setDataString(String str) {
        this._dataString = str;
    }

    /* access modifiers changed from: package-private */
    public void setElements(DatamessageheaderElement[] datamessageheaderElementArr) {
        if (datamessageheaderElementArr != null) {
            ArrayListExtensions.addRange(this.__elements, (T[]) datamessageheaderElementArr);
        }
    }

    /* access modifiers changed from: package-private */
    public void setNumberOfRetransmissions(int i) {
        for (DatamessageheaderElement datamessageheaderElement : getElements()) {
            if (datamessageheaderElement.getType() == DatamessageheaderType.getDeliveryAttempts()) {
                this.__elements.remove(datamessageheaderElement);
            }
        }
        this.__elements.add(new DatamessageheaderDeliveryAttemptsElement(i));
    }

    /* access modifiers changed from: package-private */
    public void setRemoteConnectionId(String str) {
        for (DatamessageheaderElement datamessageheaderElement : getElements()) {
            if (datamessageheaderElement.getType() == DatamessageheaderType.getConnectionId()) {
                this.__elements.remove(datamessageheaderElement);
            }
        }
        this.__elements.add(new DatamessageheaderConnectionIdElement(str));
    }

    /* access modifiers changed from: package-private */
    public void setTimeToLive(long j) {
        for (DatamessageheaderElement datamessageheaderElement : getElements()) {
            if (datamessageheaderElement.getType() == DatamessageheaderType.getTimeToLive()) {
                this.__elements.remove(datamessageheaderElement);
            }
        }
        this.__elements.add(new DatamessageheaderTimeToLiveElement(j));
    }
}
