package com.forasoft.homewavvisitor.presentation.adapter.tutorials;

import air.HomeWAV.R;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/tutorials/TutorialsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "", "Lcom/forasoft/homewavvisitor/presentation/adapter/tutorials/TutorialVH;", "itemClickListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "TutorialsDiffCallback", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TutorialsAdapter.kt */
public final class TutorialsAdapter extends ListAdapter<String, TutorialVH> {
    public static final TutorialsDiffCallback TutorialsDiffCallback = new TutorialsDiffCallback((DefaultConstructorMarker) null);
    private final Function1<String, Unit> itemClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TutorialsAdapter(Function1<? super String, Unit> function1) {
        super(TutorialsDiffCallback);
        Intrinsics.checkParameterIsNotNull(function1, "itemClickListener");
        this.itemClickListener = function1;
    }

    public TutorialVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new TutorialVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_tutorial, false, 2, (Object) null), this.itemClickListener);
    }

    public void onBindViewHolder(TutorialVH tutorialVH, int i) {
        Intrinsics.checkParameterIsNotNull(tutorialVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        tutorialVH.bind((String) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/tutorials/TutorialsAdapter$TutorialsDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TutorialsAdapter.kt */
    public static final class TutorialsDiffCallback extends DiffUtil.ItemCallback<String> {
        private TutorialsDiffCallback() {
        }

        public /* synthetic */ TutorialsDiffCallback(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public boolean areContentsTheSame(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "oldItem");
            Intrinsics.checkParameterIsNotNull(str2, "newItem");
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }

        public boolean areItemsTheSame(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "oldItem");
            Intrinsics.checkParameterIsNotNull(str2, "newItem");
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }
    }
}
