package com.pusher.client.crypto.nacl;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.UByte;

public final class TweetNaclFast {
    private static final byte[] sigma = {101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107};

    public static final class SecretBox {
        public static final int boxzerobytesLength = 16;
        public static final int nonceLength = 24;
        public static final int zerobytesLength = 32;
        private byte[] key;
        private AtomicLong nonce;

        public SecretBox(byte[] bArr) {
            this(bArr, 68);
        }

        public SecretBox(byte[] bArr, long j) {
            this.key = bArr;
            this.nonce = new AtomicLong(j);
        }

        public byte[] open(byte[] bArr, byte[] bArr2) {
            if (bArr == null) {
                return null;
            }
            return open(bArr, 0, bArr.length, bArr2);
        }

        public byte[] open(byte[] bArr, int i, int i2, byte[] bArr2) {
            if (bArr == null || bArr.length < i + i2 || i2 < 16 || bArr2 == null || bArr2.length != 24) {
                return null;
            }
            int i3 = i2 + 16;
            byte[] bArr3 = new byte[i3];
            byte[] bArr4 = new byte[i3];
            for (int i4 = 0; i4 < i2; i4++) {
                bArr3[i4 + 16] = bArr[i4 + i];
            }
            if (TweetNaclFast.crypto_secretbox_open(bArr4, bArr3, i3, bArr2, this.key) != 0) {
                return null;
            }
            int i5 = i3 - 32;
            byte[] bArr5 = new byte[i5];
            for (int i6 = 0; i6 < i5; i6++) {
                bArr5[i6] = bArr4[i6 + 32];
            }
            return bArr5;
        }
    }

    private static int vn(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte b = 0;
        for (int i4 = 0; i4 < i3; i4++) {
            b |= (bArr[i4 + i] ^ bArr2[i4 + i2]) & UByte.MAX_VALUE;
        }
        return (((b - 1) >>> 8) & 1) - 1;
    }

    private static int crypto_verify_16(byte[] bArr, int i, byte[] bArr2, int i2) {
        return vn(bArr, i, bArr2, i2, 16);
    }

    /* JADX WARNING: type inference failed for: r44v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r45v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r46v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r1v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r5v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r7v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r7v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v6, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r5v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v6, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v15, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v18, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v8, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v11, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v14, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v17, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v20, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v21, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v24, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v27, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v30, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v33, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v34, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v35, types: [byte] */
    /* JADX WARNING: type inference failed for: r11v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v15, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v18, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v40, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v41, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v26, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v43, types: [byte] */
    /* JADX WARNING: type inference failed for: r4v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v47, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v51, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v30, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v54, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v35, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v58, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v62, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v66, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v70, types: [byte] */
    /* JADX WARNING: type inference failed for: r8v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v73, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v77, types: [byte] */
    /* JADX WARNING: type inference failed for: r8v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v81, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v40, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v44, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void core_salsa20(byte[] r43, byte[] r44, byte[] r45, byte[] r46) {
        /*
            r0 = 0
            byte r1 = r46[r0]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 1
            byte r3 = r46[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r4 = 8
            int r3 = r3 << r4
            r1 = r1 | r3
            r3 = 2
            byte r5 = r46[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r6 = 16
            int r5 = r5 << r6
            r1 = r1 | r5
            r5 = 3
            byte r7 = r46[r5]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r8 = 24
            int r7 = r7 << r8
            r1 = r1 | r7
            byte r7 = r45[r0]
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r9 = r45[r2]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r4
            r7 = r7 | r9
            byte r9 = r45[r3]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r6
            r7 = r7 | r9
            byte r9 = r45[r5]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r8
            r7 = r7 | r9
            r9 = 4
            byte r10 = r45[r9]
            r10 = r10 & 255(0xff, float:3.57E-43)
            r11 = 5
            byte r12 = r45[r11]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << r4
            r10 = r10 | r12
            r12 = 6
            byte r13 = r45[r12]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r6
            r10 = r10 | r13
            r13 = 7
            byte r14 = r45[r13]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r8
            r10 = r10 | r14
            byte r14 = r45[r4]
            r14 = r14 & 255(0xff, float:3.57E-43)
            r15 = 9
            byte r5 = r45[r15]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << r4
            r5 = r5 | r14
            r14 = 10
            byte r15 = r45[r14]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r5 = r5 | r15
            r15 = 11
            byte r14 = r45[r15]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r8
            r5 = r5 | r14
            r14 = 12
            byte r15 = r45[r14]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r17 = 13
            byte r14 = r45[r17]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r4
            r14 = r14 | r15
            r15 = 14
            byte r3 = r45[r15]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r6
            r3 = r3 | r14
            r14 = 15
            byte r14 = r45[r14]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r8
            r3 = r3 | r14
            byte r14 = r46[r9]
            r14 = r14 & 255(0xff, float:3.57E-43)
            byte r15 = r46[r11]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r14 = r14 | r15
            byte r15 = r46[r12]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r14 = r14 | r15
            byte r15 = r46[r13]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r14 = r14 | r15
            byte r15 = r44[r0]
            r15 = r15 & 255(0xff, float:3.57E-43)
            byte r0 = r44[r2]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << r4
            r0 = r0 | r15
            r15 = 2
            byte r2 = r44[r15]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r0 = r0 | r2
            r2 = 3
            byte r15 = r44[r2]
            r2 = r15 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r8
            r0 = r0 | r2
            byte r2 = r44[r9]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r15 = r44[r11]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r2 = r2 | r15
            byte r15 = r44[r12]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r2 = r2 | r15
            byte r15 = r44[r13]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r2 = r2 | r15
            byte r15 = r44[r4]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r16 = 9
            byte r13 = r44[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r4
            r13 = r13 | r15
            r15 = 10
            byte r12 = r44[r15]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << r6
            r12 = r12 | r13
            r13 = 11
            byte r15 = r44[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r8
            r12 = r12 | r13
            r13 = 12
            byte r15 = r44[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            byte r15 = r44[r17]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r13 = r13 | r15
            r15 = 14
            byte r11 = r44[r15]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << r6
            r11 = r11 | r13
            r13 = 15
            byte r13 = r44[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r8
            r11 = r11 | r13
            byte r13 = r46[r4]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r15 = 9
            byte r9 = r46[r15]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r4
            r9 = r9 | r13
            r13 = 10
            byte r15 = r46[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r6
            r9 = r9 | r13
            r13 = 11
            byte r15 = r46[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r8
            r9 = r9 | r13
            byte r13 = r45[r6]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r15 = 17
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r13 = r13 | r15
            r15 = 18
            byte r4 = r45[r15]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r6
            r4 = r4 | r13
            r13 = 19
            byte r15 = r45[r13]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r4 = r4 | r15
            r15 = 20
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r22 = 21
            byte r13 = r45[r22]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r21 = 8
            int r13 = r13 << 8
            r13 = r13 | r15
            r15 = 22
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r13 = r13 | r15
            r15 = 23
            byte r6 = r45[r15]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r8
            r6 = r6 | r13
            byte r13 = r45[r8]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r24 = 25
            byte r15 = r45[r24]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r21 = 8
            int r15 = r15 << 8
            r13 = r13 | r15
            r15 = 26
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r22 = 16
            int r15 = r15 << 16
            r13 = r13 | r15
            r15 = 27
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r13 = r13 | r15
            r15 = 28
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r26 = 29
            byte r8 = r45[r26]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r21 = 8
            int r8 = r8 << 8
            r8 = r8 | r15
            r15 = 30
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r22 = 16
            int r15 = r15 << 16
            r8 = r8 | r15
            r15 = 31
            byte r15 = r45[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r26 = 24
            int r15 = r15 << 24
            r8 = r8 | r15
            r45 = r8
            r15 = 12
            byte r8 = r46[r15]
            r8 = r8 & 255(0xff, float:3.57E-43)
            byte r15 = r46[r17]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r21 = 8
            int r15 = r15 << 8
            r8 = r8 | r15
            r26 = r13
            r15 = 14
            byte r13 = r46[r15]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r15 = 16
            int r13 = r13 << r15
            r8 = r8 | r13
            r13 = 15
            byte r13 = r46[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r15 = 24
            int r13 = r13 << r15
            r8 = r8 | r13
            r40 = r45
            r32 = r0
            r15 = r1
            r33 = r2
            r30 = r3
            r37 = r4
            r29 = r5
            r38 = r6
            r27 = r7
            r46 = r8
            r41 = r46
            r36 = r9
            r28 = r10
            r35 = r11
            r34 = r12
            r31 = r14
            r39 = r26
            r13 = 0
        L_0x01f1:
            r8 = 20
            if (r13 >= r8) goto L_0x03b5
            int r8 = r15 + r38
            r20 = 0
            r8 = r8 | 0
            int r42 = r8 << 7
            int r8 = r8 >>> 25
            r8 = r42 | r8
            r8 = r30 ^ r8
            int r30 = r8 + r15
            r30 = r30 | 0
            int r42 = r30 << 9
            r25 = 23
            int r30 = r30 >>> 23
            r30 = r42 | r30
            r30 = r34 ^ r30
            int r34 = r30 + r8
            r34 = r34 | 0
            int r42 = r34 << 13
            r23 = 19
            int r34 = r34 >>> 19
            r34 = r42 | r34
            r34 = r38 ^ r34
            int r38 = r34 + r30
            r38 = r38 | 0
            int r42 = r38 << 18
            r19 = 14
            int r38 = r38 >>> 14
            r38 = r42 | r38
            r15 = r15 ^ r38
            int r38 = r31 + r27
            r38 = r38 | 0
            int r42 = r38 << 7
            int r38 = r38 >>> 25
            r38 = r42 | r38
            r35 = r35 ^ r38
            int r38 = r35 + r31
            r38 = r38 | 0
            int r42 = r38 << 9
            r25 = 23
            int r38 = r38 >>> 23
            r38 = r42 | r38
            r38 = r39 ^ r38
            int r39 = r38 + r35
            r39 = r39 | 0
            int r42 = r39 << 13
            r23 = 19
            int r39 = r39 >>> 19
            r39 = r42 | r39
            r27 = r27 ^ r39
            int r39 = r27 + r38
            r39 = r39 | 0
            int r42 = r39 << 18
            r19 = 14
            int r39 = r39 >>> 14
            r39 = r42 | r39
            r31 = r31 ^ r39
            int r39 = r36 + r32
            r39 = r39 | 0
            int r42 = r39 << 7
            int r39 = r39 >>> 25
            r39 = r42 | r39
            r39 = r40 ^ r39
            int r40 = r39 + r36
            r40 = r40 | 0
            int r42 = r40 << 9
            r25 = 23
            int r40 = r40 >>> 23
            r40 = r42 | r40
            r28 = r28 ^ r40
            int r40 = r28 + r39
            r40 = r40 | 0
            int r42 = r40 << 13
            r23 = 19
            int r40 = r40 >>> 19
            r40 = r42 | r40
            r32 = r32 ^ r40
            int r40 = r32 + r28
            r40 = r40 | 0
            int r42 = r40 << 18
            r19 = 14
            int r40 = r40 >>> 14
            r40 = r42 | r40
            r36 = r36 ^ r40
            int r40 = r41 + r37
            r40 = r40 | 0
            int r42 = r40 << 7
            int r40 = r40 >>> 25
            r40 = r42 | r40
            r29 = r29 ^ r40
            int r40 = r29 + r41
            r40 = r40 | 0
            int r42 = r40 << 9
            r25 = 23
            int r40 = r40 >>> 23
            r40 = r42 | r40
            r33 = r33 ^ r40
            int r40 = r33 + r29
            r40 = r40 | 0
            int r42 = r40 << 13
            r23 = 19
            int r40 = r40 >>> 19
            r40 = r42 | r40
            r37 = r37 ^ r40
            int r40 = r37 + r33
            r40 = r40 | 0
            int r42 = r40 << 18
            r19 = 14
            int r40 = r40 >>> 14
            r40 = r42 | r40
            r40 = r41 ^ r40
            int r41 = r15 + r29
            r41 = r41 | 0
            int r42 = r41 << 7
            int r41 = r41 >>> 25
            r41 = r42 | r41
            r27 = r27 ^ r41
            int r41 = r27 + r15
            r41 = r41 | 0
            int r42 = r41 << 9
            r25 = 23
            int r41 = r41 >>> 23
            r41 = r42 | r41
            r28 = r28 ^ r41
            int r41 = r28 + r27
            r41 = r41 | 0
            int r42 = r41 << 13
            r23 = 19
            int r41 = r41 >>> 19
            r41 = r42 | r41
            r29 = r29 ^ r41
            int r41 = r29 + r28
            r41 = r41 | 0
            int r42 = r41 << 18
            r19 = 14
            int r41 = r41 >>> 14
            r41 = r42 | r41
            r15 = r15 ^ r41
            int r41 = r31 + r8
            r41 = r41 | 0
            int r42 = r41 << 7
            int r41 = r41 >>> 25
            r41 = r42 | r41
            r32 = r32 ^ r41
            int r41 = r32 + r31
            r41 = r41 | 0
            int r42 = r41 << 9
            r25 = 23
            int r41 = r41 >>> 23
            r41 = r42 | r41
            r33 = r33 ^ r41
            int r41 = r33 + r32
            r41 = r41 | 0
            int r42 = r41 << 13
            r23 = 19
            int r41 = r41 >>> 19
            r41 = r42 | r41
            r8 = r8 ^ r41
            int r41 = r8 + r33
            r41 = r41 | 0
            int r42 = r41 << 18
            r19 = 14
            int r41 = r41 >>> 14
            r41 = r42 | r41
            r31 = r31 ^ r41
            int r41 = r36 + r35
            r41 = r41 | 0
            int r42 = r41 << 7
            int r41 = r41 >>> 25
            r41 = r42 | r41
            r37 = r37 ^ r41
            int r41 = r37 + r36
            r41 = r41 | 0
            int r42 = r41 << 9
            r25 = 23
            int r41 = r41 >>> 23
            r41 = r42 | r41
            r30 = r30 ^ r41
            int r41 = r30 + r37
            r41 = r41 | 0
            int r42 = r41 << 13
            r23 = 19
            int r41 = r41 >>> 19
            r41 = r42 | r41
            r35 = r35 ^ r41
            int r41 = r35 + r30
            r41 = r41 | 0
            int r42 = r41 << 18
            r19 = 14
            int r41 = r41 >>> 14
            r41 = r42 | r41
            r36 = r36 ^ r41
            int r41 = r40 + r39
            r41 = r41 | 0
            int r42 = r41 << 7
            int r41 = r41 >>> 25
            r41 = r42 | r41
            r34 = r34 ^ r41
            int r41 = r34 + r40
            r41 = r41 | 0
            int r42 = r41 << 9
            r25 = 23
            int r41 = r41 >>> 23
            r41 = r42 | r41
            r38 = r38 ^ r41
            int r41 = r38 + r34
            r41 = r41 | 0
            int r42 = r41 << 13
            r23 = 19
            int r41 = r41 >>> 19
            r41 = r42 | r41
            r39 = r39 ^ r41
            int r41 = r39 + r38
            r41 = r41 | 0
            int r42 = r41 << 18
            r19 = 14
            int r41 = r41 >>> 14
            r41 = r42 | r41
            r41 = r40 ^ r41
            int r13 = r13 + 2
            r40 = r39
            r39 = r38
            r38 = r34
            r34 = r30
            r30 = r8
            goto L_0x01f1
        L_0x03b5:
            r20 = 0
            int r15 = r15 + r1
            r1 = r15 | 0
            int r27 = r27 + r7
            r7 = r27 | 0
            int r28 = r28 + r10
            r8 = r28 | 0
            int r29 = r29 + r5
            r5 = r29 | 0
            int r30 = r30 + r3
            r3 = r30 | 0
            int r31 = r31 + r14
            r10 = r31 | 0
            int r32 = r32 + r0
            r0 = r32 | 0
            int r33 = r33 + r2
            r2 = r33 | 0
            int r34 = r34 + r12
            r12 = r34 | 0
            int r35 = r35 + r11
            r11 = r35 | 0
            int r36 = r36 + r9
            r9 = r36 | 0
            int r37 = r37 + r4
            r4 = r37 | 0
            int r38 = r38 + r6
            r6 = r38 | 0
            int r39 = r39 + r26
            r13 = r39 | 0
            int r40 = r40 + r45
            r14 = r40 | 0
            int r41 = r41 + r46
            r15 = r41 | 0
            r45 = r15
            int r15 = r1 >>> 0
            r15 = r15 & 255(0xff, float:3.57E-43)
            byte r15 = (byte) r15
            r43[r20] = r15
            int r15 = r1 >>> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            byte r15 = (byte) r15
            r20 = 1
            r43[r20] = r15
            int r15 = r1 >>> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            byte r15 = (byte) r15
            r18 = 2
            r43[r18] = r15
            r15 = 24
            int r1 = r1 >>> r15
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r15 = 3
            r43[r15] = r1
            int r1 = r7 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r15 = 4
            r43[r15] = r1
            int r1 = r7 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r15 = 5
            r43[r15] = r1
            int r1 = r7 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r15 = 6
            r43[r15] = r1
            r1 = 24
            int r7 = r7 >>> r1
            r1 = r7 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 7
            r43[r7] = r1
            int r1 = r8 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 8
            r43[r7] = r1
            int r1 = r8 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 9
            r43[r7] = r1
            int r1 = r8 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 10
            r43[r7] = r1
            r1 = 24
            int r7 = r8 >>> 24
            r1 = r7 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 11
            r43[r7] = r1
            int r1 = r5 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 12
            r43[r7] = r1
            int r1 = r5 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r17] = r1
            int r1 = r5 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r7 = 14
            r43[r7] = r1
            r1 = 15
            r7 = 24
            int r5 = r5 >>> r7
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r43[r1] = r5
            int r1 = r3 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r5 = 16
            r43[r5] = r1
            r1 = 17
            int r5 = r3 >>> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r43[r1] = r5
            int r1 = r3 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r5 = 18
            r43[r5] = r1
            r1 = 24
            int r3 = r3 >>> r1
            r1 = r3 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r3 = 19
            r43[r3] = r1
            r1 = 20
            int r3 = r10 >>> 0
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r43[r1] = r3
            r1 = 21
            int r3 = r10 >>> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r43[r1] = r3
            r1 = 22
            int r3 = r10 >>> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r43[r1] = r3
            r1 = 24
            int r3 = r10 >>> 24
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r5 = 23
            r43[r5] = r3
            int r3 = r0 >>> 0
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r43[r1] = r3
            int r1 = r0 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r24] = r1
            r1 = 26
            int r3 = r0 >>> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r43[r1] = r3
            r1 = 27
            r3 = 24
            int r0 = r0 >>> r3
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r43[r1] = r0
            r0 = 28
            int r1 = r2 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 29
            int r1 = r2 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 30
            int r1 = r2 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 31
            r1 = 24
            int r2 = r2 >>> r1
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 32
            int r1 = r12 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 33
            int r1 = r12 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 34
            int r1 = r12 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 35
            r1 = 24
            int r2 = r12 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 36
            int r1 = r11 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 37
            int r1 = r11 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 38
            int r1 = r11 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 39
            r1 = 24
            int r2 = r11 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 40
            int r1 = r9 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 41
            int r1 = r9 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 42
            int r1 = r9 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 43
            r1 = 24
            int r2 = r9 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 44
            int r1 = r4 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 45
            int r1 = r4 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 46
            int r1 = r4 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 47
            r1 = 24
            int r2 = r4 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 48
            int r1 = r6 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 49
            int r1 = r6 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 50
            int r1 = r6 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 51
            r1 = 24
            int r2 = r6 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 52
            int r1 = r13 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 53
            int r1 = r13 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 54
            int r1 = r13 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 55
            r1 = 24
            int r2 = r13 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 56
            int r1 = r14 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 57
            int r1 = r14 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 58
            int r1 = r14 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 59
            r1 = 24
            int r2 = r14 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 60
            int r1 = r45 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 61
            int r1 = r45 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 62
            int r1 = r45 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            r0 = 63
            r1 = 24
            int r1 = r45 >>> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r43[r0] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusher.client.crypto.nacl.TweetNaclFast.core_salsa20(byte[], byte[], byte[], byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r29v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r30v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r31v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r1v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r5v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r7v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r7v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v6, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v3, types: [byte] */
    /* JADX WARNING: type inference failed for: r5v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v6, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v15, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v18, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v8, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v11, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v14, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v17, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v20, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v21, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v24, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v27, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v30, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v33, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v34, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v35, types: [byte] */
    /* JADX WARNING: type inference failed for: r11v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v15, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v18, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v10, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v40, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v41, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v26, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v43, types: [byte] */
    /* JADX WARNING: type inference failed for: r4v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v47, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v51, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v30, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v54, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v35, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v58, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v62, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v66, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v70, types: [byte] */
    /* JADX WARNING: type inference failed for: r8v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v73, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v77, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v81, types: [byte] */
    /* JADX WARNING: type inference failed for: r1v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r1v9, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void core_hsalsa20(byte[] r28, byte[] r29, byte[] r30, byte[] r31) {
        /*
            r0 = 0
            byte r1 = r31[r0]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 1
            byte r3 = r31[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r4 = 8
            int r3 = r3 << r4
            r1 = r1 | r3
            r3 = 2
            byte r5 = r31[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r6 = 16
            int r5 = r5 << r6
            r1 = r1 | r5
            r5 = 3
            byte r7 = r31[r5]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r8 = 24
            int r7 = r7 << r8
            r1 = r1 | r7
            byte r7 = r30[r0]
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r9 = r30[r2]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r4
            r7 = r7 | r9
            byte r9 = r30[r3]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r6
            r7 = r7 | r9
            byte r9 = r30[r5]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r8
            r7 = r7 | r9
            r9 = 4
            byte r10 = r30[r9]
            r10 = r10 & 255(0xff, float:3.57E-43)
            r11 = 5
            byte r12 = r30[r11]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << r4
            r10 = r10 | r12
            r12 = 6
            byte r13 = r30[r12]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r6
            r10 = r10 | r13
            r13 = 7
            byte r14 = r30[r13]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r8
            r10 = r10 | r14
            byte r14 = r30[r4]
            r14 = r14 & 255(0xff, float:3.57E-43)
            r15 = 9
            byte r5 = r30[r15]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << r4
            r5 = r5 | r14
            r14 = 10
            byte r15 = r30[r14]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r5 = r5 | r15
            r15 = 11
            byte r14 = r30[r15]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r8
            r5 = r5 | r14
            r14 = 12
            byte r15 = r30[r14]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r17 = 13
            byte r14 = r30[r17]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r4
            r14 = r14 | r15
            r15 = 14
            byte r3 = r30[r15]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r6
            r3 = r3 | r14
            r14 = 15
            byte r14 = r30[r14]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r8
            r3 = r3 | r14
            byte r14 = r31[r9]
            r14 = r14 & 255(0xff, float:3.57E-43)
            byte r15 = r31[r11]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r14 = r14 | r15
            byte r15 = r31[r12]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r14 = r14 | r15
            byte r15 = r31[r13]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r14 = r14 | r15
            byte r15 = r29[r0]
            r15 = r15 & 255(0xff, float:3.57E-43)
            byte r0 = r29[r2]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << r4
            r0 = r0 | r15
            r15 = 2
            byte r2 = r29[r15]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r0 = r0 | r2
            r2 = 3
            byte r15 = r29[r2]
            r2 = r15 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r8
            r0 = r0 | r2
            byte r2 = r29[r9]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r15 = r29[r11]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r2 = r2 | r15
            byte r15 = r29[r12]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r2 = r2 | r15
            byte r15 = r29[r13]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r2 = r2 | r15
            byte r15 = r29[r4]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r16 = 9
            byte r13 = r29[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r4
            r13 = r13 | r15
            r15 = 10
            byte r12 = r29[r15]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << r6
            r12 = r12 | r13
            r13 = 11
            byte r15 = r29[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r8
            r12 = r12 | r13
            r13 = 12
            byte r15 = r29[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            byte r15 = r29[r17]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r13 = r13 | r15
            r15 = 14
            byte r11 = r29[r15]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << r6
            r11 = r11 | r13
            r13 = 15
            byte r13 = r29[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r8
            r11 = r11 | r13
            byte r13 = r31[r4]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r15 = 9
            byte r9 = r31[r15]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << r4
            r9 = r9 | r13
            r13 = 10
            byte r15 = r31[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r6
            r9 = r9 | r13
            r13 = 11
            byte r15 = r31[r13]
            r13 = r15 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r8
            r9 = r9 | r13
            byte r13 = r30[r6]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r15 = 17
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r4
            r13 = r13 | r15
            r15 = 18
            byte r4 = r30[r15]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r6
            r4 = r4 | r13
            r13 = 19
            byte r15 = r30[r13]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r4 = r4 | r15
            r15 = 20
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r21 = 21
            byte r13 = r30[r21]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r20 = 8
            int r13 = r13 << 8
            r13 = r13 | r15
            r15 = 22
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r6
            r13 = r13 | r15
            r15 = 23
            byte r6 = r30[r15]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r8
            r6 = r6 | r13
            byte r13 = r30[r8]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r23 = 25
            byte r15 = r30[r23]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r20 = 8
            int r15 = r15 << 8
            r13 = r13 | r15
            r15 = 26
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r21 = 16
            int r15 = r15 << 16
            r13 = r13 | r15
            r15 = 27
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << r8
            r13 = r13 | r15
            r15 = 28
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r25 = 29
            byte r8 = r30[r25]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r20 = 8
            int r8 = r8 << 8
            r8 = r8 | r15
            r15 = 30
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r21 = 16
            int r15 = r15 << 16
            r8 = r8 | r15
            r15 = 31
            byte r15 = r30[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r25 = 24
            int r15 = r15 << 24
            r8 = r8 | r15
            r25 = r0
            r15 = 12
            byte r0 = r31[r15]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r15 = r31[r17]
            r15 = r15 & 255(0xff, float:3.57E-43)
            r20 = 8
            int r15 = r15 << 8
            r0 = r0 | r15
            r26 = r1
            r15 = 14
            byte r1 = r31[r15]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r15 = 16
            int r1 = r1 << r15
            r0 = r0 | r1
            r1 = 15
            byte r1 = r31[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r15 = 24
            int r1 = r1 << r15
            r0 = r0 | r1
            r30 = r2
            r1 = r26
            r15 = 0
        L_0x01d4:
            r2 = 20
            if (r15 >= r2) goto L_0x038c
            int r2 = r1 + r6
            r19 = 0
            r2 = r2 | 0
            int r26 = r2 << 7
            int r2 = r2 >>> 25
            r2 = r26 | r2
            r2 = r2 ^ r3
            int r3 = r2 + r1
            r3 = r3 | 0
            int r26 = r3 << 9
            r24 = 23
            int r3 = r3 >>> 23
            r3 = r26 | r3
            r3 = r3 ^ r12
            int r12 = r3 + r2
            r12 = r12 | 0
            int r26 = r12 << 13
            r22 = 19
            int r12 = r12 >>> 19
            r12 = r26 | r12
            r6 = r6 ^ r12
            int r12 = r6 + r3
            r12 = r12 | 0
            int r26 = r12 << 18
            r18 = 14
            int r12 = r12 >>> 14
            r12 = r26 | r12
            r1 = r1 ^ r12
            int r12 = r14 + r7
            r12 = r12 | 0
            int r26 = r12 << 7
            int r12 = r12 >>> 25
            r12 = r26 | r12
            r11 = r11 ^ r12
            int r12 = r11 + r14
            r12 = r12 | 0
            int r26 = r12 << 9
            r24 = 23
            int r12 = r12 >>> 23
            r12 = r26 | r12
            r12 = r12 ^ r13
            int r13 = r12 + r11
            r13 = r13 | 0
            int r26 = r13 << 13
            r22 = 19
            int r13 = r13 >>> 19
            r13 = r26 | r13
            r7 = r7 ^ r13
            int r13 = r7 + r12
            r13 = r13 | 0
            int r26 = r13 << 18
            r18 = 14
            int r13 = r13 >>> 14
            r13 = r26 | r13
            r13 = r13 ^ r14
            int r14 = r9 + r25
            r14 = r14 | 0
            int r26 = r14 << 7
            int r14 = r14 >>> 25
            r14 = r26 | r14
            r8 = r8 ^ r14
            int r14 = r8 + r9
            r14 = r14 | 0
            int r26 = r14 << 9
            r24 = 23
            int r14 = r14 >>> 23
            r14 = r26 | r14
            r10 = r10 ^ r14
            int r14 = r10 + r8
            r14 = r14 | 0
            int r26 = r14 << 13
            r22 = 19
            int r14 = r14 >>> 19
            r14 = r26 | r14
            r14 = r25 ^ r14
            int r25 = r14 + r10
            r25 = r25 | 0
            int r26 = r25 << 18
            r18 = 14
            int r25 = r25 >>> 14
            r25 = r26 | r25
            r9 = r9 ^ r25
            int r25 = r0 + r4
            r25 = r25 | 0
            int r26 = r25 << 7
            int r25 = r25 >>> 25
            r25 = r26 | r25
            r5 = r5 ^ r25
            int r25 = r5 + r0
            r25 = r25 | 0
            int r26 = r25 << 9
            r24 = 23
            int r25 = r25 >>> 23
            r25 = r26 | r25
            r25 = r30 ^ r25
            int r26 = r25 + r5
            r26 = r26 | 0
            int r27 = r26 << 13
            r22 = 19
            int r26 = r26 >>> 19
            r26 = r27 | r26
            r4 = r4 ^ r26
            int r26 = r4 + r25
            r26 = r26 | 0
            int r27 = r26 << 18
            r18 = 14
            int r26 = r26 >>> 14
            r26 = r27 | r26
            r0 = r0 ^ r26
            int r26 = r1 + r5
            r26 = r26 | 0
            int r27 = r26 << 7
            int r26 = r26 >>> 25
            r26 = r27 | r26
            r7 = r7 ^ r26
            int r26 = r7 + r1
            r26 = r26 | 0
            int r27 = r26 << 9
            r24 = 23
            int r26 = r26 >>> 23
            r26 = r27 | r26
            r10 = r10 ^ r26
            int r26 = r10 + r7
            r26 = r26 | 0
            int r27 = r26 << 13
            r22 = 19
            int r26 = r26 >>> 19
            r26 = r27 | r26
            r5 = r5 ^ r26
            int r26 = r5 + r10
            r26 = r26 | 0
            int r27 = r26 << 18
            r18 = 14
            int r26 = r26 >>> 14
            r26 = r27 | r26
            r1 = r1 ^ r26
            int r26 = r13 + r2
            r26 = r26 | 0
            int r27 = r26 << 7
            int r26 = r26 >>> 25
            r26 = r27 | r26
            r14 = r14 ^ r26
            int r26 = r14 + r13
            r26 = r26 | 0
            int r27 = r26 << 9
            r24 = 23
            int r26 = r26 >>> 23
            r26 = r27 | r26
            r25 = r25 ^ r26
            int r26 = r25 + r14
            r26 = r26 | 0
            int r27 = r26 << 13
            r22 = 19
            int r26 = r26 >>> 19
            r26 = r27 | r26
            r2 = r2 ^ r26
            int r26 = r2 + r25
            r26 = r26 | 0
            int r27 = r26 << 18
            r18 = 14
            int r26 = r26 >>> 14
            r26 = r27 | r26
            r13 = r13 ^ r26
            int r26 = r9 + r11
            r26 = r26 | 0
            int r27 = r26 << 7
            int r26 = r26 >>> 25
            r26 = r27 | r26
            r4 = r4 ^ r26
            int r26 = r4 + r9
            r26 = r26 | 0
            int r27 = r26 << 9
            r24 = 23
            int r26 = r26 >>> 23
            r26 = r27 | r26
            r3 = r3 ^ r26
            int r26 = r3 + r4
            r26 = r26 | 0
            int r27 = r26 << 13
            r22 = 19
            int r26 = r26 >>> 19
            r26 = r27 | r26
            r11 = r11 ^ r26
            int r26 = r11 + r3
            r26 = r26 | 0
            int r27 = r26 << 18
            r18 = 14
            int r26 = r26 >>> 14
            r26 = r27 | r26
            r9 = r9 ^ r26
            int r26 = r0 + r8
            r26 = r26 | 0
            int r27 = r26 << 7
            int r26 = r26 >>> 25
            r26 = r27 | r26
            r6 = r6 ^ r26
            int r26 = r6 + r0
            r26 = r26 | 0
            int r27 = r26 << 9
            r24 = 23
            int r26 = r26 >>> 23
            r26 = r27 | r26
            r12 = r12 ^ r26
            int r26 = r12 + r6
            r26 = r26 | 0
            int r27 = r26 << 13
            r22 = 19
            int r26 = r26 >>> 19
            r26 = r27 | r26
            r8 = r8 ^ r26
            int r26 = r8 + r12
            r26 = r26 | 0
            int r27 = r26 << 18
            r18 = 14
            int r26 = r26 >>> 14
            r26 = r27 | r26
            r0 = r0 ^ r26
            int r15 = r15 + 2
            r30 = r25
            r25 = r14
            r14 = r13
            r13 = r12
            r12 = r3
            r3 = r2
            goto L_0x01d4
        L_0x038c:
            int r2 = r1 >>> 0
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r3 = 0
            r28[r3] = r2
            int r2 = r1 >>> 8
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r3 = 1
            r28[r3] = r2
            int r2 = r1 >>> 16
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r3 = 2
            r28[r3] = r2
            r2 = 24
            int r1 = r1 >>> r2
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 3
            r28[r2] = r1
            int r1 = r14 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 4
            r28[r2] = r1
            int r1 = r14 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 5
            r28[r2] = r1
            int r1 = r14 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 6
            r28[r2] = r1
            r1 = 24
            int r2 = r14 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 7
            r28[r2] = r1
            int r1 = r9 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 8
            r28[r2] = r1
            int r1 = r9 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 9
            r28[r2] = r1
            int r1 = r9 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 10
            r28[r2] = r1
            r1 = 24
            int r2 = r9 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 11
            r28[r2] = r1
            int r1 = r0 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 12
            r28[r2] = r1
            int r1 = r0 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r17] = r1
            int r1 = r0 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 14
            r28[r2] = r1
            r1 = 15
            r2 = 24
            int r0 = r0 >>> r2
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r28[r1] = r0
            int r0 = r25 >>> 0
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r1 = 16
            r28[r1] = r0
            r0 = 17
            int r1 = r25 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            int r0 = r25 >>> 16
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r1 = 18
            r28[r1] = r0
            r0 = 24
            int r1 = r25 >>> 24
            r0 = r1 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r1 = 19
            r28[r1] = r0
            r0 = 20
            int r1 = r30 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 21
            int r1 = r30 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 22
            int r1 = r30 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 24
            int r1 = r30 >>> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 23
            r28[r2] = r1
            int r1 = r12 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            int r0 = r12 >>> 8
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r28[r23] = r0
            r0 = 26
            int r1 = r12 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 27
            r1 = 24
            int r2 = r12 >>> 24
            r1 = r2 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 28
            int r1 = r11 >>> 0
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 29
            int r1 = r11 >>> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 30
            int r1 = r11 >>> 16
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            r0 = 31
            r1 = 24
            int r1 = r11 >>> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r28[r0] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusher.client.crypto.nacl.TweetNaclFast.core_hsalsa20(byte[], byte[], byte[], byte[]):void");
    }

    public static int crypto_core_salsa20(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        core_salsa20(bArr, bArr2, bArr3, bArr4);
        return 0;
    }

    public static int crypto_core_hsalsa20(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        core_hsalsa20(bArr, bArr2, bArr3, bArr4);
        return 0;
    }

    private static int crypto_stream_salsa20_xor(byte[] bArr, int i, byte[] bArr2, int i2, long j, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = bArr4;
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[64];
        for (int i3 = 0; i3 < 16; i3++) {
            bArr6[i3] = 0;
        }
        for (int i4 = 0; i4 < 8; i4++) {
            bArr6[i4] = bArr3[i4];
        }
        int i5 = i;
        int i6 = i2;
        long j2 = j;
        while (j2 >= 64) {
            crypto_core_salsa20(bArr7, bArr6, bArr5, sigma);
            for (int i7 = 0; i7 < 64; i7++) {
                bArr[i5 + i7] = (byte) ((bArr2[i6 + i7] ^ bArr7[i7]) & UByte.MAX_VALUE);
            }
            int i8 = 1;
            for (int i9 = 8; i9 < 16; i9++) {
                int i10 = (i8 + (bArr6[i9] & UByte.MAX_VALUE)) | 0;
                bArr6[i9] = (byte) (i10 & 255);
                i8 = i10 >>> 8;
            }
            j2 -= 64;
            i5 += 64;
            i6 += 64;
        }
        if (j2 > 0) {
            crypto_core_salsa20(bArr7, bArr6, bArr5, sigma);
            for (int i11 = 0; ((long) i11) < j2; i11++) {
                bArr[i5 + i11] = (byte) ((bArr2[i6 + i11] ^ bArr7[i11]) & UByte.MAX_VALUE);
            }
        }
        return 0;
    }

    public static int crypto_stream_salsa20(byte[] bArr, int i, long j, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[16];
        byte[] bArr5 = new byte[64];
        for (int i2 = 0; i2 < 16; i2++) {
            bArr4[i2] = 0;
        }
        for (int i3 = 0; i3 < 8; i3++) {
            bArr4[i3] = bArr2[i3];
        }
        while (j >= 64) {
            crypto_core_salsa20(bArr5, bArr4, bArr3, sigma);
            for (int i4 = 0; i4 < 64; i4++) {
                bArr[i + i4] = bArr5[i4];
            }
            int i5 = 1;
            for (int i6 = 8; i6 < 16; i6++) {
                int i7 = (i5 + (bArr4[i6] & UByte.MAX_VALUE)) | 0;
                bArr4[i6] = (byte) (i7 & 255);
                i5 = i7 >>> 8;
            }
            j -= 64;
            i += 64;
        }
        if (j > 0) {
            crypto_core_salsa20(bArr5, bArr4, bArr3, sigma);
            for (int i8 = 0; ((long) i8) < j; i8++) {
                bArr[i + i8] = bArr5[i8];
            }
        }
        return 0;
    }

    public static int crypto_stream(byte[] bArr, int i, long j, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[32];
        crypto_core_hsalsa20(bArr4, bArr2, bArr3, sigma);
        byte[] bArr5 = new byte[8];
        for (int i2 = 0; i2 < 8; i2++) {
            bArr5[i2] = bArr2[i2 + 16];
        }
        return crypto_stream_salsa20(bArr, i, j, bArr5, bArr4);
    }

    public static int crypto_stream_xor(byte[] bArr, int i, byte[] bArr2, int i2, long j, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = bArr3;
        byte[] bArr6 = new byte[32];
        crypto_core_hsalsa20(bArr6, bArr5, bArr4, sigma);
        byte[] bArr7 = new byte[8];
        for (int i3 = 0; i3 < 8; i3++) {
            bArr7[i3] = bArr5[i3 + 16];
        }
        return crypto_stream_salsa20_xor(bArr, i, bArr2, i2, j, bArr7, bArr6);
    }

    public static final class poly1305 {
        private byte[] buffer = new byte[16];
        private int fin;
        private int[] h;
        private int leftover;
        private int[] pad;
        private int[] r;

        public poly1305(byte[] bArr) {
            int[] iArr = new int[10];
            this.r = iArr;
            this.h = new int[10];
            int[] iArr2 = new int[8];
            this.pad = iArr2;
            this.leftover = 0;
            this.fin = 0;
            byte b = (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8);
            iArr[0] = b & UByte.MAX_VALUE;
            byte b2 = (bArr[2] & UByte.MAX_VALUE) | ((bArr[3] & UByte.MAX_VALUE) << 8);
            iArr[1] = ((b >>> 13) | (b2 << 3)) & 8191;
            byte b3 = (bArr[4] & UByte.MAX_VALUE) | ((bArr[5] & UByte.MAX_VALUE) << 8);
            iArr[2] = ((b2 >>> 10) | (b3 << 6)) & 7939;
            byte b4 = ((bArr[7] & UByte.MAX_VALUE) << 8) | (bArr[6] & UByte.MAX_VALUE);
            iArr[3] = ((b3 >>> 7) | (b4 << 9)) & 8191;
            byte b5 = ((bArr[9] & UByte.MAX_VALUE) << 8) | (bArr[8] & UByte.MAX_VALUE);
            iArr[4] = ((b4 >>> 4) | (b5 << 12)) & 255;
            iArr[5] = (b5 >>> 1) & 8190;
            byte b6 = (bArr[10] & UByte.MAX_VALUE) | ((bArr[11] & UByte.MAX_VALUE) << 8);
            iArr[6] = ((b5 >>> 14) | (b6 << 2)) & 8191;
            byte b7 = (bArr[12] & UByte.MAX_VALUE) | ((bArr[13] & UByte.MAX_VALUE) << 8);
            iArr[7] = ((b6 >>> 11) | (b7 << 5)) & 8065;
            byte b8 = (bArr[14] & UByte.MAX_VALUE) | ((bArr[15] & UByte.MAX_VALUE) << 8);
            iArr[8] = ((b7 >>> 8) | (b8 << 8)) & 8191;
            iArr[9] = (b8 >>> 5) & 127;
            iArr2[0] = (bArr[16] & UByte.MAX_VALUE) | ((bArr[17] & UByte.MAX_VALUE) << 8);
            iArr2[1] = (bArr[18] & UByte.MAX_VALUE) | ((bArr[19] & UByte.MAX_VALUE) << 8);
            iArr2[2] = (bArr[20] & UByte.MAX_VALUE) | ((bArr[21] & UByte.MAX_VALUE) << 8);
            iArr2[3] = (bArr[22] & UByte.MAX_VALUE) | ((bArr[23] & UByte.MAX_VALUE) << 8);
            iArr2[4] = (bArr[24] & UByte.MAX_VALUE) | ((bArr[25] & UByte.MAX_VALUE) << 8);
            iArr2[5] = (bArr[26] & UByte.MAX_VALUE) | ((bArr[27] & UByte.MAX_VALUE) << 8);
            iArr2[6] = (bArr[28] & UByte.MAX_VALUE) | ((bArr[29] & UByte.MAX_VALUE) << 8);
            iArr2[7] = (bArr[30] & UByte.MAX_VALUE) | ((bArr[31] & UByte.MAX_VALUE) << 8);
        }

        public poly1305 blocks(byte[] bArr, int i, int i2) {
            int i3 = 0;
            int i4 = this.fin != 0 ? 0 : 2048;
            int[] iArr = this.h;
            int i5 = iArr[0];
            int i6 = iArr[1];
            int i7 = iArr[2];
            int i8 = iArr[3];
            byte b = 4;
            int i9 = iArr[4];
            int i10 = iArr[5];
            int i11 = iArr[6];
            int i12 = iArr[7];
            int i13 = iArr[8];
            int i14 = iArr[9];
            int[] iArr2 = this.r;
            int i15 = iArr2[0];
            int i16 = iArr2[1];
            int i17 = iArr2[2];
            int i18 = iArr2[3];
            int i19 = iArr2[4];
            int i20 = iArr2[5];
            int i21 = iArr2[6];
            int i22 = iArr2[7];
            int i23 = iArr2[8];
            int i24 = iArr2[9];
            int i25 = i12;
            int i26 = i13;
            int i27 = i10;
            int i28 = i11;
            int i29 = i8;
            int i30 = i9;
            int i31 = i6;
            int i32 = i7;
            int i33 = i14;
            int i34 = i5;
            int i35 = i;
            int i36 = i2;
            while (i36 >= 16) {
                byte b2 = ((bArr[i35 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i35 + 0] & UByte.MAX_VALUE);
                int i37 = i34 + (b2 & UByte.MAX_VALUE);
                byte b3 = (bArr[i35 + 2] & UByte.MAX_VALUE) | ((bArr[i35 + 3] & UByte.MAX_VALUE) << 8);
                int i38 = i31 + (((b2 >>> 13) | (b3 << 3)) & 8191);
                byte b4 = (bArr[i35 + 4] & UByte.MAX_VALUE) | ((bArr[i35 + 5] & UByte.MAX_VALUE) << 8);
                int i39 = i32 + (((b3 >>> 10) | (b4 << 6)) & 8191);
                byte b5 = (bArr[i35 + 6] & UByte.MAX_VALUE) | ((bArr[i35 + 7] & UByte.MAX_VALUE) << 8);
                int i40 = i29 + (((b4 >>> 7) | (b5 << 9)) & 8191);
                byte b6 = (bArr[i35 + 8] & UByte.MAX_VALUE) | ((bArr[i35 + 9] & UByte.MAX_VALUE) << 8);
                int i41 = i30 + (((b5 >>> b) | (b6 << 12)) & 8191);
                int i42 = i27 + ((b6 >>> 1) & 8191);
                byte b7 = (bArr[i35 + 10] & UByte.MAX_VALUE) | ((bArr[i35 + 11] & UByte.MAX_VALUE) << 8);
                int i43 = i28 + (((b6 >>> 14) | (b7 << 2)) & 8191);
                byte b8 = (bArr[i35 + 12] & UByte.MAX_VALUE) | ((bArr[i35 + 13] & UByte.MAX_VALUE) << 8);
                int i44 = i25 + (((b7 >>> 11) | (b8 << 5)) & 8191);
                byte b9 = (bArr[i35 + 14] & UByte.MAX_VALUE) | ((bArr[i35 + 15] & UByte.MAX_VALUE) << 8);
                int i45 = i26 + (((b8 >>> 8) | (b9 << 8)) & 8191);
                int i46 = i33 + ((b9 >>> 5) | i4);
                int i47 = i24 * 5;
                int i48 = i23 * 5;
                int i49 = i22 * 5;
                int i50 = i21 * 5;
                int i51 = (i37 * i15) + i3 + (i38 * i47) + (i39 * i48) + (i40 * i49) + (i41 * i50);
                int i52 = i51 >>> 13;
                int i53 = i20 * 5;
                int i54 = i19 * 5;
                int i55 = i18 * 5;
                int i56 = i17 * 5;
                int i57 = (i51 & 8191) + (i42 * i53) + (i43 * i54) + (i44 * i55) + (i45 * i56) + (i16 * 5 * i46);
                int i58 = i52 + (i57 >>> 13) + (i37 * i16) + (i38 * i15) + (i39 * i47) + (i40 * i48) + (i41 * i49);
                int i59 = i58 >>> 13;
                int i60 = (i58 & 8191) + (i42 * i50) + (i43 * i53) + (i44 * i54) + (i45 * i55) + (i56 * i46);
                int i61 = i59 + (i60 >>> 13) + (i37 * i17) + (i38 * i16) + (i39 * i15) + (i40 * i47) + (i41 * i48);
                int i62 = i61 >>> 13;
                int i63 = (i61 & 8191) + (i42 * i49) + (i43 * i50) + (i44 * i53) + (i45 * i54) + (i55 * i46);
                int i64 = i62 + (i63 >>> 13) + (i37 * i18) + (i38 * i17) + (i39 * i16) + (i40 * i15) + (i41 * i47);
                int i65 = i64 >>> 13;
                int i66 = (i64 & 8191) + (i42 * i48) + (i43 * i49) + (i44 * i50) + (i45 * i53) + (i54 * i46);
                int i67 = i4;
                int i68 = i65 + (i66 >>> 13) + (i37 * i19) + (i38 * i18) + (i39 * i17) + (i40 * i16) + (i41 * i15);
                int i69 = i68 >>> 13;
                int i70 = (i68 & 8191) + (i42 * i47) + (i43 * i48) + (i44 * i49) + (i45 * i50) + (i53 * i46);
                int i71 = i70 & 8191;
                int i72 = i69 + (i70 >>> 13) + (i37 * i20) + (i38 * i19) + (i39 * i18) + (i40 * i17) + (i41 * i16);
                int i73 = i72 >>> 13;
                int i74 = (i72 & 8191) + (i42 * i15) + (i43 * i47) + (i44 * i48) + (i45 * i49) + (i50 * i46);
                boolean z = i74 & true;
                int i75 = i73 + (i74 >>> 13) + (i37 * i21) + (i38 * i20) + (i39 * i19) + (i40 * i18) + (i41 * i17);
                int i76 = i75 >>> 13;
                int i77 = (i75 & 8191) + (i42 * i16) + (i43 * i15) + (i44 * i47) + (i45 * i48) + (i49 * i46);
                int i78 = i77 & 8191;
                int i79 = i76 + (i77 >>> 13) + (i37 * i22) + (i38 * i21) + (i39 * i20) + (i40 * i19) + (i41 * i18);
                int i80 = i79 >>> 13;
                int i81 = (i79 & 8191) + (i42 * i17) + (i43 * i16) + (i44 * i15) + (i45 * i47) + (i48 * i46);
                int i82 = i80 + (i81 >>> 13) + (i37 * i23) + (i38 * i22) + (i39 * i21) + (i40 * i20) + (i41 * i19);
                int i83 = i82 >>> 13;
                int i84 = (i82 & 8191) + (i42 * i18) + (i43 * i17) + (i44 * i16) + (i45 * i15) + (i47 * i46);
                int i85 = i84 & 8191;
                int i86 = i83 + (i84 >>> 13) + (i37 * i24) + (i38 * i23) + (i39 * i22) + (i40 * i21) + (i41 * i20);
                int i87 = i86 >>> 13;
                int i88 = (i86 & 8191) + (i42 * i19) + (i43 * i18) + (i44 * i17) + (i45 * i16) + (i46 * i15);
                int i89 = i87 + (i88 >>> 13);
                i33 = i88 & 8191;
                int i90 = ((((i89 << 2) + i89) | 0) + (i57 & 8191)) | 0;
                i34 = i90 & 8191;
                i31 = (i60 & 8191) + (i90 >>> 13);
                i35 += 16;
                i36 -= 16;
                i25 = i81 & 8191;
                i29 = i66 & 8191;
                i26 = i85;
                i32 = i63 & 8191;
                i28 = i78;
                i27 = z;
                i30 = i71;
                i4 = i67;
                i3 = 0;
                b = 4;
            }
            int[] iArr3 = this.h;
            iArr3[0] = i34;
            iArr3[1] = i31;
            iArr3[2] = i32;
            iArr3[3] = i29;
            iArr3[4] = i30;
            iArr3[5] = i27;
            iArr3[6] = i28;
            iArr3[7] = i25;
            iArr3[8] = i26;
            iArr3[9] = i33;
            return this;
        }

        public poly1305 finish(byte[] bArr, int i) {
            int[] iArr = new int[10];
            int i2 = this.leftover;
            if (i2 != 0) {
                this.buffer[i2] = 1;
                for (int i3 = i2 + 1; i3 < 16; i3++) {
                    this.buffer[i3] = 0;
                }
                this.fin = 1;
                blocks(this.buffer, 0, 16);
            }
            int[] iArr2 = this.h;
            int i4 = iArr2[1] >>> 13;
            iArr2[1] = iArr2[1] & 8191;
            for (int i5 = 2; i5 < 10; i5++) {
                int[] iArr3 = this.h;
                iArr3[i5] = iArr3[i5] + i4;
                i4 = iArr3[i5] >>> 13;
                iArr3[i5] = iArr3[i5] & 8191;
            }
            int[] iArr4 = this.h;
            iArr4[0] = iArr4[0] + (i4 * 5);
            iArr4[0] = iArr4[0] & 8191;
            iArr4[1] = iArr4[1] + (iArr4[0] >>> 13);
            iArr4[1] = iArr4[1] & 8191;
            iArr4[2] = iArr4[2] + (iArr4[1] >>> 13);
            iArr[0] = iArr4[0] + 5;
            int i6 = iArr[0] >>> 13;
            iArr[0] = iArr[0] & 8191;
            for (int i7 = 1; i7 < 10; i7++) {
                iArr[i7] = this.h[i7] + i6;
                i6 = iArr[i7] >>> 13;
                iArr[i7] = iArr[i7] & 8191;
            }
            iArr[9] = iArr[9] - 8192;
            iArr[9] = iArr[9] & 65535;
            int i8 = ((i6 ^ 1) - 1) & 65535;
            for (int i9 = 0; i9 < 10; i9++) {
                iArr[i9] = iArr[i9] & i8;
            }
            int i10 = ~i8;
            for (int i11 = 0; i11 < 10; i11++) {
                int[] iArr5 = this.h;
                iArr5[i11] = (iArr5[i11] & i10) | iArr[i11];
            }
            int[] iArr6 = this.h;
            iArr6[0] = (iArr6[0] | (iArr6[1] << 13)) & 65535;
            iArr6[1] = ((iArr6[2] << 10) | (iArr6[1] >>> 3)) & 65535;
            iArr6[2] = ((iArr6[2] >>> 6) | (iArr6[3] << 7)) & 65535;
            iArr6[3] = ((iArr6[3] >>> 9) | (iArr6[4] << 4)) & 65535;
            iArr6[4] = ((iArr6[4] >>> 12) | (iArr6[5] << 1) | (iArr6[6] << 14)) & 65535;
            iArr6[5] = ((iArr6[6] >>> 2) | (iArr6[7] << 11)) & 65535;
            iArr6[6] = ((iArr6[7] >>> 5) | (iArr6[8] << 8)) & 65535;
            iArr6[7] = ((iArr6[8] >>> 8) | (iArr6[9] << 5)) & 65535;
            int i12 = iArr6[0] + this.pad[0];
            iArr6[0] = i12 & 65535;
            for (int i13 = 1; i13 < 8; i13++) {
                int[] iArr7 = this.h;
                i12 = (((iArr7[i13] + this.pad[i13]) | 0) + (i12 >>> 16)) | 0;
                iArr7[i13] = i12 & 65535;
            }
            int[] iArr8 = this.h;
            bArr[i + 0] = (byte) ((iArr8[0] >>> 0) & 255);
            bArr[i + 1] = (byte) ((iArr8[0] >>> 8) & 255);
            bArr[i + 2] = (byte) ((iArr8[1] >>> 0) & 255);
            bArr[i + 3] = (byte) ((iArr8[1] >>> 8) & 255);
            bArr[i + 4] = (byte) ((iArr8[2] >>> 0) & 255);
            bArr[i + 5] = (byte) ((iArr8[2] >>> 8) & 255);
            bArr[i + 6] = (byte) ((iArr8[3] >>> 0) & 255);
            bArr[i + 7] = (byte) ((iArr8[3] >>> 8) & 255);
            bArr[i + 8] = (byte) ((iArr8[4] >>> 0) & 255);
            bArr[i + 9] = (byte) ((iArr8[4] >>> 8) & 255);
            bArr[i + 10] = (byte) ((iArr8[5] >>> 0) & 255);
            bArr[i + 11] = (byte) ((iArr8[5] >>> 8) & 255);
            bArr[i + 12] = (byte) ((iArr8[6] >>> 0) & 255);
            bArr[i + 13] = (byte) ((iArr8[6] >>> 8) & 255);
            bArr[i + 14] = (byte) ((iArr8[7] >>> 0) & 255);
            bArr[i + 15] = (byte) ((iArr8[7] >>> 8) & 255);
            return this;
        }

        public poly1305 update(byte[] bArr, int i, int i2) {
            int i3 = this.leftover;
            if (i3 != 0) {
                int i4 = 16 - i3;
                if (i4 > i2) {
                    i4 = i2;
                }
                for (int i5 = 0; i5 < i4; i5++) {
                    this.buffer[this.leftover + i5] = bArr[i + i5];
                }
                i2 -= i4;
                i += i4;
                int i6 = this.leftover + i4;
                this.leftover = i6;
                if (i6 < 16) {
                    return this;
                }
                blocks(this.buffer, 0, 16);
                this.leftover = 0;
            }
            if (i2 >= 16) {
                int i7 = i2 - (i2 % 16);
                blocks(bArr, i, i7);
                i += i7;
                i2 -= i7;
            }
            if (i2 != 0) {
                for (int i8 = 0; i8 < i2; i8++) {
                    this.buffer[this.leftover + i8] = bArr[i + i8];
                }
                this.leftover += i2;
            }
            return this;
        }
    }

    private static int crypto_onetimeauth(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3) {
        poly1305 poly13052 = new poly1305(bArr3);
        poly13052.update(bArr2, i2, i3);
        poly13052.finish(bArr, i);
        return 0;
    }

    private static int crypto_onetimeauth_verify(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3) {
        byte[] bArr4 = new byte[16];
        crypto_onetimeauth(bArr4, 0, bArr2, i2, i3, bArr3);
        return crypto_verify_16(bArr, i, bArr4, 0);
    }

    public static int crypto_secretbox_open(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        int i2 = i;
        byte[] bArr5 = new byte[32];
        if (i2 < 32) {
            return -1;
        }
        crypto_stream(bArr5, 0, 32, bArr3, bArr4);
        if (crypto_onetimeauth_verify(bArr2, 16, bArr2, 32, i2 - 32, bArr5) != 0) {
            return -1;
        }
        crypto_stream_xor(bArr, 0, bArr2, 0, (long) i2, bArr3, bArr4);
        return 0;
    }
}
