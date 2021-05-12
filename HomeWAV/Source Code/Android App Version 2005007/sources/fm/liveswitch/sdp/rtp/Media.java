package fm.liveswitch.sdp.rtp;

import com.twilio.video.G722Codec;
import com.twilio.video.PcmaCodec;
import com.twilio.video.PcmuCodec;
import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StreamType;
import fm.liveswitch.StringExtensions;

public class Media extends fm.liveswitch.sdp.Media {
    public static String getRtpAvpTransportProtocol() {
        return "RTP/AVP";
    }

    public static String getRtpAvpfTransportProtocol() {
        return "RTP/AVPF";
    }

    public static String getRtpSavpTransportProtocol() {
        return "RTP/SAVP";
    }

    public static String getRtpSavpfTransportProtocol() {
        return "RTP/SAVPF";
    }

    public static String getUdpTlsRtpSavpTransportProtocol() {
        return "UDP/TLS/RTP/SAVP";
    }

    public static String getUdpTlsRtpSavpfTransportProtocol() {
        return "UDP/TLS/RTP/SAVPF";
    }

    public static int getWellKnownPayloadClockRate(int i) {
        if (!(i == 0 || i == 3 || i == 4 || i == 5)) {
            if (i == 6) {
                return 16000;
            }
            if (!(i == 7 || i == 8 || i == 9)) {
                if (i == 10 || i == 11) {
                    return 44100;
                }
                if (i == 12 || i == 13) {
                    return 8000;
                }
                if (i == 14) {
                    return 90000;
                }
                if (i == 15) {
                    return 8000;
                }
                if (i == 16) {
                    return 11025;
                }
                if (i == 17) {
                    return 22050;
                }
                if (i == 18) {
                    return 8000;
                }
                return (i == 25 || i == 26 || i == 28 || i == 31 || i == 32 || i == 33 || i == 34) ? 90000 : -1;
            }
        }
        return 8000;
    }

    public static String getWellKnownPayloadName(int i) {
        if (i == 0) {
            return PcmuCodec.NAME;
        }
        if (i == 3) {
            return "GSM";
        }
        if (i == 4) {
            return "G723";
        }
        if (i == 5 || i == 6) {
            return "DVI4";
        }
        if (i == 7) {
            return "LPC";
        }
        if (i == 8) {
            return PcmaCodec.NAME;
        }
        if (i == 9) {
            return G722Codec.NAME;
        }
        if (i == 10 || i == 11) {
            return "L16";
        }
        if (i == 12) {
            return "QCELP";
        }
        if (i == 13) {
            return "CN";
        }
        if (i == 14) {
            return "MPA";
        }
        if (i == 15) {
            return "G728";
        }
        if (i == 16 || i == 17) {
            return "DVI4";
        }
        if (i == 18) {
            return "G729";
        }
        if (i == 25) {
            return "CelB";
        }
        if (i == 26) {
            return "JPEG";
        }
        if (i == 28) {
            return "nv";
        }
        if (i == 31) {
            return "H261";
        }
        if (i == 32) {
            return "MPV";
        }
        if (i == 33) {
            return "MP2T";
        }
        if (i == 34) {
            return "H263";
        }
        return null;
    }

    public static String generateRtpProfile(StreamType streamType, boolean z, boolean z2, boolean z3) {
        if (!z3 && z2) {
            throw new RuntimeException(new Exception("Cannot generate RTP Profile for the case when DTLS support is required but encryption is not used. Likely, useEncryption must be set."));
        } else if (!Global.equals(streamType, StreamType.Video) && !Global.equals(streamType, StreamType.Audio)) {
            throw new RuntimeException(new Exception(StringExtensions.format("Cannot generate RTP Profile streams of type other than Audio or Video. {0} type supplied.", (Object) streamType.toString())));
        } else if (z3) {
            if (z2) {
                if (z) {
                    return getUdpTlsRtpSavpfTransportProtocol();
                }
                return getUdpTlsRtpSavpTransportProtocol();
            } else if (z) {
                return getRtpSavpfTransportProtocol();
            } else {
                return getRtpSavpTransportProtocol();
            }
        } else if (z) {
            return getRtpAvpfTransportProtocol();
        } else {
            return getRtpAvpTransportProtocol();
        }
    }

    public static int[] getPayloadTypes(String str) {
        if (str == null || StringExtensions.getLength(str) <= 0) {
            return new int[0];
        }
        String[] split = StringExtensions.split(str.trim(), new char[]{' '});
        int[] iArr = new int[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            iArr[i] = ParseAssistant.parseIntegerValue(split[i]);
        }
        return iArr;
    }

    public Media(String str, int i, String str2, int[] iArr) {
        super(str, i, str2);
        if (iArr != null) {
            String[] strArr = new String[ArrayExtensions.getLength(iArr)];
            for (int i2 = 0; i2 < ArrayExtensions.getLength(iArr); i2++) {
                strArr[i2] = IntegerExtensions.toString(Integer.valueOf(iArr[i2]));
            }
            super.setFormatDescription(StringExtensions.join(" ", strArr));
            return;
        }
        throw new RuntimeException(new Exception("rtpPayloadTypeNumbers cannot be null."));
    }

    public static boolean supportsEncryption(String str) {
        return Global.equals(str, getUdpTlsRtpSavpTransportProtocol()) || Global.equals(str, getUdpTlsRtpSavpfTransportProtocol()) || Global.equals(str, getRtpSavpTransportProtocol()) || Global.equals(str, getRtpSavpfTransportProtocol());
    }

    public static boolean supportsRtcpBasedFeedback(String str) {
        return Global.equals(str, getRtpSavpfTransportProtocol()) || Global.equals(str, getRtpAvpfTransportProtocol()) || Global.equals(str, getUdpTlsRtpSavpfTransportProtocol());
    }
}
