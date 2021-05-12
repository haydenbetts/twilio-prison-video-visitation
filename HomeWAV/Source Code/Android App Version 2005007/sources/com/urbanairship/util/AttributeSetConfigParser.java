package com.urbanairship.util;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;

public class AttributeSetConfigParser implements ConfigParser {
    private final AttributeSet attributeSet;
    private final Context context;

    public AttributeSetConfigParser(Context context2, AttributeSet attributeSet2) {
        this.context = context2;
        this.attributeSet = attributeSet2;
    }

    public int getCount() {
        return this.attributeSet.getAttributeCount();
    }

    public String getName(int i) {
        if (i < getCount() && i >= 0) {
            return this.attributeSet.getAttributeName(i);
        }
        throw new IndexOutOfBoundsException("Index out of bounds: " + i + " count: " + getCount());
    }

    public String getString(String str) {
        int attributeResourceValue = this.attributeSet.getAttributeResourceValue((String) null, str, 0);
        if (attributeResourceValue != 0) {
            return this.context.getString(attributeResourceValue);
        }
        return this.attributeSet.getAttributeValue((String) null, str);
    }

    public String getString(String str, String str2) {
        String string = getString(str);
        return string == null ? str2 : string;
    }

    public boolean getBoolean(String str, boolean z) {
        int attributeResourceValue = this.attributeSet.getAttributeResourceValue((String) null, str, 0);
        if (attributeResourceValue != 0) {
            return this.context.getResources().getBoolean(attributeResourceValue);
        }
        return this.attributeSet.getAttributeBooleanValue((String) null, str, z);
    }

    public String[] getStringArray(String str) {
        int attributeResourceValue = this.attributeSet.getAttributeResourceValue((String) null, str, 0);
        if (attributeResourceValue != 0) {
            return this.context.getResources().getStringArray(attributeResourceValue);
        }
        String attributeValue = this.attributeSet.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return new String[0];
        }
        return attributeValue.split("[, ]+");
    }

    public int getDrawableResourceId(String str) {
        int attributeResourceValue = this.attributeSet.getAttributeResourceValue((String) null, str, 0);
        if (attributeResourceValue != 0) {
            return attributeResourceValue;
        }
        String attributeValue = this.attributeSet.getAttributeValue((String) null, str);
        if (attributeValue != null) {
            return this.context.getResources().getIdentifier(attributeValue, "drawable", this.context.getPackageName());
        }
        return 0;
    }

    public int getColor(String str, int i) {
        int attributeResourceValue = this.attributeSet.getAttributeResourceValue((String) null, str, 0);
        if (attributeResourceValue != 0) {
            return ContextCompat.getColor(this.context, attributeResourceValue);
        }
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return i;
        }
        return Color.parseColor(string);
    }

    public int getInt(String str, int i) {
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return i;
        }
        return Integer.parseInt(string);
    }

    public int getRawResourceId(String str) {
        int attributeResourceValue = this.attributeSet.getAttributeResourceValue((String) null, str, 0);
        if (attributeResourceValue != 0) {
            return attributeResourceValue;
        }
        String attributeValue = this.attributeSet.getAttributeValue((String) null, str);
        if (attributeValue != null) {
            return this.context.getResources().getIdentifier(attributeValue, "raw", this.context.getPackageName());
        }
        return 0;
    }

    public long getLong(String str, long j) {
        String string = getString(str);
        if (UAStringUtil.isEmpty(string)) {
            return j;
        }
        return Long.parseLong(string);
    }
}
