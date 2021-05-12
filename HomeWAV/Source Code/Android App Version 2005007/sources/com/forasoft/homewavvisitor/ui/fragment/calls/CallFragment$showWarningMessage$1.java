package com.forasoft.homewavvisitor.ui.fragment.calls;

import androidx.cardview.widget.CardView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallFragment.kt */
final class CallFragment$showWarningMessage$1 implements Runnable {
    final /* synthetic */ CallFragment this$0;

    CallFragment$showWarningMessage$1(CallFragment callFragment) {
        this.this$0 = callFragment;
    }

    public final void run() {
        CommonKt.show((CardView) this.this$0._$_findCachedViewById(R.id.cv_warning));
        CommonKt.startBlinkingAnimation((CardView) this.this$0._$_findCachedViewById(R.id.cv_warning));
    }
}
