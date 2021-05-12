package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Router;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010!\u001a\u00020\u0014H\u0016J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$H\u0016J\u001a\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/NotificationSettingsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "router", "Lru/terrakok/cicerone/Router;", "getRouter", "()Lru/terrakok/cicerone/Router;", "setRouter", "(Lru/terrakok/cicerone/Router;)V", "settings", "Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "getSettings", "()Lcom/forasoft/homewavvisitor/model/data/account/Settings;", "setSettings", "(Lcom/forasoft/homewavvisitor/model/data/account/Settings;)V", "onBackPressed", "", "onCheckedChanged", "", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationSettingsFragment.kt */
public final class NotificationSettingsFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @Inject
    public Router router;
    @Inject
    public Settings settings;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final Router getRouter() {
        Router router2 = this.router;
        if (router2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        return router2;
    }

    public final void setRouter(Router router2) {
        Intrinsics.checkParameterIsNotNull(router2, "<set-?>");
        this.router = router2;
    }

    public final Settings getSettings() {
        Settings settings2 = this.settings;
        if (settings2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
        }
        return settings2;
    }

    public final void setSettings(Settings settings2) {
        Intrinsics.checkParameterIsNotNull(settings2, "<set-?>");
        this.settings = settings2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Toothpick.inject(this, getScope());
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_notification_settings, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((MainActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.notification_settings);
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                ActionBar supportActionBar2 = ((MainActivity) activity2).getSupportActionBar();
                if (supportActionBar2 != null) {
                    supportActionBar2.setDisplayHomeAsUpEnabled(true);
                }
                FragmentActivity activity3 = getActivity();
                if (activity3 != null) {
                    ActionBar supportActionBar3 = ((MainActivity) activity3).getSupportActionBar();
                    if (supportActionBar3 != null) {
                        supportActionBar3.setDisplayShowHomeEnabled(true);
                    }
                    FragmentActivity activity4 = getActivity();
                    if (activity4 != null) {
                        CommonKt.hide((BottomNavigationViewEx) ((MainActivity) activity4)._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
                        SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_inmate_status);
                        Intrinsics.checkExpressionValueIsNotNull(switchCompat, "sw_inmate_status");
                        Settings settings2 = this.settings;
                        if (settings2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("settings");
                        }
                        switchCompat.setChecked(settings2.getStatusNotification());
                        Switch switchR = (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_incoming_messages);
                        Intrinsics.checkExpressionValueIsNotNull(switchR, "sw_incoming_messages");
                        Settings settings3 = this.settings;
                        if (settings3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("settings");
                        }
                        switchR.setChecked(settings3.getMessageNotification());
                        Switch switchR2 = (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_low_balance);
                        Intrinsics.checkExpressionValueIsNotNull(switchR2, "sw_low_balance");
                        Settings settings4 = this.settings;
                        if (settings4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("settings");
                        }
                        switchR2.setChecked(settings4.getBalanceNotification());
                        Switch switchR3 = (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_schedule_change);
                        Intrinsics.checkExpressionValueIsNotNull(switchR3, "sw_schedule_change");
                        Settings settings5 = this.settings;
                        if (settings5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("settings");
                        }
                        switchR3.setChecked(settings5.getScheduleNotification());
                        CompoundButton[] compoundButtonArr = {(SwitchCompat) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_inmate_status), (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_incoming_messages), (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_low_balance), (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_schedule_change)};
                        for (int i = 0; i < 4; i++) {
                            compoundButtonArr[i].setOnCheckedChangeListener(this);
                        }
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
                }
                throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public void onDestroyView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            CommonKt.show((BottomNavigationViewEx) ((MainActivity) activity)._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
            super.onDestroyView();
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            return onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Intrinsics.checkParameterIsNotNull(compoundButton, "buttonView");
        if (Intrinsics.areEqual((Object) compoundButton, (Object) (SwitchCompat) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_inmate_status))) {
            Settings settings2 = this.settings;
            if (settings2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            }
            settings2.setStatusNotification(z);
        } else if (Intrinsics.areEqual((Object) compoundButton, (Object) (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_incoming_messages))) {
            Settings settings3 = this.settings;
            if (settings3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            }
            settings3.setMessageNotification(z);
        } else if (Intrinsics.areEqual((Object) compoundButton, (Object) (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_low_balance))) {
            Settings settings4 = this.settings;
            if (settings4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            }
            settings4.setBalanceNotification(z);
        } else if (Intrinsics.areEqual((Object) compoundButton, (Object) (Switch) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.sw_schedule_change))) {
            Settings settings5 = this.settings;
            if (settings5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            }
            settings5.setScheduleNotification(z);
        }
    }

    public boolean onBackPressed() {
        Router router2 = this.router;
        if (router2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        router2.exit();
        return true;
    }
}
