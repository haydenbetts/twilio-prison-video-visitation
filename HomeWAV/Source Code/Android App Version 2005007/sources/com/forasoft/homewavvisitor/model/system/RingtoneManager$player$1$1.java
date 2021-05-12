package com.forasoft.homewavvisitor.model.system;

import android.media.MediaPlayer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/media/MediaPlayer;", "kotlin.jvm.PlatformType", "onPrepared"}, k = 3, mv = {1, 1, 16})
/* compiled from: RingtoneManager.kt */
final class RingtoneManager$player$1$1 implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ MediaPlayer $this_apply;

    RingtoneManager$player$1$1(MediaPlayer mediaPlayer) {
        this.$this_apply = mediaPlayer;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.$this_apply.start();
    }
}
