package com.forasoft.homewavvisitor.presentation.adapter.notifications;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/notifications/NotificationVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "onAddFundsClickListener", "Lkotlin/Function1;", "", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "gson", "Lcom/google/gson/Gson;", "bindNotification", "notificationWithInmateStatus", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationVH.kt */
public final class NotificationVH extends RecyclerView.ViewHolder {
    private final Gson gson;
    /* access modifiers changed from: private */
    public final Function1<String, Unit> onAddFundsClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationVH(View view, Function1<? super String, Unit> function1) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(function1, "onAddFundsClickListener");
        this.onAddFundsClickListener = function1;
        Object instance = Toothpick.openScopes(DI.SERVER_SCOPE).getInstance(Gson.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScopes(DI.…nstance(Gson::class.java)");
        this.gson = (Gson) instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0575  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void bindNotification(com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.String r1 = "notificationWithInmateStatus"
            r2 = r18
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1)
            com.forasoft.homewavvisitor.model.data.Notification r1 = r18.component1()
            java.lang.String r2 = r18.component2()
            android.view.View r3 = r0.itemView
            com.google.gson.Gson r4 = r0.gson
            java.lang.String r5 = r1.getBody()
            java.lang.Class<com.forasoft.homewavvisitor.model.data.NotificationBody> r6 = com.forasoft.homewavvisitor.model.data.NotificationBody.class
            java.lang.Object r4 = r4.fromJson((java.lang.String) r5, r6)
            com.forasoft.homewavvisitor.model.data.NotificationBody r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody) r4
            java.lang.String r5 = r4.getInmateName()
            java.lang.String r7 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r5)
            java.lang.String r5 = r4.getInmateName()
            int r6 = com.forasoft.homewavvisitor.R.id.tv_full_name
            android.view.View r6 = r3.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            java.lang.String r13 = "tv_full_name"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r13)
            r8 = r5
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r6.setText(r8)
            android.content.Context r6 = r3.getContext()
            java.lang.String r8 = "context"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r8)
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 14
            r12 = 0
            com.amulyakhare.textdrawable.TextDrawable r6 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r6, r7, r8, r9, r10, r11, r12)
            int r7 = com.forasoft.homewavvisitor.R.id.iv_avatar
            android.view.View r7 = r3.findViewById(r7)
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            android.graphics.drawable.Drawable r6 = (android.graphics.drawable.Drawable) r6
            r7.setImageDrawable(r6)
            int r6 = com.forasoft.homewavvisitor.R.id.tv_date
            android.view.View r6 = r3.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            java.lang.String r7 = "tv_date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            org.threeten.bp.LocalDateTime r8 = r1.getCreated()
            java.lang.String r8 = com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt.getAsShortTime((org.threeten.bp.LocalDateTime) r8)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r6.setText(r8)
            r6 = 2
            r8 = 1
            if (r2 != 0) goto L_0x0081
            goto L_0x00a4
        L_0x0081:
            int r10 = r2.hashCode()
            r11 = 112785(0x1b891, float:1.58045E-40)
            if (r10 == r11) goto L_0x009a
            r11 = 98619139(0x5e0cf03, float:2.1140903E-35)
            if (r10 == r11) goto L_0x0090
            goto L_0x00a4
        L_0x0090:
            java.lang.String r10 = "green"
            boolean r10 = r2.equals(r10)
            if (r10 == 0) goto L_0x00a4
            r10 = 2
            goto L_0x00a5
        L_0x009a:
            java.lang.String r10 = "red"
            boolean r10 = r2.equals(r10)
            if (r10 == 0) goto L_0x00a4
            r10 = 1
            goto L_0x00a5
        L_0x00a4:
            r10 = 0
        L_0x00a5:
            int r11 = com.forasoft.homewavvisitor.R.id.iv_status
            android.view.View r11 = r3.findViewById(r11)
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            java.lang.String r12 = "iv_status"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)
            android.graphics.drawable.Drawable r11 = r11.getBackground()
            if (r11 == 0) goto L_0x0575
            android.graphics.drawable.LevelListDrawable r11 = (android.graphics.drawable.LevelListDrawable) r11
            r11.setLevel(r10)
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.BalanceBelowTwo
            r11 = 2131820860(0x7f11013c, float:1.9274447E38)
            java.lang.String r12 = "tv_add_funds"
            java.lang.String r14 = "tv_title"
            java.lang.String r15 = "tv_body"
            if (r10 == 0) goto L_0x013a
            int r6 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r6 = r3.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r15)
            android.content.res.Resources r7 = r3.getResources()
            r10 = 2131821000(0x7f1101c8, float:1.927473E38)
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r8[r9] = r5
            java.lang.String r5 = r7.getString(r10, r8)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r6.setText(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r14)
            android.content.res.Resources r6 = r3.getResources()
            java.lang.String r6 = r6.getString(r11)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_dollar
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            com.forasoft.homewavvisitor.extension.CommonKt.show(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_add_funds
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            com.forasoft.homewavvisitor.extension.CommonKt.show(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_add_funds
            android.view.View r3 = r3.findViewById(r5)
            android.widget.TextView r3 = (android.widget.TextView) r3
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r12)
            android.view.View r3 = (android.view.View) r3
            com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$bindNotification$$inlined$with$lambda$1 r5 = new com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$bindNotification$$inlined$with$lambda$1
            r5.<init>(r4, r0, r1, r2)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$inlined$sam$i$android_view_View_OnClickListener$0 r1 = new com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$inlined$sam$i$android_view_View_OnClickListener$0
            r1.<init>(r5)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r3.setOnClickListener(r1)
            goto L_0x0574
        L_0x013a:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.BalanceBelowZero
            if (r10 == 0) goto L_0x01ab
            int r6 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r6 = r3.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r15)
            android.content.res.Resources r7 = r3.getResources()
            r10 = 2131821001(0x7f1101c9, float:1.9274733E38)
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r8[r9] = r5
            java.lang.String r5 = r7.getString(r10, r8)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r6.setText(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r14)
            android.content.res.Resources r6 = r3.getResources()
            java.lang.String r6 = r6.getString(r11)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_dollar
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            com.forasoft.homewavvisitor.extension.CommonKt.show(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_add_funds
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            com.forasoft.homewavvisitor.extension.CommonKt.show(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_add_funds
            android.view.View r3 = r3.findViewById(r5)
            android.widget.TextView r3 = (android.widget.TextView) r3
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r12)
            android.view.View r3 = (android.view.View) r3
            com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$bindNotification$$inlined$with$lambda$2 r5 = new com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$bindNotification$$inlined$with$lambda$2
            r5.<init>(r4, r0, r1, r2)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$inlined$sam$i$android_view_View_OnClickListener$0 r1 = new com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$inlined$sam$i$android_view_View_OnClickListener$0
            r1.<init>(r5)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r3.setOnClickListener(r1)
            goto L_0x0574
        L_0x01ab:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.MessageText
            if (r10 == 0) goto L_0x01e2
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            com.forasoft.homewavvisitor.model.data.NotificationBody$MessageText r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody.MessageText) r4
            java.lang.String r2 = r4.getBody()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821011(0x7f1101d3, float:1.9274753E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x01e2:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.MessageVideo
            java.lang.String r16 = "video"
            r11 = 2131821009(0x7f1101d1, float:1.927475E38)
            if (r10 == 0) goto L_0x022b
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            java.lang.Object[] r5 = new java.lang.Object[r6]
            java.lang.String r4 = r4.getInmateName()
            r5[r9] = r4
            r5[r8] = r16
            java.lang.String r2 = r2.getString(r11, r5)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821012(0x7f1101d4, float:1.9274755E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x022b:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.MessageImage
            if (r10 == 0) goto L_0x027f
            int r1 = com.forasoft.homewavvisitor.R.id.iv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.show(r1)
            android.content.Context r1 = r3.getContext()
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with((android.content.Context) r1)
            com.forasoft.homewavvisitor.model.data.NotificationBody$MessageImage r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody.MessageImage) r4
            java.lang.String r2 = r4.getPreviewUrl()
            com.bumptech.glide.DrawableTypeRequest r1 = r1.load((java.lang.String) r2)
            int r2 = com.forasoft.homewavvisitor.R.id.iv_body
            android.view.View r2 = r3.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            r1.into(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821008(0x7f1101d0, float:1.9274747E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x027f:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.MessageGif
            if (r10 == 0) goto L_0x02c4
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            java.lang.Object[] r5 = new java.lang.Object[r6]
            java.lang.String r4 = r4.getInmateName()
            r5[r9] = r4
            java.lang.String r4 = "gif"
            r5[r8] = r4
            java.lang.String r2 = r2.getString(r11, r5)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821007(0x7f1101cf, float:1.9274745E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x02c4:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.MessageS3Video
            if (r10 == 0) goto L_0x0307
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            java.lang.Object[] r5 = new java.lang.Object[r6]
            java.lang.String r4 = r4.getInmateName()
            r5[r9] = r4
            r5[r8] = r16
            java.lang.String r2 = r2.getString(r11, r5)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821012(0x7f1101d4, float:1.9274755E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x0307:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.FundsAdded
            if (r10 == 0) goto L_0x034f
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            r7 = 2131820997(0x7f1101c5, float:1.9274725E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.forasoft.homewavvisitor.model.data.NotificationBody$FundsAdded r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody.FundsAdded) r4
            java.lang.String r4 = r4.getValue()
            r6[r9] = r4
            r6[r8] = r5
            java.lang.String r2 = r2.getString(r7, r6)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131820998(0x7f1101c6, float:1.9274727E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x034f:
            boolean r10 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.RequestFunds
            if (r10 == 0) goto L_0x03cc
            int r7 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r7 = r3.findViewById(r7)
            android.widget.TextView r7 = (android.widget.TextView) r7
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r15)
            android.content.res.Resources r10 = r3.getResources()
            r11 = 2131821016(0x7f1101d8, float:1.9274763E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r9] = r5
            r5 = r4
            com.forasoft.homewavvisitor.model.data.NotificationBody$RequestFunds r5 = (com.forasoft.homewavvisitor.model.data.NotificationBody.RequestFunds) r5
            java.lang.String r5 = r5.getValue()
            r6[r8] = r5
            java.lang.String r5 = r10.getString(r11, r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r7.setText(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r14)
            android.content.res.Resources r6 = r3.getResources()
            r7 = 2131821017(0x7f1101d9, float:1.9274765E38)
            java.lang.String r6 = r6.getString(r7)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_dollar
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            com.forasoft.homewavvisitor.extension.CommonKt.show(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_add_funds
            android.view.View r5 = r3.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            com.forasoft.homewavvisitor.extension.CommonKt.show(r5)
            int r5 = com.forasoft.homewavvisitor.R.id.tv_add_funds
            android.view.View r3 = r3.findViewById(r5)
            android.widget.TextView r3 = (android.widget.TextView) r3
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r12)
            android.view.View r3 = (android.view.View) r3
            com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$bindNotification$$inlined$with$lambda$3 r5 = new com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$bindNotification$$inlined$with$lambda$3
            r5.<init>(r4, r0, r1, r2)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$inlined$sam$i$android_view_View_OnClickListener$0 r1 = new com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH$inlined$sam$i$android_view_View_OnClickListener$0
            r1.<init>(r5)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r3.setOnClickListener(r1)
            goto L_0x0574
        L_0x03cc:
            boolean r1 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.VisitConfirm
            if (r1 == 0) goto L_0x0414
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            r7 = 2131821021(0x7f1101dd, float:1.9274773E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r9] = r5
            com.forasoft.homewavvisitor.model.data.NotificationBody$VisitConfirm r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody.VisitConfirm) r4
            java.lang.String r4 = r4.getTimeSlot()
            r6[r8] = r4
            java.lang.String r2 = r2.getString(r7, r6)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821015(0x7f1101d7, float:1.9274761E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x0414:
            boolean r1 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.VisitCancel
            if (r1 == 0) goto L_0x045c
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            r7 = 2131821020(0x7f1101dc, float:1.9274771E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r9] = r5
            com.forasoft.homewavvisitor.model.data.NotificationBody$VisitCancel r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody.VisitCancel) r4
            java.lang.String r4 = r4.getTimeSlot()
            r6[r8] = r4
            java.lang.String r2 = r2.getString(r7, r6)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821014(0x7f1101d6, float:1.927476E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x045c:
            boolean r1 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.IncomingCall
            if (r1 == 0) goto L_0x049c
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            r4 = 2131821002(0x7f1101ca, float:1.9274735E38)
            java.lang.Object[] r6 = new java.lang.Object[r8]
            r6[r9] = r5
            java.lang.String r2 = r2.getString(r4, r6)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821003(0x7f1101cb, float:1.9274737E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x049c:
            boolean r1 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.InmateOnline
            if (r1 == 0) goto L_0x04dc
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            android.content.res.Resources r2 = r3.getResources()
            r4 = 2131821004(0x7f1101cc, float:1.9274739E38)
            java.lang.Object[] r6 = new java.lang.Object[r8]
            r6[r9] = r5
            java.lang.String r2 = r2.getString(r4, r6)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_title
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r14)
            android.content.res.Resources r2 = r3.getResources()
            r3 = 2131821005(0x7f1101cd, float:1.927474E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            goto L_0x0574
        L_0x04dc:
            boolean r1 = r4 instanceof com.forasoft.homewavvisitor.model.data.NotificationBody.Airship
            if (r1 == 0) goto L_0x0574
            int r1 = com.forasoft.homewavvisitor.R.id.tv_full_name
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r13)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            if (r1 == 0) goto L_0x056c
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r1 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r1
            int r2 = com.forasoft.homewavvisitor.R.id.tv_date
            android.view.View r2 = r3.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r7)
            int r2 = r2.getId()
            r1.topToTop = r2
            int r2 = com.forasoft.homewavvisitor.R.id.tv_date
            android.view.View r2 = r3.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r7)
            int r2 = r2.getId()
            r1.bottomToBottom = r2
            int r2 = com.forasoft.homewavvisitor.R.id.tv_date
            android.view.View r2 = r3.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r7)
            int r2 = r2.getId()
            r1.endToStart = r2
            int r1 = com.forasoft.homewavvisitor.R.id.tv_full_name
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r13)
            android.content.res.Resources r2 = r3.getResources()
            r5 = 2131820999(0x7f1101c7, float:1.9274729E38)
            java.lang.String r2 = r2.getString(r5)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.tv_body
            android.view.View r1 = r3.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r15)
            com.forasoft.homewavvisitor.model.data.NotificationBody$Airship r4 = (com.forasoft.homewavvisitor.model.data.NotificationBody.Airship) r4
            java.lang.String r2 = r4.getMessage()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            int r1 = com.forasoft.homewavvisitor.R.id.iv_avatar
            android.view.View r1 = r3.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
            int r1 = com.forasoft.homewavvisitor.R.id.include
            android.view.View r1 = r3.findViewById(r1)
            com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
            goto L_0x0574
        L_0x056c:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams"
            r1.<init>(r2)
            throw r1
        L_0x0574:
            return
        L_0x0575:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationVH.bindNotification(com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus):void");
    }
}
