package com.forasoft.homewavvisitor.ui.fragment.account;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/Question;", "Lcom/thoughtbot/expandablerecyclerview/models/ExpandableGroup;", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Answer;", "title", "", "answer", "(Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Question.kt */
public final class Question extends ExpandableGroup<Answer> {
    private final String answer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Question(String str, String str2) {
        super(str, CollectionsKt.listOf(new Answer(str2)));
        Intrinsics.checkParameterIsNotNull(str, "title");
        Intrinsics.checkParameterIsNotNull(str2, "answer");
        this.answer = str2;
    }

    public final String getAnswer() {
        return this.answer;
    }
}
