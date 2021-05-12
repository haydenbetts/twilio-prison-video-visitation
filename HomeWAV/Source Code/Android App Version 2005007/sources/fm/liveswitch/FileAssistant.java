package fm.liveswitch;

public class FileAssistant {
    private FileStream __file;
    private boolean _littleEndian;

    public FileAssistant(FileStream fileStream) {
        this.__file = fileStream;
    }

    public FileStream getFile() {
        return this.__file;
    }

    public boolean getLittleEndian() {
        return this._littleEndian;
    }

    private byte[] getValue(int i, int i2) {
        int i3;
        byte[] bArr = new byte[i];
        for (int i4 = 0; i4 < i; i4++) {
            bArr[i4] = 0;
        }
        if (getLittleEndian()) {
            i3 = getFile().read(bArr, 0, i2);
        } else {
            i3 = getFile().read(bArr, i - i2, i2);
        }
        if (i3 == i2) {
            return bArr;
        }
        throw new RuntimeException(new Exception("Cannot read value beyond end of File."));
    }

    private byte[] getValue32(int i) {
        return getValue(4, i);
    }

    private byte[] getValue64(int i) {
        return getValue(8, i);
    }

    public byte[] read(int i) {
        byte[] bArr = new byte[i];
        int read = getFile().read(bArr, 0, i);
        return read != i ? BitAssistant.subArray(bArr, 0, read) : bArr;
    }

    public int read16() {
        return Binary.fromBytes16(getValue32(2), 0, getLittleEndian());
    }

    public int read24() {
        return Binary.fromBytes24(getValue32(3), 0, getLittleEndian());
    }

    public long read32() {
        return Binary.fromBytes32(getValue64(4), 0, getLittleEndian());
    }

    public long read40() {
        return Binary.fromBytes40(getValue64(5), 0, getLittleEndian());
    }

    public long read48() {
        return Binary.fromBytes48(getValue64(6), 0, getLittleEndian());
    }

    public long read56() {
        return Binary.fromBytes56(getValue64(7), 0, getLittleEndian());
    }

    public long read64() {
        return Binary.fromBytes64(getValue64(8), 0, getLittleEndian());
    }

    public int read8() {
        byte[] bArr = new byte[1];
        getFile().read(bArr, 0, 1);
        return bArr[0];
    }

    public static byte[] readAllBytes(String str) {
        FileStream fileStream = new FileStream(str);
        byte[] bArr = null;
        try {
            fileStream.open(FileStreamAccess.Read);
            bArr = new byte[((int) fileStream.getLength())];
            fileStream.read(bArr, 0, ArrayExtensions.getLength(bArr));
            try {
                fileStream.close();
            } catch (Exception e) {
                Log.error(StringExtensions.concat("Could not close file: ", str), e);
            }
        } catch (Exception e2) {
            Log.error(StringExtensions.concat("Could not read file: ", str), e2);
            fileStream.close();
        } catch (Throwable th) {
            try {
                fileStream.close();
            } catch (Exception e3) {
                Log.error(StringExtensions.concat("Could not close file: ", str), e3);
            }
            throw th;
        }
        return bArr;
    }

    public byte[] readOpaque16() {
        int read16 = read16();
        if (read16 < 0) {
            return null;
        }
        return read(read16);
    }

    public byte[] readOpaque24() {
        int read24 = read24();
        if (read24 < 0) {
            return null;
        }
        return read(read24);
    }

    public byte[] readOpaque32() {
        int read32 = (int) read32();
        if (read32 < 0) {
            return null;
        }
        return read(read32);
    }

    public byte[] readOpaque8() {
        int read8 = read8();
        if (read8 < 0) {
            return null;
        }
        return read(read8);
    }

    public void setLittleEndian(boolean z) {
        this._littleEndian = z;
    }

    public void write(byte[] bArr) {
        write(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public void write(byte[] bArr, int i, int i2) {
        getFile().write(bArr, i, i2);
    }

    public void write16(int i) {
        write(Binary.toBytes16(i, getLittleEndian()));
    }

    public void write16To(int i, int i2) {
        writeTo(i, Binary.toBytes16(i2, getLittleEndian()));
    }

    public void write24(int i) {
        write(Binary.toBytes24(i, getLittleEndian()));
    }

    public void write24To(int i, int i2) {
        writeTo(i, Binary.toBytes24(i2, getLittleEndian()));
    }

    public void write32(long j) {
        write(Binary.toBytes32(j, getLittleEndian()));
    }

    public void write32To(int i, long j) {
        writeTo(i, Binary.toBytes32(j, getLittleEndian()));
    }

    public void write40(long j) {
        write(Binary.toBytes40(j, getLittleEndian()));
    }

    public void write40To(int i, long j) {
        writeTo(i, Binary.toBytes40(j, getLittleEndian()));
    }

    public void write48(long j) {
        write(Binary.toBytes48(j, getLittleEndian()));
    }

    public void write48To(int i, long j) {
        writeTo(i, Binary.toBytes48(j, getLittleEndian()));
    }

    public void write56(long j) {
        write(Binary.toBytes56(j, getLittleEndian()));
    }

    public void write56To(int i, long j) {
        writeTo(i, Binary.toBytes56(j, getLittleEndian()));
    }

    public void write64(long j) {
        write(Binary.toBytes64(j, getLittleEndian()));
    }

    public void write64To(int i, long j) {
        writeTo(i, Binary.toBytes64(j, getLittleEndian()));
    }

    public void write8(int i) {
        write(new byte[]{(byte) i});
    }

    public void write8To(int i, int i2) {
        writeTo(i, new byte[]{(byte) i2});
    }

    public void writeOpaque16(byte[] bArr) {
        write16(ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque16To(int i, byte[] bArr) {
        write16To(i, ArrayExtensions.getLength(bArr));
        writeTo(i + 2, bArr);
    }

    public void writeOpaque24(byte[] bArr) {
        write24(ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque24To(int i, byte[] bArr) {
        write24To(i, ArrayExtensions.getLength(bArr));
        writeTo(i + 3, bArr);
    }

    public void writeOpaque32(byte[] bArr) {
        write32((long) ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque32To(int i, byte[] bArr) {
        write32To(i, (long) ArrayExtensions.getLength(bArr));
        writeTo(i + 4, bArr);
    }

    public void writeOpaque40(byte[] bArr) {
        write40((long) ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque40To(int i, byte[] bArr) {
        write40To(i, (long) ArrayExtensions.getLength(bArr));
        writeTo(i + 5, bArr);
    }

    public void writeOpaque48(byte[] bArr) {
        write48((long) ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque48To(int i, byte[] bArr) {
        write48To(i, (long) ArrayExtensions.getLength(bArr));
        writeTo(i + 6, bArr);
    }

    public void writeOpaque56(byte[] bArr) {
        write56((long) ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque56To(int i, byte[] bArr) {
        write56To(i, (long) ArrayExtensions.getLength(bArr));
        writeTo(i + 7, bArr);
    }

    public void writeOpaque64(byte[] bArr) {
        write64((long) ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque64To(int i, byte[] bArr) {
        write64To(i, (long) ArrayExtensions.getLength(bArr));
        writeTo(i + 8, bArr);
    }

    public void writeOpaque8(byte[] bArr) {
        write8(ArrayExtensions.getLength(bArr));
        write(bArr);
    }

    public void writeOpaque8To(int i, byte[] bArr) {
        write8To(i, ArrayExtensions.getLength(bArr));
        writeTo(i + 1, bArr);
    }

    public void writeTo(int i, byte[] bArr) {
        getFile().writeTo(i, bArr, 0, ArrayExtensions.getLength(bArr));
    }
}
