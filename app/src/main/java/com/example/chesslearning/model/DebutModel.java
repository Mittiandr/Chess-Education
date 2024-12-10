package com.example.chesslearning.model;

import com.example.chesslearning.enums.DebutType;

public class DebutModel {
    private String image;
    private String nameDebut;
    private String difficulties;
    private String explanation;

    public DebutModel(String image, String nameDebut, DebutType difficulties, String explanation) {
        this.image = image;
        this.nameDebut = nameDebut;
        this.difficulties = difficulties.name();
        this.explanation = explanation;
    }
    private DebutModel(){

    }

    public String getImage() {
        return image;
    }

    public String getNameDebut() {
        return nameDebut;
    }

    public String getDifficulties() {
        return difficulties;
    }

    public String getExplanation() {
        return explanation;
    }
}
