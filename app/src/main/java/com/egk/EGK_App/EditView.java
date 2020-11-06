package com.egk.EGK_App;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * custom EditText
 * @author Gereon
 * @version 1
 * @see EditText
 * @see androidx.appcompat.widget.AppCompatEditText
 * @see TextWatcher
 */
public class EditView extends androidx.appcompat.widget.AppCompatEditText implements TextWatcher {

    private TextWatcherInterface watcher;

    public EditView(@NonNull Context context) {super(context);}

    public EditView(@NonNull Context context, @Nullable AttributeSet attrs) {super(context, attrs);}

    public EditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

    @NonNull
    @Override
    public Editable getText() {return super.getText();}

    /**
     * Interface with method called after Text changed
     * @author Gereon
     * @version 1
     * @see TextWatcher
     */
    public interface TextWatcherInterface {
        void onTextChanged(EditText editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        watcher.onTextChanged(this);
    }

    /**
     * calls method when text changes
     * @param watcher method to be excecuted
     */
    public void setOnTextChangedListener(TextWatcherInterface watcher) {
        if(this.watcher != null) removeTextChangedListener(this);
        this.watcher = watcher;
        addTextChangedListener(this);
    }

    public boolean isEmpty() {
        return getText().toString().isEmpty();
    }
}
