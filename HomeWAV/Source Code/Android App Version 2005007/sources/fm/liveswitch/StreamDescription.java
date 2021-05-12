package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaStreamIdAttribute;

public class StreamDescription {
    private MediaDescription _mediaDescription;

    public MediaDescription getMediaDescription() {
        return this._mediaDescription;
    }

    public String getMediaDescriptionIdentifier() {
        MediaStreamIdAttribute mediaStreamIdentifierAttribute;
        MediaDescription mediaDescription = getMediaDescription();
        if (mediaDescription == null || (mediaStreamIdentifierAttribute = mediaDescription.getMediaStreamIdentifierAttribute()) == null) {
            return null;
        }
        return mediaStreamIdentifierAttribute.getIdentificationTag();
    }

    public StreamType getStreamType() {
        MediaDescription mediaDescription = getMediaDescription();
        if (mediaDescription != null) {
            if (mediaDescription.getIsApplication()) {
                return StreamType.Application;
            }
            if (mediaDescription.getIsAudio()) {
                return StreamType.Audio;
            }
            if (mediaDescription.getIsVideo()) {
                return StreamType.Video;
            }
            if (mediaDescription.getIsMessage()) {
                return StreamType.Message;
            }
            if (mediaDescription.getIsText()) {
                return StreamType.Text;
            }
        }
        throw new RuntimeException(new Exception("Could not identify the stream type from the media description."));
    }

    public void setMediaDescription(MediaDescription mediaDescription) {
        this._mediaDescription = mediaDescription;
    }

    public StreamDescription(MediaDescription mediaDescription) {
        setMediaDescription(mediaDescription);
    }
}
