package com.forasoft.homewavvisitor.ui.fragment.account;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.forasoft.homewavvisitor.model.RxImagePicker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/account/EditPhotosFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EditPhotosFragment.kt */
public final class EditPhotosFragment$installModules$1 extends Module {
    final /* synthetic */ EditPhotosFragment this$0;

    EditPhotosFragment$installModules$1(EditPhotosFragment editPhotosFragment) {
        this.this$0 = editPhotosFragment;
        Binding<T>.CanBeNamed bind = bind(RxImagePicker.class);
        RxImagePicker.Companion companion = RxImagePicker.Companion;
        FragmentActivity requireActivity = editPhotosFragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        FragmentManager supportFragmentManager = requireActivity.getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "requireActivity().supportFragmentManager");
        bind.toInstance(companion.with(supportFragmentManager));
    }
}
