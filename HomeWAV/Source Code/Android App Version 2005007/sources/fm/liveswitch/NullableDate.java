package fm.liveswitch;

import java.util.Date;

public class NullableDate extends Nullable<Date> {
    public static NullableDate nullValue() {
        return new NullableDate((Date) null);
    }

    public NullableDate() {
    }

    public NullableDate(Date date) {
        super(date);
    }

    public Date getValue() {
        return (Date) this.value;
    }

    public void setValue(Date date) {
        this.value = date;
    }

    public Date getValueOrDefault() {
        if (hasValue()) {
            return (Date) this.value;
        }
        return new Date();
    }
}
