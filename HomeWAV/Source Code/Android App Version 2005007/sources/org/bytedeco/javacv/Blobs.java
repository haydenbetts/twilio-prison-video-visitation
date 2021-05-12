package org.bytedeco.javacv;

import java.lang.reflect.Array;

public class Blobs {
    public static int BLOBAREA = 3;
    static int BLOBCOLCOUNT = 2700;
    public static int BLOBCOLOR = 2;
    public static int BLOBDATACOUNT = 14;
    public static int BLOBLABEL = 0;
    public static int BLOBMAXX = 11;
    public static int BLOBMAXY = 13;
    public static int BLOBMINX = 10;
    public static int BLOBMINY = 12;
    public static int BLOBPARENT = 1;
    public static int BLOBPERIMETER = 4;
    static int BLOBROWCOUNT = 3500;
    public static int BLOBSUMX = 5;
    public static int BLOBSUMXX = 7;
    public static int BLOBSUMXY = 9;
    public static int BLOBSUMY = 6;
    public static int BLOBSUMYY = 8;
    static int BLOBTOTALCOUNT = ((3500 + 2700) * 5);
    public static int[] CondensationMap;
    public static int[][] LabelMat;
    public static int MaxLabel;
    public static double[][] RegionData;
    public static int[] SubsumedLabel;
    static double iField;
    static double[] iProperty;
    static double jField;
    static double[] jProperty;
    public int ColorA;
    public int ColorB;
    public int ColorC;
    public int ColorD;
    public int LabelA;
    public int LabelB;
    public int LabelC;
    public int LabelD;
    public int jcol;
    public int jrow;

    static {
        int[] iArr = new int[2];
        iArr[1] = 2700;
        iArr[0] = 3500;
        LabelMat = (int[][]) Array.newInstance(int.class, iArr);
        int i = BLOBTOTALCOUNT;
        int[] iArr2 = new int[2];
        iArr2[1] = BLOBDATACOUNT;
        iArr2[0] = i;
        RegionData = (double[][]) Array.newInstance(double.class, iArr2);
        int i2 = BLOBTOTALCOUNT;
        SubsumedLabel = new int[i2];
        CondensationMap = new int[i2];
    }

    public void PrintRegionData() {
        PrintRegionData(0, MaxLabel);
    }

    public void PrintRegionData(int i, int i2) {
        int i3 = i < 0 ? 0 : i;
        int i4 = MaxLabel;
        int i5 = i2;
        if (i5 <= i4) {
            i4 = i5;
        }
        if (i4 >= i3) {
            while (i3 <= i4) {
                double[] dArr = RegionData[i3];
                double d = dArr[BLOBAREA];
                double d2 = dArr[BLOBPERIMETER];
                double d3 = dArr[BLOBSUMX];
                double d4 = dArr[BLOBSUMY];
                double d5 = dArr[BLOBSUMXX];
                double d6 = dArr[BLOBSUMYY];
                int i6 = (int) dArr[BLOBMINX];
                int i7 = (int) dArr[BLOBMAXX];
                int i8 = i4;
                System.out.println((" " + i3 + ": L[" + ((int) dArr[BLOBLABEL]) + "] P[" + ((int) dArr[BLOBPARENT]) + "] C[" + ((int) dArr[BLOBCOLOR]) + "]") + (" AP[" + d + ", " + d2 + "]") + (" M1[" + d3 + ", " + d4 + "] M2[" + d5 + ", " + d6 + ", " + dArr[BLOBSUMXY] + "]") + (" MINMAX[" + i6 + ", " + i7 + ", " + ((int) dArr[BLOBMINY]) + ", " + ((int) dArr[BLOBMAXY]) + "]"));
                i3++;
                i4 = i8;
            }
            System.out.println();
        }
    }

    public static int NextRegion(int i, int i2, double d, double d2, int i3) {
        int i4;
        double d3 = (double) i;
        double d4 = (double) i2;
        if (d4 > 0.0d) {
            d4 = 1.0d;
        }
        int i5 = i3;
        while (true) {
            i4 = MaxLabel;
            if (i5 > i4) {
                break;
            }
            double[] dArr = RegionData[i5];
            double d5 = dArr[BLOBPARENT];
            double d6 = dArr[BLOBCOLOR];
            if ((d3 < 0.0d || d3 == d5) && (d4 < 0.0d || d4 == d6)) {
                int i6 = BLOBAREA;
                if (dArr[i6] >= d && dArr[i6] <= d2) {
                    break;
                }
            }
            i5++;
        }
        if (i5 > i4) {
            return -1;
        }
        return i5;
    }

    public static int PriorRegion(int i, int i2, double d, double d2, int i3) {
        double d3 = (double) i;
        double d4 = (double) i2;
        if (d4 > 0.0d) {
            d4 = 1.0d;
        }
        int i4 = i3;
        while (i4 >= 0) {
            double[] dArr = RegionData[i4];
            double d5 = dArr[BLOBPARENT];
            double d6 = dArr[BLOBCOLOR];
            if ((d3 < 0.0d || d3 == d5) && (d4 < 0.0d || d4 == d6)) {
                int i5 = BLOBAREA;
                if (dArr[i5] >= d && dArr[i5] <= d2) {
                    break;
                }
            }
            i4--;
        }
        if (i4 < 0) {
            return -1;
        }
        return i4;
    }

    public void ResetRegion(int i) {
        double[][] dArr = RegionData;
        double[] dArr2 = dArr[i];
        int i2 = BLOBLABEL;
        int i3 = BLOBPARENT;
        int i4 = BLOBCOLOR;
        int i5 = BLOBAREA;
        int i6 = BLOBPERIMETER;
        int i7 = BLOBSUMX;
        int i8 = BLOBSUMY;
        int i9 = BLOBSUMXX;
        int i10 = BLOBSUMYY;
        int i11 = BLOBSUMXY;
        int i12 = BLOBMINX;
        int i13 = BLOBMAXX;
        int i14 = BLOBMINY;
        dArr2[BLOBMAXY] = 0.0d;
        dArr2[i14] = 0.0d;
        dArr2[i13] = 0.0d;
        dArr2[i12] = 0.0d;
        dArr2[i11] = 0.0d;
        dArr2[i10] = 0.0d;
        dArr2[i9] = 0.0d;
        dArr2[i8] = 0.0d;
        dArr2[i7] = 0.0d;
        dArr2[i6] = 0.0d;
        dArr2[i5] = 0.0d;
        dArr2[i4] = 0.0d;
        dArr2[i3] = 0.0d;
        dArr2[i2] = 0.0d;
        System.arraycopy(dArr2, 0, dArr[i], 0, BLOBDATACOUNT);
    }

    public void OldRegion(int i, int i2, int i3) {
        int i4;
        if (i2 < 0 || i2 == i) {
            i4 = 0;
        } else {
            double[][] dArr = RegionData;
            double[] dArr2 = dArr[i2];
            int i5 = BLOBPERIMETER;
            dArr2[i5] = dArr2[i5] + 1.0d;
            System.arraycopy(dArr2, 0, dArr[i2], 0, BLOBDATACOUNT);
            i4 = 1;
        }
        if (i3 >= 0 && i3 != i) {
            i4++;
            double[][] dArr3 = RegionData;
            double[] dArr4 = dArr3[i3];
            int i6 = BLOBPERIMETER;
            dArr4[i6] = dArr4[i6] + 1.0d;
            System.arraycopy(dArr4, 0, dArr3[i3], 0, BLOBDATACOUNT);
        }
        this.LabelD = i;
        double[] dArr5 = RegionData[i];
        dArr5[BLOBLABEL] = (double) i;
        int i7 = BLOBPARENT;
        dArr5[i7] = dArr5[i7] + 0.0d;
        int i8 = BLOBCOLOR;
        dArr5[i8] = dArr5[i8] + 0.0d;
        int i9 = BLOBAREA;
        dArr5[i9] = dArr5[i9] + 1.0d;
        int i10 = BLOBPERIMETER;
        dArr5[i10] = dArr5[i10] + ((double) i4);
        int i11 = BLOBSUMX;
        double d = dArr5[i11];
        int i12 = this.jcol;
        dArr5[i11] = d + ((double) i12);
        int i13 = BLOBSUMY;
        double d2 = dArr5[i13];
        int i14 = this.jrow;
        dArr5[i13] = d2 + ((double) i14);
        int i15 = BLOBSUMXX;
        dArr5[i15] = dArr5[i15] + ((double) (i12 * i12));
        int i16 = BLOBSUMYY;
        dArr5[i16] = dArr5[i16] + ((double) (i14 * i14));
        int i17 = BLOBSUMXY;
        dArr5[i17] = dArr5[i17] + ((double) (i14 * i12));
        int i18 = BLOBMINX;
        dArr5[i18] = Math.min(dArr5[i18], (double) i12);
        int i19 = BLOBMAXX;
        dArr5[i19] = Math.max(dArr5[i19], (double) this.jcol);
        int i20 = BLOBMINY;
        dArr5[i20] = Math.min(dArr5[i20], (double) this.jrow);
        int i21 = BLOBMAXY;
        dArr5[i21] = Math.max(dArr5[i21], (double) this.jrow);
        System.arraycopy(dArr5, 0, RegionData[this.LabelD], 0, BLOBDATACOUNT);
    }

    public void NewRegion(int i) {
        int i2 = MaxLabel + 1;
        MaxLabel = i2;
        this.LabelD = i2;
        double[][] dArr = RegionData;
        double[] dArr2 = dArr[i2];
        dArr2[BLOBLABEL] = (double) i2;
        dArr2[BLOBPARENT] = (double) i;
        dArr2[BLOBCOLOR] = (double) this.ColorD;
        dArr2[BLOBAREA] = 1.0d;
        dArr2[BLOBPERIMETER] = 2.0d;
        int i3 = BLOBSUMX;
        int i4 = this.jcol;
        dArr2[i3] = (double) i4;
        int i5 = BLOBSUMY;
        int i6 = this.jrow;
        dArr2[i5] = (double) i6;
        dArr2[BLOBSUMXX] = (double) (i4 * i4);
        dArr2[BLOBSUMYY] = (double) (i6 * i6);
        dArr2[BLOBSUMXY] = (double) (i4 * i6);
        dArr2[BLOBMINX] = (double) i4;
        dArr2[BLOBMAXX] = (double) i4;
        dArr2[BLOBMINY] = (double) i6;
        dArr2[BLOBMAXY] = (double) i6;
        System.arraycopy(dArr2, 0, dArr[i2], 0, BLOBDATACOUNT);
        SubsumedLabel[this.LabelD] = -1;
        double[][] dArr3 = RegionData;
        int i7 = this.LabelB;
        double[] dArr4 = dArr3[i7];
        int i8 = BLOBPERIMETER;
        dArr4[i8] = dArr4[i8] + 1.0d;
        System.arraycopy(dArr4, 0, dArr3[i7], 0, BLOBDATACOUNT);
        double[][] dArr5 = RegionData;
        int i9 = this.LabelC;
        double[] dArr6 = dArr5[i9];
        int i10 = BLOBPERIMETER;
        dArr6[i10] = dArr6[i10] + 1.0d;
        System.arraycopy(dArr6, 0, dArr5[i9], 0, BLOBDATACOUNT);
    }

    public void Subsume(int i, int i2, int i3) {
        this.LabelD = i;
        double[][] dArr = RegionData;
        double[] dArr2 = dArr[i];
        double[] dArr3 = dArr[i2];
        int i4 = BLOBLABEL;
        dArr2[i4] = dArr2[i4];
        int i5 = BLOBPARENT;
        dArr2[i5] = dArr2[i5];
        int i6 = BLOBCOLOR;
        dArr2[i6] = dArr2[i6];
        int i7 = BLOBAREA;
        dArr2[i7] = dArr2[i7] + dArr3[i7];
        int i8 = BLOBPERIMETER;
        dArr2[i8] = dArr2[i8] + (dArr3[i8] * ((double) i3));
        int i9 = BLOBSUMX;
        dArr2[i9] = dArr2[i9] + dArr3[i9];
        int i10 = BLOBSUMY;
        dArr2[i10] = dArr2[i10] + dArr3[i10];
        int i11 = BLOBSUMXX;
        dArr2[i11] = dArr2[i11] + dArr3[i11];
        int i12 = BLOBSUMYY;
        dArr2[i12] = dArr2[i12] + dArr3[i12];
        int i13 = BLOBSUMXY;
        dArr2[i13] = dArr2[i13] + dArr3[i13];
        int i14 = BLOBMINX;
        dArr2[i14] = Math.min(dArr2[i14], dArr3[i14]);
        int i15 = BLOBMAXX;
        dArr2[i15] = Math.max(dArr2[i15], dArr3[i15]);
        int i16 = BLOBMINY;
        dArr2[i16] = Math.min(dArr2[i16], dArr3[i16]);
        int i17 = BLOBMAXY;
        dArr2[i17] = Math.max(dArr2[i17], dArr3[i17]);
        System.arraycopy(dArr2, 0, RegionData[i], 0, BLOBDATACOUNT);
    }

    public static int SubsumptionChain(int i) {
        return SubsumptionChain(i, 0);
    }

    public static int SubsumptionChain(int i, int i2) {
        String str;
        if (i2 > 0) {
            str = "Subsumption chain for " + i + ": ";
        } else {
            str = "";
        }
        String str2 = str;
        int i3 = i;
        while (true) {
            if (i <= -1) {
                i = i3;
                break;
            }
            if (i2 > 0) {
                str2 = str2 + " " + i;
            }
            if (i == 0) {
                break;
            }
            i3 = i;
            i = SubsumedLabel[i];
        }
        if (i2 > 0) {
            System.out.println(str2);
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:124:0x024a  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int BlobAnalysis(org.bytedeco.opencv.opencv_core.IplImage r28, int r29, int r30, int r31, int r32, int r33, int r34) {
        /*
            r27 = this;
            r0 = r27
            org.bytedeco.opencv.opencv_core.CvMat r1 = r28.asCvMat()
            int r2 = r1.cols()
            int r3 = r1.rows()
            r4 = 0
            if (r29 >= 0) goto L_0x0013
            r5 = 0
            goto L_0x0015
        L_0x0013:
            r5 = r29
        L_0x0015:
            if (r30 >= 0) goto L_0x0019
            r6 = 0
            goto L_0x001b
        L_0x0019:
            r6 = r30
        L_0x001b:
            if (r31 >= 0) goto L_0x001f
            r7 = r2
            goto L_0x0021
        L_0x001f:
            r7 = r31
        L_0x0021:
            if (r32 >= 0) goto L_0x0025
            r8 = r3
            goto L_0x0027
        L_0x0025:
            r8 = r32
        L_0x0027:
            int r9 = r5 + r7
            if (r9 <= r2) goto L_0x002d
            int r7 = r2 - r5
        L_0x002d:
            int r2 = r6 + r8
            if (r2 <= r3) goto L_0x0033
            int r8 = r3 - r6
        L_0x0033:
            int r2 = BLOBCOLCOUNT
            if (r7 > r2) goto L_0x0402
            int r2 = BLOBROWCOUNT
            if (r8 <= r2) goto L_0x003d
            goto L_0x0402
        L_0x003d:
            if (r33 <= 0) goto L_0x0041
            r3 = 1
            goto L_0x0042
        L_0x0041:
            r3 = 0
        L_0x0042:
            r0.LabelD = r4
            r0.LabelC = r4
            r0.LabelB = r4
            r0.LabelA = r4
            r0.ColorD = r3
            r0.ColorC = r3
            r0.ColorB = r3
            r0.ColorA = r3
            r9 = 0
        L_0x0053:
            int r10 = BLOBTOTALCOUNT
            r11 = -1
            if (r9 >= r10) goto L_0x005f
            int[] r10 = SubsumedLabel
            r10[r9] = r11
            int r9 = r9 + 1
            goto L_0x0053
        L_0x005f:
            MaxLabel = r4
            double[][] r9 = RegionData
            r10 = r9[r4]
            int r12 = BLOBLABEL
            r13 = 0
            r10[r12] = r13
            int r12 = BLOBPARENT
            r15 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r10[r12] = r15
            int r12 = BLOBAREA
            int r17 = r8 + r7
            r18 = 4
            int r13 = r17 + 4
            double r13 = (double) r13
            r10[r12] = r13
            int r12 = BLOBCOLOR
            double r13 = (double) r3
            r10[r12] = r13
            int r12 = BLOBSUMX
            double r13 = (double) r7
            r19 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r21 = r13 + r19
            r23 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r25 = r13 - r23
            double r21 = r21 * r25
            r25 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r21 = r21 * r25
            r17 = r3
            double r2 = (double) r8
            double r21 = r21 - r2
            double r21 = r21 - r23
            r10[r12] = r21
            int r12 = BLOBSUMY
            double r19 = r2 + r19
            double r21 = r2 - r23
            double r19 = r19 * r21
            double r19 = r19 * r25
            double r19 = r19 - r13
            double r19 = r19 - r23
            r10[r12] = r19
            int r12 = BLOBMINX
            r10[r12] = r15
            int r12 = BLOBMINY
            r10[r12] = r15
            int r12 = BLOBMAXX
            double r13 = r13 + r23
            r10[r12] = r13
            int r12 = BLOBMAXY
            double r2 = r2 + r23
            r10[r12] = r2
            r2 = r9[r4]
            int r3 = BLOBDATACOUNT
            java.lang.System.arraycopy(r10, r4, r2, r4, r3)
            r2 = r6
        L_0x00c7:
            int r3 = r6 + r8
            if (r2 >= r3) goto L_0x028a
            int r3 = r2 - r6
            r0.jrow = r3
            r3 = r5
        L_0x00d0:
            int r9 = r5 + r7
            if (r3 >= r9) goto L_0x0283
            int r9 = r3 - r5
            r0.jcol = r9
            r10 = r17
            r0.ColorC = r10
            r0.ColorB = r10
            r0.ColorA = r10
            r0.LabelD = r4
            r0.LabelC = r4
            r0.LabelB = r4
            r0.LabelA = r4
            int r12 = r0.jrow
            double r12 = r1.get((int) r12, (int) r9)
            int r9 = (int) r12
            r0.ColorD = r9
            int r9 = r0.jrow
            if (r9 == 0) goto L_0x014d
            int r12 = r0.jcol
            if (r12 != 0) goto L_0x00fa
            goto L_0x014d
        L_0x00fa:
            int r9 = r9 + -1
            int r12 = r12 + -1
            double r12 = r1.get((int) r9, (int) r12)
            int r9 = (int) r12
            r0.ColorA = r9
            if (r9 <= 0) goto L_0x010b
            r9 = 1
            r0.ColorA = r9
            goto L_0x010c
        L_0x010b:
            r9 = 1
        L_0x010c:
            int r12 = r0.jrow
            int r12 = r12 - r9
            int r13 = r0.jcol
            double r12 = r1.get((int) r12, (int) r13)
            int r12 = (int) r12
            r0.ColorB = r12
            if (r12 <= 0) goto L_0x011c
            r0.ColorB = r9
        L_0x011c:
            int r12 = r0.jrow
            int r13 = r0.jcol
            int r13 = r13 - r9
            double r12 = r1.get((int) r12, (int) r13)
            int r12 = (int) r12
            r0.ColorC = r12
            if (r12 <= 0) goto L_0x012c
            r0.ColorC = r9
        L_0x012c:
            int[][] r9 = LabelMat
            int r12 = r0.jrow
            int r13 = r12 + -1
            r13 = r9[r13]
            int r14 = r0.jcol
            int r15 = r14 + -1
            r13 = r13[r15]
            r0.LabelA = r13
            int r13 = r12 + -1
            r13 = r9[r13]
            r13 = r13[r14]
            r0.LabelB = r13
            r9 = r9[r12]
            r12 = 1
            int r14 = r14 - r12
            r9 = r9[r14]
            r0.LabelC = r9
            goto L_0x0186
        L_0x014d:
            int r12 = r0.jcol
            if (r12 <= 0) goto L_0x0168
            int r12 = r12 + -1
            double r12 = r1.get((int) r9, (int) r12)
            int r9 = (int) r12
            r0.ColorC = r9
            int[][] r9 = LabelMat
            int r12 = r0.jrow
            r9 = r9[r12]
            int r12 = r0.jcol
            r13 = 1
            int r12 = r12 - r13
            r9 = r9[r12]
            r0.LabelC = r9
        L_0x0168:
            int r9 = r0.jrow
            if (r9 <= 0) goto L_0x0186
            int r9 = r9 + -1
            int r12 = r0.jcol
            double r12 = r1.get((int) r9, (int) r12)
            int r9 = (int) r12
            r0.ColorB = r9
            int[][] r9 = LabelMat
            int r12 = r0.jrow
            r13 = 1
            int r12 = r12 - r13
            r9 = r9[r12]
            int r12 = r0.jcol
            r9 = r9[r12]
            r0.LabelB = r9
            goto L_0x0187
        L_0x0186:
            r13 = 1
        L_0x0187:
            int r9 = r0.ColorA
            if (r9 <= 0) goto L_0x018d
            r0.ColorA = r13
        L_0x018d:
            int r9 = r0.ColorB
            if (r9 <= 0) goto L_0x0193
            r0.ColorB = r13
        L_0x0193:
            int r9 = r0.ColorC
            if (r9 <= 0) goto L_0x0199
            r0.ColorC = r13
        L_0x0199:
            int r9 = r0.ColorD
            if (r9 <= 0) goto L_0x019f
            r0.ColorD = r13
        L_0x019f:
            int r9 = r0.ColorA
            int r12 = r0.ColorB
            r13 = 2
            r14 = 5
            r4 = 3
            if (r9 != r12) goto L_0x01ba
            int r12 = r0.ColorC
            int r15 = r0.ColorD
            if (r12 != r15) goto L_0x01b4
            if (r9 != r12) goto L_0x01b2
            r9 = 1
            goto L_0x01cc
        L_0x01b2:
            r9 = 2
            goto L_0x01cc
        L_0x01b4:
            if (r9 != r12) goto L_0x01b8
            r9 = 5
            goto L_0x01cc
        L_0x01b8:
            r9 = 6
            goto L_0x01cc
        L_0x01ba:
            int r12 = r0.ColorC
            int r15 = r0.ColorD
            if (r12 != r15) goto L_0x01c6
            if (r9 != r12) goto L_0x01c4
            r9 = 3
            goto L_0x01cc
        L_0x01c4:
            r9 = 4
            goto L_0x01cc
        L_0x01c6:
            if (r9 != r12) goto L_0x01ca
            r9 = 7
            goto L_0x01cc
        L_0x01ca:
            r9 = 8
        L_0x01cc:
            r12 = 1
            if (r9 != r12) goto L_0x01d6
            int r4 = r0.LabelC
            r0.OldRegion(r4, r11, r11)
            goto L_0x023a
        L_0x01d6:
            if (r9 == r13) goto L_0x0233
            if (r9 != r4) goto L_0x01db
            goto L_0x0233
        L_0x01db:
            if (r9 == r14) goto L_0x021c
            r4 = 8
            if (r9 != r4) goto L_0x01e2
            goto L_0x021c
        L_0x01e2:
            r4 = 6
            if (r9 == r4) goto L_0x0214
            r4 = 7
            if (r9 != r4) goto L_0x01e9
            goto L_0x0214
        L_0x01e9:
            int r4 = r0.LabelB
            int r4 = SubsumptionChain(r4)
            int r9 = r0.LabelC
            int r9 = SubsumptionChain(r9)
            int r12 = java.lang.Math.min(r4, r9)
            if (r4 >= r9) goto L_0x0203
            int r4 = r0.LabelB
            r0.OldRegion(r4, r11, r11)
            int r4 = r0.LabelC
            goto L_0x020a
        L_0x0203:
            int r4 = r0.LabelC
            r0.OldRegion(r4, r11, r11)
            int r4 = r0.LabelB
        L_0x020a:
            if (r12 >= r4) goto L_0x023a
            int[] r9 = SubsumedLabel
            r13 = r9[r4]
            r9[r4] = r12
            r4 = r13
            goto L_0x020a
        L_0x0214:
            int r4 = r0.LabelB
            int r9 = r0.LabelC
            r0.OldRegion(r4, r4, r9)
            goto L_0x023a
        L_0x021c:
            int r4 = r0.jrow
            if (r4 == r8) goto L_0x0224
            int r4 = r0.jcol
            if (r4 != r7) goto L_0x022d
        L_0x0224:
            int r4 = r0.ColorD
            if (r4 != r10) goto L_0x022d
            r4 = 0
            r0.OldRegion(r4, r11, r11)
            goto L_0x023a
        L_0x022d:
            int r4 = r0.LabelB
            r0.NewRegion(r4)
            goto L_0x023a
        L_0x0233:
            int r4 = r0.LabelC
            int r9 = r0.LabelB
            r0.OldRegion(r4, r9, r4)
        L_0x023a:
            int r4 = r0.jrow
            if (r4 == r8) goto L_0x0242
            int r9 = r0.jcol
            if (r9 != r7) goto L_0x0270
        L_0x0242:
            int r9 = r0.ColorD
            if (r9 != r10) goto L_0x0270
            int r9 = r0.jcol
            if (r9 >= r7) goto L_0x025c
            int r4 = r0.ColorC
            if (r4 == r10) goto L_0x025a
            int r4 = r0.LabelB
            int r4 = SubsumptionChain(r4)
            int[] r9 = SubsumedLabel
            r12 = 0
            r9[r4] = r12
            goto L_0x026d
        L_0x025a:
            r12 = 0
            goto L_0x026d
        L_0x025c:
            r12 = 0
            if (r4 >= r8) goto L_0x026d
            int r4 = r0.ColorB
            if (r4 == r10) goto L_0x026d
            int r4 = r0.LabelC
            int r4 = SubsumptionChain(r4)
            int[] r9 = SubsumedLabel
            r9[r4] = r12
        L_0x026d:
            r0.OldRegion(r12, r11, r11)
        L_0x0270:
            int[][] r4 = LabelMat
            int r9 = r0.jrow
            r4 = r4[r9]
            int r9 = r0.jcol
            int r12 = r0.LabelD
            r4[r9] = r12
            int r3 = r3 + 1
            r17 = r10
            r4 = 0
            goto L_0x00d0
        L_0x0283:
            r10 = r17
            int r2 = r2 + 1
            r4 = 0
            goto L_0x00c7
        L_0x028a:
            r1 = 0
            r9 = 1
        L_0x028c:
            int r2 = MaxLabel
            if (r9 > r2) goto L_0x02a1
            int[] r2 = SubsumedLabel
            r2 = r2[r9]
            if (r2 <= r11) goto L_0x0298
            int r1 = r1 + 1
        L_0x0298:
            int[] r2 = CondensationMap
            int r3 = r9 - r1
            r2[r9] = r3
            int r9 = r9 + 1
            goto L_0x028c
        L_0x02a1:
            r9 = 1
        L_0x02a2:
            int r1 = MaxLabel
            if (r9 > r1) goto L_0x02b3
            int r1 = SubsumptionChain(r9)
            r2 = 1
            if (r1 == r9) goto L_0x02b0
            r0.Subsume(r1, r9, r2)
        L_0x02b0:
            int r9 = r9 + 1
            goto L_0x02a2
        L_0x02b3:
            r2 = 1
            r1 = 0
            r9 = 1
        L_0x02b6:
            int r3 = MaxLabel
            if (r9 > r3) goto L_0x02ed
            int[] r3 = SubsumedLabel
            r3 = r3[r9]
            if (r3 >= 0) goto L_0x02ea
            double[][] r1 = RegionData
            r1 = r1[r9]
            int r3 = BLOBPARENT
            r3 = r1[r3]
            int r3 = (int) r3
            int[] r4 = CondensationMap
            r4 = r4[r9]
            int r3 = SubsumptionChain(r3)
            int[] r5 = CondensationMap
            r3 = r5[r3]
            int r5 = BLOBLABEL
            double r6 = (double) r4
            r1[r5] = r6
            int r5 = BLOBPARENT
            double r6 = (double) r3
            r1[r5] = r6
            double[][] r3 = RegionData
            r3 = r3[r4]
            int r5 = BLOBDATACOUNT
            r6 = 0
            java.lang.System.arraycopy(r1, r6, r3, r6, r5)
            r1 = r4
        L_0x02ea:
            int r9 = r9 + 1
            goto L_0x02b6
        L_0x02ed:
            int r3 = r1 + 1
        L_0x02ef:
            int r4 = MaxLabel
            if (r3 > r4) goto L_0x02f9
            r0.ResetRegion(r3)
            int r3 = r3 + 1
            goto L_0x02ef
        L_0x02f9:
            MaxLabel = r1
            r3 = r1
        L_0x02fc:
            if (r3 <= 0) goto L_0x031c
            double[][] r4 = RegionData
            r4 = r4[r3]
            int r5 = BLOBAREA
            r5 = r4[r5]
            int r5 = (int) r5
            r6 = r34
            if (r5 >= r6) goto L_0x0315
            int r5 = BLOBPARENT
            r7 = r4[r5]
            int r4 = (int) r7
            int[] r5 = SubsumedLabel
            r5[r3] = r4
            goto L_0x0319
        L_0x0315:
            int[] r4 = SubsumedLabel
            r4[r3] = r11
        L_0x0319:
            int r3 = r3 + -1
            goto L_0x02fc
        L_0x031c:
            r3 = 0
            r9 = 1
        L_0x031e:
            int r4 = MaxLabel
            if (r9 > r4) goto L_0x0333
            int[] r4 = SubsumedLabel
            r4 = r4[r9]
            if (r4 <= r11) goto L_0x032a
            int r3 = r3 + 1
        L_0x032a:
            int[] r4 = CondensationMap
            int r5 = r9 - r3
            r4[r9] = r5
            int r9 = r9 + 1
            goto L_0x031e
        L_0x0333:
            r9 = 1
        L_0x0334:
            int r3 = MaxLabel
            if (r9 > r3) goto L_0x0344
            int r3 = SubsumptionChain(r9)
            if (r3 == r9) goto L_0x0341
            r0.Subsume(r3, r9, r11)
        L_0x0341:
            int r9 = r9 + 1
            goto L_0x0334
        L_0x0344:
            int r3 = MaxLabel
            if (r2 > r3) goto L_0x037b
            int[] r3 = SubsumedLabel
            r3 = r3[r2]
            if (r3 >= 0) goto L_0x0378
            double[][] r1 = RegionData
            r1 = r1[r2]
            int r3 = BLOBPARENT
            r3 = r1[r3]
            int r3 = (int) r3
            int[] r4 = CondensationMap
            r4 = r4[r2]
            int r3 = SubsumptionChain(r3)
            int[] r5 = CondensationMap
            r3 = r5[r3]
            int r5 = BLOBLABEL
            double r6 = (double) r4
            r1[r5] = r6
            int r5 = BLOBPARENT
            double r6 = (double) r3
            r1[r5] = r6
            double[][] r3 = RegionData
            r3 = r3[r4]
            int r5 = BLOBDATACOUNT
            r6 = 0
            java.lang.System.arraycopy(r1, r6, r3, r6, r5)
            r1 = r4
        L_0x0378:
            int r2 = r2 + 1
            goto L_0x0344
        L_0x037b:
            int r2 = r1 + 1
        L_0x037d:
            int r3 = MaxLabel
            if (r2 > r3) goto L_0x0387
            r0.ResetRegion(r2)
            int r2 = r2 + 1
            goto L_0x037d
        L_0x0387:
            MaxLabel = r1
            r4 = 0
        L_0x038a:
            int r1 = MaxLabel
            if (r4 > r1) goto L_0x03e5
            double[][] r1 = RegionData
            r2 = r1[r4]
            int r3 = BLOBAREA
            r5 = r2[r3]
            int r3 = BLOBSUMX
            r7 = r2[r3]
            int r9 = BLOBSUMY
            r10 = r2[r9]
            int r12 = BLOBSUMXX
            r13 = r2[r12]
            int r15 = BLOBSUMYY
            r17 = r2[r15]
            int r19 = BLOBSUMXY
            r20 = r2[r19]
            double r7 = r7 / r5
            double r10 = r10 / r5
            double r13 = r13 / r5
            double r17 = r17 / r5
            double r20 = r20 / r5
            double r5 = r7 * r7
            double r13 = r13 - r5
            double r5 = r10 * r10
            double r17 = r17 - r5
            double r5 = r7 * r10
            double r20 = r20 - r5
            r5 = -4826024147167401061(0xbd06849b86a12b9b, double:-1.0E-14)
            int r22 = (r20 > r5 ? 1 : (r20 == r5 ? 0 : -1))
            if (r22 <= 0) goto L_0x03d0
            r5 = 4397347889687374747(0x3d06849b86a12b9b, double:1.0E-14)
            int r22 = (r20 > r5 ? 1 : (r20 == r5 ? 0 : -1))
            if (r22 >= 0) goto L_0x03d0
            r20 = 0
        L_0x03d0:
            r2[r3] = r7
            r2[r9] = r10
            r2[r12] = r13
            r2[r15] = r17
            r2[r19] = r20
            r1 = r1[r4]
            int r3 = BLOBDATACOUNT
            r5 = 0
            java.lang.System.arraycopy(r2, r5, r1, r5, r3)
            int r4 = r4 + 1
            goto L_0x038a
        L_0x03e5:
            r5 = 0
            double[][] r1 = RegionData
            r2 = r1[r5]
            int r3 = BLOBSUMXX
            int r4 = BLOBSUMYY
            int r6 = BLOBSUMXY
            r7 = 0
            r2[r6] = r7
            r2[r4] = r7
            r2[r3] = r7
            r1 = r1[r5]
            int r3 = BLOBDATACOUNT
            java.lang.System.arraycopy(r2, r5, r1, r5, r3)
            int r1 = MaxLabel
            return r1
        L_0x0402:
            r5 = 0
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r2 = "Error in Class Blobs: Image too large: Edit Blobs.java"
            r1.println(r2)
            r1 = 666(0x29a, float:9.33E-43)
            java.lang.System.exit(r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.Blobs.BlobAnalysis(org.bytedeco.opencv.opencv_core.IplImage, int, int, int, int, int, int):int");
    }

    public static void SortRegions(int i) {
        int i2 = 0;
        while (i2 < MaxLabel) {
            int i3 = i2 + 1;
            for (int i4 = i3; i4 <= MaxLabel; i4++) {
                double[][] dArr = RegionData;
                double[] dArr2 = dArr[i2];
                iProperty = dArr2;
                double[] dArr3 = dArr[i4];
                jProperty = dArr3;
                double d = dArr2[i];
                iField = d;
                double d2 = dArr3[i];
                jField = d2;
                if (d > d2) {
                    dArr[i2] = dArr3;
                    dArr[i4] = dArr2;
                }
            }
            i2 = i3;
        }
    }
}
