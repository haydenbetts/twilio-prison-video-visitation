package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class FilteringHlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private final List<String> filter;
    private final HlsPlaylistParser hlsPlaylistParser = new HlsPlaylistParser();

    public FilteringHlsPlaylistParser(List<String> list) {
        this.filter = list;
    }

    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        HlsPlaylist parse = this.hlsPlaylistParser.parse(uri, inputStream);
        return parse instanceof HlsMasterPlaylist ? ((HlsMasterPlaylist) parse).copy(this.filter) : parse;
    }
}
