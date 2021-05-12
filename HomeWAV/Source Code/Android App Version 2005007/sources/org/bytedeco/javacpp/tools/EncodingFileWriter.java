package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class EncodingFileWriter extends OutputStreamWriter {
    String newline = "\n";

    public EncodingFileWriter(File file, String str) throws IOException {
        super(new FileOutputStream(file));
        if (str != null) {
            this.newline = str;
        }
    }

    public EncodingFileWriter(File file, String str, String str2) throws IOException {
        super(new FileOutputStream(file), str);
        if (str2 != null) {
            this.newline = str2;
        }
    }

    public Writer append(CharSequence charSequence) throws IOException {
        return super.append(((String) charSequence).replace("\n", this.newline).replace("\\u", "\\u005Cu"));
    }
}
