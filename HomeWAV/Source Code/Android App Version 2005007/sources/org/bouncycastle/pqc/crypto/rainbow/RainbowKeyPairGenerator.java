package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private short[][] A1;
    private short[][] A1inv;
    private short[][] A2;
    private short[][] A2inv;
    private short[] b1;
    private short[] b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;
    private SecureRandom sr;
    private int[] vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        int[] iArr = new int[2];
        iArr[1] = ((length2 + 1) * length2) / 2;
        iArr[0] = length;
        this.pub_quadratic = (short[][]) Array.newInstance(short.class, iArr);
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < length2; i3++) {
                for (int i4 = i3; i4 < length2; i4++) {
                    short[][] sArr2 = this.pub_quadratic;
                    if (i4 == i3) {
                        sArr2[i][i2] = sArr[i][i3][i4];
                    } else {
                        sArr2[i][i2] = GF2Field.addElem(sArr[i][i3][i4], sArr[i][i4][i3]);
                    }
                    i2++;
                }
            }
        }
    }

    private void computePublicKey() {
        Class<short> cls;
        Class<short> cls2 = short.class;
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.vi;
        int i = 0;
        int i2 = iArr[iArr.length - 1] - iArr[0];
        int i3 = iArr[iArr.length - 1];
        int[] iArr2 = new int[3];
        iArr2[2] = i3;
        iArr2[1] = i3;
        iArr2[0] = i2;
        short[][][] sArr = (short[][][]) Array.newInstance(cls2, iArr2);
        int[] iArr3 = new int[2];
        iArr3[1] = i3;
        iArr3[0] = i2;
        this.pub_singular = (short[][]) Array.newInstance(cls2, iArr3);
        this.pub_scalar = new short[i2];
        short[] sArr2 = new short[i3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i4 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i4].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i4].getCoeffBeta();
            short[][] coeffGamma = this.layers[i4].getCoeffGamma();
            short[] coeffEta = this.layers[i4].getCoeffEta();
            int length = coeffAlpha[i].length;
            int length2 = coeffBeta[i].length;
            while (i < length) {
                int i6 = 0;
                while (true) {
                    cls = cls2;
                    if (i6 >= length) {
                        break;
                    }
                    int i7 = 0;
                    while (i7 < length2) {
                        int i8 = i2;
                        int i9 = i3;
                        int i10 = i6 + length2;
                        short[] multVect = computeInField.multVect(coeffAlpha[i][i6][i7], this.A2[i10]);
                        int i11 = i5 + i;
                        int i12 = i4;
                        sArr[i11] = computeInField.addSquareMatrix(sArr[i11], computeInField.multVects(multVect, this.A2[i7]));
                        short[] multVect2 = computeInField.multVect(this.b2[i7], multVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[i11] = computeInField.addVect(multVect2, sArr3[i11]);
                        short[] multVect3 = computeInField.multVect(this.b2[i10], computeInField.multVect(coeffAlpha[i][i6][i7], this.A2[i7]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i11] = computeInField.addVect(multVect3, sArr4[i11]);
                        short multElem = GF2Field.multElem(coeffAlpha[i][i6][i7], this.b2[i10]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[i11] = GF2Field.addElem(sArr5[i11], GF2Field.multElem(multElem, this.b2[i7]));
                        i7++;
                        i3 = i9;
                        i2 = i8;
                        coeffAlpha = coeffAlpha;
                        i4 = i12;
                        coeffEta = coeffEta;
                    }
                    int i13 = i3;
                    int i14 = i2;
                    int i15 = i4;
                    short[][][] sArr6 = coeffAlpha;
                    short[] sArr7 = coeffEta;
                    i6++;
                    cls2 = cls;
                }
                int i16 = i3;
                int i17 = i2;
                int i18 = i4;
                short[][][] sArr8 = coeffAlpha;
                short[] sArr9 = coeffEta;
                for (int i19 = 0; i19 < length2; i19++) {
                    for (int i20 = 0; i20 < length2; i20++) {
                        short[] multVect4 = computeInField.multVect(coeffBeta[i][i19][i20], this.A2[i19]);
                        int i21 = i5 + i;
                        sArr[i21] = computeInField.addSquareMatrix(sArr[i21], computeInField.multVects(multVect4, this.A2[i20]));
                        short[] multVect5 = computeInField.multVect(this.b2[i20], multVect4);
                        short[][] sArr10 = this.pub_singular;
                        sArr10[i21] = computeInField.addVect(multVect5, sArr10[i21]);
                        short[] multVect6 = computeInField.multVect(this.b2[i19], computeInField.multVect(coeffBeta[i][i19][i20], this.A2[i20]));
                        short[][] sArr11 = this.pub_singular;
                        sArr11[i21] = computeInField.addVect(multVect6, sArr11[i21]);
                        short multElem2 = GF2Field.multElem(coeffBeta[i][i19][i20], this.b2[i19]);
                        short[] sArr12 = this.pub_scalar;
                        sArr12[i21] = GF2Field.addElem(sArr12[i21], GF2Field.multElem(multElem2, this.b2[i20]));
                    }
                }
                for (int i22 = 0; i22 < length2 + length; i22++) {
                    short[] multVect7 = computeInField.multVect(coeffGamma[i][i22], this.A2[i22]);
                    short[][] sArr13 = this.pub_singular;
                    int i23 = i5 + i;
                    sArr13[i23] = computeInField.addVect(multVect7, sArr13[i23]);
                    short[] sArr14 = this.pub_scalar;
                    sArr14[i23] = GF2Field.addElem(sArr14[i23], GF2Field.multElem(coeffGamma[i][i22], this.b2[i22]));
                }
                short[] sArr15 = this.pub_scalar;
                int i24 = i5 + i;
                sArr15[i24] = GF2Field.addElem(sArr15[i24], sArr9[i]);
                i++;
                cls2 = cls;
                i3 = i16;
                i2 = i17;
                coeffAlpha = sArr8;
                i4 = i18;
                coeffEta = sArr9;
            }
            Class<short> cls3 = cls2;
            int i25 = i3;
            int i26 = i2;
            i5 += length;
            i4++;
            i = 0;
        }
        Class<short> cls4 = cls2;
        int i27 = i3;
        int i28 = i2;
        int[] iArr4 = new int[3];
        iArr4[2] = i27;
        iArr4[1] = i27;
        iArr4[0] = i28;
        Class<short> cls5 = cls4;
        short[][][] sArr16 = (short[][][]) Array.newInstance(cls5, iArr4);
        int[] iArr5 = new int[2];
        iArr5[1] = i27;
        iArr5[0] = i28;
        short[][] sArr17 = (short[][]) Array.newInstance(cls5, iArr5);
        int i29 = i28;
        short[] sArr18 = new short[i29];
        for (int i30 = 0; i30 < i29; i30++) {
            int i31 = 0;
            while (true) {
                short[][] sArr19 = this.A1;
                if (i31 >= sArr19.length) {
                    break;
                }
                sArr16[i30] = computeInField.addSquareMatrix(sArr16[i30], computeInField.multMatrix(sArr19[i30][i31], sArr[i31]));
                sArr17[i30] = computeInField.addVect(sArr17[i30], computeInField.multVect(this.A1[i30][i31], this.pub_singular[i31]));
                sArr18[i30] = GF2Field.addElem(sArr18[i30], GF2Field.multElem(this.A1[i30][i31], this.pub_scalar[i31]));
                i31++;
            }
            sArr18[i30] = GF2Field.addElem(sArr18[i30], this.b1[i30]);
        }
        this.pub_singular = sArr17;
        this.pub_scalar = sArr18;
        compactPublicKey(sArr16);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i = 0;
        while (i < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.vi;
            int i2 = i + 1;
            layerArr[i] = new Layer(iArr[i], iArr[i2], this.sr);
            i = i2;
        }
    }

    private void generateL1() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1] - iArr[0];
        int[] iArr2 = new int[2];
        iArr2[1] = i;
        iArr2[0] = i;
        this.A1 = (short[][]) Array.newInstance(short.class, iArr2);
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A1[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.A1);
        }
        this.b1 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b1[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1];
        int[] iArr2 = new int[2];
        iArr2[1] = i;
        iArr2[0] = i;
        this.A2 = (short[][]) Array.newInstance(short.class, iArr2);
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A2inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A2[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.A2);
        }
        this.b2 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b2[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(new SecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        int[] iArr = this.vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.rainbowParams = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.sr = new SecureRandom();
        this.vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}
