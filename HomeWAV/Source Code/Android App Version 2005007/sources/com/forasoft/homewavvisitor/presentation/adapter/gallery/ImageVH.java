package com.forasoft.homewavvisitor.presentation.adapter.gallery;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.model.Image;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ$\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/gallery/ImageVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "onItemSelectedListener", "Lkotlin/Function1;", "", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "bind", "image", "Lcom/forasoft/homewavvisitor/model/Image;", "selectedItemPosition", "payload", "", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageVH.kt */
public final class ImageVH extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Function1<Integer, Unit> onItemSelectedListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageVH(View view, Function1<? super Integer, Unit> function1) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(function1, "onItemSelectedListener");
        this.onItemSelectedListener = function1;
    }

    public final void bind(Image image, int i, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(image, "image");
        Intrinsics.checkParameterIsNotNull(list, "payload");
        View view = this.itemView;
        if (list.isEmpty()) {
            RadioButton radioButton = (RadioButton) view.findViewById(R.id.radioButton);
            Intrinsics.checkExpressionValueIsNotNull(radioButton, "radioButton");
            radioButton.setChecked(getAdapterPosition() == i);
            Glide.with(view.getContext()).load(image.getUri()).into((ImageView) view.findViewById(R.id.imageView));
        } else {
            RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.radioButton);
            Intrinsics.checkExpressionValueIsNotNull(radioButton2, "radioButton");
            Object first = CollectionsKt.first(list);
            if (first != null) {
                radioButton2.setChecked(((Boolean) first).booleanValue());
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
            }
        }
        view.setOnClickListener(new ImageVH$inlined$sam$i$android_view_View_OnClickListener$0(new ImageVH$bind$$inlined$with$lambda$1(this, list, i, image)));
    }
}
