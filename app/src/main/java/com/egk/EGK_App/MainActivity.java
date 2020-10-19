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

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editTextMin;
    EditText editTextMax;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        textView = findViewById(R.id.textView);
        editTextMin = findViewById(R.id.editTextMin);
        editTextMax = findViewById(R.id.editTextMax);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> onButtonClick());
    }

    /**
     * fill TextView with random number in between the ones in EditTexts (min defaults to 1) or throws Error through Toast-Message
     */
    private void onButtonClick() {
        hideKeyboard();

        String minString = editTextMin.getText().toString();
        String maxString = editTextMax.getText().toString();
        if(!maxString.isEmpty()) {
            int min = minString.isEmpty() ? 1 : Integer.parseInt(minString);
            int max = Integer.parseInt(maxString);
            try {
                int randomNumber = rngFromSpan(min, max);
                String output = String.valueOf(randomNumber);
                textView.setText(output);
            }
            catch(Exception e) {toast(e.getMessage(), Toast.LENGTH_SHORT);}
        }
        else toast(getString(R.string.emptyInputMessage), Toast.LENGTH_SHORT);
    }

    /**
     * returns random number from span
     * @param min smallest possible outcome (inclusive)
     * @param max biggest possible outcome (inclusive)
     */
    private int rngFromSpan(int min, int max) throws Exception {
        if(max <= min) throw new Exception(getString(R.string.maxmin));
        Random random = new Random();
        return min + random.nextInt(max - min +1);
    }

    /**
     * shows a Toast-message
     * @param message the message of the Toast
     * @param length the length the Toast should be shown (Toast constants)
     */
    private void toast(String message, int length) {Toast.makeText(this, message, length).show();}

    /**
     * hides Keyboard and clearsFocus
     */
    private void hideKeyboard() {
        View v = getCurrentFocus();
        if(v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if(imm != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        }
    }
}