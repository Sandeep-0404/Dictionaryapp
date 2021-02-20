package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private EditText emailsandy2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgotpassword);

        emailsandy2 =(EditText)findViewById(R.id.emailsandy1);
        firebaseAuth =FirebaseAuth.getInstance();
    }

    public void forget_password_next(View view) {
        String forgot_password_email = emailsandy2.getText().toString().trim();

        if(forgot_password_email.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter your registered email ID",Toast.LENGTH_SHORT).show();
        }else
        {
            firebaseAuth.sendPasswordResetEmail(forgot_password_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Password reset Email sent",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),dashboard.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Error in sending password reset mail",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}

