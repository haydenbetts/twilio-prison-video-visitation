package fm.liveswitch;

class AesCounter {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(AesCounter.class);
    private AesCounterContext _context;
    private byte[] _counterData;
    private DataBuffer _key;
    private byte[] _offset;
    private DataBuffer _output;
    private byte[] _packetIndexBytes;
    private DataBuffer _salt;
    private byte[] _ssrcBytes;

    public AesCounter(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        this(dataBuffer, dataBuffer2, 65536);
    }

    public AesCounter(DataBuffer dataBuffer, DataBuffer dataBuffer2, int i) {
        if (dataBuffer.getLength() != 16) {
            throw new RuntimeException(new Exception("Invalid key length."));
        } else if (dataBuffer2.getLength() == 14) {
            this._key = dataBuffer;
            this._salt = dataBuffer2;
            this._context = new AesCounterContext(dataBuffer);
            this._offset = new byte[16];
            this._ssrcBytes = new byte[4];
            this._packetIndexBytes = new byte[6];
            this._output = DataBuffer.allocate(i);
            for (int i2 = 0; i2 < ArrayExtensions.getLength(this._offset); i2++) {
                this._offset[i2] = 0;
            }
            for (int i3 = 0; i3 < this._salt.getLength(); i3++) {
                this._offset[i3] = (byte) this._salt.read8(i3);
            }
        } else {
            throw new RuntimeException(new Exception("Invalid salt length."));
        }
    }

    public void clear() {
        AesCounterContext aesCounterContext = this._context;
        if (aesCounterContext != null) {
            aesCounterContext.clear();
            this._context = null;
        }
    }

    public DataBuffer decrypt(DataBuffer dataBuffer, long j, long j2) {
        return decrypt(dataBuffer, j, j2, false);
    }

    public DataBuffer decrypt(DataBuffer dataBuffer, long j, long j2, boolean z) {
        return encrypt(dataBuffer, j, j2, z);
    }

    public DataBuffer encrypt(DataBuffer dataBuffer, long j, long j2) {
        return encrypt(dataBuffer, j, j2, false);
    }

    public DataBuffer encrypt(DataBuffer dataBuffer, long j, long j2, boolean z) {
        int i;
        if (this._counterData == null) {
            this._counterData = new byte[16];
            int i2 = 0;
            while (true) {
                i = 4;
                if (i2 >= 4) {
                    break;
                }
                this._counterData[i2] = this._offset[i2];
                i2++;
            }
            Binary.toBytes32(j, false, this._ssrcBytes, 0);
            int i3 = 0;
            while (i3 < ArrayExtensions.getLength(this._ssrcBytes)) {
                this._counterData[i] = (byte) (this._offset[i] ^ this._ssrcBytes[i3]);
                i3++;
                i++;
            }
        }
        this._counterData[8] = (byte) ((int) (((long) this._offset[8]) ^ BitAssistant.rightShiftLong(280375465082880L & j2, 40)));
        this._counterData[9] = (byte) ((int) (((long) this._offset[9]) ^ BitAssistant.rightShiftLong(1095216660480L & j2, 32)));
        this._counterData[10] = (byte) ((int) (((long) this._offset[10]) ^ BitAssistant.rightShiftLong(4278190080L & j2, 24)));
        this._counterData[11] = (byte) ((int) (((long) this._offset[11]) ^ BitAssistant.rightShiftLong(16711680 & j2, 16)));
        this._counterData[12] = (byte) ((int) (((long) this._offset[12]) ^ BitAssistant.rightShiftLong(65280 & j2, 8)));
        this._counterData[13] = (byte) ((int) (BitAssistant.rightShiftLong(j2 & 255, 0) ^ ((long) this._offset[13])));
        byte[] bArr = this._counterData;
        byte[] bArr2 = this._offset;
        bArr[14] = bArr2[14];
        bArr[15] = bArr2[15];
        int length = (dataBuffer.getLength() / 16) * 16;
        if (length < dataBuffer.getLength()) {
            length += 16;
        }
        DataBuffer take = z ? __dataBufferPool.take(length) : this._output;
        if (!this._context.generateKeystream(take, length, this._counterData)) {
            return DataBuffer.getEmpty();
        }
        DataBuffer subset = take.subset(0, dataBuffer.getLength());
        byte[] data = subset.getData();
        int index = subset.getIndex();
        byte[] data2 = dataBuffer.getData();
        int index2 = dataBuffer.getIndex();
        for (int i4 = 0; i4 < subset.getLength(); i4++) {
            data[index] = (byte) (data[index] ^ data2[index2]);
            index++;
            index2++;
        }
        return subset;
    }

    public DataBuffer generate(byte b, int i) {
        byte[] bArr = new byte[16];
        for (int i2 = 0; i2 < ArrayExtensions.getLength(bArr); i2++) {
            bArr[i2] = 0;
        }
        bArr[7] = b;
        byte[] bArr2 = new byte[16];
        for (int i3 = 0; i3 < ArrayExtensions.getLength(bArr2); i3++) {
            bArr2[i3] = (byte) (this._offset[i3] ^ bArr[i3]);
        }
        int i4 = (i / 16) * 16;
        if (i4 < i) {
            i4 += 16;
        }
        DataBuffer allocate = DataBuffer.allocate(i4);
        try {
            AesCounterContext aesCounterContext = new AesCounterContext(this._key);
            try {
                if (!aesCounterContext.generateKeystream(allocate, i4, bArr2)) {
                    return DataBuffer.getEmpty();
                }
                aesCounterContext.clear();
                return allocate.subset(0, i);
            } finally {
                aesCounterContext.clear();
            }
        } catch (Exception unused) {
            return DataBuffer.getEmpty();
        }
    }
}
