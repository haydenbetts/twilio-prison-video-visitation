package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoRecordFragment.kt */
final class VideoRecordFragment$onViewCreated$3 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ VideoRecordFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoRecordFragment$onViewCreated$3(VideoRecordFragment videoRecordFragment) {
        super(1);
        this.this$0 = videoRecordFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        CameraView access$getCamera$p = this.this$0.camera;
        if (access$getCamera$p != null) {
            access$getCamera$p.startRecording();
        }
        this.this$0.getPresenter().onRecordingStarted();
        CommonKt.hide((Button) this.this$0._$_findCachedViewById(R.id.btn_record));
        CommonKt.hide((Button) this.this$0._$_findCachedViewById(R.id.btn_rotate));
        CommonKt.hide((Button) this.this$0._$_findCachedViewById(R.id.btn_take_photo));
        CommonKt.show((Button) this.this$0._$_findCachedViewById(R.id.btn_stop));
        CommonKt.show((TextView) this.this$0._$_findCachedViewById(R.id.tv_remaining));
    }
}
