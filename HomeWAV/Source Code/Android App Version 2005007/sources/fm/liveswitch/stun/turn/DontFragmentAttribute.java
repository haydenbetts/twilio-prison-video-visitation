package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class DontFragmentAttribute extends Attribute {
    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
    }

    public int getTypeValue() {
        return Attribute.getDontFragmentType();
    }

    public static DontFragmentAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        return new DontFragmentAttribute();
    }

    public String toString() {
        return StringExtensions.format("DONT-FRAGMENT", new Object[0]);
    }
}
