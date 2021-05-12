package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.leptonica.global.lept;

public class LeptonicaFrameConverter extends FrameConverter<PIX> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    ByteBuffer frameBuffer;
    PIX pix;
    ByteBuffer pixBuffer;

    static {
        Loader.load(lept.class);
    }

    static boolean isEqual(Frame frame, PIX pix2) {
        if (pix2 == null || frame == null || frame.image == null || frame.image.length <= 0 || frame.imageWidth != pix2.w() || frame.imageHeight != pix2.h() || frame.imageChannels != pix2.d() / 8 || frame.imageDepth != 8) {
            return false;
        }
        if ((ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) || new Pointer(frame.image[0]).address() == pix2.data().address()) && (frame.imageStride * Math.abs(frame.imageDepth)) / 8 == pix2.wpl() * 4) {
            return true;
        }
        return false;
    }

    public PIX convert(Frame frame) {
        Pointer pointer;
        if (frame == null || frame.image == null) {
            return null;
        }
        if (frame.opaque instanceof PIX) {
            return (PIX) frame.opaque;
        }
        if (!isEqual(frame, this.pix)) {
            if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                pointer = new BytePointer((long) (frame.imageHeight * frame.imageStride));
                this.pixBuffer = pointer.asByteBuffer().order(ByteOrder.BIG_ENDIAN);
            } else {
                pointer = new Pointer(frame.image[0].position(0));
            }
            this.pix = PIX.create(frame.imageWidth, frame.imageHeight, frame.imageChannels * 8, pointer).wpl(((frame.imageStride / 4) * Math.abs(frame.imageDepth)) / 8);
        }
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ((ByteBuffer) this.pixBuffer.position(0)).asIntBuffer().put(((ByteBuffer) frame.image[0].position(0)).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer());
        }
        return this.pix;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacv.Frame convert(org.bytedeco.leptonica.PIX r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 != 0) goto L_0x0004
            return r0
        L_0x0004:
            org.bytedeco.leptonica.PIXCMAP r1 = r14.colormap()
            r2 = 4
            r3 = 2
            r4 = 1
            r5 = 8
            r6 = 0
            if (r1 == 0) goto L_0x0016
            org.bytedeco.leptonica.PIX r14 = org.bytedeco.leptonica.global.lept.pixRemoveColormap(r14, r3)
        L_0x0014:
            r0 = r14
            goto L_0x003f
        L_0x0016:
            int r1 = r14.d()
            if (r1 >= r5) goto L_0x003f
            int r1 = r14.d()
            if (r1 == r4) goto L_0x0039
            if (r1 == r3) goto L_0x002c
            if (r1 == r2) goto L_0x0027
            goto L_0x003f
        L_0x0027:
            org.bytedeco.leptonica.PIX r14 = org.bytedeco.leptonica.global.lept.pixConvert4To8(r14, r6)
            goto L_0x0014
        L_0x002c:
            r8 = 0
            r9 = 85
            r10 = -86
            r11 = -1
            r12 = 0
            r7 = r14
            org.bytedeco.leptonica.PIX r14 = org.bytedeco.leptonica.global.lept.pixConvert2To8(r7, r8, r9, r10, r11, r12)
            goto L_0x0014
        L_0x0039:
            r1 = -1
            org.bytedeco.leptonica.PIX r14 = org.bytedeco.leptonica.global.lept.pixConvert1To8(r0, r14, r6, r1)
            goto L_0x0014
        L_0x003f:
            org.bytedeco.javacv.Frame r1 = r13.frame
            boolean r1 = isEqual(r1, r14)
            if (r1 != 0) goto L_0x00c4
            org.bytedeco.javacv.Frame r1 = new org.bytedeco.javacv.Frame
            r1.<init>()
            r13.frame = r1
            org.bytedeco.javacv.Frame r1 = r13.frame
            int r3 = r14.w()
            r1.imageWidth = r3
            org.bytedeco.javacv.Frame r1 = r13.frame
            int r3 = r14.h()
            r1.imageHeight = r3
            org.bytedeco.javacv.Frame r1 = r13.frame
            r1.imageDepth = r5
            org.bytedeco.javacv.Frame r1 = r13.frame
            int r3 = r14.d()
            int r3 = r3 / r5
            r1.imageChannels = r3
            org.bytedeco.javacv.Frame r1 = r13.frame
            int r3 = r14.wpl()
            int r3 = r3 * 4
            r1.imageStride = r3
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ac
            org.bytedeco.javacpp.BytePointer r1 = new org.bytedeco.javacpp.BytePointer
            org.bytedeco.javacv.Frame r2 = r13.frame
            int r2 = r2.imageHeight
            org.bytedeco.javacv.Frame r3 = r13.frame
            int r3 = r3.imageStride
            int r2 = r2 * r3
            long r2 = (long) r2
            r1.<init>((long) r2)
            java.nio.ByteBuffer r2 = r1.asByteBuffer()
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r2 = r2.order(r3)
            r13.frameBuffer = r2
            org.bytedeco.javacv.Frame r2 = r13.frame
            r2.opaque = r1
            org.bytedeco.javacv.Frame r1 = r13.frame
            java.nio.Buffer[] r2 = new java.nio.Buffer[r4]
            java.nio.ByteBuffer r3 = r13.frameBuffer
            r2[r6] = r3
            r1.image = r2
            goto L_0x00c4
        L_0x00ac:
            org.bytedeco.javacv.Frame r1 = r13.frame
            if (r0 == 0) goto L_0x00b5
            org.bytedeco.leptonica.PIX r2 = r14.clone()
            goto L_0x00b6
        L_0x00b5:
            r2 = r14
        L_0x00b6:
            r1.opaque = r2
            org.bytedeco.javacv.Frame r1 = r13.frame
            java.nio.Buffer[] r2 = new java.nio.Buffer[r4]
            java.nio.ByteBuffer r3 = r14.createBuffer()
            r2[r6] = r3
            r1.image = r2
        L_0x00c4:
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ed
            java.nio.ByteBuffer r1 = r13.frameBuffer
            java.nio.Buffer r1 = r1.position(r6)
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
            java.nio.IntBuffer r1 = r1.asIntBuffer()
            java.nio.ByteBuffer r14 = r14.createBuffer()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
            java.nio.ByteBuffer r14 = r14.order(r2)
            java.nio.IntBuffer r14 = r14.asIntBuffer()
            r1.put(r14)
        L_0x00ed:
            if (r0 == 0) goto L_0x00f2
            org.bytedeco.leptonica.global.lept.pixDestroy(r0)
        L_0x00f2:
            org.bytedeco.javacv.Frame r14 = r13.frame
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.LeptonicaFrameConverter.convert(org.bytedeco.leptonica.PIX):org.bytedeco.javacv.Frame");
    }
}
