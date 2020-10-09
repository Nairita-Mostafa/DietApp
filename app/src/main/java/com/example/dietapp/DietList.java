package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DietList extends AppCompatActivity {
    private TextView bmr, Status1;
    private EditText foodName, foodQuantity;
    private Button saveData, breakfast, lunch, evening, dinner;
    private Button lunchData, snackData, dinnerData;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''



        foodName = (EditText) findViewById(R.id.foodNameId);
        foodQuantity = (EditText) findViewById(R.id.foodQuantityId);
        saveData = (Button) findViewById(R.id.saveDataId);
        breakfast = (Button) findViewById(R.id.breakfastSaveId);
        lunch = (Button) findViewById(R.id.lunchSaveId);
        evening = (Button) findViewById(R.id.eveningSaveId);
        dinner = (Button) findViewById(R.id.dinnerSaveId);
        lunchData = (Button) findViewById(R.id.saveLunchId);
        snackData = (Button) findViewById(R.id.saveSnacksId);
        dinnerData = (Button) findViewById(R.id.saveDinnerDataId);






        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietList.this, BreakfastActivity.class);
                startActivity(intent);

            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietList.this, LunchActivity.class);
                startActivity(intent);

            }
        });
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietList.this, EveningActivity.class);
                startActivity(intent);

            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietList.this, DinnerActivity.class);
                startActivity(intent);
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Food");
                saveData();
            }
        });

        lunchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Lunch");
                lunchData();

            }
        });

        snackData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Snacks");
                snackData();

            }
        });

        dinnerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Dinner");
                dinnerData();

            }
        });

    }

    public void saveData() {
        String food_Name = foodName.getText().toString().trim();
        String food_Quantity = foodQuantity.getText().toString().trim();

        if (food_Name.isEmpty()){
            foodName.setError(" Enter food name ");
            foodName.requestFocus();
            return;
        }

        if (food_Quantity.isEmpty()){
            foodQuantity.setError(" Enter food name ");
            foodQuantity.requestFocus();
            return;
        }

        String key = databaseReference.push().getKey();

        Food Food = new Food(food_Name, food_Quantity);
        databaseReference.child(key).setValue(Food);

        foodName.setText(null);
        foodQuantity.setText(null);

    }

    public void lunchData() {
        String food_Name = foodName.getText().toString().trim();
        String food_Quantity = foodQuantity.getText().toString().trim();

        if (food_Name.isEmpty()){
            foodName.setError(" Enter food name ");
            foodName.requestFocus();
            return;
        }

        if (food_Quantity.isEmpty()){
            foodQuantity.setError(" Enter food name ");
            foodQuantity.requestFocus();
            return;
        }

        String key = databaseReference.push().getKey();

        Lunch Lunch = new Lunch(food_Name, food_Quantity);
        databaseReference.child(key).setValue(Lunch);

        foodName.setText(null);
        foodQuantity.setText(null);
    }
    public void snackData() {
        String food_Name = foodName.getText().toString().trim();
        String food_Quantity = foodQuantity.getText().toString().trim();
        if (food_Name.isEmpty()){
            foodName.setError(" Enter food name ");
            foodName.requestFocus();
            return;
        }

        if (food_Quantity.isEmpty()){
            foodQuantity.setError(" Enter food name ");
            foodQuantity.requestFocus();
            return;
        }

        String key = databaseReference.push().getKey();

        Snacks Snacks = new Snacks(food_Name, food_Quantity);
        databaseReference.child(key).setValue(Snacks);

        foodName.setText(null);
        foodQuantity.setText(null);
    }
    public void dinnerData() {
        String food_Name = foodName.getText().toString().trim();
        String food_Quantity = foodQuantity.getText().toString().trim();
        if (food_Name.isEmpty()){
            foodName.setError(" Enter food name ");
            foodName.requestFocus();
            return;
        }

        if (food_Quantity.isEmpty()){
            foodQuantity.setError(" Enter food name ");
            foodQuantity.requestFocus();
            return;
        }

        String key = databaseReference.push().getKey();

        Dinner Dinner = new Dinner(food_Name, food_Quantity);
        databaseReference.child(key).setValue(Dinner);

        foodName.setText(null);
        foodQuantity.setText(null);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    // for back button working

        if (item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("BmiValue");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    String BMI=datas.child("bmi").getValue().toString();
                    TextView bmr = (TextView) findViewById(R.id.bmrcounterId);
                    bmr.setText( "BMI : "+BMI);

                    String Status=datas.child("status").getValue().toString();

                    TextView Status1 = (TextView) findViewById(R.id.statusId);
                    Status1.setText( "Status : "+Status);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        super.onStart();
    }



}