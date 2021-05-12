package com.forasoft.homewavvisitor.presentation.adapter;

import air.HomeWAV.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.model.data.MessageType;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\u00062\n\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "Lcom/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter$ConversationVH;", "onItemClickListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "getItem", "position", "", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ConversationVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationsAdapter.kt */
public final class ConversationsAdapter extends ListAdapter<Chat, ConversationVH> {
    /* access modifiers changed from: private */
    public static final DiffUtil.ItemCallback<Chat> CHAT_COMPARATOR = new ConversationsAdapter$Companion$CHAT_COMPARATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final Function1<Chat, Unit> onItemClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConversationsAdapter(Function1<? super Chat, Unit> function1) {
        super(CHAT_COMPARATOR);
        Intrinsics.checkParameterIsNotNull(function1, "onItemClickListener");
        this.onItemClickListener = function1;
    }

    public ConversationVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new ConversationVH(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_conversation, false, 2, (Object) null));
    }

    public void onBindViewHolder(ConversationVH conversationVH, int i) {
        Intrinsics.checkParameterIsNotNull(conversationVH, "holder");
        conversationVH.bind(getItem(i));
    }

    public Chat getItem(int i) {
        Object item = super.getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "super.getItem(position)");
        return (Chat) item;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter$ConversationVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter;Landroid/view/View;)V", "bind", "", "chat", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConversationsAdapter.kt */
    public final class ConversationVH extends RecyclerView.ViewHolder {
        final /* synthetic */ ConversationsAdapter this$0;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[MessageType.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[MessageType.VIDEO.ordinal()] = 1;
                iArr[MessageType.IMAGE.ordinal()] = 2;
                iArr[MessageType.GIF.ordinal()] = 3;
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ConversationVH(ConversationsAdapter conversationsAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = conversationsAdapter;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x00e1  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x0278  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bind(com.forasoft.homewavvisitor.model.data.Chat r15) {
            /*
                r14 = this;
                java.lang.String r0 = "chat"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r0)
                android.view.View r0 = r14.itemView
                com.forasoft.homewavvisitor.model.data.Inmate r1 = r15.component1()
                com.forasoft.homewavvisitor.model.data.Message r2 = r15.component2()
                java.lang.String r3 = "iv_avatar"
                r4 = 2
                r5 = 1
                if (r2 != 0) goto L_0x003e
                int r6 = com.forasoft.homewavvisitor.R.id.tv_date
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                com.forasoft.homewavvisitor.extension.CommonKt.hide(r6)
                int r6 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                r6.setLines(r4)
                int r6 = com.forasoft.homewavvisitor.R.id.iv_avatar
                android.view.View r6 = r0.findViewById(r6)
                android.widget.ImageView r6 = (android.widget.ImageView) r6
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r3)
                android.view.View r6 = (android.view.View) r6
                r3 = 32
                com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.setMarginBottom(r6, r3)
                goto L_0x0066
            L_0x003e:
                int r6 = com.forasoft.homewavvisitor.R.id.tv_date
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                com.forasoft.homewavvisitor.extension.CommonKt.show(r6)
                int r6 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                r6.setLines(r5)
                int r6 = com.forasoft.homewavvisitor.R.id.iv_avatar
                android.view.View r6 = r0.findViewById(r6)
                android.widget.ImageView r6 = (android.widget.ImageView) r6
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r3)
                android.view.View r6 = (android.view.View) r6
                r3 = 16
                com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.setMarginBottom(r6, r3)
            L_0x0066:
                int r3 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r3 = r0.findViewById(r3)
                android.widget.TextView r3 = (android.widget.TextView) r3
                java.lang.String r6 = "tv_body"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6)
                android.view.ViewTreeObserver r3 = r3.getViewTreeObserver()
                com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter$ConversationVH$bind$1$1 r7 = new com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter$ConversationVH$bind$1$1
                r7.<init>(r0)
                android.view.ViewTreeObserver$OnGlobalLayoutListener r7 = (android.view.ViewTreeObserver.OnGlobalLayoutListener) r7
                r3.addOnGlobalLayoutListener(r7)
                java.lang.String r3 = r1.getFull_name()
                if (r3 != 0) goto L_0x008b
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x008b:
                java.lang.String r8 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r3)
                android.content.Context r7 = r0.getContext()
                java.lang.String r3 = "context"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r3)
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 14
                r13 = 0
                com.amulyakhare.textdrawable.TextDrawable r3 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r7, r8, r9, r10, r11, r12, r13)
                java.lang.String r7 = r1.getStatus()
                r8 = 0
                if (r7 != 0) goto L_0x00aa
                goto L_0x00cd
            L_0x00aa:
                int r9 = r7.hashCode()
                r10 = 112785(0x1b891, float:1.58045E-40)
                if (r9 == r10) goto L_0x00c3
                r10 = 98619139(0x5e0cf03, float:2.1140903E-35)
                if (r9 == r10) goto L_0x00b9
                goto L_0x00cd
            L_0x00b9:
                java.lang.String r9 = "green"
                boolean r7 = r7.equals(r9)
                if (r7 == 0) goto L_0x00cd
                r7 = 2
                goto L_0x00ce
            L_0x00c3:
                java.lang.String r9 = "red"
                boolean r7 = r7.equals(r9)
                if (r7 == 0) goto L_0x00cd
                r7 = 1
                goto L_0x00ce
            L_0x00cd:
                r7 = 0
            L_0x00ce:
                int r9 = com.forasoft.homewavvisitor.R.id.iv_status
                android.view.View r9 = r0.findViewById(r9)
                android.widget.ImageView r9 = (android.widget.ImageView) r9
                java.lang.String r10 = "iv_status"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
                android.graphics.drawable.Drawable r9 = r9.getBackground()
                if (r9 == 0) goto L_0x0278
                android.graphics.drawable.LevelListDrawable r9 = (android.graphics.drawable.LevelListDrawable) r9
                r9.setLevel(r7)
                int r7 = com.forasoft.homewavvisitor.R.id.iv_avatar
                android.view.View r7 = r0.findViewById(r7)
                android.widget.ImageView r7 = (android.widget.ImageView) r7
                android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
                r7.setImageDrawable(r3)
                int r3 = com.forasoft.homewavvisitor.R.id.tv_full_name
                android.view.View r3 = r0.findViewById(r3)
                android.widget.TextView r3 = (android.widget.TextView) r3
                java.lang.String r7 = "tv_full_name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r7)
                java.lang.String r1 = r1.getFull_name()
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r3.setText(r1)
                if (r2 == 0) goto L_0x022a
                com.forasoft.homewavvisitor.model.data.MessageType r1 = r2.getType()
                int[] r3 = com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter.ConversationVH.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r3[r1]
                if (r1 == r5) goto L_0x016c
                if (r1 == r4) goto L_0x0150
                r3 = 3
                if (r1 == r3) goto L_0x0134
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                java.lang.String r3 = r2.getBody()
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r1.setText(r3)
                goto L_0x0187
            L_0x0134:
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                android.content.res.Resources r3 = r0.getResources()
                r4 = 2131820874(0x7f11014a, float:1.9274475E38)
                java.lang.String r3 = r3.getString(r4)
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r1.setText(r3)
                goto L_0x0187
            L_0x0150:
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                android.content.res.Resources r3 = r0.getResources()
                r4 = 2131820875(0x7f11014b, float:1.9274477E38)
                java.lang.String r3 = r3.getString(r4)
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r1.setText(r3)
                goto L_0x0187
            L_0x016c:
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                android.content.res.Resources r3 = r0.getResources()
                r4 = 2131820878(0x7f11014e, float:1.9274483E38)
                java.lang.String r3 = r3.getString(r4)
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r1.setText(r3)
            L_0x0187:
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                android.graphics.Typeface r1 = r1.getTypeface()
                java.lang.String r3 = r2.getViews()
                java.lang.String r4 = "0"
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
                if (r3 == 0) goto L_0x01b6
                com.forasoft.homewavvisitor.model.data.Sender r3 = r2.getSender()
                com.forasoft.homewavvisitor.model.data.Sender r4 = com.forasoft.homewavvisitor.model.data.Sender.INMATE
                if (r3 != r4) goto L_0x01b6
                int r3 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r3 = r0.findViewById(r3)
                android.widget.TextView r3 = (android.widget.TextView) r3
                r3.setTypeface(r1, r5)
                goto L_0x01c1
            L_0x01b6:
                int r3 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r3 = r0.findViewById(r3)
                android.widget.TextView r3 = (android.widget.TextView) r3
                r3.setTypeface(r1, r8)
            L_0x01c1:
                org.threeten.bp.LocalDateTime r1 = r2.getCreated()
                org.threeten.bp.temporal.ChronoUnit r2 = org.threeten.bp.temporal.ChronoUnit.DAYS
                org.threeten.bp.temporal.TemporalUnit r2 = (org.threeten.bp.temporal.TemporalUnit) r2
                org.threeten.bp.LocalDateTime r2 = r1.truncatedTo(r2)
                org.threeten.bp.LocalDateTime r3 = org.threeten.bp.LocalDateTime.now()
                org.threeten.bp.temporal.ChronoUnit r4 = org.threeten.bp.temporal.ChronoUnit.DAYS
                org.threeten.bp.temporal.TemporalUnit r4 = (org.threeten.bp.temporal.TemporalUnit) r4
                org.threeten.bp.LocalDateTime r3 = r3.truncatedTo(r4)
                org.threeten.bp.temporal.Temporal r2 = (org.threeten.bp.temporal.Temporal) r2
                org.threeten.bp.temporal.Temporal r3 = (org.threeten.bp.temporal.Temporal) r3
                org.threeten.bp.Duration r2 = org.threeten.bp.Duration.between(r2, r3)
                long r2 = r2.toDays()
                int r4 = com.forasoft.homewavvisitor.R.id.tv_date
                android.view.View r4 = r0.findViewById(r4)
                android.widget.TextView r4 = (android.widget.TextView) r4
                java.lang.String r7 = "tv_date"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r7)
                r7 = 1
                int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r9 >= 0) goto L_0x0200
                java.lang.String r1 = com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt.getAsShortTime((org.threeten.bp.LocalDateTime) r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                goto L_0x0218
            L_0x0200:
                r9 = 6
                long r9 = (long) r9
                int r11 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                if (r11 <= 0) goto L_0x0207
                goto L_0x0212
            L_0x0207:
                int r7 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
                if (r7 < 0) goto L_0x0212
                java.lang.String r1 = com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt.getAsDayOfWeek(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                goto L_0x0218
            L_0x0212:
                java.lang.String r1 = com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt.getAsDayMonth(r1)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            L_0x0218:
                r4.setText(r1)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                r1.setSingleLine(r5)
                goto L_0x0266
            L_0x022a:
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                r1.setSingleLine(r8)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_body
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "See what "
                r2.append(r3)
                com.forasoft.homewavvisitor.model.data.Inmate r3 = r15.getInmate()
                java.lang.String r3 = r3.getFirst_name()
                r2.append(r3)
                java.lang.String r3 = " is up to by sending a message"
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
            L_0x0266:
                com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter$ConversationVH$bind$$inlined$with$lambda$1 r1 = new com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter$ConversationVH$bind$$inlined$with$lambda$1
                r1.<init>(r14, r15)
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter$ConversationVH$inlined$sam$i$android_view_View_OnClickListener$0 r15 = new com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter$ConversationVH$inlined$sam$i$android_view_View_OnClickListener$0
                r15.<init>(r1)
                android.view.View$OnClickListener r15 = (android.view.View.OnClickListener) r15
                r0.setOnClickListener(r15)
                return
            L_0x0278:
                kotlin.TypeCastException r15 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
                r15.<init>(r0)
                throw r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter.ConversationVH.bind(com.forasoft.homewavvisitor.model.data.Chat):void");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter$Companion;", "", "()V", "CHAT_COMPARATOR", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "getCHAT_COMPARATOR", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConversationsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DiffUtil.ItemCallback<Chat> getCHAT_COMPARATOR() {
            return ConversationsAdapter.CHAT_COMPARATOR;
        }
    }
}
