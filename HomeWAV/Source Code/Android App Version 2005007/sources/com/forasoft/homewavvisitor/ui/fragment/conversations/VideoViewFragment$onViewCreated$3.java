package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.media.MediaPlayer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/media/MediaPlayer;", "kotlin.jvm.PlatformType", "onCompletion"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoViewFragment.kt */
final class VideoViewFragment$onViewCreated$3 implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ VideoViewFragment this$0;

    VideoViewFragment$onViewCreated$3(VideoViewFragment videoViewFragment) {
        this.this$0 = videoViewFragment;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.this$0.onBackPressed();
    }
}
