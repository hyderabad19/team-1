package com.example.learningcurve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterAc extends AppCompatActivity {
    private EditText regemail;
    private EditText regpassword;
    private EditText regconfpass;
    private Button regbtn;
    private Button regloginbtn;
    private FirebaseAuth mauth;

    private ProgressBar loginprogbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regemail = (EditText) findViewById(R.id.reg_email);
        regpassword = (EditText) findViewById(R.id.reg_pass);
        regconfpass = (EditText) findViewById(R.id.reg_conformpass);
        regbtn = (Button) findViewById(R.id.reg_btn);
        regloginbtn = (Button) findViewById(R.id.reg_login_btn);
        loginprogbar = (ProgressBar) findViewById(R.id.reg_prog_bar);
        mauth=FirebaseAuth.getInstance();

        loginprogbar.setVisibility(View.INVISIBLE);
        regloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logi =new Intent(RegisterAc.this,LoginAc.class);
                startActivity(logi);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = regemail.getText().toString();
                String pass = regpassword.getText().toString();
                String confpass = regconfpass.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confpass)) {

                    if (pass.equals(confpass)) {
                        loginprogbar.setVisibility(View.VISIBLE);
                        mauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent set=new Intent(RegisterAc.this,SetupAct.class);
                                    startActivity(set);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(RegisterAc.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                                loginprogbar.setVisibility(View.INVISIBLE);

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(RegisterAc.this, "Passwords doesnt match", Toast.LENGTH_SHORT).show();
                    }

                    }
                else
                {
                    Toast.makeText(RegisterAc.this, "Make sure that all fieds are filled", Toast.LENGTH_SHORT).show();
                }

                }
        });
    }
}
