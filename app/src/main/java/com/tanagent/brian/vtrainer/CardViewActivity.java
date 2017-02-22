package com.tanagent.brian.vtrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/19/17.
 */

public class CardViewActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<ExerciseData> exerciseDataList;
    private ArrayList<Exercise> exerciseList;
    private ArrayList<String> selectedExerciseList;
    private ArrayList<Integer> selectedExerciseImage;

    private Button btnSelection;

    private ArrayList<Exercise> exercises = new ArrayList<Exercise>(){{
        add(new Exercise("Push Up", R.drawable.push_up));
        add(new Exercise("Sit Up", R.drawable.sit_up));
        add(new Exercise("Chin Up", R.drawable.chin_up));
        add(new Exercise("Pull Up", R.drawable.pull_up));
        add(new Exercise("Planks", R.drawable.planks));
        add(new Exercise("Lunge", R.drawable.lunges));
        add(new Exercise("Overhead Press", R.drawable.overhead_press));
        add(new Exercise("Fly", R.drawable.fly));
        add(new Exercise("Reverse Fly", R.drawable.reverse_fly));
        add(new Exercise("Squat", R.drawable.squat));
        add(new Exercise("Leg Curl", R.drawable.leg_curl));
        add(new Exercise("Row", R.drawable.rows));
        add(new Exercise("Bench Press", R.drawable.bench_press));
        add(new Exercise("Crunch", R.drawable.crunches));
        add(new Exercise("Russian Twist", R.drawable.russian_twist));
        add(new Exercise("Hyperextension", R.drawable.hyperextension));
        add(new Exercise("Leg Extension", R.drawable.leg_extension));
        add(new Exercise("Calf Raises", R.drawable.calf_raise));
        add(new Exercise("Dip", R.drawable.dips));
        add(new Exercise("Leg Press", R.drawable.leg_press));
        add(new Exercise("Burpee", R.drawable.burpee));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_view);
        btnSelection = (Button) findViewById(R.id.exercise_btn);

        exerciseDataList = new ArrayList<ExerciseData>();
        selectedExerciseList = new ArrayList<>();
        selectedExerciseImage = new ArrayList<>();
        Exercise ex = new Exercise();
        exerciseList = exercises;

        for(Exercise exercise : exerciseList) {
            ExerciseData exerciseData = new ExerciseData(exercise, false);
            exerciseDataList.add(exerciseData);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new CardViewDataAdapter(exerciseDataList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        btnSelection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<ExerciseData> exList = ((CardViewDataAdapter) mAdapter)
                        .getStudentist();

                for(ExerciseData selectedExercises : exList) {
                    ExerciseData singleExerciseData = selectedExercises;
                    if (singleExerciseData.isSelected()) {
                        data = data + "\n" + singleExerciseData.getExerciseName().toString();
                        selectedExerciseList.add(singleExerciseData.getExerciseName().toString());
                        selectedExerciseImage.add(singleExerciseData.getExercisePhotoId());
                    }
                }

                if(!selectedExerciseImage.isEmpty()) {
                    Toast.makeText(CardViewActivity.this,
                            "Selected Exercises: \n" + data, Toast.LENGTH_SHORT)
                            .show();

                    Intent intent = new Intent(CardViewActivity.this, TrainingActivity.class);
                    intent.putExtra("timer", getIntent().getStringExtra("timer"));
                    intent.putExtra("rest", getIntent().getStringExtra("rest"));
                    intent.putExtra("period", getIntent().getStringExtra("period"));
                    intent.putExtra("list", selectedExerciseList);
                    intent.putExtra("image", selectedExerciseImage);

                    startActivity(intent);
                } else {
                    Toast.makeText(CardViewActivity.this, "Please select at least one exercise", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
