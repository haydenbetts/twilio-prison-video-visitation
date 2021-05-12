package com.pusher.client.util;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.Authorizer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class HttpAuthorizer implements Authorizer {
    private final URL endPoint;
    private ConnectionFactory mConnectionFactory = null;
    private Map<String, String> mHeaders = new HashMap();

    public HttpAuthorizer(String str) {
        try {
            this.endPoint = new URL(str);
            this.mConnectionFactory = new UrlEncodedConnectionFactory();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not parse authentication end point into a valid URL", e);
        }
    }

    public HttpAuthorizer(String str, ConnectionFactory connectionFactory) {
        try {
            this.endPoint = new URL(str);
            this.mConnectionFactory = connectionFactory;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not parse authentication end point into a valid URL", e);
        }
    }

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
    }

    public Boolean isSSL() {
        return Boolean.valueOf(this.endPoint.getProtocol().equals("https"));
    }

    public String authorize(String str, String str2) throws AuthorizationFailureException {
        HttpURLConnection httpURLConnection;
        try {
            this.mConnectionFactory.setChannelName(str);
            this.mConnectionFactory.setSocketId(str2);
            String body = this.mConnectionFactory.getBody();
            HashMap hashMap = new HashMap();
            hashMap.put(DefaultHttpClient.CONTENT_TYPE_KEY, this.mConnectionFactory.getContentType());
            hashMap.put("charset", this.mConnectionFactory.getCharset());
            if (isSSL().booleanValue()) {
                httpURLConnection = (HttpsURLConnection) this.endPoint.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) this.endPoint.openConnection();
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_POST);
            hashMap.putAll(this.mHeaders);
            hashMap.put("Content-Length", "" + Integer.toString(body.getBytes().length));
            for (String str3 : hashMap.keySet()) {
                httpURLConnection.setRequestProperty(str3, (String) hashMap.get(str3));
            }
            httpURLConnection.setUseCaches(false);
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(body);
            dataOutputStream.flush();
            dataOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            bufferedReader.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                if (responseCode != 201) {
                    throw new AuthorizationFailureException(stringBuffer.toString());
                }
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new AuthorizationFailureException((Exception) e);
        }
    }
}
