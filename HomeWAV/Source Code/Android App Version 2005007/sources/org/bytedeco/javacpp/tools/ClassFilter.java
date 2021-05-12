package org.bytedeco.javacpp.tools;

public interface ClassFilter {
    boolean keep(String str, byte[] bArr);
}
