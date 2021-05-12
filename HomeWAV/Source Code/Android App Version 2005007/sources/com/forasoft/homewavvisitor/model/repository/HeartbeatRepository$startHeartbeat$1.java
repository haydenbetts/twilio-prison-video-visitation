package com.forasoft.homewavvisitor.model.repository;

import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: HeartbeatRepository.kt */
final class HeartbeatRepository$startHeartbeat$1<T> implements Consumer<Long> {
    final /* synthetic */ HeartbeatRepository this$0;

    HeartbeatRepository$startHeartbeat$1(HeartbeatRepository heartbeatRepository) {
        this.this$0 = heartbeatRepository;
    }

    public final void accept(Long l) {
        this.this$0.doHeartbeat();
    }
}
