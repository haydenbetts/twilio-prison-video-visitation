package org.bytedeco.javacv;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

public class AndroidFrameConverter extends FrameConverter<Bitmap> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    Bitmap bitmap;
    ByteBuffer buffer;
    byte[] row;

    public Frame convert(byte[] bArr, int i, int i2) {
        if (!(this.frame != null && this.frame.imageWidth == i && this.frame.imageHeight == i2 && this.frame.imageChannels == 3)) {
            this.frame = new Frame(i, i2, 8, 3);
        }
        ByteBuffer byteBuffer = (ByteBuffer) this.frame.image[0];
        int i3 = this.frame.imageStride;
        int i4 = i2 * i;
        for (int i5 = 0; i5 < i2; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                byte b = bArr[(i5 * i) + i6] & UByte.MAX_VALUE;
                int i7 = ((i5 / 2) * i) + i4 + ((i6 / 2) * 2);
                byte b2 = bArr[i7] & UByte.MAX_VALUE;
                int i8 = b - 16;
                int i9 = (bArr[i7 + 1] & UByte.MAX_VALUE) + ByteCompanionObject.MIN_VALUE;
                int i10 = b2 + ByteCompanionObject.MIN_VALUE;
                if (i8 < 0) {
                    i8 = 0;
                }
                int i11 = i8 * 1192;
                int i12 = (i9 * 2066) + i11;
                int min = Math.min(262143, Math.max(0, i11 + (i10 * 1634)));
                int min2 = Math.min(262143, Math.max(0, (i11 - (i10 * 833)) - (i9 * 400)));
                int i13 = (i5 * i3) + (i6 * 3);
                byteBuffer.put(i13, (byte) ((Math.min(262143, Math.max(0, i12)) >> 10) & 255));
                byteBuffer.put(i13 + 1, (byte) ((min2 >> 10) & 255));
                byteBuffer.put(i13 + 2, (byte) ((min >> 10) & 255));
            }
        }
        return this.frame;
    }

    /* renamed from: org.bytedeco.javacv.AndroidFrameConverter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                android.graphics.Bitmap$Config[] r0 = android.graphics.Bitmap.Config.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$graphics$Bitmap$Config = r0
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$Config     // Catch:{ NoSuchFieldError -> 0x001d }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$Config     // Catch:{ NoSuchFieldError -> 0x0028 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_4444     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$android$graphics$Bitmap$Config     // Catch:{ NoSuchFieldError -> 0x0033 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.AndroidFrameConverter.AnonymousClass1.<clinit>():void");
        }
    }

    public Frame convert(Bitmap bitmap2) {
        if (bitmap2 == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[bitmap2.getConfig().ordinal()];
        int i2 = i != 1 ? (i == 2 || i == 3) ? 2 : i != 4 ? 0 : 4 : 1;
        if (!(this.frame != null && this.frame.imageWidth == bitmap2.getWidth() && this.frame.imageStride == bitmap2.getRowBytes() && this.frame.imageHeight == bitmap2.getHeight() && this.frame.imageChannels == i2)) {
            this.frame = new Frame(bitmap2.getWidth(), bitmap2.getHeight(), 8, i2, bitmap2.getRowBytes());
        }
        bitmap2.copyPixelsToBuffer(this.frame.image[0].position(0));
        return this.frame;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer gray2rgba(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        ByteBuffer byteBuffer2 = this.buffer;
        if (byteBuffer2 == null || byteBuffer2.capacity() < i2 * i4) {
            this.buffer = ByteBuffer.allocate(i2 * i4);
        }
        byte[] bArr = this.row;
        if (bArr == null || bArr.length != i3) {
            this.row = new byte[i3];
        }
        for (int i5 = 0; i5 < i2; i5++) {
            byteBuffer.position(i5 * i3);
            byteBuffer.get(this.row);
            for (int i6 = 0; i6 < i; i6++) {
                byte b = this.row[i6] & UByte.MAX_VALUE;
                ByteBuffer byteBuffer3 = this.buffer;
                int i7 = (i5 * i4) + (i6 * 4);
                byteBuffer3.putInt(i7, (b << 8) | (b << 24) | (b << Tnaf.POW_2_WIDTH) | 255);
            }
        }
        return this.buffer;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer bgr2rgba(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        int i5;
        if (!byteBuffer.order().equals(ByteOrder.LITTLE_ENDIAN)) {
            byteBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        ByteBuffer byteBuffer2 = this.buffer;
        if (byteBuffer2 == null || byteBuffer2.capacity() < i2 * i4) {
            this.buffer = ByteBuffer.allocate(i2 * i4);
        }
        for (int i6 = 0; i6 < i2; i6++) {
            for (int i7 = 0; i7 < i; i7++) {
                if (i7 < i - 1 || i6 < i2 - 1) {
                    i5 = byteBuffer.getInt((i6 * i3) + (i7 * 3));
                } else {
                    int i8 = (i6 * i3) + (i7 * 3);
                    i5 = ((byteBuffer.get(i8 + 2) & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH) | ((byteBuffer.get(i8 + 1) & UByte.MAX_VALUE) << 8) | (byteBuffer.get(i8) & UByte.MAX_VALUE);
                }
                this.buffer.putInt((i6 * i4) + (i7 * 4), (i5 << 8) | 255);
            }
        }
        return this.buffer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        if (r1 != 4) goto L_0x001d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap convert(org.bytedeco.javacv.Frame r11) {
        /*
            r10 = this;
            r0 = 0
            if (r11 == 0) goto L_0x008d
            java.nio.Buffer[] r1 = r11.image
            if (r1 != 0) goto L_0x0009
            goto L_0x008d
        L_0x0009:
            int r1 = r11.imageChannels
            r2 = 3
            r3 = 1
            if (r1 == r3) goto L_0x001b
            r4 = 2
            if (r1 == r4) goto L_0x0018
            if (r1 == r2) goto L_0x001b
            r4 = 4
            if (r1 == r4) goto L_0x001b
            goto L_0x001d
        L_0x0018:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.RGB_565
            goto L_0x001d
        L_0x001b:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
        L_0x001d:
            android.graphics.Bitmap r1 = r10.bitmap
            if (r1 == 0) goto L_0x003b
            int r1 = r1.getWidth()
            int r4 = r11.imageWidth
            if (r1 != r4) goto L_0x003b
            android.graphics.Bitmap r1 = r10.bitmap
            int r1 = r1.getHeight()
            int r4 = r11.imageHeight
            if (r1 != r4) goto L_0x003b
            android.graphics.Bitmap r1 = r10.bitmap
            android.graphics.Bitmap$Config r1 = r1.getConfig()
            if (r1 == r0) goto L_0x0045
        L_0x003b:
            int r1 = r11.imageWidth
            int r4 = r11.imageHeight
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r1, r4, r0)
            r10.bitmap = r0
        L_0x0045:
            java.nio.Buffer[] r0 = r11.image
            r1 = 0
            r0 = r0[r1]
            r5 = r0
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            int r6 = r11.imageWidth
            int r7 = r11.imageHeight
            int r8 = r11.imageStride
            android.graphics.Bitmap r0 = r10.bitmap
            int r9 = r0.getRowBytes()
            int r0 = r11.imageChannels
            if (r0 != r3) goto L_0x006d
            r4 = r10
            r4.gray2rgba(r5, r6, r7, r8, r9)
            android.graphics.Bitmap r11 = r10.bitmap
            java.nio.ByteBuffer r0 = r10.buffer
            java.nio.Buffer r0 = r0.position(r1)
            r11.copyPixelsFromBuffer(r0)
            goto L_0x008a
        L_0x006d:
            int r11 = r11.imageChannels
            if (r11 != r2) goto L_0x0081
            r4 = r10
            r4.bgr2rgba(r5, r6, r7, r8, r9)
            android.graphics.Bitmap r11 = r10.bitmap
            java.nio.ByteBuffer r0 = r10.buffer
            java.nio.Buffer r0 = r0.position(r1)
            r11.copyPixelsFromBuffer(r0)
            goto L_0x008a
        L_0x0081:
            android.graphics.Bitmap r11 = r10.bitmap
            java.nio.Buffer r0 = r5.position(r1)
            r11.copyPixelsFromBuffer(r0)
        L_0x008a:
            android.graphics.Bitmap r11 = r10.bitmap
            return r11
        L_0x008d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.AndroidFrameConverter.convert(org.bytedeco.javacv.Frame):android.graphics.Bitmap");
    }
}
