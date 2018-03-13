package com.example.android.mindsport;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    //Game Data
    String playerName = "";
    String a1 = "";
    String a2 = "";
    String a4 = "";
    String a5 = "";
    String a6_1 = "f";
    String a6_2 = "f";
    String a6_3 = "f";
    String a6 = "";
    String a7_1 = "f";
    String a7_2 = "f";
    String a7_3 = "f";
    String a7 = "";
    int score = 0;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("playerName", playerName);
        outState.putString("a1", a1);
        outState.putString("a2", a2);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            playerName = savedInstanceState.getString("playerName");
            a1 = savedInstanceState.getString("a1");
            a2 = savedInstanceState.getString("a2");
        }
        setContentView(R.layout.activity_main);


        //setting up buttons to call the onClick method when app loads
        Button start = findViewById(R.id.start);
        start.setOnClickListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.a4_1:
                if (checked)
                    Log.d("tag", "onRadioButtonClicked: incorrect");
                a4 = "";
                break;
            case R.id.a4_2:
                if (checked)
                    Log.d("tag", "onRadioButtonClicked: incorrect");
                a4 = "";
                break;
            case R.id.a4_3:
                if (checked)
                    Log.d("tag", "onRadioButtonClicked: correct");
                a4 = "a4_3";
                break;
            case R.id.a5_1:
                if (checked)
                    Log.d("tag", "onRadioButtonClicked: incorrect");
                a5 = "";
                break;
            case R.id.a5_2:
                if (checked)
                    Log.d("tag", "onRadioButtonClicked: incorrect");
                a5 = "";
                break;
            case R.id.a5_3:
                if (checked)
                    Log.d("tag", "onRadioButtonClicked: correct");
                a5 = "a5_3";
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.a6_1:
                if (checked) {
                    Log.d("tag", "onCheckboxClicked: a6_1");
                    a6_1 = "t";
                } else
                    a6_1 = "f";
                break;
            case R.id.a6_2:
                if (checked) {
                    Log.d("tag", "onCheckboxClicked: a6_2");
                    a6_2 = "t";
                } else
                    a6_2 = "f";
                break;
            case R.id.a6_3:
                if (checked) {
                    Log.d("tag", "onCheckboxClicked: a6_3");
                    a6_3 = "t";
                } else
                    a6_3 = "f";
                break;
            case R.id.a7_1:
                if (checked) {
                    Log.d("tag", "onCheckboxClicked: a7_1");
                    a7_1 = "t";
                } else
                    a7_1 = "f";
                break;
            case R.id.a7_2:
                if (checked) {
                    Log.d("tag", "onCheckboxClicked: a7_2");
                    a7_2 = "t";
                } else
                    a7_2 = "f";
                break;
            case R.id.a7_3:
                if (checked) {
                    Log.d("tag", "onCheckboxClicked: a7_3");
                    a7_3 = "t";
                } else
                    a7_3 = "f";
                break;
        }
    }

    private void determineScore(){
        if (a1.toLowerCase().equals("macbeth")){
            score += 1;
        }
        if (a2.toLowerCase().equals("i")){
            score += 1;
        }
        if(a4.toLowerCase().equals("a4_3")){
            score += 1;
        }
        if(a5.toLowerCase().equals("a5_3")){
            score += 1;
        }
        a6 = a6_1 + a6_2 + a6_3;
        a7 = a7_1 + a7_2 + a7_3;
        if(a6.equals("ttt")){
            score += 1;
        }
        if(a7.equals("tft"))
            score += 1;
        Log.i("tag", "the score is: " + score);
    }

    private void getPlayerName(){
        EditText tempPlayerName = (EditText) findViewById(R.id.pname);
        playerName = tempPlayerName.getText().toString();
        Log.i("tag", "start button pressed. player name is: " + playerName);
    }

    private void getTextAnswer(String answerNum, int answerId){
        EditText tempAnswer = (EditText) findViewById(answerId);
        answerNum = tempAnswer.getText().toString();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //Button listeners
            case R.id.start:
                getPlayerName();
                updateLayout(R.id.questions, R.layout.question1);
                updateDisplay(R.id.gameName, playerName);
                String str = this.getResources().getString(R.string.next);
                updateButton(str, R.id.start, R.id.next1);
                break;

            case R.id.next1:
                getTextAnswer(a1, R.id.a1);
                getTextAnswer(a2, R.id.a2);
                updateLayout(R.id.questions, R.layout.question2);
                str = this.getResources().getString(R.string.next);
                updateButton(str, R.id.next1, R.id.next2);
                break;

            case R.id.next2:

                updateLayout(R.id.questions, R.layout.question3);
                str = this.getResources().getString(R.string.submit);
                updateButton(str, R.id.next2, R.id.submit);
                break;

            case R.id.submit:
                determineScore();
                displayToast();
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

    private void displayToast(){
        // create instance
        Toast toast = new Toast(getApplicationContext());

        // inflate custom view
        View view = getLayoutInflater().inflate(R.layout.toast, null);

        // set custom view
        toast.setView(view);

        // set duration
        toast.setDuration(Toast.LENGTH_LONG);

        // set position
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 32);

    // show toast
        toast.show();


    }
}
