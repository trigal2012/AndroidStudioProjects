package com.example.android.justjavaadvanced;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

    int quantity = 1;
    String size = "Small";
    double sizeInt = 0.00;
    String drink = "Americano";
    double drinkPrice = 2.50;
    String subTotal = "$2.50";
    double wc = 0.00;
    double cs = 0.00;
    double total = 0.00;
    String tempReceipt = "";
    String receipt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox topping1 = (CheckBox)findViewById(R.id.topping1);
        topping1.setOnClickListener(this);
        CheckBox topping2 = (CheckBox)findViewById(R.id.topping2);
        topping2.setOnClickListener(this);
        Button addToCart = (Button)findViewById(R.id.addToCart);
        addToCart.setOnClickListener(this);
        Button sumbitOrder = (Button)findViewById(R.id.submitOrder);
        sumbitOrder.setOnClickListener(this);
        final Spinner drinkQuantity = (Spinner)findViewById(R.id.drinkQuantity);
        drinkQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantity = Integer.parseInt(drinkQuantity.getSelectedItem().toString());
                getSubtotal(quantity, drink, sizeInt, wc, cs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //getSubtotal(quantity, drink, sizeInt, wc, cs);

            }
        });
        final Spinner drinkSize = (Spinner)findViewById(R.id.drinkSize);
        drinkSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size = drinkSize.getSelectedItem().toString();
                sizeInt = drinkSize.getSelectedItemPosition();
                getSubtotal(quantity, drink, sizeInt, wc, cs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //getSubtotal(quantity, drink, sizeInt, wc, cs);
            }
        });
        final Spinner drinkType = (Spinner)findViewById(R.id.drinkType);
        drinkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                drink = drinkType.getSelectedItem().toString();
                getSubtotal(quantity, drink, sizeInt, wc, cs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //getSubtotal(quantity, drink, sizeInt, wc, cs);
            }
        });

    }


    @Override
    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.topping1:
                CheckBox checkBox1 = (CheckBox)v;
                if(checkBox1.isChecked()){
                    //adjust price accordingly
                    wc = 1.00;
                } else{
                    wc = 0.00;
                }
                getSubtotal(quantity, drink, sizeInt, wc, cs);
                break;
            case R.id.topping2:
                CheckBox checkBox2 = (CheckBox)v;
                if(checkBox2.isChecked()){
                    //adjust price accordingly
                    cs = 2.00;
                } else{
                    cs = 0.00;
                }
                getSubtotal(quantity, drink, sizeInt, wc, cs);
                break;
            case R.id.addToCart:
                if(quantity > 1){
                    drink = drink + "s";
                }
                if(wc > 0.00 && cs > 0.00){
                    drink = drink + " (wc, cs)";
                } else if(wc > 0.00 && cs == 0.00){
                    drink = drink + " (wc)";
                } else if(wc == 0.00 && cs > 0.00){
                    drink = drink + " (cs)";
                }
                LinearLayout orderSum = (LinearLayout)findViewById(R.id.orderSummary);
                //adds an order row
                LinearLayout orderRow = (LinearLayout) View.inflate(this, R.layout.order_summary_row, null);
                orderSum.addView(orderRow);

                //adds the order details
                TextView orderDetails = (TextView) View.inflate(this, R.layout.order_summary_details, null);
                orderRow.addView(orderDetails);
                LinearLayout.LayoutParams detailParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
                orderDetails.setLayoutParams(detailParams);
                String orderDetailsText = quantity + " " + size + " " + drink;
                orderDetails.setText(orderDetailsText);

                //adds the price details
                TextView orderPrice = (TextView) View.inflate(this, R.layout.order_summary_price, null);
                orderRow.addView(orderPrice);
                LinearLayout.LayoutParams priceParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                orderPrice.setLayoutParams(priceParams);
                orderPrice.setText(subTotal);

                //update total nd display
                total += drinkPrice;
                String tmessage = "Total: " + Double.toString(total);
                displayMessage(tmessage, R.id.order_total);

                //update receipt
                tempReceipt += orderDetailsText + " " + subTotal + "\n";

                //reset selections
                drink = "Americano";
                size = "S";
                sizeInt = 0;
                quantity = 1;
                break;
            case R.id.submitOrder:
                receipt += "Name: " + getOrderName() + "\n";
                receipt += tempReceipt + "\n";
                receipt += "Total: " + total + "\n";
                receipt += "Thank You!";
                Log.i("submit order", receipt);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Advanced order for " + getOrderName());
                intent.putExtra(Intent.EXTRA_TEXT, receipt);
                if (intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }
        }

    }


    private double getSubtotal(int quantity, String drink, double sizeInt, double wc, double cs){
        if(drink.equals("Americano")){
            drinkPrice = quantity * (2.50 + sizeInt + wc + cs);
        }
        if(drink.equals("Mocha")){
            drinkPrice = quantity * (3.75 + sizeInt + wc + cs);
        }
        if(drink.equals("Latte")){
            drinkPrice = quantity * (3.00 + sizeInt + wc + cs);
        }
        if(drink.equals("Cappuccino")){
            drinkPrice  = quantity * (3.50 + sizeInt + wc + cs);
        }
        formatPrice(drinkPrice);

        return drinkPrice;
    };

    private void formatPrice(double drinkPrice){
        subTotal = NumberFormat.getInstance().format(drinkPrice);
        displayMessage(subTotal, R.id.subTotal);
    };

    private void displayMessage(String message, int view) {
        TextView orderSummary = findViewById(view);
        orderSummary.setText(message);
    }

    private String getOrderName(){
        EditText getName = (EditText)findViewById(R.id.name);
        String nameValue = getName.getText().toString();
        return nameValue;
    }

}