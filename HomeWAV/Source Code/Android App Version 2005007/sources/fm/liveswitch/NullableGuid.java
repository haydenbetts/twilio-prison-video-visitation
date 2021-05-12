package fm.liveswitch;

public class NullableGuid extends Nullable<Guid> {
    public static NullableGuid nullValue() {
        return new NullableGuid((Guid) null);
    }

    public static NullableGuid emptyValue() {
        return new NullableGuid(Guid.empty);
    }

    public NullableGuid() {
    }

    public NullableGuid(Guid guid) {
        super(guid);
    }

    public Guid getValue() {
        return (Guid) this.value;
    }

    public void setValue(Guid guid) {
        this.value = guid;
    }

    public Guid getValueOrDefault() {
        if (hasValue()) {
            return (Guid) this.value;
        }
        return Guid.empty;
    }

    public String toString() {
        return Guid.toString(getValueOrDefault());
    }
}
