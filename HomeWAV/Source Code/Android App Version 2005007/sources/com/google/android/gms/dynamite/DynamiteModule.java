package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.microsoft.appcenter.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
public final class DynamiteModule {
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzf();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzh();
    public static final VersionPolicy PREFER_LOCAL = new zzd();
    public static final VersionPolicy PREFER_REMOTE = new zza();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzc();
    private static Boolean zza = null;
    private static zzk zzb = null;
    private static zzm zzc = null;
    private static String zzd = null;
    private static int zze = -1;
    private static final ThreadLocal<zza> zzf = new ThreadLocal<>();
    private static final VersionPolicy.zzb zzg = new zzb();
    private static final VersionPolicy zzh = new zzg();
    private final Context zzi;

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
        public static class zza {
            public int zza = 0;
            public int zzb = 0;
            public int zzc = 0;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
        public interface zzb {
            int zza(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        zza zza(Context context, String str, zzb zzb2) throws LoadingException;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    private static class zza {
        public Cursor zza;

        private zza() {
        }

        /* synthetic */ zza(zzb zzb) {
            this();
        }
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        VersionPolicy.zza zza2;
        ThreadLocal<zza> threadLocal = zzf;
        zza zza3 = threadLocal.get();
        zza zza4 = new zza((zzb) null);
        threadLocal.set(zza4);
        try {
            zza2 = versionPolicy.zza(context, str, zzg);
            int i = zza2.zza;
            int i2 = zza2.zzb;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (zza2.zzc == 0 || ((zza2.zzc == -1 && zza2.zza == 0) || (zza2.zzc == 1 && zza2.zzb == 0))) {
                int i3 = zza2.zza;
                int i4 = zza2.zzb;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i3);
                sb2.append(" and remote version is ");
                sb2.append(i4);
                sb2.append(".");
                throw new LoadingException(sb2.toString(), (zzb) null);
            } else if (zza2.zzc == -1) {
                DynamiteModule zza5 = zza(context, str);
                if (zza4.zza != null) {
                    zza4.zza.close();
                }
                threadLocal.set(zza3);
                return zza5;
            } else if (zza2.zzc == 1) {
                DynamiteModule zza6 = zza(context, str, zza2.zzb);
                if (zza4.zza != null) {
                    zza4.zza.close();
                }
                threadLocal.set(zza3);
                return zza6;
            } else {
                int i5 = zza2.zzc;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i5);
                throw new LoadingException(sb3.toString(), (zzb) null);
            }
        } catch (LoadingException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
            if (zza2.zza == 0 || versionPolicy.zza(context, str, new zzb(zza2.zza, 0)).zzc != -1) {
                throw new LoadingException("Remote load failed. No local fallback found.", e, (zzb) null);
            }
            DynamiteModule zza7 = zza(context, str);
            if (zza4.zza != null) {
                zza4.zza.close();
            }
            zzf.set(zza3);
            return zza7;
        } catch (Throwable th) {
            if (zza4.zza != null) {
                zza4.zza.close();
            }
            zzf.set(zza3);
            throw th;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, zzb zzb) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzb zzb) {
            this(str, th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
    private static class zzb implements VersionPolicy.zzb {
        private final int zza;
        private final int zzb = 0;

        public zzb(int i, int i2) {
            this.zza = i;
        }

        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        public final int zza(Context context, String str) {
            return this.zza;
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get((Object) null), str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0057=Splitter:B:23:0x0057, B:18:0x003a=Splitter:B:18:0x003a, B:39:0x009c=Splitter:B:39:0x009c} */
    public static int zza(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)     // Catch:{ all -> 0x0110 }
            java.lang.Boolean r1 = zza     // Catch:{ all -> 0x010d }
            if (r1 != 0) goto L_0x00da
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            java.lang.Class r1 = r1.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            java.lang.String r2 = "sClassLoader"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            java.lang.Class r2 = r1.getDeclaringClass()     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            monitor-enter(r2)     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
            r3 = 0
            java.lang.Object r4 = r1.get(r3)     // Catch:{ all -> 0x00aa }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x00aa }
            if (r4 == 0) goto L_0x003d
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00aa }
            if (r4 != r1) goto L_0x0037
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00aa }
            goto L_0x00a8
        L_0x0037:
            zza((java.lang.ClassLoader) r4)     // Catch:{ LoadingException -> 0x003a }
        L_0x003a:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00aa }
            goto L_0x00a8
        L_0x003d:
            java.lang.String r4 = "com.google.android.gms"
            android.content.Context r5 = r8.getApplicationContext()     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x00aa }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00aa }
            if (r4 == 0) goto L_0x0057
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00aa }
            r1.set(r3, r4)     // Catch:{ all -> 0x00aa }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00aa }
            goto L_0x00a8
        L_0x0057:
            int r4 = zzc(r8, r9, r10)     // Catch:{ LoadingException -> 0x009f }
            java.lang.String r5 = zzd     // Catch:{ LoadingException -> 0x009f }
            if (r5 == 0) goto L_0x009c
            boolean r5 = r5.isEmpty()     // Catch:{ LoadingException -> 0x009f }
            if (r5 == 0) goto L_0x0066
            goto L_0x009c
        L_0x0066:
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ LoadingException -> 0x009f }
            r6 = 29
            if (r5 < r6) goto L_0x007e
            dalvik.system.DelegateLastClassLoader r5 = new dalvik.system.DelegateLastClassLoader     // Catch:{ LoadingException -> 0x009f }
            java.lang.String r6 = zzd     // Catch:{ LoadingException -> 0x009f }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ LoadingException -> 0x009f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ LoadingException -> 0x009f }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x009f }
            r5.<init>(r6, r7)     // Catch:{ LoadingException -> 0x009f }
            goto L_0x008f
        L_0x007e:
            com.google.android.gms.dynamite.zzi r5 = new com.google.android.gms.dynamite.zzi     // Catch:{ LoadingException -> 0x009f }
            java.lang.String r6 = zzd     // Catch:{ LoadingException -> 0x009f }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ LoadingException -> 0x009f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ LoadingException -> 0x009f }
            java.lang.ClassLoader r7 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x009f }
            r5.<init>(r6, r7)     // Catch:{ LoadingException -> 0x009f }
        L_0x008f:
            zza((java.lang.ClassLoader) r5)     // Catch:{ LoadingException -> 0x009f }
            r1.set(r3, r5)     // Catch:{ LoadingException -> 0x009f }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ LoadingException -> 0x009f }
            zza = r5     // Catch:{ LoadingException -> 0x009f }
            monitor-exit(r2)     // Catch:{ all -> 0x00aa }
            monitor-exit(r0)     // Catch:{ all -> 0x010d }
            return r4
        L_0x009c:
            monitor-exit(r2)     // Catch:{ all -> 0x00aa }
            monitor-exit(r0)     // Catch:{ all -> 0x010d }
            return r4
        L_0x009f:
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00aa }
            r1.set(r3, r4)     // Catch:{ all -> 0x00aa }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00aa }
        L_0x00a8:
            monitor-exit(r2)     // Catch:{ all -> 0x00aa }
            goto L_0x00d8
        L_0x00aa:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00aa }
            throw r1     // Catch:{ ClassNotFoundException -> 0x00b1, IllegalAccessException -> 0x00af, NoSuchFieldException -> 0x00ad }
        L_0x00ad:
            r1 = move-exception
            goto L_0x00b2
        L_0x00af:
            r1 = move-exception
            goto L_0x00b2
        L_0x00b1:
            r1 = move-exception
        L_0x00b2:
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x010d }
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x010d }
            int r3 = r3.length()     // Catch:{ all -> 0x010d }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x010d }
            r4.<init>(r3)     // Catch:{ all -> 0x010d }
            java.lang.String r3 = "Failed to load module via V2: "
            r4.append(r3)     // Catch:{ all -> 0x010d }
            r4.append(r1)     // Catch:{ all -> 0x010d }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x010d }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x010d }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x010d }
        L_0x00d8:
            zza = r1     // Catch:{ all -> 0x010d }
        L_0x00da:
            monitor-exit(r0)     // Catch:{ all -> 0x010d }
            boolean r0 = r1.booleanValue()     // Catch:{ all -> 0x0110 }
            if (r0 == 0) goto L_0x0108
            int r8 = zzc(r8, r9, r10)     // Catch:{ LoadingException -> 0x00e6 }
            return r8
        L_0x00e6:
            r9 = move-exception
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version: "
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0110 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0110 }
            int r1 = r9.length()     // Catch:{ all -> 0x0110 }
            if (r1 == 0) goto L_0x00fe
            java.lang.String r9 = r0.concat(r9)     // Catch:{ all -> 0x0110 }
            goto L_0x0103
        L_0x00fe:
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x0110 }
            r9.<init>(r0)     // Catch:{ all -> 0x0110 }
        L_0x0103:
            android.util.Log.w(r10, r9)     // Catch:{ all -> 0x0110 }
            r8 = 0
            return r8
        L_0x0108:
            int r8 = zzb((android.content.Context) r8, (java.lang.String) r9, (boolean) r10)     // Catch:{ all -> 0x0110 }
            return r8
        L_0x010d:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x010d }
            throw r9     // Catch:{ all -> 0x0110 }
        L_0x0110:
            r9 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r8, r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0080 A[Catch:{ all -> 0x006d }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0085 A[Catch:{ all -> 0x006d }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(android.content.Context r6, java.lang.String r7, boolean r8) {
        /*
            java.lang.String r0 = "DynamiteModule"
            com.google.android.gms.dynamite.zzk r1 = zza((android.content.Context) r6)
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            r3 = 0
            int r4 = r1.zzb()     // Catch:{ RemoteException -> 0x006f }
            r5 = 3
            if (r4 < r5) goto L_0x004e
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r6)     // Catch:{ RemoteException -> 0x006f }
            com.google.android.gms.dynamic.IObjectWrapper r6 = r1.zzc(r6, r7, r8)     // Catch:{ RemoteException -> 0x006f }
            java.lang.Object r6 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r6)     // Catch:{ RemoteException -> 0x006f }
            android.database.Cursor r6 = (android.database.Cursor) r6     // Catch:{ RemoteException -> 0x006f }
            if (r6 == 0) goto L_0x0043
            boolean r7 = r6.moveToFirst()     // Catch:{ RemoteException -> 0x0040, all -> 0x003d }
            if (r7 != 0) goto L_0x0029
            goto L_0x0043
        L_0x0029:
            int r7 = r6.getInt(r2)     // Catch:{ RemoteException -> 0x0040, all -> 0x003d }
            if (r7 <= 0) goto L_0x0036
            boolean r8 = zza((android.database.Cursor) r6)     // Catch:{ RemoteException -> 0x0040, all -> 0x003d }
            if (r8 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r3 = r6
        L_0x0037:
            if (r3 == 0) goto L_0x003c
            r3.close()
        L_0x003c:
            return r7
        L_0x003d:
            r7 = move-exception
            r3 = r6
            goto L_0x0094
        L_0x0040:
            r7 = move-exception
            r3 = r6
            goto L_0x0070
        L_0x0043:
            java.lang.String r7 = "Failed to retrieve remote module version."
            android.util.Log.w(r0, r7)     // Catch:{ RemoteException -> 0x0040, all -> 0x003d }
            if (r6 == 0) goto L_0x004d
            r6.close()
        L_0x004d:
            return r2
        L_0x004e:
            r5 = 2
            if (r4 != r5) goto L_0x005f
            java.lang.String r4 = "IDynamite loader version = 2, no high precision latency measurement."
            android.util.Log.w(r0, r4)     // Catch:{ RemoteException -> 0x006f }
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r6)     // Catch:{ RemoteException -> 0x006f }
            int r6 = r1.zzb((com.google.android.gms.dynamic.IObjectWrapper) r6, (java.lang.String) r7, (boolean) r8)     // Catch:{ RemoteException -> 0x006f }
            return r6
        L_0x005f:
            java.lang.String r4 = "IDynamite loader version < 2, falling back to getModuleVersion2"
            android.util.Log.w(r0, r4)     // Catch:{ RemoteException -> 0x006f }
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r6)     // Catch:{ RemoteException -> 0x006f }
            int r6 = r1.zza((com.google.android.gms.dynamic.IObjectWrapper) r6, (java.lang.String) r7, (boolean) r8)     // Catch:{ RemoteException -> 0x006f }
            return r6
        L_0x006d:
            r7 = move-exception
            goto L_0x0094
        L_0x006f:
            r7 = move-exception
        L_0x0070:
            java.lang.String r6 = "Failed to retrieve remote module version: "
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x006d }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x006d }
            int r8 = r7.length()     // Catch:{ all -> 0x006d }
            if (r8 == 0) goto L_0x0085
            java.lang.String r6 = r6.concat(r7)     // Catch:{ all -> 0x006d }
            goto L_0x008b
        L_0x0085:
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x006d }
            r7.<init>(r6)     // Catch:{ all -> 0x006d }
            r6 = r7
        L_0x008b:
            android.util.Log.w(r0, r6)     // Catch:{ all -> 0x006d }
            if (r3 == 0) goto L_0x0093
            r3.close()
        L_0x0093:
            return r2
        L_0x0094:
            if (r3 == 0) goto L_0x0099
            r3.close()
        L_0x0099:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        if (zza(r8) != false) goto L_0x0073;
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            if (r10 == 0) goto L_0x000a
            java.lang.String r8 = "api_force_staging"
            goto L_0x000c
        L_0x000a:
            java.lang.String r8 = "api"
        L_0x000c:
            int r10 = r8.length()     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            int r10 = r10 + 42
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            int r2 = r2.length()     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            int r10 = r10 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            r2.<init>(r10)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            java.lang.String r10 = "content://com.google.android.gms.chimera/"
            r2.append(r10)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            r2.append(r8)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            r2.append(r9)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            java.lang.String r8 = r2.toString()     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0093, all -> 0x0091 }
            if (r8 == 0) goto L_0x0079
            boolean r9 = r8.moveToFirst()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            if (r9 == 0) goto L_0x0079
            r9 = 0
            int r9 = r8.getInt(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            if (r9 <= 0) goto L_0x0072
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r10 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r10)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r1 = 2
            java.lang.String r1 = r8.getString(r1)     // Catch:{ all -> 0x006f }
            zzd = r1     // Catch:{ all -> 0x006f }
            java.lang.String r1 = "loaderVersion"
            int r1 = r8.getColumnIndex(r1)     // Catch:{ all -> 0x006f }
            if (r1 < 0) goto L_0x0067
            int r1 = r8.getInt(r1)     // Catch:{ all -> 0x006f }
            zze = r1     // Catch:{ all -> 0x006f }
        L_0x0067:
            monitor-exit(r10)     // Catch:{ all -> 0x006f }
            boolean r10 = zza((android.database.Cursor) r8)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            if (r10 == 0) goto L_0x0072
            goto L_0x0073
        L_0x006f:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x006f }
            throw r9     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
        L_0x0072:
            r0 = r8
        L_0x0073:
            if (r0 == 0) goto L_0x0078
            r0.close()
        L_0x0078:
            return r9
        L_0x0079:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>((java.lang.String) r10, (com.google.android.gms.dynamite.zzb) r0)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            throw r9     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
        L_0x0088:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L_0x00a4
        L_0x008c:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x0095
        L_0x0091:
            r8 = move-exception
            goto L_0x00a4
        L_0x0093:
            r8 = move-exception
            r9 = r0
        L_0x0095:
            boolean r10 = r8 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x00a2 }
            if (r10 == 0) goto L_0x009a
            throw r8     // Catch:{ all -> 0x00a2 }
        L_0x009a:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = "V2 version check failed"
            r10.<init>(r1, r8, r0)     // Catch:{ all -> 0x00a2 }
            throw r10     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            r8 = move-exception
            r0 = r9
        L_0x00a4:
            if (r0 == 0) goto L_0x00a9
            r0.close()
        L_0x00a9:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    private static boolean zza(Cursor cursor) {
        zza zza2 = zzf.get();
        if (zza2 == null || zza2.zza != null) {
            return false;
        }
        zza2.zza = cursor;
        return true;
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zza(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        IObjectWrapper iObjectWrapper;
        try {
            synchronized (DynamiteModule.class) {
                bool = zza;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", (zzb) null);
            } else if (bool.booleanValue()) {
                return zzb(context, str, i);
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
                sb.append("Selected remote version of ");
                sb.append(str);
                sb.append(", version >= ");
                sb.append(i);
                Log.i("DynamiteModule", sb.toString());
                zzk zza2 = zza(context);
                if (zza2 != null) {
                    int zzb2 = zza2.zzb();
                    if (zzb2 >= 3) {
                        zza zza3 = zzf.get();
                        if (zza3 != null) {
                            iObjectWrapper = zza2.zza(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zza3.zza));
                        } else {
                            throw new LoadingException("No cached result cursor holder", (zzb) null);
                        }
                    } else if (zzb2 == 2) {
                        Log.w("DynamiteModule", "IDynamite loader version = 2");
                        iObjectWrapper = zza2.zzb(ObjectWrapper.wrap(context), str, i);
                    } else {
                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                        iObjectWrapper = zza2.zza(ObjectWrapper.wrap(context), str, i);
                    }
                    if (ObjectWrapper.unwrap(iObjectWrapper) != null) {
                        return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapper));
                    }
                    throw new LoadingException("Failed to load remote module.", (zzb) null);
                }
                throw new LoadingException("Failed to create IDynamiteLoader.", (zzb) null);
            }
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, (zzb) null);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, (zzb) null);
        }
    }

    private static zzk zza(Context context) {
        zzk zzk;
        synchronized (DynamiteModule.class) {
            zzk zzk2 = zzb;
            if (zzk2 != null) {
                return zzk2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzk = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof zzk) {
                        zzk = (zzk) queryLocalInterface;
                    } else {
                        zzk = new zzj(iBinder);
                    }
                }
                if (zzk != null) {
                    zzb = zzk;
                    return zzk;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
        }
        return null;
    }

    public final Context getModuleContext() {
        return this.zzi;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        zzm zzm;
        IObjectWrapper iObjectWrapper;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzm = zzc;
        }
        if (zzm != null) {
            zza zza2 = zzf.get();
            if (zza2 == null || zza2.zza == null) {
                throw new LoadingException("No result cursor", (zzb) null);
            }
            Context applicationContext = context.getApplicationContext();
            Cursor cursor = zza2.zza;
            ObjectWrapper.wrap(null);
            if (zza().booleanValue()) {
                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                iObjectWrapper = zzm.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                iObjectWrapper = zzm.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            }
            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            if (context2 != null) {
                return new DynamiteModule(context2);
            }
            throw new LoadingException("Failed to get module context", (zzb) null);
        }
        throw new LoadingException("DynamiteLoaderV2 was not cached.", (zzb) null);
    }

    private static Boolean zza() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zze >= 2);
        }
        return valueOf;
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzm zzm;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzm = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzm) {
                    zzm = (zzm) queryLocalInterface;
                } else {
                    zzm = new zzl(iBinder);
                }
            }
            zzc = zzm;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzb) null);
        }
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzi.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, (zzb) null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzi = (Context) Preconditions.checkNotNull(context);
    }
}
