package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.content.Context;
import android.media.AudioManager;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.appspot.apprtc.AppRTCAudioManager;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/AudioManagerHolder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "androidAudioManager", "Landroid/media/AudioManager;", "rtcAudioManager", "Lorg/appspot/apprtc/AppRTCAudioManager;", "destroy", "", "onAudioManagerDevicesChanged", "selectedAudioDevice", "Lorg/appspot/apprtc/AppRTCAudioManager$AudioDevice;", "availableAudioDevices", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AudioManagerHolder.kt */
public final class AudioManagerHolder {
    private final AudioManager androidAudioManager;
    private final AppRTCAudioManager rtcAudioManager;

    public AudioManagerHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("audio");
        if (systemService != null) {
            AudioManager audioManager = (AudioManager) systemService;
            this.androidAudioManager = audioManager;
            AppRTCAudioManager create = AppRTCAudioManager.create(context);
            Intrinsics.checkExpressionValueIsNotNull(create, "AppRTCAudioManager.create(context)");
            this.rtcAudioManager = create;
            create.start(new AppRTCAudioManager.AudioManagerEvents(this) {
                final /* synthetic */ AudioManagerHolder this$0;

                {
                    this.this$0 = r1;
                }

                public final void onAudioDeviceChanged(AppRTCAudioManager.AudioDevice audioDevice, Set<AppRTCAudioManager.AudioDevice> set) {
                    AudioManagerHolder audioManagerHolder = this.this$0;
                    Intrinsics.checkExpressionValueIsNotNull(set, "availableAudioDevices");
                    audioManagerHolder.onAudioManagerDevicesChanged(audioDevice, set);
                }
            });
            audioManager.setStreamVolume(0, (int) (((float) audioManager.getStreamVolume(0)) / ((float) audioManager.getStreamMaxVolume(0))), 0);
            audioManager.setMode(3);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
    }

    public final void destroy() {
        this.rtcAudioManager.stop();
        this.androidAudioManager.setMode(0);
    }

    /* access modifiers changed from: private */
    public final void onAudioManagerDevicesChanged(AppRTCAudioManager.AudioDevice audioDevice, Set<? extends AppRTCAudioManager.AudioDevice> set) {
        Iterable iterable = set;
        boolean z = false;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!Intrinsics.areEqual((Object) ((AppRTCAudioManager.AudioDevice) it.next()).name(), (Object) AppRTCAudioManager.AudioDevice.SPEAKER_PHONE.toString())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        this.androidAudioManager.setSpeakerphoneOn(!z);
    }
}
