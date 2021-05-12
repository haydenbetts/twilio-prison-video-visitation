package fm.liveswitch;

import java.util.ArrayList;

public class LayoutPreset extends Dynamic {
    private int __blockHeight = 0;
    private double __blockHeightPercent = 0.0d;
    private int __blockMarginX = 0;
    private double __blockMarginXPercent = 0.0d;
    private int __blockMarginY = 0;
    private double __blockMarginYPercent = 0.0d;
    private int __blockWidth = 0;
    private double __blockWidthPercent = 0.0d;
    private int __floatHeight = 0;
    private double __floatHeightPercent = 0.0d;
    private int __floatMarginX = 0;
    private double __floatMarginXPercent = 0.0d;
    private int __floatMarginY = 0;
    private double __floatMarginYPercent = 0.0d;
    private int __floatWidth = 0;
    private double __floatWidthPercent = 0.0d;
    private LayoutAlignment _alignment;
    private LayoutDirection _direction;
    private int _inlineMargin;
    private LayoutMode _mode;

    public void applyPreset(LayoutPreset layoutPreset) {
        setMode(layoutPreset.getMode());
        setDirection(layoutPreset.getDirection());
        setAlignment(layoutPreset.getAlignment());
        setFloatWidth(0);
        setFloatHeight(0);
        setFloatMarginX(0);
        setFloatMarginY(0);
        setFloatWidthPercent(0.0d);
        setFloatHeightPercent(0.0d);
        setFloatMarginXPercent(0.0d);
        setFloatMarginYPercent(0.0d);
        setBlockWidth(0);
        setBlockHeight(0);
        setBlockMarginX(0);
        setBlockMarginY(0);
        setBlockWidthPercent(0.0d);
        setBlockHeightPercent(0.0d);
        setBlockMarginXPercent(0.0d);
        setBlockMarginYPercent(0.0d);
        setInlineMargin(0);
        if (layoutPreset.getFloatWidth() > 0) {
            setFloatWidth(layoutPreset.getFloatWidth());
        }
        if (layoutPreset.getFloatHeight() > 0) {
            setFloatHeight(layoutPreset.getFloatHeight());
        }
        if (layoutPreset.getFloatMarginX() > 0) {
            setFloatMarginX(layoutPreset.getFloatMarginX());
        }
        if (layoutPreset.getFloatMarginY() > 0) {
            setFloatMarginY(layoutPreset.getFloatMarginY());
        }
        if (layoutPreset.getFloatWidthPercent() > 0.0d) {
            setFloatWidthPercent(layoutPreset.getFloatWidthPercent());
        }
        if (layoutPreset.getFloatHeightPercent() > 0.0d) {
            setFloatHeightPercent(layoutPreset.getFloatHeightPercent());
        }
        if (layoutPreset.getFloatMarginXPercent() > 0.0d) {
            setFloatMarginXPercent(layoutPreset.getFloatMarginXPercent());
        }
        if (layoutPreset.getFloatMarginYPercent() > 0.0d) {
            setFloatMarginYPercent(layoutPreset.getFloatMarginYPercent());
        }
        if (layoutPreset.getBlockWidth() > 0) {
            setBlockWidth(layoutPreset.getBlockWidth());
        }
        if (layoutPreset.getBlockHeight() > 0) {
            setBlockHeight(layoutPreset.getBlockHeight());
        }
        if (layoutPreset.getBlockMarginX() > 0) {
            setBlockMarginX(layoutPreset.getBlockMarginX());
        }
        if (layoutPreset.getBlockMarginY() > 0) {
            setBlockMarginY(layoutPreset.getBlockMarginY());
        }
        if (layoutPreset.getBlockWidthPercent() > 0.0d) {
            setBlockWidthPercent(layoutPreset.getBlockWidthPercent());
        }
        if (layoutPreset.getBlockHeightPercent() > 0.0d) {
            setBlockHeightPercent(layoutPreset.getBlockHeightPercent());
        }
        if (layoutPreset.getBlockMarginXPercent() > 0.0d) {
            setBlockMarginXPercent(layoutPreset.getBlockMarginXPercent());
        }
        if (layoutPreset.getBlockMarginYPercent() > 0.0d) {
            setBlockMarginYPercent(layoutPreset.getBlockMarginYPercent());
        }
        if (layoutPreset.getInlineMargin() > 0) {
            setInlineMargin(layoutPreset.getInlineMargin());
        }
    }

    private LayoutFrame calculateBlockFrame(int i, int i2, IntegerHolder integerHolder, IntegerHolder integerHolder2) {
        int i3;
        if (getBlockWidth() == 0 && getBlockWidthPercent() == 0.0d) {
            setBlockWidthPercent(0.25d);
        }
        if (getBlockHeight() == 0 && getBlockHeightPercent() == 0.0d) {
            setBlockHeightPercent(0.25d);
        }
        int blockWidth = getBlockWidth() > 0 ? getBlockWidth() : (int) (((double) i) * getBlockWidthPercent());
        int blockHeight = getBlockHeight() > 0 ? getBlockHeight() : (int) (((double) i2) * getBlockHeightPercent());
        integerHolder.setValue(getBlockMarginX() > 0 ? getBlockMarginX() : (int) (((double) i) * getBlockMarginXPercent()));
        integerHolder2.setValue(getBlockMarginY() > 0 ? getBlockMarginY() : (int) (((double) i2) * getBlockMarginYPercent()));
        LayoutAlignment alignment = getAlignment();
        int i4 = 0;
        if (alignment == LayoutAlignment.Top || alignment == LayoutAlignment.Center || alignment == LayoutAlignment.Bottom) {
            i3 = divideByTwo(i - blockWidth);
        } else {
            i3 = (alignment == LayoutAlignment.TopRight || alignment == LayoutAlignment.Right || alignment == LayoutAlignment.BottomRight) ? i - blockWidth : 0;
        }
        LayoutAlignment alignment2 = getAlignment();
        if (alignment2 == LayoutAlignment.Left || alignment2 == LayoutAlignment.Center || alignment2 == LayoutAlignment.Right) {
            i4 = divideByTwo(i2 - blockHeight);
        } else if (alignment2 == LayoutAlignment.BottomLeft || alignment2 == LayoutAlignment.Bottom || alignment2 == LayoutAlignment.BottomRight) {
            i4 = i2 - blockHeight;
        }
        return new LayoutFrame(i3, i4, blockWidth, blockHeight);
    }

    private LayoutFrame calculateFillFrame(int i, int i2) {
        return new LayoutFrame(0, 0, i, i2);
    }

    private LayoutFrame calculateFloatFrame(int i, int i2) {
        return calculateFloatFrames(i, i2, 1)[0];
    }

    private LayoutFrame[] calculateFloatFrames(int i, int i2, int i3) {
        if (getFloatWidth() == 0 && getFloatWidthPercent() == 0.0d) {
            setFloatWidthPercent(0.25d);
        }
        if (getFloatHeight() == 0 && getFloatHeightPercent() == 0.0d) {
            setFloatHeightPercent(0.25d);
        }
        int floatWidth = getFloatWidth() > 0 ? getFloatWidth() : (int) (((double) i) * getFloatWidthPercent());
        int floatHeight = getFloatHeight() > 0 ? getFloatHeight() : (int) (((double) i2) * getFloatHeightPercent());
        int floatMarginX = getFloatMarginX() > 0 ? getFloatMarginX() : (int) (((double) i) * getFloatMarginXPercent());
        int floatMarginY = getFloatMarginY() > 0 ? getFloatMarginY() : (int) (((double) i2) * getFloatMarginYPercent());
        if (Global.equals(getDirection(), LayoutDirection.Horizontal)) {
            floatWidth = MathAssistant.min(i, floatWidth * i3);
        } else {
            floatHeight = MathAssistant.min(i2, floatHeight * i3);
        }
        LayoutFrame[] calculateInlineFrames = calculateInlineFrames(floatWidth, floatHeight, i3, 0, 0);
        LayoutAlignment alignment = getAlignment();
        int i4 = 0;
        if (alignment == LayoutAlignment.TopLeft || alignment == LayoutAlignment.Top || alignment == LayoutAlignment.TopRight) {
            for (LayoutFrame layoutFrame : calculateInlineFrames) {
                layoutFrame.setY(layoutFrame.getY() + floatMarginY);
            }
        } else if (alignment == LayoutAlignment.Left || alignment == LayoutAlignment.Center || alignment == LayoutAlignment.Right) {
            for (LayoutFrame layoutFrame2 : calculateInlineFrames) {
                layoutFrame2.setY(layoutFrame2.getY() + divideByTwo(i2 - floatHeight));
            }
        } else if (alignment == LayoutAlignment.BottomLeft || alignment == LayoutAlignment.Bottom || alignment == LayoutAlignment.BottomRight) {
            for (LayoutFrame layoutFrame3 : calculateInlineFrames) {
                layoutFrame3.setY(layoutFrame3.getY() + ((i2 - floatHeight) - floatMarginY));
            }
        }
        LayoutAlignment alignment2 = getAlignment();
        if (alignment2 == LayoutAlignment.TopLeft || alignment2 == LayoutAlignment.Left || alignment2 == LayoutAlignment.BottomLeft) {
            int length = calculateInlineFrames.length;
            while (i4 < length) {
                LayoutFrame layoutFrame4 = calculateInlineFrames[i4];
                layoutFrame4.setX(layoutFrame4.getX() + floatMarginX);
                i4++;
            }
            return calculateInlineFrames;
        } else if (alignment2 == LayoutAlignment.Top || alignment2 == LayoutAlignment.Center || alignment2 == LayoutAlignment.Bottom) {
            int length2 = calculateInlineFrames.length;
            while (i4 < length2) {
                LayoutFrame layoutFrame5 = calculateInlineFrames[i4];
                layoutFrame5.setX(layoutFrame5.getX() + divideByTwo(i - floatWidth));
                i4++;
            }
            return calculateInlineFrames;
        } else if (alignment2 != LayoutAlignment.TopRight && alignment2 != LayoutAlignment.Right && alignment2 != LayoutAlignment.BottomRight) {
            return calculateInlineFrames;
        } else {
            int length3 = calculateInlineFrames.length;
            while (i4 < length3) {
                LayoutFrame layoutFrame6 = calculateInlineFrames[i4];
                layoutFrame6.setX(layoutFrame6.getX() + ((i - floatWidth) - floatMarginX));
                i4++;
            }
            return calculateInlineFrames;
        }
    }

    private LayoutFrame calculateInlineFrame(int i, int i2, int i3, int i4) {
        int divideByTwo = divideByTwo(getInlineMargin());
        return new LayoutFrame(i - divideByTwo, i2 - divideByTwo, i3 - getInlineMargin(), i4 - getInlineMargin());
    }

    private LayoutFrame[] calculateInlineFrames(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11 = i3;
        ArrayList arrayList = new ArrayList();
        LayoutTable calculateTable = calculateTable(i + getInlineMargin(), i2 + getInlineMargin(), i11);
        int columnCount = calculateTable.getColumnCount();
        int rowCount = calculateTable.getRowCount();
        int cellWidth = calculateTable.getCellWidth();
        int cellHeight = calculateTable.getCellHeight();
        int divideByTwo = divideByTwo(getInlineMargin());
        int i12 = 1;
        if (Global.equals(getDirection(), LayoutDirection.Horizontal)) {
            int inlineMargin = (i2 - (rowCount * cellHeight)) + getInlineMargin();
            int i13 = i5 + divideByTwo;
            int i14 = 0;
            int i15 = 0;
            while (i14 < rowCount) {
                int i16 = i14 < inlineMargin ? 1 : 0;
                int i17 = rowCount - 1;
                int i18 = i14 == i17 ? i11 - i15 : columnCount;
                int i19 = i4 + divideByTwo;
                if (i14 != i17 || rowCount <= 1) {
                    i8 = i14;
                    i10 = i13;
                    i9 = inlineMargin;
                    int i20 = i18;
                    int inlineMargin2 = (i - (i20 * cellWidth)) + getInlineMargin();
                    int i21 = 0;
                    while (i21 < i20) {
                        int i22 = (i21 < inlineMargin2 ? 1 : 0) + cellWidth;
                        arrayList.add(calculateInlineFrame(i19, i10, i22, cellHeight + i16));
                        i19 += i22;
                        i21++;
                        i15++;
                    }
                } else {
                    int i23 = i13 - divideByTwo;
                    i8 = i14;
                    i10 = i13;
                    i9 = inlineMargin;
                    ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames(i4 + i, (i5 + i2) - i23, i18, i19 - divideByTwo, i23));
                }
                i13 = i10 + cellHeight + i16;
                i14 = i8 + 1;
                inlineMargin = i9;
            }
        } else {
            int inlineMargin3 = (i - (columnCount * cellWidth)) + getInlineMargin();
            int i24 = i4 + divideByTwo;
            int i25 = 0;
            int i26 = 0;
            while (i25 < columnCount) {
                int i27 = i25 < inlineMargin3 ? 1 : 0;
                int i28 = columnCount - 1;
                int i29 = i25 == i28 ? i11 - i26 : rowCount;
                int i30 = i5 + divideByTwo;
                if (i25 != i28 || columnCount <= i12) {
                    i6 = i25;
                    i7 = i24;
                    int inlineMargin4 = (i2 - (i29 * cellHeight)) + getInlineMargin();
                    int i31 = 0;
                    while (i31 < i29) {
                        int i32 = (i31 < inlineMargin4 ? 1 : 0) + cellHeight;
                        arrayList.add(calculateInlineFrame(i7, i30, cellWidth + i27, i32));
                        i30 += i32;
                        i31++;
                        i26++;
                    }
                } else {
                    int i33 = i24 - divideByTwo;
                    i6 = i25;
                    i7 = i24;
                    ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames((i4 + i) - i33, i5 + i2, i29, i33, i30 - divideByTwo));
                }
                i24 = i7 + cellWidth + i27;
                i25 = i6 + 1;
                i12 = 1;
            }
        }
        return (LayoutFrame[]) arrayList.toArray(new LayoutFrame[0]);
    }

    private static LayoutFrame calculateInlineOverflowFrame(int i, int i2, Size size, LayoutDirection layoutDirection) {
        if (Global.equals(layoutDirection, LayoutDirection.Horizontal)) {
            if (size.getHeight() == 0) {
                return new LayoutFrame(i, 0, 0, i2);
            }
            return new LayoutFrame(i, 0, (size.getWidth() * i2) / size.getHeight(), i2);
        } else if (size.getWidth() == 0) {
            return new LayoutFrame(0, i, i2, 0);
        } else {
            return new LayoutFrame(0, i, i2, (size.getHeight() * i2) / size.getWidth());
        }
    }

    private LayoutFrame[] calculateInlineOverflowFrames(int i, int i2, int i3, int i4, Size size, Size[] sizeArr) {
        ArrayList arrayList = new ArrayList();
        if (Global.equals(getDirection(), LayoutDirection.Horizontal)) {
            if (size != null) {
                LayoutFrame calculateInlineOverflowFrame = calculateInlineOverflowFrame(i3, i2, size, getDirection());
                i3 += calculateInlineOverflowFrame.getWidth();
                arrayList.add(calculateInlineOverflowFrame);
            }
            if (sizeArr != null) {
                for (Size calculateInlineOverflowFrame2 : sizeArr) {
                    LayoutFrame calculateInlineOverflowFrame3 = calculateInlineOverflowFrame(i3, i2, calculateInlineOverflowFrame2, getDirection());
                    i3 += calculateInlineOverflowFrame3.getWidth();
                    arrayList.add(calculateInlineOverflowFrame3);
                }
            }
        } else {
            if (size != null) {
                LayoutFrame calculateInlineOverflowFrame4 = calculateInlineOverflowFrame(i4, i, size, getDirection());
                i4 += calculateInlineOverflowFrame4.getHeight();
                arrayList.add(calculateInlineOverflowFrame4);
            }
            if (sizeArr != null) {
                for (Size calculateInlineOverflowFrame5 : sizeArr) {
                    LayoutFrame calculateInlineOverflowFrame6 = calculateInlineOverflowFrame(i4, i, calculateInlineOverflowFrame5, getDirection());
                    i4 += calculateInlineOverflowFrame6.getHeight();
                    arrayList.add(calculateInlineOverflowFrame6);
                }
            }
        }
        return (LayoutFrame[]) arrayList.toArray(new LayoutFrame[0]);
    }

    public Layout calculateLayout(int i, int i2, boolean z, int i3, LayoutOrigin layoutOrigin, Size size, Size[] sizeArr) {
        Layout layout;
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (Global.equals(getMode(), LayoutMode.FloatLocal)) {
            layout = getFloatLocalLayout(i, i2, z, i3, layoutOrigin);
        } else if (Global.equals(getMode(), LayoutMode.FloatRemote)) {
            layout = getFloatRemoteLayout(i, i2, z, i3, layoutOrigin);
        } else if (Global.equals(getMode(), LayoutMode.Block)) {
            layout = getBlockLayout(i, i2, z, i3, layoutOrigin);
        } else if (Global.equals(getMode(), LayoutMode.Inline)) {
            layout = getInlineLayout(i, i2, z, i3, layoutOrigin);
        } else if (Global.equals(getMode(), LayoutMode.InlineOverflow)) {
            layout = getInlineOverflowLayout(i, i2, layoutOrigin, size, sizeArr);
        } else {
            throw new RuntimeException(new Exception("Unrecognized layout mode."));
        }
        transformFrame(layout.getLocalFrame(), layoutOrigin, i, i2);
        for (LayoutFrame transformFrame : layout.getRemoteFrames()) {
            transformFrame(transformFrame, layoutOrigin, i, i2);
        }
        return layout;
    }

    public Layout calculateLayout(int i, int i2, int i3, LayoutOrigin layoutOrigin) {
        return calculateLayout(i, i2, true, i3, layoutOrigin, (Size) null, (Size[]) null);
    }

    private static LayoutTable calculateTable(int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        double d = (double) i3;
        double d2 = 1.0d;
        double d3 = 0.0d;
        double d4 = d;
        double d5 = 1.0d;
        double d6 = 1.0d;
        while (d4 >= d2) {
            double ceil = MathAssistant.ceil(d / d4);
            double d7 = ((double) i4) / d4;
            double d8 = d;
            double d9 = ((double) i5) / ceil;
            if (d7 >= d9) {
                d7 = d9;
            }
            if (d7 >= d3) {
                d3 = d7;
                d5 = d4;
                d6 = ceil;
            }
            d4 -= 1.0d;
            d2 = 1.0d;
            d = d8;
        }
        return new LayoutTable((int) d5, (int) d6, (int) MathAssistant.floor(((double) i4) / d5), (int) MathAssistant.floor(((double) i5) / d6));
    }

    public void copyToPreset(LayoutPreset layoutPreset) {
        layoutPreset.setMode(getMode());
        layoutPreset.setDirection(getDirection());
        layoutPreset.setAlignment(getAlignment());
        layoutPreset.setFloatWidth(0);
        layoutPreset.setFloatHeight(0);
        layoutPreset.setFloatMarginX(0);
        layoutPreset.setFloatMarginY(0);
        layoutPreset.setFloatWidthPercent(0.0d);
        layoutPreset.setFloatHeightPercent(0.0d);
        layoutPreset.setFloatMarginXPercent(0.0d);
        layoutPreset.setFloatMarginYPercent(0.0d);
        layoutPreset.setBlockWidth(0);
        layoutPreset.setBlockHeight(0);
        layoutPreset.setBlockMarginX(0);
        layoutPreset.setBlockMarginY(0);
        layoutPreset.setBlockWidthPercent(0.0d);
        layoutPreset.setBlockHeightPercent(0.0d);
        layoutPreset.setBlockMarginXPercent(0.0d);
        layoutPreset.setBlockMarginYPercent(0.0d);
        layoutPreset.setInlineMargin(0);
        if (getFloatWidth() > 0) {
            layoutPreset.setFloatWidth(getFloatWidth());
        }
        if (getFloatHeight() > 0) {
            layoutPreset.setFloatHeight(getFloatHeight());
        }
        if (getFloatMarginX() > 0) {
            layoutPreset.setFloatMarginX(getFloatMarginX());
        }
        if (getFloatMarginY() > 0) {
            layoutPreset.setFloatMarginY(getFloatMarginY());
        }
        if (getFloatWidthPercent() > 0.0d) {
            layoutPreset.setFloatWidthPercent(getFloatWidthPercent());
        }
        if (getFloatHeightPercent() > 0.0d) {
            layoutPreset.setFloatHeightPercent(getFloatHeightPercent());
        }
        if (getFloatMarginXPercent() > 0.0d) {
            layoutPreset.setFloatMarginXPercent(getFloatMarginXPercent());
        }
        if (getFloatMarginYPercent() > 0.0d) {
            layoutPreset.setFloatMarginYPercent(getFloatMarginYPercent());
        }
        if (getBlockWidth() > 0) {
            layoutPreset.setBlockWidth(getBlockWidth());
        }
        if (getBlockHeight() > 0) {
            layoutPreset.setBlockHeight(getBlockHeight());
        }
        if (getBlockMarginX() > 0) {
            layoutPreset.setBlockMarginX(getBlockMarginX());
        }
        if (getBlockMarginY() > 0) {
            layoutPreset.setBlockMarginY(getBlockMarginY());
        }
        if (getBlockWidthPercent() > 0.0d) {
            layoutPreset.setBlockWidthPercent(getBlockWidthPercent());
        }
        if (getBlockHeightPercent() > 0.0d) {
            layoutPreset.setBlockHeightPercent(getBlockHeightPercent());
        }
        if (getBlockMarginXPercent() > 0.0d) {
            layoutPreset.setBlockMarginXPercent(getBlockMarginXPercent());
        }
        if (getBlockMarginYPercent() > 0.0d) {
            layoutPreset.setBlockMarginYPercent(getBlockMarginYPercent());
        }
        if (getInlineMargin() > 0) {
            layoutPreset.setInlineMargin(getInlineMargin());
        }
    }

    private static int divideByTwo(int i) {
        return (int) MathAssistant.floor(((double) i) / 2.0d);
    }

    public LayoutAlignment getAlignment() {
        return this._alignment;
    }

    public int getBlockHeight() {
        return this.__blockHeight;
    }

    public double getBlockHeightPercent() {
        return this.__blockHeightPercent;
    }

    private Layout getBlockLayout(int i, int i2, boolean z, int i3, LayoutOrigin layoutOrigin) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = i;
        int i14 = i2;
        IntegerHolder integerHolder = new IntegerHolder(0);
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        LayoutFrame calculateBlockFrame = calculateBlockFrame(i13, i14, integerHolder, integerHolder2);
        int value = integerHolder.getValue();
        int value2 = integerHolder2.getValue();
        if (!z) {
            calculateBlockFrame = null;
        }
        LayoutFrame layoutFrame = calculateBlockFrame;
        ArrayList arrayList = new ArrayList();
        if (Global.equals(getAlignment(), LayoutAlignment.Center)) {
            int divideByTwo = divideByTwo(i3);
            int i15 = i3 - divideByTwo;
            if (Global.equals(getDirection(), LayoutDirection.Vertical)) {
                if (layoutFrame == null) {
                    i12 = 0;
                } else {
                    i12 = layoutFrame.getWidth() + value + value;
                }
                i13 = divideByTwo(i13 - i12);
                i9 = i12 + i13;
                i10 = 0;
            } else {
                if (layoutFrame == null) {
                    i11 = 0;
                } else {
                    i11 = layoutFrame.getHeight() + value2 + value2;
                }
                i14 = divideByTwo(i14 - i11);
                i10 = i11 + i14;
                i9 = 0;
            }
            if (divideByTwo > 0) {
                ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames(i13, i4, divideByTwo, 0, 0));
            }
            if (i15 > 0) {
                ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames(i13, i4, i15, i9, i10));
            }
        } else {
            if (layoutFrame == null) {
                i5 = 0;
            } else {
                i5 = layoutFrame.getWidth() + value;
            }
            if (layoutFrame == null) {
                i6 = 0;
            } else {
                i6 = layoutFrame.getHeight() + value2;
            }
            if (Global.equals(getAlignment(), LayoutAlignment.Top)) {
                i4 = i14 - i6;
                i7 = i6;
            } else {
                if (Global.equals(getAlignment(), LayoutAlignment.Bottom)) {
                    i14 -= i6;
                } else {
                    if (Global.equals(getAlignment(), LayoutAlignment.Left)) {
                        i13 -= i5;
                        i8 = i5;
                    } else if (Global.equals(getAlignment(), LayoutAlignment.Right)) {
                        i13 -= i5;
                    } else if (Global.equals(getDirection(), LayoutDirection.Vertical)) {
                        i13 -= i5;
                        i8 = (Global.equals(getAlignment(), LayoutAlignment.TopLeft) || Global.equals(getAlignment(), LayoutAlignment.BottomLeft)) ? i5 : 0;
                    } else {
                        i4 = i14 - i6;
                        i7 = (Global.equals(getAlignment(), LayoutAlignment.TopLeft) || Global.equals(getAlignment(), LayoutAlignment.TopRight)) ? i6 : 0;
                    }
                    i7 = 0;
                    ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames(i13, i4, i3, i8, i7));
                }
                i8 = 0;
                i7 = 0;
                ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames(i13, i4, i3, i8, i7));
            }
            i8 = 0;
            ArrayListExtensions.addRange(arrayList, (T[]) calculateInlineFrames(i13, i4, i3, i8, i7));
        }
        Layout layout = new Layout();
        layout.setOrigin(layoutOrigin);
        layout.setWidth(i13);
        layout.setHeight(i4);
        layout.setLocalFrame(layoutFrame);
        layout.setRemoteFrames((LayoutFrame[]) arrayList.toArray(new LayoutFrame[0]));
        return layout;
    }

    public int getBlockMarginX() {
        return this.__blockMarginX;
    }

    public double getBlockMarginXPercent() {
        return this.__blockMarginXPercent;
    }

    public int getBlockMarginY() {
        return this.__blockMarginY;
    }

    public double getBlockMarginYPercent() {
        return this.__blockMarginYPercent;
    }

    public int getBlockWidth() {
        return this.__blockWidth;
    }

    public double getBlockWidthPercent() {
        return this.__blockWidthPercent;
    }

    private int[] getBottomRowIndexes(LayoutFrame[] layoutFrameArr) {
        int yMax = getYMax(layoutFrameArr);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i2++) {
            if (layoutFrameArr[i2].getY() == yMax) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i4++) {
            if (layoutFrameArr[i4].getY() == yMax) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }

    private int[] getCenterColumnIndexes(LayoutFrame[] layoutFrameArr) {
        int xMid = getXMid(layoutFrameArr);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i2++) {
            if (layoutFrameArr[i2].getX() == xMid) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i4++) {
            if (layoutFrameArr[i4].getX() == xMid) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }

    private int[] getCenterRowIndexes(LayoutFrame[] layoutFrameArr) {
        int yMid = getYMid(layoutFrameArr);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i2++) {
            if (layoutFrameArr[i2].getY() == yMid) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i4++) {
            if (layoutFrameArr[i4].getY() == yMid) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }

    public LayoutDirection getDirection() {
        return this._direction;
    }

    public static LayoutPreset getFacetime() {
        LayoutPreset layoutPreset = new LayoutPreset();
        layoutPreset.setMode(LayoutMode.FloatLocal);
        layoutPreset.setAlignment(LayoutAlignment.BottomRight);
        layoutPreset.setFloatMarginX(10);
        layoutPreset.setFloatMarginY(10);
        layoutPreset.setFloatWidthPercent(0.25d);
        layoutPreset.setFloatHeightPercent(0.25d);
        layoutPreset.setInlineMargin(0);
        return layoutPreset;
    }

    public int getFloatHeight() {
        return this.__floatHeight;
    }

    public double getFloatHeightPercent() {
        return this.__floatHeightPercent;
    }

    private Layout getFloatLocalLayout(int i, int i2, boolean z, int i3, LayoutOrigin layoutOrigin) {
        if (i3 == 0) {
            return getSingleLayout(i, i2, z);
        }
        Layout layout = new Layout();
        layout.setOrigin(layoutOrigin);
        layout.setWidth(i);
        layout.setHeight(i2);
        layout.setLocalFrame(z ? calculateFloatFrame(i, i2) : null);
        layout.setRemoteFrames(calculateInlineFrames(i, i2, i3, 0, 0));
        return layout;
    }

    public int getFloatMarginX() {
        return this.__floatMarginX;
    }

    public double getFloatMarginXPercent() {
        return this.__floatMarginXPercent;
    }

    public int getFloatMarginY() {
        return this.__floatMarginY;
    }

    public double getFloatMarginYPercent() {
        return this.__floatMarginYPercent;
    }

    private Layout getFloatRemoteLayout(int i, int i2, boolean z, int i3, LayoutOrigin layoutOrigin) {
        Layout layout = new Layout();
        layout.setOrigin(layoutOrigin);
        layout.setWidth(i);
        layout.setHeight(i2);
        layout.setLocalFrame(z ? calculateFillFrame(i, i2) : null);
        layout.setRemoteFrames(calculateFloatFrames(i, i2, i3));
        return layout;
    }

    public int getFloatWidth() {
        return this.__floatWidth;
    }

    public double getFloatWidthPercent() {
        return this.__floatWidthPercent;
    }

    public static LayoutPreset getGoogleHangouts() {
        if (Platform.getInstance().getIsMobile()) {
            LayoutPreset layoutPreset = new LayoutPreset();
            layoutPreset.setMode(LayoutMode.FloatRemote);
            layoutPreset.setAlignment(LayoutAlignment.BottomRight);
            layoutPreset.setFloatMarginX(0);
            layoutPreset.setFloatMarginY(10);
            layoutPreset.setFloatWidthPercent(0.25d);
            layoutPreset.setFloatHeightPercent(0.25d);
            layoutPreset.setInlineMargin(5);
            return layoutPreset;
        }
        LayoutPreset layoutPreset2 = new LayoutPreset();
        layoutPreset2.setMode(LayoutMode.Block);
        layoutPreset2.setAlignment(LayoutAlignment.Top);
        layoutPreset2.setBlockWidthPercent(0.666666666666667d);
        layoutPreset2.setBlockHeightPercent(0.666666666666667d);
        layoutPreset2.setInlineMargin(0);
        return layoutPreset2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x014b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Layout getInlineLayout(int r7, int r8, boolean r9, int r10, fm.liveswitch.LayoutOrigin r11) {
        /*
            r6 = this;
            int r3 = r10 + r9
            r4 = 0
            r5 = 0
            r0 = r6
            r1 = r7
            r2 = r8
            fm.liveswitch.LayoutFrame[] r10 = r0.calculateInlineFrames(r1, r2, r3, r4, r5)
            r0 = 0
            r1 = -1
            if (r9 == 0) goto L_0x0130
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.TopLeft
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x001d
            goto L_0x0131
        L_0x001d:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.Top
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x0039
            int[] r9 = r6.getTopRowIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = divideByTwo(r0)
            r0 = r9[r0]
            goto L_0x0131
        L_0x0039:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.TopRight
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x0067
            fm.liveswitch.LayoutDirection r9 = r6.getDirection()
            fm.liveswitch.LayoutDirection r2 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x005f
            int[] r9 = r6.getTopRowIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = r0 + -1
            r0 = r9[r0]
            goto L_0x0131
        L_0x005f:
            int[] r9 = r6.getRightColumnIndexes(r10)
            r0 = r9[r0]
            goto L_0x0131
        L_0x0067:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.Left
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x0083
            int[] r9 = r6.getLeftColumnIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = divideByTwo(r0)
            r0 = r9[r0]
            goto L_0x0131
        L_0x0083:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.Center
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x00bb
            fm.liveswitch.LayoutDirection r9 = r6.getDirection()
            fm.liveswitch.LayoutDirection r0 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r9 = fm.liveswitch.Global.equals(r9, r0)
            if (r9 == 0) goto L_0x00ab
            int[] r9 = r6.getCenterRowIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = divideByTwo(r0)
            r0 = r9[r0]
            goto L_0x0131
        L_0x00ab:
            int[] r9 = r6.getCenterColumnIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = divideByTwo(r0)
            r0 = r9[r0]
            goto L_0x0131
        L_0x00bb:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.Right
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x00d6
            int[] r9 = r6.getRightColumnIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = divideByTwo(r0)
            r0 = r9[r0]
            goto L_0x0131
        L_0x00d6:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r2 = fm.liveswitch.LayoutAlignment.BottomLeft
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x0102
            fm.liveswitch.LayoutDirection r9 = r6.getDirection()
            fm.liveswitch.LayoutDirection r2 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)
            if (r9 == 0) goto L_0x00f5
            int[] r9 = r6.getBottomRowIndexes(r10)
            r0 = r9[r0]
            goto L_0x0131
        L_0x00f5:
            int[] r9 = r6.getLeftColumnIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = r0 + -1
            r0 = r9[r0]
            goto L_0x0131
        L_0x0102:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r0 = fm.liveswitch.LayoutAlignment.Bottom
            boolean r9 = fm.liveswitch.Global.equals(r9, r0)
            if (r9 == 0) goto L_0x011d
            int[] r9 = r6.getBottomRowIndexes(r10)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((int[]) r9)
            int r0 = divideByTwo(r0)
            r0 = r9[r0]
            goto L_0x0131
        L_0x011d:
            fm.liveswitch.LayoutAlignment r9 = r6.getAlignment()
            fm.liveswitch.LayoutAlignment r0 = fm.liveswitch.LayoutAlignment.BottomRight
            boolean r9 = fm.liveswitch.Global.equals(r9, r0)
            if (r9 == 0) goto L_0x0130
            int r9 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r10)
            int r0 = r9 + -1
            goto L_0x0131
        L_0x0130:
            r0 = -1
        L_0x0131:
            fm.liveswitch.Layout r9 = new fm.liveswitch.Layout
            r9.<init>()
            r9.setOrigin(r11)
            r9.setWidth(r7)
            r9.setHeight(r8)
            if (r0 != r1) goto L_0x0143
            r7 = 0
            goto L_0x0145
        L_0x0143:
            r7 = r10[r0]
        L_0x0145:
            r9.setLocalFrame(r7)
            if (r0 != r1) goto L_0x014b
            goto L_0x014f
        L_0x014b:
            fm.liveswitch.LayoutFrame[] r10 = spliceLayoutFrame(r10, r0)
        L_0x014f:
            r9.setRemoteFrames(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.LayoutPreset.getInlineLayout(int, int, boolean, int, fm.liveswitch.LayoutOrigin):fm.liveswitch.Layout");
    }

    public int getInlineMargin() {
        return this._inlineMargin;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Layout getInlineOverflowLayout(int r8, int r9, fm.liveswitch.LayoutOrigin r10, fm.liveswitch.Size r11, fm.liveswitch.Size[] r12) {
        /*
            r7 = this;
            r3 = 0
            r4 = 0
            r0 = r7
            r1 = r8
            r2 = r9
            r5 = r11
            r6 = r12
            fm.liveswitch.LayoutFrame[] r12 = r0.calculateInlineOverflowFrames(r1, r2, r3, r4, r5, r6)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)
            r1 = 0
            r2 = 0
            if (r0 != 0) goto L_0x002a
            fm.liveswitch.Layout r11 = new fm.liveswitch.Layout
            r11.<init>()
            r11.setOrigin(r10)
            r11.setWidth(r8)
            r11.setHeight(r9)
            r11.setLocalFrame(r1)
            fm.liveswitch.LayoutFrame[] r8 = new fm.liveswitch.LayoutFrame[r2]
            r11.setRemoteFrames(r8)
            return r11
        L_0x002a:
            r0 = -1
            if (r11 == 0) goto L_0x014f
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.TopLeft
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x003c
            r11 = 0
            goto L_0x0150
        L_0x003c:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.Top
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x0058
            int[] r11 = r7.getTopRowIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = divideByTwo(r3)
            r11 = r11[r3]
            goto L_0x0150
        L_0x0058:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.TopRight
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x0086
            fm.liveswitch.LayoutDirection r11 = r7.getDirection()
            fm.liveswitch.LayoutDirection r3 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x007e
            int[] r11 = r7.getTopRowIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = r3 + -1
            r11 = r11[r3]
            goto L_0x0150
        L_0x007e:
            int[] r11 = r7.getRightColumnIndexes(r12)
            r11 = r11[r2]
            goto L_0x0150
        L_0x0086:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.Left
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x00a2
            int[] r11 = r7.getLeftColumnIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = divideByTwo(r3)
            r11 = r11[r3]
            goto L_0x0150
        L_0x00a2:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.Center
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x00da
            fm.liveswitch.LayoutDirection r11 = r7.getDirection()
            fm.liveswitch.LayoutDirection r3 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x00ca
            int[] r11 = r7.getCenterRowIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = divideByTwo(r3)
            r11 = r11[r3]
            goto L_0x0150
        L_0x00ca:
            int[] r11 = r7.getCenterColumnIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = divideByTwo(r3)
            r11 = r11[r3]
            goto L_0x0150
        L_0x00da:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.Right
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x00f5
            int[] r11 = r7.getRightColumnIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = divideByTwo(r3)
            r11 = r11[r3]
            goto L_0x0150
        L_0x00f5:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.BottomLeft
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x0121
            fm.liveswitch.LayoutDirection r11 = r7.getDirection()
            fm.liveswitch.LayoutDirection r3 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x0114
            int[] r11 = r7.getBottomRowIndexes(r12)
            r11 = r11[r2]
            goto L_0x0150
        L_0x0114:
            int[] r11 = r7.getLeftColumnIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = r3 + -1
            r11 = r11[r3]
            goto L_0x0150
        L_0x0121:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.Bottom
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x013c
            int[] r11 = r7.getBottomRowIndexes(r12)
            int r3 = fm.liveswitch.ArrayExtensions.getLength((int[]) r11)
            int r3 = divideByTwo(r3)
            r11 = r11[r3]
            goto L_0x0150
        L_0x013c:
            fm.liveswitch.LayoutAlignment r11 = r7.getAlignment()
            fm.liveswitch.LayoutAlignment r3 = fm.liveswitch.LayoutAlignment.BottomRight
            boolean r11 = fm.liveswitch.Global.equals(r11, r3)
            if (r11 == 0) goto L_0x014f
            int r11 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r12)
            int r11 = r11 + -1
            goto L_0x0150
        L_0x014f:
            r11 = -1
        L_0x0150:
            fm.liveswitch.LayoutDirection r3 = r7.getDirection()
            fm.liveswitch.LayoutDirection r4 = fm.liveswitch.LayoutDirection.Horizontal
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)
            if (r3 == 0) goto L_0x018b
            int r8 = r12.length
            r3 = 0
        L_0x015e:
            if (r2 >= r8) goto L_0x016a
            r4 = r12[r2]
            int r4 = r4.getWidth()
            int r3 = r3 + r4
            int r2 = r2 + 1
            goto L_0x015e
        L_0x016a:
            fm.liveswitch.Layout r8 = new fm.liveswitch.Layout
            r8.<init>()
            r8.setOrigin(r10)
            r8.setWidth(r3)
            r8.setHeight(r9)
            if (r11 != r0) goto L_0x017b
            goto L_0x017d
        L_0x017b:
            r1 = r12[r11]
        L_0x017d:
            r8.setLocalFrame(r1)
            if (r11 != r0) goto L_0x0183
            goto L_0x0187
        L_0x0183:
            fm.liveswitch.LayoutFrame[] r12 = spliceLayoutFrame(r12, r11)
        L_0x0187:
            r8.setRemoteFrames(r12)
            return r8
        L_0x018b:
            int r9 = r12.length
            r3 = 0
        L_0x018d:
            if (r2 >= r9) goto L_0x0199
            r4 = r12[r2]
            int r4 = r4.getHeight()
            int r3 = r3 + r4
            int r2 = r2 + 1
            goto L_0x018d
        L_0x0199:
            fm.liveswitch.Layout r9 = new fm.liveswitch.Layout
            r9.<init>()
            r9.setOrigin(r10)
            r9.setWidth(r8)
            r9.setHeight(r3)
            if (r11 != r0) goto L_0x01aa
            goto L_0x01ac
        L_0x01aa:
            r1 = r12[r11]
        L_0x01ac:
            r9.setLocalFrame(r1)
            if (r11 != r0) goto L_0x01b2
            goto L_0x01b6
        L_0x01b2:
            fm.liveswitch.LayoutFrame[] r12 = spliceLayoutFrame(r12, r11)
        L_0x01b6:
            r9.setRemoteFrames(r12)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.LayoutPreset.getInlineOverflowLayout(int, int, fm.liveswitch.LayoutOrigin, fm.liveswitch.Size, fm.liveswitch.Size[]):fm.liveswitch.Layout");
    }

    private int[] getLeftColumnIndexes(LayoutFrame[] layoutFrameArr) {
        int xMin = getXMin(layoutFrameArr);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i2++) {
            if (layoutFrameArr[i2].getX() == xMin) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i4++) {
            if (layoutFrameArr[i4].getX() == xMin) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }

    public LayoutMode getMode() {
        return this._mode;
    }

    private int[] getRightColumnIndexes(LayoutFrame[] layoutFrameArr) {
        int xMax = getXMax(layoutFrameArr);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i2++) {
            if (layoutFrameArr[i2].getX() == xMax) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i4++) {
            if (layoutFrameArr[i4].getX() == xMax) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }

    private static Layout getSingleLayout(int i, int i2, boolean z) {
        Layout layout = new Layout();
        layout.setLocalFrame(z ? new LayoutFrame(0, 0, i, i2) : null);
        return layout;
    }

    public static LayoutPreset getSkype() {
        if (Platform.getInstance().getIsMobile()) {
            return getFacetime();
        }
        LayoutPreset layoutPreset = new LayoutPreset();
        layoutPreset.setMode(LayoutMode.Block);
        layoutPreset.setDirection(LayoutDirection.Horizontal);
        layoutPreset.setAlignment(LayoutAlignment.Bottom);
        layoutPreset.setBlockMarginX(10);
        layoutPreset.setBlockMarginY(10);
        layoutPreset.setBlockWidthPercent(0.333333333333333d);
        layoutPreset.setBlockHeightPercent(0.333333333333333d);
        layoutPreset.setInlineMargin(10);
        return layoutPreset;
    }

    private int[] getTopRowIndexes(LayoutFrame[] layoutFrameArr) {
        int yMin = getYMin(layoutFrameArr);
        int i = 0;
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i2++) {
            if (layoutFrameArr[i2].getY() == yMin) {
                i++;
            }
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < ArrayExtensions.getLength((Object[]) layoutFrameArr); i4++) {
            if (layoutFrameArr[i4].getY() == yMin) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }

    private static int getXMax(LayoutFrame[] layoutFrameArr) {
        if (ArrayExtensions.getLength((Object[]) layoutFrameArr) == 0) {
            return layoutFrameArr[0].getX();
        }
        int x = layoutFrameArr[0].getX();
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) layoutFrameArr); i++) {
            LayoutFrame layoutFrame = layoutFrameArr[i];
            if (layoutFrame.getX() > x) {
                x = layoutFrame.getX();
            }
        }
        return x;
    }

    private static int getXMid(LayoutFrame[] layoutFrameArr) {
        int xMin = getXMin(layoutFrameArr);
        int xMax = getXMax(layoutFrameArr);
        if (xMin == xMax) {
            return xMin;
        }
        int divideByTwo = divideByTwo(xMin + xMax);
        int x = layoutFrameArr[0].getX();
        int abs = MathAssistant.abs(divideByTwo - x);
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) layoutFrameArr); i++) {
            LayoutFrame layoutFrame = layoutFrameArr[i];
            int abs2 = MathAssistant.abs(divideByTwo - layoutFrame.getX());
            if (abs2 < abs) {
                x = layoutFrame.getX();
                abs = abs2;
            }
        }
        return x;
    }

    private static int getXMin(LayoutFrame[] layoutFrameArr) {
        if (ArrayExtensions.getLength((Object[]) layoutFrameArr) == 0) {
            return layoutFrameArr[0].getX();
        }
        int x = layoutFrameArr[0].getX();
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) layoutFrameArr); i++) {
            LayoutFrame layoutFrame = layoutFrameArr[i];
            if (layoutFrame.getX() < x) {
                x = layoutFrame.getX();
            }
        }
        return x;
    }

    private static int getYMax(LayoutFrame[] layoutFrameArr) {
        if (ArrayExtensions.getLength((Object[]) layoutFrameArr) == 0) {
            return layoutFrameArr[0].getY();
        }
        int y = layoutFrameArr[0].getY();
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) layoutFrameArr); i++) {
            LayoutFrame layoutFrame = layoutFrameArr[i];
            if (layoutFrame.getY() > y) {
                y = layoutFrame.getY();
            }
        }
        return y;
    }

    private static int getYMid(LayoutFrame[] layoutFrameArr) {
        int yMin = getYMin(layoutFrameArr);
        int yMax = getYMax(layoutFrameArr);
        if (yMin == yMax) {
            return yMin;
        }
        int divideByTwo = divideByTwo(yMin + yMax);
        int y = layoutFrameArr[0].getY();
        int abs = MathAssistant.abs(divideByTwo - y);
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) layoutFrameArr); i++) {
            LayoutFrame layoutFrame = layoutFrameArr[i];
            int abs2 = MathAssistant.abs(divideByTwo - layoutFrame.getY());
            if (abs2 < abs) {
                y = layoutFrame.getY();
                abs = abs2;
            }
        }
        return y;
    }

    private static int getYMin(LayoutFrame[] layoutFrameArr) {
        if (ArrayExtensions.getLength((Object[]) layoutFrameArr) == 0) {
            return layoutFrameArr[0].getY();
        }
        int y = layoutFrameArr[0].getY();
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) layoutFrameArr); i++) {
            LayoutFrame layoutFrame = layoutFrameArr[i];
            if (layoutFrame.getY() < y) {
                y = layoutFrame.getY();
            }
        }
        return y;
    }

    public LayoutPreset() {
        setMode(LayoutMode.FloatLocal);
        setDirection(LayoutDirection.Horizontal);
        setAlignment(LayoutAlignment.BottomRight);
    }

    private static LayoutFrame[] mergeLayoutFrames(LayoutFrame[] layoutFrameArr, LayoutFrame[] layoutFrameArr2) {
        int length = ArrayExtensions.getLength((Object[]) layoutFrameArr);
        int length2 = ArrayExtensions.getLength((Object[]) layoutFrameArr2);
        LayoutFrame[] layoutFrameArr3 = new LayoutFrame[(length + length2)];
        for (int i = 0; i < length; i++) {
            layoutFrameArr3[i] = layoutFrameArr[i];
        }
        for (int i2 = 0; i2 < length2; i2++) {
            layoutFrameArr3[i2 + length] = layoutFrameArr2[i2];
        }
        return layoutFrameArr3;
    }

    public void setAlignment(LayoutAlignment layoutAlignment) {
        this._alignment = layoutAlignment;
    }

    public void setBlockHeight(int i) {
        this.__blockHeight = i;
        this.__blockHeightPercent = 0.0d;
    }

    public void setBlockHeightPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__blockHeightPercent = d;
        this.__blockHeight = 0;
    }

    public void setBlockMarginX(int i) {
        this.__blockMarginX = i;
        this.__blockMarginXPercent = 0.0d;
    }

    public void setBlockMarginXPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__blockMarginXPercent = d;
        this.__blockMarginX = 0;
    }

    public void setBlockMarginY(int i) {
        this.__blockMarginY = i;
        this.__blockMarginYPercent = 0.0d;
    }

    public void setBlockMarginYPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__blockMarginYPercent = d;
        this.__blockMarginY = 0;
    }

    public void setBlockWidth(int i) {
        this.__blockWidth = i;
        this.__blockWidthPercent = 0.0d;
    }

    public void setBlockWidthPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__blockWidthPercent = d;
        this.__blockWidth = 0;
    }

    public void setDirection(LayoutDirection layoutDirection) {
        this._direction = layoutDirection;
    }

    public void setFloatHeight(int i) {
        this.__floatHeight = i;
        this.__floatHeightPercent = 0.0d;
    }

    public void setFloatHeightPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__floatHeightPercent = d;
        this.__floatHeight = 0;
    }

    public void setFloatMarginX(int i) {
        this.__floatMarginX = i;
        this.__floatMarginXPercent = 0.0d;
    }

    public void setFloatMarginXPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__floatMarginXPercent = d;
        this.__floatMarginX = 0;
    }

    public void setFloatMarginY(int i) {
        this.__floatMarginY = i;
        this.__floatMarginYPercent = 0.0d;
    }

    public void setFloatMarginYPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__floatMarginYPercent = d;
        this.__floatMarginY = 0;
    }

    public void setFloatWidth(int i) {
        this.__floatWidth = i;
        this.__floatWidthPercent = 0.0d;
    }

    public void setFloatWidthPercent(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.__floatWidthPercent = d;
        this.__floatWidth = 0;
    }

    public void setInlineMargin(int i) {
        this._inlineMargin = i;
    }

    public void setMode(LayoutMode layoutMode) {
        this._mode = layoutMode;
    }

    private static LayoutFrame[] spliceLayoutFrame(LayoutFrame[] layoutFrameArr, int i) {
        int i2 = i + 1;
        return mergeLayoutFrames(takeLayoutFrames(layoutFrameArr, 0, i), takeLayoutFrames(layoutFrameArr, i2, ArrayExtensions.getLength((Object[]) layoutFrameArr) - i2));
    }

    private static LayoutFrame[] takeLayoutFrames(LayoutFrame[] layoutFrameArr, int i, int i2) {
        LayoutFrame[] layoutFrameArr2 = new LayoutFrame[i2];
        for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) layoutFrameArr2); i3++) {
            layoutFrameArr2[i3] = layoutFrameArr[i + i3];
        }
        return layoutFrameArr2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r4 == fm.liveswitch.LayoutOrigin.BottomLeft) goto L_0x0013;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void transformFrame(fm.liveswitch.LayoutFrame r3, fm.liveswitch.LayoutOrigin r4, int r5, int r6) {
        /*
            fm.liveswitch.LayoutOrigin r0 = fm.liveswitch.LayoutOrigin.TopRight
            r1 = 0
            r2 = 1
            if (r4 != r0) goto L_0x0009
            r1 = 1
        L_0x0007:
            r2 = 0
            goto L_0x0013
        L_0x0009:
            fm.liveswitch.LayoutOrigin r0 = fm.liveswitch.LayoutOrigin.BottomRight
            if (r4 != r0) goto L_0x000f
            r1 = 1
            goto L_0x0013
        L_0x000f:
            fm.liveswitch.LayoutOrigin r0 = fm.liveswitch.LayoutOrigin.BottomLeft
            if (r4 != r0) goto L_0x0007
        L_0x0013:
            if (r1 == 0) goto L_0x0022
            int r4 = r3.getX()
            int r5 = r5 - r4
            int r4 = r3.getWidth()
            int r5 = r5 - r4
            r3.setX(r5)
        L_0x0022:
            if (r2 == 0) goto L_0x0031
            int r4 = r3.getY()
            int r6 = r6 - r4
            int r4 = r3.getHeight()
            int r6 = r6 - r4
            r3.setY(r6)
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.LayoutPreset.transformFrame(fm.liveswitch.LayoutFrame, fm.liveswitch.LayoutOrigin, int, int):void");
    }
}
