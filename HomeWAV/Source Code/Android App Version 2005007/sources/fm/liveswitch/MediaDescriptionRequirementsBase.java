package fm.liveswitch;

abstract class MediaDescriptionRequirementsBase {
    private String _mediaStreamIdentifier;

    public String getMediaStreamIdentifier() {
        return this._mediaStreamIdentifier;
    }

    protected MediaDescriptionRequirementsBase() {
    }

    public void setMediaStreamIdentifier(String str) {
        this._mediaStreamIdentifier = str;
    }
}
