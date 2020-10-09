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

public class Diet_Plan extends AppCompatActivity {
    private TextView bmr, Status1;

    private Button  breakfast, lunch, evening, dinner;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet__plan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''





        breakfast = (Button) findViewById(R.id.breakfastSaveId);
        lunch = (Button) findViewById(R.id.lunchSaveId);
        evening = (Button) findViewById(R.id.eveningSaveId);
        dinner = (Button) findViewById(R.id.dinnerSaveId);


        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diet_Plan.this, UserBreakfast.class);
                startActivity(intent);

            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diet_Plan.this, UserLunch.class);
                startActivity(intent);

            }
        });
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diet_Plan.this, UserSnacks.class);
                startActivity(intent);

            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diet_Plan.this, UserDinner.class);
                startActivity(intent);
            }
        });




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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    // for back button working

        if (item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
}