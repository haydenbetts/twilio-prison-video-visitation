package com.forasoft.homewavvisitor.presentation.adapter.gallery;

import com.forasoft.homewavvisitor.model.Media;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/gallery/MediaAdapter;", "Lcom/hannesdorfmann/adapterdelegates4/ListDelegationAdapter;", "", "Lcom/forasoft/homewavvisitor/model/Media;", "()V", "onItemSelectedListener", "Lkotlin/Function1;", "", "", "getOnItemSelectedListener", "()Lkotlin/jvm/functions/Function1;", "setOnItemSelectedListener", "(Lkotlin/jvm/functions/Function1;)V", "<set-?>", "selectedItemPosition", "getSelectedItemPosition", "()I", "onSelectedPositionChanged", "position", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MediaAdapter.kt */
public final class MediaAdapter extends ListDelegationAdapter<List<? extends Media>> {
    private Function1<? super Integer, Unit> onItemSelectedListener;
    /* access modifiers changed from: private */
    public int selectedItemPosition = -1;

    public MediaAdapter() {
        MediaAdapter mediaAdapter = this;
        this.delegatesManager.addDelegate(new ImageDelegate(new Function1<Integer, Unit>(mediaAdapter) {
            public final String getName() {
                return "onSelectedPositionChanged";
            }

            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(MediaAdapter.class);
            }

            public final String getSignature() {
                return "onSelectedPositionChanged(I)V";
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                ((MediaAdapter) this.receiver).onSelectedPositionChanged(i);
            }
        }, new MutablePropertyReference0(mediaAdapter) {
            public String getName() {
                return "selectedItemPosition";
            }

            public KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(MediaAdapter.class);
            }

            public String getSignature() {
                return "getSelectedItemPosition()I";
            }

            public Object get() {
                return Integer.valueOf(((MediaAdapter) this.receiver).getSelectedItemPosition());
            }

            public void set(Object obj) {
                ((MediaAdapter) this.receiver).selectedItemPosition = ((Number) obj).intValue();
            }
        })).addDelegate(new VideoDelegate(new Function1<Integer, Unit>(mediaAdapter) {
            public final String getName() {
                return "onSelectedPositionChanged";
            }

            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(MediaAdapter.class);
            }

            public final String getSignature() {
                return "onSelectedPositionChanged(I)V";
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                ((MediaAdapter) this.receiver).onSelectedPositionChanged(i);
            }
        }, new MutablePropertyReference0(mediaAdapter) {
            public String getName() {
                return "selectedItemPosition";
            }

            public KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(MediaAdapter.class);
            }

            public String getSignature() {
                return "getSelectedItemPosition()I";
            }

            public Object get() {
                return Integer.valueOf(((MediaAdapter) this.receiver).getSelectedItemPosition());
            }

            public void set(Object obj) {
                ((MediaAdapter) this.receiver).selectedItemPosition = ((Number) obj).intValue();
            }
        }));
    }

    public final int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    public final Function1<Integer, Unit> getOnItemSelectedListener() {
        return this.onItemSelectedListener;
    }

    public final void setOnItemSelectedListener(Function1<? super Integer, Unit> function1) {
        this.onItemSelectedListener = function1;
    }

    /* access modifiers changed from: private */
    public final void onSelectedPositionChanged(int i) {
        int i2 = this.selectedItemPosition;
        if (i2 == -1) {
            this.selectedItemPosition = i;
            notifyItemChanged(i, true);
        } else if (i2 == i) {
            notifyItemChanged(i2, false);
            this.selectedItemPosition = -1;
        } else {
            notifyItemChanged(i2, false);
            this.selectedItemPosition = i;
            notifyItemChanged(i, true);
        }
        Function1<? super Integer, Unit> function1 = this.onItemSelectedListener;
        if (function1 != null) {
            Unit invoke = function1.invoke(Integer.valueOf(this.selectedItemPosition));
        }
    }
}
