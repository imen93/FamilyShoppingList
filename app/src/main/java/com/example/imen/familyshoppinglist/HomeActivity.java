package com.example.imen.familyshoppinglist;

import android.content.Intent;
import android.os.Bundle;
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
    private TextView textViewUserEmail;
    private List<String> mList=new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<String>();
        setContentView(R.layout.activity_home);

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

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome ");//+user.getEmail()

        // buttonLogout = (Button) findViewById(R.id.buttonLogout);
        //adding listener to button
       // buttonLogout.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mList.add("aaaaa");
        mList.add("bbbbbbbbb");
        mAdapter = new MyAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


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
