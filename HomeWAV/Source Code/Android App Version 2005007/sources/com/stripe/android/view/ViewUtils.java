package com.stripe.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.stripe.android.model.Card;

class ViewUtils {
    ViewUtils() {
    }

    static TypedValue getThemeAccentColor(Context context) {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            i = 16843829;
        } else {
            i = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }

    static TypedValue getThemeColorControlNormal(Context context) {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            i = 16843817;
        } else {
            i = context.getResources().getIdentifier("colorControlNormal", "attr", context.getPackageName());
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }

    static TypedValue getThemeTextColorSecondary(Context context) {
        int i = Build.VERSION.SDK_INT >= 21 ? 16842808 : 17170439;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }

    static TypedValue getThemeTextColorPrimary(Context context) {
        int i = Build.VERSION.SDK_INT >= 21 ? 16842806 : 17170435;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }

    static Drawable getTintedIcon(Context context, int i, int i2) {
        int color = ContextCompat.getColor(context, i2);
        Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, i));
        DrawableCompat.setTint(wrap.mutate(), color);
        return wrap;
    }

    static Drawable getTintedIconWithAttribute(Context context, Resources.Theme theme, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        int i3 = typedValue.data;
        Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, i2));
        DrawableCompat.setTint(wrap.mutate(), i3);
        return wrap;
    }

    static boolean isColorTransparent(int i) {
        return Color.alpha(i) < 16;
    }

    static boolean isColorDark(int i) {
        return (((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d)) / 255.0d <= 0.5d;
    }

    static boolean isCvcMaximalLength(String str, String str2) {
        if (str2 == null) {
            return false;
        }
        if (Card.AMERICAN_EXPRESS.equals(str)) {
            if (str2.trim().length() == 4) {
                return true;
            }
            return false;
        } else if (str2.trim().length() == 3) {
            return true;
        } else {
            return false;
        }
    }

    static String[] separateCardNumberGroups(String str, String str2) {
        String[] strArr;
        int i;
        int i2 = 0;
        if (str.length() > 16) {
            str = str.substring(0, 16);
        }
        if (str2.equals(Card.AMERICAN_EXPRESS)) {
            strArr = new String[3];
            int length = str.length();
            if (length > 4) {
                strArr[0] = str.substring(0, 4);
                i = 4;
            } else {
                i = 0;
            }
            if (length > 10) {
                strArr[1] = str.substring(4, 10);
                i = 10;
            }
            while (true) {
                if (i2 < 3) {
                    if (strArr[i2] == null) {
                        strArr[i2] = str.substring(i);
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        } else {
            strArr = new String[4];
            int i3 = 0;
            while (true) {
                int i4 = i2 + 1;
                int i5 = i4 * 4;
                if (i5 >= str.length()) {
                    break;
                }
                strArr[i2] = str.substring(i3, i5);
                i2 = i4;
                i3 = i5;
            }
            strArr[i2] = str.substring(i3);
        }
        return strArr;
    }

    static int getPxFromDp(Context context, int i) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().density);
    }
}
