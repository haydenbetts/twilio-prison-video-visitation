package com.forasoft.homewavvisitor.presentation.adapter.account;

import air.HomeWAV.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Card;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/CardsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Card;", "Lcom/forasoft/homewavvisitor/presentation/adapter/account/CardsAdapter$CardVH;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "CardVH", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CardsAdapter.kt */
public final class CardsAdapter extends ListAdapter<Card, CardVH> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public CardsAdapter() {
        super(Companion);
    }

    public CardVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "itemView");
        return new CardVH(inflate);
    }

    public void onBindViewHolder(CardVH cardVH, int i) {
        Intrinsics.checkParameterIsNotNull(cardVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        cardVH.bind((Card) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/CardsAdapter$Companion;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/Card;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CardsAdapter.kt */
    public static final class Companion extends DiffUtil.ItemCallback<Card> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public boolean areItemsTheSame(Card card, Card card2) {
            Intrinsics.checkParameterIsNotNull(card, "oldItem");
            Intrinsics.checkParameterIsNotNull(card2, "newItem");
            return Intrinsics.areEqual((Object) card.getToken(), (Object) card2.getToken());
        }

        public boolean areContentsTheSame(Card card, Card card2) {
            Intrinsics.checkParameterIsNotNull(card, "oldItem");
            Intrinsics.checkParameterIsNotNull(card2, "newItem");
            return Intrinsics.areEqual((Object) card, (Object) card2);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/CardsAdapter$CardVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "card", "Lcom/forasoft/homewavvisitor/model/data/Card;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CardsAdapter.kt */
    public static final class CardVH extends RecyclerView.ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CardVH(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
        }

        public final void bind(Card card) {
            Intrinsics.checkParameterIsNotNull(card, "card");
            View view = this.itemView;
            Glide.with(view.getContext()).load(Integer.valueOf(card.getImageResources())).into((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_card));
            TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_number);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_number");
            textView.setText(view.getResources().getString(R.string.label_card_numbers, new Object[]{card.getLast4()}));
            if (card.isDefault()) {
                CommonKt.show((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_default));
            } else {
                CommonKt.hide((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_default));
            }
        }
    }
}
