package com.example.android.mindsport;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    //Game Data
    String playerName = "";


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("playerName", playerName);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            playerName = savedInstanceState.getString("playerName");
        }
        setContentView(R.layout.activity_main);


        //setting up buttons to call the onClick method when app loads
        Button start = findViewById(R.id.start);
        start.setOnClickListener(this);

    }



        @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //Button listeners
            case R.id.start:
                EditText tempPlayerName = (EditText) findViewById(R.id.pname);
                playerName = tempPlayerName.getText().toString();
                Log.i("tag", "start button pressed. player name is: " + playerName);
                updateLayout(R.id.questions, R.layout.question1);
                updateDisplay(R.id.gameName, playerName);
                String str = this.getResources().getString(R.string.next);
                updateButton(str, R.id.start, R.id.next1);
                break;

            case R.id.next1:
                Log.i("tag", "next1 button pressed. player name is: " + playerName);
                updateLayout(R.id.questions, R.layout.question2);
                str = this.getResources().getString(R.string.next);
                updateButton(str, R.id.next1, R.id.next2);
                break;

            case R.id.next2:
                Log.i("tag", "next2 button pressed. player name is: " + playerName);
                updateLayout(R.id.questions, R.layout.question3);
                str = this.getResources().getString(R.string.next);
                updateButton(str, R.id.next2, R.id.submit);
                break;

            case R.id.submit:
                Log.i("tag", "submit button pressed");
                Toast toast = Toast.makeText(getApplicationContext(), "Simple Toast In Android", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                toast.setGravity(android.view.Gravity.TOP | android.view.Gravity.LEFT, 0, 0);     // set gravity for the Toast.
                toast.show(); // display the Toast
                break;


            default:
                break;
        }

    }

    private void updateDisplay(int viewID, String textValue){
        TextView tempView = (TextView) findViewById(viewID);
        tempView.setText(String.valueOf(textValue) );
    }

    private void updateLayout(int currentLayout, int newLayout){
        //remove the Player Name view
        LinearLayout removeMe = (LinearLayout) findViewById(currentLayout);
        removeMe.removeAllViewsInLayout();

        //find the questions section
        LinearLayout questionsView = (LinearLayout) findViewById(currentLayout);

        //add new questions to the question section
        LinearLayout questions = (LinearLayout) View.inflate(this, newLayout, null);
        questionsView.addView(questions);
    }

    private void updateButton(String newText, int currentId, int newId){
        //change text and id on button
        Button newBtnValue = (Button)findViewById(currentId);
        newBtnValue.setText(newText);
        newBtnValue.setId(newId);
    }


}
