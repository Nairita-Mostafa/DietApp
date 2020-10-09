package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LunchActivity extends AppCompatActivity {
    private ListView listView2;
    private  List<Lunch>foodlist;
    private LunchAddapter lunchAddapter;
    DatabaseReference databaseReference;
    private Button DeletItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        this.setTitle("LUNCH PLAN");
        listView2= (ListView)findViewById(R.id.listView2Id);
        DeletItem =(Button)findViewById(R.id.deletId);
        databaseReference = FirebaseDatabase.getInstance().getReference("Lunch");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''

        foodlist = new ArrayList<>();
        lunchAddapter = new LunchAddapter(LunchActivity.this , foodlist);

        DeletItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("Food");

                databaseReference.removeValue();

                Toast.makeText(getApplicationContext(), "data deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LunchActivity.this, LunchActivity.class);
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
                    Lunch lunch = dataSnapshot1.getValue(Lunch.class);
                    foodlist.add(lunch);


                }
                listView2.setAdapter(lunchAddapter);

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