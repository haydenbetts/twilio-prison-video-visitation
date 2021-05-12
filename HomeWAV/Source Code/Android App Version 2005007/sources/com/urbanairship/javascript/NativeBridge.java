package com.urbanairship.javascript;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Cancelable;
import com.urbanairship.Logger;
import com.urbanairship.PendingResult;
import com.urbanairship.ResultCallback;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionCompletionCallback;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.actions.ActionRunRequestExtender;
import com.urbanairship.actions.ActionRunRequestFactory;
import com.urbanairship.actions.ActionValue;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UriUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public class NativeBridge {
    private static final String CLOSE_COMMAND = "close";
    private static final String RUN_ACTIONS_COMMAND = "run-actions";
    private static final String RUN_ACTIONS_COMMAND_CALLBACK = "run-action-cb";
    private static final String RUN_BASIC_ACTIONS_COMMAND = "run-basic-actions";
    private static final String UA_ACTION_SCHEME = "uairship";
    /* access modifiers changed from: private */
    public ActionCompletionCallback actionCompletionCallback;
    private final ActionRunRequestFactory actionRunRequestFactory;
    private final Executor executor;

    public interface CommandDelegate {
        void onAirshipCommand(String str, Uri uri);

        void onClose();
    }

    public NativeBridge() {
        this(new ActionRunRequestFactory(), AirshipExecutors.newSerialExecutor());
    }

    public NativeBridge(ActionRunRequestFactory actionRunRequestFactory2) {
        this(actionRunRequestFactory2, AirshipExecutors.newSerialExecutor());
    }

    NativeBridge(ActionRunRequestFactory actionRunRequestFactory2, Executor executor2) {
        this.actionRunRequestFactory = actionRunRequestFactory2;
        this.executor = executor2;
    }

    public boolean onHandleCommand(String str, JavaScriptExecutor javaScriptExecutor, ActionRunRequestExtender actionRunRequestExtender, CommandDelegate commandDelegate) {
        if (str == null) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if (parse.getHost() == null || !UA_ACTION_SCHEME.equals(parse.getScheme())) {
            return false;
        }
        Logger.verbose("Intercepting: %s", str);
        String host = parse.getHost();
        host.hashCode();
        char c = 65535;
        switch (host.hashCode()) {
            case -1507513413:
                if (host.equals(RUN_ACTIONS_COMMAND)) {
                    c = 0;
                    break;
                }
                break;
            case -1316475244:
                if (host.equals(RUN_ACTIONS_COMMAND_CALLBACK)) {
                    c = 1;
                    break;
                }
                break;
            case -189575524:
                if (host.equals(RUN_BASIC_ACTIONS_COMMAND)) {
                    c = 2;
                    break;
                }
                break;
            case 94756344:
                if (host.equals(CLOSE_COMMAND)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Logger.info("Running run actions command for URL: %s", str);
                runActions(actionRunRequestExtender, decodeActionArguments(parse, false));
                break;
            case 1:
                Logger.info("Running run actions command with callback for URL: %s", str);
                List<String> pathSegments = parse.getPathSegments();
                if (pathSegments.size() != 3) {
                    Logger.error("Unable to run action, invalid number of arguments.", new Object[0]);
                    break;
                } else {
                    Logger.info("Action: %s, Args: %s, Callback: %s", pathSegments.get(0), pathSegments.get(1), pathSegments.get(2));
                    runAction(actionRunRequestExtender, javaScriptExecutor, pathSegments.get(0), pathSegments.get(1), pathSegments.get(2));
                    break;
                }
            case 2:
                Logger.info("Running run basic actions command for URL: %s", str);
                runActions(actionRunRequestExtender, decodeActionArguments(parse, true));
                break;
            case 3:
                Logger.info("Running close command for URL: %s", str);
                commandDelegate.onClose();
                break;
            default:
                commandDelegate.onAirshipCommand(parse.getHost(), parse);
                break;
        }
        return true;
    }

    public void setActionCompletionCallback(ActionCompletionCallback actionCompletionCallback2) {
        this.actionCompletionCallback = actionCompletionCallback2;
    }

    public Cancelable loadJavaScriptEnvironment(final Context context, final JavaScriptEnvironment javaScriptEnvironment, final JavaScriptExecutor javaScriptExecutor) {
        Logger.info("Loading Airship Javascript interface.", new Object[0]);
        final PendingResult pendingResult = new PendingResult();
        pendingResult.addResultCallback(Looper.myLooper(), new ResultCallback<String>() {
            public void onResult(String str) {
                if (str != null) {
                    javaScriptExecutor.executeJavaScript(str);
                }
            }
        });
        this.executor.execute(new Runnable() {
            public void run() {
                pendingResult.setResult(javaScriptEnvironment.getJavaScript(context));
            }
        });
        return pendingResult;
    }

    private void runActions(ActionRunRequestExtender actionRunRequestExtender, Map<String, List<ActionValue>> map) {
        if (map != null) {
            for (String next : map.keySet()) {
                List<ActionValue> list = map.get(next);
                if (list != null) {
                    for (ActionValue value : list) {
                        actionRunRequestExtender.extend(this.actionRunRequestFactory.createActionRequest(next).setValue(value).setSituation(3)).run(new ActionCompletionCallback() {
                            public void onFinish(ActionArguments actionArguments, ActionResult actionResult) {
                                ActionCompletionCallback access$000 = NativeBridge.this.actionCompletionCallback;
                                if (access$000 != null) {
                                    access$000.onFinish(actionArguments, actionResult);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private void runAction(ActionRunRequestExtender actionRunRequestExtender, final JavaScriptExecutor javaScriptExecutor, final String str, String str2, final String str3) {
        try {
            actionRunRequestExtender.extend(this.actionRunRequestFactory.createActionRequest(str).setValue(new ActionValue(JsonValue.parseString(str2))).setSituation(3)).run(new ActionCompletionCallback() {
                public void onFinish(ActionArguments actionArguments, ActionResult actionResult) {
                    String str;
                    int status = actionResult.getStatus();
                    if (status == 2) {
                        str = String.format("Action %s rejected its arguments", new Object[]{str});
                    } else if (status == 3) {
                        str = String.format("Action %s not found", new Object[]{str});
                    } else if (status != 4) {
                        str = null;
                    } else if (actionResult.getException() != null) {
                        str = actionResult.getException().getMessage();
                    } else {
                        str = String.format("Action %s failed with unspecified error", new Object[]{str});
                    }
                    NativeBridge.this.triggerCallback(javaScriptExecutor, str, actionResult.getValue(), str3);
                    synchronized (this) {
                        if (NativeBridge.this.actionCompletionCallback != null) {
                            NativeBridge.this.actionCompletionCallback.onFinish(actionArguments, actionResult);
                        }
                    }
                }
            });
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse action argument value: %s", str2);
            triggerCallback(javaScriptExecutor, "Unable to decode arguments payload", new ActionValue(), str3);
        }
    }

    /* access modifiers changed from: private */
    public void triggerCallback(JavaScriptExecutor javaScriptExecutor, String str, ActionValue actionValue, String str2) {
        String str3;
        String format = String.format("'%s'", new Object[]{str2});
        if (str == null) {
            str3 = "null";
        } else {
            str3 = String.format(Locale.US, "new Error(%s)", new Object[]{JSONObject.quote(str)});
        }
        String actionValue2 = actionValue.toString();
        javaScriptExecutor.executeJavaScript(String.format(Locale.US, "UAirship.finishAction(%s, %s, %s);", new Object[]{str3, actionValue2, format}));
    }

    private Map<String, List<ActionValue>> decodeActionArguments(Uri uri, boolean z) {
        JsonValue jsonValue;
        Map<String, List<String>> queryParameters = UriUtils.getQueryParameters(uri);
        HashMap hashMap = new HashMap();
        for (String next : queryParameters.keySet()) {
            ArrayList arrayList = new ArrayList();
            if (queryParameters.get(next) == null) {
                Logger.warn("No arguments to decode for actionName: %s", next);
                return null;
            }
            List<String> list = queryParameters.get(next);
            if (list != null) {
                for (String str : list) {
                    if (z) {
                        try {
                            jsonValue = JsonValue.wrap(str);
                        } catch (JsonException e) {
                            Logger.warn(e, "Invalid json. Unable to create action argument " + next + " with args: " + str, new Object[0]);
                            return null;
                        }
                    } else {
                        jsonValue = JsonValue.parseString(str);
                    }
                    arrayList.add(new ActionValue(jsonValue));
                }
                hashMap.put(next, arrayList);
            }
        }
        if (!hashMap.isEmpty()) {
            return hashMap;
        }
        Logger.warn("Error no action names are present in the actions key set", new Object[0]);
        return null;
    }
}
