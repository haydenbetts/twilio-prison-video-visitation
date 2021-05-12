package com.urbanairship.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import com.urbanairship.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesConfigParser implements ConfigParser {
    private final Context context;
    private final List<String> propertyNames;
    private final Map<String, String> propertyValues;

    private PropertiesConfigParser(Context context2, List<String> list, Map<String, String> map) {
        this.context = context2;
        this.propertyNames = list;
        this.propertyValues = map;
    }

    public static PropertiesConfigParser fromAssets(Context context2, String str) throws IOException {
        AssetManager assets = context2.getResources().getAssets();
        String[] list = assets.list("");
        if (list == null || !Arrays.asList(list).contains(str)) {
            throw new FileNotFoundException("Unable to find properties file: " + str);
        }
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = assets.open(str);
            properties.load(inputStream);
            return fromProperties(context2, properties);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Logger.debug(e, "PropertiesConfigParser - Failed to close input stream.", new Object[0]);
                }
            }
        }
    }

    public static PropertiesConfigParser fromProperties(Context context2, Properties properties) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (String next : properties.stringPropertyNames()) {
            String property = properties.getProperty(next);
            if (property != null) {
                property = property.trim();
            }
            if (!UAStringUtil.isEmpty(property)) {
                arrayList.add(next);
                hashMap.put(next, property);
            }
        }
        return new PropertiesConfigParser(context2, arrayList, hashMap);
    }

    public int getCount() {
        return this.propertyNames.size();
    }

    public String getName(int i) {
        return this.propertyNames.get(i);
    }

    public String getString(String str) {
        return this.propertyValues.get(str);
    }

    public String getString(String str, String str2) {
        String string = getString(str);
        return string == null ? str2 : string;
    }

    public boolean getBoolean(String str, boolean z) {
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return z;
        }
        return Boolean.parseBoolean(string);
    }

    public String[] getStringArray(String str) {
        String str2 = this.propertyValues.get(str);
        if (str2 == null) {
            return new String[0];
        }
        return str2.split("[, ]+");
    }

    public int getDrawableResourceId(String str) {
        return this.context.getResources().getIdentifier(getString(str), "drawable", this.context.getPackageName());
    }

    public int getRawResourceId(String str) {
        return this.context.getResources().getIdentifier(getString(str), "raw", this.context.getPackageName());
    }

    public long getLong(String str, long j) {
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return j;
        }
        return Long.parseLong(string);
    }

    public int getInt(String str, int i) {
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return i;
        }
        return Integer.parseInt(string);
    }

    public int getColor(String str, int i) {
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return i;
        }
        return Color.parseColor(string);
    }
}
