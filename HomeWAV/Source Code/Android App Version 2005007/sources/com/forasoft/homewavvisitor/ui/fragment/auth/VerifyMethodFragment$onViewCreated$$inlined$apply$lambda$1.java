package com.forasoft.homewavvisitor.ui.fragment.auth;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/MenuItem;", "kotlin.jvm.PlatformType", "onMenuItemClick", "com/forasoft/homewavvisitor/ui/fragment/auth/VerifyMethodFragment$onViewCreated$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodFragment.kt */
final class VerifyMethodFragment$onViewCreated$$inlined$apply$lambda$1 implements Toolbar.OnMenuItemClickListener {
    final /* synthetic */ VerifyMethodFragment this$0;

    VerifyMethodFragment$onViewCreated$$inlined$apply$lambda$1(VerifyMethodFragment verifyMethodFragment) {
        this.this$0 = verifyMethodFragment;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        this.this$0.getPresenter().onCloseClicked();
        return true;
    }
}
