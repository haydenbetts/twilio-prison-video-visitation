package fm.liveswitch.stun.ice;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class UseCandidateAttribute extends Attribute {
    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
    }

    public int getTypeValue() {
        return Attribute.getUseCandidateType();
    }

    public static UseCandidateAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        return new UseCandidateAttribute();
    }

    public String toString() {
        return StringExtensions.format("USE-CANDIDATE", new Object[0]);
    }
}
