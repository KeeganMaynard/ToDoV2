package com.keegan.android.todov2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity  extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert( View v ) {
        // Retrieve task
        EditText taskEditText = findViewById( R.id.input_task );
        EditText dueDateEditText = findViewById(R.id.input_due_date);
        String task = taskEditText.getText( ).toString( );
        String dueDate = dueDateEditText.getText().toString();

        // insert new task in database
        try {
            ToDo toDo = new ToDo(0, task, dueDate);
            dbManager.insert(toDo);
            Toast.makeText( this, "Task added", Toast.LENGTH_SHORT ).show( );
        } catch( NumberFormatException nfe ) {
            Toast.makeText( this, "Task error", Toast.LENGTH_LONG ).show( );
        }

        // clear data
        taskEditText.setText( "" );
        dueDateEditText.setText("");
    }

    public void goBack( View v ) {
        this.finish( );
    }
}
