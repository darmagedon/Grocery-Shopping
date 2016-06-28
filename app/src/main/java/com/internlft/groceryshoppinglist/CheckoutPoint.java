package com.internlft.groceryshoppinglist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CheckoutPoint extends AppCompatActivity {
    TextView seePriceToPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_point);

        seePriceToPay = (TextView) findViewById(R.id.finalPayment);
        SharedPreferences sharedPreferences = getSharedPreferences("PriceToPay", Context.MODE_PRIVATE);
        int finalPrice =sharedPreferences.getInt("AmountPayable",0);
        seePriceToPay.setText(Integer.toString(finalPrice));
    }
}
