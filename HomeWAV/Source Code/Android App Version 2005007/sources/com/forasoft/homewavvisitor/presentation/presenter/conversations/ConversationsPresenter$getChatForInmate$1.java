package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Function;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "it", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/Message;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$getChatForInmate$1<T, R> implements Function<T, R> {
    final /* synthetic */ Inmate $inmate;

    ConversationsPresenter$getChatForInmate$1(Inmate inmate) {
        this.$inmate = inmate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000e, code lost:
        r0 = (com.forasoft.homewavvisitor.model.data.Message) kotlin.collections.CollectionsKt.lastOrNull(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.forasoft.homewavvisitor.model.data.Chat apply(com.forasoft.homewavvisitor.model.server.response.Response<? extends java.util.List<com.forasoft.homewavvisitor.model.data.Message>> r4) {
        /*
            r3 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.Object r0 = r4.getBody()
            java.util.List r0 = (java.util.List) r0
            r1 = 0
            if (r0 == 0) goto L_0x001b
            java.lang.Object r0 = kotlin.collections.CollectionsKt.lastOrNull(r0)
            com.forasoft.homewavvisitor.model.data.Message r0 = (com.forasoft.homewavvisitor.model.data.Message) r0
            if (r0 == 0) goto L_0x001b
            java.lang.String r0 = r0.getInmateId()
            goto L_0x001c
        L_0x001b:
            r0 = r1
        L_0x001c:
            com.forasoft.homewavvisitor.model.data.Inmate r2 = r3.$inmate
            java.lang.String r2 = r2.getId()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x003c
            com.forasoft.homewavvisitor.model.data.Chat r0 = new com.forasoft.homewavvisitor.model.data.Chat
            com.forasoft.homewavvisitor.model.data.Inmate r1 = r3.$inmate
            java.lang.Object r4 = r4.getBody()
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r4 = kotlin.collections.CollectionsKt.lastOrNull(r4)
            com.forasoft.homewavvisitor.model.data.Message r4 = (com.forasoft.homewavvisitor.model.data.Message) r4
            r0.<init>(r1, r4)
            goto L_0x0043
        L_0x003c:
            com.forasoft.homewavvisitor.model.data.Chat r0 = new com.forasoft.homewavvisitor.model.data.Chat
            com.forasoft.homewavvisitor.model.data.Inmate r4 = r3.$inmate
            r0.<init>(r4, r1)
        L_0x0043:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationsPresenter$getChatForInmate$1.apply(com.forasoft.homewavvisitor.model.server.response.Response):com.forasoft.homewavvisitor.model.data.Chat");
    }
}
