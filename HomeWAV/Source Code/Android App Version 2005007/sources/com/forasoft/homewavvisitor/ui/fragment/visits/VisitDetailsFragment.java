package com.forasoft.homewavvisitor.ui.fragment.visits;

import air.HomeWAV.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.afollestad.materialdialogs.MaterialDialog;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.visits.VisitDetailsPresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitDetailsView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import java.util.Date;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 '2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001'B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\"\u001a\u00020\u0006H\u0007J\b\u0010#\u001a\u00020\fH\u0016J\u0012\u0010$\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006("}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitDetailsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/VisitDetailsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitDetailsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitDetailsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitDetailsPresenter;)V", "displayVisit", "", "visit", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "showConfirmDialog", "updateInmateStatus", "status", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitDetailsFragment.kt */
public final class VisitDetailsFragment extends BaseFragment implements VisitDetailsView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String VISIT_ID = "visit id";
    private HashMap _$_findViewCache;
    @InjectPresenter
    public VisitDetailsPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitDetailsFragment$Companion;", "", "()V", "VISIT_ID", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitDetailsFragment;", "visitId", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: VisitDetailsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VisitDetailsFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "visitId");
            VisitDetailsFragment visitDetailsFragment = new VisitDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(VisitDetailsFragment.VISIT_ID, str);
            visitDetailsFragment.setArguments(bundle);
            return visitDetailsFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new VisitDetailsFragment$installModules$1(this));
    }

    public final VisitDetailsPresenter getPresenter() {
        VisitDetailsPresenter visitDetailsPresenter = this.presenter;
        if (visitDetailsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return visitDetailsPresenter;
    }

    public final void setPresenter(VisitDetailsPresenter visitDetailsPresenter) {
        Intrinsics.checkParameterIsNotNull(visitDetailsPresenter, "<set-?>");
        this.presenter = visitDetailsPresenter;
    }

    @ProvidePresenter
    public final VisitDetailsPresenter providePresenter() {
        Object instance = getScope().getInstance(VisitDetailsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(VisitD…ilsPresenter::class.java)");
        return (VisitDetailsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_visit_details, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_cancel);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_cancel");
        button.setOnClickListener(new VisitDetailsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VisitDetailsFragment$onViewCreated$1(this)));
        Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_add);
        Intrinsics.checkExpressionValueIsNotNull(button2, "btn_add");
        button2.setOnClickListener(new VisitDetailsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VisitDetailsFragment$onViewCreated$2(this)));
        VisitDetailsPresenter visitDetailsPresenter = this.presenter;
        if (visitDetailsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        visitDetailsPresenter.getNotificationsCount();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            VisitDetailsPresenter visitDetailsPresenter = this.presenter;
            if (visitDetailsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            visitDetailsPresenter.onBackPressed();
            return true;
        } else if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            VisitDetailsPresenter visitDetailsPresenter2 = this.presenter;
            if (visitDetailsPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            visitDetailsPresenter2.onNotificationsClicked();
            return true;
        }
    }

    public void displayVisit(ScheduledVisit scheduledVisit) {
        CharSequence charSequence;
        Intrinsics.checkParameterIsNotNull(scheduledVisit, "visit");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                if (Intrinsics.areEqual((Object) scheduledVisit.getStatus(), (Object) "requested")) {
                    charSequence = appCompatActivity.getResources().getString(R.string.label_request_pending);
                } else {
                    charSequence = appCompatActivity.getResources().getString(R.string.label_request_scheduled);
                }
                supportActionBar.setTitle(charSequence);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_full_name);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_full_name");
            textView.setText(scheduledVisit.getInmate());
            String asInitials = StringExtensionsKt.getAsInitials(scheduledVisit.getInmate());
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_avatar)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(context, asInitials, 0, 0, 0, 14, (Object) null));
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_full_name);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_full_name");
            textView2.setText(scheduledVisit.getInmate());
            TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_place);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_place");
            textView3.setText(getResources().getString(R.string.hint_place, new Object[]{scheduledVisit.getStation(), scheduledVisit.getFacility()}));
            TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_date);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tv_date");
            textView4.setText(DateExtensionsKt.getAsDetailedDateTime(new Date(scheduledVisit.getTimestamp() * ((long) 1000))));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateInmateStatus(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            goto L_0x0026
        L_0x0003:
            int r0 = r3.hashCode()
            r1 = 112785(0x1b891, float:1.58045E-40)
            if (r0 == r1) goto L_0x001c
            r1 = 98619139(0x5e0cf03, float:2.1140903E-35)
            if (r0 == r1) goto L_0x0012
            goto L_0x0026
        L_0x0012:
            java.lang.String r0 = "green"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0026
            r3 = 2
            goto L_0x0027
        L_0x001c:
            java.lang.String r0 = "red"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0026
            r3 = 1
            goto L_0x0027
        L_0x0026:
            r3 = 0
        L_0x0027:
            int r0 = com.forasoft.homewavvisitor.R.id.iv_status
            android.view.View r0 = r2._$_findCachedViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            java.lang.String r1 = "iv_status"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            android.graphics.drawable.Drawable r0 = r0.getBackground()
            if (r0 == 0) goto L_0x0040
            android.graphics.drawable.LevelListDrawable r0 = (android.graphics.drawable.LevelListDrawable) r0
            r0.setLevel(r3)
            return
        L_0x0040:
            kotlin.TypeCastException r3 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.visits.VisitDetailsFragment.updateInmateStatus(java.lang.String):void");
    }

    public void showConfirmDialog() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        new MaterialDialog.Builder(context).title((int) R.string.label_cancel_visit_title).content((int) R.string.label_cancel_visit_body).positiveText((int) R.string.label_cancel_visit).negativeText((int) R.string.label_keep_visit).cancelable(false).onPositive(new VisitDetailsFragment$showConfirmDialog$1(this)).build().show();
    }

    public boolean onBackPressed() {
        VisitDetailsPresenter visitDetailsPresenter = this.presenter;
        if (visitDetailsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        visitDetailsPresenter.onBackPressed();
        return true;
    }
}
