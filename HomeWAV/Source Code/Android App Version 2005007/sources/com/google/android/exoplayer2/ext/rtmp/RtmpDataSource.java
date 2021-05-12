package com.google.android.exoplayer2.ext.rtmp;

import android.net.Uri;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import net.butterflytv.rtmp_client.RtmpClient;

public final class RtmpDataSource implements DataSource {
    private final TransferListener<? super RtmpDataSource> listener;
    private RtmpClient rtmpClient;
    private Uri uri;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.rtmp");
    }

    public RtmpDataSource() {
        this((TransferListener<? super RtmpDataSource>) null);
    }

    public RtmpDataSource(TransferListener<? super RtmpDataSource> transferListener) {
        this.listener = transferListener;
    }

    public long open(DataSpec dataSpec) throws RtmpClient.RtmpIOException {
        RtmpClient rtmpClient2 = new RtmpClient();
        this.rtmpClient = rtmpClient2;
        rtmpClient2.open(dataSpec.uri.toString(), false);
        this.uri = dataSpec.uri;
        TransferListener<? super RtmpDataSource> transferListener = this.listener;
        if (transferListener == null) {
            return -1;
        }
        transferListener.onTransferStart(this, dataSpec);
        return -1;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.rtmpClient.read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        TransferListener<? super RtmpDataSource> transferListener = this.listener;
        if (transferListener != null) {
            transferListener.onBytesTransferred(this, read);
        }
        return read;
    }

    public void close() {
        if (this.uri != null) {
            this.uri = null;
            TransferListener<? super RtmpDataSource> transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferEnd(this);
            }
        }
        RtmpClient rtmpClient2 = this.rtmpClient;
        if (rtmpClient2 != null) {
            rtmpClient2.close();
            this.rtmpClient = null;
        }
    }

    public Uri getUri() {
        return this.uri;
    }
}
