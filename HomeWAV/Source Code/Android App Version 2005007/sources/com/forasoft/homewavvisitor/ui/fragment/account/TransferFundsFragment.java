package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.register.ProgressDialog;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J*\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J&\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010!\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\u000fH\u0016J*\u0010,\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u001a\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020$2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u00100\u001a\u00020\u0007H\u0007J\u0010\u00101\u001a\u00020\u000f2\u0006\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u0015H\u0016J\b\u00106\u001a\u00020\u000fH\u0016J\u0010\u00107\u001a\u00020\u000f2\u0006\u00102\u001a\u000203H\u0016J\b\u00108\u001a\u00020\u000fH\u0002J\u0012\u00109\u001a\u00020\u000f2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/TransferFundsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/TransferFundsView;", "Landroid/text/TextWatcher;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/TransferFundsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/TransferFundsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/TransferFundsPresenter;)V", "progressDialog", "Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "hideProgressDialog", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onTextChanged", "before", "onViewCreated", "view", "providePresenter", "showFromInmate", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showMessage", "resId", "showProgressDialog", "showToInmate", "showTransferButtonIfNecessary", "showTransferFee", "transferFee", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TransferFundsFragment.kt */
public final class TransferFundsFragment extends BaseFragment implements TransferFundsView, TextWatcher, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public TransferFundsPresenter presenter;
    private ProgressDialog progressDialog;

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

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final TransferFundsPresenter getPresenter() {
        TransferFundsPresenter transferFundsPresenter = this.presenter;
        if (transferFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return transferFundsPresenter;
    }

    public final void setPresenter(TransferFundsPresenter transferFundsPresenter) {
        Intrinsics.checkParameterIsNotNull(transferFundsPresenter, "<set-?>");
        this.presenter = transferFundsPresenter;
    }

    @ProvidePresenter
    public final TransferFundsPresenter providePresenter() {
        Object instance = getScope().getInstance(TransferFundsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Transf…ndsPresenter::class.java)");
        return (TransferFundsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_transfer_funds, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((MainActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) getResources().getString(R.string.label_transfer_funds));
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                CommonKt.hide((BottomNavigationViewEx) ((MainActivity) activity2)._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
                TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_link_from);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tv_link_from");
                textView.setOnClickListener(new TransferFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TransferFundsFragment$onViewCreated$1(this)));
                TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_link_to);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_link_to");
                textView2.setOnClickListener(new TransferFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TransferFundsFragment$onViewCreated$2(this)));
                ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmate_from_container);
                Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "inmate_from_container");
                constraintLayout.setOnClickListener(new TransferFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TransferFundsFragment$onViewCreated$3(this)));
                ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmate_to_container);
                Intrinsics.checkExpressionValueIsNotNull(constraintLayout2, "inmate_to_container");
                constraintLayout2.setOnClickListener(new TransferFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TransferFundsFragment$onViewCreated$4(this)));
                Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_transfer);
                Intrinsics.checkExpressionValueIsNotNull(button, "btn_transfer");
                button.setOnClickListener(new TransferFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TransferFundsFragment$onViewCreated$5(this)));
                ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_amount)).addTextChangedListener(this);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public void onPause() {
        super.onPause();
        ContextKt.hideKeyboard((Fragment) this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        MenuItem add = menu.add(R.string.label_close);
        add.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_close));
        add.setShowAsAction(2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        return onBackPressed();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showFromInmate(com.forasoft.homewavvisitor.model.data.Inmate r9) {
        /*
            r8 = this;
            java.lang.String r0 = "inmate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            int r0 = com.forasoft.homewavvisitor.R.id.tv_link_from
            android.view.View r0 = r8._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tv_link_from"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r1 = 4
            r0.setVisibility(r1)
            java.lang.String r0 = r9.getFull_name()
            if (r0 != 0) goto L_0x0020
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0020:
            java.lang.String r2 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r0)
            android.content.Context r1 = r8.requireContext()
            java.lang.String r0 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 14
            r7 = 0
            com.amulyakhare.textdrawable.TextDrawable r0 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r1, r2, r3, r4, r5, r6, r7)
            int r1 = com.forasoft.homewavvisitor.R.id.iv_avatar_from
            android.view.View r1 = r8._$_findCachedViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            r1.setImageDrawable(r0)
            int r0 = com.forasoft.homewavvisitor.R.id.tv_full_name_from
            android.view.View r0 = r8._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tv_full_name_from"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r1 = r9.getFull_name()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            java.lang.String r0 = r9.getStatus()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0064
            goto L_0x0087
        L_0x0064:
            int r3 = r0.hashCode()
            r4 = 112785(0x1b891, float:1.58045E-40)
            if (r3 == r4) goto L_0x007d
            r4 = 98619139(0x5e0cf03, float:2.1140903E-35)
            if (r3 == r4) goto L_0x0073
            goto L_0x0087
        L_0x0073:
            java.lang.String r3 = "green"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0087
            r0 = 2
            goto L_0x0088
        L_0x007d:
            java.lang.String r3 = "red"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0087
            r0 = 1
            goto L_0x0088
        L_0x0087:
            r0 = 0
        L_0x0088:
            int r3 = com.forasoft.homewavvisitor.R.id.tv_balance_from
            android.view.View r3 = r8._$_findCachedViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tv_balance_from"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.util.Locale r4 = java.util.Locale.ENGLISH
            java.lang.String r5 = "Locale.ENGLISH"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r9 = r9.getCredit_balance()
            if (r9 != 0) goto L_0x00aa
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00aa:
            double r6 = java.lang.Double.parseDouble(r9)
            java.lang.Double r9 = java.lang.Double.valueOf(r6)
            r5[r2] = r9
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r5, r1)
            java.lang.String r1 = "%.2f"
            java.lang.String r9 = java.lang.String.format(r4, r1, r9)
            java.lang.String r1 = "java.lang.String.format(locale, format, *args)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            r3.setText(r9)
            int r9 = com.forasoft.homewavvisitor.R.id.inmate_from_container
            android.view.View r9 = r8._$_findCachedViewById(r9)
            androidx.constraintlayout.widget.ConstraintLayout r9 = (androidx.constraintlayout.widget.ConstraintLayout) r9
            r1 = 2131296678(0x7f0901a6, float:1.821128E38)
            android.view.View r9 = r9.findViewById(r1)
            java.lang.String r1 = "inmate_from_container.fi…mageView>(R.id.iv_status)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            android.graphics.drawable.Drawable r9 = r9.getBackground()
            if (r9 == 0) goto L_0x00f8
            android.graphics.drawable.LevelListDrawable r9 = (android.graphics.drawable.LevelListDrawable) r9
            r9.setLevel(r0)
            int r9 = com.forasoft.homewavvisitor.R.id.inmate_from_container
            android.view.View r9 = r8._$_findCachedViewById(r9)
            androidx.constraintlayout.widget.ConstraintLayout r9 = (androidx.constraintlayout.widget.ConstraintLayout) r9
            com.forasoft.homewavvisitor.extension.CommonKt.show(r9)
            r8.showTransferButtonIfNecessary()
            return
        L_0x00f8:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.account.TransferFundsFragment.showFromInmate(com.forasoft.homewavvisitor.model.data.Inmate):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showToInmate(com.forasoft.homewavvisitor.model.data.Inmate r9) {
        /*
            r8 = this;
            java.lang.String r0 = "inmate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            int r0 = com.forasoft.homewavvisitor.R.id.tv_link_to
            android.view.View r0 = r8._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tv_link_to"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r1 = 4
            r0.setVisibility(r1)
            java.lang.String r0 = r9.getFull_name()
            if (r0 != 0) goto L_0x0020
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0020:
            java.lang.String r2 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r0)
            android.content.Context r1 = r8.requireContext()
            java.lang.String r0 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r0)
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 14
            r7 = 0
            com.amulyakhare.textdrawable.TextDrawable r0 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r1, r2, r3, r4, r5, r6, r7)
            int r1 = com.forasoft.homewavvisitor.R.id.iv_avatar_to
            android.view.View r1 = r8._$_findCachedViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            r1.setImageDrawable(r0)
            int r0 = com.forasoft.homewavvisitor.R.id.tv_full_name_to
            android.view.View r0 = r8._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tv_full_name_to"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r1 = r9.getFull_name()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            java.lang.String r0 = r9.getStatus()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0064
            goto L_0x0087
        L_0x0064:
            int r3 = r0.hashCode()
            r4 = 112785(0x1b891, float:1.58045E-40)
            if (r3 == r4) goto L_0x007d
            r4 = 98619139(0x5e0cf03, float:2.1140903E-35)
            if (r3 == r4) goto L_0x0073
            goto L_0x0087
        L_0x0073:
            java.lang.String r3 = "green"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0087
            r0 = 2
            goto L_0x0088
        L_0x007d:
            java.lang.String r3 = "red"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0087
            r0 = 1
            goto L_0x0088
        L_0x0087:
            r0 = 0
        L_0x0088:
            int r3 = com.forasoft.homewavvisitor.R.id.tv_balance_to
            android.view.View r3 = r8._$_findCachedViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tv_balance_to"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.util.Locale r4 = java.util.Locale.ENGLISH
            java.lang.String r5 = "Locale.ENGLISH"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r9 = r9.getCredit_balance()
            if (r9 != 0) goto L_0x00aa
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00aa:
            double r6 = java.lang.Double.parseDouble(r9)
            java.lang.Double r9 = java.lang.Double.valueOf(r6)
            r5[r2] = r9
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r5, r1)
            java.lang.String r1 = "%.2f"
            java.lang.String r9 = java.lang.String.format(r4, r1, r9)
            java.lang.String r1 = "java.lang.String.format(locale, format, *args)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            r3.setText(r9)
            int r9 = com.forasoft.homewavvisitor.R.id.inmate_to_container
            android.view.View r9 = r8._$_findCachedViewById(r9)
            androidx.constraintlayout.widget.ConstraintLayout r9 = (androidx.constraintlayout.widget.ConstraintLayout) r9
            r1 = 2131296678(0x7f0901a6, float:1.821128E38)
            android.view.View r9 = r9.findViewById(r1)
            java.lang.String r1 = "inmate_to_container.find…mageView>(R.id.iv_status)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            android.graphics.drawable.Drawable r9 = r9.getBackground()
            if (r9 == 0) goto L_0x00f8
            android.graphics.drawable.LevelListDrawable r9 = (android.graphics.drawable.LevelListDrawable) r9
            r9.setLevel(r0)
            int r9 = com.forasoft.homewavvisitor.R.id.inmate_to_container
            android.view.View r9 = r8._$_findCachedViewById(r9)
            androidx.constraintlayout.widget.ConstraintLayout r9 = (androidx.constraintlayout.widget.ConstraintLayout) r9
            com.forasoft.homewavvisitor.extension.CommonKt.show(r9)
            r8.showTransferButtonIfNecessary()
            return
        L_0x00f8:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.account.TransferFundsFragment.showToInmate(com.forasoft.homewavvisitor.model.data.Inmate):void");
    }

    public void showTransferFee(String str) {
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.label_handling_fee));
        if (str == null) {
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.label_handling_fee);
            Intrinsics.checkExpressionValueIsNotNull(textView, "label_handling_fee");
            textView.setText(getResources().getString(R.string.transfer_handling_fee, new Object[]{"0.00"}));
            return;
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.label_handling_fee);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "label_handling_fee");
        textView2.setText(getResources().getString(R.string.transfer_handling_fee, new Object[]{str}));
    }

    public void hideProgressDialog() {
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.dismiss();
        }
        this.progressDialog = null;
    }

    public void showProgressDialog() {
        String string = getResources().getString(R.string.title_transferring_funds);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…title_transferring_funds)");
        ProgressDialog progressDialog2 = new ProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ProgressDialog.EXTRA_TITLE, string);
        progressDialog2.setArguments(bundle);
        this.progressDialog = progressDialog2;
        if (progressDialog2 == null) {
            Intrinsics.throwNpe();
        }
        progressDialog2.show(getChildFragmentManager(), (String) null);
    }

    public void showMessage(int i) {
        ContextKt.showSnackbar((Fragment) this, getResources().getString(i));
    }

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, "s");
        showTransferButtonIfNecessary();
    }

    public boolean onBackPressed() {
        TransferFundsPresenter transferFundsPresenter = this.presenter;
        if (transferFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        transferFundsPresenter.onBackPressed();
        return true;
    }

    private final void showTransferButtonIfNecessary() {
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_amount);
        Intrinsics.checkExpressionValueIsNotNull(editText, "et_amount");
        Editable text = editText.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "et_amount.text");
        if (text.length() > 0) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmate_from_container);
            Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "inmate_from_container");
            if (CommonKt.isVisible(constraintLayout)) {
                ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmate_to_container);
                Intrinsics.checkExpressionValueIsNotNull(constraintLayout2, "inmate_to_container");
                if (CommonKt.isVisible(constraintLayout2)) {
                    CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_transfer));
                    return;
                }
            }
        }
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_transfer));
    }
}
