package fm.liveswitch.sdp.rtp;

import fm.liveswitch.ArrayExtensions;

public class SimulcastStream {
    private SimulcastStreamId[] _ids;

    public SimulcastStreamId[] getIds() {
        return this._ids;
    }

    private void setIds(SimulcastStreamId[] simulcastStreamIdArr) {
        this._ids = simulcastStreamIdArr;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimulcastStream(fm.liveswitch.sdp.rtp.SimulcastStreamId r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000b
        L_0x0004:
            r0 = 1
            fm.liveswitch.sdp.rtp.SimulcastStreamId[] r0 = new fm.liveswitch.sdp.rtp.SimulcastStreamId[r0]
            r1 = 0
            r0[r1] = r3
            r3 = r0
        L_0x000b:
            r2.<init>((fm.liveswitch.sdp.rtp.SimulcastStreamId[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.sdp.rtp.SimulcastStream.<init>(fm.liveswitch.sdp.rtp.SimulcastStreamId):void");
    }

    public SimulcastStream(SimulcastStreamId[] simulcastStreamIdArr) {
        if (simulcastStreamIdArr == null) {
            throw new RuntimeException(new Exception("Simulcast stream 'IDs' cannot be null."));
        } else if (ArrayExtensions.getLength((Object[]) simulcastStreamIdArr) != 0) {
            setIds(simulcastStreamIdArr);
        } else {
            throw new RuntimeException(new Exception("Simulcast stream requires at least one ID."));
        }
    }
}
