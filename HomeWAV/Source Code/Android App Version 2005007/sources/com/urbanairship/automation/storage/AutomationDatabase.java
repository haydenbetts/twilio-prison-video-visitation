package com.urbanairship.automation.storage;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.urbanairship.config.AirshipRuntimeConfig;
import java.io.File;

public abstract class AutomationDatabase extends RoomDatabase {
    public abstract AutomationDao getScheduleDao();

    public static AutomationDatabase createDatabase(Context context, AirshipRuntimeConfig airshipRuntimeConfig) {
        return Room.databaseBuilder(context, AutomationDatabase.class, new File(ContextCompat.getNoBackupFilesDir(context), airshipRuntimeConfig.getConfigOptions().appKey + "_in-app-automation").getAbsolutePath()).build();
    }
}
