package com.forasoft.homewavvisitor.ui.fragment.register;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.forasoft.homewavvisitor.model.RxImagePicker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/register/SignUp1Fragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUp1Fragment.kt */
public final class SignUp1Fragment$installModules$1 extends Module {
    final /* synthetic */ SignUp1Fragment this$0;

    SignUp1Fragment$installModules$1(SignUp1Fragment signUp1Fragment) {
        this.this$0 = signUp1Fragment;
        Binding<T>.CanBeNamed bind = bind(RxImagePicker.class);
        RxImagePicker.Companion companion = RxImagePicker.Companion;
        FragmentActivity requireActivity = signUp1Fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        FragmentManager supportFragmentManager = requireActivity.getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "requireActivity().supportFragmentManager");
        bind.toInstance(companion.with(supportFragmentManager));
    }
}
