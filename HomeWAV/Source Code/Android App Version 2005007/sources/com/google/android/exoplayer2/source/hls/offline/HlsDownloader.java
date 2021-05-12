package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class HlsDownloader extends SegmentDownloader<HlsMasterPlaylist, String> {
    public HlsDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        super(uri, downloaderConstructorHelper);
    }

    public String[] getAllRepresentationKeys() throws IOException {
        ArrayList arrayList = new ArrayList();
        HlsMasterPlaylist hlsMasterPlaylist = (HlsMasterPlaylist) getManifest();
        extractUrls(hlsMasterPlaylist.variants, arrayList);
        extractUrls(hlsMasterPlaylist.audios, arrayList);
        extractUrls(hlsMasterPlaylist.subtitles, arrayList);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    public HlsMasterPlaylist getManifest(DataSource dataSource, Uri uri) throws IOException {
        HlsPlaylist loadManifest = loadManifest(dataSource, uri);
        if (loadManifest instanceof HlsMasterPlaylist) {
            return (HlsMasterPlaylist) loadManifest;
        }
        return HlsMasterPlaylist.createSingleVariantMasterPlaylist(loadManifest.baseUri);
    }

    /* access modifiers changed from: protected */
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, HlsMasterPlaylist hlsMasterPlaylist, String[] strArr, boolean z) throws InterruptedException, IOException {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (String resolveToUri : strArr) {
            HlsMediaPlaylist hlsMediaPlaylist = null;
            Uri resolveToUri2 = UriUtil.resolveToUri(hlsMasterPlaylist.baseUri, resolveToUri);
            try {
                hlsMediaPlaylist = (HlsMediaPlaylist) loadManifest(dataSource, resolveToUri2);
            } catch (IOException e) {
                if (!z) {
                    throw e;
                }
            }
            arrayList.add(new SegmentDownloader.Segment(hlsMediaPlaylist != null ? hlsMediaPlaylist.startTimeUs : Long.MIN_VALUE, new DataSpec(resolveToUri2)));
            if (hlsMediaPlaylist != null) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.initializationSegment;
                if (segment != null) {
                    addSegment(arrayList, hlsMediaPlaylist, segment, hashSet);
                }
                List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist.segments;
                for (int i = 0; i < list.size(); i++) {
                    addSegment(arrayList, hlsMediaPlaylist, list.get(i), hashSet);
                }
            }
        }
        return arrayList;
    }

    private static HlsPlaylist loadManifest(DataSource dataSource, Uri uri) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, 4, new HlsPlaylistParser());
        parsingLoadable.load();
        return (HlsPlaylist) parsingLoadable.getResult();
    }

    private static void addSegment(ArrayList<SegmentDownloader.Segment> arrayList, HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.Segment segment, HashSet<Uri> hashSet) {
        long j = hlsMediaPlaylist.startTimeUs + segment.relativeStartTimeUs;
        if (segment.fullSegmentEncryptionKeyUri != null) {
            Uri resolveToUri = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.fullSegmentEncryptionKeyUri);
            if (hashSet.add(resolveToUri)) {
                arrayList.add(new SegmentDownloader.Segment(j, new DataSpec(resolveToUri)));
            }
        }
        arrayList.add(new SegmentDownloader.Segment(j, new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, (String) null)));
    }

    private static void extractUrls(List<HlsMasterPlaylist.HlsUrl> list, ArrayList<String> arrayList) {
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(list.get(i).url);
        }
    }
}
