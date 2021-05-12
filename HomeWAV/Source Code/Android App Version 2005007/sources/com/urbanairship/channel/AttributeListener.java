package com.urbanairship.channel;

import java.util.List;

public interface AttributeListener {
    void onAttributeMutationsUploaded(String str, List<AttributeMutation> list);
}
