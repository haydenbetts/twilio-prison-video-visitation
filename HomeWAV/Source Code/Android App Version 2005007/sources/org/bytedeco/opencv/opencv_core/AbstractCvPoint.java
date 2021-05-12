package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvPoint extends IntPointer {
    public static final CvPoint ZERO = new CvPoint().x(0).y(0);

    public abstract int x();

    public abstract CvPoint x(int i);

    public abstract int y();

    public abstract CvPoint y(int i);

    static {
        Loader.load();
    }

    public AbstractCvPoint(Pointer pointer) {
        super(pointer);
    }

    public CvPoint get(int[] iArr) {
        return get(iArr, 0, iArr.length);
    }

    public CvPoint get(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 2; i3++) {
            position((long) i3);
            int i4 = (i3 * 2) + i;
            iArr[i4] = x();
            iArr[i4 + 1] = y();
        }
        return (CvPoint) position(0);
    }

    public final CvPoint put(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 2; i3++) {
            position((long) i3);
            int i4 = (i3 * 2) + i;
            put(iArr[i4], iArr[i4 + 1]);
        }
        return (CvPoint) position(0);
    }

    public final CvPoint put(int... iArr) {
        return put(iArr, 0, iArr.length);
    }

    public final CvPoint put(byte b, double[] dArr, int i, int i2) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = (int) Math.round(dArr[i + i3] * ((double) (1 << b)));
        }
        return put(iArr, 0, i2);
    }

    public final CvPoint put(byte b, double... dArr) {
        return put(b, dArr, 0, dArr.length);
    }

    public CvPoint put(int i, int i2) {
        return x(i).y(i2);
    }

    public CvPoint put(CvPoint cvPoint) {
        return x(cvPoint.x()).y(cvPoint.y());
    }

    public CvPoint put(byte b, CvPoint2D32f cvPoint2D32f) {
        float f = (float) (1 << b);
        x(Math.round(cvPoint2D32f.x() * f));
        y(Math.round(cvPoint2D32f.y() * f));
        return (CvPoint) this;
    }

    public CvPoint put(byte b, CvPoint2D64f cvPoint2D64f) {
        double d = (double) (1 << b);
        x((int) Math.round(cvPoint2D64f.x() * d));
        y((int) Math.round(cvPoint2D64f.y() * d));
        return (CvPoint) this;
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        if (capacity() == 0) {
            return "(" + x() + ", " + y() + ")";
        }
        long position = position();
        String str2 = "";
        for (long j = 0; j < capacity(); j++) {
            position(j);
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if (j == 0) {
                str = "(";
            } else {
                str = " (";
            }
            sb.append(str);
            sb.append(x());
            sb.append(", ");
            sb.append(y());
            sb.append(")");
            str2 = sb.toString();
        }
        position(position);
        return str2;
    }
}
