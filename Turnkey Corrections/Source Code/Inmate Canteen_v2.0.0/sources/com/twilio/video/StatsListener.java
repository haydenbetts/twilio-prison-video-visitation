package com.twilio.video;

import androidx.annotation.NonNull;
import java.util.List;

public interface StatsListener {
    void onStats(@NonNull List<StatsReport> list);
}
