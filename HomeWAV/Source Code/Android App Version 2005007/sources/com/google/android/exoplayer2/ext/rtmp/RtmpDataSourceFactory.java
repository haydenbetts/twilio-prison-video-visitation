package com.google.android.exoplayer2.ext.rtmp;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.TransferListener;

public final class RtmpDataSourceFactory implements DataSource.Factory {
    private final TransferListener<? super RtmpDataSource> listener;

    public RtmpDataSourceFactory() {
        this((TransferListener<? super RtmpDataSource>) null);
    }

    public RtmpDataSourceFactory(TransferListener<? super RtmpDataSource> transferListener) {
        this.listener = transferListener;
    }

    public DataSource createDataSource() {
        return new RtmpDataSource(this.listener);
    }
}
