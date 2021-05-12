package com.braintreepayments.api.internal;

import java.lang.reflect.Field;

public class ClassHelper {
    public static boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static <FIELD_TYPE> FIELD_TYPE getFieldValue(String str, String str2) {
        try {
            Field field = Class.forName(str).getField(str2);
            field.setAccessible(true);
            return field.get(Object.class);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }
}
