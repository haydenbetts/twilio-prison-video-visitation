package com.forasoft.homewavvisitor.extension;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/MenuItem;", "kotlin.jvm.PlatformType", "onMenuItemClick", "com/forasoft/homewavvisitor/extension/ContextKt$initActionBack$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: context.kt */
final class ContextKt$initActionBack$$inlined$apply$lambda$1 implements Toolbar.OnMenuItemClickListener {
    final /* synthetic */ Function0 $itemListener$inlined;

    ContextKt$initActionBack$$inlined$apply$lambda$1(Function0 function0) {
        this.$itemListener$inlined = function0;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return ((Boolean) this.$itemListener$inlined.invoke()).booleanValue();
    }
}
