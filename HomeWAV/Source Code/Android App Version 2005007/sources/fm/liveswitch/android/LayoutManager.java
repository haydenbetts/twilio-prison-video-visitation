package fm.liveswitch.android;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import fm.liveswitch.IAction0;
import fm.liveswitch.IAction2;
import fm.liveswitch.Layout;
import fm.liveswitch.LayoutFrame;
import fm.liveswitch.LayoutMode;
import fm.liveswitch.LayoutPreset;
import fm.liveswitch.Log;
import java.util.ArrayList;

public class LayoutManager extends fm.liveswitch.LayoutManager<View> {
    /* access modifiers changed from: private */
    public ViewGroup container;
    private ViewGroup innerContainer;

    public ViewGroup getContainer() {
        return this.container;
    }

    public void setMode(LayoutMode layoutMode) {
        if (layoutMode != LayoutMode.InlineOverflow) {
            super.setMode(layoutMode);
            return;
        }
        throw new RuntimeException("Inline overflow mode is not supported on this platform.");
    }

    public LayoutManager(ViewGroup viewGroup) {
        this(viewGroup, (LayoutPreset) null);
    }

    public LayoutManager(ViewGroup viewGroup, LayoutPreset layoutPreset) {
        super(layoutPreset);
        this.container = viewGroup;
        RelativeLayout relativeLayout = new RelativeLayout(viewGroup.getContext());
        this.innerContainer = relativeLayout;
        relativeLayout.setBackgroundColor(0);
        viewGroup.addView(this.innerContainer);
        viewGroup.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
                    LayoutManager.this.layout();
                }
            }
        });
        initializeOnGlobalLayout();
    }

    /* access modifiers changed from: protected */
    public void addView(View view) {
        this.innerContainer.addView(view);
    }

    /* access modifiers changed from: protected */
    public void removeView(View view) {
        this.innerContainer.removeView(view);
    }

    /* access modifiers changed from: protected */
    public void dispatchToMainThread(final IAction2<Object, Object> iAction2, final Object obj, final Object obj2) {
        Utility.dispatchToMainThread(new IAction0() {
            public void invoke() {
                iAction2.invoke(obj, obj2);
            }
        });
    }

    public void layout() {
        initializeLayout();
        ViewGroup.LayoutParams layoutParams = this.innerContainer.getLayoutParams();
        int i = layoutParams.width;
        int i2 = layoutParams.height;
        if (i > 0 && i2 > 0) {
            View view = (View) getLocalView();
            String[] remoteViewIds = getRemoteViewIds();
            ArrayList remoteViews = getRemoteViews(remoteViewIds);
            try {
                Layout layout = getLayout(i, i2, view != null, remoteViews.size(), remoteViewIds);
                if (view != null) {
                    LayoutFrame localFrame = layout.getLocalFrame();
                    view.setX((float) localFrame.getX());
                    view.setY((float) localFrame.getY());
                    ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                    layoutParams2.width = localFrame.getWidth();
                    layoutParams2.height = localFrame.getHeight();
                    view.setLayoutParams(layoutParams2);
                    if (getMode() == LayoutMode.FloatLocal) {
                        view.bringToFront();
                    }
                }
                LayoutFrame[] remoteFrames = layout.getRemoteFrames();
                for (int i3 = 0; i3 < remoteFrames.length; i3++) {
                    LayoutFrame layoutFrame = remoteFrames[i3];
                    View view2 = (View) remoteViews.get(i3);
                    view2.setX((float) layoutFrame.getX());
                    view2.setY((float) layoutFrame.getY());
                    ViewGroup.LayoutParams layoutParams3 = view2.getLayoutParams();
                    layoutParams3.width = layoutFrame.getWidth();
                    layoutParams3.height = layoutFrame.getHeight();
                    view2.setLayoutParams(layoutParams3);
                    if (getMode() == LayoutMode.FloatRemote) {
                        view2.bringToFront();
                    }
                }
                this.innerContainer.invalidate();
            } catch (Exception e) {
                Log.error("Could not get layout.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initializeOnGlobalLayout() {
        this.container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* JADX WARNING: Can't wrap try/catch for region: R(5:7|8|9|10|17) */
            /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
                return;
             */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0020 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onGlobalLayout() {
                /*
                    r3 = this;
                    fm.liveswitch.android.LayoutManager r0 = fm.liveswitch.android.LayoutManager.this
                    boolean r0 = r0.initializeLayout()
                    if (r0 == 0) goto L_0x002a
                    fm.liveswitch.android.LayoutManager r0 = fm.liveswitch.android.LayoutManager.this
                    android.view.ViewGroup r0 = r0.container
                    android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
                    int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0024 }
                    r2 = 16
                    if (r1 >= r2) goto L_0x001c
                    r0.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x0024 }
                    goto L_0x002a
                L_0x001c:
                    r0.removeOnGlobalLayoutListener(r3)     // Catch:{ NoSuchMethodError -> 0x0020 }
                    goto L_0x002a
                L_0x0020:
                    r0.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x0024 }
                    goto L_0x002a
                L_0x0024:
                    r0 = move-exception
                    java.lang.String r1 = "Could not remove global layout listener."
                    fm.liveswitch.Log.error(r1, r0)
                L_0x002a:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.android.LayoutManager.AnonymousClass3.onGlobalLayout():void");
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean initializeLayout() {
        int width = this.container.getWidth();
        int height = this.container.getHeight();
        if (width <= 0 || height <= 0) {
            return false;
        }
        ViewGroup.LayoutParams layoutParams = this.innerContainer.getLayoutParams();
        if (!(width == layoutParams.width && height == layoutParams.height)) {
            layoutParams.width = width;
            layoutParams.height = height;
            this.innerContainer.setLayoutParams(layoutParams);
        }
        if (this.innerContainer.getX() == 0.0f && this.innerContainer.getY() == 0.0f) {
            return true;
        }
        this.innerContainer.setX(0.0f);
        this.innerContainer.setY(0.0f);
        return true;
    }
}
