package fm.liveswitch;

import com.microsoft.appcenter.http.DefaultHttpClient;
import fm.liveswitch.bzip2.Decompressor;

public class HttpFileTransfer {
    private static Future<DataBuffer> downloadFile(final Promise<DataBuffer> promise, final String str) {
        HttpRequestArgs httpRequestArgs = new HttpRequestArgs();
        httpRequestArgs.setMethod(HttpMethod.Get);
        httpRequestArgs.setUrl(str);
        HttpTransferFactory.getHttpTransfer().sendAsync(httpRequestArgs, new IAction1<HttpResponseArgs>() {
            public void invoke(HttpResponseArgs httpResponseArgs) {
                if (httpResponseArgs.getException() != null) {
                    promise.reject(httpResponseArgs.getException());
                } else if (httpResponseArgs.getStatusCode() >= 400) {
                    promise.reject(new Exception(StringExtensions.format("Could not download file {0}. Server responded with status code {1}.", str, IntegerExtensions.toString(Integer.valueOf(httpResponseArgs.getStatusCode())))));
                } else if (Global.equals(HashMapExtensions.getItem(httpResponseArgs.getHeaders()).get(DefaultHttpClient.CONTENT_TYPE_KEY), "application/x-bzip2") || str.endsWith(".bz2")) {
                    Decompressor decompressor = new Decompressor(DataBuffer.wrap(httpResponseArgs.getBinaryContent()));
                    DataBufferStream dataBufferStream = new DataBufferStream(ArrayExtensions.getLength(httpResponseArgs.getBinaryContent()) * 3);
                    byte[] bArr = new byte[2048];
                    int length = ArrayExtensions.getLength(bArr);
                    while (true) {
                        int read = decompressor.read(bArr, 0, length);
                        if (read > 0) {
                            dataBufferStream.writeBytes(bArr, 0, read);
                            length = ArrayExtensions.getLength(bArr);
                        } else {
                            promise.resolve(dataBufferStream.getBuffer());
                            return;
                        }
                    }
                } else {
                    promise.resolve(DataBuffer.wrap(httpResponseArgs.getBinaryContent()));
                }
            }
        });
        return promise;
    }

    public static Future<DataBuffer> downloadFile(String str) {
        return downloadFile((Promise<DataBuffer>) new Promise(), str);
    }

    public static Future<Object> downloadFile(String str, final String str2) {
        return downloadFile(str).then(new IFunction1<DataBuffer, Future<Object>>() {
            public Future<Object> invoke(DataBuffer dataBuffer) {
                FileStream fileStream = new FileStream(str2);
                fileStream.open(FileStreamAccess.Write);
                fileStream.write(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
                fileStream.close();
                return PromiseBase.resolveNow();
            }
        });
    }
}
