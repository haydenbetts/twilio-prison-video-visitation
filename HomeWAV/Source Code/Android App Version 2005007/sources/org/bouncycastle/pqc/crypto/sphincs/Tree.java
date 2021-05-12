package org.bouncycastle.pqc.crypto.sphincs;

class Tree {

    static class leafaddr {
        int level;
        long subleaf;
        long subtree;

        public leafaddr() {
        }

        public leafaddr(leafaddr leafaddr) {
            this.level = leafaddr.level;
            this.subtree = leafaddr.subtree;
            this.subleaf = leafaddr.subleaf;
        }
    }

    Tree() {
    }

    static void gen_leaf_wots(HashFunctions hashFunctions, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, leafaddr leafaddr2) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[2144];
        Wots wots = new Wots();
        HashFunctions hashFunctions2 = hashFunctions;
        Seed.get_seed(hashFunctions, bArr4, 0, bArr3, leafaddr2);
        wots.wots_pkgen(hashFunctions, bArr5, 0, bArr4, 0, bArr2, i2);
        l_tree(hashFunctions, bArr, i, bArr5, 0, bArr2, i2);
    }

    static void l_tree(HashFunctions hashFunctions, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int i4;
        byte[] bArr4 = bArr2;
        int i5 = i2;
        int i6 = 67;
        for (int i7 = 0; i7 < 7; i7++) {
            int i8 = 0;
            while (true) {
                i4 = i6 >>> 1;
                if (i8 >= i4) {
                    break;
                }
                hashFunctions.hash_2n_n_mask(bArr2, i5 + (i8 * 32), bArr2, i5 + (i8 * 2 * 32), bArr3, i3 + (i7 * 2 * 32));
                i8++;
            }
            if ((i6 & 1) != 0) {
                System.arraycopy(bArr4, i5 + ((i6 - 1) * 32), bArr4, (i4 * 32) + i5, 32);
                i4++;
            }
            i6 = i4;
        }
        byte[] bArr5 = bArr;
        System.arraycopy(bArr4, i5, bArr, i, 32);
    }

    static void treehash(HashFunctions hashFunctions, byte[] bArr, int i, int i2, byte[] bArr2, leafaddr leafaddr2, byte[] bArr3, int i3) {
        leafaddr leafaddr3 = new leafaddr(leafaddr2);
        int i4 = i2 + 1;
        byte[] bArr4 = new byte[(i4 * 32)];
        int[] iArr = new int[i4];
        int i5 = 1;
        int i6 = (int) (leafaddr3.subleaf + ((long) (1 << i2)));
        int i7 = 0;
        while (leafaddr3.subleaf < ((long) i6)) {
            gen_leaf_wots(hashFunctions, bArr4, i7 * 32, bArr3, i3, bArr2, leafaddr3);
            iArr[i7] = 0;
            int i8 = i7 + i5;
            while (i8 > i5) {
                int i9 = i8 - 1;
                int i10 = i8 - 2;
                if (iArr[i9] != iArr[i10]) {
                    break;
                }
                int i11 = i10 * 32;
                int[] iArr2 = iArr;
                hashFunctions.hash_2n_n_mask(bArr4, i11, bArr4, i11, bArr3, i3 + ((iArr[i9] + 7) * 2 * 32));
                iArr2[i10] = iArr2[i10] + 1;
                i8--;
                i6 = i6;
                iArr = iArr2;
                i5 = 1;
            }
            leafaddr3.subleaf++;
            i7 = i8;
            i6 = i6;
            iArr = iArr;
            i5 = 1;
        }
        for (int i12 = 0; i12 < 32; i12++) {
            bArr[i + i12] = bArr4[i12];
        }
    }
}
