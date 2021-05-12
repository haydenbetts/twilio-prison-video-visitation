package com.stripe.android.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

public class ClassUtils {
    private ClassUtils() {
    }

    public static Object getInternalObject(Class cls, Set<String> set, Object obj) {
        Field findField = findField(cls, set);
        if (findField == null) {
            return null;
        }
        try {
            return findField.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field findField(Class cls, Collection<String> collection) {
        for (Field field : cls.getDeclaredFields()) {
            if (collection.contains(field.getName())) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    public static Method findMethod(Class cls, Collection<String> collection) {
        for (Method method : cls.getDeclaredMethods()) {
            if (collection.contains(method.getName())) {
                return method;
            }
        }
        return null;
    }
}
