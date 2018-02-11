package com.example.android.aussierules;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* initialize data for team 1 */
    int scoreTeam1;
    int numOfTryTeam1;
    int goalsTeam1;
    int behindsTeam1;
    int rBehindsTeam1;
    int behinds1;

    /* initialize data for team 2 */
    int scoreTeam2;
    int numOfTryTeam2;
    int goalsTeam2;
    int behindsTeam2;
    int rBehindsTeam2;
    int behinds2;

    /* data for game play */
    int numOfPlays = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetData();
        updateEverything();
        removeBallIcon(R.id.ballIconTeam1);
        removeBallIcon(R.id.ballIconTeam2);
        disablePlayButton();
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

    private void setBallIcon(Integer teamId){
        ImageView ballIcon = (ImageView)findViewById(teamId);
        ballIcon.setVisibility(View.VISIBLE);
    }

    private void removeBallIcon(Integer teamId){
        ImageView ballIcon = (ImageView)findViewById(teamId);
        ballIcon.setVisibility(View.INVISIBLE);
    }

    private void disablePlayButton(){
        Button btn = (Button) findViewById(R.id.playButton);
        btn.setEnabled(false);
    }

    private void enablePlayButton(){
        Button btn = (Button) findViewById(R.id.playButton);
        btn.setEnabled(true);
    }

    private void enableNewGameButton(){
        Button btn = (Button)findViewById(R.id.reset);
        btn.setEnabled(true);
    }

    private void determineScore(){
        behinds1 = rBehindsTeam2 + behindsTeam1;
        scoreTeam1 = behinds1 + goalsTeam1 * 6;
        behinds2 = rBehindsTeam1 + behindsTeam2;
        scoreTeam2 = behinds2 + goalsTeam2 * 6;
    }

    private void setBold(Integer s1) {
        TextView scoreBold = (TextView) findViewById(s1);
        scoreBold.setTypeface(null, Typeface.BOLD);

    }

    private void setNormal(Integer s2){
        TextView scoreNormal = (TextView)findViewById(s2);
        scoreNormal.setTypeface(null, Typeface.NORMAL);
    }

    private void makeVisible(Integer v1){
        TextView visibile = (TextView)findViewById(v1);
        visibile.setVisibility(View.VISIBLE);
    }

    private void makeInvisible(Integer i1){
        TextView invisibile = (TextView)findViewById(i1);
        invisibile.setVisibility(View.INVISIBLE);
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
        behinds1 = 0;

        /* data for team 2 */
        scoreTeam2 = 0;
        numOfTryTeam2 = 5;
        goalsTeam2 = 0;
        behindsTeam2 = 0;
        rBehindsTeam2 = 0;
        behinds2 = 0;
    }

    private void updateEverything() {
        determineScore();

        /* update team1 stats */
        updateDisplay(R.id.scoreTeam1, scoreTeam1);
        updateDisplay(R.id.goalsTeam1, goalsTeam1);
        updateDisplay(R.id.behindsTeam1, behindsTeam1);
        updateDisplay(R.id.rBehindsTeam1, rBehindsTeam1);

        /* update team2 stats */
        updateDisplay(R.id.scoreTeam2, scoreTeam2);
        updateDisplay(R.id.goalsTeam2, goalsTeam2);
        updateDisplay(R.id.behindsTeam2, behindsTeam2);
        updateDisplay(R.id.rBehindsTeam2, rBehindsTeam2);

        /* disable play button when there are no Trys left */
        if(numOfPlays == 10 ) {
            disablePlayButton();

            /* update score display to indicate game is over */
            String team1 = goalsTeam1 + "." + behinds1 + "(" + scoreTeam1 + ")";
            String team2 = goalsTeam2 + "." + behinds2 + "(" + scoreTeam2 + ")";
            updateDisplayString(R.id.scoreTeam1, team1);
            updateDisplayString(R.id.scoreTeam2, team2);
            setBallIcon(R.id.ballIconTeam1);
            setBallIcon(R.id.ballIconTeam2);

            if(scoreTeam1 > scoreTeam2){
                setBold(R.id.scoreTeam1);
                setNormal(R.id.scoreTeam2);

            }else if(scoreTeam2 > scoreTeam1){
                setBold(R.id.scoreTeam2);
                setNormal(R.id.scoreTeam1);
            }else {
                setBold(R.id.scoreTeam1);
                setBold(R.id.scoreTeam2);
            }
        }
    }

    public void newGame(View v) {
        resetData();
        updateEverything();
        enablePlayButton();
        setBallIcon(R.id.ballIconTeam1);
        removeBallIcon(R.id.ballIconTeam2);
        updateDisplay(R.id.numOfTries, numOfTryTeam1);
        makeInvisible(R.id.gameOver);
        makeVisible(R.id.numOfTries);
    }

    public void runPlay(View v) {
        Random r = new Random();
        int playResult = r.nextInt(10 - 0) + 0;

        numOfPlays = numOfPlays + 1;

        if (numOfPlays >= 1 && numOfPlays <= 5) {
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
            updateDisplay(R.id.numOfTries, numOfTryTeam1);

            if(numOfTryTeam1 == 0){
                removeBallIcon(R.id.ballIconTeam1);
                setBallIcon(R.id.ballIconTeam2);
                updateDisplay(R.id.numOfTries, numOfTryTeam2);
            }
        }

        if (numOfPlays >= 6 && numOfPlays <= 10) {
         removeBallIcon(R.id.ballIconTeam1);
         setBallIcon(R.id.ballIconTeam2);
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
            updateDisplay(R.id.numOfTries, numOfTryTeam2);

            if(numOfTryTeam2 == 0){
                makeVisible(R.id.gameOver);
                makeInvisible(R.id.numOfTries);
            }
        }
    }
}
