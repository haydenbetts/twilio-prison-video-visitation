package fm.liveswitch;

import java.io.File;
import java.io.RandomAccessFile;

public class FileStream implements IFileStream {
    private RandomAccessFile _file;
    private String _path;

    public String getPath() {
        return this._path;
    }

    public FileStream(String str) {
        this._path = str;
    }

    public long getLength() {
        try {
            RandomAccessFile randomAccessFile = this._file;
            if (randomAccessFile == null) {
                return 0;
            }
            return randomAccessFile.length();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long getPosition() {
        try {
            RandomAccessFile randomAccessFile = this._file;
            if (randomAccessFile == null) {
                return 0;
            }
            return randomAccessFile.getFilePointer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setPosition(long j) {
        try {
            RandomAccessFile randomAccessFile = this._file;
            if (randomAccessFile != null) {
                randomAccessFile.seek(j);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists() {
        return new File(this._path).exists();
    }

    public void open(FileStreamAccess fileStreamAccess) {
        try {
            if (fileStreamAccess == FileStreamAccess.Read) {
                this._file = new RandomAccessFile(this._path, "r");
            } else if (fileStreamAccess == FileStreamAccess.Write) {
                this._file = new RandomAccessFile(this._path, "rw");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            RandomAccessFile randomAccessFile = this._file;
            if (randomAccessFile != null) {
                randomAccessFile.close();
                this._file = null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean flush() {
        RandomAccessFile randomAccessFile = this._file;
        if (randomAccessFile == null) {
            return false;
        }
        try {
            randomAccessFile.getFD().sync();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean write(byte[] bArr, int i, int i2) {
        RandomAccessFile randomAccessFile = this._file;
        if (randomAccessFile == null) {
            return false;
        }
        try {
            randomAccessFile.write(bArr, i, i2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeTo(int i, byte[] bArr, int i2, int i3) {
        RandomAccessFile randomAccessFile = this._file;
        if (randomAccessFile == null) {
            return false;
        }
        try {
            long filePointer = randomAccessFile.getFilePointer();
            this._file.seek((long) i);
            this._file.write(bArr, i2, i3);
            this._file.seek(filePointer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        RandomAccessFile randomAccessFile = this._file;
        if (randomAccessFile == null) {
            return 0;
        }
        try {
            return randomAccessFile.read(bArr, i, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
