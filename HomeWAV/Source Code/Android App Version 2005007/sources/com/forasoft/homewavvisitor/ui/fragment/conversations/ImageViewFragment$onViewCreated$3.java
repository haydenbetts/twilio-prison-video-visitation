package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.widget.ProgressBar;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J:\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00022\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J<\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u00022\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/ImageViewFragment$onViewCreated$3", "Lcom/bumptech/glide/request/RequestListener;", "", "Lcom/bumptech/glide/load/resource/drawable/GlideDrawable;", "onException", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "target", "Lcom/bumptech/glide/request/target/Target;", "isFirstResource", "onResourceReady", "resource", "isFromMemoryCache", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageViewFragment.kt */
public final class ImageViewFragment$onViewCreated$3 implements RequestListener<String, GlideDrawable> {
    final /* synthetic */ ImageViewFragment this$0;

    ImageViewFragment$onViewCreated$3(ImageViewFragment imageViewFragment) {
        this.this$0 = imageViewFragment;
    }

    public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
        CommonKt.hide((ProgressBar) this.this$0._$_findCachedViewById(R.id.loader_indicator));
        return true;
    }

    public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
        CommonKt.hide((ProgressBar) this.this$0._$_findCachedViewById(R.id.loader_indicator));
        return false;
    }
}
