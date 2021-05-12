package fm.liveswitch;

public interface IPlatform {
    Architecture getArchitecture();

    int getCoreCount();

    CryptoLibrary getCryptoLibrary();

    String getDirectorySeparator();

    boolean getIsLittleEndian();

    boolean getIsMobile();

    String getMachineName();

    OperatingSystem getOperatingSystem();

    String getOperatingSystemVersion();

    long getPhysicalMemory();

    int getProcessId();

    SourceLanguage getSourceLanguage();

    boolean getUseFipsAlgorithms();

    void setCryptoLibrary(CryptoLibrary cryptoLibrary);

    void setIsMobile(boolean z);

    void setUseFipsAlgorithms(boolean z);
}
