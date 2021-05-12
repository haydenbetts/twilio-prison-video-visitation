package com.braintreepayments.api.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnalyticsDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "braintree-analytics.db";
    private static final int DATABASE_VERSION = 1;
    static final String EVENT = "event";
    static final String ID = "_id";
    static final String META_JSON = "meta_json";
    private static final String TABLE_NAME = "analytics";
    static final String TIMESTAMP = "timestamp";
    protected final Set<AsyncTask> mTaskSet = new HashSet();

    public static AnalyticsDatabase getInstance(Context context) {
        return new AnalyticsDatabase(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public AnalyticsDatabase(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, DATABASE_NAME, cursorFactory, 1);
    }

    public AnalyticsDatabase(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler) {
        super(context, DATABASE_NAME, cursorFactory, 1, databaseErrorHandler);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table analytics(_id integer primary key autoincrement, event text not null, timestamp long not null, meta_json text not null);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists analytics");
        onCreate(sQLiteDatabase);
    }

    public void addEvent(AnalyticsEvent analyticsEvent) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("event", analyticsEvent.event);
        contentValues.put("timestamp", Long.valueOf(analyticsEvent.timestamp));
        contentValues.put(META_JSON, analyticsEvent.metadata.toString());
        queueTask(new DatabaseTask(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x001e  */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
            /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    r0 = 0
                    com.braintreepayments.api.internal.AnalyticsDatabase r1 = com.braintreepayments.api.internal.AnalyticsDatabase.this     // Catch:{ SQLiteException -> 0x0022, all -> 0x0018 }
                    android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0022, all -> 0x0018 }
                    java.lang.String r2 = "analytics"
                    android.content.ContentValues r3 = r0     // Catch:{ SQLiteException -> 0x0016, all -> 0x0014 }
                    r1.insert(r2, r0, r3)     // Catch:{ SQLiteException -> 0x0016, all -> 0x0014 }
                    if (r1 == 0) goto L_0x0028
                    r1.close()
                    goto L_0x0028
                L_0x0014:
                    r0 = move-exception
                    goto L_0x001c
                L_0x0016:
                    r0 = r1
                    goto L_0x0023
                L_0x0018:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L_0x001c:
                    if (r1 == 0) goto L_0x0021
                    r1.close()
                L_0x0021:
                    throw r0
                L_0x0022:
                L_0x0023:
                    if (r0 == 0) goto L_0x0028
                    r0.close()
                L_0x0028:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.internal.AnalyticsDatabase.AnonymousClass1.run():void");
            }
        }));
    }

    public void removeEvents(List<AnalyticsEvent> list) {
        final StringBuilder sb = new StringBuilder("_id");
        sb.append(" in (");
        final String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = Integer.toString(list.get(i).id);
            sb.append("?");
            if (i < list.size() - 1) {
                sb.append(",");
            } else {
                sb.append(")");
            }
        }
        queueTask(new DatabaseTask(new Runnable() {
            public void run() {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    sQLiteDatabase = AnalyticsDatabase.this.getWritableDatabase();
                    sQLiteDatabase.delete("analytics", sb.toString(), strArr);
                    if (sQLiteDatabase == null) {
                        return;
                    }
                } catch (SQLiteException unused) {
                    if (sQLiteDatabase == null) {
                        return;
                    }
                } catch (Throwable th) {
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
                sQLiteDatabase.close();
            }
        }));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x008e, code lost:
        if (r3 != null) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0099, code lost:
        if (r3 == null) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x009b, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009e, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.util.List<com.braintreepayments.api.internal.AnalyticsEvent>> getPendingRequests() {
        /*
            r14 = this;
            java.lang.String r0 = "meta_json"
            java.lang.String r1 = ","
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r3 = r14.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            java.lang.String r4 = "group_concat(_id)"
            java.lang.String r5 = "group_concat(event)"
            java.lang.String r6 = "group_concat(timestamp)"
            java.lang.String[] r7 = new java.lang.String[]{r4, r5, r6, r0}     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            r5 = 0
            java.lang.String r6 = "analytics"
            r8 = 0
            r9 = 0
            java.lang.String r10 = "meta_json"
            r11 = 0
            java.lang.String r12 = "_id asc"
            r13 = 0
            r4 = r3
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
        L_0x0028:
            boolean r5 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            if (r5 == 0) goto L_0x008b
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            r5.<init>()     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            r6 = 0
            java.lang.String r7 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            java.lang.String[] r7 = r7.split(r1)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            r8 = 1
            java.lang.String r8 = r4.getString(r8)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            java.lang.String[] r8 = r8.split(r1)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            r9 = 2
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            java.lang.String[] r9 = r9.split(r1)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
        L_0x004e:
            int r10 = r8.length     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            if (r6 >= r10) goto L_0x0087
            com.braintreepayments.api.internal.AnalyticsEvent r10 = new com.braintreepayments.api.internal.AnalyticsEvent     // Catch:{ JSONException -> 0x0084 }
            r10.<init>()     // Catch:{ JSONException -> 0x0084 }
            r11 = r7[r6]     // Catch:{ JSONException -> 0x0084 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ JSONException -> 0x0084 }
            int r11 = r11.intValue()     // Catch:{ JSONException -> 0x0084 }
            r10.id = r11     // Catch:{ JSONException -> 0x0084 }
            r11 = r8[r6]     // Catch:{ JSONException -> 0x0084 }
            r10.event = r11     // Catch:{ JSONException -> 0x0084 }
            r11 = r9[r6]     // Catch:{ JSONException -> 0x0084 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ JSONException -> 0x0084 }
            long r11 = r11.longValue()     // Catch:{ JSONException -> 0x0084 }
            r10.timestamp = r11     // Catch:{ JSONException -> 0x0084 }
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0084 }
            int r12 = r4.getColumnIndex(r0)     // Catch:{ JSONException -> 0x0084 }
            java.lang.String r12 = r4.getString(r12)     // Catch:{ JSONException -> 0x0084 }
            r11.<init>(r12)     // Catch:{ JSONException -> 0x0084 }
            r10.metadata = r11     // Catch:{ JSONException -> 0x0084 }
            r5.add(r10)     // Catch:{ JSONException -> 0x0084 }
        L_0x0084:
            int r6 = r6 + 1
            goto L_0x004e
        L_0x0087:
            r2.add(r5)     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            goto L_0x0028
        L_0x008b:
            r4.close()     // Catch:{ SQLiteException -> 0x0098, all -> 0x0091 }
            if (r3 == 0) goto L_0x009e
            goto L_0x009b
        L_0x0091:
            r0 = move-exception
            if (r3 == 0) goto L_0x0097
            r3.close()
        L_0x0097:
            throw r0
        L_0x0098:
            if (r3 == 0) goto L_0x009e
        L_0x009b:
            r3.close()
        L_0x009e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.internal.AnalyticsDatabase.getPendingRequests():java.util.List");
    }

    private void queueTask(final DatabaseTask databaseTask) {
        databaseTask.setFinishedCallback(new BraintreeResponseListener<Void>() {
            public void onResponse(Void voidR) {
                synchronized (AnalyticsDatabase.this.mTaskSet) {
                    AnalyticsDatabase.this.mTaskSet.remove(databaseTask);
                }
            }
        });
        synchronized (this.mTaskSet) {
            this.mTaskSet.add(databaseTask);
        }
        databaseTask.execute(new Void[0]);
    }

    private static class DatabaseTask extends AsyncTask<Void, Void, Void> {
        private Runnable mDatabaseAction;
        private BraintreeResponseListener<Void> mFinishedCallback;

        public DatabaseTask(Runnable runnable) {
            this.mDatabaseAction = runnable;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            this.mDatabaseAction.run();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            BraintreeResponseListener<Void> braintreeResponseListener = this.mFinishedCallback;
            if (braintreeResponseListener != null) {
                braintreeResponseListener.onResponse(null);
            }
        }

        /* access modifiers changed from: private */
        public void setFinishedCallback(BraintreeResponseListener<Void> braintreeResponseListener) {
            this.mFinishedCallback = braintreeResponseListener;
        }
    }
}
