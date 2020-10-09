package com.example.dietapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dietapp.BmiActivity;
import com.example.dietapp.DietList;
import com.example.dietapp.MainActivity;
import com.example.dietapp.R;

import com.google.firebase.auth.FirebaseAuth;

public class UserHomePage extends AppCompatActivity implements View.OnClickListener {
    private Button bmiButton , dietList, custom;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        this.setTitle("UserHome page");


        bmiButton = (Button) findViewById(R.id.bmiId);

        dietList=(Button)findViewById(R.id.dietListId);
        custom = (Button) findViewById(R.id.customRequestId);



        bmiButton.setOnClickListener(this);

        dietList.setOnClickListener(this);
        custom.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()== R.id.signOutMenuId){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent= new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {






         if (v.getId() == R.id. bmiId){
            Intent intent = new Intent(UserHomePage.this, BmiActivity.class);
            startActivity(intent);


        }
        else if (v.getId() == R.id. dietListId){
            Intent intent = new Intent(UserHomePage.this, Diet_Plan.class);
            startActivity(intent);


        }
        else if (v.getId() == R.id. customRequestId){

            Intent intent = new Intent(UserHomePage.this, customRequest.class);
            startActivity(intent);


        }
    }
}