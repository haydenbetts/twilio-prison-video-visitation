package com.forasoft.homewavvisitor.extension;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/extension/CommonKt$loadRoundedImage$1", "Lcom/bumptech/glide/request/target/BitmapImageViewTarget;", "setResource", "", "resource", "Landroid/graphics/Bitmap;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: common.kt */
public final class CommonKt$loadRoundedImage$1 extends BitmapImageViewTarget {
    final /* synthetic */ ImageView $this_loadRoundedImage;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CommonKt$loadRoundedImage$1(ImageView imageView, ImageView imageView2) {
        super(imageView2);
        this.$this_loadRoundedImage = imageView;
    }

    /* access modifiers changed from: protected */
    public void setResource(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "resource");
        Context context = this.$this_loadRoundedImage.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        Intrinsics.checkExpressionValueIsNotNull(create, "RoundedBitmapDrawableFac…text.resources, resource)");
        create.setCircular(true);
        this.$this_loadRoundedImage.setImageDrawable(create);
    }
}
