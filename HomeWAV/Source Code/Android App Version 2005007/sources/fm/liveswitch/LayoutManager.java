package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class LayoutManager<T> extends LayoutPreset {
    /* access modifiers changed from: private */
    public List<IAction1<Layout>> __onLayout;
    private boolean _inBatch;
    private LayoutOrigin _layoutOrigin;
    private T _localView;
    private IAction1<Layout> _onLayout;
    private Object _remoteViewsLock;
    private HashMap<String, ArrayList<T>> _remoteViewsTable;

    /* access modifiers changed from: protected */
    public abstract void addView(T t);

    /* access modifiers changed from: protected */
    public abstract void dispatchToMainThread(IAction2<Object, Object> iAction2, Object obj, Object obj2);

    public abstract void layout();

    /* access modifiers changed from: protected */
    public abstract void removeView(T t);

    public void addOnLayout(IAction1<Layout> iAction1) {
        if (iAction1 != null) {
            if (this._onLayout == null) {
                this._onLayout = new IAction1<Layout>() {
                    public void invoke(Layout layout) {
                        Iterator it = new ArrayList(LayoutManager.this.__onLayout).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(layout);
                        }
                    }
                };
            }
            this.__onLayout.add(iAction1);
        }
    }

    public boolean addRemoteMedia(IViewableMedia<T> iViewableMedia) {
        if (iViewableMedia == null) {
            return false;
        }
        return addRemoteView(iViewableMedia.getId(), iViewableMedia.getView());
    }

    public boolean addRemoteView(String str, T t) {
        if (str == null) {
            Log.warn("Could not add remote view. The ID cannot be null.");
            return false;
        } else if (t == null) {
            Log.warn("Could not add remote view. The view cannot be null.");
            return false;
        } else {
            try {
                dispatchToMainThread(new IActionDelegate2<Object, Object>() {
                    public String getId() {
                        return "fm.liveswitch.LayoutManager<T>.addRemoteViewUI";
                    }

                    public void invoke(Object obj, Object obj2) {
                        LayoutManager.this.addRemoteViewUI(obj, obj2);
                    }
                }, str, t);
                return true;
            } catch (Exception e) {
                Log.error("Could not add remote view.", e);
                return true;
            }
        }
    }

    public boolean addRemoteViews(String[] strArr, T[] tArr) {
        if (strArr == null) {
            Log.warn("Could not add remote views. The IDs cannot be null.");
            return false;
        } else if (tArr == null) {
            Log.warn("Could not add remote views. The views cannot be null.");
            return false;
        } else if (ArrayExtensions.getLength((Object[]) strArr) != ArrayExtensions.getLength((Object[]) tArr)) {
            Log.warn("Could not add remote views. The number of IDs and views must match.");
            return false;
        } else {
            try {
                dispatchToMainThread(new IActionDelegate2<Object, Object>() {
                    public String getId() {
                        return "fm.liveswitch.LayoutManager<T>.addRemoteViewsUI";
                    }

                    public void invoke(Object obj, Object obj2) {
                        LayoutManager.this.addRemoteViewsUI(obj, obj2);
                    }
                }, strArr, tArr);
                return true;
            } catch (Exception e) {
                Log.error("Could not add remote views.", e);
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void addRemoteViewsUI(Object obj, Object obj2) {
        String[] strArr = (String[]) obj;
        Object[] objArr = (Object[]) obj2;
        synchronized (this._remoteViewsLock) {
            this._inBatch = true;
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
                addRemoteViewUI(strArr[i], objArr[i]);
            }
            this._inBatch = false;
            layout();
        }
    }

    /* access modifiers changed from: private */
    public void addRemoteViewUI(Object obj, Object obj2) {
        String str = (String) obj;
        synchronized (this._remoteViewsLock) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj2);
            ArrayList remoteViewsInternal = getRemoteViewsInternal(str);
            if (remoteViewsInternal != null) {
                ArrayListExtensions.addRange(arrayList, remoteViewsInternal);
            }
            addView(obj2);
            HashMapExtensions.set(HashMapExtensions.getItem(this._remoteViewsTable), str, arrayList);
            if (!this._inBatch) {
                layout();
            }
        }
    }

    /* access modifiers changed from: protected */
    public T doGetLocalView() {
        return this._localView;
    }

    /* access modifiers changed from: protected */
    public ArrayList<T> doGetRemoteViews(String str) {
        ArrayList<T> arrayList;
        synchronized (this._remoteViewsLock) {
            arrayList = null;
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._remoteViewsTable, str, holder);
            ArrayList<T> arrayList2 = (ArrayList) holder.getValue();
            if (tryGetValue) {
                arrayList = arrayList2;
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String[] doGetRemoteViewsIds() {
        ArrayList arrayList = new ArrayList();
        synchronized (this._remoteViewsLock) {
            for (String add : HashMapExtensions.getKeys(this._remoteViewsTable)) {
                arrayList.add(add);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private void doSwapRemoteView(String str, T t, String str2, T t2) {
        try {
            final T t3 = t;
            final String str3 = str;
            final String str4 = str2;
            final T t4 = t2;
            dispatchToMainThread(new IAction2<Object, Object>() {
                public void invoke(Object obj, Object obj2) {
                    Object obj3 = t3;
                    if (obj3 != null) {
                        LayoutManager.this.removeRemoteViewUI(str3, obj3);
                    }
                    LayoutManager.this.addRemoteViewUI(str4, t4);
                }
            }, (Object) null, (Object) null);
        } catch (Exception e) {
            Log.error("Could not swap remote view.", e);
        }
    }

    private void doSwapRemoteViews(String[] strArr, ArrayList<T> arrayList, String[] strArr2, T[] tArr) {
        try {
            final String[] strArr3 = strArr;
            final ArrayList<T> arrayList2 = arrayList;
            final String[] strArr4 = strArr2;
            final T[] tArr2 = tArr;
            dispatchToMainThread(new IAction2<Object, Object>() {
                public void invoke(Object obj, Object obj2) {
                    LayoutManager.this.removeRemoteViewsUI(strArr3, arrayList2);
                    LayoutManager.this.addRemoteViewsUI(strArr4, tArr2);
                }
            }, (Object) null, (Object) null);
        } catch (Exception e) {
            Log.error("Could not swap remote views.", e);
        }
    }

    /* access modifiers changed from: protected */
    public Layout getLayout(int i, int i2, boolean z, int i3) {
        return getLayout(i, i2, z, i3, (String[]) null, (Size) null, (Size[]) null);
    }

    /* access modifiers changed from: protected */
    public Layout getLayout(int i, int i2, boolean z, int i3, String[] strArr) {
        return getLayout(i, i2, z, i3, strArr, (Size) null, (Size[]) null);
    }

    /* access modifiers changed from: protected */
    public Layout getLayout(int i, int i2, boolean z, int i3, String[] strArr, Size size, Size[] sizeArr) {
        Layout calculateLayout = super.calculateLayout(i, i2, z, i3, getLayoutOrigin(), size, sizeArr);
        if (strArr != null) {
            LayoutFrame[] remoteFrames = calculateLayout.getRemoteFrames();
            for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) remoteFrames); i4++) {
                remoteFrames[i4].setViewId(strArr[i4]);
            }
        }
        IAction1<Layout> iAction1 = this._onLayout;
        if (iAction1 != null) {
            try {
                iAction1.invoke(calculateLayout);
            } catch (Exception e) {
                Unhandled.logException(e, "LayoutManager -> OnLayout");
            }
        }
        return calculateLayout;
    }

    /* access modifiers changed from: protected */
    public Layout getLayout(int i, int i2, int i3) {
        return getLayout(i, i2, true, i3, (String[]) null, (Size) null, (Size[]) null);
    }

    /* access modifiers changed from: protected */
    public Layout getLayout(int i, int i2, int i3, String[] strArr) {
        return getLayout(i, i2, true, i3, strArr, (Size) null, (Size[]) null);
    }

    public LayoutOrigin getLayoutOrigin() {
        return this._layoutOrigin;
    }

    public T getLocalView() {
        return doGetLocalView();
    }

    private T getNewestRemoteView(String str) {
        ArrayList remoteViewsInternal = getRemoteViewsInternal(str);
        if (remoteViewsInternal == null) {
            return null;
        }
        return ArrayListExtensions.getItem(remoteViewsInternal).get(0);
    }

    private T getOldestRemoteView(String str) {
        ArrayList remoteViewsInternal = getRemoteViewsInternal(str);
        if (remoteViewsInternal == null) {
            return null;
        }
        return ArrayListExtensions.getItem(remoteViewsInternal).get(ArrayListExtensions.getCount(remoteViewsInternal) - 1);
    }

    public T getRemoteView(String str) {
        if (str == null) {
            return null;
        }
        return getNewestRemoteView(str);
    }

    public String[] getRemoteViewIds() {
        return doGetRemoteViewsIds();
    }

    public ArrayList<T> getRemoteViews() {
        return getRemoteViews(getRemoteViewIds());
    }

    public ArrayList<T> getRemoteViews(String[] strArr) {
        if (strArr != null) {
            ArrayList<T> arrayList = new ArrayList<>();
            synchronized (this._remoteViewsLock) {
                for (String remoteView : strArr) {
                    arrayList.add(getRemoteView(remoteView));
                }
            }
            return arrayList;
        }
        throw new RuntimeException(new Exception("Could not get remote views. The IDs cannot be null."));
    }

    private ArrayList<T> getRemoteViewsInternal(String str) {
        if (str != null) {
            return doGetRemoteViews(str);
        }
        throw new RuntimeException(new Exception("The ID cannot be null."));
    }

    public LayoutManager() {
        this((LayoutPreset) null);
    }

    public LayoutManager(LayoutPreset layoutPreset) {
        this.__onLayout = new ArrayList();
        this._onLayout = null;
        this._remoteViewsTable = new HashMap<>();
        this._remoteViewsLock = new Object();
        this._localView = null;
        this._inBatch = false;
        (layoutPreset == null ? LayoutPreset.getFacetime() : layoutPreset).copyToPreset(this);
        setLayoutOrigin(LayoutOrigin.TopLeft);
    }

    public void layoutOnMainThread() {
        dispatchToMainThread(new IActionDelegate2<Object, Object>() {
            public String getId() {
                return "fm.liveswitch.LayoutManager<T>.layoutOnMainThreadUI";
            }

            public void invoke(Object obj, Object obj2) {
                LayoutManager.this.layoutOnMainThreadUI(obj, obj2);
            }
        }, (Object) null, (Object) null);
    }

    /* access modifiers changed from: private */
    public void layoutOnMainThreadUI(Object obj, Object obj2) {
        layout();
    }

    public void removeOnLayout(IAction1<Layout> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onLayout, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onLayout.remove(iAction1);
        if (this.__onLayout.size() == 0) {
            this._onLayout = null;
        }
    }

    public boolean removeRemoteMedia(IViewableMedia<T> iViewableMedia) {
        if (iViewableMedia == null) {
            return false;
        }
        return removeRemoteView(iViewableMedia.getId());
    }

    public boolean removeRemoteView(String str) {
        if (str == null) {
            Log.warn("Could not remove remote view. The ID cannot be null.");
            return false;
        }
        Object oldestRemoteView = getOldestRemoteView(str);
        if (oldestRemoteView == null) {
            return true;
        }
        try {
            dispatchToMainThread(new IActionDelegate2<Object, Object>() {
                public String getId() {
                    return "fm.liveswitch.LayoutManager<T>.removeRemoteViewUI";
                }

                public void invoke(Object obj, Object obj2) {
                    LayoutManager.this.removeRemoteViewUI(obj, obj2);
                }
            }, str, oldestRemoteView);
            return true;
        } catch (Exception e) {
            Log.error("Could not remove remote view.", e);
            return true;
        }
    }

    public void removeRemoteViews() {
        removeRemoteViews(getRemoteViewIds());
    }

    public boolean removeRemoteViews(String[] strArr) {
        if (strArr == null) {
            Log.warn("Could not remove remote views. The IDs cannot be null.");
            return false;
        }
        try {
            dispatchToMainThread(new IActionDelegate2<Object, Object>() {
                public String getId() {
                    return "fm.liveswitch.LayoutManager<T>.removeRemoteViewsUI";
                }

                public void invoke(Object obj, Object obj2) {
                    LayoutManager.this.removeRemoteViewsUI(obj, obj2);
                }
            }, strArr, getRemoteViews(strArr));
            return true;
        } catch (Exception e) {
            Log.error("Could not remove remote views.", e);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void removeRemoteViewsUI(Object obj, Object obj2) {
        String[] strArr = (String[]) obj;
        ArrayList arrayList = (ArrayList) obj2;
        synchronized (this._remoteViewsLock) {
            this._inBatch = true;
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
                removeRemoteViewUI(strArr[i], ArrayListExtensions.getItem(arrayList).get(i));
            }
            this._inBatch = false;
            layout();
        }
    }

    /* access modifiers changed from: private */
    public void removeRemoteViewUI(Object obj, Object obj2) {
        String str = (String) obj;
        synchronized (this._remoteViewsLock) {
            ArrayList arrayList = new ArrayList();
            ArrayList remoteViewsInternal = getRemoteViewsInternal(str);
            if (remoteViewsInternal != null) {
                ArrayListExtensions.addRange(arrayList, remoteViewsInternal);
            }
            arrayList.remove(obj2);
            removeView(obj2);
            if (ArrayListExtensions.getCount(arrayList) == 0) {
                HashMapExtensions.remove(this._remoteViewsTable, str);
            } else {
                HashMapExtensions.set(HashMapExtensions.getItem(this._remoteViewsTable), str, arrayList);
            }
            if (!this._inBatch) {
                layout();
            }
        }
    }

    public void reset() {
        removeRemoteViews();
        unsetLocalView();
    }

    /* access modifiers changed from: protected */
    public void setLayoutOrigin(LayoutOrigin layoutOrigin) {
        this._layoutOrigin = layoutOrigin;
    }

    public boolean setLocalMedia(IViewableMedia<T> iViewableMedia) {
        if (iViewableMedia == null) {
            return false;
        }
        return setLocalView(iViewableMedia.getView());
    }

    public boolean setLocalView(T t) {
        if (t == null) {
            Log.warn("Could not set local view. The view cannot be null.");
            return false;
        } else if (t == null) {
            return true;
        } else {
            try {
                dispatchToMainThread(new IActionDelegate2<Object, Object>() {
                    public String getId() {
                        return "fm.liveswitch.LayoutManager<T>.setLocalViewUI";
                    }

                    public void invoke(Object obj, Object obj2) {
                        LayoutManager.this.setLocalViewUI(obj, obj2);
                    }
                }, t, (Object) null);
                return true;
            } catch (Exception e) {
                Log.error("Could not set local view.", e);
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void setLocalViewUI(Object obj, Object obj2) {
        T t = this._localView;
        if (t != null) {
            removeView(t);
        }
        this._localView = obj;
        addView(obj);
        layout();
    }

    public boolean swapRemoteMedia(IViewableMedia<T> iViewableMedia, IViewableMedia<T> iViewableMedia2) {
        if (iViewableMedia == null || iViewableMedia2 == null) {
            return false;
        }
        return swapRemoteView(iViewableMedia.getId(), iViewableMedia2.getId(), iViewableMedia2.getView());
    }

    public boolean swapRemoteView(String str, String str2, T t) {
        if (str == null) {
            Log.warn("Could not swap remote view. The ID to remove cannot be null.");
            return false;
        } else if (str2 == null) {
            Log.warn("Could not swap remote view. The ID to add cannot be null.");
            return false;
        } else if (t == null) {
            Log.warn("Could not swap remote view. The view to add cannot be null.");
            return false;
        } else {
            doSwapRemoteView(str, getOldestRemoteView(str), str2, t);
            return true;
        }
    }

    public boolean swapRemoteViews(String[] strArr, String[] strArr2, T[] tArr) {
        if (strArr == null) {
            Log.warn("Could not swap remote views. The IDs to remove cannot be null.");
            return false;
        } else if (strArr2 == null) {
            Log.warn("Could not swap remote views. The IDs to add cannot be null.");
            return false;
        } else if (tArr == null) {
            Log.warn("Could not swap remote views. The views to add cannot be null.");
            return false;
        } else if (ArrayExtensions.getLength((Object[]) strArr2) != ArrayExtensions.getLength((Object[]) tArr)) {
            Log.warn("Could not swap remote views. The number of IDs and views to add must match.");
            return false;
        } else {
            doSwapRemoteViews(strArr, getRemoteViews(strArr), strArr2, tArr);
            return true;
        }
    }

    public boolean unsetLocalView() {
        Object localView = getLocalView();
        if (localView == null) {
            Log.warn("Could not unset local view. A view does not exist.");
            return false;
        }
        try {
            dispatchToMainThread(new IActionDelegate2<Object, Object>() {
                public String getId() {
                    return "fm.liveswitch.LayoutManager<T>.unsetLocalViewUI";
                }

                public void invoke(Object obj, Object obj2) {
                    LayoutManager.this.unsetLocalViewUI(obj, obj2);
                }
            }, localView, (Object) null);
            return true;
        } catch (Exception e) {
            Log.error("Could not unset local view.", e);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void unsetLocalViewUI(Object obj, Object obj2) {
        this._localView = null;
        removeView(obj);
        layout();
    }
}
