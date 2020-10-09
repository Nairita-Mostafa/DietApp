package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BmiActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        this.setTitle("BMI Counter");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''



        final EditText edWeg,edHei;
        final TextView txtRes,txtInter;
        Button btnRes,btnReset;

        edWeg=(EditText) findViewById(R.id.edweg);
        edHei= (EditText) findViewById(R.id.edhei);

        txtInter=(TextView) findViewById(R.id.txtinter);
        txtRes=(TextView) findViewById(R.id.txtres);

        btnRes= (Button) findViewById(R.id.btnres);
        btnReset= (Button) findViewById(R.id.btnreset);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("BmiValue");

                databaseReference.removeValue();

                Toast.makeText(getApplicationContext(), "previous data deleted and new data saved  ", Toast.LENGTH_SHORT).show();


                String strweg= edWeg.getText().toString();
                String strhei= edHei.getText().toString();

                if(strweg.equals("")){
                    edWeg.setError("Please Enter Your Weight ");
                    edWeg.requestFocus();
                    return;
                }
                if(strhei.equals("")){
                    edHei.setError("Please Enter Your Height");
                    edHei.requestFocus();
                    return;
                }

                float weight = Float.parseFloat(strweg);
                float height = Float.parseFloat(strhei)/100;

                float bmiVlaue = BMICalculate(weight,height);

                txtInter.setText(interpreteBMI(bmiVlaue));
                txtRes.setText("BMI= "+bmiVlaue);
                databaseReference = FirebaseDatabase.getInstance().getReference("BmiValue");
                String BMI = txtRes.getText().toString().trim();
                String Status = txtInter.getText().toString().trim();
                String key = databaseReference.push().getKey();

                BmiValue BmiValue = new BmiValue( BMI,Status);
                databaseReference.child(key).setValue(BmiValue);


            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edHei.setText("");
                edWeg.setText("");
                txtInter.setText("");
                txtRes.setText("");




            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){    // for back button working

        if (item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }



    public float BMICalculate(float weight,float height){
        return weight / (height * height);
    }
    public String interpreteBMI(float bmiValue){
        if( bmiValue <16){
            return "Servely Underweight";
        }
        else if(bmiValue <18.5){
            return "Underweight";
        }
        else if(bmiValue < 25){
            return "Normal";
        }
        else if(bmiValue <30){
            return "OverWeight";
        }
        else {
            return "Obese";
        }

    }
}