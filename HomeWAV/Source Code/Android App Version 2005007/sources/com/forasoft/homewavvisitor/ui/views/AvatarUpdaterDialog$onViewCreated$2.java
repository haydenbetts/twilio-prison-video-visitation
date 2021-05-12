package com.forasoft.homewavvisitor.ui.views;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: AvatarUpdaterDialog.kt */
final class AvatarUpdaterDialog$onViewCreated$2 implements View.OnClickListener {
    final /* synthetic */ AvatarUpdaterDialog this$0;

    AvatarUpdaterDialog$onViewCreated$2(AvatarUpdaterDialog avatarUpdaterDialog) {
        this.this$0 = avatarUpdaterDialog;
    }

    public final void onClick(View view) {
        AvatarUpdaterDialog.access$getReceiveResult$p(this.this$0).invoke(AvatarUpdaterDialog.CAMERA);
        this.this$0.dismiss();
    }
}
