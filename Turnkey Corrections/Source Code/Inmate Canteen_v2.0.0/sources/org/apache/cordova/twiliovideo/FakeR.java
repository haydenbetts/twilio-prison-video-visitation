package org.apache.cordova.twiliovideo;

import android.content.Context;

public class FakeR {
    private Context context;

    public FakeR(Context context2) {
        this.context = context2;
    }

    public int getId(String str) {
        return getResourceId(this.context, "id", str);
    }

    public int getString(String str) {
        return getResourceId(this.context, "string", str);
    }

    public int getDrawable(String str) {
        return getResourceId(this.context, "drawable", str);
    }

    public int getLayout(String str) {
        return getResourceId(this.context, "layout", str);
    }

    public static int getResourceId(Context context2, String str, String str2) {
        return context2.getResources().getIdentifier(str2, str, context2.getPackageName());
    }
}
