package com.example.chesslearning.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfessionalModel {
    public ProfessionalModel(){

    }
    private String born;
    private String country;
    private String explanation;
    private String id;
    private String image;
    private String name;
    private String peakRate;
    private ArrayList<String> bestGames;

    public ArrayList<String> getBestGames() {
        return bestGames;
    }

    public void setBestGames(ArrayList<String> bestGames) {
        this.bestGames = bestGames;
    }

    public String getBorn() {
        return born;
    }
    public void setBorn(String born) {
        this.born = born;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPeakRate() {
        return peakRate;
    }
    public void setPeakRate(String peakRate) {
        this.peakRate = peakRate;
    }
}
