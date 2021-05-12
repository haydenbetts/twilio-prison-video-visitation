package com.forasoft.homewavvisitor.presentation.adapter.tutorials;

import air.HomeWAV.R;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/tutorials/TutorialVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "itemClickListener", "Lkotlin/Function1;", "", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "bind", "url", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TutorialVH.kt */
public final class TutorialVH extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Function1<String, Unit> itemClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TutorialVH(View view, Function1<? super String, Unit> function1) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(function1, "itemClickListener");
        this.itemClickListener = function1;
    }

    public final void bind(String str) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        View view = this.itemView;
        int layoutPosition = getLayoutPosition();
        Glide.with(view.getContext()).load(Integer.valueOf(layoutPosition != 0 ? layoutPosition != 1 ? R.drawable.tutorial_button_3 : R.drawable.tutorial_button_2 : R.drawable.tutorial_button_1)).into((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_tutorial));
        ((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_tutorial)).setOnClickListener(new TutorialVH$bind$$inlined$with$lambda$1(this, str));
    }
}
