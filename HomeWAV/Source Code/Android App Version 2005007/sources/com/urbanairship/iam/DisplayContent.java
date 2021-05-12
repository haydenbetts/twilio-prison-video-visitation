package com.urbanairship.iam;

import com.urbanairship.json.JsonSerializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface DisplayContent extends JsonSerializable {
    public static final String ALLOW_FULLSCREEN_DISPLAY_KEY = "allow_fullscreen_display";
    public static final String BACKGROUND_COLOR_KEY = "background_color";
    public static final String BODY_KEY = "body";
    public static final String BORDER_RADIUS_KEY = "border_radius";
    public static final String BUTTONS_KEY = "buttons";
    public static final String BUTTON_LAYOUT_JOINED = "joined";
    public static final String BUTTON_LAYOUT_KEY = "button_layout";
    public static final String BUTTON_LAYOUT_SEPARATE = "separate";
    public static final String BUTTON_LAYOUT_STACKED = "stacked";
    public static final String DISMISS_BUTTON_COLOR_KEY = "dismiss_button_color";
    public static final String DURATION_KEY = "duration";
    public static final String FOOTER_KEY = "footer";
    public static final String HEADING_KEY = "heading";
    public static final String MEDIA_KEY = "media";
    public static final String PLACEMENT_KEY = "placement";
    public static final String TEMPLATE_KEY = "template";
    public static final String URL_KEY = "url";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonLayout {
    }
}
