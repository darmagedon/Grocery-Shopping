package com.internlft.groceryshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewMenuAdapter(this,getData()));



    }
    public static List<ItemInfo> getData(){
        List info = new ArrayList<>();
      /*  String[] urls = {"https://pbs.twimg.com/profile_images/701311216669556736/tyvsHM5d.jpg",
                "https://static-s.aa-cdn.net/img/ios/900648751/c9f22b42d4ee692cc20127903048a9ce?v=1",
        "http://us.123rf.com/450wm/magurok/magurok1509/magurok150900017/44905710-paper-bag-full-of-food-grocery-delivery-concept.jpg?ver=6",
        "http://res.cloudinary.com/lightspeed-retail/image/upload/c_pad,h_256,q_75,w_256/mzhacsa1kt0qfqa0gdtj.jpg",
        "http://individual.icons-land.com/IconsPreview/3D-Food/PNG/256x256/Meat_Steak.png",
        "http://www.castlemalting.com/Publications/Recipes/Images/BlondBeer1Character.png"};
*/
        String[] title = {"Rice","Junk Food", "Healthy Food","Veggies","Meat","Beer"};

        int icons = R.drawable.shopping_cart;
        for (int i=0; i<100; i++){



            ItemInfo current = new ItemInfo();


            current.groceryImage =  icons;
            current.groceryName = title[i%title.length];
            info.add(current);

        }
        return info;
    }
    public void checkoutToPay(View view){
        Intent intent = new Intent(MainActivity.this,CheckoutPoint.class);
        startActivity(intent);
    }


}
