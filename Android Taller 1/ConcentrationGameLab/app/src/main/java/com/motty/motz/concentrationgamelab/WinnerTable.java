package com.motty.motz.concentrationgamelab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WinnerTable extends GenericActivity {
    TextView winner;
    TableLayout LosersLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_table);

        // inherited menu startup function
        startMenu();

        magicLogic420.obtainBestScores(players.getPlayerList());

        winner = (TextView) findViewById(R.id.winner_field);
        winner.setText(players.getPlayerList().get(0).getName()  + " : " + players.getPlayerList().get(0).getScore() + " Points!");
        LosersLayout = (TableLayout) findViewById(R.id.losers_layout);

        fillLosersList();
    }

    void fillLosersList(){
        if(players.getNumPlayers() > 1){
            for(int i=1; i<players.getNumPlayers(); ++i){
                TextView tempTextView = new TextView(this);
                tempTextView.setText(players.getPlayerList().get(i).getName() + " : " + players.getPlayerList().get(i).getScore() + " Points!");
                LosersLayout.addView(tempTextView);
            }
        }
    }

    public void resetButton(View view) {
        //clearBoard();
        Intent intent = new Intent(WinnerTable.this, SelectPlayers.class);

        startActivity(intent);
        magicLogic420.restartGame(players.getPlayerList());


    }

    public void replayButton(View view) {
        //clearBoard();
        Intent intent = new Intent(WinnerTable.this, GameScreen.class);
        startActivity(intent);
        magicLogic420.resetGame(players.getPlayerList());
    }
}
