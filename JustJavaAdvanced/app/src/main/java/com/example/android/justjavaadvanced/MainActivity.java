package com.example.android.justjavaadvanced;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

    int quantity = 0;
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox topping1 = (CheckBox)findViewById(R.id.topping1);
        topping1.setOnClickListener(this);
        CheckBox topping2 = (CheckBox)findViewById(R.id.topping2);
        topping2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.topping1:
                CheckBox checkBox1 = (CheckBox)v;
                if(checkBox1.isChecked()){
                    //adjust price accordingly
                    price += 1;
                    Log.i("topping", "topping1 is selected: " + price);
                } else{
                    price -= 1;
                    Log.i("topping", "topping1 is not selected: " + price);
                }
                break;
            case R.id.topping2:
                CheckBox checkBox2 = (CheckBox)v;
                if(checkBox2.isChecked()){
                    //adjust price accordingly
                    price += 2;
                    Log.i("topping", "topping2 is selected: " + price);
                } else{
                    price -= 2;
                    Log.i("topping", "topping2 is not selected: " + price);
                }
                break;
        }

    }


            /**
             * This method is called when the order button is clicked.
             */

    public void submitOrder(View view) {
        String priceMessage = "Name: ";
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: " + price;
        priceMessage += "\nThank You!";
        displayMessage(priceMessage);
    }

    /**
     * This method is called when the + (plus) button is clicked.
     * per lesson, don't allow more than 100 cups of coffee
     * send toast message if user tries to order more than 100 cups.
     */
    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(getApplicationContext(), "You have reached the maximum # of cups allowed", Toast.LENGTH_LONG).show();
        } else {
            quantity = quantity + 1;
        }
        display(quantity);
    }

    /**
     * This method is called when the - (minus) button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        } else {
            quantity = 0;
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.order_total);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.order_total);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.order_total);
        priceTextView.setText(message);
    }
}