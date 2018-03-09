package com.example.android.mindsport;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question3);

        Button button = (Button)findViewById(R.id.submit);

        // Show toast message when button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Simple Toast In Android", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                toast.setGravity(android.view.Gravity.TOP | android.view.Gravity.LEFT, 0, 0);     // set gravity for the Toast.
                toast.show(); // display the Toast
            }
        });

    }
}
