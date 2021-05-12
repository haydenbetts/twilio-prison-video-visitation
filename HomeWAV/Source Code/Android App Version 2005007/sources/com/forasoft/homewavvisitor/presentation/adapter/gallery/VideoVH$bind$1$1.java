package com.forasoft.homewavvisitor.presentation.adapter.gallery;

import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.forasoft.homewavvisitor.model.Mp4DataFetcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/Mp4DataFetcher;", "url", "", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "getResourceFetcher"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoVH.kt */
final class VideoVH$bind$1$1<T> implements StreamModelLoader<String> {
    public static final VideoVH$bind$1$1 INSTANCE = new VideoVH$bind$1$1();

    VideoVH$bind$1$1() {
    }

    public final Mp4DataFetcher getResourceFetcher(String str, int i, int i2) {
        Intrinsics.checkExpressionValueIsNotNull(str, "url");
        return new Mp4DataFetcher(str);
    }
}
