package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Invitation {
    /* access modifiers changed from: private */
    public Promise<Object> __cancelPromise;
    private Channel __channel;
    private Object __lock = new Object();
    /* access modifiers changed from: private */
    public List<IAction1<Invitation>> __onStateChanging = new ArrayList();
    private IAction1<Invitation> _onStateChanging = null;
    private String _protocol;
    private String _reason;
    private InvitationState _state;
    private String _userId;

    public void addOnStateChanging(IAction1<Invitation> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChanging == null) {
                this._onStateChanging = new IAction1<Invitation>() {
                    public void invoke(Invitation invitation) {
                        Iterator it = new ArrayList(Invitation.this.__onStateChanging).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(invitation);
                        }
                    }
                };
            }
            this.__onStateChanging.add(iAction1);
        }
    }

    public Future<Object> cancel() {
        Promise<Object> promise;
        if (Global.equals(getState(), InvitationState.Pending) || Global.equals(getState(), InvitationState.Proceeding)) {
            synchronized (this.__lock) {
                setState(InvitationState.Cancelling);
                onStateChange(this, this._onStateChanging);
                this.__cancelPromise = new Promise<>();
                Message createCancelInviteMessage = Message.createCancelInviteMessage(getUserId(), getProtocol());
                this.__channel.send(createCancelInviteMessage).fail((IAction1<Exception>) new IAction1<Exception>() {
                    public void invoke(Exception exc) {
                        Invitation.this.__cancelPromise.reject(exc);
                    }
                });
                promise = this.__cancelPromise;
            }
            return promise;
        } else if (Global.equals(getState(), InvitationState.Cancelling)) {
            return PromiseBase.rejectNow(new Exception("Already cancelling."));
        } else {
            return PromiseBase.rejectNow(new Exception("The Invitation is in a state where it cannot be canceled."));
        }
    }

    public String getChannelId() {
        return this.__channel.getId();
    }

    static String getInviteKey(String str, String str2, String str3) {
        return StringExtensions.format("{0}|{1}|{2}", str, str2, str3);
    }

    public String getInviteKey() {
        return getInviteKey(getChannelId(), getUserId(), getProtocol());
    }

    public String getProtocol() {
        return this._protocol;
    }

    public String getReason() {
        return this._reason;
    }

    public InvitationState getState() {
        return this._state;
    }

    public String getUserId() {
        return this._userId;
    }

    Invitation(Channel channel, String str, String str2) {
        this.__channel = channel;
        setUserId(str);
        setProtocol(str2);
        setState(InvitationState.Pending);
    }

    private void onStateChange(final Invitation invitation, final IAction1<Invitation> iAction1) {
        if (iAction1 != null) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    iAction1.invoke(invitation);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processFeedback(InviteFeedback inviteFeedback) {
        boolean z;
        synchronized (this.__lock) {
            setState(inviteFeedback.getState());
            setReason(inviteFeedback.getReason());
            if (this.__cancelPromise != null) {
                if (Global.equals(getState(), InvitationState.Cancelled)) {
                    this.__cancelPromise.resolve(null);
                } else if (!Global.equals(getState(), InvitationState.Cancelling)) {
                    this.__cancelPromise.reject(new Exception("The invitation was acted upon before canceled."));
                }
            }
            onStateChange(this, this._onStateChanging);
            if (!Global.equals(getState(), InvitationState.Proceeding)) {
                if (!Global.equals(getState(), InvitationState.Cancelling)) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public void removeOnStateChanging(IAction1<Invitation> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChanging, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChanging.remove(iAction1);
        if (this.__onStateChanging.size() == 0) {
            this._onStateChanging = null;
        }
    }

    private void setProtocol(String str) {
        this._protocol = str;
    }

    private void setReason(String str) {
        this._reason = str;
    }

    private void setState(InvitationState invitationState) {
        this._state = invitationState;
    }

    private void setUserId(String str) {
        this._userId = str;
    }
}
