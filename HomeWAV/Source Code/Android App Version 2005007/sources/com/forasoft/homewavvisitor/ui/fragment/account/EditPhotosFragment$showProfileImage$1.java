package com.forasoft.homewavvisitor.ui.fragment.account;

import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J:\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00022\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J<\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u00022\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/account/EditPhotosFragment$showProfileImage$1", "Lcom/bumptech/glide/request/RequestListener;", "Landroid/net/Uri;", "Lcom/bumptech/glide/load/resource/drawable/GlideDrawable;", "onException", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "target", "Lcom/bumptech/glide/request/target/Target;", "isFirstResource", "onResourceReady", "resource", "isFromMemoryCache", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EditPhotosFragment.kt */
public final class EditPhotosFragment$showProfileImage$1 implements RequestListener<Uri, GlideDrawable> {
    final /* synthetic */ EditPhotosFragment this$0;

    EditPhotosFragment$showProfileImage$1(EditPhotosFragment editPhotosFragment) {
        this.this$0 = editPhotosFragment;
    }

    public boolean onException(Exception exc, Uri uri, Target<GlideDrawable> target, boolean z) {
        CommonKt.show((ImageView) this.this$0._$_findCachedViewById(R.id.imageAddPhotoIcon));
        CommonKt.hide((ImageView) this.this$0._$_findCachedViewById(R.id.imageReuploadUserPhoto));
        return true;
    }

    public boolean onResourceReady(GlideDrawable glideDrawable, Uri uri, Target<GlideDrawable> target, boolean z, boolean z2) {
        CommonKt.hide((ImageView) this.this$0._$_findCachedViewById(R.id.imageAddPhotoIcon));
        CommonKt.show((ImageView) this.this$0._$_findCachedViewById(R.id.imageReuploadUserPhoto));
        return false;
    }
}
