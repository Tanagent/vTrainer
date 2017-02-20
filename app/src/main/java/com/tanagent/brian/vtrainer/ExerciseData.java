package com.tanagent.brian.vtrainer;

import java.io.Serializable;

/**
 * Created by Brian on 2/19/17.
 */

public class ExerciseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String emailId;
    private boolean isSelected;
    private Exercise exercise;

    public ExerciseData() {

    }

    public ExerciseData(String name, String emailId) {

        this.name = name;
        this.emailId = emailId;

    }

    public ExerciseData(String name, boolean isSelected) {

        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
