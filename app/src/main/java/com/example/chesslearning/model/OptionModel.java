package com.example.chesslearning.model;

import java.util.ArrayList;

public class OptionModel {
    private String image;
    private String name;
    private String difficulties;
    private String explanation;
    private String id;
    private ArrayList <StepModel> steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private OptionModel(){

    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public String getDifficulties() {
        return difficulties;
    }

    public String getExplanation() {
        return explanation;
    }
}
