package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.widget.ImageButton;

/**
 * This will be the base class for buttons in the config fragments.
 * It will hold a image for the button as well as an associated key and value for the particular
 * component that it represents.
 */
public class ConfigButton extends ImageButton {
    private String configKey; // The key that this button will modify
    private int configValue;  // The value of the resource this item represents

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public int getConfigValue() {
        return configValue;
    }

    public void setConfigValue(int configValue) {
        this.configValue = configValue;
    }
    public ConfigButton(Context context) {
        super(context);
    }
}
