package com.forasoft.homewavvisitor.ui.fragment.home;

import air.HomeWAV.R;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.home.InmateDetailPresenter;
import com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView;
import com.forasoft.homewavvisitor.ui.activity.BottomNavigationListener;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.TernaryView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0014\u0018\u0000 @2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001@B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001c\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\rH\u0016J\b\u0010'\u001a\u00020\rH\u0016J\u001a\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u001f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010*\u001a\u00020\u0007H\u0007J\u0010\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\rH\u0016J\b\u0010/\u001a\u00020\rH\u0016J\u0010\u00100\u001a\u00020\r2\u0006\u00101\u001a\u00020-H\u0016J\u0012\u00102\u001a\u00020\r2\b\u00103\u001a\u0004\u0018\u00010-H\u0016J\u0010\u00104\u001a\u00020\r2\u0006\u00105\u001a\u00020\u0012H\u0016J\u0010\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020-H\u0016J\u0010\u00108\u001a\u00020\r2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020-H\u0016J\u0012\u0010;\u001a\u00020\r2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020-H\u0016J\u0010\u0010>\u001a\u00020\r2\u0006\u0010?\u001a\u00020-H\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006A"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/InmateDetailFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/home/InmateDetailView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "Lcom/forasoft/homewavvisitor/ui/activity/BottomNavigationListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/home/InmateDetailPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/home/InmateDetailPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/home/InmateDetailPresenter;)V", "initTernaryViews", "", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onBottomNavigationClicked", "actionId", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onViewCreated", "view", "providePresenter", "setAvatar", "name", "", "setCheckActive", "setCheckInactive", "setCredits", "balance", "setId", "identifier", "setInvisible", "allowCalls", "setMoney", "credit_balance", "setName", "setStatus", "status", "showDeleteDialog", "showVisitsNumber", "visits", "showVisitsText", "text", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateDetailFragment.kt */
public final class InmateDetailFragment extends BaseFragment implements InmateDetailView, OnBackButtonPressedListener, BottomNavigationListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String INMATE = "inmate";
    private HashMap _$_findViewCache;
    @InjectPresenter
    public InmateDetailPresenter presenter;

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

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/InmateDetailFragment$Companion;", "", "()V", "INMATE", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/home/InmateDetailFragment;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: InmateDetailFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InmateDetailFragment newInstance(Inmate inmate) {
            Intrinsics.checkParameterIsNotNull(inmate, InmateDetailFragment.INMATE);
            InmateDetailFragment inmateDetailFragment = new InmateDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(InmateDetailFragment.INMATE, inmate);
            inmateDetailFragment.setArguments(bundle);
            return inmateDetailFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new InmateDetailFragment$installModules$1(this));
    }

    public final InmateDetailPresenter getPresenter() {
        InmateDetailPresenter inmateDetailPresenter = this.presenter;
        if (inmateDetailPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return inmateDetailPresenter;
    }

    public final void setPresenter(InmateDetailPresenter inmateDetailPresenter) {
        Intrinsics.checkParameterIsNotNull(inmateDetailPresenter, "<set-?>");
        this.presenter = inmateDetailPresenter;
    }

    @ProvidePresenter
    public final InmateDetailPresenter providePresenter() {
        Object instance = getScope().getInstance(InmateDetailPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Inmate…ailPresenter::class.java)");
        return (InmateDetailPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_inmate_detail, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        initTernaryViews();
        InmateDetailPresenter inmateDetailPresenter = this.presenter;
        if (inmateDetailPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        inmateDetailPresenter.getNotificationsCount();
    }

    private final void initTernaryViews() {
        TernaryView ternaryView = (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMoney);
        ternaryView.setCaption("?");
        ternaryView.setImageId(R.drawable.icon_dollar_round);
        ternaryView.setHighlighted(true);
        TernaryView ternaryView2 = (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryVisits);
        ternaryView2.setImageId(R.drawable.icon_event);
        ternaryView2.setText("Loading...");
        ternaryView2.setCaption("? Visits");
        ternaryView2.setHighlighted(true);
    }

    public void onResume() {
        super.onResume();
        ContextKt.showActionBack(this);
        InmateDetailPresenter inmateDetailPresenter = this.presenter;
        if (inmateDetailPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        inmateDetailPresenter.refresh();
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkAllowCalls);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkAllowCalls");
        checkBox.setOnCheckedChangeListener(new InmateDetailFragment$inlined$sam$i$android_widget_CompoundButton_OnCheckedChangeListener$0(new InmateDetailFragment$onResume$1(this)));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonMessage);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonMessage");
        button.setOnClickListener(new InmateDetailFragment$inlined$sam$i$android_view_View_OnClickListener$0(new InmateDetailFragment$onResume$2(this)));
    }

    public void onPause() {
        super.onPause();
        ContextKt.hideActionBack(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_inmate_details, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            InmateDetailPresenter inmateDetailPresenter = this.presenter;
            if (inmateDetailPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            return inmateDetailPresenter.onBackPressed();
        } else if (itemId == R.id.action_delete_inmate) {
            InmateDetailPresenter inmateDetailPresenter2 = this.presenter;
            if (inmateDetailPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            inmateDetailPresenter2.onInmateDeleteClicked();
            return true;
        } else if (itemId != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            InmateDetailPresenter inmateDetailPresenter3 = this.presenter;
            if (inmateDetailPresenter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            inmateDetailPresenter3.onNotificationsClicked();
            return true;
        }
    }

    public void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textName");
        textView.setText(str);
        ContextKt.setTitle((Fragment) this, str);
        setAvatar(str);
    }

    private final void setAvatar(String str) {
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(requireContext, StringExtensionsKt.getAsInitials(str), 0, 0, 0, 14, (Object) null));
    }

    public void setCredits(String str) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ENGLISH");
        String format = String.format(locale, "%.2f", Arrays.copyOf(new Object[]{Double.valueOf(Double.parseDouble(str))}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMoney)).setCaption(format);
    }

    public void setId(String str) {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textId);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textId");
        textView.setText(str);
    }

    public void setInvisible(boolean z) {
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkAllowCalls);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkAllowCalls");
        checkBox.setChecked(z);
    }

    public void setMoney(String str) {
        Intrinsics.checkParameterIsNotNull(str, "credit_balance");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ENGLISH");
        String format = String.format(locale, "%.2f", Arrays.copyOf(new Object[]{Double.valueOf(Double.parseDouble(str))}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMoney)).setCaption(format);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStatus(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "status"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            int r0 = r3.hashCode()
            r1 = 112785(0x1b891, float:1.58045E-40)
            if (r0 == r1) goto L_0x001e
            r1 = 98619139(0x5e0cf03, float:2.1140903E-35)
            if (r0 == r1) goto L_0x0014
            goto L_0x0028
        L_0x0014:
            java.lang.String r0 = "green"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0028
            r3 = 2
            goto L_0x0029
        L_0x001e:
            java.lang.String r0 = "red"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0028
            r3 = 1
            goto L_0x0029
        L_0x0028:
            r3 = 0
        L_0x0029:
            int r0 = com.forasoft.homewavvisitor.R.id.iv_status
            android.view.View r0 = r2._$_findCachedViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            java.lang.String r1 = "iv_status"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            android.graphics.drawable.Drawable r0 = r0.getBackground()
            if (r0 == 0) goto L_0x0042
            android.graphics.drawable.LevelListDrawable r0 = (android.graphics.drawable.LevelListDrawable) r0
            r0.setLevel(r3)
            return
        L_0x0042:
            kotlin.TypeCastException r3 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.home.InmateDetailFragment.setStatus(java.lang.String):void");
    }

    public void showVisitsNumber(String str) {
        Intrinsics.checkParameterIsNotNull(str, "visits");
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryVisits)).setCaption(str);
    }

    public void showVisitsText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryVisits)).setText(str);
    }

    public void showDeleteDialog(String str) {
        new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme).setTitle(getString(R.string.dialog_delete_inmate_title)).setMessage(getString(R.string.dialog_delete_inmate_message, str)).setPositiveButton(getString(R.string.dialog_delete_button), new InmateDetailFragment$showDeleteDialog$1(this)).setNegativeButton(getString(R.string.dialog_cancel_button), InmateDetailFragment$showDeleteDialog$2.INSTANCE).show();
    }

    public void setCheckActive() {
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkAllowCalls);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkAllowCalls");
        checkBox.setEnabled(true);
    }

    public void setCheckInactive() {
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkAllowCalls);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkAllowCalls");
        checkBox.setEnabled(false);
    }

    public boolean onBackPressed() {
        InmateDetailPresenter inmateDetailPresenter = this.presenter;
        if (inmateDetailPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return inmateDetailPresenter.onBackPressed();
    }

    public boolean onBottomNavigationClicked(int i) {
        if (i != R.id.action_home) {
            return false;
        }
        InmateDetailPresenter inmateDetailPresenter = this.presenter;
        if (inmateDetailPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return inmateDetailPresenter.onBackPressed();
    }
}
