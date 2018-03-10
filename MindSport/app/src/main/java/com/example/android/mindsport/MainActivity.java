package com.example.android.mindsport;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up buttons to call the onClick method when app loads
        Button start = findViewById(R.id.start);
        start.setOnClickListener(this);
        Button next1 = findViewById(R.id.next1);
        next1.setOnClickListener(this);
        Button next2 = findViewById(R.id.next2);
        next2.setOnClickListener(this);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start:
                Log.i("tag", "start button pressed");
                break;

            case R.id.next1:
                Log.i("tag", "next1 button pressed");
                break;

            case R.id.next2:
                Log.i("tag", "next2 button pressed");
                break;

            case R.id.submit:
                Log.i("tag", "submit button pressed");
                break;

            default:
                break;
        }

    }
}
