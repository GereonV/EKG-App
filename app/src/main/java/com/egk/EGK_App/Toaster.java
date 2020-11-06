package com.egk.EGK_App;

import android.content.Context;
import android.widget.Toast;

/**
 * Helper-Class to display Toast-messages
 * @author Gereon
 * @version 1
 * @see Toast
 */
public class Toaster {

    /**
     * current Toast, that was shown last or is currently shown
     */
    private Toast toast;

    /**
     * reference to Activity
     */
    private Context context;

    /**
     * creates new Toaster
     * @param context reference to Activity & Context
     */
    public Toaster(Context context) {
        this.context = context;
    }

    /**
     * hides active Toast if possible
     */
    public void hide() {
        if(toast != null) toast.cancel();
    }

    /**
     * shows the message for specified duration
     * @param message String to be shown
     * @param duration duration of Toast
     */
    public void show(String message, int duration) {
        show(message, duration, false);
    }

    /**
     * shows the message at ID for specified duration
     * @param messageID ID to String, that will be shown
     * @param duration duration of Toast
     */
    public void show(int messageID, int duration) {
        show(messageID, duration, false);
    }

    /**
     * shows the message for specified duration and possibly replaces previous
     * @param message String to be shown
     * @param duration duration of Toast
     * @param cancelPrevious if false Toast will be shown after current one
     */
    public void show(String message, int duration, boolean cancelPrevious) {
        if(duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            if(cancelPrevious) hide();
            toast = Toast.makeText(context, message, duration);
            toast.show();
        }
    }

    /**
     * shows the message at ID for specified duration and possibly replaces previous
     * @param messageID ID to String, that will be shown
     * @param duration duration of Toast
     * @param cancelPrevious if false Toast will be shown after current one
     */
    public void show(int messageID, int duration, boolean cancelPrevious) {
        if(duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            if(cancelPrevious) hide();
            toast = Toast.makeText(context, context.getString(messageID), duration);
            toast.show();
        }
    }
}
