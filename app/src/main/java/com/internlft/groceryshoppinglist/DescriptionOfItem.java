package com.internlft.groceryshoppinglist;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionOfItem extends AppCompatActivity {
    ImageView itemImage, buyStatus;
    TextView itemName, itemDescription, itemPrice;
    ItemInfo itemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_of_item);
        itemInfo = new ItemInfo();
        Intent intent =  getIntent();
        itemInfo.groceryName = intent.getExtras().getString("KEY_FOR_SINGLE_VIEW_NAME");
        itemInfo.groceryImage = intent.getExtras().getInt("KEY_FOR_SINGLE_VIEW_IMAGE");
        itemInfo.description = intent.getExtras().getString("KEY_FOR_SINGLE_VIEW_DESCRIPTION");
        itemInfo.price = intent.getExtras().getInt("KEY_FOR_SINGLE_VIEW_PRICE");
        itemInfo.addedToCard = intent.getExtras().getBoolean("KEY_FOR_SINGLE_VIEW_IS_SELECTED?");

        itemImage = (ImageView) findViewById(R.id.singlePicture);
        itemName = (TextView) findViewById(R.id.singleText);
        itemDescription = (TextView)findViewById(R.id.singleDescription);
        itemPrice = (TextView) findViewById(R.id.singlePrice);
        buyStatus = (ImageView)findViewById(R.id.theCart);



        itemImage.setImageResource(itemInfo.groceryImage);
        itemName.setText(itemInfo.groceryName);
        itemDescription.setText(itemInfo.description);
        itemPrice.setText("Price: "+Integer.toString(itemInfo.price));
        if (itemInfo.addedToCard == false){
            buyStatus.setImageResource(R.drawable.clear_shopping_cart);
        }
        else {
            buyStatus.setImageResource(R.drawable.shopping_cart_loaded);
        }

    }

    public  void addToCart(View view){
        //TODO
       if (itemInfo.addedToCard == false){
           Toast.makeText(this,itemInfo.groceryName+" is not Added to card",Toast.LENGTH_SHORT).show();
       }
        else{
           Toast.makeText(this,itemInfo.groceryName+" is Already Added",Toast.LENGTH_SHORT).show();
       }
    }
}

