package com.tanagent.brian.vtrainer;

import java.io.Serializable;

/**
 * Created by Brian on 2/19/17.
 */

public class ExerciseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int photoId;
    private boolean isSelected;
    private Exercise exercise;

    public ExerciseData() {

    }

    public ExerciseData(String name, int photoId) {

        this.name = name;
        this.photoId = photoId;

    }

    public ExerciseData(String name, boolean isSelected) {

        this.name = name;
        this.isSelected = isSelected;
    }

    public ExerciseData(Exercise exercise, boolean isSelected) {
        this.exercise = exercise;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getExerciseName() {
        return exercise.getExercise();
    }

    public int getExercisePhotoId() {
        return exercise.getPhotoID();
    }
}
