package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* initialize variables for team points */
    int ptsA = 0;
    int ptsB = 0;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("points1", ptsA);
        outState.putInt("points2", ptsB);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ptsA = savedInstanceState.getInt("points1");
            ptsB = savedInstanceState.getInt("points2");
        }
        setContentView(R.layout.activity_main);
        updatePtsDisplay(R.id.scoreTeam1, ptsA);
        updatePtsDisplay(R.id.scoreTeam2, ptsB);

    }

    /* method called when score buttons are clicked */
    public void scoreButton(View v){

        /* initialize values for team ids, team, and points */
        int team1 = R.id.team1;
        int team2 = R.id.team2;
        int team = 0;
        int pts = 0;

        /* determine number of points to add based on button tag values */
        pts = Integer.parseInt(v.getTag().toString());

        /* figure out which team score to update by determining the
            LinearLayout parent of the button clicked,
            call the updateDisplay method with the team and points*/
        team = ((View)v.getParent()).getId();

        switch (team){
            case R.id.team1:
                team = R.id.scoreTeam1;
                ptsA = ptsA+pts;
                updatePtsDisplay(team, ptsA);
                break;

            case R.id.team2:
                team = R.id.scoreTeam2;
                ptsB = ptsB + pts;
                updatePtsDisplay(team, ptsB);
                break;
        }
    }

    /* the update method takes two arguments; the team and the score,
        then updates the display for the correct team */

    public void updatePtsDisplay(Integer team, Integer points){
        TextView scoreView = (TextView) findViewById(team);
        scoreView.setText(String.valueOf(points) );
    }

    /* set scores to zero for both teams */
    public void resetScore(View v){
        ptsA = 0;
        ptsB = 0;
        updatePtsDisplay(R.id.scoreTeam1, ptsA);
        updatePtsDisplay(R.id.scoreTeam2, ptsB);
    }

}
