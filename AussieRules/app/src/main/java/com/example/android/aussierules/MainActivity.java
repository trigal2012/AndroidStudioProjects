package com.example.android.aussierules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* initialize data for team 1 */
    int scoreTeam1;
    int numOfTryTeam1;
    int goalsTeam1;
    int behindsTeam1;
    int rBehindsTeam1;

    /* initialize data for team 2 */
    int scoreTeam2;
    int numOfTryTeam2;
    int goalsTeam2;
    int behindsTeam2;
    int rBehindsTeam2;

    /* data for game play */
    int numOfPlays = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetData();
        updateEverything();
    }

    /* method used to update display with integer values */
    public void updateDisplay(Integer team, Integer points) {
        TextView scoreView = (TextView) findViewById(team);
        scoreView.setText(String.valueOf(points));
    }

    public void updateDisplayString(Integer team, String msg) {
        TextView scoreView = (TextView) findViewById(team);
        scoreView.setText(msg);
    }

    private void resetData() {
        /* data for game play */
        numOfPlays = 0;

        /* data for team 1 */
        scoreTeam1 = 0;
        numOfTryTeam1 = 5;
        goalsTeam1 = 0;
        behindsTeam1 = 0;
        rBehindsTeam1 = 0;

        /* data for team 2 */
        scoreTeam2 = 0;
        numOfTryTeam2 = 5;
        goalsTeam2 = 0;
        behindsTeam2 = 0;
        rBehindsTeam2 = 0;
    }

    private void updateEverything() {
        /* update team1 stats */
        scoreTeam1 = rBehindsTeam2 + behindsTeam1 + goalsTeam1 * 6;
        updateDisplay(R.id.scoreTeam1, scoreTeam1);
        updateDisplay(R.id.numOfTryTeam1, numOfTryTeam1);
        updateDisplay(R.id.goalsTeam1, goalsTeam1);
        updateDisplay(R.id.behindsTeam1, behindsTeam1);
        updateDisplay(R.id.rBehindsTeam1, rBehindsTeam1);

        /* update team2 stats */
        scoreTeam2 = rBehindsTeam1 + behindsTeam2 + goalsTeam2 * 6;
        updateDisplay(R.id.scoreTeam2, scoreTeam2);
        updateDisplay(R.id.numOfTryTeam2, numOfTryTeam2);
        updateDisplay(R.id.goalsTeam2, goalsTeam2);
        updateDisplay(R.id.behindsTeam2, behindsTeam2);
        updateDisplay(R.id.rBehindsTeam2, rBehindsTeam2);

        /* disable play button when there are no Trys left */
        if(numOfPlays == 10 ) {
            Button btn = (Button) findViewById(R.id.playButton);
            btn.setEnabled(false);

            /* update score display to indicate game is over */
            String team1 = goalsTeam1 + "." + behindsTeam1 + "(" + scoreTeam1 + ")";
            String team2 = goalsTeam2 + "." + behindsTeam2 + "(" + scoreTeam2 + ")";
            updateDisplayString(R.id.scoreTeam1, team1);
            updateDisplayString(R.id.scoreTeam2, team2);
        }
    }

    public void newGame(View v) {
        resetData();
        updateEverything();
        Button btn = (Button) findViewById(R.id.playButton);
        btn.setEnabled(true);
    }

    public void runPlay(View v) {
        Random r = new Random();
        int playResult = r.nextInt(10 - 0) + 0;
        Log.i("my tag", "random number is: " + playResult);

        numOfPlays = numOfPlays + 1;

        if (numOfPlays >= 1 && numOfPlays < 6) {
            if (playResult == 1) {
                rBehindsTeam1 = rBehindsTeam1 + 1;
            }
            if (playResult == 3) {
                behindsTeam1 = behindsTeam1 + 1;
            }
            if (playResult == 5) {
                goalsTeam1 = goalsTeam1 + 1;
            }
            numOfTryTeam1 = numOfTryTeam1 - 1;
            updateEverything();
        }

        if (numOfPlays >= 6 && numOfPlays <= 10) {
            if (playResult == 1) {
                rBehindsTeam2 = rBehindsTeam2 + 1;
            }
            if (playResult == 3) {
                behindsTeam2 = behindsTeam2 + 1;
            }
            if (playResult == 5) {
                goalsTeam2 = goalsTeam2 + 1;
            }
            numOfTryTeam2 = numOfTryTeam2 - 1;
            updateEverything();
        }

    }
}
