package com.forasoft.homewavvisitor.model;

import android.content.Context;
import com.forasoft.homewavvisitor.BuildConfig;
import com.forasoft.homewavvisitor.model.notifications.MessageListenerService;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Autopilot;
import com.urbanairship.UAirship;
import com.urbanairship.location.AirshipLocationManager;
import com.urbanairship.location.LocationRequestOptions;
import com.urbanairship.push.PushManager;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/AirshipAutopilot;", "Lcom/urbanairship/Autopilot;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createAirshipConfigOptions", "Lcom/urbanairship/AirshipConfigOptions;", "onAirshipReady", "", "airship", "Lcom/urbanairship/UAirship;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AirshipAutopilot.kt */
public final class AirshipAutopilot extends Autopilot {
    private final Context context;

    public AirshipAutopilot(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public void onAirshipReady(UAirship uAirship) {
        Intrinsics.checkParameterIsNotNull(uAirship, MessageListenerService.AIRSHIP_MESSAGE);
        super.onAirshipReady(uAirship);
        PushManager pushManager = uAirship.getPushManager();
        Intrinsics.checkExpressionValueIsNotNull(pushManager, "airship.pushManager");
        pushManager.setUserNotificationsEnabled(true);
        AirshipLocationManager shared = AirshipLocationManager.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared, "AirshipLocationManager.shared()");
        shared.setBackgroundLocationAllowed(true);
        AirshipLocationManager shared2 = AirshipLocationManager.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared2, "AirshipLocationManager.shared()");
        shared2.setLocationUpdatesEnabled(true);
        LocationRequestOptions build = new LocationRequestOptions.Builder().setPriority(2).setMinDistance(800.0f).setMinTime(5, TimeUnit.MINUTES).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "LocationRequestOptions.B…\n                .build()");
        AirshipLocationManager.shared().setLocationRequestOptions(build);
    }

    public AirshipConfigOptions createAirshipConfigOptions(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        AirshipConfigOptions.Builder channelCaptureEnabled = new AirshipConfigOptions.Builder().setInProduction(true).setChannelCaptureEnabled(true);
        channelCaptureEnabled.setProductionAppKey(BuildConfig.AIRSHIP_KEY);
        channelCaptureEnabled.setProductionAppSecret(BuildConfig.AIRSHIP_SECRET);
        return channelCaptureEnabled.build();
    }
}
