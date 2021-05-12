package com.forasoft.homewavvisitor.presentation.adapter.messages;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/InmateTextMessageVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "message", "Lcom/forasoft/homewavvisitor/model/data/Message;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateTextMessageVH.kt */
public final class InmateTextMessageVH extends RecyclerView.ViewHolder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InmateTextMessageVH(View view) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void bind(com.forasoft.homewavvisitor.model.data.Message r10) {
        /*
            r9 = this;
            java.lang.String r0 = "message"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            android.view.View r0 = r9.itemView
            java.lang.String r1 = r10.getSenderName()
            java.lang.String r3 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r1)
            android.content.Context r2 = r0.getContext()
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r1)
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 14
            r8 = 0
            com.amulyakhare.textdrawable.TextDrawable r1 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r2, r3, r4, r5, r6, r7, r8)
            java.lang.String r2 = r10.getSenderStatus()
            if (r2 != 0) goto L_0x0029
            goto L_0x004c
        L_0x0029:
            int r3 = r2.hashCode()
            r4 = 112785(0x1b891, float:1.58045E-40)
            if (r3 == r4) goto L_0x0042
            r4 = 98619139(0x5e0cf03, float:2.1140903E-35)
            if (r3 == r4) goto L_0x0038
            goto L_0x004c
        L_0x0038:
            java.lang.String r3 = "green"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x004c
            r2 = 2
            goto L_0x004d
        L_0x0042:
            java.lang.String r3 = "red"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x004c
            r2 = 1
            goto L_0x004d
        L_0x004c:
            r2 = 0
        L_0x004d:
            int r3 = com.forasoft.homewavvisitor.R.id.iv_status
            android.view.View r3 = r0.findViewById(r3)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            java.lang.String r4 = "iv_status"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            android.graphics.drawable.Drawable r3 = r3.getBackground()
            if (r3 == 0) goto L_0x00c9
            android.graphics.drawable.LevelListDrawable r3 = (android.graphics.drawable.LevelListDrawable) r3
            r3.setLevel(r2)
            int r2 = com.forasoft.homewavvisitor.R.id.iv_avatar
            android.view.View r2 = r0.findViewById(r2)
            de.hdodenhof.circleimageview.CircleImageView r2 = (de.hdodenhof.circleimageview.CircleImageView) r2
            java.lang.String r3 = "iv_avatar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            r2.setBackground(r1)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_message
            android.view.View r1 = r0.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            java.lang.String r2 = "tv_message"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r2 = r10.getBody()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            com.forasoft.homewavvisitor.model.data.MessageStatus r1 = r10.getStatus()
            com.forasoft.homewavvisitor.model.data.MessageStatus r2 = com.forasoft.homewavvisitor.model.data.MessageStatus.PENDING
            if (r1 != r2) goto L_0x00a2
            int r1 = com.forasoft.homewavvisitor.R.id.tv_pending
            android.view.View r1 = r0.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.show(r1)
            goto L_0x00ad
        L_0x00a2:
            int r1 = com.forasoft.homewavvisitor.R.id.tv_pending
            android.view.View r1 = r0.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
        L_0x00ad:
            int r1 = com.forasoft.homewavvisitor.R.id.tv_date
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tv_date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            org.threeten.bp.LocalDateTime r10 = r10.getCreated()
            java.lang.String r10 = com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt.getAsShortTime((org.threeten.bp.LocalDateTime) r10)
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            r0.setText(r10)
            return
        L_0x00c9:
            kotlin.TypeCastException r10 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.messages.InmateTextMessageVH.bind(com.forasoft.homewavvisitor.model.data.Message):void");
    }
}
