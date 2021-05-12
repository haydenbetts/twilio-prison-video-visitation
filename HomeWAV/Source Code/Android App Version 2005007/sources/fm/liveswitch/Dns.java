package fm.liveswitch;

public abstract class Dns {
    private static ILog __log = Log.getLogger(Dns.class);

    private static void doResolve(final Promise<String[]> promise, final String str, boolean z) {
        if (str == null) {
            promise.resolve(null);
        } else {
            resolve(str, new IAction2<String[], Object>() {
                public void invoke(String[] strArr, Object obj) {
                    if (strArr == null || ArrayExtensions.getLength((Object[]) strArr) <= 0) {
                        promise.reject(new Exception(StringExtensions.format("Hostname {0} could not be resolved.", (Object) str)));
                    } else {
                        promise.resolve(strArr);
                    }
                }
            }, (Object) null, z);
        }
    }

    public static Future<String[]> resolve(String str) {
        return resolve(str, false);
    }

    static Future<String[]> resolve(String str, boolean z) {
        Promise promise = new Promise();
        doResolve(promise, str, z);
        return promise;
    }

    public static void resolve(String str, IAction2<String[], Object> iAction2, Object obj) {
        resolve(str, iAction2, obj, false);
    }

    static void resolve(String str, IAction2<String[], Object> iAction2, Object obj, boolean z) {
        try {
            new DnsRequest(str, iAction2, obj).resolve(z);
        } catch (Exception e) {
            __log.error(StringExtensions.format("Could not resolve DNS name '{0}'.", (Object) str), e);
            iAction2.invoke(new String[0], obj);
        }
    }
}
