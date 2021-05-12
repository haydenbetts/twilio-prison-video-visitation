package org.bytedeco.opencv.opencv_imgproc;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Namespace("cv")
@Properties(inherit = {opencv_imgproc.class})
@NoOffset
public class Subdiv2D extends Pointer {
    public static final int NEXT_AROUND_DST = 34;
    public static final int NEXT_AROUND_LEFT = 19;
    public static final int NEXT_AROUND_ORG = 0;
    public static final int NEXT_AROUND_RIGHT = 49;
    public static final int PREV_AROUND_DST = 51;
    public static final int PREV_AROUND_LEFT = 32;
    public static final int PREV_AROUND_ORG = 17;
    public static final int PREV_AROUND_RIGHT = 2;
    public static final int PTLOC_ERROR = -2;
    public static final int PTLOC_INSIDE = 0;
    public static final int PTLOC_ON_EDGE = 2;
    public static final int PTLOC_OUTSIDE_RECT = -1;
    public static final int PTLOC_VERTEX = 1;

    private native void allocate();

    private native void allocate(@ByVal Rect rect);

    private native void allocateArray(long j);

    public native int edgeDst(int i);

    public native int edgeDst(int i, Point2f point2f);

    public native int edgeOrg(int i);

    public native int edgeOrg(int i, Point2f point2f);

    public native int findNearest(@ByVal Point2f point2f);

    public native int findNearest(@ByVal Point2f point2f, Point2f point2f2);

    public native int getEdge(int i, int i2);

    public native void getEdgeList(@ByRef Vec4fVector vec4fVector);

    public native void getLeadingEdgeList(@StdVector IntBuffer intBuffer);

    public native void getLeadingEdgeList(@StdVector IntPointer intPointer);

    public native void getLeadingEdgeList(@StdVector int[] iArr);

    public native void getTriangleList(@ByRef Vec6fVector vec6fVector);

    @ByVal
    public native Point2f getVertex(int i);

    @ByVal
    public native Point2f getVertex(int i, IntBuffer intBuffer);

    @ByVal
    public native Point2f getVertex(int i, IntPointer intPointer);

    @ByVal
    public native Point2f getVertex(int i, int[] iArr);

    public native void getVoronoiFacetList(@StdVector IntBuffer intBuffer, @ByRef Point2fVectorVector point2fVectorVector, @ByRef Point2fVector point2fVector);

    public native void getVoronoiFacetList(@StdVector IntPointer intPointer, @ByRef Point2fVectorVector point2fVectorVector, @ByRef Point2fVector point2fVector);

    public native void getVoronoiFacetList(@StdVector int[] iArr, @ByRef Point2fVectorVector point2fVectorVector, @ByRef Point2fVector point2fVector);

    public native void initDelaunay(@ByVal Rect rect);

    public native int insert(@ByVal Point2f point2f);

    public native void insert(@ByRef @Const Point2fVector point2fVector);

    public native int locate(@ByVal Point2f point2f, @ByRef IntBuffer intBuffer, @ByRef IntBuffer intBuffer2);

    public native int locate(@ByVal Point2f point2f, @ByRef IntPointer intPointer, @ByRef IntPointer intPointer2);

    public native int locate(@ByVal Point2f point2f, @ByRef int[] iArr, @ByRef int[] iArr2);

    public native int nextEdge(int i);

    public native int rotateEdge(int i, int i2);

    public native int symEdge(int i);

    static {
        Loader.load();
    }

    public Subdiv2D(Pointer pointer) {
        super(pointer);
    }

    public Subdiv2D(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Subdiv2D position(long j) {
        return (Subdiv2D) super.position(j);
    }

    public Subdiv2D getPointer(long j) {
        return new Subdiv2D((Pointer) this).position(this.position + j);
    }

    public Subdiv2D() {
        super((Pointer) null);
        allocate();
    }

    public Subdiv2D(@ByVal Rect rect) {
        super((Pointer) null);
        allocate(rect);
    }
}
