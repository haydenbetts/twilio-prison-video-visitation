package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvRect extends IntPointer {
    public abstract int height();

    public abstract int width();

    public abstract int x();

    public abstract int y();

    static {
        Loader.load();
    }

    public AbstractCvRect(Pointer pointer) {
        super(pointer);
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        if (capacity() == 0) {
            return "(" + x() + ", " + y() + "; " + width() + ", " + height() + ")";
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
            sb.append("; ");
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
