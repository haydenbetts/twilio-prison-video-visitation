package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.ByteOutputStream;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.NullableFloat;
import fm.liveswitch.NullableLong;
import fm.liveswitch.StringExtensions;

public class Audio extends Element {
    private static byte[] _bitDepthId = {98, 100};
    private static byte[] _channelsId = {-97};
    private static byte[] _outputSamplingFrequencyId = {120, -75};
    private static byte[] _samplingFrequencyId = {-75};
    private NullableLong _bitDepth;
    private long _channels;
    private NullableFloat _outputSamplingFrequency;
    private float _samplingFrequency;

    public static long getDefaultChannels() {
        return 1;
    }

    public static float getDefaultSamplingFrequency() {
        return 8000.0f;
    }

    public static byte[] getEbmlId() {
        return new byte[]{-31};
    }

    public Audio() {
        this._bitDepth = new NullableLong();
        this._outputSamplingFrequency = new NullableFloat();
        setSamplingFrequency(getDefaultSamplingFrequency());
        setChannels(getDefaultChannels());
    }

    public Audio(byte[] bArr) {
        this();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            byte[] readId = Element.readId(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            IntegerHolder integerHolder2 = new IntegerHolder(value);
            byte[] readValue = Element.readValue(bArr, value, integerHolder2);
            int value2 = integerHolder2.getValue();
            if (Element.compare(readId, _samplingFrequencyId)) {
                setSamplingFrequency(Element.readFloat(readValue));
            } else if (Element.compare(readId, _outputSamplingFrequencyId)) {
                setOutputSamplingFrequency(new NullableFloat(Element.readFloat(readValue)));
            } else if (Element.compare(readId, _channelsId)) {
                setChannels(Element.readUnsignedInteger(readValue));
            } else if (Element.compare(readId, _bitDepthId)) {
                setBitDepth(new NullableLong(Element.readUnsignedInteger(readValue)));
            } else if (!Element.compare(readId, EbmlCrc32.getEbmlId()) && !Element.compare(readId, EbmlVoid.getEbmlId())) {
                Log.warn(StringExtensions.concat("Unrecognized ID in MatroskaAudio: ", BitAssistant.getHexString(readId)));
            }
            i = value2;
        }
    }

    public NullableLong getBitDepth() {
        return this._bitDepth;
    }

    public long getChannels() {
        return this._channels;
    }

    public byte[] getId() {
        return getEbmlId();
    }

    /* access modifiers changed from: protected */
    public byte[] getInnerBytes() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        if (super.getWriteDefaultValues() || getSamplingFrequency() != getDefaultSamplingFrequency()) {
            super.writeFloat(getSamplingFrequency(), _samplingFrequencyId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getOutputSamplingFrequency().getHasValue()) {
            if (super.getWriteDefaultValues() && !getOutputSamplingFrequency().getHasValue()) {
                setOutputSamplingFrequency(new NullableFloat(getSamplingFrequency()));
            }
            super.writeFloat(getOutputSamplingFrequency().getValue(), _outputSamplingFrequencyId, byteOutputStream);
        }
        if (super.getWriteDefaultValues() || getChannels() != getDefaultChannels()) {
            super.writeUnsignedInteger(getChannels(), _channelsId, byteOutputStream);
        }
        if (getBitDepth().getHasValue()) {
            super.writeUnsignedInteger(getBitDepth().getValue(), _bitDepthId, byteOutputStream);
        }
        return byteOutputStream.toArray();
    }

    public NullableFloat getOutputSamplingFrequency() {
        return this._outputSamplingFrequency;
    }

    public float getSamplingFrequency() {
        return this._samplingFrequency;
    }

    public void setBitDepth(NullableLong nullableLong) {
        this._bitDepth = nullableLong;
    }

    public void setChannels(long j) {
        this._channels = j;
    }

    public void setOutputSamplingFrequency(NullableFloat nullableFloat) {
        this._outputSamplingFrequency = nullableFloat;
    }

    public void setSamplingFrequency(float f) {
        this._samplingFrequency = f;
    }
}
