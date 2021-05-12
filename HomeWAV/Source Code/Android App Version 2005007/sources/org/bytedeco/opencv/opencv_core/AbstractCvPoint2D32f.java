package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvPoint2D32f extends FloatPointer {
    public abstract float x();

    public abstract CvPoint2D32f x(float f);

    public abstract float y();

    public abstract CvPoint2D32f y(float f);

    static {
        Loader.load();
    }

    public AbstractCvPoint2D32f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint2D32f get(double[] dArr) {
        return get(dArr, 0, dArr.length);
    }

    public CvPoint2D32f get(double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 2; i3++) {
            position((long) i3);
            int i4 = (i3 * 2) + i;
            dArr[i4] = (double) x();
            dArr[i4 + 1] = (double) y();
        }
        return (CvPoint2D32f) position(0);
    }

    public final CvPoint2D32f put(double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2 / 2; i3++) {
            position((long) i3);
            int i4 = (i3 * 2) + i;
            put(dArr[i4], dArr[i4 + 1]);
        }
        return (CvPoint2D32f) position(0);
    }

    public final CvPoint2D32f put(double... dArr) {
        return put(dArr, 0, dArr.length);
    }

    public CvPoint2D32f put(double d, double d2) {
        return x((float) d).y((float) d2);
    }

    public CvPoint2D32f put(CvPoint cvPoint) {
        return x((float) cvPoint.x()).y((float) cvPoint.y());
    }

    public CvPoint2D32f put(CvPoint2D32f cvPoint2D32f) {
        return x(cvPoint2D32f.x()).y(cvPoint2D32f.y());
    }

    public CvPoint2D32f put(CvPoint2D64f cvPoint2D64f) {
        return x((float) cvPoint2D64f.x()).y((float) cvPoint2D64f.y());
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
