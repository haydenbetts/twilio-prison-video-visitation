package fm.liveswitch.stun;

public class BindingIndication extends BindingMessage {
    public BindingIndication() {
        super(MessageType.Indication, Message.generateTransactionId());
    }
}
