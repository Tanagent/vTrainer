package com.tanagent.brian.vtrainer;

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

public class CardViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<ExerciseData> exerciseDataList;
    private ArrayList<String> exerciseList;
    private ArrayList<String> selectedExerciseList;

    private Button btnSelection;

    private ArrayList<String> exercises = new ArrayList<String>(){{
        add("Push Up");
        add("Sit Up");
        add("Chin Up");
        add("Pull Up");
        add("Planks");
        add("Lunge");
        add("Overhead Press");
        add("Fly");
        add("Reverse Fly");
        add("Squat");
        add("Leg Curl");
        add("Row");
        add("Bench Press");
        add("Crunch");
        add("Russian Twist");
        add("Hyperextension");
        add("Leg Extension");
        add("Calf Raises");
        add("Dip");
        add("Leg Press");
        add("Burpee");
    }};

//    private ArrayList<Exercise> exercises = new ArrayList<Exercise>(){{
//        add(new Exercise("Push Up", R.drawable.push_up));
//        add(new Exercise("Sit Up", R.drawable.push_up));
//        add(new Exercise("Chin Up", R.drawable.push_up));
//        add(new Exercise("Pull Up", R.drawable.push_up));
//        add(new Exercise("Planks", R.drawable.push_up));
//        add(new Exercise("Lunge", R.drawable.push_up));
//        add(new Exercise("Overhead Press", R.drawable.push_up));
//        add(new Exercise("Fly", R.drawable.push_up));
//        add(new Exercise("Reverse Fly", R.drawable.push_up));
//        add(new Exercise("Squat", R.drawable.push_up));
//        add(new Exercise("Leg Curl", R.drawable.push_up));
//        add(new Exercise("Row", R.drawable.push_up));
//        add(new Exercise("Bench Press", R.drawable.push_up));
//        add(new Exercise("Crunch", R.drawable.push_up));
//        add(new Exercise("Russian Twist", R.drawable.push_up));
//        add(new Exercise("Hyperextension", R.drawable.hyperextension));
//        add(new Exercise("Leg Extension", R.drawable.leg_extension));
//        add(new Exercise("Calf Raises", R.drawable.push_up));
//        add(new Exercise("Dip", R.drawable.push_up));
//        add(new Exercise("Leg Press", R.drawable.push_up));
//        add(new Exercise("Burpee", R.drawable.push_up));
//    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_view);
        btnSelection = (Button) findViewById(R.id.exercise_btn);

        exerciseDataList = new ArrayList<ExerciseData>();
        selectedExerciseList = new ArrayList<>();
        Exercise ex = new Exercise();
        exerciseList = exercises;

        Log.i("timer", getIntent().getStringExtra("timer"));
        Log.i("rest", getIntent().getStringExtra("rest"));


        for(String exercise : exerciseList) {
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
                        data = data + "\n" + singleExerciseData.getName().toString();
                        selectedExerciseList.add(singleExerciseData.getName().toString());
                    }
                }

                Toast.makeText(CardViewActivity.this,
                        "Selected Exercises: \n" + data, Toast.LENGTH_SHORT)
                        .show();

                Intent intent = new Intent(CardViewActivity.this, TrainingActivity.class);
                intent.putExtra("timer", getIntent().getStringExtra("timer"));
                intent.putExtra("rest", getIntent().getStringExtra("rest"));
                intent.putExtra("list", selectedExerciseList);

                startActivity(intent);
            }
        });

    }
}
