package com.forasoft.homewavvisitor.model;

import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: RxImagePicker.kt */
final class RxImagePicker$requestPickImage$1<T> implements Consumer<Boolean> {
    final /* synthetic */ RxImagePicker this$0;

    RxImagePicker$requestPickImage$1(RxImagePicker rxImagePicker) {
        this.this$0 = rxImagePicker;
    }

    public final void accept(Boolean bool) {
        this.this$0.pickImage();
    }
}
