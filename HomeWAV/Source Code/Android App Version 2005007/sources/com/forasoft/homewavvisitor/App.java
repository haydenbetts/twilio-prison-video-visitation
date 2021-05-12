package com.forasoft.homewavvisitor;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import androidx.room.Room;
import com.forasoft.homewavvisitor.dao.AppDatabase;
import com.forasoft.homewavvisitor.model.AirshipAutopilot;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.toothpick.module.AppModule;
import com.forasoft.homewavvisitor.toothpick.module.ServerModule;
import com.getkeepsafe.relinker.ReLinker;
import com.google.firebase.FirebaseApp;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.crashes.Crashes;
import com.urbanairship.UAirship;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.danlew.android.joda.JodaTimeAndroid;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\r\u0004\u0007\n\r\u0010\u0013\u0016\u0019\u001c\u001f\"%(\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\u0010\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0002J\b\u00102\u001a\u00020+H\u0002J\b\u00103\u001a\u00020+H\u0002J\b\u00104\u001a\u00020+H\u0016R\u0010\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0010\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\u0004\n\u0002\u0010 R\u0010\u0010!\u001a\u00020\"X\u0004¢\u0006\u0004\n\u0002\u0010#R\u0010\u0010$\u001a\u00020%X\u0004¢\u0006\u0004\n\u0002\u0010&R\u0010\u0010'\u001a\u00020(X\u0004¢\u0006\u0004\n\u0002\u0010)¨\u00065"}, d2 = {"Lcom/forasoft/homewavvisitor/App;", "Landroidx/multidex/MultiDexApplication;", "()V", "MIGRATION_10_11", "com/forasoft/homewavvisitor/App$MIGRATION_10_11$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_10_11$1;", "MIGRATION_11_12", "com/forasoft/homewavvisitor/App$MIGRATION_11_12$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_11_12$1;", "MIGRATION_12_13", "com/forasoft/homewavvisitor/App$MIGRATION_12_13$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_12_13$1;", "MIGRATION_13_14", "com/forasoft/homewavvisitor/App$MIGRATION_13_14$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_13_14$1;", "MIGRATION_1_2", "com/forasoft/homewavvisitor/App$MIGRATION_1_2$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_1_2$1;", "MIGRATION_2_3", "com/forasoft/homewavvisitor/App$MIGRATION_2_3$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_2_3$1;", "MIGRATION_3_4", "com/forasoft/homewavvisitor/App$MIGRATION_3_4$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_3_4$1;", "MIGRATION_4_5", "com/forasoft/homewavvisitor/App$MIGRATION_4_5$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_4_5$1;", "MIGRATION_5_6", "com/forasoft/homewavvisitor/App$MIGRATION_5_6$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_5_6$1;", "MIGRATION_6_7", "com/forasoft/homewavvisitor/App$MIGRATION_6_7$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_6_7$1;", "MIGRATION_7_8", "com/forasoft/homewavvisitor/App$MIGRATION_7_8$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_7_8$1;", "MIGRATION_8_9", "com/forasoft/homewavvisitor/App$MIGRATION_8_9$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_8_9$1;", "MIGRATION_9_10", "com/forasoft/homewavvisitor/App$MIGRATION_9_10$1", "Lcom/forasoft/homewavvisitor/App$MIGRATION_9_10$1;", "attachBaseContext", "", "base", "Landroid/content/Context;", "initAppScope", "database", "Lcom/forasoft/homewavvisitor/dao/AppDatabase;", "initDatabase", "initLogger", "initToothpick", "onCreate", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: App.kt */
public final class App extends MultiDexApplication {
    private final App$MIGRATION_10_11$1 MIGRATION_10_11 = new App$MIGRATION_10_11$1(10, 11);
    private final App$MIGRATION_11_12$1 MIGRATION_11_12 = new App$MIGRATION_11_12$1(11, 12);
    private final App$MIGRATION_12_13$1 MIGRATION_12_13 = new App$MIGRATION_12_13$1(12, 13);
    private final App$MIGRATION_13_14$1 MIGRATION_13_14 = new App$MIGRATION_13_14$1(13, 14);
    private final App$MIGRATION_1_2$1 MIGRATION_1_2 = new App$MIGRATION_1_2$1(1, 2);
    private final App$MIGRATION_2_3$1 MIGRATION_2_3 = new App$MIGRATION_2_3$1(2, 3);
    private final App$MIGRATION_3_4$1 MIGRATION_3_4 = new App$MIGRATION_3_4$1(3, 4);
    private final App$MIGRATION_4_5$1 MIGRATION_4_5 = new App$MIGRATION_4_5$1(4, 5);
    private final App$MIGRATION_5_6$1 MIGRATION_5_6 = new App$MIGRATION_5_6$1(5, 6);
    private final App$MIGRATION_6_7$1 MIGRATION_6_7 = new App$MIGRATION_6_7$1(6, 7);
    private final App$MIGRATION_7_8$1 MIGRATION_7_8 = new App$MIGRATION_7_8$1(7, 8);
    private final App$MIGRATION_8_9$1 MIGRATION_8_9 = new App$MIGRATION_8_9$1(8, 9);
    private final App$MIGRATION_9_10$1 MIGRATION_9_10 = new App$MIGRATION_9_10$1(9, 10);

    private final void initLogger() {
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    public void onCreate() {
        super.onCreate();
        Context context = this;
        FirebaseApp.initializeApp(context);
        ReLinker.recursively();
        ReLinker.loadLibrary(getApplicationContext(), "avcodec");
        ReLinker.loadLibrary(getApplicationContext(), "avformat");
        ReLinker.loadLibrary(getApplicationContext(), "avutil");
        ReLinker.loadLibrary(getApplicationContext(), "jniavcodec");
        ReLinker.loadLibrary(getApplicationContext(), "jniavformat");
        ReLinker.loadLibrary(getApplicationContext(), "jniavutil");
        ReLinker.loadLibrary(getApplicationContext(), "jniswresample");
        ReLinker.loadLibrary(getApplicationContext(), "jniswscale");
        ReLinker.loadLibrary(getApplicationContext(), "swresample");
        ReLinker.loadLibrary(getApplicationContext(), "swscale");
        Application application = this;
        AppCenter.start(application, BuildConfig.APP_CENTER_SECRET, Crashes.class);
        AirshipAutopilot airshipAutopilot = new AirshipAutopilot(context);
        UAirship.takeOff(application, airshipAutopilot.createAirshipConfigOptions(context), airshipAutopilot);
        initLogger();
        initToothpick();
        AndroidThreeTen.init(application);
        JodaTimeAndroid.init(context);
        initAppScope(initDatabase());
    }

    private final AppDatabase initDatabase() {
        AppDatabase build = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "homewav_db").addMigrations(this.MIGRATION_1_2, this.MIGRATION_2_3, this.MIGRATION_3_4, this.MIGRATION_4_5, this.MIGRATION_5_6, this.MIGRATION_6_7, this.MIGRATION_7_8, this.MIGRATION_8_9, this.MIGRATION_9_10, this.MIGRATION_10_11, this.MIGRATION_11_12, this.MIGRATION_12_13, this.MIGRATION_13_14).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(app…_14)\n            .build()");
        return build;
    }

    private final void initToothpick() {
        Toothpick.setConfiguration(Configuration.forProduction());
    }

    private final void initAppScope(AppDatabase appDatabase) {
        Toothpick.openScope(DI.APP_SCOPE).installModules(new AppModule(this, appDatabase));
        Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE).installModules(new ServerModule(BuildConfig.API_ADDRESS));
    }
}
