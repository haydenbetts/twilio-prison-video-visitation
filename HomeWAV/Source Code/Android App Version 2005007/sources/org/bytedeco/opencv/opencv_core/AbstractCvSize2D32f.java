package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvSize2D32f extends FloatPointer {
    public abstract float height();

    public abstract CvSize2D32f height(float f);

    public abstract float width();

    public abstract CvSize2D32f width(float f);

    static {
        Loader.load();
    }

    public AbstractCvSize2D32f(Pointer pointer) {
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
