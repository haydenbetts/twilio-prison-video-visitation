package toothpick.configuration;

import java.util.List;

public class CyclicDependencyException extends RuntimeException {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int MARGIN_SIZE = 3;

    CyclicDependencyException() {
    }

    CyclicDependencyException(String str) {
        super(str);
    }

    CyclicDependencyException(String str, Throwable th) {
        super(str, th);
    }

    CyclicDependencyException(Throwable th) {
        super(th);
    }

    public CyclicDependencyException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }

    CyclicDependencyException(List<Class<?>> list, Class cls) {
        this(String.format("Class %s creates a cycle:%n%s", new Object[]{cls.getName(), format(list, cls)}));
    }

    private static String format(List<Class<?>> list, Class cls) {
        if (list.size() != 0) {
            List<Class<?>> subList = list.subList(Math.max(list.indexOf(cls), 0), list.size());
            int findLongestClassNameLength = findLongestClassNameLength(subList);
            int i = (findLongestClassNameLength / 2) + 3;
            int i2 = findLongestClassNameLength + 6;
            StringBuilder sb = new StringBuilder();
            addTopLines(sb, i, i2);
            for (Class<?> name : subList) {
                addLine(sb, name.getName(), i, i2);
                addLine(sb, "||", i, i2);
            }
            addHorizontalLine(sb, i, i2);
            return sb.toString();
        }
        throw new IllegalArgumentException();
    }

    private static void addTopLines(StringBuilder sb, int i, int i2) {
        sb.append(LINE_SEPARATOR);
        addHorizontalLine(sb, i, i2);
        addLine(sb, "||", i, i2);
        addLine(sb, "\\/", i, i2);
    }

    private static void addHorizontalLine(StringBuilder sb, int i, int i2) {
        sb.append(repeat(' ', i));
        sb.append(repeat('=', (i2 - i) + 1));
        sb.append(LINE_SEPARATOR);
    }

    private static void addLine(StringBuilder sb, String str, int i, int i2) {
        int length = i - (str.length() / 2);
        sb.append(repeat(' ', length));
        sb.append(str);
        sb.append(repeat(' ', (i2 - length) - str.length()));
        sb.append("||");
        sb.append(LINE_SEPARATOR);
    }

    private static int findLongestClassNameLength(List<Class<?>> list) {
        int i = 0;
        for (Class<?> name : list) {
            int length = name.getName().length();
            if (length > i) {
                i = length;
            }
        }
        return i;
    }

    private static String repeat(char c, int i) {
        return new String(new char[i]).replace(0, c);
    }
}
