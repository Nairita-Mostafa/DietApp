package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
  import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private Button submitButton, submitAdmin;
    private EditText registerName, registerAge, registerHeight, mobileNumber, registerEmail, registerPassword, confirmPassword;
    private RadioButton radioMale, radioFemale;
       DatabaseReference databaseReference;
    String Gender = "";
    FirebaseAuth firebaseAuth;
     FirebaseDatabase firebaseDatabase;
      ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register page");
        progressBar = (ProgressBar) findViewById(R.id.progressbarId1);

        registerName = (EditText) findViewById(R.id.registerNameId);
        registerAge = (EditText) findViewById(R.id.registerAgeId);
        registerHeight = (EditText) findViewById(R.id.registerHeightId);
        mobileNumber = (EditText) findViewById(R.id.modileNumerId);
        registerEmail = (EditText) findViewById(R.id.registerEmailId);
        registerPassword = (EditText) findViewById(R.id.registerPasswordId);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordId);
        radioMale = (RadioButton) findViewById(R.id.maleId);
        radioFemale = (RadioButton) findViewById(R.id.femaleId);
        submitButton = (Button) findViewById(R.id.submitButtonId);

        firebaseAuth = FirebaseAuth.getInstance();
        submitAdmin = (Button) findViewById(R.id.submitAdminButtonId);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                final String Name = registerName.getText().toString().trim();
                final String Age = registerAge.getText().toString().trim();
                final String Height = registerHeight.getText().toString().trim();
                final String Password = registerPassword.getText().toString().trim();
                final String Confirm_Password = confirmPassword.getText().toString().trim();
                final String Mobile_Number = mobileNumber.getText().toString().trim();
                final String Email = registerEmail.getText().toString().trim();

                if (radioMale.isChecked()) {
                    Gender = "Male";
                }
                if (radioFemale.isChecked()) {
                    Gender = "Female";
                }

                if (Name.isEmpty()) {
                    registerName.setError("Enter a name");
                    registerName.requestFocus();
                    return;
                }

                if (Age.isEmpty()) {
                    registerAge.setError("Enter a age");
                    registerAge.requestFocus();
                    return;
                }

                if (Height.isEmpty()) {
                    registerHeight.setError("Enter a Height");
                    registerHeight.requestFocus();
                    return;
                }

                if (Mobile_Number.isEmpty()) {
                    mobileNumber.setError("Enter a mobile number");
                    mobileNumber.requestFocus();
                    return;
                }

                if (Password.isEmpty()) {
                    registerPassword.setError("Enter a password");
                    registerPassword.requestFocus();
                    return;
                }

                if (Password.length() < 6) {
                    registerPassword.setError("minimum length of a password should be 6");
                    registerPassword.requestFocus();
                    return;
                }

                if (Confirm_Password.isEmpty()) {
                    confirmPassword.setError("Enter confirm password");
                    confirmPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                if (Email.isEmpty()) {
                    registerEmail.setError(" Enter an email address");
                    registerEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    registerEmail.setError("Enter a valid email address");
                    registerEmail.requestFocus();
                    return;
                }


                if (Password.equals(Confirm_Password)) {


                    firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() { //
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {

                                String key = databaseReference.push().getKey();//new
                                user user = new user(Name, Age, Height, Gender, Email, Password, Mobile_Number);
                                // FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                //   @Override
                                // public void onComplete(@NonNull Task<Void> task) {
                                databaseReference.child(key).setValue(user);


                                Toast.makeText(getApplicationContext(), "register is sucesssful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), UserHomePage.class));

                                //}
                                // });

                                //Toast.makeText(getApplicationContext(),"register is sucesssful", Toast.LENGTH_LONG).show();


                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(getApplicationContext(), "user is already registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });//
                } else {
                    Toast.makeText(getApplicationContext(), "password is not matched", Toast.LENGTH_SHORT).show();
                }


            }
        });

        submitAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("admin");
                final String Name = registerName.getText().toString().trim();
                final String Age = registerAge.getText().toString().trim();
                final String Height = registerHeight.getText().toString().trim();
                final String Password = registerPassword.getText().toString().trim();
                final String Confirm_Password = confirmPassword.getText().toString().trim();
                final String Mobile_Number = mobileNumber.getText().toString().trim();
                final String Email = registerEmail.getText().toString().trim();

                if (radioMale.isChecked()) {
                    Gender = "Male";
                }
                if (radioFemale.isChecked()) {
                    Gender = "Female";
                }

                if (Name.isEmpty()) {
                    registerName.setError("Enter a name");
                    registerName.requestFocus();
                    return;
                }

                if (Age.isEmpty()) {
                    registerAge.setError("Enter a age");
                    registerAge.requestFocus();
                    return;
                }

                if (Height.isEmpty()) {
                    registerHeight.setError("Enter a Height");
                    registerHeight.requestFocus();
                    return;
                }

                if (Mobile_Number.isEmpty()) {
                    mobileNumber.setError("Enter a mobile number");
                    mobileNumber.requestFocus();
                    return;
                }

                if (Password.isEmpty()) {
                    registerPassword.setError("Enter a password");
                    registerPassword.requestFocus();
                    return;
                }

                if (Password.length() < 6) {
                    registerPassword.setError("minimum length of a password should be 6");
                    registerPassword.requestFocus();
                    return;
                }

                if (Confirm_Password.isEmpty()) {
                    confirmPassword.setError("Enter confirm password");
                    confirmPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                if (Email.isEmpty()) {
                    registerEmail.setError(" Enter an email address");
                    registerEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    registerEmail.setError("Enter a valid email address");
                    registerEmail.requestFocus();
                    return;
                }


                if (Password.equals(Confirm_Password)) {


                    firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() { //
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {

                                String key = databaseReference.push().getKey();//new
                                user user = new user(Name, Age, Height, Gender, Email, Password, Mobile_Number);

                                databaseReference.child(key).setValue(user);


                                Toast.makeText(getApplicationContext(), "register is sucesssful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), HomePage.class));




                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(getApplicationContext(), "user is already registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "password is not matched", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }


    }




