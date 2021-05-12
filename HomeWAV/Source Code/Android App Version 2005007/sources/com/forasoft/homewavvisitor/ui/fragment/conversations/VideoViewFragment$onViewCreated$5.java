package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.media.MediaPlayer;
import android.widget.ProgressBar;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "mp", "Landroid/media/MediaPlayer;", "kotlin.jvm.PlatformType", "what", "", "extra", "onError"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoViewFragment.kt */
final class VideoViewFragment$onViewCreated$5 implements MediaPlayer.OnErrorListener {
    final /* synthetic */ VideoViewFragment this$0;

    VideoViewFragment$onViewCreated$5(VideoViewFragment videoViewFragment) {
        this.this$0 = videoViewFragment;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        CommonKt.hide((ProgressBar) this.this$0._$_findCachedViewById(R.id.loader_indicator));
        return false;
    }
}
