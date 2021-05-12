package com.urbanairship;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.exoplayer2.C;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Fonts {
    private static final String[] JELLY_BEAN_SYSTEM_FONT_FAMILIES = {C.SERIF_NAME, C.SANS_SERIF_NAME, "sans-serif-light", "sans-serif-condensed", "sans-serif-thin", "sans-serif-medium"};
    private static final String[] LOLLIPOP_SYSTEM_FONT_FAMILIES = {"sans-serif-medium", "sans-serif-black", "cursive", "casual"};
    private static final String[] MARSHMALLOW_SYSTEM_FONT_FAMILIES = {"sans-serif-smallcaps", "serif-monospace", "monospace"};
    private static Fonts instance;
    private final Context context;
    private final Map<String, Typeface> fontCache = new HashMap();
    private final Set<String> systemFonts;

    private Fonts(Context context2) {
        HashSet hashSet = new HashSet();
        this.systemFonts = hashSet;
        this.context = context2.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 16) {
            Collections.addAll(hashSet, JELLY_BEAN_SYSTEM_FONT_FAMILIES);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Collections.addAll(hashSet, LOLLIPOP_SYSTEM_FONT_FAMILIES);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Collections.addAll(hashSet, MARSHMALLOW_SYSTEM_FONT_FAMILIES);
        }
    }

    public static Fonts shared(Context context2) {
        synchronized (Fonts.class) {
            if (instance == null) {
                instance = new Fonts(context2);
            }
        }
        return instance;
    }

    public synchronized void addFontFamily(String str, Typeface typeface) {
        this.fontCache.put(str, typeface);
    }

    public synchronized Typeface getFontFamily(String str) {
        if (this.fontCache.containsKey(str)) {
            return this.fontCache.get(str);
        }
        int identifier = this.context.getResources().getIdentifier(str, "font", this.context.getPackageName());
        if (identifier != 0) {
            try {
                Typeface font = ResourcesCompat.getFont(this.context, identifier);
                if (font != null) {
                    this.fontCache.put(str, font);
                    return font;
                }
            } catch (Resources.NotFoundException e) {
                Logger.error(e, "Unable to load font from resources: %s", str);
            }
        }
        if (!this.systemFonts.contains(str)) {
            return null;
        }
        Typeface create = Typeface.create(str, 0);
        this.fontCache.put(str, create);
        return create;
    }
}
