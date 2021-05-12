package com.forasoft.homewavvisitor.ui.fragment.account;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/QuestionVH;", "Lcom/thoughtbot/expandablerecyclerview/viewholders/GroupViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "question", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Question;", "collapse", "expand", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: QuestionVH.kt */
public final class QuestionVH extends GroupViewHolder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QuestionVH(View view) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
    }

    public final void bind(Question question) {
        Intrinsics.checkParameterIsNotNull(question, "question");
        TextView textView = (TextView) this.itemView.findViewById(R.id.tv_question);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_question");
        textView.setText(question.getTitle());
    }

    public void expand() {
        RotateAnimation rotateAnimation = new RotateAnimation(360.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        View view = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_arrow);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "itemView.iv_arrow");
        imageView.setAnimation(rotateAnimation);
    }

    public void collapse() {
        RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        View view = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_arrow);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "itemView.iv_arrow");
        imageView.setAnimation(rotateAnimation);
    }
}
