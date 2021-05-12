package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

class BasicMonthOfYearDateTimeField extends ImpreciseDateTimeField {
    private static final int MIN = 1;
    private static final long serialVersionUID = -8258715387168736L;
    private final BasicChronology iChronology;
    private final int iLeapMonth;
    private final int iMax;

    public int getMinimumValue() {
        return 1;
    }

    public boolean isLenient() {
        return false;
    }

    BasicMonthOfYearDateTimeField(BasicChronology basicChronology, int i) {
        super(DateTimeFieldType.monthOfYear(), basicChronology.getAverageMillisPerMonth());
        this.iChronology = basicChronology;
        this.iMax = basicChronology.getMaxMonth();
        this.iLeapMonth = i;
    }

    public int get(long j) {
        return this.iChronology.getMonthOfYear(j);
    }

    public long add(long j, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (i == 0) {
            return j;
        }
        long millisOfDay = (long) this.iChronology.getMillisOfDay(j);
        int year = this.iChronology.getYear(j);
        int monthOfYear = this.iChronology.getMonthOfYear(j, year);
        int i7 = monthOfYear - 1;
        int i8 = i7 + i;
        if (monthOfYear <= 0 || i8 >= 0) {
            i2 = year;
        } else {
            if (Math.signum((float) (this.iMax + i)) == Math.signum((float) i)) {
                i6 = year - 1;
                i5 = i + this.iMax;
            } else {
                i6 = year + 1;
                i5 = i - this.iMax;
            }
            int i9 = i6;
            i8 = i5 + i7;
            i2 = i9;
        }
        if (i8 >= 0) {
            int i10 = this.iMax;
            i3 = i2 + (i8 / i10);
            i4 = (i8 % i10) + 1;
        } else {
            i3 = (i2 + (i8 / this.iMax)) - 1;
            int abs = Math.abs(i8);
            int i11 = this.iMax;
            int i12 = abs % i11;
            if (i12 == 0) {
                i12 = i11;
            }
            i4 = (i11 - i12) + 1;
            if (i4 == 1) {
                i3++;
            }
        }
        int dayOfMonth = this.iChronology.getDayOfMonth(j, year, monthOfYear);
        int daysInYearMonth = this.iChronology.getDaysInYearMonth(i3, i4);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(i3, i4, dayOfMonth) + millisOfDay;
    }

    public long add(long j, long j2) {
        long j3;
        long j4;
        long j5 = j;
        long j6 = j2;
        int i = (int) j6;
        if (((long) i) == j6) {
            return add(j5, i);
        }
        long millisOfDay = (long) this.iChronology.getMillisOfDay(j5);
        int year = this.iChronology.getYear(j5);
        int monthOfYear = this.iChronology.getMonthOfYear(j5, year);
        long j7 = ((long) (monthOfYear - 1)) + j6;
        if (j7 >= 0) {
            int i2 = this.iMax;
            j3 = ((long) year) + (j7 / ((long) i2));
            j4 = (j7 % ((long) i2)) + 1;
        } else {
            j3 = (((long) year) + (j7 / ((long) this.iMax))) - 1;
            long abs = Math.abs(j7);
            int i3 = this.iMax;
            int i4 = (int) (abs % ((long) i3));
            if (i4 == 0) {
                i4 = i3;
            }
            j4 = (long) ((i3 - i4) + 1);
            if (j4 == 1) {
                j3++;
            }
        }
        if (j3 < ((long) this.iChronology.getMinYear()) || j3 > ((long) this.iChronology.getMaxYear())) {
            throw new IllegalArgumentException("Magnitude of add amount is too large: " + j6);
        }
        int i5 = (int) j3;
        int i6 = (int) j4;
        int dayOfMonth = this.iChronology.getDayOfMonth(j5, year, monthOfYear);
        int daysInYearMonth = this.iChronology.getDaysInYearMonth(i5, i6);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(i5, i6, dayOfMonth) + millisOfDay;
    }

    public int[] add(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        if (i2 == 0) {
            return iArr;
        }
        if (readablePartial.size() > 0 && readablePartial.getFieldType(0).equals(DateTimeFieldType.monthOfYear()) && i == 0) {
            return set(readablePartial, 0, iArr, ((((iArr[0] - 1) + (i2 % 12)) + 12) % 12) + 1);
        }
        if (!DateTimeUtils.isContiguous(readablePartial)) {
            return super.add(readablePartial, i, iArr, i2);
        }
        long j = 0;
        int size = readablePartial.size();
        for (int i3 = 0; i3 < size; i3++) {
            j = readablePartial.getFieldType(i3).getField(this.iChronology).set(j, iArr[i3]);
        }
        return this.iChronology.get(readablePartial, add(j, i2));
    }

    public long addWrapField(long j, int i) {
        return set(j, FieldUtils.getWrappedValue(get(j), i, 1, this.iMax));
    }

    public long getDifferenceAsLong(long j, long j2) {
        if (j < j2) {
            return (long) (-getDifference(j2, j));
        }
        int year = this.iChronology.getYear(j);
        int monthOfYear = this.iChronology.getMonthOfYear(j, year);
        int year2 = this.iChronology.getYear(j2);
        int monthOfYear2 = this.iChronology.getMonthOfYear(j2, year2);
        long j3 = ((((long) (year - year2)) * ((long) this.iMax)) + ((long) monthOfYear)) - ((long) monthOfYear2);
        int dayOfMonth = this.iChronology.getDayOfMonth(j, year, monthOfYear);
        if (dayOfMonth == this.iChronology.getDaysInYearMonth(year, monthOfYear) && this.iChronology.getDayOfMonth(j2, year2, monthOfYear2) > dayOfMonth) {
            j2 = this.iChronology.dayOfMonth().set(j2, dayOfMonth);
        }
        return j - this.iChronology.getYearMonthMillis(year, monthOfYear) < j2 - this.iChronology.getYearMonthMillis(year2, monthOfYear2) ? j3 - 1 : j3;
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, 1, this.iMax);
        int year = this.iChronology.getYear(j);
        int dayOfMonth = this.iChronology.getDayOfMonth(j, year);
        int daysInYearMonth = this.iChronology.getDaysInYearMonth(year, i);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(year, i, dayOfMonth) + ((long) this.iChronology.getMillisOfDay(j));
    }

    public DurationField getRangeDurationField() {
        return this.iChronology.years();
    }

    public boolean isLeap(long j) {
        int year = this.iChronology.getYear(j);
        if (!this.iChronology.isLeapYear(year) || this.iChronology.getMonthOfYear(j, year) != this.iLeapMonth) {
            return false;
        }
        return true;
    }

    public int getLeapAmount(long j) {
        return isLeap(j) ? 1 : 0;
    }

    public DurationField getLeapDurationField() {
        return this.iChronology.days();
    }

    public int getMaximumValue() {
        return this.iMax;
    }

    public long roundFloor(long j) {
        int year = this.iChronology.getYear(j);
        return this.iChronology.getYearMonthMillis(year, this.iChronology.getMonthOfYear(j, year));
    }

    public long remainder(long j) {
        return j - roundFloor(j);
    }

    private Object readResolve() {
        return this.iChronology.monthOfYear();
    }
}
