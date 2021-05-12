package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class LifetimeAttribute extends Attribute {
    private long _lifetime;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    public long getLifetime() {
        return this._lifetime;
    }

    public int getTypeValue() {
        return Attribute.getLifetimeType();
    }

    private LifetimeAttribute() {
    }

    public LifetimeAttribute(long j) {
        setLifetime(j);
    }

    public static LifetimeAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        LifetimeAttribute lifetimeAttribute = new LifetimeAttribute();
        lifetimeAttribute.setLifetime(dataBuffer.read32(i));
        return lifetimeAttribute;
    }

    public void setLifetime(long j) {
        this._lifetime = j;
    }

    public String toString() {
        return StringExtensions.format("LIFETIME {0}", (Object) LongExtensions.toString(Long.valueOf(getLifetime())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write32(getLifetime(), i);
    }
}
