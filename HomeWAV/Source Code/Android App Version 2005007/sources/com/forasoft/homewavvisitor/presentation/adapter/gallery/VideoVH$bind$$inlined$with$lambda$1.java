package com.forasoft.homewavvisitor.presentation.adapter.gallery;

import android.view.View;
import com.forasoft.homewavvisitor.model.Video;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke", "com/forasoft/homewavvisitor/presentation/adapter/gallery/VideoVH$bind$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoVH.kt */
final class VideoVH$bind$$inlined$with$lambda$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ List $payload$inlined;
    final /* synthetic */ int $selectedItemPosition$inlined;
    final /* synthetic */ Video $video$inlined;
    final /* synthetic */ VideoVH this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoVH$bind$$inlined$with$lambda$1(VideoVH videoVH, List list, int i, Video video) {
        super(1);
        this.this$0 = videoVH;
        this.$payload$inlined = list;
        this.$selectedItemPosition$inlined = i;
        this.$video$inlined = video;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        this.this$0.onItemSelectedListener.invoke(Integer.valueOf(this.this$0.getAdapterPosition()));
    }
}
