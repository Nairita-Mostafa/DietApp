package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserSnacks extends AppCompatActivity {
    private ListView listView3;
    private  List<Snacks>foodlist;
    private SnacksAddapter snacksAddapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_snacks);
        this.setTitle("EVENING SNACKS PLAN");
        listView3= (ListView)findViewById(R.id.listView3Id);
        databaseReference = FirebaseDatabase.getInstance().getReference("Snacks");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''

        foodlist = new ArrayList<>();
        snacksAddapter = new SnacksAddapter(UserSnacks.this , foodlist);

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Snacks snacks = dataSnapshot1.getValue(Snacks.class);
                    foodlist.add(snacks);


                }
                listView3.setAdapter(snacksAddapter);

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