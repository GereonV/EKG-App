package com.egk.EGK_App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private void onButtonClick() {

    }

    /**
     * returns random number from span
     * @param min kleinste Zahl (inklusive)
     * @param max größte Zahl (inklusive)
     */
    private int rngFromSpan(int min, int max) throws Exception {
        if(max <= min) throw new Exception("max-min Verhältnis");
        Random random = new Random();
        return min + random.nextInt(max - min +1);
    }
}