package okhttp3.internal.huc;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.IOException;
import okhttp3.Request;
import okio.Buffer;
import okio.BufferedSink;

final class BufferedRequestBody extends OutputStreamRequestBody {
    final Buffer buffer = new Buffer();
    long contentLength = -1;

    BufferedRequestBody(long j) {
        initOutputStream(this.buffer, j);
    }

    public long contentLength() throws IOException {
        return this.contentLength;
    }

    public Request prepareToSendRequest(Request request) throws IOException {
        if (request.header(HttpRequest.HEADER_CONTENT_LENGTH) != null) {
            return request;
        }
        outputStream().close();
        this.contentLength = this.buffer.size();
        return request.newBuilder().removeHeader("Transfer-Encoding").header(HttpRequest.HEADER_CONTENT_LENGTH, Long.toString(this.buffer.size())).build();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        this.buffer.copyTo(bufferedSink.buffer(), 0, this.buffer.size());
    }
}
