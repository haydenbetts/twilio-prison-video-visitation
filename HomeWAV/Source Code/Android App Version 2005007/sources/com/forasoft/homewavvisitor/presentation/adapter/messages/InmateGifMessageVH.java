package com.forasoft.homewavvisitor.presentation.adapter.messages;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Message;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/InmateGifMessageVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "onClickListener", "Lkotlin/Function1;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "bind", "message", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateGifMessageVH.kt */
public final class InmateGifMessageVH extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Function1<Message, Unit> onClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InmateGifMessageVH(View view, Function1<? super Message, Unit> function1) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.onClickListener = function1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00f1  */
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
            android.content.Context r3 = r0.getContext()
            com.bumptech.glide.RequestManager r3 = com.bumptech.glide.Glide.with((android.content.Context) r3)
            java.lang.String r4 = r10.getImageUrl()
            com.bumptech.glide.DrawableTypeRequest r3 = r3.load((java.lang.String) r4)
            r4 = 2131165295(0x7f07006f, float:1.7944803E38)
            com.bumptech.glide.DrawableRequestBuilder r3 = r3.error((int) r4)
            com.forasoft.homewavvisitor.presentation.adapter.messages.InmateGifMessageVH$bind$1$1 r4 = new com.forasoft.homewavvisitor.presentation.adapter.messages.InmateGifMessageVH$bind$1$1
            r4.<init>(r0)
            com.bumptech.glide.request.RequestListener r4 = (com.bumptech.glide.request.RequestListener) r4
            com.bumptech.glide.DrawableRequestBuilder r3 = r3.listener(r4)
            int r4 = com.forasoft.homewavvisitor.R.id.iv_preview
            android.view.View r4 = r0.findViewById(r4)
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            r3.into(r4)
            int r3 = com.forasoft.homewavvisitor.R.id.iv_preview
            android.view.View r3 = r0.findViewById(r3)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            com.forasoft.homewavvisitor.presentation.adapter.messages.InmateGifMessageVH$bind$$inlined$with$lambda$1 r4 = new com.forasoft.homewavvisitor.presentation.adapter.messages.InmateGifMessageVH$bind$$inlined$with$lambda$1
            r4.<init>(r9, r10)
            android.view.View$OnClickListener r4 = (android.view.View.OnClickListener) r4
            r3.setOnClickListener(r4)
            int r3 = com.forasoft.homewavvisitor.R.id.iv_status
            android.view.View r3 = r0.findViewById(r3)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            java.lang.String r4 = "iv_status"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            android.graphics.drawable.Drawable r3 = r3.getBackground()
            if (r3 == 0) goto L_0x00f1
            android.graphics.drawable.LevelListDrawable r3 = (android.graphics.drawable.LevelListDrawable) r3
            r3.setLevel(r2)
            int r2 = com.forasoft.homewavvisitor.R.id.iv_avatar
            android.view.View r2 = r0.findViewById(r2)
            de.hdodenhof.circleimageview.CircleImageView r2 = (de.hdodenhof.circleimageview.CircleImageView) r2
            java.lang.String r3 = "iv_avatar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            r2.setBackground(r1)
            com.forasoft.homewavvisitor.model.data.MessageStatus r1 = r10.getStatus()
            com.forasoft.homewavvisitor.model.data.MessageStatus r2 = com.forasoft.homewavvisitor.model.data.MessageStatus.PENDING
            if (r1 != r2) goto L_0x00ca
            int r1 = com.forasoft.homewavvisitor.R.id.tv_pending
            android.view.View r1 = r0.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.show(r1)
            goto L_0x00d5
        L_0x00ca:
            int r1 = com.forasoft.homewavvisitor.R.id.tv_pending
            android.view.View r1 = r0.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
        L_0x00d5:
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
        L_0x00f1:
            kotlin.TypeCastException r10 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.messages.InmateGifMessageVH.bind(com.forasoft.homewavvisitor.model.data.Message):void");
    }
}
