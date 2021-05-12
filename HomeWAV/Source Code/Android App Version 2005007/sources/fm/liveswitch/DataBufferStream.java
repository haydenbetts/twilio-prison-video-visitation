package fm.liveswitch;

public class DataBufferStream {
    private byte __bitPosition;
    private DataBuffer __buffer;
    private int __position;

    public DataBufferStream(DataBuffer dataBuffer) {
        this.__buffer = dataBuffer;
    }

    public DataBufferStream(int i) {
        this(i, false);
    }

    public DataBufferStream(int i, boolean z) {
        this(DataBuffer.allocate(i, z));
    }

    public int getAvailable() {
        return getLength() - this.__position;
    }

    public byte getBitPosition() {
        return this.__bitPosition;
    }

    public DataBuffer getBuffer() {
        return this.__buffer;
    }

    public int getLength() {
        return this.__buffer.getLength();
    }

    public int getPosition() {
        return this.__position;
    }

    public void nextByte() {
        this.__bitPosition = 0;
        this.__position++;
    }

    public int peek() {
        return this.__buffer.read8(getPosition());
    }

    public DataBuffer read(int i) {
        DataBuffer subset = this.__buffer.subset(this.__position, i);
        this.__position += i;
        return subset;
    }

    public boolean read1() {
        boolean read1 = this.__buffer.read1(this.__position, this.__bitPosition);
        byte b = (byte) (this.__bitPosition + 1);
        this.__bitPosition = b;
        if (b == 8) {
            this.__position++;
            this.__bitPosition = 0;
        }
        return read1;
    }

    public int read15() {
        int read15 = this.__buffer.read15(this.__position, this.__bitPosition);
        int i = this.__position + 1;
        this.__position = i;
        byte b = (byte) (this.__bitPosition + 7);
        this.__bitPosition = b;
        if (b >= 8) {
            this.__position = i + 1;
            this.__bitPosition = (byte) (b - 8);
        }
        return read15;
    }

    public int read16() {
        int read16 = this.__buffer.read16(this.__position);
        this.__position += 2;
        return read16;
    }

    public int read2() {
        int read2 = this.__buffer.read2(this.__position, this.__bitPosition);
        byte b = (byte) (this.__bitPosition + 2);
        this.__bitPosition = b;
        if (b >= 8) {
            this.__position++;
            this.__bitPosition = (byte) (b - 8);
        }
        return read2;
    }

    public int read24() {
        int read24 = this.__buffer.read24(this.__position);
        this.__position += 3;
        return read24;
    }

    public int read3() {
        int read3 = this.__buffer.read3(this.__position, this.__bitPosition);
        byte b = (byte) (this.__bitPosition + 3);
        this.__bitPosition = b;
        if (b >= 8) {
            this.__position++;
            this.__bitPosition = (byte) (b - 8);
        }
        return read3;
    }

    public long read32() {
        long read32 = this.__buffer.read32(this.__position);
        this.__position += 4;
        return read32;
    }

    public int read4() {
        int read4 = this.__buffer.read4(this.__position, this.__bitPosition);
        byte b = (byte) (this.__bitPosition + 4);
        this.__bitPosition = b;
        if (b >= 8) {
            this.__position++;
            this.__bitPosition = (byte) (b - 8);
        }
        return read4;
    }

    public long read40() {
        long read40 = this.__buffer.read40(this.__position);
        this.__position += 5;
        return read40;
    }

    public long read48() {
        long read48 = this.__buffer.read48(this.__position);
        this.__position += 6;
        return read48;
    }

    public long read56() {
        long read56 = this.__buffer.read56(this.__position);
        this.__position += 7;
        return read56;
    }

    public long read64() {
        long read64 = this.__buffer.read64(this.__position);
        this.__position += 8;
        return read64;
    }

    public int read7() {
        int read7 = this.__buffer.read7(this.__position, this.__bitPosition);
        byte b = (byte) (this.__bitPosition + 7);
        this.__bitPosition = b;
        if (b >= 8) {
            this.__position++;
            this.__bitPosition = (byte) (b - 8);
        }
        return read7;
    }

    public int read8() {
        int read8 = this.__buffer.read8(this.__position);
        this.__position++;
        return read8;
    }

    public int readByte() {
        return read8();
    }

    public byte[] readBytes(int i) {
        byte[] array = this.__buffer.subset(this.__position, i).toArray();
        this.__position += i;
        return array;
    }

    public void setBitPosition(byte b) {
        this.__bitPosition = b;
    }

    public void setPosition(int i) {
        this.__position = i;
    }

    public DataBufferStream write(DataBuffer dataBuffer) {
        this.__buffer.write(dataBuffer, this.__position);
        this.__position += dataBuffer.getLength();
        return this;
    }

    public DataBufferStream write16(int i) {
        this.__buffer.write16(i, this.__position);
        this.__position += 2;
        return this;
    }

    public DataBufferStream write32(long j) {
        this.__buffer.write32(j, this.__position);
        this.__position += 4;
        return this;
    }

    public DataBufferStream write64(long j) {
        this.__buffer.write64(j, this.__position);
        this.__position += 8;
        return this;
    }

    public DataBufferStream write8(int i) {
        this.__buffer.write8(i, this.__position);
        this.__position++;
        return this;
    }

    public DataBufferStream writeBytes(byte[] bArr) {
        return writeBytes(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public DataBufferStream writeBytes(byte[] bArr, int i, int i2) {
        if (getAvailable() < i2) {
            getBuffer().resize((getBuffer().getLength() - getAvailable()) + i2);
        }
        this.__buffer.writeBytes(bArr, i, i2, this.__position);
        this.__position += i2;
        return this;
    }
}
