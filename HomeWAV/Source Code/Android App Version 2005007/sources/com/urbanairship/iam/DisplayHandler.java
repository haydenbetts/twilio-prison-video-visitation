package com.urbanairship.iam;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.automation.InAppAutomation;

public class DisplayHandler implements Parcelable {
    public static final Parcelable.Creator<DisplayHandler> CREATOR = new Parcelable.Creator<DisplayHandler>() {
        public DisplayHandler createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            if (readString == null) {
                readString = "";
            }
            return new DisplayHandler(readString);
        }

        public DisplayHandler[] newArray(int i) {
            return new DisplayHandler[i];
        }
    };
    private final String scheduleId;

    public int describeContents() {
        return 0;
    }

    public DisplayHandler(String str) {
        this.scheduleId = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.scheduleId);
    }

    public void finished(ResolutionInfo resolutionInfo, long j) {
        InAppAutomation inAppAutomation = getInAppAutomation();
        if (inAppAutomation == null) {
            Logger.error("Takeoff not called. Unable to finish display for schedule: %s", this.scheduleId);
            return;
        }
        inAppAutomation.getInAppMessageManager().onDisplayFinished(this.scheduleId, resolutionInfo, j);
        if (resolutionInfo.getButtonInfo() != null && "cancel".equals(resolutionInfo.getButtonInfo().getBehavior())) {
            inAppAutomation.cancelSchedule(this.scheduleId);
        }
    }

    public void cancelFutureDisplays() {
        InAppAutomation inAppAutomation = getInAppAutomation();
        if (inAppAutomation == null) {
            Logger.error("Takeoff not called. Unable to cancel displays for schedule: %s", this.scheduleId);
            return;
        }
        inAppAutomation.cancelSchedule(this.scheduleId);
    }

    public boolean isDisplayAllowed(Context context) {
        Autopilot.automaticTakeOff(context);
        InAppAutomation inAppAutomation = getInAppAutomation();
        if (inAppAutomation != null) {
            return inAppAutomation.getInAppMessageManager().isDisplayAllowed(this.scheduleId);
        }
        Logger.error("Takeoff not called. Unable to request display lock.", new Object[0]);
        return false;
    }

    private InAppAutomation getInAppAutomation() {
        if (UAirship.isTakingOff() || UAirship.isFlying()) {
            return InAppAutomation.shared();
        }
        return null;
    }
}
