package fm.liveswitch.bzip2;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteCollection;
import fm.liveswitch.Crc32;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferStream;
import fm.liveswitch.Global;
import fm.liveswitch.IAction2;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import kotlin.UByte;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

public class Decompressor {
    private boolean _blockRandomised;
    private int _blockSize100k;
    private int _bsBuff;
    private int _bsLive;
    private long _computedBlockCRC;
    private long _computedCombinedCRC;
    private ByteCollection _crcBytes;
    private int _currentChar = -1;
    private DecompressorState _currentState = DecompressorState.START_BLOCK;
    private DecompressionState _data;
    private DataBufferStream _input;
    private int _last;
    private int _nInUse;
    private int _origPtr;
    private long _storedBlockCRC;
    private long _storedCombinedCRC;
    private int _su_ch2;
    private int _su_chPrev;
    private int _su_count;
    private int _su_i2;
    private int _su_j2;
    private int _su_rNToGo;
    private int _su_rTPos;
    private int _su_tPos;
    private char _su_z;
    private long _totalBytesRead;

    private static int add(int i, int i2) {
        return i + i2;
    }

    private static int increment(int i, int i2) {
        return i + 1;
    }

    private boolean bsGetBit() {
        return getBits(1) != 0;
    }

    private long bsGetInt() {
        return (BitAssistant.leftShiftLong(BitAssistant.leftShiftLong(BitAssistant.leftShiftLong((long) getBits(8), 8) | ((long) getBits(8)), 8) | ((long) getBits(8)), 8) | ((long) getBits(8))) & 4294967295L;
    }

    private int bsGetUByte() {
        return getBits(8) & 255;
    }

    private void checkMagicChar(char c, int i) {
        int read8 = this._input.read8();
        if (read8 != c) {
            throw new RuntimeException(new Exception(StringExtensions.format("Not a valid BZip2 stream. byte {0}, expected '{1}', got '{2}'", IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(c)), IntegerExtensions.toString(Integer.valueOf(read8)))));
        }
    }

    private void complete() {
        this._storedCombinedCRC = bsGetInt();
        this._currentState = DecompressorState.EOF;
        this._data = null;
        if (this._storedCombinedCRC != this._computedCombinedCRC) {
            throw new RuntimeException(new Exception(StringExtensions.format("BZip2 CRC error (expected {0:X8}, computed {1:X8})", LongExtensions.toString(Long.valueOf(this._storedCombinedCRC)), LongExtensions.toString(Long.valueOf(this._computedCombinedCRC)))));
        }
    }

    private void createHuffmanDecodingTables(int i, int i2) {
        DecompressionState decompressionState = this._data;
        char[][] cArr = decompressionState._temp_charArray2d;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = 32;
            char[] cArr2 = cArr[i3];
            int i5 = i;
            char c = 0;
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                }
                char c2 = cArr2[i5];
                if (c2 > c) {
                    c = c2;
                }
                if (c2 < i4) {
                    i4 = c2;
                }
            }
            hbCreateDecodeTables(decompressionState._gLimit[i3], decompressionState._gBase[i3], decompressionState._gPerm[i3], cArr[i3], i4, c, i);
            decompressionState._gMinlen[i3] = i4;
        }
    }

    public Decompressor(DataBuffer dataBuffer) {
        this._input = new DataBufferStream(dataBuffer);
        initialize();
    }

    private void doRunAB(DecompressionState decompressionState, byte[] bArr, int i, Context context) {
        int i2 = -1;
        int i3 = 1;
        while (true) {
            if (context.getNextSym() == Constants.getRuna()) {
                i2 += i3;
            } else if (context.getNextSym() == Constants.getRunb()) {
                i2 += BitAssistant.leftShiftInteger(i3, 1);
            } else {
                byte b = decompressionState._seqToUnseq[bArr[0] & UByte.MAX_VALUE] & UByte.MAX_VALUE;
                decompressionState._unzftab[b] = decompressionState._unzftab[b] + i2 + 1;
                while (true) {
                    int i4 = i2 - 1;
                    if (i2 < 0) {
                        break;
                    }
                    decompressionState._ll8[Global.increment(context, context.getLastShadow(), new IAction2<Context, Integer>() {
                        public void invoke(Context context, Integer num) {
                            context.setLastShadow(num.intValue());
                        }
                    }, true)] = (byte) b;
                    i2 = i4;
                }
                if (context.getLastShadow() >= i) {
                    throw new RuntimeException(new Exception("block overrun"));
                }
                return;
            }
            if (context.getGroupPos() == 0) {
                context.setGroupPos(Constants.getG_size() - 1);
                context.setZt(decompressionState._selector[Global.increment(context, context.getGroupNo(), new IAction2<Context, Integer>() {
                    public void invoke(Context context, Integer num) {
                        context.setGroupNo(num.intValue());
                    }
                }, true)] & UByte.MAX_VALUE);
                context.setBase_zt(decompressionState._gBase[context.getZt()]);
                context.setLimit_zt(decompressionState._gLimit[context.getZt()]);
                context.setPerm_zt(decompressionState._gPerm[context.getZt()]);
                context.setMinLens_zt(decompressionState._gMinlen[context.getZt()]);
            } else {
                Global.decrement(context, context.getGroupPos(), new IAction2<Context, Integer>() {
                    public void invoke(Context context, Integer num) {
                        context.setGroupPos(num.intValue());
                    }
                }, false);
            }
            int minLens_zt = context.getMinLens_zt();
            while (context.getBsLiveShadow() < minLens_zt) {
                int readByte = this._input.readByte();
                if (readByte >= 0) {
                    context.setBsBuffShadow(readByte | BitAssistant.leftShiftInteger(context.getBsBuffShadow(), 8));
                    context.setBsLiveShadow(context.getBsLiveShadow() + 8);
                } else {
                    throw new RuntimeException(new Exception("unexpected end of stream"));
                }
            }
            int rightShiftInteger = BitAssistant.rightShiftInteger(context.getBsBuffShadow(), context.getBsLiveShadow() - minLens_zt) & (BitAssistant.leftShiftInteger(1, minLens_zt) - 1);
            context.setBsLiveShadow(context.getBsLiveShadow() - minLens_zt);
            while (rightShiftInteger > context.getLimit_zt()[minLens_zt]) {
                minLens_zt++;
                while (context.getBsLiveShadow() < 1) {
                    int readByte2 = this._input.readByte();
                    if (readByte2 >= 0) {
                        context.setBsBuffShadow(readByte2 | BitAssistant.leftShiftInteger(context.getBsBuffShadow(), 8));
                        context.setBsLiveShadow(context.getBsLiveShadow() + 8);
                    } else {
                        throw new RuntimeException(new Exception("unexpected end of stream"));
                    }
                }
                Global.decrement(context, context.getBsLiveShadow(), new IAction2<Context, Integer>() {
                    public void invoke(Context context, Integer num) {
                        context.setBsLiveShadow(num.intValue());
                    }
                }, false);
                rightShiftInteger = BitAssistant.leftShiftInteger(rightShiftInteger, 1) | (BitAssistant.rightShiftInteger(context.getBsBuffShadow(), context.getBsLiveShadow()) & 1);
            }
            context.setNextSym(context.getPerm_zt()[rightShiftInteger - context.getBase_zt()[minLens_zt]]);
            i3 = BitAssistant.leftShiftInteger(i3, 1);
        }
    }

    private void endBlock() {
        long compute = new Crc32(Crc32.getCrc32Polynomial(), true).compute(this._crcBytes.toArray());
        this._computedBlockCRC = compute;
        if (compute == this._storedBlockCRC) {
            long leftShiftLong = BitAssistant.leftShiftLong(this._computedCombinedCRC, 1) | BitAssistant.rightShiftLong(this._computedCombinedCRC, 31);
            this._computedCombinedCRC = leftShiftLong;
            this._computedCombinedCRC = (leftShiftLong ^ this._computedBlockCRC) & 4294967295L;
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("BZip2 CRC error (expected {0:X8}, computed {1:X8})", LongExtensions.toString(Long.valueOf(this._storedBlockCRC)), LongExtensions.toString(Long.valueOf(this._computedBlockCRC)))));
    }

    private void getAndMoveToFrontDecode() {
        DecompressionState decompressionState = this._data;
        int bits = getBits(24);
        this._origPtr = bits;
        if (bits < 0) {
            throw new RuntimeException(new Exception("BZ_DATA_ERROR"));
        } else if (bits <= (Constants.getBlockSizeMultiple() * this._blockSize100k) + 10) {
            recvDecodingTables();
            byte[] bArr = decompressionState._getAndMoveToFrontDecode_yy;
            int blockSizeMultiple = this._blockSize100k * Constants.getBlockSizeMultiple();
            int i = 256;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                bArr[i] = (byte) i;
                decompressionState._unzftab[i] = 0;
            }
            byte b = decompressionState._selector[0] & UByte.MAX_VALUE;
            Context context = new Context();
            context.setGroupNo(0);
            context.setGroupPos(Constants.getG_size() - 1);
            context.setEob(this._nInUse + 1);
            context.setNextSym(getAndMoveToFrontDecode0(0));
            context.setBsBuffShadow(this._bsBuff);
            context.setBsLiveShadow(this._bsLive);
            context.setLastShadow(-1);
            context.setZt(b);
            context.setBase_zt(decompressionState._gBase[b]);
            context.setLimit_zt(decompressionState._gLimit[b]);
            context.setPerm_zt(decompressionState._gPerm[b]);
            context.setMinLens_zt(decompressionState._gMinlen[b]);
            while (context.getNextSym() != context.getEob()) {
                if (context.getNextSym() == Constants.getRuna() || context.getNextSym() == Constants.getRunb()) {
                    doRunAB(decompressionState, bArr, blockSizeMultiple, context);
                } else if (Global.increment(context, context.getLastShadow(), new IAction2<Context, Integer>() {
                    public void invoke(Context context, Integer num) {
                        context.setLastShadow(num.intValue());
                    }
                }, true) < blockSizeMultiple) {
                    byte b2 = bArr[context.getNextSym() - 1] & UByte.MAX_VALUE;
                    byte b3 = decompressionState._seqToUnseq[b2] & UByte.MAX_VALUE;
                    decompressionState._unzftab[b3] = increment(decompressionState._unzftab[b3], 1);
                    decompressionState._ll8[context.getLastShadow()] = decompressionState._seqToUnseq[b2];
                    if (context.getNextSym() <= 16) {
                        for (int nextSym = context.getNextSym() - 1; nextSym > 0; nextSym--) {
                            bArr[nextSym] = bArr[nextSym - 1];
                        }
                    } else {
                        BitAssistant.copy(bArr, 0, bArr, 1, context.getNextSym() - 1);
                    }
                    bArr[0] = (byte) b2;
                    if (context.getGroupPos() == 0) {
                        context.setGroupPos(Constants.getG_size() - 1);
                        context.setZt(decompressionState._selector[Global.increment(context, context.getGroupNo(), new IAction2<Context, Integer>() {
                            public void invoke(Context context, Integer num) {
                                context.setGroupNo(num.intValue());
                            }
                        }, true)] & UByte.MAX_VALUE);
                        context.setBase_zt(decompressionState._gBase[context.getZt()]);
                        context.setLimit_zt(decompressionState._gLimit[context.getZt()]);
                        context.setPerm_zt(decompressionState._gPerm[context.getZt()]);
                        context.setMinLens_zt(decompressionState._gMinlen[context.getZt()]);
                    } else {
                        Global.decrement(context, context.getGroupPos(), new IAction2<Context, Integer>() {
                            public void invoke(Context context, Integer num) {
                                context.setGroupPos(num.intValue());
                            }
                        }, false);
                    }
                    int minLens_zt = context.getMinLens_zt();
                    while (context.getBsLiveShadow() < minLens_zt) {
                        int readByte = this._input.readByte();
                        if (readByte >= 0) {
                            context.setBsBuffShadow(readByte | BitAssistant.leftShiftInteger(context.getBsBuffShadow(), 8));
                            context.setBsLiveShadow(context.getBsLiveShadow() + 8);
                        } else {
                            throw new RuntimeException(new Exception("unexpected end of stream"));
                        }
                    }
                    int rightShiftInteger = BitAssistant.rightShiftInteger(context.getBsBuffShadow(), context.getBsLiveShadow() - minLens_zt) & (BitAssistant.leftShiftInteger(1, minLens_zt) - 1);
                    context.setBsLiveShadow(context.getBsLiveShadow() - minLens_zt);
                    while (rightShiftInteger > context.getLimit_zt()[minLens_zt]) {
                        minLens_zt++;
                        while (context.getBsLiveShadow() < 1) {
                            int readByte2 = this._input.readByte();
                            if (readByte2 >= 0) {
                                context.setBsBuffShadow(readByte2 | BitAssistant.leftShiftInteger(context.getBsBuffShadow(), 8));
                                context.setBsLiveShadow(context.getBsLiveShadow() + 8);
                            } else {
                                throw new RuntimeException(new Exception("unexpected end of stream"));
                            }
                        }
                        Global.decrement(context, context.getBsLiveShadow(), new IAction2<Context, Integer>() {
                            public void invoke(Context context, Integer num) {
                                context.setBsLiveShadow(num.intValue());
                            }
                        }, false);
                        rightShiftInteger = BitAssistant.leftShiftInteger(rightShiftInteger, 1) | (BitAssistant.rightShiftInteger(context.getBsBuffShadow(), context.getBsLiveShadow()) & 1);
                    }
                    context.setNextSym(context.getPerm_zt()[rightShiftInteger - context.getBase_zt()[minLens_zt]]);
                } else {
                    throw new RuntimeException(new Exception("block overrun"));
                }
            }
            this._last = context.getLastShadow();
            this._bsLive = context.getBsLiveShadow();
            this._bsBuff = context.getBsBuffShadow();
        } else {
            throw new RuntimeException(new Exception("BZ_DATA_ERROR"));
        }
    }

    private int getAndMoveToFrontDecode0(int i) {
        DecompressionState decompressionState = this._data;
        byte b = decompressionState._selector[i] & UByte.MAX_VALUE;
        int[] iArr = decompressionState._gLimit[b];
        int i2 = decompressionState._gMinlen[b];
        int bits = getBits(i2);
        int i3 = this._bsLive;
        int i4 = this._bsBuff;
        while (bits > iArr[i2]) {
            i2++;
            while (i3 < 1) {
                int readByte = this._input.readByte();
                if (readByte >= 0) {
                    i4 = BitAssistant.leftShiftInteger(i4, 8) | readByte;
                    i3 += 8;
                } else {
                    throw new RuntimeException(new Exception("unexpected end of stream"));
                }
            }
            i3--;
            bits = BitAssistant.leftShiftInteger(bits, 1) | (1 & BitAssistant.rightShiftInteger(i4, i3));
        }
        this._bsLive = i3;
        this._bsBuff = i4;
        return decompressionState._gPerm[b][bits - decompressionState._gBase[b][i2]];
    }

    private int getBits(int i) {
        int i2 = this._bsLive;
        int i3 = this._bsBuff;
        if (i2 < i) {
            do {
                int read8 = this._input.read8();
                if (read8 >= 0) {
                    i3 = BitAssistant.leftShiftInteger(i3, 8) | read8;
                    i2 += 8;
                } else {
                    throw new RuntimeException(new Exception("unexpected end of stream"));
                }
            } while (i2 < i);
            this._bsBuff = i3;
        }
        int i4 = i2 - i;
        this._bsLive = i4;
        return (BitAssistant.leftShiftInteger(1, i) - 1) & BitAssistant.rightShiftInteger(i3, i4);
    }

    private static void hbCreateDecodeTables(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        for (int i6 = i; i6 <= i2; i6++) {
            for (int i7 = 0; i7 < i3; i7++) {
                if (cArr[i7] == i6) {
                    iArr3[i5] = i7;
                    i5++;
                }
            }
        }
        int maxCodeLength = Constants.getMaxCodeLength();
        while (true) {
            maxCodeLength--;
            if (maxCodeLength <= 0) {
                break;
            }
            iArr2[maxCodeLength] = 0;
            iArr[maxCodeLength] = 0;
        }
        for (int i8 = 0; i8 < i3; i8++) {
            int i9 = cArr[i8] + 1;
            iArr2[i9] = increment(iArr2[i9], 1);
        }
        for (int i10 = 1; i10 < Constants.getMaxCodeLength(); i10++) {
            iArr2[i10] = iArr2[i10] + iArr2[i10 - 1];
        }
        int i11 = iArr2[i];
        int i12 = i;
        while (i12 <= i2) {
            int i13 = i12 + 1;
            int i14 = iArr2[i13];
            int i15 = i4 + (i14 - i11);
            iArr[i12] = i15 - 1;
            i4 = BitAssistant.leftShiftInteger(i15, 1);
            i12 = i13;
            i11 = i14;
        }
        for (int i16 = i + 1; i16 <= i2; i16++) {
            iArr2[i16] = BitAssistant.leftShiftInteger(iArr[i16 - 1] + 1, 1) - iArr2[i16];
        }
    }

    private void initBlock() {
        int bsGetUByte = bsGetUByte();
        int bsGetUByte2 = bsGetUByte();
        int bsGetUByte3 = bsGetUByte();
        int bsGetUByte4 = bsGetUByte();
        int bsGetUByte5 = bsGetUByte();
        int bsGetUByte6 = bsGetUByte();
        if (bsGetUByte == 23 && bsGetUByte2 == 114 && bsGetUByte3 == 69 && bsGetUByte4 == 56 && bsGetUByte5 == 80 && bsGetUByte6 == 144) {
            complete();
        } else if (bsGetUByte == 49 && bsGetUByte2 == 65 && bsGetUByte3 == 89 && bsGetUByte4 == 38 && bsGetUByte5 == 83 && bsGetUByte6 == 89) {
            this._storedBlockCRC = bsGetInt();
            boolean z = true;
            if (getBits(1) != 1) {
                z = false;
            }
            this._blockRandomised = z;
            if (this._data == null) {
                this._data = new DecompressionState(this._blockSize100k);
            }
            getAndMoveToFrontDecode();
            this._crcBytes = new ByteCollection();
            this._currentState = DecompressorState.START_BLOCK;
        } else {
            this._currentState = DecompressorState.EOF;
            throw new RuntimeException(new Exception(StringExtensions.format("bad block header at offset 0x{0:X}", (Object) IntegerExtensions.toString(Integer.valueOf(this._input.getPosition())))));
        }
    }

    private void initialize() {
        if (this._input != null) {
            checkMagicChar('B', 0);
            checkMagicChar(Matrix.MATRIX_TYPE_ZERO, 1);
            checkMagicChar('h', 2);
            int read8 = this._input.read8();
            if (read8 < 49 || read8 > 57) {
                throw new RuntimeException(new Exception(StringExtensions.format("Stream is not BZip2 formatted: illegal blocksize {0}", (Object) IntegerExtensions.toString(Integer.valueOf(read8)))));
            }
            this._blockSize100k = read8 - 48;
            initBlock();
            setupBlock();
            return;
        }
        throw new RuntimeException(new Exception("No input Stream"));
    }

    private void makeMaps() {
        boolean[] zArr = this._data._inUse;
        byte[] bArr = this._data._seqToUnseq;
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (zArr[i2]) {
                bArr[i] = (byte) i2;
                i++;
            }
        }
        this._nInUse = i;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i < 0) {
            throw new RuntimeException(new Exception(StringExtensions.format("offset ({0}) must be > 0", (Object) IntegerExtensions.toString(Integer.valueOf(i)))));
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > ArrayExtensions.getLength(bArr)) {
                throw new RuntimeException(new Exception(StringExtensions.format("offset({0}) count({1}) bLength({2})", IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength(bArr))))));
            } else if (this._input != null) {
                int i4 = i;
                while (i4 < i3) {
                    int readByte = readByte();
                    if (readByte < 0) {
                        break;
                    }
                    bArr[i4] = (byte) readByte;
                    i4++;
                }
                if (i4 == i) {
                    return -1;
                }
                return i4 - i;
            } else {
                throw new RuntimeException(new Exception("the stream is not open"));
            }
        } else {
            throw new RuntimeException(new Exception(StringExtensions.format("count ({0}) must be > 0", (Object) IntegerExtensions.toString(Integer.valueOf(i2)))));
        }
    }

    public int readByte() {
        int i = this._currentChar;
        this._totalBytesRead++;
        DecompressorState decompressorState = this._currentState;
        if (decompressorState == DecompressorState.EOF) {
            return -1;
        }
        if (decompressorState == DecompressorState.START_BLOCK) {
            throw new RuntimeException(new Exception("bad state"));
        } else if (decompressorState == DecompressorState.RAND_PART_A) {
            throw new RuntimeException(new Exception("bad state"));
        } else if (decompressorState == DecompressorState.RAND_PART_B) {
            setupRandPartB();
            return i;
        } else if (decompressorState == DecompressorState.RAND_PART_C) {
            setupRandPartC();
            return i;
        } else if (decompressorState == DecompressorState.NO_RAND_PART_A) {
            throw new RuntimeException(new Exception("bad state"));
        } else if (decompressorState == DecompressorState.NO_RAND_PART_B) {
            setupNoRandPartB();
            return i;
        } else if (decompressorState == DecompressorState.NO_RAND_PART_C) {
            setupNoRandPartC();
            return i;
        } else {
            throw new RuntimeException(new Exception("bad state"));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recvDecodingTables() {
        /*
            r12 = this;
            fm.liveswitch.bzip2.DecompressionState r0 = r12._data
            boolean[] r1 = r0._inUse
            byte[] r2 = r0._recvDecodingTables_pos
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0009:
            r6 = 16
            r7 = 1
            if (r4 >= r6) goto L_0x001c
            boolean r6 = r12.bsGetBit()
            if (r6 == 0) goto L_0x0019
            int r6 = fm.liveswitch.BitAssistant.leftShiftInteger(r7, r4)
            r5 = r5 | r6
        L_0x0019:
            int r4 = r4 + 1
            goto L_0x0009
        L_0x001c:
            r4 = 256(0x100, float:3.59E-43)
        L_0x001e:
            r8 = -1
            int r4 = r4 + r8
            if (r4 < 0) goto L_0x0025
            r1[r4] = r3
            goto L_0x001e
        L_0x0025:
            r4 = 0
        L_0x0026:
            if (r4 >= r6) goto L_0x0047
            int r9 = fm.liveswitch.BitAssistant.leftShiftInteger(r7, r4)
            r9 = r9 & r5
            if (r9 == 0) goto L_0x0044
            r9 = 4
            int r9 = fm.liveswitch.BitAssistant.leftShiftInteger(r4, r9)
            r10 = 0
        L_0x0035:
            if (r10 >= r6) goto L_0x0044
            boolean r11 = r12.bsGetBit()
            if (r11 == 0) goto L_0x0041
            int r11 = r9 + r10
            r1[r11] = r7
        L_0x0041:
            int r10 = r10 + 1
            goto L_0x0035
        L_0x0044:
            int r4 = r4 + 1
            goto L_0x0026
        L_0x0047:
            r12.makeMaps()
            int r1 = r12._nInUse
            int r1 = r1 + 2
            r4 = 3
            int r4 = r12.getBits(r4)
            r5 = 15
            int r5 = r12.getBits(r5)
            r6 = 0
        L_0x005a:
            if (r6 >= r5) goto L_0x006e
            r9 = 0
        L_0x005d:
            boolean r10 = r12.bsGetBit()
            if (r10 == 0) goto L_0x0066
            int r9 = r9 + 1
            goto L_0x005d
        L_0x0066:
            byte[] r10 = r0._selectorMtf
            byte r9 = (byte) r9
            r10[r6] = r9
            int r6 = r6 + 1
            goto L_0x005a
        L_0x006e:
            r6 = r4
        L_0x006f:
            int r6 = r6 + r8
            if (r6 < 0) goto L_0x0076
            byte r9 = (byte) r6
            r2[r6] = r9
            goto L_0x006f
        L_0x0076:
            r6 = 0
        L_0x0077:
            if (r6 >= r5) goto L_0x0093
            byte[] r9 = r0._selectorMtf
            byte r9 = r9[r6]
            byte r10 = r2[r9]
        L_0x007f:
            if (r9 <= 0) goto L_0x008a
            int r11 = r9 + -1
            byte r11 = r2[r11]
            r2[r9] = r11
            int r9 = r9 + -1
            goto L_0x007f
        L_0x008a:
            r2[r3] = r10
            byte[] r9 = r0._selector
            r9[r6] = r10
            int r6 = r6 + 1
            goto L_0x0077
        L_0x0093:
            char[][] r0 = r0._temp_charArray2d
            r2 = 0
        L_0x0096:
            if (r2 >= r4) goto L_0x00bc
            r5 = 5
            int r5 = r12.getBits(r5)
            r6 = r0[r2]
            r9 = 0
        L_0x00a0:
            if (r9 >= r1) goto L_0x00b9
        L_0x00a2:
            boolean r10 = r12.bsGetBit()
            if (r10 == 0) goto L_0x00b3
            boolean r10 = r12.bsGetBit()
            if (r10 == 0) goto L_0x00b0
            r10 = -1
            goto L_0x00b1
        L_0x00b0:
            r10 = 1
        L_0x00b1:
            int r5 = r5 + r10
            goto L_0x00a2
        L_0x00b3:
            char r10 = (char) r5
            r6[r9] = r10
            int r9 = r9 + 1
            goto L_0x00a0
        L_0x00b9:
            int r2 = r2 + 1
            goto L_0x0096
        L_0x00bc:
            r12.createHuffmanDecodingTables(r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.bzip2.Decompressor.recvDecodingTables():void");
    }

    private void setupBlock() {
        DecompressionState decompressionState = this._data;
        if (decompressionState != null) {
            int i = 1;
            int[] initTT = decompressionState.initTT(this._last + 1);
            for (int i2 = 0; i2 <= 255; i2++) {
                if (decompressionState._unzftab[i2] < 0 || decompressionState._unzftab[i2] > this._last) {
                    throw new RuntimeException(new Exception("BZ_DATA_ERROR"));
                }
            }
            decompressionState._cftab[0] = 0;
            for (int i3 = 1; i3 <= 256; i3++) {
                decompressionState._cftab[i3] = decompressionState._unzftab[i3 - 1];
            }
            for (int i4 = 1; i4 <= 256; i4++) {
                decompressionState._cftab[i4] = decompressionState._cftab[i4] + decompressionState._cftab[i4 - 1];
            }
            for (int i5 = 0; i5 <= 256; i5++) {
                if (decompressionState._cftab[i5] < 0 || decompressionState._cftab[i5] > this._last + 1) {
                    throw new RuntimeException(new Exception(StringExtensions.format("BZ_DATA_ERROR: cftab[{0}]={1} last={2}", IntegerExtensions.toString(Integer.valueOf(i5)), IntegerExtensions.toString(Integer.valueOf(decompressionState._cftab[i5])), IntegerExtensions.toString(Integer.valueOf(this._last)))));
                }
            }
            while (i <= 256) {
                if (decompressionState._cftab[i - 1] <= decompressionState._cftab[i]) {
                    i++;
                } else {
                    throw new RuntimeException(new Exception("BZ_DATA_ERROR"));
                }
            }
            int i6 = this._last;
            for (int i7 = 0; i7 <= i6; i7++) {
                byte b = decompressionState._ll8[i7] & UByte.MAX_VALUE;
                initTT[decompressionState._cftab[b]] = i7;
                decompressionState._cftab[b] = increment(decompressionState._cftab[b], b);
            }
            int i8 = this._origPtr;
            if (i8 < 0 || i8 >= ArrayExtensions.getLength(initTT)) {
                throw new RuntimeException(new Exception("stream corrupted"));
            }
            this._su_tPos = initTT[this._origPtr];
            this._su_count = 0;
            this._su_i2 = 0;
            this._su_ch2 = 256;
            if (this._blockRandomised) {
                this._su_rNToGo = 0;
                this._su_rTPos = 0;
                setupRandPartA();
                return;
            }
            setupNoRandPartA();
        }
    }

    private void setupNoRandPartA() {
        if (this._su_i2 <= this._last) {
            this._su_chPrev = this._su_ch2;
            byte b = this._data._ll8[this._su_tPos] & UByte.MAX_VALUE;
            this._su_ch2 = b;
            this._su_tPos = this._data._tt[this._su_tPos];
            this._su_i2++;
            this._currentChar = b;
            this._currentState = DecompressorState.NO_RAND_PART_B;
            this._crcBytes.add((byte) b);
            return;
        }
        this._currentState = DecompressorState.NO_RAND_PART_A;
        endBlock();
        initBlock();
        setupBlock();
    }

    private void setupNoRandPartB() {
        if (this._su_ch2 != this._su_chPrev) {
            this._su_count = 1;
            setupNoRandPartA();
            return;
        }
        int i = this._su_count + 1;
        this._su_count = i;
        if (i >= 4) {
            this._su_z = (char) (this._data._ll8[this._su_tPos] & UByte.MAX_VALUE);
            this._su_tPos = this._data._tt[this._su_tPos];
            this._su_j2 = 0;
            setupNoRandPartC();
            return;
        }
        setupNoRandPartA();
    }

    private void setupNoRandPartC() {
        if (this._su_j2 < this._su_z) {
            int i = this._su_ch2;
            this._currentChar = i;
            this._crcBytes.add((byte) i);
            this._su_j2++;
            this._currentState = DecompressorState.NO_RAND_PART_C;
            return;
        }
        this._su_i2++;
        this._su_count = 0;
        setupNoRandPartA();
    }

    private void setupRandPartA() {
        if (this._su_i2 <= this._last) {
            this._su_chPrev = this._su_ch2;
            byte b = this._data._ll8[this._su_tPos] & UByte.MAX_VALUE;
            this._su_tPos = this._data._tt[this._su_tPos];
            int i = this._su_rNToGo;
            byte b2 = 0;
            if (i == 0) {
                this._su_rNToGo = Rand.rnums(this._su_rTPos) - 1;
                int i2 = this._su_rTPos + 1;
                this._su_rTPos = i2;
                if (i2 == 512) {
                    this._su_rTPos = 0;
                }
            } else {
                this._su_rNToGo = i - 1;
            }
            if (this._su_rNToGo == 1) {
                b2 = 1;
            }
            byte b3 = b ^ b2;
            this._su_ch2 = b3;
            this._su_i2++;
            this._currentChar = b3;
            this._currentState = DecompressorState.RAND_PART_B;
            this._crcBytes.add((byte) b3);
            return;
        }
        endBlock();
        initBlock();
        setupBlock();
    }

    private void setupRandPartB() {
        if (this._su_ch2 != this._su_chPrev) {
            this._currentState = DecompressorState.RAND_PART_A;
            this._su_count = 1;
            setupRandPartA();
            return;
        }
        int i = this._su_count + 1;
        this._su_count = i;
        if (i >= 4) {
            this._su_z = (char) (this._data._ll8[this._su_tPos] & UByte.MAX_VALUE);
            this._su_tPos = this._data._tt[this._su_tPos];
            int i2 = this._su_rNToGo;
            if (i2 == 0) {
                this._su_rNToGo = Rand.rnums(this._su_rTPos) - 1;
                int i3 = this._su_rTPos + 1;
                this._su_rTPos = i3;
                if (i3 == 512) {
                    this._su_rTPos = 0;
                }
            } else {
                this._su_rNToGo = i2 - 1;
            }
            this._su_j2 = 0;
            this._currentState = DecompressorState.RAND_PART_C;
            if (this._su_rNToGo == 1) {
                this._su_z = (char) (this._su_z ^ 1);
            }
            setupRandPartC();
            return;
        }
        this._currentState = DecompressorState.RAND_PART_A;
        setupRandPartA();
    }

    private void setupRandPartC() {
        if (this._su_j2 < this._su_z) {
            int i = this._su_ch2;
            this._currentChar = i;
            this._crcBytes.add((byte) i);
            this._su_j2++;
            return;
        }
        this._currentState = DecompressorState.RAND_PART_A;
        this._su_i2++;
        this._su_count = 0;
        setupRandPartA();
    }
}
