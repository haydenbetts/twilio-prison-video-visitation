package com.forasoft.homewavvisitor.presentation.adapter.gallery;

import air.HomeWAV.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.forasoft.homewavvisitor.model.Media;
import com.forasoft.homewavvisitor.model.Video;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B'\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bJ&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0014J&\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010H\u0014J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/gallery/VideoDelegate;", "Lcom/hannesdorfmann/adapterdelegates4/AbsListItemAdapterDelegate;", "Lcom/forasoft/homewavvisitor/model/Video;", "Lcom/forasoft/homewavvisitor/model/Media;", "Lcom/forasoft/homewavvisitor/presentation/adapter/gallery/VideoVH;", "onItemSelectedListener", "Lkotlin/Function1;", "", "", "getSelectedItemPosition", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "isForViewType", "", "item", "items", "", "position", "onBindViewHolder", "video", "holder", "payload", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VideoDelegate.kt */
public final class VideoDelegate extends AbsListItemAdapterDelegate<Video, Media, VideoVH> {
    private final Function0<Integer> getSelectedItemPosition;
    private final Function1<Integer, Unit> onItemSelectedListener;

    public VideoDelegate(Function1<? super Integer, Unit> function1, Function0<Integer> function0) {
        Intrinsics.checkParameterIsNotNull(function1, "onItemSelectedListener");
        Intrinsics.checkParameterIsNotNull(function0, "getSelectedItemPosition");
        this.onItemSelectedListener = function1;
        this.getSelectedItemPosition = function0;
    }

    /* access modifiers changed from: protected */
    public VideoVH onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "itemView");
        return new VideoVH(inflate, this.onItemSelectedListener);
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(Video video, VideoVH videoVH, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(video, "video");
        Intrinsics.checkParameterIsNotNull(videoVH, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payload");
        videoVH.bind(video, this.getSelectedItemPosition.invoke().intValue(), list);
    }

    /* access modifiers changed from: protected */
    public boolean isForViewType(Media media, List<Media> list, int i) {
        Intrinsics.checkParameterIsNotNull(media, "item");
        Intrinsics.checkParameterIsNotNull(list, "items");
        return media instanceof Video;
    }
}
