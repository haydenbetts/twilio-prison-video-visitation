package org.apache.cordova;

public class ResumeCallback extends CallbackContext {
    private final String TAG = "CordovaResumeCallback";
    private PluginManager pluginManager;
    private String serviceName;

    public ResumeCallback(String str, PluginManager pluginManager2) {
        super("resumecallback", (CordovaWebView) null);
        this.serviceName = str;
        this.pluginManager = pluginManager2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.put("pluginServiceName", r5.serviceName);
        r1.put("pluginStatus", org.apache.cordova.PluginResult.StatusMessages[r6.getStatus()]);
        r0.put("action", "resume");
        r0.put("pendingResult", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0055, code lost:
        org.apache.cordova.LOG.e("CordovaResumeCallback", "Unable to create resume object for Activity Result");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        r0 = new org.json.JSONObject();
        r1 = new org.json.JSONObject();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendPluginResult(org.apache.cordova.PluginResult r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.finished     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = "CordovaResumeCallback"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r1.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r2 = r5.serviceName     // Catch:{ all -> 0x0083 }
            r1.append(r2)     // Catch:{ all -> 0x0083 }
            java.lang.String r2 = " attempted to send a second callback to ResumeCallback\nResult was: "
            r1.append(r2)     // Catch:{ all -> 0x0083 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0083 }
            r1.append(r6)     // Catch:{ all -> 0x0083 }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x0083 }
            org.apache.cordova.LOG.w((java.lang.String) r0, (java.lang.String) r6)     // Catch:{ all -> 0x0083 }
            monitor-exit(r5)     // Catch:{ all -> 0x0083 }
            return
        L_0x0026:
            r0 = 1
            r5.finished = r0     // Catch:{ all -> 0x0083 }
            monitor-exit(r5)     // Catch:{ all -> 0x0083 }
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "pluginServiceName"
            java.lang.String r3 = r5.serviceName     // Catch:{ JSONException -> 0x0055 }
            r1.put(r2, r3)     // Catch:{ JSONException -> 0x0055 }
            java.lang.String r2 = "pluginStatus"
            java.lang.String[] r3 = org.apache.cordova.PluginResult.StatusMessages     // Catch:{ JSONException -> 0x0055 }
            int r4 = r6.getStatus()     // Catch:{ JSONException -> 0x0055 }
            r3 = r3[r4]     // Catch:{ JSONException -> 0x0055 }
            r1.put(r2, r3)     // Catch:{ JSONException -> 0x0055 }
            java.lang.String r2 = "action"
            java.lang.String r3 = "resume"
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0055 }
            java.lang.String r2 = "pendingResult"
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x0055 }
            goto L_0x005c
        L_0x0055:
            java.lang.String r1 = "CordovaResumeCallback"
            java.lang.String r2 = "Unable to create resume object for Activity Result"
            org.apache.cordova.LOG.e(r1, r2)
        L_0x005c:
            org.apache.cordova.PluginResult r1 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r2 = org.apache.cordova.PluginResult.Status.OK
            r1.<init>((org.apache.cordova.PluginResult.Status) r2, (org.json.JSONObject) r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0.add(r1)
            r0.add(r6)
            org.apache.cordova.PluginManager r6 = r5.pluginManager
            java.lang.String r1 = "CoreAndroid"
            org.apache.cordova.CordovaPlugin r6 = r6.getPlugin(r1)
            org.apache.cordova.CoreAndroid r6 = (org.apache.cordova.CoreAndroid) r6
            org.apache.cordova.PluginResult r1 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r2 = org.apache.cordova.PluginResult.Status.OK
            r1.<init>((org.apache.cordova.PluginResult.Status) r2, (java.util.List<org.apache.cordova.PluginResult>) r0)
            r6.sendResumeEvent(r1)
            return
        L_0x0083:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0083 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.ResumeCallback.sendPluginResult(org.apache.cordova.PluginResult):void");
    }
}
