package com.example.imen.familyshoppinglist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.imen.familyshoppinglist.model.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {
    private static final String TAG = ToDoActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ListTaskAdapter recyclerViewAdapter;
    private EditText addTaskBox;
    private DatabaseReference databaseReference;
    private List<Task> allTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        allTask = new ArrayList<Task>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        addTaskBox = (EditText)findViewById(R.id.add_task_box);
        recyclerView = (RecyclerView)findViewById(R.id.task_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final FloatingActionButton addTaskButton = (FloatingActionButton)findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredTask = addTaskBox.getText().toString();
                if(TextUtils.isEmpty(enteredTask)){
                    Toast.makeText(ToDoActivity.this, "You must enter a task first", Toast.LENGTH_LONG).show();
                    return;
                }
                Task taskObject = new Task(enteredTask);
                databaseReference.push().setValue(taskObject);
                addTaskBox.setText("");
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                taskDeletion(dataSnapshot);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void getAllTask(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
            String taskTitle = singleSnapshot.getValue(String.class);
            allTask.add(new Task(taskTitle));
            recyclerViewAdapter = new ListTaskAdapter(ToDoActivity.this, allTask);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
    private void taskDeletion(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            String taskTitle = singleSnapshot.getValue(String.class);
            for(int i = 0; i < allTask.size(); i++){
                if(allTask.get(i).getTask().equals(taskTitle)){
                    allTask.remove(i);
                }
            }
            Log.d(TAG, "Task tile " + taskTitle);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerViewAdapter = new ListTaskAdapter(ToDoActivity.this, allTask);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}