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

public class SnacksAddapter extends ArrayAdapter<Snacks> {

    private Activity context;
    private List<Snacks>foodList;

    public SnacksAddapter(Activity context,  List<Snacks> foodList) {
        super(context,R.layout.sample_layout, foodList);
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);
        Snacks snacks = foodList.get(position);
        TextView t1 = view.findViewById(R.id.sample_NameId);
        TextView t2 = view.findViewById(R.id.sample_quantityId);

        t1.setText("Food Name :"+snacks.getFoodName());
        t2.setText("Quantity :"+snacks.getFoodQuantity());



        return view;
    }
}
