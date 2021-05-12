package com.synconset.cordovahttp;

import androidx.core.app.NotificationCompat;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.file.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

class CordovaHttpDownload extends CordovaHttp implements Runnable {
    private String filePath;

    public CordovaHttpDownload(String str, Object obj, JSONObject jSONObject, String str2, int i, CallbackContext callbackContext) {
        super(str, obj, jSONObject, i, callbackContext);
        this.filePath = str2;
    }

    public void run() {
        try {
            HttpRequest httpRequest = HttpRequest.get((CharSequence) getUrlString(), (Map<?, ?>) getParamsMap(), false);
            prepareRequest(httpRequest);
            JSONObject jSONObject = new JSONObject();
            int code = httpRequest.code();
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, code);
            jSONObject.put(ImagesContract.URL, httpRequest.url().toString());
            addResponseHeaders(httpRequest, jSONObject);
            if (code < 200 || code >= 300) {
                jSONObject.put("error", "There was an error downloading the file");
                getCallbackContext().error(jSONObject);
                return;
            }
            File file = new File(new URI(this.filePath));
            httpRequest.receive(file);
            jSONObject.put("file", FileUtils.getFilePlugin().getEntryForFile(file));
            getCallbackContext().success(jSONObject);
        } catch (URISyntaxException unused) {
            respondWithError("There was an error with the given filePath");
        } catch (JSONException unused2) {
            respondWithError("There was an error generating the response");
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
