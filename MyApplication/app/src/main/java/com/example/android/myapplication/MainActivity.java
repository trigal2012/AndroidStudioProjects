package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String edit1 = "";
    String playerName = edit1;
    int activeLayout = R.layout.activity_main;
    int actvity_main = R.layout.activity_main;
    boolean cb2State = false;
    int cb1 = R.id.cb1;
    int cb2 = R.id.cb2;
    int cb3 = R.id.cb3;

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("activeLayout", activeLayout);
        state.putString("edit1", edit1);
        state.putBoolean("cb2State",cb2State );
        Log.i("tag", "onSaveInstance active layout is: " + activeLayout);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("tag", "onCreate1 active layout is: " + activeLayout);
        if(bundle != null){
            Log.i("tag", "saved instance is NOT null: " + bundle);
            activeLayout = bundle.getInt("activeLayout");
            edit1 = bundle.getString("edit1");
            playerName = bundle.getString("playerName");
            cb2State = bundle.getBoolean("cb2State");
        }
        setContentView(activeLayout);
        if(activeLayout != actvity_main){
            Log.i("tag", "not active_main");
            TextView playerName = (TextView)findViewById(R.id.textView2);
            playerName.setText(edit1);
            CheckBox cb2S = (CheckBox)findViewById(R.id.cb2);
            cb2S.setChecked(cb2State);
        }else{
            EditText playerName = (EditText)findViewById(R.id.edit1);
            playerName.setText(edit1);
        }
        Log.i("tag", ""+cb2State);
    }

    public void page1(View view){
        EditText edit = (EditText)findViewById(R.id.edit1);
        edit1 = edit.getText().toString();
        CheckBox cb2S = (CheckBox)findViewById(R.id.cb2);
        cb2State = cb2S.isChecked();
        setContentView(R.layout.message_page1);
        TextView playerName = (TextView)findViewById(R.id.textView2);
        playerName.setText(edit1);

        Log.i("tag", "page1 button active layout is: " + activeLayout);
    }

    public void page2(View view){
        setContentView(R.layout.message_page2);
        TextView playerName = (TextView)findViewById(R.id.textView2);
        playerName.setText(edit1);
        Log.i("tag", "the value of cb2 is: " + cb2State);
        activeLayout = R.layout.message_page2;
        Log.i("tag", "page2 button active layout is: " + activeLayout);
    }

    public void goHome(View view){
        setContentView(R.layout.activity_main);
        EditText playerName = (EditText)findViewById(R.id.edit1);
        playerName.setText(edit1);
        activeLayout = R.layout.activity_main;
        Log.i("tag", "go home button active layout is: " + activeLayout);
    }

}
