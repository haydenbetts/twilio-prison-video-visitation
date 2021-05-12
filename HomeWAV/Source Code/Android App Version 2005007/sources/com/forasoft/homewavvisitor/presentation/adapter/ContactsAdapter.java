package com.forasoft.homewavvisitor.presentation.adapter;

import air.HomeWAV.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0012\u0013B!\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\u00020\u00062\n\u0010\u000b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/ContactsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "Lcom/forasoft/homewavvisitor/presentation/adapter/ContactsAdapter$ContactsViewHolder;", "clickListener", "Lkotlin/Function1;", "", "isOnlineVisible", "", "(Lkotlin/jvm/functions/Function1;Z)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ContactsItemCallback", "ContactsViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ContactsAdapter.kt */
public final class ContactsAdapter extends ListAdapter<InmateByVisitor, ContactsViewHolder> {
    /* access modifiers changed from: private */
    public final Function1<InmateByVisitor, Unit> clickListener;
    /* access modifiers changed from: private */
    public final boolean isOnlineVisible;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsAdapter(Function1<? super InmateByVisitor, Unit> function1, boolean z) {
        super(ContactsItemCallback.INSTANCE);
        Intrinsics.checkParameterIsNotNull(function1, "clickListener");
        this.clickListener = function1;
        this.isOnlineVisible = z;
    }

    public ContactsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new ContactsViewHolder(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_inmate_summary, false, 2, (Object) null));
    }

    public void onBindViewHolder(ContactsViewHolder contactsViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(contactsViewHolder, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        contactsViewHolder.bind((InmateByVisitor) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/ContactsAdapter$ContactsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/ContactsAdapter;Landroid/view/View;)V", "bind", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ContactsAdapter.kt */
    public final class ContactsViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ ContactsAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ContactsViewHolder(ContactsAdapter contactsAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = contactsAdapter;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0140, code lost:
            if (r15.equals("green") == false) goto L_0x014d;
         */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0161  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0167  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bind(com.forasoft.homewavvisitor.model.data.register.InmateByVisitor r15) {
            /*
                r14 = this;
                java.lang.String r0 = "inmate"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r0)
                android.view.View r0 = r14.itemView
                kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
                r1 = 2
                java.lang.Object[] r2 = new java.lang.Object[r1]
                java.lang.String r3 = r15.getFirst_name()
                r4 = 0
                r2[r4] = r3
                java.lang.String r3 = r15.getLast_name()
                r5 = 1
                r2[r5] = r3
                java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r1)
                java.lang.String r3 = "%s %s"
                java.lang.String r2 = java.lang.String.format(r3, r2)
                java.lang.String r3 = "java.lang.String.format(format, *args)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                int r3 = com.forasoft.homewavvisitor.R.id.textName
                android.view.View r3 = r0.findViewById(r3)
                android.widget.TextView r3 = (android.widget.TextView) r3
                java.lang.String r6 = "textName"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6)
                r6 = r2
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                r3.setText(r6)
                com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter$ContactsViewHolder$bind$$inlined$with$lambda$1 r3 = new com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter$ContactsViewHolder$bind$$inlined$with$lambda$1
                r3.<init>(r14, r15)
                kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
                com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter$ContactsViewHolder$inlined$sam$i$android_view_View_OnClickListener$0 r6 = new com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter$ContactsViewHolder$inlined$sam$i$android_view_View_OnClickListener$0
                r6.<init>(r3)
                android.view.View$OnClickListener r6 = (android.view.View.OnClickListener) r6
                r0.setOnClickListener(r6)
                android.content.res.Resources r3 = r0.getResources()
                r6 = 2131820801(0x7f110101, float:1.9274327E38)
                java.lang.String r3 = r3.getString(r6)
                java.lang.String r6 = "resources.getString(R.string.label_add_funds)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6)
                java.lang.String r6 = r15.getCredit_balance()
                r7 = r6
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                java.lang.String r8 = "."
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                r9 = 0
                boolean r7 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r7, (java.lang.CharSequence) r8, (boolean) r4, (int) r1, (java.lang.Object) r9)
                if (r7 != 0) goto L_0x0080
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                r7.append(r6)
                java.lang.String r6 = ".00"
                r7.append(r6)
                java.lang.String r6 = r7.toString()
            L_0x0080:
                int r7 = com.forasoft.homewavvisitor.R.id.textMoney
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                java.lang.String r8 = "textMoney"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                r7.setText(r6)
                java.lang.String r6 = r15.getApproved()
                java.lang.String r7 = "yes"
                boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
                java.lang.String r7 = "linkAddFunds"
                if (r6 == 0) goto L_0x00c3
                boolean r6 = r15.is_fraud()
                if (r6 != 0) goto L_0x00c3
                int r6 = com.forasoft.homewavvisitor.R.id.textApproval
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                com.forasoft.homewavvisitor.extension.CommonKt.hide(r6)
                int r6 = com.forasoft.homewavvisitor.R.id.linkAddFunds
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r6.setText(r3)
                goto L_0x00ef
            L_0x00c3:
                int r6 = com.forasoft.homewavvisitor.R.id.textApproval
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                com.forasoft.homewavvisitor.extension.CommonKt.show(r6)
                int r6 = com.forasoft.homewavvisitor.R.id.linkAddFunds
                android.view.View r6 = r0.findViewById(r6)
                android.widget.TextView r6 = (android.widget.TextView) r6
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                r7.append(r3)
                r3 = 42
                r7.append(r3)
                java.lang.String r3 = r7.toString()
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r6.setText(r3)
            L_0x00ef:
                android.content.Context r7 = r0.getContext()
                java.lang.String r3 = "context"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r3)
                java.lang.String r8 = com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt.getAsInitials(r2)
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 14
                r13 = 0
                com.amulyakhare.textdrawable.TextDrawable r2 = com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt.createTextDrawable$default(r7, r8, r9, r10, r11, r12, r13)
                int r3 = com.forasoft.homewavvisitor.R.id.imageAvatarCircle
                android.view.View r3 = r0.findViewById(r3)
                android.widget.ImageView r3 = (android.widget.ImageView) r3
                android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
                r3.setImageDrawable(r2)
                com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter r2 = r14.this$0
                boolean r2 = r2.isOnlineVisible
                if (r2 == 0) goto L_0x016f
                int r2 = com.forasoft.homewavvisitor.R.id.viewStatus
                android.view.View r2 = r0.findViewById(r2)
                com.forasoft.homewavvisitor.extension.CommonKt.show(r2)
                java.lang.String r15 = r15.getStatus()
                if (r15 != 0) goto L_0x012b
                goto L_0x014d
            L_0x012b:
                int r2 = r15.hashCode()
                r3 = 112785(0x1b891, float:1.58045E-40)
                if (r2 == r3) goto L_0x0143
                r3 = 98619139(0x5e0cf03, float:2.1140903E-35)
                if (r2 == r3) goto L_0x013a
                goto L_0x014d
            L_0x013a:
                java.lang.String r2 = "green"
                boolean r15 = r15.equals(r2)
                if (r15 == 0) goto L_0x014d
                goto L_0x014e
            L_0x0143:
                java.lang.String r1 = "red"
                boolean r15 = r15.equals(r1)
                if (r15 == 0) goto L_0x014d
                r1 = 1
                goto L_0x014e
            L_0x014d:
                r1 = 0
            L_0x014e:
                int r15 = com.forasoft.homewavvisitor.R.id.iv_status
                android.view.View r15 = r0.findViewById(r15)
                android.widget.ImageView r15 = (android.widget.ImageView) r15
                java.lang.String r0 = "iv_status"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r0)
                android.graphics.drawable.Drawable r15 = r15.getBackground()
                if (r15 == 0) goto L_0x0167
                android.graphics.drawable.LevelListDrawable r15 = (android.graphics.drawable.LevelListDrawable) r15
                r15.setLevel(r1)
                goto L_0x016f
            L_0x0167:
                kotlin.TypeCastException r15 = new kotlin.TypeCastException
                java.lang.String r0 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
                r15.<init>(r0)
                throw r15
            L_0x016f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter.ContactsViewHolder.bind(com.forasoft.homewavvisitor.model.data.register.InmateByVisitor):void");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/ContactsAdapter$ContactsItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ContactsAdapter.kt */
    public static final class ContactsItemCallback extends DiffUtil.ItemCallback<InmateByVisitor> {
        public static final ContactsItemCallback INSTANCE = new ContactsItemCallback();

        private ContactsItemCallback() {
        }

        public boolean areItemsTheSame(InmateByVisitor inmateByVisitor, InmateByVisitor inmateByVisitor2) {
            Intrinsics.checkParameterIsNotNull(inmateByVisitor, "oldItem");
            Intrinsics.checkParameterIsNotNull(inmateByVisitor2, "newItem");
            return Intrinsics.areEqual((Object) inmateByVisitor.getId(), (Object) inmateByVisitor2.getId());
        }

        public boolean areContentsTheSame(InmateByVisitor inmateByVisitor, InmateByVisitor inmateByVisitor2) {
            Intrinsics.checkParameterIsNotNull(inmateByVisitor, "oldItem");
            Intrinsics.checkParameterIsNotNull(inmateByVisitor2, "newItem");
            return Intrinsics.areEqual((Object) inmateByVisitor, (Object) inmateByVisitor2);
        }
    }
}
