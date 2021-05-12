package fm.liveswitch;

abstract class RtpHeaderDataDescription extends RtpHeaderExtensionElement {
    private byte[] __data;

    /* access modifiers changed from: package-private */
    public void fillBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        byte[] data = getData();
        if (Global.equals(super.getForm(), RtpHeaderExtensionForm.OneByte)) {
            dataBuffer.write4(super.getId(), i, 0);
            dataBuffer.write4(ArrayExtensions.getLength(data) - 1, i, 4);
            dataBuffer.writeBytes(data, i + 1);
            integerHolder.setValue(ArrayExtensions.getLength(data) + 1);
            return;
        }
        int length = ArrayExtensions.getLength(data);
        dataBuffer.write8(super.getId(), i);
        int i2 = i + 1;
        dataBuffer.write8(length, i2);
        dataBuffer.writeBytes(data, i2 + 1);
        integerHolder.setValue(ArrayExtensions.getLength(data) + 2);
    }

    public byte[] getData() {
        return this.__data;
    }

    public int getLength() {
        int length = ArrayExtensions.getLength(getData());
        if (Global.equals(super.getForm(), RtpHeaderExtensionForm.OneByte)) {
            return length + 1;
        }
        if (Global.equals(super.getForm(), RtpHeaderExtensionForm.TwoByte)) {
            return length + 2;
        }
        throw new RuntimeException(new Exception("Unrecognized header extension form."));
    }

    protected static void parseBytes(DataBuffer dataBuffer, int i, RtpHeaderExtensionForm rtpHeaderExtensionForm, IntegerHolder integerHolder, IntegerHolder integerHolder2, Holder<byte[]> holder) {
        if (Global.equals(rtpHeaderExtensionForm, RtpHeaderExtensionForm.OneByte)) {
            integerHolder2.setValue(dataBuffer.read4(i, 0));
            int read4 = dataBuffer.read4(i, 4) + 1;
            holder.setValue(dataBuffer.subset(i + 1, read4).toArray());
            integerHolder.setValue(read4 + 1);
        } else if (Global.equals(rtpHeaderExtensionForm, RtpHeaderExtensionForm.TwoByte)) {
            integerHolder2.setValue(dataBuffer.read8(i));
            int i2 = i + 1;
            int read8 = dataBuffer.read8(i2);
            holder.setValue(dataBuffer.subset(i2 + 1, read8).toArray());
            integerHolder.setValue(read8 + 2);
        } else {
            throw new RuntimeException(new Exception("Unrecognized header extension form."));
        }
    }

    private RtpHeaderDataDescription(RtpHeaderExtensionType rtpHeaderExtensionType, RtpHeaderExtensionForm rtpHeaderExtensionForm, int i) {
        super.setId(i);
        super.setType(rtpHeaderExtensionType);
        super.setForm(rtpHeaderExtensionForm);
    }

    public RtpHeaderDataDescription(RtpHeaderExtensionType rtpHeaderExtensionType, RtpHeaderExtensionForm rtpHeaderExtensionForm, int i, byte[] bArr) {
        this(rtpHeaderExtensionType, rtpHeaderExtensionForm, i);
        if (bArr != null) {
            this.__data = bArr;
            verify();
            return;
        }
        throw new RuntimeException(new Exception("Data cannot be null."));
    }

    private void verify() {
        int length = ArrayExtensions.getLength(getData());
        if (Global.equals(super.getForm(), RtpHeaderExtensionForm.OneByte)) {
            if (length < 1) {
                throw new RuntimeException(new Exception(StringExtensions.format("Data length ({0}) is too short for one-byte header extension form (minimum 1 byte).", (Object) IntegerExtensions.toString(Integer.valueOf(length)))));
            } else if (length > 16) {
                throw new RuntimeException(new Exception(StringExtensions.format("Data length ({0}) is too long for one-byte header extension form (maximum 16 bytes).", (Object) IntegerExtensions.toString(Integer.valueOf(length)))));
            }
        } else if (!Global.equals(super.getForm(), RtpHeaderExtensionForm.TwoByte)) {
            throw new RuntimeException(new Exception("Unrecognized header extension form."));
        } else if (length > 255) {
            throw new RuntimeException(new Exception(StringExtensions.format("Data length ({0}) is too long for two-byte header extension form (maximum 255 bytes).", (Object) IntegerExtensions.toString(Integer.valueOf(length)))));
        }
    }
}
