package com.tanagent.brian.vtrainer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brian on 2/18/17.
 */

public class Exercise {
    private Random random = new Random();
    private ArrayList<String> exercises;
    private String exercise;
    private int photoID;

    public Exercise() {}

    public Exercise(ArrayList<String> exercises) {
        this.exercises = exercises;
    }

    public Exercise(String exercise, int photoID) {
        this.exercise = exercise;
        this.photoID = photoID;
    }

    public void setExercises(ArrayList<String> exercises) {
        this.exercises = exercises;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public ArrayList<String> getExercises() {
        return exercises;
    }

    public int getSize() {
        return exercises.size();
    }

    public String randomExercise() {
        return exercises.get(random.nextInt(getSize()));
    }
}
