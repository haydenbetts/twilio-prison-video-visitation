package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.CustomPropertiesLog;
import com.microsoft.appcenter.ingestion.models.Log;

public class CustomPropertiesLogFactory extends AbstractLogFactory {
    public Log create() {
        return new CustomPropertiesLog();
    }
}
