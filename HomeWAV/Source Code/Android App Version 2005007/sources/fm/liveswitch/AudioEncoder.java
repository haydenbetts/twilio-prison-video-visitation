package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AudioEncoder extends AudioPipe {
    private int __bitrate = -1;
    private AtomicLong __framesEncoded = new AtomicLong();
    private int __maxBitrate = -1;
    private int __minBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction0> __onBitrateChange = new ArrayList();
    private int __targetBitrate = -1;
    private IAction0 _onBitrateChange = null;
    private boolean _staticOutputBitrate;

    public void addOnBitrateChange(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onBitrateChange == null) {
                this._onBitrateChange = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(AudioEncoder.this.__onBitrateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onBitrateChange.add(iAction0);
        }
    }

    public AudioEncoder(AudioFormat audioFormat, AudioFormat audioFormat2) {
        super(audioFormat, audioFormat2);
    }

    /* access modifiers changed from: protected */
    public void doProcessTrackStatsFromOutput(MediaTrackStats mediaTrackStats) {
        super.doProcessTrackStatsFromOutput(mediaTrackStats);
        mediaTrackStats.setMinBitrate(getMinBitrate());
        mediaTrackStats.setMaxBitrate(getMaxBitrate());
        mediaTrackStats.setBitrate(getBitrate());
        mediaTrackStats.setFramesEncoded(getFramesEncoded());
    }

    public int getBitrate() {
        return ConstraintUtility.clampMin(this.__bitrate, getMinCodecBitrate(), getMaxCodecBitrate());
    }

    /* access modifiers changed from: protected */
    public boolean getCanChangeBitrate() {
        return super.getOutputFormat() != null && !((AudioFormat) super.getOutputFormat()).getIsFixedBitrate();
    }

    public long getFramesEncoded() {
        return this.__framesEncoded.getValue();
    }

    public int getMaxBitrate() {
        return ConstraintUtility.min(this.__maxBitrate, getMaxCodecBitrate());
    }

    public int getMaxCodecBitrate() {
        AudioFormat audioFormat = (AudioFormat) super.getOutputFormat();
        if (audioFormat == null) {
            return -1;
        }
        return audioFormat.getMaxBitrate();
    }

    public int getMaxOutputBitrate() {
        int maxCodecBitrate = getStaticOutputBitrate() ? getMaxCodecBitrate() : getMaxBitrate();
        return maxCodecBitrate == -1 ? getMaxInputBitrate() : maxCodecBitrate;
    }

    public int getMinBitrate() {
        return ConstraintUtility.max(this.__minBitrate, getMinCodecBitrate());
    }

    public int getMinCodecBitrate() {
        AudioFormat audioFormat = (AudioFormat) super.getOutputFormat();
        if (audioFormat == null) {
            return 0;
        }
        return audioFormat.getMinBitrate();
    }

    public int getMinOutputBitrate() {
        int minCodecBitrate = getStaticOutputBitrate() ? getMinCodecBitrate() : getMinBitrate();
        return minCodecBitrate == -1 ? getMinInputBitrate() : minCodecBitrate;
    }

    public boolean getStaticOutputBitrate() {
        return this._staticOutputBitrate;
    }

    public int getTargetBitrate() {
        return ConstraintUtility.clampMin(this.__targetBitrate, getMinOutputBitrate(), getMaxOutputBitrate());
    }

    public int getTargetOutputBitrate() {
        return getTargetBitrate();
    }

    /* access modifiers changed from: protected */
    public void raiseFrame(AudioFrame audioFrame) {
        this.__framesEncoded.increment();
        super.raiseFrame(audioFrame);
    }

    public void removeOnBitrateChange(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onBitrateChange, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onBitrateChange.remove(iAction0);
        if (this.__onBitrateChange.size() == 0) {
            this._onBitrateChange = null;
        }
    }

    /* access modifiers changed from: protected */
    public void setBitrate(int i) {
        if (this.__bitrate != i) {
            this.__bitrate = i;
            IAction0 iAction0 = this._onBitrateChange;
            if (iAction0 != null) {
                iAction0.invoke();
            }
        }
    }

    public void setMaxBitrate(int i) {
        this.__maxBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMaxOutputBitrate(int i) {
        setMaxBitrate(i);
    }

    public void setMinBitrate(int i) {
        this.__minBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setMinOutputBitrate(int i) {
        setMinBitrate(i);
    }

    public void setStaticOutputBitrate(boolean z) {
        this._staticOutputBitrate = z;
    }

    public void setTargetBitrate(int i) {
        this.__targetBitrate = i;
    }

    /* access modifiers changed from: protected */
    public void setTargetOutputBitrate(int i) {
        setTargetBitrate(i);
    }
}
