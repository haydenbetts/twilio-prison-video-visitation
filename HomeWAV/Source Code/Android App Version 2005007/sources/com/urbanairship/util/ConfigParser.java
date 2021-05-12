package com.urbanairship.util;

public interface ConfigParser {
    boolean getBoolean(String str, boolean z);

    int getColor(String str, int i);

    int getCount();

    int getDrawableResourceId(String str);

    int getInt(String str, int i);

    long getLong(String str, long j);

    String getName(int i) throws IndexOutOfBoundsException;

    int getRawResourceId(String str);

    String getString(String str);

    String getString(String str, String str2);

    String[] getStringArray(String str);
}
