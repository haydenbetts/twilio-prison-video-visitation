package com.urbanairship;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;

public class UrbanAirshipResolver {
    private final Context context;

    public UrbanAirshipResolver(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            return getResolver().query(uri, strArr, str, strArr2, str2);
        } catch (Exception e) {
            Logger.error(e, "Failed to query the UrbanAirshipProvider.", new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public int delete(Uri uri, String str, String[] strArr) {
        try {
            return getResolver().delete(uri, str, strArr);
        } catch (Exception e) {
            Logger.error(e, "Failed to perform a delete in UrbanAirshipProvider.", new Object[0]);
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            return getResolver().update(uri, contentValues, str, strArr);
        } catch (Exception e) {
            Logger.error(e, "Failed to perform an update in UrbanAirshipProvider.", new Object[0]);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            return getResolver().insert(uri, contentValues);
        } catch (Exception e) {
            Logger.error(e, "Failed to insert in UrbanAirshipProvider.", new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        try {
            return getResolver().bulkInsert(uri, contentValuesArr);
        } catch (Exception e) {
            Logger.error(e, "Failed to bulk insert in UrbanAirshipProvider.", new Object[0]);
            return 0;
        }
    }

    public void registerContentObserver(Uri uri, boolean z, ContentObserver contentObserver) {
        try {
            getResolver().registerContentObserver(uri, z, contentObserver);
        } catch (IllegalArgumentException unused) {
            Logger.warn("Unable to register content observer for uri: %s", uri);
        }
    }

    public void unregisterContentObserver(ContentObserver contentObserver) {
        getResolver().unregisterContentObserver(contentObserver);
    }

    public void notifyChange(Uri uri, ContentObserver contentObserver) {
        try {
            getResolver().notifyChange(uri, contentObserver);
        } catch (IllegalArgumentException unused) {
            Logger.warn("Unable to notify observers of change for uri: %s", uri);
        }
    }

    private ContentResolver getResolver() {
        return this.context.getContentResolver();
    }
}
