package com.forasoft.homewavvisitor.presentation.adapter;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onGlobalLayout"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsAdapter.kt */
final class ConversationsAdapter$ConversationVH$bind$1$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ View $this_with;

    ConversationsAdapter$ConversationVH$bind$1$1(View view) {
        this.$this_with = view;
    }

    public final void onGlobalLayout() {
        TextView textView = (TextView) this.$this_with.findViewById(R.id.tv_body);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_body");
        if (textView.getLineCount() == 1) {
            ImageView imageView = (ImageView) this.$this_with.findViewById(R.id.iv_avatar);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "iv_avatar");
            ContextExtensionsKt.setMarginBottom(imageView, 16);
            return;
        }
        ImageView imageView2 = (ImageView) this.$this_with.findViewById(R.id.iv_avatar);
        Intrinsics.checkExpressionValueIsNotNull(imageView2, "iv_avatar");
        ContextExtensionsKt.setMarginBottom(imageView2, 32);
    }
}
