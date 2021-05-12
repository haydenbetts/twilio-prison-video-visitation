package fm.liveswitch.yuv4mpeg;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.FileAssistant;
import fm.liveswitch.FileStream;
import fm.liveswitch.FileStreamAccess;
import fm.liveswitch.Future;
import fm.liveswitch.Global;
import fm.liveswitch.IAction0;
import fm.liveswitch.IActionDelegate0;
import fm.liveswitch.ILog;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.ManagedTimer;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.Promise;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.Utf8;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoSource extends fm.liveswitch.VideoSource {
    private static ILog __log = Log.getLogger(VideoSource.class);
    /* access modifiers changed from: private */
    public FileStream __file;
    private FileAssistant __fileStream;
    private long __fileStreamFrameStart = 0;
    private String __headerColourSpace;
    private String __headerComment;
    private double __headerFrameRate;
    private int __headerHeight;
    private String __headerInterlacing;
    private double __headerPixelAspectRatio;
    private int __headerWidth;
    /* access modifiers changed from: private */
    public List<IAction0> __onEnded = new ArrayList();
    /* access modifiers changed from: private */
    public ManagedTimer __timer;
    private long __timestamp = -1;
    private int __timestampStep = 0;
    private IAction0 _onEnded = null;
    private String _path;

    public void addOnEnded(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onEnded == null) {
                this._onEnded = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(VideoSource.this.__onEnded).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onEnded.add(iAction0);
        }
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        int i;
        Promise promise = new Promise();
        try {
            FileStream fileStream = new FileStream(getPath());
            this.__file = fileStream;
            fileStream.open(FileStreamAccess.Read);
            FileAssistant fileAssistant = new FileAssistant(this.__file);
            this.__fileStream = fileAssistant;
            if (fileAssistant.read8() == 89 && this.__fileStream.read8() == 85 && this.__fileStream.read8() == 86 && this.__fileStream.read8() == 52 && this.__fileStream.read8() == 77 && this.__fileStream.read8() == 80 && this.__fileStream.read8() == 69 && this.__fileStream.read8() == 71 && this.__fileStream.read8() == 50) {
                int read8 = this.__fileStream.read8();
                if (read8 != 10) {
                    if (read8 != 32) {
                        throw new RuntimeException(new Exception("Malformed file header."));
                    }
                }
                while (read8 != 10) {
                    int read82 = this.__fileStream.read8();
                    if (read82 == 87) {
                        IntegerHolder integerHolder = new IntegerHolder(read82);
                        String readParameter = readParameter(integerHolder);
                        i = integerHolder.getValue();
                        IntegerHolder integerHolder2 = new IntegerHolder(this.__headerWidth);
                        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(readParameter, integerHolder2);
                        this.__headerWidth = integerHolder2.getValue();
                        if (!tryParseIntegerValue) {
                            throw new RuntimeException(new Exception("Invalid file header width."));
                        }
                    } else if (read82 == 72) {
                        IntegerHolder integerHolder3 = new IntegerHolder(read82);
                        String readParameter2 = readParameter(integerHolder3);
                        i = integerHolder3.getValue();
                        IntegerHolder integerHolder4 = new IntegerHolder(this.__headerHeight);
                        boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(readParameter2, integerHolder4);
                        this.__headerHeight = integerHolder4.getValue();
                        if (!tryParseIntegerValue2) {
                            throw new RuntimeException(new Exception("Invalid file header height."));
                        }
                    } else if (read82 == 70) {
                        IntegerHolder integerHolder5 = new IntegerHolder(read82);
                        String readParameter3 = readParameter(integerHolder5);
                        i = integerHolder5.getValue();
                        String[] split = StringExtensions.split(readParameter3, new char[]{':'});
                        if (ArrayExtensions.getLength((Object[]) split) == 2) {
                            IntegerHolder integerHolder6 = new IntegerHolder(0);
                            boolean tryParseIntegerValue3 = ParseAssistant.tryParseIntegerValue(split[0], integerHolder6);
                            int value = integerHolder6.getValue();
                            IntegerHolder integerHolder7 = new IntegerHolder(0);
                            boolean tryParseIntegerValue4 = ParseAssistant.tryParseIntegerValue(split[1], integerHolder7);
                            int value2 = integerHolder7.getValue();
                            if (!tryParseIntegerValue3 || !tryParseIntegerValue4) {
                                throw new RuntimeException(new Exception("Invalid file header frame rate."));
                            }
                            this.__headerFrameRate = ((double) value) / ((double) value2);
                        } else {
                            throw new RuntimeException(new Exception("Invalid file header frame rate."));
                        }
                    } else if (read82 == 73) {
                        IntegerHolder integerHolder8 = new IntegerHolder(read82);
                        String readParameter4 = readParameter(integerHolder8);
                        i = integerHolder8.getValue();
                        this.__headerInterlacing = readParameter4;
                    } else if (read82 == 65) {
                        IntegerHolder integerHolder9 = new IntegerHolder(read82);
                        String readParameter5 = readParameter(integerHolder9);
                        i = integerHolder9.getValue();
                        String[] split2 = StringExtensions.split(readParameter5, new char[]{':'});
                        if (ArrayExtensions.getLength((Object[]) split2) == 2) {
                            IntegerHolder integerHolder10 = new IntegerHolder(0);
                            boolean tryParseIntegerValue5 = ParseAssistant.tryParseIntegerValue(split2[0], integerHolder10);
                            int value3 = integerHolder10.getValue();
                            IntegerHolder integerHolder11 = new IntegerHolder(0);
                            boolean tryParseIntegerValue6 = ParseAssistant.tryParseIntegerValue(split2[1], integerHolder11);
                            int value4 = integerHolder11.getValue();
                            if (!tryParseIntegerValue5 || !tryParseIntegerValue6) {
                                throw new RuntimeException(new Exception("Invalid file header pixel aspect ratio."));
                            }
                            this.__headerPixelAspectRatio = ((double) value3) / ((double) value4);
                        } else {
                            throw new RuntimeException(new Exception("Invalid file header pixel aspect ratio."));
                        }
                    } else if (read82 == 67) {
                        IntegerHolder integerHolder12 = new IntegerHolder(read82);
                        String readParameter6 = readParameter(integerHolder12);
                        i = integerHolder12.getValue();
                        this.__headerColourSpace = readParameter6;
                    } else if (read82 == 67) {
                        IntegerHolder integerHolder13 = new IntegerHolder(read82);
                        String readParameter7 = readParameter(integerHolder13);
                        i = integerHolder13.getValue();
                        this.__headerColourSpace = readParameter7;
                    } else if (read82 == 88) {
                        IntegerHolder integerHolder14 = new IntegerHolder(read82);
                        String readParameter8 = readParameter(integerHolder14);
                        i = integerHolder14.getValue();
                        this.__headerComment = readParameter8;
                    } else {
                        String decode = Utf8.decode(new byte[]{(byte) read82});
                        IntegerHolder integerHolder15 = new IntegerHolder(read82);
                        String readParameter9 = readParameter(integerHolder15);
                        int value5 = integerHolder15.getValue();
                        __log.warn(StringExtensions.format("Ignoring file header parameter {0}{1}", decode, readParameter9));
                        read8 = value5;
                    }
                    read8 = i;
                }
                this.__fileStreamFrameStart = this.__file.getPosition();
                int perSecondInterval = ManagedTimer.perSecondInterval(this.__headerFrameRate);
                this.__timestampStep = (((VideoFormat) super.getOutputFormat()).getClockRate() * perSecondInterval) / 1000;
                ManagedTimer managedTimer = new ManagedTimer(perSecondInterval, new IActionDelegate0() {
                    public String getId() {
                        return "fm.liveswitch.yuv4mpeg.VideoSource.raiseFrame";
                    }

                    public void invoke() {
                        VideoSource.this.raiseFrame();
                    }
                });
                this.__timer = managedTimer;
                managedTimer.start();
                promise.resolve(null);
                return promise;
            }
            throw new RuntimeException(new Exception("Invalid file signature."));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        Promise promise = new Promise();
        try {
            doStopAsync(promise);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    private void doStopAsync(final Promise<Object> promise) {
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    if (VideoSource.this.__timer != null) {
                        VideoSource.this.__timer.stop();
                        ManagedTimer unused = VideoSource.this.__timer = null;
                    }
                    if (VideoSource.this.__file != null) {
                        VideoSource.this.__file.close();
                        FileStream unused2 = VideoSource.this.__file = null;
                    }
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
    }

    public String getLabel() {
        return StringExtensions.format("YUV4MPEG Video Source", new Object[0]);
    }

    public String getPath() {
        return this._path;
    }

    /* access modifiers changed from: private */
    public void raiseFrame() {
        int value;
        if (this.__fileStream.read8() == 70 && this.__fileStream.read8() == 82 && this.__fileStream.read8() == 65 && this.__fileStream.read8() == 77 && this.__fileStream.read8() == 69) {
            int read8 = this.__fileStream.read8();
            if (read8 == 10 || read8 == 32) {
                int i = this.__headerWidth;
                int i2 = this.__headerHeight;
                while (read8 != 10) {
                    int read82 = this.__fileStream.read8();
                    if (read82 == 87) {
                        IntegerHolder integerHolder = new IntegerHolder(read82);
                        String readParameter = readParameter(integerHolder);
                        value = integerHolder.getValue();
                        IntegerHolder integerHolder2 = new IntegerHolder(i);
                        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(readParameter, integerHolder2);
                        i = integerHolder2.getValue();
                        if (!tryParseIntegerValue) {
                            throw new RuntimeException(new Exception("Invalid frame header width."));
                        }
                    } else if (read82 == 72) {
                        IntegerHolder integerHolder3 = new IntegerHolder(read82);
                        String readParameter2 = readParameter(integerHolder3);
                        value = integerHolder3.getValue();
                        IntegerHolder integerHolder4 = new IntegerHolder(i2);
                        boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(readParameter2, integerHolder4);
                        i2 = integerHolder4.getValue();
                        if (!tryParseIntegerValue2) {
                            throw new RuntimeException(new Exception("Invalid frame header height."));
                        }
                    } else {
                        String decode = Utf8.decode(new byte[]{(byte) read82});
                        IntegerHolder integerHolder5 = new IntegerHolder(read82);
                        String readParameter3 = readParameter(integerHolder5);
                        int value2 = integerHolder5.getValue();
                        __log.warn(StringExtensions.format("Ignoring frame header parameter {0}{1}", decode, readParameter3));
                        read8 = value2;
                    }
                    read8 = value;
                }
                long j = this.__timestamp;
                if (j == -1) {
                    this.__timestamp = 0;
                } else {
                    this.__timestamp = j + ((long) this.__timestampStep);
                }
                VideoFrame videoFrame = new VideoFrame(new VideoBuffer(i, i2, DataBuffer.wrap(this.__fileStream.read(((i * i2) * 3) / 2)), (VideoFormat) super.getOutputFormat()));
                videoFrame.setTimestamp(this.__timestamp);
                raiseFrame(videoFrame);
                if (this.__file.getPosition() == this.__file.getLength()) {
                    IAction0 iAction0 = this._onEnded;
                    if (iAction0 != null) {
                        iAction0.invoke();
                    }
                    this.__file.setPosition(this.__fileStreamFrameStart);
                    return;
                }
                return;
            }
            throw new RuntimeException(new Exception("Malformed frame header."));
        }
        throw new RuntimeException(new Exception("Invalid frame signature."));
    }

    private String readParameter(IntegerHolder integerHolder) {
        String str = StringExtensions.empty;
        integerHolder.setValue(this.__fileStream.read8());
        while (integerHolder.getValue() != 10 && integerHolder.getValue() != 32) {
            str = StringExtensions.concat(str, Utf8.decode(new byte[]{(byte) integerHolder.getValue()}));
            integerHolder.setValue(this.__fileStream.read8());
        }
        return str;
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

    public VideoSource(String str) {
        super(VideoFormat.getI420());
        setPath(str);
    }
}
