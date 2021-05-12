package com.urbanairship.iam;

import android.content.Context;
import android.os.Looper;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.actions.ActionRunRequestFactory;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.automation.AutomationDriver;
import com.urbanairship.iam.AdapterWrapper;
import com.urbanairship.iam.DisplayCoordinator;
import com.urbanairship.iam.InAppMessageAdapter;
import com.urbanairship.iam.assets.AssetManager;
import com.urbanairship.iam.banner.BannerAdapterFactory;
import com.urbanairship.iam.fullscreen.FullScreenAdapterFactory;
import com.urbanairship.iam.html.HtmlAdapterFactory;
import com.urbanairship.iam.modal.ModalAdapterFactory;
import com.urbanairship.util.RetryingExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class InAppMessageManager {
    public static final long DEFAULT_DISPLAY_INTERVAL_MS = 30000;
    private static final String DISPLAY_INTERVAL_KEY = "com.urbanairship.iam.displayinterval";
    private final ActionRunRequestFactory actionRunRequestFactory;
    private final Map<String, InAppMessageAdapter.Factory> adapterFactories;
    /* access modifiers changed from: private */
    public final Map<String, AdapterWrapper> adapterWrappers;
    private final Analytics analytics;
    /* access modifiers changed from: private */
    public final AssetManager assetManager;
    /* access modifiers changed from: private */
    public final Context context;
    private final PreferenceDataStore dataStore;
    private final DefaultDisplayCoordinator defaultCoordinator;
    /* access modifiers changed from: private */
    public final Delegate delegate;
    private OnRequestDisplayCoordinatorCallback displayCoordinatorCallback;
    private final DisplayCoordinator.OnDisplayReadyCallback displayReadyCallback;
    private final Map<String, AutomationDriver.ExecutionCallback> executionCallbacks;
    private final RetryingExecutor executor;
    private final ImmediateDisplayCoordinator immediateDisplayCoordinator;
    private final List<InAppMessageListener> listeners;
    private InAppMessageExtender messageExtender;

    public interface Delegate {
        void onReadinessChanged();
    }

    public InAppMessageManager(Context context2, PreferenceDataStore preferenceDataStore, Analytics analytics2, Delegate delegate2) {
        this(context2, preferenceDataStore, analytics2, RetryingExecutor.newSerialExecutor(Looper.getMainLooper()), new ActionRunRequestFactory(), new AssetManager(context2), delegate2);
    }

    InAppMessageManager(Context context2, PreferenceDataStore preferenceDataStore, Analytics analytics2, RetryingExecutor retryingExecutor, ActionRunRequestFactory actionRunRequestFactory2, AssetManager assetManager2, Delegate delegate2) {
        this.adapterWrappers = new ConcurrentHashMap();
        this.adapterFactories = new HashMap();
        this.listeners = new ArrayList();
        this.displayReadyCallback = new DisplayCoordinator.OnDisplayReadyCallback() {
            public void onReady() {
                InAppMessageManager.this.delegate.onReadinessChanged();
            }
        };
        this.executionCallbacks = new HashMap();
        this.context = context2;
        this.dataStore = preferenceDataStore;
        this.analytics = analytics2;
        this.executor = retryingExecutor;
        this.assetManager = assetManager2;
        this.delegate = delegate2;
        this.actionRunRequestFactory = actionRunRequestFactory2;
        this.defaultCoordinator = new DefaultDisplayCoordinator(getDisplayInterval());
        this.immediateDisplayCoordinator = new ImmediateDisplayCoordinator();
        retryingExecutor.setPaused(true);
        setAdapterFactory(InAppMessage.TYPE_BANNER, new BannerAdapterFactory());
        setAdapterFactory(InAppMessage.TYPE_FULLSCREEN, new FullScreenAdapterFactory());
        setAdapterFactory(InAppMessage.TYPE_MODAL, new ModalAdapterFactory());
        setAdapterFactory(InAppMessage.TYPE_HTML, new HtmlAdapterFactory());
    }

    public void setAdapterFactory(String str, InAppMessageAdapter.Factory factory) {
        if (factory == null) {
            this.adapterFactories.remove(str);
        } else {
            this.adapterFactories.put(str, factory);
        }
    }

    public void setDisplayInterval(long j, TimeUnit timeUnit) {
        this.dataStore.put(DISPLAY_INTERVAL_KEY, timeUnit.toMillis(j));
        this.defaultCoordinator.setDisplayInterval(j, timeUnit);
    }

    public long getDisplayInterval() {
        return this.dataStore.getLong(DISPLAY_INTERVAL_KEY, 30000);
    }

    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    public void addListener(InAppMessageListener inAppMessageListener) {
        synchronized (this.listeners) {
            this.listeners.add(inAppMessageListener);
        }
    }

    public void removeListener(InAppMessageListener inAppMessageListener) {
        synchronized (this.listeners) {
            this.listeners.remove(inAppMessageListener);
        }
    }

    public void setMessageExtender(InAppMessageExtender inAppMessageExtender) {
        this.messageExtender = inAppMessageExtender;
    }

    public void setOnRequestDisplayCoordinatorCallback(OnRequestDisplayCoordinatorCallback onRequestDisplayCoordinatorCallback) {
        this.displayCoordinatorCallback = onRequestDisplayCoordinatorCallback;
    }

    public void onAirshipReady() {
        this.executor.setPaused(false);
    }

    public void onPrepare(final String str, InAppMessage inAppMessage, final AutomationDriver.PrepareScheduleCallback prepareScheduleCallback) {
        final AdapterWrapper createAdapterWrapper = createAdapterWrapper(str, inAppMessage);
        if (createAdapterWrapper == null) {
            prepareScheduleCallback.onFinish(2);
            return;
        }
        AnonymousClass2 r1 = new RetryingExecutor.Operation() {
            public int run() {
                int onPrepare = InAppMessageManager.this.assetManager.onPrepare(str, createAdapterWrapper.message);
                if (onPrepare == 0) {
                    Logger.debug("InAppMessageManager - Assets prepared for schedule %s.", str);
                    return 0;
                } else if (onPrepare != 1) {
                    Logger.debug("InAppMessageManager - Assets failed to prepare. Cancelling display for schedule %s.", str);
                    InAppMessageManager.this.assetManager.onDisplayFinished(str, createAdapterWrapper.message);
                    prepareScheduleCallback.onFinish(1);
                    return 2;
                } else {
                    Logger.debug("InAppMessageManager - Assets failed to prepare for schedule %s. Will retry.", str);
                    return 1;
                }
            }
        };
        AnonymousClass3 r2 = new RetryingExecutor.Operation() {
            public int run() {
                int prepare = createAdapterWrapper.prepare(InAppMessageManager.this.context, InAppMessageManager.this.assetManager.getAssets(str));
                if (prepare == 0) {
                    Logger.debug("InAppMessageManager - Adapter prepared schedule %s.", str);
                    InAppMessageManager.this.adapterWrappers.put(str, createAdapterWrapper);
                    prepareScheduleCallback.onFinish(0);
                    return 0;
                } else if (prepare != 1) {
                    Logger.debug("InAppMessageManager - Adapter failed to prepare. Cancelling display for schedule %s.", str);
                    prepareScheduleCallback.onFinish(1);
                    return 2;
                } else {
                    Logger.debug("InAppMessageManager - Adapter failed to prepare schedule %s. Will retry.", str);
                    return 1;
                }
            }
        };
        this.executor.execute(r1, r2);
    }

    public int onCheckExecutionReadiness(String str) {
        AdapterWrapper adapterWrapper = this.adapterWrappers.get(str);
        if (adapterWrapper != null) {
            return adapterWrapper.isReady(this.context) ? 1 : 0;
        }
        Logger.error("Missing adapter for schedule %.", str);
        return -1;
    }

    public void onExecute(String str, AutomationDriver.ExecutionCallback executionCallback) {
        final AdapterWrapper adapterWrapper = this.adapterWrappers.get(str);
        if (adapterWrapper == null) {
            Logger.error("Missing adapter for schedule %.", str);
            executionCallback.onFinish();
            return;
        }
        synchronized (this.executionCallbacks) {
            this.executionCallbacks.put(str, executionCallback);
        }
        try {
            adapterWrapper.display(this.context);
            if (adapterWrapper.message.isReportingEnabled()) {
                this.analytics.addEvent(new DisplayEvent(str, adapterWrapper.message));
            }
            synchronized (this.listeners) {
                Iterator it = new ArrayList(this.listeners).iterator();
                while (it.hasNext()) {
                    ((InAppMessageListener) it.next()).onMessageDisplayed(str, adapterWrapper.message);
                }
            }
            Logger.verbose("InAppMessagingManager - Message displayed for schedule %s.", str);
        } catch (AdapterWrapper.DisplayException e) {
            Logger.error(e, "Failed to display in-app message for schedule %s.", str);
            callExecutionFinishedCallback(str);
            this.executor.execute((Runnable) new Runnable() {
                public void run() {
                    adapterWrapper.adapterFinished(InAppMessageManager.this.context);
                }
            });
        }
    }

    public void onExecutionInvalidated(final String str) {
        final AdapterWrapper remove = this.adapterWrappers.remove(str);
        if (remove != null) {
            this.executor.execute((Runnable) new Runnable() {
                public void run() {
                    InAppMessageManager.this.assetManager.onDisplayFinished(str, remove.message);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void onDisplayFinished(String str, ResolutionInfo resolutionInfo, long j) {
        Logger.verbose("InAppMessagingManager - Message finished for schedule %s.", str);
        final AdapterWrapper remove = this.adapterWrappers.remove(str);
        if (remove != null) {
            if (remove.message.isReportingEnabled()) {
                this.analytics.addEvent(ResolutionEvent.messageResolution(str, remove.message, resolutionInfo, j));
            }
            InAppActionUtils.runActions(remove.message.getActions(), this.actionRunRequestFactory);
            synchronized (this.listeners) {
                Iterator it = new ArrayList(this.listeners).iterator();
                while (it.hasNext()) {
                    ((InAppMessageListener) it.next()).onMessageFinished(str, remove.message, resolutionInfo);
                }
            }
            callExecutionFinishedCallback(str);
            remove.displayFinished();
            this.executor.execute((Runnable) new Runnable() {
                public void run() {
                    remove.adapterFinished(InAppMessageManager.this.context);
                    InAppMessageManager.this.assetManager.onDisplayFinished(remove.scheduleId, remove.message);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isDisplayAllowed(String str) {
        AdapterWrapper adapterWrapper = this.adapterWrappers.get(str);
        return adapterWrapper != null && adapterWrapper.displayed;
    }

    public void onMessageScheduleFinished(final String str) {
        this.executor.execute((Runnable) new Runnable() {
            public void run() {
                InAppMessageManager.this.assetManager.onFinish(str);
            }
        });
    }

    public void onNewMessageSchedule(final String str, final InAppMessage inAppMessage) {
        this.executor.execute((Runnable) new Runnable() {
            public void run() {
                InAppMessageManager.this.assetManager.onSchedule(str, new Callable<InAppMessage>() {
                    public InAppMessage call() {
                        return InAppMessageManager.this.extendMessage(inAppMessage);
                    }
                });
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0065 A[Catch:{ Exception -> 0x0082 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A[Catch:{ Exception -> 0x0082 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.urbanairship.iam.AdapterWrapper createAdapterWrapper(java.lang.String r9, com.urbanairship.iam.InAppMessage r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            com.urbanairship.iam.InAppMessage r10 = r8.extendMessage(r10)     // Catch:{ Exception -> 0x0082 }
            java.util.Map<java.lang.String, com.urbanairship.iam.InAppMessageAdapter$Factory> r2 = r8.adapterFactories     // Catch:{ Exception -> 0x0082 }
            monitor-enter(r2)     // Catch:{ Exception -> 0x0082 }
            java.util.Map<java.lang.String, com.urbanairship.iam.InAppMessageAdapter$Factory> r3 = r8.adapterFactories     // Catch:{ all -> 0x007f }
            java.lang.String r4 = r10.getType()     // Catch:{ all -> 0x007f }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x007f }
            com.urbanairship.iam.InAppMessageAdapter$Factory r3 = (com.urbanairship.iam.InAppMessageAdapter.Factory) r3     // Catch:{ all -> 0x007f }
            monitor-exit(r2)     // Catch:{ all -> 0x007f }
            r2 = 1
            if (r3 != 0) goto L_0x002b
            java.lang.String r3 = "InAppMessageManager - No display adapter for message type: %s. Unable to process schedule: %s."
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0082 }
            java.lang.String r5 = r10.getType()     // Catch:{ Exception -> 0x0082 }
            r4[r0] = r5     // Catch:{ Exception -> 0x0082 }
            r4[r2] = r9     // Catch:{ Exception -> 0x0082 }
            com.urbanairship.Logger.debug(r3, r4)     // Catch:{ Exception -> 0x0082 }
            r3 = r1
            goto L_0x002f
        L_0x002b:
            com.urbanairship.iam.InAppMessageAdapter r3 = r3.createAdapter(r10)     // Catch:{ Exception -> 0x0082 }
        L_0x002f:
            com.urbanairship.iam.OnRequestDisplayCoordinatorCallback r4 = r8.displayCoordinatorCallback     // Catch:{ Exception -> 0x0082 }
            if (r4 == 0) goto L_0x0038
            com.urbanairship.iam.DisplayCoordinator r4 = r4.onRequestDisplayCoordinator(r10)     // Catch:{ Exception -> 0x0082 }
            goto L_0x0039
        L_0x0038:
            r4 = r1
        L_0x0039:
            if (r4 != 0) goto L_0x006a
            java.lang.String r4 = r10.getDisplayBehavior()     // Catch:{ Exception -> 0x0082 }
            r5 = -1
            int r6 = r4.hashCode()     // Catch:{ Exception -> 0x0082 }
            r7 = 1124382641(0x4304b7b1, float:132.71754)
            if (r6 == r7) goto L_0x0058
            r7 = 1544803905(0x5c13d641, float:1.66449585E17)
            if (r6 == r7) goto L_0x004f
            goto L_0x0062
        L_0x004f:
            java.lang.String r6 = "default"
            boolean r4 = r4.equals(r6)     // Catch:{ Exception -> 0x0082 }
            if (r4 == 0) goto L_0x0062
            goto L_0x0063
        L_0x0058:
            java.lang.String r2 = "immediate"
            boolean r2 = r4.equals(r2)     // Catch:{ Exception -> 0x0082 }
            if (r2 == 0) goto L_0x0062
            r2 = 0
            goto L_0x0063
        L_0x0062:
            r2 = -1
        L_0x0063:
            if (r2 == 0) goto L_0x0068
            com.urbanairship.iam.DefaultDisplayCoordinator r4 = r8.defaultCoordinator     // Catch:{ Exception -> 0x0082 }
            goto L_0x006a
        L_0x0068:
            com.urbanairship.iam.ImmediateDisplayCoordinator r4 = r8.immediateDisplayCoordinator     // Catch:{ Exception -> 0x0082 }
        L_0x006a:
            if (r3 != 0) goto L_0x0074
            java.lang.String r9 = "InAppMessageManager - Failed to create in-app message adapter."
            java.lang.Object[] r10 = new java.lang.Object[r0]
            com.urbanairship.Logger.error(r9, r10)
            return r1
        L_0x0074:
            com.urbanairship.iam.DisplayCoordinator$OnDisplayReadyCallback r0 = r8.displayReadyCallback
            r4.setDisplayReadyCallback(r0)
            com.urbanairship.iam.AdapterWrapper r0 = new com.urbanairship.iam.AdapterWrapper
            r0.<init>(r9, r10, r3, r4)
            return r0
        L_0x007f:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x007f }
            throw r9     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r9 = move-exception
            java.lang.String r10 = "InAppMessageManager - Failed to create in-app message adapter."
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.urbanairship.Logger.error(r9, r10, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.InAppMessageManager.createAdapterWrapper(java.lang.String, com.urbanairship.iam.InAppMessage):com.urbanairship.iam.AdapterWrapper");
    }

    /* access modifiers changed from: private */
    public InAppMessage extendMessage(InAppMessage inAppMessage) {
        InAppMessageExtender inAppMessageExtender = this.messageExtender;
        return inAppMessageExtender != null ? inAppMessageExtender.extend(inAppMessage) : inAppMessage;
    }

    private void callExecutionFinishedCallback(String str) {
        synchronized (this.executionCallbacks) {
            AutomationDriver.ExecutionCallback remove = this.executionCallbacks.remove(str);
            if (remove != null) {
                remove.onFinish();
            }
        }
    }
}
