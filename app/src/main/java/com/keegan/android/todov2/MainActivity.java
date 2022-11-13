package com.keegan.android.todov2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbManager = new DatabaseManager(this);
        scrollView = findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x;
        updateView();
    }

    protected void onResume()
    {
        super.onResume();
        updateView();
    }

    public void updateView()
    {
        ArrayList<ToDo> tasks = dbManager.selectAll();
        if(tasks.size() > 0)
        {
            scrollView.removeAllViewsInLayout();

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(tasks.size());
            grid.setColumnCount(2);

            ToDoButton[] buttons = new ToDoButton[tasks.size()];

            int i = 0;
            for(ToDo task : tasks)
            {
                buttons[i] = new ToDoButton(this, task);
                String toString = task.getTask() + " " + task.getDueDate();
                buttons[i].setText(toString);
                if(buttons[i].checkDate(task.getDueDate()))
                {
                    buttons[i].setTextColor(Color.RED);
                }

                grid.addView(buttons[i], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId( );
        switch ( id ) {
            case R.id.action_add:
                Intent insertIntent = new Intent( this, InsertActivity.class );
                this.startActivity( insertIntent );
                return true;
            case R.id.action_delete:
                Intent deleteIntent = new Intent( this, DeleteActivity.class );
                this.startActivity( deleteIntent );
                return true;
            case R.id.action_exit:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }
}