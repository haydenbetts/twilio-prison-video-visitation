package fm.liveswitch.dtmf;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.AudioPipe;
import fm.liveswitch.Global;
import fm.liveswitch.IAction1;
import fm.liveswitch.IActionDelegate1;
import fm.liveswitch.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Receiver extends AudioPipe {
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onTone;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onToneChange;
    private long _lastDuration;
    private long _lastTimestamp;
    private IAction1<Tone> _onTone;
    private IAction1<Tone> _onToneChange;
    private Tone _tone;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public boolean getDisabled() {
        return false;
    }

    public String getLabel() {
        return "DTMF Receiver";
    }

    public void addOnTone(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onTone == null) {
                this._onTone = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(Receiver.this.__onTone).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tone);
                        }
                    }
                };
            }
            this.__onTone.add(iAction1);
        }
    }

    public void addOnToneChange(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onToneChange == null) {
                this._onToneChange = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(Receiver.this.__onToneChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(tone);
                        }
                    }
                };
            }
            this.__onToneChange.add(iAction1);
        }
    }

    private static Format createFormat(int i) {
        Format format = new Format(i);
        format.setIsPacketized(true);
        return format;
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        Packet readFrom = Packet.readFrom(audioBuffer.getDataBuffer());
        if (readFrom == null) {
            Log.error("Malformed telephone-event packet.");
        } else if (audioFrame.getTimestamp() != this._lastTimestamp || (audioFrame.getTimestamp() == this._lastTimestamp && ((long) readFrom.getDuration()) > this._lastDuration)) {
            this._lastTimestamp = audioFrame.getTimestamp();
            this._lastDuration = (long) readFrom.getDuration();
            processPacket(readFrom);
            if (readFrom.getEnd()) {
                Packet packet = new Packet();
                packet.setEventCode(-1);
                packet.setEnd(true);
                packet.setDuration(0);
                packet.setVolume(0);
                processPacket(packet);
            }
            raiseFrame(audioFrame);
        }
    }

    public Tone getTone() {
        return this._tone;
    }

    private void processPacket(Packet packet) {
        Tone tone = getTone();
        setTone(Tone.fromPacket(packet, super.getConfig().getClockRate()));
        if (!Global.equals(getTone().getValue(), tone.getValue())) {
            raiseToneChange(getTone().clone(0));
        }
        raiseTone(getTone());
    }

    private void raiseTone(Tone tone) {
        IAction1<Tone> iAction1 = this._onTone;
        if (iAction1 != null) {
            iAction1.invoke(tone);
        }
    }

    private void raiseToneChange(Tone tone) {
        IAction1<Tone> iAction1 = this._onToneChange;
        if (iAction1 != null) {
            iAction1.invoke(tone);
        }
    }

    public Receiver() {
        this(new Format().getClockRate());
    }

    public Receiver(int i) {
        super(createFormat(i), createFormat(i));
        this.__onTone = new ArrayList();
        this.__onToneChange = new ArrayList();
        this._onTone = null;
        this._onToneChange = null;
        setTone(Tone.getEmpty());
    }

    public void removeOnTone(IAction1<Tone> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onTone, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onTone.remove(iAction1);
        if (this.__onTone.size() == 0) {
            this._onTone = null;
        }
    }

    public void removeOnToneChange(IAction1<Tone> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onToneChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onToneChange.remove(iAction1);
        if (this.__onToneChange.size() == 0) {
            this._onToneChange = null;
        }
    }

    private void setTone(Tone tone) {
        this._tone = tone;
    }
}
