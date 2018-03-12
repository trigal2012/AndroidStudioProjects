package com.example.android.mindsport;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up buttons to call the onClick method when app loads
        Button start = findViewById(R.id.start);
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start:
                Log.i("tag", "start button pressed");
                setContentView(R.layout.question1);
                break;

            case R.id.next1:
                Log.i("tag", "next1 button pressed");
                setContentView(R.layout.question2);
                break;

            case R.id.next2:
                Log.i("tag", "next2 button pressed");
                setContentView(R.layout.question3);
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
}
