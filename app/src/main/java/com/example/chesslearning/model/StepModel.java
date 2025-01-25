package com.example.chesslearning.model;

public class StepModel {
    private String image;
    private String explanation;
    private String name;
    private String idDebut;
    private int stepNum;


    public StepModel(){}
    public String getImage() {
        return image;
    }

    public String getIdDebut() {return idDebut;}

    public int getStepNum() {return stepNum;}

    public String getExplanation() {
        return explanation;
    }
    public String getName() {
        return name;
    }
}
