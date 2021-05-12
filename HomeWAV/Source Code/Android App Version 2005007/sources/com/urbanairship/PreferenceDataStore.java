package com.urbanairship;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class PreferenceDataStore {
    private static final String[] OBSOLETE_KEYS = {"com.urbanairship.TAG_GROUP_HISTORIAN_RECORDS"};
    private static final String WHERE_CLAUSE_KEY = "_id = ?";
    /* access modifiers changed from: private */
    public final Context context;
    Executor executor;
    private final List<PreferenceChangeListener> listeners;
    private final Map<String, Preference> preferences;
    /* access modifiers changed from: private */
    public final UrbanAirshipResolver resolver;

    public interface PreferenceChangeListener {
        void onPreferenceChange(String str);
    }

    public PreferenceDataStore(Context context2) {
        this(context2, new UrbanAirshipResolver(context2));
    }

    PreferenceDataStore(Context context2, UrbanAirshipResolver urbanAirshipResolver) {
        this.executor = AirshipExecutors.newSerialExecutor();
        this.preferences = new HashMap();
        this.listeners = new ArrayList();
        this.context = context2;
        this.resolver = urbanAirshipResolver;
    }

    public void addListener(PreferenceChangeListener preferenceChangeListener) {
        synchronized (this.listeners) {
            this.listeners.add(preferenceChangeListener);
        }
    }

    public void removeListener(PreferenceChangeListener preferenceChangeListener) {
        synchronized (this.listeners) {
            this.listeners.remove(preferenceChangeListener);
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        loadPreferences();
    }

    private void loadPreferences() {
        Cursor cursor = null;
        try {
            ArrayList arrayList = new ArrayList();
            Cursor query = this.resolver.query(UrbanAirshipProvider.getPreferencesContentUri(this.context), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query == null) {
                Logger.error("Failed to load preferences. Retrying with fallback loading.", new Object[0]);
                fallbackLoad();
                return;
            }
            int columnIndex = query.getColumnIndex(MessageCenterDataManager.MessageTable.COLUMN_NAME_KEY);
            int columnIndex2 = query.getColumnIndex(CommonProperties.VALUE);
            while (query.moveToNext()) {
                arrayList.add(new Preference(query.getString(columnIndex), query.getString(columnIndex2)));
            }
            query.close();
            finishLoad(arrayList);
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Logger.error(e, "Failed to load preferences. Retrying with fallback loading.", new Object[0]);
            fallbackLoad();
        }
    }

    private void fallbackLoad() {
        List<String> queryKeys = queryKeys();
        if (queryKeys.isEmpty()) {
            Logger.error("Unable to load keys, deleting preference store.", new Object[0]);
            this.resolver.delete(UrbanAirshipProvider.getPreferencesContentUri(this.context), (String) null, (String[]) null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String next : queryKeys) {
            String queryValue = queryValue(next);
            if (queryValue == null) {
                Logger.error("Unable to fetch preference value. Deleting: %s", next);
                this.resolver.delete(UrbanAirshipProvider.getPreferencesContentUri(this.context), "_id == ?", new String[]{next});
            } else {
                arrayList.add(new Preference(next, queryValue));
            }
        }
        finishLoad(arrayList);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        if (r1 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        r1.close();
        r7 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r1 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        return r7;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String queryValue(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "value"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 1
            r7 = 0
            r8 = 0
            com.urbanairship.UrbanAirshipResolver r1 = r9.resolver     // Catch:{ Exception -> 0x0035, all -> 0x0033 }
            android.content.Context r2 = r9.context     // Catch:{ Exception -> 0x0035, all -> 0x0033 }
            android.net.Uri r2 = com.urbanairship.UrbanAirshipProvider.getPreferencesContentUri(r2)     // Catch:{ Exception -> 0x0035, all -> 0x0033 }
            java.lang.String r4 = "_id == ?"
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0035, all -> 0x0033 }
            r5[r8] = r10     // Catch:{ Exception -> 0x0035, all -> 0x0033 }
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0035, all -> 0x0033 }
            if (r1 == 0) goto L_0x002d
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x002b }
            if (r2 == 0) goto L_0x002d
            java.lang.String r10 = r1.getString(r8)     // Catch:{ Exception -> 0x002b }
            r7 = r10
            goto L_0x002d
        L_0x002b:
            r2 = move-exception
            goto L_0x0037
        L_0x002d:
            if (r1 == 0) goto L_0x0043
        L_0x002f:
            r1.close()
            goto L_0x0043
        L_0x0033:
            r10 = move-exception
            goto L_0x0046
        L_0x0035:
            r2 = move-exception
            r1 = r7
        L_0x0037:
            java.lang.String r3 = "Failed to query preference: %s"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0044 }
            r0[r8] = r10     // Catch:{ all -> 0x0044 }
            com.urbanairship.Logger.error(r2, r3, r0)     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0043
            goto L_0x002f
        L_0x0043:
            return r7
        L_0x0044:
            r10 = move-exception
            r7 = r1
        L_0x0046:
            if (r7 == 0) goto L_0x004b
            r7.close()
        L_0x004b:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.PreferenceDataStore.queryValue(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> queryKeys() {
        /*
            r8 = this;
            java.lang.String r0 = "_id"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            r7 = 0
            com.urbanairship.UrbanAirshipResolver r1 = r8.resolver     // Catch:{ Exception -> 0x0045 }
            android.content.Context r2 = r8.context     // Catch:{ Exception -> 0x0045 }
            android.net.Uri r2 = com.urbanairship.UrbanAirshipProvider.getPreferencesContentUri(r2)     // Catch:{ Exception -> 0x0045 }
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0045 }
            if (r1 != 0) goto L_0x0024
            com.urbanairship.UrbanAirshipResolver r2 = r8.resolver     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            android.content.Context r3 = r8.context     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            android.net.Uri r3 = com.urbanairship.UrbanAirshipProvider.getPreferencesContentUri(r3)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.delete(r3, r7, r7)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
        L_0x0024:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.<init>()     // Catch:{ Exception -> 0x0040, all -> 0x003d }
        L_0x0029:
            boolean r3 = r1.moveToNext()     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            if (r3 == 0) goto L_0x0037
            java.lang.String r3 = r1.getString(r0)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.add(r3)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            goto L_0x0029
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            return r2
        L_0x003d:
            r0 = move-exception
            r7 = r1
            goto L_0x0057
        L_0x0040:
            r2 = move-exception
            r7 = r1
            goto L_0x0046
        L_0x0043:
            r0 = move-exception
            goto L_0x0057
        L_0x0045:
            r2 = move-exception
        L_0x0046:
            java.lang.String r1 = "Failed to query keys."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0043 }
            com.urbanairship.Logger.error(r2, r1, r0)     // Catch:{ all -> 0x0043 }
            if (r7 == 0) goto L_0x0052
            r7.close()
        L_0x0052:
            java.util.List r0 = java.util.Collections.emptyList()
            return r0
        L_0x0057:
            if (r7 == 0) goto L_0x005c
            r7.close()
        L_0x005c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.PreferenceDataStore.queryKeys():java.util.List");
    }

    private void finishLoad(List<Preference> list) {
        for (Preference next : list) {
            this.preferences.put(next.key, next);
            next.registerObserver();
        }
        for (String remove : OBSOLETE_KEYS) {
            remove(remove);
        }
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        for (Preference unregisterObserver : this.preferences.values()) {
            unregisterObserver.unregisterObserver();
        }
    }

    public boolean isSet(String str) {
        return getPreference(str).get() != null;
    }

    public boolean getBoolean(String str, boolean z) {
        String str2 = getPreference(str).get();
        return str2 == null ? z : Boolean.parseBoolean(str2);
    }

    public String getString(String str, String str2) {
        String str3 = getPreference(str).get();
        return str3 == null ? str2 : str3;
    }

    public long getLong(String str, long j) {
        String str2 = getPreference(str).get();
        if (str2 == null) {
            return j;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public int getInt(String str, int i) {
        String str2 = getPreference(str).get();
        if (str2 == null) {
            return i;
        }
        try {
            return Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public JsonValue getJsonValue(String str) {
        try {
            return JsonValue.parseString(getPreference(str).get());
        } catch (JsonException e) {
            Logger.debug(e, "Unable to parse preference value: %s", str);
            return JsonValue.NULL;
        }
    }

    public void remove(String str) {
        Preference preference;
        synchronized (this.preferences) {
            preference = this.preferences.containsKey(str) ? this.preferences.get(str) : null;
        }
        if (preference != null) {
            preference.put((String) null);
        }
    }

    public void put(String str, String str2) {
        if (str2 == null) {
            remove(str);
        } else {
            getPreference(str).put(str2);
        }
    }

    public void put(String str, long j) {
        getPreference(str).put(String.valueOf(j));
    }

    public void put(String str, int i) {
        getPreference(str).put(String.valueOf(i));
    }

    public void put(String str, boolean z) {
        getPreference(str).put(String.valueOf(z));
    }

    public void put(String str, JsonValue jsonValue) {
        if (jsonValue == null) {
            remove(str);
        } else {
            getPreference(str).put(jsonValue.toString());
        }
    }

    public void put(String str, JsonSerializable jsonSerializable) {
        if (jsonSerializable == null) {
            remove(str);
        } else {
            put(str, jsonSerializable.toJsonValue());
        }
    }

    public boolean putSync(String str, String str2) {
        return getPreference(str).putSync(str2);
    }

    /* access modifiers changed from: private */
    public void onPreferenceChanged(String str) {
        synchronized (this.listeners) {
            for (PreferenceChangeListener onPreferenceChange : this.listeners) {
                onPreferenceChange.onPreferenceChange(str);
            }
        }
    }

    private Preference getPreference(String str) {
        Preference preference;
        synchronized (this.preferences) {
            preference = this.preferences.get(str);
            if (preference == null) {
                preference = new Preference(str, (String) null);
                preference.registerObserver();
                this.preferences.put(str, preference);
            }
        }
        return preference;
    }

    private class Preference {
        /* access modifiers changed from: private */
        public final String key;
        private final ContentObserver observer = new ContentObserver((Handler) null) {
            public boolean deliverSelfNotifications() {
                return false;
            }

            public void onChange(boolean z) {
                Logger.verbose("PreferenceDataStore - Preference updated: %s", Preference.this.key);
                PreferenceDataStore.this.executor.execute(new Runnable() {
                    public void run() {
                        Preference.this.syncValue();
                    }
                });
            }
        };
        private final Uri uri;
        private String value;

        Preference(String str, String str2) {
            this.key = str;
            this.value = str2;
            this.uri = Uri.withAppendedPath(UrbanAirshipProvider.getPreferencesContentUri(PreferenceDataStore.this.context), str);
        }

        /* access modifiers changed from: package-private */
        public String get() {
            String str;
            synchronized (this) {
                str = this.value;
            }
            return str;
        }

        /* access modifiers changed from: package-private */
        public void put(final String str) {
            if (setValue(str)) {
                PreferenceDataStore.this.executor.execute(new Runnable() {
                    public void run() {
                        boolean unused = Preference.this.writeValue(str);
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        public boolean putSync(String str) {
            synchronized (this) {
                if (!writeValue(str)) {
                    return false;
                }
                setValue(str);
                return true;
            }
        }

        private boolean setValue(String str) {
            synchronized (this) {
                if (UAStringUtil.equals(str, this.value)) {
                    return false;
                }
                this.value = str;
                PreferenceDataStore.this.onPreferenceChanged(this.key);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public boolean writeValue(String str) {
            synchronized (this) {
                if (str == null) {
                    Logger.verbose("PreferenceDataStore - Removing preference: %s", this.key);
                    if (PreferenceDataStore.this.resolver.delete(UrbanAirshipProvider.getPreferencesContentUri(PreferenceDataStore.this.context), PreferenceDataStore.WHERE_CLAUSE_KEY, new String[]{this.key}) != 1) {
                        return false;
                    }
                    PreferenceDataStore.this.resolver.notifyChange(this.uri, this.observer);
                    return true;
                }
                Logger.verbose("PreferenceDataStore - Saving preference: %s value: %s", this.key, str);
                ContentValues contentValues = new ContentValues();
                contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_KEY, this.key);
                contentValues.put(CommonProperties.VALUE, str);
                if (PreferenceDataStore.this.resolver.insert(UrbanAirshipProvider.getPreferencesContentUri(PreferenceDataStore.this.context), contentValues) == null) {
                    return false;
                }
                PreferenceDataStore.this.resolver.notifyChange(this.uri, this.observer);
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
            if (r2.moveToFirst() == false) goto L_0x0035;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
            r0 = r2.getString(0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
            setValue(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            com.urbanairship.Logger.error(r0, "Unable to sync preference %s from database", r9.key);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
            com.urbanairship.Logger.debug("PreferenceDataStore - Unable to get preference %s from database. Falling back to cached value.", r9.key);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x005c, code lost:
            r0 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0029, code lost:
            if (r2 == null) goto L_0x0046;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void syncValue() {
            /*
                r9 = this;
                r0 = 0
                monitor-enter(r9)     // Catch:{ all -> 0x0060 }
                com.urbanairship.PreferenceDataStore r1 = com.urbanairship.PreferenceDataStore.this     // Catch:{ all -> 0x0057 }
                com.urbanairship.UrbanAirshipResolver r2 = r1.resolver     // Catch:{ all -> 0x0057 }
                com.urbanairship.PreferenceDataStore r1 = com.urbanairship.PreferenceDataStore.this     // Catch:{ all -> 0x0057 }
                android.content.Context r1 = r1.context     // Catch:{ all -> 0x0057 }
                android.net.Uri r3 = com.urbanairship.UrbanAirshipProvider.getPreferencesContentUri(r1)     // Catch:{ all -> 0x0057 }
                java.lang.String r1 = "value"
                java.lang.String[] r4 = new java.lang.String[]{r1}     // Catch:{ all -> 0x0057 }
                java.lang.String r5 = "_id = ?"
                r1 = 1
                java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ all -> 0x0057 }
                java.lang.String r7 = r9.key     // Catch:{ all -> 0x0057 }
                r8 = 0
                r6[r8] = r7     // Catch:{ all -> 0x0057 }
                r7 = 0
                android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0057 }
                monitor-exit(r9)     // Catch:{ all -> 0x005e }
                if (r2 == 0) goto L_0x0046
                boolean r3 = r2.moveToFirst()     // Catch:{ Exception -> 0x0039 }
                if (r3 == 0) goto L_0x0035
                java.lang.String r0 = r2.getString(r8)     // Catch:{ Exception -> 0x0039 }
            L_0x0035:
                r9.setValue(r0)     // Catch:{ Exception -> 0x0039 }
                goto L_0x0051
            L_0x0039:
                r0 = move-exception
                java.lang.String r3 = "Unable to sync preference %s from database"
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005c }
                java.lang.String r4 = r9.key     // Catch:{ all -> 0x005c }
                r1[r8] = r4     // Catch:{ all -> 0x005c }
                com.urbanairship.Logger.error(r0, r3, r1)     // Catch:{ all -> 0x005c }
                goto L_0x0051
            L_0x0046:
                java.lang.String r0 = "PreferenceDataStore - Unable to get preference %s from database. Falling back to cached value."
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005c }
                java.lang.String r3 = r9.key     // Catch:{ all -> 0x005c }
                r1[r8] = r3     // Catch:{ all -> 0x005c }
                com.urbanairship.Logger.debug(r0, r1)     // Catch:{ all -> 0x005c }
            L_0x0051:
                if (r2 == 0) goto L_0x0056
                r2.close()
            L_0x0056:
                return
            L_0x0057:
                r1 = move-exception
                r2 = r0
                r0 = r1
            L_0x005a:
                monitor-exit(r9)     // Catch:{ all -> 0x005e }
                throw r0     // Catch:{ all -> 0x005c }
            L_0x005c:
                r0 = move-exception
                goto L_0x0063
            L_0x005e:
                r0 = move-exception
                goto L_0x005a
            L_0x0060:
                r1 = move-exception
                r2 = r0
                r0 = r1
            L_0x0063:
                if (r2 == 0) goto L_0x0068
                r2.close()
            L_0x0068:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.PreferenceDataStore.Preference.syncValue():void");
        }

        /* access modifiers changed from: package-private */
        public void registerObserver() {
            PreferenceDataStore.this.resolver.registerContentObserver(this.uri, true, this.observer);
        }

        /* access modifiers changed from: package-private */
        public void unregisterObserver() {
            PreferenceDataStore.this.resolver.unregisterContentObserver(this.observer);
        }
    }
}
