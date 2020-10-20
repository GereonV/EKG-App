package com.egk.EGK_App;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * cleaned up TextWatcher
 * @author Gereon
 * @version 1.0
 */

public abstract class BasicTextWatcher implements TextWatcher {

    private EditText editText;

    public BasicTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        this.onTextChanged(editText);
    }

    public abstract void onTextChanged(EditText editText);
}
