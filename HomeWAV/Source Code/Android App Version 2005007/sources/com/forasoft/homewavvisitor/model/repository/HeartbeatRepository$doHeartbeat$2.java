package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.server.response.Response;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/data/State;", "it", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: HeartbeatRepository.kt */
final class HeartbeatRepository$doHeartbeat$2<T, R> implements Function<T, R> {
    public static final HeartbeatRepository$doHeartbeat$2 INSTANCE = new HeartbeatRepository$doHeartbeat$2();

    HeartbeatRepository$doHeartbeat$2() {
    }

    public final State apply(Response<State> response) {
        Intrinsics.checkParameterIsNotNull(response, "it");
        State body = response.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        return body;
    }
}
