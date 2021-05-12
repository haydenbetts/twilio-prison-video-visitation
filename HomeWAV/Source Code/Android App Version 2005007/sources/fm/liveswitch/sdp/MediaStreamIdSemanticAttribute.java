package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.StringExtensions;
import org.slf4j.Marker;

public class MediaStreamIdSemanticAttribute extends Attribute {
    private String _msIdList;
    private MediaStreamIdSemanticToken _semanticToken;

    public static MediaStreamIdSemanticAttribute fromAttributeValue(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        if (ArrayExtensions.getLength((Object[]) split) == 0) {
            return new MediaStreamIdSemanticAttribute(MediaStreamIdSemanticToken.Wms);
        }
        String str2 = split[0];
        MediaStreamIdSemanticToken semanticTokenFromString = getSemanticTokenFromString(str2);
        if (ArrayExtensions.getLength((Object[]) split) == 1) {
            return new MediaStreamIdSemanticAttribute(semanticTokenFromString);
        }
        return new MediaStreamIdSemanticAttribute(semanticTokenFromString, str.substring(StringExtensions.getLength(str2) + 1));
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        if (StringExtensions.isNullOrEmpty(getMsIdList())) {
            return getSemanticTokenString(getSemanticToken());
        }
        return StringExtensions.concat(getSemanticTokenString(getSemanticToken()), " ", getMsIdList());
    }

    public String getMsIdList() {
        return this._msIdList;
    }

    private MediaStreamIdSemanticToken getSemanticToken() {
        return this._semanticToken;
    }

    private static MediaStreamIdSemanticToken getSemanticTokenFromString(String str) {
        if (str == null || !Global.equals(str, "WMS")) {
            return MediaStreamIdSemanticToken.Wms;
        }
        return MediaStreamIdSemanticToken.Wms;
    }

    private static String getSemanticTokenString(MediaStreamIdSemanticToken mediaStreamIdSemanticToken) {
        if (Global.equals(mediaStreamIdSemanticToken, MediaStreamIdSemanticToken.Wms)) {
        }
        return "WMS";
    }

    public MediaStreamIdSemanticAttribute(MediaStreamIdSemanticToken mediaStreamIdSemanticToken) {
        this(mediaStreamIdSemanticToken, Marker.ANY_MARKER);
    }

    public MediaStreamIdSemanticAttribute(MediaStreamIdSemanticToken mediaStreamIdSemanticToken, String str) {
        super.setAttributeType(AttributeType.MediaStreamIdSemanticAttribute);
        setSemanticToken(mediaStreamIdSemanticToken);
        setMsIdList(str);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public void setMsIdList(String str) {
        this._msIdList = str;
    }

    private void setSemanticToken(MediaStreamIdSemanticToken mediaStreamIdSemanticToken) {
        this._semanticToken = mediaStreamIdSemanticToken;
    }
}
