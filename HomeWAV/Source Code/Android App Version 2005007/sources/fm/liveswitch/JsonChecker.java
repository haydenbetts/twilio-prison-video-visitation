package fm.liveswitch;

import java.math.BigDecimal;
import java.util.Stack;

class JsonChecker {
    private static int[] _ascii_class = {-1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 30, 8, 30, 30, 30, 30, 30, 30, 30, 30, 11, 7, 12, 13, 10, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 6, 30, 30, 30, 30, 30, 30, 28, 28, 28, 28, 29, 28, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 4, 9, 5, 30, 30, 30, 16, 17, 18, 19, 20, 21, 30, 30, 30, 30, 30, 22, 30, 23, 30, 30, 30, 24, 25, 26, 27, 30, 30, 30, 30, 30, 2, 30, 3, 30, 30};
    private static int[][] _state_transition_table = {new int[]{0, 0, -6, -1, -5, -1, -1, -1, -4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{1, 1, -1, -8, -1, -7, -1, -3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{2, 2, -1, -9, -1, -1, -1, -1, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{3, 3, -1, -1, -1, -1, -1, -1, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{4, 4, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{5, 5, -6, -1, -5, -1, -1, -1, 7, -1, -1, -1, 13, -1, 14, 15, -1, -1, -1, -1, -1, 23, -1, 27, -1, -1, 20, -1, -1, -1, -1}, new int[]{6, 6, -6, -1, -5, -7, -1, -1, 7, -1, -1, -1, 13, -1, 14, 15, -1, -1, -1, -1, -1, 23, -1, 27, -1, -1, 20, -1, -1, -1, -1}, new int[]{7, -1, 7, 7, 7, 7, 7, 7, -4, 8, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, 7, 7, 7, -1, -1, -1, -1, -1, -1, 7, -1, -1, -1, 7, -1, 7, 7, -1, 7, 9, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, 11, 11, 11, 11, 11, 11, 11, -1, -1, -1, -1, -1, -1, 11, 11, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, 12, 12, 12, 12, 12, 12, 12, -1, -1, -1, -1, -1, -1, 12, 12, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 7, 7, 7, 7, 7, 7, 7, 7, -1, -1, -1, -1, -1, -1, 7, 7, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{1, 1, -1, -8, -1, -7, -1, -3, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{1, 1, -1, -8, -1, -7, -1, -3, -1, -1, -1, -1, -1, 16, 15, 15, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1}, new int[]{1, 1, -1, -8, -1, -7, -1, -3, -1, -1, -1, -1, -1, -1, 16, 16, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, 18, -1, 19, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{1, 1, -1, -8, -1, -7, -1, -3, -1, -1, -1, -1, -1, -1, 19, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1}, new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1}};
    private int __depth;
    private long __offset;
    private Stack<JsonCheckerMode> __stack;
    private int __state;

    private void check(int i) {
        int i2;
        if (i < 0) {
            onError();
        }
        if (i >= 128) {
            i2 = 30;
        } else {
            i2 = _ascii_class[i];
            if (i2 <= -1) {
                onError();
            }
        }
        int i3 = _state_transition_table[this.__state][i2];
        if (i3 >= 0) {
            this.__state = i3;
            this.__offset++;
            return;
        }
        if (i3 == -9) {
            pop(JsonCheckerMode.Key);
            this.__state = 1;
        } else if (i3 == -8) {
            pop(JsonCheckerMode.Object);
            this.__state = 1;
        } else if (i3 == -7) {
            pop(JsonCheckerMode.Array);
            this.__state = 1;
        } else if (i3 == -6) {
            push(JsonCheckerMode.Key);
            this.__state = 2;
        } else if (i3 == -5) {
            push(JsonCheckerMode.Array);
            this.__state = 6;
        } else if (i3 == -4) {
            JsonCheckerMode peek = this.__stack.peek();
            if (Global.equals(peek, JsonCheckerMode.Key)) {
                this.__state = 4;
            } else if (Global.equals(peek, JsonCheckerMode.Array) || Global.equals(peek, JsonCheckerMode.Object)) {
                this.__state = 1;
            } else if (Global.equals(peek, JsonCheckerMode.Done)) {
                push(JsonCheckerMode.String);
                this.__state = 7;
            } else if (Global.equals(peek, JsonCheckerMode.String)) {
                pop(JsonCheckerMode.String);
                this.__state = 1;
            } else {
                onError();
            }
        } else if (i3 == -3) {
            JsonCheckerMode peek2 = this.__stack.peek();
            if (Global.equals(peek2, JsonCheckerMode.Object)) {
                pop(JsonCheckerMode.Object);
                push(JsonCheckerMode.Key);
                this.__state = 3;
            } else if (Global.equals(peek2, JsonCheckerMode.Array)) {
                this.__state = 5;
            } else {
                onError();
            }
        } else if (i3 == -2) {
            pop(JsonCheckerMode.Key);
            push(JsonCheckerMode.Object);
            this.__state = 5;
        } else {
            onError();
        }
        this.__offset++;
    }

    public boolean checkString(String str) {
        BigDecimal bigDecimal = new BigDecimal("0");
        DoubleHolder doubleHolder = new DoubleHolder(0.0d);
        boolean tryParseDoubleValue = ParseAssistant.tryParseDoubleValue(str, doubleHolder);
        doubleHolder.getValue();
        Holder holder = new Holder(bigDecimal);
        boolean tryParseDecimalValue = ParseAssistant.tryParseDecimalValue(str, holder);
        BigDecimal bigDecimal2 = (BigDecimal) holder.getValue();
        if (Global.equals(str, "true") || Global.equals(str, "false") || Global.equals(str, "null") || tryParseDoubleValue || tryParseDecimalValue) {
            return true;
        }
        int i = 0;
        while (i < StringExtensions.getLength(str)) {
            try {
                check(str.charAt(i));
                i++;
            } catch (Exception unused) {
                return false;
            }
        }
        finalCheck();
        return true;
    }

    private void finalCheck() {
        if (this.__state != 1) {
            onError();
        }
        pop(JsonCheckerMode.Done);
    }

    public JsonChecker() {
        this(0);
    }

    public JsonChecker(int i) {
        if (i >= 0) {
            this.__state = 0;
            this.__depth = i;
            this.__stack = StackExtensions.createStack(i);
            push(JsonCheckerMode.Done);
            return;
        }
        throw new RuntimeException(new Exception("Invalid depth."));
    }

    private void onError() {
        throw new RuntimeException(new Exception(StringExtensions.format("Invalid JSON text at character offset {0}.", (Object) LongExtensions.toString(Long.valueOf(this.__offset), "N0"))));
    }

    private void pop(JsonCheckerMode jsonCheckerMode) {
        if (!Global.equals(this.__stack.pop(), jsonCheckerMode)) {
            onError();
        }
    }

    private void push(JsonCheckerMode jsonCheckerMode) {
        if (this.__depth > 0 && StackExtensions.getCount(this.__stack) >= this.__depth) {
            onError();
        }
        this.__stack.push(jsonCheckerMode);
    }
}
