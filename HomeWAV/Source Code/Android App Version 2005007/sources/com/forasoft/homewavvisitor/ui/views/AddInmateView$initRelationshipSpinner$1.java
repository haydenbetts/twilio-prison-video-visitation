package com.forasoft.homewavvisitor.ui.views;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "position", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$initRelationshipSpinner$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ AddInmateView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddInmateView$initRelationshipSpinner$1(AddInmateView addInmateView) {
        super(1);
        this.this$0 = addInmateView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        if (i == CollectionsKt.getLastIndex(this.this$0.getConstants().getRelationshipList())) {
            this.this$0.getGroupExplain().setVisibility(0);
        } else {
            this.this$0.getGroupExplain().setVisibility(8);
        }
        this.this$0.getConnection().setRelationshipId(i);
        this.this$0.getConnection().setRelationship(this.this$0.getConstants().getRelationshipList().get(i));
        this.this$0.checkCompleteness();
    }
}
