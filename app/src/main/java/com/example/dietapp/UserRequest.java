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

public class UserRequest extends AppCompatActivity {
    private ListView listView;
    private Button delete;
    DatabaseReference databaseReference;




    private  List<RequestModel>requestModelList;
    private Addapter addapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request);
        this.setTitle("USER REQUEST");
        delete=(Button)findViewById(R.id.submitRequestId);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''




        listView= (ListView)findViewById(R.id.listRequestId);
        databaseReference = FirebaseDatabase.getInstance().getReference("RequestModel");


        requestModelList = new ArrayList<>();
        addapter = new Addapter(UserRequest.this , requestModelList);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("RequestModel");

                databaseReference.removeValue();

                Toast.makeText(getApplicationContext(), "data deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserRequest.this, DietList.class);
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
                    RequestModel requestModel= dataSnapshot1.getValue(RequestModel.class);
                    requestModelList.add(requestModel);


                }
                listView.setAdapter(addapter);


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

