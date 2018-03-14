package com.example.android.mindsport;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    String toastMessage = "";
    int score = 0;
    int page = 0;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("playerName", playerName);
        outState.putString("a1", a1);
        outState.putString("a2", a2);
        outState.putString("a4", a4);
        outState.putString("a5", a5);
        outState.putString("a6", a6);
        outState.putString("a7", a7);
        outState.putInt("score", score);
        outState.putInt("page", page);

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
            a4 = savedInstanceState.getString("a4");
            a5 = savedInstanceState.getString("a5");
            a6 = savedInstanceState.getString("a6");
            a7 = savedInstanceState.getString("a7");
            score = savedInstanceState.getInt("score");
            page = savedInstanceState.getInt("page");

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
    }

    private void getPlayerName(){
        EditText tempPlayerName = (EditText) findViewById(R.id.pname);
        playerName = tempPlayerName.getText().toString();
        Log.i("tag", "start button pressed. player name is: " + playerName);
    }

    private String getTextAnswer(String answerNum, int answerId){
        EditText tempAnswer = (EditText) findViewById(answerId);
        answerNum = tempAnswer.getText().toString();
        return answerNum;
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
                page = 1;
                break;

            case R.id.next1:
                a1 = getTextAnswer(a1, R.id.a1);
                Log.i("tag", a1);
                a2 = getTextAnswer(a2, R.id.a2);
                Log.i("tag", a2);
                updateLayout(R.id.questions, R.layout.question2);
                str = this.getResources().getString(R.string.next);
                updateButton(str, R.id.next1, R.id.next2);
                page = 2;
                break;

            case R.id.next2:

                updateLayout(R.id.questions, R.layout.question3);
                str = this.getResources().getString(R.string.submit);
                updateButton(str, R.id.next2, R.id.submit);
                page = 3;
                break;

            case R.id.submit:
                toastMessage = "";
                determineScore();
                createMessage();
                displayToast(toastMessage);
                TextView title = (TextView)findViewById(R.id.gameName);
                title.setText("GAME OVER");
                Button btn = (Button)findViewById(R.id.submit);
                btn.setEnabled(false);
                btn.setBackgroundColor(getResources().getColor(R.color.darkGray));
                btn.setTextColor(getResources().getColor(R.color.lightGray));
                disableCheckbox(R.id.a6_1);
                disableCheckbox(R.id.a6_2);
                disableCheckbox(R.id.a6_3);
                disableCheckbox(R.id.a7_1);
                disableCheckbox(R.id.a7_2);
                disableCheckbox(R.id.a7_3);
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
        //remove views no longer needed
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

    private void createMessage(){
        toastMessage += getString(R.string.salutation) + " " + playerName + ",\n";
        toastMessage += getString(R.string.thanks) + "\n";
        toastMessage += getString(R.string.youHave) + " " + score + " of 6 " + getString(R.string.correct) + " " + getString(R.string.answer);
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

    private void disableCheckbox(int chkBoxId){
        CheckBox checkBox = (CheckBox)findViewById(chkBoxId);
        checkBox.setEnabled(false);
    }

}
