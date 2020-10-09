package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private TextView textView;
   private Button button, adminButton;
   private EditText signInEmailEditText, signInpasswordEditText;
   private FirebaseAuth firebaseAuth;
  private FirebaseStorage firebaseStorage;
  private DatabaseReference databaseReference;
   private FirebaseDatabase firebaseDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login page");

        signInEmailEditText = (EditText)findViewById(R.id.edittext1);
        signInpasswordEditText=(EditText)findViewById(R.id.edittext2);
        adminButton=(Button)findViewById(R.id.buttonId2) ;


        textView = (TextView)findViewById(R.id.text2Id);
        button = (Button)findViewById(R.id.buttonId1);
        textView.setOnClickListener(this);
        button.setOnClickListener(this);
        adminButton.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonId1:

                userLogin();

                break;

            case R.id.buttonId2:

                adminLogin();

                break;


            case R.id.text2Id:
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);

                break;
        }

    }


    private void userLogin(){
         String signEmail  =signInEmailEditText.getText().toString().trim();
        String  signPassword = signInpasswordEditText.getText().toString().trim();


        if (signEmail.isEmpty()){
            signInEmailEditText.setError(" Enter an email address");
            signInEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signEmail).matches()){
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;
        }

        if (signPassword.isEmpty()){
            signInpasswordEditText.setError("Enter a password");
            signInpasswordEditText.requestFocus();
            return;
        }

        if (signPassword.length()< 6){
            signInpasswordEditText.setError("minimum length of a password should be 6");
            signInpasswordEditText.requestFocus();
            return;
        }
        firebaseAuth = FirebaseAuth.getInstance();


        firebaseAuth.signInWithEmailAndPassword(signEmail,signPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                        startActivity(new Intent(getApplicationContext(), UserHomePage.class));

                } else {
                    Toast.makeText(getApplicationContext(), "there is no user account like this .", Toast.LENGTH_LONG).show();

                }


            }

        });


        }


    private void adminLogin(){
        String signEmail  =signInEmailEditText.getText().toString().trim();
        String  signPassword = signInpasswordEditText.getText().toString().trim();


        if (signEmail.isEmpty()){
            signInEmailEditText.setError(" Enter an email address");
            signInEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signEmail).matches()){
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;
        }

        if (signPassword.isEmpty()){
            signInpasswordEditText.setError("Enter a password");
            signInpasswordEditText.requestFocus();
            return;
        }

        if (signPassword.length()< 6){
            signInpasswordEditText.setError("minimum length of a password should be 6");
            signInpasswordEditText.requestFocus();
            return;
        }
        firebaseAuth = FirebaseAuth.getInstance();


        firebaseAuth.signInWithEmailAndPassword(signEmail,signPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    startActivity(new Intent(getApplicationContext(), HomePage.class));

                } else {
                    Toast.makeText(getApplicationContext(), "there is no user account like this .", Toast.LENGTH_LONG).show();

                }


            }

        });


    }






}

