package fm.liveswitch.sdp.ice;

public class TrickleIceOptionTag extends OptionTag {
    public String toString() {
        return OptionTag.getTrickle();
    }

    public TrickleIceOptionTag() {
        super.setType(OptionTagType.Trickle);
    }
}
