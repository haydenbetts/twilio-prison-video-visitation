package com.forasoft.homewavvisitor.model;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.rxkotlin.Singles;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "Lcom/forasoft/homewavvisitor/model/Media;", "<name for destructuring parameter 0>", "Lkotlin/Pair;", "", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: S3MediaLoader.kt */
final class S3MediaLoader$loadMedia$2<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ S3MediaLoader this$0;

    S3MediaLoader$loadMedia$2(S3MediaLoader s3MediaLoader) {
        this.this$0 = s3MediaLoader;
    }

    public final Single<List<Media>> apply(Pair<Boolean, Boolean> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<name for destructuring parameter 0>");
        boolean booleanValue = pair.component1().booleanValue();
        boolean booleanValue2 = pair.component2().booleanValue();
        if (booleanValue && booleanValue2) {
            Singles singles = Singles.INSTANCE;
            Single<List<Media>> zip = Single.zip(this.this$0.getImages(), this.this$0.getVideos(), new S3MediaLoader$loadMedia$2$$special$$inlined$zip$1());
            Intrinsics.checkExpressionValueIsNotNull(zip, "Single.zip(s1, s2, BiFun…-> zipper.invoke(t, u) })");
            return zip;
        } else if (booleanValue && !booleanValue2) {
            return this.this$0.getVideos();
        } else {
            if (booleanValue2 && !booleanValue) {
                return this.this$0.getImages();
            }
            Single<List<Media>> never = Single.never();
            Intrinsics.checkExpressionValueIsNotNull(never, "Single.never()");
            return never;
        }
    }
}
