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

public class login_signup extends AppCompatActivity {

    private TextView newusertext;
     EditText Emailid,pass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_signup);


        newusertext =(TextView) findViewById(R.id.newuser);
        Emailid =(EditText) findViewById(R.id.emailsandy1);
        pass =(EditText) findViewById(R.id.passwordsandy);
        progressBar =(ProgressBar) findViewById(R.id.progressbarlogin);
        mAuth = FirebaseAuth.getInstance();
        newusertext.setPaintFlags(newusertext.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }

    public void newuser_signup(View view){
        Intent intent =new Intent(this,signup_first.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = Emailid.getText().toString().trim();
        String  password = pass.getText().toString().trim();

        if (email.isEmpty()){
            this.Emailid.setError("Field can't be empty");
            this.Emailid.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.Emailid.setError("Please enter a valid email");
            this.Emailid.requestFocus();
            return;
        }

        if (password.isEmpty()){
            pass.setError("field required");
            pass.requestFocus();
            return;
        }

        if(password.length()<6){
            pass.setError("minimum length of passwword is 6 leter");
            pass.requestFocus();
        }




        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void forgetpassword(View view) {

    }
}
