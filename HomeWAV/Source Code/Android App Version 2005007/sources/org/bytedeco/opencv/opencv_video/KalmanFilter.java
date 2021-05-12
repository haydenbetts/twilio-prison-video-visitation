package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
@NoOffset
public class KalmanFilter extends Pointer {
    private native void allocate();

    private native void allocate(int i, int i2);

    private native void allocate(int i, int i2, int i3, int i4);

    private native void allocateArray(long j);

    @ByRef
    public native Mat controlMatrix();

    public native KalmanFilter controlMatrix(Mat mat);

    @ByRef
    @Const
    public native Mat correct(@ByRef @Const Mat mat);

    @ByRef
    public native Mat errorCovPost();

    public native KalmanFilter errorCovPost(Mat mat);

    @ByRef
    public native Mat errorCovPre();

    public native KalmanFilter errorCovPre(Mat mat);

    @ByRef
    public native Mat gain();

    public native KalmanFilter gain(Mat mat);

    public native void init(int i, int i2);

    public native void init(int i, int i2, int i3, int i4);

    @ByRef
    public native Mat measurementMatrix();

    public native KalmanFilter measurementMatrix(Mat mat);

    @ByRef
    public native Mat measurementNoiseCov();

    public native KalmanFilter measurementNoiseCov(Mat mat);

    @ByRef
    @Const
    public native Mat predict();

    @ByRef
    @Const
    public native Mat predict(@ByRef(nullValue = "cv::Mat()") @Const Mat mat);

    @ByRef
    public native Mat processNoiseCov();

    public native KalmanFilter processNoiseCov(Mat mat);

    @ByRef
    public native Mat statePost();

    public native KalmanFilter statePost(Mat mat);

    @ByRef
    public native Mat statePre();

    public native KalmanFilter statePre(Mat mat);

    @ByRef
    public native Mat temp1();

    public native KalmanFilter temp1(Mat mat);

    @ByRef
    public native Mat temp2();

    public native KalmanFilter temp2(Mat mat);

    @ByRef
    public native Mat temp3();

    public native KalmanFilter temp3(Mat mat);

    @ByRef
    public native Mat temp4();

    public native KalmanFilter temp4(Mat mat);

    @ByRef
    public native Mat temp5();

    public native KalmanFilter temp5(Mat mat);

    @ByRef
    public native Mat transitionMatrix();

    public native KalmanFilter transitionMatrix(Mat mat);

    static {
        Loader.load();
    }

    public KalmanFilter(Pointer pointer) {
        super(pointer);
    }

    public KalmanFilter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public KalmanFilter position(long j) {
        return (KalmanFilter) super.position(j);
    }

    public KalmanFilter getPointer(long j) {
        return new KalmanFilter((Pointer) this).position(this.position + j);
    }

    public KalmanFilter() {
        super((Pointer) null);
        allocate();
    }

    public KalmanFilter(int i, int i2, int i3, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public KalmanFilter(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }
}
