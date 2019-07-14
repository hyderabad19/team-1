package com.example.learningcurve;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ScrollingTabContainerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.widget.Toast;

public class retreivedata extends AppCompatActivity {
    ListView add;
    Button down;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreivedata);
        storageReference=firebaseStorage.getInstance().getReference();
        down=findViewById(R.id.retrivetn);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                downloadFile();
            }
        });

    }
    public void downloadFile()
    {
        List<String> files=new ArrayList<String>();
        final List<String> response=new ArrayList<String>();
        files.add("images/image:160699");
        files.add("images/image:165620");
        files.add("images/Sampvideo.mp4");

        files.add("images/samppdf.pdf");
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(retreivedata.this, R.layout.row, R.id.textUrl, response);





        for(int i=0;i<files.size();i++)
        {
            Toast.makeText(this, "Retriving", Toast.LENGTH_SHORT).show();
            ref = storageReference.child(files.get(i));
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    response.add(uri.toString());
                   // Toast.makeText(retreivedata.this, "In res"+response, Toast.LENGTH_SHORT).show();
                    Log.d("dbs", "onSuccess: " + "Checking files : " + uri.toString());
                    arrayAdapter.notifyDataSetChanged();
                    //Log.d("dbs", "onSuccess: " + storageReference.getMetadata().toString());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.getMessage();
                    ref.getPath();
                }
            });
        }

        listView.setAdapter(arrayAdapter);
        Log.d("dbs", response.toString());
        //LinearLayout lLayout = (LinearLayout) findViewById(R.id.dynamic);

//        final TextView[] myTextViews = new TextView[response.size()];
//        for (int i = 0; i < response.size(); i++) {
//            // create a new textview
//            final TextView rowTextView = new TextView(this);
//
//            // set some properties of rowTextView or something
//            rowTextView.setText(response.get(i) + i);
//
//            // add the textview to the linearlayout
//            lLayout.addView(rowTextView);
//
//            // save a reference to the textview for later
//            myTextViews[i] = rowTextView;
//        }

    }
}



