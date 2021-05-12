package fm.liveswitch.sdp.ice;

public class UnknownIceOptionTag extends OptionTag {
    private String _tagString;

    public String getTagString() {
        return this._tagString;
    }

    private void setTagString(String str) {
        this._tagString = str;
    }

    public String toString() {
        return getTagString();
    }

    public UnknownIceOptionTag(String str) {
        super.setType(OptionTagType.Unknown);
        setTagString(str);
    }
}
