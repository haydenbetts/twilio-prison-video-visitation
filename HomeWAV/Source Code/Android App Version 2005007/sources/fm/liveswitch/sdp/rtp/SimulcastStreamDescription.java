package fm.liveswitch.sdp.rtp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.StringExtensions;

public class SimulcastStreamDescription {
    private String __direction;
    private SimulcastStream[] _streams;

    private boolean directionIsValid(String str) {
        String[] strArr = {SimulcastDirection.getSend(), SimulcastDirection.getReceive()};
        for (int i = 0; i < 2; i++) {
            if (Global.equals(str, strArr[i])) {
                return true;
            }
        }
        return false;
    }

    public String getDirection() {
        return this.__direction;
    }

    public SimulcastStream[] getStreams() {
        return this._streams;
    }

    private void setDirection(String str) {
        if (directionIsValid(str)) {
            this.__direction = str;
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Simulcast stream description 'direction' cannot be '{0}'.", (Object) str)));
    }

    private void setStreams(SimulcastStream[] simulcastStreamArr) {
        this._streams = simulcastStreamArr;
    }

    public SimulcastStreamDescription(String str, SimulcastStream[] simulcastStreamArr) {
        if (str == null) {
            throw new RuntimeException(new Exception("Simulcast stream description 'direction' cannot be null."));
        } else if (simulcastStreamArr == null) {
            throw new RuntimeException(new Exception("Simulcast stream description 'streams' cannot be null."));
        } else if (ArrayExtensions.getLength((Object[]) simulcastStreamArr) != 0) {
            setDirection(str);
            setStreams(simulcastStreamArr);
        } else {
            throw new RuntimeException(new Exception("Simulcast stream description requires at least one stream."));
        }
    }
}
