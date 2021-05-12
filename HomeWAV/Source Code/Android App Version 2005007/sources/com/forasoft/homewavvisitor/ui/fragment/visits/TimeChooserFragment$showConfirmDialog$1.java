package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/afollestad/materialdialogs/MaterialDialog;", "<anonymous parameter 1>", "Lcom/afollestad/materialdialogs/DialogAction;", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: TimeChooserFragment.kt */
final class TimeChooserFragment$showConfirmDialog$1 implements MaterialDialog.SingleButtonCallback {
    final /* synthetic */ TimeChooserFragment this$0;

    TimeChooserFragment$showConfirmDialog$1(TimeChooserFragment timeChooserFragment) {
        this.this$0 = timeChooserFragment;
    }

    public final void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(dialogAction, "<anonymous parameter 1>");
        this.this$0.getPresenter().onInviteConfirmed();
    }
}
