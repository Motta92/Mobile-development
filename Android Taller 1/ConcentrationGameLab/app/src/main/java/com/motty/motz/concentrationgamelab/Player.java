package com.motty.motz.concentrationgamelab;

/**
 * Created by Carlos on 3/6/2016.
 */
public class Player {
    private String name;
    private int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
