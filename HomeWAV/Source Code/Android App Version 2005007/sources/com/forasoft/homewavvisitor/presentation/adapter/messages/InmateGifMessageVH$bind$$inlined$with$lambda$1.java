package com.forasoft.homewavvisitor.presentation.adapter.messages;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.Message;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/forasoft/homewavvisitor/presentation/adapter/messages/InmateGifMessageVH$bind$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateGifMessageVH.kt */
final class InmateGifMessageVH$bind$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ Message $message$inlined;
    final /* synthetic */ InmateGifMessageVH this$0;

    InmateGifMessageVH$bind$$inlined$with$lambda$1(InmateGifMessageVH inmateGifMessageVH, Message message) {
        this.this$0 = inmateGifMessageVH;
        this.$message$inlined = message;
    }

    public final void onClick(View view) {
        this.this$0.onClickListener.invoke(this.$message$inlined);
    }
}
