package com.forasoft.homewavvisitor;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/App$MIGRATION_9_10$1", "Landroidx/room/migration/Migration;", "migrate", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: App.kt */
public final class App$MIGRATION_9_10$1 extends Migration {
    App$MIGRATION_9_10$1(int i, int i2) {
        super(i, i2);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkParameterIsNotNull(supportSQLiteDatabase, "database");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `credits`");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `credits` (`id` TEXT NOT NULL, `inmate_id` TEXT, `braintree_transaction_id` TEXT, `stripe_transaction_id` TEXT, `created` INTEGER NOT NULL, `creator` TEXT NOT NULL, `notes` TEXT NOT NULL, `type` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`id`))");
    }
}
