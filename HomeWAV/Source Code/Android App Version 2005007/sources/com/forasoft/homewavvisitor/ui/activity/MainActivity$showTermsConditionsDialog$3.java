package com.forasoft.homewavvisitor.ui.activity;

import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
final class MainActivity$showTermsConditionsDialog$3 implements DialogInterface.OnClickListener {
    final /* synthetic */ MainActivity this$0;

    MainActivity$showTermsConditionsDialog$3(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Intrinsics.checkParameterIsNotNull(dialogInterface, "<anonymous parameter 0>");
        this.this$0.finish();
    }
}
