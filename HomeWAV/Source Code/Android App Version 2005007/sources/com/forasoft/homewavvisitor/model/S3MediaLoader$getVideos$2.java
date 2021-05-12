package com.forasoft.homewavvisitor.model;

import com.google.android.exoplayer2.util.MimeTypes;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/Video;", "it", "", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: S3MediaLoader.kt */
final class S3MediaLoader$getVideos$2<T, R> implements Function<T, R> {
    public static final S3MediaLoader$getVideos$2 INSTANCE = new S3MediaLoader$getVideos$2();

    S3MediaLoader$getVideos$2() {
    }

    public final List<Video> apply(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "it");
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String video : iterable) {
            arrayList.add(new Video(video, MimeTypes.VIDEO_MP4, 0));
        }
        return (List) arrayList;
    }
}
