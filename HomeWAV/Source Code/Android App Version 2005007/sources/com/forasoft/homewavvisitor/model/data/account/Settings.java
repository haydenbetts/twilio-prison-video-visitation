package com.forasoft.homewavvisitor.model.data.account;

import android.content.Context;
import android.content.SharedPreferences;
import com.forasoft.homewavvisitor.model.BooleanPrefDelegate;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010$\u001a\u00020%R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR+\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001b\u0010\u0016\u001a\u00020\u00178BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R+\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\r\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u000bR+\u0010 \u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0002¢\u0006\u0012\n\u0004\b#\u0010\r\u001a\u0004\b!\u0010\t\"\u0004\b\"\u0010\u000b¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "<set-?>", "", "balanceNotification", "getBalanceNotification", "()Z", "setBalanceNotification", "(Z)V", "balanceNotification$delegate", "Lcom/forasoft/homewavvisitor/model/BooleanPrefDelegate;", "confirmMessages", "getConfirmMessages", "setConfirmMessages", "confirmMessages$delegate", "messageNotification", "getMessageNotification", "setMessageNotification", "messageNotification$delegate", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "scheduleNotification", "getScheduleNotification", "setScheduleNotification", "scheduleNotification$delegate", "statusNotification", "getStatusNotification", "setStatusNotification", "statusNotification$delegate", "clear", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Settings.kt */
public final class Settings {
    static final /* synthetic */ KProperty[] $$delegatedProperties;
    private final BooleanPrefDelegate balanceNotification$delegate;
    private final BooleanPrefDelegate confirmMessages$delegate;
    private final BooleanPrefDelegate messageNotification$delegate;
    private final Lazy prefs$delegate;
    private final BooleanPrefDelegate scheduleNotification$delegate;
    private final BooleanPrefDelegate statusNotification$delegate;

    static {
        Class<Settings> cls = Settings.class;
        $$delegatedProperties = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "statusNotification", "getStatusNotification()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "messageNotification", "getMessageNotification()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "balanceNotification", "getBalanceNotification()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "scheduleNotification", "getScheduleNotification()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "confirmMessages", "getConfirmMessages()Z"))};
    }

    private final SharedPreferences getPrefs() {
        return (SharedPreferences) this.prefs$delegate.getValue();
    }

    public final boolean getBalanceNotification() {
        return this.balanceNotification$delegate.getValue((Object) this, (KProperty<?>) $$delegatedProperties[2]).booleanValue();
    }

    public final boolean getConfirmMessages() {
        return this.confirmMessages$delegate.getValue((Object) this, (KProperty<?>) $$delegatedProperties[4]).booleanValue();
    }

    public final boolean getMessageNotification() {
        return this.messageNotification$delegate.getValue((Object) this, (KProperty<?>) $$delegatedProperties[1]).booleanValue();
    }

    public final boolean getScheduleNotification() {
        return this.scheduleNotification$delegate.getValue((Object) this, (KProperty<?>) $$delegatedProperties[3]).booleanValue();
    }

    public final boolean getStatusNotification() {
        return this.statusNotification$delegate.getValue((Object) this, (KProperty<?>) $$delegatedProperties[0]).booleanValue();
    }

    public final void setBalanceNotification(boolean z) {
        this.balanceNotification$delegate.setValue((Object) this, (KProperty<?>) $$delegatedProperties[2], z);
    }

    public final void setConfirmMessages(boolean z) {
        this.confirmMessages$delegate.setValue((Object) this, (KProperty<?>) $$delegatedProperties[4], z);
    }

    public final void setMessageNotification(boolean z) {
        this.messageNotification$delegate.setValue((Object) this, (KProperty<?>) $$delegatedProperties[1], z);
    }

    public final void setScheduleNotification(boolean z) {
        this.scheduleNotification$delegate.setValue((Object) this, (KProperty<?>) $$delegatedProperties[3], z);
    }

    public final void setStatusNotification(boolean z) {
        this.statusNotification$delegate.setValue((Object) this, (KProperty<?>) $$delegatedProperties[0], z);
    }

    @Inject
    public Settings(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.prefs$delegate = LazyKt.lazy(new Settings$prefs$2(context));
        this.statusNotification$delegate = new BooleanPrefDelegate(context, true);
        this.messageNotification$delegate = new BooleanPrefDelegate(context, true);
        this.balanceNotification$delegate = new BooleanPrefDelegate(context, true);
        this.scheduleNotification$delegate = new BooleanPrefDelegate(context, true);
        this.confirmMessages$delegate = new BooleanPrefDelegate(context, true);
    }

    public final void clear() {
        getPrefs().edit().clear().apply();
    }
}
