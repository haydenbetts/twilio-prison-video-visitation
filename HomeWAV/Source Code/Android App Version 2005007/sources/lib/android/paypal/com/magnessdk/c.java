package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Iterator;
import lib.android.paypal.com.magnessdk.network.k;
import lib.android.paypal.com.magnessdk.network.m;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class c {
    static final int A = 16;
    static final int B = 17;
    static final int C = 18;
    static final int D = 19;
    static final int E = 20;
    static final int F = 21;
    static final int G = 22;
    static final int H = 23;
    static final int I = 25;
    static final int J = 26;
    static final int K = 27;
    static final int L = 28;
    static final int M = 29;
    static final int N = 30;
    static final int O = 31;
    static final int P = 32;
    static final int Q = 33;
    static final int R = 34;
    static final int S = 35;
    static final int T = 36;
    static final int U = 37;
    static final int V = 38;
    static final int W = 39;
    static final int X = 40;
    static final int Y = 41;
    static final int Z = 42;
    static final int a = -400;
    static final int aA = 69;
    static final int aB = 70;
    static final int aC = 71;
    static final int aD = 72;
    static final int aE = 73;
    static final int aF = 74;
    static final int aG = 75;
    static final int aH = 76;
    static final int aI = 77;
    static final int aJ = 78;
    static final int aK = 79;
    static final int aL = 80;
    static final int aM = 81;
    static final int aN = 82;
    static final int aO = 83;
    static final int aP = 84;
    static final int aQ = 85;
    static final int aR = 86;
    static final int aS = 87;
    static final int aT = 88;
    static final int aU = 89;
    static final int aV = 90;
    static final int aW = 91;
    static final int aX = 92;
    static final int aY = 93;
    static final int aZ = 94;
    static final int aa = 43;
    static final int ab = 44;
    static final int ac = 45;
    static final int ad = 46;
    static final int ae = 47;
    static final int af = 48;
    static final int ag = 49;
    static final int ah = 50;
    static final int ai = 51;
    static final int aj = 52;
    static final int ak = 53;
    static final int al = 54;
    static final int am = 55;
    static final int an = 56;
    static final int ao = 57;
    static final int ap = 58;
    static final int aq = 59;
    static final int ar = 60;
    static final int as = 61;
    static final int at = 62;
    static final int au = 63;
    static final int av = 64;
    static final int aw = 65;
    static final int ax = 66;
    static final int ay = 67;
    static final int az = 68;
    static final int b = -401;
    static final int ba = 95;
    static final int bb = 96;
    static final int bc = 97;
    static final int bd = 98;
    static boolean be = false;
    static String bf = null;
    static final int bg = 600;
    static final int bh = 601;
    static final int bi = 1;
    static final int bj = 2;
    static final int bk = 3;
    public static final String bl = "hw";
    static final int bm = 100;
    static final int bn = 10000;
    public static final int c = -402;
    public static final int d = -403;
    public static final int e = -405;
    static final int f = 12345;
    static final double g = 12345.0d;
    static final long h = 12345;
    static final float i = 12345.0f;
    static final String j = "default";
    static final int k = 0;
    static final int l = 1;
    static final int m = 2;
    static final int n = 3;
    static final int o = 4;
    static final int p = 5;
    static final int q = 6;
    static final int r = 7;
    static final int s = 8;
    static final int t = 9;
    static final int u = 10;
    static final int v = 11;
    static final int w = 12;
    static final int x = 13;
    static final int y = 14;
    static final int z = 15;

    @Retention(RetentionPolicy.SOURCE)
    @interface a {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface b {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: lib.android.paypal.com.magnessdk.c$c  reason: collision with other inner class name */
    @interface C0003c {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface d {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface e {
    }

    private JSONObject a(HashMap<String, String> hashMap) {
        try {
            return new JSONObject("{\"id\":" + hashMap.get("id") + ",\"created_at\":" + hashMap.get("created_at") + "}");
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public long a(int i2) {
        String str;
        long blockSize;
        int blockCount;
        File file = new File("/storage");
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                File file2 = listFiles[i3];
                if (file2.exists()) {
                    try {
                        if (Environment.isExternalStorageRemovable(file2)) {
                            str = file2.getAbsolutePath();
                            break;
                        }
                    } catch (Exception unused) {
                        continue;
                    }
                }
                i3++;
            }
        }
        str = "";
        if (!str.isEmpty()) {
            File file3 = new File(str);
            if (file3.exists()) {
                StatFs statFs = new StatFs(file3.getPath());
                if (i2 == 600) {
                    blockSize = (long) statFs.getBlockSize();
                    blockCount = statFs.getAvailableBlocks();
                } else if (i2 == bh) {
                    blockSize = (long) statFs.getBlockSize();
                    blockCount = statFs.getBlockCount();
                }
                return blockSize * ((long) blockCount);
            }
        }
        return h;
    }

    /* access modifiers changed from: package-private */
    public Object a(Object obj) {
        boolean z2 = obj instanceof Integer;
        Integer valueOf = Integer.valueOf(a);
        return z2 ? ((Integer) obj).intValue() == f ? valueOf : obj : obj instanceof Double ? ((Double) obj).doubleValue() == g ? valueOf : obj : obj instanceof Long ? ((Long) obj).longValue() == h ? valueOf : obj : obj instanceof Float ? ((Float) obj).floatValue() == i ? valueOf : obj : obj instanceof String ? obj.equals("default") ? "-400" : obj : valueOf;
    }

    /* access modifiers changed from: package-private */
    public abstract JSONObject a();

    /* access modifiers changed from: package-private */
    public JSONObject a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(lib.android.paypal.com.magnessdk.a.b.d, 0);
        bf = sharedPreferences.getString(lib.android.paypal.com.magnessdk.a.b.d, "");
        long j2 = sharedPreferences.getLong(lib.android.paypal.com.magnessdk.a.b.e, 0);
        if (bf.equals("") && j2 == 0) {
            Boolean bool = Boolean.TRUE;
            bf = b.a(true);
            j2 = System.currentTimeMillis();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(lib.android.paypal.com.magnessdk.a.b.d, bf);
            edit.putLong(lib.android.paypal.com.magnessdk.a.b.e, j2);
            edit.apply();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", bf);
        hashMap.put("created_at", j2 + "");
        return a((HashMap<String, String>) hashMap);
    }

    /* access modifiers changed from: package-private */
    public abstract JSONObject a(MagnesSettings magnesSettings, k kVar, m mVar);

    /* access modifiers changed from: package-private */
    public JSONObject a(JSONObject jSONObject) {
        JSONObject a2 = a();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                Object opt = a2.opt(next);
                if (opt == null) {
                    opt = jSONObject.get(next);
                } else if (opt instanceof JSONObject) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    Iterator<String> keys2 = jSONObject2.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        ((JSONObject) opt).put(next2, jSONObject2.get(next2));
                    }
                } else {
                    opt = jSONObject.get(next);
                }
                a2.put(next, opt);
            } catch (JSONException e2) {
                lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e2);
            }
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(int i2, MagnesSettings magnesSettings);

    /* access modifiers changed from: package-private */
    public boolean a(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Exception e2) {
            lib.android.paypal.com.magnessdk.b.a.a(getClass(), 3, (Throwable) e2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(k kVar, String str, String str2) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        int optInt = kVar.d().optInt(str2, 0);
        int abs = Math.abs(lowerCase.hashCode());
        if (abs <= 0) {
            return false;
        }
        str2.hashCode();
        if (!str2.equals(bl)) {
            return false;
        }
        int i2 = (abs / 100) % 100;
        if (i2 < optInt) {
            be = true;
        }
        return i2 < optInt;
    }
}
