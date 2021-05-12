package com.forasoft.homewavvisitor.model.system;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\f\u0010\u0013\u001a\u00020\u0011*\u00020\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "audioManager", "Landroid/media/AudioManager;", "isPlaying", "", "player", "Landroid/media/MediaPlayer;", "ringtoneUri", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "vibrator", "Landroid/os/Vibrator;", "play", "", "stop", "vibrate", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RingtoneManager.kt */
public final class RingtoneManager {
    private final AudioManager audioManager;
    private boolean isPlaying;
    private final MediaPlayer player;
    private final Uri ringtoneUri;
    private final Vibrator vibrator;

    @Inject
    public RingtoneManager(Context context) {
        MediaPlayer mediaPlayer;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("audio");
        if (systemService != null) {
            this.audioManager = (AudioManager) systemService;
            Object systemService2 = context.getSystemService("vibrator");
            if (systemService2 != null) {
                this.vibrator = (Vibrator) systemService2;
                Uri defaultUri = android.media.RingtoneManager.getDefaultUri(1);
                this.ringtoneUri = defaultUri;
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setOnPreparedListener(new RingtoneManager$player$1$1(mediaPlayer));
                    mediaPlayer.setDataSource(context, defaultUri);
                    mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(6).setContentType(4).build());
                    mediaPlayer.setLooping(true);
                } catch (Exception unused) {
                    mediaPlayer = null;
                }
                this.player = mediaPlayer;
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
    }

    public final void play() {
        int ringerMode;
        if (!this.isPlaying && (ringerMode = this.audioManager.getRingerMode()) != 0) {
            if (ringerMode == 1) {
                vibrate(this.vibrator);
            } else if (ringerMode == 2) {
                vibrate(this.vibrator);
                MediaPlayer mediaPlayer = this.player;
                if (mediaPlayer != null) {
                    mediaPlayer.prepareAsync();
                }
            }
            this.isPlaying = true;
        }
    }

    public final void stop() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        this.vibrator.cancel();
        this.isPlaying = false;
    }

    private final void vibrate(Vibrator vibrator2) {
        long[] jArr = {200, 1000, 1000, 1000};
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator2.vibrate(VibrationEffect.createWaveform(jArr, 0));
        } else {
            vibrator2.vibrate(jArr, 0);
        }
    }
}
