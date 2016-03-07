package com.motty.motz.concentrationgamelab;

import java.util.ArrayList;

/**
 * Created by Carlos on 3/6/2016.
 */
public class PlayersHandler {
    private ArrayList<Player> playerList;

    PlayersHandler(){
        playerList = new ArrayList<Player>();
    }

    public ArrayList<Player> getPlayerList(){
        return this.playerList;
    }

    public void addNewPlayer(Player player){
        playerList.add(player);
    }

    public int getNumPlayers(){
        return playerList.size();
    }
}
