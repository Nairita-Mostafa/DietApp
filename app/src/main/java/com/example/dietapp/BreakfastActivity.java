package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BreakfastActivity extends AppCompatActivity {
    private ListView listView1;
    private Button DeletItem;
    DatabaseReference databaseReference;




    private  List<Food>foodlist;
    private CustomAddapter customAddapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        this.setTitle("BREAKFAST PLAN");
        DeletItem =(Button)findViewById(R.id.deletId);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''




        listView1= (ListView)findViewById(R.id.listView1Id);
        databaseReference = FirebaseDatabase.getInstance().getReference("Food");


        foodlist = new ArrayList<>();
        customAddapter = new CustomAddapter(BreakfastActivity.this , foodlist);

        DeletItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("Food");

                databaseReference.removeValue();

                Toast.makeText(getApplicationContext(), "data deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BreakfastActivity.this, BreakfastActivity.class);
                startActivity(intent);


            }
             });






    }

    @Override
   protected void onStart() {




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                   Food Food = dataSnapshot1.getValue(Food.class);
                    foodlist.add(Food);


                }
               listView1.setAdapter(customAddapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
       });









        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    // for back button working

        if (item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
}

