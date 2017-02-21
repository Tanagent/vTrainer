package com.tanagent.brian.vtrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Brian on 2/19/17.
 */

public class TrainingActivity extends AppCompatActivity {
    private TextView timerText;
    private TextView exerciseText;
    private ImageView imageView;
    private Button startButton;
    private Button cancelButton;
    private Button pauseButton;

    private CountDownTimer countDownTimer;
    private Exercise exercise;

    private ArrayList<String> exerciseList;
    private ArrayList<Integer> imageList;
    private Random random = new Random();
    private int secondsLeft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.training_session);

        timerText = (TextView) findViewById(R.id.seconds_text);
        exerciseText = (TextView) findViewById(R.id.exercise_text);
        imageView = (ImageView) findViewById(R.id.exercise_img);
        startButton = (Button) findViewById(R.id.start_btn);
        cancelButton = (Button) findViewById(R.id.cancel_btn);
//        pauseButton = (Button) findViewById(R.id.pause_btn);
        exerciseList = getIntent().getStringArrayListExtra("list");
        imageList = getIntent().getIntegerArrayListExtra("image");


        timerText.setText(getIntent().getStringExtra("timer"));

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String timer = getIntent().getStringExtra("timer");
                final String periodLength = getIntent().getStringExtra("period");
                final String rest = getIntent().getStringExtra("rest");

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
                                timerText.setText("seconds: " + (int) (millisUntilFinished / 1000));
                                secondsLeft = (int) (millisUntilFinished / 1000);
                                if (count <= Integer.valueOf(periodLength)) {
                                    count++;
                                    Log.i("count", String.valueOf(count));
                                    if (count == 1) {
                                        int num = random.nextInt(exerciseList.size());
                                        exerciseText.setText(exerciseList.get(num));
                                        imageView.setImageResource(imageList.get(num));
                                    }
                                } else if (count > Integer.valueOf(periodLength) && count < (Integer.valueOf(periodLength) + Integer.valueOf(rest))) {
                                    count++;
                                    Log.i("count", String.valueOf(count));
                                    exerciseText.setText("REST");
                                    imageView.setImageResource(R.drawable.rest);
                                } else {
                                    count = 0;
                                    Log.i("count", String.valueOf(count));
                                }
                            }

                            @Override
                            public void onFinish() {
                                timerText.setText("FINISHED");
                            }

                        }.start();
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }

                timerText.setText(getIntent().getStringExtra("timer"));
            }
        });

//        pauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
