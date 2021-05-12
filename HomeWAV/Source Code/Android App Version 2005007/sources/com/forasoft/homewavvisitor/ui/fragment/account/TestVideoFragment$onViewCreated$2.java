package com.forasoft.homewavvisitor.ui.fragment.account;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.ui.fragment.conversations.CameraView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: TestVideoFragment.kt */
final class TestVideoFragment$onViewCreated$2 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ TestVideoFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TestVideoFragment$onViewCreated$2(TestVideoFragment testVideoFragment) {
        super(1);
        this.this$0 = testVideoFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        this.this$0.releaseMicRecorder();
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.mic_level_layout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "mic_level_layout");
        linearLayout.setVisibility(8);
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_remaining);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_remaining");
        textView.setText(this.this$0.getResources().getString(air.HomeWAV.R.string.label_remaining_time, new Object[]{5}));
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tv_remaining);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_remaining");
        textView2.setVisibility(0);
        CameraView access$getCamera$p = this.this$0.camera;
        if (access$getCamera$p != null) {
            access$getCamera$p.startRecording();
        }
        this.this$0.getPresenter().onRecordingStarted();
        Button button = (Button) this.this$0._$_findCachedViewById(R.id.test_video_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "test_video_button");
        button.setVisibility(8);
        Button button2 = (Button) this.this$0._$_findCachedViewById(R.id.stop_video_button);
        Intrinsics.checkExpressionValueIsNotNull(button2, "stop_video_button");
        button2.setVisibility(0);
    }
}
