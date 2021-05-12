package com.urbanairship.config;

import android.net.Uri;
import com.urbanairship.Logger;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilder {
    private Uri.Builder uriBuilder;

    public UrlBuilder(String str) {
        if (str != null) {
            this.uriBuilder = Uri.parse(str).buildUpon();
        }
    }

    public UrlBuilder appendEncodedPath(String str) {
        Uri.Builder builder = this.uriBuilder;
        if (builder != null) {
            builder.appendEncodedPath(str);
        }
        return this;
    }

    public UrlBuilder appendPath(String str) {
        Uri.Builder builder = this.uriBuilder;
        if (builder != null) {
            builder.appendPath(str);
        }
        return this;
    }

    public UrlBuilder appendQueryParameter(String str, String str2) {
        Uri.Builder builder = this.uriBuilder;
        if (builder != null) {
            builder.appendQueryParameter(str, str2);
        }
        return this;
    }

    public URL build() {
        Uri.Builder builder = this.uriBuilder;
        if (builder == null) {
            return null;
        }
        try {
            return new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            Logger.error(e, "Failed to build URL", new Object[0]);
            return null;
        }
    }
}
