package com.forasoft.homewavvisitor.ui.fragment.account;

import android.media.MediaRecorder;
import android.widget.ProgressBar;
import com.forasoft.homewavvisitor.R;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: TestVideoFragment.kt */
final class TestVideoFragment$startMicLevelShowing$1<T> implements Consumer<Long> {
    final /* synthetic */ TestVideoFragment this$0;

    TestVideoFragment$startMicLevelShowing$1(TestVideoFragment testVideoFragment) {
        this.this$0 = testVideoFragment;
    }

    public final void accept(Long l) {
        MediaRecorder access$getMicLevelRecorder$p = this.this$0.micLevelRecorder;
        int maxAmplitude = access$getMicLevelRecorder$p != null ? access$getMicLevelRecorder$p.getMaxAmplitude() : 0;
        if (((ProgressBar) this.this$0._$_findCachedViewById(R.id.mic_level)) != null) {
            ProgressBar progressBar = (ProgressBar) this.this$0._$_findCachedViewById(R.id.mic_level);
            Intrinsics.checkExpressionValueIsNotNull(progressBar, "mic_level");
            progressBar.setProgress(maxAmplitude);
        }
    }
}
