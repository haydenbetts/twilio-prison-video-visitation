package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.view.MenuItem;
import com.forasoft.homewavvisitor.presentation.adapter.gallery.MediaAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: MediaChooserFragment.kt */
final class MediaChooserFragment$showMedia$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ MediaAdapter $adapter;
    final /* synthetic */ MediaChooserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaChooserFragment$showMedia$1(MediaChooserFragment mediaChooserFragment, MediaAdapter mediaAdapter) {
        super(1);
        this.this$0 = mediaChooserFragment;
        this.$adapter = mediaAdapter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        MenuItem access$getBtnSend$p = this.this$0.btnSend;
        if (access$getBtnSend$p != null) {
            access$getBtnSend$p.setVisible(this.$adapter.getSelectedItemPosition() != -1);
        }
    }
}
