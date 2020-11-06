package com.egk.EGK_App;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Helper-Class to handle the Keyboard
 * @author Gereon
 * @version 1
 * @see InputMethodManager
 */
public class Keyboard {

    private Keyboard() {}

    /**
     * hides the keyboard and clears the focus
     * @param activity reference to caller
     */
    public static void hide(AppCompatActivity activity) {
        View v = activity.getCurrentFocus();
        if(v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        }
    }

    /**
     * shows keyboard and focuses a View element
     * @param view View element to get Focus
     */
    public static void show(View view) {
        view.requestFocus();
        if(view.hasFocus()) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.showSoftInput(view, 0);
        }
    }
}
