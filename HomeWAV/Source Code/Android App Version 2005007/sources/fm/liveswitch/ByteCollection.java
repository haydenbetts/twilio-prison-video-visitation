package fm.liveswitch;

import java.util.Arrays;

public class ByteCollection {
    private int _length;
    private byte[] _list;

    public void add(byte b) {
        try {
            append(b);
        } catch (Exception e) {
            Log.error("Could not add(byte).", e);
        }
    }

    public void add(int i) {
        try {
            append((byte) i);
        } catch (Exception e) {
            Log.error("Could not add(int).", e);
        }
    }

    public void addRange(byte[] bArr) {
        if (bArr != null) {
            try {
                append(bArr, bArr.length);
            } catch (Exception e) {
                Log.error("Could not addRange(byte[]).", e);
            }
        }
    }

    public void addRange(ByteCollection byteCollection) {
        if (byteCollection != null) {
            try {
                append(byteCollection._list, byteCollection._length);
            } catch (Exception e) {
                Log.error("Could not addRange(ByteCollection).", e);
            }
        }
    }

    public ByteCollection() {
    }

    public ByteCollection(byte[] bArr) {
        addRange(bArr);
    }

    public byte get(int i) {
        byte[] bArr = this._list;
        if (bArr == null || i >= bArr.length) {
            return 0;
        }
        return bArr[i];
    }

    public int getCount() {
        return this._length;
    }

    public byte[] getRange(int i, int i2) {
        return Arrays.copyOfRange(this._list, i, i2 + i);
    }

    public void insertRange(int i, byte[] bArr) {
        if (bArr != null) {
            try {
                insert(bArr, bArr.length, i);
            } catch (Exception e) {
                Log.error("Could not insertRange(int,byte[]).", e);
            }
        }
    }

    public void insertRange(int i, ByteCollection byteCollection) {
        if (byteCollection != null) {
            try {
                insert(byteCollection._list, byteCollection._length, i);
            } catch (Exception e) {
                Log.error("Could not insertRange(int,ByteCollection).", e);
            }
        }
    }

    public void removeRange(int i, int i2) {
        try {
            remove(i2, i);
        } catch (Exception e) {
            Log.error("Could not removeRange(int,int).", e);
        }
    }

    public byte[] toArray() {
        byte[] bArr = this._list;
        if (bArr == null) {
            return new byte[0];
        }
        int length = bArr.length;
        int i = this._length;
        if (length == i) {
            return bArr;
        }
        return getRange(0, i);
    }

    private void append(byte b) {
        byte[] bArr = this._list;
        if (bArr == null) {
            byte[] bArr2 = new byte[4];
            this._list = bArr2;
            bArr2[0] = b;
            this._length = 1;
            return;
        }
        int i = this._length + 1;
        if (i > bArr.length) {
            byte[] createBiggerArray = createBiggerArray(i);
            byte[] bArr3 = this._list;
            System.arraycopy(bArr3, 0, createBiggerArray, 0, bArr3.length);
            this._list = createBiggerArray;
        }
        byte[] bArr4 = this._list;
        int i2 = this._length;
        bArr4[i2] = b;
        this._length = i2 + 1;
    }

    private void append(byte[] bArr, int i) {
        if (bArr != null) {
            byte[] bArr2 = this._list;
            if (bArr2 == null) {
                this._list = bArr;
                this._length = i;
                return;
            }
            int i2 = this._length + i;
            if (i2 > bArr2.length) {
                byte[] createBiggerArray = createBiggerArray(i2);
                byte[] bArr3 = this._list;
                System.arraycopy(bArr3, 0, createBiggerArray, 0, bArr3.length);
                this._list = createBiggerArray;
            }
            System.arraycopy(bArr, 0, this._list, this._length, i);
            this._length += i;
        }
    }

    private void insert(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            byte[] bArr2 = this._list;
            if (bArr2 == null) {
                this._list = bArr;
                this._length = i;
                return;
            }
            int i3 = this._length;
            int i4 = i3 - i2;
            int i5 = i3 + i;
            if (i5 > bArr2.length) {
                byte[] createBiggerArray = createBiggerArray(i5);
                System.arraycopy(this._list, 0, createBiggerArray, 0, i2);
                int i6 = i2 + 0;
                System.arraycopy(bArr, 0, createBiggerArray, i6, i);
                System.arraycopy(this._list, i2, createBiggerArray, i6 + i, i4);
                this._list = createBiggerArray;
            } else {
                System.arraycopy(bArr2, i2, bArr2, i2 + i, i4);
                System.arraycopy(bArr, 0, this._list, i2, i);
            }
            this._length += i;
        }
    }

    private void remove(int i, int i2) {
        byte[] bArr = this._list;
        if (bArr != null) {
            int i3 = i2 + i;
            System.arraycopy(bArr, i3, bArr, i2, this._length - i3);
            this._length -= i;
        }
    }

    private byte[] createBiggerArray(int i) {
        int length = this._list.length;
        while (length < i) {
            length *= 2;
        }
        return new byte[length];
    }
}
