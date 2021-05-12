package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.ChatItem;
import io.reactivex.functions.Function3;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\n\n\u0002\b\u000b\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u00012\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u0002H\u00032\u0006\u0010\u0007\u001a\u0002H\u0004H\n¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"<anonymous>", "R", "T1", "T2", "T3", "t1", "t2", "t3", "apply", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/reactivex/rxkotlin/Flowables$combineLatest$3"}, k = 3, mv = {1, 1, 16})
/* compiled from: Flowables.kt */
public final class ConversationPresenter$showMessages$1$$special$$inlined$combineLatest$1<T1, T2, T3, R> implements Function3<T1, T2, T3, R> {
    public final R apply(T1 t1, T2 t2, T3 t3) {
        List list = (List) t3;
        List list2 = (List) t2;
        List list3 = (List) t1;
        SpreadBuilder spreadBuilder = new SpreadBuilder(3);
        Intrinsics.checkExpressionValueIsNotNull(list3, "messages");
        Object[] array = list3.toArray(new ChatItem[0]);
        if (array != null) {
            spreadBuilder.addSpread(array);
            Intrinsics.checkExpressionValueIsNotNull(list2, "notifications");
            Object[] array2 = list2.toArray(new ChatItem[0]);
            if (array2 != null) {
                spreadBuilder.addSpread(array2);
                Intrinsics.checkExpressionValueIsNotNull(list, "calls");
                Object[] array3 = list.toArray(new ChatItem[0]);
                if (array3 != null) {
                    spreadBuilder.addSpread(array3);
                    return CollectionsKt.listOf((ChatItem[]) spreadBuilder.toArray(new ChatItem[spreadBuilder.size()]));
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
