package fm.liveswitch.sdp.ice;

import fm.liveswitch.Global;

public abstract class OptionTag {
    private OptionTagType _type;

    public static String getTrickle() {
        return "trickle";
    }

    public abstract String toString();

    public OptionTagType getType() {
        return this._type;
    }

    protected OptionTag() {
    }

    public static OptionTag parse(String str) {
        if (Global.equals(str, getTrickle())) {
            return new TrickleIceOptionTag();
        }
        return new UnknownIceOptionTag(str);
    }

    /* access modifiers changed from: protected */
    public void setType(OptionTagType optionTagType) {
        this._type = optionTagType;
    }
}
