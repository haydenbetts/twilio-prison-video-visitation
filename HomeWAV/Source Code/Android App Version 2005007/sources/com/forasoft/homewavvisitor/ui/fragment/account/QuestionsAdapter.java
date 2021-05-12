package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.view.ViewGroup;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0016J$\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/QuestionsAdapter;", "Lcom/thoughtbot/expandablerecyclerview/ExpandableRecyclerViewAdapter;", "Lcom/forasoft/homewavvisitor/ui/fragment/account/QuestionVH;", "Lcom/forasoft/homewavvisitor/ui/fragment/account/AnswerVH;", "questions", "", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Question;", "(Ljava/util/List;)V", "onBindChildViewHolder", "", "holder", "flatPosition", "", "group", "Lcom/thoughtbot/expandablerecyclerview/models/ExpandableGroup;", "childIndex", "onBindGroupViewHolder", "onCreateChildViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onCreateGroupViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: QuestionsAdapter.kt */
public final class QuestionsAdapter extends ExpandableRecyclerViewAdapter<QuestionVH, AnswerVH> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QuestionsAdapter(List<Question> list) {
        super(list);
        Intrinsics.checkParameterIsNotNull(list, "questions");
    }

    public QuestionVH onCreateGroupViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new QuestionVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_question, false, 2, (Object) null));
    }

    public AnswerVH onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new AnswerVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_answer, false, 2, (Object) null));
    }

    public void onBindGroupViewHolder(QuestionVH questionVH, int i, ExpandableGroup<?> expandableGroup) {
        Intrinsics.checkParameterIsNotNull(questionVH, "holder");
        Intrinsics.checkParameterIsNotNull(expandableGroup, "group");
        questionVH.bind((Question) expandableGroup);
    }

    public void onBindChildViewHolder(AnswerVH answerVH, int i, ExpandableGroup<?> expandableGroup, int i2) {
        Intrinsics.checkParameterIsNotNull(answerVH, "holder");
        Intrinsics.checkParameterIsNotNull(expandableGroup, "group");
        List items = ((Question) expandableGroup).getItems();
        Intrinsics.checkExpressionValueIsNotNull(items, "(group as Question).items");
        Object first = CollectionsKt.first(items);
        Intrinsics.checkExpressionValueIsNotNull(first, "(group as Question).items.first()");
        answerVH.bind((Answer) first);
    }
}
