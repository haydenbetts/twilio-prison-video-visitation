package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Lkotlin/ParameterName;", "name", "inmate", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final /* synthetic */ class ConversationPresenter$onFirstViewAttach$4 extends FunctionReference implements Function1<Inmate, Unit> {
    ConversationPresenter$onFirstViewAttach$4(ConversationView conversationView) {
        super(1, conversationView);
    }

    public final String getName() {
        return "setInmate";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ConversationView.class);
    }

    public final String getSignature() {
        return "setInmate(Lcom/forasoft/homewavvisitor/model/data/Inmate;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Inmate) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "p1");
        ((ConversationView) this.receiver).setInmate(inmate);
    }
}
