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

import com.example.dietapp.Food;
import com.example.dietapp.R;
import com.example.dietapp.RequestModel;

import java.util.ArrayList;
import java.util.List;

public class Addapter extends ArrayAdapter<RequestModel> {

    private Activity context;
    private List<RequestModel>requestModelList;


    public Addapter(Activity context,  List<RequestModel> requestModelList) {
        super(context, R.layout.sample2_layout, requestModelList);
        this.context = context;
        this.requestModelList = requestModelList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample2_layout,null,true);
        RequestModel requestModel = requestModelList.get(position);
        TextView t1 = view.findViewById(R.id.sample2_NameId);


        t1.setText("# :"+ requestModel.getRequest());




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

    public List<RequestModel> getRequestModelList() {
        return requestModelList;
    }

    public void setRequestModelList(List<RequestModel> requestModelList) {
        this.requestModelList = requestModelList;
    }
}
