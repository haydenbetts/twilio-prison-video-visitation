package fm.liveswitch;

import kotlin.UByte;

public class SoundUtility {
    private static ILog __log = Log.getLogger(SoundUtility.class);

    public static int calculateDataLengthFromTimestampDelta(int i, int i2, int i3) {
        return i * i2 * i3;
    }

    private static int calculateDataLengthPrecise(double d, int i, int i2, int i3) {
        return (int) ((((d * ((double) i)) * ((double) i2)) * ((double) i3)) / 1000.0d);
    }

    public static float floatFromShort(short s) {
        float f = ((float) s) / 32768.0f;
        if (f > 1.0f) {
            return 1.0f;
        }
        if (f < -1.0f) {
            return -1.0f;
        }
        return f;
    }

    public static short shortFromFloat(float f) {
        float f2 = f * 32768.0f;
        if (f2 > 32767.0f) {
            f2 = 32767.0f;
        }
        if (f2 < -32768.0f) {
            f2 = -32768.0f;
        }
        return (short) ((int) f2);
    }

    public static int calculateDataLength(int i, int i2, int i3) {
        return calculateDataLength(i, i2, i3, 2);
    }

    private static int calculateDataLength(int i, int i2, int i3, int i4) {
        return (((i * i2) * i3) * i4) / 1000;
    }

    public static int calculateDataLength(int i, AudioConfig audioConfig) {
        return calculateDataLength(i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static int calculateDataLengthFloat(int i, int i2, int i3) {
        return calculateDataLength(i, i2, i3, 4);
    }

    public static int calculateDataLengthFloat(int i, AudioConfig audioConfig) {
        return calculateDataLengthFloat(i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static int calculateDataLengthFloatPrecise(double d, int i, int i2) {
        return calculateDataLengthPrecise(d, i, i2, 4);
    }

    public static int calculateDataLengthFloatPrecise(double d, AudioConfig audioConfig) {
        return calculateDataLengthFloatPrecise(d, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static int calculateDataLengthFromTimestampDelta(int i, int i2) {
        return calculateDataLengthFromTimestampDelta(i, i2, 2);
    }

    public static int calculateDataLengthPrecise(double d, int i, int i2) {
        return calculateDataLengthPrecise(d, i, i2, 2);
    }

    public static int calculateDataLengthPrecise(double d, AudioConfig audioConfig) {
        return calculateDataLengthPrecise(d, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static int calculateDuration(int i, int i2, int i3) {
        return calculateDuration(i, i2, i3, 2);
    }

    private static int calculateDuration(int i, int i2, int i3, int i4) {
        return (i * 1000) / ((i2 * i3) * i4);
    }

    public static int calculateDuration(int i, AudioConfig audioConfig) {
        return calculateDuration(i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static int calculateDurationFloat(int i, int i2, int i3) {
        return calculateDuration(i, i2, i3, 4);
    }

    public static int calculateDurationFloat(int i, AudioConfig audioConfig) {
        return calculateDurationFloat(i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static int calculateDurationFromSystemTimestampDelta(int i) {
        return i / Constants.getTicksPerMillisecond();
    }

    public static int calculateDurationFromTimestampDelta(int i, int i2) {
        return (i * Constants.getMillisecondsPerSecond()) / i2;
    }

    public static int calculateOutputLengthForChannelCount(int i, int i2, int i3) {
        return (i * i3) / i2;
    }

    public static int calculateSystemTimestampDeltaFromDuration(int i) {
        return i * Constants.getTicksPerMillisecond();
    }

    public static long calculateSystemTimestampDeltaFromTimestampDelta(long j, int i) {
        return (j * ((long) Constants.getTicksPerSecond())) / ((long) i);
    }

    public static int calculateTimestampDeltaFromDataLength(int i, int i2) {
        return calculateTimestampDeltaFromDataLength(i, i2, 2);
    }

    public static int calculateTimestampDeltaFromDataLength(int i, int i2, int i3) {
        return i / (i2 * i3);
    }

    public static int calculateTimestampDeltaFromDuration(int i, int i2) {
        return (i * i2) / Constants.getMillisecondsPerSecond();
    }

    public static long calculateTimestampDeltaFromSystemTimestampDelta(long j, int i) {
        return (j * ((long) i)) / ((long) Constants.getTicksPerSecond());
    }

    public static DataBuffer convertChannelCount(DataBuffer dataBuffer, int i, DataBuffer dataBuffer2, int i2) {
        DataBuffer dataBuffer3 = dataBuffer;
        int i3 = i;
        DataBuffer dataBuffer4 = dataBuffer2;
        int i4 = i2;
        if (i3 == i4) {
            return dataBuffer3;
        }
        int i5 = i3 * 2;
        int i6 = i4 * 2;
        if (dataBuffer.getLength() % i5 != 0) {
            __log.error(StringExtensions.format("Cannot convert channel count from {0} to {1} as the input buffer length {2} is not evenly divisible by the input frame length {3}.", new Object[]{IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(dataBuffer.getLength())), IntegerExtensions.toString(Integer.valueOf(i5))}));
            return null;
        } else if (dataBuffer2.getLength() % i6 != 0) {
            __log.error(StringExtensions.format("Cannot convert channel count from {0} to {1} as the output buffer length {2} is not evenly divisible by the output frame length {3}.", new Object[]{IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(dataBuffer2.getLength())), IntegerExtensions.toString(Integer.valueOf(i6))}));
            return null;
        } else if ((i3 <= i4 || i3 % i4 == 0) && (i4 <= i3 || i4 % i3 == 0)) {
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i4];
            int length = dataBuffer.getLength() / i5;
            int i7 = 0;
            int i8 = 0;
            for (int i9 = 0; i9 < length; i9++) {
                for (int i10 = 0; i10 < i3; i10++) {
                    iArr[i10] = dataBuffer3.read16Signed(i7);
                    i7 += 2;
                }
                if (i3 > i4) {
                    int i11 = i3 / i4;
                    int i12 = 0;
                    for (int i13 = 0; i13 < ArrayExtensions.getLength(iArr2); i13++) {
                        int i14 = 0;
                        int i15 = 0;
                        while (i14 < i11) {
                            i15 += iArr[i12];
                            i14++;
                            i12++;
                        }
                        iArr2[i13] = i15 / i11;
                    }
                } else if (i4 > i3) {
                    int i16 = i4 / i3;
                    int i17 = 0;
                    for (int i18 = 0; i18 < ArrayExtensions.getLength(iArr); i18++) {
                        int i19 = iArr[i18];
                        int i20 = 0;
                        while (i20 < i16) {
                            iArr2[i17] = i19;
                            i20++;
                            i17++;
                        }
                    }
                }
                for (int i21 = 0; i21 < i4; i21++) {
                    dataBuffer4.write16(iArr2[i21], i8);
                    i8 += 2;
                }
            }
            return dataBuffer4;
        } else {
            __log.error(StringExtensions.format("Cannot convert channel count from {0} to {1} as they are not evenly divisible.", IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(i2))));
            return null;
        }
    }

    public static DataBuffer monoToStereo(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        return convertChannelCount(dataBuffer, 1, dataBuffer2, 2);
    }

    public static short readPcmShort(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & UByte.MAX_VALUE) << 8) | (bArr[i] & UByte.MAX_VALUE));
    }

    public static DataBuffer stereoToMono(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        return convertChannelCount(dataBuffer, 2, dataBuffer2, 1);
    }

    public static void writePcmShort(short s, byte[] bArr, int i) {
        bArr[i] = (byte) (s & 255);
        bArr[i + 1] = (byte) ((s >> 8) & 255);
    }
}
