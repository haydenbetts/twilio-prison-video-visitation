package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.notifications.MessageListenerService;
import com.forasoft.homewavvisitor.model.repository.analytics.AnalyticsRepository;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.CustomEvent;
import com.urbanairship.channel.NamedUser;
import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0014\u0018\u0000 *2\u00020\u0001:\u0002)*B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\fH\u0016J\b\u0010\u001d\u001a\u00020\fH\u0016J\b\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016J\b\u0010 \u001a\u00020\fH\u0016J\b\u0010!\u001a\u00020\fH\u0016J\b\u0010\"\u001a\u00020\fH\u0016J\b\u0010#\u001a\u00020\fH\u0016J\u0010\u0010$\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003J\u0010\u0010%\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010&\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/forasoft/homewavvisitor/model/AirshipAnalytics;", "Lcom/forasoft/homewavvisitor/model/Analytics;", "airship", "Lcom/urbanairship/UAirship;", "repository", "Lcom/forasoft/homewavvisitor/model/repository/analytics/AnalyticsRepository;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lcom/urbanairship/UAirship;Lcom/forasoft/homewavvisitor/model/repository/analytics/AnalyticsRepository;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "subscription", "Lio/reactivex/disposables/CompositeDisposable;", "clearLowBalanceReport", "", "userAnalytics", "Lcom/forasoft/homewavvisitor/model/data/analytics/UserAnalytics;", "clearNoMoneyReport", "onAccountCreated", "onAddConnection", "onAddFunds", "onCall", "onClose", "onDeleteInmate", "prison", "", "state", "onDroppedCall", "onLogin", "pin", "onLogout", "onLowBalance", "onMoney", "onNoMoney", "onNormalBalance", "onOpen", "onSendMessage", "onViewingMessageCenter", "saveAnalytics", "sendLowBalanceEvent", "sendNoMoneyEvent", "updateAnalytics", "analytics", "CallAnalyticsUpdater", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AirshipAnalytics.kt */
public final class AirshipAnalytics implements Analytics {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG_GROUP = "homewav-tag-group";
    private final UAirship airship;
    private final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final AnalyticsRepository repository;
    private CompositeDisposable subscription = new CompositeDisposable();

    @Inject
    public AirshipAnalytics(UAirship uAirship, AnalyticsRepository analyticsRepository, AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(uAirship, MessageListenerService.AIRSHIP_MESSAGE);
        Intrinsics.checkParameterIsNotNull(analyticsRepository, "repository");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.airship = uAirship;
        this.repository = analyticsRepository;
        this.authHolder = authHolder2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/model/AirshipAnalytics$Companion;", "", "()V", "TAG_GROUP", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AirshipAnalytics.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public void onOpen() {
        CustomEvent build = new CustomEvent.Builder("visitor_open_app").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_open_app\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onClose() {
        CustomEvent build = new CustomEvent.Builder("visitor_close_app").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_close_app\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onLogin(String str) {
        Intrinsics.checkParameterIsNotNull(str, "pin");
        NamedUser namedUser = this.airship.getNamedUser();
        Intrinsics.checkExpressionValueIsNotNull(namedUser, "airship.namedUser");
        namedUser.setId(str);
        CustomEvent build = new CustomEvent.Builder("visitor_login").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_login\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onLogout() {
        CustomEvent build = new CustomEvent.Builder("visitor_logout").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_logout\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onAddFunds() {
        CustomEvent build = new CustomEvent.Builder("visitor_add_funds").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_add_funds\").build()");
        this.airship.getAnalytics().addEvent(build);
        this.airship.getNamedUser().editTagGroups().addTag(TAG_GROUP, "visitor_first_funds").apply();
    }

    public void onDeleteInmate(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "prison");
        Intrinsics.checkParameterIsNotNull(str2, "state");
        this.airship.getNamedUser().editTagGroups().removeTags(TAG_GROUP, SetsKt.mutableSetOf(str, str2)).apply();
    }

    public void onCall() {
        String id;
        User user = this.authHolder.getUser();
        if (user != null && (id = user.getId()) != null) {
            CompositeDisposable compositeDisposable = this.subscription;
            Disposable subscribe = CommonKt.applyAsync(this.repository.getAnalytics(id)).subscribe(new AirshipAnalytics$onCall$1(this), new AirshipAnalytics$onCall$2(this, id));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "repository.getAnalytics(…     }\n                })");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
        }
    }

    public void onSendMessage() {
        CustomEvent build = new CustomEvent.Builder("visitor_sent_message").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"vis…or_sent_message\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onLowBalance() {
        String id;
        User user = this.authHolder.getUser();
        if (user != null && (id = user.getId()) != null) {
            CompositeDisposable compositeDisposable = this.subscription;
            Disposable subscribe = CommonKt.applyAsync(this.repository.getAnalytics(id)).subscribe(new AirshipAnalytics$onLowBalance$1(this), new AirshipAnalytics$onLowBalance$2(this, id));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "repository.getAnalytics(…     }\n                })");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
        }
    }

    /* access modifiers changed from: private */
    public final void sendLowBalanceEvent(UserAnalytics userAnalytics) {
        if (!userAnalytics.getLess_two_funds_reported()) {
            CustomEvent build = new CustomEvent.Builder("visitor_least_inmate_less_two").build();
            Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"vis…inmate_less_two\").build()");
            this.airship.getAnalytics().addEvent(build);
            userAnalytics.setLess_two_funds_reported(true);
            saveAnalytics(userAnalytics);
        }
    }

    public void onNormalBalance() {
        String id;
        User user = this.authHolder.getUser();
        if (user != null && (id = user.getId()) != null) {
            CompositeDisposable compositeDisposable = this.subscription;
            Disposable subscribe = CommonKt.applyAsync(this.repository.getAnalytics(id)).subscribe(new AirshipAnalytics$onNormalBalance$1(this), new AirshipAnalytics$onNormalBalance$2(this, id));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "repository.getAnalytics(…     }\n                })");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
        }
    }

    /* access modifiers changed from: private */
    public final void clearLowBalanceReport(UserAnalytics userAnalytics) {
        if (userAnalytics.getLess_two_funds_reported()) {
            userAnalytics.setLess_two_funds_reported(false);
            saveAnalytics(userAnalytics);
        }
    }

    public void onAddConnection() {
        CustomEvent build = new CustomEvent.Builder("inmate_added").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"inmate_added\").build()");
        this.airship.getAnalytics().addEvent(build);
        this.airship.getNamedUser().editTagGroups().addTag(TAG_GROUP, "visitor_added_first_inmate").apply();
    }

    public void onDroppedCall() {
        CustomEvent build = new CustomEvent.Builder("call_dropped").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"call_dropped\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onViewingMessageCenter() {
        CustomEvent build = new CustomEvent.Builder("visitor_message_center_opened").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"vis…e_center_opened\").build()");
        this.airship.getAnalytics().addEvent(build);
    }

    public void onNoMoney() {
        String id;
        User user = this.authHolder.getUser();
        if (user != null && (id = user.getId()) != null) {
            CompositeDisposable compositeDisposable = this.subscription;
            Disposable subscribe = CommonKt.applyAsync(this.repository.getAnalytics(id)).subscribe(new AirshipAnalytics$onNoMoney$1(this), new AirshipAnalytics$onNoMoney$2(this, id));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "repository.getAnalytics(…     }\n                })");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
        }
    }

    public void onMoney() {
        String id;
        User user = this.authHolder.getUser();
        if (user != null && (id = user.getId()) != null) {
            CompositeDisposable compositeDisposable = this.subscription;
            Disposable subscribe = CommonKt.applyAsync(this.repository.getAnalytics(id)).subscribe(new AirshipAnalytics$onMoney$1(this), new AirshipAnalytics$onMoney$2(this, id));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "repository.getAnalytics(…     }\n                })");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
        }
    }

    public void onAccountCreated() {
        this.airship.getNamedUser().editTagGroups().addTag(TAG_GROUP, "visitor_created_account").apply();
    }

    /* access modifiers changed from: private */
    public final void updateAnalytics(UserAnalytics userAnalytics) {
        saveAnalytics(new CallAnalyticsUpdater().run(userAnalytics, this.airship));
    }

    private final void saveAnalytics(UserAnalytics userAnalytics) {
        Completable fromCallable = Completable.fromCallable(new AirshipAnalytics$saveAnalytics$1(this, userAnalytics));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Completable.fromCallable…nalytics(userAnalytics) }");
        CommonKt.applyAsync(fromCallable).subscribe((Action) AirshipAnalytics$saveAnalytics$2.INSTANCE);
    }

    /* access modifiers changed from: private */
    public final void sendNoMoneyEvent(UserAnalytics userAnalytics) {
        if (!userAnalytics.getNo_money_reported()) {
            userAnalytics.setNo_money_reported(true);
            saveAnalytics(userAnalytics);
            CustomEvent build = new CustomEvent.Builder("visitor_no_money").build();
            Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_no_money\").build()");
            this.airship.getAnalytics().addEvent(build);
        }
    }

    /* access modifiers changed from: private */
    public final void clearNoMoneyReport(UserAnalytics userAnalytics) {
        if (userAnalytics.getNo_money_reported()) {
            userAnalytics.setNo_money_reported(false);
            saveAnalytics(userAnalytics);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/AirshipAnalytics$CallAnalyticsUpdater;", "", "()V", "run", "Lcom/forasoft/homewavvisitor/model/data/analytics/UserAnalytics;", "analytics", "airship", "Lcom/urbanairship/UAirship;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AirshipAnalytics.kt */
    private static final class CallAnalyticsUpdater {
        public final UserAnalytics run(UserAnalytics userAnalytics, UAirship uAirship) {
            Intrinsics.checkParameterIsNotNull(userAnalytics, Modules.ANALYTICS_MODULE);
            Intrinsics.checkParameterIsNotNull(uAirship, MessageListenerService.AIRSHIP_MESSAGE);
            Set linkedHashSet = new LinkedHashSet();
            Set linkedHashSet2 = new LinkedHashSet();
            if (!userAnalytics.getFirst_call_done()) {
                userAnalytics.setFirst_call_done(true);
                CustomEvent build = new CustomEvent.Builder("visitor_first_call").build();
                Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_first_call\").build()");
                uAirship.getAnalytics().addEvent(build);
                linkedHashSet.add("visitor_first_call");
            }
            DateTime withTimeAtStartOfDay = new DateTime().withZone(DateTimeZone.UTC).withTimeAtStartOfDay();
            Long call_day_timestamp = userAnalytics.getCall_day_timestamp();
            Intrinsics.checkExpressionValueIsNotNull(withTimeAtStartOfDay, "dayStartTimestamp");
            long millis = withTimeAtStartOfDay.getMillis();
            if (call_day_timestamp != null && call_day_timestamp.longValue() == millis) {
                userAnalytics.setCall_day_count(userAnalytics.getCall_day_count() + 1);
                if (userAnalytics.getCall_day_count() == 5) {
                    CustomEvent build2 = new CustomEvent.Builder("visitor_five_call_day").build();
                    Intrinsics.checkExpressionValueIsNotNull(build2, "CustomEvent.Builder(\"vis…r_five_call_day\").build()");
                    uAirship.getAnalytics().addEvent(build2);
                    linkedHashSet.add("visitor_five_call_day");
                }
            } else {
                userAnalytics.setCall_day_timestamp(Long.valueOf(withTimeAtStartOfDay.getMillis()));
                userAnalytics.setCall_day_count(1);
                linkedHashSet2.add("visitor_five_call_day");
            }
            DateTime withTimeAtStartOfDay2 = new DateTime().withZone(DateTimeZone.UTC).withDayOfWeek(1).withTimeAtStartOfDay();
            Long call_week_timestamp = userAnalytics.getCall_week_timestamp();
            Intrinsics.checkExpressionValueIsNotNull(withTimeAtStartOfDay2, "weekStartTimestamp");
            long millis2 = withTimeAtStartOfDay2.getMillis();
            if (call_week_timestamp != null && call_week_timestamp.longValue() == millis2) {
                userAnalytics.setCall_week_count(userAnalytics.getCall_week_count() + 1);
                if (userAnalytics.getCall_week_count() == 5) {
                    CustomEvent build3 = new CustomEvent.Builder("visitor_five_call_week").build();
                    Intrinsics.checkExpressionValueIsNotNull(build3, "CustomEvent.Builder(\"vis…_five_call_week\").build()");
                    uAirship.getAnalytics().addEvent(build3);
                    linkedHashSet.add("visitor_five_call_week");
                }
            } else {
                userAnalytics.setCall_week_timestamp(Long.valueOf(withTimeAtStartOfDay2.getMillis()));
                userAnalytics.setCall_week_count(1);
                linkedHashSet2.add("visitor_five_call_week");
            }
            uAirship.getNamedUser().editTagGroups().addTags(AirshipAnalytics.TAG_GROUP, linkedHashSet).removeTags(AirshipAnalytics.TAG_GROUP, linkedHashSet2).apply();
            return userAnalytics;
        }
    }
}
