package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    private Button bmiButton, dietList, message;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        this.setTitle("Home page");


        bmiButton = (Button) findViewById(R.id.bmiId);

        dietList=(Button)findViewById(R.id.dietListId);
        message = (Button) findViewById(R.id.messagesId);
       




        dietList.setOnClickListener(this);
        message.setOnClickListener(this);


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
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {







         if (v.getId() == R.id. dietListId){
            Intent intent = new Intent(HomePage.this,DietList.class);
            startActivity(intent);


        }

          else if (v.getId() == R.id. messagesId){
              Intent intent = new Intent(HomePage.this,UserRequest.class);
              startActivity(intent);


          }
    }
}