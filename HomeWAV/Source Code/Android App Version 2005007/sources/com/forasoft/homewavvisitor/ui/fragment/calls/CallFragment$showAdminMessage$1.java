package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.os.Handler;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.work.WorkRequest;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallFragment.kt */
final class CallFragment$showAdminMessage$1 implements Runnable {
    final /* synthetic */ String $message;
    final /* synthetic */ CallFragment this$0;

    CallFragment$showAdminMessage$1(CallFragment callFragment, String str) {
        this.this$0 = callFragment;
        this.$message = str;
    }

    public final void run() {
        CommonKt.show((CardView) this.this$0._$_findCachedViewById(R.id.cv_admin));
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_admin_message);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_admin_message");
        textView.setText(this.$message);
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ CallFragment$showAdminMessage$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                CommonKt.hide((CardView) this.this$0.this$0._$_findCachedViewById(R.id.cv_admin));
            }
        }, WorkRequest.MIN_BACKOFF_MILLIS);
    }
}
