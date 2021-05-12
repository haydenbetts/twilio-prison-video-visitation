package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public abstract class PreciseDurationDateTimeField extends BaseDateTimeField {
    private static final long serialVersionUID = 5004523158306266035L;
    private final DurationField iUnitField;
    final long iUnitMillis;

    public int getMinimumValue() {
        return 0;
    }

    public boolean isLenient() {
        return false;
    }

    public PreciseDurationDateTimeField(DateTimeFieldType dateTimeFieldType, DurationField durationField) {
        super(dateTimeFieldType);
        if (durationField.isPrecise()) {
            long unitMillis = durationField.getUnitMillis();
            this.iUnitMillis = unitMillis;
            if (unitMillis >= 1) {
                this.iUnitField = durationField;
                return;
            }
            throw new IllegalArgumentException("The unit milliseconds must be at least 1");
        }
        throw new IllegalArgumentException("Unit duration field must be precise");
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, getMinimumValue(), getMaximumValueForSet(j, i));
        return j + (((long) (i - get(j))) * this.iUnitMillis);
    }

    public long roundFloor(long j) {
        long j2;
        if (j >= 0) {
            j2 = j % this.iUnitMillis;
        } else {
            long j3 = j + 1;
            j2 = this.iUnitMillis;
            j = j3 - (j3 % j2);
        }
        return j - j2;
    }

    public long roundCeiling(long j) {
        if (j <= 0) {
            return j - (j % this.iUnitMillis);
        }
        long j2 = j - 1;
        long j3 = this.iUnitMillis;
        return (j2 - (j2 % j3)) + j3;
    }

    public long remainder(long j) {
        if (j >= 0) {
            return j % this.iUnitMillis;
        }
        long j2 = this.iUnitMillis;
        return (((j + 1) % j2) + j2) - 1;
    }

    public DurationField getDurationField() {
        return this.iUnitField;
    }

    public final long getUnitMillis() {
        return this.iUnitMillis;
    }

    /* access modifiers changed from: protected */
    public int getMaximumValueForSet(long j, int i) {
        return getMaximumValue(j);
    }
}
