package fm.liveswitch;

import java.net.InetAddress;
import java.util.Map;

public class Platform implements IPlatform {
    private static IPlatform _instance = new Platform();
    private static boolean _isMobile = true;
    private Architecture _arch = Architecture.Unknown;
    private CryptoLibrary _cryptoLibrary = CryptoLibrary.BouncyCastle;
    private SourceLanguage _language = SourceLanguage.Java;
    private String _machineName = null;
    private String _operatingSystemVersion = null;
    private OperatingSystem _os = OperatingSystem.Unknown;
    private boolean _useFipsAlgorithms = false;

    public boolean getIsLittleEndian() {
        return false;
    }

    public int getProcessId() {
        return -1;
    }

    public boolean getUseFipsAlgorithms() {
        return false;
    }

    public void setUseFipsAlgorithms(boolean z) {
    }

    static {
        try {
            Class.forName("java.awt.image.BufferedImage");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static IPlatform getInstance() {
        return _instance;
    }

    public boolean getIsMobile() {
        return _isMobile;
    }

    public void setIsMobile(boolean z) {
        _isMobile = z;
    }

    public OperatingSystem getOperatingSystem() {
        if (_isMobile) {
            return OperatingSystem.Android;
        }
        if (this._os == OperatingSystem.Unknown) {
            String lowerCase = System.getProperty("os.name").toLowerCase();
            if (lowerCase.indexOf("win") >= 0) {
                this._os = OperatingSystem.Windows;
            } else if (lowerCase.indexOf("mac") >= 0) {
                this._os = OperatingSystem.MacOS;
            } else {
                this._os = OperatingSystem.Linux;
            }
        }
        return this._os;
    }

    public String getOperatingSystemVersion() {
        if (_isMobile && this._operatingSystemVersion == null) {
            try {
                this._operatingSystemVersion = Class.forName("android.os.Build$VERSION").getDeclaredField("RELEASE").toString();
            } catch (Exception unused) {
            }
        }
        if (this._operatingSystemVersion == null) {
            this._operatingSystemVersion = System.getProperty("os.version");
        }
        return this._operatingSystemVersion;
    }

    public String getDirectorySeparator() {
        return getOperatingSystem() == OperatingSystem.Windows ? "\\" : "/";
    }

    public SourceLanguage getSourceLanguage() {
        return this._language;
    }

    public Architecture getArchitecture() {
        if (this._arch == Architecture.Unknown) {
            String property = System.getProperty("os.arch");
            if (property.equals("i386") || property.equals("x86") || property.equals("i686")) {
                this._arch = Architecture.X86;
            } else if (property.equals("x86_64") || property.equals("amd64")) {
                this._arch = Architecture.X64;
            } else if (property.equals("armeabi-v7a") || property.equals("armv7l") || property.equals("arm")) {
                this._arch = Architecture.Armv7;
            } else if (property.equals("aarch32")) {
                this._arch = Architecture.Armv8;
            } else if (property.equals("arm64-v8a") || property.equals("aarch64")) {
                this._arch = Architecture.Arm64;
            }
        }
        return this._arch;
    }

    public int getCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public long getPhysicalMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public String getMachineName() {
        if (this._machineName == null && _isMobile) {
            try {
                this._machineName = Class.forName("android.os.Build").getDeclaredField("MODEL").toString();
            } catch (Exception unused) {
            }
        }
        if (this._machineName == null) {
            try {
                this._machineName = InetAddress.getLocalHost().getHostName();
            } catch (Exception unused2) {
            }
        }
        if (this._machineName == null) {
            Map<String, String> map = System.getenv();
            if (map.containsKey("COMPUTERNAME")) {
                this._machineName = map.get("COMPUTERNAME");
            }
        }
        if (this._machineName == null) {
            Map<String, String> map2 = System.getenv();
            if (map2.containsKey("HOSTNAME")) {
                this._machineName = map2.get("HOSTNAME");
            }
        }
        return this._machineName;
    }

    public CryptoLibrary getCryptoLibrary() {
        return this._cryptoLibrary;
    }

    public void setCryptoLibrary(CryptoLibrary cryptoLibrary) {
        this._cryptoLibrary = cryptoLibrary;
    }
}
