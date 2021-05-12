package com.urbanairship.javascript;

import android.content.Context;
import com.urbanairship.Logger;
import com.urbanairship.R;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JavaScriptEnvironment {
    private final List<String> getters;

    private JavaScriptEnvironment(Builder builder) {
        this.getters = new ArrayList(builder.getters);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* access modifiers changed from: package-private */
    public String getJavaScript(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("var _UAirship = {};");
        for (String append : this.getters) {
            sb.append(append);
        }
        try {
            sb.append(readNativeBridge(context));
            return sb.toString();
        } catch (IOException unused) {
            Logger.error("Failed to read native bridge.", new Object[0]);
            return "";
        }
    }

    private static String readNativeBridge(Context context) throws IOException {
        InputStream openRawResource = context.getResources().openRawResource(R.raw.ua_native_bridge);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openRawResource.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            return byteArrayOutputStream.toString();
        } finally {
            try {
                openRawResource.close();
                byteArrayOutputStream.close();
            } catch (Exception e) {
                Logger.debug(e, "Failed to close streams", new Object[0]);
            }
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public List<String> getters;

        private Builder() {
            this.getters = new ArrayList();
        }

        public Builder addGetter(String str, String str2) {
            return addGetter(str, (JsonSerializable) JsonValue.wrapOpt(str2));
        }

        public Builder addGetter(String str, long j) {
            return addGetter(str, (JsonSerializable) JsonValue.wrapOpt(Long.valueOf(j)));
        }

        public Builder addGetter(String str, JsonSerializable jsonSerializable) {
            this.getters.add(String.format(Locale.ROOT, "_UAirship.%s = function(){return %s;};", new Object[]{str, (jsonSerializable == null ? JsonValue.NULL : jsonSerializable.toJsonValue()).toString()}));
            return this;
        }

        public JavaScriptEnvironment build() {
            return new JavaScriptEnvironment(this);
        }
    }
}
