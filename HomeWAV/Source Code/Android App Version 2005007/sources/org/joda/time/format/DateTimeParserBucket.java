package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;
import kotlin.text.Typography;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

public class DateTimeParserBucket {
    private final Chronology iChrono;
    private final Integer iDefaultPivotYear;
    private final int iDefaultYear;
    private final DateTimeZone iDefaultZone;
    private final Locale iLocale;
    private final long iMillis;
    /* access modifiers changed from: private */
    public Integer iOffset;
    private Integer iPivotYear;
    /* access modifiers changed from: private */
    public SavedField[] iSavedFields;
    /* access modifiers changed from: private */
    public int iSavedFieldsCount;
    /* access modifiers changed from: private */
    public boolean iSavedFieldsShared;
    private Object iSavedState;
    /* access modifiers changed from: private */
    public DateTimeZone iZone;

    @Deprecated
    public DateTimeParserBucket(long j, Chronology chronology, Locale locale) {
        this(j, chronology, locale, (Integer) null, 2000);
    }

    @Deprecated
    public DateTimeParserBucket(long j, Chronology chronology, Locale locale, Integer num) {
        this(j, chronology, locale, num, 2000);
    }

    public DateTimeParserBucket(long j, Chronology chronology, Locale locale, Integer num, int i) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iMillis = j;
        DateTimeZone zone = chronology2.getZone();
        this.iDefaultZone = zone;
        this.iChrono = chronology2.withUTC();
        this.iLocale = locale == null ? Locale.getDefault() : locale;
        this.iDefaultYear = i;
        this.iDefaultPivotYear = num;
        this.iZone = zone;
        this.iPivotYear = num;
        this.iSavedFields = new SavedField[8];
    }

    public void reset() {
        this.iZone = this.iDefaultZone;
        this.iOffset = null;
        this.iPivotYear = this.iDefaultPivotYear;
        this.iSavedFieldsCount = 0;
        this.iSavedFieldsShared = false;
        this.iSavedState = null;
    }

    public long parseMillis(DateTimeParser dateTimeParser, CharSequence charSequence) {
        reset();
        return doParseMillis(DateTimeParserInternalParser.of(dateTimeParser), charSequence);
    }

    /* access modifiers changed from: package-private */
    public long doParseMillis(InternalParser internalParser, CharSequence charSequence) {
        int parseInto = internalParser.parseInto(this, charSequence, 0);
        if (parseInto < 0) {
            parseInto = ~parseInto;
        } else if (parseInto >= charSequence.length()) {
            return computeMillis(true, charSequence);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(charSequence.toString(), parseInto));
    }

    public Chronology getChronology() {
        return this.iChrono;
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public void setZone(DateTimeZone dateTimeZone) {
        this.iSavedState = null;
        this.iZone = dateTimeZone;
    }

    @Deprecated
    public int getOffset() {
        Integer num = this.iOffset;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public Integer getOffsetInteger() {
        return this.iOffset;
    }

    @Deprecated
    public void setOffset(int i) {
        this.iSavedState = null;
        this.iOffset = Integer.valueOf(i);
    }

    public void setOffset(Integer num) {
        this.iSavedState = null;
        this.iOffset = num;
    }

    public Integer getPivotYear() {
        return this.iPivotYear;
    }

    @Deprecated
    public void setPivotYear(Integer num) {
        this.iPivotYear = num;
    }

    public void saveField(DateTimeField dateTimeField, int i) {
        obtainSaveField().init(dateTimeField, i);
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, int i) {
        obtainSaveField().init(dateTimeFieldType.getField(this.iChrono), i);
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, String str, Locale locale) {
        obtainSaveField().init(dateTimeFieldType.getField(this.iChrono), str, locale);
    }

    private SavedField obtainSaveField() {
        SavedField[] savedFieldArr = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (i == savedFieldArr.length || this.iSavedFieldsShared) {
            SavedField[] savedFieldArr2 = new SavedField[(i == savedFieldArr.length ? i * 2 : savedFieldArr.length)];
            System.arraycopy(savedFieldArr, 0, savedFieldArr2, 0, i);
            this.iSavedFields = savedFieldArr2;
            this.iSavedFieldsShared = false;
            savedFieldArr = savedFieldArr2;
        }
        this.iSavedState = null;
        SavedField savedField = savedFieldArr[i];
        if (savedField == null) {
            savedField = new SavedField();
            savedFieldArr[i] = savedField;
        }
        this.iSavedFieldsCount = i + 1;
        return savedField;
    }

    public Object saveState() {
        if (this.iSavedState == null) {
            this.iSavedState = new SavedState();
        }
        return this.iSavedState;
    }

    public boolean restoreState(Object obj) {
        if (!(obj instanceof SavedState) || !((SavedState) obj).restoreState(this)) {
            return false;
        }
        this.iSavedState = obj;
        return true;
    }

    public long computeMillis() {
        return computeMillis(false, (CharSequence) null);
    }

    public long computeMillis(boolean z) {
        return computeMillis(z, (CharSequence) null);
    }

    public long computeMillis(boolean z, String str) {
        return computeMillis(z, (CharSequence) str);
    }

    public long computeMillis(boolean z, CharSequence charSequence) {
        SavedField[] savedFieldArr = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (this.iSavedFieldsShared) {
            savedFieldArr = (SavedField[]) savedFieldArr.clone();
            this.iSavedFields = savedFieldArr;
            this.iSavedFieldsShared = false;
        }
        sort(savedFieldArr, i);
        if (i > 0) {
            DurationField field = DurationFieldType.months().getField(this.iChrono);
            DurationField field2 = DurationFieldType.days().getField(this.iChrono);
            DurationField durationField = savedFieldArr[0].iField.getDurationField();
            if (compareReverse(durationField, field) >= 0 && compareReverse(durationField, field2) <= 0) {
                saveField(DateTimeFieldType.year(), this.iDefaultYear);
                return computeMillis(z, charSequence);
            }
        }
        long j = this.iMillis;
        int i2 = 0;
        while (i2 < i) {
            try {
                j = savedFieldArr[i2].set(j, z);
                i2++;
            } catch (IllegalFieldValueException e) {
                if (charSequence != null) {
                    e.prependMessage("Cannot parse \"" + charSequence + Typography.quote);
                }
                throw e;
            }
        }
        if (z) {
            int i3 = 0;
            while (i3 < i) {
                if (!savedFieldArr[i3].iField.isLenient()) {
                    j = savedFieldArr[i3].set(j, i3 == i + -1);
                }
                i3++;
            }
        }
        Integer num = this.iOffset;
        if (num != null) {
            return j - ((long) num.intValue());
        }
        DateTimeZone dateTimeZone = this.iZone;
        if (dateTimeZone == null) {
            return j;
        }
        int offsetFromLocal = dateTimeZone.getOffsetFromLocal(j);
        long j2 = j - ((long) offsetFromLocal);
        if (offsetFromLocal == this.iZone.getOffset(j2)) {
            return j2;
        }
        String str = "Illegal instant due to time zone offset transition (" + this.iZone + ')';
        if (charSequence != null) {
            str = "Cannot parse \"" + charSequence + "\": " + str;
        }
        throw new IllegalInstantException(str);
    }

    private static void sort(SavedField[] savedFieldArr, int i) {
        if (i > 10) {
            Arrays.sort(savedFieldArr, 0, i);
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = i2; i3 > 0; i3--) {
                int i4 = i3 - 1;
                if (savedFieldArr[i4].compareTo(savedFieldArr[i3]) <= 0) {
                    break;
                }
                SavedField savedField = savedFieldArr[i3];
                savedFieldArr[i3] = savedFieldArr[i4];
                savedFieldArr[i4] = savedField;
            }
        }
    }

    class SavedState {
        final Integer iOffset;
        final SavedField[] iSavedFields;
        final int iSavedFieldsCount;
        final DateTimeZone iZone;

        SavedState() {
            this.iZone = DateTimeParserBucket.this.iZone;
            this.iOffset = DateTimeParserBucket.this.iOffset;
            this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
            this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
        }

        /* access modifiers changed from: package-private */
        public boolean restoreState(DateTimeParserBucket dateTimeParserBucket) {
            if (dateTimeParserBucket != DateTimeParserBucket.this) {
                return false;
            }
            DateTimeZone unused = dateTimeParserBucket.iZone = this.iZone;
            Integer unused2 = dateTimeParserBucket.iOffset = this.iOffset;
            SavedField[] unused3 = dateTimeParserBucket.iSavedFields = this.iSavedFields;
            if (this.iSavedFieldsCount < dateTimeParserBucket.iSavedFieldsCount) {
                boolean unused4 = dateTimeParserBucket.iSavedFieldsShared = true;
            }
            int unused5 = dateTimeParserBucket.iSavedFieldsCount = this.iSavedFieldsCount;
            return true;
        }
    }

    static class SavedField implements Comparable<SavedField> {
        DateTimeField iField;
        Locale iLocale;
        String iText;
        int iValue;

        SavedField() {
        }

        /* access modifiers changed from: package-private */
        public void init(DateTimeField dateTimeField, int i) {
            this.iField = dateTimeField;
            this.iValue = i;
            this.iText = null;
            this.iLocale = null;
        }

        /* access modifiers changed from: package-private */
        public void init(DateTimeField dateTimeField, String str, Locale locale) {
            this.iField = dateTimeField;
            this.iValue = 0;
            this.iText = str;
            this.iLocale = locale;
        }

        /* access modifiers changed from: package-private */
        public long set(long j, boolean z) {
            long j2;
            String str = this.iText;
            if (str == null) {
                j2 = this.iField.setExtended(j, this.iValue);
            } else {
                j2 = this.iField.set(j, str, this.iLocale);
            }
            return z ? this.iField.roundFloor(j2) : j2;
        }

        public int compareTo(SavedField savedField) {
            DateTimeField dateTimeField = savedField.iField;
            int compareReverse = DateTimeParserBucket.compareReverse(this.iField.getRangeDurationField(), dateTimeField.getRangeDurationField());
            if (compareReverse != 0) {
                return compareReverse;
            }
            return DateTimeParserBucket.compareReverse(this.iField.getDurationField(), dateTimeField.getDurationField());
        }
    }

    static int compareReverse(DurationField durationField, DurationField durationField2) {
        if (durationField == null || !durationField.isSupported()) {
            return (durationField2 == null || !durationField2.isSupported()) ? 0 : -1;
        }
        if (durationField2 == null || !durationField2.isSupported()) {
            return 1;
        }
        return -durationField.compareTo(durationField2);
    }
}
