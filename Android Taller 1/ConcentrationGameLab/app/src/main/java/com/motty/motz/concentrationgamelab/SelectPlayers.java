package com.motty.motz.concentrationgamelab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SelectPlayers extends GenericActivity {
    EditText edit_text_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);

        // inherited menu startup function
        startMenu();

        edit_text_field = (EditText) findViewById(R.id.numPlayersText);
        edit_text_field.setText(String.valueOf(players.getNumPlayers()));

    }

    public void gameScreen(View view) {
        if(players.getPlayerList().size() > 0){
            Intent myIntent = new Intent(SelectPlayers.this, GameScreen.class);
            startActivity(myIntent);
        }
        else{
            notEnoughPlayers();
        }
    }

    public void toAddPlayer(View view) {
        Intent myIntent = new Intent(SelectPlayers.this, addPlayer.class);
        startActivity(myIntent);
    }

    public void notEnoughPlayers() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.not_enough_players);
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
