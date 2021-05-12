package com.forasoft.homewavvisitor.presentation.adapter.messages;

import android.view.View;
import android.widget.ProgressBar;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J4\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J<\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\u000f"}, d2 = {"com/forasoft/homewavvisitor/presentation/adapter/messages/InmateGifMessageVH$bind$1$1", "Lcom/bumptech/glide/request/RequestListener;", "", "Lcom/bumptech/glide/load/resource/drawable/GlideDrawable;", "onException", "", "e", "Ljava/lang/Exception;", "model", "target", "Lcom/bumptech/glide/request/target/Target;", "isFirstResource", "onResourceReady", "resource", "isFromMemoryCache", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateGifMessageVH.kt */
public final class InmateGifMessageVH$bind$1$1 implements RequestListener<String, GlideDrawable> {
    final /* synthetic */ View $this_with;

    InmateGifMessageVH$bind$1$1(View view) {
        this.$this_with = view;
    }

    public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
        CommonKt.hide((ProgressBar) this.$this_with.findViewById(R.id.pb));
        return true;
    }

    public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
        CommonKt.hide((ProgressBar) this.$this_with.findViewById(R.id.pb));
        return false;
    }
}
