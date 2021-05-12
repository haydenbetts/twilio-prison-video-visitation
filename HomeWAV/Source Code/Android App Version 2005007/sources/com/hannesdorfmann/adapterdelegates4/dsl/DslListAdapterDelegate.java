package com.hannesdorfmann.adapterdelegates4.dsl;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u0002H\u0002*\u0004\b\u0001\u0010\u00022\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003B¹\u0001\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012Q\u0010\u0007\u001aM\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00010\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\b\u0012\u001d\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013\u00126\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\u0010\u0019J+\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00028\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0014¢\u0006\u0002\u0010\u001cJ1\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00028\u00002\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001bH\u0014¢\u0006\u0002\u0010!J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020$H\u0014J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020$H\u0014J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020$H\u0014J\u0010\u0010'\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020$H\u0014R%\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R>\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00180\u0015X\u0004¢\u0006\u0002\n\u0000RY\u0010\u0007\u001aM\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00010\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/hannesdorfmann/adapterdelegates4/dsl/DslListAdapterDelegate;", "I", "T", "Lcom/hannesdorfmann/adapterdelegates4/AbsListItemAdapterDelegate;", "Lcom/hannesdorfmann/adapterdelegates4/dsl/AdapterDelegateViewHolder;", "layout", "", "on", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "item", "", "items", "position", "", "initializerBlock", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "layoutInflater", "Lkotlin/Function2;", "Landroid/view/ViewGroup;", "parent", "Landroid/view/View;", "(ILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "isForViewType", "", "(Ljava/lang/Object;Ljava/util/List;I)Z", "onBindViewHolder", "holder", "payloads", "", "(Ljava/lang/Object;Lcom/hannesdorfmann/adapterdelegates4/dsl/AdapterDelegateViewHolder;Ljava/util/List;)V", "onCreateViewHolder", "onFailedToRecycleView", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onViewAttachedToWindow", "onViewDetachedFromWindow", "onViewRecycled", "kotlin-dsl_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ListAdapterDelegateDsl.kt */
public final class DslListAdapterDelegate<I extends T, T> extends AbsListItemAdapterDelegate<I, T, AdapterDelegateViewHolder<I>> {
    private final Function1<AdapterDelegateViewHolder<I>, Unit> initializerBlock;
    private final int layout;
    private final Function2<ViewGroup, Integer, View> layoutInflater;
    private final Function3<T, List<? extends T>, Integer, Boolean> on;

    public DslListAdapterDelegate(int i, Function3<? super T, ? super List<? extends T>, ? super Integer, Boolean> function3, Function1<? super AdapterDelegateViewHolder<I>, Unit> function1, Function2<? super ViewGroup, ? super Integer, ? extends View> function2) {
        Intrinsics.checkParameterIsNotNull(function3, DebugKt.DEBUG_PROPERTY_VALUE_ON);
        Intrinsics.checkParameterIsNotNull(function1, "initializerBlock");
        Intrinsics.checkParameterIsNotNull(function2, "layoutInflater");
        this.layout = i;
        this.on = function3;
        this.initializerBlock = function1;
        this.layoutInflater = function2;
    }

    /* access modifiers changed from: protected */
    public boolean isForViewType(T t, List<T> list, int i) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        return this.on.invoke(t, list, Integer.valueOf(i)).booleanValue();
    }

    /* access modifiers changed from: protected */
    public AdapterDelegateViewHolder<I> onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        AdapterDelegateViewHolder<I> adapterDelegateViewHolder = new AdapterDelegateViewHolder<>(this.layoutInflater.invoke(viewGroup, Integer.valueOf(this.layout)));
        this.initializerBlock.invoke(adapterDelegateViewHolder);
        return adapterDelegateViewHolder;
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(I i, AdapterDelegateViewHolder<I> adapterDelegateViewHolder, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(adapterDelegateViewHolder, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        if (i != null) {
            adapterDelegateViewHolder.set_item$kotlin_dsl_release(i);
            Function1<List<? extends Object>, Unit> function1 = adapterDelegateViewHolder.get_bind$kotlin_dsl_release();
            if (function1 != null) {
                Unit invoke = function1.invoke(list);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
    }

    /* access modifiers changed from: protected */
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Function0<Unit> function0 = ((AdapterDelegateViewHolder) viewHolder).get_onViewRecycled$kotlin_dsl_release();
        if (function0 != null) {
            Unit invoke = function0.invoke();
        }
    }

    /* access modifiers changed from: protected */
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Function0<Boolean> function0 = ((AdapterDelegateViewHolder) viewHolder).get_onFailedToRecycleView$kotlin_dsl_release();
        if (function0 == null) {
            return super.onFailedToRecycleView(viewHolder);
        }
        return function0.invoke().booleanValue();
    }

    /* access modifiers changed from: protected */
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Function0<Unit> function0 = ((AdapterDelegateViewHolder) viewHolder).get_onViewAttachedToWindow$kotlin_dsl_release();
        if (function0 != null) {
            Unit invoke = function0.invoke();
        }
    }

    /* access modifiers changed from: protected */
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Function0<Unit> function0 = ((AdapterDelegateViewHolder) viewHolder).get_onViewDetachedFromWindow$kotlin_dsl_release();
        if (function0 != null) {
            Unit invoke = function0.invoke();
        }
    }
}
