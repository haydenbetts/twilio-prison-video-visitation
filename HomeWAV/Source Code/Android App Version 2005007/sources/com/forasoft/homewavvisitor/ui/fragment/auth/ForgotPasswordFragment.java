package com.forasoft.homewavvisitor.ui.fragment.auth;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.auth.ForgotPasswordPresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.ForgotPasswordView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0007R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/auth/ForgotPasswordFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/ForgotPasswordView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/auth/ForgotPasswordPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/auth/ForgotPasswordPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/auth/ForgotPasswordPresenter;)V", "disableReset", "", "disable", "", "onBackPressed", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "providePresenter", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ForgotPasswordFragment.kt */
public final class ForgotPasswordFragment extends BaseFragment implements ForgotPasswordView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public ForgotPasswordPresenter presenter;

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

    public final ForgotPasswordPresenter getPresenter() {
        ForgotPasswordPresenter forgotPasswordPresenter = this.presenter;
        if (forgotPasswordPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return forgotPasswordPresenter;
    }

    public final void setPresenter(ForgotPasswordPresenter forgotPasswordPresenter) {
        Intrinsics.checkParameterIsNotNull(forgotPasswordPresenter, "<set-?>");
        this.presenter = forgotPasswordPresenter;
    }

    @ProvidePresenter
    public final ForgotPasswordPresenter providePresenter() {
        Object instance = getScope().getInstance(ForgotPasswordPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Forgot…ordPresenter::class.java)");
        return (ForgotPasswordPresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_forgot_password, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonBack);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "buttonBack");
        imageView.setOnClickListener(new ForgotPasswordFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ForgotPasswordFragment$onResume$1(this)));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSubmit);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonSubmit");
        button.setOnClickListener(new ForgotPasswordFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ForgotPasswordFragment$onResume$2(this)));
    }

    public void disableReset(boolean z) {
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSubmit);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonSubmit");
        button.setEnabled(!z);
    }

    public boolean onBackPressed() {
        ForgotPasswordPresenter forgotPasswordPresenter = this.presenter;
        if (forgotPasswordPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return forgotPasswordPresenter.onBackPressed();
    }
}
