package com.urbanairship.iam.banner;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.urbanairship.Logger;
import com.urbanairship.Predicate;
import com.urbanairship.app.ActivityListener;
import com.urbanairship.app.FilteredActivityListener;
import com.urbanairship.app.SimpleActivityListener;
import com.urbanairship.automation.R;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.DisplayHandler;
import com.urbanairship.iam.InAppActionUtils;
import com.urbanairship.iam.InAppActivityMonitor;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.MediaDisplayAdapter;
import com.urbanairship.iam.ResolutionInfo;
import com.urbanairship.iam.banner.BannerView;
import com.urbanairship.util.ManifestUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BannerAdapter extends MediaDisplayAdapter {
    public static final String BANNER_CONTAINER_ID = "com.urbanairship.iam.banner.BANNER_CONTAINER_ID";
    private static final Map<Class, Integer> cachedContainerIds = new HashMap();
    private final Predicate<Activity> activityPredicate;
    private WeakReference<BannerView> currentView;
    /* access modifiers changed from: private */
    public final BannerDisplayContent displayContent;
    /* access modifiers changed from: private */
    public DisplayHandler displayHandler;
    private WeakReference<Activity> lastActivity;
    private final ActivityListener listener;

    protected BannerAdapter(InAppMessage inAppMessage, BannerDisplayContent bannerDisplayContent) {
        super(inAppMessage, bannerDisplayContent.getMedia());
        AnonymousClass1 r3 = new Predicate<Activity>() {
            public boolean apply(Activity activity) {
                if (BannerAdapter.this.getContainerView(activity) != null) {
                    return true;
                }
                Logger.error("BannerAdapter - Unable to display in-app message. No view group found.", new Object[0]);
                return false;
            }
        };
        this.activityPredicate = r3;
        this.listener = new FilteredActivityListener(new SimpleActivityListener() {
            public void onActivityStopped(Activity activity) {
                super.onActivityStopped(activity);
                BannerAdapter.this.onActivityStopped(activity);
            }

            public void onActivityResumed(Activity activity) {
                super.onActivityResumed(activity);
                BannerAdapter.this.onActivityResumed(activity);
            }

            public void onActivityPaused(Activity activity) {
                super.onActivityPaused(activity);
                BannerAdapter.this.onActivityPaused(activity);
            }
        }, r3);
        this.displayContent = bannerDisplayContent;
    }

    public static BannerAdapter newAdapter(InAppMessage inAppMessage) {
        BannerDisplayContent bannerDisplayContent = (BannerDisplayContent) inAppMessage.getDisplayContent();
        if (bannerDisplayContent != null) {
            return new BannerAdapter(inAppMessage, bannerDisplayContent);
        }
        throw new IllegalArgumentException("Invalid message for adapter: " + inAppMessage);
    }

    public boolean isReady(Context context) {
        if (!super.isReady(context)) {
            return false;
        }
        return !InAppActivityMonitor.shared(context).getResumedActivities(this.activityPredicate).isEmpty();
    }

    public void onDisplay(Context context, DisplayHandler displayHandler2) {
        Logger.info("BannerAdapter - Displaying in-app message.", new Object[0]);
        this.displayHandler = displayHandler2;
        InAppActivityMonitor.shared(context).addActivityListener(this.listener);
        display(context);
    }

    /* access modifiers changed from: protected */
    public void onDisplayFinished(Context context) {
        InAppActivityMonitor.shared(context).removeActivityListener(this.listener);
    }

    /* access modifiers changed from: protected */
    public BannerView onCreateView(Activity activity, ViewGroup viewGroup) {
        return new BannerView(activity, this.displayContent, getAssets());
    }

    /* access modifiers changed from: protected */
    public void onViewCreated(BannerView bannerView, Activity activity, ViewGroup viewGroup) {
        if (getLastActivity() != activity) {
            if (BannerDisplayContent.PLACEMENT_BOTTOM.equals(this.displayContent.getPlacement())) {
                bannerView.setAnimations(R.animator.ua_iam_slide_in_bottom, R.animator.ua_iam_slide_out_bottom);
            } else {
                bannerView.setAnimations(R.animator.ua_iam_slide_in_top, R.animator.ua_iam_slide_out_top);
            }
        }
        bannerView.setListener(new BannerView.Listener() {
            public void onButtonClicked(BannerView bannerView, ButtonInfo buttonInfo) {
                InAppActionUtils.runActions(buttonInfo);
                BannerAdapter.this.displayHandler.finished(ResolutionInfo.buttonPressed(buttonInfo), bannerView.getTimer().getRunTime());
                BannerAdapter.this.onDisplayFinished(bannerView.getContext());
            }

            public void onBannerClicked(BannerView bannerView) {
                if (!BannerAdapter.this.displayContent.getActions().isEmpty()) {
                    InAppActionUtils.runActions(BannerAdapter.this.displayContent.getActions());
                    BannerAdapter.this.displayHandler.finished(ResolutionInfo.messageClicked(), bannerView.getTimer().getRunTime());
                }
                BannerAdapter.this.onDisplayFinished(bannerView.getContext());
            }

            public void onTimedOut(BannerView bannerView) {
                BannerAdapter.this.displayHandler.finished(ResolutionInfo.timedOut(), bannerView.getTimer().getRunTime());
                BannerAdapter.this.onDisplayFinished(bannerView.getContext());
            }

            public void onUserDismissed(BannerView bannerView) {
                BannerAdapter.this.displayHandler.finished(ResolutionInfo.dismissed(), bannerView.getTimer().getRunTime());
                BannerAdapter.this.onDisplayFinished(bannerView.getContext());
            }
        });
    }

    /* access modifiers changed from: protected */
    public ViewGroup getContainerView(Activity activity) {
        int containerId = getContainerId(activity);
        View findViewById = containerId != 0 ? activity.findViewById(containerId) : null;
        if (findViewById == null) {
            findViewById = activity.findViewById(16908290);
        }
        if (findViewById instanceof ViewGroup) {
            return (ViewGroup) findViewById;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0011, code lost:
        r6 = r6.get(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void display(android.content.Context r6) {
        /*
            r5 = this;
            com.urbanairship.iam.InAppActivityMonitor r6 = com.urbanairship.iam.InAppActivityMonitor.shared(r6)
            com.urbanairship.Predicate<android.app.Activity> r0 = r5.activityPredicate
            java.util.List r6 = r6.getResumedActivities(r0)
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x0011
            return
        L_0x0011:
            r0 = 0
            java.lang.Object r6 = r6.get(r0)
            android.app.Activity r6 = (android.app.Activity) r6
            android.view.ViewGroup r1 = r5.getContainerView(r6)
            if (r1 != 0) goto L_0x001f
            return
        L_0x001f:
            com.urbanairship.iam.banner.BannerView r2 = r5.onCreateView(r6, r1)
            r5.onViewCreated(r2, r6, r1)
            android.view.ViewParent r3 = r2.getParent()
            if (r3 != 0) goto L_0x0059
            int r3 = r1.getId()
            r4 = 16908290(0x1020002, float:2.3877235E-38)
            if (r3 != r4) goto L_0x0056
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 21
            if (r3 < r4) goto L_0x0049
            float r3 = com.urbanairship.iam.view.InAppViewUtils.getLargestChildZValue(r1)
            r4 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 + r4
            r2.setZ(r3)
            r1.addView(r2, r0)
            goto L_0x0059
        L_0x0049:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 19
            if (r0 < r3) goto L_0x0052
            r2.applyLegacyWindowInsetFix()
        L_0x0052:
            r1.addView(r2)
            goto L_0x0059
        L_0x0056:
            r1.addView(r2)
        L_0x0059:
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r6)
            r5.lastActivity = r0
            java.lang.ref.WeakReference r6 = new java.lang.ref.WeakReference
            r6.<init>(r2)
            r5.currentView = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.banner.BannerAdapter.display(android.content.Context):void");
    }

    private int getContainerId(Activity activity) {
        Map<Class, Integer> map = cachedContainerIds;
        synchronized (map) {
            Integer num = map.get(activity.getClass());
            if (num != null) {
                int intValue = num.intValue();
                return intValue;
            }
            int i = 0;
            ActivityInfo activityInfo = ManifestUtils.getActivityInfo(activity.getClass());
            if (!(activityInfo == null || activityInfo.metaData == null)) {
                i = activityInfo.metaData.getInt(BANNER_CONTAINER_ID, 0);
            }
            map.put(activity.getClass(), Integer.valueOf(i));
            return i;
        }
    }

    /* access modifiers changed from: private */
    public void onActivityResumed(Activity activity) {
        BannerView currentView2 = getCurrentView();
        if (currentView2 == null || !ViewCompat.isAttachedToWindow(currentView2)) {
            display(activity);
        } else if (activity == getLastActivity()) {
            currentView2.onResume();
        }
    }

    /* access modifiers changed from: private */
    public void onActivityStopped(Activity activity) {
        BannerView currentView2;
        if (activity == getLastActivity() && (currentView2 = getCurrentView()) != null) {
            this.currentView = null;
            this.lastActivity = null;
            currentView2.dismiss(false);
            display(activity.getApplicationContext());
        }
    }

    /* access modifiers changed from: private */
    public void onActivityPaused(Activity activity) {
        BannerView currentView2;
        if (activity == getLastActivity() && (currentView2 = getCurrentView()) != null) {
            currentView2.onPause();
        }
    }

    private BannerView getCurrentView() {
        WeakReference<BannerView> weakReference = this.currentView;
        if (weakReference == null) {
            return null;
        }
        return (BannerView) weakReference.get();
    }

    private Activity getLastActivity() {
        WeakReference<Activity> weakReference = this.lastActivity;
        if (weakReference == null) {
            return null;
        }
        return (Activity) weakReference.get();
    }
}
