package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractScalar extends DoublePointer {
    public static final Scalar ALPHA1 = new Scalar(0.0d, 0.0d, 0.0d, 1.0d);
    public static final Scalar ALPHA255 = new Scalar(0.0d, 0.0d, 0.0d, 255.0d);
    public static final Scalar BLACK = org.bytedeco.opencv.global.opencv_core.RGB(0.0d, 0.0d, 0.0d);
    public static final Scalar BLUE = org.bytedeco.opencv.global.opencv_core.RGB(0.0d, 0.0d, 255.0d);
    public static final Scalar CYAN = org.bytedeco.opencv.global.opencv_core.RGB(0.0d, 255.0d, 255.0d);
    public static final Scalar GRAY = org.bytedeco.opencv.global.opencv_core.RGB(128.0d, 128.0d, 128.0d);
    public static final Scalar GREEN = org.bytedeco.opencv.global.opencv_core.RGB(0.0d, 255.0d, 0.0d);
    public static final Scalar MAGENTA = org.bytedeco.opencv.global.opencv_core.RGB(255.0d, 0.0d, 255.0d);
    public static final Scalar ONE = new Scalar(1.0d, 1.0d, 1.0d, 1.0d);
    public static final Scalar ONEHALF = new Scalar(0.5d, 0.5d, 0.5d, 0.5d);
    public static final Scalar RED = org.bytedeco.opencv.global.opencv_core.RGB(255.0d, 0.0d, 0.0d);
    public static final Scalar WHITE = org.bytedeco.opencv.global.opencv_core.RGB(255.0d, 255.0d, 255.0d);
    public static final Scalar YELLOW = org.bytedeco.opencv.global.opencv_core.RGB(255.0d, 255.0d, 0.0d);
    public static final Scalar ZERO = new Scalar(0.0d, 0.0d, 0.0d, 0.0d);

    static {
        Loader.load();
    }

    public AbstractScalar(Pointer pointer) {
        super(pointer);
    }

    public void scale(double d) {
        for (int i = 0; i < 4; i++) {
            long j = (long) i;
            put(j, get(j) * d);
        }
    }

    public double red() {
        return get(2);
    }

    public double green() {
        return get(1);
    }

    public double blue() {
        return get(0);
    }

    public Scalar red(double d) {
        put(2, d);
        return (Scalar) this;
    }

    public Scalar green(double d) {
        put(1, d);
        return (Scalar) this;
    }

    public Scalar blue(double d) {
        put(0, d);
        return (Scalar) this;
    }

    public double magnitude() {
        return Math.sqrt((get(0) * get(0)) + (get(1) * get(1)) + (get(2) * get(2)) + (get(3) * get(3)));
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        long j = 0;
        if (capacity() == 0) {
            return "(" + ((float) get(0)) + ", " + ((float) get(1)) + ", " + ((float) get(2)) + ", " + ((float) get(3)) + ")";
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
            sb.append((float) get(j));
            sb.append(", ");
            sb.append((float) get(1));
            sb.append(", ");
            sb.append((float) get(2));
            sb.append(", ");
            sb.append((float) get(3));
            sb.append(")");
            str2 = sb.toString();
            j2++;
            j = 0;
        }
        position(position);
        return str2;
    }
}
