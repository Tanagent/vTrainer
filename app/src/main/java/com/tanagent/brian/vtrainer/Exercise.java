package com.tanagent.brian.vtrainer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brian on 2/18/17.
 */

public class Exercise {
    private Random random = new Random();
    private ArrayList<String> exercises = new ArrayList<String>(){{
        add("Push Ups");
        add("Sit Ups");
        add("Chin Ups");
        add("Pull Ups");
    }};

    public Exercise() {}

    public Exercise(ArrayList<String> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<String> getExercises() {
        return exercises;
    }

    public String randomExercise() {
        return exercises.get(random.nextInt(4));
    }
}
