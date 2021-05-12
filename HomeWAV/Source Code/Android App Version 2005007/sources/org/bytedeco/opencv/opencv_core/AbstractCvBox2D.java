package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvBox2D extends FloatPointer {
    public abstract float angle();

    public abstract CvBox2D angle(float f);

    public abstract CvBox2D center(CvPoint2D32f cvPoint2D32f);

    public abstract CvPoint2D32f center();

    public abstract CvBox2D size(CvSize2D32f cvSize2D32f);

    public abstract CvSize2D32f size();

    static {
        Loader.load();
    }

    public AbstractCvBox2D(Pointer pointer) {
        super(pointer);
    }

    public String toString() {
        String str;
        if (isNull()) {
            return super.toString();
        }
        if (capacity() == 0) {
            return "(" + center() + ", " + size() + ", " + angle() + ")";
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
            sb.append(center());
            sb.append(", ");
            sb.append(size());
            sb.append(", ");
            sb.append(angle());
            sb.append(")");
            str2 = sb.toString();
        }
        position(position);
        return str2;
    }
}
