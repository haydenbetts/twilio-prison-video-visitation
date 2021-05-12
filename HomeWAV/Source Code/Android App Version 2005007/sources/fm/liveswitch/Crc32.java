package fm.liveswitch;

public class Crc32 {
    private long[] __lookup;
    private long _finalXorValue;
    private long _initialValue;
    private long _polynomial;
    private boolean _reverseBits;

    public static long getCrc32Polynomial() {
        return 79764919;
    }

    public static long getCrc32cPolynomial() {
        return 517762881;
    }

    public static long getCrc32dPolynomial() {
        return 2821953579L;
    }

    public long compute(byte[] bArr) {
        return compute(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public long compute(byte[] bArr, int i) {
        return compute(bArr, i, ArrayExtensions.getLength(bArr) - i);
    }

    public long compute(byte[] bArr, int i, int i2) {
        long j;
        long j2;
        long initialValue = getInitialValue();
        for (int i3 = 0; i3 < i2; i3++) {
            if (getReverseBits()) {
                j2 = BitAssistant.leftShiftLong(initialValue, 8);
                j = this.__lookup[(int) ((BitAssistant.rightShiftLong(initialValue, 24) ^ ((long) BitAssistant.castInteger(bArr[i + i3]))) % 256)];
            } else {
                j2 = BitAssistant.rightShiftLong(initialValue, 8);
                j = this.__lookup[(int) (((255 & initialValue) ^ ((long) BitAssistant.castInteger(bArr[i + i3]))) % 256)];
            }
            initialValue = j2 ^ j;
        }
        long finalXorValue = getFinalXorValue() ^ initialValue;
        if (finalXorValue < 0) {
            finalXorValue = finalXorValue + 4294967295L + 1;
        }
        return finalXorValue & 4294967295L;
    }

    public String computeHex(byte[] bArr) {
        return computeHex(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public String computeHex(byte[] bArr, int i) {
        return computeHex(bArr, i, ArrayExtensions.getLength(bArr) - i);
    }

    public String computeHex(byte[] bArr, int i, int i2) {
        return BitAssistant.getHexString(Binary.toBytes32(compute(bArr, i, i2), false));
    }

    public Crc32() {
        this(getCrc32Polynomial());
    }

    public Crc32(long j) {
        this(j, false);
    }

    public Crc32(long j, boolean z) {
        setPolynomial(processPolynomial(j));
        setReverseBits(z);
        setInitialValue(4294967295L);
        setFinalXorValue(4294967295L);
        this.__lookup = createLookup();
    }

    private long[] createLookup() {
        long[] jArr = new long[256];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            long j = (long) i;
            for (int i2 = 8; i2 > 0; i2--) {
                j = (j & 1) == 1 ? (j >> 1) ^ getPolynomial() : j >> 1;
            }
            if (getReverseBits()) {
                jArr[reverseBits8(i)] = reverseBits32(j);
            } else {
                jArr[i] = j;
            }
        }
        return jArr;
    }

    public long getFinalXorValue() {
        return this._finalXorValue;
    }

    public long getInitialValue() {
        return this._initialValue;
    }

    public long getPolynomial() {
        return this._polynomial;
    }

    public boolean getReverseBits() {
        return this._reverseBits;
    }

    private static long processPolynomial(long j) {
        return reverseBits32(j);
    }

    private static long reverseBits32(long j) {
        return Binary.fromBytes32(Binary.bitStringToBytes(reverseString(Binary.bytesToBitString(Binary.toBytes32(j, false)))), 0, false);
    }

    private static int reverseBits8(int i) {
        return Binary.fromBytes8(Binary.bitStringToBytes(reverseString(Binary.bytesToBitString(Binary.toBytes8(i)))), 0);
    }

    private static String reverseString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int length = StringExtensions.getLength(str) - 1; length >= 0; length--) {
            StringBuilderExtensions.append(sb, StringExtensions.substring(str, length, 1));
        }
        return sb.toString();
    }

    public void setFinalXorValue(long j) {
        this._finalXorValue = j;
    }

    public void setInitialValue(long j) {
        this._initialValue = j;
    }

    private void setPolynomial(long j) {
        this._polynomial = j;
    }

    private void setReverseBits(boolean z) {
        this._reverseBits = z;
    }
}
