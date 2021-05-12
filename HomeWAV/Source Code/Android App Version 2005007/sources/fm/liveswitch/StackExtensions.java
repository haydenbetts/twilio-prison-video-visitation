package fm.liveswitch;

import java.util.Stack;

public class StackExtensions {
    public static <T> Stack<T> createStack(int i) {
        return new Stack<>();
    }

    public static <T> int getCount(Stack<T> stack) {
        return stack.size();
    }
}
