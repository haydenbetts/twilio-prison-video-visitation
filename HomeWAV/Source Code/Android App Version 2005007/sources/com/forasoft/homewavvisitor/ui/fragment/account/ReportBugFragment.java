package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.ReportBugPresenter;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0007R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/ReportBugFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/ReportBugPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/ReportBugPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/ReportBugPresenter;)V", "getSelectedProblems", "", "onBackPressed", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "providePresenter", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ReportBugFragment.kt */
public final class ReportBugFragment extends BaseFragment implements BaseView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public ReportBugPresenter presenter;

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

    public final ReportBugPresenter getPresenter() {
        ReportBugPresenter reportBugPresenter = this.presenter;
        if (reportBugPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return reportBugPresenter;
    }

    public final void setPresenter(ReportBugPresenter reportBugPresenter) {
        Intrinsics.checkParameterIsNotNull(reportBugPresenter, "<set-?>");
        this.presenter = reportBugPresenter;
    }

    @ProvidePresenter
    public final ReportBugPresenter providePresenter() {
        Object instance = getScope().getInstance(ReportBugPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(ReportBugPresenter::class.java)");
        return (ReportBugPresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_report_bug, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        FragmentActivity requireActivity = requireActivity();
        Toolbar toolbar = (Toolbar) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.toolbar);
        Intrinsics.checkExpressionValueIsNotNull(toolbar, "toolbar");
        toolbar.setTitle((CharSequence) requireActivity.getResources().getString(R.string.label_report_a_bug));
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_report);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_report");
        button.setOnClickListener(new ReportBugFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ReportBugFragment$onViewCreated$2(this)));
        CompoundButton.OnCheckedChangeListener reportBugFragment$onViewCreated$onCheckedChangeListener$1 = new ReportBugFragment$onViewCreated$onCheckedChangeListener$1(this);
        ((CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_mic)).setOnCheckedChangeListener(reportBugFragment$onViewCreated$onCheckedChangeListener$1);
        ((CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_speaker)).setOnCheckedChangeListener(reportBugFragment$onViewCreated$onCheckedChangeListener$1);
        ((CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_connectivity)).setOnCheckedChangeListener(reportBugFragment$onViewCreated$onCheckedChangeListener$1);
        ((CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_video)).setOnCheckedChangeListener(reportBugFragment$onViewCreated$onCheckedChangeListener$1);
    }

    public void onDestroyView() {
        super.onDestroyView();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        _$_clearFindViewByIdCache();
    }

    public boolean onBackPressed() {
        ReportBugPresenter reportBugPresenter = this.presenter;
        if (reportBugPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        reportBugPresenter.onBackPressed();
        return true;
    }

    /* access modifiers changed from: private */
    public final String getSelectedProblems() {
        StringBuilder sb = new StringBuilder();
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_mic);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "cb_mic");
        if (checkBox.isChecked()) {
            sb.append("m");
        }
        CheckBox checkBox2 = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_speaker);
        Intrinsics.checkExpressionValueIsNotNull(checkBox2, "cb_speaker");
        if (checkBox2.isChecked()) {
            sb.append("s");
        }
        CheckBox checkBox3 = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_connectivity);
        Intrinsics.checkExpressionValueIsNotNull(checkBox3, "cb_connectivity");
        if (checkBox3.isChecked()) {
            sb.append("c");
        }
        CheckBox checkBox4 = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cb_video);
        Intrinsics.checkExpressionValueIsNotNull(checkBox4, "cb_video");
        if (checkBox4.isChecked()) {
            sb.append("v");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
