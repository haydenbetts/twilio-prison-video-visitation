package fm.liveswitch.openh264;

class NativeLibraryInfo {
    private String _downloadURL;
    private String _libraryName;
    private String _loadLibraryName;
    private String[] _oldLibraryNames;

    public String getDownloadURL() {
        return this._downloadURL;
    }

    public String getLibraryName() {
        return this._libraryName;
    }

    public String getLoadLibraryName() {
        return this._loadLibraryName;
    }

    public String[] getOldLibraryNames() {
        return this._oldLibraryNames;
    }

    public void setDownloadURL(String str) {
        this._downloadURL = str;
    }

    public void setLibraryName(String str) {
        this._libraryName = str;
    }

    public void setLoadLibraryName(String str) {
        this._loadLibraryName = str;
    }

    public void setOldLibraryNames(String[] strArr) {
        this._oldLibraryNames = strArr;
    }
}
