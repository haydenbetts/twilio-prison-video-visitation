package fm.liveswitch;

import fm.liveswitch.vp8.Fragment;
import fm.liveswitch.vp8.Utility;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoBuffer extends MediaBuffer<VideoFormat, VideoBuffer> {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(VideoBuffer.class);
    private int __height;
    private boolean __isMuted;
    private int __orientation = 0;
    private int __width;
    private int[] _strides;

    private static int calculateByteCount(VideoFormat videoFormat, int i, int i2) {
        if (videoFormat.getIsYuvType()) {
            return (int) (((double) (i * i2)) * 1.5d);
        }
        if (videoFormat.getIsRgbType()) {
            return i * i2 * 3;
        }
        if (videoFormat.getIsRgbaType()) {
            return i * i2 * 4;
        }
        return -1;
    }

    private static boolean canPackWithoutCopy(DataBuffer[] dataBufferArr) {
        byte[] data = dataBufferArr[0].getData();
        int index = dataBufferArr[0].getIndex();
        int length = dataBufferArr[0].getLength();
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) dataBufferArr); i++) {
            DataBuffer dataBuffer = dataBufferArr[i];
            if (dataBuffer.getData() != data || dataBuffer.getIndex() != index + length) {
                return false;
            }
            index = dataBuffer.getIndex();
            length = dataBuffer.getLength();
        }
        return true;
    }

    private static int clamp(double d) {
        return MathAssistant.max(0, MathAssistant.min(255, (int) MathAssistant.round(d)));
    }

    public VideoBuffer clone() {
        VideoBuffer videoBuffer = (VideoBuffer) super.clone();
        videoBuffer.setStrides(getStrides());
        videoBuffer.setWidth(getWidth());
        videoBuffer.setHeight(getHeight());
        videoBuffer.setOrientation(getOrientation());
        videoBuffer.__isMuted = this.__isMuted;
        return videoBuffer;
    }

    public VideoBuffer convert(VideoFormat videoFormat) {
        return convert(videoFormat, false);
    }

    public VideoBuffer convert(VideoFormat videoFormat, DataBuffer dataBuffer) {
        if (!((VideoFormat) super.getFormat()).getIsRaw() || !videoFormat.getIsRaw()) {
            throw new RuntimeException(new Exception("Cannot convert to or from encoded formats."));
        }
        if (dataBuffer == null) {
            dataBuffer = DataBuffer.allocate(calculateByteCount(videoFormat, getWidth(), getHeight()));
        }
        VideoBuffer videoBuffer = new VideoBuffer(getWidth(), getHeight(), dataBuffer, videoFormat);
        if (((VideoFormat) super.getFormat()).getIsYuvType()) {
            Holder holder = new Holder(null);
            Holder holder2 = new Holder(null);
            Holder holder3 = new Holder(null);
            readYuv(holder, holder2, holder3);
            int[] iArr = (int[]) holder.getValue();
            int[] iArr2 = (int[]) holder2.getValue();
            int[] iArr3 = (int[]) holder3.getValue();
            if (videoFormat.getIsYuvType()) {
                videoBuffer.writeYuv(iArr, iArr2, iArr3);
                return videoBuffer;
            }
            Holder holder4 = new Holder(null);
            Holder holder5 = new Holder(null);
            Holder holder6 = new Holder(null);
            Holder holder7 = new Holder(null);
            convertYuvToRgba(iArr, iArr2, iArr3, holder4, holder5, holder6, holder7);
            videoBuffer.writeRgba((int[]) holder4.getValue(), (int[]) holder5.getValue(), (int[]) holder6.getValue(), (int[]) holder7.getValue());
            return videoBuffer;
        }
        Holder holder8 = new Holder(null);
        Holder holder9 = new Holder(null);
        Holder holder10 = new Holder(null);
        Holder holder11 = new Holder(null);
        readRgba(holder8, holder9, holder10, holder11);
        int[] iArr4 = (int[]) holder8.getValue();
        int[] iArr5 = (int[]) holder9.getValue();
        int[] iArr6 = (int[]) holder10.getValue();
        int[] iArr7 = (int[]) holder11.getValue();
        if (videoFormat.getIsYuvType()) {
            Holder holder12 = new Holder(null);
            Holder holder13 = new Holder(null);
            Holder holder14 = new Holder(null);
            convertRgbaToYuv(iArr4, iArr5, iArr6, iArr7, holder12, holder13, holder14);
            videoBuffer.writeYuv((int[]) holder12.getValue(), (int[]) holder13.getValue(), (int[]) holder14.getValue());
            return videoBuffer;
        }
        videoBuffer.writeRgba(iArr4, iArr5, iArr6, iArr7);
        return videoBuffer;
    }

    public VideoBuffer convert(VideoFormat videoFormat, boolean z) {
        if (videoFormat == null || ((VideoFormat) super.getFormat()).isEquivalent(videoFormat, false)) {
            return this;
        }
        DataBuffer dataBuffer = null;
        if (z) {
            dataBuffer = __dataBufferPool.take(calculateByteCount(videoFormat, getWidth(), getHeight()));
        }
        return convert(videoFormat, dataBuffer);
    }

    private void convertRgbaToYuv(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, Holder<int[]> holder, Holder<int[]> holder2, Holder<int[]> holder3) {
        int i;
        int i2;
        int i3;
        int width = getWidth() * getHeight();
        holder.setValue(new int[width]);
        int i4 = width / 4;
        holder2.setValue(new int[i4]);
        holder3.setValue(new int[i4]);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < getHeight()) {
            int i8 = 0;
            while (i8 < getWidth()) {
                holder.getValue()[i6] = clamp((((double) iArr[i6]) * 0.299d) + (((double) iArr2[i6]) * 0.587d) + (((double) iArr3[i6]) * 0.114d));
                if (i8 % 2 == 0 && i5 % 2 == 0) {
                    int i9 = iArr[i6];
                    int i10 = i6 + 1;
                    int i11 = iArr[i10];
                    int i12 = iArr[getWidth() + i6];
                    int i13 = iArr[getWidth() + i6 + 1];
                    int i14 = iArr2[i6];
                    int i15 = iArr2[i10];
                    int i16 = iArr2[getWidth() + i6];
                    int i17 = iArr2[i6 + getWidth() + 1];
                    int i18 = iArr3[i6];
                    int i19 = iArr3[i10];
                    int i20 = iArr3[i6 + getWidth()];
                    i3 = i5;
                    i2 = i6;
                    double d = (double) i9;
                    int i21 = i7;
                    i = i8;
                    double d2 = ((double) i14) * 0.5d * 0.587d;
                    double d3 = (((d * -0.5d) * 0.299d) / 0.886d) - (d2 / 0.886d);
                    double d4 = d2;
                    double d5 = ((double) i18) * 0.5d;
                    double d6 = d5;
                    double d7 = (double) i11;
                    double d8 = ((double) i15) * 0.5d * 0.587d;
                    int i22 = i21;
                    double d9 = ((double) i19) * 0.5d;
                    int i23 = i22;
                    double d10 = (double) i12;
                    int i24 = i23;
                    double d11 = ((double) i16) * 0.5d * 0.587d;
                    double d12 = (((d10 * -0.5d) * 0.299d) / 0.886d) - (d11 / 0.886d);
                    double d13 = d11;
                    double d14 = ((double) i20) * 0.5d;
                    double d15 = d14;
                    double d16 = (double) i13;
                    double d17 = ((double) i17) * 0.5d * 0.587d;
                    double d18 = ((double) iArr3[i6 + getWidth() + 1]) * 0.5d;
                    holder2.getValue()[i24] = clamp((((((d3 + d5) + 128.0d) + ((((((d7 * -0.5d) * 0.299d) / 0.886d) - (d8 / 0.886d)) + d9) + 128.0d)) + ((d12 + d14) + 128.0d)) + ((((((-0.5d * d16) * 0.299d) / 0.886d) - (d17 / 0.886d)) + d18) + 128.0d)) / 4.0d);
                    holder3.getValue()[i24] = clamp((((((((d * 0.5d) - (d4 / 0.7010000000000001d)) - ((d6 * 0.114d) / 0.7010000000000001d)) + 128.0d) + ((((d7 * 0.5d) - (d8 / 0.7010000000000001d)) - ((d9 * 0.114d) / 0.7010000000000001d)) + 128.0d)) + ((((d10 * 0.5d) - (d13 / 0.7010000000000001d)) - ((d15 * 0.114d) / 0.7010000000000001d)) + 128.0d)) + ((((d16 * 0.5d) - (d17 / 0.7010000000000001d)) - ((d18 * 0.114d) / 0.7010000000000001d)) + 128.0d)) / 4.0d);
                    i7 = i24 + 1;
                } else {
                    i3 = i5;
                    i2 = i6;
                    i = i8;
                    i7 = i7;
                }
                i6 = i2 + 1;
                i8 = i + 1;
                Holder<int[]> holder4 = holder;
                Holder<int[]> holder5 = holder2;
                Holder<int[]> holder6 = holder3;
                i5 = i3;
            }
            int i25 = i6;
            int i26 = i7;
            i5++;
            Holder<int[]> holder7 = holder;
            Holder<int[]> holder8 = holder2;
            Holder<int[]> holder9 = holder3;
        }
    }

    private void convertYuvToRgba(int[] iArr, int[] iArr2, int[] iArr3, Holder<int[]> holder, Holder<int[]> holder2, Holder<int[]> holder3, Holder<int[]> holder4) {
        int width = getWidth() * getHeight();
        holder.setValue(new int[width]);
        holder2.setValue(new int[width]);
        holder3.setValue(new int[width]);
        holder4.setValue(new int[width]);
        for (int i = 0; i < width; i++) {
            int i2 = iArr[i];
            int i3 = i / 4;
            int i4 = iArr2[i3];
            double d = (double) i2;
            double d2 = ((double) ((iArr3[i3] - 128) * 2)) * 0.7010000000000001d;
            holder.getValue()[i] = clamp(d + d2);
            double d3 = ((double) ((i4 - 128) * 2)) * 0.886d;
            holder2.getValue()[i] = clamp((d - ((0.114d * d3) / 0.587d)) - ((d2 * 0.299d) / 0.587d));
            holder3.getValue()[i] = clamp(d + d3);
            holder4.getValue()[i] = 255;
        }
    }

    public static VideoBuffer createBlack(int i, int i2, String str) {
        return createCustom(i, i2, 0, 0, 0, str);
    }

    public static VideoBuffer createBlue(int i, int i2, String str) {
        return createCustom(i, i2, 0, 0, 255, str);
    }

    public static VideoBuffer createCustom(int i, int i2, int i3, int i4, int i5, String str) {
        return createCustom(i, i2, i3, i4, i5, str, (DataBuffer) null);
    }

    public static VideoBuffer createCustom(int i, int i2, int i3, int i4, int i5, String str, DataBuffer dataBuffer) {
        int i6;
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        String str2 = str;
        int minimumBufferLength = getMinimumBufferLength(i7, i8, str2);
        if (minimumBufferLength != -1) {
            DataBuffer allocate = dataBuffer == null ? DataBuffer.allocate(minimumBufferLength) : dataBuffer;
            if (allocate.getLength() >= minimumBufferLength) {
                boolean isEqual = StringExtensions.isEqual(VideoFormat.getBgrName(), str2, StringComparison.OrdinalIgnoreCase);
                boolean z = isEqual || StringExtensions.isEqual(VideoFormat.getRgbName(), str2, StringComparison.OrdinalIgnoreCase);
                boolean isEqual2 = StringExtensions.isEqual(VideoFormat.getBgraName(), str2, StringComparison.OrdinalIgnoreCase);
                boolean isEqual3 = StringExtensions.isEqual(VideoFormat.getRgbaName(), str2, StringComparison.OrdinalIgnoreCase);
                boolean isEqual4 = StringExtensions.isEqual(VideoFormat.getAbgrName(), str2, StringComparison.OrdinalIgnoreCase);
                boolean isEqual5 = StringExtensions.isEqual(VideoFormat.getArgbName(), str2, StringComparison.OrdinalIgnoreCase);
                boolean z2 = isEqual2 || isEqual3 || isEqual4 || isEqual5;
                boolean isEqual6 = StringExtensions.isEqual(VideoFormat.getI420Name(), str2, StringComparison.OrdinalIgnoreCase);
                boolean z3 = isEqual5;
                boolean isEqual7 = StringExtensions.isEqual(VideoFormat.getYv12Name(), str2, StringComparison.OrdinalIgnoreCase);
                boolean z4 = isEqual4;
                boolean isEqual8 = StringExtensions.isEqual(VideoFormat.getNv12Name(), str2, StringComparison.OrdinalIgnoreCase);
                boolean z5 = isEqual3;
                boolean isEqual9 = StringExtensions.isEqual(VideoFormat.getNv21Name(), str2, StringComparison.OrdinalIgnoreCase);
                boolean z6 = isEqual6 || isEqual7 || isEqual8 || isEqual9;
                int i12 = i7 * i8;
                if (z) {
                    if (i9 == i10 && i9 == i11) {
                        allocate.set(BitAssistant.castByte(i3));
                    } else if (isEqual) {
                        int i13 = 0;
                        int i14 = 0;
                        while (i14 < i12) {
                            int i15 = i13 + 1;
                            allocate.write8(i11, i13);
                            int i16 = i15 + 1;
                            allocate.write8(i10, i15);
                            allocate.write8(i9, i16);
                            i14++;
                            i13 = i16 + 1;
                        }
                    } else {
                        int i17 = 0;
                        int i18 = 0;
                        while (i18 < i12) {
                            int i19 = i17 + 1;
                            allocate.write8(i9, i17);
                            int i20 = i19 + 1;
                            allocate.write8(i10, i19);
                            allocate.write8(i11, i20);
                            i18++;
                            i17 = i20 + 1;
                        }
                    }
                } else if (z2) {
                    if (isEqual2) {
                        int i21 = 0;
                        for (int i22 = 0; i22 < i12; i22++) {
                            int i23 = i21 + 1;
                            allocate.write8(i11, i21);
                            int i24 = i23 + 1;
                            allocate.write8(i10, i23);
                            int i25 = i24 + 1;
                            allocate.write8(i9, i24);
                            i21 = i25 + 1;
                            allocate.write8(255, i25);
                        }
                    } else if (z5) {
                        int i26 = 0;
                        for (int i27 = 0; i27 < i12; i27++) {
                            int i28 = i26 + 1;
                            allocate.write8(i9, i26);
                            int i29 = i28 + 1;
                            allocate.write8(i10, i28);
                            int i30 = i29 + 1;
                            allocate.write8(i11, i29);
                            i26 = i30 + 1;
                            allocate.write8(255, i30);
                        }
                    } else if (z4) {
                        int i31 = 0;
                        for (int i32 = 0; i32 < i12; i32++) {
                            int i33 = i31 + 1;
                            allocate.write8(255, i31);
                            int i34 = i33 + 1;
                            allocate.write8(i11, i33);
                            int i35 = i34 + 1;
                            allocate.write8(i10, i34);
                            i31 = i35 + 1;
                            allocate.write8(i9, i35);
                        }
                    } else if (z3) {
                        int i36 = 0;
                        for (int i37 = 0; i37 < i12; i37++) {
                            int i38 = i36 + 1;
                            allocate.write8(255, i36);
                            int i39 = i38 + 1;
                            allocate.write8(i9, i38);
                            int i40 = i39 + 1;
                            allocate.write8(i10, i39);
                            i36 = i40 + 1;
                            allocate.write8(i11, i40);
                        }
                    }
                } else if (z6) {
                    double d = (double) i9;
                    double d2 = (double) i10;
                    double d3 = (double) i11;
                    boolean z7 = isEqual9;
                    int i41 = (int) ((0.299d * d) + (0.587d * d2) + (0.110004d * d3));
                    int i42 = ((int) (((-0.168736d * d) - (0.331264d * d2)) + (d3 * 0.5d))) + 128;
                    int i43 = ((int) (((d * 0.5d) - (d2 * 0.418688d)) - (d3 * 0.081312d))) + 128;
                    int i44 = 0;
                    allocate.set(BitAssistant.castByte(i41), 0, i12);
                    if (isEqual6 || isEqual7) {
                        int i45 = i12 / 4;
                        int i46 = i12 + i45;
                        if (isEqual7) {
                            int i47 = i12;
                            i12 = i46;
                            i46 = i47;
                        }
                        allocate.set(BitAssistant.castByte(i42), i12, i45);
                        allocate.set(BitAssistant.castByte(i43), i46, i45);
                    } else {
                        int i48 = i12 / 2;
                        if (z7) {
                            i6 = 1;
                        } else {
                            i44 = 1;
                            i6 = 0;
                        }
                        for (int i49 = i12; i49 < i12 + i48; i49 += 2) {
                            allocate.write8(i12 + i6, i42);
                            allocate.write8(i12 + i44, i43);
                        }
                    }
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Could not create custom video buffer. Format '{0}' is not recognized.", (Object) str2)));
                }
                return new VideoBuffer(i, i2, allocate, new VideoFormat(str));
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Buffer too small. Minimum buffer length is {0} but buffer length is {1}.", IntegerExtensions.toString(Integer.valueOf(minimumBufferLength)), IntegerExtensions.toString(Integer.valueOf(allocate.getLength())))));
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Could not create custom video buffer. Format '{0}' is not supported.", (Object) str2)));
    }

    public static VideoBuffer createCyan(int i, int i2, String str) {
        return createCustom(i, i2, 0, 255, 255, str);
    }

    public static VideoBuffer createDarkBlue(int i, int i2, String str) {
        return createCustom(i, i2, 0, 0, 128, str);
    }

    public static VideoBuffer createDarkGreen(int i, int i2, String str) {
        return createCustom(i, i2, 0, 128, 0, str);
    }

    public static VideoBuffer createDarkRed(int i, int i2, String str) {
        return createCustom(i, i2, 128, 0, 0, str);
    }

    public static VideoBuffer createGray(int i, int i2, String str) {
        return createCustom(i, i2, 128, 128, 128, str);
    }

    public static VideoBuffer createGreen(int i, int i2, String str) {
        return createCustom(i, i2, 0, 255, 0, str);
    }

    /* access modifiers changed from: protected */
    public VideoBuffer createInstance() {
        return new VideoBuffer();
    }

    public static VideoBuffer createMagenta(int i, int i2, String str) {
        return createCustom(i, i2, 255, 0, 255, str);
    }

    public static VideoBuffer createOlive(int i, int i2, String str) {
        return createCustom(i, i2, 128, 128, 0, str);
    }

    public static VideoBuffer createPurple(int i, int i2, String str) {
        return createCustom(i, i2, 128, 0, 128, str);
    }

    public static VideoBuffer createRed(int i, int i2, String str) {
        return createCustom(i, i2, 255, 0, 0, str);
    }

    public static VideoBuffer createTeal(int i, int i2, String str) {
        return createCustom(i, i2, 0, 128, 128, str);
    }

    public static VideoBuffer createWhite(int i, int i2, String str) {
        return createCustom(i, i2, 255, 255, 255, str);
    }

    public static VideoBuffer createYellow(int i, int i2, String str) {
        return createCustom(i, i2, 255, 255, 0, str);
    }

    public static VideoBuffer fromJson(String str) {
        return (VideoBuffer) JsonSerializer.deserializeObject(str, new IFunction0<VideoBuffer>() {
            public VideoBuffer invoke() {
                return new VideoBuffer();
            }
        }, new IAction3<VideoBuffer, String, String>() {
            public void invoke(VideoBuffer videoBuffer, String str, String str2) {
                if (str.equals("width")) {
                    videoBuffer.setWidth(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("height")) {
                    videoBuffer.setHeight(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("strides")) {
                    videoBuffer.setStrides(JsonSerializer.deserializeIntegerArray(str2));
                } else if (str.equals("orientation")) {
                    videoBuffer.setOrientation(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("isMuted")) {
                    videoBuffer.setIsMuted(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("dataBuffers")) {
                    videoBuffer.setDataBuffers(DataBuffer.fromJsonArray(str2));
                } else if (str.equals("format")) {
                    videoBuffer.setFormat(VideoFormat.fromJson(str2));
                }
            }
        });
    }

    public static VideoBuffer[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, VideoBuffer>() {
            public String getId() {
                return "fm.liveswitch.VideoBuffer.fromJson";
            }

            public VideoBuffer invoke(String str) {
                return VideoBuffer.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (VideoBuffer[]) deserializeObjectArray.toArray(new VideoBuffer[0]);
    }

    public int getAValue(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgbaType()) {
            return super.getDataBuffer().read8(getAValueOffset(i));
        }
        return 255;
    }

    private int getAValueOffset(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgba() || ((VideoFormat) super.getFormat()).getIsBgra()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 3;
        }
        if (((VideoFormat) super.getFormat()).getIsArgb() || ((VideoFormat) super.getFormat()).getIsAbgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4);
        }
        return -1;
    }

    public int getBValue(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgbType() || ((VideoFormat) super.getFormat()).getIsRgbaType()) {
            return super.getDataBuffer().read8(getBValueOffset(i));
        }
        return -1;
    }

    private int getBValueOffset(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgb()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 3) + 2;
        }
        if (((VideoFormat) super.getFormat()).getIsBgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 3);
        }
        if (((VideoFormat) super.getFormat()).getIsRgba()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 2;
        }
        if (((VideoFormat) super.getFormat()).getIsBgra()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4);
        }
        if (((VideoFormat) super.getFormat()).getIsArgb()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 3;
        }
        if (((VideoFormat) super.getFormat()).getIsAbgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 1;
        }
        return -1;
    }

    private int[] getDefaultStrides(int i, VideoFormat videoFormat) {
        if (videoFormat.getIsRgbType()) {
            return new int[]{i * 3};
        } else if (videoFormat.getIsRgbaType()) {
            return new int[]{i * 4};
        } else if (videoFormat.getIsI420() || videoFormat.getIsYv12()) {
            if (getIsPacked()) {
                return new int[]{i};
            }
            int i2 = i / 2;
            return new int[]{i, i2, i2};
        } else if (videoFormat.getIsNv12() || videoFormat.getIsNv21()) {
            if (getIsPacked()) {
                return new int[]{i};
            }
            return new int[]{i, i};
        } else if (Global.equals(videoFormat.getName(), VideoFormat.getVp8Name()) || Global.equals(videoFormat.getName(), VideoFormat.getVp9Name()) || Global.equals(videoFormat.getName(), VideoFormat.getH264Name()) || Global.equals(videoFormat.getName(), MediaFormat.getRedName()) || Global.equals(videoFormat.getName(), MediaFormat.getUlpFecName())) {
            return new int[1];
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Unsupported video format '", videoFormat.getName(), "'.")));
        }
    }

    public int getGValue(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgbType() || ((VideoFormat) super.getFormat()).getIsRgbaType()) {
            return super.getDataBuffer().read8(getGValueOffset(i));
        }
        return -1;
    }

    private int getGValueOffset(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgb() || ((VideoFormat) super.getFormat()).getIsBgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 3) + 1;
        }
        if (((VideoFormat) super.getFormat()).getIsRgba() || ((VideoFormat) super.getFormat()).getIsBgra()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 1;
        }
        if (((VideoFormat) super.getFormat()).getIsArgb() || ((VideoFormat) super.getFormat()).getIsAbgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 2;
        }
        return -1;
    }

    public int getHeight() {
        return this.__height;
    }

    public boolean getIsAbgr() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsAbgr();
    }

    public boolean getIsArgb() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsArgb();
    }

    public boolean getIsBgr() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsBgr();
    }

    public boolean getIsBgra() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsBgra();
    }

    public boolean getIsH264() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsH264();
    }

    public boolean getIsI420() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsI420();
    }

    public boolean getIsKeyFrame() {
        if (!((VideoFormat) super.getFormat()).getIsVp8() && !((VideoFormat) super.getFormat()).getIsVp9() && !((VideoFormat) super.getFormat()).getIsH264()) {
            return true;
        }
        DataBuffer dataBuffer = super.getDataBuffer();
        if (dataBuffer == null) {
            return false;
        }
        if (((VideoFormat) super.getFormat()).getIsPacketized()) {
            VideoFragment videoFragment = null;
            if (((VideoFormat) super.getFormat()).getIsVp8()) {
                videoFragment = new Fragment(super.getRtpHeader(), dataBuffer);
                if (!videoFragment.getFirst()) {
                    return false;
                }
            } else if (((VideoFormat) super.getFormat()).getIsVp9()) {
                videoFragment = new fm.liveswitch.vp9.Fragment(super.getRtpHeader(), dataBuffer);
                if (!videoFragment.getFirst()) {
                    return false;
                }
            } else if (((VideoFormat) super.getFormat()).getIsH264()) {
                videoFragment = new fm.liveswitch.h264.Fragment(super.getRtpHeader(), dataBuffer);
            }
            dataBuffer = videoFragment.getBuffer();
        }
        if (((VideoFormat) super.getFormat()).getIsVp8()) {
            return Utility.isKeyFrame(dataBuffer);
        }
        if (((VideoFormat) super.getFormat()).getIsVp9()) {
            return fm.liveswitch.vp9.Utility.isKeyFrame(dataBuffer);
        }
        if (((VideoFormat) super.getFormat()).getIsH264()) {
            return fm.liveswitch.h264.Utility.isKeyFrame(dataBuffer);
        }
        return true;
    }

    public boolean getIsMuted() {
        return this.__isMuted;
    }

    public boolean getIsNv12() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsNv12();
    }

    public boolean getIsNv21() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsNv21();
    }

    public boolean getIsPacked() {
        return ArrayExtensions.getLength((Object[]) super.getDataBuffers()) == 1;
    }

    public boolean getIsPlanar() {
        return ArrayExtensions.getLength((Object[]) super.getDataBuffers()) > 1;
    }

    public boolean getIsRaw() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsRaw();
    }

    public boolean getIsRgb() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsRgb();
    }

    public boolean getIsRgba() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsRgba();
    }

    public boolean getIsRgbaType() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsRgbaType();
    }

    public boolean getIsRgbType() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsRgbType();
    }

    public boolean getIsVp8() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsVp8();
    }

    public boolean getIsVp9() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsVp9();
    }

    public boolean getIsYuvType() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsYuvType();
    }

    public boolean getIsYv12() {
        VideoFormat videoFormat = (VideoFormat) super.getFormat();
        return videoFormat != null && videoFormat.getIsYv12();
    }

    public static int getMinimumBufferLength(int i, int i2, String str) {
        int i3 = i * i2;
        if (Global.equals(str, VideoFormat.getBgrName()) || Global.equals(str, VideoFormat.getRgbName())) {
            return i3 * 3;
        }
        if (Global.equals(str, VideoFormat.getBgraName()) || Global.equals(str, VideoFormat.getRgbaName()) || Global.equals(str, VideoFormat.getAbgrName()) || Global.equals(str, VideoFormat.getArgbName())) {
            return i3 * 4;
        }
        if (Global.equals(str, VideoFormat.getNv12Name()) || Global.equals(str, VideoFormat.getNv21Name()) || Global.equals(str, VideoFormat.getI420Name()) || Global.equals(str, VideoFormat.getYv12Name())) {
            return (int) (((double) i3) * 1.5d);
        }
        Log.error(StringExtensions.format("Could not get minimum buffer length. Format '{0}' is not supported.", (Object) str));
        return -1;
    }

    public int getOrientation() {
        return this.__orientation;
    }

    public int getRValue(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgbType() || ((VideoFormat) super.getFormat()).getIsRgbaType()) {
            return super.getDataBuffer().read8(getRValueOffset(i));
        }
        return -1;
    }

    private int getRValueOffset(int i) {
        if (((VideoFormat) super.getFormat()).getIsRgb()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 3);
        }
        if (((VideoFormat) super.getFormat()).getIsBgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 3) + 2;
        }
        if (((VideoFormat) super.getFormat()).getIsRgba()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4);
        }
        if (((VideoFormat) super.getFormat()).getIsBgra()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 2;
        }
        if (((VideoFormat) super.getFormat()).getIsArgb()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 1;
        }
        if (((VideoFormat) super.getFormat()).getIsAbgr()) {
            return ((i / getWidth()) * getStride()) + ((i % getWidth()) * 4) + 3;
        }
        return -1;
    }

    public int getStride() {
        int[] strides = getStrides();
        if (strides == null) {
            return 0;
        }
        return strides[0];
    }

    public int[] getStrides() {
        return this._strides;
    }

    public int getUValue(int i) {
        if (!((VideoFormat) super.getFormat()).getIsYuvType()) {
            return -1;
        }
        if (getIsPacked()) {
            return super.getDataBuffers()[0].read8(getUValueOffset(i));
        }
        if (getIsYv12()) {
            return super.getDataBuffers()[2].read8(getUValueOffset(i));
        }
        return super.getDataBuffers()[1].read8(getUValueOffset(i));
    }

    private int getUValueOffset(int i) {
        if (getIsPacked()) {
            if (getIsI420() || getIsYv12()) {
                int width = getWidth() / 2;
                int stride = ((i / width) * (getStride() / 2)) + (i % width);
                if (getIsI420()) {
                    return (getHeight() * getStride()) + stride;
                }
                return (((getHeight() * getStride()) * 5) / 4) + stride;
            }
            int i2 = i * 2;
            int width2 = ((i2 / getWidth()) * getStride()) + (i2 % getWidth());
            if (getIsNv12()) {
                return (getHeight() * getStride()) + width2;
            }
            return (getHeight() * getStride()) + width2 + 1;
        } else if (getIsI420() || getIsYv12()) {
            int width3 = getWidth() / 2;
            if (getIsI420()) {
                return ((i / width3) * getStrides()[1]) + (i % width3);
            }
            return ((i / width3) * getStrides()[2]) + (i % width3);
        } else {
            int i3 = i * 2;
            int width4 = ((i3 / getWidth()) * getStrides()[1]) + (i3 % getWidth());
            return getIsNv12() ? width4 : width4 + 1;
        }
    }

    public int getVValue(int i) {
        if (!((VideoFormat) super.getFormat()).getIsYuvType()) {
            return -1;
        }
        if (getIsPacked()) {
            return super.getDataBuffers()[0].read8(getVValueOffset(i));
        }
        if (getIsI420()) {
            return super.getDataBuffers()[2].read8(getVValueOffset(i));
        }
        return super.getDataBuffers()[1].read8(getVValueOffset(i));
    }

    private int getVValueOffset(int i) {
        if (getIsPacked()) {
            if (getIsI420() || getIsYv12()) {
                int width = getWidth() / 2;
                int stride = ((i / width) * (getStride() / 2)) + (i % width);
                if (getIsI420()) {
                    return (((getHeight() * getStride()) * 5) / 4) + stride;
                }
                return (getHeight() * getStride()) + stride;
            }
            int i2 = i * 2;
            int width2 = ((i2 / getWidth()) * getStride()) + (i2 % getWidth());
            if (getIsNv12()) {
                return (getHeight() * getStride()) + width2 + 1;
            }
            return (getHeight() * getStride()) + width2;
        } else if (getIsI420() || getIsYv12()) {
            int width3 = getWidth() / 2;
            if (getIsI420()) {
                return ((i / width3) * getStrides()[2]) + (i % width3);
            }
            return ((i / width3) * getStrides()[1]) + (i % width3);
        } else {
            int i3 = i * 2;
            int width4 = ((i3 / getWidth()) * getStrides()[1]) + (i3 % getWidth());
            return getIsNv12() ? width4 + 1 : width4;
        }
    }

    public int getWidth() {
        return this.__width;
    }

    public int getYValue(int i) {
        if (((VideoFormat) super.getFormat()).getIsYuvType()) {
            return super.getDataBuffer().read8(getYValueOffset(i));
        }
        return -1;
    }

    private int getYValueOffset(int i) {
        return ((i / getWidth()) * getStride()) + (i % getWidth());
    }

    public boolean mute() {
        int i;
        int i2;
        if (!this.__isMuted) {
            if (((VideoFormat) super.getFormat()).getIsRgbType()) {
                super.getDataBuffer().set((byte) 0);
            } else if (((VideoFormat) super.getFormat()).getIsRgbaType()) {
                DataBuffer dataBuffer = super.getDataBuffer();
                if (((VideoFormat) super.getFormat()).getIsBgra() || ((VideoFormat) super.getFormat()).getIsRgba()) {
                    i2 = 0;
                    i = 3;
                } else {
                    i2 = 1;
                    i = 0;
                }
                for (int i3 = 0; i3 < dataBuffer.getLength(); i3 += 4) {
                    dataBuffer.set((byte) 0, i3 + i2, 3);
                    dataBuffer.set(BitAssistant.castByte(255), i3 + i, 1);
                }
            } else if (!((VideoFormat) super.getFormat()).getIsYuvType()) {
                return false;
            } else {
                DataBuffer[] dataBuffers = super.getDataBuffers();
                if (getIsPacked()) {
                    int width = getWidth() * getHeight();
                    DataBuffer dataBuffer2 = dataBuffers[0];
                    dataBuffer2.set((byte) 0, 0, width);
                    dataBuffer2.set(BitAssistant.castByte(128), width);
                } else if (getIsPlanar()) {
                    dataBuffers[0].set((byte) 0);
                    for (int i4 = 1; i4 < ArrayExtensions.getLength((Object[]) dataBuffers); i4++) {
                        dataBuffers[i4].set(BitAssistant.castByte(128));
                    }
                }
            }
            this.__isMuted = true;
        }
        return true;
    }

    private void readRgba(Holder<int[]> holder, Holder<int[]> holder2, Holder<int[]> holder3, Holder<int[]> holder4) {
        int width = getWidth() * getHeight();
        holder.setValue(new int[width]);
        holder2.setValue(new int[width]);
        holder3.setValue(new int[width]);
        holder4.setValue(new int[width]);
        for (int i = 0; i < width; i++) {
            holder.getValue()[i] = getRValue(i);
            holder2.getValue()[i] = getGValue(i);
            holder3.getValue()[i] = getBValue(i);
            holder4.getValue()[i] = getAValue(i);
        }
    }

    private void readYuv(Holder<int[]> holder, Holder<int[]> holder2, Holder<int[]> holder3) {
        int width = getWidth() * getHeight();
        holder.setValue(new int[width]);
        int i = width / 4;
        holder2.setValue(new int[i]);
        holder3.setValue(new int[i]);
        for (int i2 = 0; i2 < width; i2++) {
            holder.getValue()[i2] = getYValue(i2);
        }
        for (int i3 = 0; i3 < i; i3++) {
            holder2.getValue()[i3] = getUValue(i3);
            holder3.getValue()[i3] = getVValue(i3);
        }
    }

    public boolean setAValue(int i, int i2) {
        return ((VideoFormat) super.getFormat()).getIsRgbaType() && super.getDataBuffer().write8(i, getAValueOffset(i2));
    }

    public boolean setBValue(int i, int i2) {
        return (((VideoFormat) super.getFormat()).getIsRgbType() || ((VideoFormat) super.getFormat()).getIsRgbaType()) && super.getDataBuffer().write8(i, getBValueOffset(i2));
    }

    public boolean setGValue(int i, int i2) {
        return (((VideoFormat) super.getFormat()).getIsRgbType() || ((VideoFormat) super.getFormat()).getIsRgbaType()) && super.getDataBuffer().write8(i, getGValueOffset(i2));
    }

    public void setHeight(int i) {
        if (i != -1 && i % 2 > 0) {
            i--;
        }
        this.__height = i;
    }

    /* access modifiers changed from: package-private */
    public void setIsMuted(boolean z) {
        this.__isMuted = z;
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 90 || i == 180 || i == 270) {
            this.__orientation = i;
            return;
        }
        throw new RuntimeException(new Exception("Video orientation can only be 0, 90, 180 or 270."));
    }

    public boolean setRValue(int i, int i2) {
        return (((VideoFormat) super.getFormat()).getIsRgbType() || ((VideoFormat) super.getFormat()).getIsRgbaType()) && super.getDataBuffer().write8(i, getRValueOffset(i2));
    }

    public void setStride(int i) {
        setStrides(new int[]{i});
    }

    public void setStrides(int[] iArr) {
        this._strides = iArr;
    }

    public boolean setUValue(int i, int i2) {
        if (!((VideoFormat) super.getFormat()).getIsYuvType()) {
            return false;
        }
        if (getIsPacked()) {
            return super.getDataBuffers()[0].write8(i, getUValueOffset(i2));
        }
        if (getIsYv12()) {
            return super.getDataBuffers()[2].write8(i, getUValueOffset(i2));
        }
        return super.getDataBuffers()[1].write8(i, getUValueOffset(i2));
    }

    public boolean setVValue(int i, int i2) {
        if (!((VideoFormat) super.getFormat()).getIsYuvType()) {
            return false;
        }
        if (getIsPacked()) {
            return super.getDataBuffers()[0].write8(i, getVValueOffset(i2));
        }
        if (getIsI420()) {
            return super.getDataBuffers()[2].write8(i, getVValueOffset(i2));
        }
        return super.getDataBuffers()[1].write8(i, getVValueOffset(i2));
    }

    public void setWidth(int i) {
        if (i != -1 && i % 2 > 0) {
            i--;
        }
        this.__width = i;
    }

    public boolean setYValue(int i, int i2) {
        return ((VideoFormat) super.getFormat()).getIsYuvType() && super.getDataBuffer().write8(i, getYValueOffset(i2));
    }

    public static String toJson(VideoBuffer videoBuffer) {
        return JsonSerializer.serializeObject(videoBuffer, new IAction2<VideoBuffer, HashMap<String, String>>(videoBuffer) {
            final /* synthetic */ VideoBuffer val$videoBuffer;

            {
                this.val$videoBuffer = r1;
            }

            public void invoke(VideoBuffer videoBuffer, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "width", JsonSerializer.serializeInteger(new NullableInteger(this.val$videoBuffer.getWidth())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "height", JsonSerializer.serializeInteger(new NullableInteger(this.val$videoBuffer.getHeight())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "strides", JsonSerializer.serializeIntegerArray(this.val$videoBuffer.getStrides()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "orientation", JsonSerializer.serializeInteger(new NullableInteger(this.val$videoBuffer.getOrientation())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isMuted", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$videoBuffer.getIsMuted())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataBuffers", DataBuffer.toJsonArray(this.val$videoBuffer.getDataBuffers()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "format", VideoFormat.toJson((VideoFormat) this.val$videoBuffer.getFormat()));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(VideoBuffer[] videoBufferArr) {
        return JsonSerializer.serializeObjectArray(videoBufferArr, new IFunctionDelegate1<VideoBuffer, String>() {
            public String getId() {
                return "fm.liveswitch.VideoBuffer.toJson";
            }

            public String invoke(VideoBuffer videoBuffer) {
                return VideoBuffer.toJson(videoBuffer);
            }
        });
    }

    public VideoBuffer toPacked() {
        return toPacked(false);
    }

    public VideoBuffer toPacked(boolean z) {
        DataBuffer dataBuffer;
        if (!getIsYuvType() || ArrayExtensions.getLength((Object[]) super.getDataBuffers()) <= 1) {
            return this;
        }
        VideoBuffer clone = clone();
        DataBuffer[] dataBuffers = clone.getDataBuffers();
        int[] strides = clone.getStrides();
        if (canPackWithoutCopy(dataBuffers)) {
            int i = 0;
            for (DataBuffer length : dataBuffers) {
                i += length.getLength();
            }
            DataBuffer dataBuffer2 = dataBuffers[0];
            dataBuffer2.setLength(i);
            clone.setDataBuffer(dataBuffer2);
        } else {
            int height = ((strides[0] * clone.getHeight()) * 3) / 2;
            if (z) {
                dataBuffer = __dataBufferPool.take(height);
            } else {
                dataBuffer = DataBuffer.allocate(height);
            }
            int i2 = 0;
            for (DataBuffer write : dataBuffers) {
                IntegerHolder integerHolder = new IntegerHolder(i2);
                dataBuffer.write(write, i2, integerHolder);
                i2 = integerHolder.getValue();
            }
            clone.setDataBuffer(dataBuffer);
        }
        clone.setStride(strides[0]);
        return clone;
    }

    public VideoBuffer toPlanar() {
        if (!getIsYuvType() || ArrayExtensions.getLength((Object[]) super.getDataBuffers()) > 1) {
            return this;
        }
        VideoBuffer clone = clone();
        DataBuffer dataBuffer = clone.getDataBuffer();
        int stride = clone.getStride();
        if (clone.getIsI420() || clone.getIsYv12()) {
            int i = stride / 2;
            int height = clone.getHeight() * stride;
            int i2 = height / 4;
            if (clone.getIsI420()) {
                clone.setDataBuffers(new DataBuffer[]{dataBuffer.subset(0, height), dataBuffer.subset(height, i2), dataBuffer.subset(height + i2, i2)});
                clone.setStrides(new int[]{stride, i, i});
            } else {
                clone.setDataBuffers(new DataBuffer[]{dataBuffer.subset(0, height), dataBuffer.subset(height, i2), dataBuffer.subset(height + i2, i2)});
                clone.setStrides(new int[]{stride, i, i});
            }
            return clone;
        }
        int height2 = clone.getHeight() * stride;
        clone.setDataBuffers(new DataBuffer[]{dataBuffer.subset(0, height2), dataBuffer.subset(height2, height2 / 2)});
        clone.setStrides(new int[]{stride, stride});
        return clone;
    }

    protected VideoBuffer() {
    }

    public VideoBuffer(int i, int i2, DataBuffer dataBuffer, VideoFormat videoFormat) {
        super(dataBuffer, videoFormat);
        setWidth(i);
        setHeight(i2);
        setStrides(getDefaultStrides(i, videoFormat));
    }

    public VideoBuffer(int i, int i2, DataBuffer[] dataBufferArr, VideoFormat videoFormat) {
        super(dataBufferArr, videoFormat);
        setWidth(i);
        setHeight(i2);
        setStrides(getDefaultStrides(i, videoFormat));
    }

    public VideoBuffer(int i, int i2, int i3, DataBuffer dataBuffer, VideoFormat videoFormat) {
        super(dataBuffer, videoFormat);
        setWidth(i);
        setHeight(i2);
        setStride(i3);
    }

    public VideoBuffer(int i, int i2, int[] iArr, DataBuffer[] dataBufferArr, VideoFormat videoFormat) {
        super(dataBufferArr, videoFormat);
        setWidth(i);
        setHeight(i2);
        setStrides(iArr);
    }

    private void writeRgba(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int width = getWidth() * getHeight();
        for (int i = 0; i < width; i++) {
            setRValue(iArr[i], i);
            setGValue(iArr2[i], i);
            setBValue(iArr3[i], i);
            setAValue(iArr4[i], i);
        }
    }

    private void writeYuv(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            setYValue(iArr[i], i);
        }
        for (int i2 = 0; i2 < ArrayExtensions.getLength(iArr2); i2++) {
            setUValue(iArr2[i2], i2);
        }
        for (int i3 = 0; i3 < ArrayExtensions.getLength(iArr3); i3++) {
            setVValue(iArr3[i3], i3);
        }
    }
}
