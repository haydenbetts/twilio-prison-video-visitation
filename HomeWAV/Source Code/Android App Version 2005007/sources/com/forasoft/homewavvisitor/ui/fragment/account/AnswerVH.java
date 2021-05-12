package com.forasoft.homewavvisitor.ui.fragment.account;

import android.view.View;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/AnswerVH;", "Lcom/thoughtbot/expandablerecyclerview/viewholders/ChildViewHolder;", "itemVIew", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "answer", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Answer;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AnswerVH.kt */
public final class AnswerVH extends ChildViewHolder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnswerVH(View view) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemVIew");
    }

    public final void bind(Answer answer) {
        Intrinsics.checkParameterIsNotNull(answer, "answer");
        TextView textView = (TextView) this.itemView.findViewById(R.id.tv_answer);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_answer");
        textView.setText(answer.getText());
    }
}
