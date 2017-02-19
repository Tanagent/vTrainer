package com.tanagent.brian.vtrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonStart;
    private Button buttonCancel;
    private Button buttonPause;
    private TextView textView;
    private TextView exerciseText;
    private CountDownTimer countDownTimer;
    private boolean isPaused = false;
    private int secondsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_seconds);
        buttonStart = (Button) findViewById(R.id.start_btn);
        buttonPause = (Button) findViewById(R.id.pause_btn);
        buttonCancel = (Button) findViewById(R.id.cancel_btn);
        textView = (TextView) findViewById(R.id.seconds_text);
        exerciseText = (TextView) findViewById(R.id.exercise_text);

        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(!text.equalsIgnoreCase("")) {
                    int seconds = 0;
                    if(countDownTimer == null) {
                        seconds = Integer.valueOf(text);
                    } else {
                        seconds = secondsLeft;
                    }
                    if(countDownTimer == null) {
                        countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                            int count = 0;
                            @Override
                            public void onTick(long millisUntilFinished) {
                                textView.setText("seconds: " + (int) (millisUntilFinished / 1000));
                                secondsLeft = (int) (millisUntilFinished / 1000);
                                if (count <= 15) {
                                    count++;
                                    Log.i("count", String.valueOf(count));
                                    exerciseText.setText("DO SOMETHING");
                                } else if (count > 15 && count <= 20) {
                                    count++;
                                    Log.i("count", String.valueOf(count));
                                    exerciseText.setText("REST");
                                } else {
                                    count = 0;
                                    Log.i("count", String.valueOf(count));
                                }
                            }

                            @Override
                            public void onFinish() {
                                textView.setText("FINISHED");
                            }

                        }.start();
                    }
                }
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer != null) {
                    if (isPaused != false) {
                        buttonPause.setText("PAUSE");
                        isPaused = false;
                    } else {
                        buttonPause.setText("RESUME");
                        isPaused = true;
                    }
                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }

                textView.setText("SECONDS");
            }
        });
    }
}
