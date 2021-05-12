package fm.liveswitch;

import java.util.Date;
import org.joda.time.DateTimeConstants;

class TurnPermission {
    private String _address;
    private Date _expires = new Date();

    public void extendTimeToExpiry() {
        setTimeToExpiry(DateTimeConstants.SECONDS_PER_DAY);
    }

    public String getAddress() {
        return this._address;
    }

    public boolean getIsExpired() {
        return DateExtensions.getTicks(DateExtensions.getUtcNow()) > DateExtensions.getTicks(this._expires);
    }

    public int getTimeToExpiry() {
        return (int) ((DateExtensions.getTicks(this._expires) - DateExtensions.getTicks(DateExtensions.getUtcNow())) / 10000000);
    }

    private void setAddress(String str) {
        this._address = str;
    }

    private void setTimeToExpiry(int i) {
        this._expires = DateExtensions.addSeconds(DateExtensions.getUtcNow(), (double) i);
    }

    public String toString() {
        return getAddress();
    }

    public TurnPermission(String str) {
        setAddress(str);
        extendTimeToExpiry();
    }
}
