package fm.liveswitch.vp9;

import fm.liveswitch.DataBuffer;

public class Utility {
    public static int deriveHeight(DataBuffer dataBuffer) {
        return getDimension(dataBuffer, 8);
    }

    public static int deriveWidth(DataBuffer dataBuffer) {
        return getDimension(dataBuffer, 6);
    }

    private static int getDimension(DataBuffer dataBuffer, int i) {
        if (!isKeyFrame(dataBuffer) || dataBuffer.getLength() < 10) {
            return 0;
        }
        int read8 = (dataBuffer.read8(i) >> 6) & 3;
        int read82 = ((dataBuffer.read8(i + 1) & 255) * 256) + (dataBuffer.read8(i) & 63);
        if (read8 == 0) {
            return read82 / 1;
        }
        if (read8 == 1) {
            return (read82 * 2) / 1;
        }
        if (read8 == 2) {
            return (read82 * 5) / 4;
        }
        return read8 == 3 ? (read82 * 5) / 3 : read82;
    }

    public static boolean isKeyFrame(DataBuffer dataBuffer) {
        if (dataBuffer == null || dataBuffer.getLength() <= 2 || dataBuffer.read2(0, 0) != 2) {
            return false;
        }
        int i = 4;
        if (dataBuffer.read2(0, 2) == 3) {
            i = 5;
        }
        int i2 = i + 1;
        if (dataBuffer.read1(0, i)) {
            return false;
        }
        return !dataBuffer.read1(0, i2);
    }
}
