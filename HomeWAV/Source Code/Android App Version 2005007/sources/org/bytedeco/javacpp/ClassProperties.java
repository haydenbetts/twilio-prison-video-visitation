package org.bytedeco.javacpp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.bytedeco.javacpp.tools.Logger;

public class ClassProperties extends HashMap<String, List<String>> {
    private static final Logger logger = Logger.create(ClassProperties.class);
    String[] defaultNames = new String[0];
    List<Class> effectiveClasses = null;
    List<Class> inheritedClasses = null;
    boolean loaded = false;
    String pathSeparator;
    String platform;
    String platformExtension;
    String platformRoot;

    public ClassProperties() {
    }

    public ClassProperties(Properties properties) {
        this.platform = properties.getProperty("platform");
        this.platformExtension = properties.getProperty("platform.extension");
        this.platformRoot = properties.getProperty("platform.root");
        this.pathSeparator = properties.getProperty("platform.path.separator");
        String str = this.platformRoot;
        if (str == null || str.length() == 0) {
            this.platformRoot = ".";
        }
        if (!this.platformRoot.endsWith(File.separator)) {
            this.platformRoot += File.separator;
        }
        for (Map.Entry entry : properties.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (!(str3 == null || str3.length() == 0)) {
                if (str2.equals("platform.includepath") || str2.equals("platform.includeresource") || str2.equals("platform.include") || str2.equals("platform.linkpath") || str2.equals("platform.linkresource") || str2.equals("platform.link") || str2.equals("platform.preloadpath") || str2.equals("platform.preloadresource") || str2.equals("platform.preload") || str2.equals("platform.resourcepath") || str2.equals("platform.resource") || str2.equals("platform.frameworkpath") || str2.equals("platform.framework") || str2.equals("platform.executablepath") || str2.equals("platform.executable") || str2.equals("platform.library.suffix") || str2.equals("platform.extension")) {
                    addAll(str2, str3.split(this.pathSeparator));
                } else {
                    setProperty(str2, str3);
                }
            }
        }
    }

    public List<String> get(String str) {
        List<String> list = (List) super.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        put(str, arrayList);
        return arrayList;
    }

    public void addAll(String str, String... strArr) {
        if (strArr != null) {
            addAll(str, (Collection<String>) Arrays.asList(strArr));
        }
    }

    public void addAll(String str, Collection<String> collection) {
        if (collection != null) {
            String str2 = null;
            if (str.equals("platform.compiler") || str.equals("platform.sysroot") || str.equals("platform.toolchain") || str.equals("platform.includepath") || str.equals("platform.linkpath")) {
                str2 = this.platformRoot;
            }
            List<String> list = get(str);
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next != null) {
                    if (str2 != null && !new File(next).isAbsolute()) {
                        if (new File(str2 + next).exists()) {
                            next = str2 + next;
                        }
                    }
                    if (!list.contains(next)) {
                        list.add(next);
                    }
                }
            }
        }
    }

    public String getProperty(String str) {
        return getProperty(str, (String) null);
    }

    public String getProperty(String str, String str2) {
        List<String> list = get(str);
        return list.isEmpty() ? str2 : list.get(0);
    }

    public String setProperty(String str, String str2) {
        List<String> list = get(str);
        String str3 = list.isEmpty() ? null : list.get(0);
        list.clear();
        addAll(str, str2);
        return str3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:202:0x03fb  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0441  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0527 A[SYNTHETIC, Splitter:B:228:0x0527] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x043d A[EDGE_INSN: B:255:0x043d->B:209:0x043d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01a3 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x022b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(java.lang.Class r42, boolean r43) {
        /*
            r41 = this;
            r1 = r41
            r0 = r43
            java.lang.Class r2 = org.bytedeco.javacpp.Loader.getEnclosingClass(r42)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            r3.add(r4, r2)
        L_0x0011:
            java.lang.Class<org.bytedeco.javacpp.annotation.Properties> r5 = org.bytedeco.javacpp.annotation.Properties.class
            boolean r5 = r2.isAnnotationPresent(r5)
            if (r5 != 0) goto L_0x003f
            java.lang.Class<org.bytedeco.javacpp.annotation.Platform> r5 = org.bytedeco.javacpp.annotation.Platform.class
            boolean r5 = r2.isAnnotationPresent(r5)
            if (r5 != 0) goto L_0x003f
            java.lang.Class r5 = r2.getSuperclass()
            if (r5 == 0) goto L_0x003f
            java.lang.Class r5 = r2.getSuperclass()
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            if (r5 == r6) goto L_0x003f
            java.lang.Class r5 = r2.getSuperclass()
            java.lang.Class<org.bytedeco.javacpp.Pointer> r6 = org.bytedeco.javacpp.Pointer.class
            if (r5 == r6) goto L_0x003f
            java.lang.Class r2 = r2.getSuperclass()
            r3.add(r4, r2)
            goto L_0x0011
        L_0x003f:
            java.util.List<java.lang.Class> r5 = r1.effectiveClasses
            if (r5 != 0) goto L_0x0045
            r1.effectiveClasses = r3
        L_0x0045:
            java.lang.Class<org.bytedeco.javacpp.annotation.Properties> r3 = org.bytedeco.javacpp.annotation.Properties.class
            java.lang.annotation.Annotation r3 = r2.getAnnotation(r3)
            org.bytedeco.javacpp.annotation.Properties r3 = (org.bytedeco.javacpp.annotation.Properties) r3
            java.lang.Class<org.bytedeco.javacpp.annotation.Platform> r5 = org.bytedeco.javacpp.annotation.Platform.class
            java.lang.annotation.Annotation r5 = r2.getAnnotation(r5)
            org.bytedeco.javacpp.annotation.Platform r5 = (org.bytedeco.javacpp.annotation.Platform) r5
            java.lang.String r6 = "global"
            r7 = 0
            r8 = 1
            if (r3 == 0) goto L_0x011f
            java.lang.Class[] r7 = r3.inherit()
            if (r0 == 0) goto L_0x0087
            if (r7 == 0) goto L_0x0087
            java.util.List<java.lang.Class> r9 = r1.inheritedClasses
            if (r9 != 0) goto L_0x006e
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r1.inheritedClasses = r9
        L_0x006e:
            int r9 = r7.length
            r10 = 0
        L_0x0070:
            if (r10 >= r9) goto L_0x0087
            r11 = r7[r10]
            r1.load(r11, r0)
            java.util.List<java.lang.Class> r12 = r1.inheritedClasses
            boolean r12 = r12.contains(r11)
            if (r12 != 0) goto L_0x0084
            java.util.List<java.lang.Class> r12 = r1.inheritedClasses
            r12.add(r11)
        L_0x0084:
            int r10 = r10 + 1
            goto L_0x0070
        L_0x0087:
            java.lang.String r0 = r3.target()
            java.lang.String r7 = r3.global()
            int r9 = r7.length()
            java.lang.String r10 = "."
            if (r9 != 0) goto L_0x0099
            r7 = r0
            goto L_0x00bf
        L_0x0099:
            int r9 = r0.length()
            if (r9 != 0) goto L_0x00a1
            r0 = r7
            goto L_0x00bf
        L_0x00a1:
            int r9 = r0.length()
            if (r9 <= 0) goto L_0x00bf
            boolean r9 = r7.contains(r10)
            if (r9 != 0) goto L_0x00bf
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r0)
            r9.append(r10)
            r9.append(r7)
            java.lang.String r7 = r9.toString()
        L_0x00bf:
            int r9 = r0.length()
            if (r9 <= 0) goto L_0x00ce
            java.lang.String[] r9 = new java.lang.String[r8]
            r9[r4] = r0
            java.lang.String r11 = "target"
            r1.addAll((java.lang.String) r11, (java.lang.String[]) r9)
        L_0x00ce:
            int r9 = r7.length()
            if (r9 <= 0) goto L_0x00db
            java.lang.String[] r9 = new java.lang.String[r8]
            r9[r4] = r7
            r1.addAll((java.lang.String) r6, (java.lang.String[]) r9)
        L_0x00db:
            java.lang.String r9 = r3.helper()
            int r11 = r9.length()
            if (r11 <= 0) goto L_0x00fd
            boolean r11 = r9.contains(r10)
            if (r11 != 0) goto L_0x00fd
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r0)
            r11.append(r10)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
        L_0x00fd:
            int r0 = r9.length()
            if (r0 <= 0) goto L_0x010c
            java.lang.String[] r0 = new java.lang.String[r8]
            r0[r4] = r9
            java.lang.String r9 = "helper"
            r1.addAll((java.lang.String) r9, (java.lang.String[]) r0)
        L_0x010c:
            java.lang.String[] r0 = r3.names()
            int r9 = r0.length
            if (r9 <= 0) goto L_0x0115
            r1.defaultNames = r0
        L_0x0115:
            org.bytedeco.javacpp.annotation.Platform[] r0 = r3.value()
            r40 = r7
            r7 = r0
            r0 = r40
            goto L_0x0120
        L_0x011f:
            r0 = r7
        L_0x0120:
            if (r5 == 0) goto L_0x0135
            if (r7 != 0) goto L_0x0129
            org.bytedeco.javacpp.annotation.Platform[] r7 = new org.bytedeco.javacpp.annotation.Platform[r8]
            r7[r4] = r5
            goto L_0x0135
        L_0x0129:
            int r9 = r7.length
            int r9 = r9 + r8
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r9)
            org.bytedeco.javacpp.annotation.Platform[] r7 = (org.bytedeco.javacpp.annotation.Platform[]) r7
            int r9 = r7.length
            int r9 = r9 - r8
            r7[r9] = r5
        L_0x0135:
            if (r7 == 0) goto L_0x0143
            int r9 = r7.length
            if (r3 == 0) goto L_0x013e
            if (r5 == 0) goto L_0x013e
            r3 = 1
            goto L_0x013f
        L_0x013e:
            r3 = 0
        L_0x013f:
            if (r9 <= r3) goto L_0x0143
            r3 = 1
            goto L_0x0144
        L_0x0143:
            r3 = 0
        L_0x0144:
            java.lang.String[] r5 = new java.lang.String[r4]
            java.lang.String[] r9 = new java.lang.String[r4]
            java.lang.String[] r10 = new java.lang.String[r4]
            java.lang.String[] r11 = new java.lang.String[r4]
            java.lang.String[] r12 = new java.lang.String[r4]
            java.lang.String[] r13 = new java.lang.String[r4]
            java.lang.String[] r14 = new java.lang.String[r4]
            java.lang.String[] r15 = new java.lang.String[r4]
            java.lang.String[] r8 = new java.lang.String[r4]
            r43 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r17 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r18 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r19 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r20 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r21 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r22 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r23 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r24 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r25 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r26 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            r27 = r5
            java.lang.String[] r5 = new java.lang.String[r4]
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r28 = r5
            java.lang.String r5 = "jni"
            r4.append(r5)
            r29 = r8
            java.lang.String r8 = r2.getSimpleName()
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            r8 = 46
            if (r3 == 0) goto L_0x01c5
            if (r0 == 0) goto L_0x01f5
            int r6 = r0.length()
            if (r6 <= 0) goto L_0x01f5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            int r5 = r0.lastIndexOf(r8)
            r6 = 1
            int r5 = r5 + r6
            java.lang.String r0 = r0.substring(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            goto L_0x01f5
        L_0x01c5:
            java.util.List r0 = r1.get(r6)
            if (r0 == 0) goto L_0x01f5
            int r6 = r0.size()
            if (r6 <= 0) goto L_0x01f5
            int r4 = r0.size()
            r6 = 1
            int r4 = r4 - r6
            java.lang.Object r0 = r0.get(r4)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            int r5 = r0.lastIndexOf(r8)
            int r5 = r5 + r6
            java.lang.String r0 = r0.substring(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
        L_0x01f5:
            if (r7 == 0) goto L_0x01f8
            goto L_0x01fb
        L_0x01f8:
            r0 = 0
            org.bytedeco.javacpp.annotation.Platform[] r7 = new org.bytedeco.javacpp.annotation.Platform[r0]
        L_0x01fb:
            int r0 = r7.length
            r5 = r43
            r35 = r9
            r36 = r10
            r37 = r11
            r38 = r12
            r39 = r13
            r8 = r14
            r6 = r17
            r9 = r18
            r10 = r19
            r11 = r20
            r12 = r21
            r13 = r22
            r14 = r23
            r30 = r25
            r33 = r26
            r31 = r27
            r32 = r28
            r34 = r29
            r18 = r2
            r17 = r3
            r3 = r15
            r15 = r24
            r2 = 0
        L_0x0229:
            if (r2 >= r0) goto L_0x03eb
            r19 = r7[r2]
            r20 = r0
            r0 = 2
            r21 = r7
            java.lang.String[][] r7 = new java.lang.String[r0][]
            java.lang.String[] r0 = r19.value()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0240
            java.lang.String[] r0 = r19.value()
            goto L_0x0242
        L_0x0240:
            java.lang.String[] r0 = r1.defaultNames
        L_0x0242:
            r23 = 0
            r7[r23] = r0
            java.lang.String[] r0 = r19.not()
            r16 = 1
            r7[r16] = r0
            r22 = r4
            r0 = 2
            boolean[] r4 = new boolean[r0]
            r4 = {0, 0} // fill-array
            r23 = r15
            r15 = 0
        L_0x0259:
            if (r15 >= r0) goto L_0x0286
            r0 = r7[r15]
            r25 = r14
            int r14 = r0.length
            r26 = r12
            r12 = 0
        L_0x0263:
            if (r12 >= r14) goto L_0x027e
            r27 = r14
            r14 = r0[r12]
            r28 = r0
            java.lang.String r0 = r1.platform
            boolean r0 = r0.startsWith(r14)
            if (r0 == 0) goto L_0x0277
            r0 = 1
            r4[r15] = r0
            goto L_0x027e
        L_0x0277:
            int r12 = r12 + 1
            r14 = r27
            r0 = r28
            goto L_0x0263
        L_0x027e:
            int r15 = r15 + 1
            r14 = r25
            r12 = r26
            r0 = 2
            goto L_0x0259
        L_0x0286:
            r26 = r12
            r25 = r14
            r0 = 0
            r12 = r7[r0]
            int r12 = r12.length
            if (r12 == 0) goto L_0x0294
            boolean r12 = r4[r0]
            if (r12 == 0) goto L_0x02d7
        L_0x0294:
            r0 = 1
            r7 = r7[r0]
            int r7 = r7.length
            if (r7 == 0) goto L_0x029e
            boolean r4 = r4[r0]
            if (r4 != 0) goto L_0x02d7
        L_0x029e:
            java.lang.String[] r4 = r19.extension()
            int r4 = r4.length
            if (r4 == 0) goto L_0x02b2
            boolean r4 = org.bytedeco.javacpp.Loader.isLoadLibraries()
            if (r4 == 0) goto L_0x02b0
            java.lang.String r4 = r1.platformExtension
            if (r4 != 0) goto L_0x02b0
            goto L_0x02b2
        L_0x02b0:
            r4 = 0
            goto L_0x02b3
        L_0x02b2:
            r4 = 1
        L_0x02b3:
            java.lang.String[] r7 = r19.extension()
            int r12 = r7.length
            r14 = 0
        L_0x02b9:
            if (r14 >= r12) goto L_0x02d5
            r15 = r7[r14]
            java.lang.String r0 = r1.platformExtension
            if (r0 == 0) goto L_0x02d1
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x02d1
            java.lang.String r0 = r1.platformExtension
            boolean r0 = r0.endsWith(r15)
            if (r0 == 0) goto L_0x02d1
            r4 = 1
            goto L_0x02d5
        L_0x02d1:
            int r14 = r14 + 1
            r0 = 1
            goto L_0x02b9
        L_0x02d5:
            if (r4 != 0) goto L_0x02e1
        L_0x02d7:
            r4 = r22
            r15 = r23
            r14 = r25
            r12 = r26
            goto L_0x03e3
        L_0x02e1:
            java.lang.String[] r0 = r19.pragma()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x02ec
            java.lang.String[] r5 = r19.pragma()
        L_0x02ec:
            java.lang.String[] r0 = r19.define()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x02f7
            java.lang.String[] r35 = r19.define()
        L_0x02f7:
            java.lang.String[] r0 = r19.exclude()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0302
            java.lang.String[] r36 = r19.exclude()
        L_0x0302:
            java.lang.String[] r0 = r19.include()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x030d
            java.lang.String[] r37 = r19.include()
        L_0x030d:
            java.lang.String[] r0 = r19.cinclude()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0318
            java.lang.String[] r38 = r19.cinclude()
        L_0x0318:
            java.lang.String[] r0 = r19.includepath()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0323
            java.lang.String[] r39 = r19.includepath()
        L_0x0323:
            java.lang.String[] r0 = r19.includeresource()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x032e
            java.lang.String[] r8 = r19.includeresource()
        L_0x032e:
            java.lang.String[] r0 = r19.compiler()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0339
            java.lang.String[] r3 = r19.compiler()
        L_0x0339:
            java.lang.String[] r0 = r19.linkpath()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0344
            java.lang.String[] r34 = r19.linkpath()
        L_0x0344:
            java.lang.String[] r0 = r19.linkresource()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x034f
            java.lang.String[] r6 = r19.linkresource()
        L_0x034f:
            java.lang.String[] r0 = r19.link()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x035a
            java.lang.String[] r9 = r19.link()
        L_0x035a:
            java.lang.String[] r0 = r19.frameworkpath()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0365
            java.lang.String[] r10 = r19.frameworkpath()
        L_0x0365:
            java.lang.String[] r0 = r19.framework()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0370
            java.lang.String[] r11 = r19.framework()
        L_0x0370:
            java.lang.String[] r0 = r19.preloadresource()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x037b
            java.lang.String[] r13 = r19.preloadresource()
        L_0x037b:
            java.lang.String[] r0 = r19.preloadpath()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0387
            java.lang.String[] r12 = r19.preloadpath()
            goto L_0x0389
        L_0x0387:
            r12 = r26
        L_0x0389:
            java.lang.String[] r0 = r19.preload()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x0395
            java.lang.String[] r14 = r19.preload()
            goto L_0x0397
        L_0x0395:
            r14 = r25
        L_0x0397:
            java.lang.String[] r0 = r19.resourcepath()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x03a3
            java.lang.String[] r15 = r19.resourcepath()
            goto L_0x03a5
        L_0x03a3:
            r15 = r23
        L_0x03a5:
            java.lang.String[] r0 = r19.resource()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x03b0
            java.lang.String[] r30 = r19.resource()
        L_0x03b0:
            java.lang.String[] r0 = r19.extension()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x03bb
            java.lang.String[] r33 = r19.extension()
        L_0x03bb:
            java.lang.String[] r0 = r19.executablepath()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x03c6
            java.lang.String[] r31 = r19.executablepath()
        L_0x03c6:
            java.lang.String[] r0 = r19.executable()
            int r0 = r0.length
            if (r0 <= 0) goto L_0x03d1
            java.lang.String[] r32 = r19.executable()
        L_0x03d1:
            java.lang.String r0 = r19.library()
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x03e1
            java.lang.String r0 = r19.library()
            r4 = r0
            goto L_0x03e3
        L_0x03e1:
            r4 = r22
        L_0x03e3:
            int r2 = r2 + 1
            r0 = r20
            r7 = r21
            goto L_0x0229
        L_0x03eb:
            r22 = r4
            r26 = r12
            r25 = r14
            r23 = r15
            r0 = 0
        L_0x03f4:
            int r2 = r8.length
            r4 = 47
            java.lang.String r7 = "/"
            if (r0 >= r2) goto L_0x043d
            r2 = r8[r0]
            boolean r12 = r2.startsWith(r7)
            if (r12 != 0) goto L_0x043a
            java.lang.String r12 = r42.getName()
            r14 = 46
            java.lang.String r12 = r12.replace(r14, r4)
            int r4 = r12.lastIndexOf(r4)
            if (r4 < 0) goto L_0x0429
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            int r4 = r4 + 1
            r15 = 0
            java.lang.String r4 = r12.substring(r15, r4)
            r14.append(r4)
            r14.append(r2)
            java.lang.String r2 = r14.toString()
        L_0x0429:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r7)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r8[r0] = r2
        L_0x043a:
            int r0 = r0 + 1
            goto L_0x03f4
        L_0x043d:
            r0 = 0
        L_0x043e:
            int r2 = r6.length
            if (r0 >= r2) goto L_0x0489
            r2 = r6[r0]
            boolean r12 = r2.startsWith(r7)
            if (r12 != 0) goto L_0x0483
            java.lang.String r12 = r42.getName()
            r14 = 46
            java.lang.String r12 = r12.replace(r14, r4)
            int r15 = r12.lastIndexOf(r4)
            if (r15 < 0) goto L_0x0470
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r15 = r15 + 1
            r14 = 0
            java.lang.String r12 = r12.substring(r14, r15)
            r4.append(r12)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            goto L_0x0471
        L_0x0470:
            r14 = 0
        L_0x0471:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r7)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r6[r0] = r2
            goto L_0x0484
        L_0x0483:
            r14 = 0
        L_0x0484:
            int r0 = r0 + 1
            r4 = 47
            goto L_0x043e
        L_0x0489:
            java.lang.String r0 = "platform.pragma"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r5)
            java.lang.String r0 = "platform.define"
            r2 = r35
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.exclude"
            r2 = r36
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.include"
            r2 = r37
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.cinclude"
            r12 = r38
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r12)
            java.lang.String r0 = "platform.includepath"
            r2 = r39
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.includeresource"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r8)
            java.lang.String r0 = "platform.compiler.*"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r3)
            java.lang.String r0 = "platform.linkpath"
            r2 = r34
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.linkresource"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r6)
            java.lang.String r0 = "platform.link"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r9)
            java.lang.String r0 = "platform.frameworkpath"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r10)
            java.lang.String r0 = "platform.framework"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r11)
            java.lang.String r0 = "platform.preloadresource"
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r13)
            java.lang.String r0 = "platform.preloadpath"
            r12 = r26
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r12)
            java.lang.String r0 = "platform.preload"
            r14 = r25
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r14)
            java.lang.String r0 = "platform.resourcepath"
            r15 = r23
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r15)
            java.lang.String r0 = "platform.resource"
            r2 = r30
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = r1.platformExtension
            if (r0 == 0) goto L_0x0501
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0508
        L_0x0501:
            java.lang.String r0 = "platform.extension"
            r2 = r33
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
        L_0x0508:
            java.lang.String r0 = "platform.executablepath"
            r2 = r31
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.executable"
            r2 = r32
            r1.addAll((java.lang.String) r0, (java.lang.String[]) r2)
            java.lang.String r0 = "platform.library"
            r4 = r22
            r1.setProperty(r0, r4)
            java.lang.Class<org.bytedeco.javacpp.LoadEnabled> r0 = org.bytedeco.javacpp.LoadEnabled.class
            r2 = r18
            boolean r0 = r0.isAssignableFrom(r2)
            if (r0 == 0) goto L_0x0554
            java.lang.Object r0 = r2.newInstance()     // Catch:{ ClassCastException -> 0x0535, InstantiationException -> 0x0533, IllegalAccessException -> 0x0531 }
            org.bytedeco.javacpp.LoadEnabled r0 = (org.bytedeco.javacpp.LoadEnabled) r0     // Catch:{ ClassCastException -> 0x0535, InstantiationException -> 0x0533, IllegalAccessException -> 0x0531 }
            r0.init(r1)     // Catch:{ ClassCastException -> 0x0535, InstantiationException -> 0x0533, IllegalAccessException -> 0x0531 }
            goto L_0x0554
        L_0x0531:
            r0 = move-exception
            goto L_0x0536
        L_0x0533:
            r0 = move-exception
            goto L_0x0536
        L_0x0535:
            r0 = move-exception
        L_0x0536:
            org.bytedeco.javacpp.tools.Logger r3 = logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Could not create an instance of "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = ": "
            r4.append(r2)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r3.warn(r0)
        L_0x0554:
            boolean r0 = r1.loaded
            r0 = r0 | r17
            r1.loaded = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.ClassProperties.load(java.lang.Class, boolean):void");
    }

    public List<Class> getInheritedClasses() {
        return this.inheritedClasses;
    }

    public List<Class> getEffectiveClasses() {
        return this.effectiveClasses;
    }

    public boolean isLoaded() {
        return this.loaded;
    }
}
