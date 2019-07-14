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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SetupAcc extends AppCompatActivity {
    private EditText name;
    private EditText sklname;
    private EditText langp;
    private EditText mobileno;
    private Button updbtn;
    private String user_id1;
    private FirebaseAuth firebaseauth;
    private StorageReference storageref;
    private FirebaseFirestore firebasefirestor;
    private ProgressBar pgb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_acc);
        name=findViewById(R.id.setup_name);
        sklname=findViewById(R.id.setup_school);
        langp=findViewById(R.id.setup_lang);
        mobileno=findViewById(R.id.setup_mno);
        updbtn=findViewById(R.id.button_submit);
        firebaseauth= FirebaseAuth.getInstance();
        storageref= FirebaseStorage.getInstance().getReference();
        user_id1= firebaseauth.getCurrentUser().getUid();
        firebasefirestor=FirebaseFirestore.getInstance();
        pgb=findViewById(R.id.progressBar1);
        pgb.setVisibility(View.INVISIBLE);

        firebasefirestor.collection("Users").document(user_id1).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if(task.isSuccessful())
            {

                if(task.getResult().exists())
                   {
                       pgb.setVisibility(View.VISIBLE);
                    String uuname=task.getResult().getString("name");
                    String uuskl=task.getResult().getString("sklname");
                    String uulng=task.getResult().getString("mblno");
                    String uumno=task.getResult().getString("langpref");

                    name.setText(uuname);
                    sklname.setText(uuskl);
                    mobileno.setText(uumno);
                    langp.setText(uulng);

                }
                else
                {
                    Toast.makeText(SetupAcc.this, "Fill Details", Toast.LENGTH_SHORT).show();
                }
                pgb.setVisibility(View.INVISIBLE);
            }
            else
            {
                Toast.makeText(SetupAcc.this, "Firebase retrive error", Toast.LENGTH_SHORT).show();
            }
            }
        });

        updbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=name.getText().toString();
                String usklname=sklname.getText().toString();
                String ulangp=langp.getText().toString();
                String umno=mobileno.getText().toString();
                pgb.setVisibility(View.VISIBLE);
                if( !TextUtils.isEmpty(uname) &&  !TextUtils.isEmpty(umno) && !TextUtils.isEmpty(ulangp) && !TextUtils.isEmpty(usklname) )
                {
                    Map<String,String> hm=new HashMap<>();
                    hm.put("name" ,uname);
                    hm.put("sklname" ,usklname);
                    hm.put("langpref" ,ulangp);
                    hm.put("mblno" ,umno);

                    firebasefirestor.collection("Users").document(user_id1).set(hm).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(SetupAcc.this, "Sucessfully updated", Toast.LENGTH_SHORT).show();
                                    Intent nee=new Intent(SetupAcc.this,MainActivity.class);
                                    startActivity(nee);
                                    finish();

                                }
                                else
                                {
                                    Toast.makeText(SetupAcc.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            pgb.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                else
                {
                    Toast.makeText(SetupAcc.this, "All Fields Are compulsory", Toast.LENGTH_SHORT).show();
                }


            }

        });






    }
}
