package com.internlft.groceryshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by sparsha on 6/26/2016.
 */
public class RecyclerViewMenuAdapter extends RecyclerView.Adapter<RecyclerViewMenuAdapter.RcViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<ItemInfo> data = Collections.emptyList();

    public RecyclerViewMenuAdapter(Context context,List<ItemInfo> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("PriceToPay",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("AmountPayable",0);
        for (int i=0;i<100;i++){
            editor.putBoolean("IsChecked"+Integer.toString(i),false);
            editor.putInt("CurrentPrice"+Integer.toString(i),0);
        }

        editor.commit();
        Log.v("LOG","Adapter Constructor Called");
    }

    public RcViewHolder onCreateViewHolder(ViewGroup parent,int viewType ){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row,parent,false);
        RcViewHolder holder = new RcViewHolder(view);
        Log.v("LOG","Created  ");
        return (holder);
    }

    @Override
    public void onBindViewHolder(final RcViewHolder holder, final int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PriceToPay",Context.MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("IsChecked"+Integer.toString(position),false);
        final ItemInfo current = data.get(position);

        holder.groceryName.setText(current.groceryName);


        final Random random = new Random();
        current.description = ("Its a "+current.groceryName+". What more do you want?");

        current.price = sharedPreferences.getInt("CurrentPrice"+Integer.toString(position),0);
        if(current.price==0){
            current.price = (position+1+random.nextInt(getItemCount()))*(random.nextInt(60)+1)/(random.nextInt(10)+1);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("CurrentPrice"+Integer.toString(position),current.price);
            editor.commit();
        }
        holder.toggleButton.setChecked(state);


        holder.groceryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"NRs  "+current.price,Toast.LENGTH_SHORT).show();
            }
        });

        holder.groceryImage.setImageResource(current.groceryImage);

        holder.groceryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DescriptionOfItem.class);
                intent.putExtra("KEY_FOR_SINGLE_VIEW_IMAGE",current.groceryImage);
                intent.putExtra("KEY_FOR_SINGLE_VIEW_NAME",current.groceryName);
                intent.putExtra("KEY_FOR_SINGLE_VIEW_DESCRIPTION",current.description);
                intent.putExtra("KEY_FOR_SINGLE_VIEW_PRICE",current.price);
                intent.putExtra("KEY_FOR_SINGLE_VIEW_IS_SELECTED?",current.addedToCard);
                context.startActivity(intent);
            }
        });
        Log.v("LOG","Bound "+position+"  checkvalue  "+ Boolean.toString(current.addedToCard));

        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.toggleButton.isChecked()){

                    current.addedToCard = true;
                    SharedPreferences sharedPreferences = context.getSharedPreferences("PriceToPay",Context.MODE_PRIVATE);
                    int added = sharedPreferences.getInt("AmountPayable",0);
                    int newPrice = added + current.price;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("AmountPayable",newPrice);
                    editor.putBoolean("IsChecked"+Integer.toString(position),true);
                    editor.commit();


                    Toast.makeText(context,"Added",Toast.LENGTH_SHORT).show();

                }
                else {
                    current.addedToCard = false;
                    SharedPreferences sharedPreferences = context.getSharedPreferences("PriceToPay",Context.MODE_PRIVATE);
                    int added = sharedPreferences.getInt("AmountPayable",0);
                    int newPrice = added - current.price;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("AmountPayable",newPrice);
                    editor.commit();
                    editor.putBoolean("IsChecked"+Integer.toString(position),false);
                    Toast.makeText(context,"Removed",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RcViewHolder extends RecyclerView.ViewHolder {
        TextView groceryName;
        ImageView groceryImage;
        ToggleButton toggleButton;


        public RcViewHolder(View view) {
            super(view);
            groceryName = (TextView) view.findViewById(R.id.itemName);
            groceryImage= (ImageView) view.findViewById(R.id.itemPicture);
            toggleButton = (ToggleButton) view.findViewById(R.id.addOrRemove);


        }
    }
}
