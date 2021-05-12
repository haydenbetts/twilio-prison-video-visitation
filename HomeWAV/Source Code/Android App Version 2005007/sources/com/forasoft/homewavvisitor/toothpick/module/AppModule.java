package com.forasoft.homewavvisitor.toothpick.module;

import android.content.Context;
import com.forasoft.homewavvisitor.AppInfo;
import com.forasoft.homewavvisitor.BuildConfig;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.AppDatabase;
import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.dao.CreditDao;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserAnalyticsDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.model.AirshipAnalytics;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.AuthPrefs;
import com.forasoft.homewavvisitor.model.repository.analytics.AnalyticsRepository;
import com.forasoft.homewavvisitor.toothpick.PrimitiveWrapper;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import com.forasoft.homewavvisitor.toothpick.qualifier.PasswordLength;
import com.urbanairship.UAirship;
import com.urbanairship.channel.ChannelRegistrationPayload;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/module/AppModule;", "Ltoothpick/config/Module;", "context", "Landroid/content/Context;", "database", "Lcom/forasoft/homewavvisitor/dao/AppDatabase;", "(Landroid/content/Context;Lcom/forasoft/homewavvisitor/dao/AppDatabase;)V", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AppModule.kt */
public final class AppModule extends Module {
    public AppModule(Context context, AppDatabase appDatabase) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(appDatabase, "database");
        bind(Context.class).toInstance(context);
        bind(File.class).toInstance(context.getFilesDir());
        bind(PrimitiveWrapper.class).withName(PasswordLength.class).toInstance(new PrimitiveWrapper(6));
        bind(AnalyticsRepository.class).singleton();
        bind(UAirship.class).toInstance(UAirship.shared());
        bind(Analytics.class).to(AirshipAnalytics.class).singleton();
        Cicerone create = Cicerone.create(new HomewavRouter());
        Intrinsics.checkExpressionValueIsNotNull(create, "Cicerone.create(HomewavRouter())");
        bind(Router.class).withName(Global.class).toInstance(create.getRouter());
        bind(HomewavRouter.class).withName(Global.class).toInstance(create.getRouter());
        bind(NavigatorHolder.class).withName(Global.class).toInstance(create.getNavigatorHolder());
        bind(AuthHolder.class).to(AuthPrefs.class).singleton();
        bind(AppInfo.class).toInstance(new AppInfo(ChannelRegistrationPayload.ANDROID_DEVICE_TYPE, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
        bind(AppDatabase.class).toInstance(appDatabase);
        bind(UserDao.class).toInstance(appDatabase.userDao());
        bind(InmateDao.class).toInstance(appDatabase.inmateDao());
        bind(NotificationDao.class).toInstance(appDatabase.notificationDao());
        bind(MessageDao.class).toInstance(appDatabase.messageDao());
        bind(VisitDao.class).toInstance(appDatabase.visitDao());
        bind(CallDao.class).toInstance(appDatabase.callDao());
        bind(CreditDao.class).toInstance(appDatabase.creditDao());
        bind(UserAnalyticsDao.class).toInstance(appDatabase.userAnalyticsDao());
        bind(Constants.class).toInstance(new Constants());
    }
}
