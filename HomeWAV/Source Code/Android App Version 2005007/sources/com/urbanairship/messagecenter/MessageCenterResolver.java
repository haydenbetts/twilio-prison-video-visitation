package com.urbanairship.messagecenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.urbanairship.Logger;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.UrbanAirshipProvider;
import com.urbanairship.UrbanAirshipResolver;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MessageCenterResolver extends UrbanAirshipResolver {
    private static final String FALSE_VALUE = "0";
    private static final String TRUE_VALUE = "1";
    private static final String WHERE_CLAUSE_CHANGED = "unread <> unread_orig";
    private static final String WHERE_CLAUSE_MESSAGE_ID = "message_id = ?";
    private static final String WHERE_CLAUSE_READ = "unread = ?";
    private final Uri uri;

    MessageCenterResolver(Context context) {
        super(context);
        this.uri = UrbanAirshipProvider.getRichPushContentUri(context);
    }

    /* access modifiers changed from: package-private */
    public List<Message> getMessages() {
        ArrayList arrayList = new ArrayList();
        Cursor query = query(this.uri, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return arrayList;
        }
        while (query.moveToNext()) {
            try {
                String string = query.getString(query.getColumnIndex(MessageCenterDataManager.MessageTable.COLUMN_NAME_RAW_MESSAGE_OBJECT));
                boolean z = true;
                boolean z2 = query.getInt(query.getColumnIndex(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD)) == 1;
                if (query.getInt(query.getColumnIndex(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED)) != 1) {
                    z = false;
                }
                Message create = Message.create(JsonValue.parseString(string), z2, z);
                if (create != null) {
                    arrayList.add(create);
                }
            } catch (JsonException e) {
                Logger.error(e, "RichPushResolver - Failed to parse message from the database.", new Object[0]);
            }
        }
        query.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getMessageIds() {
        return getMessageIdsFromCursor(query(this.uri, (String[]) null, (String) null, (String[]) null, (String) null));
    }

    /* access modifiers changed from: package-private */
    public Set<String> getReadUpdatedMessageIds() {
        return getMessageIdsFromCursor(query(this.uri, (String[]) null, "unread = ? AND unread <> unread_orig", new String[]{FALSE_VALUE}, (String) null));
    }

    /* access modifiers changed from: package-private */
    public Set<String> getDeletedMessageIds() {
        return getMessageIdsFromCursor(query(this.uri, (String[]) null, "deleted = ?", new String[]{"1"}, (String) null));
    }

    /* access modifiers changed from: package-private */
    public int markMessagesRead(Set<String> set) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD, false);
        return updateMessages(set, contentValues);
    }

    /* access modifiers changed from: package-private */
    public int markMessagesUnread(Set<String> set) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD, true);
        return updateMessages(set, contentValues);
    }

    /* access modifiers changed from: package-private */
    public int markMessagesDeleted(Set<String> set) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED, true);
        return updateMessages(set, contentValues);
    }

    /* access modifiers changed from: package-private */
    public int markMessagesReadOrigin(Set<String> set) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD_ORIG, false);
        return updateMessages(set, contentValues);
    }

    /* access modifiers changed from: package-private */
    public int deleteMessages(Set<String> set) {
        return delete(this.uri, "message_id IN ( " + UAStringUtil.repeat("?", set.size(), ", ") + " )", (String[]) set.toArray(new String[0]));
    }

    /* access modifiers changed from: package-private */
    public int insertMessages(List<JsonValue> list) {
        ArrayList arrayList = new ArrayList();
        for (JsonValue parseMessageContentValues : list) {
            ContentValues parseMessageContentValues2 = parseMessageContentValues(parseMessageContentValues);
            if (parseMessageContentValues2 != null) {
                parseMessageContentValues2.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD, parseMessageContentValues2.getAsBoolean(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD_ORIG));
                arrayList.add(parseMessageContentValues2);
            }
        }
        if (arrayList.isEmpty()) {
            return -1;
        }
        return bulkInsert(this.uri, (ContentValues[]) arrayList.toArray(new ContentValues[0]));
    }

    /* access modifiers changed from: package-private */
    public int updateMessage(String str, JsonValue jsonValue) {
        ContentValues parseMessageContentValues = parseMessageContentValues(jsonValue);
        if (parseMessageContentValues == null) {
            return -1;
        }
        return update(Uri.withAppendedPath(this.uri, str), parseMessageContentValues, WHERE_CLAUSE_MESSAGE_ID, new String[]{str});
    }

    private int updateMessages(Set<String> set, ContentValues contentValues) {
        Uri uri2 = this.uri;
        return update(uri2, contentValues, "message_id IN ( " + UAStringUtil.repeat("?", set.size(), ", ") + " )", (String[]) set.toArray(new String[0]));
    }

    private Set<String> getMessageIdsFromCursor(Cursor cursor) {
        if (cursor == null) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet(cursor.getCount());
        int i = -1;
        while (cursor.moveToNext()) {
            if (i == -1) {
                i = cursor.getColumnIndex("message_id");
            }
            hashSet.add(cursor.getString(i));
        }
        cursor.close();
        return hashSet;
    }

    private ContentValues parseMessageContentValues(JsonValue jsonValue) {
        if (jsonValue == null || !jsonValue.isJsonMap()) {
            Logger.error("RichPushResolver - Unexpected message: %s", jsonValue);
            return null;
        }
        JsonMap optMap = jsonValue.optMap();
        if (UAStringUtil.isEmpty(optMap.opt("message_id").getString())) {
            Logger.error("RichPushResolver - Message is missing an ID: %s", jsonValue);
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, optMap.opt("message_sent").getString());
        contentValues.put("message_id", optMap.opt("message_id").getString());
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_MESSAGE_URL, optMap.opt(MessageCenterDataManager.MessageTable.COLUMN_NAME_MESSAGE_URL).getString());
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_MESSAGE_BODY_URL, optMap.opt(MessageCenterDataManager.MessageTable.COLUMN_NAME_MESSAGE_BODY_URL).getString());
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_MESSAGE_READ_URL, optMap.opt(MessageCenterDataManager.MessageTable.COLUMN_NAME_MESSAGE_READ_URL).getString());
        contentValues.put("title", optMap.opt("title").getString());
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD_ORIG, Boolean.valueOf(optMap.opt(MessageCenterDataManager.MessageTable.COLUMN_NAME_UNREAD).getBoolean(true)));
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_EXTRA, optMap.opt(MessageCenterDataManager.MessageTable.COLUMN_NAME_EXTRA).toString());
        contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_RAW_MESSAGE_OBJECT, optMap.toString());
        if (optMap.containsKey("message_expiry")) {
            contentValues.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_EXPIRATION_TIMESTAMP, optMap.opt("message_expiry").getString());
        }
        return contentValues;
    }
}
