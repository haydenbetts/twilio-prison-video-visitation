package com.urbanairship.messagecenter.actions;

import com.urbanairship.actions.Action;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionResult;
import com.urbanairship.messagecenter.MessageCenter;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.AirshipComponentUtils;
import com.urbanairship.util.UAStringUtil;
import java.util.concurrent.Callable;

public class MessageCenterAction extends Action {
    public static final String DEFAULT_REGISTRY_NAME = "open_mc_action";
    public static final String DEFAULT_REGISTRY_SHORT_NAME = "^mc";
    public static final String MESSAGE_ID_PLACEHOLDER = "auto";
    private final Callable<MessageCenter> messageCenterCallable;

    public boolean shouldRunOnMainThread() {
        return true;
    }

    public boolean acceptsArguments(ActionArguments actionArguments) {
        int situation = actionArguments.getSituation();
        return situation == 0 || situation == 6 || situation == 2 || situation == 3 || situation == 4;
    }

    public MessageCenterAction() {
        this(AirshipComponentUtils.callableForComponent(MessageCenter.class));
    }

    MessageCenterAction(Callable<MessageCenter> callable) {
        this.messageCenterCallable = callable;
    }

    public ActionResult perform(ActionArguments actionArguments) {
        try {
            MessageCenter call = this.messageCenterCallable.call();
            String string = actionArguments.getValue().getString();
            if ("auto".equalsIgnoreCase(string)) {
                PushMessage pushMessage = (PushMessage) actionArguments.getMetadata().getParcelable(ActionArguments.PUSH_MESSAGE_METADATA);
                if (pushMessage == null || pushMessage.getRichPushMessageId() == null) {
                    string = actionArguments.getMetadata().containsKey(ActionArguments.RICH_PUSH_ID_METADATA) ? actionArguments.getMetadata().getString(ActionArguments.RICH_PUSH_ID_METADATA) : null;
                } else {
                    string = pushMessage.getRichPushMessageId();
                }
            }
            if (UAStringUtil.isEmpty(string)) {
                call.showMessageCenter();
            } else {
                call.showMessageCenter(string);
            }
            return ActionResult.newEmptyResult();
        } catch (Exception e) {
            return ActionResult.newErrorResult(e);
        }
    }
}
