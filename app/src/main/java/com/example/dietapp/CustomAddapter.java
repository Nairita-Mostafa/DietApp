package com.example.dietapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAddapter extends ArrayAdapter<Food> {

    private Activity context;
    private List<Food>foodList;




    public CustomAddapter(Activity context,  List<Food> foodList) {
        super(context,R.layout.sample_layout, foodList );
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);
        Food food = foodList.get(position);
        TextView t1 = view.findViewById(R.id.sample_NameId);
        TextView t2 = view.findViewById(R.id.sample_quantityId);

        t1.setText("Food Name :"+food.getFoodName());
        t2.setText("Quantity :"+food.getFoodQuantity());



        return view;
    }

    @NonNull
    @Override
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public List<Food> getFoodList() {

        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
