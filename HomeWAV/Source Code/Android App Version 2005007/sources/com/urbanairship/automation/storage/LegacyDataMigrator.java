package com.urbanairship.automation.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.automation.Audience;
import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.storage.LegacyDataManager;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LegacyDataMigrator {
    private static final String LEGACY_SCHEDULED_MESSAGES_KEY = "com.urbanairship.iam.data.SCHEDULED_MESSAGES";
    private final AirshipRuntimeConfig config;
    private final Context context;
    private final PreferenceDataStore dataStore;

    interface Migrator {
        void onMigrate(ScheduleEntity scheduleEntity, List<TriggerEntity> list);
    }

    public LegacyDataMigrator(Context context2, AirshipRuntimeConfig airshipRuntimeConfig, PreferenceDataStore preferenceDataStore) {
        this.context = context2.getApplicationContext();
        this.config = airshipRuntimeConfig;
        this.dataStore = preferenceDataStore;
    }

    public void migrateData(final AutomationDao automationDao) {
        LegacyDataManager legacyDataManager = new LegacyDataManager(this.context, this.config.getConfigOptions().appKey, "ua_automation.db");
        if (legacyDataManager.databaseExists(this.context)) {
            Logger.verbose("Migrating actions automation database.", new Object[0]);
            migrateDatabase(legacyDataManager, new Migrator() {
                public void onMigrate(ScheduleEntity scheduleEntity, List<TriggerEntity> list) {
                    scheduleEntity.scheduleType = Schedule.TYPE_ACTION;
                    Logger.verbose("Saving migrated action schedule: %s triggers: %s", scheduleEntity, list);
                    automationDao.insert(new FullSchedule(scheduleEntity, list));
                }
            });
        }
        LegacyDataManager legacyDataManager2 = new LegacyDataManager(this.context, this.config.getConfigOptions().appKey, "in-app");
        if (legacyDataManager2.databaseExists(this.context)) {
            Logger.verbose("Migrating in-app message database.", new Object[0]);
            migrateDatabase(legacyDataManager2, new MessageMigrator(automationDao, this.dataStore.getJsonValue(LEGACY_SCHEDULED_MESSAGES_KEY).optMap().keySet()));
            this.dataStore.remove(LEGACY_SCHEDULED_MESSAGES_KEY);
        }
    }

    private void migrateDatabase(LegacyDataManager legacyDataManager, Migrator migrator) {
        Cursor cursor = null;
        try {
            cursor = legacyDataManager.querySchedules();
            if (cursor != null) {
                migrateDataFromCursor(cursor, migrator);
            }
        } catch (Exception e) {
            Logger.error(e, "Error when migrating database.", new Object[0]);
        } catch (Throwable th) {
            closeCursor((Cursor) null);
            legacyDataManager.deleteAllSchedules();
            legacyDataManager.close();
            legacyDataManager.deleteDatabase(this.context);
            throw th;
        }
        closeCursor(cursor);
        legacyDataManager.deleteAllSchedules();
        legacyDataManager.close();
        legacyDataManager.deleteDatabase(this.context);
    }

    private void migrateDataFromCursor(Cursor cursor, Migrator migrator) {
        ScheduleEntity scheduleEntity;
        JsonException e;
        ArrayList arrayList = new ArrayList();
        cursor.moveToFirst();
        ScheduleEntity scheduleEntity2 = null;
        String str = null;
        while (!cursor.isAfterLast()) {
            String string = cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_SCHEDULE_ID));
            if (!UAStringUtil.equals(str, string)) {
                if (scheduleEntity2 != null) {
                    migrator.onMigrate(scheduleEntity2, arrayList);
                }
                arrayList.clear();
                scheduleEntity2 = null;
                str = string;
            }
            boolean z = false;
            if (scheduleEntity2 == null) {
                try {
                    scheduleEntity = new ScheduleEntity();
                    try {
                        scheduleEntity.scheduleId = cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_SCHEDULE_ID));
                        scheduleEntity.metadata = JsonValue.parseString(cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_METADATA))).optMap();
                        scheduleEntity.count = cursor.getInt(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_COUNT));
                        scheduleEntity.limit = cursor.getInt(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_LIMIT));
                        scheduleEntity.priority = cursor.getInt(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_PRIORITY));
                        scheduleEntity.group = cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_GROUP));
                        scheduleEntity.editGracePeriod = cursor.getLong(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_EDIT_GRACE_PERIOD));
                        scheduleEntity.scheduleEnd = cursor.getLong(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_END));
                        scheduleEntity.scheduleStart = cursor.getLong(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_START));
                        scheduleEntity.executionState = cursor.getInt(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_EXECUTION_STATE));
                        scheduleEntity.executionStateChangeDate = cursor.getLong(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_EXECUTION_STATE_CHANGE_DATE));
                        scheduleEntity.appState = cursor.getInt(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_APP_STATE));
                        scheduleEntity.regionId = cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_REGION_ID));
                        scheduleEntity.interval = cursor.getLong(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_INTERVAL));
                        scheduleEntity.seconds = cursor.getLong(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_SECONDS));
                        scheduleEntity.screens = parseScreens(JsonValue.parseString(cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_SCREEN))));
                        scheduleEntity.data = JsonValue.parseString(cursor.getString(cursor.getColumnIndex(LegacyDataManager.ScheduleTable.COLUMN_NAME_DATA)));
                        scheduleEntity2 = scheduleEntity;
                    } catch (JsonException e2) {
                        e = e2;
                        Logger.error(e, "Failed to parse schedule entry.", new Object[0]);
                        scheduleEntity2 = scheduleEntity;
                    }
                } catch (JsonException e3) {
                    scheduleEntity = scheduleEntity2;
                    e = e3;
                    Logger.error(e, "Failed to parse schedule entry.", new Object[0]);
                    scheduleEntity2 = scheduleEntity;
                }
            }
            if (cursor.getColumnIndex(LegacyDataManager.TriggerTable.COLUMN_NAME_TYPE) != -1) {
                TriggerEntity triggerEntity = new TriggerEntity();
                triggerEntity.parentScheduleId = scheduleEntity2.scheduleId;
                triggerEntity.triggerType = cursor.getInt(cursor.getColumnIndex(LegacyDataManager.TriggerTable.COLUMN_NAME_TYPE));
                triggerEntity.goal = cursor.getDouble(cursor.getColumnIndex(LegacyDataManager.TriggerTable.COLUMN_NAME_GOAL));
                triggerEntity.progress = cursor.getDouble(cursor.getColumnIndex(LegacyDataManager.TriggerTable.COLUMN_NAME_PROGRESS));
                triggerEntity.jsonPredicate = parseJsonPredicate(cursor.getString(cursor.getColumnIndex(LegacyDataManager.TriggerTable.COLUMN_NAME_PREDICATE)));
                if (cursor.getInt(cursor.getColumnIndex(LegacyDataManager.TriggerTable.COLUMN_NAME_IS_CANCELLATION)) == 1) {
                    z = true;
                }
                triggerEntity.isCancellation = z;
                arrayList.add(triggerEntity);
            }
            cursor.moveToNext();
        }
        if (scheduleEntity2 != null) {
            migrator.onMigrate(scheduleEntity2, arrayList);
        }
    }

    private List<String> parseScreens(JsonValue jsonValue) {
        ArrayList arrayList = new ArrayList();
        if (jsonValue.isJsonList()) {
            Iterator<JsonValue> it = jsonValue.optList().iterator();
            while (it.hasNext()) {
                JsonValue next = it.next();
                if (next.getString() != null) {
                    arrayList.add(next.getString());
                }
            }
        } else {
            String string = jsonValue.getString();
            if (string != null) {
                arrayList.add(string);
            }
        }
        return arrayList;
    }

    private JsonPredicate parseJsonPredicate(String str) {
        try {
            JsonValue parseString = JsonValue.parseString(str);
            if (!parseString.isNull()) {
                return JsonPredicate.parse(parseString);
            }
            return null;
        } catch (JsonException e) {
            Logger.error(e, "Failed to parse JSON predicate.", new Object[0]);
            return null;
        }
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (SQLException e) {
                Logger.error(e, "Failed to close cursor.", new Object[0]);
            }
        }
    }

    private static class MessageMigrator implements Migrator {
        private final AutomationDao dao;
        private final Set<String> knownRemoteScheduleIds;
        private final Set<String> messageIds;

        private MessageMigrator(AutomationDao automationDao, Set<String> set) {
            this.dao = automationDao;
            this.knownRemoteScheduleIds = set;
            this.messageIds = new HashSet();
        }

        private String getUniqueId(String str) {
            int i = 0;
            String str2 = str;
            while (this.messageIds.contains(str2)) {
                i++;
                str2 = str + "#" + i;
            }
            return str2;
        }

        public void onMigrate(ScheduleEntity scheduleEntity, List<TriggerEntity> list) {
            scheduleEntity.scheduleType = "in_app_message";
            if (this.knownRemoteScheduleIds.contains(scheduleEntity.scheduleId)) {
                scheduleEntity.data = JsonMap.newBuilder().putAll(scheduleEntity.data.optMap()).put("source", InAppMessage.SOURCE_REMOTE_DATA).build().toJsonValue();
            }
            String string = scheduleEntity.data.optMap().opt("message_id").getString(scheduleEntity.scheduleId);
            if (InAppMessage.SOURCE_APP_DEFINED.equals(scheduleEntity.data.optMap().opt("source").optString())) {
                scheduleEntity.metadata = JsonMap.newBuilder().putAll(scheduleEntity.metadata).put("com.urbanairship.original_schedule_id", scheduleEntity.scheduleId).put("com.urbanairship.original_message_id", string).build();
                string = getUniqueId(string);
            }
            scheduleEntity.scheduleId = string;
            for (TriggerEntity triggerEntity : list) {
                triggerEntity.parentScheduleId = string;
            }
            this.messageIds.add(string);
            JsonValue jsonValue = scheduleEntity.data.optMap().get("audience");
            if (jsonValue != null) {
                try {
                    scheduleEntity.audience = Audience.fromJson(jsonValue);
                } catch (JsonException e) {
                    Logger.error(e, "Unable to schedule due to audience JSON", new Object[0]);
                    return;
                }
            }
            Logger.verbose("Saving migrated message schedule: %s triggers: %s", scheduleEntity, list);
            this.dao.insert(new FullSchedule(scheduleEntity, list));
        }
    }
}
