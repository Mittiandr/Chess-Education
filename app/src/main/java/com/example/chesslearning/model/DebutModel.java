package com.example.chesslearning.model;

import com.example.chesslearning.enums.DebutType;

public class DebutModel{
    private String image;
    private String nameDebut;
    private String difficulties;
    private String explanation;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
