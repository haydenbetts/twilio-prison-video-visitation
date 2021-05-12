package fm.liveswitch.vp8;

import fm.liveswitch.DataBuffer;

public class Utility {
    public static int getPictureIdDelta(int i, int i2) {
        int i3 = i - i2;
        return (i > 127 || i2 > 127) ? i3 < -16384 ? i3 + 32768 : i3 >= 16384 ? i3 - 32768 : i3 : i3 < -64 ? i3 + 128 : i3 >= 64 ? i3 - 128 : i3;
    }

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

    public static boolean isGapAllowed(Fragment fragment, Fragment fragment2) {
        int rtpSequenceNumberDelta = fm.liveswitch.Utility.getRtpSequenceNumberDelta(fragment.getRtpSequenceNumber(), fragment2.getRtpSequenceNumber());
        if (rtpSequenceNumberDelta < 2) {
            return false;
        }
        if ((!fragment.getFirst() && fragment.getTemporalLayerIndex() <= 0) || (!fragment2.getLast() && fragment2.getTemporalLayerIndex() <= 0)) {
            return false;
        }
        if (fragment.getPictureId() == -1 || fragment2.getPictureId() == -1 || getPictureIdDelta(fragment.getPictureId(), fragment2.getPictureId()) != 1) {
            if (rtpSequenceNumberDelta > 2) {
                if (fragment.getRtpTimestamp() == fragment2.getRtpTimestamp() && fragment2.getTemporalLayerIndex() > 0) {
                    return true;
                }
                return false;
            } else if (fragment2.getLast() && fragment.getFirst()) {
                return false;
            } else {
                if ((!fragment2.getLast() || fragment.getFirst() || fragment.getTemporalLayerIndex() <= 0) && ((fragment2.getLast() || !fragment.getFirst() || fragment2.getTemporalLayerIndex() <= 0) && (fragment2.getLast() || fragment.getFirst() || fragment2.getTemporalLayerIndex() <= 0))) {
                    return false;
                }
                return true;
            }
        } else if ((!fragment2.getLast() || fragment.getFirst() || fragment.getTemporalLayerIndex() <= 0) && (fragment2.getLast() || !fragment.getFirst() || fragment2.getTemporalLayerIndex() <= 0)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isKeyFrame(DataBuffer dataBuffer) {
        return dataBuffer != null && dataBuffer.getLength() >= 10 && (dataBuffer.read8(0) & 1) == 0 && dataBuffer.read8(3) == 157 && dataBuffer.read8(4) == 1 && dataBuffer.read8(5) == 42;
    }
}
