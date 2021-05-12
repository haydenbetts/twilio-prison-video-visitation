package io.sqlc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SQLiteAndroidDatabase {
    private static final Pattern DELETE_TABLE_NAME = Pattern.compile("^\\s*DELETE\\s+FROM\\s+(\\S+)", 2);
    private static final Pattern FIRST_WORD = Pattern.compile("^[\\s;]*([^\\s;]+)", 2);
    private static final Pattern UPDATE_TABLE_NAME = Pattern.compile("^\\s*UPDATE\\s+(\\S+)", 2);
    private static final Pattern WHERE_CLAUSE = Pattern.compile("\\s+WHERE\\s+(.+)$", 2);
    private static final boolean isPostHoneycomb = (Build.VERSION.SDK_INT >= 11);
    File dbFile;
    boolean isTransactionActive = false;
    SQLiteDatabase mydb;

    enum QueryType {
        update,
        insert,
        delete,
        select,
        begin,
        commit,
        rollback,
        other
    }

    SQLiteAndroidDatabase() {
    }

    /* access modifiers changed from: package-private */
    public void open(File file) throws Exception {
        if (isPostHoneycomb) {
            this.dbFile = file;
            this.mydb = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
            return;
        }
        Log.v("SQLiteAndroidDatabase.open", "INTERNAL PLUGIN ERROR: deprecated android.os.Build.VERSION not supported: " + Build.VERSION.SDK_INT);
        throw new RuntimeException("INTERNAL PLUGIN ERROR: deprecated android.os.Build.VERSION not supported: " + Build.VERSION.SDK_INT);
    }

    /* access modifiers changed from: package-private */
    public void closeDatabaseNow() {
        SQLiteDatabase sQLiteDatabase = this.mydb;
        if (sQLiteDatabase != null) {
            if (this.isTransactionActive) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                    Log.v("closeDatabaseNow", "INTERNAL PLUGIN ERROR IGNORED: Not able to end active transaction before closing database: " + e.getMessage());
                    e.printStackTrace();
                }
                this.isTransactionActive = false;
            }
            this.mydb.close();
            this.mydb = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void bugWorkaround() throws Exception {
        closeDatabaseNow();
        open(this.dbFile);
    }

    /* access modifiers changed from: package-private */
    public void executeSqlBatch(String[] strArr, JSONArray[] jSONArrayArr, CallbackContext callbackContext) {
        if (this.mydb == null) {
            callbackContext.error("INTERNAL PLUGIN ERROR: database not open");
            return;
        }
        int length = strArr.length;
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            executeSqlBatchStatement(strArr[i], jSONArrayArr[i], jSONArray);
        }
        callbackContext.success(jSONArray);
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x01c9 A[SYNTHETIC, Splitter:B:101:0x01c9] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0205 A[SYNTHETIC, Splitter:B:116:0x0205] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0283 A[SYNTHETIC, Splitter:B:135:0x0283] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0298 A[Catch:{ JSONException -> 0x02bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e8 A[Catch:{ SQLiteConstraintException -> 0x00f6, SQLiteException -> 0x00f3, Exception -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0150 A[SYNTHETIC, Splitter:B:75:0x0150] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x018a A[SYNTHETIC, Splitter:B:88:0x018a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void executeSqlBatchStatement(java.lang.String r18, org.json.JSONArray r19, org.json.JSONArray r20) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            android.database.sqlite.SQLiteDatabase r0 = r1.mydb
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            r5 = 0
            java.lang.String r0 = "unknown"
            r6 = 6
            r7 = 0
            io.sqlc.SQLiteAndroidDatabase$QueryType r8 = getQueryType(r18)     // Catch:{ Exception -> 0x0262 }
            io.sqlc.SQLiteAndroidDatabase$QueryType r9 = io.sqlc.SQLiteAndroidDatabase.QueryType.update     // Catch:{ Exception -> 0x0262 }
            r10 = 1
            if (r8 == r9) goto L_0x0024
            io.sqlc.SQLiteAndroidDatabase$QueryType r9 = io.sqlc.SQLiteAndroidDatabase.QueryType.delete     // Catch:{ Exception -> 0x0262 }
            if (r8 != r9) goto L_0x0020
            goto L_0x0024
        L_0x0020:
            r9 = 1
            r13 = 0
            goto L_0x00bf
        L_0x0024:
            android.database.sqlite.SQLiteDatabase r9 = r1.mydb     // Catch:{ Exception -> 0x0262 }
            android.database.sqlite.SQLiteStatement r9 = r9.compileStatement(r2)     // Catch:{ Exception -> 0x0262 }
            if (r3 == 0) goto L_0x002f
            r1.bindArgsToStatement(r9, r3)     // Catch:{ Exception -> 0x0262 }
        L_0x002f:
            r11 = -1
            int r12 = r9.executeUpdateDelete()     // Catch:{ SQLiteConstraintException -> 0x0077, SQLiteException -> 0x0056, Exception -> 0x0036 }
        L_0x0034:
            r13 = 0
            goto L_0x00a9
        L_0x0036:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r0 = "SQLiteAndroidDatabase.executeSqlBatchStatement"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0262 }
            r3.<init>()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r6 = "INTERNAL PLUGIN ERROR: could not do myStatement.executeUpdateDelete(): "
            r3.append(r6)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r6 = r2.getMessage()     // Catch:{ Exception -> 0x0262 }
            r3.append(r6)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0262 }
            android.util.Log.v(r0, r3)     // Catch:{ Exception -> 0x0262 }
            throw r2     // Catch:{ Exception -> 0x0262 }
        L_0x0056:
            r0 = move-exception
            r12 = r0
            r12.printStackTrace()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r0 = r12.getMessage()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r12 = "executeSqlBatch"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0262 }
            r13.<init>()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r14 = "SQLiteStatement.executeUpdateDelete(): Error="
            r13.append(r14)     // Catch:{ Exception -> 0x0262 }
            r13.append(r0)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x0262 }
            android.util.Log.v(r12, r13)     // Catch:{ Exception -> 0x0262 }
            r12 = -1
            goto L_0x0034
        L_0x0077:
            r0 = move-exception
            r12 = r0
            r12.printStackTrace()     // Catch:{ Exception -> 0x0262 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0262 }
            r0.<init>()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r13 = "constraint failure: "
            r0.append(r13)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r12 = r12.getMessage()     // Catch:{ Exception -> 0x0262 }
            r0.append(r12)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0262 }
            java.lang.String r12 = "executeSqlBatch"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025f }
            r13.<init>()     // Catch:{ Exception -> 0x025f }
            java.lang.String r14 = "SQLiteStatement.executeUpdateDelete(): Error="
            r13.append(r14)     // Catch:{ Exception -> 0x025f }
            r13.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x025f }
            android.util.Log.v(r12, r13)     // Catch:{ Exception -> 0x025f }
            r12 = -1
            r13 = 6
        L_0x00a9:
            r9.close()     // Catch:{ Exception -> 0x025d }
            if (r12 == r11) goto L_0x00be
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Exception -> 0x025d }
            r9.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r5 = "rowsAffected"
            r9.put(r5, r12)     // Catch:{ Exception -> 0x00ba }
            r5 = r9
            goto L_0x00be
        L_0x00ba:
            r0 = move-exception
            r5 = r9
            goto L_0x0264
        L_0x00be:
            r9 = 0
        L_0x00bf:
            io.sqlc.SQLiteAndroidDatabase$QueryType r11 = io.sqlc.SQLiteAndroidDatabase.QueryType.insert     // Catch:{ Exception -> 0x025d }
            if (r8 != r11) goto L_0x014c
            if (r3 == 0) goto L_0x014c
            android.database.sqlite.SQLiteDatabase r9 = r1.mydb     // Catch:{ Exception -> 0x025d }
            android.database.sqlite.SQLiteStatement r9 = r9.compileStatement(r2)     // Catch:{ Exception -> 0x025d }
            r1.bindArgsToStatement(r9, r3)     // Catch:{ Exception -> 0x025d }
            long r11 = r9.executeInsert()     // Catch:{ SQLiteConstraintException -> 0x0118, SQLiteException -> 0x00f9 }
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ SQLiteConstraintException -> 0x0118, SQLiteException -> 0x00f9 }
            r14.<init>()     // Catch:{ SQLiteConstraintException -> 0x0118, SQLiteException -> 0x00f9 }
            r15 = -1
            int r5 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x00e8
            java.lang.String r5 = "insertId"
            r14.put(r5, r11)     // Catch:{ SQLiteConstraintException -> 0x00f6, SQLiteException -> 0x00f3, Exception -> 0x00ef }
            java.lang.String r5 = "rowsAffected"
            r14.put(r5, r10)     // Catch:{ SQLiteConstraintException -> 0x00f6, SQLiteException -> 0x00f3, Exception -> 0x00ef }
            goto L_0x00ed
        L_0x00e8:
            java.lang.String r5 = "rowsAffected"
            r14.put(r5, r7)     // Catch:{ SQLiteConstraintException -> 0x00f6, SQLiteException -> 0x00f3, Exception -> 0x00ef }
        L_0x00ed:
            r5 = r14
            goto L_0x0148
        L_0x00ef:
            r0 = move-exception
            r5 = r14
            goto L_0x0264
        L_0x00f3:
            r0 = move-exception
            r5 = r14
            goto L_0x00fa
        L_0x00f6:
            r0 = move-exception
            r5 = r14
            goto L_0x0119
        L_0x00f9:
            r0 = move-exception
        L_0x00fa:
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            java.lang.String r11 = "executeSqlBatch"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r12.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r14 = "SQLiteDatabase.executeInsert(): Error="
            r12.append(r14)     // Catch:{ Exception -> 0x025d }
            r12.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x025d }
            android.util.Log.v(r11, r12)     // Catch:{ Exception -> 0x025d }
            goto L_0x0148
        L_0x0118:
            r0 = move-exception
        L_0x0119:
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r11.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r12 = "constraint failure: "
            r11.append(r12)     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            r11.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r11.toString()     // Catch:{ Exception -> 0x025d }
            java.lang.String r11 = "executeSqlBatch"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025f }
            r12.<init>()     // Catch:{ Exception -> 0x025f }
            java.lang.String r13 = "SQLiteDatabase.executeInsert(): Error="
            r12.append(r13)     // Catch:{ Exception -> 0x025f }
            r12.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x025f }
            android.util.Log.v(r11, r12)     // Catch:{ Exception -> 0x025f }
            r13 = 6
        L_0x0148:
            r9.close()     // Catch:{ Exception -> 0x025d }
            r9 = 0
        L_0x014c:
            io.sqlc.SQLiteAndroidDatabase$QueryType r11 = io.sqlc.SQLiteAndroidDatabase.QueryType.begin     // Catch:{ Exception -> 0x025d }
            if (r8 != r11) goto L_0x0186
            android.database.sqlite.SQLiteDatabase r9 = r1.mydb     // Catch:{ SQLiteException -> 0x0167 }
            r9.beginTransaction()     // Catch:{ SQLiteException -> 0x0167 }
            r1.isTransactionActive = r10     // Catch:{ SQLiteException -> 0x0167 }
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x0167 }
            r9.<init>()     // Catch:{ SQLiteException -> 0x0167 }
            java.lang.String r5 = "rowsAffected"
            r9.put(r5, r7)     // Catch:{ SQLiteException -> 0x0164 }
            r5 = r9
            r9 = 0
            goto L_0x0186
        L_0x0164:
            r0 = move-exception
            r5 = r9
            goto L_0x0168
        L_0x0167:
            r0 = move-exception
        L_0x0168:
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            java.lang.String r9 = "executeSqlBatch"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r10.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r11 = "SQLiteDatabase.beginTransaction(): Error="
            r10.append(r11)     // Catch:{ Exception -> 0x025d }
            r10.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x025d }
            android.util.Log.v(r9, r10)     // Catch:{ Exception -> 0x025d }
            r9 = 0
        L_0x0186:
            io.sqlc.SQLiteAndroidDatabase$QueryType r10 = io.sqlc.SQLiteAndroidDatabase.QueryType.commit     // Catch:{ Exception -> 0x025d }
            if (r8 != r10) goto L_0x01c5
            android.database.sqlite.SQLiteDatabase r9 = r1.mydb     // Catch:{ SQLiteException -> 0x01a6 }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01a6 }
            android.database.sqlite.SQLiteDatabase r9 = r1.mydb     // Catch:{ SQLiteException -> 0x01a6 }
            r9.endTransaction()     // Catch:{ SQLiteException -> 0x01a6 }
            r1.isTransactionActive = r7     // Catch:{ SQLiteException -> 0x01a6 }
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x01a6 }
            r9.<init>()     // Catch:{ SQLiteException -> 0x01a6 }
            java.lang.String r5 = "rowsAffected"
            r9.put(r5, r7)     // Catch:{ SQLiteException -> 0x01a3 }
            r5 = r9
            r9 = 0
            goto L_0x01c5
        L_0x01a3:
            r0 = move-exception
            r5 = r9
            goto L_0x01a7
        L_0x01a6:
            r0 = move-exception
        L_0x01a7:
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            java.lang.String r9 = "executeSqlBatch"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r10.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r11 = "SQLiteDatabase.setTransactionSuccessful/endTransaction(): Error="
            r10.append(r11)     // Catch:{ Exception -> 0x025d }
            r10.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x025d }
            android.util.Log.v(r9, r10)     // Catch:{ Exception -> 0x025d }
            r9 = 0
        L_0x01c5:
            io.sqlc.SQLiteAndroidDatabase$QueryType r10 = io.sqlc.SQLiteAndroidDatabase.QueryType.rollback     // Catch:{ Exception -> 0x025d }
            if (r8 != r10) goto L_0x0202
            android.database.sqlite.SQLiteDatabase r8 = r1.mydb     // Catch:{ SQLiteException -> 0x01e3 }
            r8.endTransaction()     // Catch:{ SQLiteException -> 0x01e3 }
            r1.isTransactionActive = r7     // Catch:{ SQLiteException -> 0x01e3 }
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x01e3 }
            r8.<init>()     // Catch:{ SQLiteException -> 0x01e3 }
            java.lang.String r5 = "rowsAffected"
            r8.put(r5, r7)     // Catch:{ SQLiteException -> 0x01e0, Exception -> 0x01dc }
            r5 = r8
            goto L_0x0203
        L_0x01dc:
            r0 = move-exception
            r5 = r8
            goto L_0x0264
        L_0x01e0:
            r0 = move-exception
            r5 = r8
            goto L_0x01e4
        L_0x01e3:
            r0 = move-exception
        L_0x01e4:
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            java.lang.String r8 = "executeSqlBatch"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r9.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r10 = "SQLiteDatabase.endTransaction(): Error="
            r9.append(r10)     // Catch:{ Exception -> 0x025d }
            r9.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x025d }
            android.util.Log.v(r8, r9)     // Catch:{ Exception -> 0x025d }
            goto L_0x0203
        L_0x0202:
            r7 = r9
        L_0x0203:
            if (r7 == 0) goto L_0x0281
            android.database.sqlite.SQLiteDatabase r7 = r1.mydb     // Catch:{ SQLiteConstraintException -> 0x022c, SQLiteException -> 0x020d }
            org.json.JSONObject r2 = r1.executeSqlStatementQuery(r7, r2, r3)     // Catch:{ SQLiteConstraintException -> 0x022c, SQLiteException -> 0x020d }
            r5 = r2
            goto L_0x0281
        L_0x020d:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            java.lang.String r2 = "executeSqlBatch"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r3.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r6 = "Raw query error="
            r3.append(r6)     // Catch:{ Exception -> 0x025d }
            r3.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x025d }
            android.util.Log.v(r2, r3)     // Catch:{ Exception -> 0x025d }
            goto L_0x0281
        L_0x022c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x025d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025d }
            r2.<init>()     // Catch:{ Exception -> 0x025d }
            java.lang.String r3 = "constraint failure: "
            r2.append(r3)     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x025d }
            r2.append(r0)     // Catch:{ Exception -> 0x025d }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x025d }
            java.lang.String r2 = "executeSqlBatch"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x025f }
            r3.<init>()     // Catch:{ Exception -> 0x025f }
            java.lang.String r7 = "Raw query error="
            r3.append(r7)     // Catch:{ Exception -> 0x025f }
            r3.append(r0)     // Catch:{ Exception -> 0x025f }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x025f }
            android.util.Log.v(r2, r3)     // Catch:{ Exception -> 0x025f }
            r13 = 6
            goto L_0x0281
        L_0x025d:
            r0 = move-exception
            goto L_0x0264
        L_0x025f:
            r0 = move-exception
            r13 = 6
            goto L_0x0264
        L_0x0262:
            r0 = move-exception
            r13 = 0
        L_0x0264:
            r0.printStackTrace()
            java.lang.String r0 = r0.getMessage()
            java.lang.String r2 = "executeSqlBatch"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "SQLiteAndroidDatabase.executeSql[Batch](): Error="
            r3.append(r6)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.v(r2, r3)
        L_0x0281:
            if (r5 == 0) goto L_0x0298
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x02bc }
            r0.<init>()     // Catch:{ JSONException -> 0x02bc }
            java.lang.String r2 = "type"
            java.lang.String r3 = "success"
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x02bc }
            java.lang.String r2 = "result"
            r0.put(r2, r5)     // Catch:{ JSONException -> 0x02bc }
            r4.put(r0)     // Catch:{ JSONException -> 0x02bc }
            goto L_0x02da
        L_0x0298:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x02bc }
            r2.<init>()     // Catch:{ JSONException -> 0x02bc }
            java.lang.String r3 = "type"
            java.lang.String r5 = "error"
            r2.put(r3, r5)     // Catch:{ JSONException -> 0x02bc }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x02bc }
            r3.<init>()     // Catch:{ JSONException -> 0x02bc }
            java.lang.String r5 = "message"
            r3.put(r5, r0)     // Catch:{ JSONException -> 0x02bc }
            java.lang.String r0 = "code"
            r3.put(r0, r13)     // Catch:{ JSONException -> 0x02bc }
            java.lang.String r0 = "result"
            r2.put(r0, r3)     // Catch:{ JSONException -> 0x02bc }
            r4.put(r2)     // Catch:{ JSONException -> 0x02bc }
            goto L_0x02da
        L_0x02bc:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r2 = "executeSqlBatch"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "SQLiteAndroidDatabase.executeSql[Batch](): Error="
            r3.append(r4)
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.v(r2, r0)
        L_0x02da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sqlc.SQLiteAndroidDatabase.executeSqlBatchStatement(java.lang.String, org.json.JSONArray, org.json.JSONArray):void");
    }

    private final int countRowsAffectedCompat(QueryType queryType, String str, JSONArray jSONArray, SQLiteDatabase sQLiteDatabase) throws JSONException {
        Matcher matcher = WHERE_CLAUSE.matcher(str);
        String str2 = "";
        for (int i = 0; matcher.find(i); i = matcher.start(1)) {
            str2 = " WHERE " + matcher.group(1);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < str2.length(); i3++) {
            if (str2.charAt(i3) == '?') {
                i2++;
            }
        }
        JSONArray jSONArray2 = null;
        if (jSONArray != null) {
            jSONArray2 = new JSONArray();
            int length = jSONArray.length() - i2;
            for (int i4 = length; i4 < jSONArray.length(); i4++) {
                jSONArray2.put(i4 - length, jSONArray.get(i4));
            }
        }
        if (queryType == QueryType.update) {
            Matcher matcher2 = UPDATE_TABLE_NAME.matcher(str);
            if (matcher2.find()) {
                try {
                    SQLiteStatement compileStatement = sQLiteDatabase.compileStatement("SELECT count(*) FROM " + matcher2.group(1) + str2);
                    if (jSONArray2 != null) {
                        bindArgsToStatement(compileStatement, jSONArray2);
                    }
                    return (int) compileStatement.simpleQueryForLong();
                } catch (Exception e) {
                    Log.e(SQLiteAndroidDatabase.class.getSimpleName(), "uncaught", e);
                }
            }
        } else {
            Matcher matcher3 = DELETE_TABLE_NAME.matcher(str);
            if (matcher3.find()) {
                try {
                    SQLiteStatement compileStatement2 = sQLiteDatabase.compileStatement("SELECT count(*) FROM " + matcher3.group(1) + str2);
                    bindArgsToStatement(compileStatement2, jSONArray2);
                    return (int) compileStatement2.simpleQueryForLong();
                } catch (Exception e2) {
                    Log.e(SQLiteAndroidDatabase.class.getSimpleName(), "uncaught", e2);
                }
            }
        }
        return 0;
    }

    private void bindArgsToStatement(SQLiteStatement sQLiteStatement, JSONArray jSONArray) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            if ((jSONArray.get(i) instanceof Float) || (jSONArray.get(i) instanceof Double)) {
                sQLiteStatement.bindDouble(i + 1, jSONArray.getDouble(i));
            } else if (jSONArray.get(i) instanceof Number) {
                sQLiteStatement.bindLong(i + 1, jSONArray.getLong(i));
            } else if (jSONArray.isNull(i)) {
                sQLiteStatement.bindNull(i + 1);
            } else {
                sQLiteStatement.bindString(i + 1, jSONArray.getString(i));
            }
        }
    }

    private JSONObject executeSqlStatementQuery(SQLiteDatabase sQLiteDatabase, String str, JSONArray jSONArray) throws Exception {
        JSONObject jSONObject = new JSONObject();
        try {
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                if (jSONArray.isNull(i)) {
                    strArr[i] = "";
                } else {
                    strArr[i] = jSONArray.getString(i);
                }
            }
            Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr);
            if (rawQuery != null && rawQuery.moveToFirst()) {
                JSONArray jSONArray2 = new JSONArray();
                int columnCount = rawQuery.getColumnCount();
                do {
                    JSONObject jSONObject2 = new JSONObject();
                    int i2 = 0;
                    while (i2 < columnCount) {
                        try {
                            String columnName = rawQuery.getColumnName(i2);
                            if (isPostHoneycomb) {
                                bindPostHoneycomb(jSONObject2, columnName, rawQuery, i2);
                                i2++;
                            } else {
                                Log.v("SQLiteAndroidDatabase.executeSqlStatementQuery", "INTERNAL PLUGIN ERROR: deprecated android.os.Build.VERSION not supported: " + Build.VERSION.SDK_INT);
                                throw new RuntimeException("INTERNAL PLUGIN ERROR: deprecated android.os.Build.VERSION not supported: " + Build.VERSION.SDK_INT);
                            }
                        } catch (Exception e) {
                            Log.v("SQLiteAndroidDatabase.executeSqlStatementQuery", "INTERNAL PLUGIN ERROR: could not bindPostHoneycomb: " + e.getMessage());
                            throw e;
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                    jSONArray2.put(jSONObject2);
                } while (rawQuery.moveToNext());
                try {
                    jSONObject.put("rows", jSONArray2);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return jSONObject;
        } catch (Exception e4) {
            e4.printStackTrace();
            String message = e4.getMessage();
            Log.v("executeSqlBatch", "SQLiteAndroidDatabase.executeSql[Batch](): Error=" + message);
            throw e4;
        }
    }

    private void bindPostHoneycomb(JSONObject jSONObject, String str, Cursor cursor, int i) throws JSONException {
        switch (cursor.getType(i)) {
            case 0:
                jSONObject.put(str, JSONObject.NULL);
                return;
            case 1:
                jSONObject.put(str, cursor.getLong(i));
                return;
            case 2:
                jSONObject.put(str, cursor.getDouble(i));
                return;
            default:
                jSONObject.put(str, cursor.getString(i));
                return;
        }
    }

    static QueryType getQueryType(String str) {
        Matcher matcher = FIRST_WORD.matcher(str);
        if (matcher.find()) {
            try {
                String group = matcher.group(1);
                if (group.length() != 0) {
                    return QueryType.valueOf(group.toLowerCase(Locale.ENGLISH));
                }
                throw new RuntimeException("query not found");
            } catch (IllegalArgumentException unused) {
                return QueryType.other;
            }
        } else {
            throw new RuntimeException("query not found");
        }
    }
}
