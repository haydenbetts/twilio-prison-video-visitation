package fm.liveswitch;

public class GenericNack {
    private DataBuffer _dataBuffer;
    private boolean _lostPacketIdPlus10Handled;
    private boolean _lostPacketIdPlus11Handled;
    private boolean _lostPacketIdPlus12Handled;
    private boolean _lostPacketIdPlus13Handled;
    private boolean _lostPacketIdPlus14Handled;
    private boolean _lostPacketIdPlus15Handled;
    private boolean _lostPacketIdPlus16Handled;
    private boolean _lostPacketIdPlus1Handled;
    private boolean _lostPacketIdPlus2Handled;
    private boolean _lostPacketIdPlus3Handled;
    private boolean _lostPacketIdPlus4Handled;
    private boolean _lostPacketIdPlus5Handled;
    private boolean _lostPacketIdPlus6Handled;
    private boolean _lostPacketIdPlus7Handled;
    private boolean _lostPacketIdPlus8Handled;
    private boolean _lostPacketIdPlus9Handled;
    private boolean _packetIdHandled;

    public static int getFixedPayloadLength() {
        return 4;
    }

    public int getLostPacketIdPlusLength() {
        return 16;
    }

    public void flush() {
        if (getPacketIdHandled()) {
            boolean[] zArr = new boolean[17];
            int i = -1;
            int i2 = 0;
            for (int i3 = 1; i3 <= 16; i3++) {
                boolean z = getLostPacketIdPlus(i3) && !getLostPacketIdPlusHandled(i3);
                if (i != -1) {
                    zArr[i3 - i2] = z;
                } else if (z) {
                    i = getPacketId() + i3;
                    i2 = i3;
                }
            }
            setPacketId(i);
            setPacketIdHandled(false);
            for (int i4 = 1; i4 <= 16; i4++) {
                setLostPacketIdPlus(i4, zArr[i4]);
                setLostPacketIdPlusHandled(i4, false);
            }
            return;
        }
        for (int i5 = 1; i5 <= 16; i5++) {
            setLostPacketIdPlus(i5, getLostPacketIdPlus(i5) && !getLostPacketIdPlusHandled(i5));
            setLostPacketIdPlusHandled(i5, false);
        }
    }

    public GenericNack() {
        this(DataBuffer.allocate(getFixedPayloadLength()));
    }

    public GenericNack(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() < getFixedPayloadLength()) {
            dataBuffer.resize(getFixedPayloadLength());
        }
        setDataBuffer(dataBuffer);
    }

    public int getBitmaskOfLostPackets() {
        return getDataBuffer().read16(2);
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public boolean getHandled() {
        if (!getPacketIdHandled()) {
            return false;
        }
        for (int i = 1; i <= 16; i++) {
            if (getLostPacketIdPlus(i) && !getLostPacketIdPlusHandled(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean getLostPacketIdPlus(int i) {
        if (i == 1) {
            return getLostPacketIdPlus1();
        }
        if (i == 2) {
            return getLostPacketIdPlus2();
        }
        if (i == 3) {
            return getLostPacketIdPlus3();
        }
        if (i == 4) {
            return getLostPacketIdPlus4();
        }
        if (i == 5) {
            return getLostPacketIdPlus5();
        }
        if (i == 6) {
            return getLostPacketIdPlus6();
        }
        if (i == 7) {
            return getLostPacketIdPlus7();
        }
        if (i == 8) {
            return getLostPacketIdPlus8();
        }
        if (i == 9) {
            return getLostPacketIdPlus9();
        }
        if (i == 10) {
            return getLostPacketIdPlus10();
        }
        if (i == 11) {
            return getLostPacketIdPlus11();
        }
        if (i == 12) {
            return getLostPacketIdPlus12();
        }
        if (i == 13) {
            return getLostPacketIdPlus13();
        }
        if (i == 14) {
            return getLostPacketIdPlus14();
        }
        if (i == 15) {
            return getLostPacketIdPlus15();
        }
        if (i == 16) {
            return getLostPacketIdPlus16();
        }
        throw new RuntimeException(new Exception("Value must be in the range 1-16."));
    }

    private boolean getLostPacketIdPlus1() {
        return getDataBuffer().read1(3, 7);
    }

    private boolean getLostPacketIdPlus10() {
        return getDataBuffer().read1(2, 6);
    }

    public boolean getLostPacketIdPlus10Handled() {
        return this._lostPacketIdPlus10Handled;
    }

    private boolean getLostPacketIdPlus11() {
        return getDataBuffer().read1(2, 5);
    }

    public boolean getLostPacketIdPlus11Handled() {
        return this._lostPacketIdPlus11Handled;
    }

    private boolean getLostPacketIdPlus12() {
        return getDataBuffer().read1(2, 4);
    }

    public boolean getLostPacketIdPlus12Handled() {
        return this._lostPacketIdPlus12Handled;
    }

    private boolean getLostPacketIdPlus13() {
        return getDataBuffer().read1(2, 3);
    }

    public boolean getLostPacketIdPlus13Handled() {
        return this._lostPacketIdPlus13Handled;
    }

    private boolean getLostPacketIdPlus14() {
        return getDataBuffer().read1(2, 2);
    }

    public boolean getLostPacketIdPlus14Handled() {
        return this._lostPacketIdPlus14Handled;
    }

    private boolean getLostPacketIdPlus15() {
        return getDataBuffer().read1(2, 1);
    }

    public boolean getLostPacketIdPlus15Handled() {
        return this._lostPacketIdPlus15Handled;
    }

    private boolean getLostPacketIdPlus16() {
        return getDataBuffer().read1(2, 0);
    }

    public boolean getLostPacketIdPlus16Handled() {
        return this._lostPacketIdPlus16Handled;
    }

    public boolean getLostPacketIdPlus1Handled() {
        return this._lostPacketIdPlus1Handled;
    }

    private boolean getLostPacketIdPlus2() {
        return getDataBuffer().read1(3, 6);
    }

    public boolean getLostPacketIdPlus2Handled() {
        return this._lostPacketIdPlus2Handled;
    }

    private boolean getLostPacketIdPlus3() {
        return getDataBuffer().read1(3, 5);
    }

    public boolean getLostPacketIdPlus3Handled() {
        return this._lostPacketIdPlus3Handled;
    }

    private boolean getLostPacketIdPlus4() {
        return getDataBuffer().read1(3, 4);
    }

    public boolean getLostPacketIdPlus4Handled() {
        return this._lostPacketIdPlus4Handled;
    }

    private boolean getLostPacketIdPlus5() {
        return getDataBuffer().read1(3, 3);
    }

    public boolean getLostPacketIdPlus5Handled() {
        return this._lostPacketIdPlus5Handled;
    }

    private boolean getLostPacketIdPlus6() {
        return getDataBuffer().read1(3, 2);
    }

    public boolean getLostPacketIdPlus6Handled() {
        return this._lostPacketIdPlus6Handled;
    }

    private boolean getLostPacketIdPlus7() {
        return getDataBuffer().read1(3, 1);
    }

    public boolean getLostPacketIdPlus7Handled() {
        return this._lostPacketIdPlus7Handled;
    }

    private boolean getLostPacketIdPlus8() {
        return getDataBuffer().read1(3, 0);
    }

    public boolean getLostPacketIdPlus8Handled() {
        return this._lostPacketIdPlus8Handled;
    }

    private boolean getLostPacketIdPlus9() {
        return getDataBuffer().read1(2, 7);
    }

    public boolean getLostPacketIdPlus9Handled() {
        return this._lostPacketIdPlus9Handled;
    }

    public boolean getLostPacketIdPlusHandled(int i) {
        if (i == 0) {
            return getPacketIdHandled();
        }
        if (i == 1) {
            return getLostPacketIdPlus1Handled();
        }
        if (i == 2) {
            return getLostPacketIdPlus2Handled();
        }
        if (i == 3) {
            return getLostPacketIdPlus3Handled();
        }
        if (i == 4) {
            return getLostPacketIdPlus4Handled();
        }
        if (i == 5) {
            return getLostPacketIdPlus5Handled();
        }
        if (i == 6) {
            return getLostPacketIdPlus6Handled();
        }
        if (i == 7) {
            return getLostPacketIdPlus7Handled();
        }
        if (i == 8) {
            return getLostPacketIdPlus8Handled();
        }
        if (i == 9) {
            return getLostPacketIdPlus9Handled();
        }
        if (i == 10) {
            return getLostPacketIdPlus10Handled();
        }
        if (i == 11) {
            return getLostPacketIdPlus11Handled();
        }
        if (i == 12) {
            return getLostPacketIdPlus12Handled();
        }
        if (i == 13) {
            return getLostPacketIdPlus13Handled();
        }
        if (i == 14) {
            return getLostPacketIdPlus14Handled();
        }
        if (i == 15) {
            return getLostPacketIdPlus15Handled();
        }
        if (i == 16) {
            return getLostPacketIdPlus16Handled();
        }
        throw new RuntimeException(new Exception("Value must be in the range 0-16."));
    }

    public int getPacketId() {
        return getDataBuffer().read16(0);
    }

    public boolean getPacketIdHandled() {
        return this._packetIdHandled;
    }

    public void setBitmaskOfLostPackets(int i) {
        getDataBuffer().write16(i, 2);
    }

    private void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    public void setLostPacketIdPlus(int i, boolean z) {
        if (i == 1) {
            setLostPacketIdPlus1(z);
        } else if (i == 2) {
            setLostPacketIdPlus2(z);
        } else if (i == 3) {
            setLostPacketIdPlus3(z);
        } else if (i == 4) {
            setLostPacketIdPlus4(z);
        } else if (i == 5) {
            setLostPacketIdPlus5(z);
        } else if (i == 6) {
            setLostPacketIdPlus6(z);
        } else if (i == 7) {
            setLostPacketIdPlus7(z);
        } else if (i == 8) {
            setLostPacketIdPlus8(z);
        } else if (i == 9) {
            setLostPacketIdPlus9(z);
        } else if (i == 10) {
            setLostPacketIdPlus10(z);
        } else if (i == 11) {
            setLostPacketIdPlus11(z);
        } else if (i == 12) {
            setLostPacketIdPlus12(z);
        } else if (i == 13) {
            setLostPacketIdPlus13(z);
        } else if (i == 14) {
            setLostPacketIdPlus14(z);
        } else if (i == 15) {
            setLostPacketIdPlus15(z);
        } else if (i == 16) {
            setLostPacketIdPlus16(z);
        } else {
            throw new RuntimeException(new Exception("Value must be in the range 1-16."));
        }
    }

    private void setLostPacketIdPlus1(boolean z) {
        getDataBuffer().write1(z, 3, 7);
    }

    private void setLostPacketIdPlus10(boolean z) {
        getDataBuffer().write1(z, 2, 6);
    }

    public void setLostPacketIdPlus10Handled(boolean z) {
        this._lostPacketIdPlus10Handled = z;
    }

    private void setLostPacketIdPlus11(boolean z) {
        getDataBuffer().write1(z, 2, 5);
    }

    public void setLostPacketIdPlus11Handled(boolean z) {
        this._lostPacketIdPlus11Handled = z;
    }

    private void setLostPacketIdPlus12(boolean z) {
        getDataBuffer().write1(z, 2, 4);
    }

    public void setLostPacketIdPlus12Handled(boolean z) {
        this._lostPacketIdPlus12Handled = z;
    }

    private void setLostPacketIdPlus13(boolean z) {
        getDataBuffer().write1(z, 2, 3);
    }

    public void setLostPacketIdPlus13Handled(boolean z) {
        this._lostPacketIdPlus13Handled = z;
    }

    private void setLostPacketIdPlus14(boolean z) {
        getDataBuffer().write1(z, 2, 2);
    }

    public void setLostPacketIdPlus14Handled(boolean z) {
        this._lostPacketIdPlus14Handled = z;
    }

    private void setLostPacketIdPlus15(boolean z) {
        getDataBuffer().write1(z, 2, 1);
    }

    public void setLostPacketIdPlus15Handled(boolean z) {
        this._lostPacketIdPlus15Handled = z;
    }

    private void setLostPacketIdPlus16(boolean z) {
        getDataBuffer().write1(z, 2, 0);
    }

    public void setLostPacketIdPlus16Handled(boolean z) {
        this._lostPacketIdPlus16Handled = z;
    }

    public void setLostPacketIdPlus1Handled(boolean z) {
        this._lostPacketIdPlus1Handled = z;
    }

    private void setLostPacketIdPlus2(boolean z) {
        getDataBuffer().write1(z, 3, 6);
    }

    public void setLostPacketIdPlus2Handled(boolean z) {
        this._lostPacketIdPlus2Handled = z;
    }

    private void setLostPacketIdPlus3(boolean z) {
        getDataBuffer().write1(z, 3, 5);
    }

    public void setLostPacketIdPlus3Handled(boolean z) {
        this._lostPacketIdPlus3Handled = z;
    }

    private void setLostPacketIdPlus4(boolean z) {
        getDataBuffer().write1(z, 3, 4);
    }

    public void setLostPacketIdPlus4Handled(boolean z) {
        this._lostPacketIdPlus4Handled = z;
    }

    private void setLostPacketIdPlus5(boolean z) {
        getDataBuffer().write1(z, 3, 3);
    }

    public void setLostPacketIdPlus5Handled(boolean z) {
        this._lostPacketIdPlus5Handled = z;
    }

    private void setLostPacketIdPlus6(boolean z) {
        getDataBuffer().write1(z, 3, 2);
    }

    public void setLostPacketIdPlus6Handled(boolean z) {
        this._lostPacketIdPlus6Handled = z;
    }

    private void setLostPacketIdPlus7(boolean z) {
        getDataBuffer().write1(z, 3, 1);
    }

    public void setLostPacketIdPlus7Handled(boolean z) {
        this._lostPacketIdPlus7Handled = z;
    }

    private void setLostPacketIdPlus8(boolean z) {
        getDataBuffer().write1(z, 3, 0);
    }

    public void setLostPacketIdPlus8Handled(boolean z) {
        this._lostPacketIdPlus8Handled = z;
    }

    private void setLostPacketIdPlus9(boolean z) {
        getDataBuffer().write1(z, 2, 7);
    }

    public void setLostPacketIdPlus9Handled(boolean z) {
        this._lostPacketIdPlus9Handled = z;
    }

    public void setLostPacketIdPlusHandled(int i, boolean z) {
        if (i == 0) {
            setPacketIdHandled(z);
        } else if (i == 1) {
            setLostPacketIdPlus1Handled(z);
        } else if (i == 2) {
            setLostPacketIdPlus2Handled(z);
        } else if (i == 3) {
            setLostPacketIdPlus3Handled(z);
        } else if (i == 4) {
            setLostPacketIdPlus4Handled(z);
        } else if (i == 5) {
            setLostPacketIdPlus5Handled(z);
        } else if (i == 6) {
            setLostPacketIdPlus6Handled(z);
        } else if (i == 7) {
            setLostPacketIdPlus7Handled(z);
        } else if (i == 8) {
            setLostPacketIdPlus8Handled(z);
        } else if (i == 9) {
            setLostPacketIdPlus9Handled(z);
        } else if (i == 10) {
            setLostPacketIdPlus10Handled(z);
        } else if (i == 11) {
            setLostPacketIdPlus11Handled(z);
        } else if (i == 12) {
            setLostPacketIdPlus12Handled(z);
        } else if (i == 13) {
            setLostPacketIdPlus13Handled(z);
        } else if (i == 14) {
            setLostPacketIdPlus14Handled(z);
        } else if (i == 15) {
            setLostPacketIdPlus15Handled(z);
        } else if (i == 16) {
            setLostPacketIdPlus16Handled(z);
        } else {
            throw new RuntimeException(new Exception("Value must be in the range 0-16."));
        }
    }

    public void setPacketId(int i) {
        getDataBuffer().write16(i, 0);
    }

    public void setPacketIdHandled(boolean z) {
        this._packetIdHandled = z;
    }
}
