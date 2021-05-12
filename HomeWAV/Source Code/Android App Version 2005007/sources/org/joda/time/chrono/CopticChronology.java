package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.SkipDateTimeField;

public final class CopticChronology extends BasicFixedMonthChronology {
    public static final int AM = 1;
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AM");
    private static final CopticChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final int MAX_YEAR = 292272708;
    private static final int MIN_YEAR = -292269337;
    private static final ConcurrentHashMap<DateTimeZone, CopticChronology[]> cCache = new ConcurrentHashMap<>();
    private static final long serialVersionUID = -5972804258688333942L;

    /* access modifiers changed from: package-private */
    public long getApproxMillisAtEpochDividedByTwo() {
        return 26607895200000L;
    }

    /* access modifiers changed from: package-private */
    public int getMaxYear() {
        return MAX_YEAR;
    }

    /* access modifiers changed from: package-private */
    public int getMinYear() {
        return MIN_YEAR;
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ long getDateTimeMillis(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        return super.getDateTimeMillis(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ long getDateTimeMillis(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        return super.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
    }

    public /* bridge */ /* synthetic */ int getMinimumDaysInFirstWeek() {
        return super.getMinimumDaysInFirstWeek();
    }

    public /* bridge */ /* synthetic */ DateTimeZone getZone() {
        return super.getZone();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static CopticChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static CopticChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static CopticChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r1 = new org.joda.time.chrono.CopticChronology[7];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.joda.time.chrono.CopticChronology getInstance(org.joda.time.DateTimeZone r13, int r14) {
        /*
            if (r13 != 0) goto L_0x0006
            org.joda.time.DateTimeZone r13 = org.joda.time.DateTimeZone.getDefault()
        L_0x0006:
            java.util.concurrent.ConcurrentHashMap<org.joda.time.DateTimeZone, org.joda.time.chrono.CopticChronology[]> r0 = cCache
            java.lang.Object r1 = r0.get(r13)
            org.joda.time.chrono.CopticChronology[] r1 = (org.joda.time.chrono.CopticChronology[]) r1
            if (r1 != 0) goto L_0x001c
            r1 = 7
            org.joda.time.chrono.CopticChronology[] r1 = new org.joda.time.chrono.CopticChronology[r1]
            java.lang.Object r0 = r0.putIfAbsent(r13, r1)
            org.joda.time.chrono.CopticChronology[] r0 = (org.joda.time.chrono.CopticChronology[]) r0
            if (r0 == 0) goto L_0x001c
            r1 = r0
        L_0x001c:
            int r0 = r14 + -1
            r2 = r1[r0]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0061 }
            if (r2 != 0) goto L_0x0060
            monitor-enter(r1)
            r2 = r1[r0]     // Catch:{ all -> 0x005d }
            if (r2 != 0) goto L_0x005b
            org.joda.time.DateTimeZone r2 = org.joda.time.DateTimeZone.UTC     // Catch:{ all -> 0x005d }
            r3 = 0
            if (r13 != r2) goto L_0x0049
            org.joda.time.chrono.CopticChronology r13 = new org.joda.time.chrono.CopticChronology     // Catch:{ all -> 0x005d }
            r13.<init>(r3, r3, r14)     // Catch:{ all -> 0x005d }
            org.joda.time.DateTime r2 = new org.joda.time.DateTime     // Catch:{ all -> 0x005d }
            r5 = 1
            r6 = 1
            r7 = 1
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r4 = r2
            r12 = r13
            r4.<init>((int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (int) r10, (int) r11, (org.joda.time.Chronology) r12)     // Catch:{ all -> 0x005d }
            org.joda.time.chrono.CopticChronology r4 = new org.joda.time.chrono.CopticChronology     // Catch:{ all -> 0x005d }
            org.joda.time.chrono.LimitChronology r13 = org.joda.time.chrono.LimitChronology.getInstance(r13, r2, r3)     // Catch:{ all -> 0x005d }
            r4.<init>(r13, r3, r14)     // Catch:{ all -> 0x005d }
            goto L_0x0058
        L_0x0049:
            org.joda.time.DateTimeZone r2 = org.joda.time.DateTimeZone.UTC     // Catch:{ all -> 0x005d }
            org.joda.time.chrono.CopticChronology r2 = getInstance(r2, r14)     // Catch:{ all -> 0x005d }
            org.joda.time.chrono.CopticChronology r4 = new org.joda.time.chrono.CopticChronology     // Catch:{ all -> 0x005d }
            org.joda.time.chrono.ZonedChronology r13 = org.joda.time.chrono.ZonedChronology.getInstance(r2, r13)     // Catch:{ all -> 0x005d }
            r4.<init>(r13, r3, r14)     // Catch:{ all -> 0x005d }
        L_0x0058:
            r1[r0] = r4     // Catch:{ all -> 0x005d }
            r2 = r4
        L_0x005b:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            goto L_0x0060
        L_0x005d:
            r13 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            throw r13
        L_0x0060:
            return r2
        L_0x0061:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid min days in first week: "
            r0.append(r1)
            r0.append(r14)
            java.lang.String r14 = r0.toString()
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.chrono.CopticChronology.getInstance(org.joda.time.DateTimeZone, int):org.joda.time.chrono.CopticChronology");
    }

    CopticChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    private Object readResolve() {
        Chronology base = getBase();
        int minimumDaysInFirstWeek = getMinimumDaysInFirstWeek();
        if (minimumDaysInFirstWeek == 0) {
            minimumDaysInFirstWeek = 4;
        }
        if (base == null) {
            return getInstance(DateTimeZone.UTC, minimumDaysInFirstWeek);
        }
        return getInstance(base.getZone(), minimumDaysInFirstWeek);
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        if (dateTimeZone == getZone()) {
            return this;
        }
        return getInstance(dateTimeZone);
    }

    /* access modifiers changed from: package-private */
    public boolean isLeapDay(long j) {
        return dayOfMonth().get(j) == 6 && monthOfYear().isLeap(j);
    }

    /* access modifiers changed from: package-private */
    public long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1687;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            int i4 = i3 >> 2;
            i2 = !isLeapYear(i) ? i4 + 1 : i4;
        }
        return (((((long) i3) * 365) + ((long) i2)) * 86400000) + 21859200000L;
    }

    /* access modifiers changed from: protected */
    public void assemble(AssembledChronology.Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
            fields.era = ERA_FIELD;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
            fields.months = fields.monthOfYear.getDurationField();
        }
    }
}
