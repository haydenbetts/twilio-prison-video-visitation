package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.os.Bundle;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/VideoRecordFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VideoRecordFragment.kt */
public final class VideoRecordFragment$installModules$1 extends Module {
    final /* synthetic */ VideoRecordFragment this$0;

    VideoRecordFragment$installModules$1(VideoRecordFragment videoRecordFragment) {
        this.this$0 = videoRecordFragment;
        Binding<T>.CanBeBound withName = bind(String.class).withName(InmateId.class);
        Bundle arguments = videoRecordFragment.getArguments();
        withName.toInstance(arguments != null ? arguments.getString("inmate id") : null);
    }
}
