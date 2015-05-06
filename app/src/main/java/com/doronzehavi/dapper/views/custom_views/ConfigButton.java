package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.widget.ImageButton;

/**
 * This will be the base class for buttons in the config fragments.
 * It will hold a image for the button as well as an associated key and value for the particular
 * component that it represents.
 */
public class ConfigButton extends ImageButton {
    private String componentKey; // The key that this button will modify
    private String componentResourceKey;  //

    // Getters
    public String getComponentKey() {
        return componentKey;
    }
    public String getComponentResourceKey() {
        return componentResourceKey;
    }

    // Setters
    public void setComponentKey(String componentKey) {
        this.componentKey = componentKey;
    }
    public void setComponentResourceKey(String componentResourceKey) {
        this.componentResourceKey = componentResourceKey;
    }

    // Constructor
    public ConfigButton(Context context) {
        super(context);
    }
}
