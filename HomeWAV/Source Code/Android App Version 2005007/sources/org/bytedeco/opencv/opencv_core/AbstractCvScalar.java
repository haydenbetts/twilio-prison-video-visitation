package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvScalar extends DoublePointer {
    public static final CvScalar ALPHA1 = new CvScalar().val(0, 0.0d).val(1, 0.0d).val(2, 0.0d).val(3, 1.0d);
    public static final CvScalar ALPHA255 = new CvScalar().val(0, 0.0d).val(1, 0.0d).val(2, 0.0d).val(3, 255.0d);
    public static final CvScalar BLACK = org.bytedeco.opencv.global.opencv_core.CV_RGB(0.0d, 0.0d, 0.0d);
    public static final CvScalar BLUE = org.bytedeco.opencv.global.opencv_core.CV_RGB(0.0d, 0.0d, 255.0d);
    public static final CvScalar CYAN = org.bytedeco.opencv.global.opencv_core.CV_RGB(0.0d, 255.0d, 255.0d);
    public static final CvScalar GRAY = org.bytedeco.opencv.global.opencv_core.CV_RGB(128.0d, 128.0d, 128.0d);
    public static final CvScalar GREEN = org.bytedeco.opencv.global.opencv_core.CV_RGB(0.0d, 255.0d, 0.0d);
    public static final CvScalar MAGENTA = org.bytedeco.opencv.global.opencv_core.CV_RGB(255.0d, 0.0d, 255.0d);
    public static final CvScalar ONE = new CvScalar().val(0, 1.0d).val(1, 1.0d).val(2, 1.0d).val(3, 1.0d);
    public static final CvScalar ONEHALF = new CvScalar().val(0, 0.5d).val(1, 0.5d).val(2, 0.5d).val(3, 0.5d);
    public static final CvScalar RED = org.bytedeco.opencv.global.opencv_core.CV_RGB(255.0d, 0.0d, 0.0d);
    public static final CvScalar WHITE = org.bytedeco.opencv.global.opencv_core.CV_RGB(255.0d, 255.0d, 255.0d);
    public static final CvScalar YELLOW = org.bytedeco.opencv.global.opencv_core.CV_RGB(255.0d, 255.0d, 0.0d);
    public static final CvScalar ZERO = new CvScalar().val(0, 0.0d).val(1, 0.0d).val(2, 0.0d).val(3, 0.0d);

    public abstract double val(int i);

    public abstract DoublePointer val();

    public abstract CvScalar val(int i, double d);

    static {
        Loader.load();
    }

    public AbstractCvScalar(Pointer pointer) {
        super(pointer);
    }

    public double getVal(int i) {
        return val(i);
    }

    public CvScalar setVal(int i, double d) {
        return val(i, d);
    }

    public DoublePointer getDoublePointerVal() {
        return val();
    }

    public LongPointer getLongPointerVal() {
        return new LongPointer((Pointer) val());
    }

    public void scale(double d) {
        for (int i = 0; i < 4; i++) {
            val(i, val(i) * d);
        }
    }

    public double red() {
        return val(2);
    }

    public double green() {
        return val(1);
    }

    public double blue() {
        return val(0);
    }

    public CvScalar red(double d) {
        val(2, d);
        return (CvScalar) this;
    }

    public CvScalar green(double d) {
        val(1, d);
        return (CvScalar) this;
    }

    public CvScalar blue(double d) {
        val(0, d);
        return (CvScalar) this;
    }

    public double magnitude() {
        return Math.sqrt((val(0) * val(0)) + (val(1) * val(1)) + (val(2) * val(2)) + (val(3) * val(3)));
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        long j = 0;
        if (capacity() == 0) {
            return "(" + ((float) val(0)) + ", " + ((float) val(1)) + ", " + ((float) val(2)) + ", " + ((float) val(3)) + ")";
        }
        long position = position();
        String str2 = "";
        long j2 = 0;
        while (j2 < capacity()) {
            position(j2);
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if (j2 == j) {
                str = "(";
            } else {
                str = " (";
            }
            sb.append(str);
            sb.append((float) val(0));
            sb.append(", ");
            sb.append((float) val(1));
            sb.append(", ");
            sb.append((float) val(2));
            sb.append(", ");
            sb.append((float) val(3));
            sb.append(")");
            str2 = sb.toString();
            j2++;
            j = 0;
        }
        position(position);
        return str2;
    }
}
