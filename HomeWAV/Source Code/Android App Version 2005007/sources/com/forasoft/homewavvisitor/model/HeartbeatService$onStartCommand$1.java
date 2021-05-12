package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.server.response.Response;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "Lcom/forasoft/homewavvisitor/model/data/State;", "test"}, k = 3, mv = {1, 1, 16})
/* compiled from: HeartbeatService.kt */
final class HeartbeatService$onStartCommand$1<T> implements Predicate<Response<? extends State>> {
    public static final HeartbeatService$onStartCommand$1 INSTANCE = new HeartbeatService$onStartCommand$1();

    HeartbeatService$onStartCommand$1() {
    }

    public final boolean test(Response<State> response) {
        Intrinsics.checkParameterIsNotNull(response, "it");
        return response.getBody() != null;
    }
}
