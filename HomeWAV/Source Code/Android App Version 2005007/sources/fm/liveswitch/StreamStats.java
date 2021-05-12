package fm.liveswitch;

import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.HashMap;

public abstract class StreamStats extends BaseStats {
    private TransportStats _transport;
    private StreamType _type;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "type")) {
            setType(typeFromString(JsonSerializer.deserializeString(str2)));
        } else if (Global.equals(str, NotificationCompat.CATEGORY_TRANSPORT)) {
            setTransport(TransportStats.fromJson(str2));
        }
    }

    public boolean getIsHost() {
        TransportStats transport = getTransport();
        if (transport == null) {
            return false;
        }
        return transport.getIsHost();
    }

    public boolean getIsReflexive() {
        TransportStats transport = getTransport();
        if (transport == null) {
            return false;
        }
        return transport.getIsReflexive();
    }

    public boolean getIsRelayed() {
        TransportStats transport = getTransport();
        if (transport == null) {
            return false;
        }
        return transport.getIsRelayed();
    }

    public TransportStats getTransport() {
        return this._transport;
    }

    public StreamType getType() {
        return this._type;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(typeToString(getType())));
        if (getTransport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), NotificationCompat.CATEGORY_TRANSPORT, TransportStats.toJson(getTransport()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setTransport(TransportStats transportStats) {
        this._transport = transportStats;
    }

    /* access modifiers changed from: package-private */
    public void setType(StreamType streamType) {
        this._type = streamType;
    }

    protected StreamStats() {
    }

    private StreamType typeFromString(String str) {
        if (str.equals("audio")) {
            return StreamType.Audio;
        }
        if (str.equals("video")) {
            return StreamType.Video;
        }
        if (str.equals(MimeTypes.BASE_TYPE_APPLICATION)) {
            return StreamType.Application;
        }
        if (str.equals("message")) {
            return StreamType.Message;
        }
        if (str.equals("text")) {
            return StreamType.Text;
        }
        return StreamType.Text;
    }

    private String typeToString(StreamType streamType) {
        if (streamType == StreamType.Audio) {
            return "audio";
        }
        if (streamType == StreamType.Video) {
            return "video";
        }
        if (streamType == StreamType.Application) {
            return MimeTypes.BASE_TYPE_APPLICATION;
        }
        if (streamType == StreamType.Message) {
            return "message";
        }
        if (streamType == StreamType.Text) {
            return "text";
        }
        return null;
    }
}
