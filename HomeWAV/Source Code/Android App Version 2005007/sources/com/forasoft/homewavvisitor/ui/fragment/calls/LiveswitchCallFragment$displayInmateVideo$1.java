package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.widget.FrameLayout;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.model.liveswitch.RemoteMedia;
import fm.liveswitch.android.LayoutManager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallFragment.kt */
final class LiveswitchCallFragment$displayInmateVideo$1 implements Runnable {
    final /* synthetic */ RemoteMedia $remoteMedia;
    final /* synthetic */ LiveswitchCallFragment this$0;

    LiveswitchCallFragment$displayInmateVideo$1(LiveswitchCallFragment liveswitchCallFragment, RemoteMedia remoteMedia) {
        this.this$0 = liveswitchCallFragment;
        this.$remoteMedia = remoteMedia;
    }

    public final void run() {
        LayoutManager access$getRemoteLayoutManager$p;
        if (this.this$0.remoteLayoutManager == null) {
            LiveswitchCallFragment liveswitchCallFragment = this.this$0;
            liveswitchCallFragment.remoteLayoutManager = new LayoutManager((FrameLayout) liveswitchCallFragment._$_findCachedViewById(R.id.inmate_video_view));
        }
        if (this.$remoteMedia.getView() != null && (access$getRemoteLayoutManager$p = this.this$0.remoteLayoutManager) != null) {
            access$getRemoteLayoutManager$p.addRemoteView(this.$remoteMedia.getId(), this.$remoteMedia.getView());
        }
    }
}
