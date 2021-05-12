package org.bytedeco.javacv;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.ImageObserver;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Hashtable;
import kotlin.UByte;
import kotlin.UShort;

public class Java2DFrameConverter extends FrameConverter<BufferedImage> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final byte[] gamma22 = new byte[256];
    public static final byte[] gamma22inv = new byte[256];
    protected BufferedImage bufferedImage = null;

    public Frame convert(BufferedImage bufferedImage2) {
        return getFrame(bufferedImage2);
    }

    public BufferedImage convert(Frame frame) {
        return getBufferedImage(frame);
    }

    public static BufferedImage cloneBufferedImage(BufferedImage bufferedImage2) {
        if (bufferedImage2 == null) {
            return null;
        }
        int type = bufferedImage2.getType();
        if (type == 0) {
            return new BufferedImage(bufferedImage2.getColorModel(), bufferedImage2.copyData((WritableRaster) null), bufferedImage2.isAlphaPremultiplied(), (Hashtable) null);
        }
        BufferedImage bufferedImage3 = new BufferedImage(bufferedImage2.getWidth(), bufferedImage2.getHeight(), type);
        Graphics graphics = bufferedImage3.getGraphics();
        graphics.drawImage(bufferedImage2, 0, 0, (ImageObserver) null);
        graphics.dispose();
        return bufferedImage3;
    }

    static {
        for (int i = 0; i < 256; i++) {
            double d = ((double) i) / 255.0d;
            gamma22[i] = (byte) ((int) Math.round(Math.pow(d, 2.2d) * 255.0d));
            gamma22inv[i] = (byte) ((int) Math.round(Math.pow(d, 0.45454545454545453d) * 255.0d));
        }
    }

    public static int decodeGamma22(int i) {
        return gamma22[i & 255] & UByte.MAX_VALUE;
    }

    public static int encodeGamma22(int i) {
        return gamma22inv[i & 255] & UByte.MAX_VALUE;
    }

    public static void flipCopyWithGamma(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3, int i4, boolean z, double d, boolean z2, int i5) {
        int i6;
        byte[] bArr;
        byte b;
        byte b2;
        int i7;
        byte[] bArr2;
        byte b3;
        int i8;
        byte b4;
        ByteBuffer byteBuffer3 = byteBuffer;
        int i9 = i2;
        ByteBuffer byteBuffer4 = byteBuffer2;
        int i10 = i4;
        double d2 = d;
        int i11 = i5;
        byte[] bArr3 = new byte[i11];
        int i12 = i3;
        int min = Math.min(i9, i10);
        int i13 = i;
        while (i13 < byteBuffer.capacity() && i12 < byteBuffer2.capacity()) {
            int capacity = z2 ? (byteBuffer.capacity() - i13) - i9 : i13;
            min = Math.min(Math.min(min, byteBuffer.capacity() - capacity), byteBuffer2.capacity() - i12);
            double d3 = 1.0d;
            if (z) {
                if (i11 > 1) {
                    int i14 = i12;
                    int i15 = 0;
                    while (i15 < min) {
                        int i16 = 0;
                        while (i16 < i11) {
                            int i17 = capacity + 1;
                            byte b5 = byteBuffer3.get(capacity);
                            if (d2 == d3) {
                                b4 = (byte) b5;
                            } else {
                                b4 = (byte) ((int) Math.round(Math.pow(((double) b5) / 127.0d, d2) * 127.0d));
                            }
                            bArr3[i16] = b4;
                            i16++;
                            capacity = i17;
                            d3 = 1.0d;
                        }
                        int i18 = i11 - 1;
                        while (true) {
                            i8 = i14;
                            if (i18 < 0) {
                                break;
                            }
                            i14 = i8 + 1;
                            byteBuffer4.put(i8, bArr3[i18]);
                            i18--;
                        }
                        i15 += i11;
                        i14 = i8;
                        d3 = 1.0d;
                    }
                } else {
                    int i19 = i12;
                    int i20 = 0;
                    while (i20 < min) {
                        int i21 = capacity + 1;
                        byte b6 = byteBuffer3.get(capacity);
                        if (d2 == 1.0d) {
                            b3 = (byte) b6;
                            i7 = i13;
                            bArr2 = bArr3;
                        } else {
                            i7 = i13;
                            bArr2 = bArr3;
                            b3 = (byte) ((int) Math.round(Math.pow(((double) b6) / 127.0d, d2) * 127.0d));
                        }
                        byteBuffer4.put(i19, b3);
                        i20++;
                        i19++;
                        capacity = i21;
                        bArr3 = bArr2;
                        i13 = i7;
                    }
                }
                i6 = i13;
                bArr = bArr3;
            } else {
                i6 = i13;
                bArr = bArr3;
                double d4 = 2.2d;
                if (i11 > 1) {
                    int i22 = i12;
                    int i23 = 0;
                    while (i23 < min) {
                        int i24 = 0;
                        while (i24 < i11) {
                            int i25 = capacity + 1;
                            byte b7 = byteBuffer3.get(capacity) & UByte.MAX_VALUE;
                            if (d2 == 1.0d) {
                                b2 = (byte) b7;
                            } else if (d2 == d4) {
                                b2 = gamma22[b7];
                            } else if (d2 == 0.45454545454545453d) {
                                b2 = gamma22inv[b7];
                            } else {
                                b2 = (byte) ((int) Math.round(Math.pow(((double) b7) / 255.0d, d2) * 255.0d));
                            }
                            bArr[i24] = b2;
                            i24++;
                            capacity = i25;
                            d4 = 2.2d;
                        }
                        int i26 = i11 - 1;
                        while (i26 >= 0) {
                            byteBuffer4.put(i22, bArr[i26]);
                            i26--;
                            i22++;
                        }
                        i23 += i11;
                        d4 = 2.2d;
                    }
                } else {
                    int i27 = i12;
                    int i28 = 0;
                    while (i28 < min) {
                        int i29 = capacity + 1;
                        byte b8 = byteBuffer3.get(capacity) & UByte.MAX_VALUE;
                        if (d2 == 1.0d) {
                            b = (byte) b8;
                        } else if (d2 == 2.2d) {
                            b = gamma22[b8];
                        } else {
                            if (d2 == 0.45454545454545453d) {
                                b = gamma22inv[b8];
                            } else {
                                b = (byte) ((int) Math.round(Math.pow(((double) b8) / 255.0d, d2) * 255.0d));
                            }
                            byteBuffer4.put(i27, b);
                            i28++;
                            capacity = i29;
                            i27++;
                        }
                        byteBuffer4.put(i27, b);
                        i28++;
                        capacity = i29;
                        i27++;
                    }
                }
            }
            i13 = i6 + i9;
            i12 += i10;
            bArr3 = bArr;
        }
    }

    public static void flipCopyWithGamma(ShortBuffer shortBuffer, int i, int i2, ShortBuffer shortBuffer2, int i3, int i4, boolean z, double d, boolean z2, int i5) {
        int i6;
        short[] sArr;
        ShortBuffer shortBuffer3;
        int i7;
        short s;
        short s2;
        int i8;
        short[] sArr2;
        short s3;
        int i9;
        short s4;
        ShortBuffer shortBuffer4 = shortBuffer;
        int i10 = i2;
        ShortBuffer shortBuffer5 = shortBuffer2;
        int i11 = i4;
        double d2 = d;
        int i12 = i5;
        short[] sArr3 = new short[i12];
        int i13 = i3;
        int min = Math.min(i10, i11);
        int i14 = i;
        while (i14 < shortBuffer.capacity() && i13 < shortBuffer2.capacity()) {
            int capacity = z2 ? (shortBuffer.capacity() - i14) - i10 : i14;
            int min2 = Math.min(Math.min(min, shortBuffer.capacity() - capacity), shortBuffer2.capacity() - i13);
            double d3 = 1.0d;
            if (!z) {
                i6 = i14;
                sArr = sArr3;
                if (i12 > 1) {
                    int i15 = i13;
                    int i16 = 0;
                    while (i16 < min2) {
                        int i17 = 0;
                        while (i17 < i12) {
                            int i18 = capacity + 1;
                            short s5 = shortBuffer4.get(capacity);
                            if (d2 == 1.0d) {
                                s2 = (short) s5;
                            } else {
                                s2 = (short) ((int) Math.round(Math.pow(((double) s5) / 65535.0d, d2) * 65535.0d));
                            }
                            sArr[i17] = s2;
                            i17++;
                            shortBuffer4 = shortBuffer;
                            int i19 = i2;
                            capacity = i18;
                        }
                        int i20 = i12 - 1;
                        while (i20 >= 0) {
                            shortBuffer5.put(i15, sArr[i20]);
                            i20--;
                            i15++;
                        }
                        i16 += i12;
                        shortBuffer4 = shortBuffer;
                        int i21 = i2;
                    }
                } else {
                    int i22 = i13;
                    int i23 = 0;
                    while (i23 < min2) {
                        int i24 = capacity + 1;
                        short s6 = shortBuffer.get(capacity) & UShort.MAX_VALUE;
                        if (d2 == 1.0d) {
                            s = (short) s6;
                            i7 = min2;
                        } else {
                            i7 = min2;
                            s = (short) ((int) Math.round(Math.pow(((double) s6) / 65535.0d, d2) * 65535.0d));
                        }
                        shortBuffer5.put(i22, s);
                        i23++;
                        capacity = i24;
                        i22++;
                        min2 = i7;
                    }
                }
                shortBuffer3 = shortBuffer;
            } else if (i12 > 1) {
                int i25 = i13;
                int i26 = 0;
                while (i26 < min2) {
                    int i27 = 0;
                    while (i27 < i12) {
                        int i28 = capacity + 1;
                        short s7 = shortBuffer4.get(capacity);
                        if (d2 == d3) {
                            s4 = (short) s7;
                        } else {
                            s4 = (short) ((int) Math.round(Math.pow(((double) s7) / 32767.0d, d2) * 32767.0d));
                        }
                        sArr3[i27] = s4;
                        i27++;
                        capacity = i28;
                        d3 = 1.0d;
                    }
                    int i29 = i12 - 1;
                    while (true) {
                        i9 = i25;
                        if (i29 < 0) {
                            break;
                        }
                        i25 = i9 + 1;
                        shortBuffer5.put(i9, sArr3[i29]);
                        i29--;
                    }
                    i26 += i12;
                    i25 = i9;
                    d3 = 1.0d;
                }
                shortBuffer3 = shortBuffer4;
                i6 = i14;
                sArr = sArr3;
            } else {
                int i30 = i13;
                int i31 = 0;
                while (i31 < min2) {
                    int i32 = capacity + 1;
                    short s8 = shortBuffer4.get(capacity);
                    if (d2 == 1.0d) {
                        s3 = (short) s8;
                        i8 = i14;
                        sArr2 = sArr3;
                    } else {
                        i8 = i14;
                        sArr2 = sArr3;
                        s3 = (short) ((int) Math.round(Math.pow(((double) s8) / 32767.0d, d2) * 32767.0d));
                    }
                    shortBuffer5.put(i30, s3);
                    i31++;
                    i30++;
                    capacity = i32;
                    sArr3 = sArr2;
                    i14 = i8;
                }
                i6 = i14;
                sArr = sArr3;
                shortBuffer3 = shortBuffer4;
            }
            i14 = i6 + i2;
            i13 += i11;
            min = min2;
            i10 = i2;
            shortBuffer4 = shortBuffer3;
            sArr3 = sArr;
        }
    }

    public static void flipCopyWithGamma(IntBuffer intBuffer, int i, int i2, IntBuffer intBuffer2, int i3, int i4, double d, boolean z, int i5) {
        int i6;
        int i7;
        IntBuffer intBuffer3 = intBuffer;
        int i8 = i2;
        IntBuffer intBuffer4 = intBuffer2;
        int i9 = i4;
        double d2 = d;
        int i10 = i5;
        int[] iArr = new int[i10];
        int i11 = i3;
        int min = Math.min(i8, i9);
        int i12 = i;
        while (i12 < intBuffer.capacity() && i11 < intBuffer2.capacity()) {
            int capacity = z ? (intBuffer.capacity() - i12) - i8 : i12;
            int min2 = Math.min(Math.min(min, intBuffer.capacity() - capacity), intBuffer2.capacity() - i11);
            double d3 = 1.0d;
            if (i10 > 1) {
                int i13 = i11;
                int i14 = 0;
                while (i14 < min2) {
                    int i15 = 0;
                    while (i15 < i10) {
                        int i16 = capacity + 1;
                        int i17 = intBuffer3.get(capacity);
                        if (d2 != d3) {
                            i17 = (int) Math.round(Math.pow(((double) i17) / 2.147483647E9d, d2) * 2.147483647E9d);
                        }
                        iArr[i15] = i17;
                        i15++;
                        capacity = i16;
                        d3 = 1.0d;
                    }
                    int i18 = i10 - 1;
                    while (true) {
                        i7 = i13;
                        if (i18 < 0) {
                            break;
                        }
                        i13 = i7 + 1;
                        intBuffer4.put(i7, iArr[i18]);
                        i18--;
                    }
                    i14 += i10;
                    i13 = i7;
                    d3 = 1.0d;
                }
            } else {
                int i19 = i11;
                int i20 = 0;
                while (i20 < min2) {
                    int i21 = capacity + 1;
                    int i22 = intBuffer3.get(capacity);
                    if (d2 == 1.0d) {
                        i6 = min2;
                    } else {
                        i6 = min2;
                        i22 = (int) Math.round(Math.pow(((double) i22) / 2.147483647E9d, d2) * 2.147483647E9d);
                    }
                    intBuffer4.put(i19, i22);
                    i20++;
                    i19++;
                    capacity = i21;
                    min2 = i6;
                }
            }
            i12 += i8;
            i11 += i9;
            min = min2;
        }
    }

    public static void flipCopyWithGamma(FloatBuffer floatBuffer, int i, int i2, FloatBuffer floatBuffer2, int i3, int i4, double d, boolean z, int i5) {
        int i6;
        int i7;
        FloatBuffer floatBuffer3 = floatBuffer;
        int i8 = i2;
        FloatBuffer floatBuffer4 = floatBuffer2;
        int i9 = i4;
        double d2 = d;
        int i10 = i5;
        float[] fArr = new float[i10];
        int i11 = i3;
        int min = Math.min(i8, i9);
        int i12 = i;
        while (i12 < floatBuffer.capacity() && i11 < floatBuffer2.capacity()) {
            int capacity = z ? (floatBuffer.capacity() - i12) - i8 : i12;
            int min2 = Math.min(Math.min(min, floatBuffer.capacity() - capacity), floatBuffer2.capacity() - i11);
            double d3 = 1.0d;
            if (i10 > 1) {
                int i13 = i11;
                int i14 = 0;
                while (i14 < min2) {
                    int i15 = 0;
                    while (i15 < i10) {
                        int i16 = capacity + 1;
                        float f = floatBuffer3.get(capacity);
                        if (d2 != d3) {
                            f = (float) Math.pow((double) f, d2);
                        }
                        fArr[i15] = f;
                        i15++;
                        capacity = i16;
                        d3 = 1.0d;
                    }
                    int i17 = i10 - 1;
                    while (true) {
                        i7 = i13;
                        if (i17 < 0) {
                            break;
                        }
                        i13 = i7 + 1;
                        floatBuffer4.put(i7, fArr[i17]);
                        i17--;
                    }
                    i14 += i10;
                    i13 = i7;
                    d3 = 1.0d;
                }
            } else {
                int i18 = i11;
                int i19 = 0;
                while (i19 < min2) {
                    int i20 = capacity + 1;
                    float f2 = floatBuffer3.get(capacity);
                    if (d2 == 1.0d) {
                        i6 = min2;
                    } else {
                        i6 = min2;
                        f2 = (float) Math.pow((double) f2, d2);
                    }
                    floatBuffer4.put(i18, f2);
                    i19++;
                    i18++;
                    capacity = i20;
                    min2 = i6;
                }
            }
            i12 += i8;
            i11 += i9;
            min = min2;
        }
    }

    public static void flipCopyWithGamma(DoubleBuffer doubleBuffer, int i, int i2, DoubleBuffer doubleBuffer2, int i3, int i4, double d, boolean z, int i5) {
        int i6;
        int i7;
        double[] dArr;
        DoubleBuffer doubleBuffer3 = doubleBuffer;
        int i8 = i2;
        DoubleBuffer doubleBuffer4 = doubleBuffer2;
        int i9 = i4;
        double d2 = d;
        int i10 = i5;
        double[] dArr2 = new double[i10];
        int i11 = i3;
        int min = Math.min(i8, i9);
        int i12 = i;
        while (i12 < doubleBuffer.capacity() && i11 < doubleBuffer2.capacity()) {
            int capacity = z ? (doubleBuffer.capacity() - i12) - i8 : i12;
            int min2 = Math.min(Math.min(min, doubleBuffer.capacity() - capacity), doubleBuffer2.capacity() - i11);
            double d3 = 1.0d;
            if (i10 > 1) {
                int i13 = i11;
                int i14 = 0;
                while (i14 < min2) {
                    int i15 = 0;
                    while (i15 < i10) {
                        int i16 = capacity + 1;
                        int i17 = i11;
                        int i18 = min2;
                        double d4 = doubleBuffer3.get(capacity);
                        if (d2 != d3) {
                            d4 = Math.pow(d4, d2);
                        }
                        dArr2[i15] = d4;
                        i15++;
                        i11 = i17;
                        capacity = i16;
                        min2 = i18;
                    }
                    int i19 = i11;
                    int i20 = min2;
                    int i21 = i10 - 1;
                    int i22 = i13;
                    while (i21 >= 0) {
                        doubleBuffer4.put(i22, dArr2[i21]);
                        i21--;
                        i22++;
                    }
                    i14 += i10;
                    i11 = i19;
                    i13 = i22;
                    min2 = i20;
                    d3 = 1.0d;
                }
                i6 = i11;
                dArr = dArr2;
                i7 = min2;
            } else {
                i6 = i11;
                int i23 = min2;
                int i24 = 0;
                int i25 = i6;
                while (i24 < i23) {
                    int i26 = capacity + 1;
                    double[] dArr3 = dArr2;
                    int i27 = i23;
                    double d5 = doubleBuffer3.get(capacity);
                    if (d2 != 1.0d) {
                        d5 = Math.pow(d5, d2);
                    }
                    doubleBuffer4.put(i25, d5);
                    i24++;
                    i25++;
                    capacity = i26;
                    dArr2 = dArr3;
                    i23 = i27;
                }
                dArr = dArr2;
                i7 = i23;
            }
            i12 += i8;
            i11 = i6 + i9;
            dArr2 = dArr;
            min = i7;
        }
    }

    public static void applyGamma(Frame frame, double d) {
        applyGamma(frame.image[0], frame.imageDepth, frame.imageStride, d);
    }

    public static void applyGamma(Buffer buffer, int i, int i2, double d) {
        int i3 = i;
        if (d != 1.0d) {
            if (i3 == -32) {
                IntBuffer intBuffer = (IntBuffer) buffer;
                flipCopyWithGamma(intBuffer.asReadOnlyBuffer(), 0, i2, intBuffer, 0, i2, d, false, 0);
            } else if (i3 == -16) {
                ShortBuffer shortBuffer = (ShortBuffer) buffer;
                flipCopyWithGamma(shortBuffer.asReadOnlyBuffer(), 0, i2, shortBuffer, 0, i2, true, d, false, 0);
            } else if (i3 == -8) {
                ByteBuffer byteBuffer = (ByteBuffer) buffer;
                flipCopyWithGamma(byteBuffer.asReadOnlyBuffer(), 0, i2, byteBuffer, 0, i2, true, d, false, 0);
            } else if (i3 == 8) {
                ByteBuffer byteBuffer2 = (ByteBuffer) buffer;
                flipCopyWithGamma(byteBuffer2.asReadOnlyBuffer(), 0, i2, byteBuffer2, 0, i2, false, d, false, 0);
            } else if (i3 == 16) {
                ShortBuffer shortBuffer2 = (ShortBuffer) buffer;
                flipCopyWithGamma(shortBuffer2.asReadOnlyBuffer(), 0, i2, shortBuffer2, 0, i2, false, d, false, 0);
            } else if (i3 == 32) {
                FloatBuffer floatBuffer = (FloatBuffer) buffer;
                flipCopyWithGamma(floatBuffer.asReadOnlyBuffer(), 0, i2, floatBuffer, 0, i2, d, false, 0);
            } else if (i3 == 64) {
                DoubleBuffer doubleBuffer = (DoubleBuffer) buffer;
                flipCopyWithGamma(doubleBuffer.asReadOnlyBuffer(), 0, i2, doubleBuffer, 0, i2, d, false, 0);
            }
        }
    }

    public static void copy(Frame frame, BufferedImage bufferedImage2) {
        copy(frame, bufferedImage2, 1.0d);
    }

    public static void copy(Frame frame, BufferedImage bufferedImage2, double d) {
        copy(frame, bufferedImage2, d, false, (Rectangle) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copy(org.bytedeco.javacv.Frame r16, java.awt.image.BufferedImage r17, double r18, boolean r20, java.awt.Rectangle r21) {
        /*
            r0 = r16
            r1 = r21
            java.nio.Buffer[] r2 = r0.image
            r3 = 0
            r2 = r2[r3]
            if (r1 != 0) goto L_0x000d
            r6 = 0
            goto L_0x001b
        L_0x000d:
            int r4 = r1.y
            int r5 = r0.imageStride
            int r4 = r4 * r5
            int r1 = r1.x
            int r5 = r0.imageChannels
            int r1 = r1 * r5
            int r4 = r4 + r1
            r6 = r4
        L_0x001b:
            java.awt.image.SampleModel r1 = r17.getSampleModel()
            java.awt.image.WritableRaster r4 = r17.getRaster()
            java.awt.image.DataBuffer r5 = r4.getDataBuffer()
            int r7 = r4.getSampleModelTranslateX()
            int r7 = -r7
            int r4 = r4.getSampleModelTranslateY()
            int r4 = -r4
            int r8 = r1.getWidth()
            int r9 = r1.getNumBands()
            int r8 = r8 * r9
            int r9 = r1.getNumBands()
            boolean r10 = r1 instanceof java.awt.image.ComponentSampleModel
            if (r10 == 0) goto L_0x004f
            java.awt.image.ComponentSampleModel r1 = (java.awt.image.ComponentSampleModel) r1
            int r8 = r1.getScanlineStride()
            int r9 = r1.getPixelStride()
        L_0x004d:
            r10 = r8
            goto L_0x006d
        L_0x004f:
            boolean r10 = r1 instanceof java.awt.image.SinglePixelPackedSampleModel
            if (r10 == 0) goto L_0x005c
            java.awt.image.SinglePixelPackedSampleModel r1 = (java.awt.image.SinglePixelPackedSampleModel) r1
            int r1 = r1.getScanlineStride()
            r9 = 1
            r10 = r1
            goto L_0x006d
        L_0x005c:
            boolean r10 = r1 instanceof java.awt.image.MultiPixelPackedSampleModel
            if (r10 == 0) goto L_0x004d
            java.awt.image.MultiPixelPackedSampleModel r1 = (java.awt.image.MultiPixelPackedSampleModel) r1
            int r8 = r1.getScanlineStride()
            int r1 = r1.getPixelBitStride()
            int r9 = r1 / 8
            goto L_0x004d
        L_0x006d:
            int r4 = r4 * r10
            int r7 = r7 * r9
            int r1 = r4 + r7
            boolean r4 = r5 instanceof java.awt.image.DataBufferByte
            if (r4 == 0) goto L_0x0095
            java.awt.image.DataBufferByte r5 = (java.awt.image.DataBufferByte) r5
            byte[] r4 = r5.getData()
            r5 = r2
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            int r7 = r0.imageStride
            java.nio.ByteBuffer r8 = java.nio.ByteBuffer.wrap(r4)
            r11 = 0
            r14 = 0
            if (r20 == 0) goto L_0x008c
            r15 = r9
            goto L_0x008d
        L_0x008c:
            r15 = 0
        L_0x008d:
            r9 = r1
            r12 = r18
            flipCopyWithGamma((java.nio.ByteBuffer) r5, (int) r6, (int) r7, (java.nio.ByteBuffer) r8, (int) r9, (int) r10, (boolean) r11, (double) r12, (boolean) r14, (int) r15)
            goto L_0x0150
        L_0x0095:
            boolean r4 = r5 instanceof java.awt.image.DataBufferDouble
            if (r4 == 0) goto L_0x00b6
            java.awt.image.DataBufferDouble r5 = (java.awt.image.DataBufferDouble) r5
            double[] r4 = r5.getData()
            r5 = r2
            java.nio.DoubleBuffer r5 = (java.nio.DoubleBuffer) r5
            int r7 = r0.imageStride
            java.nio.DoubleBuffer r8 = java.nio.DoubleBuffer.wrap(r4)
            r13 = 0
            if (r20 == 0) goto L_0x00ad
            r14 = r9
            goto L_0x00ae
        L_0x00ad:
            r14 = 0
        L_0x00ae:
            r9 = r1
            r11 = r18
            flipCopyWithGamma((java.nio.DoubleBuffer) r5, (int) r6, (int) r7, (java.nio.DoubleBuffer) r8, (int) r9, (int) r10, (double) r11, (boolean) r13, (int) r14)
            goto L_0x0150
        L_0x00b6:
            boolean r4 = r5 instanceof java.awt.image.DataBufferFloat
            if (r4 == 0) goto L_0x00d7
            java.awt.image.DataBufferFloat r5 = (java.awt.image.DataBufferFloat) r5
            float[] r4 = r5.getData()
            r5 = r2
            java.nio.FloatBuffer r5 = (java.nio.FloatBuffer) r5
            int r7 = r0.imageStride
            java.nio.FloatBuffer r8 = java.nio.FloatBuffer.wrap(r4)
            r13 = 0
            if (r20 == 0) goto L_0x00ce
            r14 = r9
            goto L_0x00cf
        L_0x00ce:
            r14 = 0
        L_0x00cf:
            r9 = r1
            r11 = r18
            flipCopyWithGamma((java.nio.FloatBuffer) r5, (int) r6, (int) r7, (java.nio.FloatBuffer) r8, (int) r9, (int) r10, (double) r11, (boolean) r13, (int) r14)
            goto L_0x0150
        L_0x00d7:
            boolean r4 = r5 instanceof java.awt.image.DataBufferInt
            if (r4 == 0) goto L_0x010f
            java.awt.image.DataBufferInt r5 = (java.awt.image.DataBufferInt) r5
            int[] r4 = r5.getData()
            int r0 = r0.imageStride
            boolean r5 = r2 instanceof java.nio.ByteBuffer
            if (r5 == 0) goto L_0x00fa
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            if (r20 == 0) goto L_0x00ee
            java.nio.ByteOrder r5 = java.nio.ByteOrder.LITTLE_ENDIAN
            goto L_0x00f0
        L_0x00ee:
            java.nio.ByteOrder r5 = java.nio.ByteOrder.BIG_ENDIAN
        L_0x00f0:
            java.nio.ByteBuffer r2 = r2.order(r5)
            java.nio.IntBuffer r2 = r2.asIntBuffer()
            int r0 = r0 / 4
        L_0x00fa:
            r7 = r0
            r5 = r2
            java.nio.IntBuffer r5 = (java.nio.IntBuffer) r5
            java.nio.IntBuffer r8 = java.nio.IntBuffer.wrap(r4)
            r13 = 0
            if (r20 == 0) goto L_0x0107
            r14 = r9
            goto L_0x0108
        L_0x0107:
            r14 = 0
        L_0x0108:
            r9 = r1
            r11 = r18
            flipCopyWithGamma((java.nio.IntBuffer) r5, (int) r6, (int) r7, (java.nio.IntBuffer) r8, (int) r9, (int) r10, (double) r11, (boolean) r13, (int) r14)
            goto L_0x0150
        L_0x010f:
            boolean r4 = r5 instanceof java.awt.image.DataBufferShort
            if (r4 == 0) goto L_0x0130
            java.awt.image.DataBufferShort r5 = (java.awt.image.DataBufferShort) r5
            short[] r4 = r5.getData()
            r5 = r2
            java.nio.ShortBuffer r5 = (java.nio.ShortBuffer) r5
            int r7 = r0.imageStride
            java.nio.ShortBuffer r8 = java.nio.ShortBuffer.wrap(r4)
            r11 = 1
            r14 = 0
            if (r20 == 0) goto L_0x0128
            r15 = r9
            goto L_0x0129
        L_0x0128:
            r15 = 0
        L_0x0129:
            r9 = r1
            r12 = r18
            flipCopyWithGamma((java.nio.ShortBuffer) r5, (int) r6, (int) r7, (java.nio.ShortBuffer) r8, (int) r9, (int) r10, (boolean) r11, (double) r12, (boolean) r14, (int) r15)
            goto L_0x0150
        L_0x0130:
            boolean r4 = r5 instanceof java.awt.image.DataBufferUShort
            if (r4 == 0) goto L_0x0150
            java.awt.image.DataBufferUShort r5 = (java.awt.image.DataBufferUShort) r5
            short[] r4 = r5.getData()
            r5 = r2
            java.nio.ShortBuffer r5 = (java.nio.ShortBuffer) r5
            int r7 = r0.imageStride
            java.nio.ShortBuffer r8 = java.nio.ShortBuffer.wrap(r4)
            r11 = 0
            r14 = 0
            if (r20 == 0) goto L_0x0149
            r15 = r9
            goto L_0x014a
        L_0x0149:
            r15 = 0
        L_0x014a:
            r9 = r1
            r12 = r18
            flipCopyWithGamma((java.nio.ShortBuffer) r5, (int) r6, (int) r7, (java.nio.ShortBuffer) r8, (int) r9, (int) r10, (boolean) r11, (double) r12, (boolean) r14, (int) r15)
        L_0x0150:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.Java2DFrameConverter.copy(org.bytedeco.javacv.Frame, java.awt.image.BufferedImage, double, boolean, java.awt.Rectangle):void");
    }

    public static void copy(BufferedImage bufferedImage2, Frame frame) {
        copy(bufferedImage2, frame, 1.0d);
    }

    public static void copy(BufferedImage bufferedImage2, Frame frame, double d) {
        copy(bufferedImage2, frame, d, false, (Rectangle) null);
    }

    public static void copy(BufferedImage bufferedImage2, Frame frame, double d, boolean z, Rectangle rectangle) {
        int i;
        Frame frame2 = frame;
        Rectangle rectangle2 = rectangle;
        Buffer buffer = frame2.image[0];
        if (rectangle2 == null) {
            i = 0;
        } else {
            i = (rectangle2.y * frame2.imageStride) + (rectangle2.x * frame2.imageChannels);
        }
        ComponentSampleModel sampleModel = bufferedImage2.getSampleModel();
        WritableRaster raster = bufferedImage2.getRaster();
        DataBufferByte dataBuffer = raster.getDataBuffer();
        int i2 = -raster.getSampleModelTranslateX();
        int i3 = -raster.getSampleModelTranslateY();
        int width = sampleModel.getWidth() * sampleModel.getNumBands();
        int numBands = sampleModel.getNumBands();
        if (sampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel = sampleModel;
            width = componentSampleModel.getScanlineStride();
            numBands = componentSampleModel.getPixelStride();
        } else if (sampleModel instanceof SinglePixelPackedSampleModel) {
            numBands = 1;
            width = ((SinglePixelPackedSampleModel) sampleModel).getScanlineStride();
        } else if (sampleModel instanceof MultiPixelPackedSampleModel) {
            MultiPixelPackedSampleModel multiPixelPackedSampleModel = (MultiPixelPackedSampleModel) sampleModel;
            width = multiPixelPackedSampleModel.getScanlineStride();
            numBands = multiPixelPackedSampleModel.getPixelBitStride() / 8;
        }
        int i4 = (i2 * numBands) + (i3 * width);
        if (dataBuffer instanceof DataBufferByte) {
            flipCopyWithGamma(ByteBuffer.wrap(dataBuffer.getData()), i4, width, (ByteBuffer) buffer, i, frame2.imageStride, false, d, false, z ? numBands : 0);
        } else if (dataBuffer instanceof DataBufferDouble) {
            flipCopyWithGamma(DoubleBuffer.wrap(((DataBufferDouble) dataBuffer).getData()), i4, width, (DoubleBuffer) buffer, i, frame2.imageStride, d, false, z ? numBands : 0);
        } else if (dataBuffer instanceof DataBufferFloat) {
            flipCopyWithGamma(FloatBuffer.wrap(((DataBufferFloat) dataBuffer).getData()), i4, width, (FloatBuffer) buffer, i, frame2.imageStride, d, false, z ? numBands : 0);
        } else if (dataBuffer instanceof DataBufferInt) {
            int[] data = ((DataBufferInt) dataBuffer).getData();
            int i5 = frame2.imageStride;
            if (buffer instanceof ByteBuffer) {
                buffer = ((ByteBuffer) buffer).order(z ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN).asIntBuffer();
                i5 /= 4;
            }
            flipCopyWithGamma(IntBuffer.wrap(data), i4, width, (IntBuffer) buffer, i, i5, d, false, z ? numBands : 0);
        } else if (dataBuffer instanceof DataBufferShort) {
            flipCopyWithGamma(ShortBuffer.wrap(((DataBufferShort) dataBuffer).getData()), i4, width, (ShortBuffer) buffer, i, frame2.imageStride, true, d, false, z ? numBands : 0);
        } else if (dataBuffer instanceof DataBufferUShort) {
            flipCopyWithGamma(ShortBuffer.wrap(((DataBufferUShort) dataBuffer).getData()), i4, width, (ShortBuffer) buffer, i, frame2.imageStride, false, d, false, z ? numBands : 0);
        }
    }

    public static int getBufferedImageType(Frame frame) {
        if (frame.imageChannels == 1) {
            if (frame.imageDepth == 8 || frame.imageDepth == -8) {
                return 10;
            }
            if (frame.imageDepth == 16) {
                return 11;
            }
        } else if (frame.imageChannels != 3) {
            return (frame.imageChannels == 4 && (frame.imageDepth == 8 || frame.imageDepth == -8)) ? 6 : 0;
        } else {
            if (frame.imageDepth == 8 || frame.imageDepth == -8) {
                return 5;
            }
        }
    }

    public BufferedImage getBufferedImage(Frame frame) {
        return getBufferedImage(frame, 1.0d);
    }

    public BufferedImage getBufferedImage(Frame frame, double d) {
        return getBufferedImage(frame, d, false, (ColorSpace) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.awt.image.BufferedImage getBufferedImage(org.bytedeco.javacv.Frame r17, double r18, boolean r20, java.awt.color.ColorSpace r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 0
            if (r1 == 0) goto L_0x0183
            java.nio.Buffer[] r3 = r1.image
            if (r3 != 0) goto L_0x000d
            goto L_0x0183
        L_0x000d:
            int r3 = getBufferedImageType(r17)
            java.awt.image.BufferedImage r4 = r0.bufferedImage
            if (r4 == 0) goto L_0x002f
            int r4 = r4.getWidth()
            int r5 = r1.imageWidth
            if (r4 != r5) goto L_0x002f
            java.awt.image.BufferedImage r4 = r0.bufferedImage
            int r4 = r4.getHeight()
            int r5 = r1.imageHeight
            if (r4 != r5) goto L_0x002f
            java.awt.image.BufferedImage r4 = r0.bufferedImage
            int r4 = r4.getType()
            if (r4 == r3) goto L_0x0041
        L_0x002f:
            if (r3 == 0) goto L_0x003e
            if (r21 == 0) goto L_0x0034
            goto L_0x003e
        L_0x0034:
            java.awt.image.BufferedImage r4 = new java.awt.image.BufferedImage
            int r5 = r1.imageWidth
            int r6 = r1.imageHeight
            r4.<init>(r5, r6, r3)
            goto L_0x003f
        L_0x003e:
            r4 = r2
        L_0x003f:
            r0.bufferedImage = r4
        L_0x0041:
            java.awt.image.BufferedImage r3 = r0.bufferedImage
            if (r3 != 0) goto L_0x0172
            int r3 = r1.imageChannels
            r4 = 1
            r5 = 0
            if (r3 != r4) goto L_0x005e
            if (r21 != 0) goto L_0x0054
            r3 = 1003(0x3eb, float:1.406E-42)
            java.awt.color.ColorSpace r3 = java.awt.color.ColorSpace.getInstance(r3)
            goto L_0x0056
        L_0x0054:
            r3 = r21
        L_0x0056:
            int[] r4 = new int[r4]
            r4[r5] = r5
        L_0x005a:
            r8 = r3
            r15 = r4
        L_0x005c:
            r9 = 0
            goto L_0x008f
        L_0x005e:
            int r3 = r1.imageChannels
            r6 = 1004(0x3ec, float:1.407E-42)
            r7 = 3
            if (r3 != r7) goto L_0x0074
            if (r21 != 0) goto L_0x006c
            java.awt.color.ColorSpace r3 = java.awt.color.ColorSpace.getInstance(r6)
            goto L_0x006e
        L_0x006c:
            r3 = r21
        L_0x006e:
            int[] r4 = new int[r7]
            r4 = {2, 1, 0} // fill-array
            goto L_0x005a
        L_0x0074:
            int r3 = r1.imageChannels
            r7 = 4
            if (r3 != r7) goto L_0x008b
            if (r21 != 0) goto L_0x0080
            java.awt.color.ColorSpace r3 = java.awt.color.ColorSpace.getInstance(r6)
            goto L_0x0082
        L_0x0080:
            r3 = r21
        L_0x0082:
            int[] r6 = new int[r7]
            r6 = {0, 1, 2, 3} // fill-array
            r8 = r3
            r15 = r6
            r9 = 1
            goto L_0x008f
        L_0x008b:
            r8 = r21
            r15 = r2
            goto L_0x005c
        L_0x008f:
            int r3 = r1.imageDepth
            r4 = 8
            if (r3 == r4) goto L_0x0150
            int r3 = r1.imageDepth
            r4 = -8
            if (r3 != r4) goto L_0x009c
            goto L_0x0150
        L_0x009c:
            int r3 = r1.imageDepth
            r4 = 16
            if (r3 != r4) goto L_0x00c0
            java.awt.image.ComponentColorModel r3 = new java.awt.image.ComponentColorModel
            r10 = 0
            r11 = 1
            r12 = 1
            r7 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            java.awt.image.ComponentSampleModel r4 = new java.awt.image.ComponentSampleModel
            r10 = 1
            int r11 = r1.imageWidth
            int r12 = r1.imageHeight
            int r13 = r1.imageChannels
            int r14 = r1.imageStride
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15)
            java.awt.image.WritableRaster r4 = java.awt.image.Raster.createWritableRaster(r4, r2)
            goto L_0x016b
        L_0x00c0:
            int r3 = r1.imageDepth
            r4 = -16
            if (r3 != r4) goto L_0x00e4
            java.awt.image.ComponentColorModel r3 = new java.awt.image.ComponentColorModel
            r10 = 0
            r11 = 1
            r12 = 2
            r7 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            java.awt.image.ComponentSampleModel r4 = new java.awt.image.ComponentSampleModel
            r10 = 2
            int r11 = r1.imageWidth
            int r12 = r1.imageHeight
            int r13 = r1.imageChannels
            int r14 = r1.imageStride
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15)
            java.awt.image.WritableRaster r4 = java.awt.image.Raster.createWritableRaster(r4, r2)
            goto L_0x016b
        L_0x00e4:
            int r3 = r1.imageDepth
            r4 = -32
            if (r3 != r4) goto L_0x0107
            java.awt.image.ComponentColorModel r3 = new java.awt.image.ComponentColorModel
            r10 = 0
            r11 = 1
            r12 = 3
            r7 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            java.awt.image.ComponentSampleModel r4 = new java.awt.image.ComponentSampleModel
            r10 = 3
            int r11 = r1.imageWidth
            int r12 = r1.imageHeight
            int r13 = r1.imageChannels
            int r14 = r1.imageStride
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15)
            java.awt.image.WritableRaster r4 = java.awt.image.Raster.createWritableRaster(r4, r2)
            goto L_0x016b
        L_0x0107:
            int r3 = r1.imageDepth
            r4 = 32
            if (r3 != r4) goto L_0x012a
            java.awt.image.ComponentColorModel r3 = new java.awt.image.ComponentColorModel
            r10 = 0
            r11 = 1
            r12 = 4
            r7 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            java.awt.image.ComponentSampleModel r4 = new java.awt.image.ComponentSampleModel
            r10 = 4
            int r11 = r1.imageWidth
            int r12 = r1.imageHeight
            int r13 = r1.imageChannels
            int r14 = r1.imageStride
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15)
            java.awt.image.WritableRaster r4 = java.awt.image.Raster.createWritableRaster(r4, r2)
            goto L_0x016b
        L_0x012a:
            int r3 = r1.imageDepth
            r4 = 64
            if (r3 != r4) goto L_0x014d
            java.awt.image.ComponentColorModel r3 = new java.awt.image.ComponentColorModel
            r10 = 0
            r11 = 1
            r12 = 5
            r7 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            java.awt.image.ComponentSampleModel r4 = new java.awt.image.ComponentSampleModel
            r10 = 5
            int r11 = r1.imageWidth
            int r12 = r1.imageHeight
            int r13 = r1.imageChannels
            int r14 = r1.imageStride
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15)
            java.awt.image.WritableRaster r4 = java.awt.image.Raster.createWritableRaster(r4, r2)
            goto L_0x016b
        L_0x014d:
            r3 = r2
            r4 = r3
            goto L_0x016b
        L_0x0150:
            java.awt.image.ComponentColorModel r3 = new java.awt.image.ComponentColorModel
            r10 = 0
            r11 = 1
            r12 = 0
            r7 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            java.awt.image.ComponentSampleModel r4 = new java.awt.image.ComponentSampleModel
            int r11 = r1.imageWidth
            int r12 = r1.imageHeight
            int r13 = r1.imageChannels
            int r14 = r1.imageStride
            r9 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15)
            java.awt.image.WritableRaster r4 = java.awt.image.Raster.createWritableRaster(r4, r2)
        L_0x016b:
            java.awt.image.BufferedImage r6 = new java.awt.image.BufferedImage
            r6.<init>(r3, r4, r5, r2)
            r0.bufferedImage = r6
        L_0x0172:
            java.awt.image.BufferedImage r2 = r0.bufferedImage
            if (r2 == 0) goto L_0x0180
            r6 = 0
            r1 = r17
            r3 = r18
            r5 = r20
            copy((org.bytedeco.javacv.Frame) r1, (java.awt.image.BufferedImage) r2, (double) r3, (boolean) r5, (java.awt.Rectangle) r6)
        L_0x0180:
            java.awt.image.BufferedImage r1 = r0.bufferedImage
            return r1
        L_0x0183:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.Java2DFrameConverter.getBufferedImage(org.bytedeco.javacv.Frame, double, boolean, java.awt.color.ColorSpace):java.awt.image.BufferedImage");
    }

    public Frame getFrame(BufferedImage bufferedImage2) {
        return getFrame(bufferedImage2, 1.0d);
    }

    public Frame getFrame(BufferedImage bufferedImage2, double d) {
        return getFrame(bufferedImage2, d, false);
    }

    public Frame getFrame(BufferedImage bufferedImage2, double d, boolean z) {
        if (bufferedImage2 == null) {
            return null;
        }
        SampleModel sampleModel = bufferedImage2.getSampleModel();
        int i = 0;
        int numBands = sampleModel.getNumBands();
        int type = bufferedImage2.getType();
        int i2 = 8;
        if (type == 1 || type == 2 || type == 3 || type == 4) {
            i = 8;
            numBands = 4;
        }
        if (i == 0 || numBands == 0) {
            int dataType = sampleModel.getDataType();
            if (dataType != 0) {
                if (dataType == 1) {
                    i2 = 16;
                } else if (dataType == 2) {
                    i2 = -16;
                } else if (dataType == 3) {
                    i2 = -32;
                } else if (dataType == 4) {
                    i2 = 32;
                } else if (dataType == 5) {
                    i2 = 64;
                }
            }
            if (!(this.frame != null && this.frame.imageWidth == bufferedImage2.getWidth() && this.frame.imageHeight == bufferedImage2.getHeight() && this.frame.imageDepth == i2 && this.frame.imageChannels == numBands)) {
                this.frame = new Frame(bufferedImage2.getWidth(), bufferedImage2.getHeight(), i2, numBands);
            }
            copy(bufferedImage2, this.frame, d, z, (Rectangle) null);
            return this.frame;
        }
        i2 = i;
        this.frame = new Frame(bufferedImage2.getWidth(), bufferedImage2.getHeight(), i2, numBands);
        copy(bufferedImage2, this.frame, d, z, (Rectangle) null);
        return this.frame;
    }
}
