package fm.liveswitch.h264;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.Holder;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.NullableInteger;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringComparison;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.sdp.FormatParametersAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import java.util.ArrayList;
import tvi.webrtc.VideoCodecInfo;

public class Utility {
    private static int[] _supportedPacketizationModes;
    private static int[] _supportedProfileIdcs;

    public static int getNaluLength(DataBuffer dataBuffer, int i) {
        int startCodeLength = getStartCodeLength(dataBuffer, i);
        if (startCodeLength <= 0) {
            return -1;
        }
        for (int i2 = startCodeLength + i; i2 < dataBuffer.getLength(); i2++) {
            if (getStartCodeLength(dataBuffer, i2) > 0) {
                return i2 - i;
            }
        }
        return dataBuffer.getLength() - i;
    }

    public static int getNaluType(DataBuffer dataBuffer) {
        return getNaluType(dataBuffer, 0);
    }

    public static int getNaluType(DataBuffer dataBuffer, int i) {
        int startCodeLength = getStartCodeLength(dataBuffer, i);
        if (startCodeLength <= 0) {
            return -1;
        }
        return dataBuffer.read8(i + startCodeLength) & Nalu.getTypeMask();
    }

    public static int[] getNaluTypes(DataBuffer dataBuffer) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < dataBuffer.getLength()) {
            int naluType = getNaluType(dataBuffer, i);
            int naluLength = getNaluLength(dataBuffer, i);
            if (naluType == -1 && naluLength == -1) {
                break;
            } else if (naluType == -1 || naluLength == -1) {
                return null;
            } else {
                arrayList.add(Integer.valueOf(naluType));
                i += naluLength;
            }
        }
        return fm.liveswitch.Utility.toIntArray(arrayList);
    }

    public static NullableInteger getPacketizationMode(FormatParametersAttribute formatParametersAttribute) {
        int singleNal = PacketizationMode.getSingleNal();
        if (formatParametersAttribute != null) {
            Holder holder = new Holder(null);
            boolean tryGetFormatSpecificParameter = formatParametersAttribute.tryGetFormatSpecificParameter(VideoCodecInfo.H264_FMTP_PACKETIZATION_MODE, holder);
            IntegerHolder integerHolder = new IntegerHolder(singleNal);
            boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue((String) holder.getValue(), integerHolder);
            int value = integerHolder.getValue();
            if (tryGetFormatSpecificParameter && !tryParseIntegerValue) {
                return new NullableInteger((Integer) null);
            }
            singleNal = value;
        }
        return new NullableInteger(singleNal);
    }

    public static NullableInteger getPacketizationMode(MapAttribute mapAttribute) {
        if (mapAttribute.getFormatName() != null && StringExtensions.isEqual(mapAttribute.getFormatName(), VideoFormat.getH264Name(), StringComparison.InvariantCultureIgnoreCase)) {
            return getPacketizationMode(mapAttribute.getRelatedFormatParametersAttribute());
        }
        throw new RuntimeException(new Exception("RTP map attribute must have a format name of H264."));
    }

    public static ProfileLevelId getProfileLevelId(FormatParametersAttribute formatParametersAttribute) {
        ProfileLevelId baselineLevel10 = ProfileLevelId.getBaselineLevel10();
        if (formatParametersAttribute == null) {
            return baselineLevel10;
        }
        Holder holder = new Holder(null);
        return formatParametersAttribute.tryGetFormatSpecificParameter(VideoCodecInfo.H264_FMTP_PROFILE_LEVEL_ID, holder) ? new ProfileLevelId((String) holder.getValue()) : baselineLevel10;
    }

    public static ProfileLevelId getProfileLevelId(MapAttribute mapAttribute) {
        if (mapAttribute.getFormatName() != null && StringExtensions.isEqual(mapAttribute.getFormatName(), VideoFormat.getH264Name(), StringComparison.InvariantCultureIgnoreCase)) {
            return getProfileLevelId(mapAttribute.getRelatedFormatParametersAttribute());
        }
        throw new RuntimeException(new Exception("RTP map attribute must have a format name of H264."));
    }

    public static int getStartCodeLength(DataBuffer dataBuffer, int i) {
        if (dataBuffer != null && dataBuffer.getLength() >= i + 4 && dataBuffer.read8(i) == 0 && dataBuffer.read8(i + 1) == 0) {
            int i2 = i + 2;
            if (dataBuffer.read8(i2) == 1) {
                return 3;
            }
            if (dataBuffer.getLength() >= i + 5 && dataBuffer.read8(i2) == 0 && dataBuffer.read8(i + 3) == 1) {
                return 4;
            }
        }
        return -1;
    }

    public static int[] getSupportedPacketizationModes() {
        return _supportedPacketizationModes;
    }

    public static int[] getSupportedProfileIdcs() {
        return _supportedProfileIdcs;
    }

    public static boolean isAud(DataBuffer dataBuffer) {
        return isAud(dataBuffer, 0);
    }

    public static boolean isAud(DataBuffer dataBuffer, int i) {
        return isNaluType(9, dataBuffer, i);
    }

    public static boolean isIdr(DataBuffer dataBuffer) {
        return isIdr(dataBuffer, 0);
    }

    public static boolean isIdr(DataBuffer dataBuffer, int i) {
        return isNaluType(5, dataBuffer, i);
    }

    public static boolean isKeyFrame(DataBuffer dataBuffer) {
        return isIdr(dataBuffer) || isPps(dataBuffer) || isSps(dataBuffer);
    }

    public static boolean isNaluType(int i, DataBuffer dataBuffer, int i2) {
        return getNaluType(dataBuffer, i2) == i;
    }

    public static boolean isPps(DataBuffer dataBuffer) {
        return isPps(dataBuffer, 0);
    }

    public static boolean isPps(DataBuffer dataBuffer, int i) {
        return isNaluType(8, dataBuffer, i);
    }

    public static boolean isSps(DataBuffer dataBuffer) {
        return isSps(dataBuffer, 0);
    }

    public static boolean isSps(DataBuffer dataBuffer, int i) {
        return isNaluType(7, dataBuffer, i);
    }

    private static void setSupportedPacketizationModes(int[] iArr) {
        _supportedPacketizationModes = iArr;
    }

    private static void setSupportedProfileIdcs(int[] iArr) {
        _supportedProfileIdcs = iArr;
    }

    public static DataBuffer trimAud(DataBuffer dataBuffer) {
        return isAud(dataBuffer) ? dataBuffer.subset(getNaluLength(dataBuffer, 0)) : dataBuffer;
    }

    static {
        setSupportedPacketizationModes(new int[]{PacketizationMode.getNonInterleaved(), PacketizationMode.getSingleNal()});
        setSupportedProfileIdcs(new int[]{ProfileIdc.getBaseline()});
    }
}
