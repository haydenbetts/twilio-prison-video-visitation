package org.bytedeco.opencv.global;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvBox2D;
import org.bytedeco.opencv.opencv_core.CvCmpFunc;
import org.bytedeco.opencv.opencv_core.CvErrorCallback;
import org.bytedeco.opencv.opencv_core.CvGraph;
import org.bytedeco.opencv.opencv_core.CvGraphEdge;
import org.bytedeco.opencv.opencv_core.CvGraphScanner;
import org.bytedeco.opencv.opencv_core.CvGraphVtx;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvMatND;
import org.bytedeco.opencv.opencv_core.CvMemStorage;
import org.bytedeco.opencv.opencv_core.CvMemStoragePos;
import org.bytedeco.opencv.opencv_core.CvNArrayIterator;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvPoint2D32f;
import org.bytedeco.opencv.opencv_core.CvPoint2D64f;
import org.bytedeco.opencv.opencv_core.CvPoint3D32f;
import org.bytedeco.opencv.opencv_core.CvPoint3D64f;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.CvSeqBlock;
import org.bytedeco.opencv.opencv_core.CvSeqReader;
import org.bytedeco.opencv.opencv_core.CvSeqWriter;
import org.bytedeco.opencv.opencv_core.CvSet;
import org.bytedeco.opencv.opencv_core.CvSetElem;
import org.bytedeco.opencv.opencv_core.CvSize;
import org.bytedeco.opencv.opencv_core.CvSize2D32f;
import org.bytedeco.opencv.opencv_core.CvSlice;
import org.bytedeco.opencv.opencv_core.CvSparseMat;
import org.bytedeco.opencv.opencv_core.CvSparseMatIterator;
import org.bytedeco.opencv.opencv_core.CvSparseNode;
import org.bytedeco.opencv.opencv_core.CvTermCriteria;
import org.bytedeco.opencv.opencv_core.CvTreeNodeIterator;
import org.bytedeco.opencv.opencv_core.Cv_iplAllocateImageData;
import org.bytedeco.opencv.opencv_core.Cv_iplCloneImage;
import org.bytedeco.opencv.opencv_core.Cv_iplCreateImageHeader;
import org.bytedeco.opencv.opencv_core.Cv_iplCreateROI;
import org.bytedeco.opencv.opencv_core.Cv_iplDeallocate;
import org.bytedeco.opencv.opencv_core.DMatch;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.ErrorCallback;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileNodeIterator;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Formatted;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.InstrNode;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplROI;
import org.bytedeco.opencv.opencv_core.KeyPoint;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatAllocator;
import org.bytedeco.opencv.opencv_core.MatExpr;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.NodeData;
import org.bytedeco.opencv.opencv_core.ParallelLoopBody;
import org.bytedeco.opencv.opencv_core.PlatformInfo;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.RNG;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RotatedRect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.SparseMat;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.TickMeter;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_core.float16_t;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_core extends org.bytedeco.opencv.helper.opencv_core {
    public static final int ACCESS_FAST = 67108864;
    public static final int ACCESS_MASK = 50331648;
    public static final int ACCESS_READ = 16777216;
    public static final int ACCESS_RW = 50331648;
    public static final int ACCESS_WRITE = 33554432;
    public static final int ALGORITHM = 6;
    public static final int BOOLEAN = 1;
    public static final int BORDER_CONSTANT = 0;
    public static final int BORDER_DEFAULT = 4;
    public static final int BORDER_ISOLATED = 16;
    public static final int BORDER_REFLECT = 2;
    public static final int BORDER_REFLECT101 = 4;
    public static final int BORDER_REFLECT_101 = 4;
    public static final int BORDER_REPLICATE = 1;
    public static final int BORDER_TRANSPARENT = 5;
    public static final int BORDER_WRAP = 3;
    public static final int BadAlign = -21;
    public static final int BadAlphaChannel = -18;
    public static final int BadCOI = -24;
    public static final int BadCallBack = -22;
    public static final int BadDataPtr = -12;
    public static final int BadDepth = -17;
    public static final int BadImageSize = -10;
    public static final int BadModelOrChSeq = -14;
    public static final int BadNumChannel1U = -16;
    public static final int BadNumChannels = -15;
    public static final int BadOffset = -11;
    public static final int BadOrder = -19;
    public static final int BadOrigin = -20;
    public static final int BadROISize = -25;
    public static final int BadStep = -13;
    public static final int BadTileSize = -23;
    public static final int CMP_EQ = 0;
    public static final int CMP_GE = 2;
    public static final int CMP_GT = 1;
    public static final int CMP_LE = 4;
    public static final int CMP_LT = 3;
    public static final int CMP_NE = 5;
    public static final int COVAR_COLS = 16;
    public static final int COVAR_NORMAL = 1;
    public static final int COVAR_ROWS = 8;
    public static final int COVAR_SCALE = 4;
    public static final int COVAR_SCRAMBLED = 0;
    public static final int COVAR_USE_AVG = 2;
    public static final int CPU_AVX = 10;
    public static final int CPU_AVX2 = 11;
    public static final int CPU_AVX512_CLX = 261;
    public static final int CPU_AVX512_CNL = 260;
    public static final int CPU_AVX512_COMMON = 257;
    public static final int CPU_AVX512_ICL = 262;
    public static final int CPU_AVX512_KNL = 258;
    public static final int CPU_AVX512_KNM = 259;
    public static final int CPU_AVX512_SKX = 256;
    public static final int CPU_AVX_5124FMAPS = 27;
    public static final int CPU_AVX_5124VNNIW = 26;
    public static final int CPU_AVX_512BITALG = 24;
    public static final int CPU_AVX_512BW = 14;
    public static final int CPU_AVX_512CD = 15;
    public static final int CPU_AVX_512DQ = 16;
    public static final int CPU_AVX_512ER = 17;
    public static final int CPU_AVX_512F = 13;
    public static final int CPU_AVX_512IFMA = 18;
    public static final int CPU_AVX_512IFMA512 = 18;
    public static final int CPU_AVX_512PF = 19;
    public static final int CPU_AVX_512VBMI = 20;
    public static final int CPU_AVX_512VBMI2 = 22;
    public static final int CPU_AVX_512VL = 21;
    public static final int CPU_AVX_512VNNI = 23;
    public static final int CPU_AVX_512VPOPCNTDQ = 25;
    public static final int CPU_FMA3 = 12;
    public static final int CPU_FP16 = 9;
    public static final int CPU_MAX_FEATURE = 512;
    public static final int CPU_MMX = 1;
    public static final int CPU_MSA = 150;
    public static final int CPU_NEON = 100;
    public static final int CPU_POPCNT = 8;
    public static final int CPU_SSE = 2;
    public static final int CPU_SSE2 = 3;
    public static final int CPU_SSE3 = 4;
    public static final int CPU_SSE4_1 = 6;
    public static final int CPU_SSE4_2 = 7;
    public static final int CPU_SSSE3 = 5;
    public static final int CPU_VSX = 200;
    public static final int CPU_VSX3 = 201;
    public static final int CV_16F = 7;
    public static final int CV_16FC1 = CV_16FC1();
    public static final int CV_16FC2 = CV_16FC2();
    public static final int CV_16FC3 = CV_16FC3();
    public static final int CV_16FC4 = CV_16FC4();
    public static final int CV_16S = 3;
    public static final int CV_16SC1 = CV_MAKETYPE(3, 1);
    public static final int CV_16SC2 = CV_MAKETYPE(3, 2);
    public static final int CV_16SC3 = CV_MAKETYPE(3, 3);
    public static final int CV_16SC4 = CV_MAKETYPE(3, 4);
    public static final int CV_16U = 2;
    public static final int CV_16UC1 = CV_MAKETYPE(2, 1);
    public static final int CV_16UC2 = CV_MAKETYPE(2, 2);
    public static final int CV_16UC3 = CV_MAKETYPE(2, 3);
    public static final int CV_16UC4 = CV_MAKETYPE(2, 4);
    public static final double CV_2PI = 6.283185307179586d;
    public static final int CV_32F = 5;
    public static final int CV_32FC1 = CV_MAKETYPE(5, 1);
    public static final int CV_32FC2 = CV_MAKETYPE(5, 2);
    public static final int CV_32FC3;
    public static final int CV_32FC4 = CV_MAKETYPE(5, 4);
    public static final int CV_32S = 4;
    public static final int CV_32SC1;
    public static final int CV_32SC2;
    public static final int CV_32SC3 = CV_MAKETYPE(4, 3);
    public static final int CV_32SC4 = CV_MAKETYPE(4, 4);
    public static final int CV_64F = 6;
    public static final int CV_64FC1 = CV_MAKETYPE(6, 1);
    public static final int CV_64FC2 = CV_MAKETYPE(6, 2);
    public static final int CV_64FC3 = CV_MAKETYPE(6, 3);
    public static final int CV_64FC4 = CV_MAKETYPE(6, 4);
    public static final int CV_8S = 1;
    public static final int CV_8SC1 = CV_MAKETYPE(1, 1);
    public static final int CV_8SC2 = CV_MAKETYPE(1, 2);
    public static final int CV_8SC3 = CV_MAKETYPE(1, 3);
    public static final int CV_8SC4 = CV_MAKETYPE(1, 4);
    public static final int CV_8U = 0;
    public static final int CV_8UC1;
    public static final int CV_8UC2 = CV_MAKETYPE(0, 2);
    public static final int CV_8UC3 = CV_MAKETYPE(0, 3);
    public static final int CV_8UC4 = CV_MAKETYPE(0, 4);
    public static final int CV_AUTOSTEP = Integer.MAX_VALUE;
    public static final int CV_AUTO_STEP = Integer.MAX_VALUE;
    public static final int CV_BACK = 0;
    public static final int CV_BadAlign = -21;
    public static final int CV_BadAlphaChannel = -18;
    public static final int CV_BadCOI = -24;
    public static final int CV_BadCallBack = -22;
    public static final int CV_BadDataPtr = -12;
    public static final int CV_BadDepth = -17;
    public static final int CV_BadImageSize = -10;
    public static final int CV_BadModelOrChSeq = -14;
    public static final int CV_BadNumChannel1U = -16;
    public static final int CV_BadNumChannels = -15;
    public static final int CV_BadOffset = -11;
    public static final int CV_BadOrder = -19;
    public static final int CV_BadOrigin = -20;
    public static final int CV_BadROISize = -25;
    public static final int CV_BadStep = -13;
    public static final int CV_BadTileSize = -23;
    public static final int CV_C = 1;
    public static final int CV_CHECK_QUIET = 2;
    public static final int CV_CHECK_RANGE = 1;
    public static final int CV_CHOLESKY = 3;
    public static final int CV_CMP_EQ = 0;
    public static final int CV_CMP_GE = 2;
    public static final int CV_CMP_GT = 1;
    public static final int CV_CMP_LE = 4;
    public static final int CV_CMP_LT = 3;
    public static final int CV_CMP_NE = 5;
    public static final int CV_CN_MAX = 512;
    public static final int CV_CN_SHIFT = 3;
    public static final int CV_COVAR_COLS = 16;
    public static final int CV_COVAR_NORMAL = 1;
    public static final int CV_COVAR_ROWS = 8;
    public static final int CV_COVAR_SCALE = 4;
    public static final int CV_COVAR_SCRAMBLED = 0;
    public static final int CV_COVAR_USE_AVG = 2;
    public static final int CV_CPU_AVX = 10;
    public static final int CV_CPU_AVX2 = 11;
    public static final int CV_CPU_AVX512_CLX = 261;
    public static final int CV_CPU_AVX512_CNL = 260;
    public static final int CV_CPU_AVX512_COMMON = 257;
    public static final int CV_CPU_AVX512_ICL = 262;
    public static final int CV_CPU_AVX512_KNL = 258;
    public static final int CV_CPU_AVX512_KNM = 259;
    public static final int CV_CPU_AVX512_SKX = 256;
    public static final int CV_CPU_AVX_5124FMAPS = 27;
    public static final int CV_CPU_AVX_5124VNNIW = 26;
    public static final int CV_CPU_AVX_512BITALG = 24;
    public static final int CV_CPU_AVX_512BW = 14;
    public static final int CV_CPU_AVX_512CD = 15;
    public static final int CV_CPU_AVX_512DQ = 16;
    public static final int CV_CPU_AVX_512ER = 17;
    public static final int CV_CPU_AVX_512F = 13;
    public static final int CV_CPU_AVX_512IFMA = 18;
    public static final int CV_CPU_AVX_512IFMA512 = 18;
    public static final int CV_CPU_AVX_512PF = 19;
    public static final int CV_CPU_AVX_512VBMI = 20;
    public static final int CV_CPU_AVX_512VBMI2 = 22;
    public static final int CV_CPU_AVX_512VL = 21;
    public static final int CV_CPU_AVX_512VNNI = 23;
    public static final int CV_CPU_AVX_512VPOPCNTDQ = 25;
    public static final int CV_CPU_FMA3 = 12;
    public static final int CV_CPU_FP16 = 9;
    public static final int CV_CPU_MMX = 1;
    public static final int CV_CPU_MSA = 150;
    public static final int CV_CPU_NEON = 100;
    public static final int CV_CPU_NONE = 0;
    public static final int CV_CPU_POPCNT = 8;
    public static final int CV_CPU_SSE = 2;
    public static final int CV_CPU_SSE2 = 3;
    public static final int CV_CPU_SSE3 = 4;
    public static final int CV_CPU_SSE4_1 = 6;
    public static final int CV_CPU_SSE4_2 = 7;
    public static final int CV_CPU_SSSE3 = 5;
    public static final int CV_CPU_VSX = 200;
    public static final int CV_CPU_VSX3 = 201;
    public static final int CV_CXX_MOVE_SEMANTICS = 1;
    public static final int CV_CXX_STD_ARRAY = 1;
    public static final int CV_DEPTH_MAX = 8;
    public static final int CV_DIFF = 16;
    public static final int CV_DIFF_C = 17;
    public static final int CV_DIFF_L1 = 18;
    public static final int CV_DIFF_L2 = 20;
    public static final int CV_DXT_FORWARD = 0;
    public static final int CV_DXT_INVERSE = 1;
    public static final int CV_DXT_INVERSE_SCALE = 3;
    public static final int CV_DXT_INV_SCALE = 3;
    public static final int CV_DXT_MUL_CONJ = 8;
    public static final int CV_DXT_ROWS = 4;
    public static final int CV_DXT_SCALE = 2;
    public static final int CV_ErrModeLeaf = 0;
    public static final int CV_ErrModeParent = 1;
    public static final int CV_ErrModeSilent = 2;
    public static final int CV_FP16_TYPE = CV_FP16_TYPE();
    public static final int CV_FRONT = 1;
    public static final int CV_GEMM_A_T = 1;
    public static final int CV_GEMM_B_T = 2;
    public static final int CV_GEMM_C_T = 4;
    public static final int CV_GRAPH = 4096;
    public static final int CV_GRAPH_ALL_ITEMS = -1;
    public static final int CV_GRAPH_ANY_EDGE = 30;
    public static final int CV_GRAPH_BACKTRACKING = 64;
    public static final int CV_GRAPH_BACK_EDGE = 4;
    public static final int CV_GRAPH_CROSS_EDGE = 16;
    public static final int CV_GRAPH_FLAG_ORIENTED = 16384;
    public static final int CV_GRAPH_FORWARD_EDGE = 8;
    public static final int CV_GRAPH_FORWARD_EDGE_FLAG = 268435456;
    public static final int CV_GRAPH_ITEM_VISITED_FLAG = 1073741824;
    public static final int CV_GRAPH_NEW_TREE = 32;
    public static final int CV_GRAPH_OVER = -1;
    public static final int CV_GRAPH_SEARCH_TREE_NODE_FLAG = 536870912;
    public static final int CV_GRAPH_TREE_EDGE = 2;
    public static final int CV_GRAPH_VERTEX = 1;
    public static final int CV_GpuApiCallError = -217;
    public static final int CV_GpuNotSupported = -216;
    public static final int CV_HAL_BORDER_CONSTANT = 0;
    public static final int CV_HAL_BORDER_ISOLATED = 16;
    public static final int CV_HAL_BORDER_REFLECT = 2;
    public static final int CV_HAL_BORDER_REFLECT_101 = 4;
    public static final int CV_HAL_BORDER_REPLICATE = 1;
    public static final int CV_HAL_BORDER_TRANSPARENT = 5;
    public static final int CV_HAL_BORDER_WRAP = 3;
    public static final int CV_HAL_CMP_EQ = 0;
    public static final int CV_HAL_CMP_GE = 2;
    public static final int CV_HAL_CMP_GT = 1;
    public static final int CV_HAL_CMP_LE = 4;
    public static final int CV_HAL_CMP_LT = 3;
    public static final int CV_HAL_CMP_NE = 5;
    public static final int CV_HAL_DFT_COMPLEX_OUTPUT = 16;
    public static final int CV_HAL_DFT_INVERSE = 1;
    public static final int CV_HAL_DFT_IS_CONTINUOUS = 512;
    public static final int CV_HAL_DFT_IS_INPLACE = 1024;
    public static final int CV_HAL_DFT_REAL_OUTPUT = 32;
    public static final int CV_HAL_DFT_ROWS = 4;
    public static final int CV_HAL_DFT_SCALE = 2;
    public static final int CV_HAL_DFT_STAGE_COLS = 128;
    public static final int CV_HAL_DFT_TWO_STAGE = 64;
    public static final int CV_HAL_ERROR_NOT_IMPLEMENTED = 1;
    public static final int CV_HAL_ERROR_OK = 0;
    public static final int CV_HAL_ERROR_UNKNOWN = -1;
    public static final int CV_HAL_GEMM_1_T = 1;
    public static final int CV_HAL_GEMM_2_T = 2;
    public static final int CV_HAL_GEMM_3_T = 4;
    public static final int CV_HAL_SVD_FULL_UV = 8;
    public static final int CV_HAL_SVD_MODIFY_A = 4;
    public static final int CV_HAL_SVD_NO_UV = 1;
    public static final int CV_HAL_SVD_SHORT_UV = 2;
    public static final int CV_HARDWARE_MAX_FEATURE = 512;
    public static final int CV_HIST_ARRAY = 0;
    public static final int CV_HIST_MAGIC_VAL = 1111818240;
    public static final int CV_HIST_RANGES_FLAG = 2048;
    public static final int CV_HIST_SPARSE = 1;
    public static final int CV_HIST_TREE = 1;
    public static final int CV_HIST_UNIFORM = 1;
    public static final int CV_HIST_UNIFORM_FLAG = 1024;
    public static final int CV_HeaderIsNull = -9;
    public static final int CV_KMEANS_USE_INITIAL_LABELS = 1;
    public static final int CV_L1 = 2;
    public static final int CV_L2 = 4;
    public static final double CV_LOG2 = 0.6931471805599453d;
    public static final int CV_LU = 0;
    public static final int CV_MAGIC_MASK = -65536;
    public static final int CV_MAJOR_VERSION = 4;
    public static final int CV_MATND_MAGIC_VAL = 1111687168;
    public static final int CV_MAT_CN_MASK = 4088;
    public static final int CV_MAT_CONT_FLAG = 16384;
    public static final int CV_MAT_CONT_FLAG_SHIFT = 14;
    public static final int CV_MAT_DEPTH_MASK = 7;
    public static final int CV_MAT_MAGIC_VAL = 1111621632;
    public static final int CV_MAT_TYPE_MASK = 4095;
    public static final int CV_MAX_ARR = 10;
    public static final int CV_MAX_DIM = 32;
    public static final int CV_MINMAX = 32;
    public static final int CV_MINOR_VERSION = 4;
    public static final int CV_MaskIsTiled = -26;
    public static final int CV_NORMAL = 16;
    public static final int CV_NORM_MASK = 7;
    public static final int CV_NO_CN_CHECK = 2;
    public static final int CV_NO_DEPTH_CHECK = 1;
    public static final int CV_NO_SIZE_CHECK = 4;
    public static final int CV_ORIENTED_GRAPH = 20480;
    public static final int CV_OpenCLApiCallError = -220;
    public static final int CV_OpenCLDoubleNotSupported = -221;
    public static final int CV_OpenCLInitError = -222;
    public static final int CV_OpenCLNoAMDBlasFft = -223;
    public static final int CV_OpenGlApiCallError = -219;
    public static final int CV_OpenGlNotSupported = -218;
    public static final int CV_PCA_DATA_AS_COL = 1;
    public static final int CV_PCA_DATA_AS_ROW = 0;
    public static final int CV_PCA_USE_AVG = 2;
    public static final double CV_PI = 3.141592653589793d;
    public static final int CV_QR = 4;
    public static final int CV_RAND_NORMAL = 1;
    public static final int CV_RAND_UNI = 0;
    public static final int CV_REDUCE_AVG = 1;
    public static final int CV_REDUCE_MAX = 2;
    public static final int CV_REDUCE_MIN = 3;
    public static final int CV_REDUCE_SUM = 0;
    public static final int CV_RELATIVE = 8;
    public static final int CV_RELATIVE_C = 9;
    public static final int CV_RELATIVE_L1 = 10;
    public static final int CV_RELATIVE_L2 = 12;
    public static final long CV_RNG_COEFF = 4164903690L;
    public static final int CV_SEQ_CHAIN;
    public static final int CV_SEQ_CHAIN_CONTOUR;
    public static final int CV_SEQ_CONNECTED_COMP = 0;
    public static final int CV_SEQ_CONTOUR;
    public static final int CV_SEQ_ELTYPE_BITS = 12;
    public static final int CV_SEQ_ELTYPE_CODE;
    public static final int CV_SEQ_ELTYPE_CONNECTED_COMP = 0;
    public static final int CV_SEQ_ELTYPE_GENERIC = 0;
    public static final int CV_SEQ_ELTYPE_GRAPH_EDGE = 0;
    public static final int CV_SEQ_ELTYPE_GRAPH_VERTEX = 0;
    public static final int CV_SEQ_ELTYPE_INDEX;
    public static final int CV_SEQ_ELTYPE_MASK = 4095;
    public static final int CV_SEQ_ELTYPE_POINT;
    public static final int CV_SEQ_ELTYPE_POINT3D;
    public static final int CV_SEQ_ELTYPE_PPOINT = CV_SEQ_ELTYPE_PPOINT();
    public static final int CV_SEQ_ELTYPE_PTR = CV_SEQ_ELTYPE_PTR();
    public static final int CV_SEQ_ELTYPE_TRIAN_ATR = 0;
    public static final int CV_SEQ_FLAG_CLOSED = 16384;
    public static final int CV_SEQ_FLAG_CONVEX = 0;
    public static final int CV_SEQ_FLAG_HOLE = 32768;
    public static final int CV_SEQ_FLAG_SHIFT = 14;
    public static final int CV_SEQ_FLAG_SIMPLE = 0;
    public static final int CV_SEQ_INDEX;
    public static final int CV_SEQ_KIND_BIN_TREE = 8192;
    public static final int CV_SEQ_KIND_BITS = 2;
    public static final int CV_SEQ_KIND_CURVE = 4096;
    public static final int CV_SEQ_KIND_GENERIC = 0;
    public static final int CV_SEQ_KIND_GRAPH = 4096;
    public static final int CV_SEQ_KIND_MASK = 12288;
    public static final int CV_SEQ_KIND_SUBDIV2D = 8192;
    public static final int CV_SEQ_MAGIC_VAL = 1117323264;
    public static final int CV_SEQ_POINT3D_SET;
    public static final int CV_SEQ_POINT_SET;
    public static final int CV_SEQ_POLYGON;
    public static final int CV_SEQ_POLYGON_TREE = 8192;
    public static final int CV_SEQ_POLYLINE;
    public static final int CV_SEQ_SIMPLE_POLYGON;
    public static final int CV_SET_ELEM_FREE_FLAG = CV_SET_ELEM_FREE_FLAG();
    public static final int CV_SET_ELEM_IDX_MASK = 67108863;
    public static final int CV_SET_MAGIC_VAL = 1117257728;
    public static final int CV_SORT_ASCENDING = 0;
    public static final int CV_SORT_DESCENDING = 16;
    public static final int CV_SORT_EVERY_COLUMN = 1;
    public static final int CV_SORT_EVERY_ROW = 0;
    public static final int CV_SPARSE_MAT_MAGIC_VAL = 1111752704;
    public static final int CV_STATIC_ANALYSIS = 1;
    public static final int CV_STORAGE_MAGIC_VAL = 1116274688;
    public static final int CV_STRONG_ALIGNMENT = 1;
    public static final int CV_STRUCT_INITIALIZER = CV_STRUCT_INITIALIZER();
    public static final int CV_SUBMAT_FLAG = 32768;
    public static final int CV_SUBMAT_FLAG_SHIFT = 15;
    public static final int CV_SUBMINOR_VERSION = 0;
    public static final int CV_SVD = 1;
    public static final int CV_SVD_MODIFY_A = 1;
    public static final int CV_SVD_SYM = 2;
    public static final int CV_SVD_U_T = 2;
    public static final int CV_SVD_V_T = 4;
    public static final int CV_StsAssert = -215;
    public static final int CV_StsAutoTrace = -8;
    public static final int CV_StsBackTrace = -1;
    public static final int CV_StsBadArg = -5;
    public static final int CV_StsBadFlag = -206;
    public static final int CV_StsBadFunc = -6;
    public static final int CV_StsBadMask = -208;
    public static final int CV_StsBadMemBlock = -214;
    public static final int CV_StsBadPoint = -207;
    public static final int CV_StsBadSize = -201;
    public static final int CV_StsDivByZero = -202;
    public static final int CV_StsError = -2;
    public static final int CV_StsFilterOffsetErr = -31;
    public static final int CV_StsFilterStructContentErr = -29;
    public static final int CV_StsInplaceNotSupported = -203;
    public static final int CV_StsInternal = -3;
    public static final int CV_StsKernelStructContentErr = -30;
    public static final int CV_StsNoConv = -7;
    public static final int CV_StsNoMem = -4;
    public static final int CV_StsNotImplemented = -213;
    public static final int CV_StsNullPtr = -27;
    public static final int CV_StsObjectNotFound = -204;
    public static final int CV_StsOk = 0;
    public static final int CV_StsOutOfRange = -211;
    public static final int CV_StsParseError = -212;
    public static final int CV_StsUnmatchedFormats = -205;
    public static final int CV_StsUnmatchedSizes = -209;
    public static final int CV_StsUnsupportedFormat = -210;
    public static final int CV_StsVecLengthErr = -28;
    public static final int CV_TERMCRIT_EPS = 2;
    public static final int CV_TERMCRIT_ITER = 1;
    public static final int CV_TERMCRIT_NUMBER = 1;
    public static final String CV_TYPE_NAME_GRAPH = "opencv-graph";
    public static final String CV_TYPE_NAME_IMAGE = "opencv-image";
    public static final String CV_TYPE_NAME_MAT = "opencv-matrix";
    public static final String CV_TYPE_NAME_MATND = "opencv-nd-matrix";
    public static final String CV_TYPE_NAME_SEQ = "opencv-sequence";
    public static final String CV_TYPE_NAME_SEQ_TREE = "opencv-sequence-tree";
    public static final String CV_TYPE_NAME_SPARSE_MAT = "opencv-sparse-matrix";
    public static final String CV_VERSION = CV_VERSION();
    public static final int CV_VERSION_MAJOR = 4;
    public static final int CV_VERSION_MINOR = 4;
    public static final int CV_VERSION_REVISION = 0;
    public static final String CV_VERSION_STATUS = "";
    public static final CvSlice CV_WHOLE_ARR = cvSlice(0, CV_WHOLE_SEQ_END_INDEX);
    public static final CvSlice CV_WHOLE_SEQ = cvSlice(0, CV_WHOLE_SEQ_END_INDEX);
    public static final int CV_WHOLE_SEQ_END_INDEX = 1073741823;
    public static final int DCT_INVERSE = 1;
    public static final int DCT_ROWS = 4;
    public static final int DECOMP_CHOLESKY = 3;
    public static final int DECOMP_EIG = 2;
    public static final int DECOMP_LU = 0;
    public static final int DECOMP_NORMAL = 16;
    public static final int DECOMP_QR = 4;
    public static final int DECOMP_SVD = 1;
    public static final int DFT_COMPLEX_INPUT = 64;
    public static final int DFT_COMPLEX_OUTPUT = 16;
    public static final int DFT_INVERSE = 1;
    public static final int DFT_REAL_OUTPUT = 32;
    public static final int DFT_ROWS = 4;
    public static final int DFT_SCALE = 2;
    public static final int DYNAMIC_PARALLELISM = 35;
    public static final int FEATURE_SET_COMPUTE_10 = 10;
    public static final int FEATURE_SET_COMPUTE_11 = 11;
    public static final int FEATURE_SET_COMPUTE_12 = 12;
    public static final int FEATURE_SET_COMPUTE_13 = 13;
    public static final int FEATURE_SET_COMPUTE_20 = 20;
    public static final int FEATURE_SET_COMPUTE_21 = 21;
    public static final int FEATURE_SET_COMPUTE_30 = 30;
    public static final int FEATURE_SET_COMPUTE_32 = 32;
    public static final int FEATURE_SET_COMPUTE_35 = 35;
    public static final int FEATURE_SET_COMPUTE_50 = 50;
    public static final int FLAGS_EXPAND_SAME_NAMES = 2;
    public static final int FLAGS_MAPPING = 1;
    public static final int FLAGS_NONE = 0;
    public static final int FLOAT = 7;
    public static final int GEMM_1_T = 1;
    public static final int GEMM_2_T = 2;
    public static final int GEMM_3_T = 4;
    public static final int GLOBAL_ATOMICS = 11;
    public static final int GpuApiCallError = -217;
    public static final int GpuNotSupported = -216;
    public static final int HeaderIsNull = -9;
    public static final int IMPL_IPP = 1;
    public static final int IMPL_OPENCL = 2;
    public static final int IMPL_PLAIN = 0;
    public static final int INT = 0;
    public static final int IPL_ALIGN_16BYTES = 16;
    public static final int IPL_ALIGN_32BYTES = 32;
    public static final int IPL_ALIGN_4BYTES = 4;
    public static final int IPL_ALIGN_8BYTES = 8;
    public static final int IPL_ALIGN_DWORD = 4;
    public static final int IPL_ALIGN_QWORD = 8;
    public static final int IPL_BORDER_CONSTANT = 0;
    public static final int IPL_BORDER_REFLECT = 2;
    public static final int IPL_BORDER_REFLECT_101 = 4;
    public static final int IPL_BORDER_REPLICATE = 1;
    public static final int IPL_BORDER_TRANSPARENT = 5;
    public static final int IPL_BORDER_WRAP = 3;
    public static final int IPL_DATA_ORDER_PIXEL = 0;
    public static final int IPL_DATA_ORDER_PLANE = 1;
    public static final int IPL_DEPTH_16S = -2147483632;
    public static final int IPL_DEPTH_16U = 16;
    public static final int IPL_DEPTH_1U = 1;
    public static final int IPL_DEPTH_32F = 32;
    public static final int IPL_DEPTH_32S = -2147483616;
    public static final int IPL_DEPTH_64F = 64;
    public static final int IPL_DEPTH_8S = -2147483640;
    public static final int IPL_DEPTH_8U = 8;
    public static final int IPL_DEPTH_SIGN = Integer.MIN_VALUE;
    public static final int IPL_IMAGE_DATA = 2;
    public static final int IPL_IMAGE_HEADER = 1;
    public static final int IPL_IMAGE_MAGIC_VAL = IPL_IMAGE_MAGIC_VAL();
    public static final int IPL_IMAGE_ROI = 4;
    public static final int IPL_ORIGIN_BL = 1;
    public static final int IPL_ORIGIN_TL = 0;
    public static final int KMEANS_PP_CENTERS = 2;
    public static final int KMEANS_RANDOM_CENTERS = 0;
    public static final int KMEANS_USE_INITIAL_LABELS = 1;
    public static final int MAT = 4;
    public static final int MAT_VECTOR = 5;
    public static final int MaskIsTiled = -26;
    public static final int NATIVE_DOUBLE = 13;
    public static final int NORM_HAMMING = 6;
    public static final int NORM_HAMMING2 = 7;
    public static final int NORM_INF = 1;
    public static final int NORM_L1 = 2;
    public static final int NORM_L2 = 4;
    public static final int NORM_L2SQR = 5;
    public static final int NORM_MINMAX = 32;
    public static final int NORM_RELATIVE = 8;
    public static final int NORM_TYPE_MASK = 7;
    public static final int OCL_VECTOR_DEFAULT = 0;
    public static final int OCL_VECTOR_MAX = 1;
    public static final int OCL_VECTOR_OWN = 0;
    public static final int OPENCV_ABI_COMPATIBILITY = 400;
    public static final int OPENCV_USE_FASTMATH_BUILTINS = 1;
    public static final int OpenCLApiCallError = -220;
    public static final int OpenCLDoubleNotSupported = -221;
    public static final int OpenCLInitError = -222;
    public static final int OpenCLNoAMDBlasFft = -223;
    public static final int OpenGlApiCallError = -219;
    public static final int OpenGlNotSupported = -218;
    public static final int REAL = 2;
    public static final int REDUCE_AVG = 1;
    public static final int REDUCE_MAX = 2;
    public static final int REDUCE_MIN = 3;
    public static final int REDUCE_SUM = 0;
    public static final int ROTATE_180 = 1;
    public static final int ROTATE_90_CLOCKWISE = 0;
    public static final int ROTATE_90_COUNTERCLOCKWISE = 2;
    public static final int SCALAR = 12;
    public static final int SHARED_ATOMICS = 12;
    public static final int SOLVELP_MULTI = 1;
    public static final int SOLVELP_SINGLE = 0;
    public static final int SOLVELP_UNBOUNDED = -2;
    public static final int SOLVELP_UNFEASIBLE = -1;
    public static final int SORT_ASCENDING = 0;
    public static final int SORT_DESCENDING = 16;
    public static final int SORT_EVERY_COLUMN = 1;
    public static final int SORT_EVERY_ROW = 0;
    public static final int STRING = 3;
    public static final int StsAssert = -215;
    public static final int StsAutoTrace = -8;
    public static final int StsBackTrace = -1;
    public static final int StsBadArg = -5;
    public static final int StsBadFlag = -206;
    public static final int StsBadFunc = -6;
    public static final int StsBadMask = -208;
    public static final int StsBadMemBlock = -214;
    public static final int StsBadPoint = -207;
    public static final int StsBadSize = -201;
    public static final int StsDivByZero = -202;
    public static final int StsError = -2;
    public static final int StsFilterOffsetErr = -31;
    public static final int StsFilterStructContentErr = -29;
    public static final int StsInplaceNotSupported = -203;
    public static final int StsInternal = -3;
    public static final int StsKernelStructContentErr = -30;
    public static final int StsNoConv = -7;
    public static final int StsNoMem = -4;
    public static final int StsNotImplemented = -213;
    public static final int StsNullPtr = -27;
    public static final int StsObjectNotFound = -204;
    public static final int StsOk = 0;
    public static final int StsOutOfRange = -211;
    public static final int StsParseError = -212;
    public static final int StsUnmatchedFormats = -205;
    public static final int StsUnmatchedSizes = -209;
    public static final int StsUnsupportedFormat = -210;
    public static final int StsVecLengthErr = -28;
    public static final int TYPE_FUN = 3;
    public static final int TYPE_GENERAL = 0;
    public static final int TYPE_MARKER = 1;
    public static final int TYPE_WRAPPER = 2;
    public static final int UCHAR = 11;
    public static final int UINT64 = 9;
    public static final int UNSIGNED_INT = 8;
    public static final int USAGE_ALLOCATE_DEVICE_MEMORY = 2;
    public static final int USAGE_ALLOCATE_HOST_MEMORY = 1;
    public static final int USAGE_ALLOCATE_SHARED_MEMORY = 4;
    public static final int USAGE_DEFAULT = 0;
    public static final int WARP_SHUFFLE_FUNCTIONS = 30;
    public static final int __UMAT_USAGE_FLAGS_32BIT = Integer.MAX_VALUE;
    public static final String cvFuncName = "";

    @MemberGetter
    public static native int CV_16FC1();

    @MemberGetter
    public static native int CV_16FC2();

    @MemberGetter
    public static native int CV_16FC3();

    @MemberGetter
    public static native int CV_16FC4();

    public static native int CV_16SC(int i);

    public static native int CV_16UC(int i);

    public static native int CV_32FC(int i);

    public static native int CV_32SC(int i);

    public static native int CV_64FC(int i);

    public static native int CV_8SC(int i);

    public static native int CV_8UC(int i);

    @MemberGetter
    public static native int CV_FP16_TYPE();

    public static native int CV_IS_CONT_MAT(int i);

    public static native int CV_IS_MAT_CONT(int i);

    public static native int CV_MAKETYPE(int i, int i2);

    public static native int CV_MAKE_TYPE(int i, int i2);

    public static native int CV_MAT_CN(int i);

    public static native int CV_MAT_DEPTH(int i);

    public static native int CV_MAT_TYPE(int i);

    @MemberGetter
    public static native int CV_SEQ_ELTYPE_PPOINT();

    @MemberGetter
    public static native int CV_SEQ_ELTYPE_PTR();

    @MemberGetter
    public static native int CV_SET_ELEM_FREE_FLAG();

    @MemberGetter
    public static native int CV_STRUCT_INITIALIZER();

    @MemberGetter
    public static native String CV_VERSION();

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky(DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, int i, DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky(FloatBuffer floatBuffer, @Cast({"size_t"}) long j, int i, FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky(DoublePointer doublePointer, @Cast({"size_t"}) long j, int i, DoublePointer doublePointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky(FloatPointer floatPointer, @Cast({"size_t"}) long j, int i, FloatPointer floatPointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky(double[] dArr, @Cast({"size_t"}) long j, int i, double[] dArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky(float[] fArr, @Cast({"size_t"}) long j, int i, float[] fArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky32f(FloatBuffer floatBuffer, @Cast({"size_t"}) long j, int i, FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky32f(FloatPointer floatPointer, @Cast({"size_t"}) long j, int i, FloatPointer floatPointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky32f(float[] fArr, @Cast({"size_t"}) long j, int i, float[] fArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky64f(DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, int i, DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky64f(DoublePointer doublePointer, @Cast({"size_t"}) long j, int i, DoublePointer doublePointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    @Cast({"bool"})
    public static native boolean Cholesky64f(double[] dArr, @Cast({"size_t"}) long j, int i, double[] dArr2, @Cast({"size_t"}) long j2, int i2);

    @MemberGetter
    public static native int IPL_IMAGE_MAGIC_VAL();

    @Namespace("cv::hal")
    public static native int LU(DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, int i, DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU(FloatBuffer floatBuffer, @Cast({"size_t"}) long j, int i, FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU(DoublePointer doublePointer, @Cast({"size_t"}) long j, int i, DoublePointer doublePointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU(FloatPointer floatPointer, @Cast({"size_t"}) long j, int i, FloatPointer floatPointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU(double[] dArr, @Cast({"size_t"}) long j, int i, double[] dArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU(float[] fArr, @Cast({"size_t"}) long j, int i, float[] fArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU32f(FloatBuffer floatBuffer, @Cast({"size_t"}) long j, int i, FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU32f(FloatPointer floatPointer, @Cast({"size_t"}) long j, int i, FloatPointer floatPointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU32f(float[] fArr, @Cast({"size_t"}) long j, int i, float[] fArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU64f(DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, int i, DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU64f(DoublePointer doublePointer, @Cast({"size_t"}) long j, int i, DoublePointer doublePointer2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv::hal")
    public static native int LU64f(double[] dArr, @Cast({"size_t"}) long j, int i, double[] dArr2, @Cast({"size_t"}) long j2, int i2);

    @Namespace("cv")
    public static native void LUT(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void LUT(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void LUT(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native double Mahalanobis(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native double Mahalanobis(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native double Mahalanobis(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void PCABackProject(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void PCABackProject(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void PCABackProject(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void PCACompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void PCACompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d);

    @Namespace("cv")
    public static native void PCACompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void PCACompute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void PCACompute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d);

    @Namespace("cv")
    public static native void PCACompute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void PCACompute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void PCACompute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d);

    @Namespace("cv")
    public static native void PCACompute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, double d);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, double d);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, double d);

    @Namespace("cv")
    @Name({"PCACompute"})
    public static native void PCACompute2(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    @Namespace("cv")
    public static native void PCAProject(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void PCAProject(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void PCAProject(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native double PSNR(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native double PSNR(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d);

    @Namespace("cv")
    public static native double PSNR(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native double PSNR(@ByVal Mat mat, @ByVal Mat mat2, double d);

    @Namespace("cv")
    public static native double PSNR(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native double PSNR(@ByVal UMat uMat, @ByVal UMat uMat2, double d);

    @Namespace("cv::hal")
    public static native int QR32f(FloatBuffer floatBuffer, @Cast({"size_t"}) long j, int i, int i2, int i3, FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3);

    @Namespace("cv::hal")
    public static native int QR32f(FloatPointer floatPointer, @Cast({"size_t"}) long j, int i, int i2, int i3, FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3);

    @Namespace("cv::hal")
    public static native int QR32f(float[] fArr, @Cast({"size_t"}) long j, int i, int i2, int i3, float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3);

    @Namespace("cv::hal")
    public static native int QR64f(DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, int i, int i2, int i3, DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3);

    @Namespace("cv::hal")
    public static native int QR64f(DoublePointer doublePointer, @Cast({"size_t"}) long j, int i, int i2, int i3, DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3);

    @Namespace("cv::hal")
    public static native int QR64f(double[] dArr, @Cast({"size_t"}) long j, int i, int i2, int i3, double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3);

    @Namespace("cv")
    public static native void SVBackSubst(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv")
    public static native void SVBackSubst(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    public static native void SVBackSubst(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv::hal")
    public static native void SVD32f(FloatBuffer floatBuffer, @Cast({"size_t"}) long j, FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer4, @Cast({"size_t"}) long j3, int i, int i2, int i3);

    @Namespace("cv::hal")
    public static native void SVD32f(FloatPointer floatPointer, @Cast({"size_t"}) long j, FloatPointer floatPointer2, FloatPointer floatPointer3, @Cast({"size_t"}) long j2, FloatPointer floatPointer4, @Cast({"size_t"}) long j3, int i, int i2, int i3);

    @Namespace("cv::hal")
    public static native void SVD32f(float[] fArr, @Cast({"size_t"}) long j, float[] fArr2, float[] fArr3, @Cast({"size_t"}) long j2, float[] fArr4, @Cast({"size_t"}) long j3, int i, int i2, int i3);

    @Namespace("cv::hal")
    public static native void SVD64f(DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, DoubleBuffer doubleBuffer2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer4, @Cast({"size_t"}) long j3, int i, int i2, int i3);

    @Namespace("cv::hal")
    public static native void SVD64f(DoublePointer doublePointer, @Cast({"size_t"}) long j, DoublePointer doublePointer2, DoublePointer doublePointer3, @Cast({"size_t"}) long j2, DoublePointer doublePointer4, @Cast({"size_t"}) long j3, int i, int i2, int i3);

    @Namespace("cv::hal")
    public static native void SVD64f(double[] dArr, @Cast({"size_t"}) long j, double[] dArr2, double[] dArr3, @Cast({"size_t"}) long j2, double[] dArr4, @Cast({"size_t"}) long j3, int i, int i2, int i3);

    @Namespace("cv")
    public static native void SVDecomp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void SVDecomp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void SVDecomp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void SVDecomp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    @Namespace("cv")
    public static native void SVDecomp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void SVDecomp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    @Namespace("cv")
    @Cast({"uchar"})
    public static native byte abs(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"unsigned"})
    public static native int abs(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @ByVal
    public static native MatExpr abs(@ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    public static native MatExpr abs(@ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @Cast({"ushort"})
    public static native short abs(@Cast({"ushort"}) short s);

    @Namespace("cv")
    public static native void absdiff(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void absdiff(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void absdiff(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::hal")
    public static native void absdiff16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void absdiff8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const Mat mat, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const MatExpr matExpr, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const MatExpr matExpr, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const Scalar scalar, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native MatExpr add(@ByRef @Const Scalar scalar, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native Range add(int i, @ByRef @Const Range range);

    @Namespace("cv")
    @ByVal
    @Name({"operator +"})
    public static native Range add(@ByRef @Const Range range, int i);

    @Namespace("cv")
    public static native void add(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void add(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void add(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void add(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, int i);

    @Namespace("cv")
    public static native void add(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void add(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, int i);

    @Namespace("cv::hal")
    public static native void add16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void add8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    @ByRef
    @Name({"operator +="})
    public static native Mat addPut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByRef
    @Name({"operator +="})
    public static native Mat addPut(@ByRef Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv::hal")
    public static native void addRNGBias32f(FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void addRNGBias32f(FloatPointer floatPointer, @Const FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native void addRNGBias32f(float[] fArr, @Const float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void addRNGBias64f(DoubleBuffer doubleBuffer, @Const DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void addRNGBias64f(DoublePointer doublePointer, @Const DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void addRNGBias64f(double[] dArr, @Const double[] dArr2, int i);

    @Namespace("cv::samples")
    public static native void addSamplesDataSearchPath(@opencv_core.Str String str);

    @Namespace("cv::samples")
    public static native void addSamplesDataSearchPath(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::samples")
    public static native void addSamplesDataSearchSubDirectory(@opencv_core.Str String str);

    @Namespace("cv::samples")
    public static native void addSamplesDataSearchSubDirectory(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv")
    public static native void addWeighted(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, double d2, double d3, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void addWeighted(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, double d2, double d3, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void addWeighted(@ByVal Mat mat, double d, @ByVal Mat mat2, double d2, double d3, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void addWeighted(@ByVal Mat mat, double d, @ByVal Mat mat2, double d2, double d3, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void addWeighted(@ByVal UMat uMat, double d, @ByVal UMat uMat2, double d2, double d3, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void addWeighted(@ByVal UMat uMat, double d, @ByVal UMat uMat2, double d2, double d3, @ByVal UMat uMat3, int i);

    @Namespace("cv::hal")
    public static native void addWeighted16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void addWeighted8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    @Cast({"size_t"})
    public static native long alignSize(@Cast({"size_t"}) long j, int i);

    @Namespace("cv")
    @Cast({"cv::AccessFlag"})
    @Name({"operator &"})
    public static native int and(@Cast({"const cv::AccessFlag"}) int i, @Cast({"const cv::AccessFlag"}) int i2);

    @Namespace("cv")
    @ByVal
    @Name({"operator &"})
    public static native MatExpr and(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator &"})
    public static native MatExpr and(@ByRef @Const Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator &"})
    public static native MatExpr and(@ByRef @Const Scalar scalar, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator &"})
    public static native Range and(@ByRef @Const Range range, @ByRef @Const Range range2);

    @Namespace("cv::hal")
    public static native void and8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void and8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void and8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator &="})
    @Namespace("cv")
    public static native IntBuffer andPut(@ByRef @Cast({"cv::AccessFlag*"}) IntBuffer intBuffer, @Cast({"const cv::AccessFlag"}) int i);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator &="})
    @Namespace("cv")
    public static native IntPointer andPut(@ByRef @Cast({"cv::AccessFlag*"}) IntPointer intPointer, @Cast({"const cv::AccessFlag"}) int i);

    @Namespace("cv")
    @ByRef
    @Name({"operator &="})
    public static native Mat andPut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByRef
    @Name({"operator &="})
    public static native Mat andPut(@ByRef Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByRef
    @Name({"operator &="})
    public static native Range andPut(@ByRef Range range, @ByRef @Const Range range2);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator &="})
    @Namespace("cv")
    public static native int[] andPut(@ByRef @Cast({"cv::AccessFlag*"}) int[] iArr, @Cast({"const cv::AccessFlag"}) int i);

    @Namespace("cv::ocl")
    public static native void attachContext(@opencv_core.Str String str, Pointer pointer, Pointer pointer2, Pointer pointer3);

    @Namespace("cv::ocl")
    public static native void attachContext(@opencv_core.Str BytePointer bytePointer, Pointer pointer, Pointer pointer2, Pointer pointer3);

    @Namespace("cv")
    public static native void batchDistance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void batchDistance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByVal GpuMat gpuMat4, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, int i4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void batchDistance(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void batchDistance(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByVal Mat mat4, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, int i4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void batchDistance(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void batchDistance(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByVal UMat uMat4, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, int i4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void bitwise_and(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void bitwise_and(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void bitwise_and(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void bitwise_and(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void bitwise_and(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void bitwise_and(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void bitwise_not(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void bitwise_not(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native void bitwise_not(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void bitwise_not(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native void bitwise_not(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void bitwise_not(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    public static native void bitwise_or(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void bitwise_or(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void bitwise_or(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void bitwise_or(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void bitwise_or(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void bitwise_or(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void bitwise_xor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void bitwise_xor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void bitwise_xor(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void bitwise_xor(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void bitwise_xor(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void bitwise_xor(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native int borderInterpolate(int i, int i2, int i3);

    @Namespace("cv::ocl")
    public static native void buildOptionsAddMatrixDescription(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native void buildOptionsAddMatrixDescription(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native void buildOptionsAddMatrixDescription(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal UMat uMat);

    @Namespace("cv::ocl")
    public static native void buildOptionsAddMatrixDescription(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native void buildOptionsAddMatrixDescription(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native void buildOptionsAddMatrixDescription(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void calcCovarMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void calcCovarMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2);

    @Namespace("cv")
    public static native void calcCovarMatrix(@Const Mat mat, int i, @ByRef Mat mat2, @ByRef Mat mat3, int i2);

    @Namespace("cv")
    public static native void calcCovarMatrix(@Const Mat mat, int i, @ByRef Mat mat2, @ByRef Mat mat3, int i2, int i3);

    @Namespace("cv")
    public static native void calcCovarMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void calcCovarMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2);

    @Namespace("cv")
    public static native void calcCovarMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    public static native void calcCovarMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2);

    @Namespace("cv")
    public static native void cartToPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void cartToPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void cartToPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void cartToPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void cartToPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void cartToPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @Cast({"bool"}) boolean z);

    @Namespace("cv::details")
    @Cast({"char"})
    public static native byte char_tolower(@Cast({"char"}) byte b);

    @Namespace("cv::details")
    @Cast({"char"})
    public static native byte char_toupper(@Cast({"char"}) byte b);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkHardwareSupport(int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntBuffer intBuffer, @ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntBuffer intBuffer, @ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntBuffer intBuffer, @ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntBuffer intBuffer, @ByVal UMat uMat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntBuffer intBuffer, @ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntPointer intPointer, @ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntPointer intPointer, @ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntPointer intPointer, @ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntPointer intPointer, @ByVal UMat uMat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const IntPointer intPointer, @ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const int[] iArr, @ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const int[] iArr, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const int[] iArr, @ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const int[] iArr, @ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const int[] iArr, @ByVal UMat uMat);

    @Namespace("cv::ocl")
    public static native int checkOptimalVectorWidth(@Const int[] iArr, @ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkRange(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkRange(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z, Point point, double d, double d2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkRange(@ByVal Mat mat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkRange(@ByVal Mat mat, @Cast({"bool"}) boolean z, Point point, double d, double d2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkRange(@ByVal UMat uMat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean checkRange(@ByVal UMat uMat, @Cast({"bool"}) boolean z, Point point, double d, double d2);

    @Namespace("cv")
    public static native void clearSeq(CvSeq cvSeq);

    @Namespace("cv::hal")
    public static native void cmp16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void cmp8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    public static native void compare(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void compare(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void compare(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    public static native void completeSymm(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void completeSymm(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void completeSymm(@ByVal Mat mat);

    @Namespace("cv")
    public static native void completeSymm(@ByVal Mat mat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void completeSymm(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void completeSymm(@ByVal UMat uMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void convertFp16(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void convertFp16(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void convertFp16(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ocl")
    public static native void convertFromBuffer(Pointer pointer, @Cast({"size_t"}) long j, int i, int i2, int i3, @ByRef UMat uMat);

    @Namespace("cv::ocl")
    public static native void convertFromImage(Pointer pointer, @ByRef UMat uMat);

    @Namespace("cv")
    public static native void convertScaleAbs(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void convertScaleAbs(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2);

    @Namespace("cv")
    public static native void convertScaleAbs(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void convertScaleAbs(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2);

    @Namespace("cv")
    public static native void convertScaleAbs(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void convertScaleAbs(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2);

    @Namespace("cv::ocl")
    public static native String convertTypeStr(int i, int i2, int i3, @Cast({"char*"}) ByteBuffer byteBuffer);

    @Namespace("cv::ocl")
    @Cast({"const char*"})
    public static native BytePointer convertTypeStr(int i, int i2, int i3, @Cast({"char*"}) BytePointer bytePointer);

    @Namespace("cv::ocl")
    @Cast({"const char*"})
    public static native BytePointer convertTypeStr(int i, int i2, int i3, @Cast({"char*"}) byte[] bArr);

    @Namespace("cv")
    public static native void copyMakeBorder(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void copyMakeBorder(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, int i4, int i5, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void copyMakeBorder(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void copyMakeBorder(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, int i4, int i5, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void copyMakeBorder(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, int i4, int i5);

    @Namespace("cv")
    public static native void copyMakeBorder(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, int i4, int i5, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void copyTo(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void copyTo(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void copyTo(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native int countNonZero(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native int countNonZero(@ByVal Mat mat);

    @Namespace("cv")
    public static native int countNonZero(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    public static native void createContinuous(int i, int i2, int i3, @ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    public static native void createContinuous(int i, int i2, int i3, @ByVal Mat mat);

    @Namespace("cv::cuda")
    public static native void createContinuous(int i, int i2, int i3, @ByVal UMat uMat);

    @Namespace("cv")
    public static native float cubeRoot(float f);

    public static native void cvAbsDiff(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvAbsDiffS(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvScalar cvScalar);

    public static native void cvAdd(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvAdd(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvAddS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2);

    public static native void cvAddS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2, @Const CvArr cvArr3);

    public static native void cvAddWeighted(@Const CvArr cvArr, double d, @Const CvArr cvArr2, double d2, double d3, CvArr cvArr3);

    public static native Pointer cvAlloc(@Cast({"size_t"}) long j);

    public static native void cvAnd(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvAnd(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvAndS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2);

    public static native void cvAndS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2, @Const CvArr cvArr3);

    @ByVal
    public static native CvScalar cvAvg(@Const CvArr cvArr);

    @ByVal
    public static native CvScalar cvAvg(@Const CvArr cvArr, @Const CvArr cvArr2);

    public static native void cvAvgSdv(@Const CvArr cvArr, CvScalar cvScalar, CvScalar cvScalar2);

    public static native void cvAvgSdv(@Const CvArr cvArr, CvScalar cvScalar, CvScalar cvScalar2, @Const CvArr cvArr2);

    public static native void cvBackProjectPCA(@Const CvArr cvArr, @Const CvArr cvArr2, @Const CvArr cvArr3, CvArr cvArr4);

    @ByVal
    public static native CvBox2D cvBox2D();

    @ByVal
    public static native CvBox2D cvBox2D(@Cast({"CvPoint2D32f*"}) @ByVal(nullValue = "CvPoint2D32f()") FloatBuffer floatBuffer, @ByVal(nullValue = "CvSize2D32f()") CvSize2D32f cvSize2D32f, float f);

    @ByVal
    public static native CvBox2D cvBox2D(@ByVal(nullValue = "CvPoint2D32f()") CvPoint2D32f cvPoint2D32f, @ByVal(nullValue = "CvSize2D32f()") CvSize2D32f cvSize2D32f, float f);

    @ByVal
    public static native CvBox2D cvBox2D(@ByRef @Const RotatedRect rotatedRect);

    @ByVal
    public static native CvBox2D cvBox2D(@Cast({"CvPoint2D32f*"}) @ByVal(nullValue = "CvPoint2D32f()") float[] fArr, @ByVal(nullValue = "CvSize2D32f()") CvSize2D32f cvSize2D32f, float f);

    public static native void cvCalcCovarMatrix(@Cast({"const CvArr**"}) PointerPointer pointerPointer, int i, CvArr cvArr, CvArr cvArr2, int i2);

    public static native void cvCalcCovarMatrix(@ByPtrPtr @Const CvArr cvArr, int i, CvArr cvArr2, CvArr cvArr3, int i2);

    public static native void cvCalcPCA(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvArr cvArr4, int i);

    public static native void cvCartToPolar(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvCartToPolar(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, CvArr cvArr4, int i);

    public static native float cvCbrt(float f);

    public static native int cvCeil(double d);

    public static native int cvCeil(float f);

    public static native int cvCeil(int i);

    public static native void cvChangeSeqBlock(Pointer pointer, int i);

    public static native int cvCheckArr(@Const CvArr cvArr);

    public static native int cvCheckArr(@Const CvArr cvArr, int i, double d, double d2);

    public static native int cvCheckArray(CvArr cvArr, int i, double d, double d2);

    public static native int cvCheckHardwareSupport(int i);

    @ByVal
    public static native CvTermCriteria cvCheckTermCriteria(@ByVal CvTermCriteria cvTermCriteria, double d, int i);

    public static native void cvClearGraph(CvGraph cvGraph);

    public static native void cvClearMemStorage(CvMemStorage cvMemStorage);

    public static native void cvClearND(CvArr cvArr, @Const IntBuffer intBuffer);

    public static native void cvClearND(CvArr cvArr, @Const IntPointer intPointer);

    public static native void cvClearND(CvArr cvArr, @Const int[] iArr);

    public static native void cvClearSeq(CvSeq cvSeq);

    public static native void cvClearSet(CvSet cvSet);

    public static native Pointer cvClone(@Const Pointer pointer);

    public static native CvGraph cvCloneGraph(@Const CvGraph cvGraph, CvMemStorage cvMemStorage);

    public static native IplImage cvCloneImage(@Const IplImage iplImage);

    public static native CvMat cvCloneMat(@Const CvMat cvMat);

    public static native CvMatND cvCloneMatND(@Const CvMatND cvMatND);

    public static native CvSeq cvCloneSeq(@Const CvSeq cvSeq);

    public static native CvSeq cvCloneSeq(@Const CvSeq cvSeq, CvMemStorage cvMemStorage);

    public static native CvSparseMat cvCloneSparseMat(@Const CvSparseMat cvSparseMat);

    public static native void cvCmp(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, int i);

    public static native void cvCmpS(@Const CvArr cvArr, double d, CvArr cvArr2, int i);

    public static native void cvCompleteSymm(CvMat cvMat);

    public static native void cvCompleteSymm(CvMat cvMat, int i);

    public static native void cvConvert(CvArr cvArr, CvArr cvArr2);

    public static native void cvConvertScale(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvConvertScale(@Const CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native void cvConvertScaleAbs(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvConvertScaleAbs(@Const CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native void cvCopy(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvCopy(@Const CvArr cvArr, CvArr cvArr2, @Const CvArr cvArr3);

    public static native int cvCountNonZero(@Const CvArr cvArr);

    public static native CvMemStorage cvCreateChildMemStorage(CvMemStorage cvMemStorage);

    public static native void cvCreateData(CvArr cvArr);

    public static native CvGraph cvCreateGraph(int i, int i2, int i3, int i4, CvMemStorage cvMemStorage);

    public static native CvGraphScanner cvCreateGraphScanner(CvGraph cvGraph);

    public static native CvGraphScanner cvCreateGraphScanner(CvGraph cvGraph, CvGraphVtx cvGraphVtx, int i);

    public static native IplImage cvCreateImage(@ByVal CvSize cvSize, int i, int i2);

    public static native IplImage cvCreateImageHeader(@ByVal CvSize cvSize, int i, int i2);

    public static native CvMat cvCreateMat(int i, int i2, int i3);

    public static native CvMat cvCreateMatHeader(int i, int i2, int i3);

    public static native CvMatND cvCreateMatND(int i, @Const IntBuffer intBuffer, int i2);

    public static native CvMatND cvCreateMatND(int i, @Const IntPointer intPointer, int i2);

    public static native CvMatND cvCreateMatND(int i, @Const int[] iArr, int i2);

    public static native CvMatND cvCreateMatNDHeader(int i, @Const IntBuffer intBuffer, int i2);

    public static native CvMatND cvCreateMatNDHeader(int i, @Const IntPointer intPointer, int i2);

    public static native CvMatND cvCreateMatNDHeader(int i, @Const int[] iArr, int i2);

    public static native CvMemStorage cvCreateMemStorage();

    public static native CvMemStorage cvCreateMemStorage(int i);

    public static native CvSeq cvCreateSeq(int i, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, CvMemStorage cvMemStorage);

    public static native void cvCreateSeqBlock(CvSeqWriter cvSeqWriter);

    public static native CvSet cvCreateSet(int i, int i2, int i3, CvMemStorage cvMemStorage);

    public static native CvSparseMat cvCreateSparseMat(int i, @Const IntBuffer intBuffer, int i2);

    public static native CvSparseMat cvCreateSparseMat(int i, @Const IntPointer intPointer, int i2);

    public static native CvSparseMat cvCreateSparseMat(int i, @Const int[] iArr, int i2);

    public static native CvSparseMat cvCreateSparseMat(@ByRef @Const SparseMat sparseMat);

    public static native void cvCrossProduct(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvCvtScale(CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native void cvCvtScaleAbs(CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native Pointer cvCvtSeqToArray(@Const CvSeq cvSeq, Pointer pointer);

    public static native Pointer cvCvtSeqToArray(@Const CvSeq cvSeq, Pointer pointer, @ByVal(nullValue = "CvSlice(CV_WHOLE_SEQ)") CvSlice cvSlice);

    public static native void cvDCT(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvDFT(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvDFT(@Const CvArr cvArr, CvArr cvArr2, int i, int i2);

    public static native void cvDecRefData(CvArr cvArr);

    public static native double cvDet(@Const CvArr cvArr);

    public static native void cvDiv(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvDiv(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, double d);

    public static native double cvDotProduct(@Const CvArr cvArr, @Const CvArr cvArr2);

    public static native void cvEigenVV(CvArr cvArr, CvArr cvArr2, CvArr cvArr3);

    public static native void cvEigenVV(CvArr cvArr, CvArr cvArr2, CvArr cvArr3, double d, int i, int i2);

    public static native CvSeq cvEndWriteSeq(CvSeqWriter cvSeqWriter);

    public static native void cvError(int i, String str, String str2, String str3, int i2);

    public static native void cvError(int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i2);

    public static native int cvErrorFromIppStatus(int i);

    @Cast({"const char*"})
    public static native BytePointer cvErrorStr(int i);

    public static native void cvExp(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvFFT(CvArr cvArr, CvArr cvArr2, int i, int i2);

    public static native float cvFastArctan(float f, float f2);

    public static native CvGraphEdge cvFindGraphEdge(@Const CvGraph cvGraph, int i, int i2);

    public static native CvGraphEdge cvFindGraphEdgeByPtr(@Const CvGraph cvGraph, @Const CvGraphVtx cvGraphVtx, @Const CvGraphVtx cvGraphVtx2);

    public static native void cvFlip(@Const CvArr cvArr);

    public static native void cvFlip(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native int cvFloor(double d);

    public static native int cvFloor(float f);

    public static native int cvFloor(int i);

    public static native void cvFlushSeqWriter(CvSeqWriter cvSeqWriter);

    public static native void cvFree_(Pointer pointer);

    public static native void cvGEMM(@Const CvArr cvArr, @Const CvArr cvArr2, double d, @Const CvArr cvArr3, double d2, CvArr cvArr4);

    public static native void cvGEMM(@Const CvArr cvArr, @Const CvArr cvArr2, double d, @Const CvArr cvArr3, double d2, CvArr cvArr4, int i);

    @ByVal
    public static native CvScalar cvGet1D(@Const CvArr cvArr, int i);

    @ByVal
    public static native CvScalar cvGet2D(@Const CvArr cvArr, int i, int i2);

    @ByVal
    public static native CvScalar cvGet3D(@Const CvArr cvArr, int i, int i2, int i3);

    public static native CvMat cvGetCol(@Const CvArr cvArr, CvMat cvMat, int i);

    public static native CvMat cvGetCols(@Const CvArr cvArr, CvMat cvMat, int i, int i2);

    public static native CvMat cvGetDiag(@Const CvArr cvArr, CvMat cvMat);

    public static native CvMat cvGetDiag(@Const CvArr cvArr, CvMat cvMat, int i);

    public static native int cvGetDimSize(@Const CvArr cvArr, int i);

    public static native int cvGetDims(@Const CvArr cvArr);

    public static native int cvGetDims(@Const CvArr cvArr, IntBuffer intBuffer);

    public static native int cvGetDims(@Const CvArr cvArr, IntPointer intPointer);

    public static native int cvGetDims(@Const CvArr cvArr, int[] iArr);

    public static native int cvGetElemType(@Const CvArr cvArr);

    public static native int cvGetErrInfo(@ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer2, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer3, IntBuffer intBuffer);

    public static native int cvGetErrInfo(@ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer3, IntPointer intPointer);

    public static native int cvGetErrInfo(@Cast({"const char**"}) PointerPointer pointerPointer, @Cast({"const char**"}) PointerPointer pointerPointer2, @Cast({"const char**"}) PointerPointer pointerPointer3, IntPointer intPointer);

    public static native int cvGetErrInfo(@ByPtrPtr @Cast({"const char**"}) byte[] bArr, @ByPtrPtr @Cast({"const char**"}) byte[] bArr2, @ByPtrPtr @Cast({"const char**"}) byte[] bArr3, int[] iArr);

    public static native int cvGetErrMode();

    public static native int cvGetErrStatus();

    public static native IplImage cvGetImage(@Const CvArr cvArr, IplImage iplImage);

    public static native int cvGetImageCOI(@Const IplImage iplImage);

    @ByVal
    public static native CvRect cvGetImageROI(@Const IplImage iplImage);

    public static native CvMat cvGetMat(@Const CvArr cvArr, CvMat cvMat);

    public static native CvMat cvGetMat(@Const CvArr cvArr, CvMat cvMat, IntBuffer intBuffer, int i);

    public static native CvMat cvGetMat(@Const CvArr cvArr, CvMat cvMat, IntPointer intPointer, int i);

    public static native CvMat cvGetMat(@Const CvArr cvArr, CvMat cvMat, int[] iArr, int i);

    @ByVal
    public static native CvScalar cvGetND(@Const CvArr cvArr, @Const IntBuffer intBuffer);

    @ByVal
    public static native CvScalar cvGetND(@Const CvArr cvArr, @Const IntPointer intPointer);

    @ByVal
    public static native CvScalar cvGetND(@Const CvArr cvArr, @Const int[] iArr);

    public static native CvSparseNode cvGetNextSparseNode(CvSparseMatIterator cvSparseMatIterator);

    public static native int cvGetNumThreads();

    public static native int cvGetOptimalDFTSize(int i);

    public static native void cvGetRawData(@Const CvArr cvArr, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer);

    public static native void cvGetRawData(@Const CvArr cvArr, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer, IntBuffer intBuffer, CvSize cvSize);

    public static native void cvGetRawData(@Const CvArr cvArr, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer);

    public static native void cvGetRawData(@Const CvArr cvArr, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer, IntPointer intPointer, CvSize cvSize);

    public static native void cvGetRawData(@Const CvArr cvArr, @Cast({"uchar**"}) PointerPointer pointerPointer, IntPointer intPointer, CvSize cvSize);

    public static native void cvGetRawData(@Const CvArr cvArr, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr);

    public static native void cvGetRawData(@Const CvArr cvArr, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr, int[] iArr, CvSize cvSize);

    public static native double cvGetReal1D(@Const CvArr cvArr, int i);

    public static native double cvGetReal2D(@Const CvArr cvArr, int i, int i2);

    public static native double cvGetReal3D(@Const CvArr cvArr, int i, int i2, int i3);

    public static native double cvGetRealND(@Const CvArr cvArr, @Const IntBuffer intBuffer);

    public static native double cvGetRealND(@Const CvArr cvArr, @Const IntPointer intPointer);

    public static native double cvGetRealND(@Const CvArr cvArr, @Const int[] iArr);

    public static native CvMat cvGetRow(@Const CvArr cvArr, CvMat cvMat, int i);

    public static native CvMat cvGetRows(@Const CvArr cvArr, CvMat cvMat, int i, int i2);

    public static native CvMat cvGetRows(@Const CvArr cvArr, CvMat cvMat, int i, int i2, int i3);

    @Cast({"schar*"})
    public static native BytePointer cvGetSeqElem(@Const CvSeq cvSeq, int i);

    public static native int cvGetSeqReaderPos(CvSeqReader cvSeqReader);

    public static native CvSetElem cvGetSetElem(@Const CvSet cvSet, int i);

    @ByVal
    public static native CvSize cvGetSize(@Const CvArr cvArr);

    public static native CvMat cvGetSubArr(CvArr cvArr, CvMat cvMat, @ByVal CvRect cvRect);

    public static native CvMat cvGetSubRect(@Const CvArr cvArr, CvMat cvMat, @ByVal CvRect cvRect);

    public static native int cvGetThreadNum();

    @Cast({"int64"})
    public static native long cvGetTickCount();

    public static native double cvGetTickFrequency();

    public static native int cvGraphAddEdge(CvGraph cvGraph, int i, int i2);

    public static native int cvGraphAddEdge(CvGraph cvGraph, int i, int i2, @Const CvGraphEdge cvGraphEdge, @Cast({"CvGraphEdge**"}) PointerPointer pointerPointer);

    public static native int cvGraphAddEdge(CvGraph cvGraph, int i, int i2, @Const CvGraphEdge cvGraphEdge, @ByPtrPtr CvGraphEdge cvGraphEdge2);

    public static native int cvGraphAddEdgeByPtr(CvGraph cvGraph, CvGraphVtx cvGraphVtx, CvGraphVtx cvGraphVtx2);

    public static native int cvGraphAddEdgeByPtr(CvGraph cvGraph, CvGraphVtx cvGraphVtx, CvGraphVtx cvGraphVtx2, @Const CvGraphEdge cvGraphEdge, @Cast({"CvGraphEdge**"}) PointerPointer pointerPointer);

    public static native int cvGraphAddEdgeByPtr(CvGraph cvGraph, CvGraphVtx cvGraphVtx, CvGraphVtx cvGraphVtx2, @Const CvGraphEdge cvGraphEdge, @ByPtrPtr CvGraphEdge cvGraphEdge2);

    public static native int cvGraphAddVtx(CvGraph cvGraph);

    public static native int cvGraphAddVtx(CvGraph cvGraph, @Const CvGraphVtx cvGraphVtx, @Cast({"CvGraphVtx**"}) PointerPointer pointerPointer);

    public static native int cvGraphAddVtx(CvGraph cvGraph, @Const CvGraphVtx cvGraphVtx, @ByPtrPtr CvGraphVtx cvGraphVtx2);

    public static native CvGraphEdge cvGraphFindEdge(CvGraph cvGraph, int i, int i2);

    public static native CvGraphEdge cvGraphFindEdgeByPtr(CvGraph cvGraph, CvGraphVtx cvGraphVtx, CvGraphVtx cvGraphVtx2);

    public static native void cvGraphRemoveEdge(CvGraph cvGraph, int i, int i2);

    public static native void cvGraphRemoveEdgeByPtr(CvGraph cvGraph, CvGraphVtx cvGraphVtx, CvGraphVtx cvGraphVtx2);

    public static native int cvGraphRemoveVtx(CvGraph cvGraph, int i);

    public static native int cvGraphRemoveVtxByPtr(CvGraph cvGraph, CvGraphVtx cvGraphVtx);

    public static native int cvGraphVtxDegree(@Const CvGraph cvGraph, int i);

    public static native int cvGraphVtxDegreeByPtr(@Const CvGraph cvGraph, @Const CvGraphVtx cvGraphVtx);

    public static native int cvGuiBoxReport(int i, String str, String str2, String str3, int i2, Pointer pointer);

    public static native int cvGuiBoxReport(int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i2, Pointer pointer);

    public static native void cvInRange(@Const CvArr cvArr, @Const CvArr cvArr2, @Const CvArr cvArr3, CvArr cvArr4);

    public static native void cvInRangeS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2, CvArr cvArr2);

    public static native int cvIncRefData(CvArr cvArr);

    public static native IplImage cvInitImageHeader(IplImage iplImage, @ByVal CvSize cvSize, int i, int i2);

    public static native IplImage cvInitImageHeader(IplImage iplImage, @ByVal CvSize cvSize, int i, int i2, int i3, int i4);

    public static native CvMat cvInitMatHeader(CvMat cvMat, int i, int i2, int i3);

    public static native CvMat cvInitMatHeader(CvMat cvMat, int i, int i2, int i3, Pointer pointer, int i4);

    public static native CvMatND cvInitMatNDHeader(CvMatND cvMatND, int i, @Const IntBuffer intBuffer, int i2);

    public static native CvMatND cvInitMatNDHeader(CvMatND cvMatND, int i, @Const IntBuffer intBuffer, int i2, Pointer pointer);

    public static native CvMatND cvInitMatNDHeader(CvMatND cvMatND, int i, @Const IntPointer intPointer, int i2);

    public static native CvMatND cvInitMatNDHeader(CvMatND cvMatND, int i, @Const IntPointer intPointer, int i2, Pointer pointer);

    public static native CvMatND cvInitMatNDHeader(CvMatND cvMatND, int i, @Const int[] iArr, int i2);

    public static native CvMatND cvInitMatNDHeader(CvMatND cvMatND, int i, @Const int[] iArr, int i2, Pointer pointer);

    public static native int cvInitNArrayIterator(int i, @Cast({"CvArr**"}) PointerPointer pointerPointer, @Const CvArr cvArr, CvMatND cvMatND, CvNArrayIterator cvNArrayIterator, int i2);

    public static native int cvInitNArrayIterator(int i, @ByPtrPtr CvArr cvArr, @Const CvArr cvArr2, CvMatND cvMatND, CvNArrayIterator cvNArrayIterator);

    public static native int cvInitNArrayIterator(int i, @ByPtrPtr CvArr cvArr, @Const CvArr cvArr2, CvMatND cvMatND, CvNArrayIterator cvNArrayIterator, int i2);

    public static native CvSparseNode cvInitSparseMatIterator(@Const CvSparseMat cvSparseMat, CvSparseMatIterator cvSparseMatIterator);

    public static native void cvInitTreeNodeIterator(CvTreeNodeIterator cvTreeNodeIterator, @Const Pointer pointer, int i);

    public static native void cvInsertNodeIntoTree(Pointer pointer, Pointer pointer2, Pointer pointer3);

    public static native void cvInv(CvArr cvArr, CvArr cvArr2, int i);

    public static native double cvInvert(@Const CvArr cvArr, CvArr cvArr2);

    public static native double cvInvert(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native int cvIplDepth(int i);

    @ByVal
    public static native IplImage cvIplImage();

    @ByVal
    public static native IplImage cvIplImage(@ByRef @Const Mat mat);

    public static native int cvIsInf(double d);

    public static native int cvIsInf(float f);

    public static native int cvIsNaN(double d);

    public static native int cvIsNaN(float f);

    public static native int cvKMeans2(@Const CvArr cvArr, int i, CvArr cvArr2, @ByVal CvTermCriteria cvTermCriteria);

    public static native int cvKMeans2(@Const CvArr cvArr, int i, CvArr cvArr2, @ByVal CvTermCriteria cvTermCriteria, int i2, @Cast({"CvRNG*"}) LongBuffer longBuffer, int i3, CvArr cvArr3, DoubleBuffer doubleBuffer);

    public static native int cvKMeans2(@Const CvArr cvArr, int i, CvArr cvArr2, @ByVal CvTermCriteria cvTermCriteria, int i2, @Cast({"CvRNG*"}) LongPointer longPointer, int i3, CvArr cvArr3, DoublePointer doublePointer);

    public static native int cvKMeans2(@Const CvArr cvArr, int i, CvArr cvArr2, @ByVal CvTermCriteria cvTermCriteria, int i2, @Cast({"CvRNG*"}) long[] jArr, int i3, CvArr cvArr3, double[] dArr);

    public static native void cvLUT(@Const CvArr cvArr, CvArr cvArr2, @Const CvArr cvArr3);

    public static native void cvLog(@Const CvArr cvArr, CvArr cvArr2);

    public static native double cvMahalanobis(@Const CvArr cvArr, @Const CvArr cvArr2, @Const CvArr cvArr3);

    public static native double cvMahalonobis(CvArr cvArr, CvArr cvArr2, CvArr cvArr3);

    public static native CvSeq cvMakeSeqHeaderForArray(int i, int i2, int i3, Pointer pointer, int i4, CvSeq cvSeq, CvSeqBlock cvSeqBlock);

    @ByVal
    public static native CvMat cvMat();

    @ByVal
    public static native CvMat cvMat(int i, int i2, int i3);

    @ByVal
    public static native CvMat cvMat(int i, int i2, int i3, Pointer pointer);

    @ByVal
    public static native CvMat cvMat(@ByRef @Const CvMat cvMat);

    @ByVal
    public static native CvMat cvMat(@ByRef @Const Mat mat);

    public static native void cvMatMul(CvArr cvArr, CvArr cvArr2, CvArr cvArr3);

    public static native void cvMatMulAdd(CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvArr cvArr4);

    public static native void cvMatMulAddEx(CvArr cvArr, CvArr cvArr2, double d, CvArr cvArr3, double d2, CvArr cvArr4, int i);

    public static native void cvMatMulAddS(CvArr cvArr, CvArr cvArr2, CvMat cvMat, CvMat cvMat2);

    @ByVal
    public static native CvMatND cvMatND();

    @ByVal
    public static native CvMatND cvMatND(@ByRef @Const Mat mat);

    public static native void cvMax(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvMaxS(@Const CvArr cvArr, double d, CvArr cvArr2);

    public static native Pointer cvMemStorageAlloc(CvMemStorage cvMemStorage, @Cast({"size_t"}) long j);

    public static native void cvMerge(@Const CvArr cvArr, @Const CvArr cvArr2, @Const CvArr cvArr3, @Const CvArr cvArr4, CvArr cvArr5);

    public static native void cvMin(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvMinMaxLoc(@Const CvArr cvArr, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2);

    public static native void cvMinMaxLoc(@Const CvArr cvArr, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, @Cast({"CvPoint*"}) IntBuffer intBuffer, @Cast({"CvPoint*"}) IntBuffer intBuffer2, @Const CvArr cvArr2);

    public static native void cvMinMaxLoc(@Const CvArr cvArr, DoublePointer doublePointer, DoublePointer doublePointer2);

    public static native void cvMinMaxLoc(@Const CvArr cvArr, DoublePointer doublePointer, DoublePointer doublePointer2, CvPoint cvPoint, CvPoint cvPoint2, @Const CvArr cvArr2);

    public static native void cvMinMaxLoc(@Const CvArr cvArr, double[] dArr, double[] dArr2);

    public static native void cvMinMaxLoc(@Const CvArr cvArr, double[] dArr, double[] dArr2, @Cast({"CvPoint*"}) int[] iArr, @Cast({"CvPoint*"}) int[] iArr2, @Const CvArr cvArr2);

    public static native void cvMinS(@Const CvArr cvArr, double d, CvArr cvArr2);

    public static native void cvMirror(CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvMixChannels(@Cast({"const CvArr**"}) PointerPointer pointerPointer, int i, @Cast({"CvArr**"}) PointerPointer pointerPointer2, int i2, @Const IntPointer intPointer, int i3);

    public static native void cvMixChannels(@ByPtrPtr @Const CvArr cvArr, int i, @ByPtrPtr CvArr cvArr2, int i2, @Const IntBuffer intBuffer, int i3);

    public static native void cvMixChannels(@ByPtrPtr @Const CvArr cvArr, int i, @ByPtrPtr CvArr cvArr2, int i2, @Const IntPointer intPointer, int i3);

    public static native void cvMixChannels(@ByPtrPtr @Const CvArr cvArr, int i, @ByPtrPtr CvArr cvArr2, int i2, @Const int[] iArr, int i3);

    public static native void cvMul(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvMul(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, double d);

    public static native void cvMulSpectrums(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, int i);

    public static native void cvMulTransposed(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvMulTransposed(@Const CvArr cvArr, CvArr cvArr2, int i, @Const CvArr cvArr3, double d);

    public static native int cvNextGraphItem(CvGraphScanner cvGraphScanner);

    public static native int cvNextNArraySlice(CvNArrayIterator cvNArrayIterator);

    public static native Pointer cvNextTreeNode(CvTreeNodeIterator cvTreeNodeIterator);

    public static native double cvNorm(@Const CvArr cvArr);

    public static native double cvNorm(@Const CvArr cvArr, @Const CvArr cvArr2, int i, @Const CvArr cvArr3);

    public static native void cvNormalize(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvNormalize(@Const CvArr cvArr, CvArr cvArr2, double d, double d2, int i, @Const CvArr cvArr3);

    public static native void cvNot(@Const CvArr cvArr, CvArr cvArr2);

    public static native int cvNulDevReport(int i, String str, String str2, String str3, int i2, Pointer pointer);

    public static native int cvNulDevReport(int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i2, Pointer pointer);

    public static native void cvOr(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvOr(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvOrS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2);

    public static native void cvOrS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2, @Const CvArr cvArr3);

    public static native void cvPerspectiveTransform(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat);

    @ByVal
    public static native CvPoint cvPoint(int i, int i2);

    @ByVal
    public static native CvPoint cvPoint(@ByRef @Const Point point);

    @ByVal
    public static native CvPoint2D32f cvPoint2D32f(double d, double d2);

    @ByVal
    public static native CvPoint2D64f cvPoint2D64f(double d, double d2);

    @ByVal
    public static native CvPoint3D32f cvPoint3D32f(double d, double d2, double d3);

    @ByVal
    public static native CvPoint3D64f cvPoint3D64f(double d, double d2, double d3);

    @Cast({"CvPoint*"})
    @ByVal
    public static native IntBuffer cvPointFrom32f(@Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer);

    @ByVal
    public static native CvPoint cvPointFrom32f(@ByVal CvPoint2D32f cvPoint2D32f);

    @Cast({"CvPoint*"})
    @ByVal
    public static native int[] cvPointFrom32f(@Cast({"CvPoint2D32f*"}) @ByVal float[] fArr);

    @Cast({"CvPoint2D32f*"})
    @ByVal
    public static native FloatBuffer cvPointTo32f(@Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer);

    @ByVal
    public static native CvPoint2D32f cvPointTo32f(@ByVal CvPoint cvPoint);

    @Cast({"CvPoint2D32f*"})
    @ByVal
    public static native float[] cvPointTo32f(@Cast({"CvPoint*"}) @ByVal int[] iArr);

    public static native void cvPolarToCart(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, CvArr cvArr4);

    public static native void cvPolarToCart(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, CvArr cvArr4, int i);

    public static native void cvPow(@Const CvArr cvArr, CvArr cvArr2, double d);

    public static native Pointer cvPrevTreeNode(CvTreeNodeIterator cvTreeNodeIterator);

    public static native void cvProjectPCA(@Const CvArr cvArr, @Const CvArr cvArr2, @Const CvArr cvArr3, CvArr cvArr4);

    @Cast({"uchar*"})
    public static native ByteBuffer cvPtr1D(@Const CvArr cvArr, int i, IntBuffer intBuffer);

    @Cast({"uchar*"})
    public static native BytePointer cvPtr1D(@Const CvArr cvArr, int i);

    @Cast({"uchar*"})
    public static native BytePointer cvPtr1D(@Const CvArr cvArr, int i, IntPointer intPointer);

    @Cast({"uchar*"})
    public static native byte[] cvPtr1D(@Const CvArr cvArr, int i, int[] iArr);

    @Cast({"uchar*"})
    public static native ByteBuffer cvPtr2D(@Const CvArr cvArr, int i, int i2, IntBuffer intBuffer);

    @Cast({"uchar*"})
    public static native BytePointer cvPtr2D(@Const CvArr cvArr, int i, int i2);

    @Cast({"uchar*"})
    public static native BytePointer cvPtr2D(@Const CvArr cvArr, int i, int i2, IntPointer intPointer);

    @Cast({"uchar*"})
    public static native byte[] cvPtr2D(@Const CvArr cvArr, int i, int i2, int[] iArr);

    @Cast({"uchar*"})
    public static native ByteBuffer cvPtr3D(@Const CvArr cvArr, int i, int i2, int i3, IntBuffer intBuffer);

    @Cast({"uchar*"})
    public static native BytePointer cvPtr3D(@Const CvArr cvArr, int i, int i2, int i3);

    @Cast({"uchar*"})
    public static native BytePointer cvPtr3D(@Const CvArr cvArr, int i, int i2, int i3, IntPointer intPointer);

    @Cast({"uchar*"})
    public static native byte[] cvPtr3D(@Const CvArr cvArr, int i, int i2, int i3, int[] iArr);

    @Cast({"uchar*"})
    public static native ByteBuffer cvPtrND(@Const CvArr cvArr, @Const IntBuffer intBuffer);

    @Cast({"uchar*"})
    public static native ByteBuffer cvPtrND(@Const CvArr cvArr, @Const IntBuffer intBuffer, IntBuffer intBuffer2, int i, @Cast({"unsigned*"}) IntBuffer intBuffer3);

    @Cast({"uchar*"})
    public static native BytePointer cvPtrND(@Const CvArr cvArr, @Const IntPointer intPointer);

    @Cast({"uchar*"})
    public static native BytePointer cvPtrND(@Const CvArr cvArr, @Const IntPointer intPointer, IntPointer intPointer2, int i, @Cast({"unsigned*"}) IntPointer intPointer3);

    @Cast({"uchar*"})
    public static native byte[] cvPtrND(@Const CvArr cvArr, @Const int[] iArr);

    @Cast({"uchar*"})
    public static native byte[] cvPtrND(@Const CvArr cvArr, @Const int[] iArr, int[] iArr2, int i, @Cast({"unsigned*"}) int[] iArr3);

    @Cast({"CvRNG"})
    public static native long cvRNG();

    @Cast({"CvRNG"})
    public static native long cvRNG(@Cast({"int64"}) long j);

    @ByVal
    public static native CvRect cvROIToRect(@ByVal IplROI iplROI);

    public static native void cvRandArr(@Cast({"CvRNG*"}) LongBuffer longBuffer, CvArr cvArr, int i, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2);

    public static native void cvRandArr(@Cast({"CvRNG*"}) LongPointer longPointer, CvArr cvArr, int i, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2);

    public static native void cvRandArr(@Cast({"CvRNG*"}) long[] jArr, CvArr cvArr, int i, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2);

    @Cast({"unsigned"})
    public static native int cvRandInt(@Cast({"CvRNG*"}) LongBuffer longBuffer);

    @Cast({"unsigned"})
    public static native int cvRandInt(@Cast({"CvRNG*"}) LongPointer longPointer);

    @Cast({"unsigned"})
    public static native int cvRandInt(@Cast({"CvRNG*"}) long[] jArr);

    public static native double cvRandReal(@Cast({"CvRNG*"}) LongBuffer longBuffer);

    public static native double cvRandReal(@Cast({"CvRNG*"}) LongPointer longPointer);

    public static native double cvRandReal(@Cast({"CvRNG*"}) long[] jArr);

    public static native void cvRandShuffle(CvArr cvArr, @Cast({"CvRNG*"}) LongBuffer longBuffer);

    public static native void cvRandShuffle(CvArr cvArr, @Cast({"CvRNG*"}) LongBuffer longBuffer, double d);

    public static native void cvRandShuffle(CvArr cvArr, @Cast({"CvRNG*"}) LongPointer longPointer);

    public static native void cvRandShuffle(CvArr cvArr, @Cast({"CvRNG*"}) LongPointer longPointer, double d);

    public static native void cvRandShuffle(CvArr cvArr, @Cast({"CvRNG*"}) long[] jArr);

    public static native void cvRandShuffle(CvArr cvArr, @Cast({"CvRNG*"}) long[] jArr, double d);

    public static native CvArr cvRange(CvArr cvArr, double d, double d2);

    public static native void cvRawDataToScalar(@Const Pointer pointer, int i, CvScalar cvScalar);

    @ByVal
    public static native CvScalar cvRealScalar(double d);

    @ByVal
    public static native CvRect cvRect(int i, int i2, int i3, int i4);

    @ByVal
    public static native CvRect cvRect(@ByRef @Const Rect rect);

    @ByVal
    public static native IplROI cvRectToROI(@ByVal CvRect cvRect, int i);

    public static native CvErrorCallback cvRedirectError(CvErrorCallback cvErrorCallback);

    public static native CvErrorCallback cvRedirectError(CvErrorCallback cvErrorCallback, Pointer pointer, @ByPtrPtr @Cast({"void**"}) Pointer pointer2);

    public static native CvErrorCallback cvRedirectError(CvErrorCallback cvErrorCallback, Pointer pointer, @Cast({"void**"}) PointerPointer pointerPointer);

    public static native void cvReduce(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvReduce(@Const CvArr cvArr, CvArr cvArr2, int i, int i2);

    public static native void cvRelease(@ByPtrPtr @Cast({"void**"}) Pointer pointer);

    public static native void cvRelease(@Cast({"void**"}) PointerPointer pointerPointer);

    public static native void cvReleaseData(CvArr cvArr);

    public static native void cvReleaseGraphScanner(@Cast({"CvGraphScanner**"}) PointerPointer pointerPointer);

    public static native void cvReleaseGraphScanner(@ByPtrPtr CvGraphScanner cvGraphScanner);

    public static native void cvReleaseImage(@Cast({"IplImage**"}) PointerPointer pointerPointer);

    public static native void cvReleaseImage(@ByPtrPtr IplImage iplImage);

    public static native void cvReleaseImageHeader(@Cast({"IplImage**"}) PointerPointer pointerPointer);

    public static native void cvReleaseImageHeader(@ByPtrPtr IplImage iplImage);

    public static native void cvReleaseMat(@Cast({"CvMat**"}) PointerPointer pointerPointer);

    public static native void cvReleaseMat(@ByPtrPtr CvMat cvMat);

    public static native void cvReleaseMatND(@Cast({"CvMatND**"}) PointerPointer pointerPointer);

    public static native void cvReleaseMatND(@ByPtrPtr CvMatND cvMatND);

    public static native void cvReleaseMemStorage(@Cast({"CvMemStorage**"}) PointerPointer pointerPointer);

    public static native void cvReleaseMemStorage(@ByPtrPtr CvMemStorage cvMemStorage);

    public static native void cvReleaseSparseMat(@Cast({"CvSparseMat**"}) PointerPointer pointerPointer);

    public static native void cvReleaseSparseMat(@ByPtrPtr CvSparseMat cvSparseMat);

    public static native void cvRemoveNodeFromTree(Pointer pointer, Pointer pointer2);

    public static native void cvRepeat(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvResetImageROI(IplImage iplImage);

    public static native CvMat cvReshape(@Const CvArr cvArr, CvMat cvMat, int i);

    public static native CvMat cvReshape(@Const CvArr cvArr, CvMat cvMat, int i, int i2);

    public static native CvArr cvReshapeMatND(@Const CvArr cvArr, int i, CvArr cvArr2, int i2, int i3, IntBuffer intBuffer);

    public static native CvArr cvReshapeMatND(@Const CvArr cvArr, int i, CvArr cvArr2, int i2, int i3, IntPointer intPointer);

    public static native CvArr cvReshapeMatND(@Const CvArr cvArr, int i, CvArr cvArr2, int i2, int i3, int[] iArr);

    public static native void cvRestoreMemStoragePos(CvMemStorage cvMemStorage, CvMemStoragePos cvMemStoragePos);

    public static native int cvRound(double d);

    public static native int cvRound(float f);

    public static native int cvRound(int i);

    public static native void cvSVBkSb(@Const CvArr cvArr, @Const CvArr cvArr2, @Const CvArr cvArr3, @Const CvArr cvArr4, CvArr cvArr5, int i);

    public static native void cvSVD(CvArr cvArr, CvArr cvArr2);

    public static native void cvSVD(CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvArr cvArr4, int i);

    public static native void cvSaveMemStoragePos(@Const CvMemStorage cvMemStorage, CvMemStoragePos cvMemStoragePos);

    @ByVal
    public static native CvScalar cvScalar();

    @ByVal
    public static native CvScalar cvScalar(double d);

    @ByVal
    public static native CvScalar cvScalar(double d, double d2, double d3, double d4);

    @ByVal
    public static native CvScalar cvScalar(@ByRef @Const Scalar scalar);

    @ByVal
    public static native CvScalar cvScalarAll(double d);

    public static native void cvScalarToRawData(@Const CvScalar cvScalar, Pointer pointer, int i);

    public static native void cvScalarToRawData(@Const CvScalar cvScalar, Pointer pointer, int i, int i2);

    public static native void cvScale(CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native void cvScaleAdd(@Const CvArr cvArr, @ByVal CvScalar cvScalar, @Const CvArr cvArr2, CvArr cvArr3);

    public static native int cvSeqElemIdx(@Const CvSeq cvSeq, @Const Pointer pointer);

    public static native int cvSeqElemIdx(@Const CvSeq cvSeq, @Const Pointer pointer, @Cast({"CvSeqBlock**"}) PointerPointer pointerPointer);

    public static native int cvSeqElemIdx(@Const CvSeq cvSeq, @Const Pointer pointer, @ByPtrPtr CvSeqBlock cvSeqBlock);

    @Cast({"schar*"})
    public static native BytePointer cvSeqInsert(CvSeq cvSeq, int i);

    @Cast({"schar*"})
    public static native BytePointer cvSeqInsert(CvSeq cvSeq, int i, @Const Pointer pointer);

    public static native void cvSeqInsertSlice(CvSeq cvSeq, int i, @Const CvArr cvArr);

    public static native void cvSeqInvert(CvSeq cvSeq);

    public static native int cvSeqPartition(@Const CvSeq cvSeq, CvMemStorage cvMemStorage, @Cast({"CvSeq**"}) PointerPointer pointerPointer, CvCmpFunc cvCmpFunc, Pointer pointer);

    public static native int cvSeqPartition(@Const CvSeq cvSeq, CvMemStorage cvMemStorage, @ByPtrPtr CvSeq cvSeq2, CvCmpFunc cvCmpFunc, Pointer pointer);

    public static native void cvSeqPop(CvSeq cvSeq);

    public static native void cvSeqPop(CvSeq cvSeq, Pointer pointer);

    public static native void cvSeqPopFront(CvSeq cvSeq);

    public static native void cvSeqPopFront(CvSeq cvSeq, Pointer pointer);

    public static native void cvSeqPopMulti(CvSeq cvSeq, Pointer pointer, int i);

    public static native void cvSeqPopMulti(CvSeq cvSeq, Pointer pointer, int i, int i2);

    @Cast({"schar*"})
    public static native BytePointer cvSeqPush(CvSeq cvSeq);

    @Cast({"schar*"})
    public static native BytePointer cvSeqPush(CvSeq cvSeq, @Const Pointer pointer);

    @Cast({"schar*"})
    public static native BytePointer cvSeqPushFront(CvSeq cvSeq);

    @Cast({"schar*"})
    public static native BytePointer cvSeqPushFront(CvSeq cvSeq, @Const Pointer pointer);

    public static native void cvSeqPushMulti(CvSeq cvSeq, @Const Pointer pointer, int i);

    public static native void cvSeqPushMulti(CvSeq cvSeq, @Const Pointer pointer, int i, int i2);

    public static native void cvSeqRemove(CvSeq cvSeq, int i);

    public static native void cvSeqRemoveSlice(CvSeq cvSeq, @ByVal CvSlice cvSlice);

    @Cast({"schar*"})
    public static native ByteBuffer cvSeqSearch(CvSeq cvSeq, @Const Pointer pointer, CvCmpFunc cvCmpFunc, int i, IntBuffer intBuffer);

    @Cast({"schar*"})
    public static native ByteBuffer cvSeqSearch(CvSeq cvSeq, @Const Pointer pointer, CvCmpFunc cvCmpFunc, int i, IntBuffer intBuffer, Pointer pointer2);

    @Cast({"schar*"})
    public static native BytePointer cvSeqSearch(CvSeq cvSeq, @Const Pointer pointer, CvCmpFunc cvCmpFunc, int i, IntPointer intPointer);

    @Cast({"schar*"})
    public static native BytePointer cvSeqSearch(CvSeq cvSeq, @Const Pointer pointer, CvCmpFunc cvCmpFunc, int i, IntPointer intPointer, Pointer pointer2);

    @Cast({"schar*"})
    public static native byte[] cvSeqSearch(CvSeq cvSeq, @Const Pointer pointer, CvCmpFunc cvCmpFunc, int i, int[] iArr);

    @Cast({"schar*"})
    public static native byte[] cvSeqSearch(CvSeq cvSeq, @Const Pointer pointer, CvCmpFunc cvCmpFunc, int i, int[] iArr, Pointer pointer2);

    public static native CvSeq cvSeqSlice(@Const CvSeq cvSeq, @ByVal CvSlice cvSlice);

    public static native CvSeq cvSeqSlice(@Const CvSeq cvSeq, @ByVal CvSlice cvSlice, CvMemStorage cvMemStorage, int i);

    public static native void cvSeqSort(CvSeq cvSeq, CvCmpFunc cvCmpFunc);

    public static native void cvSeqSort(CvSeq cvSeq, CvCmpFunc cvCmpFunc, Pointer pointer);

    public static native void cvSet(CvArr cvArr, @ByVal CvScalar cvScalar);

    public static native void cvSet(CvArr cvArr, @ByVal CvScalar cvScalar, @Const CvArr cvArr2);

    public static native void cvSet1D(CvArr cvArr, int i, @ByVal CvScalar cvScalar);

    public static native void cvSet2D(CvArr cvArr, int i, int i2, @ByVal CvScalar cvScalar);

    public static native void cvSet3D(CvArr cvArr, int i, int i2, int i3, @ByVal CvScalar cvScalar);

    public static native int cvSetAdd(CvSet cvSet);

    public static native int cvSetAdd(CvSet cvSet, CvSetElem cvSetElem, @Cast({"CvSetElem**"}) PointerPointer pointerPointer);

    public static native int cvSetAdd(CvSet cvSet, CvSetElem cvSetElem, @ByPtrPtr CvSetElem cvSetElem2);

    public static native void cvSetData(CvArr cvArr, Pointer pointer, int i);

    public static native int cvSetErrMode(int i);

    public static native void cvSetErrStatus(int i);

    public static native void cvSetIPLAllocators(Cv_iplCreateImageHeader cv_iplCreateImageHeader, Cv_iplAllocateImageData cv_iplAllocateImageData, Cv_iplDeallocate cv_iplDeallocate, Cv_iplCreateROI cv_iplCreateROI, Cv_iplCloneImage cv_iplCloneImage);

    public static native void cvSetIdentity(CvArr cvArr);

    public static native void cvSetIdentity(CvArr cvArr, @ByVal(nullValue = "CvScalar(cvRealScalar(1))") CvScalar cvScalar);

    public static native void cvSetImageCOI(IplImage iplImage, int i);

    public static native void cvSetImageROI(IplImage iplImage, @ByVal CvRect cvRect);

    public static native void cvSetND(CvArr cvArr, @Const IntBuffer intBuffer, @ByVal CvScalar cvScalar);

    public static native void cvSetND(CvArr cvArr, @Const IntPointer intPointer, @ByVal CvScalar cvScalar);

    public static native void cvSetND(CvArr cvArr, @Const int[] iArr, @ByVal CvScalar cvScalar);

    public static native CvSetElem cvSetNew(CvSet cvSet);

    public static native void cvSetNumThreads();

    public static native void cvSetNumThreads(int i);

    public static native void cvSetReal1D(CvArr cvArr, int i, double d);

    public static native void cvSetReal2D(CvArr cvArr, int i, int i2, double d);

    public static native void cvSetReal3D(CvArr cvArr, int i, int i2, int i3, double d);

    public static native void cvSetRealND(CvArr cvArr, @Const IntBuffer intBuffer, double d);

    public static native void cvSetRealND(CvArr cvArr, @Const IntPointer intPointer, double d);

    public static native void cvSetRealND(CvArr cvArr, @Const int[] iArr, double d);

    public static native void cvSetRemove(CvSet cvSet, int i);

    public static native void cvSetRemoveByPtr(CvSet cvSet, Pointer pointer);

    public static native void cvSetSeqBlockSize(CvSeq cvSeq, int i);

    public static native void cvSetSeqReaderPos(CvSeqReader cvSeqReader, int i);

    public static native void cvSetSeqReaderPos(CvSeqReader cvSeqReader, int i, int i2);

    public static native void cvSetZero(CvArr cvArr);

    @ByVal
    public static native CvSize cvSize(int i, int i2);

    @ByVal
    public static native CvSize cvSize(@ByRef @Const Size size);

    @ByVal
    public static native CvSize2D32f cvSize2D32f(double d, double d2);

    @ByVal
    public static native CvSlice cvSlice(int i, int i2);

    @ByVal
    public static native CvSlice cvSlice(@ByRef @Const Range range);

    public static native int cvSliceLength(@ByVal CvSlice cvSlice, @Const CvSeq cvSeq);

    public static native int cvSolve(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native int cvSolve(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, int i);

    public static native int cvSolveCubic(@Const CvMat cvMat, CvMat cvMat2);

    public static native void cvSolvePoly(@Const CvMat cvMat, CvMat cvMat2);

    public static native void cvSolvePoly(@Const CvMat cvMat, CvMat cvMat2, int i, int i2);

    public static native void cvSort(@Const CvArr cvArr);

    public static native void cvSort(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, int i);

    public static native void cvSplit(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvArr cvArr4, CvArr cvArr5);

    public static native void cvStartAppendToSeq(CvSeq cvSeq, CvSeqWriter cvSeqWriter);

    public static native void cvStartReadSeq(@Const CvSeq cvSeq, CvSeqReader cvSeqReader);

    public static native void cvStartReadSeq(@Const CvSeq cvSeq, CvSeqReader cvSeqReader, int i);

    public static native void cvStartWriteSeq(int i, int i2, int i3, CvMemStorage cvMemStorage, CvSeqWriter cvSeqWriter);

    public static native int cvStdErrReport(int i, String str, String str2, String str3, int i2, Pointer pointer);

    public static native int cvStdErrReport(int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i2, Pointer pointer);

    public static native void cvSub(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvSub(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvSubRS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2);

    public static native void cvSubRS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2, @Const CvArr cvArr3);

    public static native void cvSubS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2);

    public static native void cvSubS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2, @Const CvArr cvArr3);

    @ByVal
    public static native CvScalar cvSum(@Const CvArr cvArr);

    public static native void cvT(CvArr cvArr, CvArr cvArr2);

    @ByVal
    public static native CvTermCriteria cvTermCriteria(int i, int i2, double d);

    @ByVal
    public static native CvTermCriteria cvTermCriteria(@ByRef @Const TermCriteria termCriteria);

    @ByVal
    public static native CvScalar cvTrace(@Const CvArr cvArr);

    public static native void cvTransform(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat);

    public static native void cvTransform(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat, @Const CvMat cvMat2);

    public static native void cvTranspose(@Const CvArr cvArr, CvArr cvArr2);

    public static native CvSeq cvTreeToNodeSeq(@Const Pointer pointer, int i, CvMemStorage cvMemStorage);

    public static native int cvUseOptimized(int i);

    public static native void cvXor(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvXor(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvXorS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2);

    public static native void cvXorS(@Const CvArr cvArr, @ByVal CvScalar cvScalar, CvArr cvArr2, @Const CvArr cvArr3);

    public static native void cvZero(CvArr cvArr);

    @Namespace("cv")
    public static native int cv_abs(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    public static native int cv_abs(@Cast({"ushort"}) short s);

    @Namespace("cv")
    @ByVal
    public static native Mat cvarrToMat(@Const CvArr cvArr);

    @Namespace("cv")
    @ByVal
    public static native Mat cvarrToMat(@Const CvArr cvArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, int i, @Cast({"cv::AutoBuffer<double>*"}) Pointer pointer);

    @Namespace("cv")
    @ByVal
    public static native Mat cvarrToMatND(@Const CvArr cvArr);

    @Namespace("cv")
    @ByVal
    public static native Mat cvarrToMatND(@Const CvArr cvArr, @Cast({"bool"}) boolean z, int i);

    public static native double cvmGet(@Const CvMat cvMat, int i, int i2);

    public static native void cvmSet(CvMat cvMat, int i, int i2, double d);

    @Namespace("cv::hal")
    public static native void cvt16f32f(@Const float16_t float16_t, FloatBuffer floatBuffer, int i);

    @Namespace("cv::hal")
    public static native void cvt16f32f(@Const float16_t float16_t, FloatPointer floatPointer, int i);

    @Namespace("cv::hal")
    public static native void cvt16f32f(@Const float16_t float16_t, float[] fArr, int i);

    @Namespace("cv::hal")
    public static native void cvt32f16f(@Const FloatBuffer floatBuffer, float16_t float16_t, int i);

    @Namespace("cv::hal")
    public static native void cvt32f16f(@Const FloatPointer floatPointer, float16_t float16_t, int i);

    @Namespace("cv::hal")
    public static native void cvt32f16f(@Const float[] fArr, float16_t float16_t, int i);

    @Namespace("cv")
    public static native void dct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void dct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void dct(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void dct(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void dct(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void dct(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native double determinant(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native double determinant(@ByVal Mat mat);

    @Namespace("cv")
    public static native double determinant(@ByVal UMat uMat);

    @Namespace("cv::cuda")
    @Cast({"bool"})
    public static native boolean deviceSupports(@Cast({"cv::cuda::FeatureSet"}) int i);

    @Namespace("cv")
    public static native void dft(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void dft(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void dft(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void dft(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void dft(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void dft(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv::hal")
    public static native void div16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void div8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    public static native int divUp(int i, @Cast({"unsigned int"}) int i2);

    @Namespace("cv")
    @Cast({"size_t"})
    public static native long divUp(@Cast({"size_t"}) long j, @Cast({"unsigned int"}) int i);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(double d, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(@ByRef @Const Mat mat, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(@ByRef @Const MatExpr matExpr, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(@ByRef @Const MatExpr matExpr, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator /"})
    public static native MatExpr divide(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2);

    @Namespace("cv")
    public static native void divide(double d, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void divide(double d, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void divide(double d, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void divide(double d, @ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void divide(double d, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void divide(double d, @ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void divide(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void divide(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i);

    @Namespace("cv")
    public static native void divide(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void divide(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i);

    @Namespace("cv")
    public static native void divide(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void divide(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i);

    @Namespace("cv")
    @ByRef
    @Name({"operator /="})
    public static native Mat dividePut(@ByRef Mat mat, double d);

    @Namespace("cv")
    @ByRef
    @Name({"operator /="})
    public static native Mat dividePut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @Name({"randu<double>"})
    public static native double doubleRand();

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean eigen(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean eigen(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean eigen(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean eigen(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean eigen(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean eigen(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    public static native void eigenNonSymmetric(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void eigenNonSymmetric(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void eigenNonSymmetric(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::cuda")
    public static native void ensureSizeIsEnough(int i, int i2, int i3, @ByVal GpuMat gpuMat);

    @Namespace("cv::cuda")
    public static native void ensureSizeIsEnough(int i, int i2, int i3, @ByVal Mat mat);

    @Namespace("cv::cuda")
    public static native void ensureSizeIsEnough(int i, int i2, int i3, @ByVal UMat uMat);

    @Namespace("cv")
    @ByVal
    @Name({"operator =="})
    public static native MatExpr equals(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator =="})
    public static native MatExpr equals(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator =="})
    public static native MatExpr equals(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator =="})
    public static native boolean equals(@Cast({"const cv::AccessFlag"}) int i, int i2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator =="})
    public static native boolean equals(@ByRef @Const FileNodeIterator fileNodeIterator, @ByRef @Const FileNodeIterator fileNodeIterator2);

    @Namespace("cv::instr")
    @Cast({"bool"})
    @Name({"operator =="})
    public static native boolean equals(@ByRef @Const NodeData nodeData, @ByRef @Const NodeData nodeData2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator =="})
    public static native boolean equals(@ByRef @Const Range range, @ByRef @Const Range range2);

    @Namespace("cv")
    public static native void error(int i, @opencv_core.Str String str, String str2, String str3, int i2);

    @Namespace("cv")
    public static native void error(int i, @opencv_core.Str BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i2);

    @Namespace("cv::hal")
    public static native void exp(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void exp(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void exp(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void exp(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv")
    public static native void exp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void exp(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void exp(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::hal")
    public static native void exp(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv::hal")
    public static native void exp(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void exp32f(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void exp32f(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native void exp32f(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void exp64f(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void exp64f(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void exp64f(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv")
    public static native void extractChannel(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void extractChannel(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void extractChannel(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void extractImageCOI(@Const CvArr cvArr, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void extractImageCOI(@Const CvArr cvArr, @ByVal GpuMat gpuMat, int i);

    @Namespace("cv")
    public static native void extractImageCOI(@Const CvArr cvArr, @ByVal Mat mat);

    @Namespace("cv")
    public static native void extractImageCOI(@Const CvArr cvArr, @ByVal Mat mat, int i);

    @Namespace("cv")
    public static native void extractImageCOI(@Const CvArr cvArr, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void extractImageCOI(@Const CvArr cvArr, @ByVal UMat uMat, int i);

    @Namespace("cv")
    public static native float fastAtan2(float f, float f2);

    @Namespace("cv::hal")
    public static native void fastAtan2(@Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan2(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, FloatPointer floatPointer3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan2(@Const float[] fArr, @Const float[] fArr2, float[] fArr3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan32f(@Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan32f(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, FloatPointer floatPointer3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan32f(@Const float[] fArr, @Const float[] fArr2, float[] fArr3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan64f(@Const DoubleBuffer doubleBuffer, @Const DoubleBuffer doubleBuffer2, DoubleBuffer doubleBuffer3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan64f(@Const DoublePointer doublePointer, @Const DoublePointer doublePointer2, DoublePointer doublePointer3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void fastAtan64f(@Const double[] dArr, @Const double[] dArr2, double[] dArr3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void fastFree(Pointer pointer);

    @Namespace("cv")
    public static native Pointer fastMalloc(@Cast({"size_t"}) long j);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native String findFile(@opencv_core.Str String str);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native String findFile(@opencv_core.Str String str, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native BytePointer findFile(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native BytePointer findFile(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native String findFileOrKeep(@opencv_core.Str String str);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native String findFileOrKeep(@opencv_core.Str String str, @Cast({"bool"}) boolean z);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native BytePointer findFileOrKeep(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::samples")
    @opencv_core.Str
    public static native BytePointer findFileOrKeep(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void findNonZero(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void findNonZero(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void findNonZero(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ocl")
    public static native void finish();

    @Namespace("cv")
    public static native void flip(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void flip(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void flip(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(double d);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(float f);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @ByVal
    @Name({"saturate_cast<cv::float16_t>"})
    public static native float16_t float16SaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv")
    @Name({"randu<float>"})
    public static native float floatRand();

    @Namespace("cv")
    @opencv_core.Str
    public static native String format(String str);

    @Namespace("cv")
    @opencv_core.Str
    public static native BytePointer format(@Cast({"const char*"}) BytePointer bytePointer);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Formatted format(@ByVal GpuMat gpuMat, @Cast({"cv::Formatter::FormatType"}) int i);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Formatted format(@ByVal Mat mat, @Cast({"cv::Formatter::FormatType"}) int i);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native Formatted format(@ByVal UMat uMat, @Cast({"cv::Formatter::FormatType"}) int i);

    @Namespace("cv")
    public static native void gemm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByVal GpuMat gpuMat3, double d2, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void gemm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByVal GpuMat gpuMat3, double d2, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void gemm(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByVal Mat mat3, double d2, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void gemm(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByVal Mat mat3, double d2, @ByVal Mat mat4, int i);

    @Namespace("cv")
    public static native void gemm(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByVal UMat uMat3, double d2, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void gemm(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByVal UMat uMat3, double d2, @ByVal UMat uMat4, int i);

    @Namespace("cv::hal")
    public static native void gemm32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, float f, @Const FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, float f2, FloatBuffer floatBuffer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, float f, @Const FloatPointer floatPointer3, @Cast({"size_t"}) long j3, float f2, FloatPointer floatPointer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float f, @Const float[] fArr3, @Cast({"size_t"}) long j3, float f2, float[] fArr4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm32fc(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, float f, @Const FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, float f2, FloatBuffer floatBuffer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm32fc(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, float f, @Const FloatPointer floatPointer3, @Cast({"size_t"}) long j3, float f2, FloatPointer floatPointer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm32fc(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float f, @Const float[] fArr3, @Cast({"size_t"}) long j3, float f2, float[] fArr4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, double d, @Const DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, double d2, DoubleBuffer doubleBuffer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, double d, @Const DoublePointer doublePointer3, @Cast({"size_t"}) long j3, double d2, DoublePointer doublePointer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double d, @Const double[] dArr3, @Cast({"size_t"}) long j3, double d2, double[] dArr4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm64fc(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, double d, @Const DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, double d2, DoubleBuffer doubleBuffer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm64fc(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, double d, @Const DoublePointer doublePointer3, @Cast({"size_t"}) long j3, double d2, DoublePointer doublePointer4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv::hal")
    public static native void gemm64fc(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double d, @Const double[] dArr3, @Cast({"size_t"}) long j3, double d2, double[] dArr4, @Cast({"size_t"}) long j4, int i, int i2, int i3, int i4);

    @Namespace("cv")
    @opencv_core.Str
    public static native BytePointer getBuildInformation();

    @Namespace("cv")
    @StdString
    public static native BytePointer getCPUFeaturesLine();

    @Namespace("cv")
    @Cast({"int64"})
    public static native long getCPUTickCount();

    @Namespace("cv::cuda")
    public static native int getCudaEnabledDeviceCount();

    @Namespace("cv::cuda")
    public static native int getDevice();

    @Namespace("cv")
    @Cast({"size_t"})
    public static native long getElemSize(int i);

    @Namespace("cv::instr")
    @Cast({"cv::instr::FLAGS"})
    public static native int getFlags();

    @Namespace("cv")
    @opencv_core.Str
    public static native BytePointer getHardwareFeatureName(int i);

    @Namespace("cv::ipp")
    @opencv_core.Str
    public static native BytePointer getIppErrorLocation();

    @Namespace("cv::ipp")
    @Cast({"unsigned long long"})
    public static native long getIppFeatures();

    @Namespace("cv::ipp")
    public static native int getIppStatus();

    @Namespace("cv::ipp")
    @opencv_core.Str
    public static native BytePointer getIppVersion();

    @Namespace("cv")
    public static native int getNumThreads();

    @Namespace("cv")
    public static native int getNumberOfCPUs();

    @Namespace("cv::ocl")
    public static native MatAllocator getOpenCLAllocator();

    @Namespace("cv::ocl")
    @Cast({"const char*"})
    public static native BytePointer getOpenCLErrorString(int i);

    @Namespace("cv")
    public static native int getOptimalDFTSize(int i);

    @Namespace("cv::ocl")
    public static native void getPlatfomsInfo(@StdVector PlatformInfo platformInfo);

    @Namespace("cv")
    @Cast({"schar*"})
    public static native BytePointer getSeqElem(@Const CvSeq cvSeq, int i);

    @Namespace("cv::utils")
    public static native int getThreadID();

    @Namespace("cv")
    public static native int getThreadNum();

    @Namespace("cv")
    @Cast({"int64"})
    public static native long getTickCount();

    @Namespace("cv")
    public static native double getTickFrequency();

    @Namespace("cv::instr")
    public static native InstrNode getTrace();

    @Namespace("cv")
    public static native int getVersionMajor();

    @Namespace("cv")
    public static native int getVersionMinor();

    @Namespace("cv")
    public static native int getVersionRevision();

    @Namespace("cv")
    @opencv_core.Str
    public static native BytePointer getVersionString();

    @Namespace("cv")
    public static native void glob(@opencv_core.Str String str, @ByRef StringVector stringVector);

    @Namespace("cv")
    public static native void glob(@opencv_core.Str String str, @ByRef StringVector stringVector, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void glob(@opencv_core.Str BytePointer bytePointer, @ByRef StringVector stringVector);

    @Namespace("cv")
    public static native void glob(@opencv_core.Str BytePointer bytePointer, @ByRef StringVector stringVector, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    @Name({"operator >"})
    public static native MatExpr greaterThan(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator >"})
    public static native MatExpr greaterThan(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator >"})
    public static native MatExpr greaterThan(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator >="})
    public static native MatExpr greaterThanEquals(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator >="})
    public static native MatExpr greaterThanEquals(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator >="})
    public static native MatExpr greaterThanEquals(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv::ocl")
    @Cast({"bool"})
    public static native boolean haveAmdBlas();

    @Namespace("cv::ocl")
    @Cast({"bool"})
    public static native boolean haveAmdFft();

    @Namespace("cv::ocl")
    @Cast({"bool"})
    public static native boolean haveOpenCL();

    @Namespace("cv::ocl")
    @Cast({"bool"})
    public static native boolean haveSVM();

    @Namespace("cv")
    public static native void hconcat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void hconcat(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void hconcat(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void hconcat(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void hconcat(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void hconcat(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void hconcat(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void hconcat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void hconcat(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void hconcat(@ByVal MatVector matVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void hconcat(@ByVal MatVector matVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void hconcat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void hconcat(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void hconcat(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void hconcat(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void idct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void idct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void idct(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void idct(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void idct(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void idct(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void idft(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void idft(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void idft(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void idft(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void idft(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void idft(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void inRange(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void inRange(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void inRange(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void insertChannel(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void insertChannel(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void insertChannel(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void insertImageCOI(@ByVal GpuMat gpuMat, CvArr cvArr);

    @Namespace("cv")
    public static native void insertImageCOI(@ByVal GpuMat gpuMat, CvArr cvArr, int i);

    @Namespace("cv")
    public static native void insertImageCOI(@ByVal Mat mat, CvArr cvArr);

    @Namespace("cv")
    public static native void insertImageCOI(@ByVal Mat mat, CvArr cvArr, int i);

    @Namespace("cv")
    public static native void insertImageCOI(@ByVal UMat uMat, CvArr cvArr);

    @Namespace("cv")
    public static native void insertImageCOI(@ByVal UMat uMat, CvArr cvArr, int i);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(double d);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(float f);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Cast({"int64"})
    @Name({"saturate_cast<int64>"})
    public static native long int64SaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv")
    @Name({"randu<int>"})
    public static native int intRand();

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(double d);

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(float f);

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Name({"saturate_cast<int>"})
    public static native int intSaturate(@Cast({"ushort"}) short s);

    @Namespace("cv::hal")
    public static native void invSqrt(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt32f(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt32f(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt32f(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt64f(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt64f(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void invSqrt64f(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv")
    public static native double invert(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native double invert(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native double invert(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native double invert(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native double invert(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native double invert(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native String kernelToStr(@ByVal GpuMat gpuMat, int i, String str);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native String kernelToStr(@ByVal Mat mat, int i, String str);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native String kernelToStr(@ByVal UMat uMat);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native String kernelToStr(@ByVal UMat uMat, int i, String str);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native BytePointer kernelToStr(@ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native BytePointer kernelToStr(@ByVal GpuMat gpuMat, int i, @Cast({"const char*"}) BytePointer bytePointer);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native BytePointer kernelToStr(@ByVal Mat mat);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native BytePointer kernelToStr(@ByVal Mat mat, int i, @Cast({"const char*"}) BytePointer bytePointer);

    @Namespace("cv::ocl")
    @opencv_core.Str
    public static native BytePointer kernelToStr(@ByVal UMat uMat, int i, @Cast({"const char*"}) BytePointer bytePointer);

    @Namespace("cv")
    public static native double kmeans(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal TermCriteria termCriteria, int i2, int i3);

    @Namespace("cv")
    public static native double kmeans(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal TermCriteria termCriteria, int i2, int i3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native double kmeans(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal TermCriteria termCriteria, int i2, int i3);

    @Namespace("cv")
    public static native double kmeans(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal TermCriteria termCriteria, int i2, int i3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native double kmeans(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal TermCriteria termCriteria, int i2, int i3);

    @Namespace("cv")
    public static native double kmeans(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal TermCriteria termCriteria, int i2, int i3, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    @ByVal
    @Name({"operator <"})
    public static native MatExpr lessThan(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator <"})
    public static native MatExpr lessThan(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator <"})
    public static native MatExpr lessThan(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator <"})
    public static native boolean lessThan(@ByRef @Const FileNodeIterator fileNodeIterator, @ByRef @Const FileNodeIterator fileNodeIterator2);

    @Namespace("cv")
    @ByVal
    @Name({"operator <="})
    public static native MatExpr lessThanEquals(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator <="})
    public static native MatExpr lessThanEquals(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator <="})
    public static native MatExpr lessThanEquals(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv::hal")
    public static native void log(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void log(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void log(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void log(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv")
    public static native void log(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void log(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void log(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::hal")
    public static native void log(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv::hal")
    public static native void log(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void log32f(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void log32f(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native void log32f(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void log64f(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void log64f(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void log64f(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv::hal")
    public static native void magnitude(@Const DoubleBuffer doubleBuffer, @Const DoubleBuffer doubleBuffer2, DoubleBuffer doubleBuffer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude(@Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude(@Const DoublePointer doublePointer, @Const DoublePointer doublePointer2, DoublePointer doublePointer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, FloatPointer floatPointer3, int i);

    @Namespace("cv")
    public static native void magnitude(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void magnitude(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void magnitude(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::hal")
    public static native void magnitude(@Const double[] dArr, @Const double[] dArr2, double[] dArr3, int i);

    @Namespace("cv::hal")
    public static native void magnitude(@Const float[] fArr, @Const float[] fArr2, float[] fArr3, int i);

    @Namespace("cv::hal")
    public static native void magnitude32f(@Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, FloatBuffer floatBuffer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude32f(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, FloatPointer floatPointer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude32f(@Const float[] fArr, @Const float[] fArr2, float[] fArr3, int i);

    @Namespace("cv::hal")
    public static native void magnitude64f(@Const DoubleBuffer doubleBuffer, @Const DoubleBuffer doubleBuffer2, DoubleBuffer doubleBuffer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude64f(@Const DoublePointer doublePointer, @Const DoublePointer doublePointer2, DoublePointer doublePointer3, int i);

    @Namespace("cv::hal")
    public static native void magnitude64f(@Const double[] dArr, @Const double[] dArr2, double[] dArr3, int i);

    @Namespace("cv")
    @ByVal
    public static native MatExpr max(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    public static native MatExpr max(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    public static native MatExpr max(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    public static native void max(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef Mat mat3);

    @Namespace("cv")
    public static native void max(@ByRef @Const UMat uMat, @ByRef @Const UMat uMat2, @ByRef UMat uMat3);

    @Namespace("cv::hal")
    public static native void max16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void max8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    @ByVal
    public static native Scalar mean(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native Scalar mean(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Scalar mean(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native Scalar mean(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Scalar mean(@ByVal UMat uMat);

    @Namespace("cv")
    @ByVal
    public static native Scalar mean(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void meanStdDev(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void meanStdDev(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void meanStdDev(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void meanStdDev(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void meanStdDev(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void meanStdDev(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv::ocl")
    @Cast({"const char*"})
    public static native BytePointer memopTypeToStr(int i);

    @Namespace("cv")
    public static native void merge(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void merge(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void merge(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void merge(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void merge(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void merge(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void merge(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void merge(@ByVal MatVector matVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void merge(@ByVal MatVector matVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void merge(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void merge(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void merge(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    @Namespace("cv::hal")
    public static native void merge16u(@ByPtrPtr @Cast({"const ushort**"}) ShortBuffer shortBuffer, @Cast({"ushort*"}) ShortBuffer shortBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge16u(@Cast({"const ushort**"}) PointerPointer pointerPointer, @Cast({"ushort*"}) ShortPointer shortPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge16u(@ByPtrPtr @Cast({"const ushort**"}) ShortPointer shortPointer, @Cast({"ushort*"}) ShortPointer shortPointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge16u(@ByPtrPtr @Cast({"const ushort**"}) short[] sArr, @Cast({"ushort*"}) short[] sArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge32s(@ByPtrPtr @Const IntBuffer intBuffer, IntBuffer intBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge32s(@ByPtrPtr @Const IntPointer intPointer, IntPointer intPointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge32s(@Cast({"const int**"}) PointerPointer pointerPointer, IntPointer intPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge32s(@ByPtrPtr @Const int[] iArr, int[] iArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge64s(@ByPtrPtr @Cast({"const int64**"}) LongBuffer longBuffer, @Cast({"int64*"}) LongBuffer longBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge64s(@ByPtrPtr @Cast({"const int64**"}) LongPointer longPointer, @Cast({"int64*"}) LongPointer longPointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge64s(@Cast({"const int64**"}) PointerPointer pointerPointer, @Cast({"int64*"}) LongPointer longPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge64s(@ByPtrPtr @Cast({"const int64**"}) long[] jArr, @Cast({"int64*"}) long[] jArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge8u(@ByPtrPtr @Cast({"const uchar**"}) ByteBuffer byteBuffer, @Cast({"uchar*"}) ByteBuffer byteBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge8u(@ByPtrPtr @Cast({"const uchar**"}) BytePointer bytePointer, @Cast({"uchar*"}) BytePointer bytePointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge8u(@Cast({"const uchar**"}) PointerPointer pointerPointer, @Cast({"uchar*"}) BytePointer bytePointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void merge8u(@ByPtrPtr @Cast({"const uchar**"}) byte[] bArr, @Cast({"uchar*"}) byte[] bArr2, int i, int i2);

    @Namespace("cv")
    @ByVal
    public static native MatExpr min(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    public static native MatExpr min(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    public static native MatExpr min(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    public static native void min(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef Mat mat3);

    @Namespace("cv")
    public static native void min(@ByRef @Const UMat uMat, @ByRef @Const UMat uMat2, @ByRef UMat uMat3);

    @Namespace("cv::hal")
    public static native void min16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void min8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, IntBuffer intBuffer, IntBuffer intBuffer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal GpuMat gpuMat, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal GpuMat gpuMat, DoublePointer doublePointer, DoublePointer doublePointer2, IntPointer intPointer, IntPointer intPointer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal GpuMat gpuMat, double[] dArr);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal GpuMat gpuMat, double[] dArr, double[] dArr2, int[] iArr, int[] iArr2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal Mat mat, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal Mat mat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, IntBuffer intBuffer, IntBuffer intBuffer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal Mat mat, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal Mat mat, DoublePointer doublePointer, DoublePointer doublePointer2, IntPointer intPointer, IntPointer intPointer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal Mat mat, double[] dArr);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal Mat mat, double[] dArr, double[] dArr2, int[] iArr, int[] iArr2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal UMat uMat, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal UMat uMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, IntBuffer intBuffer, IntBuffer intBuffer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal UMat uMat, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal UMat uMat, DoublePointer doublePointer, DoublePointer doublePointer2, IntPointer intPointer, IntPointer intPointer2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal UMat uMat, double[] dArr);

    @Namespace("cv")
    public static native void minMaxIdx(@ByVal UMat uMat, double[] dArr, double[] dArr2, int[] iArr, int[] iArr2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, double[] dArr);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal GpuMat gpuMat, double[] dArr, double[] dArr2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal Mat mat, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal Mat mat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal Mat mat, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal Mat mat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal Mat mat, double[] dArr);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal Mat mat, double[] dArr, double[] dArr2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByRef @Const SparseMat sparseMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByRef @Const SparseMat sparseMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, IntBuffer intBuffer, IntBuffer intBuffer2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByRef @Const SparseMat sparseMat, DoublePointer doublePointer, DoublePointer doublePointer2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByRef @Const SparseMat sparseMat, DoublePointer doublePointer, DoublePointer doublePointer2, IntPointer intPointer, IntPointer intPointer2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByRef @Const SparseMat sparseMat, double[] dArr, double[] dArr2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByRef @Const SparseMat sparseMat, double[] dArr, double[] dArr2, int[] iArr, int[] iArr2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal UMat uMat, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal UMat uMat, DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal UMat uMat, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal UMat uMat, DoublePointer doublePointer, DoublePointer doublePointer2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal UMat uMat, double[] dArr);

    @Namespace("cv")
    public static native void minMaxLoc(@ByVal UMat uMat, double[] dArr, double[] dArr2, Point point, Point point2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native void mixChannels(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @StdVector int[] iArr);

    @Namespace("cv")
    public static native void mixChannels(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @Const int[] iArr, @Cast({"size_t"}) long j);

    @Namespace("cv")
    public static native void mixChannels(@Const Mat mat, @Cast({"size_t"}) long j, Mat mat2, @Cast({"size_t"}) long j2, @Const IntBuffer intBuffer, @Cast({"size_t"}) long j3);

    @Namespace("cv")
    public static native void mixChannels(@Const Mat mat, @Cast({"size_t"}) long j, Mat mat2, @Cast({"size_t"}) long j2, @Const IntPointer intPointer, @Cast({"size_t"}) long j3);

    @Namespace("cv")
    public static native void mixChannels(@Const Mat mat, @Cast({"size_t"}) long j, Mat mat2, @Cast({"size_t"}) long j2, @Const int[] iArr, @Cast({"size_t"}) long j3);

    @Namespace("cv")
    public static native void mixChannels(@ByVal MatVector matVector, @ByVal MatVector matVector2, @StdVector IntPointer intPointer);

    @Namespace("cv")
    public static native void mixChannels(@ByVal MatVector matVector, @ByVal MatVector matVector2, @Const IntPointer intPointer, @Cast({"size_t"}) long j);

    @Namespace("cv")
    public static native void mixChannels(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @StdVector IntBuffer intBuffer);

    @Namespace("cv")
    public static native void mixChannels(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @Const IntBuffer intBuffer, @Cast({"size_t"}) long j);

    @Namespace("cv::hal")
    public static native void mul16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void mul8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    public static native void mulSpectrums(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void mulSpectrums(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void mulSpectrums(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void mulSpectrums(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void mulSpectrums(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    public static native void mulSpectrums(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void mulTransposed(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void mulTransposed(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, double d, int i);

    @Namespace("cv")
    public static native void mulTransposed(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void mulTransposed(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, double d, int i);

    @Namespace("cv")
    public static native void mulTransposed(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void mulTransposed(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool"}) boolean z, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, double d, int i);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(double d, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(@ByRef @Const Mat mat, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(@ByRef @Const MatExpr matExpr, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(@ByRef @Const MatExpr matExpr, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator *"})
    public static native MatExpr multiply(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2);

    @Namespace("cv")
    public static native void multiply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void multiply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i);

    @Namespace("cv")
    public static native void multiply(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void multiply(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i);

    @Namespace("cv")
    public static native void multiply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void multiply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i);

    @Namespace("cv")
    @ByRef
    @Name({"operator *="})
    public static native Mat multiplyPut(@ByRef Mat mat, double d);

    @Namespace("cv")
    @ByRef
    @Name({"operator *="})
    public static native Mat multiplyPut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    public static Mat noArray() {
        return null;
    }

    @Namespace("cv")
    public static native double norm(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native double norm(@ByVal GpuMat gpuMat, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @Namespace("cv")
    public static native double norm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native double norm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native double norm(@ByVal Mat mat);

    @Namespace("cv")
    public static native double norm(@ByVal Mat mat, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @Namespace("cv")
    public static native double norm(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native double norm(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native double norm(@ByRef @Const SparseMat sparseMat, int i);

    @Namespace("cv")
    public static native double norm(@ByVal UMat uMat);

    @Namespace("cv")
    public static native double norm(@ByVal UMat uMat, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @Namespace("cv")
    public static native double norm(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native double norm(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) ByteBuffer byteBuffer, int i);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) ByteBuffer byteBuffer, int i, int i2);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, int i);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) BytePointer bytePointer, int i);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) BytePointer bytePointer, int i, int i2);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"const uchar*"}) BytePointer bytePointer2, int i);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"const uchar*"}) BytePointer bytePointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) byte[] bArr, int i);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) byte[] bArr, int i, int i2);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) byte[] bArr, @Cast({"const uchar*"}) byte[] bArr2, int i);

    @Namespace("cv::hal")
    public static native int normHamming(@Cast({"const uchar*"}) byte[] bArr, @Cast({"const uchar*"}) byte[] bArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native float normL1_(@Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native float normL1_(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native float normL1_(@Const float[] fArr, @Const float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native int normL1_(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, int i);

    @Namespace("cv::hal")
    public static native int normL1_(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"const uchar*"}) BytePointer bytePointer2, int i);

    @Namespace("cv::hal")
    public static native int normL1_(@Cast({"const uchar*"}) byte[] bArr, @Cast({"const uchar*"}) byte[] bArr2, int i);

    @Namespace("cv::hal")
    public static native float normL2Sqr_(@Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native float normL2Sqr_(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native float normL2Sqr_(@Const float[] fArr, @Const float[] fArr2, int i);

    @Namespace("cv")
    public static native void normalize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void normalize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i, int i2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native void normalize(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void normalize(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i, int i2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native void normalize(@ByRef @Const SparseMat sparseMat, @ByRef SparseMat sparseMat2, double d, int i);

    @Namespace("cv")
    public static native void normalize(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void normalize(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i, int i2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    @ByVal
    @Name({"operator ~"})
    public static native MatExpr not(@ByRef @Const Mat mat);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !"})
    public static native boolean not(@Cast({"const cv::AccessFlag"}) int i);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !"})
    public static native boolean not(@ByRef @Const Range range);

    @Namespace("cv::hal")
    public static native void not8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void not8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void not8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    @ByVal
    @Name({"operator !="})
    public static native MatExpr notEquals(double d, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator !="})
    public static native MatExpr notEquals(@ByRef @Const Mat mat, double d);

    @Namespace("cv")
    @ByVal
    @Name({"operator !="})
    public static native MatExpr notEquals(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !="})
    public static native boolean notEquals(@Cast({"const cv::AccessFlag"}) int i, int i2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !="})
    public static native boolean notEquals(@ByRef @Const FileNodeIterator fileNodeIterator, @ByRef @Const FileNodeIterator fileNodeIterator2);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !="})
    public static native boolean notEquals(@ByRef @Const Range range, @ByRef @Const Range range2);

    @Namespace("cv")
    @Cast({"cv::AccessFlag"})
    @Name({"operator |"})
    public static native int or(@Cast({"const cv::AccessFlag"}) int i, @Cast({"const cv::AccessFlag"}) int i2);

    @Namespace("cv")
    @ByVal
    @Name({"operator |"})
    public static native MatExpr or(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator |"})
    public static native MatExpr or(@ByRef @Const Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator |"})
    public static native MatExpr or(@ByRef @Const Scalar scalar, @ByRef @Const Mat mat);

    @Namespace("cv::hal")
    public static native void or8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void or8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void or8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator |="})
    @Namespace("cv")
    public static native IntBuffer orPut(@ByRef @Cast({"cv::AccessFlag*"}) IntBuffer intBuffer, @Cast({"const cv::AccessFlag"}) int i);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator |="})
    @Namespace("cv")
    public static native IntPointer orPut(@ByRef @Cast({"cv::AccessFlag*"}) IntPointer intPointer, @Cast({"const cv::AccessFlag"}) int i);

    @Namespace("cv")
    @ByRef
    @Name({"operator |="})
    public static native Mat orPut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByRef
    @Name({"operator |="})
    public static native Mat orPut(@ByRef Mat mat, @ByRef @Const Scalar scalar);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator |="})
    @Namespace("cv")
    public static native int[] orPut(@ByRef @Cast({"cv::AccessFlag*"}) int[] iArr, @Cast({"const cv::AccessFlag"}) int i);

    @Namespace("cv")
    public static native void parallel_for_(@ByRef @Const Range range, @ByRef @Const ParallelLoopBody parallelLoopBody);

    @Namespace("cv")
    public static native void parallel_for_(@ByRef @Const Range range, @ByRef @Const ParallelLoopBody parallelLoopBody, double d);

    @Namespace("cv")
    public static native void parallel_for_(@ByRef @Const Range range, @ByVal opencv_core.Functor functor);

    @Namespace("cv")
    public static native void parallel_for_(@ByRef @Const Range range, @ByVal opencv_core.Functor functor, double d);

    @Namespace("cv")
    public static native void patchNaNs(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void patchNaNs(@ByVal GpuMat gpuMat, double d);

    @Namespace("cv")
    public static native void patchNaNs(@ByVal Mat mat);

    @Namespace("cv")
    public static native void patchNaNs(@ByVal Mat mat, double d);

    @Namespace("cv")
    public static native void patchNaNs(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void patchNaNs(@ByVal UMat uMat, double d);

    @Namespace("cv")
    public static native void perspectiveTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void perspectiveTransform(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void perspectiveTransform(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void phase(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void phase(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void phase(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void phase(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void phase(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void phase(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void polarToCart(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void polarToCart(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void polarToCart(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void polarToCart(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void polarToCart(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void polarToCart(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void pow(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void pow(@ByVal Mat mat, double d, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void pow(@ByVal UMat uMat, double d, @ByVal UMat uMat2);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidth(@ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidth(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidth(@ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidth(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidth(@ByVal UMat uMat);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidth(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat9, @Cast({"cv::ocl::OclVectorStrategy"}) int i);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidthMax(@ByVal GpuMat gpuMat);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidthMax(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat9);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidthMax(@ByVal Mat mat);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidthMax(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat9);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidthMax(@ByVal UMat uMat);

    @Namespace("cv::ocl")
    public static native int predictOptimalVectorWidthMax(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat7, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat8, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat9);

    @Namespace("cv")
    public static native int print(@opencv_core.Ptr Formatted formatted);

    @Namespace("cv")
    public static native int print(@opencv_core.Ptr Formatted formatted, @Cast({"FILE*"}) Pointer pointer);

    @Namespace("cv")
    public static native int print(@ByRef @Const Mat mat);

    @Namespace("cv")
    public static native int print(@ByRef @Const Mat mat, @Cast({"FILE*"}) Pointer pointer);

    @Namespace("cv")
    public static native int print(@ByRef @Const UMat uMat);

    @Namespace("cv")
    public static native int print(@ByRef @Const UMat uMat, @Cast({"FILE*"}) Pointer pointer);

    @Namespace("cv::cuda")
    public static native void printCudaDeviceInfo(int i);

    @Namespace("cv::cuda")
    public static native void printShortCudaDeviceInfo(int i);

    @Namespace("cv")
    public static native void randShuffle(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void randShuffle(@ByVal GpuMat gpuMat, double d, RNG rng);

    @Namespace("cv")
    public static native void randShuffle(@ByVal Mat mat);

    @Namespace("cv")
    public static native void randShuffle(@ByVal Mat mat, double d, RNG rng);

    @Namespace("cv")
    public static native void randShuffle(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void randShuffle(@ByVal UMat uMat, double d, RNG rng);

    @Namespace("cv")
    public static native void randn(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void randn(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void randn(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void randu(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void randu(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void randu(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"uchar*"}) ByteBuffer byteBuffer, @Cast({"uchar"}) byte b);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef DoubleBuffer doubleBuffer, double d);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef FloatBuffer floatBuffer, float f);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef IntBuffer intBuffer, int i);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"ushort*"}) ShortBuffer shortBuffer, @Cast({"ushort"}) short s);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"bool*"}) BoolPointer boolPointer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"uchar*"}) BytePointer bytePointer, @Cast({"uchar"}) byte b);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @StdString BytePointer bytePointer, @StdString String str);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @StdString BytePointer bytePointer, @StdString BytePointer bytePointer2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef DoublePointer doublePointer, double d);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef FloatPointer floatPointer, float f);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef IntPointer intPointer, int i);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"ushort*"}) ShortPointer shortPointer, @Cast({"ushort"}) short s);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef DMatch dMatch, @ByRef @Const DMatch dMatch2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef DMatchVector dMatchVector);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef DMatchVector dMatchVector, @ByRef @Const DMatchVector dMatchVector2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef KeyPoint keyPoint, @ByRef @Const KeyPoint keyPoint2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef KeyPointVector keyPointVector);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef KeyPointVector keyPointVector, @ByRef @Const KeyPointVector keyPointVector2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef Mat mat);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef Mat mat, @ByRef(nullValue = "cv::Mat()") @Const Mat mat2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef Range range, @ByRef @Const Range range2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef SparseMat sparseMat);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef SparseMat sparseMat, @ByRef(nullValue = "cv::SparseMat()") @Const SparseMat sparseMat2);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"uchar*"}) byte[] bArr, @Cast({"uchar"}) byte b);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef double[] dArr, double d);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef float[] fArr, float f);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef int[] iArr, int i);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"ushort*"}) short[] sArr, @Cast({"ushort"}) short s);

    @Namespace("cv")
    public static native void read(@ByRef @Const FileNode fileNode, @ByRef @Cast({"bool*"}) boolean[] zArr, @Cast({"bool"}) boolean z);

    @Namespace("cv::hal")
    public static native void recip16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void recip8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    public static native ErrorCallback redirectError(ErrorCallback errorCallback);

    @Namespace("cv")
    public static native ErrorCallback redirectError(ErrorCallback errorCallback, Pointer pointer, @ByPtrPtr @Cast({"void**"}) Pointer pointer2);

    @Namespace("cv")
    public static native ErrorCallback redirectError(ErrorCallback errorCallback, Pointer pointer, @Cast({"void**"}) PointerPointer pointerPointer);

    @Namespace("cv")
    public static native void reduce(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void reduce(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void reduce(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void reduce(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void reduce(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void reduce(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv::cuda")
    public static native void registerPageLocked(@ByRef Mat mat);

    @Namespace("cv")
    @ByVal
    public static native Mat repeat(@ByRef @Const Mat mat, int i, int i2);

    @Namespace("cv")
    public static native void repeat(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void repeat(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void repeat(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2);

    @Namespace("cv::cuda")
    public static native void resetDevice();

    @Namespace("cv::instr")
    public static native void resetTrace();

    @Namespace("cv")
    public static native void rotate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void rotate(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void rotate(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native int roundUp(int i, @Cast({"unsigned int"}) int i2);

    @Namespace("cv")
    @Cast({"size_t"})
    public static native long roundUp(@Cast({"size_t"}) long j, @Cast({"unsigned int"}) int i);

    @Namespace("cv")
    public static native void scaleAdd(@ByVal GpuMat gpuMat, double d, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void scaleAdd(@ByVal Mat mat, double d, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void scaleAdd(@ByVal UMat uMat, double d, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(double d);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(float f);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Cast({"schar"})
    @Name({"saturate_cast<schar>"})
    public static native byte scharSaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv")
    public static native void seqInsertSlice(CvSeq cvSeq, int i, @Const CvArr cvArr);

    @Namespace("cv")
    public static native void seqPop(CvSeq cvSeq);

    @Namespace("cv")
    public static native void seqPop(CvSeq cvSeq, Pointer pointer);

    @Namespace("cv")
    public static native void seqPopFront(CvSeq cvSeq);

    @Namespace("cv")
    public static native void seqPopFront(CvSeq cvSeq, Pointer pointer);

    @Namespace("cv")
    @Cast({"schar*"})
    public static native BytePointer seqPush(CvSeq cvSeq);

    @Namespace("cv")
    @Cast({"schar*"})
    public static native BytePointer seqPush(CvSeq cvSeq, @Const Pointer pointer);

    @Namespace("cv")
    @Cast({"schar*"})
    public static native BytePointer seqPushFront(CvSeq cvSeq);

    @Namespace("cv")
    @Cast({"schar*"})
    public static native BytePointer seqPushFront(CvSeq cvSeq, @Const Pointer pointer);

    @Namespace("cv")
    public static native void seqRemove(CvSeq cvSeq, int i);

    @Namespace("cv")
    public static native void seqRemoveSlice(CvSeq cvSeq, @ByVal CvSlice cvSlice);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean setBreakOnError(@Cast({"bool"}) boolean z);

    @Namespace("cv::cuda")
    public static native void setBufferPoolConfig(int i, @Cast({"size_t"}) long j, int i2);

    @Namespace("cv::cuda")
    public static native void setBufferPoolUsage(@Cast({"bool"}) boolean z);

    @Namespace("cv::cuda")
    public static native void setDevice(int i);

    @Namespace("cv::instr")
    public static native void setFlags(@Cast({"cv::instr::FLAGS"}) int i);

    @Namespace("cv")
    public static native void setIdentity(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void setIdentity(@ByVal GpuMat gpuMat, @ByRef(nullValue = "cv::Scalar(1)") @Const Scalar scalar);

    @Namespace("cv")
    public static native void setIdentity(@ByVal Mat mat);

    @Namespace("cv")
    public static native void setIdentity(@ByVal Mat mat, @ByRef(nullValue = "cv::Scalar(1)") @Const Scalar scalar);

    @Namespace("cv")
    public static native void setIdentity(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void setIdentity(@ByVal UMat uMat, @ByRef(nullValue = "cv::Scalar(1)") @Const Scalar scalar);

    @Namespace("cv::ipp")
    public static native void setIppStatus(int i);

    @Namespace("cv::ipp")
    public static native void setIppStatus(int i, String str, String str2, int i2);

    @Namespace("cv::ipp")
    public static native void setIppStatus(int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i2);

    @Namespace("cv")
    public static native void setNumThreads(int i);

    @Namespace("cv")
    public static native void setRNGSeed(int i);

    @Namespace("cv::ipp")
    public static native void setUseIPP(@Cast({"bool"}) boolean z);

    @Namespace("cv::ipp")
    public static native void setUseIPP_NE(@Cast({"bool"}) boolean z);

    @Namespace("cv::ipp")
    public static native void setUseIPP_NotExact(@Cast({"bool"}) boolean z);

    @Namespace("cv::instr")
    public static native void setUseInstrumentation(@Cast({"bool"}) boolean z);

    @Namespace("cv::ocl")
    public static native void setUseOpenCL(@Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void setUseOptimized(@Cast({"bool"}) boolean z);

    @Namespace("cv")
    @Name({"operator <<"})
    @opencv_core.Str
    public static native String shiftLeft(@opencv_core.Str String str, @opencv_core.Ptr Formatted formatted);

    @Namespace("cv")
    @Name({"operator <<"})
    @opencv_core.Str
    public static native String shiftLeft(@opencv_core.Str String str, @ByRef @Const Mat mat);

    @Namespace("cv")
    @Name({"operator <<"})
    @opencv_core.Str
    public static native BytePointer shiftLeft(@opencv_core.Str BytePointer bytePointer, @opencv_core.Ptr Formatted formatted);

    @Namespace("cv")
    @Name({"operator <<"})
    @opencv_core.Str
    public static native BytePointer shiftLeft(@opencv_core.Str BytePointer bytePointer, @ByRef @Const Mat mat);

    @ByRef
    @Cast({"std::ostream*"})
    @Name({"operator <<"})
    @Namespace("cv")
    public static native Pointer shiftLeft(@ByRef @Cast({"std::ostream*"}) Pointer pointer, @ByRef @Const TickMeter tickMeter);

    @Namespace("cv")
    @ByRef
    @Name({"operator <<"})
    public static native FileStorage shiftLeft(@ByRef FileStorage fileStorage, @opencv_core.Str String str);

    @Namespace("cv")
    @ByRef
    @Name({"operator <<"})
    public static native FileStorage shiftLeft(@ByRef FileStorage fileStorage, @Cast({"char*"}) ByteBuffer byteBuffer);

    @Namespace("cv")
    @ByRef
    @Name({"operator <<"})
    public static native FileStorage shiftLeft(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    @Namespace("cv")
    @ByRef
    @Name({"operator <<"})
    public static native FileStorage shiftLeft(@ByRef FileStorage fileStorage, @Cast({"char*"}) byte[] bArr);

    @Namespace("cv")
    @Name({"operator >>"})
    public static native void shiftRight(@ByRef @Const FileNode fileNode, @ByRef DMatch dMatch);

    @Namespace("cv")
    @Name({"operator >>"})
    public static native void shiftRight(@ByRef @Const FileNode fileNode, @ByRef DMatchVector dMatchVector);

    @Namespace("cv")
    @Name({"operator >>"})
    public static native void shiftRight(@ByRef @Const FileNode fileNode, @ByRef KeyPoint keyPoint);

    @Namespace("cv")
    @Name({"operator >>"})
    public static native void shiftRight(@ByRef @Const FileNode fileNode, @ByRef KeyPointVector keyPointVector);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(double d);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(float f);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Name({"saturate_cast<short>"})
    public static native short shortSaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solve(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solve(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solve(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solve(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solve(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean solve(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    public static native int solveCubic(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native int solveCubic(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native int solveCubic(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native int solveLP(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native int solveLP(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native int solveLP(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native double solvePoly(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native double solvePoly(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native double solvePoly(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native double solvePoly(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native double solvePoly(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native double solvePoly(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void sort(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void sort(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void sort(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void sortIdx(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void sortIdx(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void sortIdx(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void split(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv")
    public static native void split(@ByVal GpuMat gpuMat, @ByVal MatVector matVector);

    @Namespace("cv")
    public static native void split(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector);

    @Namespace("cv")
    public static native void split(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv")
    public static native void split(@ByRef @Const Mat mat, Mat mat2);

    @Namespace("cv")
    public static native void split(@ByVal Mat mat, @ByVal MatVector matVector);

    @Namespace("cv")
    public static native void split(@ByVal Mat mat, @ByVal UMatVector uMatVector);

    @Namespace("cv")
    public static native void split(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv")
    public static native void split(@ByVal UMat uMat, @ByVal MatVector matVector);

    @Namespace("cv")
    public static native void split(@ByVal UMat uMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::hal")
    public static native void split16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @ByPtrPtr @Cast({"ushort**"}) ShortBuffer shortBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"ushort**"}) PointerPointer pointerPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void split16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @ByPtrPtr @Cast({"ushort**"}) ShortPointer shortPointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split16u(@Cast({"const ushort*"}) short[] sArr, @ByPtrPtr @Cast({"ushort**"}) short[] sArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split32s(@Const IntBuffer intBuffer, @ByPtrPtr IntBuffer intBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split32s(@Const IntPointer intPointer, @ByPtrPtr IntPointer intPointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split32s(@Const IntPointer intPointer, @Cast({"int**"}) PointerPointer pointerPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void split32s(@Const int[] iArr, @ByPtrPtr int[] iArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split64s(@Cast({"const int64*"}) LongBuffer longBuffer, @ByPtrPtr @Cast({"int64**"}) LongBuffer longBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split64s(@Cast({"const int64*"}) LongPointer longPointer, @ByPtrPtr @Cast({"int64**"}) LongPointer longPointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split64s(@Cast({"const int64*"}) LongPointer longPointer, @Cast({"int64**"}) PointerPointer pointerPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void split64s(@Cast({"const int64*"}) long[] jArr, @ByPtrPtr @Cast({"int64**"}) long[] jArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split8u(@Cast({"const uchar*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer2, int i, int i2);

    @Namespace("cv::hal")
    public static native void split8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"uchar**"}) PointerPointer pointerPointer, int i, int i2);

    @Namespace("cv::hal")
    public static native void split8u(@Cast({"const uchar*"}) byte[] bArr, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr2, int i, int i2);

    @Namespace("cv::hal")
    public static native void sqrt(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv")
    public static native void sqrt(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void sqrt(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void sqrt(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::hal")
    public static native void sqrt(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv::hal")
    public static native void sqrt(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void sqrt32f(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt32f(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt32f(@Const float[] fArr, float[] fArr2, int i);

    @Namespace("cv::hal")
    public static native void sqrt64f(@Const DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt64f(@Const DoublePointer doublePointer, DoublePointer doublePointer2, int i);

    @Namespace("cv::hal")
    public static native void sqrt64f(@Const double[] dArr, double[] dArr2, int i);

    @Namespace("cv::hal")
    public static native void sub16s(@Const ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Const ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub16s(@Const ShortPointer shortPointer, @Cast({"size_t"}) long j, @Const ShortPointer shortPointer2, @Cast({"size_t"}) long j2, ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub16s(@Const short[] sArr, @Cast({"size_t"}) long j, @Const short[] sArr2, @Cast({"size_t"}) long j2, short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub16u(@Cast({"const ushort*"}) ShortBuffer shortBuffer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortBuffer shortBuffer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortBuffer shortBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub16u(@Cast({"const ushort*"}) ShortPointer shortPointer, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) ShortPointer shortPointer2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) ShortPointer shortPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub16u(@Cast({"const ushort*"}) short[] sArr, @Cast({"size_t"}) long j, @Cast({"const ushort*"}) short[] sArr2, @Cast({"size_t"}) long j2, @Cast({"ushort*"}) short[] sArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub32f(@Const FloatBuffer floatBuffer, @Cast({"size_t"}) long j, @Const FloatBuffer floatBuffer2, @Cast({"size_t"}) long j2, FloatBuffer floatBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub32f(@Const FloatPointer floatPointer, @Cast({"size_t"}) long j, @Const FloatPointer floatPointer2, @Cast({"size_t"}) long j2, FloatPointer floatPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub32f(@Const float[] fArr, @Cast({"size_t"}) long j, @Const float[] fArr2, @Cast({"size_t"}) long j2, float[] fArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub32s(@Const IntBuffer intBuffer, @Cast({"size_t"}) long j, @Const IntBuffer intBuffer2, @Cast({"size_t"}) long j2, IntBuffer intBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub32s(@Const IntPointer intPointer, @Cast({"size_t"}) long j, @Const IntPointer intPointer2, @Cast({"size_t"}) long j2, IntPointer intPointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub32s(@Const int[] iArr, @Cast({"size_t"}) long j, @Const int[] iArr2, @Cast({"size_t"}) long j2, int[] iArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub64f(@Const DoubleBuffer doubleBuffer, @Cast({"size_t"}) long j, @Const DoubleBuffer doubleBuffer2, @Cast({"size_t"}) long j2, DoubleBuffer doubleBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub64f(@Const DoublePointer doublePointer, @Cast({"size_t"}) long j, @Const DoublePointer doublePointer2, @Cast({"size_t"}) long j2, DoublePointer doublePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub64f(@Const double[] dArr, @Cast({"size_t"}) long j, @Const double[] dArr2, @Cast({"size_t"}) long j2, double[] dArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub8s(@Cast({"const schar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub8s(@Cast({"const schar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const schar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub8s(@Cast({"const schar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const schar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"schar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void sub8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv")
    @Cast({"ptrdiff_t"})
    @Name({"operator -"})
    public static native long subtract(@ByRef @Const FileNodeIterator fileNodeIterator, @ByRef @Const FileNodeIterator fileNodeIterator2);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const Mat mat, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const MatExpr matExpr, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const MatExpr matExpr, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const Scalar scalar, @ByRef @Const Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native MatExpr subtract(@ByRef @Const Scalar scalar, @ByRef @Const MatExpr matExpr);

    @Namespace("cv")
    @ByVal
    @Name({"operator -"})
    public static native Range subtract(@ByRef @Const Range range, int i);

    @Namespace("cv")
    public static native void subtract(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void subtract(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void subtract(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void subtract(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, int i);

    @Namespace("cv")
    public static native void subtract(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void subtract(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, int i);

    @Namespace("cv")
    @ByRef
    @Name({"operator -="})
    public static native Mat subtractPut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByRef
    @Name({"operator -="})
    public static native Mat subtractPut(@ByRef Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"sum"})
    public static native Scalar sumElems(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    @Name({"sum"})
    public static native Scalar sumElems(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    @Name({"sum"})
    public static native Scalar sumElems(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void swap(@ByRef Mat mat, @ByRef Mat mat2);

    @Namespace("cv")
    public static native void swap(@ByRef UMat uMat, @ByRef UMat uMat2);

    @Namespace("cv")
    @opencv_core.Str
    public static native String tempfile(String str);

    @Namespace("cv")
    @opencv_core.Str
    public static native BytePointer tempfile();

    @Namespace("cv")
    @opencv_core.Str
    public static native BytePointer tempfile(@Cast({"const char*"}) BytePointer bytePointer);

    @Namespace("cv")
    @ByRef
    public static native RNG theRNG();

    @Namespace("cv")
    @StdString
    public static native String toLowerCase(@StdString String str);

    @Namespace("cv")
    @StdString
    public static native BytePointer toLowerCase(@StdString BytePointer bytePointer);

    @Namespace("cv")
    @StdString
    public static native String toUpperCase(@StdString String str);

    @Namespace("cv")
    @StdString
    public static native BytePointer toUpperCase(@StdString BytePointer bytePointer);

    @Namespace("cv")
    @ByVal
    public static native Scalar trace(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native Scalar trace(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native Scalar trace(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void transform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void transform(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void transform(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void transpose(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void transpose(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void transpose(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ocl")
    @Cast({"const char*"})
    public static native BytePointer typeToStr(int i);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(double d);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(float f);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Cast({"uchar"})
    @Name({"saturate_cast<uchar>"})
    public static native byte ucharSaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(double d);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(float f);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Cast({"uint64"})
    @Name({"saturate_cast<uint64>"})
    public static native int uint64SaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv::cuda")
    public static native void unregisterPageLocked(@ByRef Mat mat);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(double d);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(float f);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Cast({"unsigned"})
    @Name({"saturate_cast<unsigned>"})
    public static native int unsignedSaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv::ipp")
    @Cast({"bool"})
    public static native boolean useIPP();

    @Namespace("cv::ipp")
    @Cast({"bool"})
    public static native boolean useIPP_NE();

    @Namespace("cv::ipp")
    @Cast({"bool"})
    public static native boolean useIPP_NotExact();

    @Namespace("cv::instr")
    @Cast({"bool"})
    public static native boolean useInstrumentation();

    @Namespace("cv::ocl")
    @Cast({"bool"})
    public static native boolean useOpenCL();

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean useOptimized();

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(@Cast({"uchar"}) byte b);

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(double d);

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(float f);

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(@Cast({"unsigned"}) int i);

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(@Cast({"int64"}) long j);

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(@ByVal float16_t float16_t);

    @Namespace("cv")
    @Cast({"ushort"})
    @Name({"saturate_cast<ushort>"})
    public static native short ushortSaturateCast(@Cast({"ushort"}) short s);

    @Namespace("cv")
    public static native void vconcat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void vconcat(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void vconcat(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void vconcat(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void vconcat(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void vconcat(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void vconcat(@Const Mat mat, @Cast({"size_t"}) long j, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void vconcat(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void vconcat(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void vconcat(@ByVal MatVector matVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void vconcat(@ByVal MatVector matVector, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void vconcat(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void vconcat(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void vconcat(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    @Namespace("cv")
    public static native void vconcat(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    @Namespace("cv::ocl")
    @Cast({"const char*"})
    public static native BytePointer vecopTypeToStr(int i);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, double d);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, float f);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, int i);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, double d);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, float f);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, int i);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @opencv_core.Str String str2);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const DMatch dMatch);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const DMatchVector dMatchVector);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const KeyPoint keyPoint);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const KeyPointVector keyPointVector);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const Mat mat);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const Range range);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str, @ByRef @Const SparseMat sparseMat);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, double d);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, int i);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const DMatch dMatch);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const DMatchVector dMatchVector);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const KeyPoint keyPoint);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const KeyPointVector keyPointVector);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const Mat mat);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const Range range);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, @ByRef @Const SparseMat sparseMat);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @ByRef @Const DMatch dMatch);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @ByRef @Const DMatchVector dMatchVector);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @ByRef @Const KeyPoint keyPoint);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @ByRef @Const KeyPointVector keyPointVector);

    @Namespace("cv")
    public static native void write(@ByRef FileStorage fileStorage, @ByRef @Const Range range);

    @Namespace("cv")
    public static native void writeScalar(@ByRef FileStorage fileStorage, double d);

    @Namespace("cv")
    public static native void writeScalar(@ByRef FileStorage fileStorage, float f);

    @Namespace("cv")
    public static native void writeScalar(@ByRef FileStorage fileStorage, int i);

    @Namespace("cv")
    public static native void writeScalar(@ByRef FileStorage fileStorage, @opencv_core.Str String str);

    @Namespace("cv")
    public static native void writeScalar(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    @Namespace("cv")
    @Cast({"cv::AccessFlag"})
    @Name({"operator ^"})
    public static native int xor(@Cast({"const cv::AccessFlag"}) int i, @Cast({"const cv::AccessFlag"}) int i2);

    @Namespace("cv")
    @ByVal
    @Name({"operator ^"})
    public static native MatExpr xor(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByVal
    @Name({"operator ^"})
    public static native MatExpr xor(@ByRef @Const Mat mat, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    @Name({"operator ^"})
    public static native MatExpr xor(@ByRef @Const Scalar scalar, @ByRef @Const Mat mat);

    @Namespace("cv::hal")
    public static native void xor8u(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) ByteBuffer byteBuffer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void xor8u(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) BytePointer bytePointer3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @Namespace("cv::hal")
    public static native void xor8u(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2, @Cast({"uchar*"}) byte[] bArr3, @Cast({"size_t"}) long j3, int i, int i2, Pointer pointer);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator ^="})
    @Namespace("cv")
    public static native IntBuffer xorPut(@ByRef @Cast({"cv::AccessFlag*"}) IntBuffer intBuffer, @Cast({"const cv::AccessFlag"}) int i);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator ^="})
    @Namespace("cv")
    public static native IntPointer xorPut(@ByRef @Cast({"cv::AccessFlag*"}) IntPointer intPointer, @Cast({"const cv::AccessFlag"}) int i);

    @Namespace("cv")
    @ByRef
    @Name({"operator ^="})
    public static native Mat xorPut(@ByRef Mat mat, @ByRef @Const Mat mat2);

    @Namespace("cv")
    @ByRef
    @Name({"operator ^="})
    public static native Mat xorPut(@ByRef Mat mat, @ByRef @Const Scalar scalar);

    @ByRef
    @Cast({"cv::AccessFlag*"})
    @Name({"operator ^="})
    @Namespace("cv")
    public static native int[] xorPut(@ByRef @Cast({"cv::AccessFlag*"}) int[] iArr, @Cast({"const cv::AccessFlag"}) int i);

    static {
        Loader.load();
        int CV_MAKETYPE = CV_MAKETYPE(0, 1);
        CV_8UC1 = CV_MAKETYPE;
        int CV_MAKETYPE2 = CV_MAKETYPE(4, 1);
        CV_32SC1 = CV_MAKETYPE2;
        int CV_MAKETYPE3 = CV_MAKETYPE(4, 2);
        CV_32SC2 = CV_MAKETYPE3;
        int CV_MAKETYPE4 = CV_MAKETYPE(5, 3);
        CV_32FC3 = CV_MAKETYPE4;
        CV_SEQ_ELTYPE_POINT = CV_MAKETYPE3;
        CV_SEQ_ELTYPE_CODE = CV_MAKETYPE;
        CV_SEQ_ELTYPE_INDEX = CV_MAKETYPE2;
        CV_SEQ_ELTYPE_POINT3D = CV_MAKETYPE4;
        CV_SEQ_POINT_SET = CV_MAKETYPE3 | 0;
        CV_SEQ_POINT3D_SET = CV_MAKETYPE4 | 0;
        int i = CV_MAKETYPE3 | 4096;
        CV_SEQ_POLYLINE = i;
        int i2 = i | 16384;
        CV_SEQ_POLYGON = i2;
        CV_SEQ_CONTOUR = i2;
        CV_SEQ_SIMPLE_POLYGON = i2 | 0;
        int i3 = CV_MAKETYPE | 4096;
        CV_SEQ_CHAIN = i3;
        CV_SEQ_CHAIN_CONTOUR = i3 | 16384;
        CV_SEQ_INDEX = 0 | CV_MAKETYPE2;
    }
}
