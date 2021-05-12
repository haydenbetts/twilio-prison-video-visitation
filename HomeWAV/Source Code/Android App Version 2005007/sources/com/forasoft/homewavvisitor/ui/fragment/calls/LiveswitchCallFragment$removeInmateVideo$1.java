package com.forasoft.homewavvisitor.ui.fragment.calls;

import fm.liveswitch.android.LayoutManager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallFragment.kt */
final class LiveswitchCallFragment$removeInmateVideo$1 implements Runnable {
    final /* synthetic */ String $mediaId;
    final /* synthetic */ LiveswitchCallFragment this$0;

    LiveswitchCallFragment$removeInmateVideo$1(LiveswitchCallFragment liveswitchCallFragment, String str) {
        this.this$0 = liveswitchCallFragment;
        this.$mediaId = str;
    }

    public final void run() {
        LayoutManager access$getRemoteLayoutManager$p;
        if (this.this$0.remoteLayoutManager != null && (access$getRemoteLayoutManager$p = this.this$0.remoteLayoutManager) != null) {
            access$getRemoteLayoutManager$p.removeRemoteView(this.$mediaId);
        }
    }
}
