package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.ui.fragment.conversations.SwipeCallBack;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/ConversationListFragment$setupList$1$swipeCallback$1", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack;", "instantiateUnderlayButton", "", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "underlayButtons", "Ljava/util/ArrayList;", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$UnderlayButton;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationListFragment.kt */
public final class ConversationListFragment$setupList$$inlined$apply$lambda$2 extends SwipeCallBack {
    final /* synthetic */ RecyclerView $this_apply;
    final /* synthetic */ ConversationListFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ConversationListFragment$setupList$$inlined$apply$lambda$2(RecyclerView recyclerView, Context context, RecyclerView recyclerView2, ConversationListFragment conversationListFragment) {
        super(context, recyclerView2);
        this.$this_apply = recyclerView;
        this.this$0 = conversationListFragment;
    }

    public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, ArrayList<SwipeCallBack.UnderlayButton> arrayList) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(arrayList, "underlayButtons");
        String string = this.this$0.getString(R.string.dialog_delete_button);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_delete_button)");
        Context context = this.$this_apply.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Bitmap bitmapFromVectorDrawable = ContextKt.getBitmapFromVectorDrawable(context, R.drawable.icon_delete);
        Context context2 = this.$this_apply.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        arrayList.add(new SwipeCallBack.UnderlayButton(string, bitmapFromVectorDrawable, ContextExtensionsKt.getColorResource(context2, R.color.colorAccent), new SwipeCallBack.UnderlayButtonClickListener(this) {
            final /* synthetic */ ConversationListFragment$setupList$$inlined$apply$lambda$2 this$0;

            {
                this.this$0 = r1;
            }

            public void onClick(int i) {
                RecyclerView recyclerView = (RecyclerView) this.this$0.this$0._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_conversations);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_conversations");
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null) {
                    this.this$0.this$0.showDeleteDialog(((ConversationsAdapter) adapter).getItem(i).getInmate());
                    RecyclerView.Adapter adapter2 = this.this$0.$this_apply.getAdapter();
                    if (adapter2 != null) {
                        ((ConversationsAdapter) adapter2).notifyItemChanged(i);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter");
                }
                throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter");
            }
        }));
    }
}
