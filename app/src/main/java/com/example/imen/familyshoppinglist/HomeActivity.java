package com.example.imen.familyshoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imen on 11/12/2016.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    //firebase auth object
   // private FirebaseAuth firebaseAuth;

    //view objects
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    ArrayList<String> name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("test");    //+user.getEmail()
        name=new ArrayList<>();
        name.add("imen");
        name.add("sisters");
        name.add("mum");
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        adapter=new MyAdapter(this,name);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //initializing firebase authentication object
      /*  firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
     /*   if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }*/

        //getting current user
       // FirebaseUser user = firebaseAuth.getCurrentUser();


        // buttonLogout = (Button) findViewById(R.id.buttonLogout);
        //adding listener to button
       // buttonLogout.setOnClickListener(this);




        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              name.add("cccccccccccc");
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
      /*  //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }*/
    }
}
