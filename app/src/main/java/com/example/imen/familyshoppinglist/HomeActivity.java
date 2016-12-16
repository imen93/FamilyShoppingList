package com.example.imen.familyshoppinglist;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by imen on 11/12/2016.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    //firebase auth object
   // private FirebaseAuth firebaseAuth;

    //view objects
    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    ArrayList<String> name ;
    String[] DayOfWeek = {"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("test");    //+user.getEmail()
        name=new ArrayList<>();

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        adapter=new GroupAdapter(this,name);
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

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater =
                        (LayoutInflater)getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.popup_layout, null);
                final PopupWindow popupWindow;
                popupWindow = new PopupWindow(popUpView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
                popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                popupWindow.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
               /* View popupView = layoutInflater.inflate(R.layout.popup_layout, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                //Update TextView in PopupWindow dynamically
                String stringOut ="test"; //textIn.getText().toString();
                if(!stringOut.equals("")){
                    textOut.setText(stringOut);
                }*/

                final TextView textOut = (TextView)popUpView.findViewById(R.id.groupName);

                Button createButton = (Button)popUpView.findViewById(R.id.create);
                Button cancelButton = (Button)popUpView.findViewById(R.id.cancel);

                Spinner popupSpinner = (Spinner)popUpView.findViewById(R.id.popupspinner);

                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(HomeActivity.this,
                                android.R.layout.simple_spinner_item, DayOfWeek);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                popupSpinner.setAdapter(adapter);

                createButton.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        name.add(textOut.getText().toString());
                        adapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }});
                cancelButton.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});
                adapter.notifyDataSetChanged();
                popupWindow.showAsDropDown(fab,50,100); //AsDropDown(btnOpenPopup, 50, -30)
                popupWindow.setFocusable(true);


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_item, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
            /* DO EDIT */
                return true;
            case R.id.action_add:
            /* DO ADD */
                return true;
            case R.id.action_delete:
            /* DO DELETE */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }}
