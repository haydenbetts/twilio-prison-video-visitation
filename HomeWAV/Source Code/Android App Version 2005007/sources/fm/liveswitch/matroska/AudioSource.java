package fm.liveswitch.matroska;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioDecoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.Binary;
import fm.liveswitch.Constants;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.FileStream;
import fm.liveswitch.FileStreamAccess;
import fm.liveswitch.Future;
import fm.liveswitch.Global;
import fm.liveswitch.IAction0;
import fm.liveswitch.IAction1;
import fm.liveswitch.IActionDelegate0;
import fm.liveswitch.IActionDelegate1;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.Promise;
import fm.liveswitch.SoundConverter;
import fm.liveswitch.SourceInput;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.pcm.Format;
import fm.liveswitch.pcma.Decoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AudioSource extends fm.liveswitch.AudioSource {
    private long ___baseTimestamp;
    private int __channelCount;
    private int __clockRate;
    private Cluster[] __clusters;
    private byte[] __data;
    private AudioDecoder __decoder;
    private File __file;
    private long __lastTimestamp = -1;
    private long __lastTimestampChange = -1;
    /* access modifiers changed from: private */
    public List<IAction0> __onEnded = new ArrayList();
    private Segment __segment;
    private SegmentInfo __segmentInfo;
    /* access modifiers changed from: private */
    public SoundConverter __soundConverter;
    private ManagedThread __thread;
    private volatile boolean __threadActive = false;
    private volatile boolean __threadExited = false;
    private long __timecodeScale;
    private TrackEntry __trackEntry;
    private long __trackNumber;
    private Track[] __tracks;
    private IAction0 _onEnded = null;
    private String _path;

    /* access modifiers changed from: protected */
    public abstract AudioDecoder createOpusDecoder(AudioConfig audioConfig);

    public void addOnEnded(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onEnded == null) {
                this._onEnded = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(AudioSource.this.__onEnded).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onEnded.add(iAction0);
        }
    }

    public AudioSource(String str) {
        super(new Format(48000, 2));
        setPath(str);
        super.setOutputSynchronizable(true);
        readFile();
        parseFile();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0002 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void captureLoop(fm.liveswitch.ManagedThread r17) {
        /*
            r16 = this;
            r6 = r16
        L_0x0002:
            boolean r0 = r6.__threadActive
            if (r0 == 0) goto L_0x0089
            fm.liveswitch.ManagedStopwatch r7 = new fm.liveswitch.ManagedStopwatch
            r7.<init>()
            r7.start()
            long r0 = r6.__lastTimestamp
            r2 = -1
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0021
            long r4 = r6.__lastTimestampChange
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 != 0) goto L_0x001d
            goto L_0x0021
        L_0x001d:
            long r0 = r0 + r4
            r6.___baseTimestamp = r0
            goto L_0x0025
        L_0x0021:
            r0 = 0
            r6.___baseTimestamp = r0
        L_0x0025:
            fm.liveswitch.matroska.Cluster[] r8 = r6.__clusters
            int r9 = r8.length
            r10 = 0
            r11 = 0
        L_0x002a:
            if (r11 >= r9) goto L_0x007d
            r12 = r8[r11]
            fm.liveswitch.matroska.SimpleBlock[] r0 = r12.getSimpleBlocks()
            if (r0 == 0) goto L_0x0052
            fm.liveswitch.matroska.SimpleBlock[] r13 = r12.getSimpleBlocks()
            int r14 = r13.length
            r15 = 0
        L_0x003a:
            if (r15 >= r14) goto L_0x007a
            r1 = r13[r15]
            boolean r0 = r6.__threadActive
            if (r0 == 0) goto L_0x004f
            long r2 = r12.getTimecode()
            long r4 = r7.getElapsedTicks()
            r0 = r16
            r0.processBlock(r1, r2, r4)
        L_0x004f:
            int r15 = r15 + 1
            goto L_0x003a
        L_0x0052:
            fm.liveswitch.matroska.BlockGroup[] r0 = r12.getBlockGroups()
            if (r0 == 0) goto L_0x007a
            fm.liveswitch.matroska.BlockGroup[] r13 = r12.getBlockGroups()
            int r14 = r13.length
            r15 = 0
        L_0x005e:
            if (r15 >= r14) goto L_0x007a
            r0 = r13[r15]
            boolean r1 = r6.__threadActive
            if (r1 == 0) goto L_0x0077
            fm.liveswitch.matroska.Block r1 = r0.getBlock()
            long r2 = r12.getTimecode()
            long r4 = r7.getElapsedTicks()
            r0 = r16
            r0.processBlock(r1, r2, r4)
        L_0x0077:
            int r15 = r15 + 1
            goto L_0x005e
        L_0x007a:
            int r11 = r11 + 1
            goto L_0x002a
        L_0x007d:
            r7.stop()
            fm.liveswitch.IAction0 r0 = r6._onEnded
            if (r0 == 0) goto L_0x0002
            r0.invoke()
            goto L_0x0002
        L_0x0089:
            r0 = 1
            r6.__threadExited = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.matroska.AudioSource.captureLoop(fm.liveswitch.ManagedThread):void");
    }

    /* access modifiers changed from: protected */
    public AudioDecoder createPcmaDecoder(AudioConfig audioConfig) {
        return new Decoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public AudioDecoder createPcmuDecoder(AudioConfig audioConfig) {
        return new fm.liveswitch.pcmu.Decoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        Promise promise = new Promise();
        try {
            this.__trackEntry = null;
            SourceInput input = getInput();
            TrackEntry[] trackEntries = getTrackEntries(this.__tracks);
            if (ArrayExtensions.getLength((Object[]) trackEntries) != 0) {
                if (input != null) {
                    for (TrackEntry trackEntry : trackEntries) {
                        if (Global.equals(LongExtensions.toString(Long.valueOf(trackEntry.getTrackUid())), input.getId())) {
                            this.__trackEntry = trackEntry;
                        }
                    }
                }
                if (this.__trackEntry == null) {
                    this.__trackEntry = trackEntries[0];
                }
                this.__trackNumber = this.__trackEntry.getTrackNumber();
                String codecId = this.__trackEntry.getCodecId();
                if (codecId != null) {
                    this.__clockRate = (int) Audio.getDefaultSamplingFrequency();
                    this.__channelCount = (int) Audio.getDefaultChannels();
                    Audio audio = this.__trackEntry.getAudio();
                    if (audio != null) {
                        this.__clockRate = (int) audio.getSamplingFrequency();
                        this.__channelCount = (int) audio.getChannels();
                    }
                    if (Global.equals(codecId, TrackEntry.getOpusCodecId())) {
                        byte[] codecPrivate = this.__trackEntry.getCodecPrivate();
                        if (codecPrivate != null) {
                            if (ArrayExtensions.getLength(codecPrivate) >= 9 && codecPrivate[8] == 1) {
                                if (ArrayExtensions.getLength(codecPrivate) >= 10) {
                                    this.__channelCount = codecPrivate[9];
                                }
                                if (ArrayExtensions.getLength(codecPrivate) >= 16) {
                                    this.__clockRate = (int) Binary.fromBytes32(codecPrivate, 12, true);
                                }
                            }
                            this.__decoder = createOpusDecoder(new AudioConfig(this.__clockRate, this.__channelCount));
                        } else {
                            throw new RuntimeException(new Exception("Matroska audio track has no codec-private data."));
                        }
                    } else if (Global.equals(codecId, TrackEntry.getPcmCodecId())) {
                        byte[] codecPrivate2 = this.__trackEntry.getCodecPrivate();
                        if (codecPrivate2 != null) {
                            if (ArrayExtensions.getLength(codecPrivate2) >= 4) {
                                this.__channelCount = Binary.fromBytes16(codecPrivate2, 2, true);
                            }
                            if (ArrayExtensions.getLength(codecPrivate2) >= 8) {
                                this.__clockRate = Binary.fromBytes16(codecPrivate2, 4, true);
                            }
                            if (ArrayExtensions.getLength(codecPrivate2) >= 2) {
                                int fromBytes16 = Binary.fromBytes16(codecPrivate2, 0, true);
                                if (fromBytes16 == TrackEntry.getPcmuFormatTag()) {
                                    this.__decoder = createPcmuDecoder(new AudioConfig(this.__clockRate, this.__channelCount));
                                } else if (fromBytes16 == TrackEntry.getPcmaFormatTag()) {
                                    this.__decoder = createPcmaDecoder(new AudioConfig(this.__clockRate, this.__channelCount));
                                } else {
                                    throw new RuntimeException(new Exception("Matroska audio track has unrecognized PCM codec."));
                                }
                            } else {
                                throw new RuntimeException(new Exception("Matroska audio track has unrecognized PCM codec."));
                            }
                        } else {
                            throw new RuntimeException(new Exception("Matroska audio track has no codec-private data."));
                        }
                    } else {
                        throw new RuntimeException(new Exception(StringExtensions.format("Matroska audio track has unrecognized codec ({0}).", (Object) codecId)));
                    }
                    this.__decoder.addOnRaiseFrame(new IAction1<AudioFrame>() {
                        public void invoke(AudioFrame audioFrame) {
                            if (!((AudioFormat) ((AudioBuffer) audioFrame.getLastBuffer()).getFormat()).isCompatible((AudioFormat) AudioSource.this.getOutputFormat()) && AudioSource.this.__soundConverter == null) {
                                SoundConverter unused = AudioSource.this.__soundConverter = new SoundConverter(((AudioFormat) AudioSource.this.getOutputFormat()).getConfig());
                                AudioSource.this.__soundConverter.addOnProcessFrame(new IAction1<AudioFrame>() {
                                    public void invoke(AudioFrame audioFrame) {
                                        AudioSource.this.raiseFrame(audioFrame);
                                    }
                                });
                            }
                            if (AudioSource.this.__soundConverter == null) {
                                AudioSource.this.raiseFrame(audioFrame);
                            } else {
                                AudioSource.this.__soundConverter.processFrame(audioFrame);
                            }
                        }
                    });
                    this.__threadActive = true;
                    this.__threadExited = false;
                    ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                        public String getId() {
                            return "fm.liveswitch.matroska.AudioSource.captureLoop";
                        }

                        public void invoke(ManagedThread managedThread) {
                            AudioSource.this.captureLoop(managedThread);
                        }
                    });
                    this.__thread = managedThread;
                    managedThread.start();
                    promise.resolve(null);
                    return promise;
                }
                throw new RuntimeException(new Exception("Matroska audio track has no codec ID."));
            }
            throw new RuntimeException(new Exception("Matroska file has no matching audio tracks."));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        Promise promise = new Promise();
        try {
            this.__threadActive = false;
            while (!this.__threadExited) {
                ManagedThread.sleep(10);
            }
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    public Future<SourceInput[]> getInputs() {
        Promise promise = new Promise();
        try {
            ArrayList arrayList = new ArrayList();
            for (TrackEntry trackEntry : getTrackEntries(this.__tracks)) {
                arrayList.add(new SourceInput(LongExtensions.toString(Long.valueOf(trackEntry.getTrackUid())), trackEntry.getName()));
            }
            promise.resolve(arrayList.toArray(new SourceInput[0]));
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    public String getLabel() {
        return StringExtensions.format("Matroska Audio Source", new Object[0]);
    }

    public String getPath() {
        return this._path;
    }

    private static TrackEntry[] getTrackEntries(Track[] trackArr) {
        ArrayList arrayList = new ArrayList();
        for (Track trackEntries : trackArr) {
            TrackEntry[] trackEntries2 = trackEntries.getTrackEntries();
            if (trackEntries2 != null) {
                for (TrackEntry trackEntry : trackEntries2) {
                    if (trackEntry.getTrackType() == TrackType.getAudio()) {
                        arrayList.add(trackEntry);
                    }
                }
            }
        }
        return (TrackEntry[]) arrayList.toArray(new TrackEntry[0]);
    }

    private void parseFile() {
        File file = new File(this.__data);
        this.__file = file;
        Segment segment = file.getSegment();
        this.__segment = segment;
        if (segment != null) {
            SegmentInfo segmentInfo = segment.getSegmentInfo();
            this.__segmentInfo = segmentInfo;
            if (segmentInfo == null) {
                this.__timecodeScale = SegmentInfo.getDefaultTimecodeScale();
            } else {
                this.__timecodeScale = segmentInfo.getTimecodeScale();
            }
            Track[] tracks = this.__segment.getTracks();
            this.__tracks = tracks;
            if (tracks != null) {
                Cluster[] clusters = this.__segment.getClusters();
                this.__clusters = clusters;
                if (clusters == null) {
                    throw new RuntimeException(new Exception("Matroska file has no clusters."));
                }
                return;
            }
            throw new RuntimeException(new Exception("Matroska file has no tracks."));
        }
        throw new RuntimeException(new Exception("Matroska file has no segment."));
    }

    private void processBlock(Block block, long j, long j2) {
        if (block.getTrackNumber() == this.__trackNumber) {
            long timecode = ((j + ((long) block.getTimecode())) * this.__timecodeScale) / 100;
            if (timecode > j2) {
                ManagedThread.sleep((int) ((timecode - j2) / ((long) Constants.getTicksPerMillisecond())));
            }
            if (this.__threadActive) {
                long ticksPerSecond = this.___baseTimestamp + ((((long) this.__clockRate) * timecode) / ((long) Constants.getTicksPerSecond()));
                AudioFrame audioFrame = new AudioFrame(-1, new AudioBuffer(DataBuffer.wrap(block.getData()), (AudioFormat) this.__decoder.getInputFormat()));
                audioFrame.setTimestamp(ticksPerSecond);
                this.__decoder.processFrame(audioFrame);
                long j3 = this.__lastTimestamp;
                if (j3 != -1) {
                    this.__lastTimestampChange = (long) ((int) (ticksPerSecond - j3));
                }
                this.__lastTimestamp = ticksPerSecond;
            }
        }
    }

    private void readFile() {
        FileStream fileStream = new FileStream(getPath());
        fileStream.open(FileStreamAccess.Read);
        this.__data = new byte[((int) fileStream.getLength())];
        int i = 0;
        while (i < ArrayExtensions.getLength(this.__data)) {
            byte[] bArr = this.__data;
            i += fileStream.read(bArr, i, MathAssistant.min(ArrayExtensions.getLength(bArr) - i, 8192));
        }
        fileStream.close();
    }

    public void removeOnEnded(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onEnded, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onEnded.remove(iAction0);
        if (this.__onEnded.size() == 0) {
            this._onEnded = null;
        }
    }

    private void setPath(String str) {
        this._path = str;
    }
}
