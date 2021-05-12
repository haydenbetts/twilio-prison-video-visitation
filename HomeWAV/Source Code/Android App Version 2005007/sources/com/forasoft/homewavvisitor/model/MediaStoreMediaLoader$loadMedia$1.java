package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.rxkotlin.Observables;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "Lcom/forasoft/homewavvisitor/model/Media;", "kotlin.jvm.PlatformType", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: MediaStoreMediaLoader.kt */
final class MediaStoreMediaLoader$loadMedia$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ MediaStoreMediaLoader this$0;

    MediaStoreMediaLoader$loadMedia$1(MediaStoreMediaLoader mediaStoreMediaLoader) {
        this.this$0 = mediaStoreMediaLoader;
    }

    public final Single<List<Media>> apply(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        if (!inmate.getCan_video_message()) {
            return this.this$0.getImages().singleOrError();
        }
        Observables observables = Observables.INSTANCE;
        Observable zip = Observable.zip(this.this$0.getVideos(inmate), this.this$0.getImages(), new MediaStoreMediaLoader$loadMedia$1$$special$$inlined$zip$1());
        if (zip == null) {
            Intrinsics.throwNpe();
        }
        return zip.singleOrError();
    }
}
