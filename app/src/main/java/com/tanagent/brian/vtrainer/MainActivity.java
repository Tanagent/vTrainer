package com.tanagent.brian.vtrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editRest;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_seconds);
        editRest = (EditText) findViewById(R.id.edit_rest);
        buttonNext = (Button) findViewById(R.id.next_btn);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timer = editText.getText().toString();
                String rest = editRest.getText().toString();

                if(!timer.equalsIgnoreCase("") && !rest.equalsIgnoreCase("")) {
                    Intent intent = new Intent(MainActivity.this, CardViewActivity.class);
                    intent.putExtra("timer", timer);
                    intent.putExtra("rest", rest);
                    startActivity(intent);
                } else if(!timer.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Please enter Time.", Toast.LENGTH_SHORT).show();
                } else if(!rest.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Please enter Rest.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter Time and Rest", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
