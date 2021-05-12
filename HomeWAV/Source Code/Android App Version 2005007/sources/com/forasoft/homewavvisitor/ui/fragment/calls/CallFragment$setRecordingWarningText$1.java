package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallFragment.kt */
final class CallFragment$setRecordingWarningText$1 implements Runnable {
    final /* synthetic */ int $englishResId;
    final /* synthetic */ int $spanishResId;
    final /* synthetic */ CallFragment this$0;

    CallFragment$setRecordingWarningText$1(CallFragment callFragment, int i, int i2) {
        this.this$0 = callFragment;
        this.$englishResId = i;
        this.$spanishResId = i2;
    }

    public final void run() {
        ((TextView) this.this$0._$_findCachedViewById(R.id.tv_recording_warning)).setText(this.$englishResId);
        ((TextView) this.this$0._$_findCachedViewById(R.id.tv_recording_warning_es)).setText(this.$spanishResId);
    }
}
