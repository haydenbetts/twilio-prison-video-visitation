package com.urbanairship;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.analytics.data.EventsStorage;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.util.DataManager;

public final class UrbanAirshipProvider extends ContentProvider {
    static final String EVENTS_CONTENT_ITEM_TYPE = "vnd.urbanairship.cursor.item/events";
    static final String EVENTS_CONTENT_TYPE = "vnd.urbanairship.cursor.dir/events";
    private static final int EVENTS_URI_TYPE = 4;
    private static final int EVENT_URI_TYPE = 5;
    static final String MULTIPLE_SUFFIX = "vnd.urbanairship.cursor.dir/";
    static final String PREFERENCES_CONTENT_ITEM_TYPE = "vnd.urbanairship.cursor.item/preference";
    static final String PREFERENCES_CONTENT_TYPE = "vnd.urbanairship.cursor.dir/preference";
    private static final int PREFERENCES_URI_TYPE = 2;
    private static final int PREFERENCE_URI_TYPE = 3;
    public static final String QUERY_PARAMETER_LIMIT = "limit";
    private static final int RICHPUSH_MESSAGES_URI_TYPE = 0;
    private static final int RICHPUSH_MESSAGE_URI_TYPE = 1;
    static final String RICH_PUSH_CONTENT_ITEM_TYPE = "vnd.urbanairship.cursor.item/richpush";
    static final String RICH_PUSH_CONTENT_TYPE = "vnd.urbanairship.cursor.dir/richpush";
    static final String SINGLE_SUFFIX = "vnd.urbanairship.cursor.item/";
    private static String authorityString;
    private DatabaseModel eventsDataModel;
    private final UriMatcher matcher = new UriMatcher(-1);
    private DatabaseModel preferencesDataModel;
    private DatabaseModel richPushDataModel;

    public static Uri getRichPushContentUri(Context context) {
        return Uri.parse("content://" + getAuthorityString(context) + "/richpush");
    }

    public static Uri getPreferencesContentUri(Context context) {
        return Uri.parse("content://" + getAuthorityString(context) + "/preferences");
    }

    public static Uri getEventsContentUri(Context context) {
        return Uri.parse("content://" + getAuthorityString(context) + "/events");
    }

    public static String getAuthorityString(Context context) {
        if (authorityString == null) {
            String packageName = context.getPackageName();
            authorityString = packageName + ".urbanairship.provider";
        }
        return authorityString;
    }

    public boolean onCreate() {
        if (getContext() == null) {
            return false;
        }
        this.matcher.addURI(getAuthorityString(getContext()), MessageCenterDataManager.MessageTable.TABLE_NAME, 0);
        this.matcher.addURI(getAuthorityString(getContext()), "richpush/*", 1);
        this.matcher.addURI(getAuthorityString(getContext()), "preferences", 2);
        this.matcher.addURI(getAuthorityString(getContext()), "preferences/*", 3);
        this.matcher.addURI(getAuthorityString(getContext()), EventsStorage.Events.TABLE_NAME, 5);
        this.matcher.addURI(getAuthorityString(getContext()), "events/*", 5);
        Autopilot.automaticTakeOff((Application) getContext().getApplicationContext(), true);
        UAirship.isMainProcess = true;
        GlobalActivityMonitor.shared(getContext().getApplicationContext());
        return true;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        DatabaseModel databaseModel = getDatabaseModel(uri);
        if (databaseModel == null || getContext() == null) {
            return -1;
        }
        return databaseModel.dataManager.delete(databaseModel.table, str, strArr);
    }

    public String getType(Uri uri) {
        int match = this.matcher.match(uri);
        if (match == 0) {
            return RICH_PUSH_CONTENT_TYPE;
        }
        if (match == 1) {
            return RICH_PUSH_CONTENT_ITEM_TYPE;
        }
        if (match == 2) {
            return PREFERENCES_CONTENT_TYPE;
        }
        if (match == 3) {
            return PREFERENCES_CONTENT_ITEM_TYPE;
        }
        if (match == 4) {
            return EVENTS_CONTENT_ITEM_TYPE;
        }
        if (match == 5) {
            return EVENTS_CONTENT_TYPE;
        }
        throw new IllegalArgumentException("Invalid Uri: " + uri);
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        DatabaseModel databaseModel = getDatabaseModel(uri);
        if (databaseModel == null || getContext() == null) {
            return -1;
        }
        return databaseModel.dataManager.bulkInsert(databaseModel.table, contentValuesArr).size();
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        DatabaseModel databaseModel = getDatabaseModel(uri);
        if (databaseModel == null || getContext() == null || contentValues == null || databaseModel.dataManager.insert(databaseModel.table, contentValues) == -1) {
            return null;
        }
        return Uri.withAppendedPath(uri, contentValues.getAsString(databaseModel.notificationColumnId));
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursor;
        DatabaseModel databaseModel = getDatabaseModel(uri);
        if (databaseModel == null || getContext() == null) {
            return null;
        }
        String queryParameter = uri.getQueryParameter(QUERY_PARAMETER_LIMIT);
        if (queryParameter != null) {
            DataManager dataManager = databaseModel.dataManager;
            String str3 = databaseModel.table;
            cursor = dataManager.query(str3, strArr, str, strArr2, str2, "0, " + queryParameter);
        } else {
            cursor = databaseModel.dataManager.query(databaseModel.table, strArr, str, strArr2, str2);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        DatabaseModel databaseModel = getDatabaseModel(uri);
        if (databaseModel == null || getContext() == null) {
            return -1;
        }
        return databaseModel.dataManager.update(databaseModel.table, contentValues, str, strArr);
    }

    public void shutdown() {
        DatabaseModel databaseModel = this.richPushDataModel;
        if (databaseModel != null) {
            databaseModel.dataManager.close();
            this.richPushDataModel = null;
        }
        DatabaseModel databaseModel2 = this.preferencesDataModel;
        if (databaseModel2 != null) {
            databaseModel2.dataManager.close();
            this.preferencesDataModel = null;
        }
        DatabaseModel databaseModel3 = this.eventsDataModel;
        if (databaseModel3 != null) {
            databaseModel3.dataManager.close();
            this.eventsDataModel = null;
        }
    }

    private DatabaseModel getDatabaseModel(Uri uri) {
        UAirship uAirship;
        if (getContext() == null || ((!UAirship.isFlying() && !UAirship.isTakingOff()) || (uAirship = UAirship.sharedAirship) == null)) {
            return null;
        }
        String str = uAirship.getAirshipConfigOptions().appKey;
        int match = this.matcher.match(uri);
        if (match == 0 || match == 1) {
            if (this.richPushDataModel == null) {
                this.richPushDataModel = DatabaseModel.createRichPushModel(getContext(), str);
            }
            return this.richPushDataModel;
        } else if (match == 2 || match == 3) {
            if (this.preferencesDataModel == null) {
                this.preferencesDataModel = DatabaseModel.createPreferencesModel(getContext(), str);
            }
            return this.preferencesDataModel;
        } else if (match == 4 || match == 5) {
            if (this.eventsDataModel == null) {
                this.eventsDataModel = DatabaseModel.createEventsDataModel(getContext(), str);
            }
            return this.eventsDataModel;
        } else {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }
    }

    private static class DatabaseModel {
        final DataManager dataManager;
        /* access modifiers changed from: private */
        public final String notificationColumnId;
        final String table;

        private DatabaseModel(DataManager dataManager2, String str, String str2) {
            this.dataManager = dataManager2;
            this.table = str;
            this.notificationColumnId = str2;
        }

        static DatabaseModel createRichPushModel(Context context, String str) {
            return new DatabaseModel(new MessageCenterDataManager(context, str), MessageCenterDataManager.MessageTable.TABLE_NAME, "message_id");
        }

        static DatabaseModel createPreferencesModel(Context context, String str) {
            return new DatabaseModel(new PreferencesDataManager(context, str), "preferences", MessageCenterDataManager.MessageTable.COLUMN_NAME_KEY);
        }

        static DatabaseModel createEventsDataModel(Context context, String str) {
            return new DatabaseModel(new EventsStorage(context, str), EventsStorage.Events.TABLE_NAME, MessageCenterDataManager.MessageTable.COLUMN_NAME_KEY);
        }
    }
}
