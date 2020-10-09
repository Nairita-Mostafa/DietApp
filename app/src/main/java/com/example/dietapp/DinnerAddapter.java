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

import java.util.List;

public class DinnerAddapter extends ArrayAdapter<Dinner> {

    private Activity context;
    private List<Dinner>foodList;

    public DinnerAddapter(Activity context, List<Dinner> foodList) {
        super(context,R.layout.sample_layout, foodList);
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);
        Dinner dinner = foodList.get(position);
        TextView t1 = view.findViewById(R.id.sample_NameId);
        TextView t2 = view.findViewById(R.id.sample_quantityId);

        t1.setText("Food Name :"+dinner.getFoodName());
        t2.setText("Quantity :"+dinner.getFoodQuantity());



        return view;
    }
}
