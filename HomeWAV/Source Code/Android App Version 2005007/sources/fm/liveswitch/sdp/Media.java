package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.ShortExtensions;
import fm.liveswitch.StringAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;

public class Media {
    private static short _defaultNumberOfPorts = 1;
    private String _formatDescription;
    private String _mediaType;
    private short _numberOfPorts;
    private int _transportPort;
    private String _transportProtocol;

    public String getFormatDescription() {
        return this._formatDescription;
    }

    public String getMediaType() {
        return this._mediaType;
    }

    public short getNumberOfPorts() {
        return this._numberOfPorts;
    }

    public int getTransportPort() {
        return this._transportPort;
    }

    public String getTransportProtocol() {
        return this._transportProtocol;
    }

    public Media() {
    }

    public Media(String str, int i, String str2) {
        this(str, i, str2, (String) null);
    }

    public Media(String str, int i, String str2, String str3) {
        if (str == null) {
            throw new RuntimeException(new Exception("mediaType cannot be null."));
        } else if (str2 != null) {
            setMediaType(str);
            setTransportPort(i);
            setNumberOfPorts(_defaultNumberOfPorts);
            setTransportProtocol(str2);
            setFormatDescription(str3);
        } else {
            throw new RuntimeException(new Exception("transportProtocol cannot be null."));
        }
    }

    public static Media parse(String str) {
        Media media;
        String[] split = StringExtensions.split(str.substring(2), new char[]{' '});
        String str2 = split[0];
        String[] split2 = StringExtensions.split(split[1], new char[]{'/'});
        int parseIntegerValue = ParseAssistant.parseIntegerValue(split2[0]);
        String str3 = split[2];
        String join = StringExtensions.join(" ", StringAssistant.subArray(split, 3));
        if (Global.equals(str3, UdpMedia.getUdpTransportProtocol())) {
            media = new UdpMedia(str2, parseIntegerValue, join);
        } else if (Global.equals(str3, fm.liveswitch.sdp.rtp.Media.getRtpAvpTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.rtp.Media.getRtpSavpTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.rtp.Media.getRtpAvpfTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.rtp.Media.getRtpSavpfTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.rtp.Media.getUdpTlsRtpSavpTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.rtp.Media.getUdpTlsRtpSavpfTransportProtocol())) {
            int[] iArr = new int[0];
            if (!StringExtensions.isNullOrEmpty(join)) {
                String[] split3 = StringExtensions.split(join, new char[]{' '});
                iArr = new int[ArrayExtensions.getLength((Object[]) split3)];
                for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
                    iArr[i] = ParseAssistant.parseIntegerValue(split3[i]);
                }
            }
            media = new fm.liveswitch.sdp.rtp.Media(str2, parseIntegerValue, str3, iArr);
        } else if (Global.equals(str3, fm.liveswitch.sdp.sctp.Media.getDtlsSctpTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.sctp.Media.getSctpDtlsTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.sctp.Media.getSctpTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.sctp.Media.getTcpDtlsSctpTransportProtocol()) || Global.equals(str3, fm.liveswitch.sdp.sctp.Media.getUdpDtlsSctpTransportProtocol())) {
            media = new fm.liveswitch.sdp.sctp.Media(str2, parseIntegerValue, str3, join);
        } else {
            media = new Media(str2, parseIntegerValue, str3, join);
        }
        if (ArrayExtensions.getLength((Object[]) split2) == 2) {
            media.setNumberOfPorts(ParseAssistant.parseShortValue(split2[1]));
        }
        return media;
    }

    public void setFormatDescription(String str) {
        this._formatDescription = str;
    }

    public void setMediaType(String str) {
        this._mediaType = str;
    }

    public void setNumberOfPorts(short s) {
        this._numberOfPorts = s;
    }

    public void setTransportPort(int i) {
        this._transportPort = i;
    }

    public void setTransportProtocol(String str) {
        this._transportProtocol = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "m=");
        StringBuilderExtensions.append(sb, getMediaType());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getTransportPort())));
        if (getNumberOfPorts() > 1) {
            StringBuilderExtensions.append(sb, "/");
            StringBuilderExtensions.append(sb, ShortExtensions.toString(Short.valueOf(getNumberOfPorts())));
        }
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getTransportProtocol());
        if (getFormatDescription() != null) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getFormatDescription());
        }
        return sb.toString();
    }
}
