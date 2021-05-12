package com.urbanairship.automation.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.urbanairship.Logger;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.util.DataManager;

public class LegacyDataManager extends DataManager {
    private static final int DATABASE_VERSION = 6;
    private static final String GET_SCHEDULES_QUERY = "SELECT * FROM action_schedules a LEFT OUTER JOIN triggers b ON a.s_id=b.t_s_id";

    public interface ScheduleTable {
        public static final String COLUMN_EDIT_GRACE_PERIOD = "s_edit_grace_period";
        public static final String COLUMN_NAME_APP_STATE = "d_app_state";
        public static final String COLUMN_NAME_COUNT = "s_count";
        public static final String COLUMN_NAME_DATA = "s_data";
        public static final String COLUMN_NAME_DELAY_FINISH_DATE = "s_pending_execution_date";
        public static final String COLUMN_NAME_END = "s_end";
        public static final String COLUMN_NAME_EXECUTION_STATE = "s_execution_state";
        public static final String COLUMN_NAME_EXECUTION_STATE_CHANGE_DATE = "s_execution_state_change_date";
        public static final String COLUMN_NAME_GROUP = "s_group";
        public static final String COLUMN_NAME_ID = "s_row_id";
        public static final String COLUMN_NAME_INTERVAL = "s_interval";
        public static final String COLUMN_NAME_LIMIT = "s_limit";
        public static final String COLUMN_NAME_METADATA = "s_metadata";
        public static final String COLUMN_NAME_PRIORITY = "s_priority";
        public static final String COLUMN_NAME_REGION_ID = "d_region_id";
        public static final String COLUMN_NAME_SCHEDULE_ID = "s_id";
        public static final String COLUMN_NAME_SCREEN = "d_screen";
        public static final String COLUMN_NAME_SECONDS = "d_seconds";
        public static final String COLUMN_NAME_START = "s_start";
        public static final String COLUMN_NAME_TRIGGER_CONTEXT = "s_trigger_context";
        public static final String TABLE_NAME = "action_schedules";
    }

    public interface TriggerTable {
        public static final String COLUMN_NAME_GOAL = "t_goal";
        public static final String COLUMN_NAME_ID = "t_row_id";
        public static final String COLUMN_NAME_IS_CANCELLATION = "t_cancellation";
        public static final String COLUMN_NAME_PREDICATE = "t_predicate";
        public static final String COLUMN_NAME_PROGRESS = "t_progress";
        public static final String COLUMN_NAME_SCHEDULE_ID = "t_s_id";
        public static final String COLUMN_NAME_TYPE = "t_type";
        public static final String TABLE_NAME = "triggers";
    }

    public LegacyDataManager(Context context, String str, String str2) {
        super(context, str, str2, 6);
    }

    /* access modifiers changed from: protected */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Logger.debug("LegacyDataManager - Creating automation database", new Object[0]);
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS action_schedules (s_row_id INTEGER PRIMARY KEY AUTOINCREMENT,s_id TEXT UNIQUE,s_metadata TEXT,s_data TEXT,s_start INTEGER,s_end INTEGER,s_edit_grace_period INTEGER,s_execution_state_change_date INTEGER,s_count INTEGER,s_limit INTEGER,s_priority INTEGER,s_group TEXT,s_execution_state INTEGER,s_pending_execution_date DOUBLE,d_app_state INTEGER,d_region_id TEXT,d_screen TEXT,d_seconds DOUBLE,s_interval INTEGER,s_trigger_context TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS triggers (t_row_id INTEGER PRIMARY KEY AUTOINCREMENT,t_type INTEGER,t_cancellation INTEGER,t_s_id TEXT,t_predicate TEXT,t_progress DOUBLE,t_goal DOUBLE,FOREIGN KEY(t_s_id) REFERENCES action_schedules(s_id) ON DELETE CASCADE);");
        Logger.debug("LegacyDataManager - Automation database created", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SQLiteDatabase sQLiteDatabase2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
        int i3 = i;
        String str17 = "DROP TABLE ";
        String str18 = " FROM ";
        String str19 = ", 0, ";
        String str20 = ScheduleTable.COLUMN_NAME_SECONDS;
        String str21 = ScheduleTable.COLUMN_NAME_SCREEN;
        String str22 = ", ";
        String str23 = ScheduleTable.COLUMN_NAME_REGION_ID;
        String str24 = ScheduleTable.COLUMN_NAME_APP_STATE;
        String str25 = ScheduleTable.COLUMN_NAME_DELAY_FINISH_DATE;
        String str26 = "s_is_pending_execution";
        if (i3 == 1) {
            sQLiteDatabase3.execSQL("BEGIN TRANSACTION;");
            sQLiteDatabase3.execSQL("ALTER TABLE action_schedules RENAME TO " + "temp_schedule_entry_table" + ";");
            sQLiteDatabase3.execSQL("ALTER TABLE triggers RENAME TO " + "temp_triggers_entry_table" + ";");
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE action_schedules (s_row_id INTEGER PRIMARY KEY AUTOINCREMENT,s_id TEXT UNIQUE,");
            sb.append("s_actions");
            sb.append(" TEXT,");
            sb.append(ScheduleTable.COLUMN_NAME_START);
            str3 = "ALTER TABLE triggers RENAME TO ";
            sb.append(" INTEGER,");
            sb.append(ScheduleTable.COLUMN_NAME_END);
            sb.append(" INTEGER,");
            sb.append(ScheduleTable.COLUMN_NAME_COUNT);
            sb.append(" INTEGER,");
            sb.append(ScheduleTable.COLUMN_NAME_LIMIT);
            sb.append(" INTEGER,");
            sb.append(ScheduleTable.COLUMN_NAME_GROUP);
            sb.append(" TEXT,");
            str4 = "ALTER TABLE action_schedules RENAME TO ";
            str11 = str26;
            sb.append(str11);
            sb.append(" INTEGER,");
            str13 = str25;
            sb.append(str13);
            str5 = "COMMIT;";
            sb.append(" DOUBLE,");
            String str27 = str24;
            sb.append(str27);
            sb.append(" INTEGER,");
            String str28 = str23;
            sb.append(str28);
            sb.append(" TEXT,");
            String str29 = str21;
            sb.append(str29);
            sb.append(" TEXT,");
            String str30 = str20;
            sb.append(str30);
            sb.append(" DOUBLE);");
            sQLiteDatabase3.execSQL(sb.toString());
            sQLiteDatabase3.execSQL("CREATE TABLE triggers(t_row_id INTEGER PRIMARY KEY AUTOINCREMENT,t_type INTEGER,t_cancellation INTEGER,t_s_id TEXT,t_predicate TEXT,t_progress DOUBLE,t_goal DOUBLE,FOREIGN KEY(t_s_id) REFERENCES action_schedules(s_id) ON DELETE CASCADE);");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("INSERT INTO action_schedules(s_row_id, s_id, ");
            sb2.append("s_actions");
            str10 = str22;
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_START);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_END);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_COUNT);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_LIMIT);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_PRIORITY);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_GROUP);
            sb2.append(str10);
            sb2.append(str11);
            sb2.append(str10);
            sb2.append(str13);
            sb2.append(str10);
            sb2.append(str27);
            sb2.append(str10);
            sb2.append(str28);
            sb2.append(str10);
            sb2.append(str29);
            sb2.append(str10);
            sb2.append(str30);
            sb2.append(") SELECT ");
            sb2.append(MessageCenterDataManager.MessageTable.COLUMN_NAME_KEY);
            sb2.append(str10);
            str7 = str30;
            sb2.append(ScheduleTable.COLUMN_NAME_SCHEDULE_ID);
            sb2.append(str10);
            sb2.append("s_actions");
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_START);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_END);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_COUNT);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_LIMIT);
            sb2.append(str10);
            sb2.append(ScheduleTable.COLUMN_NAME_GROUP);
            sb2.append(", 0, 0.0, 1, NULL, NULL, 0 FROM ");
            sb2.append("temp_schedule_entry_table");
            str9 = ";";
            sb2.append(str9);
            str8 = str29;
            sQLiteDatabase2 = sQLiteDatabase;
            sQLiteDatabase2.execSQL(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            str6 = str28;
            sb3.append("INSERT INTO triggers(t_row_id, t_type, t_cancellation, t_s_id, t_predicate, t_progress, t_goal) SELECT ");
            sb3.append(MessageCenterDataManager.MessageTable.COLUMN_NAME_KEY);
            sb3.append(str10);
            sb3.append(TriggerTable.COLUMN_NAME_TYPE);
            str16 = str19;
            sb3.append(str16);
            sb3.append(TriggerTable.COLUMN_NAME_SCHEDULE_ID);
            sb3.append(str10);
            sb3.append(TriggerTable.COLUMN_NAME_PREDICATE);
            sb3.append(str10);
            sb3.append(TriggerTable.COLUMN_NAME_PROGRESS);
            sb3.append(str10);
            sb3.append(TriggerTable.COLUMN_NAME_GOAL);
            sb3.append(str18);
            str12 = "temp_triggers_entry_table";
            sb3.append(str12);
            sb3.append(str9);
            sQLiteDatabase2.execSQL(sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            str14 = str17;
            sb4.append(str14);
            sb4.append("temp_schedule_entry_table");
            sb4.append(str9);
            sQLiteDatabase2.execSQL(sb4.toString());
            sQLiteDatabase2.execSQL(str14 + str12 + str9);
            sQLiteDatabase2.execSQL(str5);
            str15 = "BEGIN TRANSACTION;";
        } else if (i3 == 2) {
            str15 = "BEGIN TRANSACTION;";
            str3 = "ALTER TABLE triggers RENAME TO ";
            str4 = "ALTER TABLE action_schedules RENAME TO ";
            str9 = ";";
            str12 = "temp_triggers_entry_table";
            str10 = str22;
            str6 = str23;
            str13 = str25;
            str11 = str26;
            sQLiteDatabase2 = sQLiteDatabase3;
            str5 = "COMMIT;";
            str14 = str17;
            str16 = str19;
            String str31 = str21;
            str7 = str20;
            str8 = str31;
        } else if (i3 == 3) {
            sQLiteDatabase2 = sQLiteDatabase3;
            str2 = "COMMIT;";
            str = "BEGIN TRANSACTION;";
            sQLiteDatabase2.execSQL(str);
            sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_execution_state_change_date INTEGER;");
            sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_edit_grace_period INTEGER;");
            sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_interval INTEGER;");
            sQLiteDatabase2.execSQL(str2);
            sQLiteDatabase2.execSQL(str);
            sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_metadata TEXT;");
            sQLiteDatabase2.execSQL(str2);
        } else if (i3 == 4) {
            sQLiteDatabase2 = sQLiteDatabase3;
            str2 = "COMMIT;";
            str = "BEGIN TRANSACTION;";
            sQLiteDatabase2.execSQL(str);
            sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_metadata TEXT;");
            sQLiteDatabase2.execSQL(str2);
        } else if (i3 != 5) {
            sQLiteDatabase3.execSQL("DROP TABLE IF EXISTS action_schedules");
            sQLiteDatabase3.execSQL("DROP TABLE IF EXISTS triggers");
            onCreate(sQLiteDatabase);
            return;
        } else {
            sQLiteDatabase3.execSQL("BEGIN TRANSACTION;");
            sQLiteDatabase3.execSQL("ALTER TABLE action_schedules ADD COLUMN s_trigger_context TEXT;");
            sQLiteDatabase3.execSQL("COMMIT;");
            return;
        }
        sQLiteDatabase2.execSQL(str15);
        String str32 = str15;
        sQLiteDatabase2.execSQL(str4 + "temp_schedule_entry_table" + str9);
        sQLiteDatabase2.execSQL(str3 + str12 + str9);
        sQLiteDatabase2.execSQL("CREATE TABLE action_schedules (s_row_id INTEGER PRIMARY KEY AUTOINCREMENT,s_id TEXT UNIQUE,s_data TEXT,s_start INTEGER,s_end INTEGER,s_count INTEGER,s_limit INTEGER,s_priority INTEGER,s_group TEXT,s_execution_state INTEGER,s_pending_execution_date DOUBLE,d_app_state INTEGER,d_region_id TEXT,d_screen TEXT,d_seconds DOUBLE);");
        sQLiteDatabase2.execSQL("CREATE TABLE IF NOT EXISTS triggers (t_row_id INTEGER PRIMARY KEY AUTOINCREMENT,t_type INTEGER,t_cancellation INTEGER,t_s_id TEXT,t_predicate TEXT,t_progress DOUBLE,t_goal DOUBLE,FOREIGN KEY(t_s_id) REFERENCES action_schedules(s_id) ON DELETE CASCADE);");
        sQLiteDatabase2.execSQL("INSERT INTO action_schedules(s_row_id, s_id, s_data, s_start, s_end, s_count, s_limit, s_priority, s_group, s_execution_state, s_pending_execution_date, d_app_state, d_region_id, d_screen, d_seconds) SELECT s_row_id, s_id, " + "s_actions" + str10 + ScheduleTable.COLUMN_NAME_START + str10 + ScheduleTable.COLUMN_NAME_END + str10 + ScheduleTable.COLUMN_NAME_COUNT + str10 + ScheduleTable.COLUMN_NAME_LIMIT + str16 + ScheduleTable.COLUMN_NAME_GROUP + str10 + str11 + str10 + str13 + str10 + str24 + str10 + str6 + str10 + str8 + str10 + str7 + str18 + "temp_schedule_entry_table" + str9);
        StringBuilder sb5 = new StringBuilder();
        sb5.append("INSERT INTO triggers(t_row_id, t_type, t_cancellation, t_s_id, t_predicate, t_progress, t_goal) SELECT t_row_id, t_type, t_cancellation, t_s_id, t_predicate, t_progress, t_goal FROM ");
        sb5.append(str12);
        sb5.append(str9);
        sQLiteDatabase2.execSQL(sb5.toString());
        StringBuilder sb6 = new StringBuilder();
        String str33 = str14;
        sb6.append(str33);
        sb6.append("temp_schedule_entry_table");
        sb6.append(str9);
        sQLiteDatabase2.execSQL(sb6.toString());
        StringBuilder sb7 = new StringBuilder();
        sb7.append(str33);
        sb7.append(str12);
        sb7.append(str9);
        sQLiteDatabase2.execSQL(sb7.toString());
        str2 = str5;
        sQLiteDatabase2.execSQL(str2);
        str = str32;
        sQLiteDatabase2.execSQL(str);
        sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_execution_state_change_date INTEGER;");
        sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_edit_grace_period INTEGER;");
        sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_interval INTEGER;");
        sQLiteDatabase2.execSQL(str2);
        sQLiteDatabase2.execSQL(str);
        sQLiteDatabase2.execSQL("ALTER TABLE action_schedules ADD COLUMN s_metadata TEXT;");
        sQLiteDatabase2.execSQL(str2);
    }

    /* access modifiers changed from: protected */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.debug("AutomationDataManager - Dropping automation database. Downgrading from version %s to %s", Integer.valueOf(i), Integer.valueOf(i2));
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS triggers");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS action_schedules");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: protected */
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        super.onConfigure(sQLiteDatabase);
        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    public void deleteAllSchedules() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.execSQL("DROP TABLE IF EXISTS triggers");
            writableDatabase.execSQL("DROP TABLE IF EXISTS action_schedules");
            writableDatabase.close();
        } catch (Exception e) {
            Logger.error(e, "Failed to delete schedules.", new Object[0]);
        }
    }

    public Cursor querySchedules() {
        try {
            return rawQuery(GET_SCHEDULES_QUERY, (String[]) null);
        } catch (SQLException e) {
            Logger.error(e, "LegacyAutomationDataManager - Unable to get schedules.", new Object[0]);
            return null;
        }
    }
}
