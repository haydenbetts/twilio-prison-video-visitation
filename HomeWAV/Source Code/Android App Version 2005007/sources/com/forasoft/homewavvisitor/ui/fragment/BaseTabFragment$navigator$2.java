package com.forasoft.homewavvisitor.ui.fragment;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import ru.terrakok.cicerone.Navigator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lru/terrakok/cicerone/Navigator;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: BaseTabFragment.kt */
final class BaseTabFragment$navigator$2 extends Lambda implements Function0<Navigator> {
    final /* synthetic */ BaseTabFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseTabFragment$navigator$2(BaseTabFragment baseTabFragment) {
        super(0);
        this.this$0 = baseTabFragment;
    }

    public final Navigator invoke() {
        return this.this$0.createNavigator();
    }
}
