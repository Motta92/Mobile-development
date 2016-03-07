package com.motty.motz.concentrationgamelab;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class GameScreen extends GenericActivity {
    TableLayout gameBoard;
    colorHandler colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // inherited menu startup function
        startMenu();

        gameBoard = (TableLayout) findViewById(R.id.game_board_layout);
        colors = new colorHandler();

        // Create a 4x4 board only functioning for a 4x4 board
        createBoardGame();
    }



    public void toWinnersTable(View view) {
        Intent intent = new Intent(GameScreen.this, WinnerTable.class);
        startActivity(intent);
    }

    public void createBoardGame(){
        int idFlag = 0;
        for(int i=0; i<4; ++i){
            TableRow tr = new TableRow(this);
            for(int j=0; j<4; ++j){
                Button tempButton = new Button(this);
                tempButton.setLayoutParams(new TableRow.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                colors.addColor(idFlag);
                tempButton.setOnClickListener(handleOnClick(tempButton));
                tempButton.setId(idFlag);
                tr.addView(tempButton);
                ++idFlag;
            }
            gameBoard.addView(tr);
        }

    }

    View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if(magicLogic420.getCard() == 1){
                    button.setBackgroundColor(colors.getCorrespondingColor(button.getId()));
                    magicLogic420.setCard(2);
                    magicLogic420.setIdButton1(button.getId());
                }
                else{
                    magicLogic420.setIdButton2(button.getId());
                    button.setBackgroundColor(colors.getCorrespondingColor(button.getId()));
                    if(magicLogic420.match(colors.getCorrespondingColor(magicLogic420.getIdButton1()), colors.getCorrespondingColor(magicLogic420.getIdButton2()))){
                        magicLogic420.scorePoint(players.getPlayerList().get(magicLogic420.getCurrTurn()));
                        magicLogic420.setCard(1);

                        // disable buttons
                        findViewById(magicLogic420.getIdButton1()).setEnabled(false);
                        button.setEnabled(false);
                        magicLogic420.setTotalFlips(magicLogic420.getTotalFlips()+1);
                        if(magicLogic420.getTotalFlips() == 8){
                            Intent intent = new Intent(GameScreen.this, WinnerTable.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        findViewById(magicLogic420.getIdButton1()).setBackgroundResource(android.R.drawable.btn_default);
                        button.setBackgroundResource(android.R.drawable.btn_default);
                        magicLogic420.setCard(1);
                        magicLogic420.nextTurn(players.getPlayerList(), magicLogic420.getCurrTurn()+1);
                    }

                }
            }
        };
    }
}
