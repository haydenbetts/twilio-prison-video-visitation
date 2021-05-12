package fm.liveswitch.android;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.Log;
import fm.liveswitch.VideoFormat;
import java.util.ArrayList;

public class MediaCodecUtility {
    public static void logAvailableCodecs() {
        for (MediaCodecInfo mediaCodecInfo : getCodecInfos()) {
            for (String str : mediaCodecInfo.getSupportedTypes()) {
                StringBuilder sb = new StringBuilder();
                sb.append(mediaCodecInfo.isEncoder() ? "Encoder" : "Decoder");
                sb.append(" supported with mime-type '");
                sb.append(str);
                sb.append("' and codec name '");
                sb.append(mediaCodecInfo.getName());
                sb.append("'.");
                Log.info(sb.toString());
            }
        }
    }

    public static MediaCodecInfo[] getEncoderInfos(String str) {
        return getCodecInfos(str, -1, false, true);
    }

    public static MediaCodecInfo[] getEncoderInfos(String str, int i) {
        return getCodecInfos(str, i, false, true);
    }

    public static MediaCodecInfo[] getEncoderInfos(String str, int i, boolean z) {
        return getCodecInfos(str, i, z, true);
    }

    public static MediaCodecInfo[] getDecoderInfos(String str) {
        return getCodecInfos(str, -1, false, false);
    }

    public static MediaCodecInfo[] getDecoderInfos(String str, int i) {
        return getCodecInfos(str, i, false, false);
    }

    public static MediaCodecInfo[] getDecoderInfos(String str, int i, boolean z) {
        return getCodecInfos(str, i, z, false);
    }

    private static MediaCodecInfo[] getCodecInfos(String str, int i, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        for (MediaCodecInfo mediaCodecInfo : getCodecInfos()) {
            if (mediaCodecInfo.isEncoder() == z2) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                int length = supportedTypes.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (supportedTypes[i2].equalsIgnoreCase(str)) {
                        boolean z3 = true;
                        boolean z4 = !z || isHardwareAccelerated(mediaCodecInfo);
                        if (i >= 0) {
                            if (!z4 || !hasColorFormat(mediaCodecInfo, str, i)) {
                                z3 = false;
                            }
                            z4 = z3;
                        }
                        if (z4) {
                            arrayList.add(mediaCodecInfo);
                            break;
                        }
                    }
                    i2++;
                }
            }
        }
        return (MediaCodecInfo[]) arrayList.toArray(new MediaCodecInfo[arrayList.size()]);
    }

    public static boolean hasMimeType(MediaCodecInfo mediaCodecInfo, String str) {
        for (String equalsIgnoreCase : mediaCodecInfo.getSupportedTypes()) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo) {
        return !mediaCodecInfo.getName().contains("OMX.google");
    }

    public static boolean hasColorFormat(MediaCodecInfo mediaCodecInfo, String str, int i) {
        for (int i2 : mediaCodecInfo.getCapabilitiesForType(str).colorFormats) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private static MediaCodecInfo[] getCodecInfos() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new MediaCodecList(0).getCodecInfos();
        }
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo[] mediaCodecInfoArr = new MediaCodecInfo[codecCount];
        for (int i = 0; i < codecCount; i++) {
            mediaCodecInfoArr[i] = MediaCodecList.getCodecInfoAt(i);
        }
        return mediaCodecInfoArr;
    }

    private static String getTopLevelType(String str) {
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        throw new IllegalArgumentException("Invalid mime type: " + str);
    }

    public static boolean isAudio(String str) {
        return getTopLevelType(str).equals("audio");
    }

    public static boolean isVideo(String str) {
        return getTopLevelType(str).equals("video");
    }

    public static String getFormatName(String str) {
        if (str.equals("audio/raw")) {
            return AudioFormat.getPcmName();
        }
        if (str.equals("audio/opus")) {
            return AudioFormat.getOpusName();
        }
        if (str.equals("video/avc")) {
            return VideoFormat.getH264Name();
        }
        if (str.equals("video/x-vnd.on2.vp8")) {
            return VideoFormat.getVp8Name();
        }
        if (str.equals("video/x-vnd.on2.vp9")) {
            return VideoFormat.getVp9Name();
        }
        return null;
    }

    public static String getMimeType(AudioFormat audioFormat) {
        if (audioFormat.getName().equals(AudioFormat.getPcmName())) {
            return "audio/raw";
        }
        if (audioFormat.getName().equals(AudioFormat.getOpusName())) {
            return "audio/opus";
        }
        return null;
    }

    public static String getMimeType(VideoFormat videoFormat) {
        if (videoFormat.getName().equals(VideoFormat.getH264Name())) {
            return "video/avc";
        }
        if (videoFormat.getName().equals(VideoFormat.getVp8Name())) {
            return "video/x-vnd.on2.vp8";
        }
        if (videoFormat.getName().equals(VideoFormat.getVp8Name())) {
            return "video/x-vnd.on2.vp9";
        }
        return null;
    }

    public static int getColorFormat(VideoFormat videoFormat) {
        if (videoFormat.getName().equals(VideoFormat.getI420Name())) {
            return 19;
        }
        if (videoFormat.getName().equals(VideoFormat.getNv12Name())) {
            return 21;
        }
        if (videoFormat.getName().equals(VideoFormat.getBgrName())) {
            return 12;
        }
        if (videoFormat.getName().equals(VideoFormat.getRgbName())) {
            return 11;
        }
        if (videoFormat.getName().equals(VideoFormat.getBgraName())) {
            return 15;
        }
        if (videoFormat.getName().equals(VideoFormat.getArgbName())) {
            return 16;
        }
        if (videoFormat.getName().equals(VideoFormat.getAbgrName())) {
            return MediaCodecColorFormats.Abgr;
        }
        return -1;
    }
}
