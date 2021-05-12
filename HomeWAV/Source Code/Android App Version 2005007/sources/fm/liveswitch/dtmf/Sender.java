package fm.liveswitch.dtmf;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.AudioPipe;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Global;
import fm.liveswitch.IAction1;
import fm.liveswitch.IActionDelegate1;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.PacketizedAudioBuffer;
import fm.liveswitch.RtpPacketHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sender extends AudioPipe {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Sender.class);
    private volatile boolean __nextStart;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onTone;
    /* access modifiers changed from: private */
    public List<IAction1<Tone>> __onToneChange;
    private ArrayList<Tone> __toneQueue;
    private Object __toneQueueLock;
    private IAction1<Tone> _onTone;
    private IAction1<Tone> _onToneChange;
    private Tone _tone;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "DTMF Sender";
    }

    public void addOnTone(IAction1<Tone> iAction1) {
        if (iAction1 != null) {
            if (this._onTone == null) {
                this._onTone = new IAction1<Tone>() {
                    public void invoke(Tone tone) {
                        Iterator it = new ArrayList(Sender.this.__onTone).iterator();
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
                        Iterator it = new ArrayList(Sender.this.__onToneChange).iterator();
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
        raiseFrame(audioFrame);
    }

    private int doRaisePacket(Packet packet, long j, long j2, long j3) {
        DataBuffer take = __dataBufferPool.take(packet.getLength());
        try {
            packet.writeTo(take);
            processPacket(packet);
            if (packet.getEnd()) {
                Packet packet2 = new Packet();
                packet2.setEventCode(-1);
                packet2.setEnd(true);
                packet2.setDuration(0);
                packet2.setVolume(0);
                processPacket(packet2);
            }
            int i = 1;
            raiseFrame(getFrame(take, packet.getStart(), j, j2, j3, 0));
            if (packet.getEnd()) {
                raiseFrame(getFrame(take, packet.getStart(), j, j2, j3, 1));
                i = 3;
                raiseFrame(getFrame(take, packet.getStart(), j, j2, j3, 2));
            }
            return i;
        } finally {
            take.free();
        }
    }

    private AudioFrame getFrame(DataBuffer dataBuffer, boolean z, long j, long j2, long j3, int i) {
        RtpPacketHeader rtpPacketHeader = new RtpPacketHeader();
        rtpPacketHeader.setMarker(z);
        AudioFrame audioFrame = new AudioFrame(-1, (AudioBuffer) new PacketizedAudioBuffer(dataBuffer, (AudioFormat) super.getOutputFormat(), rtpPacketHeader));
        audioFrame.setSynchronizationSource(j);
        audioFrame.setTimestamp(j2);
        if (j3 != -1) {
            long j4 = j3 + ((long) i);
            audioFrame.setSequenceNumber(j4);
            audioFrame.setRtpSequenceNumber((int) (j4 % 65536));
            ((AudioBuffer) audioFrame.getLastBuffer()).setSequenceNumber(j4);
            ((AudioBuffer) audioFrame.getLastBuffer()).getRtpHeader().setSequenceNumber(audioFrame.getRtpSequenceNumber());
        }
        return audioFrame;
    }

    public Tone getTone() {
        return this._tone;
    }

    public boolean hasTone() {
        boolean z;
        synchronized (this.__toneQueueLock) {
            z = ArrayListExtensions.getCount(this.__toneQueue) > 0;
        }
        return z;
    }

    public void insertTones(Tone[] toneArr) {
        if (toneArr != null) {
            int length = toneArr.length;
            int i = 0;
            while (i < length) {
                if (toneArr[i] != null) {
                    i++;
                } else {
                    throw new RuntimeException(new Exception("Tone cannot be null."));
                }
            }
            if (ArrayExtensions.getLength((Object[]) toneArr) > 0) {
                synchronized (this.__toneQueueLock) {
                    ArrayListExtensions.addRange(this.__toneQueue, (T[]) toneArr);
                    Tone tone = toneArr[ArrayExtensions.getLength((Object[]) toneArr) - 1];
                    if (!Global.equals(tone.getValue(), Tone.getEmptyValue()) && !Global.equals(tone.getValue(), Tone.getPauseValue())) {
                        this.__toneQueue.add(new Tone(Tone.getEmptyValue(), 100));
                    }
                }
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception("Tones cannot be null."));
    }

    private void processPacket(Packet packet) {
        Tone tone = getTone();
        setTone(Tone.fromPacket(packet, super.getConfig().getClockRate()));
        if (!Global.equals(getTone().getValue(), tone.getValue())) {
            raiseToneChange(getTone());
        }
        raiseTone(getTone());
    }

    public boolean raiseTone(int i, long j, long j2) {
        return raiseTone(i, j, j2, -1) != 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        if (r5.getValue() == null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0074, code lost:
        if (fm.liveswitch.Global.equals(r5.getValue(), fm.liveswitch.dtmf.Tone.getEmptyValue()) == false) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0083, code lost:
        if (fm.liveswitch.Global.equals(r5.getValue(), fm.liveswitch.dtmf.Tone.getPauseValue()) == false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0085, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0086, code lost:
        r2 = r5.toPacket(super.getConfig().getClockRate());
        r2.setVolume(10);
        r2.setEnd(r8);
        r2.setStart(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a6, code lost:
        return doRaisePacket(r2, r14, r6, r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a7, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int raiseTone(int r11, long r12, long r14, long r16) {
        /*
            r10 = this;
            r9 = r10
            if (r11 > 0) goto L_0x0006
            r0 = 20
            goto L_0x0007
        L_0x0006:
            r0 = r11
        L_0x0007:
            boolean r1 = r9.__nextStart
            java.lang.Object r2 = r9.__toneQueueLock
            monitor-enter(r2)
            java.util.ArrayList<fm.liveswitch.dtmf.Tone> r3 = r9.__toneQueue     // Catch:{ all -> 0x00a8 }
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r3)     // Catch:{ all -> 0x00a8 }
            r4 = 0
            if (r3 != 0) goto L_0x0017
            monitor-exit(r2)     // Catch:{ all -> 0x00a8 }
            return r4
        L_0x0017:
            java.util.ArrayList<fm.liveswitch.dtmf.Tone> r3 = r9.__toneQueue     // Catch:{ all -> 0x00a8 }
            java.util.ArrayList r3 = fm.liveswitch.ArrayListExtensions.getItem(r3)     // Catch:{ all -> 0x00a8 }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x00a8 }
            fm.liveswitch.dtmf.Tone r3 = (fm.liveswitch.dtmf.Tone) r3     // Catch:{ all -> 0x00a8 }
            fm.liveswitch.dtmf.Tone r5 = r3.clone()     // Catch:{ all -> 0x00a8 }
            int r6 = r3.getSentDuration()     // Catch:{ all -> 0x00a8 }
            int r6 = r6 + r0
            r5.setDuration(r6)     // Catch:{ all -> 0x00a8 }
            int r0 = r5.getDuration()     // Catch:{ all -> 0x00a8 }
            r3.setSentDuration(r0)     // Catch:{ all -> 0x00a8 }
            int r0 = r3.getSentDuration()     // Catch:{ all -> 0x00a8 }
            fm.liveswitch.MediaFormat r6 = super.getOutputFormat()     // Catch:{ all -> 0x00a8 }
            fm.liveswitch.AudioFormat r6 = (fm.liveswitch.AudioFormat) r6     // Catch:{ all -> 0x00a8 }
            int r6 = r6.getClockRate()     // Catch:{ all -> 0x00a8 }
            int r0 = fm.liveswitch.SoundUtility.calculateTimestampDeltaFromDuration(r0, r6)     // Catch:{ all -> 0x00a8 }
            long r6 = (long) r0     // Catch:{ all -> 0x00a8 }
            long r6 = r12 - r6
            int r0 = r3.getSentDuration()     // Catch:{ all -> 0x00a8 }
            int r3 = r3.getDuration()     // Catch:{ all -> 0x00a8 }
            r8 = 1
            if (r0 != r3) goto L_0x005e
            java.util.ArrayList<fm.liveswitch.dtmf.Tone> r0 = r9.__toneQueue     // Catch:{ all -> 0x00a8 }
            fm.liveswitch.ArrayListExtensions.removeAt(r0, r4)     // Catch:{ all -> 0x00a8 }
            r9.__nextStart = r8     // Catch:{ all -> 0x00a8 }
            goto L_0x0061
        L_0x005e:
            r9.__nextStart = r4     // Catch:{ all -> 0x00a8 }
            r8 = 0
        L_0x0061:
            monitor-exit(r2)     // Catch:{ all -> 0x00a8 }
            java.lang.String r0 = r5.getValue()
            if (r0 == 0) goto L_0x00a7
            java.lang.String r0 = r5.getValue()
            java.lang.String r2 = fm.liveswitch.dtmf.Tone.getEmptyValue()
            boolean r0 = fm.liveswitch.Global.equals(r0, r2)
            if (r0 == 0) goto L_0x0077
            goto L_0x00a7
        L_0x0077:
            java.lang.String r0 = r5.getValue()
            java.lang.String r2 = fm.liveswitch.dtmf.Tone.getPauseValue()
            boolean r0 = fm.liveswitch.Global.equals(r0, r2)
            if (r0 == 0) goto L_0x0086
            return r4
        L_0x0086:
            fm.liveswitch.AudioConfig r0 = super.getConfig()
            int r0 = r0.getClockRate()
            fm.liveswitch.dtmf.Packet r2 = r5.toPacket(r0)
            r0 = 10
            r2.setVolume(r0)
            r2.setEnd(r8)
            r2.setStart(r1)
            r1 = r10
            r3 = r14
            r5 = r6
            r7 = r16
            int r0 = r1.doRaisePacket(r2, r3, r5, r7)
            return r0
        L_0x00a7:
            return r4
        L_0x00a8:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00a8 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.dtmf.Sender.raiseTone(int, long, long, long):int");
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

    public Sender() {
        this(new Format().getClockRate());
    }

    public Sender(int i) {
        super(createFormat(i), createFormat(i));
        this.__onTone = new ArrayList();
        this.__onToneChange = new ArrayList();
        this._onTone = null;
        this._onToneChange = null;
        this.__toneQueue = new ArrayList<>();
        this.__toneQueueLock = new Object();
        this.__nextStart = true;
        setTone(Tone.getEmpty());
    }

    private void setTone(Tone tone) {
        this._tone = tone;
    }
}
