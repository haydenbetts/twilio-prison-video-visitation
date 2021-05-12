package org.apache.cordova.camera;

import android.media.ExifInterface;
import java.io.IOException;

public class ExifHelper {
    private String aperture = null;
    private String datetime = null;
    private String exposureTime = null;
    private String flash = null;
    private String focalLength = null;
    private String gpsAltitude = null;
    private String gpsAltitudeRef = null;
    private String gpsDateStamp = null;
    private String gpsLatitude = null;
    private String gpsLatitudeRef = null;
    private String gpsLongitude = null;
    private String gpsLongitudeRef = null;
    private String gpsProcessingMethod = null;
    private String gpsTimestamp = null;
    private ExifInterface inFile = null;
    private String iso = null;
    private String make = null;
    private String model = null;
    private String orientation = null;
    private ExifInterface outFile = null;
    private String whiteBalance = null;

    public void createInFile(String str) throws IOException {
        this.inFile = new ExifInterface(str);
    }

    public void createOutFile(String str) throws IOException {
        this.outFile = new ExifInterface(str);
    }

    public void readExifData() {
        this.aperture = this.inFile.getAttribute("FNumber");
        this.datetime = this.inFile.getAttribute("DateTime");
        this.exposureTime = this.inFile.getAttribute("ExposureTime");
        this.flash = this.inFile.getAttribute("Flash");
        this.focalLength = this.inFile.getAttribute("FocalLength");
        this.gpsAltitude = this.inFile.getAttribute("GPSAltitude");
        this.gpsAltitudeRef = this.inFile.getAttribute("GPSAltitudeRef");
        this.gpsDateStamp = this.inFile.getAttribute("GPSDateStamp");
        this.gpsLatitude = this.inFile.getAttribute("GPSLatitude");
        this.gpsLatitudeRef = this.inFile.getAttribute("GPSLatitudeRef");
        this.gpsLongitude = this.inFile.getAttribute("GPSLongitude");
        this.gpsLongitudeRef = this.inFile.getAttribute("GPSLongitudeRef");
        this.gpsProcessingMethod = this.inFile.getAttribute("GPSProcessingMethod");
        this.gpsTimestamp = this.inFile.getAttribute("GPSTimeStamp");
        this.iso = this.inFile.getAttribute("ISOSpeedRatings");
        this.make = this.inFile.getAttribute("Make");
        this.model = this.inFile.getAttribute("Model");
        this.orientation = this.inFile.getAttribute("Orientation");
        this.whiteBalance = this.inFile.getAttribute("WhiteBalance");
    }

    public void writeExifData() throws IOException {
        ExifInterface exifInterface = this.outFile;
        if (exifInterface != null) {
            String str = this.aperture;
            if (str != null) {
                exifInterface.setAttribute("FNumber", str);
            }
            String str2 = this.datetime;
            if (str2 != null) {
                this.outFile.setAttribute("DateTime", str2);
            }
            String str3 = this.exposureTime;
            if (str3 != null) {
                this.outFile.setAttribute("ExposureTime", str3);
            }
            String str4 = this.flash;
            if (str4 != null) {
                this.outFile.setAttribute("Flash", str4);
            }
            String str5 = this.focalLength;
            if (str5 != null) {
                this.outFile.setAttribute("FocalLength", str5);
            }
            String str6 = this.gpsAltitude;
            if (str6 != null) {
                this.outFile.setAttribute("GPSAltitude", str6);
            }
            String str7 = this.gpsAltitudeRef;
            if (str7 != null) {
                this.outFile.setAttribute("GPSAltitudeRef", str7);
            }
            String str8 = this.gpsDateStamp;
            if (str8 != null) {
                this.outFile.setAttribute("GPSDateStamp", str8);
            }
            String str9 = this.gpsLatitude;
            if (str9 != null) {
                this.outFile.setAttribute("GPSLatitude", str9);
            }
            String str10 = this.gpsLatitudeRef;
            if (str10 != null) {
                this.outFile.setAttribute("GPSLatitudeRef", str10);
            }
            String str11 = this.gpsLongitude;
            if (str11 != null) {
                this.outFile.setAttribute("GPSLongitude", str11);
            }
            String str12 = this.gpsLongitudeRef;
            if (str12 != null) {
                this.outFile.setAttribute("GPSLongitudeRef", str12);
            }
            String str13 = this.gpsProcessingMethod;
            if (str13 != null) {
                this.outFile.setAttribute("GPSProcessingMethod", str13);
            }
            String str14 = this.gpsTimestamp;
            if (str14 != null) {
                this.outFile.setAttribute("GPSTimeStamp", str14);
            }
            String str15 = this.iso;
            if (str15 != null) {
                this.outFile.setAttribute("ISOSpeedRatings", str15);
            }
            String str16 = this.make;
            if (str16 != null) {
                this.outFile.setAttribute("Make", str16);
            }
            String str17 = this.model;
            if (str17 != null) {
                this.outFile.setAttribute("Model", str17);
            }
            String str18 = this.orientation;
            if (str18 != null) {
                this.outFile.setAttribute("Orientation", str18);
            }
            String str19 = this.whiteBalance;
            if (str19 != null) {
                this.outFile.setAttribute("WhiteBalance", str19);
            }
            this.outFile.saveAttributes();
        }
    }

    public int getOrientation() {
        int parseInt = Integer.parseInt(this.orientation);
        if (parseInt == 1) {
            return 0;
        }
        if (parseInt == 6) {
            return 90;
        }
        if (parseInt == 3) {
            return 180;
        }
        return parseInt == 8 ? 270 : 0;
    }

    public void resetOrientation() {
        this.orientation = "1";
    }
}
