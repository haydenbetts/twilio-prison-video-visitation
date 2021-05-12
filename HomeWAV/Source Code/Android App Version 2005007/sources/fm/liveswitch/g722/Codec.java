package fm.liveswitch.g722;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.SoundUtility;
import kotlin.jvm.internal.ShortCompanionObject;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.opencv.global.opencv_core;

class Codec {
    private static int[] _ihn;
    private static int[] _ihp;
    private static int[] _ilb = {2048, 2093, 2139, 2186, 2233, 2282, 2332, 2383, 2435, 2489, 2543, 2599, 2656, 2714, 2774, 2834, 2896, 2960, 3025, 3091, 3158, 3228, 3298, 3371, 3444, 3520, 3597, 3676, 3756, 3838, 3922, 4008};
    private static int[] _iln = {0, 63, 62, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 0};
    private static int[] _ilp = {0, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 0};
    private static int[] _q6 = {0, 35, 72, 110, 150, 190, 233, 276, 323, 370, 422, 473, 530, 587, 650, 714, 786, 858, 940, 1023, 1121, 1219, 1339, 1458, 1612, 1765, 1980, 2195, 2557, 2919, 0, 0};
    private static int[] _qm2 = {-7408, -1616, 7408, 1616};
    private static int[] _qm4 = {0, -20456, -12896, -8968, -6288, -4240, -2584, -1200, 20456, 12896, 8968, 6288, 4240, 2584, 1200, 0};
    private static int[] _qm5 = {-280, -280, -23352, -17560, -14120, -11664, -9752, -8184, -6864, -5712, -4696, -3784, -2960, -2208, -1520, -880, 23352, 17560, 14120, 11664, 9752, 8184, 6864, 5712, 4696, 3784, 2960, 2208, 1520, 880, 280, -280};
    private static int[] _qm6 = {-136, -136, -136, -136, -24808, -21904, -19008, -16704, -14984, -13512, -12280, -11192, -10232, -9360, -8576, -7856, -7192, -6576, -6000, -5456, -4944, -4464, -4008, -3576, -3168, -2776, -2400, -2032, -1688, -1360, -1040, -728, 24808, 21904, 19008, 16704, 14984, 13512, 12280, 11192, 10232, 9360, 8576, 7856, 7192, 6576, 6000, 5456, 4944, 4464, 4008, 3576, 3168, 2776, 2400, 2032, 1688, 1360, 1040, 728, 432, 136, -432, -136};
    private static int[] _qmf_coeffs = {3, -11, 12, 32, -210, 951, 3876, -805, 362, -156, 53, -11};
    private static int[] _rh2 = {2, 1, 2, 1};
    private static int[] _rl42 = {0, 7, 6, 5, 4, 3, 2, 1, 7, 6, 5, 4, 3, 2, 1, 0};
    private static int[] _wh;
    private static int[] _wl = {-60, -30, 58, 172, 334, 538, 1198, 3042};

    private static short saturate(int i) {
        short s = (short) i;
        return i == s ? s : i > 32767 ? ShortCompanionObject.MAX_VALUE : ShortCompanionObject.MIN_VALUE;
    }

    private static void block4(CodecState codecState, int i, int i2) {
        codecState.getBand()[i].getD()[0] = i2;
        codecState.getBand()[i].getR()[0] = saturate(codecState.getBand()[i].getS() + i2);
        codecState.getBand()[i].getP()[0] = saturate(codecState.getBand()[i].getSz() + i2);
        for (int i3 = 0; i3 < 3; i3++) {
            codecState.getBand()[i].getSg()[i3] = codecState.getBand()[i].getP()[i3] >> 15;
        }
        int saturate = saturate(codecState.getBand()[i].getA()[1] << 2);
        if (codecState.getBand()[i].getSg()[0] == codecState.getBand()[i].getSg()[1]) {
            saturate = -saturate;
        }
        if (saturate > 32767) {
            saturate = avutil.FF_LAMBDA_MAX;
        }
        int i4 = 128;
        int i5 = (codecState.getBand()[i].getSg()[0] == codecState.getBand()[i].getSg()[2] ? 128 : -128) + (saturate >> 7) + ((codecState.getBand()[i].getA()[2] * 32512) >> 15);
        if (i5 > 12288) {
            i5 = opencv_core.CV_SEQ_KIND_MASK;
        } else if (i5 < -12288) {
            i5 = -12288;
        }
        codecState.getBand()[i].getAp()[2] = i5;
        codecState.getBand()[i].getSg()[0] = codecState.getBand()[i].getP()[0] >> 15;
        codecState.getBand()[i].getSg()[1] = codecState.getBand()[i].getP()[1] >> 15;
        codecState.getBand()[i].getAp()[1] = saturate((codecState.getBand()[i].getSg()[0] == codecState.getBand()[i].getSg()[1] ? 192 : -192) + ((codecState.getBand()[i].getA()[1] * 32640) >> 15));
        int saturate2 = saturate(15360 - codecState.getBand()[i].getAp()[2]);
        if (codecState.getBand()[i].getAp()[1] > saturate2) {
            codecState.getBand()[i].getAp()[1] = saturate2;
        } else {
            int i6 = -saturate2;
            if (codecState.getBand()[i].getAp()[1] < i6) {
                codecState.getBand()[i].getAp()[1] = i6;
            }
        }
        if (i2 == 0) {
            i4 = 0;
        }
        codecState.getBand()[i].getSg()[0] = i2 >> 15;
        for (int i7 = 1; i7 < 7; i7++) {
            codecState.getBand()[i].getSg()[i7] = codecState.getBand()[i].getD()[i7] >> 15;
            codecState.getBand()[i].getBp()[i7] = saturate((codecState.getBand()[i].getSg()[i7] == codecState.getBand()[i].getSg()[0] ? i4 : -i4) + ((codecState.getBand()[i].getB()[i7] * 32640) >> 15));
        }
        for (int i8 = 6; i8 > 0; i8--) {
            codecState.getBand()[i].getD()[i8] = codecState.getBand()[i].getD()[i8 - 1];
            codecState.getBand()[i].getB()[i8] = codecState.getBand()[i].getBp()[i8];
        }
        for (int i9 = 2; i9 > 0; i9--) {
            int i10 = i9 - 1;
            codecState.getBand()[i].getR()[i9] = codecState.getBand()[i].getR()[i10];
            codecState.getBand()[i].getP()[i9] = codecState.getBand()[i].getP()[i10];
            codecState.getBand()[i].getA()[i9] = codecState.getBand()[i].getAp()[i9];
        }
        codecState.getBand()[i].setSp(saturate(((codecState.getBand()[i].getA()[1] * saturate(codecState.getBand()[i].getR()[1] + codecState.getBand()[i].getR()[1])) >> 15) + ((codecState.getBand()[i].getA()[2] * saturate(codecState.getBand()[i].getR()[2] + codecState.getBand()[i].getR()[2])) >> 15)));
        codecState.getBand()[i].setSz(0);
        for (int i11 = 6; i11 > 0; i11--) {
            short saturate3 = saturate(codecState.getBand()[i].getD()[i11] + codecState.getBand()[i].getD()[i11]);
            CodecBand codecBand = codecState.getBand()[i];
            codecBand.setSz(codecBand.getSz() + ((codecState.getBand()[i].getB()[i11] * saturate3) >> 15));
        }
        codecState.getBand()[i].setSz(saturate(codecState.getBand()[i].getSz()));
        codecState.getBand()[i].setS(saturate(codecState.getBand()[i].getSp() + codecState.getBand()[i].getSz()));
    }

    static {
        int[] iArr = new int[3];
        iArr[1] = -214;
        iArr[2] = 798;
        _wh = iArr;
        int[] iArr2 = new int[3];
        iArr2[1] = 1;
        _ihn = iArr2;
        int[] iArr3 = new int[3];
        iArr3[1] = 3;
        iArr3[2] = 2;
        _ihp = iArr3;
    }

    public int decode(CodecState codecState, DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        int i;
        int i2;
        int i3;
        CodecState codecState2 = codecState;
        int i4 = 0;
        int i5 = 0;
        while (i4 < dataBuffer.getLength()) {
            int i6 = i4 + 1;
            int read8 = dataBuffer.read8(i4);
            int bitsPerSample = codecState.getBitsPerSample();
            if (bitsPerSample == 6) {
                i2 = read8 & 15;
                i3 = (read8 >> 4) & 3;
                i = _qm4[i2];
            } else if (bitsPerSample == 7) {
                int i7 = read8 & 31;
                i3 = (read8 >> 5) & 3;
                i = _qm5[i7];
                i2 = i7 >> 1;
            } else {
                int i8 = read8 & 63;
                i3 = (read8 >> 6) & 3;
                i = _qm6[i8];
                i2 = i8 >> 2;
            }
            int s = codecState.getBand()[0].getS() + ((codecState.getBand()[0].getDet() * i) >> 15);
            int i9 = -16384;
            if (s > 16383) {
                s = 16383;
            } else if (s < -16384) {
                s = -16384;
            }
            int det = (codecState.getBand()[0].getDet() * _qm4[i2]) >> 15;
            int nb = ((codecState.getBand()[0].getNb() * 127) >> 7) + _wl[_rl42[i2]];
            if (nb < 0) {
                nb = 0;
            } else if (nb > 18432) {
                nb = 18432;
            }
            codecState.getBand()[0].setNb(nb);
            int nb2 = (codecState.getBand()[0].getNb() >> 6) & 31;
            int nb3 = 8 - (codecState.getBand()[0].getNb() >> 11);
            int[] iArr = _ilb;
            codecState.getBand()[0].setDet((nb3 < 0 ? iArr[nb2] << (-nb3) : iArr[nb2] >> nb3) << 2);
            block4(codecState2, 0, det);
            int det2 = (codecState.getBand()[1].getDet() * _qm2[i3]) >> 15;
            int s2 = codecState.getBand()[1].getS() + det2;
            if (s2 > 16383) {
                i9 = 16383;
            } else if (s2 >= -16384) {
                i9 = s2;
            }
            int nb4 = ((codecState.getBand()[1].getNb() * 127) >> 7) + _wh[_rh2[i3]];
            if (nb4 < 0) {
                nb4 = 0;
            } else if (nb4 > 22528) {
                nb4 = 22528;
            }
            codecState.getBand()[1].setNb(nb4);
            int nb5 = (codecState.getBand()[1].getNb() >> 6) & 31;
            int nb6 = 10 - (codecState.getBand()[1].getNb() >> 11);
            int[] iArr2 = _ilb;
            codecState.getBand()[1].setDet((nb6 < 0 ? iArr2[nb5] << (-nb6) : iArr2[nb5] >> nb6) << 2);
            block4(codecState2, 1, det2);
            for (int i10 = 0; i10 < 22; i10++) {
                codecState.getQmfSignalHistory()[i10] = codecState.getQmfSignalHistory()[i10 + 2];
            }
            codecState.getQmfSignalHistory()[22] = s + i9;
            codecState.getQmfSignalHistory()[23] = s - i9;
            int i11 = 0;
            int i12 = 0;
            for (int i13 = 0; i13 < 12; i13++) {
                int i14 = i13 * 2;
                i12 += codecState.getQmfSignalHistory()[i14] * _qmf_coeffs[i13];
                i11 += codecState.getQmfSignalHistory()[i14 + 1] * _qmf_coeffs[11 - i13];
            }
            SoundUtility.writePcmShort((short) (i11 >> 11), dataBuffer2.getData(), dataBuffer2.getIndex() + i5);
            int i15 = i5 + 2;
            SoundUtility.writePcmShort((short) (i12 >> 11), dataBuffer2.getData(), dataBuffer2.getIndex() + i15);
            i5 = i15 + 2;
            i4 = i6;
        }
        return i5;
    }

    public int encode(CodecState codecState, DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        CodecState codecState2 = codecState;
        int i = 0;
        int i2 = 0;
        while (i < dataBuffer.getLength()) {
            for (int i3 = 0; i3 < 22; i3++) {
                codecState.getQmfSignalHistory()[i3] = codecState.getQmfSignalHistory()[i3 + 2];
            }
            codecState.getQmfSignalHistory()[22] = SoundUtility.readPcmShort(dataBuffer.getData(), dataBuffer.getIndex() + i);
            int i4 = i + 2;
            codecState.getQmfSignalHistory()[23] = SoundUtility.readPcmShort(dataBuffer.getData(), dataBuffer.getIndex() + i4);
            i = i4 + 2;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < 12; i7++) {
                int i8 = i7 * 2;
                i6 += codecState.getQmfSignalHistory()[i8] * _qmf_coeffs[i7];
                i5 += codecState.getQmfSignalHistory()[i8 + 1] * _qmf_coeffs[11 - i7];
            }
            int i9 = (i5 - i6) >> 14;
            short saturate = saturate(((i5 + i6) >> 14) - codecState.getBand()[0].getS());
            int i10 = saturate >= 0 ? saturate : -(saturate + 1);
            int i11 = 1;
            while (i11 < 30 && i10 >= ((_q6[i11] * codecState.getBand()[0].getDet()) >> 12)) {
                i11++;
            }
            int i12 = saturate < 0 ? _iln[i11] : _ilp[i11];
            int i13 = i12 >> 2;
            int det = (codecState.getBand()[0].getDet() * _qm4[i13]) >> 15;
            codecState.getBand()[0].setNb(((codecState.getBand()[0].getNb() * 127) >> 7) + _wl[_rl42[i13]]);
            if (codecState.getBand()[0].getNb() < 0) {
                codecState.getBand()[0].setNb(0);
            } else if (codecState.getBand()[0].getNb() > 18432) {
                codecState.getBand()[0].setNb(18432);
            }
            int nb = (codecState.getBand()[0].getNb() >> 6) & 31;
            int nb2 = 8 - (codecState.getBand()[0].getNb() >> 11);
            int[] iArr = _ilb;
            codecState.getBand()[0].setDet((nb2 < 0 ? iArr[nb] << (-nb2) : iArr[nb] >> nb2) << 2);
            block4(codecState, 0, det);
            short saturate2 = saturate(i9 - codecState.getBand()[1].getS());
            char c = (saturate2 >= 0 ? saturate2 : -(saturate2 + 1)) >= ((codecState.getBand()[1].getDet() * 564) >> 12) ? (char) 2 : 1;
            int i14 = saturate2 < 0 ? _ihn[c] : _ihp[c];
            int det2 = (codecState.getBand()[1].getDet() * _qm2[i14]) >> 15;
            codecState.getBand()[1].setNb(((codecState.getBand()[1].getNb() * 127) >> 7) + _wh[_rh2[i14]]);
            if (codecState.getBand()[1].getNb() < 0) {
                codecState.getBand()[1].setNb(0);
            } else if (codecState.getBand()[1].getNb() > 22528) {
                codecState.getBand()[1].setNb(22528);
            }
            int nb3 = (codecState.getBand()[1].getNb() >> 6) & 31;
            int nb4 = 10 - (codecState.getBand()[1].getNb() >> 11);
            int[] iArr2 = _ilb;
            codecState.getBand()[1].setDet((nb4 < 0 ? iArr2[nb3] << (-nb4) : iArr2[nb3] >> nb4) << 2);
            block4(codecState, 1, det2);
            dataBuffer2.write8(((i14 << 6) | i12) >> (8 - codecState.getBitsPerSample()), i2);
            i2++;
        }
        return i2;
    }
}
