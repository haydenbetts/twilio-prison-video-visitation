package com.github.kevinsawicki.http;

import com.github.kevinsawicki.http.HttpRequest;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;

public class OkConnectionFactory implements HttpRequest.ConnectionFactory {
    protected OkHttpClient okHttpClient = new OkHttpClient();

    public HttpURLConnection create(URL url) {
        return new OkUrlFactory(this.okHttpClient).open(url);
    }

    public HttpURLConnection create(URL url, Proxy proxy) {
        return new OkUrlFactory(this.okHttpClient.newBuilder().proxy(proxy).build()).open(url);
    }
}
