package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.os.Bundle;
import com.forasoft.homewavvisitor.model.MediaLoader;
import com.forasoft.homewavvisitor.model.MediaStoreMediaLoader;
import com.forasoft.homewavvisitor.model.S3MediaLoader;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/MediaChooserFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MediaChooserFragment.kt */
public final class MediaChooserFragment$installModules$1 extends Module {
    final /* synthetic */ MediaChooserFragment this$0;

    MediaChooserFragment$installModules$1(MediaChooserFragment mediaChooserFragment) {
        this.this$0 = mediaChooserFragment;
        Binding<T>.CanBeBound withName = bind(String.class).withName(InmateId.class);
        Bundle arguments = mediaChooserFragment.getArguments();
        String str = null;
        withName.toInstance(arguments != null ? arguments.getString("inmate id") : null);
        Bundle arguments2 = mediaChooserFragment.getArguments();
        str = arguments2 != null ? arguments2.getString("media loader") : str;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -16919206) {
                if (hashCode == 535807671 && str.equals(MediaChooserFragment.S3_MEDIA_LOADER)) {
                    bind(MediaLoader.class).to(S3MediaLoader.class);
                }
            } else if (str.equals(MediaChooserFragment.MEDIA_STORE_MEDIA_LOADER)) {
                bind(MediaLoader.class).to(MediaStoreMediaLoader.class);
            }
        }
    }
}
