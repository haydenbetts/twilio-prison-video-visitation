package com.synconset.cordovahttp;

import android.webkit.MimeTypeMap;
import com.github.kevinsawicki.http.HttpRequest;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class CordovaHttpUpload extends CordovaHttp implements Runnable {
    private String filePath;
    private String name;

    public CordovaHttpUpload(String str, Object obj, JSONObject jSONObject, String str2, String str3, int i, CallbackContext callbackContext) {
        super(str, obj, jSONObject, i, callbackContext);
        this.filePath = str2;
        this.name = str3;
    }

    public void run() {
        try {
            HttpRequest post = HttpRequest.post((CharSequence) getUrlString());
            prepareRequest(post);
            URI uri = new URI(this.filePath);
            String substring = this.filePath.substring(this.filePath.lastIndexOf(47) + 1);
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(this.filePath.substring(this.filePath.lastIndexOf(46) + 1));
            for (Map.Entry next : getParamsMap().entrySet()) {
                String str = (String) next.getKey();
                Object value = next.getValue();
                if (value instanceof Number) {
                    post.part(str, (Number) value);
                } else if (value instanceof String) {
                    post.part(str, (String) value);
                } else {
                    respondWithError("All parameters must be Numbers or Strings");
                    return;
                }
            }
            post.part(this.name, substring, mimeTypeFromExtension, new File(uri));
            returnResponseObject(post);
        } catch (URISyntaxException unused) {
            respondWithError("There was an error loading the file");
        } catch (JSONException unused2) {
            respondWithError("There was an error generating the response");
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
