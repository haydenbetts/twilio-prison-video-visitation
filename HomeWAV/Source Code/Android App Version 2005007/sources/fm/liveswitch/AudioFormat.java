package fm.liveswitch;

import com.twilio.video.G722Codec;
import com.twilio.video.OpusCodec;
import com.twilio.video.PcmaCodec;
import com.twilio.video.PcmuCodec;
import java.util.HashMap;

public class AudioFormat extends MediaFormat<AudioFormat> {
    private int _channelCount;
    private boolean _littleEndian;

    public static String getDtmfName() {
        return "telephone-event";
    }

    public static String getG722Name() {
        return G722Codec.NAME;
    }

    public static String getOpusName() {
        return OpusCodec.NAME;
    }

    public static String getPcmName() {
        return "PCM";
    }

    public static String getPcmaName() {
        return PcmaCodec.NAME;
    }

    public static String getPcmuName() {
        return PcmuCodec.NAME;
    }

    protected AudioFormat() {
    }

    public AudioFormat(String str, int i, int i2) {
        super(str, i);
        setChannelCount(i2);
    }

    public AudioFormat(String str, AudioConfig audioConfig) {
        super(str, audioConfig.getClockRate());
        setChannelCount(audioConfig.getChannelCount());
    }

    public AudioFormat clone() {
        AudioFormat audioFormat = (AudioFormat) super.clone();
        audioFormat.setChannelCount(getChannelCount());
        audioFormat.setLittleEndian(getLittleEndian());
        return audioFormat;
    }

    /* access modifiers changed from: protected */
    public AudioFormat createInstance() {
        return new AudioFormat();
    }

    public static AudioFormat fromFormatInfo(FormatInfo formatInfo) {
        if (formatInfo.getChannelCount() != -1) {
            return new AudioFormat(formatInfo.getName(), new AudioConfig(formatInfo.getClockRate(), formatInfo.getChannelCount()));
        }
        throw new RuntimeException(new Exception("Not an audio format info."));
    }

    public static AudioFormat fromJson(String str) {
        return (AudioFormat) JsonSerializer.deserializeObject(str, new IFunction0<AudioFormat>() {
            public AudioFormat invoke() {
                return new AudioFormat();
            }
        }, new IAction3<AudioFormat, String, String>() {
            public void invoke(AudioFormat audioFormat, String str, String str2) {
                if (str.equals("channelCount")) {
                    audioFormat.setChannelCount(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("littleEndian")) {
                    audioFormat.setLittleEndian(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("name")) {
                    audioFormat.setName(JsonSerializer.deserializeString(str2));
                } else if (str.equals("clockRate")) {
                    audioFormat.setClockRate(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("isPacketized")) {
                    audioFormat.setIsPacketized(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("isEncrypted")) {
                    audioFormat.setIsEncrypted(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("registeredPayloadType")) {
                    audioFormat.setRegisteredPayloadType(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (str.equals("staticPayloadType")) {
                    audioFormat.setStaticPayloadType(JsonSerializer.deserializeInteger(str2).getValue());
                }
            }
        });
    }

    public int getChannelCount() {
        return this._channelCount;
    }

    public AudioConfig getConfig() {
        return new AudioConfig(super.getClockRate(), getChannelCount());
    }

    public FormatInfo getInfo() {
        return new FormatInfo(this);
    }

    public boolean getIsCompressed() {
        return !getIsPcm();
    }

    public boolean getIsDtmf() {
        return StringExtensions.isEqual(super.getName(), getDtmfName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsG722() {
        return StringExtensions.isEqual(super.getName(), getG722Name(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsOpus() {
        return StringExtensions.isEqual(super.getName(), getOpusName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsPcm() {
        return StringExtensions.isEqual(super.getName(), getPcmName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsPcma() {
        return StringExtensions.isEqual(super.getName(), getPcmaName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getIsPcmu() {
        return StringExtensions.isEqual(super.getName(), getPcmuName(), StringComparison.OrdinalIgnoreCase);
    }

    public boolean getLittleEndian() {
        return this._littleEndian;
    }

    public String getParameters() {
        int channelCount = getChannelCount();
        if (channelCount == 1) {
            return null;
        }
        return IntegerExtensions.toString(Integer.valueOf(channelCount));
    }

    public boolean isCompatible(AudioFormat audioFormat) {
        if (super.isCompatible(audioFormat) && getChannelCount() == audioFormat.getChannelCount()) {
            return true;
        }
        return false;
    }

    public boolean isEquivalent(AudioFormat audioFormat, boolean z) {
        return super.isEquivalent(audioFormat, z) && getChannelCount() == audioFormat.getChannelCount();
    }

    public void setChannelCount(int i) {
        this._channelCount = i;
    }

    public void setLittleEndian(boolean z) {
        this._littleEndian = z;
    }

    public static String toJson(AudioFormat audioFormat) {
        return JsonSerializer.serializeObject(audioFormat, new IAction2<AudioFormat, HashMap<String, String>>(audioFormat) {
            final /* synthetic */ AudioFormat val$audioFormat;

            {
                this.val$audioFormat = r1;
            }

            public void invoke(AudioFormat audioFormat, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelCount", JsonSerializer.serializeInteger(new NullableInteger(this.val$audioFormat.getChannelCount())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "littleEndian", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$audioFormat.getLittleEndian())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "name", JsonSerializer.serializeString(this.val$audioFormat.getName()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clockRate", JsonSerializer.serializeInteger(new NullableInteger(this.val$audioFormat.getClockRate())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isPacketized", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$audioFormat.getIsPacketized())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isEncrypted", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$audioFormat.getIsEncrypted())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "registeredPayloadType", JsonSerializer.serializeInteger(new NullableInteger(this.val$audioFormat.getRegisteredPayloadType())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "staticPayloadType", JsonSerializer.serializeInteger(new NullableInteger(this.val$audioFormat.getStaticPayloadType())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
