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
    private EditText editRest;
    private Button buttonStart;
    private Button buttonCancel;
    private Button buttonPause;
    private TextView textView;
    private TextView exerciseText;
    private CountDownTimer countDownTimer;
    private boolean isPaused = false;
    private int secondsLeft;
    private int periodLength = 15;

    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercise = new Exercise();

        editText = (EditText) findViewById(R.id.edit_seconds);
        editRest = (EditText) findViewById(R.id.edit_rest);
        buttonStart = (Button) findViewById(R.id.start_btn);
        buttonPause = (Button) findViewById(R.id.pause_btn);
        buttonCancel = (Button) findViewById(R.id.cancel_btn);
        textView = (TextView) findViewById(R.id.seconds_text);
        exerciseText = (TextView) findViewById(R.id.exercise_text);

        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String timer = editText.getText().toString();
                final String rest = editRest.getText().toString();

                if(!timer.equalsIgnoreCase("") && !rest.equalsIgnoreCase("")) {
                    int seconds = 0;
                    if(countDownTimer == null) {
                        seconds = Integer.valueOf(timer);
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
                                if (count <= periodLength) {
                                    count++;
                                    Log.i("count", String.valueOf(count));
                                    if (count == 1) {
                                        exerciseText.setText(exercise.randomExercise());
                                    }
                                } else if (count > periodLength && count < (periodLength + Integer.valueOf(rest))) {
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
