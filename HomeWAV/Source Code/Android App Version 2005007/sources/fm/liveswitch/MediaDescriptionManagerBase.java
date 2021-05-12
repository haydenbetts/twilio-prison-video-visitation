package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaStreamIdAttribute;
import fm.liveswitch.sdp.Message;

abstract class MediaDescriptionManagerBase {
    private String _mediaStreamIdentifier;

    public String getMediaStreamIdentifier() {
        return this._mediaStreamIdentifier;
    }

    protected MediaDescriptionManagerBase() {
    }

    public Error processSdpMediaDescription(MediaDescriptionRequirementsBase mediaDescriptionRequirementsBase, Message message, int i, boolean z, boolean z2, boolean z3) {
        mediaDescriptionRequirementsBase.setMediaStreamIdentifier(syncroniseMediaIdentification(message.getMediaDescriptions()[i], i));
        setMediaStreamIdentifier(mediaDescriptionRequirementsBase.getMediaStreamIdentifier());
        return null;
    }

    public void setMediaStreamIdentifier(String str) {
        this._mediaStreamIdentifier = str;
    }

    public static String syncroniseMediaIdentification(MediaDescription mediaDescription, int i) {
        MediaStreamIdAttribute mediaStreamIdentifierAttribute = mediaDescription.getMediaStreamIdentifierAttribute();
        if (mediaStreamIdentifierAttribute != null) {
            return mediaStreamIdentifierAttribute.getIdentificationTag();
        }
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(i));
        mediaDescription.addMediaAttribute(new MediaStreamIdAttribute(integerExtensions));
        return integerExtensions;
    }
}
