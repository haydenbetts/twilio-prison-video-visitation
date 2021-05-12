package com.forasoft.homewavvisitor.ui.fragment.account;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.forasoft.homewavvisitor.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: TestVideoFragment.kt */
final class TestVideoFragment$onViewCreated$4 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ TestVideoFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestVideoFragment$onViewCreated$4(TestVideoFragment testVideoFragment) {
        super(1);
        this.this$0 = testVideoFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        ((VideoView) this.this$0._$_findCachedViewById(R.id.result_video)).setOnCompletionListener(new MediaPlayer.OnCompletionListener(this) {
            final /* synthetic */ TestVideoFragment$onViewCreated$4 this$0;

            {
                this.this$0 = r1;
            }

            public final void onCompletion(MediaPlayer mediaPlayer) {
                ConstraintLayout constraintLayout = (ConstraintLayout) this.this$0.this$0._$_findCachedViewById(R.id.video_holder);
                Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "video_holder");
                constraintLayout.setVisibility(8);
                ConstraintLayout constraintLayout2 = (ConstraintLayout) this.this$0.this$0._$_findCachedViewById(R.id.camera_preview);
                Intrinsics.checkExpressionValueIsNotNull(constraintLayout2, "camera_preview");
                constraintLayout2.setVisibility(0);
                Button button = (Button) this.this$0.this$0._$_findCachedViewById(R.id.see_result_button);
                Intrinsics.checkExpressionValueIsNotNull(button, "see_result_button");
                button.setVisibility(8);
                Button button2 = (Button) this.this$0.this$0._$_findCachedViewById(R.id.test_video_button);
                Intrinsics.checkExpressionValueIsNotNull(button2, "test_video_button");
                button2.setVisibility(0);
                TestVideoFragmentPermissionsDispatcher.setupCameraWithPermissionCheck(this.this$0.this$0);
            }
        });
        ((VideoView) this.this$0._$_findCachedViewById(R.id.result_video)).start();
    }
}
