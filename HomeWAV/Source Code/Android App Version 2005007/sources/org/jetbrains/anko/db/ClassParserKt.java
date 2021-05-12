package org.jetbrains.anko.db;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u0001H\b\u001a\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004H\u0001\u001a\u0014\u0010\t\u001a\u00020\n2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u000b"}, d2 = {"castValue", "", "value", "type", "Ljava/lang/Class;", "classParser", "Lorg/jetbrains/anko/db/RowParser;", "T", "clazz", "hasApplicableType", "", "sqlite-base_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: ClassParser.kt */
public final class ClassParserKt {
    private static final <T> RowParser<T> classParser() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return classParser(Object.class);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        if (r7 != false) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> org.jetbrains.anko.db.RowParser<T> classParser(java.lang.Class<T> r11) {
        /*
            java.lang.String r0 = "clazz"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.reflect.Constructor[] r0 = r11.getDeclaredConstructors()
            java.lang.String r1 = "clazz.declaredConstructors"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            int r2 = r0.length
            r3 = 0
            r4 = 0
        L_0x0018:
            r5 = 1
            if (r4 >= r2) goto L_0x0060
            r6 = r0[r4]
            java.lang.String r7 = "ctr"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r7 = r6.isVarArgs()
            if (r7 != 0) goto L_0x0057
            int r7 = r6.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isPublic(r7)
            if (r7 != 0) goto L_0x0033
            goto L_0x0057
        L_0x0033:
            java.lang.Class[] r7 = r6.getParameterTypes()
            if (r7 == 0) goto L_0x0057
            int r8 = r7.length
            if (r8 != 0) goto L_0x003e
            r8 = 1
            goto L_0x003f
        L_0x003e:
            r8 = 0
        L_0x003f:
            r8 = r8 ^ r5
            if (r8 == 0) goto L_0x0057
            int r8 = r7.length
            r9 = 0
        L_0x0044:
            if (r9 >= r8) goto L_0x0053
            r10 = r7[r9]
            boolean r10 = hasApplicableType(r10)
            if (r10 != 0) goto L_0x0050
            r7 = 0
            goto L_0x0054
        L_0x0050:
            int r9 = r9 + 1
            goto L_0x0044
        L_0x0053:
            r7 = 1
        L_0x0054:
            if (r7 == 0) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            r5 = 0
        L_0x0058:
            if (r5 == 0) goto L_0x005d
            r1.add(r6)
        L_0x005d:
            int r4 = r4 + 1
            goto L_0x0018
        L_0x0060:
            java.util.List r1 = (java.util.List) r1
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x00b7
            int r11 = r1.size()
            if (r11 <= r5) goto L_0x00a9
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Collection r11 = (java.util.Collection) r11
            java.util.Iterator r0 = r1.iterator()
        L_0x007b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0094
            java.lang.Object r1 = r0.next()
            r2 = r1
            java.lang.reflect.Constructor r2 = (java.lang.reflect.Constructor) r2
            java.lang.Class<org.jetbrains.anko.db.ClassParserConstructor> r3 = org.jetbrains.anko.db.ClassParserConstructor.class
            boolean r2 = r2.isAnnotationPresent(r3)
            if (r2 == 0) goto L_0x007b
            r11.add(r1)
            goto L_0x007b
        L_0x0094:
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r11 = kotlin.collections.CollectionsKt.singleOrNull(r11)
            java.lang.reflect.Constructor r11 = (java.lang.reflect.Constructor) r11
            if (r11 == 0) goto L_0x009f
            goto L_0x00af
        L_0x009f:
            org.jetbrains.anko.AnkoException r11 = new org.jetbrains.anko.AnkoException
            java.lang.String r0 = "Several constructors are annotated with ClassParserConstructor"
            r11.<init>(r0)
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            throw r11
        L_0x00a9:
            java.lang.Object r11 = r1.get(r3)
            java.lang.reflect.Constructor r11 = (java.lang.reflect.Constructor) r11
        L_0x00af:
            org.jetbrains.anko.db.ClassParserKt$classParser$1 r0 = new org.jetbrains.anko.db.ClassParserKt$classParser$1
            r0.<init>(r11)
            org.jetbrains.anko.db.RowParser r0 = (org.jetbrains.anko.db.RowParser) r0
            return r0
        L_0x00b7:
            org.jetbrains.anko.AnkoException r0 = new org.jetbrains.anko.AnkoException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Can't initialize object parser for "
            r1.append(r2)
            java.lang.String r11 = r11.getCanonicalName()
            r1.append(r11)
            java.lang.String r11 = ", no acceptable constructors found"
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.<init>(r11)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.db.ClassParserKt.classParser(java.lang.Class):org.jetbrains.anko.db.RowParser");
    }

    private static final boolean hasApplicableType(Class<?> cls) {
        if (!cls.isPrimitive() && !Intrinsics.areEqual((Object) cls, (Object) String.class) && !Intrinsics.areEqual((Object) cls, (Object) CharSequence.class) && !Intrinsics.areEqual((Object) cls, (Object) Long.class) && !Intrinsics.areEqual((Object) cls, (Object) Integer.class) && !Intrinsics.areEqual((Object) cls, (Object) Byte.class) && !Intrinsics.areEqual((Object) cls, (Object) Character.class) && !Intrinsics.areEqual((Object) cls, (Object) Boolean.class) && !Intrinsics.areEqual((Object) cls, (Object) Float.class) && !Intrinsics.areEqual((Object) cls, (Object) Double.class) && !Intrinsics.areEqual((Object) cls, (Object) Short.class)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static final Object castValue(Object obj, Class<?> cls) {
        if (obj == null && cls.isPrimitive()) {
            throw new AnkoException("null can't be converted to the value of primitive type " + cls.getCanonicalName());
        } else if (obj == null || Intrinsics.areEqual((Object) cls, (Object) Object.class)) {
            return obj;
        } else {
            if (cls.isPrimitive() && Intrinsics.areEqual((Object) JavaSqliteUtils.PRIMITIVES_TO_WRAPPERS.get(cls), (Object) obj.getClass())) {
                return obj;
            }
            if ((obj instanceof Double) && (Intrinsics.areEqual((Object) cls, (Object) Float.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Float.class))) {
                return Float.valueOf((float) ((Number) obj).doubleValue());
            }
            if ((obj instanceof Float) && (Intrinsics.areEqual((Object) cls, (Object) Double.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Double.class))) {
                return Double.valueOf((double) ((Number) obj).floatValue());
            }
            if ((obj instanceof Character) && CharSequence.class.isAssignableFrom(cls)) {
                return obj.toString();
            }
            if (obj instanceof Long) {
                if (Intrinsics.areEqual((Object) cls, (Object) Integer.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Integer.class)) {
                    return Integer.valueOf((int) ((Number) obj).longValue());
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Short.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Short.class)) {
                    return Short.valueOf((short) ((int) ((Number) obj).longValue()));
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Byte.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Byte.class)) {
                    return Byte.valueOf((byte) ((int) ((Number) obj).longValue()));
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Boolean.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Boolean.class)) {
                    return Boolean.valueOf(!Intrinsics.areEqual(obj, (Object) 0L));
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Character.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Character.class)) {
                    return Character.valueOf((char) ((int) ((Number) obj).longValue()));
                }
            }
            if (obj instanceof Integer) {
                if (Intrinsics.areEqual((Object) cls, (Object) Long.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Long.class)) {
                    return Long.valueOf((long) ((Number) obj).intValue());
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Short.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Short.class)) {
                    return Short.valueOf((short) ((Number) obj).intValue());
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Byte.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Byte.class)) {
                    return Byte.valueOf((byte) ((Number) obj).intValue());
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Boolean.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Boolean.class)) {
                    return Boolean.valueOf(!Intrinsics.areEqual(obj, (Object) 0));
                }
                if (Intrinsics.areEqual((Object) cls, (Object) Character.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Character.class)) {
                    return Character.valueOf((char) ((Number) obj).intValue());
                }
            }
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 1 && (Intrinsics.areEqual((Object) cls, (Object) Character.TYPE) || Intrinsics.areEqual((Object) cls, (Object) Character.class))) {
                    return Character.valueOf(str.charAt(0));
                }
            }
            throw new AnkoException("Value " + obj + " of type " + obj.getClass() + " can't be cast to " + cls.getCanonicalName());
        }
    }
}
