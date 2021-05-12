package com.google.gson.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PostConstructAdapterFactory implements TypeAdapterFactory {
    /* JADX WARNING: type inference failed for: r8v0, types: [com.google.gson.reflect.TypeToken<T>, com.google.gson.reflect.TypeToken] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.google.gson.TypeAdapter<T> create(com.google.gson.Gson r7, com.google.gson.reflect.TypeToken<T> r8) {
        /*
            r6 = this;
            java.lang.Class r0 = r8.getRawType()
        L_0x0004:
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            if (r0 == r1) goto L_0x0036
            java.lang.Class r1 = r0.getSuperclass()
            if (r1 == 0) goto L_0x0036
            java.lang.reflect.Method[] r1 = r0.getDeclaredMethods()
            int r2 = r1.length
            r3 = 0
        L_0x0014:
            if (r3 >= r2) goto L_0x0031
            r4 = r1[r3]
            java.lang.Class<javax.annotation.PostConstruct> r5 = javax.annotation.PostConstruct.class
            boolean r5 = r4.isAnnotationPresent(r5)
            if (r5 == 0) goto L_0x002e
            r0 = 1
            r4.setAccessible(r0)
            com.google.gson.TypeAdapter r7 = r7.getDelegateAdapter(r6, r8)
            com.google.gson.typeadapters.PostConstructAdapterFactory$PostConstructAdapter r8 = new com.google.gson.typeadapters.PostConstructAdapterFactory$PostConstructAdapter
            r8.<init>(r7, r4)
            return r8
        L_0x002e:
            int r3 = r3 + 1
            goto L_0x0014
        L_0x0031:
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x0004
        L_0x0036:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.typeadapters.PostConstructAdapterFactory.create(com.google.gson.Gson, com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter");
    }

    static final class PostConstructAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegate;
        private final Method method;

        public PostConstructAdapter(TypeAdapter<T> typeAdapter, Method method2) {
            this.delegate = typeAdapter;
            this.method = method2;
        }

        public T read(JsonReader jsonReader) throws IOException {
            T read = this.delegate.read(jsonReader);
            if (read != null) {
                try {
                    this.method.invoke(read, new Object[0]);
                } catch (IllegalAccessException unused) {
                    throw new AssertionError();
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof RuntimeException) {
                        throw ((RuntimeException) e.getCause());
                    }
                    throw new RuntimeException(e.getCause());
                }
            }
            return read;
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            this.delegate.write(jsonWriter, t);
        }
    }
}
