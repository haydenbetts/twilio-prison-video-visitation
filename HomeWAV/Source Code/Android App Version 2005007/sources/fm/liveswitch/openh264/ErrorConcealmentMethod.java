package fm.liveswitch.openh264;

public class ErrorConcealmentMethod {
    private static ErrorConcealmentMethod __disable = new ErrorConcealmentMethod(0);
    private static ErrorConcealmentMethod __frameCopy = new ErrorConcealmentMethod(1);
    private static ErrorConcealmentMethod __frameCopyCrossIdr = new ErrorConcealmentMethod(3);
    private static ErrorConcealmentMethod __sliceCopy = new ErrorConcealmentMethod(2);
    private static ErrorConcealmentMethod __sliceCopyCrossIdr = new ErrorConcealmentMethod(4);
    private static ErrorConcealmentMethod __sliceCopyCrossIdrFreezeResChange = new ErrorConcealmentMethod(5);
    private static ErrorConcealmentMethod __sliceMvCopyCrossIdr = new ErrorConcealmentMethod(6);
    private static ErrorConcealmentMethod __sliceMvCopyCrossIdrFreezeResChange = new ErrorConcealmentMethod(7);
    private int _value;

    private ErrorConcealmentMethod() {
    }

    private ErrorConcealmentMethod(int i) {
        setValue(i);
    }

    public static ErrorConcealmentMethod getDisable() {
        return __disable;
    }

    public static ErrorConcealmentMethod getFrameCopy() {
        return __frameCopy;
    }

    public static ErrorConcealmentMethod getFrameCopyCrossIdr() {
        return __frameCopyCrossIdr;
    }

    public static ErrorConcealmentMethod getSliceCopy() {
        return __sliceCopy;
    }

    public static ErrorConcealmentMethod getSliceCopyCrossIdr() {
        return __sliceCopyCrossIdr;
    }

    public static ErrorConcealmentMethod getSliceCopyCrossIdrFreezeResChange() {
        return __sliceCopyCrossIdrFreezeResChange;
    }

    public static ErrorConcealmentMethod getSliceMvCopyCrossIdr() {
        return __sliceMvCopyCrossIdr;
    }

    public static ErrorConcealmentMethod getSliceMvCopyCrossIdrFreezeResChange() {
        return __sliceMvCopyCrossIdrFreezeResChange;
    }

    public int getValue() {
        return this._value;
    }

    private void setValue(int i) {
        this._value = i;
    }
}
