package com.forasoft.homewavvisitor.presentation.adapter.tutorials;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/forasoft/homewavvisitor/presentation/adapter/tutorials/TutorialVH$bind$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: TutorialVH.kt */
final class TutorialVH$bind$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ String $url$inlined;
    final /* synthetic */ TutorialVH this$0;

    TutorialVH$bind$$inlined$with$lambda$1(TutorialVH tutorialVH, String str) {
        this.this$0 = tutorialVH;
        this.$url$inlined = str;
    }

    public final void onClick(View view) {
        this.this$0.itemClickListener.invoke(this.$url$inlined);
    }
}
