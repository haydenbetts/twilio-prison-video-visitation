package com.forasoft.homewavvisitor.ui.fragment.auth;

import air.HomeWAV.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.auth.VerifyCodePresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyCodeView;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.Sdk27PropertiesKt;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 02\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u00010B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J*\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\rH\u0016J*\u0010%\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u001a\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010)\u001a\u00020\u0007H\u0007J\u0010\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u0014H\u0016J\u0010\u0010,\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u0014H\u0016J\u0010\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020/H\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u00061"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/auth/VerifyCodeFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/VerifyCodeView;", "Landroid/text/TextWatcher;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyCodePresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyCodePresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyCodePresenter;)V", "afterTextChanged", "", "editable", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onTextChanged", "before", "onViewCreated", "view", "providePresenter", "showErrorMessage", "resId", "showSuccessMessage", "updateHint", "message", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VerifyCodeFragment.kt */
public final class VerifyCodeFragment extends BaseFragment implements VerifyCodeView, TextWatcher, OnBackButtonPressedListener {
    private static final String CHANNEL = "inmate id";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    @InjectPresenter
    public VerifyCodePresenter presenter;

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

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/auth/VerifyCodeFragment$Companion;", "", "()V", "CHANNEL", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/auth/VerifyCodeFragment;", "channel", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: VerifyCodeFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VerifyCodeFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, Modules.CHANNEL_MODULE);
            VerifyCodeFragment verifyCodeFragment = new VerifyCodeFragment();
            Bundle bundle = new Bundle();
            bundle.putString(VerifyCodeFragment.CHANNEL, str);
            verifyCodeFragment.setArguments(bundle);
            return verifyCodeFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new VerifyCodeFragment$installModules$1(this));
    }

    public final VerifyCodePresenter getPresenter() {
        VerifyCodePresenter verifyCodePresenter = this.presenter;
        if (verifyCodePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return verifyCodePresenter;
    }

    public final void setPresenter(VerifyCodePresenter verifyCodePresenter) {
        Intrinsics.checkParameterIsNotNull(verifyCodePresenter, "<set-?>");
        this.presenter = verifyCodePresenter;
    }

    @ProvidePresenter
    public final VerifyCodePresenter providePresenter() {
        Object instance = getScope().getInstance(VerifyCodePresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(VerifyCodePresenter::class.java)");
        return (VerifyCodePresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_code_verify, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toolbar);
        toolbar.setTitle((CharSequence) "Verify Account");
        MenuItem add = toolbar.getMenu().add(R.string.label_close);
        add.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_close));
        add.setShowAsAction(2);
        toolbar.setOnMenuItemClickListener(new VerifyCodeFragment$onViewCreated$$inlined$apply$lambda$1(this));
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_code)).addTextChangedListener(this);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_renew);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_renew");
        textView.setOnClickListener(new VerifyCodeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VerifyCodeFragment$onViewCreated$2(this)));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_submit);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_submit");
        button.setOnClickListener(new VerifyCodeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VerifyCodeFragment$onViewCreated$3(this)));
        if (getActivity() instanceof CreateAccountActivity) {
            CommonKt.hide(requireActivity().findViewById(R.id.stepper));
            CommonKt.hide(requireActivity().findViewById(R.id.toolbar));
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof CreateAccountActivity) {
            CommonKt.show(requireActivity().findViewById(R.id.stepper));
            CommonKt.show(requireActivity().findViewById(R.id.toolbar));
        }
        _$_clearFindViewByIdCache();
    }

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, "editable");
        if (editable.toString().length() > 0) {
            CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_submit));
        } else {
            CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_submit));
        }
    }

    public boolean onBackPressed() {
        VerifyCodePresenter verifyCodePresenter = this.presenter;
        if (verifyCodePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        verifyCodePresenter.onBackPressed();
        return true;
    }

    public void showSuccessMessage(int i) {
        String string = getResources().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(resId)");
        View requireView = requireView();
        Intrinsics.checkExpressionValueIsNotNull(requireView, "requireView()");
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        ContextKt.showGreenSnackbar(string, requireView, resources);
    }

    public void showErrorMessage(int i) {
        String string = getResources().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(resId)");
        View requireView = requireView();
        Intrinsics.checkExpressionValueIsNotNull(requireView, "requireView()");
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        ContextKt.showRedSnackbar(string, requireView, resources);
    }

    public void updateHint(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_hint);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_hint");
        textView.setText(getResources().getString(R.string.verify_error));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_hint);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_hint");
        Sdk27PropertiesKt.setTextColor(textView2, ContextCompat.getColor(requireContext(), R.color.errorRed));
    }
}
