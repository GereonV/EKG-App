package com.egk.EGK_App;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Gereon
 * @see EditView
 * @see Toaster
 * @see Keyboard
 * @see RandomNumberGenerator
 */
public class MainActivity extends AppCompatActivity {

    RandomNumberGenerator generator;
    Toaster toaster;

    EditView editViewMin;
    EditView editViewMax;
    TextView textView;
    Button button;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generator = new RandomNumberGenerator();    //creates new Generator
        toaster = new Toaster(this);    //creates new Toast-Helper
        initViews();
    }

    /**
     * assigns a value to all layout-variables and adds EventListeners
     */
    private void initViews() {
        textView = findViewById(R.id.textView);
        editViewMin = findViewById(R.id.editTextMin);
        editViewMax = findViewById(R.id.editTextMax);
        button = findViewById(R.id.button);
        switchCompat = findViewById(R.id.switchCompat);

        editViewMin.setOnTextChangedListener(this::onTextChanged);  //calls onTextChanged-method when Text changes
        editViewMax.setOnTextChangedListener(this::onTextChanged);  //calls onTextChanged-method when Text changes
        button.setOnClickListener(view -> onButtonClick()); //calls onButtonClick-method if clicked
    }

    /**
     * shortens the input if 10th character is input, shows Toast
     * @param editText editText to be controlled
     */
    private void onTextChanged(EditText editText) {
        String input = editText.getText().toString();   //gets text from EditText
        if(input.length() > 9) {    //if input is too long
            int selection = Math.min(editText.getSelectionStart(), 9);   //get cursor position, maxes out at 9
            editText.setText(input.substring(0, 9));    //deletes last character
            editText.setSelection(selection);   //sets cursor to previous position
            toaster.show(R.string.tooLongInputMessage, Toast.LENGTH_SHORT, true);   //shows error as Toast
        }
    }

    /**
     * fill TextView with random number in between the ones in EditTexts (min defaults to 1) or throws Error through Toast-Message
     */
    private void onButtonClick() {
        textView.setText(null); //clears textView's content

        if(!editViewMax.isEmpty()) {    //if a max value is specified
            int min = editViewMin.isEmpty() ? 1 : Integer.parseInt(editViewMin.getText().toString());   //set min to input, defaults to one
            int max = Integer.parseInt(editViewMax.getText().toString());   //sets max to input
            if(min >= max) {    //if max isn't bigger than min
                toaster.show(R.string.maxminRatioMessage, Toast.LENGTH_SHORT, true);    //shows Toast with Error to User
                Keyboard.show(editViewMax); //opens max input
            } else {    //if correct inputs
                textView.setText(String.valueOf(generator.nextRandomNumber(min, max, switchCompat.isChecked()))); //set Text to a random number
                Keyboard.hide(this);    //hides Keyboard
            }
        } else {    //if no max value is specified
            toaster.show(R.string.emptyInputMessage, Toast.LENGTH_SHORT, true); //shows Error to User
            Keyboard.show(editViewMax); //opens max input
        }
    }
}