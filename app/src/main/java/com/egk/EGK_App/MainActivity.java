package com.egk.EGK_App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * @author Gereon, Leon
 * @see BasicTextWatcher
 */

public class MainActivity extends AppCompatActivity {

    Toast toast;

    EditText editTextMin;
    EditText editTextMax;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * assigns a value to all layout-variables and adds EventListeners
     */
    private void initViews() {
        textView = findViewById(R.id.textView);
        editTextMin = findViewById(R.id.editTextMin);
        editTextMax = findViewById(R.id.editTextMax);
        button = findViewById(R.id.button);

        editTextMin.addTextChangedListener(new BasicTextWatcher(editTextMin) {public void onTextChanged(EditText editText) {MainActivity.this.onTextChanged(editText);}});  //calls onTextChanged-method if input changed
        editTextMax.addTextChangedListener(new BasicTextWatcher(editTextMax) {public void onTextChanged(EditText editText) {MainActivity.this.onTextChanged(editText);}});  //calls onTextChanged-method if input changed
        button.setOnClickListener(view -> onButtonClick()); //calls onButtonClick-method if clicked
    }

    /**
     * shortens the input if 10th character is input, shows Toast
     * @param editText editText to be controlled
     */
    private void onTextChanged(EditText editText) {
        String input = editText.getText().toString();
        if(input.length() > 9) {    //if input is too long
            editText.setText(input.substring(0, 9));    //deletes last character
            editText.setSelection(9);   //sets cursor to last character
            toast(getString(R.string.tooLongInputMessage), Toast.LENGTH_SHORT);
        }
    }

    /**
     * fill TextView with random number in between the ones in EditTexts (min defaults to 1) or throws Error through Toast-Message
     */
    private void onButtonClick() {
        textView.setText(null); //clears textView's content
        String minString = editTextMin.getText().toString();
        String maxString = editTextMax.getText().toString();
        if(!maxString.isEmpty()) {  //if a maximum is defined
            int min = minString.isEmpty() ? 1 : Integer.parseInt(minString);    //sets min to input, or to 1 if no minimum is defined
            int max = Integer.parseInt(maxString);
            try {
                textView.setText(String.valueOf(rngFromSpan(min, max)));    //sets textView's content to a random number
                hideKeyboard(); //calls hideKeyboard-method if generating a random number succeeded
            }
            catch(Exception e) {toast(e.getMessage(), Toast.LENGTH_LONG);}  //shows a long Toast if generating a random number failed
        } else {    //if maximum isn't defined
            toast(getString(R.string.emptyInputMessage), Toast.LENGTH_SHORT);
            showKeyboard(editTextMax);
        }
    }

    /**
     * @return random number from span
     * @param min smallest possible outcome (inclusive)
     * @param max biggest possible outcome (inclusive)
     */
    private int rngFromSpan(int min, int max) throws Exception {
        if(max <= min) throw new Exception(getString(R.string.maxmin)); //throws an error if inputs are invalid
        Random random = new Random();
        return min + random.nextInt(max - min +1);  //returns random number
    }

    /**
     * shows a Toast-message if length is valid
     * @param message the message of the Toast
     * @param length the length the Toast should be shown (Toast constants)
     */
    private void toast(String message, int length) {
        if(length == Toast.LENGTH_SHORT || length == Toast.LENGTH_LONG) {   //if length is valid
            if(toast != null) toast.cancel();   //cancels previous Toast if there is one
            toast = Toast.makeText(this, message, length);  //creates Toast based on inputs, replaces canceled Toast
            toast.show();   //shows the new Toast
        }
    }

    /**
     * hides Keyboard and clearsFocus
     */
    private void hideKeyboard() {
        View v = getCurrentFocus();
        if(v != null) { //if something is focused
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);   //gets InputMethodManager from Activity
            if(imm != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //hides Keyboard
            v.clearFocus();
        }
    }

    /**
     * focuses EditText and shows Keyboard
     * @param editText editText to focus
     */
    private void showKeyboard(EditText editText) {
        editText.requestFocus();    //focuses editText
        if(editText.hasFocus()) {   //if successfull
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);   //gets InputMethodManager from Activity
            if(imm != null) imm.showSoftInput(editText.findFocus(), 0);    //show Keyboard with editText as target
        }
    }
}