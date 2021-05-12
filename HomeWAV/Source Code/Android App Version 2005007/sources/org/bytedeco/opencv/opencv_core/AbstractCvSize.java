package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvSize extends IntPointer {
    public static final CvSize ZERO = new CvSize().width(0).height(0);

    public abstract int height();

    public abstract CvSize height(int i);

    public abstract int width();

    public abstract CvSize width(int i);

    static {
        Loader.load();
    }

    public AbstractCvSize(Pointer pointer) {
        super(pointer);
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        if (capacity() == 0) {
            return "(" + width() + ", " + height() + ")";
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
            sb.append(width());
            sb.append(", ");
            sb.append(height());
            sb.append(")");
            str2 = sb.toString();
        }
        position(position);
        return str2;
    }
}
