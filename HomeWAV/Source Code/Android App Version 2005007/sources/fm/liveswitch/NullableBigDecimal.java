package fm.liveswitch;

import java.math.BigDecimal;

public class NullableBigDecimal extends Nullable<BigDecimal> {
    public static NullableBigDecimal nullValue() {
        return new NullableBigDecimal((BigDecimal) null);
    }

    public NullableBigDecimal() {
    }

    public NullableBigDecimal(BigDecimal bigDecimal) {
        super(bigDecimal);
    }

    public BigDecimal getValue() {
        return (BigDecimal) this.value;
    }

    public void setValue(BigDecimal bigDecimal) {
        this.value = bigDecimal;
    }

    public BigDecimal getValueOrDefault() {
        if (hasValue()) {
            return (BigDecimal) this.value;
        }
        return new BigDecimal(0);
    }

    public String toString() {
        return getValueOrDefault().toString();
    }
}
