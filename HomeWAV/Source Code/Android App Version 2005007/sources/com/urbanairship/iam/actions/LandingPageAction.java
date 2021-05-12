package com.urbanairship.iam.actions;

import android.net.Uri;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.actions.Action;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.automation.InAppAutomation;
import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.ScheduleData;
import com.urbanairship.automation.Triggers;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.html.HtmlDisplayContent;
import com.urbanairship.json.JsonMap;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.AirshipComponentUtils;
import com.urbanairship.util.Checks;
import com.urbanairship.util.UAStringUtil;
import com.urbanairship.util.UriUtils;
import java.util.UUID;
import java.util.concurrent.Callable;

public class LandingPageAction extends Action {
    public static final float DEFAULT_BORDER_RADIUS = 2.0f;
    public static final String DEFAULT_REGISTRY_NAME = "landing_page_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^p";
    private static final String LEGACY_ASPECT_LOCK_KEY = "aspectLock";
    public static final String URL_KEY = "url";
    private float borderRadius;
    private final Callable<InAppAutomation> inAppCallable;

    /* access modifiers changed from: protected */
    public InAppMessage.Builder extendMessage(InAppMessage.Builder builder) {
        return builder;
    }

    /* access modifiers changed from: protected */
    public Schedule.Builder<InAppMessage> extendSchedule(Schedule.Builder<InAppMessage> builder) {
        return builder;
    }

    public LandingPageAction() {
        this(AirshipComponentUtils.callableForComponent(InAppAutomation.class));
    }

    LandingPageAction(Callable<InAppAutomation> callable) {
        this.borderRadius = 2.0f;
        this.inAppCallable = callable;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        try {
            InAppAutomation call = this.inAppCallable.call();
            Uri parseUri = parseUri(actionArguments);
            Checks.checkNotNull(parseUri, "URI should not be null");
            call.schedule((Schedule<? extends ScheduleData>) createSchedule(parseUri, actionArguments));
            return ActionResult.newEmptyResult();
        } catch (Exception e) {
            return ActionResult.newErrorResult(e);
        }
    }

    public void setBorderRadius(float f) {
        this.borderRadius = f;
    }

    public float getBorderRadius() {
        return this.borderRadius;
    }

    /* access modifiers changed from: protected */
    public Schedule<InAppMessage> createSchedule(Uri uri, ActionArguments actionArguments) {
        boolean z;
        String str;
        boolean z2;
        JsonMap optMap = actionArguments.getValue().toJsonValue().optMap();
        int i = optMap.opt("width").getInt(0);
        int i2 = optMap.opt("height").getInt(0);
        if (optMap.containsKey(HtmlDisplayContent.ASPECT_LOCK_KEY)) {
            z = optMap.opt(HtmlDisplayContent.ASPECT_LOCK_KEY).getBoolean(false);
        } else {
            z = optMap.opt(LEGACY_ASPECT_LOCK_KEY).getBoolean(false);
        }
        PushMessage pushMessage = (PushMessage) actionArguments.getMetadata().getParcelable(ActionArguments.PUSH_MESSAGE_METADATA);
        if (pushMessage == null || pushMessage.getSendId() == null) {
            str = UUID.randomUUID().toString();
            z2 = false;
        } else {
            str = pushMessage.getSendId();
            z2 = true;
        }
        return extendSchedule(Schedule.newBuilder(extendMessage(InAppMessage.newBuilder().setDisplayContent(HtmlDisplayContent.newBuilder().setUrl(uri.toString()).setAllowFullscreenDisplay(false).setBorderRadius(this.borderRadius).setSize(i, i2, z).setRequireConnectivity(false).build()).setReportingEnabled(z2).setDisplayBehavior(InAppMessage.DISPLAY_BEHAVIOR_IMMEDIATE)).build()).setId(str).addTrigger(Triggers.newActiveSessionTriggerBuilder().setGoal(1.0d).build()).setLimit(1).setPriority(Integer.MIN_VALUE)).build();
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        if ((situation == 0 || situation == 6 || situation == 2 || situation == 3 || situation == 4) && parseUri(actionArguments) != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Uri parseUri(ActionArguments actionArguments) {
        String str;
        Uri parse;
        if (actionArguments.getValue().getMap() != null) {
            str = actionArguments.getValue().getMap().opt("url").getString();
        } else {
            str = actionArguments.getValue().getString();
        }
        if (str == null || (parse = UriUtils.parse(str)) == null || UAStringUtil.isEmpty(parse.toString())) {
            return null;
        }
        if (UAStringUtil.isEmpty(parse.getScheme())) {
            parse = Uri.parse("https://" + parse);
        }
        if (UAirship.shared().getUrlAllowList().isAllowed(parse.toString(), 2)) {
            return parse;
        }
        Logger.error("Landing page URL is not allowed: %s", parse);
        return null;
    }
}
