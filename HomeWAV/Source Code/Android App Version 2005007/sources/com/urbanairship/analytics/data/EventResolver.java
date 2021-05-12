package com.urbanairship.analytics.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.urbanairship.Logger;
import com.urbanairship.UrbanAirshipProvider;
import com.urbanairship.UrbanAirshipResolver;
import com.urbanairship.analytics.Event;
import com.urbanairship.analytics.data.EventsStorage;
import com.urbanairship.util.UAStringUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EventResolver extends UrbanAirshipResolver {
    public static final String ASCENDING_SORT_ORDER = "_id ASC";
    private final Uri uri;

    public EventResolver(Context context) {
        super(context);
        this.uri = UrbanAirshipProvider.getEventsContentUri(context);
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getEvents(int i) {
        HashMap hashMap = new HashMap(i);
        Cursor query = query(this.uri.buildUpon().appendQueryParameter(UrbanAirshipProvider.QUERY_PARAMETER_LIMIT, String.valueOf(i)).build(), new String[]{EventsStorage.Events.COLUMN_NAME_EVENT_ID, "data"}, (String) null, (String[]) null, ASCENDING_SORT_ORDER);
        if (query == null) {
            return hashMap;
        }
        query.moveToFirst();
        while (!query.isAfterLast()) {
            hashMap.put(query.getString(0), query.getString(1));
            query.moveToNext();
        }
        query.close();
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void deleteAllEvents() {
        delete(this.uri, (String) null, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean deleteEvents(Set<String> set) {
        if (set == null || set.size() == 0) {
            Logger.verbose("EventsStorage - Nothing to delete. Returning.", new Object[0]);
            return false;
        }
        int size = set.size();
        String repeat = repeat("?", size, ", ");
        Uri uri2 = this.uri;
        if (delete(uri2, "event_id IN ( " + repeat + " )", (String[]) set.toArray(new String[size])) > 0) {
            return true;
        }
        return false;
    }

    private static String repeat(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < i) {
            sb.append(str);
            i2++;
            if (i2 != i) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    private String getOldestSessionId() {
        Cursor query = query(this.uri.buildUpon().appendQueryParameter(UrbanAirshipProvider.QUERY_PARAMETER_LIMIT, "1").build(), new String[]{EventsStorage.Events.COLUMN_NAME_SESSION_ID}, (String) null, (String[]) null, ASCENDING_SORT_ORDER);
        String str = null;
        if (query == null) {
            Logger.error("EventsStorage - Unable to query database.", new Object[0]);
            return null;
        }
        if (query.moveToFirst()) {
            str = query.getString(0);
        }
        query.close();
        return str;
    }

    /* access modifiers changed from: package-private */
    public int getEventCount() {
        Cursor query = query(this.uri, new String[]{"COUNT(*) as _cnt"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            Logger.error("EventsStorage - Unable to query events database.", new Object[0]);
            return -1;
        }
        Integer valueOf = query.moveToFirst() ? Integer.valueOf(query.getInt(0)) : null;
        query.close();
        if (valueOf == null) {
            return -1;
        }
        return valueOf.intValue();
    }

    /* access modifiers changed from: package-private */
    public int getDatabaseSize() {
        Cursor query = query(this.uri, new String[]{"SUM(event_size) as _size"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            Logger.error("EventsStorage - Unable to query events database.", new Object[0]);
            return -1;
        }
        Integer valueOf = query.moveToFirst() ? Integer.valueOf(query.getInt(0)) : null;
        query.close();
        if (valueOf == null) {
            return -1;
        }
        return valueOf.intValue();
    }

    /* access modifiers changed from: package-private */
    public void insertEvent(Event event, String str) {
        String createEventPayload = event.createEventPayload(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", event.getType());
        contentValues.put(EventsStorage.Events.COLUMN_NAME_EVENT_ID, event.getEventId());
        contentValues.put("data", createEventPayload);
        contentValues.put(EventsStorage.Events.COLUMN_NAME_TIME, event.getTime());
        contentValues.put(EventsStorage.Events.COLUMN_NAME_SESSION_ID, str);
        contentValues.put(EventsStorage.Events.COLUMN_NAME_EVENT_SIZE, Integer.valueOf(createEventPayload.length()));
        insert(this.uri, contentValues);
    }

    /* access modifiers changed from: package-private */
    public void trimDatabase(int i) {
        while (getDatabaseSize() > i) {
            String oldestSessionId = getOldestSessionId();
            if (!UAStringUtil.isEmpty(oldestSessionId)) {
                Logger.debug("Event database size exceeded. Deleting oldest session: %s", oldestSessionId);
                int delete = delete(this.uri, "session_id = ?", new String[]{oldestSessionId});
                if (delete > 0) {
                    Logger.debug("EventsStorage - Deleted %s rows with session ID %s", Integer.valueOf(delete), oldestSessionId);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
