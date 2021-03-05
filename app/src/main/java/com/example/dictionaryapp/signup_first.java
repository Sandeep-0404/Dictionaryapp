package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signup_first extends AppCompatActivity {

    private TextView create;
    private EditText username ,email, password;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_first);

        create =(TextView)findViewById(R.id.createaccount);
        create.setPaintFlags(create.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        username =(EditText) findViewById(R.id.usernamesandy);
        email =(EditText) findViewById(R.id.emailsandy);
        password =(EditText) findViewById(R.id.passwordsandy);
        progressbar =(ProgressBar) findViewById(R.id.progressbarlogin);
        mAuth = FirebaseAuth.getInstance();
    }




    //Button clicked----
    public void goto_otp(View view) {

       String usernametext = username.getText().toString().trim();
       String emailtext = email.getText().toString().trim();
       String passwordtext = password.getText().toString().trim();

       // ALL THE VALIDATIONS-----


        //EMAIL VALIDATIONS--
        if (emailtext.isEmpty()){
            this.email.setError("Field can't be empty");
            this.email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
            this.email.setError("Please enter a valid email");
            this.email.requestFocus();
            return;
        }


        //PASSWORD VALIDATIONS----

        if (passwordtext.isEmpty()){
            password.setError("field required");
            password.requestFocus();
            return;
        }

        if(passwordtext.length()<6){
            password.setError("minimum length of password is 6 leter");
            password.requestFocus();
        }


        //USERNAME VALIADTIONS----
        if(usernametext.isEmpty()){
            username.setError("Field can't be Empty");
            username.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);



        mAuth.createUserWithEmailAndPassword(emailtext, passwordtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                progressbar.setVisibility(View.GONE);

                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registered Succesfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("username",usernametext);
                    intent.putExtra("emailid",emailtext);
                    intent.putExtra("password",passwordtext);
                    startActivity(intent);

                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Some error occured", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}

