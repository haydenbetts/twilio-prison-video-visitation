package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvPoint3D64f extends DoublePointer {
    public abstract double x();

    public abstract CvPoint3D64f x(double d);

    public abstract double y();

    public abstract CvPoint3D64f y(double d);

    public abstract double z();

    public abstract CvPoint3D64f z(double d);

    static {
        Loader.load();
    }

    public AbstractCvPoint3D64f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint3D64f get(double[] dArr) {
        return get(dArr, 0, dArr.length);
    }

    public CvPoint3D64f get(double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 3; i3++) {
            position((long) i3);
            int i4 = (i3 * 3) + i;
            dArr[i4] = x();
            dArr[i4 + 1] = y();
            dArr[i4 + 2] = z();
        }
        return (CvPoint3D64f) position(0);
    }

    public final CvPoint3D64f put(double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 3; i3++) {
            position((long) i3);
            int i4 = (i3 * 3) + i;
            put(dArr[i4], dArr[i4 + 1], dArr[i4 + 2]);
        }
        return (CvPoint3D64f) position(0);
    }

    public final CvPoint3D64f put(double... dArr) {
        return put(dArr, 0, dArr.length);
    }

    public CvPoint3D64f put(double d, double d2, double d3) {
        return x(x()).y(y()).z(z());
    }

    public CvPoint3D64f put(CvPoint cvPoint) {
        return x((double) cvPoint.x()).y((double) cvPoint.y()).z(0.0d);
    }

    public CvPoint3D64f put(CvPoint2D32f cvPoint2D32f) {
        return x((double) cvPoint2D32f.x()).y((double) cvPoint2D32f.y()).z(0.0d);
    }

    public CvPoint3D64f put(CvPoint2D64f cvPoint2D64f) {
        return x(cvPoint2D64f.x()).y(cvPoint2D64f.y()).z(0.0d);
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        if (capacity() == 0) {
            return "(" + ((float) x()) + ", " + ((float) y()) + ", " + ((float) z()) + ")";
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
            sb.append((float) x());
            sb.append(", ");
            sb.append((float) y());
            sb.append(", ");
            sb.append((float) z());
            sb.append(")");
            str2 = sb.toString();
        }
        position(position);
        return str2;
    }
}
