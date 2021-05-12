package com.forasoft.homewavvisitor.ui.fragment.conversations;

import java.util.LinkedList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$recoverQueue$1", "Ljava/util/LinkedList;", "", "add", "", "element", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SwipeCallBack.kt */
public final class SwipeCallBack$recoverQueue$1 extends LinkedList<Integer> {
    SwipeCallBack$recoverQueue$1() {
    }

    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return add(((Number) obj).intValue());
    }

    public /* bridge */ boolean contains(Integer num) {
        return super.contains(num);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Integer) {
            return contains((Integer) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(Integer num) {
        return super.indexOf(num);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Integer) {
            return indexOf((Integer) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(Integer num) {
        return super.lastIndexOf(num);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Integer) {
            return lastIndexOf((Integer) obj);
        }
        return -1;
    }

    public final /* bridge */ Integer remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(Integer num) {
        return super.remove(num);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Integer) {
            return remove((Integer) obj);
        }
        return false;
    }

    public /* bridge */ Integer removeAt(int i) {
        return (Integer) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public boolean add(int i) {
        if (contains((Object) Integer.valueOf(i))) {
            return false;
        }
        return super.add(Integer.valueOf(i));
    }
}
