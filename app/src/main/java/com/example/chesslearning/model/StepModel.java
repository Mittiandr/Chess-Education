package com.example.chesslearning.model;

public class StepModel {
    private String image;
    private String explanation;
    private String shortExplanation;


    public StepModel(){}
    public String getImage() {
        return image;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getShortExplanation() {
        return shortExplanation;
    }

    public void setShortExplanation(String shortExplanation) {
        this.shortExplanation = shortExplanation;
    }
}
