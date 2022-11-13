package com.keegan.android.todov2;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoButton extends AppCompatButton {
    private ToDo toDo;

    public ToDoButton(Context context, ToDo newToDo)
    {
        super(context);
        toDo = newToDo;
    }

    public boolean checkDate(String date)
    {
        boolean pastDue = false;
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date dueDate = format.parse(date);
            pastDue = new Date().after(dueDate);
        } catch(java.text.ParseException e){
            e.printStackTrace();
        }

        return pastDue;
    }
}
