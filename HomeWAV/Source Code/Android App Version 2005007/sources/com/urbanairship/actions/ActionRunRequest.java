package com.urbanairship.actions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionRegistry;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

public class ActionRunRequest {
    private Action action;
    private String actionName;
    private ActionValue actionValue;
    private Executor executor = AirshipExecutors.THREAD_POOL_EXECUTOR;
    private Bundle metadata;
    private ActionRegistry registry;
    private int situation = 0;

    public static ActionRunRequest createRequest(String str) {
        return new ActionRunRequest(str, (ActionRegistry) null);
    }

    public static ActionRunRequest createRequest(String str, ActionRegistry actionRegistry) {
        return new ActionRunRequest(str, actionRegistry);
    }

    public static ActionRunRequest createRequest(Action action2) {
        if (action2 != null) {
            return new ActionRunRequest(action2);
        }
        throw new IllegalArgumentException("Unable to run null action");
    }

    private ActionRunRequest(String str, ActionRegistry actionRegistry) {
        this.actionName = str;
        this.registry = actionRegistry;
    }

    public ActionRunRequest(Action action2) {
        this.action = action2;
    }

    public ActionRunRequest setValue(ActionValue actionValue2) {
        this.actionValue = actionValue2;
        return this;
    }

    public ActionRunRequest setValue(Object obj) {
        try {
            this.actionValue = ActionValue.wrap(obj);
            return this;
        } catch (ActionValueException e) {
            throw new IllegalArgumentException("Unable to wrap object: " + obj + " as an ActionValue.", e);
        }
    }

    public ActionRunRequest setMetadata(Bundle bundle) {
        this.metadata = bundle;
        return this;
    }

    public ActionRunRequest setSituation(int i) {
        this.situation = i;
        return this;
    }

    public ActionRunRequest setExecutor(Executor executor2) {
        this.executor = executor2;
        return this;
    }

    public ActionResult runSync() {
        ActionArguments createActionArguments = createActionArguments();
        final Semaphore semaphore = new Semaphore(0);
        AnonymousClass1 r3 = new ActionRunnable(createActionArguments) {
            /* access modifiers changed from: package-private */
            public void onFinish(ActionArguments actionArguments, ActionResult actionResult) {
                semaphore.release();
            }
        };
        if (shouldRunOnMain(createActionArguments)) {
            new Handler(Looper.getMainLooper()).post(r3);
        } else {
            this.executor.execute(r3);
        }
        try {
            semaphore.acquire();
            return r3.result;
        } catch (InterruptedException e) {
            Logger.error("Failed to run action with arguments %s", createActionArguments);
            Thread.currentThread().interrupt();
            return ActionResult.newErrorResult(e);
        }
    }

    public void run() {
        run((Looper) null, (ActionCompletionCallback) null);
    }

    public void run(ActionCompletionCallback actionCompletionCallback) {
        run((Looper) null, actionCompletionCallback);
    }

    public void run(Looper looper, final ActionCompletionCallback actionCompletionCallback) {
        if (looper == null && (looper = Looper.myLooper()) == null) {
            looper = Looper.getMainLooper();
        }
        ActionArguments createActionArguments = createActionArguments();
        final Handler handler = new Handler(looper);
        AnonymousClass2 r3 = new ActionRunnable(createActionArguments) {
            /* access modifiers changed from: package-private */
            public void onFinish(final ActionArguments actionArguments, final ActionResult actionResult) {
                if (actionCompletionCallback != null) {
                    if (handler.getLooper() == Looper.myLooper()) {
                        actionCompletionCallback.onFinish(actionArguments, actionResult);
                    } else {
                        handler.post(new Runnable() {
                            public void run() {
                                actionCompletionCallback.onFinish(actionArguments, actionResult);
                            }
                        });
                    }
                }
            }
        };
        if (!shouldRunOnMain(createActionArguments)) {
            this.executor.execute(r3);
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            r3.run();
        } else {
            new Handler(Looper.getMainLooper()).post(r3);
        }
    }

    private ActionArguments createActionArguments() {
        Bundle bundle = this.metadata == null ? new Bundle() : new Bundle(this.metadata);
        String str = this.actionName;
        if (str != null) {
            bundle.putString(ActionArguments.REGISTRY_ACTION_NAME_METADATA, str);
        }
        return new ActionArguments(this.situation, this.actionValue, bundle);
    }

    private ActionRegistry.Entry lookUpAction(String str) {
        ActionRegistry actionRegistry = this.registry;
        if (actionRegistry != null) {
            return actionRegistry.getEntry(str);
        }
        return UAirship.shared().getActionRegistry().getEntry(str);
    }

    private boolean shouldRunOnMain(ActionArguments actionArguments) {
        Action action2 = this.action;
        if (action2 != null) {
            return action2.shouldRunOnMainThread();
        }
        ActionRegistry.Entry lookUpAction = lookUpAction(this.actionName);
        return lookUpAction != null && lookUpAction.getActionForSituation(actionArguments.getSituation()).shouldRunOnMainThread();
    }

    /* access modifiers changed from: private */
    public ActionResult executeAction(ActionArguments actionArguments) {
        String str = this.actionName;
        if (str != null) {
            ActionRegistry.Entry lookUpAction = lookUpAction(str);
            if (lookUpAction == null) {
                return ActionResult.newEmptyResultWithStatus(3);
            }
            if (lookUpAction.getPredicate() == null || lookUpAction.getPredicate().apply(actionArguments)) {
                return lookUpAction.getActionForSituation(this.situation).run(actionArguments);
            }
            Logger.info("Action %s will not be run. Registry predicate rejected the arguments: %s", this.actionName, actionArguments);
            return ActionResult.newEmptyResultWithStatus(2);
        }
        Action action2 = this.action;
        if (action2 != null) {
            return action2.run(actionArguments);
        }
        return ActionResult.newEmptyResultWithStatus(3);
    }

    private abstract class ActionRunnable implements Runnable {
        private final ActionArguments arguments;
        /* access modifiers changed from: private */
        public volatile ActionResult result;

        /* access modifiers changed from: package-private */
        public abstract void onFinish(ActionArguments actionArguments, ActionResult actionResult);

        public ActionRunnable(ActionArguments actionArguments) {
            this.arguments = actionArguments;
        }

        public final void run() {
            this.result = ActionRunRequest.this.executeAction(this.arguments);
            onFinish(this.arguments, this.result);
        }
    }
}
