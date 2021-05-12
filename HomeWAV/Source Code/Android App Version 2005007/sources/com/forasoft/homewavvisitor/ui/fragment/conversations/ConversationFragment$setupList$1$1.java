package com.forasoft.homewavvisitor.ui.fragment.conversations;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "count", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
final /* synthetic */ class ConversationFragment$setupList$1$1 extends FunctionReference implements Function1<Integer, Unit> {
    ConversationFragment$setupList$1$1(ConversationFragment conversationFragment) {
        super(1, conversationFragment);
    }

    public final String getName() {
        return "onMessageSelected";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ConversationFragment.class);
    }

    public final String getSignature() {
        return "onMessageSelected(I)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        ((ConversationFragment) this.receiver).onMessageSelected(i);
    }
}
