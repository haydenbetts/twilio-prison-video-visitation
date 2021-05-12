package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001aV\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003 \u0004*\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004**\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003 \u0004*\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lkotlin/Pair;", "", "kotlin.jvm.PlatformType", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: S3MediaLoader.kt */
final class S3MediaLoader$loadMedia$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ S3MediaLoader this$0;

    S3MediaLoader$loadMedia$1(S3MediaLoader s3MediaLoader) {
        this.this$0 = s3MediaLoader;
    }

    public final Single<Pair<Boolean, Boolean>> apply(final Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        HomewavApi access$getApi$p = this.this$0.api;
        String prison_id = inmate.getPrison_id();
        if (prison_id == null) {
            Intrinsics.throwNpe();
        }
        return access$getApi$p.getFacility(prison_id).map(new Function<T, R>() {
            public final Pair<Boolean, Boolean> apply(ApiResponse<Facility> apiResponse) {
                Intrinsics.checkParameterIsNotNull(apiResponse, "it");
                Facility body = apiResponse.getBody();
                return TuplesKt.to(Boolean.valueOf(!Intrinsics.areEqual((Object) body != null ? body.getMax_s3_video_message_length() : null, (Object) "0")), Boolean.valueOf(inmate.getCan_image_message()));
            }
        });
    }
}
