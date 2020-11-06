package com.egk.EGK_App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Gereon
 * @see EditView
 * @see Toaster
 */
public class MainActivity extends AppCompatActivity {

    RandomNumberGenerator generator;
    Toaster toaster;

    EditView editViewMin;
    EditView editViewMax;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generator = new RandomNumberGenerator();
        toaster = new Toaster(this);
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
            editText.setText(input.substring(0, 9));    //deletes last character
            editText.setSelection(9);   //sets cursor to last character
            toaster.show(R.string.tooLongInputMessage, Toast.LENGTH_SHORT, true);   //shows error as Toast
        }
    }

    /**
     * fill TextView with random number in between the ones in EditTexts (min defaults to 1) or throws Error through Toast-Message
     */
    private void onButtonClick() {
        textView.setText(null); //clears textView's content

        if(!editViewMax.isEmpty()) {
            int min = editViewMin.isEmpty() ? 1 : Integer.parseInt(editViewMin.getText().toString());
            int max = Integer.parseInt(editViewMax.getText().toString());
            if(min >= max) {
                toaster.show(R.string.maxminRatioMessage, Toast.LENGTH_SHORT, true);
                Keyboard.show(editViewMax);
            } else {
                textView.setText(String.valueOf(generator.nextRandomNumber(min, max)));
            }
        } else {
            toaster.show(R.string.emptyInputMessage, Toast.LENGTH_SHORT, true);
            Keyboard.show(editViewMax);
        }
    }
}