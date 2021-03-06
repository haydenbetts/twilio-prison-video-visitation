package org.opencv.aruco;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint3f;
import org.opencv.utils.Converters;

public class Board {
    protected final long nativeObj;

    private static native long create_0(long j, long j2, long j3);

    private static native void delete(long j);

    private static native long get_dictionary_0(long j);

    private static native long get_ids_0(long j);

    private static native long get_objPoints_0(long j);

    protected Board(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static Board __fromPtr__(long j) {
        return new Board(j);
    }

    public static Board create(List<Mat> list, Dictionary dictionary, Mat mat) {
        return __fromPtr__(create_0(Converters.vector_Mat_to_Mat(list).nativeObj, dictionary.getNativeObjAddr(), mat.nativeObj));
    }

    public List<MatOfPoint3f> get_objPoints() {
        ArrayList arrayList = new ArrayList();
        Converters.Mat_to_vector_vector_Point3f(new Mat(get_objPoints_0(this.nativeObj)), arrayList);
        return arrayList;
    }

    public Dictionary get_dictionary() {
        return Dictionary.__fromPtr__(get_dictionary_0(this.nativeObj));
    }

    public MatOfInt get_ids() {
        return MatOfInt.fromNativeAddr(get_ids_0(this.nativeObj));
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
