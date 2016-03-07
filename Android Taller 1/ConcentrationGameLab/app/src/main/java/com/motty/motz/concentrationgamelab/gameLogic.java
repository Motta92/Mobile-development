package com.motty.motz.concentrationgamelab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Carlos on 3/6/2016.
 */
public class gameLogic implements Comparator<Player> {

    private int currTurn;
    private int card;
    private int id_button1;
    private int id_button2;
    private int totalFlips;

    gameLogic(){
        this.currTurn = 0;
        this.card = 1;
        this.totalFlips = 0;
    }

    public void setTotalFlips(int flips){
        this.totalFlips = flips;
    }

    public int getTotalFlips(){
        return this.totalFlips;
    }

    public void setIdButton1(int id_button1){
        this.id_button1 = id_button1;
    }

    public int getIdButton1(){
        return this.id_button1;
    }

    public void setIdButton2(int id_button2){
        this.id_button2 = id_button2;
    }

    public int getIdButton2(){
        return this.id_button2;
    }

    public void setCard(int card){
        this.card = card;
    }

    public int getCard(){
        return this.card;
    }

    public void setCurrTurn(int currTurn){
        this.currTurn = currTurn;
    }

    public int getCurrTurn(){
        return this.currTurn;
    }

    public boolean match(int id1, int id2){
        if(id1 == id2){
            return true;
        }
        else{
            return false;
        }
    }

    public int compare(Player p1, Player p2){
        return p2.getScore() - p1.getScore();
    }

    public void nextTurn(ArrayList<Player> players, int newTurn){
        if(newTurn % players.size() == 0){
            currTurn = 0;
        }
        else{
            currTurn = newTurn;
        }
    }

    public void scorePoint(Player player){
        player.setScore(player.getScore() + 1);
    }

    public void obtainBestScores(ArrayList<Player> players){
        Collections.sort(players, new gameLogic());
    }

    public void resetGame(ArrayList<Player> players){
        this.currTurn = 0;
        this.card = 1;
        this.totalFlips = 0;
        for(int i=0; i<players.size(); ++i){
            players.get(i).setScore(0);
        }
    }

    public void restartGame(ArrayList<Player> players){
        this.currTurn = 0;
        this.card = 1;
        this.totalFlips = 0;
        players.clear();
    }

}
