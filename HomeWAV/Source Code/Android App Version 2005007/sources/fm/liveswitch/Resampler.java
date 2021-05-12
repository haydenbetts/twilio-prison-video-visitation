package fm.liveswitch;

import kotlin.UByte;

public class Resampler {
    private static int _defaultLowPassOrder;
    private double _factor;
    private double _factorMax;
    private double _factorMin;
    private float[] _impulseResponse;
    private float[] _impulseResponseDeltas;
    private byte[] _input;
    private int _inputOffset;
    private int _inputPosition;
    private int _inputRead;
    private int _inputSize;
    private int _lowpassOrder;
    private float _lowpassScale;
    private byte[] _output;
    private int _outputPosition;
    private double _time;
    private int _wingLength;

    private static float floatFromShort(short s) {
        float f = ((float) s) / 32768.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }
        if (f < -1.0f) {
            return -1.0f;
        }
        return f;
    }

    private static short shortFromFloat(float f) {
        float f2 = f * 32768.0f;
        if (f2 > 32767.0f) {
            f2 = 32767.0f;
        }
        if (f2 < -32768.0f) {
            f2 = -32768.0f;
        }
        return (short) ((int) f2);
    }

    private static double zero(double d) {
        double d2 = d / 2.0d;
        double d3 = d2 / ((double) 1);
        double d4 = d3 * d3 * 1.0d;
        double d5 = 1.0d + d4;
        int i = 2;
        while (d4 >= 1.0E-21d * d5) {
            double d6 = d2 / ((double) i);
            i++;
            d4 *= d6 * d6;
            d5 += d4;
        }
        return d5;
    }

    private static float downsample(float[] fArr, float[] fArr2, int i, boolean z, byte[] bArr, int i2, double d, int i3, double d2) {
        double d3 = d * d2;
        if (i3 == 2) {
            i--;
            if (d == 0.0d) {
                d3 += d2;
            }
        }
        float f = 0.0f;
        if (z) {
            while (true) {
                int i4 = (int) d3;
                if (i4 >= i) {
                    return f;
                }
                f += (fArr[i4] + (fArr2[(int) d3] * ((float) (d3 - MathAssistant.floor(d3))))) * floatFromShort(readPcmShort(bArr, i2));
                d3 += d2;
                i2 += i3;
            }
        } else {
            while (true) {
                int i5 = (int) d3;
                if (i5 >= i) {
                    return f;
                }
                f += fArr[i5] * floatFromShort(readPcmShort(bArr, i2));
                d3 += d2;
                i2 += i3;
            }
        }
    }

    private int downsample(byte[] bArr, byte[] bArr2, double d, int i, int i2, float f, float[] fArr, float[] fArr2, boolean z) {
        double d2 = this._time;
        double d3 = 1.0d;
        double d4 = 1.0d / d;
        double min = MathAssistant.min(4096.0d, d * 4096.0d);
        double d5 = ((double) i) + d2;
        int i3 = 0;
        while (d2 < d5) {
            double floor = d2 - MathAssistant.floor(d2);
            double d6 = d3 - floor;
            int i4 = ((int) d2) * 2;
            float[] fArr3 = fArr;
            float[] fArr4 = fArr2;
            int i5 = i2;
            boolean z2 = z;
            int i6 = i3;
            byte[] bArr3 = bArr;
            double d7 = min;
            writePcmShort(shortFromFloat((downsample(fArr3, fArr4, i5, z2, bArr3, i4, floor, -2, d7) + downsample(fArr3, fArr4, i5, z2, bArr3, i4 + 2, d6, 2, d7)) * f), bArr2, i6);
            i3 = i6 + 2;
            d2 += d4;
            d3 = 1.0d;
        }
        int i7 = i3;
        this._time = d2;
        return i7;
    }

    public static int getDefaultLowPassOrder() {
        return _defaultLowPassOrder;
    }

    public double getFactor() {
        return this._factor;
    }

    public int getOutputLength(DataBuffer dataBuffer) {
        return (int) (((double) dataBuffer.getLength()) * getFactor());
    }

    private static void lowpassFilter(double[] dArr, int i, double d, double d2, int i2) {
        dArr[0] = d * 2.0d;
        for (int i3 = 1; i3 < i; i3++) {
            double pi = (MathAssistant.getPi() * ((double) i3)) / ((double) i2);
            dArr[i3] = MathAssistant.sin((pi * 2.0d) * d) / pi;
        }
        double zero = 1.0d / zero(d2);
        double d3 = 1.0d / ((double) (i - 1));
        for (int i4 = 1; i4 < i; i4++) {
            double d4 = ((double) i4) * d3;
            double d5 = 1.0d - (d4 * d4);
            if (d5 < 0.0d) {
                d5 = 0.0d;
            }
            dArr[i4] = dArr[i4] * zero(MathAssistant.sqrt(d5) * d2) * zero;
        }
    }

    private static short readPcmShort(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & UByte.MAX_VALUE) << 8) | (bArr[i] & UByte.MAX_VALUE));
    }

    private boolean resample(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        float[] fArr;
        float[] fArr2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19 = i2;
        byte[] bArr3 = bArr2;
        double factor = getFactor();
        if (factor < this._factorMin || factor > this._factorMax) {
            throw new RuntimeException(new Exception("Factor must be between minimum and maximum factors."));
        }
        float[] fArr3 = this._impulseResponse;
        float[] fArr4 = this._impulseResponseDeltas;
        float f = this._lowpassScale;
        int i20 = this._wingLength;
        int i21 = this._outputPosition;
        if (i21 == 0 || (i17 = i4 + 0) <= 0) {
            i6 = i3;
            i5 = 0;
        } else {
            int min = MathAssistant.min(i17, i21);
            BitAssistant.copy(this._output, 0, bArr3, i3 * 2, min * 2);
            i6 = i3 + min;
            i5 = min + 0;
            int i22 = 0;
            while (true) {
                i18 = this._outputPosition;
                if (i22 >= i18 - min) {
                    break;
                }
                for (int i23 = 0; i23 < 2; i23++) {
                    byte[] bArr4 = this._output;
                    bArr4[(i22 * 2) + i23] = bArr4[((i22 + min) * 2) + i23];
                }
                i22++;
            }
            this._outputPosition = i18 - min;
        }
        if (this._outputPosition == 0) {
            if (factor < 1.0d) {
                f = (float) (((double) f) * factor);
            }
            float f2 = f;
            int i24 = i6;
            int i25 = i5;
            int i26 = 0;
            int i27 = i;
            while (true) {
                int i28 = this._inputSize;
                int i29 = this._inputRead;
                int i30 = i28 - i29;
                int i31 = i19 - i26;
                if (i30 >= i31) {
                    i30 = i31;
                }
                BitAssistant.copy(bArr, i27 * 2, this._input, i29 * 2, i30 * 2);
                int i32 = i27 + i30;
                int i33 = i26 + i30;
                int i34 = this._inputRead + i30;
                this._inputRead = i34;
                if (!z || i33 != i19) {
                    i9 = i34 - (this._inputOffset * 2);
                } else {
                    int i35 = i34 - this._inputOffset;
                    for (int i36 = 0; i36 < this._inputOffset; i36++) {
                        for (int i37 = 0; i37 < 2; i37++) {
                            this._input[((this._inputRead + i36) * 2) + i37] = 0;
                        }
                    }
                    i9 = i35;
                }
                if (i9 <= 0) {
                    i10 = i33;
                    break;
                }
                if (factor >= 1.0d) {
                    i11 = i9;
                    i13 = 2;
                    i10 = i33;
                    i12 = i20;
                    fArr2 = fArr4;
                    fArr = fArr3;
                    i14 = upsample(this._input, this._output, factor, i9, i20, f2, fArr3, fArr4, false) / 2;
                } else {
                    i11 = i9;
                    i10 = i33;
                    i12 = i20;
                    fArr2 = fArr4;
                    fArr = fArr3;
                    i13 = 2;
                    i14 = downsample(this._input, this._output, factor, i9, i12, f2, fArr, fArr4, false) / 2;
                }
                int i38 = i11;
                double d = this._time - ((double) i38);
                this._time = d;
                int i39 = this._inputPosition + i38;
                this._inputPosition = i39;
                int i40 = this._inputOffset;
                int i41 = ((int) d) - i40;
                if (i41 != 0) {
                    this._time = d - ((double) i41);
                    this._inputPosition = i39 + i41;
                }
                int i42 = this._inputRead - (this._inputPosition - i40);
                for (int i43 = 0; i43 < i42; i43++) {
                    for (int i44 = 0; i44 < i13; i44++) {
                        byte[] bArr5 = this._input;
                        bArr5[(i43 * 2) + i44] = bArr5[(((this._inputPosition - this._inputOffset) + i43) * 2) + i44];
                    }
                }
                this._inputRead = i42;
                this._inputPosition = this._inputOffset;
                this._outputPosition = i14;
                if (i14 != 0 && (i15 = i4 - i25) > 0) {
                    int min2 = MathAssistant.min(i15, i14);
                    BitAssistant.copy(this._output, 0, bArr3, i24 * 2, min2 * 2);
                    i24 += min2;
                    i25 += min2;
                    int i45 = 0;
                    while (true) {
                        i16 = this._outputPosition;
                        if (i45 >= i16 - min2) {
                            break;
                        }
                        for (int i46 = 0; i46 < i13; i46++) {
                            byte[] bArr6 = this._output;
                            bArr6[(i45 * 2) + i46] = bArr6[((i45 + min2) * 2) + i46];
                        }
                        i45++;
                    }
                    this._outputPosition = i16 - min2;
                }
                if (this._outputPosition != 0) {
                    break;
                }
                i19 = i2;
                i26 = i10;
                i20 = i12;
                i27 = i32;
                fArr4 = fArr2;
                fArr3 = fArr;
            }
            i8 = i2;
            i7 = i10;
        } else {
            i8 = i2;
            i7 = 0;
        }
        return i7 == i8;
    }

    public boolean resample(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        return resample(dataBuffer, dataBuffer2, true);
    }

    public boolean resample(DataBuffer dataBuffer, DataBuffer dataBuffer2, boolean z) {
        if (dataBuffer2.getLength() == getOutputLength(dataBuffer)) {
            return resample(dataBuffer.getData(), dataBuffer.getIndex() / 2, dataBuffer.getLength() / 2, dataBuffer2.getData(), dataBuffer2.getIndex() / 2, dataBuffer2.getLength() / 2, z);
        }
        throw new RuntimeException(new Exception("Invalid output length."));
    }

    public Resampler(double d) {
        setFactor(d);
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i <= 0 || i <= 0) {
            throw new RuntimeException(new Exception("Mininum and maximum factor must be positive."));
        } else if (d >= d) {
            this._factorMin = d;
            this._factorMax = d;
            int defaultLowPassOrder = getDefaultLowPassOrder() > 0 ? getDefaultLowPassOrder() : Platform.getInstance().getIsMobile() ? 5 : 11;
            this._lowpassOrder = defaultLowPassOrder;
            this._lowpassScale = 1.0f;
            int i2 = ((defaultLowPassOrder - 1) * 4096) / 2;
            this._wingLength = i2;
            double[] dArr = new double[i2];
            lowpassFilter(dArr, i2, 0.45d, 6.0d, 4096);
            int i3 = this._wingLength;
            this._impulseResponse = new float[i3];
            this._impulseResponseDeltas = new float[i3];
            for (int i4 = 0; i4 < this._wingLength; i4++) {
                this._impulseResponse[i4] = (float) dArr[i4];
            }
            int i5 = 0;
            while (true) {
                int i6 = this._wingLength;
                if (i5 < i6 - 1) {
                    float[] fArr = this._impulseResponseDeltas;
                    float[] fArr2 = this._impulseResponse;
                    int i7 = i5 + 1;
                    fArr[i5] = fArr2[i7] - fArr2[i5];
                    i5 = i7;
                } else {
                    this._impulseResponseDeltas[i6 - 1] = -this._impulseResponse[i6 - 1];
                    double d2 = 1.0d / d;
                    int max = MathAssistant.max((int) (((((double) (this._lowpassOrder + 1)) / 2.0d) * MathAssistant.max(1.0d, d2)) + 10.0d), (int) (((((double) (this._lowpassOrder + 1)) / 2.0d) * MathAssistant.max(1.0d, d2)) + 10.0d));
                    this._inputOffset = max;
                    int max2 = MathAssistant.max((max * 2) + 10, 4096);
                    this._inputSize = max2;
                    int i8 = this._inputOffset;
                    this._input = new byte[((max2 + i8) * 2)];
                    this._inputPosition = i8;
                    this._inputRead = i8;
                    this._output = new byte[(((int) ((((double) max2) * d) + 2.0d)) * 2)];
                    this._outputPosition = 0;
                    this._time = (double) i8;
                    return;
                }
            }
        } else {
            throw new RuntimeException(new Exception("Mininum factor must be less than maximum factor."));
        }
    }

    public Resampler(int i, int i2) {
        this(((double) i2) / ((double) i));
    }

    public static void setDefaultLowPassOrder(int i) {
        _defaultLowPassOrder = i;
    }

    private void setFactor(double d) {
        this._factor = d;
    }

    private static float upsample(float[] fArr, float[] fArr2, int i, boolean z, byte[] bArr, int i2, double d, int i3) {
        double d2 = d * 4096.0d;
        int i4 = (int) d2;
        double floor = z ? d2 - MathAssistant.floor(d2) : 0.0d;
        if (i3 == 2) {
            i--;
            if (d2 == 0.0d) {
                i4 += 4096;
            }
        }
        int i5 = i4;
        float f = 0.0f;
        if (!z) {
            while (i4 < i) {
                f += fArr[i4] * floatFromShort(readPcmShort(bArr, i2));
                i4 += 4096;
                i2 += i3;
            }
            return f;
        }
        while (i4 < i) {
            i5 += 4096;
            f += (fArr[i4] + ((float) (((double) fArr2[i5]) * floor))) * floatFromShort(readPcmShort(bArr, i2));
            i4 += 4096;
            i2 += i3;
        }
        return f;
    }

    private int upsample(byte[] bArr, byte[] bArr2, double d, int i, int i2, float f, float[] fArr, float[] fArr2, boolean z) {
        double d2 = this._time;
        double d3 = 1.0d / d;
        double d4 = ((double) i) + d2;
        int i3 = 0;
        while (d2 < d4) {
            double floor = d2 - MathAssistant.floor(d2);
            int i4 = ((int) d2) * 2;
            float[] fArr3 = fArr;
            float[] fArr4 = fArr2;
            int i5 = i2;
            boolean z2 = z;
            byte[] bArr3 = bArr;
            writePcmShort(shortFromFloat((upsample(fArr, fArr2, i2, z, bArr, i4, floor, -2) + upsample(fArr3, fArr4, i5, z2, bArr3, i4 + 2, 1.0d - floor, 2)) * f), bArr2, i3);
            i3 += 2;
            d2 += d3;
        }
        this._time = d2;
        return i3;
    }

    private void writePcmShort(short s, byte[] bArr, int i) {
        bArr[i] = (byte) (s & 255);
        bArr[i + 1] = (byte) ((s >> 8) & 255);
    }
}
