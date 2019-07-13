package com.example.learningcurve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth=FirebaseAuth.getInstance();

    }


    protected void onStart() {
        super.onStart();
        FirebaseUser current= FirebaseAuth.getInstance().getCurrentUser();
        if(current== null)
        {
            Intent loginint=new Intent(MainActivity.this,LoginAc.class);
            startActivity(loginint);
            finish();
            }
    }
}
