package com.forasoft.homewavvisitor.presentation.adapter.account;

import air.HomeWAV.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.model.data.Card;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0013B3\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00062\n\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0016R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/EditCardsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Card;", "Lcom/forasoft/homewavvisitor/presentation/adapter/account/EditCardsAdapter$PaymentMethodVH;", "onDelete", "Lkotlin/Function1;", "", "onChange", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "PaymentMethodVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EditCardsAdapter.kt */
public final class EditCardsAdapter extends ListAdapter<Card, PaymentMethodVH> {
    /* access modifiers changed from: private */
    public final Function2<Card, Boolean, Unit> onChange;
    /* access modifiers changed from: private */
    public final Function1<Card, Unit> onDelete;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EditCardsAdapter(Function1<? super Card, Unit> function1, Function2<? super Card, ? super Boolean, Unit> function2) {
        super(CardsAdapter.Companion);
        Intrinsics.checkParameterIsNotNull(function1, "onDelete");
        Intrinsics.checkParameterIsNotNull(function2, "onChange");
        this.onDelete = function1;
        this.onChange = function2;
    }

    public PaymentMethodVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_edit, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "itemView");
        return new PaymentMethodVH(this, inflate);
    }

    public void onBindViewHolder(PaymentMethodVH paymentMethodVH, int i) {
        Intrinsics.checkParameterIsNotNull(paymentMethodVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        paymentMethodVH.bind((Card) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/EditCardsAdapter$PaymentMethodVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/account/EditCardsAdapter;Landroid/view/View;)V", "bind", "", "card", "Lcom/forasoft/homewavvisitor/model/data/Card;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: EditCardsAdapter.kt */
    public final class PaymentMethodVH extends RecyclerView.ViewHolder {
        final /* synthetic */ EditCardsAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PaymentMethodVH(EditCardsAdapter editCardsAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = editCardsAdapter;
        }

        public final void bind(Card card) {
            Intrinsics.checkParameterIsNotNull(card, "card");
            View view = this.itemView;
            Glide.with(view.getContext()).load(Integer.valueOf(card.getImageResources())).into((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_card));
            TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_number);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_number");
            textView.setText(view.getResources().getString(R.string.label_card_numbers, new Object[]{card.getLast4()}));
            CheckBox checkBox = (CheckBox) view.findViewById(com.forasoft.homewavvisitor.R.id.cb_default);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "cb_default");
            checkBox.setChecked(card.isDefault());
            TextView textView2 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_delete);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_delete");
            textView2.setOnClickListener(new EditCardsAdapter$PaymentMethodVH$inlined$sam$i$android_view_View_OnClickListener$0(new EditCardsAdapter$PaymentMethodVH$bind$$inlined$with$lambda$1(this, card)));
            ((CheckBox) view.findViewById(com.forasoft.homewavvisitor.R.id.cb_default)).setOnCheckedChangeListener(new EditCardsAdapter$PaymentMethodVH$bind$$inlined$with$lambda$2(this, card));
        }
    }
}
