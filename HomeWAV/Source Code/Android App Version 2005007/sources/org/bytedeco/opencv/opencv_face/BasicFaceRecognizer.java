package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
@NoOffset
public class BasicFaceRecognizer extends FaceRecognizer {
    @Cast({"bool"})
    public native boolean empty();

    @ByVal
    public native Mat getEigenValues();

    @ByVal
    public native Mat getEigenVectors();

    @ByVal
    public native Mat getLabels();

    @ByVal
    public native Mat getMean();

    public native int getNumComponents();

    @ByVal
    public native MatVector getProjections();

    public native double getThreshold();

    public native void read(@ByRef @Const FileNode fileNode);

    public native void setNumComponents(int i);

    public native void setThreshold(double d);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public BasicFaceRecognizer(Pointer pointer) {
        super(pointer);
    }
}
