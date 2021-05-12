package com.forasoft.homewavvisitor;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/App$MIGRATION_13_14$1", "Landroidx/room/migration/Migration;", "migrate", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: App.kt */
public final class App$MIGRATION_13_14$1 extends Migration {
    App$MIGRATION_13_14$1(int i, int i2) {
        super(i, i2);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkParameterIsNotNull(supportSQLiteDatabase, "database");
        supportSQLiteDatabase.execSQL("CREATE TABLE calls_new (id TEXT NOT NULL, protocol TEXT, disconnect_cause TEXT, visitor_username TEXT, notes TEXT, splicing_priority TEXT, timezone TEXT, splicing_finished TEXT, visitor_id TEXT, visitor_checkin TEXT, beta_splicer TEXT, splicing_outcome TEXT, visitor_location TEXT, prison_name TEXT, splicing_hints TEXT, disconnected INTEGER, ui TEXT, internal_notes TEXT, zone TEXT, station TEXT, reviewed TEXT, etype TEXT, purged TEXT, splicing_started TEXT, inmate_id TEXT, extension_count TEXT, inmate_name TEXT, recorded TEXT, visitor_email TEXT, visitor_station_id TEXT, visitor_name TEXT, tags TEXT, connected INTEGER, warning_message TEXT, pubid TEXT NOT NULL, free_seconds TEXT, invalidated TEXT, inmate_checkin TEXT, disconnect_required TEXT, account TEXT, age TEXT, prison_id TEXT, inmate_station_id TEXT, PRIMARY KEY(id))");
        supportSQLiteDatabase.execSQL("INSERT INTO calls_new (id, protocol, disconnect_cause, visitor_username, notes, splicing_priority, timezone, splicing_finished, visitor_id, visitor_checkin, beta_splicer, splicing_outcome, visitor_location, prison_name, splicing_hints, disconnected, ui, internal_notes, zone, station, reviewed, etype, purged, splicing_started, inmate_id, extension_count, inmate_name, recorded, visitor_email, visitor_station_id, visitor_name, tags, connected, warning_message, pubid, free_seconds, invalidated, inmate_checkin, disconnect_required, account, age, prison_id, inmate_station_id) SELECT id, protocol, disconnect_cause, visitor_username, notes, splicing_priority, timezone, splicing_finished, visitor_id, visitor_checkin, beta_splicer, splicing_outcome, visitor_location, prison_name, splicing_hints, disconnected, ui, internal_notes, zone, station, reviewed, etype, purged, splicing_started, inmate_id, extension_count, inmate_name, recorded, visitor_email, visitor_station_id, visitor_name, tags, connected, warning_message, pubid, free_seconds, invalidated, inmate_checkin, disconnect_required, account, age, prison_id, inmate_station_id FROM calls");
        supportSQLiteDatabase.execSQL("DROP TABLE calls");
        supportSQLiteDatabase.execSQL("ALTER TABLE calls_new RENAME TO calls");
    }
}
