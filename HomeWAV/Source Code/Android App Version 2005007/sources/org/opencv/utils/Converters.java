package org.opencv.utils;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.Rect;
import org.opencv.core.Rect2d;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;

public class Converters {
    public static Mat vector_Point_to_Mat(List<Point> list) {
        return vector_Point_to_Mat(list, 4);
    }

    public static Mat vector_Point2f_to_Mat(List<Point> list) {
        return vector_Point_to_Mat(list, 5);
    }

    public static Mat vector_Point2d_to_Mat(List<Point> list) {
        return vector_Point_to_Mat(list, 6);
    }

    public static Mat vector_Point_to_Mat(List<Point> list, int i) {
        int i2;
        if (list != null) {
            i2 = list.size();
        } else {
            i2 = 0;
        }
        if (i2 <= 0) {
            return new Mat();
        }
        if (i == 4) {
            Mat mat = new Mat(i2, 1, CvType.CV_32SC2);
            int[] iArr = new int[(i2 * 2)];
            for (int i3 = 0; i3 < i2; i3++) {
                Point point = list.get(i3);
                int i4 = i3 * 2;
                iArr[i4] = (int) point.x;
                iArr[i4 + 1] = (int) point.y;
            }
            mat.put(0, 0, iArr);
            return mat;
        } else if (i == 5) {
            Mat mat2 = new Mat(i2, 1, CvType.CV_32FC2);
            float[] fArr = new float[(i2 * 2)];
            for (int i5 = 0; i5 < i2; i5++) {
                Point point2 = list.get(i5);
                int i6 = i5 * 2;
                fArr[i6] = (float) point2.x;
                fArr[i6 + 1] = (float) point2.y;
            }
            mat2.put(0, 0, fArr);
            return mat2;
        } else if (i == 6) {
            Mat mat3 = new Mat(i2, 1, CvType.CV_64FC2);
            double[] dArr = new double[(i2 * 2)];
            for (int i7 = 0; i7 < i2; i7++) {
                Point point3 = list.get(i7);
                int i8 = i7 * 2;
                dArr[i8] = point3.x;
                dArr[i8 + 1] = point3.y;
            }
            mat3.put(0, 0, dArr);
            return mat3;
        } else {
            throw new IllegalArgumentException("'typeDepth' can be CV_32S, CV_32F or CV_64F");
        }
    }

    public static Mat vector_Point3i_to_Mat(List<Point3> list) {
        return vector_Point3_to_Mat(list, 4);
    }

    public static Mat vector_Point3f_to_Mat(List<Point3> list) {
        return vector_Point3_to_Mat(list, 5);
    }

    public static Mat vector_Point3d_to_Mat(List<Point3> list) {
        return vector_Point3_to_Mat(list, 6);
    }

    public static Mat vector_Point3_to_Mat(List<Point3> list, int i) {
        int i2;
        if (list != null) {
            i2 = list.size();
        } else {
            i2 = 0;
        }
        if (i2 <= 0) {
            return new Mat();
        }
        if (i == 4) {
            Mat mat = new Mat(i2, 1, CvType.CV_32SC3);
            int[] iArr = new int[(i2 * 3)];
            for (int i3 = 0; i3 < i2; i3++) {
                Point3 point3 = list.get(i3);
                int i4 = i3 * 3;
                iArr[i4] = (int) point3.x;
                iArr[i4 + 1] = (int) point3.y;
                iArr[i4 + 2] = (int) point3.z;
            }
            mat.put(0, 0, iArr);
            return mat;
        } else if (i == 5) {
            Mat mat2 = new Mat(i2, 1, CvType.CV_32FC3);
            float[] fArr = new float[(i2 * 3)];
            for (int i5 = 0; i5 < i2; i5++) {
                Point3 point32 = list.get(i5);
                int i6 = i5 * 3;
                fArr[i6] = (float) point32.x;
                fArr[i6 + 1] = (float) point32.y;
                fArr[i6 + 2] = (float) point32.z;
            }
            mat2.put(0, 0, fArr);
            return mat2;
        } else if (i == 6) {
            Mat mat3 = new Mat(i2, 1, CvType.CV_64FC3);
            double[] dArr = new double[(i2 * 3)];
            for (int i7 = 0; i7 < i2; i7++) {
                Point3 point33 = list.get(i7);
                int i8 = i7 * 3;
                dArr[i8] = point33.x;
                dArr[i8 + 1] = point33.y;
                dArr[i8 + 2] = point33.z;
            }
            mat3.put(0, 0, dArr);
            return mat3;
        } else {
            throw new IllegalArgumentException("'typeDepth' can be CV_32S, CV_32F or CV_64F");
        }
    }

    public static void Mat_to_vector_Point2f(Mat mat, List<Point> list) {
        Mat_to_vector_Point(mat, list);
    }

    public static void Mat_to_vector_Point2d(Mat mat, List<Point> list) {
        Mat_to_vector_Point(mat, list);
    }

    public static void Mat_to_vector_Point(Mat mat, List<Point> list) {
        if (list != null) {
            int rows = mat.rows();
            int type = mat.type();
            if (mat.cols() == 1) {
                list.clear();
                int i = 0;
                if (type == CvType.CV_32SC2) {
                    int[] iArr = new int[(rows * 2)];
                    mat.get(0, 0, iArr);
                    while (i < rows) {
                        int i2 = i * 2;
                        list.add(new Point((double) iArr[i2], (double) iArr[i2 + 1]));
                        i++;
                    }
                } else if (type == CvType.CV_32FC2) {
                    float[] fArr = new float[(rows * 2)];
                    mat.get(0, 0, fArr);
                    while (i < rows) {
                        int i3 = i * 2;
                        list.add(new Point((double) fArr[i3], (double) fArr[i3 + 1]));
                        i++;
                    }
                } else if (type == CvType.CV_64FC2) {
                    double[] dArr = new double[(rows * 2)];
                    mat.get(0, 0, dArr);
                    while (i < rows) {
                        int i4 = i * 2;
                        list.add(new Point(dArr[i4], dArr[i4 + 1]));
                        i++;
                    }
                } else {
                    throw new IllegalArgumentException("Input Mat should be of CV_32SC2, CV_32FC2 or CV_64FC2 type\n" + mat);
                }
            } else {
                throw new IllegalArgumentException("Input Mat should have one column\n" + mat);
            }
        } else {
            throw new IllegalArgumentException("Output List can't be null");
        }
    }

    public static void Mat_to_vector_Point3i(Mat mat, List<Point3> list) {
        Mat_to_vector_Point3(mat, list);
    }

    public static void Mat_to_vector_Point3f(Mat mat, List<Point3> list) {
        Mat_to_vector_Point3(mat, list);
    }

    public static void Mat_to_vector_Point3d(Mat mat, List<Point3> list) {
        Mat_to_vector_Point3(mat, list);
    }

    public static void Mat_to_vector_Point3(Mat mat, List<Point3> list) {
        if (list != null) {
            int rows = mat.rows();
            int type = mat.type();
            if (mat.cols() == 1) {
                list.clear();
                int i = 0;
                if (type == CvType.CV_32SC3) {
                    int[] iArr = new int[(rows * 3)];
                    mat.get(0, 0, iArr);
                    while (i < rows) {
                        int i2 = i * 3;
                        list.add(new Point3((double) iArr[i2], (double) iArr[i2 + 1], (double) iArr[i2 + 2]));
                        i++;
                    }
                } else if (type == CvType.CV_32FC3) {
                    float[] fArr = new float[(rows * 3)];
                    mat.get(0, 0, fArr);
                    while (i < rows) {
                        int i3 = i * 3;
                        list.add(new Point3((double) fArr[i3], (double) fArr[i3 + 1], (double) fArr[i3 + 2]));
                        i++;
                    }
                } else if (type == CvType.CV_64FC3) {
                    double[] dArr = new double[(rows * 3)];
                    mat.get(0, 0, dArr);
                    while (i < rows) {
                        int i4 = i * 3;
                        list.add(new Point3(dArr[i4], dArr[i4 + 1], dArr[i4 + 2]));
                        i++;
                    }
                } else {
                    throw new IllegalArgumentException("Input Mat should be of CV_32SC3, CV_32FC3 or CV_64FC3 type\n" + mat);
                }
            } else {
                throw new IllegalArgumentException("Input Mat should have one column\n" + mat);
            }
        } else {
            throw new IllegalArgumentException("Output List can't be null");
        }
    }

    public static Mat vector_Mat_to_Mat(List<Mat> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_32SC2);
        int[] iArr = new int[(size * 2)];
        for (int i = 0; i < size; i++) {
            long j = list.get(i).nativeObj;
            int i2 = i * 2;
            iArr[i2] = (int) (j >> 32);
            iArr[i2 + 1] = (int) (j & -1);
        }
        mat.put(0, 0, iArr);
        return mat;
    }

    public static void Mat_to_vector_Mat(Mat mat, List<Mat> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_32SC2 == mat.type() && mat.cols() == 1) {
                list.clear();
                int[] iArr = new int[(rows * 2)];
                mat.get(0, 0, iArr);
                for (int i = 0; i < rows; i++) {
                    int i2 = i * 2;
                    list.add(new Mat((((long) iArr[i2]) << 32) | (((long) iArr[i2 + 1]) & 4294967295L)));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_32SC2 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("mats == null");
    }

    public static Mat vector_float_to_Mat(List<Float> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_32FC1);
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = list.get(i).floatValue();
        }
        mat.put(0, 0, fArr);
        return mat;
    }

    public static void Mat_to_vector_float(Mat mat, List<Float> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_32FC1 == mat.type() && mat.cols() == 1) {
                list.clear();
                float[] fArr = new float[rows];
                mat.get(0, 0, fArr);
                for (int i = 0; i < rows; i++) {
                    list.add(Float.valueOf(fArr[i]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_32FC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("fs == null");
    }

    public static Mat vector_uchar_to_Mat(List<Byte> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_8UC1);
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = list.get(i).byteValue();
        }
        mat.put(0, 0, bArr);
        return mat;
    }

    public static void Mat_to_vector_uchar(Mat mat, List<Byte> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_8UC1 == mat.type() && mat.cols() == 1) {
                list.clear();
                byte[] bArr = new byte[rows];
                mat.get(0, 0, bArr);
                for (int i = 0; i < rows; i++) {
                    list.add(Byte.valueOf(bArr[i]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_8UC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("Output List can't be null");
    }

    public static Mat vector_char_to_Mat(List<Byte> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_8SC1);
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = list.get(i).byteValue();
        }
        mat.put(0, 0, bArr);
        return mat;
    }

    public static Mat vector_int_to_Mat(List<Integer> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_32SC1);
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        mat.put(0, 0, iArr);
        return mat;
    }

    public static void Mat_to_vector_int(Mat mat, List<Integer> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_32SC1 == mat.type() && mat.cols() == 1) {
                list.clear();
                int[] iArr = new int[rows];
                mat.get(0, 0, iArr);
                for (int i = 0; i < rows; i++) {
                    list.add(Integer.valueOf(iArr[i]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_32SC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("is == null");
    }

    public static void Mat_to_vector_char(Mat mat, List<Byte> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_8SC1 == mat.type() && mat.cols() == 1) {
                list.clear();
                byte[] bArr = new byte[rows];
                mat.get(0, 0, bArr);
                for (int i = 0; i < rows; i++) {
                    list.add(Byte.valueOf(bArr[i]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_8SC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("Output List can't be null");
    }

    public static Mat vector_Rect_to_Mat(List<Rect> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_32SC4);
        int[] iArr = new int[(size * 4)];
        for (int i = 0; i < size; i++) {
            Rect rect = list.get(i);
            int i2 = i * 4;
            iArr[i2] = rect.x;
            iArr[i2 + 1] = rect.y;
            iArr[i2 + 2] = rect.width;
            iArr[i2 + 3] = rect.height;
        }
        mat.put(0, 0, iArr);
        return mat;
    }

    public static void Mat_to_vector_Rect(Mat mat, List<Rect> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_32SC4 == mat.type() && mat.cols() == 1) {
                list.clear();
                int[] iArr = new int[(rows * 4)];
                mat.get(0, 0, iArr);
                for (int i = 0; i < rows; i++) {
                    int i2 = i * 4;
                    list.add(new Rect(iArr[i2], iArr[i2 + 1], iArr[i2 + 2], iArr[i2 + 3]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_32SC4 != m.type() ||  m.rows()!=1\n" + mat);
        }
        throw new IllegalArgumentException("rs == null");
    }

    public static Mat vector_Rect2d_to_Mat(List<Rect2d> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_64FC4);
        double[] dArr = new double[(size * 4)];
        for (int i = 0; i < size; i++) {
            Rect2d rect2d = list.get(i);
            int i2 = i * 4;
            dArr[i2] = rect2d.x;
            dArr[i2 + 1] = rect2d.y;
            dArr[i2 + 2] = rect2d.width;
            dArr[i2 + 3] = rect2d.height;
        }
        mat.put(0, 0, dArr);
        return mat;
    }

    public static void Mat_to_vector_Rect2d(Mat mat, List<Rect2d> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_64FC4 == mat.type() && mat.cols() == 1) {
                list.clear();
                double[] dArr = new double[(rows * 4)];
                mat.get(0, 0, dArr);
                for (int i = 0; i < rows; i++) {
                    int i2 = i * 4;
                    list.add(new Rect2d(dArr[i2], dArr[i2 + 1], dArr[i2 + 2], dArr[i2 + 3]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_64FC4 != m.type() ||  m.rows()!=1\n" + mat);
        }
        throw new IllegalArgumentException("rs == null");
    }

    public static Mat vector_KeyPoint_to_Mat(List<KeyPoint> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_64FC(7));
        double[] dArr = new double[(size * 7)];
        for (int i = 0; i < size; i++) {
            KeyPoint keyPoint = list.get(i);
            int i2 = i * 7;
            dArr[i2] = keyPoint.pt.x;
            dArr[i2 + 1] = keyPoint.pt.y;
            dArr[i2 + 2] = (double) keyPoint.size;
            dArr[i2 + 3] = (double) keyPoint.angle;
            dArr[i2 + 4] = (double) keyPoint.response;
            dArr[i2 + 5] = (double) keyPoint.octave;
            dArr[i2 + 6] = (double) keyPoint.class_id;
        }
        mat.put(0, 0, dArr);
        return mat;
    }

    public static void Mat_to_vector_KeyPoint(Mat mat, List<KeyPoint> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_64FC(7) == mat.type() && mat.cols() == 1) {
                list.clear();
                double[] dArr = new double[(rows * 7)];
                mat.get(0, 0, dArr);
                for (int i = 0; i < rows; i++) {
                    int i2 = i * 7;
                    list.add(new KeyPoint((float) dArr[i2], (float) dArr[i2 + 1], (float) dArr[i2 + 2], (float) dArr[i2 + 3], (float) dArr[i2 + 4], (int) dArr[i2 + 5], (int) dArr[i2 + 6]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_64FC(7) != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("Output List can't be null");
    }

    public static Mat vector_vector_Point_to_Mat(List<MatOfPoint> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) <= 0) {
            return new Mat();
        }
        list2.addAll(list);
        return vector_Mat_to_Mat(list2);
    }

    public static void Mat_to_vector_vector_Point(Mat mat, List<MatOfPoint> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        } else if (mat != null) {
            ArrayList<Mat> arrayList = new ArrayList<>(mat.rows());
            Mat_to_vector_Mat(mat, arrayList);
            for (Mat mat2 : arrayList) {
                list.add(new MatOfPoint(mat2));
                mat2.release();
            }
            arrayList.clear();
        } else {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
    }

    public static void Mat_to_vector_vector_Point2f(Mat mat, List<MatOfPoint2f> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        } else if (mat != null) {
            ArrayList<Mat> arrayList = new ArrayList<>(mat.rows());
            Mat_to_vector_Mat(mat, arrayList);
            for (Mat mat2 : arrayList) {
                list.add(new MatOfPoint2f(mat2));
                mat2.release();
            }
            arrayList.clear();
        } else {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
    }

    public static Mat vector_vector_Point2f_to_Mat(List<MatOfPoint2f> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) <= 0) {
            return new Mat();
        }
        list2.addAll(list);
        return vector_Mat_to_Mat(list2);
    }

    public static void Mat_to_vector_vector_Point3f(Mat mat, List<MatOfPoint3f> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        } else if (mat != null) {
            ArrayList<Mat> arrayList = new ArrayList<>(mat.rows());
            Mat_to_vector_Mat(mat, arrayList);
            for (Mat mat2 : arrayList) {
                list.add(new MatOfPoint3f(mat2));
                mat2.release();
            }
            arrayList.clear();
        } else {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
    }

    public static Mat vector_vector_Point3f_to_Mat(List<MatOfPoint3f> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) <= 0) {
            return new Mat();
        }
        list2.addAll(list);
        return vector_Mat_to_Mat(list2);
    }

    public static Mat vector_vector_KeyPoint_to_Mat(List<MatOfKeyPoint> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) <= 0) {
            return new Mat();
        }
        list2.addAll(list);
        return vector_Mat_to_Mat(list2);
    }

    public static void Mat_to_vector_vector_KeyPoint(Mat mat, List<MatOfKeyPoint> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        } else if (mat != null) {
            ArrayList<Mat> arrayList = new ArrayList<>(mat.rows());
            Mat_to_vector_Mat(mat, arrayList);
            for (Mat mat2 : arrayList) {
                list.add(new MatOfKeyPoint(mat2));
                mat2.release();
            }
            arrayList.clear();
        } else {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
    }

    public static Mat vector_double_to_Mat(List<Double> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_64FC1);
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        mat.put(0, 0, dArr);
        return mat;
    }

    public static void Mat_to_vector_double(Mat mat, List<Double> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_64FC1 == mat.type() && mat.cols() == 1) {
                list.clear();
                double[] dArr = new double[rows];
                mat.get(0, 0, dArr);
                for (int i = 0; i < rows; i++) {
                    list.add(Double.valueOf(dArr[i]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_64FC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("ds == null");
    }

    public static Mat vector_DMatch_to_Mat(List<DMatch> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_64FC4);
        double[] dArr = new double[(size * 4)];
        for (int i = 0; i < size; i++) {
            DMatch dMatch = list.get(i);
            int i2 = i * 4;
            dArr[i2] = (double) dMatch.queryIdx;
            dArr[i2 + 1] = (double) dMatch.trainIdx;
            dArr[i2 + 2] = (double) dMatch.imgIdx;
            dArr[i2 + 3] = (double) dMatch.distance;
        }
        mat.put(0, 0, dArr);
        return mat;
    }

    public static void Mat_to_vector_DMatch(Mat mat, List<DMatch> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_64FC4 == mat.type() && mat.cols() == 1) {
                list.clear();
                double[] dArr = new double[(rows * 4)];
                mat.get(0, 0, dArr);
                for (int i = 0; i < rows; i++) {
                    int i2 = i * 4;
                    list.add(new DMatch((int) dArr[i2], (int) dArr[i2 + 1], (int) dArr[i2 + 2], (float) dArr[i2 + 3]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_64FC4 != m.type() ||  m.cols()!=1\n" + mat);
        }
        throw new IllegalArgumentException("Output List can't be null");
    }

    public static Mat vector_vector_DMatch_to_Mat(List<MatOfDMatch> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) <= 0) {
            return new Mat();
        }
        list2.addAll(list);
        return vector_Mat_to_Mat(list2);
    }

    public static void Mat_to_vector_vector_DMatch(Mat mat, List<MatOfDMatch> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        } else if (mat != null) {
            ArrayList<Mat> arrayList = new ArrayList<>(mat.rows());
            Mat_to_vector_Mat(mat, arrayList);
            list.clear();
            for (Mat mat2 : arrayList) {
                list.add(new MatOfDMatch(mat2));
                mat2.release();
            }
            arrayList.clear();
        } else {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
    }

    public static Mat vector_vector_char_to_Mat(List<MatOfByte> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) <= 0) {
            return new Mat();
        }
        list2.addAll(list);
        return vector_Mat_to_Mat(list2);
    }

    public static void Mat_to_vector_vector_char(Mat mat, List<List<Byte>> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        } else if (mat != null) {
            ArrayList<Mat> arrayList = new ArrayList<>(mat.rows());
            Mat_to_vector_Mat(mat, arrayList);
            for (Mat mat2 : arrayList) {
                ArrayList arrayList2 = new ArrayList();
                Mat_to_vector_char(mat2, arrayList2);
                list.add(arrayList2);
                mat2.release();
            }
            arrayList.clear();
        } else {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
    }

    public static Mat vector_RotatedRect_to_Mat(List<RotatedRect> list) {
        int size = list != null ? list.size() : 0;
        if (size <= 0) {
            return new Mat();
        }
        Mat mat = new Mat(size, 1, CvType.CV_32FC(5));
        float[] fArr = new float[(size * 5)];
        for (int i = 0; i < size; i++) {
            RotatedRect rotatedRect = list.get(i);
            int i2 = i * 5;
            fArr[i2] = (float) rotatedRect.center.x;
            fArr[i2 + 1] = (float) rotatedRect.center.y;
            fArr[i2 + 2] = (float) rotatedRect.size.width;
            fArr[i2 + 3] = (float) rotatedRect.size.height;
            fArr[i2 + 4] = (float) rotatedRect.angle;
        }
        mat.put(0, 0, fArr);
        return mat;
    }

    public static void Mat_to_vector_RotatedRect(Mat mat, List<RotatedRect> list) {
        if (list != null) {
            int rows = mat.rows();
            if (CvType.CV_32FC(5) == mat.type() && mat.cols() == 1) {
                list.clear();
                float[] fArr = new float[(rows * 5)];
                mat.get(0, 0, fArr);
                for (int i = 0; i < rows; i++) {
                    int i2 = i * 5;
                    list.add(new RotatedRect(new Point((double) fArr[i2], (double) fArr[i2 + 1]), new Size((double) fArr[i2 + 2], (double) fArr[i2 + 3]), (double) fArr[i2 + 4]));
                }
                return;
            }
            throw new IllegalArgumentException("CvType.CV_32FC5 != m.type() ||  m.rows()!=1\n" + mat);
        }
        throw new IllegalArgumentException("rs == null");
    }
}
