package org.bytedeco.javacv;

import java.util.ArrayList;
import java.util.List;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.librealsense2.global.realsense2;
import org.bytedeco.librealsense2.rs2_config;
import org.bytedeco.librealsense2.rs2_context;
import org.bytedeco.librealsense2.rs2_device;
import org.bytedeco.librealsense2.rs2_device_list;
import org.bytedeco.librealsense2.rs2_error;
import org.bytedeco.librealsense2.rs2_frame;
import org.bytedeco.librealsense2.rs2_options;
import org.bytedeco.librealsense2.rs2_pipeline;
import org.bytedeco.librealsense2.rs2_pipeline_profile;
import org.bytedeco.librealsense2.rs2_sensor;
import org.bytedeco.librealsense2.rs2_sensor_list;
import org.bytedeco.librealsense2.rs2_stream_profile;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Size;

public class RealSense2FrameGrabber extends FrameGrabber {
    private rs2_config config;
    private rs2_context context;
    private FrameConverter converter;
    private rs2_device device;
    private int deviceNumber;
    private rs2_error error;
    private rs2_frame frameset;
    private rs2_pipeline pipeline;
    private rs2_pipeline_profile pipelineProfile;
    private List<RealSenseStream> streams;

    private static boolean toBoolean(int i) {
        return i >= 1;
    }

    public RealSense2FrameGrabber() throws FrameGrabber.Exception {
        this(0);
    }

    public RealSense2FrameGrabber(int i) throws FrameGrabber.Exception {
        this.error = new rs2_error();
        this.streams = new ArrayList();
        this.converter = new OpenCVFrameConverter.ToIplImage();
        this.deviceNumber = i;
        this.context = createContext();
    }

    public List<RealSense2DeviceInfo> getDeviceInfos() throws FrameGrabber.Exception {
        ArrayList arrayList = new ArrayList();
        rs2_device_list createDeviceList = createDeviceList();
        int rs2_get_device_count = realsense2.rs2_get_device_count(createDeviceList, this.error);
        checkError(this.error);
        for (int i = 0; i < rs2_get_device_count; i++) {
            rs2_device createDevice = createDevice(createDeviceList, i);
            arrayList.add(new RealSense2DeviceInfo(getDeviceInfo(createDevice, 0), getDeviceInfo(createDevice, 1), getDeviceInfo(createDevice, 2), toBoolean(getDeviceInfo(createDevice, 6)), toBoolean(getDeviceInfo(createDevice, 8))));
            realsense2.rs2_delete_device(createDevice);
        }
        realsense2.rs2_delete_device_list(createDeviceList);
        return arrayList;
    }

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        RealSense2FrameGrabber realSense2FrameGrabber = new RealSense2FrameGrabber();
        List<RealSense2DeviceInfo> deviceInfos = realSense2FrameGrabber.getDeviceInfos();
        realSense2FrameGrabber.release();
        int size = deviceInfos.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = deviceInfos.get(i).toString();
        }
        return strArr;
    }

    public void disableAllStreams() {
        this.streams.clear();
    }

    public List<RealSenseStream> getEnabledStreams() {
        return this.streams;
    }

    public void enableStream(RealSenseStream realSenseStream) {
        this.streams.add(realSenseStream);
    }

    public void enableColorStream(int i, int i2, int i3) {
        enableStream(new RealSenseStream(2, 0, new Size(i, i2), i3, 6));
    }

    public void enableDepthStream(int i, int i2, int i3) {
        enableStream(new RealSenseStream(1, 0, new Size(i, i2), i3, 1));
    }

    public void enableIRStream(int i, int i2, int i3, int i4) {
        enableStream(new RealSenseStream(3, i4, new Size(i, i2), i3, 9));
    }

    public void enableIRStream(int i, int i2, int i3) {
        enableIRStream(i, i2, i3, 1);
    }

    public void open() throws FrameGrabber.Exception {
        if (getDeviceCount() > 0) {
            rs2_device_list createDeviceList = createDeviceList();
            this.device = createDevice(createDeviceList, this.deviceNumber);
            realsense2.rs2_delete_device_list(createDeviceList);
            return;
        }
        throw new FrameGrabber.Exception("No realsense2 device is connected.");
    }

    public void start() throws FrameGrabber.Exception {
        if (this.device == null) {
            open();
        }
        this.pipeline = createPipeline();
        this.config = createConfig();
        if (!this.streams.isEmpty()) {
            for (RealSenseStream next : this.streams) {
                realsense2.rs2_config_enable_stream(this.config, next.type, next.index, next.size.width(), next.size.height(), next.format, next.frameRate, this.error);
                checkError(this.error);
            }
            RealSenseStream largestStreamByArea = getLargestStreamByArea();
            this.imageWidth = largestStreamByArea.size.width();
            this.imageHeight = largestStreamByArea.size.height();
            this.pipelineProfile = realsense2.rs2_pipeline_start_with_config(this.pipeline, this.config, this.error);
            checkError(this.error);
            return;
        }
        throw new FrameGrabber.Exception("No stream has been added to be enabled.");
    }

    public void stop() throws FrameGrabber.Exception {
        realsense2.rs2_pipeline_stop(this.pipeline, this.error);
        checkError(this.error);
        realsense2.rs2_release_frame(this.frameset);
        realsense2.rs2_delete_pipeline_profile(this.pipelineProfile);
        realsense2.rs2_delete_config(this.config);
        realsense2.rs2_delete_pipeline(this.pipeline);
        realsense2.rs2_delete_device(this.device);
        this.device = null;
    }

    private void readNextFrameSet() throws FrameGrabber.Exception {
        realsense2.rs2_release_frame(this.frameset);
        this.frameset = realsense2.rs2_pipeline_wait_for_frames(this.pipeline, 15000, this.error);
        checkError(this.error);
    }

    public void trigger() throws FrameGrabber.Exception {
        if (!this.triggerMode) {
            this.triggerMode = true;
        }
        readNextFrameSet();
    }

    public Frame grab() throws FrameGrabber.Exception {
        int access$000 = this.streams.get(0).type;
        if (access$000 == 1) {
            return grabDepth();
        }
        if (access$000 != 3) {
            return grabColor();
        }
        return grabIR();
    }

    public Frame grab(int i, int i2, int i3, int i4) throws FrameGrabber.Exception {
        if (!this.triggerMode) {
            readNextFrameSet();
        }
        return grabCVFrame(i, i2, i3, i4);
    }

    public float getDistance(int i, int i2) throws FrameGrabber.Exception {
        rs2_frame findFrameByStreamType = findFrameByStreamType(this.frameset, 1, 0);
        if (findFrameByStreamType == null) {
            return -1.0f;
        }
        float rs2_depth_frame_get_distance = realsense2.rs2_depth_frame_get_distance(findFrameByStreamType, i, i2, this.error);
        checkError(this.error);
        realsense2.rs2_release_frame(findFrameByStreamType);
        return rs2_depth_frame_get_distance;
    }

    public Frame grabColor() throws FrameGrabber.Exception {
        if (!this.triggerMode) {
            readNextFrameSet();
        }
        return grabCVFrame(2, 0, 8, 3);
    }

    public Frame grabDepth() throws FrameGrabber.Exception {
        if (!this.triggerMode) {
            readNextFrameSet();
        }
        return grabCVFrame(1, 0, 16, 1);
    }

    public Frame grabIR() throws FrameGrabber.Exception {
        return grabIR(0);
    }

    public Frame grabIR(int i) throws FrameGrabber.Exception {
        if (!this.triggerMode) {
            readNextFrameSet();
        }
        return grabCVFrame(3, i, 8, 1);
    }

    private RealSenseStream getLargestStreamByArea() {
        RealSenseStream realSenseStream = this.streams.get(0);
        for (RealSenseStream next : this.streams) {
            if (next.size.area() > realSenseStream.size.area()) {
                realSenseStream = next;
            }
        }
        return realSenseStream;
    }

    private Frame grabCVFrame(int i, int i2, int i3, int i4) throws FrameGrabber.Exception {
        rs2_frame findFrameByStreamType = findFrameByStreamType(this.frameset, i, i2);
        if (findFrameByStreamType == null) {
            return null;
        }
        Pointer frameData = getFrameData(findFrameByStreamType);
        Size frameSize = getFrameSize(findFrameByStreamType);
        IplImage createHeader = IplImage.createHeader(frameSize.width(), frameSize.height(), i3, i4);
        opencv_core.cvSetData(createHeader, frameData, ((frameSize.width() * i4) * i3) / 8);
        Frame convert = this.converter.convert(createHeader);
        convert.timestamp = Math.round(getFrameTimeStamp(findFrameByStreamType));
        realsense2.rs2_release_frame(findFrameByStreamType);
        return convert;
    }

    private rs2_frame findFrameByStreamType(rs2_frame rs2_frame, int i, int i2) throws FrameGrabber.Exception {
        int rs2_embedded_frames_count = realsense2.rs2_embedded_frames_count(rs2_frame, this.error);
        checkError(this.error);
        int i3 = 0;
        for (int i4 = 0; i4 < rs2_embedded_frames_count; i4++) {
            rs2_frame rs2_extract_frame = realsense2.rs2_extract_frame(rs2_frame, i4, this.error);
            checkError(this.error);
            if (i == getStreamProfileData(getStreamProfile(rs2_extract_frame)).nativeStreamIndex.get()) {
                if (i3 == i2) {
                    return rs2_extract_frame;
                }
                i3++;
            }
            realsense2.rs2_release_frame(rs2_extract_frame);
        }
        return null;
    }

    public void release() {
        realsense2.rs2_delete_device(this.device);
        realsense2.rs2_delete_context(this.context);
    }

    public void setSensorOption(Rs2SensorType rs2SensorType, int i, boolean z) throws FrameGrabber.Exception {
        setSensorOption(rs2SensorType, i, z ? 1.0f : 0.0f);
    }

    public void setSensorOption(Rs2SensorType rs2SensorType, int i, float f) throws FrameGrabber.Exception {
        rs2_sensor_list rs2_query_sensors = realsense2.rs2_query_sensors(this.device, this.error);
        checkError(this.error);
        int rs2_get_sensors_count = realsense2.rs2_get_sensors_count(rs2_query_sensors, this.error);
        checkError(this.error);
        for (int i2 = 0; i2 < rs2_get_sensors_count; i2++) {
            rs2_sensor rs2_create_sensor = realsense2.rs2_create_sensor(rs2_query_sensors, i2, this.error);
            checkError(this.error);
            if (rs2SensorType.getName().equals(getSensorInfo(rs2_create_sensor, 0))) {
                setRs2Option(new rs2_options(rs2_create_sensor), i, f);
            }
            realsense2.rs2_delete_sensor(rs2_create_sensor);
        }
        realsense2.rs2_delete_sensor_list(rs2_query_sensors);
    }

    private rs2_context createContext() throws FrameGrabber.Exception {
        rs2_context rs2_create_context = realsense2.rs2_create_context(22900, this.error);
        checkError(this.error);
        return rs2_create_context;
    }

    private rs2_device_list createDeviceList() throws FrameGrabber.Exception {
        rs2_device_list rs2_query_devices = realsense2.rs2_query_devices(this.context, this.error);
        checkError(this.error);
        return rs2_query_devices;
    }

    private rs2_device createDevice(rs2_device_list rs2_device_list, int i) throws FrameGrabber.Exception {
        rs2_device rs2_create_device = realsense2.rs2_create_device(rs2_device_list, i, this.error);
        checkError(this.error);
        return rs2_create_device;
    }

    private rs2_pipeline createPipeline() throws FrameGrabber.Exception {
        rs2_pipeline rs2_create_pipeline = realsense2.rs2_create_pipeline(this.context, this.error);
        checkError(this.error);
        return rs2_create_pipeline;
    }

    private rs2_config createConfig() throws FrameGrabber.Exception {
        rs2_config rs2_create_config = realsense2.rs2_create_config(this.error);
        checkError(this.error);
        return rs2_create_config;
    }

    private double getFrameTimeStamp(rs2_frame rs2_frame) throws FrameGrabber.Exception {
        double rs2_get_frame_timestamp = realsense2.rs2_get_frame_timestamp(rs2_frame, this.error);
        checkError(this.error);
        return rs2_get_frame_timestamp;
    }

    private int getDeviceCount() throws FrameGrabber.Exception {
        rs2_device_list createDeviceList = createDeviceList();
        int rs2_get_device_count = realsense2.rs2_get_device_count(createDeviceList, this.error);
        checkError(this.error);
        realsense2.rs2_delete_device_list(createDeviceList);
        return rs2_get_device_count;
    }

    private String getDeviceInfo(rs2_device rs2_device, int i) throws FrameGrabber.Exception {
        rs2_error rs2_error = new rs2_error();
        boolean z = toBoolean(realsense2.rs2_supports_device_info(rs2_device, i, rs2_error));
        checkError(rs2_error);
        if (!z) {
            return null;
        }
        String string = realsense2.rs2_get_device_info(rs2_device, i, rs2_error).getString();
        checkError(rs2_error);
        return string;
    }

    private String getSensorInfo(rs2_sensor rs2_sensor, int i) throws FrameGrabber.Exception {
        rs2_error rs2_error = new rs2_error();
        boolean z = toBoolean(realsense2.rs2_supports_sensor_info(rs2_sensor, i, rs2_error));
        checkError(rs2_error);
        if (!z) {
            return null;
        }
        String string = realsense2.rs2_get_sensor_info(rs2_sensor, i, rs2_error).getString();
        checkError(rs2_error);
        return string;
    }

    private Pointer getFrameData(rs2_frame rs2_frame) throws FrameGrabber.Exception {
        Pointer rs2_get_frame_data = realsense2.rs2_get_frame_data(rs2_frame, this.error);
        checkError(this.error);
        return rs2_get_frame_data;
    }

    private Size getFrameSize(rs2_frame rs2_frame) throws FrameGrabber.Exception {
        int rs2_get_frame_width = realsense2.rs2_get_frame_width(rs2_frame, this.error);
        checkError(this.error);
        int rs2_get_frame_height = realsense2.rs2_get_frame_height(rs2_frame, this.error);
        checkError(this.error);
        return new Size(rs2_get_frame_width, rs2_get_frame_height);
    }

    private rs2_stream_profile getStreamProfile(rs2_frame rs2_frame) throws FrameGrabber.Exception {
        rs2_stream_profile rs2_get_frame_stream_profile = realsense2.rs2_get_frame_stream_profile(rs2_frame, this.error);
        checkError(this.error);
        return rs2_get_frame_stream_profile;
    }

    private StreamProfileData getStreamProfileData(rs2_stream_profile rs2_stream_profile) throws FrameGrabber.Exception {
        StreamProfileData streamProfileData = new StreamProfileData();
        realsense2.rs2_get_stream_profile_data(rs2_stream_profile, streamProfileData.nativeStreamIndex, streamProfileData.nativeFormatIndex, streamProfileData.index, streamProfileData.uniqueId, streamProfileData.frameRate, this.error);
        checkError(this.error);
        return streamProfileData;
    }

    private boolean isSensorExtendableTo(rs2_sensor rs2_sensor, int i) throws FrameGrabber.Exception {
        boolean z = toBoolean(realsense2.rs2_is_sensor_extendable_to(rs2_sensor, i, this.error));
        checkError(this.error);
        return z;
    }

    private void setRs2Option(rs2_options rs2_options, int i, float f) throws FrameGrabber.Exception {
        boolean z = toBoolean(realsense2.rs2_supports_option(rs2_options, i, this.error));
        checkError(this.error);
        if (z) {
            realsense2.rs2_set_option(rs2_options, i, f, this.error);
            checkError(this.error);
            return;
        }
        throw new FrameGrabber.Exception("Option " + i + " is not supported!");
    }

    private static void checkError(rs2_error rs2_error) throws FrameGrabber.Exception {
        if (!rs2_error.isNull()) {
            throw new FrameGrabber.Exception(String.format("rs_error was raised when calling %s(%s):\n%s\n", new Object[]{realsense2.rs2_get_failed_function(rs2_error).getString(), realsense2.rs2_get_failed_args(rs2_error).getString(), realsense2.rs2_get_error_message(rs2_error).getString()}));
        }
    }

    private static boolean toBoolean(String str) {
        if (str == null) {
            return false;
        }
        return str.equals("YES");
    }

    static class StreamProfileData {
        IntPointer frameRate = new IntPointer(1);
        IntPointer index = new IntPointer(1);
        IntPointer nativeFormatIndex = new IntPointer(1);
        IntPointer nativeStreamIndex = new IntPointer(1);
        IntPointer uniqueId = new IntPointer(1);

        StreamProfileData() {
        }
    }

    public static class RealSenseStream {
        /* access modifiers changed from: private */
        public int format;
        /* access modifiers changed from: private */
        public int frameRate;
        /* access modifiers changed from: private */
        public int index;
        /* access modifiers changed from: private */
        public Size size;
        /* access modifiers changed from: private */
        public int type;

        public RealSenseStream(int i, int i2, Size size2, int i3, int i4) {
            this.type = i;
            this.index = i2;
            this.size = size2;
            this.frameRate = i3;
            this.format = i4;
        }

        public int getType() {
            return this.type;
        }

        public int getIndex() {
            return this.index;
        }

        public Size getSize() {
            return this.size;
        }

        public int getFrameRate() {
            return this.frameRate;
        }

        public int getFormat() {
            return this.format;
        }
    }

    public static class RealSense2DeviceInfo {
        private String firmware;
        private boolean inAdvancedMode;
        private boolean locked;
        private String name;
        private String serialNumber;

        RealSense2DeviceInfo(String str, String str2, String str3, boolean z, boolean z2) {
            this.name = str;
            this.serialNumber = str2;
            this.firmware = str3;
            this.inAdvancedMode = z;
            this.locked = z2;
        }

        public String getName() {
            return this.name;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public String getFirmware() {
            return this.firmware;
        }

        public boolean isInAdvancedMode() {
            return this.inAdvancedMode;
        }

        public boolean isLocked() {
            return this.locked;
        }

        public String toString() {
            return String.format("%s", new Object[]{this.name});
        }
    }

    public enum Rs2SensorType {
        StereoModule("Stereo Module"),
        RGBCamera("RGB Camera");
        
        private String name;

        private Rs2SensorType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }
}
