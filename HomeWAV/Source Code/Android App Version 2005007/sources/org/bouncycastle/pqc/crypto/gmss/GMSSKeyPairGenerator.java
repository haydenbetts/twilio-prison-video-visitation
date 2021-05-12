package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;

public class GMSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.3";
    private int[] K;
    private byte[][] currentRootSigs;
    private byte[][] currentSeeds;
    private GMSSDigestProvider digestProvider;
    private GMSSParameters gmssPS;
    private GMSSKeyGenerationParameters gmssParams;
    private GMSSRandom gmssRandom;
    private int[] heightOfTrees;
    private boolean initialized = false;
    private int mdLength;
    private Digest messDigestTree;
    private byte[][] nextNextSeeds;
    private int numLayer;
    private int[] otsIndex;

    public GMSSKeyPairGenerator(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTree = digest;
        this.mdLength = digest.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTree);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0127 A[LOOP:3: B:34:0x0121->B:36:0x0127, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.bouncycastle.crypto.AsymmetricCipherKeyPair genKeyPair() {
        /*
            r20 = this;
            r1 = r20
            java.lang.Class<byte> r0 = byte.class
            boolean r2 = r1.initialized
            if (r2 != 0) goto L_0x000b
            r20.initializeDefault()
        L_0x000b:
            int r2 = r1.numLayer
            byte[][][] r6 = new byte[r2][][]
            int r3 = r2 + -1
            byte[][][] r7 = new byte[r3][][]
            org.bouncycastle.pqc.crypto.gmss.Treehash[][] r8 = new org.bouncycastle.pqc.crypto.gmss.Treehash[r2][]
            int r3 = r2 + -1
            org.bouncycastle.pqc.crypto.gmss.Treehash[][] r9 = new org.bouncycastle.pqc.crypto.gmss.Treehash[r3][]
            java.util.Vector[] r10 = new java.util.Vector[r2]
            int r3 = r2 + -1
            java.util.Vector[] r11 = new java.util.Vector[r3]
            java.util.Vector[][] r12 = new java.util.Vector[r2][]
            r3 = 1
            int r2 = r2 - r3
            java.util.Vector[][] r13 = new java.util.Vector[r2][]
            r2 = 0
            r4 = 0
        L_0x0027:
            int r5 = r1.numLayer
            r14 = 2
            if (r4 >= r5) goto L_0x0089
            int[] r5 = r1.heightOfTrees
            r5 = r5[r4]
            int r15 = r1.mdLength
            r16 = r13
            int[] r13 = new int[r14]
            r13[r3] = r15
            r13[r2] = r5
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r0, r13)
            byte[][] r5 = (byte[][]) r5
            r6[r4] = r5
            int[] r5 = r1.heightOfTrees
            r13 = r5[r4]
            int[] r15 = r1.K
            r15 = r15[r4]
            int r13 = r13 - r15
            org.bouncycastle.pqc.crypto.gmss.Treehash[] r13 = new org.bouncycastle.pqc.crypto.gmss.Treehash[r13]
            r8[r4] = r13
            if (r4 <= 0) goto L_0x0072
            int r13 = r4 + -1
            r5 = r5[r4]
            int r15 = r1.mdLength
            int[] r14 = new int[r14]
            r14[r3] = r15
            r14[r2] = r5
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r0, r14)
            byte[][] r5 = (byte[][]) r5
            r7[r13] = r5
            int[] r5 = r1.heightOfTrees
            r5 = r5[r4]
            int[] r14 = r1.K
            r14 = r14[r4]
            int r5 = r5 - r14
            org.bouncycastle.pqc.crypto.gmss.Treehash[] r5 = new org.bouncycastle.pqc.crypto.gmss.Treehash[r5]
            r9[r13] = r5
        L_0x0072:
            java.util.Vector r5 = new java.util.Vector
            r5.<init>()
            r10[r4] = r5
            if (r4 <= 0) goto L_0x0084
            int r5 = r4 + -1
            java.util.Vector r13 = new java.util.Vector
            r13.<init>()
            r11[r5] = r13
        L_0x0084:
            int r4 = r4 + 1
            r13 = r16
            goto L_0x0027
        L_0x0089:
            r16 = r13
            int r4 = r1.mdLength
            int[] r13 = new int[r14]
            r13[r3] = r4
            r13[r2] = r5
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r0, r13)
            byte[][] r4 = (byte[][]) r4
            int r5 = r1.numLayer
            int r5 = r5 - r3
            int r13 = r1.mdLength
            int[] r15 = new int[r14]
            r15[r3] = r13
            r15[r2] = r5
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r0, r15)
            r15 = r5
            byte[][] r15 = (byte[][]) r15
            int r5 = r1.numLayer
            int r13 = r1.mdLength
            r17 = r15
            int[] r15 = new int[r14]
            r15[r3] = r13
            r15[r2] = r5
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r0, r15)
            byte[][] r5 = (byte[][]) r5
            r13 = 0
        L_0x00be:
            int r15 = r1.numLayer
            if (r13 >= r15) goto L_0x00d2
            byte[][] r15 = r1.currentSeeds
            r15 = r15[r13]
            r14 = r5[r13]
            int r3 = r1.mdLength
            java.lang.System.arraycopy(r15, r2, r14, r2, r3)
            int r13 = r13 + 1
            r3 = 1
            r14 = 2
            goto L_0x00be
        L_0x00d2:
            int r15 = r15 - r3
            int r13 = r1.mdLength
            r19 = r9
            r14 = 2
            int[] r9 = new int[r14]
            r9[r3] = r13
            r9[r2] = r15
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r9)
            byte[][] r0 = (byte[][]) r0
            r1.currentRootSigs = r0
            int r0 = r1.numLayer
            int r0 = r0 - r3
            r3 = r0
        L_0x00ea:
            if (r3 < 0) goto L_0x0154
            org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc r9 = new org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc
            int[] r0 = r1.heightOfTrees
            r0 = r0[r3]
            int[] r13 = r1.K
            r13 = r13[r3]
            org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider r14 = r1.digestProvider
            r9.<init>(r0, r13, r14)
            int r0 = r1.numLayer     // Catch:{ Exception -> 0x011b }
            r13 = 1
            int r0 = r0 - r13
            if (r3 != r0) goto L_0x010c
            r0 = 0
            r14 = r10[r3]     // Catch:{ Exception -> 0x0119 }
            r15 = r5[r3]     // Catch:{ Exception -> 0x0119 }
            org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc r0 = r1.generateCurrentAuthpathAndRoot(r0, r14, r15, r3)     // Catch:{ Exception -> 0x0119 }
        L_0x010a:
            r9 = r0
            goto L_0x0120
        L_0x010c:
            int r0 = r3 + 1
            r0 = r4[r0]     // Catch:{ Exception -> 0x0119 }
            r14 = r10[r3]     // Catch:{ Exception -> 0x0119 }
            r15 = r5[r3]     // Catch:{ Exception -> 0x0119 }
            org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc r0 = r1.generateCurrentAuthpathAndRoot(r0, r14, r15, r3)     // Catch:{ Exception -> 0x0119 }
            goto L_0x010a
        L_0x0119:
            r0 = move-exception
            goto L_0x011d
        L_0x011b:
            r0 = move-exception
            r13 = 1
        L_0x011d:
            r0.printStackTrace()
        L_0x0120:
            r0 = 0
        L_0x0121:
            int[] r14 = r1.heightOfTrees
            r14 = r14[r3]
            if (r0 >= r14) goto L_0x013a
            byte[][] r14 = r9.getAuthPath()
            r14 = r14[r0]
            r15 = r6[r3]
            r15 = r15[r0]
            int r13 = r1.mdLength
            java.lang.System.arraycopy(r14, r2, r15, r2, r13)
            int r0 = r0 + 1
            r13 = 1
            goto L_0x0121
        L_0x013a:
            java.util.Vector[] r0 = r9.getRetain()
            r12[r3] = r0
            org.bouncycastle.pqc.crypto.gmss.Treehash[] r0 = r9.getTreehash()
            r8[r3] = r0
            byte[] r0 = r9.getRoot()
            r9 = r4[r3]
            int r13 = r1.mdLength
            java.lang.System.arraycopy(r0, r2, r9, r2, r13)
            int r3 = r3 + -1
            goto L_0x00ea
        L_0x0154:
            int r0 = r1.numLayer
            r3 = 2
            int r0 = r0 - r3
        L_0x0158:
            if (r0 < 0) goto L_0x01aa
            r3 = r11[r0]
            int r9 = r0 + 1
            r13 = r5[r9]
            org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc r3 = r1.generateNextAuthpathAndRoot(r3, r13, r9)
            r13 = 0
        L_0x0165:
            int[] r14 = r1.heightOfTrees
            r14 = r14[r9]
            if (r13 >= r14) goto L_0x0181
            byte[][] r14 = r3.getAuthPath()
            r14 = r14[r13]
            r15 = r7[r0]
            r15 = r15[r13]
            r18 = r12
            int r12 = r1.mdLength
            java.lang.System.arraycopy(r14, r2, r15, r2, r12)
            int r13 = r13 + 1
            r12 = r18
            goto L_0x0165
        L_0x0181:
            r18 = r12
            java.util.Vector[] r12 = r3.getRetain()
            r16[r0] = r12
            org.bouncycastle.pqc.crypto.gmss.Treehash[] r12 = r3.getTreehash()
            r19[r0] = r12
            byte[] r3 = r3.getRoot()
            r12 = r17[r0]
            int r13 = r1.mdLength
            java.lang.System.arraycopy(r3, r2, r12, r2, r13)
            r3 = r5[r9]
            byte[][] r9 = r1.nextNextSeeds
            r9 = r9[r0]
            int r12 = r1.mdLength
            java.lang.System.arraycopy(r3, r2, r9, r2, r12)
            int r0 = r0 + -1
            r12 = r18
            goto L_0x0158
        L_0x01aa:
            r18 = r12
            org.bouncycastle.pqc.crypto.gmss.GMSSPublicKeyParameters r0 = new org.bouncycastle.pqc.crypto.gmss.GMSSPublicKeyParameters
            r2 = r4[r2]
            org.bouncycastle.pqc.crypto.gmss.GMSSParameters r3 = r1.gmssPS
            r0.<init>(r2, r3)
            org.bouncycastle.pqc.crypto.gmss.GMSSPrivateKeyParameters r2 = new org.bouncycastle.pqc.crypto.gmss.GMSSPrivateKeyParameters
            byte[][] r4 = r1.currentSeeds
            byte[][] r5 = r1.nextNextSeeds
            byte[][] r15 = r1.currentRootSigs
            org.bouncycastle.pqc.crypto.gmss.GMSSParameters r14 = r1.gmssPS
            org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider r13 = r1.digestProvider
            r3 = r2
            r9 = r19
            r18 = r13
            r13 = r16
            r16 = r14
            r14 = r17
            r17 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            org.bouncycastle.crypto.AsymmetricCipherKeyPair r3 = new org.bouncycastle.crypto.AsymmetricCipherKeyPair
            r3.<init>((org.bouncycastle.crypto.params.AsymmetricKeyParameter) r0, (org.bouncycastle.crypto.params.AsymmetricKeyParameter) r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gmss.GMSSKeyPairGenerator.genKeyPair():org.bouncycastle.crypto.AsymmetricCipherKeyPair");
    }

    private GMSSRootCalc generateCurrentAuthpathAndRoot(byte[] bArr, Vector vector, byte[] bArr2, int i) {
        byte[] bArr3;
        int i2 = this.mdLength;
        byte[] bArr4 = new byte[i2];
        byte[] bArr5 = new byte[i2];
        byte[] nextSeed = this.gmssRandom.nextSeed(bArr2);
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i], this.K[i], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        if (i == this.numLayer - 1) {
            bArr3 = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[i]).getPublicKey();
        } else {
            this.currentRootSigs[i] = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[i]).getSignature(bArr);
            bArr3 = new WinternitzOTSVerify(this.digestProvider.get(), this.otsIndex[i]).Verify(bArr, this.currentRootSigs[i]);
        }
        gMSSRootCalc.update(bArr3);
        int i3 = 3;
        int i4 = 0;
        int i5 = 1;
        while (true) {
            int[] iArr = this.heightOfTrees;
            if (i5 >= (1 << iArr[i])) {
                break;
            }
            if (i5 == i3 && i4 < iArr[i] - this.K[i]) {
                gMSSRootCalc.initializeTreehashSeed(bArr2, i4);
                i3 *= 2;
                i4++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr2), this.digestProvider.get(), this.otsIndex[i]).getPublicKey());
            i5++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private GMSSRootCalc generateNextAuthpathAndRoot(Vector vector, byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.numLayer];
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i], this.K[i], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        int i2 = 0;
        int i3 = 0;
        int i4 = 3;
        while (true) {
            int[] iArr = this.heightOfTrees;
            if (i2 >= (1 << iArr[i])) {
                break;
            }
            if (i2 == i4 && i3 < iArr[i] - this.K[i]) {
                gMSSRootCalc.initializeTreehashSeed(bArr, i3);
                i4 *= 2;
                i3++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr), this.digestProvider.get(), this.otsIndex[i]).getPublicKey());
            i2++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("N�chster Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private void initializeDefault() {
        initialize(new GMSSKeyGenerationParameters(new SecureRandom(), new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2})));
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(int i, SecureRandom secureRandom) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters;
        if (i <= 10) {
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(1, new int[]{10}, new int[]{3}, new int[]{2}));
        } else {
            gMSSKeyGenerationParameters = i <= 20 ? new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2})) : new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2}));
        }
        initialize(gMSSKeyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        Class<byte> cls = byte.class;
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters = (GMSSKeyGenerationParameters) keyGenerationParameters;
        this.gmssParams = gMSSKeyGenerationParameters;
        GMSSParameters gMSSParameters = new GMSSParameters(gMSSKeyGenerationParameters.getParameters().getNumOfLayers(), this.gmssParams.getParameters().getHeightOfTrees(), this.gmssParams.getParameters().getWinternitzParameter(), this.gmssParams.getParameters().getK());
        this.gmssPS = gMSSParameters;
        this.numLayer = gMSSParameters.getNumOfLayers();
        this.heightOfTrees = this.gmssPS.getHeightOfTrees();
        this.otsIndex = this.gmssPS.getWinternitzParameter();
        this.K = this.gmssPS.getK();
        int i = this.numLayer;
        int[] iArr = new int[2];
        iArr[1] = this.mdLength;
        iArr[0] = i;
        this.currentSeeds = (byte[][]) Array.newInstance(cls, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = this.mdLength;
        iArr2[0] = this.numLayer - 1;
        this.nextNextSeeds = (byte[][]) Array.newInstance(cls, iArr2);
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < this.numLayer; i2++) {
            secureRandom.nextBytes(this.currentSeeds[i2]);
            this.gmssRandom.nextSeed(this.currentSeeds[i2]);
        }
        this.initialized = true;
    }
}
