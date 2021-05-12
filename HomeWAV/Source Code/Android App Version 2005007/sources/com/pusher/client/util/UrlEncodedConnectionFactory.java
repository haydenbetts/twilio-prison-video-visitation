package com.pusher.client.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlEncodedConnectionFactory extends ConnectionFactory {
    private Map<String, String> mQueryStringParameters = new HashMap();

    public String getCharset() {
        return "UTF-8";
    }

    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }

    public UrlEncodedConnectionFactory() {
    }

    public UrlEncodedConnectionFactory(Map<String, String> map) {
        this.mQueryStringParameters = map;
    }

    public String getBody() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("channel_name=");
            stringBuffer.append(URLEncoder.encode(getChannelName(), getCharset()));
            stringBuffer.append("&socket_id=");
            stringBuffer.append(URLEncoder.encode(getSocketId(), getCharset()));
            for (String next : this.mQueryStringParameters.keySet()) {
                stringBuffer.append("&");
                stringBuffer.append(next);
                stringBuffer.append("=");
                stringBuffer.append(URLEncoder.encode(this.mQueryStringParameters.get(next), getCharset()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
