package com.motty.motz.concentrationgamelab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class addPlayer extends GenericActivity {
    EditText name_of_player;
    int currPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        startMenu();

        name_of_player = (EditText) findViewById(R.id.add_player_field);
        currPlayer = players.getNumPlayers()+1;
        name_of_player.setText("Player " + currPlayer);

    }

    public void addPlayer(View view) {
        players.addNewPlayer(new Player(name_of_player.getText().toString(), 0));

        Intent intent = new Intent(addPlayer.this, SelectPlayers.class);
        startActivity(intent);
    }
}
