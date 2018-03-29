package com.example.android.mindsport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.content.res.Resources;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //initialize vars for score
    String playerName = "";
    String a1 = "";
    String a2 = "";
    String a6 = "";
    String a7 = "";
    String toastMessage = "";

    int layoutId = R.layout.activity_main;
    int score = 0;

    Boolean a4 = false;
    Boolean a5 = false;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(layoutId == R.layout.question1){
            a1 = getEditTextAnswers(R.id.a1);
            a2 = getEditTextAnswers(R.id.a2);
        }
        if(layoutId == R.layout.question2){
            getRBAnswers(R.id.rg4);
            getRBAnswers(R.id.rg5);
        }
        if(layoutId == R.layout.question3){
            getCBAnswers();
        }
        if(layoutId == R.layout.game_over){
            determineScore();
        }
        if(layoutId == R.layout.activity_main){
            resetVars();
        }
        outState.putInt("layoutId", layoutId);
        outState.putString("playerName", playerName);
        outState.putString("a1", a1);
        outState.putString("a2", a2);
        outState.putBoolean("a4", a4);
        outState.putBoolean("a5", a5);
        outState.putString("a6", a6);
        outState.putString("a7", a7);
        outState.putInt("score", score);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null && (savedInstanceState.getInt("layoutId") != R.layout.activity_main)) {
            Log.i("tag", "on create if layoutid is: " + layoutId);
            layoutId = savedInstanceState.getInt("layoutId");
            playerName = savedInstanceState.getString("playerName");
            a1 = savedInstanceState.getString("a1");
            a2 = savedInstanceState.getString("a2");
            a4 = savedInstanceState.getBoolean("a4");
            a5 = savedInstanceState.getBoolean("a5");
            a6 = savedInstanceState.getString("a6");
            a7 = savedInstanceState.getString("a7");
            score = savedInstanceState.getInt("score");
        }
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

    }

    public void onButtonClick(View view){
        switch (view.getId()) {
            case R.id.start:
                getPlayerName();
                changeLayout(R.layout.question1, getString(R.string.title));
                break;
            case R.id.next1:
                a1 = getEditTextAnswers(R.id.a1);
                a2 = getEditTextAnswers(R.id.a2);
                changeLayout(R.layout.question2, getString(R.string.title));
                break;
            case R.id.next2:
                getRBAnswers(R.id.rg4);
                getRBAnswers(R.id.rg5);
                changeLayout(R.layout.question3, getString(R.string.title));
                break;
            case R.id.submit:
                getCBAnswers();
                changeLayout(R.layout.game_over, getString(R.string.gameOver));
                determineScore();
                createMessage();
                displayToast(toastMessage);
                break;
            case R.id.playAgain:
                resetVars();
                layoutId = R.layout.activity_main;
                setContentView(layoutId);
                break;
        }

    }

    private void resetVars() {
        playerName = "";
        a1 = "";
        a2 = "";
        a6 = "";
        a7 = "";
        toastMessage = "";

        layoutId = R.layout.activity_main;
        score = 0;

        a4 = false;
        a5 = false;
    }

    private void changeLayout(int layout, String newText){
        layoutId = layout;
        setContentView(layoutId);
        TextView name = (TextView)findViewById(R.id.gameName);
        name.setText(newText);
    }

    private void getPlayerName(){
        EditText tempName = (EditText)findViewById(R.id.pname);
        playerName = tempName.getText().toString();
    }

    private String getEditTextAnswers(int answerId){
        EditText tempAns = (EditText)findViewById(answerId);
        return tempAns.getText().toString().toLowerCase();
    }

    private void getRBAnswers(int radioGroupId){
        RadioGroup radioGroup = (RadioGroup) findViewById(radioGroupId);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(radioGroupId == R.id.rg4) {
            // is the correct one selected?
            if (selectedId == R.id.a4_3) {
                a4 = true;
            }
        }
        if(radioGroupId == R.id.rg5){
            // is the correct one selected?
            if (selectedId == R.id.a5_3) {
                a5 = true;
            }
        }
    }

    private void getCBAnswers(){
        a6 = "";
        a7 = "";
        CheckBox cbAns = (CheckBox)findViewById(R.id.a6_1);
        if(cbAns.isChecked()) {
            a6 += "t";
        }
        cbAns = (CheckBox)findViewById(R.id.a6_2);
        if(cbAns.isChecked()){
            a6 += "t";
        }
        cbAns = (CheckBox)findViewById(R.id.a6_2);
        if(cbAns.isChecked()) {
            a6 += "t";
        }
        cbAns = (CheckBox)findViewById(R.id.a7_1);
        if(cbAns.isChecked()) {
            a7 += "t";
        }
        cbAns = (CheckBox)findViewById(R.id.a7_2);
        if(cbAns.isChecked()) {
            a7 += "t";
        }
        cbAns = (CheckBox)findViewById(R.id.a7_3);
        if(cbAns.isChecked()) {
            a7 += "t";
        }
    }

    private void determineScore(){
        Log.i("tag", "a1: " + a1);
        Log.i("tag", "a2: " + a2);
        Log.i("tag", "a4: " + a4);
        Log.i("tag", "a5: " + a5);
        Log.i("tag", "a6: " + a6);
        Log.i("tag", "a7: " + a7);
        if (a1.equals("macbeth")){
            score += 1;
        }
        if (a2.equals("i")){
            score += 1;
        }
        if(a4 == true){
            score += 1;
        }
        if(a5 == true){
            score += 1;
        }
        if(a6.equals("ttt")){
            score += 1;
        }
        if(a7.equals("t")) {
            score += 1;
        }
        Log.i("tag", "score: " + score);
    }

    private void createMessage(){
        toastMessage = "";
        Resources res = getResources();
        if((double)score/6 >= .8) {
           double tempScore = (double)score/6 * 100;
           DecimalFormat df = new DecimalFormat("#.00");
           String scorePerc = df.format(tempScore);
            toastMessage = res.getString(R.string.goodScore, playerName, scorePerc);
        }else {
            toastMessage = res.getString(R.string.okScore, playerName, score);

        }
    }

    private void displayToast(String message){

        //get the LayoutInflater and inflate the custom_toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, null);

        //get the TextView from the custom_toast layout
        TextView text = (TextView) layout.findViewById(R.id.txtMessage);
        text.setText(message);

        //create the toast object, set display duration,
        //set the view as layout that's inflated above and then call show()
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


}