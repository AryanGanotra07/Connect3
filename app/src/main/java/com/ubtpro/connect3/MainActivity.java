package com.ubtpro.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;

    boolean gameIsActive=true;

    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};





    public void dropIn(View view) {
        ImageView counter = (ImageView) view;




        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotationXBy(360).setDuration(200);
            for(int[] winningPosition:winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2){
                    String winner="red";
                    gameIsActive = false;
                    if(gameState[winningPosition[0]]==0){
                        winner="yellow";
                    }


                    TextView winnerMessage= (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" Has won!!");

                    LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);



                }
                else{
                    boolean gameIsOver=true;
                    for(int counterstate:gameState){
                        if(counterstate==2){
                            gameIsOver=false;
                        }
                    }
                    if (gameIsOver){
                        TextView winnerMessage= (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Its a draw");

                        LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }



        }

    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
