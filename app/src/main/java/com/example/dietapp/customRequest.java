package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customRequest extends AppCompatActivity {
    DatabaseReference databaseReference;
    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_request);
        this.setTitle("Custom Request");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back button to the tool bar
        getSupportActionBar().setDisplayShowHomeEnabled(true); // ''  ''    ''    ''   ''  ''  ''

       editText=(EditText)findViewById(R.id.editRequestId);
       button=(Button)findViewById(R.id.submitRequestId);
        databaseReference = FirebaseDatabase.getInstance().getReference("RequestAddapter");

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String req = editText.getText().toString().trim();


               if (req.equals("")) {

                   editText.setError("Please write Your request ");
                   editText.requestFocus();
                   return;
               }
                   databaseReference = FirebaseDatabase.getInstance().getReference("RequestModel");
                   String request = editText.getText().toString().trim();

                   String key = databaseReference.push().getKey();

                   RequestModel requestModel = new RequestModel(request);
                   databaseReference.child(key).setValue(requestModel);
                   Toast.makeText(getApplicationContext(), "Request Send Successfully  ", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(customRequest.this, UserHomePage.class);
               startActivity(intent);
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
}