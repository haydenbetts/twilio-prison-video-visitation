package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvPoint3D32f extends FloatPointer {
    public abstract float x();

    public abstract CvPoint3D32f x(float f);

    public abstract float y();

    public abstract CvPoint3D32f y(float f);

    public abstract float z();

    public abstract CvPoint3D32f z(float f);

    static {
        Loader.load();
    }

    public AbstractCvPoint3D32f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint3D32f get(double[] dArr) {
        return get(dArr, 0, dArr.length);
    }

    public CvPoint3D32f get(double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 3; i3++) {
            position((long) i3);
            int i4 = (i3 * 3) + i;
            dArr[i4] = (double) x();
            dArr[i4 + 1] = (double) y();
            dArr[i4 + 2] = (double) z();
        }
        return (CvPoint3D32f) position(0);
    }

    public final CvPoint3D32f put(double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 3; i3++) {
            position((long) i3);
            int i4 = (i3 * 3) + i;
            put(dArr[i4], dArr[i4 + 1], dArr[i4 + 2]);
        }
        return (CvPoint3D32f) position(0);
    }

    public final CvPoint3D32f put(double... dArr) {
        return put(dArr, 0, dArr.length);
    }

    public CvPoint3D32f put(double d, double d2, double d3) {
        return x((float) d).y((float) d2).z((float) d3);
    }

    public CvPoint3D32f put(CvPoint cvPoint) {
        return x((float) cvPoint.x()).y((float) cvPoint.y()).z(0.0f);
    }

    public CvPoint3D32f put(CvPoint2D32f cvPoint2D32f) {
        return x(cvPoint2D32f.x()).y(cvPoint2D32f.y()).z(0.0f);
    }

    public CvPoint3D32f put(CvPoint2D64f cvPoint2D64f) {
        return x((float) cvPoint2D64f.x()).y((float) cvPoint2D64f.y()).z(0.0f);
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        if (capacity() == 0) {
            return "(" + x() + ", " + y() + ", " + z() + ")";
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
            sb.append(", ");
            sb.append(z());
            sb.append(")");
            str2 = sb.toString();
        }
        position(position);
        return str2;
    }
}
