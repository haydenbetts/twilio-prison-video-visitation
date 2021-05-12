package org.jetbrains.anko.db;

import android.database.Cursor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u001e\u0010\u001f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e0 *\u00020\u001b\u001a\u0018\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180 *\u00020\u001b\u001a\u0016\u0010\"\u001a\u0004\u0018\u00010\u0019*\u00020\u001b2\u0006\u0010#\u001a\u00020\fH\u0002\u001a \u0010$\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e0 *\u00020\u001bH\u0007\u001a(\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)\u001a(\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001\u001a)\u0010*\u001a\u0004\u0018\u0001H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)¢\u0006\u0002\u0010+\u001a)\u0010*\u001a\u0004\u0018\u0001H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001¢\u0006\u0002\u0010,\u001a'\u0010-\u001a\u0002H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)¢\u0006\u0002\u0010+\u001a'\u0010-\u001a\u0002H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001¢\u0006\u0002\u0010,\u001a\u001a\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180 *\u00020\u001bH\u0007\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0004\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0004\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0004\"\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0004\"\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0004\"\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0004¨\u0006/"}, d2 = {"BlobParser", "Lorg/jetbrains/anko/db/RowParser;", "", "getBlobParser", "()Lorg/jetbrains/anko/db/RowParser;", "DoubleParser", "", "getDoubleParser", "FloatParser", "", "getFloatParser", "IntParser", "", "getIntParser", "LongParser", "", "getLongParser", "ShortParser", "", "getShortParser", "StringParser", "", "getStringParser", "readColumnsArray", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)[Ljava/lang/Object;", "readColumnsMap", "", "asMapSequence", "Lkotlin/sequences/Sequence;", "asSequence", "getColumnValue", "index", "mapSequence", "parseList", "", "T", "parser", "Lorg/jetbrains/anko/db/MapRowParser;", "parseOpt", "(Landroid/database/Cursor;Lorg/jetbrains/anko/db/MapRowParser;)Ljava/lang/Object;", "(Landroid/database/Cursor;Lorg/jetbrains/anko/db/RowParser;)Ljava/lang/Object;", "parseSingle", "sequence", "sqlite-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: SqlParsers.kt */
public final class SqlParsersKt {
    private static final RowParser<byte[]> BlobParser = new SingleColumnParser();
    private static final RowParser<Double> DoubleParser = new SingleColumnParser();
    private static final RowParser<Float> FloatParser = new ScalarColumnParser(SqlParsersKt$FloatParser$1.INSTANCE);
    private static final RowParser<Integer> IntParser = new ScalarColumnParser(SqlParsersKt$IntParser$1.INSTANCE);
    private static final RowParser<Long> LongParser = new SingleColumnParser();
    private static final RowParser<Short> ShortParser = new ScalarColumnParser(SqlParsersKt$ShortParser$1.INSTANCE);
    private static final RowParser<String> StringParser = new SingleColumnParser();

    public static final RowParser<Short> getShortParser() {
        return ShortParser;
    }

    public static final RowParser<Integer> getIntParser() {
        return IntParser;
    }

    public static final RowParser<Long> getLongParser() {
        return LongParser;
    }

    public static final RowParser<Float> getFloatParser() {
        return FloatParser;
    }

    public static final RowParser<Double> getDoubleParser() {
        return DoubleParser;
    }

    public static final RowParser<String> getStringParser() {
        return StringParser;
    }

    public static final RowParser<byte[]> getBlobParser() {
        return BlobParser;
    }

    @Deprecated(message = "Use asSequence() instead", replaceWith = @ReplaceWith(expression = "asSequence()", imports = {}))
    public static final Sequence<Object[]> sequence(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorSequence(cursor);
    }

    @Deprecated(message = "Use asMapSequence() instead", replaceWith = @ReplaceWith(expression = "asMapSequence()", imports = {}))
    public static final Sequence<Map<String, Object>> mapSequence(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorMapSequence(cursor);
    }

    public static final Sequence<Object[]> asSequence(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorSequence(cursor);
    }

    public static final Sequence<Map<String, Object>> asMapSequence(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "receiver$0");
        return new CursorMapSequence(cursor);
    }

    private static final Object getColumnValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        int type = cursor.getType(i);
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type != 4) {
            return null;
        }
        return (Serializable) cursor.getBlob(i);
    }

    /* access modifiers changed from: private */
    public static final Object[] readColumnsArray(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        Object[] objArr = new Object[columnCount];
        int i = columnCount - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                objArr[i2] = getColumnValue(cursor, i2);
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return objArr;
    }

    /* access modifiers changed from: private */
    public static final Map<String, Object> readColumnsMap(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        HashMap hashMap = new HashMap();
        int i = columnCount - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                hashMap.put(cursor.getColumnName(i2), getColumnValue(cursor, i2));
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T parseSingle(android.database.Cursor r5, org.jetbrains.anko.db.RowParser<? extends T> r6) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "parser"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "parseSingle accepts only cursors with a single entry"
            r2 = 1
            r3 = 16
            if (r0 < r3) goto L_0x0040
            r0 = r5
            java.io.Closeable r0 = (java.io.Closeable) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r4 = r0
            android.database.Cursor r4 = (android.database.Cursor) r4     // Catch:{ all -> 0x0039 }
            int r4 = r5.getCount()     // Catch:{ all -> 0x0039 }
            if (r4 != r2) goto L_0x0031
            r5.moveToFirst()     // Catch:{ all -> 0x0039 }
            java.lang.Object[] r5 = readColumnsArray(r5)     // Catch:{ all -> 0x0039 }
            java.lang.Object r5 = r6.parseRow(r5)     // Catch:{ all -> 0x0039 }
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            return r5
        L_0x0031:
            android.database.sqlite.SQLiteException r5 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x0039 }
            r5.<init>(r1)     // Catch:{ all -> 0x0039 }
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ all -> 0x0039 }
            throw r5     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003b }
        L_0x003b:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r5)
            throw r6
        L_0x0040:
            int r0 = r5.getCount()     // Catch:{ all -> 0x005d }
            if (r0 != r2) goto L_0x0055
            r5.moveToFirst()     // Catch:{ all -> 0x005d }
            java.lang.Object[] r0 = readColumnsArray(r5)     // Catch:{ all -> 0x005d }
            java.lang.Object r6 = r6.parseRow(r0)     // Catch:{ all -> 0x005d }
            r5.close()     // Catch:{ Exception -> 0x0054 }
        L_0x0054:
            return r6
        L_0x0055:
            android.database.sqlite.SQLiteException r6 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x005d }
            r6.<init>(r1)     // Catch:{ all -> 0x005d }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x005d }
            throw r6     // Catch:{ all -> 0x005d }
        L_0x005d:
            r6 = move-exception
            r5.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseSingle(android.database.Cursor, org.jetbrains.anko.db.RowParser):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T parseOpt(android.database.Cursor r6, org.jetbrains.anko.db.RowParser<? extends T> r7) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "parser"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "parseSingle accepts only cursors with a single entry or empty cursors"
            r2 = 1
            r3 = 0
            r4 = 16
            if (r0 < r4) goto L_0x004b
            r0 = r6
            java.io.Closeable r0 = (java.io.Closeable) r0
            r4 = r3
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r5 = r0
            android.database.Cursor r5 = (android.database.Cursor) r5     // Catch:{ all -> 0x0044 }
            int r5 = r6.getCount()     // Catch:{ all -> 0x0044 }
            if (r5 > r2) goto L_0x003c
            int r1 = r6.getCount()     // Catch:{ all -> 0x0044 }
            if (r1 != 0) goto L_0x002d
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            return r3
        L_0x002d:
            r6.moveToFirst()     // Catch:{ all -> 0x0044 }
            java.lang.Object[] r6 = readColumnsArray(r6)     // Catch:{ all -> 0x0044 }
            java.lang.Object r6 = r7.parseRow(r6)     // Catch:{ all -> 0x0044 }
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            return r6
        L_0x003c:
            android.database.sqlite.SQLiteException r6 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x0044 }
            r6.<init>(r1)     // Catch:{ all -> 0x0044 }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x0044 }
            throw r6     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r6)
            throw r7
        L_0x004b:
            int r0 = r6.getCount()     // Catch:{ all -> 0x0072 }
            if (r0 > r2) goto L_0x006a
            int r0 = r6.getCount()     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x005b
            r6.close()     // Catch:{ Exception -> 0x005a }
        L_0x005a:
            return r3
        L_0x005b:
            r6.moveToFirst()     // Catch:{ all -> 0x0072 }
            java.lang.Object[] r0 = readColumnsArray(r6)     // Catch:{ all -> 0x0072 }
            java.lang.Object r7 = r7.parseRow(r0)     // Catch:{ all -> 0x0072 }
            r6.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0069:
            return r7
        L_0x006a:
            android.database.sqlite.SQLiteException r7 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x0072 }
            r7.<init>(r1)     // Catch:{ all -> 0x0072 }
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x0072 }
            throw r7     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r7 = move-exception
            r6.close()     // Catch:{ Exception -> 0x0076 }
        L_0x0076:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseOpt(android.database.Cursor, org.jetbrains.anko.db.RowParser):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.util.List<T> parseList(android.database.Cursor r4, org.jetbrains.anko.db.RowParser<? extends T> r5) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "parser"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0047
            r0 = r4
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r0
            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x0040 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0040 }
            int r3 = r4.getCount()     // Catch:{ all -> 0x0040 }
            r2.<init>(r3)     // Catch:{ all -> 0x0040 }
            r4.moveToFirst()     // Catch:{ all -> 0x0040 }
        L_0x0025:
            boolean r3 = r4.isAfterLast()     // Catch:{ all -> 0x0040 }
            if (r3 != 0) goto L_0x003a
            java.lang.Object[] r3 = readColumnsArray(r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r3 = r5.parseRow(r3)     // Catch:{ all -> 0x0040 }
            r2.add(r3)     // Catch:{ all -> 0x0040 }
            r4.moveToNext()     // Catch:{ all -> 0x0040 }
            goto L_0x0025
        L_0x003a:
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0040 }
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return r2
        L_0x0040:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            throw r5
        L_0x0047:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x006e }
            int r1 = r4.getCount()     // Catch:{ all -> 0x006e }
            r0.<init>(r1)     // Catch:{ all -> 0x006e }
            r4.moveToFirst()     // Catch:{ all -> 0x006e }
        L_0x0053:
            boolean r1 = r4.isAfterLast()     // Catch:{ all -> 0x006e }
            if (r1 != 0) goto L_0x0068
            java.lang.Object[] r1 = readColumnsArray(r4)     // Catch:{ all -> 0x006e }
            java.lang.Object r1 = r5.parseRow(r1)     // Catch:{ all -> 0x006e }
            r0.add(r1)     // Catch:{ all -> 0x006e }
            r4.moveToNext()     // Catch:{ all -> 0x006e }
            goto L_0x0053
        L_0x0068:
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x006e }
            r4.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            return r0
        L_0x006e:
            r5 = move-exception
            r4.close()     // Catch:{ Exception -> 0x0072 }
        L_0x0072:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseList(android.database.Cursor, org.jetbrains.anko.db.RowParser):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T parseSingle(android.database.Cursor r5, org.jetbrains.anko.db.MapRowParser<? extends T> r6) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "parser"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "parseSingle accepts only cursors with getCount() == 1"
            r2 = 1
            r3 = 16
            if (r0 < r3) goto L_0x0040
            r0 = r5
            java.io.Closeable r0 = (java.io.Closeable) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r4 = r0
            android.database.Cursor r4 = (android.database.Cursor) r4     // Catch:{ all -> 0x0039 }
            int r4 = r5.getCount()     // Catch:{ all -> 0x0039 }
            if (r4 != r2) goto L_0x0031
            r5.moveToFirst()     // Catch:{ all -> 0x0039 }
            java.util.Map r5 = readColumnsMap(r5)     // Catch:{ all -> 0x0039 }
            java.lang.Object r5 = r6.parseRow(r5)     // Catch:{ all -> 0x0039 }
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            return r5
        L_0x0031:
            android.database.sqlite.SQLiteException r5 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x0039 }
            r5.<init>(r1)     // Catch:{ all -> 0x0039 }
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ all -> 0x0039 }
            throw r5     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003b }
        L_0x003b:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r5)
            throw r6
        L_0x0040:
            int r0 = r5.getCount()     // Catch:{ all -> 0x005d }
            if (r0 != r2) goto L_0x0055
            r5.moveToFirst()     // Catch:{ all -> 0x005d }
            java.util.Map r0 = readColumnsMap(r5)     // Catch:{ all -> 0x005d }
            java.lang.Object r6 = r6.parseRow(r0)     // Catch:{ all -> 0x005d }
            r5.close()     // Catch:{ Exception -> 0x0054 }
        L_0x0054:
            return r6
        L_0x0055:
            android.database.sqlite.SQLiteException r6 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x005d }
            r6.<init>(r1)     // Catch:{ all -> 0x005d }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x005d }
            throw r6     // Catch:{ all -> 0x005d }
        L_0x005d:
            r6 = move-exception
            r5.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseSingle(android.database.Cursor, org.jetbrains.anko.db.MapRowParser):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T parseOpt(android.database.Cursor r6, org.jetbrains.anko.db.MapRowParser<? extends T> r7) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "parser"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "parseSingle accepts only cursors with getCount() == 1 or empty cursors"
            r2 = 1
            r3 = 0
            r4 = 16
            if (r0 < r4) goto L_0x004b
            r0 = r6
            java.io.Closeable r0 = (java.io.Closeable) r0
            r4 = r3
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r5 = r0
            android.database.Cursor r5 = (android.database.Cursor) r5     // Catch:{ all -> 0x0044 }
            int r5 = r6.getCount()     // Catch:{ all -> 0x0044 }
            if (r5 > r2) goto L_0x003c
            int r1 = r6.getCount()     // Catch:{ all -> 0x0044 }
            if (r1 != 0) goto L_0x002d
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            return r3
        L_0x002d:
            r6.moveToFirst()     // Catch:{ all -> 0x0044 }
            java.util.Map r6 = readColumnsMap(r6)     // Catch:{ all -> 0x0044 }
            java.lang.Object r6 = r7.parseRow(r6)     // Catch:{ all -> 0x0044 }
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            return r6
        L_0x003c:
            android.database.sqlite.SQLiteException r6 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x0044 }
            r6.<init>(r1)     // Catch:{ all -> 0x0044 }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x0044 }
            throw r6     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r6)
            throw r7
        L_0x004b:
            int r0 = r6.getCount()     // Catch:{ all -> 0x0072 }
            if (r0 > r2) goto L_0x006a
            int r0 = r6.getCount()     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x005b
            r6.close()     // Catch:{ Exception -> 0x005a }
        L_0x005a:
            return r3
        L_0x005b:
            r6.moveToFirst()     // Catch:{ all -> 0x0072 }
            java.util.Map r0 = readColumnsMap(r6)     // Catch:{ all -> 0x0072 }
            java.lang.Object r7 = r7.parseRow(r0)     // Catch:{ all -> 0x0072 }
            r6.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0069:
            return r7
        L_0x006a:
            android.database.sqlite.SQLiteException r7 = new android.database.sqlite.SQLiteException     // Catch:{ all -> 0x0072 }
            r7.<init>(r1)     // Catch:{ all -> 0x0072 }
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x0072 }
            throw r7     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r7 = move-exception
            r6.close()     // Catch:{ Exception -> 0x0076 }
        L_0x0076:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseOpt(android.database.Cursor, org.jetbrains.anko.db.MapRowParser):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.util.List<T> parseList(android.database.Cursor r4, org.jetbrains.anko.db.MapRowParser<? extends T> r5) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "parser"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0047
            r0 = r4
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r0
            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x0040 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0040 }
            int r3 = r4.getCount()     // Catch:{ all -> 0x0040 }
            r2.<init>(r3)     // Catch:{ all -> 0x0040 }
            r4.moveToFirst()     // Catch:{ all -> 0x0040 }
        L_0x0025:
            boolean r3 = r4.isAfterLast()     // Catch:{ all -> 0x0040 }
            if (r3 != 0) goto L_0x003a
            java.util.Map r3 = readColumnsMap(r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r3 = r5.parseRow(r3)     // Catch:{ all -> 0x0040 }
            r2.add(r3)     // Catch:{ all -> 0x0040 }
            r4.moveToNext()     // Catch:{ all -> 0x0040 }
            goto L_0x0025
        L_0x003a:
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0040 }
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return r2
        L_0x0040:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            throw r5
        L_0x0047:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x006e }
            int r1 = r4.getCount()     // Catch:{ all -> 0x006e }
            r0.<init>(r1)     // Catch:{ all -> 0x006e }
            r4.moveToFirst()     // Catch:{ all -> 0x006e }
        L_0x0053:
            boolean r1 = r4.isAfterLast()     // Catch:{ all -> 0x006e }
            if (r1 != 0) goto L_0x0068
            java.util.Map r1 = readColumnsMap(r4)     // Catch:{ all -> 0x006e }
            java.lang.Object r1 = r5.parseRow(r1)     // Catch:{ all -> 0x006e }
            r0.add(r1)     // Catch:{ all -> 0x006e }
            r4.moveToNext()     // Catch:{ all -> 0x006e }
            goto L_0x0053
        L_0x0068:
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x006e }
            r4.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            return r0
        L_0x006e:
            r5 = move-exception
            r4.close()     // Catch:{ Exception -> 0x0072 }
        L_0x0072:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.SqlParsersKt.parseList(android.database.Cursor, org.jetbrains.anko.db.MapRowParser):java.util.List");
    }
}
