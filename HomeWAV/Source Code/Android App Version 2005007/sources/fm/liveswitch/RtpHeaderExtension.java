package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class RtpHeaderExtension implements IRtpHeaderExtension {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(RtpHeaderExtension.class);
    private HashMap<String, RtpHeaderExtensionElement> __headerExtensionElements;
    private byte[] __id;
    private Object __lock;
    private int _appBits;
    private RtpHeaderExtensionForm _form;

    public void fillBuffer(DataBuffer dataBuffer, int i) {
        fillBytes(this, dataBuffer, i, false);
    }

    private static void fillBytes(RtpHeaderExtension rtpHeaderExtension, DataBuffer dataBuffer, int i, boolean z) {
        int i2;
        if (z) {
            dataBuffer.writeBytes(rtpHeaderExtension.getId(), i);
            i2 = 4;
        } else {
            i2 = 0;
        }
        for (RtpHeaderExtensionElement fillBytes : rtpHeaderExtension.getHeaderExtensionElements()) {
            IntegerHolder integerHolder = new IntegerHolder(0);
            fillBytes.fillBytes(dataBuffer, i + i2, integerHolder);
            i2 += integerHolder.getValue();
        }
        if (z) {
            dataBuffer.write16((int) MathAssistant.ceil((double) (((i2 + 2) / 4) - 1)), i + 2);
        }
        int i3 = 4 - (i2 % 4);
        if (i3 == 4) {
            i3 = 0;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            dataBuffer.write8(0, i + i2);
            i2++;
        }
    }

    public RtpHeaderAbsSendTime getAbsSendTime() {
        return (RtpHeaderAbsSendTime) Global.tryCast(getHeaderExtension(RtpHeaderExtensionType.AbsSendTime), RtpHeaderAbsSendTime.class);
    }

    public int getAppBits() {
        return this._appBits;
    }

    /* access modifiers changed from: package-private */
    public DataBuffer getData() {
        DataBuffer take = __dataBufferPool.take(8);
        fillBytes(this, take, 0, true);
        return take;
    }

    public int getExtensionElementCount() {
        int count;
        synchronized (this.__lock) {
            count = HashMapExtensions.getCount(this.__headerExtensionElements);
        }
        return count;
    }

    public RtpHeaderExtensionForm getForm() {
        return this._form;
    }

    private RtpHeaderExtensionElement getHeaderExtension(RtpHeaderExtensionType rtpHeaderExtensionType) {
        RtpHeaderExtensionElement rtpHeaderExtensionElement;
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this.__headerExtensionElements, rtpHeaderExtensionType.toString(), holder);
            rtpHeaderExtensionElement = (RtpHeaderExtensionElement) holder.getValue();
        }
        return rtpHeaderExtensionElement;
    }

    public RtpHeaderExtensionElement[] getHeaderExtensionElements() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.__lock) {
            ArrayListExtensions.addRange(arrayList, HashMapExtensions.getValues(this.__headerExtensionElements));
        }
        return (RtpHeaderExtensionElement[]) arrayList.toArray(new RtpHeaderExtensionElement[0]);
    }

    public byte[] getId() {
        if (this.__id == null) {
            if (Global.equals(getForm(), RtpHeaderExtensionForm.OneByte)) {
                this.__id = Binary.toBytes16(48862, false);
            } else {
                this.__id = new byte[]{Tnaf.POW_2_WIDTH, (byte) getAppBits()};
            }
        }
        return this.__id;
    }

    public int getLength() {
        RtpHeaderExtensionElement[] headerExtensionElements = getHeaderExtensionElements();
        if (headerExtensionElements == null) {
            return 0;
        }
        int i = 0;
        for (RtpHeaderExtensionElement length : headerExtensionElements) {
            i += length.getLength();
        }
        return (int) MathAssistant.ceil((double) (((float) i) / 4.0f));
    }

    public RtpHeaderSdesMid getSdesMid() {
        return (RtpHeaderSdesMid) Global.tryCast(getHeaderExtension(RtpHeaderExtensionType.SdesMid), RtpHeaderSdesMid.class);
    }

    public RtpHeaderSdesRepairedRtpStreamId getSdesRepairedRtpStreamId() {
        return (RtpHeaderSdesRepairedRtpStreamId) Global.tryCast(getHeaderExtension(RtpHeaderExtensionType.SdesRepairedRtpStreamId), RtpHeaderSdesRepairedRtpStreamId.class);
    }

    public RtpHeaderSdesRtpStreamId getSdesRtpStreamId() {
        return (RtpHeaderSdesRtpStreamId) Global.tryCast(getHeaderExtension(RtpHeaderExtensionType.SdesRtpStreamId), RtpHeaderSdesRtpStreamId.class);
    }

    static RtpHeaderExtension parseBytes(DataBuffer dataBuffer, RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        int i = 0;
        int read8 = dataBuffer.read8(0);
        RtpHeaderExtensionForm rtpHeaderExtensionForm = RtpHeaderExtensionForm.OneByte;
        if (read8 == 16) {
            rtpHeaderExtensionForm = RtpHeaderExtensionForm.TwoByte;
            i = dataBuffer.read8(1);
        }
        return parsePayload(dataBuffer.subset(4, dataBuffer.read16(2) * 4), rtpHeaderExtensionRegistry, rtpHeaderExtensionForm, i);
    }

    static RtpHeaderExtension parsePayload(DataBuffer dataBuffer, RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry, RtpHeaderExtensionForm rtpHeaderExtensionForm, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < dataBuffer.getLength()) {
            IntegerHolder integerHolder = new IntegerHolder(0);
            RtpHeaderExtensionElement parseBytes = RtpHeaderExtensionElement.parseBytes(dataBuffer, i2, rtpHeaderExtensionForm, rtpHeaderExtensionRegistry, integerHolder);
            int value = integerHolder.getValue();
            if (parseBytes != null) {
                arrayList.add(parseBytes);
            }
            i2 += value;
        }
        return new RtpHeaderExtension(rtpHeaderExtensionForm, (RtpHeaderExtensionElement[]) arrayList.toArray(new RtpHeaderExtensionElement[0]), i);
    }

    static RtpHeaderExtension parseRawHeaderExtension(RtpRawHeaderExtension rtpRawHeaderExtension, RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        return parsePayload(DataBuffer.wrap(rtpRawHeaderExtension.getPayload()), rtpHeaderExtensionRegistry, rtpRawHeaderExtension.getForm(), rtpRawHeaderExtension.getAppBits());
    }

    public RtpHeaderExtension(RtpHeaderExtensionForm rtpHeaderExtensionForm, RtpHeaderExtensionElement[] rtpHeaderExtensionElementArr) {
        this(rtpHeaderExtensionForm, rtpHeaderExtensionElementArr, 0);
    }

    public RtpHeaderExtension(RtpHeaderExtensionForm rtpHeaderExtensionForm, RtpHeaderExtensionElement[] rtpHeaderExtensionElementArr, int i) {
        this.__headerExtensionElements = new HashMap<>();
        this.__lock = new Object();
        setForm(rtpHeaderExtensionForm);
        for (RtpHeaderExtensionElement rtpHeaderExtensionElement : rtpHeaderExtensionElementArr) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__headerExtensionElements), rtpHeaderExtensionElement.getType().toString(), rtpHeaderExtensionElement);
        }
        setAppBits(i);
    }

    public void setAbsSendTime(RtpHeaderAbsSendTime rtpHeaderAbsSendTime) {
        setHeaderExtension(RtpHeaderExtensionType.AbsSendTime, rtpHeaderAbsSendTime);
    }

    private void setAppBits(int i) {
        this._appBits = i;
    }

    private void setForm(RtpHeaderExtensionForm rtpHeaderExtensionForm) {
        this._form = rtpHeaderExtensionForm;
    }

    private void setHeaderExtension(RtpHeaderExtensionType rtpHeaderExtensionType, RtpHeaderExtensionElement rtpHeaderExtensionElement) {
        if (rtpHeaderExtensionElement == null) {
            synchronized (this.__lock) {
                HashMapExtensions.remove(this.__headerExtensionElements, rtpHeaderExtensionType.toString());
            }
            return;
        }
        synchronized (this.__lock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__headerExtensionElements), rtpHeaderExtensionType.toString(), rtpHeaderExtensionElement);
        }
    }

    public void setSdesMid(RtpHeaderSdesMid rtpHeaderSdesMid) {
        setHeaderExtension(RtpHeaderExtensionType.SdesMid, rtpHeaderSdesMid);
    }

    public void setSdesRepairedRtpStreamId(RtpHeaderSdesRepairedRtpStreamId rtpHeaderSdesRepairedRtpStreamId) {
        setHeaderExtension(RtpHeaderExtensionType.SdesRepairedRtpStreamId, rtpHeaderSdesRepairedRtpStreamId);
    }

    public void setSdesRtpStreamId(RtpHeaderSdesRtpStreamId rtpHeaderSdesRtpStreamId) {
        setHeaderExtension(RtpHeaderExtensionType.SdesRtpStreamId, rtpHeaderSdesRtpStreamId);
    }
}
