package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.bouncycastle.util.Arrays;

public class Layer {
    private short[][][] coeff_alpha;
    private short[][][] coeff_beta;
    private short[] coeff_eta;
    private short[][] coeff_gamma;
    private int oi;
    private int vi;
    private int viNext;

    public Layer(byte b, byte b2, short[][][] sArr, short[][][] sArr2, short[][] sArr3, short[] sArr4) {
        byte b3 = b & UByte.MAX_VALUE;
        this.vi = b3;
        byte b4 = b2 & UByte.MAX_VALUE;
        this.viNext = b4;
        this.oi = b4 - b3;
        this.coeff_alpha = sArr;
        this.coeff_beta = sArr2;
        this.coeff_gamma = sArr3;
        this.coeff_eta = sArr4;
    }

    public Layer(int i, int i2, SecureRandom secureRandom) {
        Class<short> cls = short.class;
        this.vi = i;
        this.viNext = i2;
        int i3 = i2 - i;
        this.oi = i3;
        int[] iArr = new int[3];
        iArr[2] = i;
        iArr[1] = i3;
        iArr[0] = i3;
        this.coeff_alpha = (short[][][]) Array.newInstance(cls, iArr);
        int i4 = this.oi;
        int i5 = this.vi;
        int[] iArr2 = new int[3];
        iArr2[2] = i5;
        iArr2[1] = i5;
        iArr2[0] = i4;
        this.coeff_beta = (short[][][]) Array.newInstance(cls, iArr2);
        int i6 = this.oi;
        int[] iArr3 = new int[2];
        iArr3[1] = this.viNext;
        iArr3[0] = i6;
        this.coeff_gamma = (short[][]) Array.newInstance(cls, iArr3);
        int i7 = this.oi;
        this.coeff_eta = new short[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            for (int i9 = 0; i9 < this.oi; i9++) {
                for (int i10 = 0; i10 < this.vi; i10++) {
                    this.coeff_alpha[i8][i9][i10] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int i11 = 0; i11 < i7; i11++) {
            for (int i12 = 0; i12 < this.vi; i12++) {
                for (int i13 = 0; i13 < this.vi; i13++) {
                    this.coeff_beta[i11][i12][i13] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int i14 = 0; i14 < i7; i14++) {
            for (int i15 = 0; i15 < this.viNext; i15++) {
                this.coeff_gamma[i14][i15] = (short) (secureRandom.nextInt() & 255);
            }
        }
        for (int i16 = 0; i16 < i7; i16++) {
            this.coeff_eta[i16] = (short) (secureRandom.nextInt() & 255);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Layer)) {
            return false;
        }
        Layer layer = (Layer) obj;
        return this.vi == layer.getVi() && this.viNext == layer.getViNext() && this.oi == layer.getOi() && RainbowUtil.equals(this.coeff_alpha, layer.getCoeffAlpha()) && RainbowUtil.equals(this.coeff_beta, layer.getCoeffBeta()) && RainbowUtil.equals(this.coeff_gamma, layer.getCoeffGamma()) && RainbowUtil.equals(this.coeff_eta, layer.getCoeffEta());
    }

    public short[][][] getCoeffAlpha() {
        return this.coeff_alpha;
    }

    public short[][][] getCoeffBeta() {
        return this.coeff_beta;
    }

    public short[] getCoeffEta() {
        return this.coeff_eta;
    }

    public short[][] getCoeffGamma() {
        return this.coeff_gamma;
    }

    public int getOi() {
        return this.oi;
    }

    public int getVi() {
        return this.vi;
    }

    public int getViNext() {
        return this.viNext;
    }

    public int hashCode() {
        return (((((((((((this.vi * 37) + this.viNext) * 37) + this.oi) * 37) + Arrays.hashCode(this.coeff_alpha)) * 37) + Arrays.hashCode(this.coeff_beta)) * 37) + Arrays.hashCode(this.coeff_gamma)) * 37) + Arrays.hashCode(this.coeff_eta);
    }

    public short[][] plugInVinegars(short[] sArr) {
        int i = this.oi;
        int[] iArr = new int[2];
        iArr[1] = i + 1;
        int i2 = 0;
        iArr[0] = i;
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, iArr);
        short[] sArr3 = new short[this.oi];
        for (int i3 = 0; i3 < this.oi; i3++) {
            for (int i4 = 0; i4 < this.vi; i4++) {
                for (int i5 = 0; i5 < this.vi; i5++) {
                    sArr3[i3] = GF2Field.addElem(sArr3[i3], GF2Field.multElem(GF2Field.multElem(this.coeff_beta[i3][i4][i5], sArr[i4]), sArr[i5]));
                }
            }
        }
        for (int i6 = 0; i6 < this.oi; i6++) {
            for (int i7 = 0; i7 < this.oi; i7++) {
                for (int i8 = 0; i8 < this.vi; i8++) {
                    sArr2[i6][i7] = GF2Field.addElem(sArr2[i6][i7], GF2Field.multElem(this.coeff_alpha[i6][i7][i8], sArr[i8]));
                }
            }
        }
        for (int i9 = 0; i9 < this.oi; i9++) {
            for (int i10 = 0; i10 < this.vi; i10++) {
                sArr3[i9] = GF2Field.addElem(sArr3[i9], GF2Field.multElem(this.coeff_gamma[i9][i10], sArr[i10]));
            }
        }
        for (int i11 = 0; i11 < this.oi; i11++) {
            for (int i12 = this.vi; i12 < this.viNext; i12++) {
                short[] sArr4 = sArr2[i11];
                int i13 = this.vi;
                sArr4[i12 - i13] = GF2Field.addElem(this.coeff_gamma[i11][i12], sArr2[i11][i12 - i13]);
            }
        }
        for (int i14 = 0; i14 < this.oi; i14++) {
            sArr3[i14] = GF2Field.addElem(sArr3[i14], this.coeff_eta[i14]);
        }
        while (true) {
            int i15 = this.oi;
            if (i2 >= i15) {
                return sArr2;
            }
            sArr2[i2][i15] = sArr3[i2];
            i2++;
        }
    }
}
