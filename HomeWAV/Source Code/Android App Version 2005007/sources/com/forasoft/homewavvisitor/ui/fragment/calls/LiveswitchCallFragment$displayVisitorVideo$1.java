package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.widget.FrameLayout;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.model.liveswitch.CameraLocalMedia;
import fm.liveswitch.android.LayoutManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallFragment.kt */
final class LiveswitchCallFragment$displayVisitorVideo$1 implements Runnable {
    final /* synthetic */ CameraLocalMedia $localMedia;
    final /* synthetic */ LiveswitchCallFragment this$0;

    LiveswitchCallFragment$displayVisitorVideo$1(LiveswitchCallFragment liveswitchCallFragment, CameraLocalMedia cameraLocalMedia) {
        this.this$0 = liveswitchCallFragment;
        this.$localMedia = cameraLocalMedia;
    }

    public final void run() {
        if (this.this$0.localLayoutManager == null) {
            LiveswitchCallFragment liveswitchCallFragment = this.this$0;
            liveswitchCallFragment.localLayoutManager = new LayoutManager((FrameLayout) liveswitchCallFragment._$_findCachedViewById(R.id.visitor_video_view));
        }
        if (this.$localMedia.getView() != null) {
            LayoutManager access$getLocalLayoutManager$p = this.this$0.localLayoutManager;
            if (access$getLocalLayoutManager$p == null) {
                Intrinsics.throwNpe();
            }
            access$getLocalLayoutManager$p.setLocalView(this.$localMedia.getView());
        }
    }
}
