package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.media.MediaPlayer;
import android.widget.ProgressBar;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/media/MediaPlayer;", "kotlin.jvm.PlatformType", "onPrepared"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoViewFragment.kt */
final class VideoViewFragment$onViewCreated$4 implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ VideoViewFragment this$0;

    VideoViewFragment$onViewCreated$4(VideoViewFragment videoViewFragment) {
        this.this$0 = videoViewFragment;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        CommonKt.hide((ProgressBar) this.this$0._$_findCachedViewById(R.id.loader_indicator));
    }
}
