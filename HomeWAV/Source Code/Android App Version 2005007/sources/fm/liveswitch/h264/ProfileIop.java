package fm.liveswitch.h264;

import fm.liveswitch.DataBuffer;

public class ProfileIop {
    private DataBuffer _dataBuffer;

    public boolean getConstraintSet0() {
        return getDataBuffer().read1(0, 0);
    }

    public boolean getConstraintSet1() {
        return getDataBuffer().read1(0, 1);
    }

    public boolean getConstraintSet2() {
        return getDataBuffer().read1(0, 2);
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public ProfileIop(byte b) {
        setDataBuffer(DataBuffer.wrap(new byte[]{b}));
    }

    public void setConstraintSet0(boolean z) {
        getDataBuffer().write1(z, 0, 0);
    }

    public void setConstraintSet1(boolean z) {
        getDataBuffer().write1(z, 0, 1);
    }

    public void setConstraintSet2(boolean z) {
        getDataBuffer().write1(z, 0, 2);
    }

    private void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }
}
