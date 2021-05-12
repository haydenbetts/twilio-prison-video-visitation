package fm.liveswitch;

public class RpsiControlFrame extends PayloadSpecificControlFrame {
    private Rpsi _referencePictureSelectionIndication;

    public static byte getFeedbackMessageTypeByte() {
        return 3;
    }

    public Rpsi getReferencePictureSelectionIndication() {
        return this._referencePictureSelectionIndication;
    }

    public RpsiControlFrame() {
        super(getFeedbackMessageTypeByte());
    }

    public void setReferencePictureSelectionIndication(Rpsi rpsi) {
        this._referencePictureSelectionIndication = rpsi;
    }
}
